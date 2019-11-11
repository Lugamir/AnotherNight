package armase.anothernight.worlds;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.EntityManager;
import armase.anothernight.entities.creatures.enemies.DangerDoggo;
import armase.anothernight.entities.creatures.enemies.Mushdrool;
import armase.anothernight.entities.creatures.enemies.SneakySkeleton;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class World {
	
	private Handler handler;
	private int width, height;
	
	// Entities
	private EntityManager entityManager;
	
	public World(Handler handler) {
		this.handler = handler;
		
		// Test
		entityManager = new EntityManager(handler, new DangerDoggo(handler).setPosition(handler.getWidth() / 3 - 240, handler.getHeight() / 10 * 5));
		entityManager.addEntity(new Mushdrool(handler).setPosition(handler.getWidth() / 3 * 2 - 240, handler.getHeight() / 10 * 5));
		entityManager.addEntity(new SneakySkeleton(handler).setPosition(handler.getWidth() - 240, handler.getHeight() / 10 * 5));
	}
	
	public void tick() {
		handler.getBackdropManager().tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		handler.getBackdropManager().render(g);
		entityManager.render(g);

		// TODO : render UI
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
