package compiler488.semantics;

import java.io.*;

import compiler488.ast.stmt.Program;
import compiler488.ast.stmt.Scope;
import compiler488.parser.Lexer;
import compiler488.parser.Parser;
import compiler488.symbol.SymbolTable;
import java_cup.runtime.Symbol;

/** Implement semantic analysis for compiler 488 
 *  @author  <B>Nagee Elghassein</B>
 */
public class Semantics {
	
     public static final String version = "Winter 2017" ;

	/** flag for tracing semantic analysis */
	private boolean traceSemantics = false;
	/** file sink for semantic analysis trace */
	private String traceFile = new String();
	public FileWriter Tracer;
	public File f;

     
     
     /** SemanticAnalyzer constructor */
	public Semantics (){
	
	}

	/**  semanticsInitialize - called once by the parser at the      */
	/*                        start of  compilation                 */
	void Initialize() {
	
	   /*   Initialize the symbol table             */
		try {
			Parser p = new Parser(new Lexer(new FileReader(f)));
			Symbol q = null;
			q = p.parse();
			Program s = (Program) q.value;
			boolean b = s.semantic_visit(new SemanticObject());
			System.out.println("Semantic:" + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   /*********************************************/
	   /*  Additional initialization code for the   */
	   /*  semantic analysis module                 */
	   /*  GOES HERE                                */
	   /*********************************************/
	   
	}

	/**  semanticsFinalize - called by the parser once at the        */
	/*                      end of compilation                      */
	void Finalize(){
	
	  /*  Finalize the symbol table                 */
	
	  // Symbol.Finalize();
	  
	   /*********************************************/
	  /*  Additional finalization code for the      */
	  /*  semantics analysis module                 */
	  /*  GOES here.                                */
	  /**********************************************/
	  
	}
	
	/**
	 *  Perform one semantic analysis action
         *  @param  actionNumber  semantic analysis action number
         */
	void semanticAction( int actionNumber ) {

		if( traceSemantics ){
			if(traceFile.length() > 0 ){
				//output trace to the file represented by traceFile
				try{
					//open the file for writing and append to it
					File f = new File(traceFile);
					Tracer = new FileWriter(traceFile, true);

					Tracer.write("Sematics: S" + actionNumber + "\n");
					//always be sure to close the file
					Tracer.close();
				}
				catch (IOException e) {
					System.out.println(traceFile +
							" could be opened/created.  It may be in use.");
				}
			}
			else{
				//output the trace to standard out.
				System.out.println("Sematics: S" + actionNumber );
			}

		}

		/*************************************************************/
	   /*  Code to implement each semantic action GOES HERE         */
	   /*  This stub semantic analyzer just prints the actionNumber */   
	   /*                                                           */
           /*  FEEL FREE TO ignore or replace this procedure            */
		/*************************************************************/

		System.out.println("Semantic Action: S" + actionNumber  );
		return ;
	}

	// ADDITIONAL FUNCTIONS TO IMPLEMENT SEMANTIC ANALYSIS GO HERE

}
