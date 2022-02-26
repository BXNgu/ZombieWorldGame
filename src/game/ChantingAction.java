package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * An action where Mambo Marie uses to summon Zombies
 * @author Bing Xian
 *
 */
public class ChantingAction extends Action{
	
	private static int counter = 0;
	private Random random = new Random();
	
	/**
	 * This function is used to summon Zombies at a random location
	 * @param actor	Mambo Marie
	 * @param map	The current Game Map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int i = 0;
		while (i < 5) {
			int x = random.nextInt(80);
			int y = random.nextInt(25);
			try {
				if (x >= 24 && x <= 61) {
					if (y > 18) {
						map.at(x, y).addActor(new Zombie("Zombie " + ++counter));
						i++;
					}
				} else {
					map.at(x, y).addActor(new Zombie("Zombie " + ++counter));
					i++;
				}
			} catch (Exception e) {
				counter--;
			}
		}
		return menuDescription(actor);
	}

	/**
	 * This function is used to print out in the menu description
	 * @param actor	Mambo Marie
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " summoned Zombie";
	}

}
