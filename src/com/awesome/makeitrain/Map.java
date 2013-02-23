package com.awesome.makeitrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

public class Map 
{
	private static final int TILE_SIZE = 32;
	private static final String XML_TILE_ELEMENT = "Tile";
	private static final String XML_TILE_TYPE_ATTRIBUTE = "type";
	private static final String XML_TILE_COLOR_ATTRIBUTE = "color";
	private ArrayList<MapObject> mMapObjects;
	
	public ArrayList<MapObject> getMapObjects()
	{
		return mMapObjects;
	}
	
	private Map(ArrayList<MapObject> inMapObjects)
	{
		mMapObjects = inMapObjects;
	}
	
	private static MapObject GetMapObjectByType(String inType)
	{
		switch(inType)
		{
			case "grass":
			{
				return null;
			}
			default:
			{
				return null;
			}
		}
	}
	
	private static int ConvertFloatColorToByte(float inColor)
	{
		return (int)Math.round(inColor*255.0);
	}
	
	private static MapObject ParseColor(Color inColor, HashMap<String, String> inTileDefinitions)
	{
		String colorString = String.format("%d,%d,%d,%d",
				ConvertFloatColorToByte(inColor.r),
				ConvertFloatColorToByte(inColor.g),
				ConvertFloatColorToByte(inColor.b), 
				ConvertFloatColorToByte(inColor.a));
		
		if (inTileDefinitions.containsKey(colorString))
		{
			String typeString = inTileDefinitions.get(colorString);
			return GetMapObjectByType(typeString);
		}
		return null;
	}
	
	private static HashMap<String, String> LoadTileDefinitions(String inMapFilename)
	{
		HashMap<String, String> tileDefs = new HashMap<String, String>();
		int extStartIndex = inMapFilename.lastIndexOf('.');
		if (extStartIndex != -1)
		{
			String tileDefFilename = String.format("%s.xml", inMapFilename.substring(0, extStartIndex));
			XMLParser parser = new XMLParser();
			try 
			{
				XMLElement rootNode = parser.parse(tileDefFilename);
				XMLElementList tileNodes = rootNode.getChildrenByName(XML_TILE_ELEMENT);
				for (int tileIndex = 0; tileIndex < tileNodes.size(); tileIndex++)
				{
					String typeString = tileNodes.get(tileIndex).getAttribute(XML_TILE_TYPE_ATTRIBUTE);
					String colorString = tileNodes.get(tileIndex).getAttribute(XML_TILE_COLOR_ATTRIBUTE);
					
					tileDefs.put(colorString, typeString);
				}
			} 
			catch (SlickException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tileDefs;
	}
	
	public static Map LoadFromFile(String inFilename)
	{
		try 
		{
			HashMap<String, String> tileDefs = LoadTileDefinitions(inFilename);
			BufferedImage mapImage = ImageIO.read(new File(inFilename));
			int mapWidth = mapImage.getWidth();
			int mapHeight = mapImage.getHeight();
			ArrayList<MapObject> mapObjects = new ArrayList<MapObject>();
			for (int y = 0; y < mapHeight; y++)
			{
				for (int x = 0; x < mapWidth; x++)
				{
					Color pixelColor = new Color(mapImage.getRGB(x, y));
					MapObject mapObject = ParseColor(pixelColor, tileDefs);
					if (mapObject != null)
					{
						mapObject.setPosition(new Vector2f(x*TILE_SIZE,y*TILE_SIZE));
						mapObjects.add(mapObject);
					}
				}
			}
			
			return new Map(mapObjects);
			
		}
		catch (IOException e) 
		{
			System.out.println("Failed to Load Map From File!");
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
}
