package armase.anothernight.entities.creatures;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Vegeta extends Creature {

	public Vegeta() {
		super("Vegeta", 9001, 9001, 9001, 9001);
		isEnemy = false;
		
		animIdle = new Animation(animSpeed, Assets.player_idle);
		animAttack = new Animation(animSpeed, Assets.player_attack);
		animBattleCry = new Animation(animSpeed, Assets.player_battleCry);
		animShieldsUp = new Animation(animSpeed, Assets.player_shieldsUp);
		animDeath = new Animation(animSpeed, Assets.player_death);
		currentAnim = animIdle;
	}
	
	public Vegeta(Handler handler) {
		super("Vegeta", 9001, 9001, 9001, 9001, handler);
		isEnemy = false;
		
		animIdle = new Animation(animSpeed, Assets.player_idle);
		animAttack = new Animation(animSpeed, Assets.player_attack);
		animBattleCry = new Animation(animSpeed, Assets.player_battleCry);
		animShieldsUp = new Animation(animSpeed, Assets.player_shieldsUp);
		animDeath = new Animation(animSpeed, Assets.player_death);
		currentAnim = animIdle;
	}

}
