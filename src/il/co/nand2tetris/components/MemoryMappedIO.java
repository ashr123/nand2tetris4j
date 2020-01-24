package il.co.nand2tetris.components;

public abstract class MemoryMappedIO extends SequentialGate
{
	protected int[] m_aiMemoryMap;
	protected int m_iOffset;
}
