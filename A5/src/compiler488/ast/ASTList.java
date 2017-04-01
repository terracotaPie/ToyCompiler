package compiler488.ast;

import compiler488.codegen.Instruction;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * For nodes with an arbitrary number of children.
 */
public class ASTList<E> extends AST {
	/*
	 * Keep the list here. We delegate rather than subclass LinkedList
	 * because Java won't let us override the return type for addLast.
	 */
	private LinkedList<E> ll;

	/**
	 * Create an empty list.
	 */
	public ASTList() {
		ll = new LinkedList<E>();
	}

	/**
	 * Create a list with one element.
	 */
	public ASTList(E ast) {
		ll = new LinkedList<E>();
		ll.addLast(ast);
	}

	/**
	 * The number of elements in the list.
	 */
	public int size() {
		return ll.size();
	}

	/**
	 * Append an element to the list, then return the list. This is a
	 * pure-side-effect method, so it doesn't need to return anything.
	 * However, we like the conciseness gained when such methods return the
	 * target object.
	 */
	public ASTList addLast(E ast) {
		ll.addLast(ast);
		return this;
	}

	/**
	 * Ask each element of the list to print itself using
	 * <b>printOn(out,depth)</b>.  This should only be used when the
	 * elements are typically printed on seperate lines, otherwise they may
	 * not implement <b>printOn</b>. If the list is empty, print
	 * <b>&gt;&gt;empty&lt;&lt;</b> follwed by a new-line.
	 * 
	 * @param out
	 *            Where to print the list.
	 * @param depth
	 *            How much indentation to use while printing.
	 */
	public void printOnSeperateLines(PrintStream out, int depth) {
		ListIterator iterator = ll.listIterator();
		if (iterator.hasNext())
			while (iterator.hasNext())
				((Indentable) iterator.next()).printOn(out, depth);
		else
			Indentable.printIndentOn(out, depth, ">>empty<<\n");
	}

	public boolean semantic_visit(SemanticObject semanticObject) {
		if (0 == ll.size())
			return true;
		else {
			ListIterator<E> iterator = ll.listIterator();
			boolean b;
			b = true;
			while (iterator.hasNext())
				b &= ((AST)iterator.next()).semantic_visit(semanticObject);

			return b;
		}
	}

	/**
	 * Return the contatenation of the strings obtained by sending
	 * <b>toString</b> to each element.
	 */
	@Override
	public String toString() {
		if (0 == ll.size())
			return "";
		else {
			ListIterator<E> iterator = ll.listIterator();

			StringBuffer result = new StringBuffer(iterator.next().toString());
			while (iterator.hasNext())
				result.append(", " + iterator.next());

			return result.toString();
		}
	}

	public ListIterator<E> getIterator()
	{
		return ll.listIterator();
	}

	public void table_visit(SymbolTable symbolTable){
		for (E e: ll) {
			((AST)e).table_visit(symbolTable);
		}
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		return null;
	}
}
