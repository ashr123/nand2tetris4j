package il.co.nand2tetris.components;

public class MultiBitAdder extends Gate
{
	private WireSet input1, input2, output;
	private Wire overflow = new Wire();

	public MultiBitAdder(int iSize)
	{
		input1 = new WireSet(iSize);
		input2 = new WireSet(iSize);
		output = new WireSet(iSize);

		HalfAdder halfAdder = new HalfAdder();
		FullAdder[] fullAdders = new FullAdder[iSize - 1];

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

	public void connectInput1(WireSet wInput)
	{
		input1.connectInput(wInput);
	}

	public void connectInput2(WireSet wInput)
	{
		input2.connectInput(wInput);
	}

	@Override
	public boolean TestGate()
	{
		for (int i = 0; i < 1 << input1.getSize(); i++)
		{
			input1.SetValue(i);
			for (int j = 0; j < (1 << input2.getSize()) - i; j++)
			{
				input2.SetValue(j);
				if (output.getValue() != i + j)
					return false;
			}
		}

		return true;
	}

	@Override
	public String toString()
	{
		return input1 + "(" + input1.Get2sComplement() + ")" + " + " + input2 + "(" + input2.Get2sComplement() + ")" + " = " + output + "(" + output.Get2sComplement() + ")";
	}
}
