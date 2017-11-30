

class NewPassTwo{

   OPTAB optable;SYMTAB symtable;Source_line[] sourcelines;Math mathLib;

   public String objectCode;
   public boolean isFour, isIndexed;
   String B_Register;String displacement;

   public NewPassTwo(OPTAB optable, SYMTAB symtable, Source_line[] sourcelines, Math test){
      this.optable = optable; this.symtable = symtable; this.sourcelines = sourcelines; this.mathLib = test;
      print_values();
      pass2_assembly(sourcelines);
   
   }
   public void print_values()
   {
    //show_optable(optable.OPARRAY);
    //optable.displayTable();
      show_sourcelines(sourcelines);
      symtable.displayTable();//symtable;
   }
   public void show_sourcelines(Source_line[] source_lines){
      for (Source_line item : source_lines) {
         item.tell_source_line();
      //System.out.printf("   object code App: %s%n",item.objectCode);
      }
   }
   public void pass2_assembly(Source_line[] sourcelines){
   
   
   
      for(int j = 0; j < sourcelines.length; j++){
      
         String mneumonic = sourcelines[j].get_mnemonic();
         sourcelines[j] = is_indexed(sourcelines[j]);
         is_BASE(mneumonic, sourcelines[j]);
      
         mneumonic = check_format_4(mneumonic, sourcelines[j]);//this needs to be stored in sourceline
      
         if(optable.find(mneumonic) != null){//if mneumonic exists in OPTAB (i.e. ADD, STA, etc)
            String opcode = optable.find(mneumonic).getOpcode();//grap opcode
            String format = optable.find(mneumonic).getFormatN();//get format
            switch(format){
               case "1"://format 1
                  objectCode = opcode;
                  break;
               case "2"://format 2
                  break;
               case "3/4"://format 3 or 4
               //determineIfIndexed(sourcelines[j]);
                  determineAddressing(sourcelines[j]);
                  
                  if(!sourcelines[j].isFour){//if is three
                     sourcelines[j].e = "0";
                     if(sourcelines[j].i.equals("1") && sourcelines[j].p.equals("0")){
                        //find target address
                        String targetAddress = "0";
                        //grab symbol
                        String symbol = sourcelines[j].symbol;
                           
                     }
                     else if(sourcelines[j].p.equals("1")){
                        try{PCMODE(sourcelines[j], sourcelines[j+1]);}
                        catch (Exception exception) {};
                     }
                     
                     
                  }//if is three
                  
                  
                  
                  //if is four
                  else{
                     //defaults for format 4
                     sourcelines[j].b = "0";
                     sourcelines[j].p = "0";
                  
                     sourcelines[j].e = "1";
                     
                   //find target address
                     String targetAddress = "0";
                   //grab symbol
                     String symbol = sourcelines[j].symbol;
                   //check for leading bit of # or @
                     if(symbol.charAt(0) == '@' || symbol.charAt(0) == '#'){
                        symbol = symbol.substring(1);
                     }
                   
                     try{targetAddress = (symtable.find(symbol)).get_address();}
                     catch (Exception exception) {};
                     
                     System.out.println("TA: "+targetAddress);
                     
                     System.out.println("opcode: "+opcode);
                   //convert opcode to binary, and "chop off" last two bits
                     String opcodeBinary = mathLib.hexToBin(opcode);
                     System.out.println("opcode binary: "+opcodeBinary);
                   //chop off
                     opcodeBinary = opcodeBinary.substring(0,6);
                     System.out.println("Binary opcode: " + opcodeBinary);
                  
                   //convert address to binary
                     String binaryAddress = mathLib.hexToBin_Addr(targetAddress);
                  
                     System.out.println("binaryAddress:" + binaryAddress);
                   
                   //then we concatanate with n,i,x,b,p,e and adddress
                     System.out.println("n "+sourcelines[j].n + " i "+sourcelines[j].i + " x "+sourcelines[j].x + " b "+sourcelines[j].b + " p "+sourcelines[j].p + " e "+sourcelines[j].e);
                     String binaryObjectCode = opcodeBinary + sourcelines[j].n + sourcelines[j].i + sourcelines[j].x + sourcelines[j].b + sourcelines[j].p + sourcelines[j].e + binaryAddress;
                     System.out.println("binaryObjectCode:" + binaryObjectCode);
                  
                   //then convert back to Hex
                     objectCode = mathLib.binToHex(binaryObjectCode);
                     
                     
                     //binToHex chops off leading zeros so we need to bring them back
                     if(objectCode.length() != 8){
                        int value =  8 - (objectCode.length() % 8);
                        System.out.println("Missing #0: " + value);
                        for(int counter = 0; counter < value; counter++)
                        {
                           objectCode = "0" + objectCode;
                        }
                     }
                     System.out.println("objectCode:" + objectCode);
                     
                                          
                     sourcelines[j].set_objectCode(objectCode);
                  
                  //                Format 4 Instruction
                  //                format of object code is four bytes: ## ## ## ##
                  //                first six bits is opcode code
                  //                next six bits is nixbpe
                  //                next 20 bits are address
                  //                last four bytes are target address
                  
                  }//if is four
               default:
            }//switch
         }//if_opcode exists
      }//end for each sourceline
   }//end pass2_assembly

   public Source_line is_indexed(Source_line sourceline){
      sourceline.tell_source_line();
   
      try{
         if(sourceline.symbol.substring(sourceline.symbol.length()-2).equals(",X")){
         
            sourceline.isIndexed = true;
            System.out.println(sourceline.symbol + " is indexed!");
            sourceline.x = "1";
            sourceline.symbol = sourceline.symbol.substring(0, (sourceline.symbol.length()-2));
            return sourceline;
         }
         else{
            sourceline.isIndexed = false;
            sourceline.x = "0";
            return sourceline;
         }//If is BASE command
      }
      catch (Exception error) {
      
         sourceline.isIndexed = false;
         sourceline.x = "0";
         return sourceline;
      }//If is BASE command
   
   }

   public void is_BASE(String mneumonic, Source_line sourceline){
      if(mneumonic.equals("BASE")){//set B register
         this.B_Register = sourceline.get_address();
      }
   }

   public String check_format_4(String mneumonic, Source_line sourceline){
      if(mneumonic.charAt(0) == '+')
      {
         sourceline.isFour = true;//this needs to be stored in sourceline.
         return mneumonic = mneumonic.substring(1);
      }
      else{
         sourceline.isFour = false;//this needs to be stored in sourceline
         return mneumonic;
      }
   }


   public void show_optable(DataItem[] OPARRAY)
   {
      try{
         for (DataItem item : OPARRAY) {item.printDataItem();}}
      catch (Exception exception) {};
   }

   public void determineAddressing(Source_line sourceline){
      String label = sourceline.symbol;
   
     //if immiedate addressing
      try{
         if(label.charAt(0) == '#'){
            sourceline.n = "0";
            sourceline.i = "1";
         
         //if label does not exist in SYMTAB then it is assumed to be a constant
            if(symtable.find(label.substring(1)) != null){
               sourceline.b = "0";
               sourceline.p = "1";
            }
            else{
               sourceline.b = "0";
               sourceline.p = "0";
            //objectcode = opcode nixbpe hexadecimal value of constant with leading zeros
            }
            
         }//end immiediate addressing
         
         //check if label is indirect addressing
         else if(label.charAt(0) == '@'){
            sourceline.n = "1";
            sourceline.i = "0";
         //if exists in SYMTAB is a label, else is a constant
            if(symtable.find(label.substring(1)) != null){
               sourceline.p = "1";
               sourceline.b = "0";
            }
            else{
               sourceline.p = "0";
               sourceline.b = "0";
            }
         }//end indirect addressing
         
         //else is simple addressing
         else{
            sourceline.n = "1";
            sourceline.i = "1";
         //if exists in SYMTAB is a label, else is a constant
            if(symtable.find(label) != null){
               sourceline.p = "1";
               sourceline.b = "0";
            }
            else{
               sourceline.p = "0";
               sourceline.b = "0";
            }
         
         }}
      catch (Exception exception) {};//else simple addressing
   
   }//determineAddressing
  //
  //
   public void PCMODE(Source_line source_line, Source_line source_line2){
   
     //grab address of next variables
      String address_2 = source_line2.get_address();
   
     //get label
      String label = source_line.get_symbol();
   
     //get address of label
      String address = this.symtable.find(label).get_address();
   
     //calculate displacement
      this.displacement = this.mathLib.subHextoHex(address, address_2);
   
      int value = this.mathLib.convertHexToInt(this.displacement);
   
     //if displacement is bigger than 2047 or less than -2048
      if(value > 2047 || value < -2048){
         BASEMODE(address, source_line);
      }
      
   
   
   }//PCMODE

   public void BASEMODE(String address, Source_line source_line){
   //Switched to Base Mode relative addressing
      source_line.p = "0";
      source_line.b = "1";
   
     //calculate address - BASE register
      this.displacement = mathLib.subHextoHex(address, B_Register);
     //if displacement is bigger than 4095 or less than 0, then fail
      int value = mathLib.convertHexToInt(displacement);
      if( value > 4095 || value < 0){
      //should fail
      }
   }//BASEMODE
}
