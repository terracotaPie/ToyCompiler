package compiler488.ast.type;

import compiler488.ast.AST;
import compiler488.semantics.SemanticObject;

/**
 * A placeholder for types.
 */
public class Type extends AST {

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        return true;
    }
}
