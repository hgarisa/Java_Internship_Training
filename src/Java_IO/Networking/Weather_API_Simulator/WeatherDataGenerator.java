package Java_IO.Networking.Weather_API_Simulator;
import java.util.*;
/*
 * WeatherDataGenerator:
 * - Generates fake weather information.
 * - Returns data for temperature, humidity, and description.
 */
public class WeatherDataGenerator
{

    private static final Random random = new Random();
    private static final List<String> conditions = Arrays.asList("Sunny", "Cloudy", "Rainy", "Windy", "Stormy", "Foggy");

    public static String generate(String city) {
        int temp = random.nextInt(35);      // 0 - 34 °C
        int humidity = 30 + random.nextInt(50); // 30% - 80%
        String condition = conditions.get(random.nextInt(conditions.size()));

        return String.format("%s → %d°C, %d%% Humidity, %s", city, temp, humidity, condition);
    }

}
