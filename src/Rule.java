
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
	private String ruleBinary;
	
	/**
	 * ruleInt is the int form of the given rule
	 */
	private int ruleInt;
	
	/**
	 * ruleTrueOrFalseArray is an array with a length of 8
	 * that is made up by the 8 digits in the String ruleBinary
	 */
	private boolean[] ruleTrueOrFalseArray = new boolean[8];
	
	/**
	 * Used for the maximum rule number, which is 255
	 */
	private static final int MAXRULENUM = 255;
	
	/**
	 * Used for the minimum rule number, which is 0;
	 */
	private static final int MINRULENUM = 0;
	
	
	
	/**
	 * This method is a constructor for the Rule class. It takes in
	 * an int that is the rule number to be used. If ruleNum given is out
	 * of the range of 0-255, it assigns the closest valid value as the ruleInt 
	 * and ruleBinary.
	 * 
	 * @param ruleNum, integer used to determine the number of the rule used.
	 */
	public Rule(int ruleNum) {
		if (ruleNum < MINRULENUM) {
			ruleInt = MINRULENUM;
			ruleBinary = Integer.toBinaryString(MINRULENUM);
			// ruleBinaryInt is an int in the form of the binary rule number
			int ruleBinaryInt = Integer.parseInt(ruleBinary);
			// I then convert that binary int into a formatted String of
			// 8 digits.
			ruleBinary = String.format("%08d", ruleBinaryInt);
		}
		else if (ruleNum > MAXRULENUM) {
			ruleBinary = Integer.toBinaryString(MAXRULENUM);
			ruleInt = MAXRULENUM;
			int ruleBinaryInt = Integer.parseInt(ruleBinary);
			ruleBinary = String.format("%08d", ruleBinaryInt);
		}
		else {
			ruleBinary = Integer.toBinaryString(ruleNum);
			ruleInt = ruleNum;
			int ruleBinaryInt = Integer.parseInt(ruleBinary);
			ruleBinary = String.format("%08d", ruleBinaryInt);
		}
		
		// This for loop converts the ruleBinary String into an array
		// which is later used to compare the generations to the rule
		// in order to evolve them
		for (int i = 0; i < ruleBinary.length(); ++i) {
			if (ruleBinary.charAt(i) == '1') {
				ruleTrueOrFalseArray[i] = true;
			}
			else {
				ruleTrueOrFalseArray[i] = false;
			}
		}
		
	}
	
	/**
	 * This method takes in a Generation in order to evolve it to
	 * the next Generation. It calls the other evolve method 
	 * (which returns the evolution of a single cell) over and over
	 * until the entire generation is evolved. It records each
	 * of those evolved cells into a boolean array, and then 
	 * constructs a new Generation from that boolean array. And 
	 * returns the new Generation
	 * 
	 * @param gen, The given Generation to be evolved
	 * 
	 * @return nextGen, The evolved Generation
	 */
	public Generation evolve(Generation gen) {
		Generation nextGen;
		boolean[] evolvedCells = new boolean[gen.size()];
		for (int i = 0; i < gen.size(); ++i) {
			evolvedCells[i] = evolve(getNeighborhood(i,gen));
		}
		nextGen = new Generation(evolvedCells);
		return nextGen;
		
		
	}
	
	
	/**
	 * This method evolves a single cell based on itself and its neighbors.
	 * It compares the boolean[] neighborhood to each possible scenario, and then 
	 * assigns the evolvedState to the value of the binary int array ruleTrueOrFalseArray
	 * 
	 * @param neighborhood, boolean array of the cell to be evolved and it's neighbors
	 * @return evolvedState, the evolved boolean value of the cell.
	 */
	public abstract boolean evolve(boolean[] neighborhood);
		/* boolean evolvedState = false;
		
		if ((neighborhood[0] == true) && (neighborhood[1] == true) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[0];
		}
		
		else if ((neighborhood[0] == true) && (neighborhood[1] == true) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[1];
		}
		else if ((neighborhood[0] == true) && (neighborhood[1] == false) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[2];
		}
		else if ((neighborhood[0] == true) && (neighborhood[1] == false) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[3];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == true) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[4];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == true) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[5];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == false) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[6];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == false) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[7];
		}
		
		return evolvedState;
					
	}
	
	/**
	 * This method gets the boolean values of the cell at index idx and
	 * it's neighbors of a specific Generation gen. When finding the neighbors
	 * of the first or last index, we circle back to last or first index respectively.
	 * For example the left neighbor of the first index would be the last index, and 
	 * the right neighbor of the last index would be the first index.
	 * 
	 * @param idx, index of the cell and it's neighbors you want to get
	 * @param gen, Generation of the cell and its neighbors you want to get
	 * 
	 * @return neighborhood, a boolean array of the boolean values
	 * of the cells and its neighbors, from left to right.
	 */
	public abstract boolean[] getNeighborhood(int idx, Generation gen);
		/** boolean leftNeighbor;
		boolean stateAtIdx;
		boolean rightNeighbor;
		
		// This if statement is for the rare case where the size of
		// a generation is 1. In this case the left and right neighbors
		// of a cell would be itself.
		
		if (idx == 0 && gen.size() == 1) {
			leftNeighbor = gen.getState(idx);
			stateAtIdx = gen.getState(idx);
			rightNeighbor = gen.getState(idx);
			
		}
		else if (idx == 0) {
			leftNeighbor = gen.getState(gen.size() - 1);
			stateAtIdx = gen.getState(idx);
			rightNeighbor = gen.getState(idx+1);
		}
		else if (idx == (gen.size()-1)) {
			leftNeighbor = gen.getState(idx - 1);
			stateAtIdx = gen.getState(idx);
			rightNeighbor = gen.getState(0);
		}
		else {
			leftNeighbor = gen.getState(idx - 1);
			stateAtIdx = gen.getState(idx);
			rightNeighbor = gen.getState(idx + 1);
			}
		
		boolean[] neighborhood = new boolean[]
		{leftNeighbor, stateAtIdx, rightNeighbor};
		
		return neighborhood;
	}
	/**
	 * This method is used to get the integer form of the rule number used.
	 * 
	 * @return ruleInt, the rule number used.
	 */
	public int getRuleNum() {
		return ruleInt;
	}
	
	public abstract String getRuleTable(char falseSymbol, char trueSymbol);


}
