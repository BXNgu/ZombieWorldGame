package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing an action to eat
 * @author Wincent
 *
 */
public class EatingAction extends Action {
	
	protected Item food;
	
	/**
	 * Constructor
	 * 
	 */
	public EatingAction(Item food) {
		this.food = food;
	}

	/**
	 * To recover the health when the actor consume the food.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(10);
		try {
			actor.removeItemFromInventory(food);
		} catch (Exception e) {
			
		}
		return actor + " heals 10 hitpoints";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " eat " + food;
	}

}
