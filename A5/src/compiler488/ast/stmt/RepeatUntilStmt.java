package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ArrayList;

import compiler488.ast.Indentable;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.symbol.SymbolTable;

/**
 * Represents a loop in which the exit condition is evaluated after each pass.
 */
public class RepeatUntilStmt extends LoopingStmt {
	/**
	 * Print a description of the <b>repeat-until</b> construct.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, "repeat");
		body.printOn(out, depth + 1);
		Indentable.printIndentOnLn(out, depth, " until "  + expn );

	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		ArrayList<Instruction> condition = new ArrayList<>();
		condition.addAll(expn.machine_visit(symbolTable));
		ArrayList<Instruction> block = body.machine_visit(symbolTable);
		return MachineUtils.repeatUntil(condition, block);
	}
}
