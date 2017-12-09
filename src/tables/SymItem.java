package src.tables;
public class SymItem{
   String label;
   String address;

   public SymItem(String label, String address){
      this.label = label;
      this.address = address;
   }//constructor SymItem

   public void printSymItem(int ind_of_hash){
      System.out.println("label: " + label + " address: " + address + " hash index: " + ind_of_hash);

   }//printSymItem

   public String getKey(){
      return label;
   }//getKey

   public String get_address(){
      return address;
   }

   public String get_label(){
      return label;
   }

}//SymItem
