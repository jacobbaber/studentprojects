
/**
 * This class is used to construct and use each unique rule used for the
 * evolution of generations.
 * 
 * @author Jacob Baber
 *
 */
public abstract class Rule {

	/**
	 * ruleBinary is a String of the given rule in binary form, with eight chars
	 */
	protected String ruleBinary;

	/**
	 * ruleInt is the int form of the given rule
	 */
	protected int ruleInt;

	/**
	 * ruleTrueOrFalseArray is an array with a length of 8 that is made up by the 8
	 * digits in the String ruleBinary
	 */
	protected boolean[] ruleTrueOrFalseArray;

	/**
	 * Used for the minimum rule number, which is 0;
	 */
	protected static final int MINRULENUM = 0;

	/**
	 * This method is a constructor for the Rule class. It takes in an int that is
	 * the rule number to be used. If ruleNum given is out of the range of 0-255, it
	 * assigns the closest valid value as the ruleInt and ruleBinary.
	 * 
	 * @param ruleNum, integer used to determine the number of the rule used.
	 */
	protected Rule(int ruleNum) {

		ruleBinary = Integer.toBinaryString(ruleNum);
		ruleInt = ruleNum;

	}

	// This for loop converts the ruleBinary String into an array
	// which is later used to compare the generations to the rule
	// in order to evolve them

	/**
	 * This method takes in a Generation in order to evolve it to the next
	 * Generation. It calls the other evolve method (which returns the evolution of
	 * a single cell) over and over until the entire generation is evolved. It
	 * records each of those evolved cells into a boolean array, and then constructs
	 * a new Generation from that boolean array. And returns the new Generation
	 * 
	 * @param gen, The given Generation to be evolved
	 * 
	 * @return nextGen, The evolved Generation
	 */
	public Generation evolve(Generation gen) {
		Generation nextGen;
		boolean[] evolvedCells = new boolean[gen.size()];
		for (int i = 0; i < gen.size(); ++i) {
			evolvedCells[i] = evolve(getNeighborhood(i, gen));
		}
		nextGen = new Generation(evolvedCells);
		return nextGen;

	}

	/**
	 * This method evolves a single cell based on itself and its neighbors. It
	 * compares the boolean[] neighborhood to each possible scenario, and then
	 * assigns the evolvedState to the value of the binary int array
	 * ruleTrueOrFalseArray
	 * 
	 * @param neighborhood, boolean array of the cell to be evolved and it's
	 *                      neighbors
	 * @return evolvedState, the evolved boolean value of the cell.
	 */
	public abstract boolean evolve(boolean[] neighborhood);

	
	 
	
	/** This method gets the boolean values of the cell at index idx and it's
	 * neighbors of a specific Generation gen. When finding the neighbors of the
	 * first or last index, we circle back to last or first index respectively. For
	 * example the left neighbor of the first index would be the last index, and the
	 * right neighbor of the last index would be the first index.
	 * 
	 * @param idx, index of the cell and it's neighbors you want to get
	 * 
	 * @param gen, Generation of the cell and its neighbors you want to get
	 * 
	 * @return neighborhood, a boolean array of the boolean values of the cells and
	 * its neighbors, from left to right.
	 */
	public abstract boolean[] getNeighborhood(int idx, Generation gen);

	/**
	 * boolean leftNeighbor; boolean stateAtIdx; boolean rightNeighbor;
	 * 
	 * // This if statement is for the rare case where the size of // a generation
	 * is 1. In this case the left and right neighbors // of a cell would be itself.
	 * @return ruleInt, the rule number used.
	 */
	public int getRuleNum() {
		return ruleInt;
	}

	public boolean[] getRuleTrueOrFalseArray(int size) {

		return ruleTrueOrFalseArray;
	}

	public String getRuleBinary() {
		return ruleBinary;
	}

	public abstract String getRuleTable(char falseSymbol, char trueSymbol);

}
