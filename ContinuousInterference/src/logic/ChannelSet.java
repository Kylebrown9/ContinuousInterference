package logic;

import java.util.HashMap;
import java.util.Map;

public class ChannelSet {
	private Map<String, Channel> channels = new HashMap<>();
	
	public ChannelSet() {}
	
	public Channel makeChannel(String name, Channel.Type type) {
		Channel newChannel = new Channel(this,name,type);
		channels.put(name, newChannel);
		return newChannel;
	}
	
	public Channel get(String name) {
		return channels.get(name);
	}
}