//Scenario 1
//
//This file simulates in detail a line of code from a program
//
//
// Consider the input file below
//
//SUM	START	0
// FIRST	LDX    #0
// 		LDA    #0
// 		+LDB   #TABLE2
// 		BASE 	TABLE2
// LOOP	ADD 	TABLE,X
// 		ADD 	TABLE2,X
// 		TIX	COUNT
// 		JLT 	LOOP
// 		+STA 	TOTAL
// 		RSUB
// COUNT 	RESW 	1
// TABLE 	RESW	2000
// TABLE2 	RESW 	2000
// TOTAL 	RESW 	1
// 		END 	FIRST

//During Pass 1 addresses will be assigned to each source line that uses space in the program
//This would result in the following
//
//
//0000 SUM	START	0
//0000 FIRST	LDX    #0
//0003 		   LDA    #0
//0006 		   +LDB   #TABLE2
//     		   BASE 	 TABLE2
//000A LOOP	   ADD 	TABLE,X
//000D 		   ADD 	TABLE2,X
//0010 		   TIX	COUNT
//0013 		   JLT 	LOOP
//0016 		   +STA 	TOTAL
//001A 		   RSUB
//001D COUNT 	RESW 	1
//0020 TABLE 	RESW	2000
//1790 TABLE2 	RESW 	2000
//2F00 TOTAL 	RESW 	1
//2F03    		END 	FIRST
//
//
//Next Pass Two will generate object code from the results of Pass 1
//
// Let's look at line FIRST LDX #0
//
// First we need the LOCCTR at that line
// LOCCTR will be public class variable that we can access
//
// PC register will be set to the next line address so we will need to figure out how best to approach that
// PC = next sourceline.getAddress
// in this case PC = 0003
//
// Next we need to determine the format number of the operation/mnumonic
// We do this by looking at OPTAB
// so let's create a temp object called operation equal to an Object from OPTAB (objects in OBTAB are of type DataItem)
// DataItem operation =  OPTAB.find(sourceline.getMnumonic)//will return DataItem
//
// Then we can get the format number
// formatN = operation.getFormatN
//
// then based on the format N we decide how to calculate the object code
// for this instruction the format number will return as 3/4
// so we need to decide if this is a format 3 or format 4 instruction
//
// for this we check the sourceline.isFour which will be a boolean method in sourceline that will return 0 if false or 1 if true
// so if sourceline.isFour == 1
//       then format 4
//    else
//       format 3
// 
// this will return 0 as this was declared as a format 3 instruction (no "+" was used)
// we could use a format 3 method to work the rest or we could type it all out depending on what is easier
//
//
// in format 3 we always first try to determine the displacement by PC relative mode if PC mode failes we then try BASE relative mode
//
// so now we look at the label
// label = sourceline.getLabel
// in this case label is a constant "#0"
// the hash '#' declaration was used 
// thus we use what is called immiediate direct addressing and we do not need to use SYMTAB as we are not referencing other variables
//
// format of format 3 instruction is
// opcode nixbe displacement
// opcode uses 6 bits, nixbpe uses 6 bits and displacement uses 12 bits for a total of 24 bits or 3 bytes
//
// as this is a format 3 instruction x = 0 (if format 4 x = 1)
// since this is simple immiediate addressing n = 0, i = 1 b = 0 p = 0 e = 0
// since #0 is a constant we will be storing the value of the constant into the address field
// 
// now we just need the opcode
// opcode = operation.getOpcode
// in this case opcode = 04 as that is the opcode of LDX
// 
// so finally we have what we need to calculate the object code
//
// the first six bits of the opcode are used so the hex value of 04 is 0000 01 in binary (we cut off the last two 0s)
// then we insert the nixbpe bits 010000 to have 0000 0101 0000
// finally we insert the value of 0 represented in twelve bits to have 0000 0111 0000 0000 0000 0000
// taking each four bits and translating to hexadecimal gives us 0 5 0 0 0 0 thus our object code is 050000 
// so our line will note look like
// 0000 FIRST LDX #0 050000