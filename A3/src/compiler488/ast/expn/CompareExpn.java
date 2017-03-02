package compiler488.ast.expn;

import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;

/**
 * Place holder for all ordered comparisions expression where both operands must
 * be integer expressions. e.g. < , > etc. comparisons
 */
public class CompareExpn extends BinaryExpn {
    @Override
    public Type getType() {
        return new IntegerType();
    }
}
