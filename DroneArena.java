package DroneSimulation;

import java.util.Random;
import java.util.ArrayList;

/**
 * 
 * @author gphil(28000050)
 */
public class DroneArena {
	private static int xmax, ymax;
	private static int valx, valy;
	private static Drone d;
	static ArrayList<Drone> drones;

	/**
	 * Constructor Assigns dimensions to the Arena
	 * 
	 * @param a x-dimension
	 * @param b y-dimension
	 */
	DroneArena(int a, int b) {
		xmax = a;
		ymax = b;
		drones = new ArrayList<Drone>();
	}

	/**
	 * search arraylist of drones to see if there is a drone at x,y
	 * 
	 * @param x
	 * @param y
	 * @return true if there is a drone, false otherwise
	 */
	public static boolean getDroneAt(int x, int y) {
		for (int i = 0; i < drones.size(); i++) {
			if (drones.get(i).isHere(x, y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test if drone can move to the next position
	 * 
	 * @param x
	 * @param y
	 * @return true if it can, false if not
	 */
	public static boolean canMoveHere(int x, int y) {
		if (getDroneAt(x, y)) {
			return false;
		} else if ((x == 0 || y == 0) || (x == xmax - 1 || y == ymax - 1)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * moves all Drones
	 * 
	 * @return null
	 */
	public static Drone moveAllDrones() {
		Drone md;
		for (int i = 0; i < drones.size(); i++) {
			md = drones.get(i);
			md.tryToMove();
		}
		return null;
	}

	/**
	 * show all the drones in the interface
	 * 
	 * @param c the canvas in which drones are shown
	 */
	public void showDrones(ConsoleCanvas c) {
		for (int i = 0; i < drones.size(); i++) {
			drones.get(i).displayDrone(c);
		}
	}

	/**
	 * adds Drone to the Arraylist taken from a file
	 * 
	 * @param a x-position
	 * @param b y-position
	 */
	void inputDrone(int a, int b) {
		if (drones.size() == 0) {
			Drone.id = 0;
		}
		d = new Drone(a, b, Direction.getRandomDirection());
		drones.add(d);
	}

	/**
	 * creates the Drone with random values and adds it to the Arraylist
	 */
	void addDrone() {
		rNum();
		d = new Drone(valx, valy, Direction.getRandomDirection());
		drones.add(d);
	}

	/**
	 * creates random values
	 */
	void rNum() {
		Random rx = new Random();
		valx = rx.nextInt(xmax - 1);
		Random ry = new Random();
		valy = ry.nextInt(ymax - 1);
	}

	public String toString() {
		String res = "";
		res = res + "Arena size: " + xmax + " , " + ymax + " " + drones;
		return res;
	}

	public static void main(String[] args) {

		DroneArena a = new DroneArena(20, 10);
		for (int i = 0; i < 6; i++) {
			a.rNum();
			a.addDrone();
		}
		System.out.println(a.toString());
		d.tryToMove();
		for (int i = 0; i < 4; i++) {
			moveAllDrones();
			System.out.println(a.toString()); // print where is
		}
	}
}