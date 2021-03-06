package compiler488.ast;

import compiler488.codegen.Instruction;
import compiler488.parser.SyntaxErrorException;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Generic features of constructs that cannot be expected to print on a single
 * line.
 */
public class Indentable extends AST {
	/**
	 * Print the whitespace that should appear at the start of the line when
	 * indenting to this depth.
	 * 
	 * @param out
	 *            Where to print the whitespace.
	 * @param depth
	 *            How much indentation to use.
	 */
	// TODO: Revert tabs
	public static void printIndentOn(PrintStream out, int depth) {
		for (; depth > 1; depth -= 2)
			out.print("        ");
		if (1 == depth)
			out.print("    ");
	}

	/**
	 * Print the string after indenting to the indicated depth.
	 * 
	 * @param out
	 *            Where to print the string.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	public static void printIndentOn(PrintStream out, int depth, String s) {
		printIndentOn(out, depth);
		out.print(s);
	}

	/**
	 * Print the string after indenting to the indicated depth, then terminate
	 * the line.
	 * 
	 * @param out
	 *            Where to print the string.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	public static void printIndentOnLn(PrintStream out, int depth, String s) {
		printIndentOn(out, depth);
		out.println(s);
	}

	/**
	 * Print this objects <b>toString</b> after indenting to the indicated
	 * depth, then terminate the line.
	 * 
	 * @param out
	 *            Where to print the <b>toString</b>.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, this.toString());
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		/* Has to be overwritten by child */
		return false;
	}
	public void table_visit(SymbolTable symbolTable){
		//pass
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		return null;
	}
}
