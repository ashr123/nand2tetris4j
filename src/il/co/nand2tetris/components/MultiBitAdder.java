package il.co.nand2tetris.components;

public class MultiBitAdder extends Gate
{
	private final WireSet input1, input2, output;
	private final Wire overflow = new Wire();

	public MultiBitAdder(final int iSize)
	{
		input1 = new WireSet(iSize);
		input2 = new WireSet(iSize);
		output = new WireSet(iSize);

		final HalfAdder halfAdder = new HalfAdder();
		final FullAdder[] fullAdders = new FullAdder[iSize - 1];

		halfAdder.connectInput1(input1.getWireAt(0));
		halfAdder.connectInput2(input2.getWireAt(0));
		output.getWireAt(0).connectInput(halfAdder.getOutput());

		fullAdders[0] = new FullAdder();
		fullAdders[0].connectInput1(input1.getWireAt(1));
		fullAdders[0].connectInput2(input2.getWireAt(1));
		fullAdders[0].getCarryInput().connectInput(halfAdder.getCarryOutput());
		output.getWireAt(1).connectInput(fullAdders[0].getOutput());
		for (int i = 1; i < fullAdders.length; i++)
		{
			fullAdders[i] = new FullAdder();
			fullAdders[i].connectInput1(input1.getWireAt(i + 1));
			fullAdders[i].connectInput2(input2.getWireAt(i + 1));
			fullAdders[i].getCarryInput().connectInput(fullAdders[i - 1].getCarryOutput());
			output.getWireAt(i + 1).connectInput(fullAdders[i].getOutput());
		}

		overflow.connectInput(fullAdders[fullAdders.length - 1].getCarryOutput());
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

	public Wire getOverflow()
	{
		return overflow;
	}

	public void connectInput1(final WireSet wInput)
	{
		input1.connectInput(wInput);
	}

	public void connectInput2(final WireSet wInput)
	{
		input2.connectInput(wInput);
	}

	public int size()
	{
		return input1.size();
	}

	@Override
	public boolean testGate()
	{
		for (int i = 0; i < 1 << input1.size(); i++)
		{
			input1.setValue(i);
			for (int j = 0; j < (1 << input2.size()) - i; j++)
			{
				input2.setValue(j);
				if (output.getValue() != i + j)
					return false;
			}
		}

		return true;
	}

	@Override
	public String toString()
	{
		return input1 + "(" + input1.get2sComplement() + ")" + " + " + input2 + "(" + input2.get2sComplement() + ")" + " = " + output + "(" + output.get2sComplement() + ")";
	}
}
