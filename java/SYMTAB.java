// public class testingOptab {
//
//    public static void main(String args[]){
//    SYMTAB symTable = new SYMTAB();
//
//    symTable.createSymItem("DRESSER", "1000");
//    symTable.createSymItem("HAPPY", "1200");
//    symTable.createSymItem("CREATE", "4334");
//    symTable.createSymItem("ALPHA", "4334");
//    symTable.createSymItem("OMEGA", "4334");
//    symTable.createSymItem("OMEGA", "4334");
//    symTable.createSymItem("OMEGA", "4334");
//
//
//    symTable.displayTable();
//
//
//    }//main
// }//testingOptab



class SymItem{
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

class SYMTAB
{
   private SymItem[] SYMARRAY;
   private int arraySize;
   private int symCount;


   public SYMTAB(){

    //get ArraySize as akin to a Hash Table
    arraySize = getPrime(50);//array size is the first prime number of twice the number of mnumonics
    SYMARRAY = new SymItem[arraySize];//will create a prime sized array based on the number of mnumonics


   }//constuctor OPTAB
   public void displayTable()
   {
      String header = "Index  String\n";
      String row = "%-7d%-20s\n";
      float probeSum = 0;
      int numItems = 0;

      System.out.println("\nSYMTAB");
      System.out.print(header);
      for(int j=0; j < arraySize; j++)
      {
         if(SYMARRAY[j] != null && SYMARRAY[j].getKey() != "-1")
         {
            //System.out.print(String.format(row, j, SYMARRAY[j].getKey()));
            //
            SYMARRAY[j].printSymItem(j);
            numItems++;
         }
      }
   }//displayTable


   public void createSymItem(String label, String address){
       SymItem item = new SymItem(label, address);
       insert(item);
   }

   public void insert(SymItem item)
   {
      String key = item.getKey();
      int hashVal = hashFunc(key);
      int k = 0;

   //until empty cell or nonItem
      while(SYMARRAY[hashVal] != null && SYMARRAY[hashVal].getKey() != "-1")
      {
         //quadratic probe
         hashVal += (2*k+1);
         ++k;
         hashVal %= arraySize;//wraparound if needed
      }//while
      SYMARRAY[hashVal] = item;//insert new item
      symCount++;//increase symCount
   }//insert



   public int hashFunc(String key)
   {
      int hashVal = 0;
      for(int j=0; j<key.length(); j++)
      {
         int letter = key.charAt(j); //get char code
         hashVal = (hashVal * 26 + letter) % arraySize; //mod
      }
      return hashVal;
   }//hashFunc


   public SymItem find(String key)
   {
      int hashVal = hashFunc(key);
      int k = 0;

      while(SYMARRAY[hashVal] != null)
      {
         if(SYMARRAY[hashVal].getKey().equals(key))
            return SYMARRAY[hashVal];
         //quadratic probe
         hashVal += (2*k+1);
         ++k;
         hashVal %= arraySize; //wraparound

      }//while
      return null; //couldn't find it
   }//find


   public int findProbeLength(String key)
   {
      int hashVal = hashFunc(key);
      int probeLength = 1;//initialize to 1
      int k = 0;

      while(SYMARRAY[hashVal] != null)
      {
         if(SYMARRAY[hashVal].getKey().equals(key))
            return probeLength;
         //quadratic probe
         hashVal += (2*k+1);
         ++k;
         ++probeLength;//increase probeLength
         hashVal %= arraySize; //wraparound

      }//while
      return probeLength; //couldn't find it
   }//find



   public static int getPrime(int min)
   {
      for(int j = min+1; true; j++)
      {
         if( isPrime(j) )
            return j;
      }//for
   }//getPrime

   public static boolean isPrime(int n)
   {
      for(int j = 2; (j*j <= n); j++)
      {
         if( n % j == 0)
            return false;
      }//for
      return true;
   }//iPrime


}//SYMTAB
