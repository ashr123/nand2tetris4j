package il.co.nand2tetris.components;

public class BitwiseMux extends BitwiseTwoInputGate
{
	private final Wire controlInput = new Wire();

	public BitwiseMux(final int iSize)
	{
		super(iSize);
		for (int i = 0; i < iSize; i++)
		{
			final MuxGate mux = new MuxGate();
			mux.ConnectControl(controlInput);
			mux.connectInput1(getInput1().getWireAt(i));
			mux.connectInput2(getInput2().getWireAt(i));
			getOutput().getWireAt(i).connectInput(mux.getOutput());
		}
	}

	public void connectControl(final Wire wControl)
	{
		controlInput.connectInput(wControl);
	}

	public Wire getControlInput()
	{
		return controlInput;
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean TestGate()
	{
		for (int i = 0; i < getInput1().getSize(); i++)
		{
			controlInput.setValue(0);
			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(1);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(1);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 1)
				return false;

			getInput1().getWireAt(i).setValue(1);
			getInput2().getWireAt(i).setValue(1);
			if (getOutput().getWireAt(i).getValue() != 1)
				return false;


			controlInput.setValue(1);
			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(0);
			getInput2().getWireAt(i).setValue(1);
			if (getOutput().getWireAt(i).getValue() != 1)
				return false;

			getInput1().getWireAt(i).setValue(1);
			getInput2().getWireAt(i).setValue(0);
			if (getOutput().getWireAt(i).getValue() != 0)
				return false;

			getInput1().getWireAt(i).setValue(1);
			getInput2().getWireAt(i).setValue(1);
			if (getOutput().getWireAt(i).getValue() != 1)
				return false;
		}

		return true;
	}

	@Override
	public String toString()
	{
		return "BitwiseMux " + getInput1() + ", " + getInput2() + ",C " + controlInput.getValue() + " -> " + getOutput();
	}
}
