package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**BONUS FEATURE
 * A Doctor
 * @author Wincent
 *
 */
public class Doctor extends Human {
	
	private Behaviour[] behaviours = {
			new BackToHospitalBehaviour(Hospital.class, 100),
			new CleaningUpCorpseBehaviour(),
			new HealingBehaviour(),
			new WanderBehaviour()
	};

	/**
	 * Constructor
	 * 
	 * @param name Doctor's name
	 */
	public Doctor(String name) {
		super(name, 'D', 100);
		for (int i = 0; i < 6; i++) {
			this.addItemToInventory(new PortableItem("First Aid Kit", '$'));
		}
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}

}
