package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * Represents negation of an integer expression
 */
public class UnaryMinusExpn extends UnaryExpn {

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        boolean b;
        SymbolTable st = semanticObject.getSymbolTable();
        b = operand.semantic_visit(semanticObject);
        return b && operand.getTypeFromSymbolTable(st).equals(new IntegerType()); /* S31 */
    }

    @Override
    public Type getType() {
        return new IntegerType(); /* S21 */
    }

    @Override
    public ArrayList<Instruction> machine_lhs_vist(SymbolTable symbolTable) {
        return null;
    }

    @Override
    public void table_visit(SymbolTable symbolTable){}

    /**
     * Generate a minus
     * @param symbolTable the symbol table from the semantic check
     * @return the following machine code </br>
     * <code>
     * machine visit for val </br>
     * NEG
     * </code>
     *
     */
    @Override
    public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
        ArrayList<Instruction> negatedVal = new ArrayList<>();
        MachineUtils.programOffset += 1;
        ArrayList<Instruction> val = operand.machine_visit(symbolTable);
        negatedVal.addAll(val);
        negatedVal.add(new Instruction(Machine.NEG));
        return negatedVal;
    }
}
