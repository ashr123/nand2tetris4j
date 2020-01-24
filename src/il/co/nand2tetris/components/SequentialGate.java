package il.co.nand2tetris.components;

public abstract class SequentialGate
{
	public SequentialGate()
	{
		Clock.RegisterSequentialGate(this);
	}

	public abstract void onClockUp();

	public abstract void onClockDown();

	public abstract boolean testGate();
}
