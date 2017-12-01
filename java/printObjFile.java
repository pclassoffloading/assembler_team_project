import java.io.*;
class printObjFile{

   Math mathLib;

   public printObjFile(Source_line[] sourcelines, Math test){
      this.mathLib = test;
   
      //header file
      printHeader(sourcelines);
      printTextRecords(sourcelines);
      printModificationRecords(sourcelines);
      printEndRecord(sourcelines);
      
   }
   
   public void printHeader(Source_line[] sourcelines){
      System.out.format("%c%-6s%-6s%-6s\n", 'H', sourcelines[0].label, padString(sourcelines[0].get_address()), padString(sourcelines[sourcelines.length-1].get_address()));
      //System.out.println("H" + sourcelines[0].label + sourcelines[0].get_address() + sourcelines[sourcelines.length-1].get_address());
   }
   
   public void printTextRecords(Source_line[] sourcelines){
      
      String textRecord = "T";
      String addressStart = padString(sourcelines[0].get_address());
      textRecord = textRecord + addressStart;
      int tCounter = 0;
      
      for(int c = 1; c < sourcelines.length; c++){
      
         if(sourcelines[c].objectCode != null){
            tCounter += sourcelines[c].objectCode.length();
            if(tCounter > 60){
               System.out.println(textRecord);
               tCounter = 0;
               textRecord = "T" + sourcelines[c].objectCode;
            }//if
            else{
               textRecord = textRecord + sourcelines[c].objectCode;  
            }//else
         }//if
      }//for
      textRecord = textRecord.substring(0,7) + mathLib.convertIntToHex(tCounter/2) + textRecord.substring(7, textRecord.length());
      System.out.println(textRecord);
   }
   
   public void printModificationRecords(Source_line[] sourcelines){
   
      for(int c = 1; c < sourcelines.length; c++){
         if(sourcelines[c].isFour){
            String address = mathLib.addHextoHex(sourcelines[c].get_address(), "1");
            System.out.format("%c%-6s%2s\n",'M', padString(address), "05");
         }
      }
   }
   
   public void printEndRecord(Source_line[] sourcelines){
      System.out.println("E" + padString(sourcelines[0].get_address()));
   }
   
   public String padString(String s){
      return "000000".substring(s.length()) + s;
   }
   
 //  String padded = unpadded + "##########".substring(unpadded.length());
   
//    public void tell_lst_file(){
// 
//       String line_numb = "--"+Integer.toString(this.sourceline_number);
//       System.out.printf(" %s %s %s %s %s %s %n", fomat(line_numb).substring(fomat(line_numb).length()-3), fomat(this.address).substring(fomat(this.address).length()-4), fomat(this.objectCode), fomat(this.label),fomat(this.mnemonic),fomat(this.symbol), fomat(this.note));
//    }
//    public String fomat(String format_this){
//       try{
//         return ("             " + format_this).substring(format_this.length());
//       }catch(NullPointerException e){};
//       return "         null";
//    }
}