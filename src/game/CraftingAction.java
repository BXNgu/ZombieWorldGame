package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing an action to craft weapon
 * @author Wincent
 *
 */
public class CraftingAction extends Action {
	
	protected Item item;
	protected char displayChar;
	
	/**
	 * Constructor 
	 */
	public CraftingAction(Item item) {
		this.item = item;
		this.displayChar = item.getDisplayChar();
	}

	/**
	 * This method is to craft a weapon if the crafting condition is met.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (this.displayChar == 'A') {
			Club club = new Club();
			actor.addItemToInventory(club);
		} else if (this.displayChar == 'L') {
			Mace mace = new Mace();
			actor.addItemToInventory(mace);
		}
		actor.removeItemFromInventory(item);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts " + item;
	}

}
