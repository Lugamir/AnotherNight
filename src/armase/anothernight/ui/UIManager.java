package armase.anothernight.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import armase.anothernight.Handler;

public class UIManager {
	private Handler handler;
	private ArrayList<UIObject> objects;
	private boolean uiActive = true;
	private int disableTime = 300;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for(UIObject o : objects)
			o.tick();
	}
	
	public void render(Graphics g) {
		for(UIObject o : objects)
			o.render(g);
	}
	
	public void onMouseMove(MouseEvent e) {
		if(uiActive)
			for(UIObject o : objects)
				o.onMouseMove(e);
	}
	
	public void onMouseRelease(MouseEvent e) {
		if(uiActive) {
			for(UIObject o : objects)
				o.onMouseRelease(e);
			
			uiActive = false;
			new Timer().schedule(new TimerTask() {
		        public void run() {
		        	uiActive = true;
		        }
		    }, disableTime);
		}
	}
	
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	public void removeObject(UIObject o) {
		objects.remove(o);
	}
	
	// GETTERS & SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
}
