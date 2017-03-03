package compiler488.symbol;

import java.util.*;

import compiler488.ast.type.Type;
import compiler488.ast.AST;

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

    private int depth;

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
	    	for(SymbolTableEntry entry : symbols.get(key)) {
	    	    if (entry.depth == depth) {
	    	        // TODO might be broken
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
    public void addEntry(String identifier, AST value, Type type) {
        SymbolTableEntry entry = new SymbolTableEntry(identifier, value, type, depth);
        if (this.hasEntry(identifier)){
            for(SymbolTableEntry e : symbols.get(identifier)) {
                if (entry.depth == e.depth) {
                // TODO throw exception
				} else {
					this.symbols.get(identifier).add(0,entry);
				}
			}
        } else {
            ArrayList<SymbolTableEntry> chain = new ArrayList<SymbolTableEntry>();
            chain.add(entry);
            this.symbols.put(identifier, chain);
        }
    }


    /*
     * Return the SymbolTableEntry given by name if there is one, null if there is not.
     */
    public SymbolTableEntry getEntry(String name,int depth) {
		System.out.println("Get entry " + name + " in symbol table");
		System.out.println(symbols.size());
		ArrayList<SymbolTableEntry> entries = symbols.get(name);
        for (SymbolTableEntry entry : entries) {
            if (entry.getName().equals(name) && entry.depth == depth) {
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
