//import input.*;
//import Assembler_Application
class PassOne{
  	//String label, mnemonic, symbol;
   Source_line source_lines[];
   SYMTAB symtab;
   OPTAB optable;
   boolean isFour;
   PassOne(OPTAB optable, Source_line source_lines[], SYMTAB symtab){
      this.optable = optable;
      this.source_lines = source_lines;
      this.symtab = symtab;
      String LOCCTR = "0";
   
      for (Source_line item : source_lines) {
        //System.out.println(item.mnemonic);
         String operation = item.mnemonic;
        //if mnemonic is START then we are at the start of the program and need to set LOCCTR
         if(operation.equals("START")){
         //Set LOCCTR to starting address
         //i.e SUM START 100 is a program whose name is SUM and LOCCTR starts at 100
            LOCCTR = item.symbol;
         //we should store the name of the program and starting address somewhere to access for printing the obj file later
         }
         else{
            try{
               this.symtab.createSymItem(item.label, LOCCTR);
               DataItem temp = this.optable.find(item.mnemonic);
               temp = find_format(temp, item.mnemonic);
               temp.printDataItem();
               System.out.println("mneumonic: " + temp.mnumonic + " formatN: " + temp.formatN + " format_4: " + temp.isFour);
            }
            catch (Exception e) {};
         }
      }
   
      //System.out.println(operation.getMnumonic());System.out.println(operation.getFormatN());System.out.println(operation.getOpcode());
   }
   //or should it be sourceline? <<<<<<< Yes should be in source_line
   public DataItem find_format(DataItem temp, String mneumonic){
     //temp.formatN, temp.mnumonic
   
      switch (temp.formatN.charAt(0)) {
         case '2': //not_sure_yet();
         
            return temp; // do I need to return???
         case '3': //temp = is_format_4(temp, mneumonic);
         
            return temp;
      }
     //System.out.println("passone, ln 41 reached");
      return temp;
   }
   
   //NO this is not how we do it
//    public DataItem is_format_4(DataItem temp, String mneumonic){
//      if(temp.mnumonic.charAt(0) == '+')
//      {
//        System.out.println("true reached");
//         temp.setIsFour(true);
//         temp.mnumonic = mneumonic.substring(1);
//         return temp;
//      }
//      else{
//               System.out.println("false reached");
//         temp.setIsFour(false);
//         return temp;
//      }
//  }


}
//This program simulates Pass 1
//
//
//Pass 1
//
//Look at LOCCTR
//for each sourceline
//    operation = sourceLine.getMnumonic;
//if operation is in OPTAB
// if(OPTAB.find(operation) != Null)
//    SYMTAB.createSymItem(sourceline.getLabel, LOCCTR);
//    SymItem temp = SYMTAB.find(sourceline.getLabel);
//    addressSpace = temp.getFormatN
//    LOCTRR += addressSpace
//
//if operation is in i.e WORD RESW BYTE RESBYTE
//    These calculations are done in Hexadecimal arithmatic
//
//    if WORD
//       LOCCTR + 3
//    else if BYTE
//       LOCCTR + 1
//    else if RESW
//       LOCCTR += convertToHex(sourceline.getLabel * 3); //every word is three bytes
//    else if RESBYTE
//       LOCCTR += convertToHex(sourceline.getLabel);
//
