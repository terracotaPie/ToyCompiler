package compiler488.ast.decl;

import compiler488.ast.Indentable;
import compiler488.ast.type.Type;
import compiler488.symbol.SymbolTable;

/**
 * The common features of declarations.
 */
public abstract class Declaration extends Indentable {
	/** The type of thing being declared. */
	protected Type type=null;

	/** The name of the thing being declared. */
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return  String.format("var %s %s", type, name);
	}

	public abstract void table_visit(SymbolTable symbolTable);
	public abstract int size_visit();
}
