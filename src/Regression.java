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

    public double[] regression_model(double[] weights, ArrayList<HashMap<Integer, Integer>> bigX, int[] label, int learning_rate){
        int n = bigX.size();
        for(int i = 0; i < n; i ++){
            int[] smallXArray = new int[124];
            for (int j = 1; j < 124; j++){
                smallXArray[j] = bigX.get(i).get(j);
            }

            for(int j = 1; j < weights.length; j++) {
                weights[j] += learning_rate * sigmoid(dot_product(smallXArray, weights)) * smallXArray[j];
            }
        }
        return weights;
    }
}
