import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/a7a.train");
        Parse parse = new Parse();

        ArrayList<Integer> labels = parse.parse_input_to_labels(file);
        ArrayList<HashMap<Integer, Integer>> featuresList = parse.parse_features(file);
        HashMap<Integer, Double> weights = new HashMap<>();
        int MAXSIZE = 124;
        for(int i = 1; i < MAXSIZE; i++){
            weights.put(i, 0.0);
        }

        for (HashMap<Integer, Integer> featureMap : featuresList) {
            TreeMap<Integer, Integer> sortedMap = new TreeMap<>(featureMap);
            for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
                System.out.print(entry.getKey() + ":" + entry.getValue() + ", ");
            }
            System.out.println();
            break;
        }
    }
}