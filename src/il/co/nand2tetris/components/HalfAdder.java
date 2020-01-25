package il.co.nand2tetris.components;

public class HalfAdder extends TwoInputGate
{
	private final Wire carryOutput = new Wire();

	public HalfAdder()
	{
		final XorGate sum = new XorGate();
		final AndGate carry = new AndGate();

		sum.connectInput1(getInput1());
		sum.connectInput2(getInput2());

		carry.connectInput1(getInput1());
		carry.connectInput2(getInput2());

		getOutput().connectInput(sum.getOutput());

		carryOutput.connectInput(carry.getOutput());
	}

	public Wire getCarryOutput()
	{
		return carryOutput;
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean testGate()
	{
		getInput1().setValue(0);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0 || carryOutput.getValue() != 0)
		{
			System.out.println(this);
			return false;
		}

		getInput1().setValue(0);
		getInput2().setValue(1);
		if (getOutput().getValue() != 1 || carryOutput.getValue() != 0)
		{
			System.out.println(this);
			return false;
		}

		getInput1().setValue(1);
		getInput2().setValue(0);
		if (getOutput().getValue() != 1 || carryOutput.getValue() != 0)
		{
			System.out.println(this);
			return false;
		}

		getInput1().setValue(1);
		getInput2().setValue(1);
//		System.out.println(this);
		return getOutput().getValue() == 0 && carryOutput.getValue() == 1;
	}

	@Override
	public String toString()
	{
		return "HA " + getInput1().getValue() + ", " + getInput2().getValue() + " -> " + getOutput().getValue() + " (C " + carryOutput + ")";
	}
}
