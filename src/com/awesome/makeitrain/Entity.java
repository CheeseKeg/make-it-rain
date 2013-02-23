package com.awesome.makeitrain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Entity extends GameObject implements Drawable {
	public Entity() {
		super();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		image.draw(boundingBox.getX(), boundingBox.getY());
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		acceleration.add(GRAVITY);
		boundingBox.setLocation(
				boundingBox.getX() + acceleration.x,
				boundingBox.getY() + acceleration.y);
	}
}
