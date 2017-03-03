package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * Represents the boolean negation of an expression.
 */
public class NotExpn extends UnaryExpn {

    @Override
    public Type getType() {
        return new BooleanType(); /* S20 */
    }

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        SymbolTable st = semanticObject.getSymbolTable();

        boolean semanticallyCorrectOperand = operand.semantic_visit(semanticObject);
        boolean correctType = operand.getTypeFromSymbolTable(st) instanceof BooleanType;

        return semanticallyCorrectOperand && correctType;
    }
    @Override
    public void table_visit(SymbolTable symbolTable){}
}
