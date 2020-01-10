package il.co.nand2tetris.components;

import java.util.LinkedList;
import java.util.List;

/**
 * this class represents a wire that can hold a single value - 0 or 1
 */
public class Wire implements Component
{
	private static int wires;
	private List<Component> outputs = new LinkedList<>();
	private boolean isInputConnected;
	private int value;

	public Wire()
	{
		wires++;
	}

	public static int getWires()
	{
		return wires;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		if (value != 0 && value != 1)
			throw new IllegalArgumentException("Illegal value for wire, got " + value);
		this.value = value;
		for (Component component : outputs)
		{
			if (component instanceof Wire)
				((Wire) component).setValue(value);
			else
				component.compute();
		}
	}

	public void connectInput(Wire wIn)
	{
		if (isInputConnected)
			throw new IllegalStateException("Cannot connect a wire to more than one inputs");
		isInputConnected = true;
		wIn.outputs.add(this);
		value = wIn.value;
	}

	public void connectOutput(Component cOut)
	{
		outputs.add(cOut);
		cOut.compute();
	}

	@Override
	public void compute()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString()
	{
		return String.valueOf(value);
	}
}
