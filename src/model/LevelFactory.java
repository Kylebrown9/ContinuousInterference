package model;

import java.awt.Rectangle;

import com.google.gson.Gson;

import logic.Channel;
import logic.ChannelSet;

public class LevelFactory {
	public static Level makeLevel(String jsonText) {
		Gson g = new Gson();
		LevelDescriptor lD = g.fromJson(jsonText, LevelDescriptor.class);
		
		Level level = new Level(lD.width,lD.height);
		
		for(SourceDesc s: lD.sources) {
			level.addSource(new Source(s.x,s.y,s.h,s.v));
		}
		
		for(TargetDesc t: lD.targets) {
			level.addTarget(new Target(level,t.name,t.x,t.y,t.targetVal,t.tolerance,t.isPermanent));
		}
		
		ChannelSet channels = level.getChannels();
		Channel channel;
		for(ChannelDesc c: lD.channels) {
			channel = channels.makeChannel(c.name, Channel.Type.fromString(c.type));
			for(String dependentId: c.dependentIds) {
				channel.addDependent(dependentId);
			}
		}
		
		for(ObstacleDesc o: lD.obsctacles) {
			Obstacle obstacle = new Obstacle(new Rectangle(o.x,o.y,o.width,o.height),o.permittivity);
			level.addObstacle(obstacle);
			if(!o.channel.equals("null")) {
				channels.get(o.channel).setListener(obstacle);	
			}
		}
		
		channels.get(lD.succesChannel).setListener(level);
		
		return level;
	}
	
	class LevelDescriptor {
		public SourceDesc[] sources;
		public TargetDesc[] targets;
		public ChannelDesc[] channels;
		public ObstacleDesc[] obsctacles;
		public String succesChannel;
		
		public int width,height;
	}
	
	class SourceDesc {
		public float x,y,h,v;
	}
	
	class TargetDesc {
		public float x,y,targetVal,tolerance;
		public String name;
		public boolean isPermanent;
	}
	
	class ChannelDesc {
		public String type,name;
		public String[] dependentIds;
	}
	
	class ObstacleDesc {
		public int x,y,width,height;
		public boolean permittivity;
		public String channel;
	}	
}
