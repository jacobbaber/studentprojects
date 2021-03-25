import java.io.IOException;

public class TotalisticAutomaton extends Automaton {

	public TotalisticAutomaton(int ruleNum, Generation initial) {
		super(ruleNum, initial);
	}
	
	public TotalisticAutomaton(String filename) throws IOException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) {
		try {
			Rule newRule = new TotalisticRule(ruleNum);
			return newRule;
		} catch (RuleNumException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
