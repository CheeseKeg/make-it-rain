package com.awesome.makeitrain;

import org.newdawn.slick.geom.Vector2f;

public abstract class GameObject implements Drawable {
	
	private Vector2f position;

	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
}
