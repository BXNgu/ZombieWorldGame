package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing an action to harvest crop
 * @author Wincent
 *
 */
public class HarvestAction extends Action{
	
	protected String direction;
	protected Ground ground;
	protected int x;
	protected int y;
	
	/**
	 * Constructor
	 */
	public HarvestAction(Ground ground, String direction, int x, int y) {
		this.ground = ground;
		this.direction = direction;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Player will add Food item into inventory after harvesting, while Farmer 
	 * will harvest the Food item and drop it on the Ground
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Item food = new PortableItem("food",'*');
		if (actor.getDisplayChar()=='@') {
			actor.addItemToInventory(food);
		} else {
			map.at(x,y).addItem(food);
		}
		map.at(x, y).setGround(new Dirt());
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvest at position (" + x + ", " + y + ") " + direction;
	}

}
