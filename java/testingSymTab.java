public class testingSymTab{

   public static void main(String args[]){
      SYMTAB symTable = new SYMTAB();
      symTable.createSymItem("LENGTH", "00003");
      SymItem temp = symTable.find("LENGTH");
   }
}