package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * A new type of Zombie that can summons more Zombies
 * @author Wincent
 *
 */
public class MamboMarie extends ZombieActor {
		
	private Behaviour[] behaviours = {
			new ChantingBehaviour(),
			new WanderBehaviour()};
	
	/**
	 * Constructor
	 * @param hitPoint Health of MamboMarie
	 */
	public MamboMarie(int hitPoint) {
		super("Mambo Marie", 'M', hitPoint, ZombieCapability.UNDEAD);
	}
	
	/**
	 * Getter
	 * @return int of health points
	 */
	public int getHitPoint() {
		return this.hitPoints;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}
	
}
