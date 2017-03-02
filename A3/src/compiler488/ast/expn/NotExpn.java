package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

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
        return operand.semantic_visit(semanticObject) && operand.getType() instanceof BooleanType; /* S30 */
    }
}
