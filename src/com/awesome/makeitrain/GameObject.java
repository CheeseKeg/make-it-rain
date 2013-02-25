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
		boundingBox.setLocation(position);
	}
	
	public Vector2f getCenter() {
		return new Vector2f(
				boundingBox.getX() + boundingBox.getWidth()/2f,
				boundingBox.getY() + boundingBox.getHeight()/2f);
	}
	
	public void setCenter(Vector2f center) {
		boundingBox.setLocation(
				center.x - boundingBox.getWidth()/2f,
				center.y - boundingBox.getHeight()/2f);
	}
	
	public Vector2f getSize() {
		return new Vector2f(boundingBox.getWidth(), boundingBox.getHeight());
	}
	
	public void setSize(Vector2f size) {
		boundingBox.setSize(size.x, size.y);
	}
	
	public void setVelocity(Vector2f velocity)
	{
		this.velocity = velocity;
	}
	
	public Vector2f getVelocity()
	{
		return this.velocity;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
}
