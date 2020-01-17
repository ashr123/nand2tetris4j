package il.co.nand2tetris.components;

import java.util.Arrays;

public class BitwiseMultiwayDemux extends Gate
{
	private final WireSet input, control;
	private final WireSet[] outputs;

	public BitwiseMultiwayDemux(final int iSize, final int cControlBits)
	{
		if (cControlBits > 30)
			throw new IllegalArgumentException("cControlBits cannot be over 30, got: " + cControlBits);
		outputs = new WireSet[1 << cControlBits]; // 2^cControlBits
		input = new WireSet(iSize);
		control = new WireSet(cControlBits);
		for (int i = 0; i < outputs.length; i++)
			outputs[i] = new WireSet(iSize);

		//your code here
		final BitwiseDemux[] bitwiseDemux = new BitwiseDemux[outputs.length - 1];
		Arrays.setAll(bitwiseDemux, i -> new BitwiseDemux(iSize));

		for (int i = 0, count = bitwiseDemux.length - 1; i < outputs.length; i += 2)
		{
			outputs[i].connectInput(bitwiseDemux[count].getOutput1());
			outputs[i + 1].connectInput(bitwiseDemux[count].getOutput2());
			bitwiseDemux[count--].connectControl(control.getWireAt(0));
		}

		for (int i = bitwiseDemux.length; i > 2; i -= 2)
		{
			bitwiseDemux[i - 1].connectInput(bitwiseDemux[i / 2 - 1].getOutput1());
			bitwiseDemux[i - 2].connectInput(bitwiseDemux[i / 2 - 1].getOutput2());
			bitwiseDemux[i / 2 - 1].connectControl(control.getWireAt(control.getSize() - 1 - log2(i / 2)));
		}

		bitwiseDemux[0].connectInput(input);
	}

	public void connectInput(final WireSet wsInput)
	{
		input.connectInput(wsInput);
	}

	public void connectControl(final WireSet wsControl)
	{
		control.connectInput(wsControl);
	}

	public WireSet getInput()
	{
		return input;
	}

	public WireSet getControl()
	{
		return control;
	}

	public WireSet[] getOutputs()
	{
		return outputs;
	}

	@Override
	public boolean TestGate()
	{
		input.SetValue(2);
		for (int i = 0; i < outputs.length; i++)
		{
			control.SetValue(i);
			for (int j = 0; j < input.getSize(); j++)
				if (outputs[i].getWireAt(j).getValue() != input.getWireAt(j).getValue())
					return false;
		}

		return true;
	}
}
