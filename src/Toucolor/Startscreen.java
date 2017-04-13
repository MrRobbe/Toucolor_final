package Toucolor;

import javafx.scene.Parent;
import processing.core.*;

import java.awt.print.Paper;

/**
 * Created by loren on 07/04/2017.
 */
class Startscreen {

    //PROPERTIES
    private PImage bImage; //background image
    private menuButton[] menuItems; //all the items in the menu
    private PImage logo;
    private PApplet applet;
    private menuButton selectedButton;

    //in the level selection screen

    Startscreen(String[] itemsText, PApplet applet) {
        //load the logo to display on top of page
        logo = applet.loadImage("menu_logo.png");
        //set on which applet to draw
        this.applet = applet;

        //all the items in the menu
        menuItems = new menuButton[itemsText.length];

        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i] = new menuButton(applet.width/2, 200 + (i * 120), 160, 80, itemsText[i], applet);
        }

        selectedButton = menuItems[0];
//
//        //all the coords for the buttons for level select
//        levelSelectPoints = new int[4][2];
//
//        levelSelectPoints[0][0] = 560;
//        levelSelectPoints[0][1] = 200;
//
//        levelSelectPoints[1][0] = 680;
//        levelSelectPoints[1][1] = 200;
//
//        levelSelectPoints[2][0] = 560;
//        levelSelectPoints[2][1] = 300;
//
//        levelSelectPoints[3][0] = 680;
//        levelSelectPoints[3][1] = 300;
    }

    //this function is called by the sketches
    void renderStartScreen() {
        applet.background(0);
        renderLogo();
        renderMenu();
        selectedButton.renderSelecter();
    }

    //selects the button the selector is on
    void enter() {

    }

    //draws the logo to screen
    private void renderLogo() {
        //render temp logo
        applet.imageMode(PConstants.CENTER); //now i can give the center point of the image
        applet.image(logo, applet.width/2, logo.height /2); //render image using center coordinates
    }

    //renders the menu
    private void renderMenu() {
        for(menuButton button : menuItems) {
            button.renderbutton();
        }

    }

    //a subclass for the menubuttons, will only be used here
    private class menuButton {
        private int posX;
        private int posY;
        private int buttonWidth;
        private int buttonHeight;
        private String buttonText;
        private int fillColor;
        private int textColor;

        private PApplet applet;

        menuButton(int x, int y, int width, int height, String text, PApplet applet) {
            posX = x; posY = y; buttonWidth = width; buttonHeight = height; buttonText = text; //assignments

            fillColor = 255;

            this.applet = applet;

        }

        //renders the button
        void renderbutton() {
            //render the button
            applet.fill(fillColor);
            applet.rectMode(PConstants.CENTER);
            applet.rect(posX, posY, buttonWidth, buttonHeight);
            //render the text
            applet.fill(textColor);
            applet.textSize(32);
            applet.textAlign(PConstants.CENTER);
            applet.text(buttonText, posX, posY);
        }

        //setters for the fillcolor and textcolor
        void setFillColor(int fillColor) {
            this.fillColor = fillColor;
        }

        void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        void renderSelecter() {
            applet.fill(255);
        }

        boolean isClicked() {
            return true;
        }
    }

}
