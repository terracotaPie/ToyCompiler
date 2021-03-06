package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import javax.crypto.Mac;
import java.util.ArrayList;

/**
 * Boolean literal constants.
 */
public class BoolConstExpn extends ConstExpn
{
    private boolean  value ;	/* value of the constant */

    /** Returns the value of the boolean constant */
    @Override
    public String toString () {
        return ( value ? "(true)" : "(false)" );
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        return true;
    }

    @Override
    public void table_visit(SymbolTable symbolTable){}
    @Override
    public Type getType() {
        return new BooleanType();
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
     * Generate the following code based on the `value` of the  BoolConstExpn </br>
     * <code>
     * PUSH (value)
     * </code>
     * @param symbolTable the symbol table from semantic check
     * @return machine code representing a boolean constant
     */
    @Override
    public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
        ArrayList<Instruction> pushNumber = new ArrayList<>();
        pushNumber.add(new Instruction(Machine.PUSH, (value ? Machine.MACHINE_TRUE : Machine.MACHINE_FALSE)));
        MachineUtils.programOffset++;
        MachineUtils.programOffset++;
        return pushNumber;
    }
}
