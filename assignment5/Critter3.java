package assignment5;/* This Critter is named Troy. Troy likes to
   live an uncomplicated life, so he only
   moves eastward.
   
   Troy only makes little Troy's when he has at
   least 100 energy.
   
   Troy is Abed's best friend and would never
   fight Abed. However, since he is very
   protective of his friend Abed, he will fight
   anyone else that runs into him just so Abed
   would be safe.
 */

public class Critter3 extends Critter {

    @Override
    public String toString () { return "T"; }

    @Override
    public void doTimeStep() {
        walk(0);
        run(0);
        if (getEnergy() >= 100) {
            Critter3 child = new Critter3();

            reproduce(child, 2);
        }
    }

    @Override
    public boolean fight(String opponent) {
        if(opponent.equals("A")){
            return false;
        }
        return true;
    }
    
	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.DARKORANGE;
	}
	@Override
	public javafx.scene.paint.Color viewOutlineColor() {
		return javafx.scene.paint.Color.BLACK;
	}

}