import java.io.IOException;
import java.util.Scanner;
//import input.*;
//import Assembler_Application
public class Assembler_Application{
int LOCCTR = 0;

	public static void main(String [] args) throws IOException{
		System.out.println("on to the first file.");
		input dragon0 = new input(loc("input.txt"));
//		System.out.println("on to the second file.");
//		input other_dragon = new input(loc("input3.txt"));

		System.out.println("on to the mnumonic file.");
		input dragon1 = new input(loc("nemonic_info.txt"));
		System.out.println("on to the format file.");
		input dragon2 = new input(loc("format_info.txt"));
		System.out.println("on to the opcode_info file.");
		input dragon3 = new input(loc("opcode_info.txt"));
//		System.out.println("on to the first file.");
//		input dragon4 = new input(loc("apndx_eff_and_note_incldd.txt"));


		System.out.println("on to the source_line.");
		source_line frog = new source_line(loc("output.txt"));
	}
	public static String loc(String text){
		return "../ext_files/".concat(text);
	}
//array_of_sourcelines instantiated by int count = input.count(file )
//SourceLines[] array_of_sourcelines =  new SourceLines[count];
//array_of_sourcelines = generated by input.evaluate(array_of_sourcelines, file)
//passone()//will gather location_address
//{
//
//}
//addresses_without_issue()
//{
//	for each SourceLine in array_of_sourcelines
//	{
//		LOCCTR = SourceLine.getAddr(LOCCTR)//LOCCTR,SYMBOL,MNEMONIC,LABEL
//		SourceLine.update(LOCCTR)
//	}
//}
}