package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**BONUS FEATURE
 * An Action that is used to stun a Zombie when it steps on the trap
 * @author Bing Xian
 *
 */
public class StunAction extends Action{

	/**
	 * This function is used to deal damage to the Zombie and stun the Zombie for 1 round 
	 * when the Zombie steps on the trap
	 * @param actor	Zombie
	 * @param map	The current Game Map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.hurt(20);
		map.locationOf(actor).setGround(new Trap('O'));
		return menuDescription(actor);
	}

	/**
	 * This function is used to print out in the menu description
	 * @param actor	Zombie
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " stuns for 1 round";
	}

}
