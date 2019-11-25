package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Player;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class GameOverState extends State {
	
	UIManager uiManager;
	Creature player;
	int dayCount;

	public GameOverState(Handler handler, Creature player, int dayCount) {
		super(handler);
		this.player = player;
		this.dayCount = dayCount;
		
		System.out.println("Reached NIGHT : " + dayCount);
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		
		handler.getBackdropManager().setCurrentBackdropImage(Assets.gameOverBackdrop);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 3 * 2, handler.getHeight() / 3 * 2,
				buttonWidth, buttonHeight, Assets.btn_ok,
				new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null); // buttons disappear on state change
						State.setState(new MenuState(handler));
					}
				}));
	}

	@Override
	public void tick() {
		handler.getBackdropManager().tick();
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getBackdropManager().render(g);
		uiManager.render(g);
	}
}
