package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**BONUS FEATURE
 * An Action used by Doctor to heal injured actor
 * @author Bing Xian
 *
 */
public class HealingAction extends Action{
	
	private Actor target;
	private int healing;
	private Item item;
	
	/**
	 * Constructor to initialize values
	 * @param target	An actor
	 * @param item		First Aid Kit
	 * @param healing	Healing figure
	 */
	public HealingAction(Actor target, Item item, int healing) {
		this.target = target;
		this.item = item;
		this.healing = healing;
	}

	/**
	 * This function is used to heal an injured actor by a Doctor
	 * @param actor	Doctor
	 * @param map	The Town map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		target.heal(healing);
		actor.removeItemFromInventory(item);
		return menuDescription(actor);
	}

	/**
	 * This function is used to print out in the menu description
	 * @param actor	Doctor
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " heals " + target;
	}

}
