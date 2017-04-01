package compiler488.ast.expn;


import compiler488.ast.type.BooleanType;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * The common features of binary expressions.
 */
public class BinaryExpn extends Expn
{
    Expn left, right;	/* Left and right operands of the binary operator. */
    String opSymbol;	/* Name of the operator. */

    /** Returns a string that represents the binary expression. */
    @Override
    public String toString ()
    {
        return ("(" + left + ")" + opSymbol + "(" + right + ")");
    }

    public Expn getLeft() {
        return left;
    }

    public void setLeft(Expn left) {
        this.left = left;
    }

    public String getOpSymbol() {
        return opSymbol;
    }

    public void setOpSymbol(String opSymbol) {
        this.opSymbol = opSymbol;
    }

    public Expn getRight() {
        return right;
    }

    public void setRight(Expn right) {
        this.right = right;
    }

    @Override
    public boolean semantic_visit(SemanticObject semanticObject) {
        boolean b, c;
        SymbolTable sb = semanticObject.getSymbolTable();
        b = true;
        c = true;
        b &= left.semantic_visit(semanticObject);
        b &= right.semantic_visit(semanticObject);
        switch (opSymbol)
        {
            case "+":
            case "-":
            case "*":
            case "/":
            case ">":
            case ">=":
            case "<":
            case "<=":
            case "!=":
                c = left.getTypeFromSymbolTable(sb) instanceof IntegerType && right.getTypeFromSymbolTable(sb) instanceof IntegerType; /* S31 */
                if (!c)
                {
                    semanticObject.addError(String.format("TypeError in `%s`: Integer expected", this.toString()));
                }
                break;
            case "and":
            case "or":
                c = left.getTypeFromSymbolTable(sb) instanceof BooleanType && right.getTypeFromSymbolTable(sb) instanceof BooleanType; /* S30 */
                if (!c)
                {
                    semanticObject.addError(String.format("TypeError in `%s`: Boolean expected", this.toString()));
                }
                break;
            case "=":
                c = left.getTypeFromSymbolTable(sb).equals(right.getTypeFromSymbolTable(sb));
                if (!c)
                {
                    semanticObject.addError(String.format("%s type is not equal to %s", left, right));
                }
                break;
        }
        return b && c;
    }
    @Override
    public void table_visit(SymbolTable symbolTable){}

    @Override
    public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
        return null;
    }

    @Override
    public Type getType() {
        if (left.getType().equals(right.getType()))
            return left.getType(); /* S21 */
        else
            return null;
    }

    @Override
    public Type getTypeFromSymbolTable(SymbolTable sb) {
        return left.getTypeFromSymbolTable(sb);
    }
}
