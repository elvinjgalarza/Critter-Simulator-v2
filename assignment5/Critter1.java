package assignment5;

/* CRITTERS2 Critter1.java
 * EE422C Project 4 submission by
 *
 * Elvin J. Galarza
 * ejg2298
 * 15455
 *
 * Bianca Antonio
 * bla774
 * 15510
 *
 * Slip days used: <0>
 * Spring 2018
 *
 */

/* The Morty Critter's life has always been down.
   It only knows being down, and so it moves downward.
   It spits out babies downwards. It always runs away
   from fights; however, when Morty is on the same spot as
   a Rick Critter, Morty becomes enraged and fights him.
 */

public class Critter1 extends Critter {

    @Override
    public String toString () { return "M"; }

    @Override
    public void doTimeStep() {
        walk(6);
        run(6);
        if (getEnergy() >= Params.min_reproduce_energy) {
            Critter1 child = new Critter1();
            reproduce(child, 6);
        }
    }

    @Override
    public boolean fight(String opponent) {
        if (opponent.equals("R")) {
            return true;
        }
        return false;
    }
    
	public static void runStats(java.util.List<Critter> critter) {
		System.out.println("There are " + critter.size() + " Morty's on the board.");
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.SQUARE;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.LIGHTYELLOW;
	}
	@Override
	public javafx.scene.paint.Color viewOutlineColor() {
		return javafx.scene.paint.Color.BLACK;
	}

} 