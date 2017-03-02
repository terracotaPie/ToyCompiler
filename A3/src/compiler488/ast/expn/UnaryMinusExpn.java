package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * Represents negation of an integer expression
 */
public class UnaryMinusExpn extends UnaryExpn {

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        boolean b;
        b = operand.semantic_visit(semanticObject);
        return b && operand.getType().equals(new IntegerType()); /* S31 */
    }

    @Override
    public Type getType() {
        return new IntegerType(); /* S21 */
    }
    @Override
    public void table_visit(SymbolTable symbolTable){}
}
