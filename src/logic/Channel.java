package logic;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	public enum Type {
		OR,AND,VALUE,PERMANENT;
		public static Type fromString(String s) {
			switch(s.toUpperCase()) {
			case "OR":
				return OR;
			case "AND":
				return AND;
			case "PERMANENT":
				return PERMANENT;
			case "VALUE":
			default:
				return VALUE;
			}
		}
	}
	
	private ChannelSet channels;
	private List<String> sources = new ArrayList<>();
	private List<String> dependents = new ArrayList<>();
	private Type type;
	private LogicListener listener = null;
	
	private boolean output = false;
	
	public Channel(ChannelSet channels, String name, Type type) {
		this.type = type;
		this.channels = channels;
	}
	
	public boolean getOutput() {
		return output;
	}
	
	public void update() {
		update(false);
	}
	
	public void update(boolean signal) {
		boolean out;
		
		switch(type) {
		case OR:
			out = false;
			for(String name : sources) {
				out = out || channels.get(name).getOutput();
			}
			setOutput(out);
			break;
		case AND:
			out = true;
			for(String name : sources) {
				out = out && channels.get(name).getOutput();
			}
			setOutput(out);
			break;
		case VALUE:
			setOutput(signal);
			break;
		case PERMANENT:
			if(signal) {
				setOutput(true);
			}
			break;
		}
	}
	
	private void setOutput(boolean out) {
		if(output != out) {
			output = out;
			updateDependents();
			if(listener != null) {
				listener.notify(output);
			}
		}
	}
	
	public void addDependent(String name) {
		dependents.add(name);
	}
	
	public void addSource(String name) {
		sources.add(name);
	}
	
	public void setListener(LogicListener listener) {
		this.listener = listener;
	}
	
	private void updateDependents() {
		for(String name : dependents) {
			channels.get(name).update();
		}
	}
}