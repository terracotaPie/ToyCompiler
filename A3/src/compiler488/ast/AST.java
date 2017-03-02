package compiler488.ast;

import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * This is a placeholder at the top of the Abstract Syntax Tree hierarchy. It is
 * a convenient place to add common behaviour.
 * @author  Dave Wortman, Marsha Chechik, Danny House
 */
public abstract class AST {

	public final static String version = "Winter 2017";

	public abstract boolean semantic_visit(SemanticObject semanticObject);
	public abstract void table_visit(SymbolTable symbolTable);
}
