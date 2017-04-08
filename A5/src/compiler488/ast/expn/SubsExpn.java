package compiler488.ast.expn;

import compiler488.ast.AST;
import compiler488.ast.Readable;
import compiler488.ast.decl.ArrayDeclPart;
import compiler488.ast.decl.MultiDeclarations;
import compiler488.ast.type.IntegerType;
import compiler488.ast.type.Type;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import compiler488.symbol.SymbolTableEntry;

import java.util.ArrayList;

/**
 * References to an array element variable
 * 
 * Treat array subscript operation as a special form of unary expression.
 * operand must be an integer expression
 */
public class SubsExpn extends UnaryExpn implements Readable {

	private String variable; // name of the array variable

	/** Returns a string that represents the array subscript. */
	@Override
	public String toString() {
		return (variable + "[" + operand + "]");
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	@Override
	public Type getType() {
		// Requires Symbol table
		return null;
	}

	@Override
	public Type getTypeFromSymbolTable(SymbolTable sb)
	{
		SymbolTableEntry entry = sb.getEntry(variable);
		return entry.getType();
	}


    @Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		SymbolTable st;

		st = semanticObject.getSymbolTable();
		b = true;

		b &= operand.semantic_visit(semanticObject) && operand.getTypeFromSymbolTable(st) instanceof IntegerType; /* S31 */

		return b;
	}
	@Override
	public void table_visit(SymbolTable symbolTable){
	    // TODO
	}

    /**
     * Generate code for indexing into an array. Note that this is _not_ what
     * is used when an array is on the left hand side of an assignment, this is used
     * when `A[i]` is used in something similar to the following
     * `A[i] + 1`, and not `A[i] := 1`
     * @param symbolTable the symbol table from the semantic visit
     * @return the following machine code
     * {@code
     * instructions for (operand)
     * PUSH (Math.abs(array lower bound))
     * ADD // get normalized index
     * PUSH (array start address)
     * ADD // get address from array
     * LOAD // NOTE: this is the difference between LHS function
     * }
     */
	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
	    // Get the start address
        short arrayStartAddr = (short)(int)symbolTable.getEntry(variable).getAddr();

        MachineUtils.programOffset += 7;
        // get the array declaration so we can get the lower boudary
        AST value = symbolTable.getEntry(variable).getValue();
        ArrayDeclPart arrayDecl;
        if (value instanceof MultiDeclarations) {
            arrayDecl = (ArrayDeclPart) MachineUtils.pullFromMultiDeclaration((MultiDeclarations) value, variable);
        } else {
            arrayDecl = (ArrayDeclPart)value;
        }

        int offset = Math.abs(arrayDecl.getLowerBoundary());

		ArrayList<Instruction> addr;

        addr = operand.machine_visit(symbolTable);
        addr.add(new Instruction(Machine.PUSH, offset));
        addr.add(new Instruction(Machine.ADD));
        addr.add(new Instruction(Machine.PUSH, arrayStartAddr));
        addr.add(new Instruction(Machine.ADD));
        addr.add(new Instruction(Machine.LOAD));

        return addr;
	}

    /**
     * Generate code for indexing into an array
     * @param symbolTable the symbol table from the semantic visit
     * @return the following machine code </br>
     * <code>
     * instructions for (operand)
     * PUSH (Math.abs(array lower bound))
     * ADD // get normalized index
     * PUSH (array start address)
     * ADD // get address from array
     * </code>
     */
    @Override
    public ArrayList<Instruction> machine_lhs_vist(SymbolTable symbolTable) {
	    // Get the start address
        short arrayStartAddr = (short)(int)symbolTable.getEntry(variable).getAddr();

        MachineUtils.programOffset += 6;
        // get the array declaration so we can get the lower boudary
        AST value = symbolTable.getEntry(variable).getValue();
        ArrayDeclPart arrayDecl;
        if (value instanceof MultiDeclarations) {
            arrayDecl = (ArrayDeclPart) MachineUtils.pullFromMultiDeclaration((MultiDeclarations) value, variable);
        } else {
            arrayDecl = (ArrayDeclPart)value;
        }

        int offset = Math.abs(arrayDecl.getLowerBoundary());

		ArrayList<Instruction> addr;

        addr = operand.machine_visit(symbolTable);
        addr.add(new Instruction(Machine.PUSH, offset));
        addr.add(new Instruction(Machine.ADD));
        addr.add(new Instruction(Machine.PUSH, arrayStartAddr));
        addr.add(new Instruction(Machine.ADD));

        return addr;
    }
}
