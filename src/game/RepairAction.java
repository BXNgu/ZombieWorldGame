package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**BONUS FEATURE
 * An Action to repair the trap that has been stepped by the Zombie
 * @author Bing Xian
 *
 */
public class RepairAction extends Action{

	private String direction;
	private int x;
	private int y;
	private final static int WOODCOUNT = 2;
	
	/**
	 * Constructor to initialize values
	 * @param direction	A String
	 * @param x			x-coordinate
	 * @param y			y-coordinate
	 */
	public RepairAction(String direction, int x, int y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This function is used to repair the trap stepped by a Zombie
	 * @param actor	Player
	 * @param map	The current Game Map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.at(x, y).setGround(new Trap('X'));
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
		return actor + " repairs trap at " + direction;
	}

}
