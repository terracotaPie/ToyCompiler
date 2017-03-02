package compiler488.semantics;

import compiler488.ast.type.Type;
import compiler488.symbol.SymbolTable;

import java.util.Stack;

/**
 * Created by isthisnagee on 3/2/17.
 */
public class SemanticObject {

    public boolean S00_01; /* Program scope */
    public int S50; /* Loop levels */
    private Stack<Type> S35;
    private SymbolTable sym_table;

    public SemanticObject() {
        S35 = new Stack<Type>();
    }

    public boolean IsTopStackType(Type t) {
        if (S35.size() == 0)
            return false; /* S51/S52 */
        if (t == null)
            return S35.peek() == null; /* S52 */
        return t.equals(S35.peek()); /* S35 */
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
}
