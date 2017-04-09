package compiler488.symbol;

import compiler488.ast.decl.Declaration;
import compiler488.ast.decl.RoutineBody;
import compiler488.ast.decl.RoutineDecl;
import compiler488.ast.type.Type;
import compiler488.ast.AST;

/**
 * Created by theo on 2017-03-02.
 */
public class SymbolTableEntry {

    public short getAddr() {
        return addr;
    }



    public enum VarType{
        FUNC,
        PROC,
        ARRAY,
        SCALAR,
    }

    /**
     *  Identifier for this symbol
     */
    private String name;

    private Type type;

    private VarType varType;

    /* level of scope*/
    private VarType t;

    /* Value of variable */
    private AST value;

    /* position within this scopes*/
    private short addr;

    /* scope depth */
    public int depth;

    public SymbolTableEntry(String name, Declaration value, Type type, int depth, VarType varType) {
        this.name  = name;
        this.value = value;
        this.type  = type;
        this.depth = depth;
        this.t = varType;
    }

    /* Modifiers ID */
    public String getName() {
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
    public VarType getVarType() {
        return t;
    }

    public SymbolTableEntry setType(Type type) {
        this.type = type;
        return this;
    }

    public void setAddr(short i) {
        this.addr = i;
    }

}
