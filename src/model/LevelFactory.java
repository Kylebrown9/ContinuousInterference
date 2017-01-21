package model;

import java.awt.Rectangle;

import logic.Channel;
import logic.ChannelSet;

public class LevelFactory {
	public static Level makeLevel(String jsonText) {
		Level level = new Level();
		//TODO: Implement level creation from json text
		
		LevelDescriptor lD = ;
		
		for(SourceDesc s: lD.sourceDescs) {
			level.addSource(new Source(s.x,s.y,s.h,s.v));
		}
		
		for(TargetDesc t: lD.targetDescs) {
			level.addTarget(new Target(level,t.name,t.x,t.y,t.targetVal,t.tolerance,t.isPermanent));
		}
		
		ChannelSet channels = level.getChannels();
		Channel channel;
		for(ChannelDesc c: lD.channelDescs) {
			channel = channels.makeChannel(c.name, Channel.Type.fromString(c.type));
		}
		
		for(ObstacleDesc o: lD.obsctacleDescs) {
			Obstacle obstacle = new Obstacle(new Rectangle(o.x,o.y,o.width,o.height),o.permittivity);
			level.getLevelMap().addObstacle(obstacle);
			if(!o.channel.equals("null")) {
				channels.get(o.channel).setListener(obstacle);	
			}
		}
		
		return level;
	}
	
	class LevelDescriptor {
		public SourceDesc[] sourceDescs;
		public TargetDesc[] targetDescs;
		public ChannelDesc[] channelDescs;
		public ObstacleDesc[] obsctacleDescs;
		public String succesChannel;
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
