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
	public static final float FREEFALL_LIMIT = 9f;
	
	protected boolean mOnGround = false;
	protected float mJumpVelocity = -12.0f;
	protected float mMoveVelocity = 3.0f;
	protected float mGroundFriction = 0.80f;
	protected float mAirFriction = 0.85f;
	protected float mScale = 1.0f;
	protected float mBounciness = 0.0f;
	
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
		
		if (velocity.getY() > FREEFALL_LIMIT)
			velocity.set(velocity.getX(), FREEFALL_LIMIT);
		
		boundingBox.setLocation(
				boundingBox.getX() + velocity.x,
				boundingBox.getY() + velocity.y);
		
		if (mOnGround)
		{
			velocity.x *= mGroundFriction;
		}
		else
		{
			velocity.x *= (mAirFriction);
		}
	}
	
	public void collide(Entity otherEntity)
	{
		if (!(this instanceof Gib) && (otherEntity instanceof Gib))
		{
			EntityManager.getInstance().RemoveEntity(otherEntity);
			mScale += 0.001f;
		}
		else if (!(this instanceof Gib) && !(otherEntity instanceof Gib))
		{
			mScale -= 0.001f;
		}
	}
	
	public void checkCollision(Entity otherEntity) 
	{
		if (this != otherEntity && (this.getPosition().distance(otherEntity.getPosition()) <= 64.0f)
				&& boundingBox.intersects(otherEntity.getBoundingBox()))
				{
					this.collide(otherEntity);
					otherEntity.collide(this);
				}
	}
	
	public void checkCollision(MapTile tile) {
		//Small collision optimization; dont check tiles that are too far away to touch us
		if ((this.getPosition().distance(tile.getPosition()) <= 64.0f)
				&& boundingBox.intersects(tile.getBoundingBox())
				&& tile.getType().equals("turf")
				&& ((TurfMapTile)tile).getHasCollision()) {
			// Do collision push
			Rectangle BB = boundingBox, tileBB = tile.getBoundingBox();
			float left = BB.getX(), right = left + BB.getWidth(),
					top = BB.getY(), bottom = top + BB.getHeight();
			final int BULK = 10;
			
			// Left & right
			{
				Rectangle leftSensor = new Rectangle(left, top + BULK, BULK, BB.getHeight() - 2*BULK);
				Rectangle rightSensor = new Rectangle(right - BULK, top + BULK, BULK, BB.getHeight() - 2*BULK);
				if (leftSensor.intersects(tileBB)) {
					velocity.x = 0;
					BB.setX(tileBB.getX() + tileBB.getWidth());
				} else if (rightSensor.intersects(tileBB)) {
					velocity.x = 0;
					BB.setX(tileBB.getX() - BB.getWidth());
				}
			}
			
			left = BB.getX(); right = left + BB.getWidth();
			// Bottom & top
			{
				Rectangle bottomSensor = new Rectangle(left + BULK, bottom - BULK, BB.getWidth() - 2*BULK, BULK);
				Rectangle topSensor = new Rectangle(left + BULK, top, BB.getWidth() - 2*BULK, BULK);
				if (bottomSensor.intersects(tileBB)) {
					if (!mOnGround)
					{
						velocity.y = -( Math.abs(velocity.y+velocity.x) / 2.0f ) * mBounciness;
					}
					else
					{
						velocity.y = 0.0f;
					}
					mOnGround = true;
					BB.setY(tileBB.getY() - BB.getHeight());
				} else if (topSensor.intersects(tileBB)) {
					velocity.y = 0;
					BB.setY(tileBB.getY() + tileBB.getHeight());
				}
			}
		}
	}
}
