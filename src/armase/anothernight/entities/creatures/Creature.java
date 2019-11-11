package armase.anothernight.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;

public abstract class Creature {
	
	protected Handler handler;
	protected boolean isEnemy;

	// Animations
	protected Animation animIdle, animAttack, animDeath;
	
	/*
	# damagedAnim: Animation
	# buffAnim: Animation
	# debuffAnim: Animation
	*/
	
	// Position
	protected int xPos = 80, yPos = 400;
	
	// Size
	protected int width = 160, height = 160;
	
	// Stats
	protected String name;
	protected int currentHp, maxHp;
	protected int power, defense;
	
	public Creature(String name, int currentHp, int maxHp, int power, int defense) {
		this.name = name;
		this.currentHp = currentHp;
		this.maxHp = maxHp;
		this.power = power;
		this.defense = defense;
	}
	
	public Creature(String name, int currentHp, int maxHp, int power, int defense, Handler handler) {
		this.name = name;
		this.currentHp = currentHp;
		this.maxHp = maxHp;
		this.power = power;
		this.defense = defense;
		this.handler = handler;
	}
	
	public void tick() {
		// Tick all animations here!
		animIdle.tick();
//		animAttack.tick();
//		animDeath.tick();
	}
	
	public void render(Graphics g) {
		// TODO : render where exactly? (responsive?)
		g.drawImage(getCurrentAnimationFrame(),
				xPos, yPos, width, height, null);
	}
	
	protected BufferedImage getCurrentAnimationFrame() {
		// TODO : if structure that returns images based on what creature is doing
		return animIdle.getCurrentFrame();
	}
	
	public void scaleUpMultipler(int multiplier) {
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
		receiver.receiveDamage(dmgDone);
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
	}
	
	// "Battle Cry" Ability
	public void debuffPowerOfOpponent(Creature receiver) {
		this.buffPower(1); // TODO : diminishing returns
		receiver.debuffPower(1); // TODO : diminishing returns
		receiver.buffDefense(1); // TODO : diminishing returns
	}
	
	public void buffPower(int addedPwr) {
		power += addedPwr;
	}
	
	public void debuffPower(int subtractedPwr) {
		power -= subtractedPwr;
	}
	
	// "Shields Up" Ability
	public void buffOwnDefense() {
		this.buffDefense(1); // TODO : diminishing returns
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
		currentHp = 0;
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
}
