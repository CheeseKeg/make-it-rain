package com.awesome.makeitrain;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Map 
{
	private static final int TILE_SIZE = 32;
	private ArrayList<MapObject> mMapObjects;
	
	public ArrayList<MapObject> getMapObjects()
	{
		return mMapObjects;
	}
	
	private Map(ArrayList<MapObject> inMapObjects)
	{
		mMapObjects = inMapObjects;
	}
	
	private static MapObject ParseColor(Color inColor)
	{
		return null;
	}
	
	public static Map LoadFromFile(String inFilename)
	{
		try 
		{
			Image mapImage = new Image(inFilename);
			int mapWidth = mapImage.getWidth();
			int mapHeight = mapImage.getHeight();
			ArrayList<MapObject> mapObjects = new ArrayList<MapObject>();
			for (int y = 0; y < mapHeight; y++)
			{
				for (int x = 0; x < mapWidth; x++)
				{
					Color pixelColor = mapImage.getColor(x, y);
					MapObject mapObject = ParseColor(pixelColor);
					if (mapObject != null)
					{
						mapObject.setPosition(new Vector2f(x*TILE_SIZE,y*TILE_SIZE));
						mapObjects.add(mapObject);
					}
				}
			}
			
			return new Map(mapObjects);
			
		}
		catch (SlickException e) 
		{
			System.out.println("Failed to Load Map From File!");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
