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
	protected Rule createRule(int ruleNum)  {
		Rule newRule;
		try {
			newRule = new ElementaryRule(ruleNum);
			return newRule;
		} catch (RuleNumException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
