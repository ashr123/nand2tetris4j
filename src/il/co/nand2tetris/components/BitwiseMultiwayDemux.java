package il.co.nand2tetris.components;

public class BitwiseMultiwayDemux extends Gate
{
	private WireSet input, control;
	private WireSet[] outputs;

	public BitwiseMultiwayDemux(int iSize, int cControlBits)
	{
		outputs = new WireSet[1 << cControlBits]; // 2^cControlBits
		input = new WireSet(iSize);
		control = new WireSet(cControlBits);
		for (int i = 0; i < outputs.length; i++)
			outputs[i] = new WireSet(iSize);

		//your code here
		BitwiseDemux[] bitwiseDemux = new BitwiseDemux[outputs.length - 1];
		for (int i = 0; i < bitwiseDemux.length; i++)
			bitwiseDemux[i] = new BitwiseDemux(iSize);

		for (int i = 0, count = bitwiseDemux.length - 1; i < outputs.length; i += 2)
		{
			outputs[i].connectInput(bitwiseDemux[count].getOutput1());
			outputs[i + 1].connectInput(bitwiseDemux[count].getOutput2());
			bitwiseDemux[count--].connectControl(control.getWireAt(0));
		}

		for (int i = bitwiseDemux.length; i > 2; i -= 2)
		{
			bitwiseDemux[i - 1].connectInput(bitwiseDemux[(i / 2) - 1].getOutput1());
			bitwiseDemux[i - 2].connectInput(bitwiseDemux[(i / 2) - 1].getOutput2());
			bitwiseDemux[(i / 2) - 1].connectControl(control.getWireAt(control.getSize() - 1 - log2(i / 2)));
		}
	}

	private static int log2(int x)
	{
		return (int) (Math.log(x) / Math.log(2) + 1e-10);
	}

	public void connectInput(WireSet wsInput)
	{
		input.connectInput(wsInput);
	}

	public void connectControl(WireSet wsControl)
	{
		control.connectInput(wsControl);
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
