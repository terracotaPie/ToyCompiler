package compiler488.ast.expn;

import compiler488.ast.Printable;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

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

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		return getType();
	}

    @Override
    public ArrayList<Instruction> machine_lhs_vist(SymbolTable symbolTable) {
        return null;
    }

    @Override
	public void table_visit(SymbolTable symbolTable){}

    /**
     * Generate code for a new line statement (newline)
     * @param symbolTable
     * @return the following machine code </br>
     * <code>
     * PUSH '\n'
     * </code>
     */
	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		ArrayList<Instruction> newline = new ArrayList<>();
        newline.add(new Instruction(Machine.PUSH, '\n'));
        MachineUtils.programOffset++;
        MachineUtils.programOffset++;
		return newline;
	}
}
