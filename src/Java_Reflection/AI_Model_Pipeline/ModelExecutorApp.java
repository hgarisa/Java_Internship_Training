package Java_Reflection.AI_Model_Pipeline;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/*
*
 Scenario:
You work in a company developing a modular AI model execution framework.
Each model (e.g., Sentiment Analysis, Image Recognition) implements a common interface called ModelTask.
 A text file (pipeline.txt) contains the list of models to run dynamically.

* */
public class ModelExecutorApp {

    public static void main(String[] args) {
        String pipelineFile = "pipeline.txt";
        String logFile = "model_audit.log";

        try (BufferedReader reader = new BufferedReader(new FileReader(pipelineFile));
             BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFile))) {

            String className;
            while ((className = reader.readLine()) != null) {
                try {
                    Class<?> clazz = Class.forName(className);
                    Object instance = clazz.getDeclaredConstructor().newInstance();

                    if (instance instanceof ModelTask) {
                        logWriter.write("Executing: " + className + " at " + LocalDateTime.now() + "\n");
                        ((ModelTask) instance).runModel();
                        logWriter.write("Finished: " + className + " at " + LocalDateTime.now() + "\n\n");
                    } else {
                        logWriter.write("Class " + className + " is not a ModelTask\n");
                    }

                } catch (Exception e) {
                    logWriter.write("Failed to execute " + className + ": " + e.getMessage() + "\n\n");
                }
            }

            System.out.println("Model pipeline execution completed. Check model_audit.log.");
        } catch (IOException e) {
            System.err.println("Error reading pipeline: " + e.getMessage());
        }
    }
}

