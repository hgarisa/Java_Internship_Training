package Java_Reflection.Plugin_Loader;
import java.io.*;
import java.util.*;

/*
Plugin System for Custom Commands (Using Reflection + I/O)
Real-World Scenario:
You are building a modular plugin-based console application that can
load and execute user-defined commands. Each command is a class stored in a plugins folder,
 and they implement a common interface Command.
 Your application reads class names from a file (plugins.txt),
 loads those classes dynamically using Reflection, and executes their run() method.


* */


public class PluginExecutorApp {
    public static void main(String[] args) {
        String pluginFile = "plugins.txt"; // class names to load

        try (BufferedReader reader = new BufferedReader(new FileReader(pluginFile))) {
            String className;
            while ((className = reader.readLine()) != null) {
                try {
                    Class<?> clazz = Class.forName(className); // dynamically load class
                    Object obj = clazz.getDeclaredConstructor().newInstance();

                    if (obj instanceof Command) {
                        Command command = (Command) obj;
                        System.out.println("Running: " + className);
                        command.run();
                    } else {
                        System.err.println("Class " + className + " does not implement Command interface.");
                    }

                } catch (Exception e) {
                    System.err.println("Failed to load class " + className + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading plugin file: " + e.getMessage());
        }
    }
}
