package il.co.nand2tetris.components;

public class MultiBitAndGate extends MultiBitGate
{
	public MultiBitAndGate(int iInputCount)
	{
		super(iInputCount);
		AndGate[] and = new AndGate[iInputCount - 1];
		and[0] = new AndGate();
		and[0].connectInput1(getInputWireAt(0));
		and[0].connectInput2(getInputWireAt(1));
		for (int i = 2; i < iInputCount; i++)
		{
			and[i - 1] = new AndGate();
			and[i - 1].connectInput1(and[i - 2].getOutput());
			and[i - 1].connectInput2(getInputWireAt(i));
		}
		getOutput().connectInput(and[iInputCount - 2].getOutput());
	}

	/**
	 * TODO
	 *
	 * @return
	 */
	@Override
	public boolean TestGate()
	{
		return true;
	}
}
