package compiler488.ast.expn;


import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

/**
 * The common features of unary expressions.
 */
public abstract class UnaryExpn extends Expn
    {
    Expn  operand ;	/* operand of the unary  operator. */
    String opSymbol;	/* Name of the operator. */

    /** Returns a string that represents the unary expression. */
    @Override
	public String toString ()
	{
	return ( opSymbol + "(" + operand + ")");
    }

	public Expn getOperand() {
		return operand;
	}

	public void setOperand(Expn operand) {
		this.operand = operand;
	}

	public String getOpSymbol() {
		return opSymbol;
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		return operand.getTypeFromSymbolTable(sb);
	}

	public void setOpSymbol(String opSymbol) {
	this.opSymbol = opSymbol;
	}
}
