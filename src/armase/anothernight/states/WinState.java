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

public class WinState extends State {
	
	UIManager uiManager;
	Creature player;

	private String winMsg1 = "Wow, girlie! You've proven yourself. Let me introduce you to the boys...";
	private String winMsg2 = "Congrats, you won!";
	
	public WinState(Handler handler, Creature player) {
		super(handler);
		this.player = player;
		
		System.out.println("Won with " + player.getCurrentHp() + " HP left!"); // TODO : remove testline
		
		try {
			// TODO : prompt: add score? name?
			ScoboManager.writeToScobo("Anni", "survived night 10!");
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
		GFXwriter.write(g, "- WIN -", handler.getWidth() / 10,
				handler.getHeight() / 12, handler.getWidth() / 10 * 9, 2);
		GFXwriter.write(g, winMsg1, handler.getWidth() / 10,
				handler.getHeight() / 4, handler.getWidth() / 10 * 9);
		GFXwriter.write(g, winMsg2, handler.getWidth() / 10,
				handler.getHeight() / 4 * 2, handler.getWidth() / 10 * 9);
	}
}
