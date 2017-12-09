package src;
public class PassTwo{

   OPTAB optable;SYMTAB symtable;Source_line[] sourcelines;

   public String objectCode;
   public boolean isFour, isIndexed;
   String B_Register;String displacement;
   Output writefile;
   String fileName;

   public PassTwo(OPTAB optable, SYMTAB symtable, Source_line[] sourcelines, String fileName){
      this.optable = optable; this.symtable = symtable; this.sourcelines = sourcelines;
      this.fileName = fileName; this.writefile = new Output();
      //print_values();
      pass2_assembly(sourcelines);
      print_values();
   }
   public void print_values()
   {
    //show_optable(optable.OPARRAY);
    //optable.displayTable();
      show_sourcelines(sourcelines);
      symtable.displayTable();//symtable;
   }
   public void show_sourcelines(Source_line[] source_lines){
     System.out.println();
     print_lst_intro();
      for (Source_line item : source_lines) {
         item.tell_lst_file(this.fileName);
         //item.tell_source_line();
      //System.out.printf("   object code App: %s%n",item.objectCode);
      }
   }
   public void print_lst_intro(){
     System.out.println("*******************************************");
     this.writefile.write_file(this.fileName,".lst", "*******************************************\n");
     System.out.println("ASSEMBLER REPORT");
     this.writefile.write_file(this.fileName,".lst", "ASSEMBLER REPORT\n");
     System.out.println("----------------");
     this.writefile.write_file(this.fileName,".lst","----------------\n");
     System.out.println("      Loc   Object Code   Source Code");
     this.writefile.write_file(this.fileName,".lst","      Loc   Object Code   Source Code\n");
     System.out.println("      ---   -----------   -----------");
     this.writefile.write_file(this.fileName,".lst","      ---   -----------   -----------\n");
   }
   public void pass2_assembly(Source_line[] sourcelines){
      for(int j = 0; j < sourcelines.length; j++){

         String mneumonic = sourcelines[j].get_mnemonic();
         sourcelines[j] = is_indexed(sourcelines[j]);
         if(mneumonic == null){continue;}
          else{
            if(mneumonic.equals("BASE")){//set B register
              this.B_Register = sourcelines[j].get_address();
            }
          }

         mneumonic = check_format_4(mneumonic, sourcelines[j]);//this needs to be stored in sourceline

         if(optable.find(mneumonic) != null){//if mneumonic exists in OPTAB (i.e. ADD, STA, etc)
            String opcode = optable.find(mneumonic).getOpcode();//grap opcode

            //RSUB exception
            if(mneumonic.equals("RSUB")){
            sourcelines[j].set_objectCode("4f0000");
            }

            String format = optable.find(mneumonic).getFormatN();//get format
            switch(format){
               case "1"://format 1
                  objectCode = opcode;
                  sourcelines[j].set_objectCode(objectCode);
                  break;
               case "2"://format 2
                  objectCode = opcode + sourcelines[j].symbol;
                  sourcelines[j].set_objectCode(objectCode);
                  break;
               case "3/4"://format 3 or 4
                  determineAddressing(sourcelines[j]);
                  if(!sourcelines[j].isFour){//if is three
                     sourcelines[j].e = "0";
                     //convert opcode to binary, and "chop off" last two bits
                     String opcodeBinary = Math.hexToBin(opcode);
                     //chop off
                     opcodeBinary = opcodeBinary.substring(0,6);
                     //simple addressing
                     if(sourcelines[j].n.equals("1") && sourcelines[j].i.equals("1")){
                        //op c
                        if(sourcelines[j].b.equals("0") && sourcelines[j].p.equals("0")){
                           //TA is symbol itself
                           String symbol = sourcelines[j].symbol;
                           //convert to binary
                           String binarySym = Math.hexToBin(symbol);
                           //displacement must be 12 bits long
                           if(binarySym.length() != 12){
                              int value = 12 - (binarySym.length() % 12);
                              for(int counter = 0; counter < value; counter++){
                                 binarySym = "0" + binarySym;
                              }
                           }
                           calculateObjectCode(sourcelines[j], opcodeBinary, binarySym);
                        }
                        //op m
                        else if(sourcelines[j].b.equals("0") && sourcelines[j].p.equals("1")){
                           try{PCMODE(sourcelines[j], sourcelines[j+1]);}
                           catch (Exception exception) {};
                           //String binaryObjectCode = opcodeBinary + sourcelines[j].n + sourcelines[j].i + sourcelines[j].x + sourcelines[j].b + sourcelines[j].p + sourcelines[j].e;
                           String binDisplacement = Math.hexToBin12(displacement);
                           calculateObjectCode(sourcelines[j], opcodeBinary, binDisplacement);
                        }
                     }
                     //indirect addressing
                     else if(sourcelines[j].n.equals("1") && sourcelines[j].i.equals("0")){
                     }
                     else if(sourcelines[j].n.equals("0") && sourcelines[j].i.equals("1")){
                        String symbol = sourcelines[j].symbol;
                        //remove the #
                        symbol = symbol.substring(1);
                        //constant i.e. #0 #100 etc
                        if(sourcelines[j].p.equals("0") && sourcelines[j].b.equals("0")){
                           String binSymbol = Math.hexToBin12(symbol);
                           //calculate ObjectCode
                           calculateObjectCode(sourcelines[j], opcodeBinary, binSymbol);
                        }
                        else if(sourcelines[j].p.equals("1") && sourcelines[j].b.equals("0")){
                           String address = symtable.find(symbol).get_address();
                           String binAddress = Math.hexToBin12(address);
                           calculateObjectCode(sourcelines[j], opcodeBinary, binAddress);
                        }
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

                   //convert opcode to binary, and "chop off" last two bits
                     String opcodeBinary = Math.hexToBin(opcode);

                   //chop off
                     opcodeBinary = opcodeBinary.substring(0,6);

                   //convert address to binary
                     String binaryAddress = Math.hexToBin_Addr(targetAddress);

                     //then we concatanate with n,i,x,b,p,e and adddress
                     System.out.println("n "+sourcelines[j].n + " i "+sourcelines[j].i + " x "+sourcelines[j].x + " b "+sourcelines[j].b + " p "+sourcelines[j].p + " e "+sourcelines[j].e);
                     String binaryObjectCode = opcodeBinary + sourcelines[j].n + sourcelines[j].i + sourcelines[j].x + sourcelines[j].b + sourcelines[j].p + sourcelines[j].e + binaryAddress;

                     //then convert back to Hex
                     objectCode = Math.binToHex(binaryObjectCode);

                     //binToHex chops off leading zeros so we need to bring them back
                     if(objectCode.length() != 8){
                        int value =  8 - (objectCode.length() % 8);

                        for(int counter = 0; counter < value; counter++)
                        {
                           objectCode = "0" + objectCode;
                        }
                     }
                     //System.out.println("objectCode:" + objectCode);
                     sourcelines[j].set_objectCode(objectCode);
                  }//if is four
               default:
            }//switch
         }//if_opcode exists
      }//end for each sourceline
   }//end pass2_assembly

   public void calculateObjectCode(Source_line sourceline, String opcodeBinary, String displacement){
      //calulate objectCode

      String binaryObjectCode = opcodeBinary + sourceline.n + sourceline.i + sourceline.x + sourceline.b + sourceline.p + sourceline.e + displacement;
      this.objectCode = Math.binToHex(binaryObjectCode);

      //binToHex cuts off leading 0's so make sure is 6 bytes long
      if(objectCode.length() != 6){
         int value =  6 - (objectCode.length() % 6);
         //System.out.println("Missing #0: " + value);
         for(int counter = 0; counter < value; counter++)
         {
            this.objectCode = "0" + this.objectCode;
         }
      }
      //System.out.println(objectCode);
      sourceline.set_objectCode(this.objectCode);
   }

   public Source_line is_indexed(Source_line sourceline){
   //sourceline.tell_source_line();

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
            }//objectcode = opcode nixbpe hexadecimal value of constant with leading zeros
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
         }
       }
       catch (Exception exception) {};//else simple addressing
   }//determineAddressing

   public void PCMODE(Source_line source_line, Source_line source_line2){

     //grab address of next variables
     String address_2 = source_line2.get_address();

     //get label
     String label = source_line.get_symbol();
     System.out.println("label: "+label);

     //get address of label
     String address = this.symtable.find(label).get_address();

     //calculate displacement
     this.displacement = Math.subHextoHex(address, address_2);
     int value = Math.convertHexToInt(this.displacement);

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
      this.displacement = Math.subHextoHex(address, B_Register);
     //if displacement is bigger than 4095 or less than 0, then fail
      int value = Math.convertHexToInt(displacement);
      if( value > 4095 || value < 0){
      //should fail
        System.out.println("Displcament out of range: Should have been format 4");
        source_line.tell_source_line();
      }
   }//BASEMODE
   public Source_line[] provide_source_lines(){
     return this.sourcelines;
   }
}
