class printObjFile{

   public printObjFile(Source_line[] sourcelines){
      //header file
      printHeader(sourcelines);
      printTextRecords(sourcelines);
      
   
      
   }
   
   public void printHeader(Source_line[] sourcelines){
      System.out.println("H" + sourcelines[0].label + sourcelines[0].get_address() + sourcelines[sourcelines.length-1].get_address());
   }
   
   public void printTextRecords(Source_line[] sourcelines){
      
      String textRecord = "T";
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
      System.out.println(textRecord);
   
   }
}