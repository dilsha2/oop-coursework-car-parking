package view.tm;

public class SlotTM {
    private String VehicleType;
    private int Slot;


    public SlotTM() {
    }

    public SlotTM(String vehicleType, int slot) {
        VehicleType = vehicleType;
        Slot = slot;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public int getSlot() {
        return Slot;
    }

    public void setSlot(int slot) {
        Slot = slot;
    }

    @Override
    public String toString() {
        return "SlotTM{" +
                "VehicleType='" + VehicleType + '\'' +
                ", Slot=" + Slot +
                '}';
    }
}
