
package Java_Nested_Classes.Q5;
import java.time.LocalDateTime;
import java.util.*;

// Main class: SmartCity
public class SmartCity {

    private String cityName;
    private List<SensorZone> zones;

    public SmartCity(String cityName) {
        this.cityName = cityName;
        this.zones = new ArrayList<>();
    }

    public void addZone(SensorZone zone) {
        zones.add(zone);
    }

    public void displayCity() {
        System.out.println("Smart City: " + cityName);
        for (SensorZone zone : zones) {
            zone.displayZone();
        }
    }

    // Inner class: SensorZone
    public class SensorZone {
        private String zoneId;
        private List<Sensor> sensors;

        public SensorZone(String zoneId) {
            this.zoneId = zoneId;
            this.sensors = new ArrayList<>();
        }

        public void addSensor(Sensor sensor) {
            sensors.add(sensor);
        }

        public void displayZone() {
            System.out.println("\tZone: " + zoneId);
            for (Sensor s : sensors) {
                s.displaySensor();
            }
        }

        // Inner class: Sensor
        public class Sensor {
            private String sensorId;
            private String sensorType;
            private List<SensorReading> readings;

            public Sensor(String sensorId, String sensorType) {
                this.sensorId = sensorId;
                this.sensorType = sensorType;
                this.readings = new ArrayList<>();
            }

            public void addReading(SensorReading reading) {
                readings.add(reading);
            }

            public void displaySensor() {
                System.out.println("\t\tSensor ID: " + sensorId + " (" + sensorType + ")");
                for (SensorReading r : readings) {
                    r.displayReading();
                }
            }

            // Static nested class: SensorReading
            public static class SensorReading {
                private LocalDateTime timestamp;
                private double readingValue;

                public SensorReading(LocalDateTime timestamp, double readingValue) {
                    this.timestamp = timestamp;
                    this.readingValue = readingValue;
                }

                public void displayReading() {
                    System.out.println("\t\t\tTime: " + timestamp + ", Value: " + readingValue);
                }
            }
        }
    }


    public static void main(String[] args) {
        SmartCity city = new SmartCity("SmartTown");

        SensorZone zone1 = city.new SensorZone("Zone-A");
        SensorZone.Sensor sensor = zone1.new Sensor("S001", "Temperature");
        SensorZone.Sensor.SensorReading reading = new SensorZone.Sensor.SensorReading(LocalDateTime.now(), 25.3);
        sensor.addReading(reading);

        zone1.addSensor(sensor);
        city.addZone(zone1);
        city.displayCity();
    }
}

