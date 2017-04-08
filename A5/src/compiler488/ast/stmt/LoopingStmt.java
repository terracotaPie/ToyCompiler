package compiler488.ast.stmt;

import compiler488.ast.expn.Expn;
import compiler488.ast.type.BooleanType;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;


/**
 * Represents the common parts of loops.
 */
public abstract class LoopingStmt extends Stmt
{
    protected Stmt body ;	  // body of ther loop
    protected Expn expn;          // Loop condition

	public Expn getExpn() {
		return expn;
	}

	public void setExpn(Expn expn) {
		this.expn = expn;
	}

	public Stmt getBody() {
		return body;
	}

	public void setBody(Stmt body) {
		this.body = body;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		SymbolTable st = semanticObject.getSymbolTable();
		semanticObject.S50 += 1;
		b = expn.getTypeFromSymbolTable(st).equals(new BooleanType()); /* S30 */
		if (body != null)
			b &= body.semantic_visit(semanticObject);
		semanticObject.S50 -= 1;
		return b;
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		ArrayList<Instruction> condition = new ArrayList<>();
		condition.addAll(expn.machine_visit(symbolTable));
		ArrayList<Instruction> block = body.machine_visit(symbolTable);
		return MachineUtils.loop(condition, block);
	}
}
