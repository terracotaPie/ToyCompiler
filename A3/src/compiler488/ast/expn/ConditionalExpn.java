package compiler488.ast.expn;


import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/** Represents a conditional expression (i.e., x>0?3:4). */
public class ConditionalExpn extends Expn {
	private Expn condition; // Evaluate this to decide which value to yield.

	private Expn trueValue; // The value is this when the condition is true.

	private Expn falseValue; // Otherwise, the value is this.

	/** Returns a string that describes the conditional expression. */
	@Override
	public String toString() {
		return "(" + condition + " ? " + trueValue + " : " + falseValue + ")";
	}

	public Expn getCondition() {
		return condition;
	}

	public void setCondition(Expn condition) {
		this.condition = condition;
	}

	public Expn getFalseValue() {
		return falseValue;
	}

	public void setFalseValue(Expn falseValue) {
		this.falseValue = falseValue;
	}

	public Expn getTrueValue() {
		return trueValue;
	}

	public void setTrueValue(Expn trueValue) {
		this.trueValue = trueValue;
	}

	@Override
	public Type getType() {
		return trueValue.getType(); /* S24 */
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		return trueValue.getTypeFromSymbolTable(sb);
	}

	@Override
	public void table_visit(SymbolTable symbolTable){}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		SymbolTable st = semanticObject.getSymbolTable();
		boolean b;
		b = condition.semantic_visit(semanticObject);
		b &= condition.getTypeFromSymbolTable(st) instanceof BooleanType; /* S30 */
		if (!b) {
			semanticObject.addError("The condition `%s ` should return a Boolean".format(condition.toString()));
		}
		b &= trueValue.semantic_visit(semanticObject);
		b &= falseValue.semantic_visit(semanticObject);
		b &= trueValue.getTypeFromSymbolTable(st).equals(falseValue.getTypeFromSymbolTable(st)); /* S33 */
		return b;
	}
}
