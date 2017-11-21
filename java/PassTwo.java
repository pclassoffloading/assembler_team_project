//This program simulates Pass 2
//
//Pass 2
//
class Pass2{

public String opjectCode; //what this class should return to main
public String n,i,x,b,p,e; //nixbpe bits used for format 3/4 instructions


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
                  e = "0";
//                First try PC mode
//                if PC mode fail
//                   try BASE mode
//                      if BASE mode fail
//                         fail source line, print error "format 4 needed for instruction"
                  }//if is three

                  //if is four
                  else{
                  e = "1";
                  //find target address
                  String targetAddress = (SYMTAB.find(sourceLine.getSymbol)).getAddress;
//                object code = opject code cancatanated with the target address
//                format of object code is four bytes: ## ## ## ##
//                first six bits is opcode code
//                next six bits is nixbpe
//                next 20 bits are address
//                last four bytes are target address
                  }//if is four
//          default:
//                  should not reach here
//                  if it does, throw error
//
//
//         write object code to text file

   public void PCMODE(){
      String label = sourceline.getLabel;
      
      
      
      //if immiedate addressing
      if(label.charAt(0) == '#'){
         n = "0";
         i = "1";
         
         //if label does not exist in SYMTAB then it is assumed to be a constant
         if(SYMTAB.find(label.substring(0) != NULL){
            b = "0";
            p = "1";
         }
         else{
            b = "0";
            p = "0";
         }
      }
      //check if label is indirect addressing
      else if(label.charAt(0) == "@"){
         n = "1";
         i = "0";
         //if exists in SYMTAB is a label, else is a constant
         if(SYMTAB.find(label.substring(0) != NULL){
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
         //if is not a constant
         if(SYMTAB.find(label) != NULL){
            if(sourceline.isIndexed){
            x = "1";
            }
            else{
            }
         }
         
      }
}