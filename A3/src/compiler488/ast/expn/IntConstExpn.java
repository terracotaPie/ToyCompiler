package compiler488.ast.expn;

import compiler488.symbol.SymbolTable;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

/**
 * Represents a literal integer constant.
 */
public class IntConstExpn extends ConstExpn
    {
    private Integer value;	// The value of this literal.

    /** Returns a string representing the value of the literal. */
    @Override
	public String toString () { return value.toString (); }

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
    @Override
    public void table_visit(SymbolTable symbolTable){}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		return true;
	}

	@Override
	public Type getType() {
		return new IntegerType();
	}
}
