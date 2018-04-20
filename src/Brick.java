import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public class Brick {

    private int x;
    private int y;
    private int w = 30;
    private int h = 10;
    private int centerX = x+(w/2);
    private int centerY = y+(h/2);
    private boolean isDead = false;
    private int lives = 3;

    public Brick(int x, int y){
        this.x = x;
        this.y = y;
        centerX = x+(w/2);
        centerY = y+(h/2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, w, h);
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public boolean isAlive(){
        if(isDead){
            return false;
        }else{
            return true;
        }
    }

    public int getLives() {
        return lives;
    }

    public void kill(){
        lives--;
        if(lives == 0){
            this.isDead = true;
        }

    }
}
