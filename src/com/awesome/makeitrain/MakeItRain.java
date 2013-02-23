package com.awesome.makeitrain;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.awesome.map.Map;

public class MakeItRain extends BasicGame {
	
	private static AppGameContainer app;
	private int mScreenWidth;
	private int mScreenHeight;
	
	
	
	public MakeItRain() {
		super("Make It Rain");
		
		// --SLICK STUFF--
		
		System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/native/" + LWJGLUtil.getPlatformName());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		mScreenWidth = 1280;
		mScreenHeight = 720;
		
		Map testMap = Map.LoadFromFile("res/maps/TestMap.png");
		
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
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.clear();
		g.translate(mScreenWidth/2, mScreenHeight/2);
		int lineWidth = g.getFont().getWidth("Hello World");
		g.drawString("Hello World!", -(lineWidth/2), 0.0f);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
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
