package compiler488.ast.stmt;

import compiler488.ast.ASTList;
import compiler488.ast.decl.RoutineBody;
import compiler488.ast.decl.RoutineDecl;
import compiler488.ast.decl.ScalarDecl;
import compiler488.ast.expn.Expn;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTableEntry;

import java.util.Iterator;

/**
 * Represents calling a procedure.
 */
public class ProcedureCallStmt extends Stmt {
	private String name; // The name of the procedure being called.

	private ASTList<Expn> arguments; // The arguments passed to the procedure.

	/** Returns a string describing the procedure call. */
	@Override
	public String toString() {
		if (arguments!=null)
			return "Procedure call: " + name + " (" + arguments + ")";
		else
			return "Procedure call: " + name + " ";
	}

	public ASTList<Expn> getArguments() {
		return arguments;
	}

	public void setArguments(ASTList<Expn> args) {
		this.arguments = args;
	}

	public String getName() { return name; }

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		int num_param;
		num_param = 0;
		b = true;
		SymbolTableEntry s = semanticObject.getSymbolTable().getEntry(name);
		RoutineDecl procedureDeclaration = (RoutineDecl)s.getValue();
		RoutineBody procedureBody = (RoutineBody)procedureDeclaration.getRoutineBody();

		// check that this has the same number of arguments
		if (arguments.size() != procedureBody.getParameters().size()) {
			semanticObject.addError(String.format(
					"Incorrect number of arguments in %s. Expected %d, found %d.",
					name,
					procedureBody.getParameters().size(),
					arguments.size()));
			return false;
		}

		// check that the types match
		ScalarDecl param;
		Expn arg;
		Iterator<ScalarDecl> params = procedureBody.getParameters().getIterator();
		Iterator<Expn> args = arguments.getIterator();
		while (params.hasNext() && args.hasNext()) {
			param = params.next();
			arg = args.next();

			boolean sameType = param.getType().equals(arg.getType());

			if (!sameType) {
				return false;
			}
		}
		return b;
	}
}
