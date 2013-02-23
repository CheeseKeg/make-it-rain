package com.awesome.makeitrain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Player extends Entity {
	
	public Player() {
		super();
	}

	public void init(GameContainer gc) throws SlickException {
		image = new Image("images/player.png");
		boundingBox = new Rectangle(
				boundingBox.getX(), boundingBox.getY(),
				image.getWidth(), image.getHeight());
	}
}
