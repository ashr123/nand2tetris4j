package il.co.nand2tetris.components;

public class MultiBitOrGate extends MultiBitGate
{
	public MultiBitOrGate(int iInputCount)
	{
		super(iInputCount);
		final OrGate[] or = new OrGate[iInputCount - 1];
		or[0] = new OrGate();
		or[0].connectInput1(getInputWireAt(0));
		or[0].connectInput2(getInputWireAt(1));
		for (int i = 2; i < iInputCount; i++)
		{
			or[i - 1] = new OrGate();
			or[i - 1].connectInput1(or[i - 2].getOutput());
			or[i - 1].connectInput2(getInputWireAt(i));
		}
		getOutput().connectInput(or[iInputCount - 2].getOutput());
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

//	@Override
//	public String toString()
//	{
//		return output.toString();
//	}
}
