
public class ElementaryRule extends Rule {
	

	public ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		if (ruleNum < 0 || ruleNum > 255) {
			throw new RuleNumException(0,255);
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean evolve(boolean[] neighborhood) {
		// TODO Auto-generated method stub
		
		boolean[] ruleTrueOrFalseArray = super.getRuleTrueOrFalseArray();
		boolean evolvedState = false;
		
		if ((neighborhood[0] == true) && (neighborhood[1] == true) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[0];
		}
		
		else if ((neighborhood[0] == true) && (neighborhood[1] == true) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[1];
		}
		else if ((neighborhood[0] == true) && (neighborhood[1] == false) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[2];
		}
		else if ((neighborhood[0] == true) && (neighborhood[1] == false) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[3];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == true) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[4];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == true) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[5];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == false) && (neighborhood[2] == true)) {
			evolvedState = ruleTrueOrFalseArray[6];
		}
		else if ((neighborhood[0] == false) && (neighborhood[1] == false) && (neighborhood[2] == false)) {
			evolvedState = ruleTrueOrFalseArray[7];
		}
		
		return evolvedState;
					
	}
		
		
	

	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		// TODO Auto-generated method stub
		
		 boolean leftNeighbor;
			boolean stateAtIdx;
			boolean rightNeighbor;
			
			// This if statement is for the rare case where the size of
			// a generation is 1. In this case the left and right neighbors
			// of a cell would be itself.
			
			if (idx == 0 && gen.size() == 1) {
				leftNeighbor = gen.getState(idx);
				stateAtIdx = gen.getState(idx);
				rightNeighbor = gen.getState(idx);
				
			}
			else if (idx == 0) {
				leftNeighbor = gen.getState(gen.size() - 1);
				stateAtIdx = gen.getState(idx);
				rightNeighbor = gen.getState(idx+1);
			}
			else if (idx == (gen.size()-1)) {
				leftNeighbor = gen.getState(idx - 1);
				stateAtIdx = gen.getState(idx);
				rightNeighbor = gen.getState(0);
			}
			else {
				leftNeighbor = gen.getState(idx - 1);
				stateAtIdx = gen.getState(idx);
				rightNeighbor = gen.getState(idx + 1);
				}
			
			boolean[] neighborhood = new boolean[]
			{leftNeighbor, stateAtIdx, rightNeighbor};
			
			return neighborhood;
		}
		
	

	@Override
	public String getRuleTable(char falseSymbol, char trueSymbol) {
		
		String neighborhoods = "111 110 101 100 011 010 001 000";
		neighborhoods.replace('1', trueSymbol);
		neighborhoods.replace('0', falseSymbol);
		String ruleBinary = super.getRuleBinary();
		ruleBinary = ruleBinary.replaceAll(".", "$0   ");
		StringBuilder strB = new StringBuilder();
		strB.append(" ");
	
		
		return neighborhoods + System.lineSeparator() + ruleBinary.format(ruleBinary, strB.toString());
		
		
	}

}
