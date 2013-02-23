package com.awesome.makeitrain;

public abstract class TileDefinition 
{
	protected String mName;
	protected String mType;
	
	public String getName() { return mName; }
	public String getType() { return mType; }
	
	protected TileDefinition()
	{
		mName = "Undefined Tile Name";
		mType = "Undefined Tile Type";
	}
}
