package il.co.nand2tetris.components;

public class Demux extends Gate
{
	private final Wire
			output1 = new Wire(),
			output2 = new Wire(),
			input = new Wire(),
			control = new Wire();

	public Demux()
	{
		NotGate not = new NotGate();
		AndGate
				xAndNotC = new AndGate(),
				xAndC = new AndGate();

		not.connectInput(control);

		xAndNotC.connectInput1(input);
		xAndNotC.connectInput2(not.getOutput());
		output1.connectInput(xAndNotC.getOutput());

		xAndC.connectInput1(control);
		xAndC.connectInput2(input);
		output2.connectInput(xAndC.getOutput());
	}

	public void connectControl(final Wire wControl)
	{
		control.connectInput(wControl);
	}

	public void connectInput(final Wire wInput)
	{
		input.connectInput(wInput);
	}

	public Wire getOutput1()
	{
		return output1;
	}

	public Wire getOutput2()
	{
		return output2;
	}

	public Wire getInput()
	{
		return input;
	}

	public Wire getControl()
	{
		return control;
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean TestGate()
	{
		control.setValue(0);
		input.setValue(0);
		if (output1.getValue() != 0 || output2.getValue() != 0)
			return false;

		control.setValue(0);
		input.setValue(1);
		if (output1.getValue() != 1 || output2.getValue() != 0)
			return false;

		control.setValue(1);
		input.setValue(0);
		if (output1.getValue() != 0 || output2.getValue() != 0)
			return false;

		control.setValue(1);
		input.setValue(1);
		return output1.getValue() == 0 && output2.getValue() == 1;
	}
}
