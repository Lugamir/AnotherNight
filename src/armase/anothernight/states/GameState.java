package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.worlds.World;

public class GameState extends State {

	// Worlds
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler);
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}
