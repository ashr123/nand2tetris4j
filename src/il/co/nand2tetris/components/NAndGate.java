package il.co.nand2tetris.components;

public class NAndGate extends TwoInputGate implements Component
{
	private static int nandGates, nandCompute;

	public NAndGate()
	{
		nandGates++;
		getInput1().connectOutput(this);
		getInput2().connectOutput(this);
	}

	public static int getNandGates()
	{
		return nandGates;
	}

	public static int getNandCompute()
	{
		return nandCompute;
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean TestGate()
	{
		getInput1().setValue(0);
		getInput2().setValue(0);
		if (getOutput().getValue() != 1)
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
		return getOutput().getValue() == 0;
	}

	@Override
	public void compute()
	{
		nandCompute++;
		getOutput().setValue(getInput1().getValue() == 1 && getInput2().getValue() == 1 ? 0 : 1);
	}

	@Override
	public String toString()
	{
		return "NAnd " + getInput1().getValue() + ", " + getInput2().getValue() + " -> " + getOutput().getValue();
	}
}
