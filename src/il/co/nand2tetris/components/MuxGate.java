package il.co.nand2tetris.components;

public class MuxGate extends TwoInputGate
{
	private final Wire controlInput = new Wire();

	public MuxGate()
	{
		//your code here
		final NotGate not = new NotGate();
		final AndGate
				xAndNotC = new AndGate(),
				cAndy = new AndGate();
		final OrGate or = new OrGate();

		not.connectInput(controlInput);

		xAndNotC.connectInput1(getInput1());
		xAndNotC.connectInput2(not.getOutput());

		cAndy.connectInput1(controlInput);
		cAndy.connectInput2(getInput2());

		or.connectInput1(xAndNotC.getOutput());
		or.connectInput2(cAndy.getOutput());

		getOutput().connectInput(or.getOutput());
	}

	public Wire getControlInput()
	{
		return controlInput;
	}

	public void ConnectControl(final Wire wControl)
	{
		controlInput.connectInput(wControl);
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean testGate()
	{
		controlInput.setValue(0);
		getInput1().setValue(0);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(0);
		getInput2().setValue(1);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(0);
		if (getOutput().getValue() != 1)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(1);
		if (getOutput().getValue() != 1)
			return false;


		controlInput.setValue(1);
		getInput1().setValue(0);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(0);
		getInput2().setValue(1);
		if (getOutput().getValue() != 1)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(1);
		return getOutput().getValue() == 1;
	}

	@Override
	public String toString()
	{
		return "Mux " + getInput1().getValue() + ", " + getInput2().getValue() + ", C" + controlInput.getValue() + " -> " + getOutput().getValue();
	}
}
