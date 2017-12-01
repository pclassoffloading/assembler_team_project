//Scenario 2
//
//This file simulates in detail a line of code from a program
//
//
// Consider the input file below
//
// SUM	START	0
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
// Let's take a look at line 000A LOOP ADD TABLE,X
//
// First let's determine the formatN
// create temp DataItem operation
// DataItem operation = OPTAB.find(sourceLine.getMnumonic);
// this will return the DataItem ADD from OPTAB
//
// String formatN = operation.getFormatN
// this will return "3/4"
// so now we need to know if it is format 3 or format 4
// if(sourceline.isFour == 1)
//    format 4
// else
//    format 3
//
// sourceline.isFour will return 0, thus this will be a format 3 instruction
//  
// next we will check the symbol
// symbol = sourceLine.getsymbol
// this will return "TABLE" (when we store symbols in sourceline we should remove ,X and set boolean isIndexed = 1
// because this is not a constant we will need to use displacement
//
// first set the PC registar to the address of the next line
// PC = next sourceline.getAddress
// PC = 000D
//
// next we will deterine the type of addressing this will require
// no special symbols such as @ or # were used thus this will be simple direct addressing
// this will set the n i x b p e bits to 1 1 0 0 1 0 respectively
// 
// next let us check if this is indexing
// sourceline.isIndexed will be a boolean method that returns 0 if indexed or 1 if it is indexed
// indexing is declared by the programmer be appending ",X" at the end of a symbol
// sourcelin.isIndexed will return 1 as ",X" was appended to TABLE
// this will set the x bit to 1
// the n i x b p e bits will now be 1 1 1 0 1 0
// 
//
// next let us grab the opcode of ADD
// opcode = operation.getOpcode
// this will return "18" which is 0001 1000 in binary but for format 3 we only keep the first four bits
//
//
// next we need to calculate the displacement which will require the address of TABLE
// 
// we search SYMTAB for TABLE
// SymItem tempItem = Symtab.find(symbol);
// TargetAddress = tempItem.getAddress
// TargetAddress will equal "0020"
//
// next we subtract the TargetAdress from the PC register
// displacement = TargetAddress - PC
// displacement = 0020 - 000D = 0013
// remember this is a hexadecimal calculation
// 
// so our object coude can be now generated
// opcode = 0001 10
// nixbpe = 1110 10
// displacement = 0000 0001 0011
// put togethor that gives us 0001 1011 1010 0000 0001 0011
// this is 1BA013 in HEX and that is the object code
