package compiler488.ast.stmt;


import compiler488.semantics.SemanticObject;

/**
 * Placeholder for the scope that is the entire program
 */
public class Program extends Scope {

    public boolean semantic_visit(SemanticObject semanticObject)
    {
        boolean b;
        b = getDeclarations().semantic_visit(semanticObject);
        b &= getStatements().semantic_visit(semanticObject);
        return b;
    }

}
