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
	private static final String XML_TILE_NAME_ATTRIBUTE = "name";
	private static final String XML_TILE_COLOR_ATTRIBUTE = "color";
	private static final String XML_TILE_COLLISION_ATTRIBUTE = "collision";
	private static final String XML_TILE_TEXTURE_ATTRIBUTE = "texture";
	private ArrayList<MapTile> mMapTiles;
	
	public ArrayList<MapTile> getMapTiles()
	{
		return mMapTiles;
	}
	
	private Map(ArrayList<MapTile> inMapTiles)
	{
		mMapTiles = inMapTiles;
	}
	
	private static MapTile GetMapTileByTileDefinition(TileDefinition inTileDefinition)
	{
		switch(inTileDefinition.getType())
		{
			case "turf":
			{
				TurfTileDefinition turfTileDef = (TurfTileDefinition)inTileDefinition;
				return new TurfMapTile();
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
	
	private static MapTile ParseColor(Color inColor, HashMap<String, TileDefinition> inTileDefinitions)
	{
		String colorString = String.format("%d,%d,%d,%d",
				ConvertFloatColorToByte(inColor.r),
				ConvertFloatColorToByte(inColor.g),
				ConvertFloatColorToByte(inColor.b), 
				ConvertFloatColorToByte(inColor.a));
		
		if (inTileDefinitions.containsKey(colorString))
		{
			TileDefinition tileDef = inTileDefinitions.get(colorString);
			return GetMapTileByTileDefinition(tileDef);
		}
		return null;
	}
	
	private static HashMap<String, TileDefinition> LoadTileDefinitions(String inMapFilename)
	{
		HashMap<String, TileDefinition> tileDefs = new HashMap<String, TileDefinition>();
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
					String nameString = tileNodes.get(tileIndex).getAttribute(XML_TILE_NAME_ATTRIBUTE);
					String colorString = tileNodes.get(tileIndex).getAttribute(XML_TILE_COLOR_ATTRIBUTE);
					
					switch(typeString)
					{
						case "turf":
						{
							String collisionString = tileNodes.get(tileIndex).getAttribute(XML_TILE_COLLISION_ATTRIBUTE);
							String textureString = tileNodes.get(tileIndex).getAttribute(XML_TILE_TEXTURE_ATTRIBUTE);
							
							boolean hasCollision = Boolean.parseBoolean(collisionString);
							tileDefs.put(colorString, new TurfTileDefinition(typeString, nameString, hasCollision, textureString));
							
							break;
						}
					}
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
			HashMap<String, TileDefinition> tileDefs = LoadTileDefinitions(inFilename);
			BufferedImage mapImage = ImageIO.read(new File(inFilename));
			int mapWidth = mapImage.getWidth();
			int mapHeight = mapImage.getHeight();
			ArrayList<MapTile> mapTiles = new ArrayList<MapTile>();
			for (int y = 0; y < mapHeight; y++)
			{
				for (int x = 0; x < mapWidth; x++)
				{
					Color pixelColor = new Color(mapImage.getRGB(x, y));
					MapTile mapTile = ParseColor(pixelColor, tileDefs);
					if (mapTile != null)
					{
						mapTile.setPosition(new Vector2f(x*TILE_SIZE,y*TILE_SIZE));
						mapTiles.add(mapTile);
					}
				}
			}
			
			return new Map(mapTiles);
			
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
