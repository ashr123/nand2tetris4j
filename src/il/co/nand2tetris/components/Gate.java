package il.co.nand2tetris.components;

public abstract class Gate
{
	protected static int log2(final int x)
	{
		return (int) (Math.log(x) / Math.log(2) + 1e-10);
	}

	public abstract boolean TestGate();
}
