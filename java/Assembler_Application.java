
import java.io.IOException;
import java.util.Scanner;
public class Assembler_Application{
int LOCCTR = 0;
//sourceline should exist outside of scope and and passed into pass one and pass 2.
   public static void main(String [] args) throws IOException{
      System.out.println("on to the first file.");

      input textfile = new input(loc("input3.txt"));
      Math test = new Math();
      Source_line source_lines[] = textfile.provide_source_lines();
       // for (Source_line item : source_lines) {
       //   item.tell_source_line();
       // }
      //source_line ttt = new source_line(loc("input.txt"));
      OPTAB optable = new OPTAB("ADD 3/4 18 ADDF 3/4 58 ADDR 2 90 AND 3/4 40 CLEAR 2 4 COMP 3/4 28 COMPF 3/4 88 DIV 3/4 24 DIVR 2 9C FIX 1 C4 FLOAT 1 C4 HIO 1 F4 J 3/4 3C JEQ 3/4 30 JGT 3/4 34 JLT 3/4 38 JSUB 3/4 48 LDA 3/4 00 LDB 3/4 68 LDCH 3/4 50 LDF 3/4 70 LDL 3/4 08 LDS 3/4 6C LDT 3/4 74 LDX 3/4 04 LPS 3/4 D0 MUL 3/4 20 MULF 3/4 60 MULR 2 98 NORM 1 C8 OR 3/4 44 RD 3/4 D8 RMO 2 AC RSUB 3/4 4C SHIFTL 2 A4 SHIFTR 2 A8 SIO 1 F0 SSK 3/4 EC STA 3/4 0C STB 3/4 78 STCH 3/4 54 STF 3/4 80 STI 3/4 D4 STL 3/4 14 STS 3/4 7C STSW 3/4 E8 STT 3/4 84 STX 3/4 10 SUB 3/4 1C SUBF 3/4 5C SUBR 2 94 SVC 2 B0 TD 3/4 E0 TIO 1 F8 TIX 3/4 2C TIXR 2 B8 WD 3/4 DC");
//      SYMTAB symtab = new SYMTAB("");
      PassOne passone = new PassOne(optable, source_lines, test);//sourcelines
      //PassTwo passtwo = new PassTwo(optable, sourcelines, symtable);//
   }
   public static String loc(String text){
      return "../ext_files/".concat(text);
   }
}
