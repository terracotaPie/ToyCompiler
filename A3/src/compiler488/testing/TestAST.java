package compiler488.testing;

/* File Name: TestAST.java
 * To Create: 
 * After the scanner and the parser, have been created.
 * > javac TestAST.java
 *
 * To Run: 
 * > java   csc488.TestAST   program
 * where program is an input file for the compiler
 * This simple minded driver does not read from standard input
 */

import compiler488.ast.stmt.Scope;
import compiler488.parser.*;
import java.io.*;


/**
 * A sample class, very similar to Compiler488.
 * It shows how to print the AST after the
 * parsing is done.
 */
class TestAST {
	
	static public void main(String argv[]) {
		/* Start the parser */
		try {
			System.out.println("Start parsing");
			PrintAST (new File (argv[0]));
			System.out.println("End parsing");
		} catch (Exception e) {
			/* do cleanup here -- possibly rethrow e */
			System.out.println("Exception during Parsing");
			e.printStackTrace();
		}
	}
	
	static public void PrintAST (File file) throws Exception {
		Parser p = new Parser(new Lexer(new FileReader(file)));
		Scope s = (Scope) p.parse().value;
		/*  Print the entire AST from it's root */
		s.printOn(System.out, 0);		
	}

}
