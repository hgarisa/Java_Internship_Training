package JavaCollection.JavaMap;

import java.util.Objects;

public class Parcel
{


    private String recipient;

    private String address;

    private boolean delivered;

    public Parcel(String recipient, String address, boolean delivered) {
        this.recipient = recipient;
        this.address = address;
        this.delivered = delivered;
    }


    // Getters

    public String getRecipient() {
        return recipient;
    }

    public String getAddress() {
        return address;
    }

    public boolean isDelivered() {
        return delivered;
    }


    public String toString() {
        return "Recipient: " + recipient + ", Address: " + address + ", Delivered: " + delivered;
    }



    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parcel)) return false;
        Parcel parcel = (Parcel) o;
        return delivered == parcel.delivered &&
                recipient.equals(parcel.recipient) &&
                address.equals(parcel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, address, delivered);
    }


}
