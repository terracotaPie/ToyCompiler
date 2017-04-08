package compiler488.ast.stmt;

import compiler488.ast.AST;
import compiler488.ast.ASTList;
import compiler488.ast.Printable;
import compiler488.ast.expn.BoolConstExpn;
import compiler488.ast.expn.Expn;
import compiler488.ast.expn.SkipConstExpn;
import compiler488.ast.expn.TextConstExpn;
import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import javax.xml.soap.Text;
import java.util.ArrayList;
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

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
	    ArrayList<Instruction> printer = new ArrayList<>();
	    ArrayList<Instruction> printValues = new ArrayList<>();
	    ArrayList<Instruction> printCommands = new ArrayList<>();

        Iterator<Printable> iterator = outputs.getIterator();
        Expn p;
        while (iterator.hasNext()) {
            p = (Expn)iterator.next();
            ArrayList<Instruction> printInst = p.machine_visit(symbolTable);

            // need to add to the beginning because of the stack pops from the top, so
            // the first one has to be the top
            printValues.addAll(0, printInst);

            // decide if we're using `PRINTI` or `PRINTC`
            Type t = p.getTypeFromSymbolTable(symbolTable);

            // TODO: print "false", "true", as necessary
            if (t instanceof IntegerType || t instanceof BooleanType) {
                printCommands.add(new Instruction(Machine.PRINTI));
            }
            else {
                if (p instanceof TextConstExpn) {
                    for (int i = 0; i < ((TextConstExpn)p).getValue().length(); i++) {
                        printCommands.add(new Instruction(Machine.PRINTC));
                    }
                }
            }
        }

        // print 1,2,3,4 -> [4,3,2,1,PRINTI, PRINTI, PRINTI, PRINTI]
        printer.addAll(printValues);
        printer.addAll(printCommands);
        return printer;
    }
}
