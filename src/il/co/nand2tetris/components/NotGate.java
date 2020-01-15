package il.co.nand2tetris.components;

public class NotGate extends Gate implements Component
{
	private static long notGates, notComputed;
	private final Wire
			input = new Wire(),
			output = new Wire();

	public NotGate()
	{
		notGates++;
		input.connectOutput(this);
	}

	public static long getNotGates()
	{
		return notGates;
	}

	public static long getNotComputed()
	{
		return notComputed;
	}

	public Wire getInput()
	{
		return input;
	}

	public Wire getOutput()
	{
		return output;
	}

	public void connectInput(final Wire wInput)
	{
		input.connectInput(wInput);
	}

	@Override
	public void compute()
	{
		notComputed++;
		output.setValue(input.getValue() == 0 ? 1 : 0);
	}

	@Override
	public boolean TestGate()
	{
		input.setValue(0);
		if (output.getValue() != 1)
			return false;

		input.setValue(1);
		return output.getValue() == 0;
	}

	@Override
	public String toString()
	{
		return "Not " + input.getValue() + " -> " + output.getValue();
	}
}
