package compiler488.ast.expn;


import com.sun.org.apache.xpath.internal.operations.Bool;
import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
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
		boolean b;
		b = true;
		b &= left.semantic_visit(semanticObject);
		b &= right.semantic_visit(semanticObject);
		switch (opSymbol)
		{
			case "+":
			case "-":
			case "*":
			case "/":
			case ">":
			case ">=":
			case "<":
			case "<=":
			case "!=":
				b &= left.getType() instanceof IntegerType && right.getType() instanceof IntegerType; /* S31 */
				break;
			case "and":
			case "or":
				b &= left.getType() instanceof BooleanType && right.getType() instanceof BooleanType; /* S30 */
				break;
			case "=":
				b &= left.getType().equals(right.getType());
				break;
		}
		return b;
	}

	@Override
	public Type getType() {
		if (left.getType().equals(right.getType()))
			return left.getType(); /* S21 */
		else
			return null;
	}
	}
