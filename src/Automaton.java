import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class automates the evolution of Generations by calling on
 * it's aggregated methods. It also saves the evolved Generations to a file
 * and can change them to Strings.
 * 
 * @author Jacob Baber
 *
 */
public abstract class Automaton {
	
	/**
	 * Rule rule is the Rule object of the specific rule used for the Generation
	 */
	private Rule rule;
	
	/**
	 * This is an ArrayList of Generation objects that is used to save the intial
	 * and evolved Generations of a Generation
	 */
	private ArrayList<Generation> generations = new ArrayList<Generation>();
	/**
	 * falseSymbol is a char value that is used to symbolize a false boolean
	 * element of a cell
	 */
	public char falseSymbol;
	/**
	 *  trueSymbol is a char value that is used to symbolize a true boolean
	 * element of a cell
	 */
	public char trueSymbol;
	
	
	/**
	 * Constructor for class Automaton which takes in the ruleNum
	 * to create a new Rule object, and a Generation to add to the
	 * ArrayList of generations. It also intializes falseSymbol and trueSymbol
	 * to their default values 0 and 1.
	 * 
	 * @param ruleNum, rule number used to create Rule object
	 * @param initial, initial Generation for the arrayList of Generations
	 */
	public Automaton(int ruleNum, Generation initial) {
		rule = createRule(ruleNum);
		generations.add(initial);
		falseSymbol = '0';
		trueSymbol = '1';
	}
	
	/**
	 * This constructor for class Automaton reads a file that contains
	 * the rule number, the true and false symbols, and a String
	 * of the first generation using the true and false symbols.
	 * After getting the values from the file, it converts the 
	 * String of true and false symbols to a boolean array, and then
	 * uses that array to create the initial Generation, and then
	 * adds that Generation to the Array List of Generations.
	 * 
	 * @param filename, name of the file with the automaton values
	 * @throws IOException, throws exception if file of filename does not exist
	 */
	public Automaton(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String ruleNum = br.readLine();
		
		int ruleNumInt = Integer.parseInt(ruleNum);
		rule = createRule(ruleNumInt);
		
		String symbols = br.readLine();
		falseSymbol = symbols.charAt(0);
		trueSymbol = symbols.charAt(2);
		
		String firstGen = br.readLine();
		
		// Creates a boolean array from the characters in the
		// firstGen String
		boolean[] firstGenArray = new boolean[firstGen.length()];
		for (int i = 0; i < firstGen.length(); i++) {
			if (firstGen.charAt(i) == falseSymbol) {
				firstGenArray[i] = false;
				}
			else {
				firstGenArray[i] = true;
			}
		}
		Generation gen = new Generation(firstGenArray);
		generations.add(gen);
		br.close();
			
		
		
	}
	
	public abstract Rule createRule(int ruleNum);
	
	/**
	 * This method evolves a Generation according to the rule
	 * a numSteps number of times and saves each new Generation
	 * to the Generation array list
	 * 
	 * @param numSteps, integer value for the number of times a generation
	 * should be evolved
	 */
	public void evolve(int numSteps) {
		for (int i = 0; i < numSteps; ++i) {
			Generation gen = (generations.get(generations.size() - 1));
			Generation nextGen = rule.evolve(gen);
			generations.add(nextGen);
		}
	}
	
	/**
	 * This method gets the Generation in the Generation arrayList
	 * at the index of stepNum. If the Generation arrayList hasn't gotten
	 * to the step at stepNum, it will evolve the Generations to that step.
	 * 
	 * @param stepNum, Int value for the index of the Generation you want to return
	 * from the Generation arraylist.
	 * 
	 * @return Generation at the index stepNum
	 */
	public Generation getGeneration(int stepNum) {
		if (stepNum < generations.size()) {
			return generations.get(stepNum);
		}
		else {
			evolve(stepNum - generations.size() + 1);
			return generations.get(stepNum);
		}
	}
	
	/**
	 * This method returns the int value of the rule number for
	 * the Rule object being used
	 * 
	 * @return rule number int value
	 */
	public int getRuleNum() {
		return rule.getRuleNum();
		
	}
	
	public String getRuleTable() {
		
	}
	
	/**
	 * this method returns the total times the intial generation
	 * has evolved, which is the arraylist's total size - 1
	 * 
	 * @return int value of the number of times it's evolved
	 */
	public int getTotalSteps() {
		return generations.size() -1;		
	}
	
	
	/**
	 * This method writes the toString method to a file, which saves
	 * each generation.
	 * 
	 * @param filename, name of the file it's writing to
	 * @throws IOException, throws exception if file filename doesn't exist
	 */
	public void saveEvolution(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(toString());
		bw.close();
	}
	
	
	/**
	 * Overrides the toString method. It converts each generation to
	 * a String represented by the falseSymbol and trueSymbols.
	 * With each generation separated by a new line.
	 */
	@Override
	public String toString() {
		String genString = "";
		for (int i = 0; i < generations.size(); i++) {
			if (i + 1 == generations.size()) {
				genString += generations.get(i).getStates(falseSymbol, trueSymbol);
			}
			else {
				genString += generations.get(i).getStates(falseSymbol, trueSymbol) + System.lineSeparator();
				
			}
		}
			
		
		return genString;
	}
	

}
