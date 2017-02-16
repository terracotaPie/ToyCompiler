package compiler488.ast.stmt;

import compiler488.ast.ASTList;
import compiler488.ast.Printable;

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
}
