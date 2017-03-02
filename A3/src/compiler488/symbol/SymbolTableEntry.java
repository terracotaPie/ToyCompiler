package compiler488.symbol;

import compiler488.ast.type.Type;
import compiler488.ast.AST;

/**
 * Created by theo on 2017-03-02.
 */
public class SymbolTableEntry {

    /**
     *  Identifier for this symbol
     */
    private String name;

    private Type type;

    /* level of scope*/
    public int depth;

    /* Value of variable */
    private AST value;

    /* position within this scopes*/
    private int orderNo;
    /* scope depth */

    public SymbolTableEntry(String name, AST value, Type type,int depth) {
        this.name  = name;
        this.value = value;
        this.type  = type;
        this.depth = depth;
    }

    /* Modifiers ID */
    public String getIdentifier() {
        return name;
    }

    public SymbolTableEntry setIdentifier(String name) {
        this.name = name;
        return this;
    }

    /* Modifiers Value */
    public AST getValue() {
        return value;
    }

    public SymbolTableEntry setValue(AST value) {
        this.value = value;
        return this;
    }

    /* Modifiers Type */
    public Type getType() {
        return type;
    }

    public SymbolTableEntry setType(Type type) {
        this.type = type;
        return this;
    }

}
