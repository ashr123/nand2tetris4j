package il.co.nand2tetris.components;

import java.util.LinkedList;
import java.util.List;

public class Clock
{
	private final static List<SequentialGate> m_lListeners = new LinkedList<>();
	private static boolean m_bUp = true;
//	private final static Clock m_cClock = new Clock();

	private Clock()
	{
	}

//	private void register(SequentialGate g)
//	{
//		m_lListeners.add(g);
//	}

	public static void RegisterSequentialGate(SequentialGate g)
	{
		m_lListeners.add(g);
//		m_cClock.register(g);
	}

//	private void up()
//	{
//		if (!m_bUp)
//		{
//			m_lListeners.forEach(SequentialGate::onClockUp);
//			m_bUp = true;
//		}
//	}
//
//	private void down()
//	{
//		if (m_bUp)
//		{
//			m_lListeners.forEach(SequentialGate::onClockDown);
//			m_bUp = false;
//		}
//	}

	public static void clockUp()
	{
		if (!m_bUp)
		{
			m_lListeners.forEach(SequentialGate::onClockUp);
			m_bUp = true;
		}
//		m_cClock.up();
	}

	public static void clockDown()
	{
		if (m_bUp)
		{
			m_lListeners.forEach(SequentialGate::onClockDown);
			m_bUp = false;
		}
//		m_cClock.down();
	}
}
