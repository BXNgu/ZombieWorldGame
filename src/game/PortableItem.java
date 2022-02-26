package game;

import java.util.Random;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {
	
	Random random = new Random();
	
	private int turns;
	private static int id = 0;

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
		turns = random.nextInt((10-5)+1) + 5;
	}
	
	/**
	 * This method will be called each round to keep track of the turns of corpse item
	 * If there is a corpse and the number of turns becomes 0, the corpse will turn into a zombie
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		
		turns--;
		try {
			if (turns == 0 && this.displayChar == '%') {				
				location.removeItem(this);
				System.out.println(this.name.substring(5,this.name.length()) + " rise from dead");
				location.addActor(new Zombie(this.name.substring(5,this.name.length())));
			}
		} catch (Exception e) {
			turns++;
		}
	}
}
