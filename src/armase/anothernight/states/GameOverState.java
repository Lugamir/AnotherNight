package armase.anothernight.states;

import java.awt.Graphics;
import java.io.IOException;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Assets;
import armase.anothernight.gfx.GFXwriter;
import armase.anothernight.scobo.ScoboManager;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class GameOverState extends State {
	
	UIManager uiManager;
	Creature player;
	int dayCount;

	private String loseMsg1 = "This is exactly why we don't accept     girlies in our guild!";
	private String loseMsg2 = "You died, game over!";
	
	public GameOverState(Handler handler, Creature player, int dayCount) {
		super(handler);
		this.player = player;
		this.dayCount = dayCount;
		
		System.out.println("Reached NIGHT : " + dayCount); // TODO : remove test line
		
		try {
			// TODO : prompt: add score? name?
			ScoboManager.writeToScobo("Anni", "reached night " + dayCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		
		handler.getBackdropManager().setCurrentBackdrop(Assets.black);

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
		
		// TODO : only renders should be in render
		GFXwriter.write(g, "- GAME OVER -", handler.getWidth() / 10,
				handler.getHeight() / 12, handler.getWidth() / 10 * 9, 2);
		GFXwriter.write(g, loseMsg1, handler.getWidth() / 10,
				handler.getHeight() / 4, handler.getWidth() / 10 * 9);
		GFXwriter.write(g, loseMsg2, handler.getWidth() / 10,
				handler.getHeight() / 4 * 2, handler.getWidth() / 10 * 9);
	}
}
