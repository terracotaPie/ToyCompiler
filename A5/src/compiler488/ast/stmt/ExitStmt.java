package compiler488.ast.stmt;

import compiler488.ast.expn.*;
import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * Represents the command to exit from a loop.
 */

public class ExitStmt extends Stmt {

	// condition for 'exit when'
        private Expn expn = null;
	private Integer level = -1 ;

	/** Returns the string <b>"exit"</b> or <b>"exit when e"</b>" 
            or  <b>"exit"</b> level  or  <b>"exit"</b> level  when e 
	*/
	@Override
	public String toString() {
		  {
		    String stmt = "exit " ;
	 	    if( level >= 0 )
			stmt = stmt + level + " " ;
                    if( expn != null )
		        stmt = stmt + "when " + expn + " " ;
		    return stmt ;
		  }
	}

	public Expn getExpn() {
		return expn;
	}

	public void setExpn(Expn expn) {
		this.expn = expn;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public boolean semantic_visit(SemanticObject semanticObject) {
		boolean b;
		b = semanticObject.S50 > level && level > 0; /* S50 && S53 */
		if (!b) {
			semanticObject.addError("Exit has incorrect level");
		}
		return b;
	}

	@Override
	public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
		MachineUtils.programOffset += 1;
		return super.machine_visit(symbolTable);
	}
}
