package Enums.Vehicle_Type;

public class TollBoothTest
{
    public static void main(String[] args) {
        Vehicle v = new Vehicle("KA01AB1234", VehicleType.TRUCK);
        new TollBooth().calculateToll(v);
    }

}
