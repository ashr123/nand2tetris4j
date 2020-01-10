package il.co.nand2tetris;

import il.co.nand2tetris.components.*;

public class Main
{

	public static void main(String[] args)
	{
		// write your code here
		final byte size = 30;
		System.out.println("Building...");
		NAndGate nAndGate = new NAndGate();
		NotGate not = new NotGate();
		AndGate and = new AndGate();
		OrGate or = new OrGate();//1.1
		XorGate xOr = new XorGate();
		BitwiseAndGate bitwiseAnd = new BitwiseAndGate(size);
		BitwiseOrGate bitwiseOr = new BitwiseOrGate(size);
		BitwiseNotGate bitwiseNot = new BitwiseNotGate(size);
		MultiBitAndGate multiBitAnd = new MultiBitAndGate(size);
		MultiBitOrGate multiBitOr = new MultiBitOrGate(size);
		MuxGate mux = new MuxGate();//1.2
		Demux demux = new Demux();
		BitwiseMux bitwiseMux = new BitwiseMux(size);
		BitwiseDemux bitwiseDemux = new BitwiseDemux(size);
		BitwiseMultiwayMux bitwiseMultiwayMux = new BitwiseMultiwayMux(size, 8);
		BitwiseMultiwayDemux bitwiseMultiwayDemux = new BitwiseMultiwayDemux(size, 8);
		HalfAdder halfAdder = new HalfAdder();
		FullAdder fullAdder = new FullAdder();
		WireSet wireSet = new WireSet(size);
		MultiBitAdder multiBitAdder = new MultiBitAdder(4);//1.3
		ALU alu = new ALU(4);
//		SingleBitRegister singleBitRegister = new SingleBitRegister();
//		MultiBitRegister multiBitRegister = new MultiBitRegister(size);
//		Memory memory = new Memory(4, 4);

		//wireSet.SetValue(5);
		//int a = wireSet.GetValue();
		wireSet.Set2sComplement(-5);
		int b = wireSet.Get2sComplement();
		System.out.println("Testing...");
		if (!nAndGate.TestGate() ||
				!not.TestGate() ||
				!and.TestGate() ||
				!or.TestGate() ||
				!xOr.TestGate() ||
				!bitwiseAnd.TestGate() ||
				!bitwiseOr.TestGate() ||
				!bitwiseNot.TestGate() ||
				!multiBitAnd.TestGate() ||
				!multiBitOr.TestGate() ||
				!mux.TestGate() ||
				!demux.TestGate() ||
				!bitwiseMux.TestGate() ||
				!bitwiseDemux.TestGate() ||
				!bitwiseMultiwayMux.TestGate() ||
				!bitwiseMultiwayDemux.TestGate() ||
				!halfAdder.TestGate() ||
				!fullAdder.TestGate() ||
				!multiBitAdder.TestGate() /*||
				!alu.TestGate()*/ /*||
				!singleBitRegister.TestGate() ||
				!multiBitRegister.TestGate() ||
				!memory.TestGate()*/)
			System.out.println("bugbug");
		System.out.println("done");
		System.out.println("Nand Gates: " + NAndGate.getNandGates() + '\n' +
				"Nand Gates computed: " + NAndGate.getNandCompute() + '\n' +
				"Not Gates: " + NotGate.getNotGates() + '\n' +
				"Not Gates computed: " + NotGate.getNotComputed());
	}
}
