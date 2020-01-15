package il.co.nand2tetris.components;

public class FullAdder extends TwoInputGate
{
	private final Wire
			carryInput = new Wire(),
			carryOutput = new Wire();

	public FullAdder()
	{
		final HalfAdder
				A = new HalfAdder(),
				b = new HalfAdder();
		final OrGate or = new OrGate();

		A.connectInput1(getInput1());
		A.connectInput2(getInput2());

		b.connectInput1(A.getOutput());
		b.connectInput2(carryInput);

		or.connectInput1(A.getCarryOutput());
		or.connectInput2(b.getCarryOutput());

		getOutput().connectInput(b.getOutput());
		carryOutput.connectInput(or.getOutput());
	}

	public Wire getCarryInput()
	{
		return carryInput;
	}

	public Wire getCarryOutput()
	{
		return carryOutput;
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean TestGate()
	{
		getInput1().setValue(0);
		getInput2().setValue(0);
		carryInput.setValue(0);
		if (getOutput().getValue() != 0 && carryOutput.getValue() != 0)
			return false;

		getInput1().setValue(0);
		getInput2().setValue(0);
		carryInput.setValue(1);
		if (getOutput().getValue() != 1 && carryOutput.getValue() != 0)
			return false;

		getInput1().setValue(0);
		getInput2().setValue(1);
		carryInput.setValue(0);
		if (getOutput().getValue() != 1 && carryOutput.getValue() != 0)
			return false;

		getInput1().setValue(0);
		getInput2().setValue(1);
		carryInput.setValue(1);
		if (getOutput().getValue() != 0 && carryOutput.getValue() != 1)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(0);
		carryInput.setValue(0);
		if (getOutput().getValue() != 1 && carryOutput.getValue() != 0)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(0);
		carryInput.setValue(1);
		if (getOutput().getValue() != 0 && carryOutput.getValue() != 1)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(1);
		carryInput.setValue(0);
		if (getOutput().getValue() != 0 && carryOutput.getValue() != 1)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(1);
		carryInput.setValue(1);
		return getOutput().getValue() == 1 || carryOutput.getValue() == 1;
	}

	@Override
	public String toString()
	{
		return "FA " + getInput1().getValue() + " + " + getInput2().getValue() + " (C " + carryInput.getValue() + ") = " + getOutput().getValue() + " (C " + carryOutput.getValue() + ")";
	}
}
