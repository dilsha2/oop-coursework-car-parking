package model;

public class Vehicle {
    private String vehicleNo;
    private String vehicleType;
    private double maxWeight;
    private int passengers;
    private int slot;

    public Vehicle() {
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "slot=" + slot +
                '}';
    }

    public Vehicle(String vehicleNo, String vehicleType, double maxWeight, int slot) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.passengers = passengers;
        this.slot=slot;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
