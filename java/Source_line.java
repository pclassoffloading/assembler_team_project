
class Source_line{
   String label, mnemonic, symbol, address, objectCode;
   int format;
   boolean isFour = false;//default is false

   public Source_line(){
   }
   public void add_word(int total_line_count, int word_numb, String word){
      evaluate_total_line_count(word, word_numb, total_line_count);
   }
   public void tell_source_line(){
      System.out.printf("label: %s, mnemonic: %s, symbol: %s %n",label,mnemonic,symbol);
   }

   public void evaluate_total_line_count(String word, int word_numb, int total_line_count){
   	//System.out.printf("label: %s, mnemonic: %s, symbol: %s %n", word, word_numb, total_line_count);
      switch (total_line_count) {
         case 1: this.mnemonic = word;
            break;
         case 2: tot_2_evaluate_word_numb(word, word_numb);
            break;
         case 3: tot_3_evaluate_word_numb(word, word_numb);
            break;
      }
   }
   public void tot_2_evaluate_word_numb(String word, int word_numb){
      switch (word_numb) {
         case 0: this.mnemonic = word;
            break;
         case 1: this.symbol = word;
            break;
      }
   }
   public void tot_3_evaluate_word_numb(String word, int word_numb){
      switch (word_numb) {
         case 0: this.label = word;
            break;
         case 1: this.mnemonic = word;
            break;
         case 2: this.symbol = word;
            break;
      }
   }

   public String get_mnemonic(){
      return this.mnemonic;
   }
   public String get_label(){
      return this.label;
   }
   public String get_symbol(){
      return this.symbol;
   }
   public String get_address(){
      return this.address;
   }
   public void set_address(String address){
      this.address = address;
   }

   public void set_objectCode(String objectCode){
      this.objectCode = objectCode;
   }

   public void set_format4(){
      this.isFour = true;
   }
   public void set_format(int format){
      this.format = format;
   }
//addresses(pass1) opcode(pass2)-- output variables
}
