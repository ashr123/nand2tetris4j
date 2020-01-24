package il.co.nand2tetris.components;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Memory extends SequentialGate
{
	//	private final int addressSize, wordSize;
	private final WireSet input, output, address;
	private final Wire load = new Wire();
	private final int[] registers;

	public Memory(int iAddressSize, int iWordSize)
	{
		input = new WireSet(iWordSize);
		output = new WireSet(iWordSize);
		address = new WireSet(iAddressSize);

		//your code here
		BitwiseMultiwayDemux bitwiseMultiwayDemux = new BitwiseMultiwayDemux(iWordSize, iAddressSize);
		BitwiseMultiwayMux bitwiseMultiwayMux = new BitwiseMultiwayMux(iWordSize, iAddressSize);

		registers = new int[bitwiseMultiwayMux.getInputs().length];

		bitwiseMultiwayDemux.connectInput(input);
		bitwiseMultiwayDemux.connectControl(address);
		bitwiseMultiwayMux.connectControl(address);

		for (int i = 0; i < bitwiseMultiwayMux.getInputs().length; i++)
		{
			MultiBitRegister multiBitRegister = new MultiBitRegister(iWordSize);

			multiBitRegister.connectInput(bitwiseMultiwayDemux.getOutputs()[i]);
			multiBitRegister.getLoad().connectInput(load);

			bitwiseMultiwayMux.connectInput(i, multiBitRegister.getOutput());
		}

		output.connectInput(bitwiseMultiwayMux.getOutput());
	}

	public WireSet getInput()
	{
		return input;
	}

	public WireSet getOutput()
	{
		return output;
	}

	public WireSet getAddress()
	{
		return address;
	}

	public Wire getLoad()
	{
		return load;
	}

	public int[] getRegisters()
	{
		return registers;
	}

	public int getRegisterValueAt(int i)
	{
		return registers[i];
	}

	public void setRegisterValueAt(int i, int value)
	{
		//TODO what if value isn't 0 or 1?
		registers[i] = value;
	}

	public void connectInput(WireSet wsInput)
	{
		input.connectInput(wsInput);
	}

	public void connectAddress(WireSet wsAddress)
	{
		address.connectInput(wsAddress);
	}

//	from C#
//	public void registerMemoryMappedIO(MemoryMappedIO io, int iOffset, int cRegisters, out int[] aiRegisters)
//	{
//		//TODO - we can register the io here to avoid duplicate settings
//		aiRegisters = registers;
//	}

	public void loadFromFile(String sFileName) throws IOException
	{
		try (Scanner scanner = new Scanner(new File(sFileName)))
		{
			switch ()
			int iAddress = 0;
			while (scanner.hasNextLine())
			{
				String sLine = scanner.nextLine().trim();
				if (sLine.length() != input.size())
					throw new IOException("Every line must contain a single instruction with " + input.size() + " bits (line " + iAddress + ").");
				int iValue = 0;
				for (int i = 0; i < input.size(); i++)
					if (sLine.charAt(i) == '1')
						iValue = (iValue << 1) + 1;
					else if (sLine.charAt(i) == '0')
						iValue = iValue << 1; // iValue = iValue * 2
					else
						throw new IOException("Can only have 0 or 1 for bits (line " + iAddress + ").");
				registers[iAddress] = iValue;

				iAddress++;
			}
		}
	}

	@Override
	public void onClockUp()
	{

	}

	@Override
	public void onClockDown()
	{

	}

	@Override
	public boolean testGate()
	{
		load.setValue(1);
		for (int i = 0; i < 1 << address.size(); i++)
		{
			if (output.getValue() != 0)
				return false;
			address.setValue(i);
			input.setValue(3);
			Clock.clockDown();
			Clock.clockUp();
			if (output.getValue() != 3)
				return false;

			input.setValue(0);
			Clock.clockDown();
			Clock.clockUp();
		}
		return true;
	}
}
