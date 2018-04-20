import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(){

        initUI();

    }

    private void initUI() {

        add(new GameBoard());

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.green);
        pack();

    }

    public static void main(String[] args){

        JFrame board = new Main();
        board.setVisible(true);

    }

}