package armase.anothernight.gfx;

import java.awt.image.BufferedImage;

import armase.anothernight.properties.Props;

public class Animation {
	private int speed = Props.DEFAULT_ANIM_SPEED;
	private int index = 0;
	private long lastTime, timer = 0;
	private BufferedImage[] frames;

	public Animation(BufferedImage[] frames) {
		this.frames = frames;
	}
	
	public Animation(BufferedImage[] frames, int speed) {
		this.frames = frames;
		this.speed = speed;
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	public int getMaxIndex() {
		return frames.length - 1;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
