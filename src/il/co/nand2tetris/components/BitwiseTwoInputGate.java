package il.co.nand2tetris.components;

/**
 * base class for all bitwise gates with two inputs
 */
public abstract class BitwiseTwoInputGate extends Gate
{
	private final WireSet input1, input2, output;

	public BitwiseTwoInputGate(final int iSize)
	{
		input1 = new WireSet(iSize);
		input2 = new WireSet(iSize);
		output = new WireSet(iSize);
	}

	public void connectInput1(final WireSet wInput)
	{
		input1.connectInput(wInput);
	}

	public void connectInput2(final WireSet wInput)
	{
		input2.connectInput(wInput);
	}

	public WireSet getInput1()
	{
		return input1;
	}

	public WireSet getInput2()
	{
		return input2;
	}

	public WireSet getOutput()
	{
		return output;
	}

	public int getSize()
	{
		return input1.getSize();
	}
}
