package Toucolor;

/**
 * Created by loren on 02/04/2017.
 */
import processing.core.PApplet;

public class Toucolor  extends PApplet {
    public static void main(String args[]) {
        PApplet.main(new String[]{"Toucolor.Toucolor"});


    }


    //Wereld variabelen
    int sizeX = 1280;
    int sizeY = 720;
    static int blockSize = 80;

    Enemy goedkoop_sletje = new Enemy(1,1,0.01f,400,640);


    Enemy[] Enemies = {goedkoop_sletje};

    //Player variabelern
    float xpos;
    float ypos;
    private Player speler = new Player();

    //Alle enemies die bestaan met hun eigenschappen





    @Override
    public void settings() {
        size(sizeX, sizeY);
    }


    @Override
    public void setup(){
        frameRate(144);
    }

    @Override
    public void draw(){
        background(225);
        EnemiesBehaviour(speler.playerX, speler.playerY);
        PlayerBehaviour();
        drawCube();
    }

    public void tekenMap(float x, float y) {
        fill(255,0,0);
        rect(x,y,blockSize,blockSize);

    }

    public void drawCube() {
        fill(255, 255, 0);
        rect(speler.playerX, speler.playerY, blockSize, blockSize);
        fill(0,200,0);
        rect(goedkoop_sletje.posX,goedkoop_sletje.posY, blockSize, blockSize);
    }

    public void keyPressed() {

        if (keyCode == RIGHT) {
            speler.rightPressed = true;
        }
        if (keyCode == LEFT) {
            speler.leftPressed = true;
        }
        if (keyCode == UP && !speler.isInAir) {
            speler.upIsPressed = true;
        }
        if(keyCode == DOWN){
            speler.downPressed = true;
        }

    }

    public void keyReleased() {
        if(keyCode == RIGHT){
            speler.rightPressed = false;
        }
        if(keyCode == LEFT){
            speler.leftPressed = false;
        }
        if(keyCode == DOWN){
            speler.downPressed = false;
        }
    }

    private void EnemiesBehaviour(float playerX, float playerY){
        for (Enemy vijand:Enemies) {
            if(vijand.EnemyBehave(playerX,playerY)){
                PApplet.println("DEUD");
                exit();
            }
        }
    }


    private void PlayerBehaviour(){
        for (float[] hoevcoord:Player.fullCoords) {
            xpos = hoevcoord[0];
            ypos = hoevcoord[1];
            tekenMap(xpos,ypos);
        }
        speler.keyUse();
    }

}

