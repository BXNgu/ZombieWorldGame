package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that show the directions that can be shoot
 * @author Wincent
 *
 */
public class ShotgunAttackDirectionAction extends ShotgunAttackAction{
	private String[] direction;
	
	/**
	 * Constructor
	 * @param direction list of directions
	 */
	public ShotgunAttackDirectionAction (String[] direction) {
		super(direction);
		this.direction = direction;
	}
	
	/**
	 * A method to call the ShotgunAttackAction's execute method
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return super.execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " uses shotgun";
	}

}
