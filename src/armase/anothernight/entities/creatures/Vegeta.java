package armase.anothernight.entities.creatures;

import armase.anothernight.Handler;

// for testing & enemy whacking purposes

public class Vegeta extends Player {
	
	public Vegeta() {
		super();
		
		name = "Vegeta";
		currentHp = 9001;
		maxHp = 9001;
		power = 9001;
		defense = 9001;
	}
	
	public Vegeta(Handler handler) {
		super(handler);

		name = "Vegeta";
		currentHp = 9001;
		maxHp = 9001;
		power = 9001;
		defense = 9001;
	}
}
