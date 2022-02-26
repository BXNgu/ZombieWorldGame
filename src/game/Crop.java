package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A Crop that is sowed by a farmer and slowly turn ripe and can be eaten by humans and player
 * 
 * @author Bing Xian
 *
 */
public class Crop extends Ground{
	
	private int turns = 20;
	
	/**
	 * Constructor
	 */
	public Crop() {
		super('u');
	}
	
	/**
	 * This method is used to fertilise the crop
	 */
	public void fertilise() {
		turns -= 10;
		if (turns <= 0) {
			turns = 1;
		}
	}

	/**
	 * This method will be called each round to know the number of rounds that has been performed
	 * The crop will turn into 'r' when it is ripe
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		turns --;
		if (turns == 0)
			displayChar = 'r';

	}
	
}
