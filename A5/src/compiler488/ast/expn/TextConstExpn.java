package compiler488.ast.expn;

import compiler488.ast.Printable;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * Represents a literal text constant.
 */
public class TextConstExpn extends ConstExpn implements Printable {
	private String value; // The value of this literal.

	/** Returns a description of the literal text constant. */
	@Override
	public String toString() {
		return "\"" + value + "\"";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		return true;
	}

	@Override
	public Type getType() {
		return new Type();
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		return getType();
	}

	@Override
	public void table_visit(SymbolTable symbolTable){}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		// read string backwards
		ArrayList<Instruction> pushChars = new ArrayList();
		for (int i = value.length() - 1; i >= 0; i--) {
			System.out.println(value.charAt(i));
			// TODO: make sure that nothing is lost lol
			pushChars.add(new Instruction(Machine.PUSH, (short)value.charAt(i)));
		}
		return pushChars;
	}
}
