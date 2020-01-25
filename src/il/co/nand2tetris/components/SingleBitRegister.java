package il.co.nand2tetris.components;

public class SingleBitRegister extends Gate
{
	private final Wire
			input = new Wire(),
			output = new Wire(),
			load = new Wire();

	public SingleBitRegister()
	{
		//your code here
		MuxGate mux = new MuxGate();
		DFlipFlopGate dFlipFlop = new DFlipFlopGate();

		mux.connectInput1(dFlipFlop.getOutput());
		mux.connectInput2(input);
		mux.ConnectControl(load);

		dFlipFlop.connectInput(mux.getOutput());

		output.connectInput(dFlipFlop.getOutput());
	}

	public void connectInput(Wire wInput)
	{
		input.connectInput(wInput);
	}

	public void connectLoad(Wire wLoad)
	{
		load.connectInput(wLoad);
	}

	public Wire getInput()
	{
		return input;
	}

	public Wire getOutput()
	{
		return output;
	}

	public Wire getLoad()
	{
		return load;
	}

	@Override
	public boolean testGate()
	{
		load.setValue(1);
		input.setValue(1);
		if (output.getValue() != 0)
			return false;

		Clock.clockDown();
		Clock.clockUp();
		if (output.getValue() != 1)
			return false;

		load.setValue(0);
		input.setValue(0);
		Clock.clockDown();
		Clock.clockUp();
		return output.getValue() == 1;
	}
}
