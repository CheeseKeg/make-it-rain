package com.awesome.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
	
	protected enum eDirection
	{
		kDirection_Left,
		kDirection_Right
	}
	
	protected boolean mControlFlags[] = new boolean[eControlFlag.kControlFlag_Count.ordinal()];
	protected eDirection mDirection = eDirection.kDirection_Right;
	
	public static final Vector2f GRAVITY = new Vector2f(0f, 1f);
	
	protected boolean mOnGround = false;
	protected float mJumpVelocity = -10.0f;
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
		if (GetControlFlag(eControlFlag.kControlFlag_Jump) && mOnGround)
		{
			velocity.y = mJumpVelocity;
			mOnGround = false;
		}
		//Left
		if (GetControlFlag(eControlFlag.kControlFlag_Left))
		{
			velocity.x -= mMoveVelocity;
			mDirection = eDirection.kDirection_Left;
		}
		//Right
		if (GetControlFlag(eControlFlag.kControlFlag_Right))
		{
			velocity.x += mMoveVelocity;
			mDirection = eDirection.kDirection_Right;
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
	
	public eDirection getDirection()
	{
		return mDirection;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		image.draw(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth() * mScale, boundingBox.getHeight() * mScale);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		ApplyControlFlags();
		velocity.add(GRAVITY);
		boundingBox.setLocation(
				boundingBox.getX() + velocity.x,
				boundingBox.getY() + velocity.y);
		velocity.x *= mWalkFriction;
	}
	
	public void checkCollision(MapTile tile) {
		//Small collision optimization Dont check tiles that are too far away to touch us
		if(this.getPosition().distance(tile.getPosition()) > 64.0f)
				return;
		if (this.boundingBox.intersects(tile.getBoundingBox())) {
			if (tile.getType().equals("turf")) {
				TurfMapTile turfHandle = (TurfMapTile)tile;
				if (turfHandle.getHasCollision()) {
					// Do collision push
					Rectangle BB = this.boundingBox, tileBB = tile.getBoundingBox();
					
					//float tDist, bDist, lDist, rDist;
					//tDist = bDist = lDist = rDist = 0;
					
					// Top
					if (BB.getMaxY() > tileBB.getMinY() && BB.getMaxY() < tileBB.getMaxY()) {
						BB.setY(BB.getY() - (BB.getMaxY() - tileBB.getMinY()));
						//lDist = (BB.getMaxY() - tileBB.getMinY());
						velocity.y = 0;
						mOnGround = true;
					}
					
					if (this.boundingBox.intersects(tile.getBoundingBox())) {
						// Left
						if (BB.getMaxX() > tileBB.getMinX() && BB.getMinX() < tileBB.getMinX()) {
							BB.setX(BB.getX() - (BB.getMaxX() - tileBB.getMinX()));
							//lDist = (BB.getMaxX() - tileBB.getMinX());
							velocity.x = 0;
						} else
						// Right
						if (BB.getMinX() < tileBB.getMaxX() && BB.getMaxX() > tileBB.getMaxX()) {
							BB.setX(BB.getX() + (tileBB.getMaxX() - BB.getMinX()));
							//rDist = (tileBB.getMaxX() - BB.getMinX());
							velocity.x = 0;
						}
					}
					/* else
					}
					// Bottom
					if (BB.getMinY() < tileBB.getMaxY() && BB.getMinY() > tileBB.getMinY()) {
						BB.setY(BB.getY() + (tileBB.getMaxY() - BB.getMinY()));
						//bDist = (tileBB.getMaxY() - BB.getMinY());
						velocity.y = 0;
						mOnGround = true;
					} else
						
					// Left
					if (BB.getMaxX() > tileBB.getMinX() && BB.getMinX() < tileBB.getMinX()) {
						BB.setX(BB.getX() - (BB.getMaxX() - tileBB.getMinX()));
						//lDist = (BB.getMaxX() - tileBB.getMinX());
						velocity.x = 0;
					} else
					// Right
					if (BB.getMinX() < tileBB.getMaxX() && BB.getMaxX() > tileBB.getMaxX()) {
						BB.setX(BB.getX() + (tileBB.getMaxX() - BB.getMinX()));
						//rDist = (tileBB.getMaxX() - BB.getMinX());
						velocity.x = 0;
					}*/
				}
			}
		}
	}
	
	private float min(float a, float b) {
		return a > b ? b : a;
	}
	
	private float max(float a, float b) {
		return a > b ? a : b;
	}
}
