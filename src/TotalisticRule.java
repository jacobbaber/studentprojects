
public class TotalisticRule extends Rule {

	public TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if (ruleNum < MINRULENUM || ruleNum > 63) {
			throw new RuleNumException(MINRULENUM, 63);
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean evolve(boolean[] neighborhood) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRuleTable(char falseSymbol, char trueSymbol) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
