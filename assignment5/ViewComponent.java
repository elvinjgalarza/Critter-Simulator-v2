package assignment5;

import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class ViewComponent {

    static int size = 10;


    /**
     * See FAQ Q6: What does scaling mean? Scaling of the window
     * Scale the given Parameters to create an aesthetic board
     * The size is inversely proportional to the given width (columns)
     * and given height (rows)
     */
    public static void scaleParams(){
        size = 5;
    }

    /**
     * Creates the board.
     */
    private static void createBoard(){
        for(int columns = 0; columns < Params.world_width; columns++){
            for(int rows = 0; rows < Params.world_height; rows++){
                Shape board = new Rectangle(size, size);
                board.setFill(Color.BROWN);
                board.setStroke(Color.WHITE);
                Main.makeWindow.add(board, columns, rows);
            }
        }
    }
    /**
     * Creates the sprites for the Critter object
     * @param shape CritterShape
     * @return Shape (sprite)
     */
    static Shape convertToShape(Critter.CritterShape shape){
        Shape s = null;
        switch(shape){
            case CIRCLE:
                s = new Circle(size/2);
                return s;
            case SQUARE:
                s = new Rectangle(size, size);
                return s;
            case TRIANGLE:
                s = convertToTriangle();
                return s;
            case DIAMOND:
                s = convertToDiamond();
                return s;
            case STAR:
                s = convertToStar();
                return s;
        }
        return s;
    }
    /**
     * Creates the Triangle sprite
     * @return the shape of the sprite
     */
    private static Shape convertToTriangle() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                (double) size / 2.0, 1.0,
                (double) size - 1.0, (double) size - 1.0,
                1.0, (double) size - 1.0});
        return triangle;
    }
    /**
     * Creates the Diamond sprite
     * @return the shape of the sprite
     */
    private static Shape convertToDiamond() {
        Polygon diamond = new Polygon();
        diamond.getPoints().addAll(new Double[]{
                (double)size/2.0, 1.0,
                (double)size - 1.0, (double)size/2.0,
                (double)size/2.0, (double)size - 1.0,
                1.0, (double)size/2.0
        });
        return diamond;
    }
    /**
     * Creates the Star sprite
     * @return the shape of the sprite
     */
    private static Shape convertToStar(){
        Polygon star = new Polygon();
        star.getPoints().addAll(new Double[]{
                (double)size/2.0, 1.0,
                (double)size - 1.0, (double)size/2.0,
                (double)size/2.0 + (double)size/4.0, (double)size/2.0 + 1.0,
                (double)size - 2.0, (double)size - 1.0,
                (double)size/2.0, (double)size/2.0 + (double)size/4.0,
                2.0, (double)size-1.0,
                (double)size/2.0 - (double)size/4.0, (double)size/2.0 + 1.0,
                1.0, (double)size/2.0

        });
        return star;
    }


    /**
     * Create the actual sprite of the Critter.
     * @param shape shape of the Critter
     * @param x_coord x coordinate of the Critter
     * @param y_coord y coordinate of the Critter
     * @param color color of the Critter
     * @param outline outline color of the Critter
     * @param fill filled in color of the Critter
     */
    public static void displayCritterSprite(Critter.CritterShape shape, int x_coord, int y_coord,
                                            Color color, Color outline, Color fill){
        Shape s = convertToShape(shape);
        if (color.equals(fill)) {
            s.setFill(fill);
            if (outline.equals(Color.BROWN)) {
                s.setStroke(fill);
            }
            else {
                s.setStroke(outline);
            }
        }
        else if (color.equals(Color.BROWN)) {
            s.setFill(fill);
            if (outline.equals(Color.BROWN)) {
                s.setStroke(fill);
            }
            else {
                s.setStroke(outline);
            }
        }
        else {
            s.setFill(color);
            if (outline.equals(Color.BROWN)) {
                s.setStroke(color);
            }
            else {
                s.setStroke(outline);
            }
        }
        Main.makeWindow.add(s, x_coord, y_coord);
    }

    public static void displayBoard(){
        Main.makeWindow.getChildren().clear();
        createBoard();
    }
}