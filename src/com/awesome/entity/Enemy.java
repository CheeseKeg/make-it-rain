package com.awesome.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.awesome.map.Map;

public class Enemy extends Entity 
{
	protected enum eAIState
	{
		kAIState_Idle,
		kAIState_PursueTarget,
		kAIState_AttackTarget,
		kAIState_Flee,
	}
	
	protected Entity mTargetEntity = null;
	protected eAIState mCurrentState = eAIState.kAIState_Idle;
	protected long mNextThinkTime = 0;
	protected static final long mThinkDelay = 50;
	
	public Enemy()
	{
		super();
		
		mScale = 5.0f;
		mWalkFriction = 0.80f;
	}
	
	protected void Think()
	{
		SetControlFlag(eControlFlag.kControlFlag_Right, true);
	}

	public void init(GameContainer gc) throws SlickException 
	{
		image = new Image("res/images/enemy.png");
		boundingBox.setBounds(boundingBox.getX(),
				boundingBox.getY(),
				Map.TILE_SIZE,
				Map.TILE_SIZE);
	}

	public void update(GameContainer gc, int delta) throws SlickException
	{
		long currentTime = gc.getTime();
		if (currentTime >= mNextThinkTime)
		{
			mNextThinkTime = currentTime + mThinkDelay;
			Think();
		}
		super.update(gc, delta);
	}
}
