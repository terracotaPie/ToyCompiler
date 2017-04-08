package compiler488.ast.decl;

import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.parser.SyntaxErrorException;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry.VarType;

import java.util.ArrayList;

/**
 * Represents the declaration of a simple variable.
 */

public class ScalarDecl extends Declaration {

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		/* S10, S47 handled by table */
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
	@Override
	public void table_visit(SymbolTable symbolTable) {
		symbolTable.addEntry(name,this, getType(), VarType.SCALAR);
	}

	@Override
	public int size_visit() {
		return 1;
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		ArrayList<Instruction> get_param_instructions = new ArrayList<>();
		get_param_instructions.add(new Instruction(Machine.PUSH, 1));
		get_param_instructions.add(new Instruction(Machine.SUB));
		return get_param_instructions;
	}

}
