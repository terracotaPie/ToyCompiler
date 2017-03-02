package compiler488.ast.type;

import compiler488.ast.AST;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

public class Type extends AST {

    private VarType type;
    public Type(VarType type){
       this.type = type;
    }
    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        return true;
    }
    @Override
    public void table_visit(SymbolTable symbolTable){}

}
