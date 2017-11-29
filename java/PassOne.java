//import input.*;
//import Assembler_Application
class PassOne{
  	//String label, mnemonic, symbol;
  Source_line source_lines[];
   PassOne(OPTAB optable, Source_line source_lines[]){
      this.source_lines = source_lines;

      for (Source_line item : source_lines) {
        System.out.println(item.mnemonic);
        String operation = item.mnemonic;
        try{DataItem et = optable.find(operation);
        et.printDataItem();}catch (Exception e) {};
      }
      //System.out.println(operation.getMnumonic());System.out.println(operat3ion.getFormatN());System.out.println(operation.getOpcode());
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
// if(OPTAB.find(operation) != null)
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
