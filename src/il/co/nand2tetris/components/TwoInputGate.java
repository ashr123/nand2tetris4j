package il.co.nand2tetris.components;

/**
 * this is the base class for all boolean gates with two input wires
 */
public abstract class TwoInputGate extends Gate
{
	private final Wire
			input1 = new Wire(),
			input2 = new Wire(),
			output = new Wire();

	public void connectInput1(final Wire wInput)
	{
		input1.connectInput(wInput);
	}

	public void connectInput2(final Wire wInput)
	{
		input2.connectInput(wInput);
	}

	public Wire getInput1()
	{
		return input1;
	}

	public Wire getInput2()
	{
		return input2;
	}

	public Wire getOutput()
	{
		return output;
	}
}
