package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.EntityManager;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Vegeta;
import armase.anothernight.entities.creatures.enemies.DangerDoggo;
import armase.anothernight.entities.creatures.enemies.HiddenBug;
import armase.anothernight.entities.creatures.enemies.Mushdrool;
import armase.anothernight.entities.creatures.enemies.SneakySkeleton;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;
import armase.anothernight.utils.Utils;

public class MenuState extends State {

	private UIManager uiManager;
	private EntityManager entityManager;
	private Creature enemy;
	private final int enemyTypes = 3;
	
	public MenuState(Handler handler) {
		super(handler);
		
//		handler.getBackdropManager().setCurrentBackdropImage(Assets.mainMenuBackdrop);
		handler.getBackdropManager().setCurrentBackdrop(Assets.test);
		
		enemy = generateRndEnemy();
		int enemyPosX = handler.getWidth() - enemy.getWidth() - 10;
		int enemyPosY =  handler.getHeight() - enemy.getHeight() - 10;
		enemy.setPosition(enemyPosX, enemyPosY).setHpVisible(false);;
		entityManager = new EntityManager(handler, enemy);
		
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
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					State.setState(new ScoreboardState(handler));
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
		
		// Super Secret Vegeta Mode!
		uiManager.addObject(new UIImageButton(0, handler.getHeight() - 64, 128, 64, Assets.btn_secret,
			new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					State.setState(new DayState(handler, new Vegeta(handler), 1));
				}
			}));
	}

	@Override
	public void tick() {
		handler.getBackdropManager().tick();
		uiManager.tick();
		entityManager.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getBackdropManager().render(g);
		uiManager.render(g);
		entityManager.render(g);
	}
	
	private Creature generateRndEnemy() {
		// TODO : Creature Factory?
		switch (Utils.generateRandomMinMax(1, enemyTypes)) {
		case 1:
			return new SneakySkeleton();
		case 2:
			return new Mushdrool();
		case 3:
			return new DangerDoggo();
		default:
			return new HiddenBug();
		}
	}
}
