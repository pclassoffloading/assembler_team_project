class OPTAB
{
   private DataItem[] OPARRAY;
   private int arraySize;
   private DataItem nonItem;

   public OPTAB(String dictionary){

    //parse String Dictionary into tokens[]
      String delims = "[ ]+";
      String[] tokens = dictionary.split(delims);

    //define what a non-item is
      nonItem = new DataItem("-1", "", "");


    //get ArraySize as akin to a Hash Table
      arraySize = getPrime(tokens.length/3 * 2);//array size is the first prime number of twice the number of mnumonics
      OPARRAY = new DataItem[arraySize];//will create a prime sized array based on the number of mnumonics

      for(int i = 0; i < tokens.length; i=i+3){
         DataItem item = new DataItem(tokens[i], tokens[i+1], tokens[i+2]);
         insert(item);

      }//for

   }//constuctor OPTAB

   public void insert(DataItem item){
     String key = item.getKey();
     int hashVal = hashFunc(key);
     int k = 0;//until empty cell or nonItem
     while(OPARRAY[hashVal] != null && OPARRAY[hashVal].getKey() != "-1"){
         //quadratic probe
         hashVal += (2*k+1);
         ++k;
         hashVal %= arraySize;//wraparound if needed
      }//while
      OPARRAY[hashVal] = item;
   }//insert

   public int hashFunc(String key){
      int hashVal = 0;
      for(int j=0; j<key.length(); j++)
      {
         int letter = key.charAt(j); //get char code
         hashVal = (hashVal * 26 + letter) % arraySize; //mod
      }
      return hashVal;
   }//hashFunc

   public DataItem find(String key){
      int hashVal = hashFunc(key);
      int k = 0;

      while(OPARRAY[hashVal] != null)
      {
         if(OPARRAY[hashVal].getKey().equals(key))
            return OPARRAY[hashVal];
         //quadratic probe
         hashVal += (2*k+1);
         ++k;
         hashVal %= arraySize; //wraparound

      }//while
      return null; //couldn't find it
   }//find

   public static int getPrime(int min){
      for(int j = min+1; true; j++)
      {
         if( isPrime(j) )
            return j;
      }//for
   }//getPrime

   public static boolean isPrime(int n){
      for(int j = 2; (j*j <= n); j++)
      {
         if( n % j == 0)
            return false;
      }//for
      return true;
   }//iPrime


}//OPTAB
