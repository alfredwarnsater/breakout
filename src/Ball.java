import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public class Ball {

    private double x = GameBoard.getWIDTH()/2;
    private double y = GameBoard.getHEIGHT()/2;
    private int r = 8;
    private double xVel = 0;
    private double yVel = 2;
    private double xOld;
    private double yOld;
    private double centerX = x+r/2;
    private double centerY = y+r/2;

    public Ball(){


    }

    public void move(){

        xOld = x;
        yOld = y;
        y = y + yVel;
        x = x + xVel;
        centerX = x+(r/2);
        centerY = y+(r/2);


    }

    public double getCenterX() {
        return centerX;

    }

    public double getCenterY() {
        return centerY;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds(){

        return new Rectangle((int)Math.round(x), (int)Math.round(y), r, r);
    }

    public void inverseYVel(){
        this.yVel = yVel*-1;
    }

    public void inverseXVel(){
        this.xVel = xVel*-1;
    }

    public void setXVel(double v){
        this.xVel = v;
    }

    public void setYVel(double v){
        this.yVel = v;
    }

    public int getR() {
        return r;
    }


    public double getxOld() {
        return xOld;
    }

    public double getyOld() {
        return yOld;
    }
}
