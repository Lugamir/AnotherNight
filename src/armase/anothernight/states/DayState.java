package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.EntityManager;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Player;
import armase.anothernight.entities.creatures.enemies.DangerDoggo;
import armase.anothernight.entities.creatures.enemies.HiddenBug;
import armase.anothernight.entities.creatures.enemies.Mushdrool;
import armase.anothernight.entities.creatures.enemies.SneakySkeleton;
import armase.anothernight.gfx.Assets;
import armase.anothernight.ui.ClickListener;
import armase.anothernight.ui.UIImageButton;
import armase.anothernight.ui.UIManager;
import armase.anothernight.utils.Utils;

public class DayState extends State {

	private UIManager uiManager;
	private EntityManager entityManager;
	private Creature player, enemy;
	private int playerPosX, playerPosY;
	private int enemyPosX, enemyPosY;
	private final int enemyTypes = 3;
	private int dayCount;

	public DayState(Handler handler, Creature player, int dayCount) {
		super(handler);
		this.player = player;
		this.dayCount = dayCount;

		playerPosX = handler.getWidth() / 3 - 240;
		playerPosY = handler.getHeight() / 10 * 5;
		
		enemy = generateRndEnemy();
		
		enemyPosX = handler.getWidth() - 240;
		enemyPosY =  handler.getHeight() / 10 * 5;

		player.setPosition(playerPosX, playerPosY);
		enemy.setPosition(enemyPosX, enemyPosY);
		
		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(enemy);
		
		int buttonWidth = 128;
		int buttonHeight = 64;
		int buttonSpacing = 32;
		
		handler.getBackdropManager().setCurrentBackdropImage(Assets.campSceneBackdrop);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - buttonWidth / 2 - buttonWidth - buttonSpacing,
				handler.getHeight() - buttonHeight - buttonSpacing, buttonWidth, buttonHeight, Assets.btn_attack,
				new ClickListener() {
					@Override
					public void onClick() {
						playerAttack();
						enemyTurn();
						changeStateMaybe();
					}
				}));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - buttonWidth / 2,
				handler.getHeight() - buttonHeight - buttonSpacing, buttonWidth, buttonHeight, Assets.btn_battleCry,
				new ClickListener() {
					@Override
					public void onClick() {
						playerBattleCry();
						enemyTurn();
						changeStateMaybe();
					}
				}));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 + buttonWidth / 2 + buttonSpacing,
				handler.getHeight() - buttonHeight - buttonSpacing, buttonWidth, buttonHeight, Assets.btn_shieldsUp,
				new ClickListener() {
					@Override
					public void onClick() {
						playerShieldsUp();
						enemyTurn();
						changeStateMaybe();
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
	
	private void playerAttack() {
		player.dealDamageToOpponent(enemy);
	}
	
	private void playerBattleCry() {
		player.debuffPowerOfOpponent(enemy);
	}
	
	private void playerShieldsUp() {
		player.buffOwnDefense();
	}
	
	private void enemyTurn() {
		// TODO : enemy AIs
		enemy.dealDamageToOpponent(player);
	}
	
	private void changeStateMaybe() { // TODO : rename plox
		if (!player.isAlive()) {
			handler.getMouseManager().setUIManager(null);
			System.out.println("NIGHT : " + dayCount); // TODO : remove testline
			State.setState(new GameOverState(handler, player, dayCount));
		} else if (!enemy.isAlive() && dayCount < 10) {
			handler.getMouseManager().setUIManager(null);
			System.out.println("NIGHT : " + dayCount); // TODO : remove testline
			State.setState(new DayState(handler, player, ++dayCount));
		} else if (!enemy.isAlive() && dayCount >= 10) {
			handler.getMouseManager().setUIManager(null);
			System.out.println("NIGHT : " + dayCount); // TODO : remove testline
			State.setState(new WinState(handler, player));
		}
	}
}
