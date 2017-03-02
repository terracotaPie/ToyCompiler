package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

/**
 *  References to a scalar variable.
 */
public class IdentExpn extends Expn implements Readable
{
    private String ident;  	// name of the identifier

    /**
     * Returns the name of the variable or function.
     */
    @Override
	public String toString () { return ident; }

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
    	// TODO: Check if it is in symbol table
		return true;
	}

	@Override
	public Type getType() {
    	// TODO: Grab from symbol table S37
		return null;
	}
}
