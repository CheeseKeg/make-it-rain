package com.awesome.makeitrain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player extends Entity {
	
	public Player() {
		super();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		image = new Image("images/player.png");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
}
