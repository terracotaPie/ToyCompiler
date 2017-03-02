package compiler488.ast.expn;


import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

/**
 * The common features of binary expressions.
 */
public class BinaryExpn extends Expn
    {
    Expn left, right;	/* Left and right operands of the binary operator. */
    String opSymbol;	/* Name of the operator. */

    /** Returns a string that represents the binary expression. */
    @Override
	public String toString ()
	{
	return ("(" + left + ")" + opSymbol + "(" + right + ")");
    }

	public Expn getLeft() {
		return left;
	}

	public void setLeft(Expn left) {
		this.left = left;
	}

	public String getOpSymbol() {
		return opSymbol;
	}

	public void setOpSymbol(String opSymbol) {
		this.opSymbol = opSymbol;
	}

	public Expn getRight() {
		return right;
	}

	public void setRight(Expn right) {
		this.right = right;
	}

		@Override
		public boolean semantic_visit(SemanticObject semanticObject) {
			return false;
		}

		@Override
		public Type getType() {
			if (left.getType().equals(right.getType()))
				return left.getType();
			else
				return null;
		}
	}
