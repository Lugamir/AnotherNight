package armase.anothernight.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {
	private BufferedImage[] images;
	private ClickListener clicker;
	private int buttonWidthInc;
	private int buttonHeightInc;
	
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
		
		buttonWidthInc = width / 10;
		buttonHeightInc = height / 10;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], (int) x - buttonWidthInc / 2, (int) y - buttonHeightInc / 2,
					width + buttonWidthInc, height + buttonHeightInc, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}
}
