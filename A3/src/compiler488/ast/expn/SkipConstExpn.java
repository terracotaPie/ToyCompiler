package compiler488.ast.expn;

import compiler488.ast.Printable;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

/**
 * Represents the special literal constant associated with writing a new-line
 * character on the output device.
 */
public class SkipConstExpn extends ConstExpn implements Printable {
	/** Returns the string <b>"skip"</b>. */
	@Override
	public String toString() {
		return " newline ";
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		return true;
	}

	@Override
	public Type getType() {
		return new Type();
	}
}
