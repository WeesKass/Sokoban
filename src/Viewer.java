import javax.swing.*;
import java.awt.*;

public class Viewer {

    private JFrame frame;
    private Controller controller;
    private Canvas canvas;

    public Viewer() {
        controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model);
        canvas.setBackground(Color.BLACK);



        JButton restart = new JButton("", new ImageIcon("../images/restart.png"));
        restart.setActionCommand("restart");
        restart.addActionListener(controller);
        restart.setBackground(Color.BLACK);
        restart.setBorderPainted(false);

        JButton next = new JButton("", new ImageIcon("../images/next.png"));
        next.setActionCommand("next");
        next.addActionListener(controller);
        next.setBackground(Color.BLACK);
        next.setBorderPainted(false);

        JButton prev = new JButton("", new ImageIcon("../images/prev.png"));
        prev.setActionCommand("prev");
        prev.addActionListener(controller);
        prev.setBackground(Color.BLACK);
        prev.setBorderPainted(false);

        JButton music = new JButton("", new ImageIcon("../images/music.png"));
        music.setActionCommand("audio");
        music.addActionListener(controller);
        music.setBackground(Color.BLACK);
        music.setBorderPainted(false);

        JButton search = new JButton("", new ImageIcon("../images/search.png"));
        search.setActionCommand("search");
        search.addActionListener(controller);
        search.setBackground(Color.BLACK);
        search.setBorderPainted(false);

        JPanel menu = new JPanel();
        menu.setBackground(Color.BLACK);
        menu.add(search);
        menu.add(prev);
        menu.add(restart);
        menu.add(next);
        menu.add(music);


        frame = new JFrame("Sokoban PACMAN");

        frame.add(canvas,BorderLayout.CENTER);
        canvas.setFocusable(true);
        canvas.addKeyListener(controller);
        frame.add( menu,BorderLayout.PAGE_END);
        frame.setSize(650, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 0);
        frame.setFocusable(false);
        frame.setVisible(true);
        canvas.requestFocus();
    }


    public void update() {
        canvas.repaint();
    }

    public Canvas getCanvas() {
        return canvas;
    }


}

