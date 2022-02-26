package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		NewWorld world = new NewWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree(),
				new ShotgunAmmunitionBox(), new SniperAmmunitionBox(), new Hospital());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);
		
		List<String> townMap = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"......+++++.....................................................................",
		"......+++++..................................................+++++..............",
		"......+++++......................#..........#...............+++++++.............",
		".................................#..........#...............++++++++............",
		".................................#..........#................++++++.............",
		".................................#..........#...................................",
		"............................######..........######..............................",
		"............................#....................#..............................",
		"............................#....................#..............................",
		".{}.................#########....................#########......................",
		".........+++++........................^^^.......................................",
		".........+++++........................^^^.......................................",
		"...........+++........................^^^.......................................",
		"....................#########....................#########......................",
		"............................#....................#..............................",
		"............................#....................#..............................",
		"............................######..........######..............................",
		"...................+++...........#..........#.....................++++++++......",
		"..................+++............#..........#......................+++++++......",
		"...................+++...........#..........#....................+++++..........",
		".................................#..........#......................++++++.......",
		"................................................................................",
		"...+++++........................................................................",
		"...+++++........................................................................",
		"................................................................................");
		GameMap town = new GameMap(groundFactory, townMap);
		world.addGameMap(town);
		
		Vehicle car = new Vehicle("Car", 'V');
        car.addAction(new MoveActorAction(town.at(2, 13), "to Town!"));
        gameMap.at(5, 16).addItem(car);
        
        Vehicle car2 = new Vehicle("Car", 'V');
        car2.addAction(new MoveActorAction(gameMap.at(5, 16), "to Home!"));
        town.at(2, 13).addItem(car2);
        		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(42, 15));
		
	    // Place some random humans
		String[] humans = {"Carlton","May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		
		// Place some random farmers
		String[] farmer = {"Bob","Chris","Kean"};
		int x2, y2;
		for (String name : farmer) {
			do {
				x2 = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y2 = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x2, y2).containsAnActor());
			gameMap.at(x2,  y2).addActor(new Farmer(name));	
		}
		
		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());
		town.at(41, 15).addItem(new SniperRifle());
		town.at(42, 15).addItem(new Shotgun());
		
		// FIXME: Add more zombies!
		gameMap.at(42, 23).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	
				
		// Place humans in Town
		town.at(34, 6).addActor(new Human("Jared"));
		town.at(35, 8).addActor(new Human("Patrick"));
		town.at(36, 10).addActor(new Human("Lizzie"));
		town.at(34, 12).addActor(new Human("Harlen"));
		town.at(38, 14).addActor(new Human("Briana"));
		town.at(43, 12).addActor(new Human("Zeynep"));
		town.at(44, 16).addActor(new Human("Johan"));
		town.at(36, 18).addActor(new Human("Dana"));
		town.at(37, 20).addActor(new Human("Isha"));
		town.at(34, 22).addActor(new Human("Reanna"));
				
		// Place farmers in Town
		town.at(16, 8).addActor(new Farmer("Tait"));
		town.at(18, 24).addActor(new Farmer("Dickson"));
		town.at(70, 22).addActor(new Farmer("Benton"));
		town.at(67, 5).addActor(new Farmer("Johnny"));
			
		// Place zombie in Town
		town.at(30, 20).addActor(new Zombie("Biters"));
		town.at(20,  18).addActor(new Zombie("Creeper"));
		town.at(10,  4).addActor(new Zombie("Floaters"));
		town.at(60, 18).addActor(new Zombie("Geek"));
		town.at(1, 10).addActor(new Zombie("Lamebrains"));
		town.at(62, 12).addActor(new Zombie("Lurkers"));
		
		// Place Doctor in Town
		town.at(37, 13).addActor(new Doctor("Lucy"));
		town.at(39, 13).addActor(new Doctor("Ivory"));
		town.at(38, 15).addActor(new Doctor("Sam"));
		
		world.run();
	}
}
