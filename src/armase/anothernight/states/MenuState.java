package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Assets;

// TODO : This file is an example, use it as a template or delete it
// LEAVE IT UNTIL ANOTHER FILE ARRIVES IN THIS PACKAGE (Git is whiny)

public class MenuState extends State {
	
	public MenuState(Handler handler) {
		super(handler);
//		uiManager = new UIManager(handler);
//		handler.getMouseManager().setUIManager(uiManager);
		
//		uiManager.addObject(new UIImageButton(100, 200, 128, 64, Assets.btn_start,
//			new ClickListener() {
//				@Override
//				public void onClick() {
//					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
//					State.setState(handler.getGame().gameState);
//				}
//			}));
	}

	@Override
	public void tick() {
//		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
//		uiManager.render(g);
	}
}
