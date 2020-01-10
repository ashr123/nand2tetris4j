package il.co.nand2tetris.components;

public class OrGate extends TwoInputGate
{
	public OrGate()
	{
		//your code here
		NAndGate
				A = new NAndGate(),
				B = new NAndGate(),
				AB = new NAndGate();

		A.connectInput1(getInput1());
		A.connectInput2(getInput1());

		B.connectInput1(getInput2());
		B.connectInput2(getInput2());

		AB.connectInput1(A.getOutput());
		AB.connectInput2(B.getOutput());

		getOutput().connectInput(AB.getOutput());
	}

	@Override
	public boolean TestGate()
	{
		getInput1().setValue(0);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(0);
		getInput2().setValue(1);
		if (getOutput().getValue() != 1)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(0);
		if (getOutput().getValue() != 1)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(1);
		return getOutput().getValue() == 1;
	}

	@Override
	public String toString()
	{
		return "Or " + getInput1().getValue() + ", " + getInput2().getValue() + " -> " + getOutput().getValue();
	}
}
