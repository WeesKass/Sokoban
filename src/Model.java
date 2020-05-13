import javax.swing.*;

public class Model {

    private Viewer viewer;
    private int[][] desktop;
    private int indexX;
    private int indexY;
    private LevelManager levels;
    private int[][] targetIndex;
    private Audio audio;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        levels = new LevelManager();
        audio = new Audio();
        desktop = levels.next();
        initialization();
        setPlayerIndexes();
        audio = new Audio();
        audio.start();
    }


    public void initialization() {

        int targetCounter = 0;
        int ghostCounter = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    indexY = j;
                    indexX = i;
                }
                if (desktop[i][j] == 4) {
                    targetCounter = targetCounter + 1;
                }
                if (desktop[i][j] == 3) {
                    ghostCounter = ghostCounter + 1;
                }
            }
        }

        if (targetCounter != ghostCounter) {
            desktop = levels.next();
            initialization();
        }


        targetIndex = new int[targetCounter][2];
        int count = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 4) {
                    targetIndex[count][0] = i;
                    targetIndex[count][1] = j;
                    count++;
                }
            }
        }
    }


    public void doAction(String command) {
        System.out.println(command);
        switch (command){
            case "restart":
                desktop = levels.restart();
                initialization();
                setPlayerIndexes();
                viewer.update();
                break;
            case "prev":
                desktop = levels.previous();
                initialization();
                setPlayerIndexes();
                viewer.update();
                break;
            case "next":
                if (levels.getAvailableLevels().contains(String.valueOf(levels.getCurrentLevelIndex() + 1))){
                    desktop = levels.next();
                    initialization();
                    setPlayerIndexes();
                    viewer.update();
                }
                break;
            case "search":
                levelChooser();
                initialization();
                setPlayerIndexes();
                viewer.update();
                break;
            case "audio":
                turnOnOrOffSounds();
                break;
        }
    }

    private void moveUp() {
        if (desktop[indexX - 1][indexY] == 3) {
            if (desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4) {
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }

        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX -= 1;
            desktop[indexX][indexY] = 1;
        }
    }


    private void moveDown() {
        if (desktop[indexX + 1][indexY] == 3) {
            if (desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4) {
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }

        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX += 1;
            desktop[indexX][indexY] = 1;
        }
    }


    private void moveRight() {
        if (desktop[indexX][indexY + 1] == 3) {
            if (desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4) {
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }

        if (desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY += 1;
            desktop[indexX][indexY] = 1;
        }
    }


    private void moveLeft() {
        if (desktop[indexX][indexY - 1] == 3) {
            if (desktop[indexX][indexY - 2] == 0 || desktop[indexX][indexY - 2] == 4) {
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }

        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY -= 1;
            desktop[indexX][indexY] = 1;
        }
    }


    private boolean checkWin() {
        boolean flag = true;
        for (int i = 0; i < targetIndex.length; i++) {
            int y = targetIndex[i][0];
            int x = targetIndex[i][1];
            if (desktop[y][x] == 4 || desktop[y][x] == 1) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private void check() {
        for (int i = 0; i < targetIndex.length; i++) {
            int x = targetIndex[i][0];
            int y = targetIndex[i][1];
            if (desktop[x][y] == 0) {
                desktop[x][y] = 4;
                break;
            }

        }
        if (checkWin()) {
            audio.playChomp();
            desktop = levels.next();
            initialization();
            setPlayerIndexes();
            viewer.update();
        }
    }


    public void move(String direction) {
        try {
            switch (direction) {
                case "Right":
                    viewer.getCanvas().changeToRight();
                    moveRight();
                    break;
                case "Left":
                    viewer.getCanvas().changeToLeft();
                    moveLeft();
                    break;
                case "Up":
                    viewer.getCanvas().changeToUp();
                    moveUp();
                    break;
                case "Down":
                    viewer.getCanvas().changeToDown();
                    moveDown();
                    break;
                default:
            }
            viewer.update();
            check();
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid move");
        }

    }


    public int[][] getDesktop() {
        return desktop;
    }

    private void setPlayerIndexes() {
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    indexX = i;
                    indexY = j;
                }
            }
        }
    }

    private void levelChooser() {
        try {
            Object[] options = levels.getAvailableLevels().toArray();
            int level = JOptionPane.showOptionDialog(null, "Choose level:",
                    "Level chooser",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) + 1;
            if (level <= 0) {
                throw new NumberFormatException();
            } else {
                desktop = levels.getLevel(level);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid level");
        }
    }
    
    private void turnOnOrOffSounds() {
        audio.soundOnOrOff();
    }

    public Viewer getViewer(){
        return this.viewer;
    }
}


