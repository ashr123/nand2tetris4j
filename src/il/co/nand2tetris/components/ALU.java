package il.co.nand2tetris.components;

public class ALU extends Gate
{
	private final WireSet inputX, inputY, output;
	private final Wire
			zeroX = new Wire(),
			zeroY = new Wire(),
			notX = new Wire(),
			notY = new Wire(),
			f = new Wire(),
			notOutput = new Wire(),
			zero = new Wire(),
			negative = new Wire();

	public ALU(final int iSize)
	{
		inputX = new WireSet(iSize);
		inputY = new WireSet(iSize);
		output = new WireSet(iSize);

		final BitwiseMux
				zxMux = new BitwiseMux(iSize),
				zyMux = new BitwiseMux(iSize),
				nxMux = new BitwiseMux(iSize),
				nyMux = new BitwiseMux(iSize),
				fMux = new BitwiseMux(iSize),
				noMux = new BitwiseMux(iSize);
		final BitwiseNotGate
				nx = new BitwiseNotGate(iSize),
				ny = new BitwiseNotGate(iSize),
				no = new BitwiseNotGate(iSize);
		final BitwiseAndGate andGate = new BitwiseAndGate(iSize);
		final MultiBitAdder adder = new MultiBitAdder(iSize);
		final MultiBitOrGate multiBitOr = new MultiBitOrGate(iSize);
		final NotGate not = new NotGate();

		zxMux.connectInput1(inputX);
		zxMux.connectControl(zeroX);

		zyMux.connectInput1(inputY);
		zyMux.connectControl(zeroY);
		//zxMux, zyMux input2's wires remains at 0

		nx.connectInput(zxMux.getOutput());
		nxMux.connectInput1(zxMux.getOutput());
		nxMux.connectInput2(nx.getOutput());
		nxMux.connectControl(notX);

		ny.connectInput(zyMux.getOutput());
		nyMux.connectInput1(zyMux.getOutput());
		nyMux.connectInput2(ny.getOutput());
		nyMux.connectControl(notY);

		andGate.connectInput1(nxMux.getOutput());
		andGate.connectInput2(nyMux.getOutput());

		adder.connectInput1(nxMux.getOutput());
		adder.connectInput2(nyMux.getOutput());

		fMux.connectInput1(andGate.getOutput());
		fMux.connectInput2(adder.getOutput());
		fMux.connectControl(f);

		no.connectInput(fMux.getOutput());
		noMux.connectInput1(fMux.getOutput());
		noMux.connectInput2(no.getOutput());
		noMux.connectControl(notOutput);

		output.connectInput(noMux.getOutput());//V

		negative.connectInput(output.getWireAt(iSize - 1));//V

		multiBitOr.connectInput(output);
		not.connectInput(multiBitOr.getOutput());
		zero.connectInput(not.getOutput());//V
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public boolean testGate()
	{
		//throw new NotImplementedException();
		int numX = 5, numY = 6;
		inputX.set2sComplement(numX);
		inputY.set2sComplement(numY);
		final WireSet result = new WireSet(output.size());
		final BitwiseAndGate bitwiseAnd = new BitwiseAndGate(inputX.size());
		final BitwiseOrGate bitwiseOr = new BitwiseOrGate(inputX.size());
		final BitwiseNotGate bitwiseNot = new BitwiseNotGate(inputX.size());
		bitwiseAnd.connectInput1(inputX);
		bitwiseAnd.connectInput2(inputY);
		bitwiseOr.connectInput1(inputX);
		bitwiseOr.connectInput2(inputY);

		zeroX.setValue(1);
		notX.setValue(0);
		zeroY.setValue(1);
		notY.setValue(0);
		f.setValue(1);
		notOutput.setValue(0);
		if (output.get2sComplement() != 0 || negative.getValue() != 0 || zero.getValue() != 1)//0
			return false;

		zeroX.setValue(1);
		notX.setValue(1);
		zeroY.setValue(1);
		notY.setValue(1);
		f.setValue(1);
		notOutput.setValue(1);
		if (output.get2sComplement() != 1 || negative.getValue() != 0 || zero.getValue() != 0)//1
			return false;

		zeroX.setValue(1);
		notX.setValue(1);
		zeroY.setValue(1);
		notY.setValue(0);
		f.setValue(1);
		notOutput.setValue(0);
		if (output.get2sComplement() != -1 || negative.getValue() != 1 || zero.getValue() != 0)//-1
			return false;

		zeroX.setValue(0);
		notX.setValue(0);
		zeroY.setValue(1);
		notY.setValue(1);
		f.setValue(0);
		notOutput.setValue(0);
		if (output.get2sComplement() != inputX.get2sComplement() || negative.getValue() != inputX.getWireAt(inputX.size() - 1).getValue() || zero.getValue() != (numX == 0 ? 1 : 0))//x
			return false;

		zeroX.setValue(1);
		notX.setValue(1);
		zeroY.setValue(0);
		notY.setValue(0);
		f.setValue(0);
		notOutput.setValue(0);
		if (output.get2sComplement() != inputY.get2sComplement() || negative.getValue() != inputY.getWireAt(inputY.size() - 1).getValue() || zero.getValue() != (numY == 0 ? 1 : 0))//y
			return false;

		zeroX.setValue(0);
		notX.setValue(0);
		zeroY.setValue(1);
		notY.setValue(1);
		f.setValue(0);
		notOutput.setValue(1);
		bitwiseNot.getInput().set2sComplement(numX);
		if (output.get2sComplement() != bitwiseNot.getOutput().get2sComplement() || negative.getValue() != 1 - inputX.getWireAt(inputX.size() - 1).getValue() || zero.getValue() != TestZero())//!x
			return false;

		zeroX.setValue(1);
		notX.setValue(1);
		zeroY.setValue(0);
		notY.setValue(0);
		f.setValue(0);
		notOutput.setValue(1);
		bitwiseNot.getInput().set2sComplement(numY);
		if (output.get2sComplement() != bitwiseNot.getOutput().get2sComplement() || negative.getValue() != 1 - inputY.getWireAt(inputY.size() - 1).getValue() || zero.getValue() != TestZero())//!y
			return false;

		zeroX.setValue(0);
		notX.setValue(0);
		zeroY.setValue(1);
		notY.setValue(1);
		f.setValue(1);
		notOutput.setValue(1);
		if (output.get2sComplement() != -inputX.get2sComplement() || negative.getValue() != 1 - inputX.getWireAt(inputX.size() - 1).getValue() || zero.getValue() != (numX == 0 ? 1 : 0))//-x
			return false;

		zeroX.setValue(1);
		notX.setValue(1);
		zeroY.setValue(0);
		notY.setValue(0);
		f.setValue(1);
		notOutput.setValue(1);
		if (output.get2sComplement() != -inputY.get2sComplement() || negative.getValue() != 1 - inputY.getWireAt(inputY.size() - 1).getValue() || zero.getValue() != (numY == 0 ? 1 : 0))//-y
			return false;

		zeroX.setValue(0);
		notX.setValue(1);
		zeroY.setValue(1);
		notY.setValue(1);
		f.setValue(1);
		notOutput.setValue(1);
		result.set2sComplement(numX + 1);
		if (output.get2sComplement() != inputX.get2sComplement() + 1 || negative.getValue() != result.getWireAt(result.size() - 1).getValue() || zero.getValue() != (numX + 1 == 0 ? 1 : 0))//x+1
			return false;

		zeroX.setValue(1);
		notX.setValue(1);
		zeroY.setValue(0);
		notY.setValue(1);
		f.setValue(1);
		notOutput.setValue(1);
		result.set2sComplement(numY + 1);
		if (output.get2sComplement() != inputY.get2sComplement() + 1 || negative.getValue() != result.getWireAt(result.size() - 1).getValue() || zero.getValue() != (numY + 1 == 0 ? 1 : 0))//y+1
			return false;

		zeroX.setValue(0);
		notX.setValue(0);
		zeroY.setValue(1);
		notY.setValue(1);
		f.setValue(1);
		notOutput.setValue(0);
		result.set2sComplement(numX - 1);
		if (output.get2sComplement() != inputX.get2sComplement() - 1 || negative.getValue() != result.getWireAt(result.size() - 1).getValue() || zero.getValue() != (numX - 1 == 0 ? 1 : 0))//x-1
			return false;

		zeroX.setValue(1);
		notX.setValue(1);
		zeroY.setValue(0);
		notY.setValue(0);
		f.setValue(1);
		notOutput.setValue(0);
		result.set2sComplement(numY - 1);
		if (output.get2sComplement() != inputY.get2sComplement() - 1 || negative.getValue() != result.getWireAt(result.size() - 1).getValue() || zero.getValue() != (numY - 1 == 0 ? 1 : 0))//y-1
			return false;

		zeroX.setValue(0);
		notX.setValue(0);
		zeroY.setValue(0);
		notY.setValue(0);
		f.setValue(1);
		notOutput.setValue(0);
		result.set2sComplement(numX + numY);
		if (output.getValue() != inputX.get2sComplement() + inputY.get2sComplement() || negative.getValue() != result.getWireAt(result.size() - 1).getValue() || zero.getValue() != (numX + numY == 0 ? 1 : 0))//x+y
			return false;

		zeroX.setValue(0);
		notX.setValue(1);
		zeroY.setValue(0);
		notY.setValue(0);
		f.setValue(1);
		notOutput.setValue(1);
		result.set2sComplement(numX - numY);
		if (output.get2sComplement() != inputX.get2sComplement() - inputY.get2sComplement() || negative.getValue() != result.getWireAt(result.size() - 1).getValue() || zero.getValue() != (numX - numY == 0 ? 1 : 0))//x-y
			return false;

		zeroX.setValue(0);
		notX.setValue(0);
		zeroY.setValue(0);
		notY.setValue(1);
		f.setValue(1);
		notOutput.setValue(1);
		result.set2sComplement(numY - numX);
		if (output.get2sComplement() != inputY.get2sComplement() - inputX.get2sComplement() || negative.getValue() != result.getWireAt(result.size() - 1).getValue() || zero.getValue() != (numY - numX == 0 ? 1 : 0))//y-x
			return false;

		//TODO fix!!!
		zeroX.setValue(0);
		notX.setValue(0);
		zeroY.setValue(0);
		notY.setValue(0);
		f.setValue(0);
		notOutput.setValue(0);
		if (output.get2sComplement() != bitwiseAnd.getOutput().get2sComplement() || negative.getValue() != bitwiseAnd.getOutput().getWireAt(bitwiseAnd.getOutput().size() - 1).getValue() || zero.getValue() != TestZero())//x&y
			return false;

		//TODO fix!!!
		zeroX.setValue(0);
		notX.setValue(1);
		zeroY.setValue(0);
		notY.setValue(1);
		f.setValue(0);
		notOutput.setValue(1);
		return output.get2sComplement() == bitwiseOr.getOutput().get2sComplement() && negative.getValue() == bitwiseOr.getOutput().getWireAt(bitwiseOr.getOutput().size() - 1).getValue() && zero.getValue() == TestZero();//x|y
	}

	private int TestZero()
	{
		for (int i = 0; i < output.size(); i++)
			if (output.getWireAt(i).getValue() == 1)
				return 0;
		return 1;
	}

	public WireSet getInputX()
	{
		return inputX;
	}

	public WireSet getInputY()
	{
		return inputY;
	}

	public WireSet getOutput()
	{
		return output;
	}

	public Wire getZeroX()
	{
		return zeroX;
	}

	public Wire getZeroY()
	{
		return zeroY;
	}

	public Wire getNotX()
	{
		return notX;
	}

	public Wire getNotY()
	{
		return notY;
	}

	public Wire getF()
	{
		return f;
	}

	public Wire getNotOutput()
	{
		return notOutput;
	}

	public Wire getZero()
	{
		return zero;
	}

	public Wire getNegative()
	{
		return negative;
	}

	public int size()
	{
		return inputX.size();
	}
}
