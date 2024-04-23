import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Parse {
    public ArrayList<Integer> parse_input_to_labels(File file){
        ArrayList<Integer> labels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while(line != null ){
                labels.add(line.charAt(0) == '-' ? -1 : 1);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return labels;
    }

    public ArrayList<HashMap<Integer, Integer>> parse_features(File file){
        ArrayList<HashMap<Integer, Integer>> bigX = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                HashMap<Integer, Integer> features = new HashMap<>();
                String[] tokens = line.split(" ");  // Split the line into tokens
                for (int i = 1; i < tokens.length; i++) {  // Start from 1 to skip the label
                    String[] parts = tokens[i].split(":");
                    if (parts.length == 2) {
                        int index = Integer.parseInt(parts[0]);
                        int value = Integer.parseInt(parts[1]);
                        features.put(index, value);  // Store the feature and its value
                    }
                }
                // Initialize all other indices to zero if necessary
                for (int i = 1; i <= 124; i++) {
                    features.putIfAbsent(i, 0);  // Only set to zero if not already present
                }
                bigX.add(features);  // Add the map for this line to the list
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bigX;
    }
}
