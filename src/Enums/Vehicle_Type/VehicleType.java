package Enums.Vehicle_Type;

public enum VehicleType
{
    CAR(50), TRUCK(150), MOTORBIKE(30), BUS(100);

    private final int tollFee;

    VehicleType(int tollFee) {
        this.tollFee = tollFee;
    }

    public int getTollFee() {
        return tollFee;
    }


}
