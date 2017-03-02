package compiler488.symbol;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

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

	private HashMap<String, Stack<SymbolTableEntry>> symbols;

	/** Symbol Table  constructor
         *  Create and initialize a symbol table 
	 */
	public SymbolTable  (){
	    this.symbols = new HashMap<String, Stack<SymbolTableEntry>>();
	}

	/**  Initialize - called once by semantic analysis  
	 *                at the start of  compilation     
	 *                May be unnecessary if constructor
 	 *                does all required initialization	
	 */
	public void Initialize() {
	
	   /**   Initialize the symbol table             
	    *	Any additional symbol table initialization
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


	private openScope(){
	    depth++;
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
    public boolean addEntry(String identifier, AST value, Type type) {
        SymbolTableEntry entry = new SymbolTableEntry(identifier, value, type);
        if (this.hasEntry(identifier)){
            this.symbols.get(identifier).push(entry);
        } else {
            Stack<SymbolTableEntry> chain = new Stack<SymbolTableEntry>();
            chain.push(entry);
            this.symbols.put(identifier, chain);
        }
    }


    /*
     * Return the SymbolTableEntry given by name if there is one, null if there is not.
     */
    public SymbolTableEntry getEntry(String name) {
        //System.out.println("Get entry " + name + " in symbol table");
        //System.out.println(entries.size());
        for (SymbolTableEntry entry : entries) {
            // System.out.println("current entry name = " + entry.getName());
            if (entry.getName().equals(name)) {
                return entry;
            }
        }

        return null;



        /**
         * Remove the entry associated with name and return true
         * if it was successfully removed
         */
    public boolean removeEntry(String name) {

        for (SymbolTableEntry entry : entries) {
            if (entry.getName().equals(name)) {
                entries.remove(entry);
                return true;
            }
        }

        return false;
    }

}
