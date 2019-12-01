package armase.anothernight.entities.creatures;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Player extends Creature {

	public Player() {
		super("Anni", 50, 50, 10, 10);
		isEnemy = false;
		
		animIdle = new Animation(animSpeed, Assets.player_idle);
		animAttack = new Animation(animSpeed, Assets.player_attack);
		animBattleCry = new Animation(animSpeed, Assets.player_battleCry);
		animShieldsUp = new Animation(animSpeed, Assets.player_shieldsUp);
		animDeath = new Animation(animSpeed, Assets.player_death);
		currentAnim = animIdle;
	}
	
	public Player(Handler handler) {
		super("Anni", 50, 50, 10, 10, handler);
		isEnemy = false;
		
		animIdle = new Animation(animSpeed, Assets.player_idle);
		animAttack = new Animation(animSpeed, Assets.player_attack);
		animBattleCry = new Animation(animSpeed, Assets.player_battleCry);
		animShieldsUp = new Animation(animSpeed, Assets.player_shieldsUp);
		animDeath = new Animation(animSpeed, Assets.player_death);
		currentAnim = animIdle;
	}
}
