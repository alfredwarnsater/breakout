import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameBoard extends JPanel implements Runnable{

    private static final int BOARD_WIDTH = 400;
    private static final int BOARD_HEIGHT = 400;
    private final int TIME_PER_FRAME = 10;
    private long ticks_from_last_collision = 20;

    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;

    private Thread animator;

    public GameBoard(){

        initGameBoard();
    }

    private void initGameBoard() {

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(new KeyboardAdapter());
        setBackground(new Color(238,245,219));

        paddle = new Paddle();
        ball = new Ball();
        bricks = new ArrayList();

        int x = 25;
        int y = 40;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 9; j++){
                bricks.add(new Brick(x, y));
                x = x + 40;
            }
            y = y + 20;
            x = 25;

        }

        animator = new Thread(this);
        animator.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        drawPaddle(g);
        drawBall(g);


        for(Brick brick : bricks){
            if(brick.isAlive()){
                drawBrick(g, brick);
            }

        }
    }

    public void drawPaddle(Graphics g){
        g.setColor(new Color(51,55,69));
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getW(), paddle.getH());

    }

    public void drawBall(Graphics g){
        g.setColor(new Color(51,55,69));
        g.fillRect((int)Math.round(ball.getX()), (int)Math.round(ball.getY()), ball.getR(), ball.getR());

    }

    public void drawBrick(Graphics g, Brick brick){
        if(brick.getLives() == 3){
            g.setColor(new Color(230,52,98));
        } else if(brick.getLives() == 2){
            g.setColor(new Color(254,95,85));
        } else if(brick.getLives() == 1){
            g.setColor(new Color(255,208,69));
        }
        g.fillRect(brick.getX(), brick.getY(), brick.getW(), brick.getH());

    }

    private void update(){

        paddle.move();
        ball.move();
        if(ticks_from_last_collision >= 20){
            ticks_from_last_collision = 20;
        } else {
            ticks_from_last_collision++;
        }
        checkCollisions();

    }

    private void checkCollisions() {

        Rectangle ballHitbox = ball.getBounds();
        Rectangle paddleHitbox = paddle.getBounds();


        for(Brick brick : bricks){

                if(ballHitbox.intersects(new Rectangle(brick.getBounds())) && brick.isAlive() && ticks_from_last_collision > 2){
                    if(Math.abs(ball.getCenterX()-brick.getCenterX())/brick.getW() >= Math.abs(ball.getCenterY()-brick.getCenterY())/brick.getH()){

                        ball.setX(ball.getxOld());
                        ball.setY(ball.getyOld());
                        ball.inverseXVel();
                        //System.out.println("V/H");
                    }else{
                        ball.setX(ball.getxOld());
                        ball.setY(ball.getyOld());
                        ball.inverseYVel();
                        //System.out.println("U/N");

                    }

                    brick.kill();

                    ticks_from_last_collision = 0;
                }

        }
        double f = 0;
        if(ballHitbox.intersects(paddleHitbox) && ticks_from_last_collision > 5){
            f = paddle.getXCenter() - ball.getCenterX();
            ball.setX(ball.getxOld());
            ball.setY(ball.getyOld());
            ball.setXVel(-f*2/(paddle.getW()/2));
            ball.inverseYVel();

            ticks_from_last_collision = 0;
        }


        if(ball.getX() + ball.getR() > BOARD_WIDTH || ball.getX() < 0){
            ball.inverseXVel();
        }
        if(ball.getY() + ball.getR() > BOARD_HEIGHT || ball.getY() < 0){
            ball.inverseYVel();
        }

    }

    public void run(){

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while(true){

            update();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = TIME_PER_FRAME - timeDiff;

            try{
                Thread.sleep(sleep);
            } catch(Throwable t){
                System.out.println("Något blev fel!");
            }

            beforeTime = System.currentTimeMillis();

        }

    }

    public static int getWIDTH() {
        return BOARD_WIDTH;
    }

    public static int getHEIGHT() {
        return BOARD_HEIGHT;
    }

    private class KeyboardAdapter extends KeyAdapter{

        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }
    }

}
