package compiler488.ast.decl;

import java.io.PrintStream;
import java.util.ArrayList;

import compiler488.ast.Indentable;
import compiler488.codegen.Instruction;
import compiler488.parser.SyntaxErrorException;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry.VarType;

/**
 * Represents the declaration of a function or procedure.
 */
public class RoutineDecl extends Declaration {
	/*
	 * The formal parameters of the function/procedure and the
	 * statements to execute when the procedure is called.
	 */
	private RoutineBody routineBody;

	/**
	 * Returns a string indicating that this is a function with
	 * return type or a procedure, name, Type parameters, if any,
	 * are listed later by routineBody
	 */
	@Override
	public String toString() {
	  if(type==null)
	    {
	      return " procedure " + name;
	    }
	  else
	    {
	      return " function " + name + " : " + type ;
	    }
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		if (type == null)
			semanticObject.pushProcedure(); /* S08 */
		else
			semanticObject.pushFunction(type); /* S04 */
		b = routineBody.semantic_visit(semanticObject);
		semanticObject.popScope(); /* S05 S09 */
		// S11, 12, 13, 17, 18: done by `table_visit`
		return b;
	}

	@Override
	public void table_visit(SymbolTable symbolTable) {
		symbolTable.addEntry(name,this, getType(),
				getType() == null ? VarType.PROC : VarType.FUNC);
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		return null;
	}

	/**
	 * Prints a description of the function/procedure.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOn(out, depth, this + " ");
		routineBody.printOn(out, depth + 1);
	}

	public RoutineBody getRoutineBody() {
		return routineBody;
	}

	public void setRoutineBody(RoutineBody routineBody) {
		this.routineBody = routineBody;
	}

	@Override
	public int size_visit() {
		/*
		Params are considered part of the scope
		 */
		return 1;
	}
}
