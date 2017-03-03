package compiler488.ast.decl;

import java.io.PrintStream;
import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.Indentable;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

/**
 * Holds the declaration of multiple elements.
 */
public class MultiDeclarations extends Declaration {
	/* The elements being declared */
	private ASTList<DeclarationPart> elements;

	public MultiDeclarations () {
		elements = new ASTList<DeclarationPart> ();
	}
	
	/**
	 * Returns a string that describes the array.
	 */
	@Override
	public String toString() {
		return  " : " + type ;
	}


	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		/* TODO: S47 */
		return true;
	}

	/**
	 * Print the multiple declarations of the same type.
	 * 
	 * @param out
	 *            Where to print the description.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	@Override
	public void printOn(PrintStream out, int depth) {
		Indentable.printIndentOn (out, depth, String.format("%s", elements));
		Indentable.printIndentOnLn (out, depth, this + " ");
	}


	public ASTList<DeclarationPart> getElements() {
		return elements;
	}

	public void setElements(ASTList<DeclarationPart> elements) {
		this.elements = elements;
	}
	public void table_visit(SymbolTable symbolTable) {
		ListIterator<DeclarationPart> iter_d;
		if (elements.size() > 0)
		{
			iter_d = elements.getIterator();
			while (iter_d.hasNext())
			{
				DeclarationPart t = iter_d.next();
				SymbolTableEntry.VarType vt;
				if (t instanceof ArrayDeclPart)
				{
					vt = SymbolTableEntry.VarType.ARRAY;
				}
				else
				{
					vt = SymbolTableEntry.VarType.SCALAR;
				}
			    symbolTable.addEntry(t.getName(),this, getType(), vt);
			}
		}
	}

}
