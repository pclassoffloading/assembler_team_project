class printObjFile{

   public printObjFile(Source_line[] sourcelines){
      //header file
      printHeader(sourcelines);
      
      System.out.print("T");
      int tCounter = 0;
      
      for(int c = 1; c < sourcelines.length; c++){
      //get format number of sourceline
         tCounter += sourcelines[c].format;
         if(tCounter > 30){
            System.out.println("T");
            System.out.print(sourcelines[c].objectCode);
         }
         else{
            System.out.print(sourcelines[c].objectCode);
         }

      }
      
   }
   
   public void printHeader(Source_line[] sourcelines){
      System.out.println("H" + sourcelines[0].label + sourcelines[0].get_address() + sourcelines[sourcelines.length-1].get_address());
   }
   
   public void printTextRecords(Source_line[] sourcelines){
   
   }
}