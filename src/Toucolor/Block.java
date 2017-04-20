package Toucolor;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * Created by loren on 17/03/2017.
 */
public class Block {

    /**
     * PRIVATE VARIABLES
     */
    private int id;
    private boolean collision;
    private String name;
    private PImage img;
    private PApplet applet;

    //constructor
    public Block(int id, String name, String imgFileName, boolean collision, PApplet applet) {
        this.id = id;
        this.collision = collision;
        this.name = name;
        this.applet = applet;

        img = applet.loadImage(imgFileName); //load the image
    }

    //renders this block on given location
    public void renderblock(int x, int y) {
        applet.imageMode(PConstants.CORNER);
        applet.image(img, x, y, 80, 80);
    }



    //getters and setters
    public int getId() {
        return id;
    }

    public boolean isCollision() {
        return collision;
    }

    public String getName() {
        return name;
    }

}
