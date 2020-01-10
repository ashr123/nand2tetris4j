package il.co.nand2tetris.components;

public class BitwiseNotGate extends Gate
{
	private WireSet input, output;

	public BitwiseNotGate(int iSize)
	{
		input = new WireSet(iSize);
		output = new WireSet(iSize);

		//your code here
		//not = new NotGate[iSize];
		for (int i = 0; i < iSize; i++)
		{
			NotGate not = new NotGate();
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

	public void ConnectInput(WireSet ws)
	{
		input.connectInput(ws);
	}

	@Override
	public String toString()
	{
		return "BitwiseNot " + input + " -> " + output;
	}

	@Override
	public boolean TestGate()
	{
		for (int i = 0; i < input.getSize(); i++)
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
