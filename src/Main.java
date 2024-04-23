import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java Main --lr <learningRate> --iterations <iterations> <trainFile> <testFile>");
            return;
        }

        double learningRate = Double.parseDouble(args[1]);
        int iterations = Integer.parseInt(args[3]);
        String trainFile = args[4];
        String testFile = args[5];

        File trainDataFile = new File(trainFile);
        File testDataFile = new File(testFile);

        Parse parser = new Parse();

        // Parsing training data
        ArrayList<Integer> trainLabels = parser.parse_input_to_labels(trainDataFile);
        ArrayList<HashMap<Integer, Integer>> trainFeatures = parser.parse_features(trainDataFile);

        // Parsing testing data
        ArrayList<Integer> testLabels = parser.parse_input_to_labels(testDataFile);
        ArrayList<HashMap<Integer, Integer>> testFeatures = parser.parse_features(testDataFile);

        Regression regression = new Regression();
        double[] weights = new double[124];  // Initialize weights, assuming 124 features including a bias if necessary

        // Train the model
        for (int i = 0; i < iterations; i++) {
            weights = regression.regression_model(weights, trainFeatures, trainLabels, learningRate);

            // Evaluating the model on training data
            double trainAccuracy = calculateAccuracy(trainFeatures, trainLabels, weights);
            double trainXent = calculateCrossEntropy(trainFeatures, trainLabels, weights);

            // Evaluating the model on testing data
            double testAccuracy = calculateAccuracy(testFeatures, testLabels, weights);
            double testXent = calculateCrossEntropy(testFeatures, testLabels, weights);

            System.out.format("Iteration %d: TRAIN accuracy %.3f xent %.3f   TEST accuracy %.3f xent %.3f%n",
                    i+1, trainAccuracy, trainXent, testAccuracy, testXent);
        }
    }

    private static double calculateAccuracy(ArrayList<HashMap<Integer, Integer>> features, ArrayList<Integer> labels, double[] weights) {
        int correct = 0;
        Regression regression = new Regression();
        for (int i = 0; i < features.size(); i++) {
            int[] featureArray = new int[124];
            for (int j = 0; j < 124; j++) {
                featureArray[j] = features.get(i).getOrDefault(j, 0);
            }
            double prediction = regression.sigmoid(regression.dot_product(featureArray, weights));
            int predictedLabel = prediction >= 0.5 ? 1 : -1;
            if (predictedLabel == labels.get(i)) {
                correct++;
            }
        }
        return (double) correct / labels.size();
    }

    private static double calculateCrossEntropy(ArrayList<HashMap<Integer, Integer>> features, ArrayList<Integer> labels, double[] weights) {
        double xent = 0.0;
        Regression regression = new Regression();
        for (int i = 0; i < features.size(); i++) {
            int[] featureArray = new int[124];
            for (int j = 0; j < 124; j++) {
                featureArray[j] = features.get(i).getOrDefault(j, 0);
            }
            double probability = regression.sigmoid(regression.dot_product(featureArray, weights));
            double labelProbability = labels.get(i) == 1 ? probability : 1 - probability;
            xent -= Math.log(labelProbability);
        }
        return xent / labels.size();
    }
}
