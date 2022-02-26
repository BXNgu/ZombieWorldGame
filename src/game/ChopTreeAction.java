package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**BONUS FEATURE
 * An action where the Player is able to chop a large tree
 * @author Bing Xian
 *
 */
public class ChopTreeAction extends Action{

	private int x;
	private int y;
	private String direction;
	
	/**
	 * Constructor to initialize values
	 * @param direction	A String
	 * @param x			x-coordinate
	 * @param y			y-coordinate
	 */
	public ChopTreeAction(String direction, int x, int y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
	}

	/**
	 * This function is used to chop large trees
	 * @param actor	Player
	 * @param map	The current Game Map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.addItemToInventory(new PortableItem("Wood",'w'));
		map.at(x, y).setGround(new Dirt());
		return menuDescription(actor);
	}

	/**
	 * This function is used to print out in the menu description
	 * @param actor	Player
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " chop a tree at " + direction;
	}

}
