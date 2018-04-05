package assignment5;/* The Rick Critter's life has always been up.
   It only knows being up, and so it moves upward.
   It spits out tiny Ricks upwards. It's always
   looking to pick a fight; however, it does not
   fight Morty Critters.
 */

public class Critter2 extends Critter {

    @Override
    public String toString () { return "R"; }

    @Override
    public void doTimeStep() {
        walk(2);
        run(2);
        if (getEnergy() >= Params.min_reproduce_energy) {
            Critter2 child = new Critter2();

            reproduce(child, 2);
        }
    }

    @Override
    public boolean fight(String opponent) {
        if(opponent.equals("M")){
            return false;
        }
        return true;
    }
    
	@Override
	public CritterShape viewShape() {
		return CritterShape.STAR;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.POWDERBLUE;
	}
	@Override
	public javafx.scene.paint.Color viewOutlineColor() {
		return javafx.scene.paint.Color.WHITE;
	}

}