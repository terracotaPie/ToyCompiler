package compiler488.ast.expn;

import compiler488.ast.ASTList;
import compiler488.ast.decl.RoutineDecl;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

/**
 * Represents a function call with or without arguments.
 */
public class FunctionCallExpn extends Expn {
	private String ident; // The name of the function.

	private ASTList<Expn> arguments; // The arguments passed to the function.

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
		SymbolTableEntry s = semanticObject.getSymbolTable().getEntry(ident);

		// check that this is a routine that is a function
		if (!(s.getValue() instanceof RoutineDecl) && !(s.getVarType() == SymbolTableEntry.VarType.FUNC)) {
			return false;
		}


		return b;
	}

	@Override
	public Type getType() {
		/* Requires symbol table */
		return null;
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		SymbolTableEntry entry = sb.getEntry(ident);
		return entry.getType();
	}

	@Override
	public void table_visit(SymbolTable symbolTable){}
}
