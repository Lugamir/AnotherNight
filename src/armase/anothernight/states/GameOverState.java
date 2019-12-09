package armase.anothernight.states;

import java.awt.Graphics;
import java.io.IOException;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Assets;
import armase.anothernight.scobo.ScoboManager;
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
		
		System.out.println("Reached NIGHT : " + dayCount); // TODO : remove test line
		
		try {
			ScoboManager.writeToScobo("BobTheBuilder", "reached night " + dayCount);
			// TODO : prompt: add score? name?
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		
		handler.getBackdropManager().setCurrentBackdrop(Assets.gameOverBackdrop);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 3 * 2, handler.getHeight() / 3 * 2,
				buttonWidth, buttonHeight, Assets.btn_ok,
				new ClickListener() {
					@Override
					public void onClick() {
						// TODO : fix, writeToScobo does not write to scobo
//						ScoboManager.writeToScobo("BobTheBuilder", "reached night " + dayCount); // TODO : prompt: add score? name?
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
