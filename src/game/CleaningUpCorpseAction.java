package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**BONUS FEATURE
 * An Action to clean up the corpse on the Ground by a Doctor
 * @author Bing Xian
 *
 */
public class CleaningUpCorpseAction extends Action{
	
	private Item item;
	private int x;
	private int y;
	
	/**
	 * A constructor that is used to initialize values
	 * @param item	An item
	 * @param x		x-coordinate
	 * @param y		y-coordinate
	 */
	public CleaningUpCorpseAction(Item item, int x, int y) {
		this.item = item;
		this.x = x;
		this.y = y;
	}

	/**
	 * This function is used to remove the corpse on the Ground
	 * @param actor	Doctor
	 * @param map	The current Game Map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.at(x, y).removeItem(item);
		return menuDescription(actor);
	}

	/**
	 * This function is used to print out in the menu description
	 * @param actor	Doctor
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " cleans up corpse";
	}

}
