package compiler488.ast.expn;

import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.symbol.SymbolTable;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

import java.util.ArrayList;

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

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		return getType();
	}

	@Override
	public ArrayList<Instruction> machine_lhs_vist(SymbolTable symbolTable) {
		return null;
	}

	/**
	 * Generates code for an integer constant
	 * @param symbolTable the symbol table generated in the semantic visit
	 * @return the following machine code <br>
	 * <code>
	 * PUSH (value)
	 * </code>
	 */
	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
	    ArrayList<Instruction> pushNumber = new ArrayList<>();
	    pushNumber.add(new Instruction(Machine.PUSH, value));
		MachineUtils.programOffset++;
		MachineUtils.programOffset++;
        return pushNumber;

	}
}
