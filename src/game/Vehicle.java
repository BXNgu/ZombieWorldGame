package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
/**
 * A vehicle to move from the open space to a town and back and forth
 * @author Bing Xian
 *
 */
public class Vehicle extends Item{

	public Vehicle(String name, char displayChar) {
		super(name, displayChar, false);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
}
