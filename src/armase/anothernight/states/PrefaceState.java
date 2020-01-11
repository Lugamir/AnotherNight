package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Player;
import armase.anothernight.gfx.Assets;
import armase.anothernight.gfx.GFXwriter;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class PrefaceState extends State {
	
	private UIManager uiManager;
	
	private String introMsg1 = "Another Night is a story about a girl   named Anni."
			+ " Anni wants to get into the  Amazing Warrior Guild but they are      sexist.";
	private String introMsg2 = "You are Anni and your objective is to   survive ten nights in the Forest of     DooOOOOoom"
			+ " to prove your worth to the   Amazing Warrior Guild...";

	public PrefaceState(Handler handler) {
		super(handler);
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		int buttonSpacing = 32;
		
		handler.getBackdropManager().setCurrentBackdrop(Assets.black);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() - buttonWidth - buttonSpacing,
				handler.getHeight() - buttonHeight - buttonSpacing, buttonWidth, buttonHeight, Assets.btn_ok,
				new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null); // buttons disappear on state change
						State.setState(new DayState(handler, new Player(handler), 1));
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
		GFXwriter.write(g, "- Preface -", handler.getWidth() / 10,
				handler.getHeight() / 12, handler.getWidth() / 10 * 9, 2);
		GFXwriter.write(g, introMsg1, handler.getWidth() / 10,
				handler.getHeight() / 4, handler.getWidth() / 10 * 9);
		GFXwriter.write(g, introMsg2, handler.getWidth() / 10,
				handler.getHeight() / 4 * 2, handler.getWidth() / 10 * 9);
	}
}
