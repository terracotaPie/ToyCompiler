package compiler488.ast.expn;

import compiler488.ast.Readable;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

import java.util.ArrayList;

/**
 *  References to a scalar variable.
 */
public class IdentExpn extends Expn implements Readable
{
    private String ident;  	// name of the identifier

    /**
     * Returns the name of the variable or function.
     */
    @Override
	public String toString () { return ident; }

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
    	boolean exists = semanticObject.getSymbolTable().getEntry(ident) != null;
    	if (!exists) {
			semanticObject.addError("%s is not declared".format(ident));
			return false;
		}
		return true;
	}

	@Override
	public Type getType() {
    	// Requires symbol table
		return null;
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		SymbolTableEntry entry = sb.getEntry(ident);
		return entry.getType();
	}

	@Override
	public void table_visit(SymbolTable symbolTable){}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		return null;
	}
}
