

class NewPassTwo{

  OPTAB optable;SYMTAB symtable;Source_line[] sourcelines;Math mathLib;
  public String n,i,x,b,p,e, opjectCode, objectCode;
  public boolean isFour, isIndexed;
  String B_Register;String displacement;

  public NewPassTwo(OPTAB optable, SYMTAB symtable, Source_line[] sourcelines, Math test){
    this.optable = optable; this.symtable = symtable; this.sourcelines = sourcelines; this.mathLib = test;
    print_values();
    pass2_assembly(sourcelines);

  }
  public void print_values()
  {
    show_optable(optable.OPARRAY);
    show_sourcelines(sourcelines);
    symtable.displayTable();//print_optable;
  }
  public void show_sourcelines(Source_line[] source_lines){
    for (Source_line item : source_lines) {
      item.tell_source_line();
      //System.out.printf("   object code App: %s%n",item.objectCode);
    }
  }
  public void pass2_assembly(Source_line[] sourcelines){
    for(int i = 0; i < sourcelines.length; i++){
      String mneumonic = sourcelines[i].get_mnemonic();
      String modif_mneumonic = is_indexed(mneumonic);
      is_BASE(mneumonic, sourcelines[i]);

      mneumonic = check_format_4(mneumonic, sourcelines[i]);//this needs to be stored in sourceline

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
            //determineIfIndexed(sourcelines[i]);
               determineAddressing(sourcelines[i]);
               if(!(isFour)){//if is three
                  e = "0";
                  try{PCMODE(sourcelines[i], sourcelines[i+1]);}catch (Exception e) {};
                }//if is three

                //if is four
                else{
                   e = "1";
                   //find target address
                   String targetAddress = (symtable.find(sourcelines[i].get_symbol())).get_address();
                   System.out.println(opcode);
                   //convert opcode to binary, and "chop off" last two bits
                   String opcodeBinary = mathLib.hexToBin(opcode);
                    System.out.println(opcodeBinary);
                   //chop off
                   opcodeBinary = opcodeBinary.substring(0,6);
                   System.out.println("Binary opcode: " + opcodeBinary);

                   //convert address to binary
                   String binaryAddress = mathLib.hexToBin(targetAddress);

                   System.out.println("binaryAddress:" + binaryAddress);
                   System.out.println("binaryAddress:" + binaryAddress);
                   //then we concatanate with n,i,x,b,p,e and adddress
                   String binaryObjectCode = opcodeBinary + this.n + this.i + this.x + this.b + this.p + this.e + binaryAddress;
                   System.out.println("binaryObjectCode:" + binaryObjectCode);

                   //then convert back to Hex
                   objectCode = mathLib.binToHex(binaryObjectCode);
                   System.out.println("objectCode:" + objectCode);
                   sourcelines[i].set_objectCode(objectCode);

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

  public String is_indexed(String mneumonic){
    if(mneumonic.substring(mneumonic.length()-1).equals('X')){
       this.isIndexed = true;
       this.x = "1";
       mneumonic = mneumonic.substring(0, (mneumonic.length()-2));
       return mneumonic;

    }
    else{
       this.isIndexed = false;
       this.x = "0";
       return mneumonic;
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
       isFour = false;//this needs to be stored in sourceline
       return mneumonic;
    }
  }


  public void show_optable(DataItem[] OPARRAY)
  {
    try{for (DataItem item : OPARRAY) {item.printDataItem();}}catch (Exception e) {};
  }

  public void determineAddressing(Source_line sourceline){
     String label = sourceline.label;

     //if immiedate addressing
     try{if(label.charAt(0) == '#'){
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

     }}catch (Exception e) {};//else simple addressing

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
        BASEMODE(address);
     }


  }//PCMODE

  public void BASEMODE(String address){
  //Switched to Base Mode relative addressing
     p = "0";
     b = "1";

     //calculate address - BASE register
     this.displacement = mathLib.subHextoHex(address, B_Register);
     //if displacement is bigger than 4095 or less than 0, then fail
     int value = mathLib.convertHexToInt(displacement);
     if( value > 4095 || value < 0){
     //should fail
     }
  }//BASEMODE
}
