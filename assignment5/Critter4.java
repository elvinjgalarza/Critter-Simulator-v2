package assignment5;

/* Abed is Troy's best friend. He likes
   being independent, so he will move
   diagonally to the bottom left.
   
   When Abed has little Abed's, he wants
   them to be born above him, so they
   will be placed towards the north.
   
   Abed would never fight Troy, but if
   he were to meet another Critter besides
   Troy, he will get into a heated debate
   with them about the media and fight them.
   
 */

public class Critter4 extends Critter {

    @Override
    public String toString () { return "A"; }

    @Override
    public void doTimeStep() {
        walk(5);
        run(5);
        if (getEnergy() >= Params.min_reproduce_energy) {
            Critter4 child = new Critter4();

            reproduce(child, 2);
        }
    }

    @Override
    public boolean fight(String opponent) {
        if(opponent.equals("T")){
            return false;
        }
        return true;
    }
    
	public static void runStats(java.util.List<Critter> critter) {
		System.out.println("There are " + critter.size() + " Abed's on the board.");
	}
    
	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor() {
		return javafx.scene.paint.Color.PALEGOLDENROD;
	}
	@Override
	public javafx.scene.paint.Color viewOutlineColor() {
		return javafx.scene.paint.Color.BLACK;
	}

}