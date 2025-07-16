package Enums.Vehicle_Type;

public class TollBooth
{
    public void calculateToll(Vehicle vehicle) {
        System.out.println("Vehicle #" + vehicle.getPlateNumber() +
                " (" + vehicle.getType() + ") Toll Fee: $" + vehicle.getType().getTollFee());
    }

}
