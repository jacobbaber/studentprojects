import java.io.IOException;

public class TotalisticAutomaton extends Automaton {

	public TotalisticAutomaton(int ruleNum, Generation initial) {
		super(ruleNum, initial);
		// TODO Auto-generated constructor stub
	}
	
	public TotalisticAutomaton(String filename) throws IOException {
		super(filename);
	}

	@Override
	public Rule createRule(int ruleNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
