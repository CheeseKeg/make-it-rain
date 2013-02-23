package com.awesome.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.awesome.makeitrain.Drawable;
import com.awesome.makeitrain.GameObject;
import com.awesome.map.tiles.MapTile;
import com.awesome.map.tiles.TurfMapTile;

public abstract class Entity extends GameObject implements Drawable {
	
	protected enum eControlFlag
	{
		kControlFlag_Jump,
		kControlFlag_Left,
		kControlFlag_Right,
		kControlFlag_Attack,
		
		kControlFlag_Count
	}
	
	protected boolean mControlFlags[] = new boolean[eControlFlag.kControlFlag_Count.ordinal()];
	
	public static final Vector2f GRAVITY = new Vector2f(0f, 0f);
	
	protected float mJumpVelocity = -5.0f;
	protected float mMoveVelocity = 3.0f;
	protected float mWalkFriction = 0.80f;
	protected float mScale = 1.0f;
	
	public Entity() {
		super();
	}
	
	protected void SetControlFlag(eControlFlag inControlFlag, boolean inValue)
	{
		mControlFlags[inControlFlag.ordinal()] = inValue;
	}
	
	protected boolean GetControlFlag(eControlFlag inControlFlag)
	{
		return mControlFlags[inControlFlag.ordinal()];
	}
	
	private void ApplyControlFlags()
	{
		//Apply controls
		
		//Jump
		if (GetControlFlag(eControlFlag.kControlFlag_Jump))
		{
			acceleration.y = mJumpVelocity;
		}
		//Left
		if (GetControlFlag(eControlFlag.kControlFlag_Left))
		{
			acceleration.x -= mMoveVelocity;
		}
		//Right
		if (GetControlFlag(eControlFlag.kControlFlag_Right))
		{
			acceleration.x += mMoveVelocity;
		}
		//Attack
		if (GetControlFlag(eControlFlag.kControlFlag_Attack))
		{
			
		}
		
		//Reset the flags
		for (int controlFlagIndex = 0; controlFlagIndex < eControlFlag.kControlFlag_Count.ordinal(); controlFlagIndex++)
		{
			mControlFlags[controlFlagIndex] = false;
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		image.draw(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth() * mScale, boundingBox.getHeight() * mScale);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		ApplyControlFlags();
		acceleration.add(GRAVITY);
		boundingBox.setLocation(
				boundingBox.getX() + acceleration.x,
				boundingBox.getY() + acceleration.y);
		acceleration.x *= mWalkFriction;
	}
	
	public void checkCollision(MapTile tile) {
		if (this.boundingBox.intersects(tile.getBoundingBox())) {
			if (tile.getType() == "turf") {
				TurfMapTile turfHandle = (TurfMapTile)tile;
				if (turfHandle.getHasCollision()) {
					// Collision stuff
				}
			}
		}
	}
}
