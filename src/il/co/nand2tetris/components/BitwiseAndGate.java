package il.co.nand2tetris.components;

public class BitwiseAndGate extends BitwiseTwoInputGate
{
	public BitwiseAndGate(final int iSize)
	{
		super(iSize);
		for (int i = 0; i < iSize; i++)
		{
			final AndGate and = new AndGate();
			and.connectInput1(getInput1().getWireAt(i));
			and.connectInput2(getInput2().getWireAt(i));
			getOutput().getWireAt(i).connectInput(and.getOutput());
		}
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean TestGate()
	{
		for (int i = 0; i < getInput1().size(); i++)
		{
			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(1);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(1);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(1);
			getInput2().getWireAt(i).setValue(1);
			if (getOutput().getWireAt(i).getValue() != 1)
				return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "BitwiseAnd " + getInput1() + ", " + getInput2() + " -> " + getOutput();
	}
}
