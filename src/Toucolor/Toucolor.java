/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toucolor;

import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.io.File;

/**
 *
 * @author loren
 */
public class Toucolor extends PApplet {

    /**
     * CONSTRUCTOR
     * Zorgt ervoor dat het een volwaardige java-application wordt
     * dit zou de 'mooie manier zijn om een processing app in netbeans te maken
     * src: http://stackoverflow.com/questions/31845686/making-different-screens-using-processing-in-eclipse
     */
    public static void main(String args[]) {
        PApplet.main(new String[]{"Toucolor.Toucolor"});
    }

    /**
     * PRIVATE VARIABLES
     */
    //width & height of world
    private static int WORLDWIDTH = 1280;
    private static int WORLDHEIGHT = 720;
    //temp playerX --> goes into object later
    private int playerX;
    //array of the world which stores the Level

    //levelmanager functions
    private Level currentLevel;
    public Startscreen menu;
    private String status;
    private int levelToLoad;

    //initializing variables
    private LoadScreen loadScreen;
    private String[] levelFiles;
    private int numberOfLevels;
    private String[] menuTexts = {"Play", "Score"};


    /**
     * initializes the world
     * initializes a Level on startup (will be changed)
     */
    @Override
    public  void setup() {
        //stroke(155,0,0);
        playerX = 600; //temp test var
        frameRate(144);
        status = "initializing";
        loadScreen = new LoadScreen("Initializing, Please wait.", this);
        thread("initWorld");
    }

    /**
     * define and initialize settings for the world
     */
    @Override
    public void settings() {
        size(WORLDWIDTH, WORLDHEIGHT);

    }

    /**
     * HERE HAPPENS THE RENDERING
     */
    @Override
    public void draw() {
        if(status.equals("initializing")) {
            //show first loading screen
            loadScreen.renderLoadScreen();
        }
        else if(status.equals("startscreen")) {
            menu.renderStartScreen();
        }
        else if(status.equals("levelSelectScreen")) {
            menu.renderStartScreen();
        }
        else if(status.equals("loadScreen")) {
            loadScreen.renderLoadScreen();
        }
        else if( status.equals("playing")){
            currentLevel.renderLevel(playerX);
        }

    }

    @Override
     public void keyPressed() {
    //TODO: een defitge logica schrijven voor dit
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                //enter wordt ingedrukt
                switch (status) {
                    case "startscreen":
                        //startscherm is geladen
                        if(menu.getTextOfSelected().equals(menuTexts[0])) {
                            //PLAY HAS BEEN SELECTED
                            menu = new Startscreen(this.numberOfLevels, this);
                            this.status = "levelSelectScreen";
                        } else {
                            //SCORE HAS BEEN SELECTED
                            //TODO:ROBBE'S Code here
                        }
                        break;
                    case "levelSelectScreen":
                        //levle selectiescherm is geladen
                        this.levelToLoad = menu.getIdOfSelected() + 1; //set the number of level to load
                        thread("startLevel"); //init the level in seperate thread
                        //create new loading screen
                        this.loadScreen = new LoadScreen("Loading, Please wait.", this);
                        this.status = "loadScreen"; //change status
                        break;
                }
               break;
            default:
                //andere toets
                switch (status) {
                    case "startscreen":
                        menu.keyPressed(keyCode);
                        break;
                    case "levelSelectScreen":
                        menu.keyPressed(keyCode);
                        break;
                    case "loadScreen":

                        break;
                    case "playing":

                        break;
                }
        }

    }

    /**
     * creates new level object and initializes it.
     * makes it ready to render the level
     * this function is used in a seperate thread
     */
    public void startLevel() {
        this.currentLevel = new Level(this, levelFiles[this.levelToLoad -1 ]);
        this.status = "playing";

    }

    /**
     * creates menu screen
     * loads in all the files for the levels, blocks and other info
     * creates player
     *
     */
    public void initWorld() {
        //check if the files for the levels exist
        numberOfLevels = 0;
        boolean fileExists = true;
        //load necessary files
        for (int i = 0; fileExists; i++) {
            File f = new File(sketchPath() + "/data/level" + (i+1) + ".csv");
            if(f.exists() && !f.isDirectory()) {
                numberOfLevels++;
            } else { fileExists = false;}
        }

        levelFiles = new String[numberOfLevels];
        for(int i = 0; i < numberOfLevels; i++) {
            levelFiles[i] = "level" + (i+1) + ".csv";
        }
        print("Number of levels: " + numberOfLevels +"\n");


        //creating menu screen
        menu = new Startscreen( menuTexts, this);

        this.status = "startscreen";
    }


}
