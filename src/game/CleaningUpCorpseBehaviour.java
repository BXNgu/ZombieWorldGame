package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**BONUS FEATURE
 * A Cleaning Up Corpse Behaviour that implements Behaviour class to allow Doctors to remove the corpse from the Ground
 * @author Bing Xian
 *
 */
public class CleaningUpCorpseBehaviour implements Behaviour{

	/**
	 * This function is used to clean up corpse on the Ground
	 * @param actor	A Doctor
	 * @param map	The Town Game Map
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		int x = map.locationOf(actor).x();
		int y = map.locationOf(actor).y();
		double probability = Math.random();
		try {
			if (probability <= 0.2) {
				for (int i = -1; i<2; i++) {
					for (int j = -1; j < 2; j++) {
						for (Item item : map.at(x+i, y+j).getItems()) {
							if(item.getDisplayChar() == '%') {
								return new CleaningUpCorpseAction(item, x+i,y+j); 
							}
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		return null;
	}

}
