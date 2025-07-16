package Java_Reflection.AI_Model_Pipeline;
/*
 * Sample model task that simulates image recognition.
 */
public class ImageRecognitionModel implements ModelTask {
    @Override
    public void runModel() {
        System.out.println("Running Image Recognition...");
        // Simulate processing
        try { Thread.sleep(700); } catch (InterruptedException e) {}
        System.out.println("Image Recognition Complete!");
    }
}
