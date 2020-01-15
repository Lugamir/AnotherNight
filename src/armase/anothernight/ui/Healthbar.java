package armase.anothernight.ui;

import java.awt.Color;
import java.awt.Graphics;

import armase.anothernight.entities.creatures.Creature;

public class Healthbar extends UIObject {
	protected Creature creature;
	double hpHeight, maxHpWidth, currentHpWidth;
	int border;

	public Healthbar(Creature creature) {
		super(creature.getxPos(), creature.getyPos() - creature.getHeight() / 5,
				creature.getWidth(), creature.getHeight() / 5);
		
		this.creature = creature;
		updatePositionVariables();
	}

	@Override
	public void tick() {
		updatePositionVariables();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(Color.RED);
		g.fillRect((int) x + border, (int) y + border, (int) maxHpWidth, (int) hpHeight);
//		if(creature.isEnemy())
//			g.setColor(Color.RED);
//		else
			g.setColor(Color.GREEN);
		g.fillRect((int) x + border, (int) y + border, (int) currentHpWidth, (int) hpHeight);
	}

	@Override
	public void onClick() {}
	
	private void updatePositionVariables() {
		x = creature.getxPos();
		y = creature.getyPos() - creature.getHeight() / 5;
		width = creature.getWidth();
		height = creature.getHeight() / 5;
		
		border = height / 10;
		hpHeight = height - border * 2;
		maxHpWidth = width - border * 2;
		currentHpWidth = creature.getCurrentHp() / (double) creature.getMaxHp() * maxHpWidth;
	}
}
