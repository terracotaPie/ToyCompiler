package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ArrayList;

import compiler488.ast.Indentable;
import compiler488.ast.expn.Expn;
import compiler488.ast.type.BooleanType;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * Represents an if-then or an if-then-else construct.
 */
public class IfStmt extends Stmt {
	// The condition that determines which branch to execute.
	private Expn condition;

	// Represents the statement to execute when the condition is true.
	private Stmt whenTrue;

	// Represents the statement to execute when the condition is false.
	private Stmt whenFalse = null;

	/**
	 * Print a description of the <b>if-then-else</b> construct. If the
	 * <b>else</b> part is empty, just print an <b>if-then</b> construct.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, "if " + condition + " then ");
		whenTrue.printOn(out, 	depth + 1);
		if (whenFalse != null) {
			Indentable.printIndentOnLn(out, depth, "else");
			whenFalse.printOn(out, depth + 1);
		}
		Indentable.printIndentOnLn(out, depth, "End if");
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		SymbolTable st = semanticObject.getSymbolTable();
		b = condition.getTypeFromSymbolTable(st).equals(new BooleanType()); /* S30 */
		if (whenTrue != null)
			b &= whenTrue.semantic_visit(semanticObject);
		if (whenFalse != null)
			b &= whenFalse.semantic_visit(semanticObject);
		return b;
	}

	public Expn getCondition() {
		return condition;
	}

	public void setCondition(Expn condition) {
		this.condition = condition;
	}

	public Stmt getWhenFalse() {
		return whenFalse;
	}

	public void setWhenFalse(Stmt whenFalse) {
		this.whenFalse = whenFalse;
	}

	public Stmt getWhenTrue() {
		return whenTrue;
	}

	public void setWhenTrue(Stmt whenTrue) {
		this.whenTrue = whenTrue;
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		ArrayList<Instruction> conditionInstructions = condition.machine_visit(symbolTable);
		ArrayList<Instruction> trueBlock = whenTrue.machine_visit(symbolTable);
		ArrayList<Instruction> falseBlock;
		if (whenFalse == null) {
		    return MachineUtils.ifThen(conditionInstructions, trueBlock);
		} else {
			falseBlock = whenFalse.machine_visit(symbolTable);
            return MachineUtils.ifThenElse(conditionInstructions, trueBlock, falseBlock);
		}
	}
}
