import java.io.IOException;

public class ElementaryAutomaton extends Automaton {

	public ElementaryAutomaton(int ruleNum, Generation initial) {
		super(ruleNum, initial);
	}

	public ElementaryAutomaton(String filename) throws IOException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) {
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
