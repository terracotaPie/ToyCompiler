
package compiler488.ast.stmt;


import compiler488.semantics.SemanticObject;

/**
 * Placeholder for the scope that is the entire program
 */
public class Program extends Scope {

    public boolean semantic_visit(SemanticObject semanticObject)
    {
        boolean b;
        semanticObject.S00_01 = true;
        b = super.semantic_visit(semanticObject);
        semanticObject.S00_01 = false;
        return b;
    }

}