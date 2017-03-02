package compiler488.ast.stmt;

import compiler488.ast.expn.Expn;
import compiler488.semantics.SemanticObject;

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
		boolean b;
		b = lval.semantic_visit(semanticObject);
		b &= rval.semantic_visit(semanticObject);
		b &= lval.getType().equals(rval.getType());
		return b;
	}
}
