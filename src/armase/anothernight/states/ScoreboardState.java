package armase.anothernight.states;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Assets;
import armase.anothernight.scobo.ScoboManager;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class ScoreboardState extends State {
	
	UIManager uiManager;

	public ScoreboardState(Handler handler) {
		super(handler);
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		int buttonSpacing = 32;
		
		handler.getBackdropManager().setCurrentBackdrop(Assets.scoreboardBackdrop);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() - buttonWidth - buttonSpacing, buttonSpacing, buttonWidth, buttonHeight, Assets.btn_ok,
				new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null); // buttons disappear on state change
						State.setState(new MenuState(handler));
					}
				}));
		
		// Load Scoreboard
		try {
			ArrayList<String[]> scoboMatrix = ScoboManager.loadScoreboardMatrix();
			if(!ScoboManager.scoboMatrixIsEmpty(scoboMatrix)) {
				System.out.println("SCORES FOUND:"); // TODO : remove test line
				for(String[] line : scoboMatrix) {
					for(int i = 0; i < line.length; i++) {
						// TODO : display graphical scobo with uiManager
						System.out.print(line[i] + "\t"); // TODO : remove test line
					}
					System.out.println(); // TODO : remove test line
				}
			} else {
				// TODO : display "no scores yet"
				System.out.println("NO SCORES FOUND"); // TODO : remove test line
			}
		} catch (IOException e) {
			System.out.println("SOMETHING WENT WRONG"); // TODO : remove test line
			e.printStackTrace();
		}
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
