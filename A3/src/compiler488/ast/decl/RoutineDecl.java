package compiler488.ast.decl;

import java.io.PrintStream;

import compiler488.ast.Indentable;
import compiler488.ast.type.Type;
import compiler488.ast.type.VarType;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

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
		if (b)
		{
			/* TODO: Assign routine to Symbol table  S11 S12 S13 S17 S18*/
		}
		return b;
	}

	@Override
	public void table_visit(SymbolTable symbolTable) {
		if(type==null)
		{
			type = new Type(VarType.PROC);
		}
		else
		{
			type = new Type(VarType.FUNC);
		}
		symbolTable.addEntry(name,this,type);
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
}
