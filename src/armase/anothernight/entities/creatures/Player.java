package armase.anothernight.entities.creatures;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Player extends Creature {
	private int anniAnimSpeed = 120;

	public Player() {
		super("Anni", 50, 50, 10, 10);
		isEnemy = false;
		animSpeed = anniAnimSpeed;
		
		animIdle = new Animation(Assets.player_idle, animSpeed);
		animAttack = new Animation(Assets.player_attack, animSpeed);
		animBattleCry = new Animation(Assets.player_battleCry, animSpeed);
		animShieldsUp = new Animation(Assets.player_shieldsUp, animSpeed);
		animDeath = new Animation(Assets.player_death, animSpeed);
		currentAnim = animIdle;
	}
	
	public Player(Handler handler) {
		super("Anni", 50, 50, 10, 10, handler);
		isEnemy = false;
		animSpeed = anniAnimSpeed;
		
		animIdle = new Animation(Assets.player_idle, animSpeed);
		animAttack = new Animation(Assets.player_attack, animSpeed);
		animBattleCry = new Animation(Assets.player_battleCry, animSpeed);
		animShieldsUp = new Animation(Assets.player_shieldsUp, animSpeed);
		animDeath = new Animation(Assets.player_death, animSpeed);
		currentAnim = animIdle;
	}
}
