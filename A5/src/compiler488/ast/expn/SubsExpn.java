package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

import java.util.ArrayList;

/**
 * References to an array element variable
 * 
 * Treat array subscript operation as a special form of unary expression.
 * operand must be an integer expression
 */
public class SubsExpn extends UnaryExpn implements Readable {

	private String variable; // name of the array variable

	/** Returns a string that represents the array subscript. */
	@Override
	public String toString() {
		return (variable + "[" + operand + "]");
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	@Override
	public Type getType() {
		// Requires Symbol table
		return null;
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb)
	{
		SymbolTableEntry entry = sb.getEntry(variable);
		return entry.getType();
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		SymbolTable st;

		st = semanticObject.getSymbolTable();
		b = true;

		b &= operand.semantic_visit(semanticObject) && operand.getTypeFromSymbolTable(st) instanceof IntegerType; /* S31 */

		return b;
	}
	@Override
	public void table_visit(SymbolTable symbolTable){
	    // TODO
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
	    // GET VARIABLE
		ArrayList<Instruction> ithElem = new ArrayList<>();
		ArrayList<Instruction> index = operand.machine_visit(symbolTable);
		// adjust index
		// TODO
		return ithElem;
	}
}
