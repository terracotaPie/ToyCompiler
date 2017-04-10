package compiler488.ast.decl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.stmt.Scope;
import compiler488.codegen.Instruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * Represents the parameters and instructions associated with a
 * function or procedure.
 */
public class RoutineBody extends Indentable {
	private ASTList<ScalarDecl> parameters = new ASTList<>(); // The formal parameters of the routine.
	private Scope body; // Execute this scope when routine is called.

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		b = true;
		ListIterator<ScalarDecl> iterator;
		if (parameters != null && parameters.size() > 0)
		{
			iterator = parameters.getIterator();
			while (iterator.hasNext())
			{
				b &= iterator.next().semantic_visit(semanticObject);
			}
		}
		b &= body.semantic_visit(semanticObject);
		return b;
	}

	/**
	 *
	 * @param symbolTable
	 * @return
	 */
	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {

		ListIterator<ScalarDecl> iterator;
		short size = 0;
		if (parameters.size() > 0)
		{
			iterator = parameters.getIterator();
			while (iterator.hasNext())
			{
				size += iterator.next().size_visit();
			}
		}

        ArrayList<Instruction> routineBodyInst = new ArrayList<Instruction>();
		ArrayList<Instruction> switchInstr = new ArrayList<Instruction>();
        ArrayList<Instruction> scopeInstr = body.machine_visit(symbolTable);

        ArrayList<Instruction> modifiedScopeInstr = new ArrayList<>();
        for (Instruction i : scopeInstr) {
        	if (i.getCode() == Machine.ADDR) {
        		short newAddrLL = (short)(i.getArgs().get(0) + 1);
				short addrON = i.getArgs().get(1);
				Instruction modifiedAddr = new Instruction(Machine.ADDR, newAddrLL, addrON);
				modifiedScopeInstr.add(modifiedAddr);
			} else if (i.getCode() == Machine.SETD) {
        	    short newLL = (short)(i.getArgs().get(0) - 1);
        	    Instruction modifiedSetd = new Instruction(Machine.SETD, newLL);
        	    modifiedScopeInstr.add(modifiedSetd);
            } else {
        		modifiedScopeInstr.add(i);
			}
		}


        switchInstr.add(new Instruction(Machine.PUSH, size));
        switchInstr.add(new Instruction(Machine.SUB));


		routineBodyInst.addAll(modifiedScopeInstr);
		routineBodyInst.addAll(1, switchInstr);
		return routineBodyInst;
	}

	/**
	 * Print a description of the formal parameters and the scope for this
	 * routine.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		if (parameters != null)
			out.println("(" + parameters + ")");
		else
			out.println(" ");
		body.printOn(out, depth);
	}

	public Scope getBody() {
		return body;
	}

	public void setBody(Scope body) {
		this.body = body;
	}

	public ASTList<ScalarDecl> getParameters() {
		return parameters;
	}

	public void setParameters(ASTList<ScalarDecl> parameters) {
		this.parameters = parameters;
	}
}
