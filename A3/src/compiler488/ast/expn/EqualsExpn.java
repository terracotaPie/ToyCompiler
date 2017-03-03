package compiler488.ast.expn;

import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.symbol.SymbolTable;

/**
 * Place holder for all binary expression where both operands could be either
 * integer or boolean expressions. e.g. = and not = comparisons
 */
public class EqualsExpn extends BinaryExpn {
    @Override
    public Type getTypeFromSymbolTable(SymbolTable sb) {
        return getType();
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
