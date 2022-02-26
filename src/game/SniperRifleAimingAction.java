package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action for Player to aim for a target
 * @author Wincent
 *
 */
public class SniperRifleAimingAction extends Action{

	protected Actor target;
	private static HashMap<Actor, Integer> aiming = new HashMap<Actor, Integer>();
	
	/**
	 * Constructor 
	 * @param target A Zombie target
	 * @param aiming A HashMap of Actor as a key and integer as the value
	 */
	public SniperRifleAimingAction(Actor target, HashMap<Actor, Integer> aiming) {
		this.target = target;
		this.aiming = aiming;
	}
	
	/**
	 * Increase the count of aim for the target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		try {
			int aim = aiming.get(target);

			if(aim >= 0 && aim < 2) {
				aim++;
				aiming.put(target, aim);
			}
		} catch (Exception e) {
		}

		return actor + " aims " + target + " for " + aiming.get(target) + " turn";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " aim " + target;
	}
	
	/**
	 * To reset the aim count of the target
	 */
	public void reset() {
		aiming.put(target, 0);
	}
}
