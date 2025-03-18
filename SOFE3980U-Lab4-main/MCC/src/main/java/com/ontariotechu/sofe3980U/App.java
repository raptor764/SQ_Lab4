package com.ontariotechu.sofe3980U;

import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

/**
 * Evaluate Multi-Class Classification
 */
public class App {
    public static void main(String[] args) {
        String filePath = "model.csv";
        FileReader filereader;
        List<String[]> allData;

        try {
            filereader = new FileReader(filePath);
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            allData = csvReader.readAll();
        } catch (Exception e) {
            System.out.println("Error reading the CSV file");
            return;
        }

        // Initialize confusion matrix (5 classes)
        int[][] confusionMatrix = new int[5][5];
        double ceSum = 0;
        int count = 0;

        // Iterate over the data to calculate CE and confusion matrix
        for (String[] row : allData) {
            int y_true = Integer.parseInt(row[0]) - 1;  // Convert to zero-indexed (adjust if needed)
            float[] y_predicted = new float[5];

            // Extract predicted values for the 5 classes
            for (int i = 0; i < 5; i++) {
                y_predicted[i] = Float.parseFloat(row[i + 1]);
            }

            // Find predicted class (argmax of predicted probabilities)
            int y_pred = argMax(y_predicted);

            // Update confusion matrix
            confusionMatrix[y_true][y_pred]++;

            // Calculate Cross-Entropy for this sample
            // CE for a particular sample: -log( y_predicted[y_true] )
            ceSum += -Math.log(y_predicted[y_true]);

            count++;
        }

        // Calculate overall Cross-Entropy
        double ce = ceSum / count;
        System.out.println("CE = " + ce);

        // Print confusion matrix
        System.out.println("Confusion matrix:");
        System.out.print("        ");
        for (int i = 1; i <= 5; i++) {
            System.out.print("y=" + i + "     ");
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print("y^=" + (i + 1) + "   ");
            for (int j = 0; j < 5; j++) {
                System.out.print(confusionMatrix[i][j] + "     ");
            }
            System.out.println();
        }
    }

    // Helper method to return the index of the maximum value (argmax)
    private static int argMax(float[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
