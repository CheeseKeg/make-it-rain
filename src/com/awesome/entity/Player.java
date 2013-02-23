package com.awesome.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.awesome.map.Map;


public class Player extends Entity {
	public Player() {
		super();
	}

	public void init(GameContainer gc) throws SlickException {
		image = new Image("res/images/player.png");
		boundingBox.setBounds(
				boundingBox.getX(), boundingBox.getY(),
				Map.TILE_SIZE, Map.TILE_SIZE);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_W))
		{
			SetControlFlag(eControlFlag.kControlFlag_Jump, true);
		}
		if (input.isKeyDown(Input.KEY_A))
		{
			SetControlFlag(eControlFlag.kControlFlag_Left, true);
		}
		if (input.isKeyDown(Input.KEY_D))
		{
			SetControlFlag(eControlFlag.kControlFlag_Right, true);
		}
		if (input.isKeyDown(Input.KEY_SPACE))
		{
			SetControlFlag(eControlFlag.kControlFlag_Attack, true);
		}
		
		super.update(gc, delta);
	}
}
