package il.co.nand2tetris.components;

public class AndGate extends TwoInputGate
{
	public AndGate()
	{
		final NotGate m_gNot = new NotGate();
		final NAndGate m_gNand = new NAndGate();
		m_gNot.connectInput(m_gNand.getOutput());
		m_gNand.connectInput1(getInput1());
		m_gNand.connectInput2(getInput2());
		getOutput().connectInput(m_gNot.getOutput());
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean testGate()
	{
		getInput1().setValue(0);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(0);
		getInput2().setValue(1);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(0);
		if (getOutput().getValue() != 0)
			return false;

		getInput1().setValue(1);
		getInput2().setValue(1);
		return getOutput().getValue() == 1;
	}

	@Override
	public String toString()
	{
		return "And " + getInput1().getValue() + ", " + getInput2().getValue() + " -> " + getOutput().getValue();
	}
}
