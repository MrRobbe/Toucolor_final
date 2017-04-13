package Toucolor;

/**
 * Created by loren on 02/04/2017.
 */
import processing.core.PApplet;

public class Toucolor  extends PApplet {

    /**
     * PRIVATE VARIABLES
     */
    //width & height of world
    private int worldWidth = 1280;
    private int worldHeight = 720;


    public static void main(String args[]) {
        PApplet.main(new String[]{"Toucolor.Toucolor"});
    }

    /**
     * initializes the world
     * initializes a Level on startup (will be changed)
     */
    @Override
    public  void setup() {
        frameRate(144);
    }

    /**
     * define and initialize settings for the world
     */
    @Override
    public void settings() {
        size(worldWidth, worldHeight);
    }

    /**
     * HERE HAPPENS THE RENDERING
     */
    @Override
    public void draw() {

    }
}
