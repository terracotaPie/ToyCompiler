package compiler488.ast.stmt;

import compiler488.ast.AST;
import compiler488.ast.ASTList;
import compiler488.ast.Printable;
import compiler488.ast.Readable;
import compiler488.ast.expn.Expn;
import compiler488.ast.expn.IdentExpn;
import compiler488.ast.expn.SubsExpn;
import compiler488.ast.type.IntegerType;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ListIterator;

/**
 * The command to read data into one or more variables.
 */
public class ReadStmt extends Stmt {
	
	private ASTList<Readable> inputs; // A list of locations to put the values read.

	public ReadStmt () {
		inputs = new ASTList<Readable> ();
	}
	
	/** Returns a string describing the <b>read</b> statement. */
	@Override
	public String toString() {
		return "read " + inputs;
	}

	public ASTList<Readable> getInputs() {
		return inputs;
	}

	public void setInputs(ASTList<Readable> inputs) {
		this.inputs = inputs;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		SymbolTable st;

		b = true;
		st = semanticObject.getSymbolTable();

		if (0 == inputs.size())
			return false;
		else {
			ListIterator<Readable> iterator = inputs.getIterator();

			Readable p;
			while (iterator.hasNext())
			{
				p = iterator.next();
				b &= (p instanceof IdentExpn && ((IdentExpn)p).semantic_visit(semanticObject)
						&& ((IdentExpn)p).getTypeFromSymbolTable(st) instanceof IntegerType) ||
						(p instanceof SubsExpn && ((SubsExpn)p).semantic_visit(semanticObject)
								&& ((SubsExpn)p).getTypeFromSymbolTable(st) instanceof IntegerType); /* S31 */

			}
		}
		return b;
	}
}
