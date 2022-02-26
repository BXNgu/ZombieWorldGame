package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**BONUS FEATURE
 * An Action that is used by Player to set a trap in the Game Map
 * @author Bing Xian
 *
 */
public class SetTrapAction extends Action{
	
	private final static int WOODCOUNT = 4;

	/**
	 * This function is used to set a trap
	 * @param actor	Player
	 * @param map	The current Game Map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.locationOf(actor).setGround(new Trap('X'));
		int counter = 0;
		int i = 0;
		while (i < actor.getInventory().size()) {
			if (counter < WOODCOUNT && actor.getInventory().get(i).getDisplayChar() == 'w') {
				actor.removeItemFromInventory(actor.getInventory().get(i));
				counter ++;
			} else {
				i ++;
			}
		}
		return menuDescription(actor);
	}

	/**
	 * This function is used to print out in the menu description
	 * @param actor	Player
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sets trap";
	}

}
