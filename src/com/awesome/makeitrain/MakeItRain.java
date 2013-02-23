package com.awesome.makeitrain;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MakeItRain extends BasicGame {
	
	private static AppGameContainer app;
	
	public MakeItRain() {
		super("Make It Rain");
		
		// --SLICK STUFF--
		
		System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/native/" + LWJGLUtil.getPlatformName());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		try {
			app = new AppGameContainer(this);
			app.setDisplayMode(1280, 720, false);
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
	
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE)
			app.exit();
	}
	
}
