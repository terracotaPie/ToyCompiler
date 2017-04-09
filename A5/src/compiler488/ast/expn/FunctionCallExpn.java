package compiler488.ast.expn;

import compiler488.ast.ASTList;
import compiler488.ast.decl.RoutineBody;
import compiler488.ast.decl.RoutineDecl;
import compiler488.ast.decl.ScalarDecl;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a function call with or without arguments.
 */
public class FunctionCallExpn extends Expn {
	private String ident; // The name of the function.

	private ASTList<Expn> arguments; // The arguments passed to the function.

	/** Returns a string describing the function call. */
	@Override
	public String toString() {
		if (arguments!=null) {
			return ident + " (" + arguments + ")";
		}
		else
			return ident + " " ;
	}

	public ASTList<Expn> getArguments() {
		return arguments;
	}

	public void setArguments(ASTList<Expn> args) {
		this.arguments = args;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		int num_args;
		num_args = 0;
		b = true;
		SymbolTableEntry s = semanticObject.getSymbolTable().getEntry(ident);

		// check that this is a routine that is a function
		if (!(s.getValue() instanceof RoutineDecl) && !(s.getVarType() == SymbolTableEntry.VarType.FUNC)) {
			return false;
		}

        RoutineDecl functionDeclaration = (RoutineDecl)s.getValue();
		RoutineBody functionBody = (RoutineBody)functionDeclaration.getRoutineBody();

		// check that this has the same number of arguments
	    if (arguments.size() != functionBody.getParameters().size()) {
	    	return false;
		}

		// check that the types match
		ScalarDecl param;
	    Expn arg;
	    Iterator<ScalarDecl> params = functionBody.getParameters().getIterator();
	    Iterator<Expn> args = arguments.getIterator();
	    while (params.hasNext() && args.hasNext()) {
	    	param = params.next();
	    	arg = args.next();

	    	boolean sameType = param.getType().equals(arg.getType());

	    	if (!sameType) {
	    		return false;
			}
		}

		// TODO: check return type

		return b;
	}

	@Override
	public Type getType() {
		/* Requires symbol table */
		return null;
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb) {
		SymbolTableEntry entry = sb.getEntry(ident);
		return entry.getType();
	}

	@Override
	public ArrayList<Instruction> machine_lhs_vist(SymbolTable symbolTable) {
		return null;
	}

	@Override
	public void table_visit(SymbolTable symbolTable){}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
        ArrayList<Instruction> functionCallInstructions = new ArrayList<>();
        ArrayList<Instruction> argVals = new ArrayList<>();
        ArrayList<Instruction> funcBody = new ArrayList<>();
        Iterator<Expn> args = arguments.getIterator();
        while (args.hasNext()) {
            Expn arg = args.next();
            argVals.addAll(arg.machine_visit(symbolTable));
        }
        RoutineDecl a = (RoutineDecl) symbolTable.getEntry(ident).getValue();
        funcBody.addAll(a.machine_visit(symbolTable));
        functionCallInstructions.addAll(argVals);
        functionCallInstructions.addAll(funcBody);
        return functionCallInstructions;
	}
}
