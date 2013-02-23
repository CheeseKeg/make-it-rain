package com.awesome.map.tiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.awesome.makeitrain.Drawable;
import com.awesome.makeitrain.GameObject;
import com.awesome.map.Map;

public abstract class MapTile extends GameObject implements Drawable 
{
	protected String mName;
	protected String mType;
	
	public String getName() { return mName; }
	public String getType() { return mType; }
	
	public MapTile() 
	{
		super();
		
		mName = "Undefined Tile Name";
		mType = "Undefined Tile Type";
		
		boundingBox = new Rectangle(0, 0, Map.TILE_SIZE, Map.TILE_SIZE);
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
	}

	public void update(GameContainer gc, int delta) throws SlickException
	{
		
	}
}
