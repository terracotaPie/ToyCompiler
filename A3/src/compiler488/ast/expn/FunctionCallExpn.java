package compiler488.ast.expn;

import compiler488.ast.ASTList;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

/**
 * Represents a function call with or without arguments.
 */
public class FunctionCallExpn extends Expn {
	private String ident; // The name of the function.

	private ASTList<Expn> arguments; // The arguments passed to the function.

	private Type return_type;

	/** Returns a string describing the function call. */
	@Override
	public String toString() {
		if (arguments!=null) {
			return ident + " (" + arguments + ")";
		}
		else
			return ident + " " ;
	}

	public ASTList<Expn> getArguments() {
		return arguments;
	}

	public void setArguments(ASTList<Expn> args) {
		this.arguments = args;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		int num_args;
		num_args = 0;
		b = true;
		// TODO: Wait for symbol table: Check if arguments/types match and set return_type
		return b;
	}

	@Override
	public Type getType() {
		return return_type; /* S28 */
	}
}
