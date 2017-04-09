package compiler488.symbol;

import java.lang.reflect.Array;
import java.util.*;

import compiler488.ast.decl.*;
import compiler488.ast.type.Type;
import compiler488.ast.AST;
import compiler488.parser.SyntaxErrorException;

/** Symbol Table
 *  This almost empty class is a framework for implementing
 *  a Symbol Table class for the CSC488S compiler
 *  
 *  Each implementation can change/modify/delete this class
 *  as they see fit.
 *
 *  @author  <B> PUT YOUR NAMES HERE </B>
 */

public class SymbolTable {
	
	/** String used by Main to print symbol table
         *  version information.
         */

	public final static String version = "Winter 2017" ;

    public int depth;

	private HashMap<String, ArrayList<SymbolTableEntry>> symbols;

	/** Symbol Table  constructor
         *  Create and initialize a symbol table 
	 */
	public SymbolTable  (){
	    this.symbols = new HashMap<String, ArrayList<SymbolTableEntry> >();
	}

	/**  Initialize - called once by semantic analysis  
	 *                at the start of  compilation     
	 *                May be unnecessary if constructor
 	 *                does all required initialization	
	 */
	public void Initialize() {
	
	   /**  Initialize the symbol table
	    *  Any additional symbol table initialization
	    *  GOES HERE                                	
	    */
	    depth = -1;
	}

	/**  Finalize - called once by Semantics at the end of compilation
	 *              May be unnecessary 		
	 */
	public void Finalize(){
	
	  /**  Additional finalization code for the 
	   *  symbol table  class GOES HERE.
	   *  
	   */
	}
	

	/** The rest of Symbol Table
	 *  Data structures, public and private functions
 	 *  to implement the Symbol Table
	 *  GO HERE.				
	 */


	public void openScope() {
	    depth++;
    }

    public void cleanCurrentScope() {

	    for(String key : symbols.keySet()) {
            ArrayList<SymbolTableEntry> clone =
                    (ArrayList<SymbolTableEntry>) symbols.get(key).clone();

            for(SymbolTableEntry entry : clone) {
	    	    if (entry.depth == depth) {
                /*
                 * Deleting through an array while looping will result in an error,
                 * so iterate over a copy and remove from the original.
                 * We could also use a stream and filter (java8 collections?) but I'll just
                 * do this for now.
                 *
                 * Issue resolved.
                 * - Nagee
                 */
	    	        symbols.get(key).remove(entry);
				}
			}
		}

	}

	public void closeScope() {
		depth--;
	}

	/**
     * Return true if this SymbolTable contains SymbolTableEntry with the given name
    */
    public boolean hasEntry(String identifier) {
        return this.symbols.get(identifier) != null;
    }

    /**
     * Add a new SymbolTableEntry given by name, kind, value, type. Return true if
     * the new entry was successfully inserted
     */
    public void addEntry(String identifier,
                         Declaration value,
                         Type type,
                         SymbolTableEntry.VarType var_type) {
        SymbolTableEntry entry = new SymbolTableEntry(identifier,
                                                value,
                                                type,
                                                depth,
                                                var_type);
        if (this.hasEntry(identifier)){
            for(SymbolTableEntry e : symbols.get(identifier)) {
                if (entry.depth == e.depth) {
                    // TODO: handle error case properly
                    return;
				} else {
                    this.symbols.get(identifier).add(0, entry);
                }
			}
        } else {
            ArrayList<SymbolTableEntry> chain = new ArrayList<SymbolTableEntry>();
            chain.add(entry);
            this.symbols.put(identifier, chain);
        }

		if (var_type == SymbolTableEntry.VarType.FUNC || var_type == SymbolTableEntry.VarType.PROC)
		{
			RoutineBody b = ((RoutineDecl) value).getRoutineBody();
			ListIterator<ScalarDecl> iter;
			if (b != null)
			{


				if (b.getParameters().size() > 0)
				{
					iter = b.getParameters().getIterator();

					while (iter.hasNext())
					{
						ScalarDecl d = iter.next();
						addEntry(d.getName(), d, d.getType(), SymbolTableEntry.VarType.SCALAR);
					}
				}
			}
		}
    }


    /*
     * Return the SymbolTableEntry given by name if there is one, null if there is not.
     */
    public SymbolTableEntry getEntry(String name) {
		ArrayList<SymbolTableEntry> entries = symbols.get(name);
        for (SymbolTableEntry entry : entries) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }

		return null;
	}



        /**
         * Remove the entry associated with name and return true
         * if it was successfully removed
         */
    public boolean removeEntry(String name) {

//        for (SymbolTableEntry entry : entries) {
//            if (entry.getName().equals(name)) {
//                entries.remove(entry);
//                return true;
//            }
//        }

        return false;
    }

}
