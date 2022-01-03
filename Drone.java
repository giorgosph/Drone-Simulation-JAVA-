package DroneSimulation;

/**
 * @author gphil(28000050)
 */
public class Drone {

	private int x;
	private int y;
	static int id;
	private int ids;
	Direction travel, initTravel;

	/**
	 * Constructor creates Drone giving them unique id and position
	 * 
	 * @param a  x-position
	 * @param b  y-position
	 * @param d  direction
	 */
	public Drone(int a, int b, Direction d) {
		id++;
		ids = id;
		x = a;
		y = b;
		if (x == 0 && y == 0) {
			x += 1;
			y += 1;
		} else if (x == 0) {
			x += 1;
		} else if (y == 0) {
			y += 1;
		}
		travel = d;
		initTravel = travel;
	}

	/**
	 * The Drone object test if there is space to move on the current Direction
	 * Otherwise it will change Direction If there is no space it will remain on
	 * constant position
	 * 
	 * @return 0
	 */
	public int tryToMove() {
		int tx = 0;
		int ty = 0;

		switch (travel) {
		case North:
			ty = y - 1;
			if (DroneArena.canMoveHere(x, ty)) {
				y -= 1;
				break;
			} else {
				travel = Direction.nextDirection(travel);
				if (travel == initTravel) {
					break;
				} else {
					tryToMove();
				}
				break;
			}
		case East:
			tx = x + 1;
			if (DroneArena.canMoveHere(tx, y)) {
				x += 1;
				break;
			} else {
				travel = Direction.nextDirection(travel);
				if (travel == initTravel) {
					break;
				} else {
					tryToMove();
				}
				break;
			}
		case South:
			ty = y + 1;
			if (DroneArena.canMoveHere(x, ty)) {
				y += 1;
				break;
			} else {
				travel = Direction.nextDirection(travel);
				if (travel == initTravel) {
					break;
				} else {
					tryToMove();
				}
				break;
			}
		case West:
			tx = x - 1;
			if (DroneArena.canMoveHere(tx, y)) {
				x -= 1;
				break;
			} else {
				travel = Direction.nextDirection(travel);
				if (travel == initTravel) {
					break;
				} else {
					tryToMove();
				}
				break;
			}
		}
		return 0;
	}

	/**
	 * Is the drone at this x,y position
	 * 
	 * @param sx x position
	 * @param sy y position
	 * @return true if drone is at sx,sy, false otherwise
	 */
	public boolean isHere(int sx, int sy) {
		if (sx == x && sy == y) {
			// coords = true;
			return true;
		} else
			return false; // coords = false;
	}

	/**
	 * Takes all the information in a String
	 */
	public String toString() {
		String res = "";
		res = res + "Drone " + ids + " is at ( " + x + " . " + y + " ) " + " moving at " + travel + "\n\t";
		return res;
	}

	/**
	 * Displays the Drone on the Canvas in appropriate position
	 * 
	 * @param c current Canvas
	 */
	public void displayDrone(ConsoleCanvas c) {
		c.showIt(x, y, 'D');
	}

	public static void main(String[] args) {
		Drone d1 = new Drone(8, 9, Direction.South);
		System.out.println(d1.toString());
		/*
		 * Drone d2 = new Drone(7, 9, Direction.East);
		 * System.out.println(d2.toString()); Drone d3 = new Drone(3, 2,
		 * Direction.East); System.out.println(d3.toString());
		 */
	}

}
