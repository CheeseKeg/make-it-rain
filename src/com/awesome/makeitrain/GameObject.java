package com.awesome.makeitrain;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public abstract class GameObject {
	
	private static int ID_COUNTER = 0;
	
	protected Vector2f velocity = new Vector2f(0.0f, 0.0f);
	protected Rectangle boundingBox = new Rectangle(0, 0, 0, 0);
	
	protected Image image;
	
	protected int ID;
	
	public GameObject() {
		ID = ID_COUNTER;
		ID_COUNTER ++;
		
		velocity = new Vector2f(0f, 0f);
	}
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public Vector2f getPosition() {
		return new Vector2f(boundingBox.getX(), boundingBox.getY());
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
