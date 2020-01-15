package il.co.nand2tetris.components;

import java.util.Arrays;

public class BitwiseMultiwayMux extends Gate
{
	private final WireSet output, control;
	private final WireSet[] inputs;

	public BitwiseMultiwayMux(final int iSize, final int cControlBits)
	{
		if (cControlBits > 30)
			throw new IllegalArgumentException("cControlBits cannot be over 30, got: " + cControlBits);
		inputs = new WireSet[1 << cControlBits]; // 2^cControlBits
		output = new WireSet(iSize);
		control = new WireSet(cControlBits);
		for (int i = 0; i < inputs.length; i++)
			inputs[i] = new WireSet(iSize);

		//your code here
		final BitwiseMux[] bitwiseMux = new BitwiseMux[inputs.length - 1];
		Arrays.setAll(bitwiseMux, i -> new BitwiseMux(iSize));

		for (int i = 0, count = bitwiseMux.length - 1; i < inputs.length; i += 2)
		{
			bitwiseMux[count].connectInput1(inputs[i]);
			bitwiseMux[count].connectInput2(inputs[i + 1]);
			bitwiseMux[count--].connectControl(control.getWireAt(0));
		}

		for (int i = bitwiseMux.length; i > 2; i -= 2)
		{
			bitwiseMux[i / 2 - 1].connectInput1(bitwiseMux[i - 1].getOutput());
			bitwiseMux[i / 2 - 1].connectInput2(bitwiseMux[i - 2].getOutput());
			bitwiseMux[i / 2 - 1].connectControl(control.getWireAt(control.getSize() - 1 - log2(i / 2)));
		}

		output.connectInput(bitwiseMux[0].getOutput());
	}

	public void connectInput(final int i, final WireSet wsInput)
	{
		inputs[i].connectInput(wsInput);
	}

	public void connectControl(final WireSet wsControl)
	{
		control.connectInput(wsControl);
	}

	public WireSet getOutput()
	{
		return output;
	}

	public WireSet getControl()
	{
		return control;
	}

	public WireSet[] getInputs()
	{
		return inputs;
	}

	@Override
	public boolean TestGate()
	{
		for (int i = 0; i < inputs.length; i++)
		{
			inputs[i].SetValue(i);
			control.SetValue(i);
			for (int j = 0; j < output.getSize(); j++)
				if (inputs[i].getWireAt(j).getValue() != output.getWireAt(j).getValue())
					return false;
		}
		return true;
	}
}
