package com.awesome.makeitrain;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.awesome.map.Map;
import com.awesome.map.tiles.MapTile;
import com.awesome.map.tiles.PlayerSpawnMapTile;
import com.awesome.entity.Enemy;
import com.awesome.entity.Entity;
import com.awesome.entity.EntityManager;
import com.awesome.entity.Player;

public class MakeItRain extends BasicGame {
	
	private static AppGameContainer app;
	private int mScreenWidth;
	private int mScreenHeight;
	private Map mMap;
	private Player mPlayer;
	private Vector2f camOffset;
	
	public MakeItRain() {
		super("Make It Rain");
		
		// --SLICK STUFF--
		
		System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/native/" + LWJGLUtil.getPlatformName());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		mScreenWidth = 1280;
		mScreenHeight = 720;
		
		try {
			app = new AppGameContainer(this);
			app.setDisplayMode(mScreenWidth, mScreenHeight, false);
			app.setVSync(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private Vector2f getCameraOffset() {
		Vector2f pos = mPlayer.getPosition();
		return new Vector2f( ( mScreenWidth / 2 ) - ( Map.TILE_SIZE / 2 ) - pos.x,
				( mScreenHeight / 2 ) - ( Map.TILE_SIZE / 2 ) - pos.y );
	}
	
	// -- SLICK GAME METHOD OVERRIDES --
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(true);
		
		mMap = Map.LoadFromFile("res/maps/TestMap.png");
		mMap.init(gc);
		
		EntityManager entityManager = EntityManager.getInstance();
		mPlayer = new Player();
		mPlayer.init(gc);
		
		PlayerSpawnMapTile spawnTile = mMap.FindPlayerSpawnMapTile();
		if (spawnTile != null)
		{
			mPlayer.setPosition(spawnTile.getPosition());
		}
		
		Enemy enemy = new Enemy();
		enemy.init(gc);
		
		entityManager.AddEntity(mPlayer);
		entityManager.AddEntity(enemy);
		
		camOffset = getCameraOffset();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.clear();
		
		Vector2f camDest = getCameraOffset();
		if (camDest.distance(camOffset) <= 1.0f)
		{
			camOffset = camDest;
		}
		else
		{
			camOffset = new Vector2f(
					camOffset.x + (camDest.x - camOffset.x)/10,
					camOffset.y + (camDest.y - camOffset.y)/10);
		}
		g.translate(camOffset.x, camOffset.y);
		
		mMap.render(gc, g);
		
		EntityManager entityManager = EntityManager.getInstance();
		for (Entity entity : entityManager.getEntities()) {
			entity.render(gc, g);
		}
		
		g.translate(-camOffset.x, -camOffset.y);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		EntityManager entityManager = EntityManager.getInstance();
		mMap.update(gc, delta);
		
		for (Entity entity : entityManager.getEntities()) {
			entity.update(gc, delta);
		}
		
		updateCollision();
		
		entityManager.update();
	}
	
	private void updateCollision() {
		EntityManager entityManager = EntityManager.getInstance();
		for (Entity entity : entityManager.getEntities()) {
			for (MapTile mapObject : mMap.getMapTiles()) {
				entity.checkCollision(mapObject);
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE)
			app.exit();
	}
	
}
