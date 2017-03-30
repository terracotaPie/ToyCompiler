package compiler488.ast.stmt;

import compiler488.ast.AST;
import compiler488.ast.ASTList;
import compiler488.ast.Printable;
import compiler488.ast.expn.Expn;
import compiler488.ast.expn.SkipConstExpn;
import compiler488.ast.expn.TextConstExpn;
import compiler488.ast.type.IntegerType;
import compiler488.semantics.SemanticObject;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * The command to write data on the output device.
 */
public class WriteStmt extends Stmt {
	private ASTList<Printable> outputs; // The objects to be printed.

	public WriteStmt () {
		outputs = new ASTList<Printable> ();
	}
	
	/** Returns a description of the <b>write</b> statement. */
	@Override
	public String toString() {
		return "write " + outputs;
	}

	public ASTList<Printable> getOutputs() {
		return outputs;
	}

	public void setOutputs(ASTList<Printable> outputs) {
		this.outputs = outputs;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		b = true;
		if (0 == outputs.size())
			return false;
		else {
			ListIterator<Printable> iterator = outputs.getIterator();

			Printable p;
			while (iterator.hasNext())
			{
				p = iterator.next();
				if (p instanceof AST)
				{
					b &= ((AST) p).semantic_visit(semanticObject);
					b &= p instanceof Expn && ((Expn) p).semantic_visit(semanticObject) &&
							((Expn) p).getTypeFromSymbolTable(semanticObject.getSymbolTable()) instanceof IntegerType ||
					p instanceof TextConstExpn ||
					p instanceof SkipConstExpn; /* S31 */
				}

			}
		}
		return b;
	}
}
