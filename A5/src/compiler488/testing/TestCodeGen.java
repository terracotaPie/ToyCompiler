package compiler488.testing;

/* File Name: TestAST.java
 * To Create:
 * After the scanner and the parser, have been created.
 * > javac TestAST.java
 *
 * To Run:
 * > java   csc488.TestAST   program
 * where program is an input file for the compiler
 * This simple minded driver does not read from standard input
 */

import compiler488.codegen.Instruction;
import compiler488.parser.Lexer;
import compiler488.parser.Parser;
import compiler488.ast.stmt.Program;
import compiler488.runtime.Machine;
import compiler488.semantics.SemanticObject;
import compiler488.symbol.SymbolTable;
import java_cup.runtime.Symbol;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A sample class, very similar to Compiler488.
 * It shows how to print the AST after the
 * parsing is done.
 */
class TestCodeGen {

    static public void main(String argv[]) {
		/* Start the parser */
        try {
            System.out.println("Start machine");
            PrintMachineCode(new File(argv[0]));
            System.out.println("End machine");
        } catch (Exception e) {
			/* do cleanup here -- possibly rethrow e */
            System.out.println("Exception during Parsing");
            e.printStackTrace();
        }
    }

    static public void PrintMachineCode(File file) throws Exception {
        Parser p = new Parser(new Lexer(new FileReader(file)));
        Symbol q = p.parse();
        Program s = (Program) q.value;
		/*  Print the entire AST from it's root */
        s.printOn(System.out, 0);
        SemanticObject so = new SemanticObject();
        System.out.println("Semantic: " + s.semantic_visit(so));
        SymbolTable t = new SymbolTable();
        s.table_visit(t);
        ArrayList<Instruction> is = s.machine_visit(t);
        for (int l = 1; l < is.size() + 1; l++) {
            is.get(l - 1).setLineNumber(l);
        }
		/* feed into machine */
        System.out.println(is);

        Machine.powerOn();
        short codeLength = 0;
        for (Instruction inst : is) {
            System.out.println(inst.getCode());
            Machine.writeMemory(codeLength, inst.getCode());
            codeLength++;
            for (short arg : inst.getArgs()) {
                System.out.println(arg);
                Machine.writeMemory(codeLength, arg);
                codeLength++;
            }
        }
        Machine.setPC((short) (0));
        Machine.setMLP((short) (Machine.memorySize - codeLength));
        Machine.setMSP(codeLength);
        Machine.run();
    }
}