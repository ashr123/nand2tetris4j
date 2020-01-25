package il.co.nand2tetris.components;

public abstract class MultiBitGate extends Gate
{
	private final WireSet m_wsInput;
	private final Wire output = new Wire();

	public MultiBitGate(final int iInputCount)
	{
		m_wsInput = new WireSet(iInputCount);
	}

	public void connectInput(final WireSet ws)
	{
		m_wsInput.connectInput(ws);
	}

	public Wire getInputWireAt(final int i)
	{
		return m_wsInput.getWireAt(i);
	}

	public Wire getOutput()
	{
		return output;
	}

	public int size()
	{
		return m_wsInput.size();
	}
}
