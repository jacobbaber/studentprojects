
public class TotalisticRule extends Rule {

	public TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		ruleTrueOrFalseArray = new boolean[6];
		if (ruleNum < MINRULENUM || ruleNum > 63) {
			throw new RuleNumException(MINRULENUM, 63);
		}
		ruleBinary = Integer.toBinaryString(ruleNum);
		ruleInt = ruleNum;
		int ruleBinaryInt = Integer.parseInt(ruleBinary);
		ruleBinary = String.format("%06d", ruleBinaryInt);
		
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
		
		int trueCount = 0;
		
		for (int i = 0;  i < neighborhood.length; ++i) {
			if (neighborhood[i] == true) {
				++trueCount;
			}
		}
		if (trueCount == 5) {
			return ruleTrueOrFalseArray[0];
		}
		if (trueCount == 4) {
			return ruleTrueOrFalseArray[1];
		}
		if (trueCount == 3) {
			return ruleTrueOrFalseArray[2];
		}
		if (trueCount == 2) {
			return ruleTrueOrFalseArray[3];
		}
		if (trueCount == 1) {
			return ruleTrueOrFalseArray[4];
		}
		if (trueCount == 0) {
			return ruleTrueOrFalseArray[5];
		}
		
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
		
		String neighborhoods = "5 4 3 2 1 0";
		String ruleBinaryFormatted = ruleBinary.replaceAll(".", "$0 ");
		ruleBinaryFormatted = ruleBinaryFormatted.substring(0, ruleBinaryFormatted.length() - 1);
		ruleBinaryFormatted = ruleBinaryFormatted.replace('1', trueSymbol);
		ruleBinaryFormatted = ruleBinaryFormatted.replace('0', falseSymbol);
		return neighborhoods + System.lineSeparator() + ruleBinaryFormatted;
		
	}
	

}
