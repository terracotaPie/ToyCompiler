package compiler488.ast.stmt;

import compiler488.ast.expn.Expn;
import compiler488.codegen.Instruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * Holds the assignment of an expression to a variable.
 * e.g. x := 2
 */
public class AssignStmt extends Stmt {
	/*
	 * lval is the location being assigned to, and rval is the value being
	 * assigned.
	 */
	private Expn lval, rval;

	/** Returns a string that describes the assignment statement. */
	// 1 + 1 := 2
	@Override
	public String toString() {
		return "Assignment: " + lval + " := " + rval;
	}

	public Expn getLval() {
		return lval;
	}

	public void setLval(Expn lval) {
		this.lval = lval;
	}

	public Expn getRval() {
		return rval;
	}

	public void setRval(Expn rval) {
		this.rval = rval;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		this.table_visit(semanticObject.getSymbolTable());
		boolean b, c;
		b = lval.semantic_visit(semanticObject);
		b &= rval.semantic_visit(semanticObject);
		c = lval.getTypeFromSymbolTable(semanticObject.getSymbolTable()).equals(rval.getTypeFromSymbolTable(semanticObject.getSymbolTable()));
		if (!c)
		{
			semanticObject.addError(String.format("Type mismatch on %d: %s and %s", line_num, lval, rval));
		}
		return b && c;
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
	    /* EX: i := 2+3 */

		ArrayList<Instruction> assignment = new ArrayList<>();

	    // [PUSH <location of i>, LOAD]
	    ArrayList<Instruction> location = lval.machine_visit(symbolTable);
	    // [PUSH 2, PUSH 3, ADD]
	    ArrayList<Instruction> value = lval.machine_visit(symbolTable);
	    // [STORE]
		Instruction store = new Instruction(Machine.STORE);

	    // [<location of i> (PUSH <location of i>, LOAD), 5 (PUSH 2, PUSH 3, ADD), STORE]
		assignment.addAll(location);
		assignment.addAll(value);
	    assignment.add(store);

		return assignment;
	}
}
