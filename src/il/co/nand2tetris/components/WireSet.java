package il.co.nand2tetris.components;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * this class represents a set of wires (a cable)
 */
public class WireSet
{
	private final Wire[] m_aWires;
	private boolean isInputConnected;

	public WireSet(int iSize)
	{
		if (iSize > 30)
			throw new IllegalArgumentException("iSize cannot be over 30, got: " + iSize);
		m_aWires = new Wire[iSize];
		Arrays.setAll(m_aWires, i -> new Wire());
	}

	public Wire getWireAt(int i)
	{
		return m_aWires[i];
	}

	public void connectInput(WireSet wIn)
	{
		if (isInputConnected)
			throw new IllegalStateException("Cannot connect a wire to more than one inputs");
		if (wIn.m_aWires.length != m_aWires.length)
			throw new IllegalStateException("Cannot connect two wire sets of different sizes");
		for (int i = 0; i < m_aWires.length; i++)
			m_aWires[i].connectInput(wIn.m_aWires[i]);
		isInputConnected = true;
	}

	/**
	 * transform the binary code into a positive integer
	 *
	 * @return
	 */
	public int getValue()
	{
		//throw new NotImplementedException();
		return IntStream.range(0, m_aWires.length)
				.map(i -> m_aWires[i].getValue() * (1 << i))
				.sum();
	}

	public void setValue(int iValue)
	{
		//throw new NotImplementedException();
		//if (iValue == 0)
		for (final Wire m_aWire : m_aWires)
			m_aWire.setValue(0);
		int index = 0;
		while (iValue > 0)
		{
			m_aWires[index++].setValue(iValue % 2);
			iValue /= 2;
		}
		//int[] temp = Array.ConvertAll(Convert.ToString(iValue, 2).PadLeft(Size, '0').Reverse().ToArray(), c => (int)char.GetNumericValue(c));
		//for (int i = 0; i < Size; i++)
		//	m_aWires[i].Value = temp[i];
	}

	/**
	 * transform the binary code in 2's complement into an integer
	 *
	 * @return
	 */
	public int get2sComplement()
	{
		if (m_aWires[m_aWires.length - 1].getValue() == 1)
		{
			final int[] tempArr = Arrays.stream(m_aWires)
					.mapToInt(m_aWire -> 1 - m_aWire.getValue())
					.toArray();

			for (int i = 0, carry = 1; i < m_aWires.length; i++)
			{
				final int temp = carry + tempArr[i];
				tempArr[i] = temp % 2;
				carry = temp == 2 ? 1 : 0;
			}

			return -IntStream.range(0, m_aWires.length)
					.map(i -> tempArr[i] * (1 << i)) // 2^i
					.sum();
		}
		return getValue();
	}

	/**
	 * transform an integer value into binary using 2`s complement and set the wires accordingly, with 0 being the LSB
	 *
	 * @param iValue
	 */
	public void set2sComplement(final int iValue)
	{
		//throw new NotImplementedException();
		if (iValue >= 0)
			setValue(iValue);
		else
		{
			setValue(-iValue);
			for (Wire m_aWire : m_aWires)
				m_aWire.setValue(1 - m_aWire.getValue());

			for (int i = 0, carry = 1; i < m_aWires.length; i++)
			{
				final int temp = carry + m_aWires[i].getValue();
				m_aWires[i].setValue(temp % 2);
				carry = temp == 2 ? 1 : 0;
			}
		}
	}

	public int size()
	{
		return m_aWires.length;
	}

	@Override
	public String toString()
	{
		final StringBuilder s = new StringBuilder("[");
		for (int i = m_aWires.length - 1; i >= 0; i--)
			s.append(m_aWires[i].getValue());
		s.append("]");
		return s.toString();
	}
}
