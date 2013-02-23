package com.awesome.makeitrain;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public abstract class GameObject {
	
	private static int ID_COUNTER = 0;
	private static final Vector2f GRAVITY = new Vector2f(0f, 4f);
	
	protected Image image;
	
	protected int ID;
	protected Vector2f position;
	
	public GameObject() {
		ID = ID_COUNTER;
		
		ID_COUNTER ++;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
}
