package il.co.nand2tetris.components;

public class Counter extends SequentialGate
{
	private int m_iValue;
	private final WireSet input, output;
	private final Wire
			load = new Wire(),
			reset = new Wire();

	public Counter(int iSize)
	{
		input = new WireSet(iSize);
		output = new WireSet(iSize);
	}

	public void ConnectInput(WireSet ws)
	{
		input.connectInput(ws);
	}

	public void ConnectLoad(Wire w)
	{
		load.connectInput(w);
	}

	public void ConnectReset(Wire w)
	{
		reset.connectInput(w);
	}

	@Override
	public void onClockUp()
	{
		output.setValue(m_iValue);
	}

	@Override
	public void onClockDown()
	{
		if (load.getValue() == 1)
			m_iValue = input.getValue();
		else if (reset.getValue() == 1)
			m_iValue = 0;
		else
			m_iValue++;
	}

	@Override
	public boolean testGate()
	{
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public String toString()
	{
		return output.toString();
	}
}
