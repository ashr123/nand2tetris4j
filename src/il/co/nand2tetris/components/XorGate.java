package il.co.nand2tetris.components;

public class XorGate extends TwoInputGate
{
	public XorGate()
	{
		//your code here
		NAndGate
				a = new NAndGate(),
				b = new NAndGate(),
				c = new NAndGate(),
				d = new NAndGate();

		a.connectInput1(getInput1());
		a.connectInput2(getInput2());

		b.connectInput1(getInput1());
		b.connectInput2(a.getOutput());

		c.connectInput1(a.getOutput());
		c.connectInput2(getInput2());

		d.connectInput1(b.getOutput());
		d.connectInput2(c.getOutput());

		getOutput().connectInput(d.getOutput());
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean TestGate()
	{
		getInput1().setValue(0);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0)
		{
			System.out.println(this);
			return false;
		}

		getInput1().setValue(0);
		getInput2().setValue(1);
		if (getOutput().getValue() != 1)
		{
			System.out.println(this);
			return false;
		}

		getInput1().setValue(1);
		getInput2().setValue(0);
		if (getOutput().getValue() != 1)
		{
			System.out.println(this);
			return false;
		}

		getInput1().setValue(1);
		getInput2().setValue(1);
		return getOutput().getValue() == 0;
	}

	@Override
	public String toString()
	{
		return "Xor " + getInput1().getValue() + ", " + getInput2().getValue() + " -> " + getOutput().getValue();
	}
}
