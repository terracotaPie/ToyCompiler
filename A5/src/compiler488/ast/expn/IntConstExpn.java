package compiler488.ast.expn;

import compiler488.codegen.Instruction;
import compiler488.symbol.SymbolTable;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.semantics.SemanticObject;

import java.util.ArrayList;

/**
 * Represents a literal integer constant.
 */
public class IntConstExpn extends ConstExpn
    {
    private Integer value;	// The value of this literal.

    /** Returns a string representing the value of the literal. */
    @Override
	public String toString () { return value.toString (); }

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
    @Override
    public void table_visit(SymbolTable symbolTable){}

		@Override
		public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
			return null;
		}

		@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		return true;
	}

	@Override
	public Type getType() {
		return new IntegerType();
	}

		@Override
		public Type getTypeFromSymbolTable(SymbolTable sb) {
			return getType();
		}
	}
