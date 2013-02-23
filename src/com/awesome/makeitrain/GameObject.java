package com.awesome.makeitrain;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public abstract class GameObject {
	
	private static int ID_COUNTER = 0;
	
	public static final Vector2f GRAVITY = new Vector2f(0f, -4f);
	protected Vector2f acceleration;
	protected Rectangle boundingBox;
	
	protected Image image;
	
	protected int ID;
	
	public GameObject() {
		ID = ID_COUNTER;
		ID_COUNTER ++;
		
		acceleration = new Vector2f(0f, 0f);
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public Vector2f getPosition() {
		return new Vector2f(boundingBox.getWidth(), boundingBox.getHeight());
	}
	
	public void setPosition(Vector2f position) {
		boundingBox.setLocation(position.x, position.y);
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
}