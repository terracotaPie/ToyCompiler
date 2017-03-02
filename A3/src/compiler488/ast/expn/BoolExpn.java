package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;

/**
 * Place holder for all binary expression where both operands must be boolean
 * expressions.
 */
public class BoolExpn extends BinaryExpn {
    @Override
    public Type getType() {
        return new BooleanType();
    }
}
