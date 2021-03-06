package com.awesome.entity;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.awesome.map.Map;

public class Gib extends Entity 
{
	protected String mGibName = "gib";
	protected static final int GIB_IMAGE_COUNT = 3;
	protected float mRotation = 0.0f;
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		Random rand = new Random();
		image = new Image(String.format("res/images/gibs/%s_%d.png", mGibName, rand.nextInt(GIB_IMAGE_COUNT)));
		boundingBox.setBounds(boundingBox.getX(),
				boundingBox.getY(),
				Map.TILE_SIZE,
				Map.TILE_SIZE);
		mGroundFriction = 0.96f;
		mBounciness = 0.92f;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		long currentTime = gc.getTime();
		
		if (!mOnGround)
		{
			image.rotate((currentTime / 1000.0f) / 360.0f);
		}
		
		super.update(gc, delta);
	}

}
