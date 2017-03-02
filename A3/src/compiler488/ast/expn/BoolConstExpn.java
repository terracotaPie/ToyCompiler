package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

/**
 * Boolean literal constants.
 */
public class BoolConstExpn extends ConstExpn
    {
    private boolean  value ;	/* value of the constant */

    /** Returns the value of the boolean constant */
    @Override
	public String toString () {
	return ( value ? "(true)" : "(false)" );
    }

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		return true;
	}

	@Override
	public Type getType() {
		return new BooleanType();
	}
}
