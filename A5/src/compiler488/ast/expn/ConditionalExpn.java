package compiler488.ast.expn;


import compiler488.ast.type.BooleanType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

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


	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
	    ArrayList<Instruction> output = new ArrayList<>();
		/*
		TODO: this offset is evaluated here, we need to propogate this evaluation...
		 */
		ArrayList<Instruction> conditionInstructions = condition.machine_visit(symbolTable);
		ArrayList<Instruction> trueBlock = trueValue.machine_visit(symbolTable);
		ArrayList<Instruction> falseBlock = falseValue.machine_visit(symbolTable);

        int lastConditionInst = conditionInstructions.size() - 1;
        int offset = conditionInstructions.get(lastConditionInst).getLineNumber();

        int breakLines = 2;
		int trueLines = MachineUtils.numLines(trueBlock);
		int falseLines = MachineUtils.numLines(falseBlock);

		/* TODO: might need a -1 */
		int trueLineStart = offset + breakLines;
		int falseLineStart = trueLineStart + trueLines + breakLines;
		int exitLine = falseLineStart + falseLines;

		/*
		 codegen(condition)
		 PUSH <else start>
		 BF
		 codegen(trueblock)
		 PUSH <exit line>
		 BR (exit)
		 codegen(falseblock)
		 // exit line num
		 */
		output.addAll(conditionInstructions);
		output.add(new Instruction(Machine.PUSH, falseLineStart));
		output.add(new Instruction(Machine.BF));

		output.addAll(trueBlock);
		output.add(new Instruction(Machine.PUSH, exitLine));
		output.add(new Instruction(Machine.BR));

		output.addAll(falseBlock);

		/* jump to true, and true */

		return output;
	}
}
