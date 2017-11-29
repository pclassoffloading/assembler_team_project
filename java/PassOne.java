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
        try{
          DataItem et = optable.find(operation);
          //et.printDataItem();

          this.symtab.createSymItem(item.label, LOCCTR);
          DataItem temp = this.optable.find(item.mnemonic);

          System.out.println("mneumonic: " + find_format(temp).mnumonic + " formatN: " + find_format(temp).formatN + " format_4: " + find_format(temp).isFour);

        }catch (Exception e) {};
      }

      //System.out.println(operation.getMnumonic());System.out.println(operation.getFormatN());System.out.println(operation.getOpcode());
   }
   public DataItem find_format(DataItem temp){
     //temp.formatN, temp.mnumonic

     switch (temp.formatN) {
        case "2": //not_sure_yet();
           return temp; // do I need to return???
        case "3": temp = is_format_4(temp);
           return temp;
     }
     //System.out.println("passone, ln 41 reached");
     return temp;
   }

   public DataItem is_format_4(DataItem temp){
     if(temp.mnumonic.charAt(0) == '+')
     {
        temp.setIsFour(true);
        temp.mnumonic = temp.mnumonic.substring(1);
        return temp;
     }
     else{
        temp.setIsFour(false);
        return temp;
     }
 }


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
