import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Canvas extends JPanel {
    private Model model;
    private Image currentState;
    private Image gamerLeft;
    private Image gamerRight;
    private Image gamerDown;
    private Image gamerUp;
    private Image wall;
    private Image ghost;
    private Image target;

    Canvas(Model model) {

        this.model = model;
        setBackground(Color.WHITE);

        File gamerRightFile = new File("../images/pacman-right.png");
        File gamerLeftFile = new File("../images/pacman-left.png");
        File gamerDownFile = new File("../images/pacman-down.png");
        File gamerUpFile = new File("../images/pacman-up.png");
        File wallFile = new File("../images/wall.png");
        File ghostFile = new File("../images/ghost.png");
        File targetFile = new File("../images/goal.png");
        try {
            gamerLeft = ImageIO.read(gamerLeftFile);
            gamerRight = ImageIO.read(gamerRightFile);
            gamerDown = ImageIO.read(gamerDownFile);
            gamerUp = ImageIO.read(gamerUpFile);
            wall = ImageIO.read(wallFile);
            ghost = ImageIO.read(ghostFile);
            target = ImageIO.read(targetFile);
            currentState = gamerRight;
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe);
            ioe.printStackTrace();
        }
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        int x = 50;
        int y = 50;
        int width = 50;
        int height = 50;
        int offset = 5;
        if (model.getDesktop() != null) {
            int[][] desktop = model.getDesktop();
            for (int i = 0; i < desktop.length; i++) {
                for (int j = 0; j < desktop[i].length; j++) {
                    if (desktop[i][j] == 1) {
                        graphics.drawImage(currentState, x, y, null);
                    } else if (desktop[i][j] == 2) {
                        graphics.drawImage(wall, x, y, null);

                    } else if (desktop[i][j] == 3) {
                        graphics.drawImage(ghost, x, y, null);
                    } else if (desktop[i][j] == 4) {
                        graphics.drawImage(target, x, y, null);
                    } else {
                        graphics.drawRect(x+25, y+25, 1, 1);
                    }
                    x = x + width + offset;
                }
                x = 50;
                y = y + height + offset;
            }
        }
    }

    public void changeToLeft() {
        currentState = gamerLeft;
    }

    public void changeToRight() {
        currentState = gamerRight;
    }

    public void changeToDown() {
        currentState = gamerDown;
    }

    public void changeToUp() {
        currentState = gamerUp;
    }

}
