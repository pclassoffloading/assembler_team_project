//This program simulates Pass 2
//
//Pass 2
//
class Pass2{

   public String opjectCode; //what this class should return to main
   public String n,i,x,b,p,e; //nixbpe bits used for format 3/4 instructions
   public boolean isFour;
   public String objectCode;
   SYMTAB symtable;
   OPTAB optable;
   Math test;


   public void Pass2(OPTAB optable, SYMTAB symtable, Source_line[] sourcelines, Math test){
      this.symtable = symtable;
      this.optable = optable;
      this.test = test;
      
      pass2_assembly();
      
   }//Pass2
      
      
   public void math_example()
   {
      String testy = this.test.convertIntToHex(17099800);
      this.test.convertHexToInt(testy);
          //this.test.addHextoHex("104ec18", "104ec10"); // value put into system.out.println below
      System.out.println("HERE IS YOUR NEW HEX VALUE: " + this.test.addHextoHex("104ec18", "104ec10"));
   }
      
   public void pass2_assembly(){
      for(int i = 0; i < sourcelines.length; i++){
      
      //    get LOCCTR -- public class variable
      
         String operation = sourcelines[i].get_mnemonic();
         //check if is format 4
         if(operation.charAt(0) == '+')
         {
            isFour = true;
            operation = operation.substring(1);
         }
         else{
            isFour = false;
         }
         //if operation exists in OPTAB (i.e. ADD, STA, etc)
         if(optable.find(operation) != null){
            //grap opcode
            String opcode = optable.find(operation).getOpcode();      
            //get format
            String format = optable.find(operation).getFormatN();
         
            switch(format){
               //format 1
               case "1":
                  objectCode = opcode;
                  break;
               //format 2
               case "2":
                  break;
               //format 3 or 4
               case "3/4":
                  determineAddressing(sourcelines[i]);
                  //if is three
                  if(!(isFour)){
                     e = "0";
                     //First try PC mode
                     PCMODE();
                  //                if PC mode fail
                  //                   try BASE mode which is called in PCMODE
                  //                      if BASE mode fail
                  //                         fail source line, print error "format 4 needed for instruction"
                  }//if is three
                  
                  //if is four
                  else{
                     e = "1";
                  //find target address
                     String targetAddress = (symtable.find(sourcelines[i].get_symbol())).get_address();
                  
                  //                format of object code is four bytes: ## ## ## ##
                  //                first six bits is opcode code
                  //                next six bits is nixbpe
                  //                next 20 bits are address
                  //                last four bytes are target address
                  }//if is four
               default:
            //                  should not reach here
            //                  if it does, throw error
            //
            //
            //         write object code to text file
            }//switch
         }//if opcode exists
      }//for
   }//end pass_two_assembly

   public void determineAddressing(Source_line sourceline){
      String label = sourceline.get_label();
      
      //if immiedate addressing
      if(label.charAt(0) == '#'){
         n = "0";
         i = "1";
         
         //if label does not exist in SYMTAB then it is assumed to be a constant
         if(symtable.find(label.substring(1)) != null){
            b = "0";
            p = "1";
            PCMODE();
         }
         else{
            b = "0";
            p = "0";
            //objectcode = opcode nixbpe hexadecimal value of constant with leading zeros
         }
      }
      //check if label is indirect addressing
      else if(label.charAt(0) == '@'){
         n = "1";
         i = "0";
         //if exists in SYMTAB is a label, else is a constant
         if(symtable.find(label.substring(1)) != null){
            p = "1";
            b = "0";
         }
         else{
            p = "0";
            b = "0";
         }
      }
      //else is simple addressing
      else{
         n = "1";
         i = "1";
         
         if(symtable.find(label) != null){
            if(sourceline.isIndexed){
               x = "1";
            }
            else{
            }
         }//if is not a constant
         
      }//else simple addressing
   
   }//determineAddressing

   public void PCMODE(){
   
      String label = sourceline.getLabel();
   
      String address = symtable.find(label).getAddress();
   
   //calculate LOCCTR - addr 
   //if is within range set object code
   //else go to BASE mode
   
   }//PCMODE
   
   public void BASEMODE(String address){
   //calculate address - BASE register
   }//BASEMODE
   
}//class Pass 2