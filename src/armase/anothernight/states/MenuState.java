package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Vegeta;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		
		handler.getBackdropManager().setCurrentBackdropImage(Assets.mainMenuBackdrop);
		
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200 - 64, handler.getHeight() - 200 - 32, 128, 64, Assets.btn_start,
			new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					State.setState(new PrefaceState(handler));
				}
			}));

		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 64, handler.getHeight() - 200 - 32, 128, 64, Assets.btn_scoreboard,
			new ClickListener() {
				@Override
				public void onClick() {
					// TODO : Open Scoreboard
				}
			}));

		uiManager.addObject(new UIImageButton(handler.getWidth() - 200 - 64, handler.getHeight() - 200 - 32, 128, 64, Assets.btn_quit,
			new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					System.exit(0);
				}
			}));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() - 128,0 , 128, 64, Assets.btn_secret,
			new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					State.setState(new DayState(handler, new Vegeta(handler), 1)); // TODO : remove testline
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
