package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * Represents the boolean negation of an expression.
 */
public class NotExpn extends UnaryExpn {

    @Override
    public Type getType() {
        return new BooleanType(); /* S20 */
    }

    @Override
    public ArrayList<Instruction> machine_lhs_vist(SymbolTable symbolTable) {
        return null;
    }

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        SymbolTable st = semanticObject.getSymbolTable();

        boolean semanticallyCorrectOperand = operand.semantic_visit(semanticObject);
        boolean correctType = operand.getTypeFromSymbolTable(st) instanceof BooleanType;
        if (!correctType) {
            semanticObject.addError("Incorrect operand type in `%s`".format(operand.toString()));;
        }

        return semanticallyCorrectOperand && correctType;
    }
    @Override
    public void table_visit(SymbolTable symbolTable){}


    /**
     * Generate machine code for `not (expn)`
     * @param symbolTable the symbol table generated in the semantic check
     * @return the following machine code <br>
     * <code>
     * operand machine code
     * @see MachineUtils#generateNegation()
     * </code>
     */
    @Override
    public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
        ArrayList<Instruction> negation = new ArrayList<>();
        ArrayList<Instruction> toNegate = operand.machine_visit(symbolTable);
        negation.addAll(toNegate);
        negation.addAll(MachineUtils.generateNegation());
        return negation;
    }
}
