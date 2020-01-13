package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.EntityManager;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Player;
import armase.anothernight.entities.creatures.Vegeta;
import armase.anothernight.entities.creatures.enemies.DangerDoggo;
import armase.anothernight.entities.creatures.enemies.HiddenBug;
import armase.anothernight.entities.creatures.enemies.Mushdrool;
import armase.anothernight.entities.creatures.enemies.SneakySkeleton;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.Logo;
import armase.anothernight.ui.Title;
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
		
		handler.getBackdropManager().setCurrentBackdrop(Assets.forestNight);
		
		enemy = generateRndEnemy();
		int enemyPosX = handler.getWidth() - enemy.getWidth() - 10;
		int enemyPosY =  handler.getHeight() - enemy.getHeight() - 10;
		enemy.setPosition(enemyPosX, enemyPosY).setHpVisible(false);;
		
		entityManager = new EntityManager(handler, enemy);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new Logo(handler.getWidth() / 100, handler.getHeight() / 100, 64, 80,
			new ClickListener() {
				@Override
				public void onClick() {
					Utils.redirectTo("https://github.com/Tem-Dev/AnotherNight");
				}
		}));

		// Title
		uiManager.addObject(new Title(handler.getWidth() / 2 - 160, handler.getHeight() / 3 - 140, 320, 280));
		
		// Start
		uiManager.addObject(new UIImageButton(200 - 64, handler.getHeight() - 200 - 32, 128, 64, Assets.btn_start,
			new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					State.setState(new PrefaceState(handler));
				}
			}));

		// Scoreboard
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 64, handler.getHeight() - 200 - 32, 128, 64, Assets.btn_scoreboard,
			new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					State.setState(new ScoreboardState(handler));
				}
			}));
		
		// Tutorial
		uiManager.addObject(new UIImageButton(handler.getWidth() - 200 - 64, handler.getHeight() - 200 - 32, 128, 64, Assets.btn_tutorial,
			new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null); // buttons disappear on state change
					State.setState(new TutorialState(handler, new Player(handler), 1));
				}
			}));

		// Quit
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 64, handler.getHeight() - 100 - 32, 128, 64, Assets.btn_quit,
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
