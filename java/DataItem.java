class DataItem{
   public String mnumonic;
   public String opcode;
   public String formatN;


   public DataItem(String mnumonic, String formatN, String opcode){
      this.mnumonic = mnumonic;
      this.formatN = formatN;
      this.opcode = opcode;

   }//constructor DataItem

   public void printDataItem(){
      System.out.println(mnumonic);
      System.out.println(opcode);
      System.out.println(formatN);

   }

   public String getKey(){
      return this.mnumonic;
   }

   public String getMnumonic(){
      return this.mnumonic;
   }

   public String getOpcode(){
      return this.opcode;
   }

   public String getFormatN(){
      return this.formatN;
   }




}//DataItem
