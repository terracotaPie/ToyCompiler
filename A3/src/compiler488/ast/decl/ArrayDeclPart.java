package compiler488.ast.decl;

/**
 * Holds the declaration part of an array.
 */
public class ArrayDeclPart extends DeclarationPart {

	/* The lower and upper boundaries of the array. */
        private Integer lb, ub;


	/* The number of objects the array holds. */
	private Integer size;

	/**
	 * Returns a string that describes the array.
	 */
	@Override
	public String toString() {
		return name + "[" + lb + ".." + ub + "]";
	}

	public Integer getSize() {
		return size;
	}


	public Integer getLowerBoundary() {
		return lb;
	}

	public Integer getUpperBoundary() {
		return ub;
	}

        public void setLowerBoundary(Integer lb) {
		this.lb = lb;
	}

        public void setUpperBoundary(Integer ub) {
		this.ub = ub;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
