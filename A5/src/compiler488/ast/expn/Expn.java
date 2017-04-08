package compiler488.ast.expn;

import compiler488.ast.AST;
import compiler488.ast.Printable;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * A placeholder for all expressions.
 */
public abstract class Expn extends AST implements Printable {
    public abstract Type getType();
    public abstract Type getTypeFromSymbolTable(SymbolTable sb);

    public abstract ArrayList<Instruction> machine_lhs_vist(SymbolTable symbolTable);
}
