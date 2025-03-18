package com.ontariotechu.sofe3980U;

import java.io.FileReader; 
import java.util.List;
import com.opencsv.*;

public class App {

    // Method to calculate Mean Squared Error (MSE)
    private static double calculateMSE(List<String[]> allData) {
        double mse = 0.0;
        int count = 0;
        for (String[] row : allData) {
            double y_true = Double.parseDouble(row[0]);
            double y_predicted = Double.parseDouble(row[1]);
            mse += Math.pow(y_true - y_predicted, 2);
            count++;
        }
        return mse / count;
    }

    // Method to calculate Mean Absolute Error (MAE)
    private static double calculateMAE(List<String[]> allData) {
        double mae = 0.0;
        int count = 0;
        for (String[] row : allData) {
            double y_true = Double.parseDouble(row[0]);
            double y_predicted = Double.parseDouble(row[1]);
            mae += Math.abs(y_true - y_predicted);
            count++;
        }
        return mae / count;
    }

    // Method to calculate Mean Absolute Relative Error (MARE)
    private static double calculateMARE(List<String[]> allData) {
        double mare = 0.0;
        int count = 0;
        for (String[] row : allData) {
            double y_true = Double.parseDouble(row[0]);
            double y_predicted = Double.parseDouble(row[1]);
            if (y_true != 0) {
                mare += Math.abs((y_true - y_predicted) / y_true);
            }
            count++;
        }
        return mare / count;
    }

    // Method to read the CSV file and return a list of data
    private static List<String[]> readCSV(String filePath) {
        List<String[]> allData = null;
        try {
            FileReader filereader = new FileReader(filePath);
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            allData = csvReader.readAll();
        } catch (Exception e) {
            System.out.println("Error reading the CSV file");
            e.printStackTrace();
        }
        return allData;
    }

    public static void main(String[] args) {

        // Process model_1.csv
        String model1Path = "model_1.csv";
        List<String[]> model1Data = readCSV(model1Path);

        // Process model_2.csv
        String model2Path = "model_2.csv";
        List<String[]> model2Data = readCSV(model2Path);

        // Process model_3.csv
        String model3Path = "model_3.csv";
        List<String[]> model3Data = readCSV(model3Path);

        // Calculate and display metrics for model_1
        double mse1 = calculateMSE(model1Data);
        double mae1 = calculateMAE(model1Data);
        double mare1 = calculateMARE(model1Data);
        System.out.println("For model_1.csv");
        System.out.println("MSE = " + mse1);
        System.out.println("MAE = " + mae1);
        System.out.println("MARE = " + mare1);
        System.out.println();

        // Calculate and display metrics for model_2
        double mse2 = calculateMSE(model2Data);
        double mae2 = calculateMAE(model2Data);
        double mare2 = calculateMARE(model2Data);
        System.out.println("For model_2.csv");
        System.out.println("MSE = " + mse2);
        System.out.println("MAE = " + mae2);
        System.out.println("MARE = " + mare2);
        System.out.println();

        // Calculate and display metrics for model_3
        double mse3 = calculateMSE(model3Data);
        double mae3 = calculateMAE(model3Data);
        double mare3 = calculateMARE(model3Data);
        System.out.println("For model_3.csv");
        System.out.println("MSE = " + mse3);
        System.out.println("MAE = " + mae3);
        System.out.println("MARE = " + mare3);
        System.out.println();

        // Determine the best model based on MSE, MAE, and MARE
        String bestModelMSE = (mse1 < mse2 && mse1 < mse3) ? "model_1.csv" :
                              (mse2 < mse1 && mse2 < mse3) ? "model_2.csv" : "model_3.csv";
        String bestModelMAE = (mae1 < mae2 && mae1 < mae3) ? "model_1.csv" :
                               (mae2 < mae1 && mae2 < mae3) ? "model_2.csv" : "model_3.csv";
        String bestModelMARE = (mare1 < mare2 && mare1 < mare3) ? "model_1.csv" :
                                (mare2 < mare1 && mare2 < mare3) ? "model_2.csv" : "model_3.csv";

        // Output the best model for each metric
        System.out.println("According to MSE, The best model is " + bestModelMSE);
        System.out.println("According to MAE, The best model is " + bestModelMAE);
        System.out.println("According to MARE, The best model is " + bestModelMARE);
    }
}

