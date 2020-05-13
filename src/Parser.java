import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Parser {

    public static int[][] parse(String fileName) throws FileNotFoundException {

        BufferedReader inputStream = null;
        String line = "";
        int[][] result;
        ArrayList<String> desktopStringLine = new ArrayList<>();

        try {
            inputStream = new BufferedReader(new FileReader(fileName));
            String newLine;
            while ((newLine = inputStream.readLine()) != null) {
                for (int i = 0; i < newLine.length(); i++) {
                    // проходит по всему файлу и вытаскивает только 0, 1, 2, 3, 4 сохраняя их в line
                    if (newLine.charAt(i) == '0' || newLine.charAt(i) == '1' || newLine.charAt(i) == '2' || newLine.charAt(i) == '3' || newLine.charAt(i) == '4') {
                        line += newLine.charAt(i);
                    }
                }
                if (!line.equals("")) {
                    desktopStringLine.add(line);
                }
                line = "";
            }
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            result = new int[desktopStringLine.size()][];
            for (int i = 0; i < desktopStringLine.size(); i++) {
                int[] arrayLine = new int[desktopStringLine.get(i).length()];
                for (int j = 0; j < desktopStringLine.get(i).length(); j++) {
                    arrayLine[j] = Integer.parseInt(String.valueOf(desktopStringLine.get(i).charAt(j)));
                }
                result[i] = arrayLine;
            }
        }
        return result;
    }

    public static int[][] parseString(byte[] level) {
        StringBuilder myLevel = new StringBuilder();
        for (byte b : level) {
            myLevel.append((char) b);
        }
        return Arrays.stream(myLevel.toString().split("\n")).map(line -> Stream.of(line.split("")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
    }
}
