//This program simulates Pass 2
//
//Pass 2
//
class Pass2{

public String opjectCode;

   public void Pass2(sourceline[] sourcelines){
      // for each sourceline
      for(int i = 0; i < sourcelines.length(); i++){

      //    get LOCCTR -- public class variable

         String operation = sourcelines[i].getMnumonic;
         //if operation exists in OPTAB (i.e. ADD, STA, etc)
         if(OPTAB.find(operation) != Null){
         //grap opcode
            opcode = OPTAB.find(operation).getOpcode;      
            //get format
            String format = sourcelines[i].getFormat;

            switch(format):
            case "1":
               objectCode = opcode;
               break;
            case "2":
               break;
            case "3/4":
                  //if is three
                  if(!(sourcelines[i].isFour)){
//                First try PC mode
//                if PC mode fail
//                   try BASE mode
//                      if BASE mode fail
//                         fail source line, print error "format 4 needed for instruction"
                  }//if is three

                  //if is four
                  else{
                  //find target address
                  String targetAddress = (SYMTAB.find(sourceLine.getSymbol)).getAddress;
//                object code = opject code and target address
//                format of object code is four bytes: ## ## ## ##
//                first two bytes is opcode code
//                last four bytes are target address
                  }//if is four
//          default:
//                  should not reach here
//
//
//         write object code to text file