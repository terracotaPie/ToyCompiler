package compiler488.ast.expn;

import compiler488.ast.Printable;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * Represents a literal text constant.
 */
public class TextConstExpn extends ConstExpn implements Printable {
	private String value; // The value of this literal.

	/** Returns a description of the literal text constant. */
	@Override
	public String toString() {
		return "\"" + value + "\"";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		return true;
	}

	@Override
	public Type getType() {
		return new Type();
	}
	@Override
	public void table_visit(SymbolTable symbolTable){}
}
