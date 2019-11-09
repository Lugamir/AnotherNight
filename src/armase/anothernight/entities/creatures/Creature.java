package armase.anothernight.entities.creatures;

public abstract class Creature {
	
	/*
	# idleAnim: Animation
	# attackAnim: Animation
	# damagedAnim: Animation
	# buffAnim: Animation
	# debuffAnim: Animation
	# deathAnim: Animation
	*/
	
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
	
	// fallback easter egg TODO : cannot instantiate creature (abstract)
	public Creature() {
		name = "~UnKnOwN~";
		currentHp = 1;
		maxHp = 1;
		power = 50;
		defense = 999;
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

}
