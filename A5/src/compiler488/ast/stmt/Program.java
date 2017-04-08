
package compiler488.ast.stmt;


import compiler488.codegen.Instruction;
import compiler488.codegen.MachineUtils;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;

import java.util.ArrayList;

/**
 * Placeholder for the scope that is the entire program
 */
public class Program extends Scope {

    public boolean semantic_visit(SemanticObject semanticObject)
    {
        boolean b;
        semanticObject.S00_01 = true;
        b = super.semantic_visit(semanticObject);
        semanticObject.S00_01 = false;
        return b;
    }

    @Override
    public ArrayList<Instruction> machine_visit(SymbolTable symbolTable) {
        ArrayList<Instruction> allInst = super.machine_visit(symbolTable);

        // we need to tell it to stop :)
        allInst.add(new Instruction(Machine.HALT));
        MachineUtils.programOffset += 1;
        return allInst;
    }
}