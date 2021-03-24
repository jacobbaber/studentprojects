import java.io.IOException;

public class ElementaryAutomaton extends Automaton {

	public ElementaryAutomaton(int ruleNum, Generation initial) {
		super(ruleNum, initial);
		// TODO Auto-generated constructor stub
	}
	
	public ElementaryAutomaton(String filename) throws IOException {
		super(filename);
	}

	@Override
	public Rule createRule(int ruleNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
