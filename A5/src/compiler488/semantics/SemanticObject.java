package compiler488.semantics;

import compiler488.ast.type.Type;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by isthisnagee on 3/2/17.
 */
public class SemanticObject {

    public boolean S00_01; /* Program scope */
    public int S50; /* Loop levels */
    private Stack<Type> S35;
    private SymbolTable sym_table;

    private StringBuilder sb;

    public SemanticObject() {
        S35 = new Stack<Type>();
        sym_table = new SymbolTable();
        sb = new StringBuilder();
    }

    public boolean IsTopStackType(Type t) {
        if (S35.size() == 0)
            return false; /* S51/S52 */
        if (t == null)
            return S35.peek() == null; /* S52 */
        return t.equals(S35.peek()); /* S35 */
    }

    public void pushScope() {
       S35.push(null);
    }

    public void pushProcedure() {
        S35.push(null);
    }

    public void pushFunction(Type t) {
        S35.push(t);
    }

    public void popScope() {
        S35.pop();
    }

    public SymbolTable getSymbolTable()
    {
        return sym_table;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public void addError(String s)
    {
        sb.append(s + '\n');
    }
}
