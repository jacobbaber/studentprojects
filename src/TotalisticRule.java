
public class TotalisticRule extends Rule {

	public TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if (ruleNum < MINRULENUM || ruleNum > 63) {
			throw new RuleNumException(MINRULENUM, 63);
		}
		
		for (int i = 0; i < ruleBinary.length(); ++i) {
			if (ruleBinary.charAt(i) == '1') {
				ruleTrueOrFalseArray[i] = true;
			}
			else {
				ruleTrueOrFalseArray[i] = false;
			}
		}
	
	}

	@Override
	public boolean evolve(boolean[] neighborhood) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] neighborhood;
		
		
		if (idx == gen.size() -1 || idx == gen.size() - 2|| idx == 0 || idx == 1) {
			neighborhood = new boolean[5];
			neighborhood[0] = gen.getState(Math.floorMod(idx -2, gen.size()));
			neighborhood[1] = gen.getState(Math.floorMod(idx -1, gen.size()));
			neighborhood[2] = gen.getState(Math.floorMod(idx, gen.size()));
			neighborhood[3] = gen.getState(Math.floorMod(idx +1, gen.size()));
			neighborhood[4] = gen.getState(Math.floorMod(idx +2, gen.size()));
			
		}
		
		else {
		neighborhood = new boolean[] {gen.getState(idx-2), gen.getState(idx-1), gen.getState(idx),gen.getState(idx+1),gen.getState(idx+2)};
		}
		
		return neighborhood;
	}

	@Override
	public String getRuleTable(char falseSymbol, char trueSymbol) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
