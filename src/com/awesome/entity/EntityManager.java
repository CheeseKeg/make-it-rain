package com.awesome.entity;

import java.util.ArrayList;

public class EntityManager 
{	
	private static EntityManager sInstance = null;
	
	private ArrayList<Entity> mEntities = null;
	
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
	}
	
	public ArrayList<Entity> getEntities()
	{
		return mEntities;
	}
	
	public void AddEntity(Entity inEntity)
	{
		mEntities.add(inEntity);
	}
	
	public void RemoveEntity(Entity inEntity)
	{
		mEntities.remove(inEntity);
	}
}
