package src;
import src.*;
// Team Sweet & Sour
// Camron Conway
// Michael Dimmitt
// Samantha Maletta
// Michael Vasquez
import java.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;



public class Assembler_Application{
int LOCCTR = 0;
//sourceline should exist outside of scope and and passed into pass one and pass 2.
public static void main(String [] args) throws IOException{
  if(0 == args.length){instruct_arg_input();}

  OPTAB optable = new OPTAB("ADD 3/4 18 ADDF 3/4 58 ADDR 2 90 AND 3/4 40 CLEAR 2 4 COMP 3/4 28 COMPF 3/4 88 DIV 3/4 24 DIVR 2 9C FIX 1 C4 FLOAT 1 C4 HIO 1 F4 J 3/4 3C JEQ 3/4 30 JGT 3/4 34 JLT 3/4 38 JSUB 3/4 48 LDA 3/4 00 LDB 3/4 68 LDCH 3/4 50 LDF 3/4 70 LDL 3/4 08 LDS 3/4 6C LDT 3/4 74 LDX 3/4 04 LPS 3/4 D0 MUL 3/4 20 MULF 3/4 60 MULR 2 98 NORM 1 C8 OR 3/4 44 RD 3/4 D8 RMO 2 AC RSUB 3/4 4C SHIFTL 2 A4 SHIFTR 2 A8 SIO 1 F0 SSK 3/4 EC STA 3/4 0C STB 3/4 78 STCH 3/4 54 STF 3/4 80 STI 3/4 D4 STL 3/4 14 STS 3/4 7C STSW 3/4 E8 STT 3/4 84 STX 3/4 10 SUB 3/4 1C SUBF 3/4 5C SUBR 2 94 SVC 2 B0 TD 3/4 E0 TIO 1 F8 TIX 3/4 2C TIXR 2 B8 WD 3/4 DC");
  Input input = new Input();

  for(int i=0;i< args.length;i++){
    Delete_Any_Old_Files(args[i]);//Output writefile = new Output();
    try{
      PassOne passone = new PassOne(optable, input.args_and_run(args[i]), new SYMTAB(), args[i]);//sourcelines
      PassTwo passtwo = new PassTwo(optable, passone.provide_symtable(), passone.Pass1(), args[i]);
      PrintObjFile printObjFile = new PrintObjFile(passtwo.provide_source_lines(), args[i]);
      notify_program_completed(args[i]);
    }catch(FileNotFoundException exception){
      instruct_inputfiles_and_arg_input();
    };
  }
}

public static void notify_program_completed(String arg){
  System.out.println("Check files: "+modify_filename(arg,".lst")+" and "+modify_filename(arg,".obj")+"!!");
}

public static void instruct_inputfiles_and_arg_input(){
  System.out.println();
  System.out.println("whoa, incorrect file name given.");
  System.out.println("1) please put your test files in ext_files directory");
  System.out.println("2) please type your filename as the first command line argument.");
  System.out.println("thanks, this assembler team.");
}
public static void instruct_arg_input(){
  System.out.println("whoa, no command arguement given.");
  System.out.println("Please compile again with a command line arguement to your file.");
}
  public static void Delete_Any_Old_Files(String filename){
    Delete_It_Already(modify_filename(filename, ".lst"));
    Delete_It_Already(modify_filename(filename, ".obj"));
  }
  public static void Delete_It_Already(String filename){
    File file = new File(filename);
      if(file.delete()){
        //System.out.println("File deleted successfully");
      }
      else{
        //System.out.println("Failed to delete the file");
      }
  }
  public static String modify_filename(String filename, String type){
    return filename.substring(0, filename.length()-4)+type;
  }
}
