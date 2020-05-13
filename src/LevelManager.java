import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    private int level;
    private List<String> available;
//    private Client client;

    public LevelManager() {
//        client = new Client();
        this.level = 0;
        available = new ArrayList<>();
        available.add("1");
    }


    public int[][] restart() {
        return getLevel(level);
    }

    public int[][] previous() {
        if (level <= 1) {
            return getLevel(1);
        } else {
            return getLevel(--level);
        }
    }

    public int[][] next() {
        return getLevel(++level);
    }

    public int[][] getLevel(int index) {
        System.out.println("Current: " + index);
        int[][] desktop;
        switch (index) {
            case 1:
                desktop = level1();
                break;
            case 2:
                desktop = level2();
                break;
            case 3:
                desktop = level3();
                break;

            default:
                try {
                    desktop = fileLevel(index);
                }catch (FileNotFoundException e){
                    index = 1;
                    desktop = level1();
                }
                break;
        }
        level = index;
        if (!available.contains(String.valueOf(level))){
            available.add(String.valueOf(level));
        }
        return desktop;
    }

    private int[][] level1() {
        int[][] desktop = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 3, 4, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        return desktop;
    }

    private int[][] level2() {
        int[][] desktop = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 3, 4, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 3, 4, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        return desktop;
    }

    private int[][] level3() {
        int[][] desktop = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 3, 3, 4, 4, 0, 0, 2},
                {2, 0, 0, 1, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 3, 4, 0, 0, 0, 2},
                {2, 0, 0, 3, 3, 4, 4, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        return desktop;
    }

    private int[][] fileLevel(int level) throws FileNotFoundException {
        return Parser.parse(String.format("../levels/level%d.txt",level));

    }

    public List<String> getAvailableLevels(){
        return available;
    }

    public int getCurrentLevelIndex(){
        return level;
    }

}
