package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelSet {
	private Map<String, Channel> channels = new HashMap<>();
	private Map<String, List<String>> pendingBindings = new HashMap<>();
	
	public ChannelSet() {}
	
	public Channel makeChannel(String name, Channel.Type type) {
		return makeChannel(name,null,type);
	}
	
	public synchronized Channel makeChannel(String name, String[] sources, Channel.Type type) {
		Channel newChannel = new Channel(this,type);
		channels.put(name, newChannel);
		
		Channel sourceChannel;
		
		if(pendingBindings.get(name) != null) {
			for(String binding: pendingBindings.get(name)) {
				newChannel.addDependent(binding);
			}
			pendingBindings.get(name).clear();
		}
		
		if(sources == null)
			return newChannel;
		
		for(String sourceName: sources) {
			newChannel.addSource(sourceName);
			
			sourceChannel = channels.get(sourceName);
			
			if(sourceChannel != null) {
				sourceChannel.addDependent(name);
			} else {
				if(pendingBindings.get(sourceName) == null) {
					List<String> temp = new ArrayList<>();
					temp.add(name);
					pendingBindings.put(sourceName, temp);
				} else {
					pendingBindings.get(sourceName).add(name);
				}
			}
		}
		
		
		
		return newChannel;
	}
	
	public Channel get(String name) {
		return channels.get(name);
	}
}