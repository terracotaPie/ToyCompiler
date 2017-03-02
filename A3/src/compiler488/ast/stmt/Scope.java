package compiler488.ast.stmt;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.ast.decl.Declaration;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

/**
 * Represents the declarations and instructions of a scope construct.
 */
public class Scope extends Stmt {
	private ASTList<Declaration> declarations; // The declarations at the top.

	private ASTList<Stmt> statements; // The statements to execute.

	public Scope() {
		declarations = new ASTList<Declaration>();
		statements = new ASTList<Stmt>();
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
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
		if (statements.size() > 0)
		{
			iter_s = statements.getIterator();
			while(iter_s.hasNext())
			{
				b &= iter_s.next().semantic_visit(semanticObject);
			}

		}
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
		symbolTable.closeScope();
	}

}
