package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ArrayList;

import compiler488.ast.Indentable;
import compiler488.ast.expn.Expn;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import javax.swing.plaf.synth.SynthButtonUI;

/**
 * The command to return from a function or procedure.
 */
public class ReturnStmt extends Stmt {
	// The value to be returned by a function.
	private Expn value = null;

	/**
	 * Print <b>return</b> or <b>return with </b> expression on a line, by itself.
	 * 
	 * @param out
	 *            Where to print.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOn(out, depth);
		if (value == null)
			out.println("return ");
		else
			out.println("return with " + value );
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		SymbolTable st = semanticObject.getSymbolTable();
		return value.semantic_visit(semanticObject) && semanticObject.IsTopStackType(value.getTypeFromSymbolTable(st));
	}

	public Expn getValue() {
		return value;
	}

	public void setValue(Expn value) {
		this.value = value;
	}

    /**
     * The funtcion handles moving the return statement to where it needs to be
     * @param symbolTable
     * @return
     */
	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
	    if (value != null) {
	        return value.machine_visit(symbolTable);
        }
        return new ArrayList<>();
	}
}
