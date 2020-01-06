package armase.anothernight.states;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Assets;
import armase.anothernight.gfx.GFXwriter;
import armase.anothernight.scobo.ScoboManager;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class ScoreboardState extends State {
	
	private UIManager uiManager;
	private String text;
	private ArrayList<String> scoboEntries;

	public ScoreboardState(Handler handler) {
		super(handler);
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		int buttonSpacing = 32;
		
		text = "";
		scoboEntries = new ArrayList<String>();
		
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
				scoboEntries.add("Scores found:");
				for(String[] line : scoboMatrix) {
					for(int i = 0; i < line.length; i++) {
						text += line[i] + " "; // TODO : fix the ugly
					}
					scoboEntries.add(text); // TODO : fix the ugly
					text = ""; // TODO : fix the ugly
				}
			} else {
				scoboEntries.add("No scores yet!");
			}
		} catch (IOException e) {
			scoboEntries.add("Something went wrong, toss your admin a coin!");
			e.printStackTrace();
		}
		
//		uiManager.addObject(new UIImageButton(handler.getWidth() - buttonWidth - buttonSpacing, buttonSpacing, buttonWidth, buttonHeight, GFXwriter.createBufferedImageFromMsg(text, handler.getWidth(), handler.getHeight()),
//				new ClickListener() {
//					@Override
//					public void onClick() {
//						handler.getMouseManager().setUIManager(null); // buttons disappear on state change
//						State.setState(new MenuState(handler));
//					}
//				}));
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

		// TODO : only renders should be in render  // TODO : fix the ugly
		int yStart = handler.getHeight() / 12;
		for(String entry : scoboEntries) {
			GFXwriter.write(g, entry, handler.getWidth() / 10,
					yStart += 32, handler.getWidth());
		}
		
//		GFXwriter.write(g, text, handler.getWidth() / 10,
//				handler.getHeight() / 12, handler.getWidth() / 10 * 9);
	}
}
