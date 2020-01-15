package il.co.nand2tetris.components;

public class BitwiseOrGate extends BitwiseTwoInputGate
{
	public BitwiseOrGate(int iSize)
	{
		super(iSize);
		for (int i = 0; i < iSize; i++)
		{
			final OrGate or = new OrGate();
			or.connectInput1(getInput1().getWireAt(i));
			or.connectInput2(getInput2().getWireAt(i));
			getOutput().getWireAt(i).connectInput(or.getOutput());
		}
	}

	@Override
	public boolean TestGate()
	{
		for (int i = 0; i < getInput1().getSize(); i++)
		{
			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(1);
			if (getOutput().getWireAt(i).getValue() != 1)
				return false;

			getInput1().getWireAt(i).setValue(1);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 1)
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
		return "BitwiseOr " + getInput1() + ", " + getInput2() + " -> " + getOutput();
	}
}
