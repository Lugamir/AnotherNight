package armase.anothernight.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Player;

public class EntityManager {
	private Handler handler;
	private Creature player;
	private ArrayList<Creature> creatures;
	
	public EntityManager(Handler handler, Creature player) {
		this.handler = handler;
		this.player = player;
		
		creatures = new ArrayList<Creature>();
		addEntity(player);
	}
	
	public EntityManager(Handler handler) {
		this.handler = handler;
		creatures = new ArrayList<Creature>();
	}
	
	public void tick() {
		if(creatures.size() > 0)
			for(int i = 0; i < creatures.size(); i++) {
				Creature e = creatures.get(i);
				e.tick();
			}
	}
	
	public void render(Graphics g) {
		for(Creature e : creatures) {
			e.render(g);
		}
	}
	
	public void addEntity(Creature e) {
		creatures.add(e);
	}
	
	// ### GETTERS & SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Creature getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Creature> getEntities() {
		return creatures;
	}

	public void setEntities(ArrayList<Creature> entities) {
		this.creatures = entities;
	}
}
