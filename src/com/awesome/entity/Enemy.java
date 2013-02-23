package com.awesome.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.awesome.map.Map;

public class Enemy extends Entity 
{
	public Enemy()
	{
		super();
		
		mScale = 5.0f;
	}

	public void init(GameContainer gc) throws SlickException 
	{
		image = new Image("res/images/enemy.png");
		boundingBox.setBounds(
				boundingBox.getX(), boundingBox.getY(),
				Map.TILE_SIZE, Map.TILE_SIZE);
	}
}
