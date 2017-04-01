package compiler488.ast.decl;

import compiler488.ast.AST;
import compiler488.codegen.Instruction;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * The common features of declarations' parts.
 */
public class DeclarationPart extends AST {

	/** The name of the thing being declared. */
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		return true;
	}
    public void table_visit(SymbolTable symbolTable) {
		//pass
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		return null;
	}

}
