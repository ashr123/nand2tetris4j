package il.co.nand2tetris.components;

public class MultiBitRegister extends Gate
{
	private final WireSet input, output;
	private final Wire load = new Wire();

	public MultiBitRegister(int iSize)
	{
		input = new WireSet(iSize);
		output = new WireSet(iSize);
		//your code here
		for (int i=0; i<iSize; i++)
		{
			SingleBitRegister singleBitRegister = new SingleBitRegister();

			singleBitRegister.connectInput(input.getWireAt(i));
			singleBitRegister.connectLoad(load);

			output.getWireAt(i).connectInput(singleBitRegister.getOutput());
		}
	}

	public void connectInput(WireSet wsInput)
	{
		input.connectInput(wsInput);
	}

	public WireSet getInput()
	{
		return input;
	}

	public WireSet getOutput()
	{
		return output;
	}

	public int size()
	{
		return input.size();
	}

	public Wire getLoad()
	{
		return load;
	}

	@Override
	public boolean testGate()
	{
		load.setValue(1);
		input.getWireAt(0).setValue(1);
		input.getWireAt(1).setValue(1);
		Clock.clockDown();
		Clock.clockUp();
		load.setValue(0);
		input.getWireAt(0).setValue(0);
		Clock.clockDown();
		Clock.clockUp();
		return output.getWireAt(0).getValue() == 1 || output.getWireAt(1).getValue() == 1;
	}

	@Override
	public String toString()
	{
		return output.toString();
	}
}
