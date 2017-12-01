//import input.*;
//import Assembler_Application
class PassOne{
  	//String label, mnemonic, symbol;
   Source_line source_lines[];
   SYMTAB symtab;
   OPTAB optable;
   boolean isFour;
   String LOCCTR = "0";
   Math mathLib;

   PassOne(OPTAB optable, Source_line source_lines[], SYMTAB symtab, Math test, String fileName){
      System.out.println("passone" + fileName);
      this.optable = optable;
      this.source_lines = source_lines;
      this.symtab = symtab;
      this.mathLib = test;
// Pass1();


 }
 public Source_line[] Pass1()
 {
   return provide_source_lines(construct_map_addresses());
 }
 public Source_line[] provide_source_lines(Source_line[] source_lines){
   return source_lines;
 }
 public SYMTAB provide_symtable()
 {
   return this.symtab;
 }
 public Source_line[] construct_map_addresses(){
   int count = 0;

   for (Source_line item : this.source_lines) {//System.out.println(item.mnemonic);//if mnemonic is START then we are at the start of the program and need to set LOCCTR
     item.sourceline_number = count++;

     try{
      if(item.label != null){this.symtab.createSymItem(item.label, this.LOCCTR);};
       if(item.mnemonic.equals("START")){//Set LOCCTR to starting address//i.e SUM START 100 is a program whose name is SUM and LOCCTR starts at 100
         this.LOCCTR = item.symbol;//we should store the name of the program and starting address somewhere to access for printing the obj file later
         item.set_address(this.LOCCTR);
      }
      //else if operation is in i.e WORD RESW BYTE RESBYTE
      else{//format is of LABEL MNEMONIC SYMBOL//If the mnumonic is within OPTAB we can simply determine how many addresses the line uses by the FormatN//i.e Format 1 is 1, Format 2 is 2, Format 3 is 3, and Format 4 is 4 bytes
        item.set_address(this.LOCCTR);
        //System.out.println(item.mnemonic);
        //    symTable.createSymItem("HAPPY", "1200");
        if(item.mnemonic.equals("WORD")){this.LOCCTR = mathLib.addHextoHex("3", this.LOCCTR);                                                      } //this.symtab.createSymItem(item.label, this.LOCCTR);}
        if(item.mnemonic.equals("RESW")){this.LOCCTR = mathLib.addHextoHex(mathLib.convertIntToHex(mathLib.toInt(item.symbol) * 3), this.LOCCTR);  } //this.symtab.createSymItem(item.label, this.LOCCTR);}
        if(item.mnemonic.equals("BYTE")){byteCondition(item.symbol.charAt(0), item.symbol);                                                        } //this.symtab.createSymItem(item.label, this.LOCCTR);}
        if(item.mnemonic.equals("RESB")){this.LOCCTR = mathLib.addHextoHex(mathLib.convertIntToHex(mathLib.toInt(item.symbol) * 1), this.LOCCTR);  } //this.symtab.createSymItem(item.label, this.LOCCTR);}

        boolean exist;
        DataItem temp;
                  //System.out.println(item.address);
        if(item.mnemonic.charAt(0) == '+'){
          temp = this.optable.find(item.mnemonic);
          this.LOCCTR = mathLib.addHextoHex("4", this.LOCCTR);

        }
        else{
          temp = this.optable.find(item.mnemonic);
        }
        //System.out.println(item.mnemonic);
        switch (temp.formatN.charAt(0)) {
         case '1': if(does_mnemonic_exist(item, item.mnemonic)){
             this.LOCCTR = mathLib.addHextoHex("1", this.LOCCTR);
             //item.set_address(this.LOCCTR);
             //System.out.println(item.address);
           }
           break;
         case '2': if(does_mnemonic_exist(item, item.mnemonic)){
             this.LOCCTR = mathLib.addHextoHex("2", this.LOCCTR);
             //item.set_address(this.LOCCTR);
             //System.out.println(item.address);
           }
           break;
         case '3':
          this.LOCCTR = mathLib.addHextoHex(check_if_four(item.mnemonic.substring(1)), this.LOCCTR);
          //item.set_address(this.LOCCTR);

          break;
         default:

          item.set_address(this.LOCCTR);
          break;
        }
      }


   //System.out.println(operation.getMnumonic());System.out.println(operation.getFormatN());System.out.println(operation.getOpcode());
    }catch (Exception e) {};
  }
  return source_lines;
}
public String check_if_four(String mnemonic){
    if(mnemonic.charAt(0) == '+')
    {
      //System.out.println("reauihiux");
      return "4";
    }
    else{
      //System.out.println("dadnasbndnb");
      return "3";
    }

}

public String byteCondition(char i, String stringy){
  switch (i)
  {
    case 'C':
    return Integer.toString(stringy.split("\\'")[1].length());
    case 'X':
    return "1";
  }
  return "N";
}

// case '+':
// if(does_mnemonic_exist(item, item.mnemonic)){
//     this.LOCCTR = mathLib.addHextoHex("3", this.LOCCTR);
//     item.set_address(this.LOCCTR);
//     System.out.println(item.address);
//
//
// System.out.println("reached");
//   exist = does_mnemonic_exist(item, item.mnemonic.substring(1));
//   this.LOCCTR = mathLib.addHextoHex("4", this.LOCCTR);
//   item.set_address(this.LOCCTR);//item.set_format4();//this.set_format(4);//format assigned to sourceline.
//   System.out.println(item.address);
// break;
// }


   // if(this.optable.find(item.mnemonic) != null){//grab temp object
   //   DataItem temp = this.optable.find(item.mnemonic);//local formatNumber string set to formatN of DataItem
   //   String formatNumber = temp.formatN;//if 3/4 need to determine if format 4 was declared
   //   if((temp.formatN).equals("3/4")){}
   //   temp = find_format(temp, item.mnemonic);
   //   temp.printDataItem();
   //   System.out.println("mneumonic: " + temp.mnumonic + " formatN: " + temp.formatN + " format_4: " + temp.isFour);
   //   this.symtab.createSymItem(item.label, LOCCTR);
   // }

   public boolean does_mnemonic_exist(Source_line item, String mnemonic){
    if(this.optable.find(mnemonic) != null){//grab temp object
      return true;
    }
    else{
      return false;
       //if the mnemonic was not in OPTAB then we check if is a 'variable declaration' i.e. WORD RESW BYTE
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
