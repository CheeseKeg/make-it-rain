package com.awesome.map.tiles;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class TurfMapTile extends MapTile 
{
	protected boolean mHasCollision;
	protected String mTextureName;
	
	public boolean getHasCollision() { return mHasCollision; }
	public String getTextureName() { return mTextureName; }
	
	public TurfMapTile(String inType, String inName, boolean inHasCollision, String inTextureName)
	{
		mType = inType;
		mName = inName;
		mHasCollision = inHasCollision;
		mTextureName = inTextureName;
	}
	
	public void init(GameContainer gc) throws SlickException
	{
		setImage(new Image(new File("res/images/", mTextureName).toString()));
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		Vector2f pos = getPosition();
		image.draw(pos.x, pos.y);
	}

	public void update(GameContainer gc, int delta) throws SlickException
	{
		
	}
}
