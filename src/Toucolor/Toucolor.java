package Toucolor;

/**
 * Created by loren on 02/04/2017.
 */
import processing.core.PApplet;
import processing.core.PImage;

public class Toucolor  extends PApplet {
    public static void main(String args[]) {
        PApplet.main(new String[]{"Toucolor.Toucolor"});

    }


    //Wereld variabelen
    static int WORLDWIDTH = 1280;
    static int WORLDHEIGHT = 720;
    static int BLOCKSIZE = 80;

    Enemy goedkoop_sletje = new Enemy(3,1,0.01f,400,640);
    Enemy[] Enemies = {goedkoop_sletje};

    Animation playerWandelen, enemyWandelen;

    //Player variabelern
    float xpos;
    float ypos;
    private Player speler = new Player();



    @Override
    public void settings() {
        size(WORLDWIDTH, WORLDHEIGHT);

    }


    @Override
    public void setup(){
        frameRate(144);
        playerWandelen = new Animation("Toucolooor", 4);
        enemyWandelen = new Animation("soccer_player_fro", 1); //testenemy

    }

    @Override
    public void draw(){
        background(225);
        speler.keyUse();
        EnemiesBehaviour(speler.playerX, speler.playerY);
        enemyWandelen.display(goedkoop_sletje.posX,goedkoop_sletje.posY,'n',0);
        playerWandelen.display(speler.playerX, speler.playerY, speler.lastMove,speler.imgCounter);
        tekenmap();
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


    private void tekenmap(){
        for (float[] hoevcoord:Player.fullCoords) {
            xpos = hoevcoord[0];
            ypos = hoevcoord[1];
            fill(255,0,0);
            rect(xpos,ypos, BLOCKSIZE, BLOCKSIZE);
        }

    }

    class Animation {
        PImage[] images;
        int imageCount;
        int frame;

        Animation(String imagePrefix, int count) {
            imageCount = count;
            images = new PImage[imageCount];
            for (int i = 0; i < imageCount; i++) {
                String filename = imagePrefix + nf(i, 1) + ".png";
                images[i] = loadImage(filename);
            }
        }

        void display(float xpos, float ypos, char lastM, int frameR) {
            if(frameR < (144/4)) {
                frame = 0;
            }else if(frameR < (144/4)*2){
                frame = 1;
            }else if(frameR < (144/4)*3 ){
                frame = 2;
            }else if(frameR < (144/4) *4){
                frame = 3;
            }else{
                speler.imgCounter = 0;
            }
            if(lastM == 'r' || lastM == 'n') {
            image(images[frame], xpos, ypos, BLOCKSIZE, BLOCKSIZE);
        }else{
            pushMatrix();
            scale(-1,1);
            image(images[frame], - (xpos + images[frame].width), ypos, BLOCKSIZE, BLOCKSIZE);
            popMatrix();
        }
    }

}

}

