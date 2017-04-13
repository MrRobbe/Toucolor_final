/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toucolor;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.*;


/**
 *
 * @author loren
 */
public class Level {

    /**
     * changeable variables:
     * deze variablen hebben te maken met de algeme looks van het programma
     * kunnen achteraf veranderd worden om het spel mooier te maken
     */
    private static int blockWidth = 80;

    /**
     * PRIVATE VARIABLES
     */
    //applet
    private PApplet applet;
    //alles voor loadTiles();
    private int numberOfTiles; //aantal verschillende tiles
    private Block[] tileBlocks; //array met alle afbeeldingen van de tiles
    private static String tileFileName = "test.csv"; //file met alle namen van afbeelding
    private String levelFileName; //name of the file which describes the Level
    private int[][] levelMap; //an array which holds the map



    /**
     *
     * @param sketch sketch where Level plays in
     */
    Level(Toucolor sketch, String levelFilename) {
        this.applet = sketch;
        this.levelFileName = levelFilename;
        loadTiles();
        loadlevel();
    }

    //not sure if this is used somewhere
    public void level(PApplet applet, String levelFileName) {
        this.applet = applet;
        this.levelFileName = levelFileName;
        loadTiles();
        loadlevel();

    }

    /**
     * Laad alle verschillende afbeeldingen en geeft ze een specifiek nummer
     * alle tiles worden uit een csv file gehaald
     * tiles worden opgeslaan in een array van Blocks: zie Block class
     */
    private void loadTiles() {
        //load the table
        Table myTable = applet.loadTable(tileFileName, "header, csv");

        //get number of pictures
        int rowCount = myTable.getRowCount();

//        //debug info
//        PApplet.print("rowcount= " + rowCount + "\n");

        //create the array for the pictures & names
        tileBlocks = new Block[rowCount];
        //get a new row and load the image and name of the tile
        //all print statements are for debugging
        for (int i = 0; i < rowCount; i++) {
            TableRow row = myTable.getRow(i); //get a new row

//            //debug info
//            PApplet.print("row loaded\n");
//            PApplet.print("id = " + row.getInt("id") + "\n");

            int id = row.getInt("id"); //get id of img
            String name = row.getString("name");
            String imgFileName = row.getString("filename");
            boolean collision = PApplet.parseBoolean("true");

            tileBlocks[id] = new Block(id, name, imgFileName, collision, applet); //load the img into the array

//            //debug info
//            PApplet.print("img loaded:" + row.getString("filename") + "\n");
        }
    }

    /**
     * Laad de csv file die de map van het Level beschrijft
     * slaat het Level op in een array
     */
    private void loadlevel() {
        //load the file which holds the Level
        Table myTable = applet.loadTable(levelFileName, "csv");

        //get number of rows & columns in the Level
        int rowCount = myTable.getRowCount();
        int columnCount = myTable.getColumnCount();

        //create the array for the Level
        levelMap = new int[columnCount][rowCount];

        //loop through the rows
        for (int i = 0; i < rowCount; i++) {
            TableRow row = myTable.getRow(i);//get next row
            //loop through the columns
            for(int c = 0; c < columnCount; c++) {
                levelMap[c][i] = row.getInt(c);//map the int to right position in array
                //PApplet.print(i +" " + c + "\n");
            }
        }
    }

    /**
     * renders the Level on screen based on the players location
     * todo: decide how to render Level --> see descriptoin
     *
     * @param playerX x-coordinate of player
     */
    void renderLevel(int playerX) {
        /*
         * the position to start drawing:
         * 80 -( (playerX -600) % 80 )
         *  playerX -600 has to be positive and we need the remainder --> modulo
          */
        int startScrX =  -((((playerX - 600) < 0) ? 0 : (playerX - 600)) % blockWidth);
        int startBlock = ((playerX - 600) / blockWidth); //calculates on which block to start
        //test commnet
        //PApplet.print(levelMap.length);
        for (int i = 0; i < 16; i++) {
            for (int u = 0; u < 9; u++) {
                tileBlocks[levelMap[i+startBlock][u]].renderblock( startScrX + (i * 80), u * 80);
                //PApplet.print("Rendering block " + levelMap[i][u] + " on x: " + startScrX  + " and y: " + (u * 80) + "\n");
            }
        }



//        //kijker
        applet.stroke(0,0,0);
        applet.fill(0,0,0);
        applet.rect(0, 0, applet.width /13, applet.height);
        applet.rect(applet.width - applet.width /13, 0, applet.width /13, applet.height);

    }

    /**
     * renders a block on a given location
     *
     * @param x location on x-axis to render image
     * @param y location on y-axis to render image
     * @param img image to render
     */
    private void renderBlock(int x, int y, PImage img) {
        applet.image(img, x, y, 80, 80);
    }

    /**
     * test function
     * @param x qsdf
     * @param y qsdf
     * @param width qdsf
     * @param height qsdf
     * @param applet  Applet to draw on
     *
     */
    public void drawRect(int x, int y, int width, int height, PApplet applet) {
        //rect(x, y, width, height);
        applet.rect(10,10,10,10);
    }
}
