package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;

/**
 * Place holder for all binary expression where both operands must be integer
 * expressions.
 */
public class ArithExpn extends BinaryExpn {
    @Override
    public Type getType() {
        return new IntegerType();
    }
}
