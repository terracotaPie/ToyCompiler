package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.decl.Declaration;
import compiler488.codegen.Instruction;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * Represents the declarations and instructions of a scope construct.
 */
public class Scope extends Stmt {
	private ASTList<Declaration> declarations; // The declarations at the top.

	private ASTList<Stmt> statements; // The statements to execute.

	private int depth = -1;
	public Scope() {
		declarations = new ASTList<Declaration>();
		statements = new ASTList<Stmt>();
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		semanticObject.pushScope();
		/* getNumScopes gives us the number of scopes, but our depth for the first scope is 0, so -- */
		depth = semanticObject.numActiveScopes() - 1;
		this.table_visit(semanticObject.getSymbolTable());
		boolean b;
		ListIterator<Declaration> iter_d;
		ListIterator<Stmt> iter_s;
		b = true;
		if (declarations.size() > 0)
		{
			iter_d = declarations.getIterator();
			while (iter_d.hasNext())
			{
				b &= iter_d.next().semantic_visit(semanticObject); /* S02 */
			}
		}
		if (!b)
		{
			semanticObject.addError("declaration error");
		}
		if (statements.size() > 0)
		{
			iter_s = statements.getIterator();
			while(iter_s.hasNext())
			{
				b &= iter_s.next().semantic_visit(semanticObject);
			}

		}
		if (!b)
		{
			semanticObject.addError("statement error");
		}
		semanticObject.popScope();
//		semanticObject.getSymbolTable().cleanCurrentScope();
//		semanticObject.getSymbolTable().closeScope();
		return b;
	}

	/**
	 * Print a description of the <b>scope</b> construct.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOnLn(out, depth, "Scope");
		Indentable.printIndentOnLn(out, depth + 1, "declarations");

		declarations.printOnSeperateLines(out, depth + 2);

		Indentable.printIndentOnLn(out, depth + 1, "statements");

		statements.printOnSeperateLines(out, depth + 2);

		Indentable.printIndentOnLn(out, depth, "End Scope");
	}

	public ASTList<Declaration> getDeclarations() {
		return declarations;
	}

	public ASTList<Stmt> getStatements() {
		return statements;
	}

	public void setDeclarations(ASTList<Declaration> declarations) {
		this.declarations = declarations;
	}

	public void setStatements(ASTList<Stmt> statements) {
		this.statements = statements;
	}

	public void table_visit(SymbolTable symbolTable) {
	    symbolTable.openScope();
		ListIterator<Declaration> iter_d;
		if (declarations.size() > 0)
		{
			iter_d = declarations.getIterator();
			while (iter_d.hasNext())
			{
				iter_d.next().table_visit(symbolTable);
			}
		}
		ListIterator<Stmt> iter_s;
		if (statements.size() > 0)
		{
			iter_s = statements.getIterator();
			while(iter_s.hasNext())
			{
				iter_s.next().table_visit(symbolTable);
			}
		}
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {

	    ArrayList<Instruction> instructions = new ArrayList<>();
	    /*
	     Add instructions common to all scopes:
	     PUSHMT
	     SETD n
	    */
	    Instruction pushStackPointer = new Instruction(Machine.PUSHMT);
	    Instruction display = new Instruction(Machine.SETD);
	    display.addNumberArg((short)depth);

	    instructions.add(pushStackPointer);
	    instructions.add(display);

		/* Machine visit on the declarations */
		if (declarations.size() > 0) {
            // Get the amount of space we need to allocate
            ListIterator<Declaration> declIterator = declarations.getIterator();
            short alloc = 0;
            while (declIterator.hasNext()) {
                Declaration decl = declIterator.next();
                alloc += decl.size_visit();
            }

            /*
             * PUSH UNDEFINED
             * PUSH n
             * DUPN
             */
            Instruction varInitial = new Instruction(Machine.PUSH, Machine.UNDEFINED);
            Instruction numVars = new Instruction(Machine.PUSH);
            numVars.addNumberArg(alloc);
            Instruction declareVars = new Instruction(Machine.DUPN);

            instructions.add(varInitial);
            instructions.add(numVars);
            instructions.add(declareVars);
        }

        /* Machine visit on the Statements */
        if (statements.size() > 0) {
            ListIterator<Stmt> stmtIterator = statements.getIterator();
            while (stmtIterator.hasNext()) {
                Stmt stmt = stmtIterator.next();
                instructions.addAll(stmt.machine_visit(symbolTable));
            }
        }

        return instructions;
	}
}
