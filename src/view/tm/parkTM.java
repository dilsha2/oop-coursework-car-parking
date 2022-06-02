package view.tm;

public class parkTM {
    private String VehicleNumber;
    private String VehicleType;
    private int Slot;
    private String ParkedTime;

    public parkTM() {
    }

    public parkTM(String vehicleNumber, String vehicleType, int slot, String parkedTime) {
        VehicleNumber = vehicleNumber;
        VehicleType = vehicleType;
        Slot = slot;
        ParkedTime = parkedTime;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
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

    public String getParkedTime() {
        return ParkedTime;
    }

    public void setParkedTime(String parkedTime) {
        ParkedTime = parkedTime;
    }

    @Override
    public String toString() {
        return "parkTM{" +
                "VehicleNumber='" + VehicleNumber + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                ", Slot=" + Slot +
                ", ParkedTime='" + ParkedTime + '\'' +
                '}';
    }
}
