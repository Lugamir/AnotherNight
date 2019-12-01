package armase.anothernight.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.ui.Healthbar;
import armase.anothernight.utils.Utils;

public abstract class Creature {
	
	protected Handler handler;
	protected boolean isEnemy;
	protected boolean needsAnimChange = false;
	protected int msBetweenTurns = 1200;

	// Animations
	protected Animation currentAnim, animIdle, animAttack, animBattleCry, animShieldsUp, animDeath;
	protected int animSpeed = 300;
	
	// Position // TODO : size responsiveness
	protected int xPos = 80, yPos = 400;
	
	// Size // TODO : size responsiveness
	protected int width = 160, height = 160;
	
	// Stats
	protected String name;
	protected int currentHp, maxHp;
	protected int power, defense;
	
	// Healthbar
	protected Healthbar hpBar;
	protected boolean hpVisible;
	
	public Creature(String name, int currentHp, int maxHp, int power, int defense) {
		this.name = name;
		this.currentHp = currentHp;
		this.maxHp = maxHp;
		this.power = power;
		this.defense = defense;
		
		currentAnim = animIdle;
		hpVisible = true;
		hpBar = new Healthbar(this);
	}
	
	public Creature(String name, int currentHp, int maxHp, int power, int defense, Handler handler) {
		this.name = name;
		this.currentHp = currentHp;
		this.maxHp = maxHp;
		this.power = power;
		this.defense = defense;
		this.handler = handler;
		
		currentAnim = animIdle;
		hpVisible = true;
		hpBar = new Healthbar(this);
	}
	
	public void tick() {
		// Tick all active animations here!

		if (!(currentAnim == animDeath && currentAnim.getIndex() >= currentAnim.getMaxIndex()))
			currentAnim.tick();
		
		hpBar.tick();
	}
	
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),
				xPos, yPos, width, height, null);
		
		if(hpVisible)
			hpBar.render(g);
	}
	
	protected BufferedImage getCurrentAnimationFrame() {
		return currentAnim.getCurrentFrame();
	}
	
	public void scaleUpMultiplier(int multiplier) {
		width *= multiplier;
		height *= multiplier;		
	}
	
	public Creature setPosition(int x, int y) {
		xPos = x;
		yPos = y;
		return this;
	}
	
	public void autoPosition() {
		if (this.isEnemy) {
			this.setPosition(handler.getWidth() / 3, handler.getHeight() / 3 * 2);
		} else {
			this.setPosition(handler.getWidth() / 3 * 2, handler.getHeight() / 3 * 2);
		}
	}
	
	// "Attack" Ability
	public int dealDamageToOpponent(Creature receiver) {
		int dmgDone = calculateDamageToOpponent(receiver);
		currentAnim = animAttack;
		currentAnim.setIndex(3);
		receiver.receiveDamage(dmgDone);
		Utils.waitInMs(msBetweenTurns);
		currentAnim = animIdle;
		
		if (receiver.getCurrentHp() == 0 && receiver.isEnemy()) {
			Utils.waitInMs(600);
		} else if (receiver.getCurrentHp() == 0 && !receiver.isEnemy()) {
			Utils.waitInMs(2000);
		}
		
		return dmgDone;
	}
	
	private int calculateDamageToOpponent(Creature receiver) {
		int dmg = power - (receiver.getDefense() / 3);
		if (dmg < 1)
			dmg = 1;
		return dmg;
	}
	
	public void receiveDamage(int receivedDmg) {
		currentHp -= receivedDmg;
		if (currentHp < 0)
			currentHp = 0;
		if (currentHp == 0) {
			currentAnim = animDeath;
			currentAnim.setIndex(0);
		}
	}
	
	// "Battle Cry" Ability
	public void debuffPowerOfOpponent(Creature receiver) {
		currentAnim = animBattleCry;
		currentAnim.setIndex(3);
		this.buffPower(1); // TODO : diminishing returns
		receiver.debuffPower(1); // TODO : diminishing returns
		receiver.buffDefense(1); // TODO : diminishing returns
		Utils.waitInMs(msBetweenTurns);
		currentAnim = animIdle;
	}
	
	public void buffPower(int addedPwr) {
		power += addedPwr;
	}
	
	public void debuffPower(int subtractedPwr) {
		power -= subtractedPwr;
	}
	
	// "Shields Up" Ability
	public void buffOwnDefense() {
		currentAnim = animShieldsUp;
		currentAnim.setIndex(3);
		this.buffDefense(1); // TODO : diminishing returns
		Utils.waitInMs(msBetweenTurns);
		currentAnim = animIdle;
	}
	
	public void buffDefense(int addedDef) {
		defense += addedDef;
	}
	
	public void debuffDefense(int subtractedDef) {
		defense -= subtractedDef;
	}
	
	public void receiveHeal(int addedHp) {
		currentHp += addedHp;
	}
	
	public boolean isAlive() {
		return currentHp > 0;
	}
	
	public void kill() {
		currentAnim = animDeath;
		currentHp = 0;
		Utils.waitInMs(2000);
	}
	
	// ### GETTERS & SETTERS

	public String getName() {
		return name;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getPower() {
		return power;
	}

	public int getDefense() {
		return defense;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setAnimSpeed(int animSpeed) {
		this.animSpeed = animSpeed;
	}

	public void setHpVisible(boolean hpVisible) {
		this.hpVisible = hpVisible;
	}

	public boolean isEnemy() {
		return isEnemy;
	}
}
