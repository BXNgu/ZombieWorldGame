package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**BONUS FEATURE
 * A Behaviour to heal Actor
 * @author Wincent
 *
 */
public class HealingBehaviour implements Behaviour{

	/**
	 * If Human, Farmer or Player is next to the Doctor, Doctor will heal them 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		int counter = 0;
		Item medic = null;
		for (Item item : actor.getInventory()) {
			if (item.getDisplayChar() == '$') {
				counter++;
				medic = item;
			}
		}
		if (counter != 0) {
			int x = map.locationOf(actor).x();
			int y = map.locationOf(actor).y();
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					try {
						if (map.at(x+i,y+j).getActor().getDisplayChar() == 'H' || 
								map.at(x+i,y+j).getActor().getDisplayChar() == 'F') {
							return new HealingAction(map.at(x+i,y+j).getActor(),medic,25);
						} else if (map.at(x+i,y+j).getActor().getDisplayChar() == '@') {
							return new HealingAction(map.at(x+i,y+j).getActor(),medic,50);
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return null;
	}

}
