import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {

    private int x = GameBoard.getWIDTH()/2;
    private int y = GameBoard.getHEIGHT()-50;
    private int w = 60;
    private int h = 8;
    private boolean leftPressed, rightPressed;
    private int xCenter;

    public Paddle(){


    }

    public void move(){

        if(leftPressed && !(x < 0)) x = x - 2;
        if(rightPressed && !(x+w > GameBoard.getWIDTH())) x = x + 2;

        xCenter = x + (w/2);


    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

    }

    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getXCenter() {
        return xCenter;
    }

    public Rectangle getBounds(){

        return new Rectangle(x, y, w, h);
    }

}
