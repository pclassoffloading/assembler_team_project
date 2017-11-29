//This program simulates Pass 2
//
//Pass 2
//
class PassTwo{

   public String opjectCode; //what this class should return to main
   public String n,i,x,b,p,e; //nixbpe bits used for format 3/4 instructions
   public boolean isFour;
   public String objectCode;
   SYMTAB symtable;
   OPTAB optable;
   Math mathLib;

   String B_Register;


   PassTwo(OPTAB optable, SYMTAB symtable, Source_line[] sourcelines, Math test){
      this.symtable = symtable;
      this.optable = optable;
      this.mathLib = test;
      pass2_assembly(sourcelines);

   }//Pass2


   public void math_example()
   {
      // String testy = this.test.convertIntToHex(17099800);
   //       this.test.convertHexToInt(testy);
   //           //this.test.addHextoHex("104ec18", "104ec10"); // value put into system.out.println below
   //       //System.out.println("HERE IS YOUR NEW HEX VALUE: " + this.test.addHextoHex("104ec18", "104ec10"));
   }

   public void pass2_assembly(Source_line[] sourcelines){

      for(int i = 0; i < sourcelines.length; i++){


         try{

            String operation = sourcelines[i].get_mnemonic();

            //If is BASE command
            if(operation.equals("BASE")){

            //set B register
            this.B_Register = sourcelines[i].get_address();

            }
            else {

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

                     //determineIfIndexed(sourcelines[i]);
                        determineAddressing(sourcelines[i]);

                     //if is three
                        if(!(isFour)){
                           e = "0";
                        //First try PC mode
                           PCMODE(sourcelines[i], sourcelines[i+1]);
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

                        //convert opcode to binary, and "chop off" last two bits
                        //String opcodeBinary = mathLib.hexToBinary(opcode);
                        //opcodeBinary = opcodeBinary.substring(0,6);

                        //convert address to binary
                        //binaryAddress = mathLib.hexToBinary(targetAddress);

                        //then we concatanate with n,i,x,b,p,e and adddress
                        //String binaryObjectCode = opcodeBinary + n + i + x + b + p + e + binaryAddress

                        //then convert back to Hex
                        //objectCode = mathLib.binaryToHex(binaryObjectCode);s

                        //                Format 4 Instruction
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
               else{

               }
            }//else if not BASE command

         }
         catch (Exception e) {};
      }//for
   }//end pass_two_assembly

//    public void determineIfIndexed(Source_line sourceline){
//       if(sourceline.isIndexed == true){
//          x = "1";
//       }
//       else x = "0";
//    }

   public void determineAddressing(Source_line sourceline){
      String label = sourceline.get_symbol();

      //if immiedate addressing

         if(label.charAt(0) == '#'){
            n = "0";
            i = "1";

         //if label does not exist in SYMTAB then it is assumed to be a constant
            if(symtable.find(label.substring(1)) != null){
               b = "0";
               p = "1";
            }
            else{
               b = "0";
               p = "0";
            //objectcode = opcode nixbpe hexadecimal value of constant with leading zeros
            }
         }//end immiediate addressing

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
         }//end indirect addressing

         //else is simple addressing
         else{
            n = "1";
            i = "1";
         //if exists in SYMTAB is a label, else is a constant
            if(symtable.find(label) != null){
               p = "1";
               b = "0";
            }
            else{
               p = "0";
               b = "0";
            }

         }//else simple addressing

   }//determineAddressing


   public void PCMODE(Source_line source_line, Source_line source_line2){

         //grab address of next variables
         String address_2 = source_line2.get_address();

         //get label
         String label = source_line.get_symbol();

         //get address of label
         String address = this.symtable.find(label).get_address();

         //calculate displacement
         String displacement = this.mathLib.subHextoHex(address, address_2);

         //if displacement is bigger than 2047 or less than -2048
         BASEMODE(address);


   }//PCMODE

   public void BASEMODE(String address){
   //Switched to Base Mode relative addressing
      p = "0";
      b = "1";
   //calculate address - BASE register
   //if displacement is bigger than 4095 or less than 0, then fail
   }//BASEMODE

}//class Pass 2
