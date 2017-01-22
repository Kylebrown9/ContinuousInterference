package unittests;

import logic.Channel;
import logic.ChannelSet;
import logic.LogicListener;

public class ChannelLinkTest extends Test {
	public ChannelLinkTest(String name) {
		super(name);
	}
	
	public boolean getOutcome() {
		return andTest() && orTest();
	}
	
	public boolean orTest() {
		ChannelSet channels = new ChannelSet();
		
		Channel cA = channels.makeChannel("A",Channel.Type.VALUE);
		Channel cB = channels.makeChannel("B",Channel.Type.VALUE);
		
		Channel cOr = channels.makeChannel("OR",new String[]{"A","B"},Channel.Type.OR);
		
		BoolStore p = new BoolStore();
		cOr.setListener(p);
		
		boolean out = true;
		
		cA.update(true);
		cB.update(true);
		out &= p.getSignalVal();
		System.out.println("OR: " + out);
		
		cA.update(false);
		out &= p.getSignalVal();
		
		cB.update(false);
		out &= !p.getSignalVal();
		
		System.out.println("OR: " + out);
		return out;
	}
	
	public boolean andTest() {
		ChannelSet channels = new ChannelSet();
		
		Channel cA = channels.makeChannel("A",Channel.Type.VALUE);
		Channel cB = channels.makeChannel("B",Channel.Type.VALUE);
		
		Channel cAnd = channels.makeChannel("AND",new String[]{"A","B"},Channel.Type.AND);
		
		BoolStore p = new BoolStore();
		cAnd.setListener(p);
		
		boolean out = true;
		
		cA.update(true);
		cB.update(true);
		
		out &= p.getSignalVal();
		
		cA.update(false);
		
		out &= !p.getSignalVal();
		
		return out;
	}
	
	class BoolStore implements LogicListener {
		private boolean signal;
		
		public void notify(boolean signal) {
			this.signal = signal;
		}
		
		public boolean getSignalVal() {
			return signal;
		}
	}
}
