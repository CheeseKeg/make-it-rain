package com.awesome.entity;

import java.util.ArrayList;

public class EntityManager 
{	
	private static EntityManager sInstance = null;
	
	private ArrayList<Entity> mEntities = null;
	private ArrayList<Entity> mAddEntityList = null;
	private ArrayList<Entity> mRemoveEntityList = null;
	
	public static EntityManager getInstance()
	{
		if (sInstance == null)
		{
			sInstance = new EntityManager();
		}
		
		return sInstance;
	}
	
	private EntityManager()
	{
		mEntities = new ArrayList<Entity>();
		mAddEntityList = new ArrayList<Entity>();
		mRemoveEntityList = new ArrayList<Entity>();
	}
	
	public ArrayList<Entity> getEntities()
	{
		return mEntities;
	}
	
	public void AddEntity(Entity inEntity)
	{
		mAddEntityList.add(inEntity);
	}
	
	public void RemoveEntity(Entity inEntity)
	{
		mRemoveEntityList.add(inEntity);
	}
	
	public void update()
	{
		for (Entity entity : mAddEntityList)
		{
			mEntities.add(entity);
		}
		mAddEntityList.clear();
		
		for (Entity entity : mRemoveEntityList)
		{
			mEntities.remove(entity);
		}
		mRemoveEntityList.clear();
	}
}
