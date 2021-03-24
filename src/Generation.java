/**
 * The Generation class is going to be used to produce a single
 * generation of cells. As well as get the state or states of cells, 
 * and the size of the generation.
 * 
 * @author Jacob Baber
 *
 */
public class Generation {
	
	
	/**
	 * cellStates is a boolean array that acts as a generation of cells.
	 */
	private boolean[] cellStates;
	
	
	/**
	 * Generation constructor that takes in boolean values and puts
	 * them into the cellStates array. If the given states are null or is 
	 * empty, cellStates is created with one false value. 
	 * 
	 * @param states, boolean values given to be used for a generation
	 */
	public Generation(boolean... states){
		
		if (states == null) {
			cellStates = new boolean[1];
			cellStates[0] = false;
		}
		else if (states.length == 0) {
			cellStates = new boolean[1];
			cellStates[0] = false;
		}
		else {
			cellStates = new boolean[states.length];
			for (int i = 0; i < states.length; i++) {
				cellStates[i] = states[i];
			}
		}
	}
	/**
	 * Generation constructor that takes in a String of states and char
	 * used as a trueSymbol. It will go through the String one char at a time
	 * and create the boolean array cellStates, making a true element if the
	 * character in the String matches the trueSymbol, and a false element if 
	 * it doesn't. If the String is null or empty, it creates cellStates with one
	 * false element.
	 * 
	 * @param states
	 * @param trueSymbol
	 */
	public Generation(String states, char trueSymbol) {
		if (states == "" || states == null) {
			cellStates = new boolean[1];
			cellStates[0] = false;
			
		}
		else {
			cellStates = new boolean[states.length()];
			for (int i = 0; i < states.length(); ++i) {
				if (states.charAt(i) == trueSymbol) {
					cellStates[i] = true;
				}
				else {
					cellStates[i] = false;
				}
			}
		}
	}
	
	/**
	 * Used to get the State of an individual cell in a generation.
	 * 
	 * @param idx, index of the cell whose state you're getting
	 * @return returns the state of the cell.
	 */
	public boolean getState(int idx) {
		boolean stateOfCell = cellStates[idx];
		return stateOfCell;
		
	}
	
	/**
	 * This method gets the states of all the cells in a generation
	 * in the form of a boolean array.
	 * 
	 * @return, returns a copy of the boolean array cellStates
	 */
	public boolean[] getStates() {
		boolean[] copyOfStates = new boolean[cellStates.length];
		for (int i = 0; i < cellStates.length; ++i) {
			copyOfStates[i] = cellStates[i];
		}
		return copyOfStates;
		
	}
	
	/**
	 * This method gets the states of all the cells in a generation
	 * in the form of a String. With false symbol representing the false
	 * booleans and the trueSymbol representing the true booleans.
	 * 
	 * @param falseSymbol, determines what symbol in the String will represent the false boolean
	 * @param trueSymbol, determines what symbol in the String will represent the true boolean
	 * @return, returns the String that is constructed
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		String states = "";
		for (int i = 0; i < cellStates.length; ++i) {
			if (cellStates[i] == true) {
				states = states + trueSymbol;
			}
			else {
				states = states + falseSymbol;
			}
		}
		return states;
	}
	/**
	 * gets the length in the form of an int of cellStates array
	 * 
	 * @return, returns length of cellStates
	 */
	public int size() {
		int size = cellStates.length;
		return size;
	}
}



