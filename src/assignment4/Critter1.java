package assignment4;

/* CRITTERS Critter.java
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
import assignment4.Critter.TestCritter;

/* The Morty Critter's life has always been down.
   It only knows being down, and so it moves downward.
   It spits out babies downwards. It always runs away
   from fights; however, when Morty is on the same spot as
   a Rick Critter, Morty becomes enraged and fights him.
 */


public class Critter1 extends TestCritter {

    /**
     * String representation of the Critter to display on board
     * @return returns the string representation of Morty
     */
    @Override
    public String toString () { return "M"; }

    /**
     * Does the timestep for this Critter
     */
    @Override
    public void doTimeStep() {
        walk(6);
        run(6);
        if (getEnergy() >= Params.min_reproduce_energy) {
            Critter1 child = new Critter1();
            reproduce(child, 6);
        }
    }

    /**
     * Tells if should fight or not
     * @param opponent Opponent Critter
     * @return boolean that says to fight/not to fight
     */
    @Override
    public boolean fight(String opponent) {
        if (opponent.equals("R")) {
            return true;
        }
        return false;
    }

}