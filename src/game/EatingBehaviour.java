package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An Eating Behaviour that implements Behaviour class to allow Eating for actors
 * @author Bing Xian
 *
 */
public class EatingBehaviour implements Behaviour {
	
	private int hitPoint;
	private int maxHitPoint;
	
	/**
	 * Constructor
	 * 
	 * @param hitPoint		Actor's current hit point
	 * @param maxHitPoint	Actor's maximum hit point
	 */
	public EatingBehaviour(int hitPoint, int maxHitPoint) {
		this.hitPoint = hitPoint;
		this.maxHitPoint = maxHitPoint;
	}

	/**
	 * Returns an Eating Action that eats the food if the hitpoint is not maximum 
	 * and there is food in the inventory
	 * 
	 * @param actor	the Actor that performs eating action
	 * @param map	the GameMap
	 * @return null or an action
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		for (Item item : map.locationOf(actor).getItems()) {
			if (item != null && item.getDisplayChar() == '*' && actor.getDisplayChar() != 'Z' && hitPoint < maxHitPoint) {
				map.locationOf(actor).removeItem(item);
				return new EatingAction(item);
			}
		}
		return null;
	}
	
}
