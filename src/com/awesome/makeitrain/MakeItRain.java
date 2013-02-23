package com.awesome.makeitrain;

import java.io.InputStream;
import java.net.URL;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.util.ResourceLocation;

import com.awesome.map.Map;

public class MakeItRain extends BasicGame {
	
	private static AppGameContainer app;
	private int mScreenWidth;
	private int mScreenHeight;
	private Map mMap;
	
	
	
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
	
	// -- SLICK GAME METHOD OVERRIDES --
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(true);
		
		mMap = Map.LoadFromFile("res/maps/TestMap.png");
		mMap.init(gc);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.clear();
		mMap.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		mMap.update(gc, delta);
	}
	
	private void updateCollision() {
		//for (Entity entity : )
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE)
			app.exit();
	}
	
}
