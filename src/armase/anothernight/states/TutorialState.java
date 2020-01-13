package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.EntityManager;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.enemies.Dummy;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;

public class TutorialState extends State {
	private UIManager uiManager;
	private EntityManager entityManager;
	
	private Creature player, enemy;
	private int playerPosX, playerPosY;
	private int enemyPosX, enemyPosY;
	private int dummyCount;

	public TutorialState(Handler handler, Creature player, int dayCount) {
		super(handler);
		this.player = player;
		this.dummyCount = dayCount;

		playerPosX = player.getWidth() / 2;
		playerPosY = handler.getHeight() / 10 * 5;
		player.setPosition(playerPosX, playerPosY);
		
		enemy = new Dummy();
		enemyPosX = handler.getWidth() - enemy.getWidth() * 3 / 2;
		enemyPosY =  handler.getHeight() / 10 * 5;
		enemy.setPosition(enemyPosX, enemyPosY);

		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(enemy);
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		int buttonSpacing = 32;
		
		handler.getBackdropManager().setCurrentBackdrop(Assets.forestNight);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		// Attack Ability
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - buttonWidth / 2 - buttonWidth - buttonSpacing,
				handler.getHeight() - buttonHeight - buttonSpacing, buttonWidth, buttonHeight, Assets.btn_attack,
				new ClickListener() {
					@Override
					public void onClick() {
						playerAttack();
						tryUpdate();
					}
				}));
		
		// BattleCry Ability
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - buttonWidth / 2,
				handler.getHeight() - buttonHeight - buttonSpacing, buttonWidth, buttonHeight, Assets.btn_battleCry,
				new ClickListener() {
					@Override
					public void onClick() {
						playerBattleCry();
						tryUpdate();
					}
				}));
		
		// ShieldsUp Ability
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 + buttonWidth / 2 + buttonSpacing,
				handler.getHeight() - buttonHeight - buttonSpacing, buttonWidth, buttonHeight, Assets.btn_shieldsUp,
				new ClickListener() {
					@Override
					public void onClick() {
						playerShieldsUp();
						tryUpdate();
					}
				}));
		
		// OK button to return to Main Menu
		uiManager.addObject(new UIImageButton(handler.getWidth() / 30, handler.getHeight() / 20, buttonWidth, buttonHeight, Assets.btn_ok,
				new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null); // buttons disappear on state change
						State.setState(new MenuState(handler));
					}
				}));
	}

	@Override
	public void tick() {
		handler.getBackdropManager().tick();
		entityManager.tick();
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getBackdropManager().render(g);
		entityManager.render(g);
		uiManager.render(g);
	}
	
	private void playerAttack() {
		player.dealDamageToOpponent(enemy);
	}
	
	private void playerBattleCry() {
		player.debuffPowerOfOpponent(enemy);
	}
	
	private void playerShieldsUp() {
		player.buffOwnDefense();
	}
	
	private void tryUpdate() {
		if (!enemy.isAlive()) {
			handler.getMouseManager().setUIManager(null);
			State.setState(new TutorialState(handler, player, ++dummyCount));
		}
	}
}
