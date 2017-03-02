package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

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
		// TODO: Check symbol table for type
		return null;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		b = true;

		b &= operand.semantic_visit(semanticObject) && operand.getType() instanceof IntegerType; /* S31 */

		// TODO: Check indent for variable, then check if it is integer

		return b;
	}
}
