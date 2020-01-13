package armase.anothernight.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class NightCounter extends UIObject {
	private Handler handler;
	private Animation animNightWord;
	private int nightCount;
	private ClickListener clicker;

	public NightCounter(Handler handler, int nightCount, ClickListener clicker) {
		super(handler.getWidth() / 30, handler.getHeight() / 10, 128, 64);
		this.handler = handler;
		animNightWord = new Animation(300, Assets.night_word);
		this.nightCount = nightCount;
		this.clicker = clicker;
	}

	@Override
	public void tick() {
		updatePositionVariables();
		animNightWord.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentNightAnimationFrame(),
				(int) x, (int) y, width, height, null);
		g.drawImage(getCurrentCountFrame(),
				(int) x + width - 16, (int) y, 64, 64, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}
	
	private BufferedImage getCurrentNightAnimationFrame() {
		return animNightWord.getCurrentFrame();
	}
	
	private BufferedImage getCurrentCountFrame() {
		return Assets.numbers[nightCount - 1];
	}

	private void updatePositionVariables() {
		x = handler.getWidth() / 30;
		y = handler.getHeight() / 20;
	}
}
