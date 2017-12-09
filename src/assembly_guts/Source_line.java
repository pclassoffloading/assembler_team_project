package src.assembly_guts;
import src.input_output.*;
public class Source_line{
   public String label, mnemonic, symbol, objectCode;
   public String address = "";
   public int format;
   public boolean isFour = false;//default is false
   public String note = "";
   public boolean isIndexed = false;//default
   public int sourceline_number = 0;
   public Output writefile;

   String n = "";
   String i = "";
   String x = "";
   String b = "";
   String p = "";
   String e = "";

   public Source_line(){
     this.writefile = new Output();
   }
   public void add_To_Note(String note){
     this.note = this.note + " " + note;
   }
   public void show_Notes()
   {
     System.out.println(this.note);
   }
   public void add_word(int total_line_count, int word_numb, String word){
      evaluate_total_line_count(word, word_numb, total_line_count);
   }
   public void tell_source_line(){
      System.out.printf("address: %s, label: %s, mnemonic: %s, symbol: %s, note: %s,  %n",this.address, this.label,this.mnemonic,this.symbol, this.note);
   }
   public void tell_lst_file(String filename){

      String line_numb = "--"+Integer.toString(this.sourceline_number);

      System.out.printf(" %s %s %s %s %s %s %s %n", fomat(line_numb).substring(fomat(line_numb).length()-3), fomat(this.address).substring(fomat(this.address).length()-4), fomat(this.objectCode), fomat(this.label),fomat(this.mnemonic),fomat(this.symbol), this.note);
      writefile.write_file(filename, ".lst", String.format(" %s %s %s %s %s %s %s %n", fomat(line_numb).substring(fomat(line_numb).length()-3), fomat(this.address).substring(fomat(this.address).length()-4), fomat(this.objectCode), fomat(this.label),fomat(this.mnemonic),fomat(this.symbol), this.note));
   }
   public String fomat(String format_this){
      try{
        return ("             " + format_this).substring(format_this.length());
      }catch(NullPointerException e){};
      return "         null";
   }

   public void evaluate_total_line_count(String word, int word_numb, int total_line_count){
   	//System.out.printf("label: %s, mnemonic: %s, symbol: %s %n", word, word_numb, total_line_count);
      switch (total_line_count) {
         case 0: this.mnemonic = word;
            break;
         case 1: tot_2_evaluate_word_numb(word, word_numb);
            break;
         case 2: tot_3_evaluate_word_numb(word, word_numb);
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
