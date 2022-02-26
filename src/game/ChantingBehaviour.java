package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * A Chanting Behaviour that implements Behaviour class to allow Mambo Marie to summon Zombies
 * @author Bing Xian
 *
 */
public class ChantingBehaviour implements Behaviour{

	private static int counter = 0;
	
	/**
	 * This function is used to summon Zombie every 10 rounds
	 * @param actor	Mambo Marie
	 * @param map	The current GameMap
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		counter ++;
		if (counter%10 == 0 && counter%30 != 0) {
			return new ChantingAction();
		} else {
			return null;
		}
	}

}
