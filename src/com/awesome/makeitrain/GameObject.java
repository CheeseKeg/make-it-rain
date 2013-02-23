package com.awesome.makeitrain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class GameObject implements Drawable {
	private Vector2f position;

	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}

}
