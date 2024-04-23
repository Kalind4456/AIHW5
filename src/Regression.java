import java.util.*;
import java.lang.Math;
public class Regression {
    public double sigmoid(double z){
        return 1/(1+Math.exp(-1*z));
    }

    public int[] multiply_array_number(int[] a, int b){
        int n = a.length;
        int[] c = new int[n];
        for(int i = 0; i < n; i++) {
            c[i] = a[i] * b;
        }
        return c;
    }

    public double dot_product(int[] a, double[] b){
        double sum = 0;
        int n = a.length;
        for(int i = 0; i < n; i++){
            sum += a[i]*b[i];
        }
        return sum;
    }

    public double[] regression_model(double[] weights, ArrayList<HashMap<Integer, Integer>> bigX, ArrayList<Integer> labels, double learning_rate) {
        int n = bigX.size();
        for (int i = 0; i < n; i++) {
            int[] smallXArray = new int[124]; // Assuming 124 features (adjust accordingly)
            HashMap<Integer, Integer> features = bigX.get(i);
            for (int j = 0; j < 124; j++) {
                smallXArray[j] = features.getOrDefault(j, 0);
            }

            double predicted = sigmoid(labels.get(i) * dot_product(smallXArray, weights));
            double error = (1 - predicted) * labels.get(i);

            for (int j = 0; j < weights.length; j++) {
                if (features.containsKey(j)) {
                    weights[j] += learning_rate * error * smallXArray[j];
                }
            }
        }
        return weights;
    }
}
