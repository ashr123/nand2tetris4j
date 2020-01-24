package il.co.nand2tetris.components;

/**
 * this is the only gate that actually gets the clock ticks, it is the base component for all memory components.
 */
public class DFlipFlopGate extends SequentialGate
{
	private int m_iState;
	private final Wire
			input = new Wire(),
			output = new Wire();

	public void connectInput(Wire wInput)
	{
		input.connectInput(wInput);
	}

	public Wire getInput()
	{
		return input;
	}

	public Wire getOutput()
	{
		return output;
	}

	@Override
	public void onClockUp()
	{
		output.setValue(m_iState);
	}

	@Override
	public void onClockDown()
	{
		m_iState = input.getValue();
	}

	@Override
	public boolean testGate()
	{
		input.setValue(1);
		Clock.clockDown();
		Clock.clockUp();
		input.setValue(0);
		if (output.getValue() != 1)
			return false;

		Clock.clockDown();
		Clock.clockUp();
		input.setValue(1);
		return output.getValue() == 0;
	}
}
