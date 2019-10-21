package armase.anothernight.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

// TODO : This file is an example, use it as a template or delete it
// LEAVE IT UNTIL ANOTHER FILE ARRIVES IN THIS PACKAGE (Git is whiny)

public class Player extends Creature {
	
	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 8;
		bounds.y = 16;
		bounds.width = 16;
		bounds.height = 16;
		
		// Animations - set animation speed in ms here
		int animSpeed = 300;
		animDown = new Animation(animSpeed, Assets.blueGuy_down);
		animUp = new Animation(animSpeed, Assets.blueGuy_up);
		animLeft = new Animation(animSpeed, Assets.blueGuy_left);
		animRight = new Animation(animSpeed, Assets.blueGuy_right);
	}

	@Override
	public void tick() {
		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		// Movement
		getInput();
		move();
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if(xMove > 0 ) {
			return animRight.getCurrentFrame();
		} else if(yMove < 0) {
			return animUp.getCurrentFrame();
		} else if(yMove > 0){
			return animDown.getCurrentFrame();
		} else {
			return Assets.blueGuy;
		}
	}
}
