package il.co.nand2tetris.components;

public class BitwiseNotGate extends Gate
{
	private final WireSet input, output;

	public BitwiseNotGate(final int iSize)
	{
		input = new WireSet(iSize);
		output = new WireSet(iSize);

		//your code here
		for (int i = 0; i < iSize; i++)
		{
			final NotGate not = new NotGate();
			not.connectInput(input.getWireAt(i));
			output.getWireAt(i).connectInput(not.getOutput());
		}
	}

	public WireSet getInput()
	{
		return input;
	}

	public WireSet getOutput()
	{
		return output;
	}

	public void connectInput(final WireSet ws)
	{
		input.connectInput(ws);
	}

	@Override
	public String toString()
	{
		return "BitwiseNot " + input + " -> " + output;
	}

	@Override
	public boolean testGate()
	{
		for (int i = 0; i < input.size(); i++)
		{
			input.getWireAt(i).setValue(0);
			if (output.getWireAt(i).getValue() != 1)
				return false;

			input.getWireAt(i).setValue(1);
			if (output.getWireAt(i).getValue() != 0)
				return false;
		}
		return true;
	}
}
