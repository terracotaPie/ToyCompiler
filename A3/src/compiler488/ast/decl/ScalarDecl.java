package compiler488.ast.decl;

import compiler488.semantics.SemanticObject;

/**
 * Represents the declaration of a simple variable.
 */

public class ScalarDecl extends Declaration {

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		/* TODO: S10 S47 */
		return true;
	}

	/**
	 * Returns a string describing the name and type of the object being
	 * declared.
	 */
	@Override
	public String toString() {
		return  name + " : " + type ;
	}
}
