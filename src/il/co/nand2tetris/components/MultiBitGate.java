package il.co.nand2tetris.components;

public abstract class MultiBitGate extends Gate
{
	private WireSet m_wsInput;
	private Wire output = new Wire();

	public MultiBitGate(int iInputCount)
	{
		m_wsInput = new WireSet(iInputCount);
	}

	public void connectInput(WireSet ws)
	{
		m_wsInput.connectInput(ws);
	}

	public Wire getInputWireAt(int i)
	{
		return m_wsInput.getWireAt(i);
	}

	public Wire getOutput()
	{
		return output;
	}
}
