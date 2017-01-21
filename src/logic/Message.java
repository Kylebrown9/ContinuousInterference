package logic;

public class Message {
	private Channel channel;
	private boolean signal;
	
	public Message(Channel channel, boolean signal) {
		this.channel = channel;
		this.signal = signal;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public boolean getSignal() {
		return signal;
	}
}
