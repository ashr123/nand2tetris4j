package il.co.nand2tetris.components;

public class BitwiseDemux extends Gate
{
	private WireSet output1, output2, input;
	private Wire control = new Wire();

	public BitwiseDemux(int iSize)
	{
		input = new WireSet(iSize);
		//your code here
		output1 = new WireSet(iSize);
		output2 = new WireSet(iSize);

		for (int i = 0; i < iSize; i++)
		{
			Demux demux = new Demux();
			demux.connectControl(control);
			demux.connectInput(input.getWireAt(i));
			output1.getWireAt(i).connectInput(demux.getOutput1());
			output2.getWireAt(i).connectInput(demux.getOutput2());
		}
	}

	public void connectControl(Wire wControl)
	{
		control.connectInput(wControl);
	}

	public void connectInput(WireSet wsInput)
	{
		input.connectInput(wsInput);
	}

	public WireSet getOutput1()
	{
		return output1;
	}

	public WireSet getOutput2()
	{
		return output2;
	}

	public WireSet getInput()
	{
		return input;
	}

	public Wire getControl()
	{
		return control;
	}

	@Override
	public boolean TestGate()
	{
		for (int i = 0; i < input.getSize(); i++)
		{
			control.setValue(0);
			input.getWireAt(i).setValue(0);
			if (output1.getWireAt(i).getValue() != 0 && output2.getWireAt(i).getValue() != 0)
				return false;

			control.setValue(0);
			input.getWireAt(i).setValue(1);
			if (output1.getWireAt(i).getValue() != 1 && output2.getWireAt(i).getValue() != 0)
				return false;

			control.setValue(1);
			input.getWireAt(i).setValue(0);
			if (output1.getWireAt(i).getValue() != 0 && output2.getWireAt(i).getValue() != 0)
				return false;

			control.setValue(1);
			input.getWireAt(i).setValue(1);
			if (output1.getWireAt(i).getValue() != 0 && output2.getWireAt(i).getValue() != 1)
				return false;
		}

		return true;
	}
}
