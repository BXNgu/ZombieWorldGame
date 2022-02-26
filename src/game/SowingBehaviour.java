package game;

import java.util.HashMap;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A Sowing Behaviour that implements Behaviour class to allow actors to sow crops
 * @author Bing Xian
 *
 */
public class SowingBehaviour implements Behaviour {
		
	Random random = new Random();
	static HashMap<String,Crop> crops = new HashMap<String,Crop>();

	/**
	 * This method is used for fertilising crops and sowing crops
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		double probability = Math.random();
		int x = map.locationOf(actor).x();
		int y = map.locationOf(actor).y();
		if (map.at(x,y).getGround().getDisplayChar() == 'u'){
			return new FertiliseAction(crops.get(x+"0"+y));
		}
		Crop crop = new Crop();
		if (probability <= 0.33) {
			for (int i = -1; i < 2; i++ ) {
				for (int j = -1; j < 2; j++) {
					try {
						if (map.at(x+i,y+j).getGround().getDisplayChar() == '.') {
							crops.put((x+i)+"0"+(y+j), crop);
							map.at(x+i, y+j).setGround(crop);
							return new SowingAction();	
						}
					} catch (Exception e) {
						
					}
				}
			}
		}
		return null;
	}

}
