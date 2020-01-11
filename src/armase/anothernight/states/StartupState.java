package armase.anothernight.states;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.Logo;
import armase.anothernight.ui.UIManager;

public class StartupState extends State {

	private UIManager uiManager;
	
	public StartupState(Handler handler) {
		super(handler);
		
		handler.getBackdropManager().setCurrentBackdrop(Assets.forestNight);
		uiManager = new UIManager(handler);
		uiManager.addObject(new Logo(handler.getWidth() / 2 - 160, handler.getHeight() / 2 - 200, 320, 400));
		
		openMenuAfterMillis(4000);
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
	
	public void openMenuAfterMillis(long delay) {
	    TimerTask task = new TimerTask() {
	        public void run() {
	    		State.setState(new MenuState(handler));
	        }
	    };
	    Timer timer = new Timer("Timer");
	    timer.schedule(task, delay);
	}
}
