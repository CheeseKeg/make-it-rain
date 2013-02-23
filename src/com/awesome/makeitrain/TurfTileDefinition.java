package com.awesome.makeitrain;

public class TurfTileDefinition extends TileDefinition 
{
	protected boolean mHasCollision;
	protected String mTextureName;
	
	public boolean getHasCollision() { return mHasCollision; }
	public String getTextureName() { return mTextureName; }
	
	public TurfTileDefinition(String inType, String inName, boolean inHasCollision, String inTextureName)
	{
		mType = inType;
		mName = inName;
		mHasCollision = inHasCollision;
		mTextureName = inTextureName;
	}
}
