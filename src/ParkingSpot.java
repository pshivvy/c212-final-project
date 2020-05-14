/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This is the parking spot class, all parkinglots will contain parkingspots
 */
import java.awt.*;
import java.io.Serializable;

public class ParkingSpot implements Serializable {
    private Customer user;
    private Vehicle vehicle;
    // premium or regular
    private String parkingOption;
    private String longOrShortTerm;
    private long timeParked;
    private int row;
    private int column;

    // this is the constructor for the parkingSpots information
    public ParkingSpot(Customer u, Vehicle v, String parkingOption, String longOrShortTerm){
        this.user = u;
        this.vehicle = v;
        this.parkingOption = parkingOption;
        this.longOrShortTerm = longOrShortTerm;
        this.timeParked = System.currentTimeMillis();
    }

    // this is the getter for the user in the parking spot
    public Customer getUser() {
        return user;
    }

    // this is the setter for the user in the parking spot
    public void setUser(Customer user) {
        this.user = user;
    }

    // this is the getter for the users vehicle in the parking spot
    public Vehicle getVehicle() {
        return vehicle;
    }

    // this is the setter for the users vehicle in the parking spot
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // this is the getter for the user parking option in the parking spot
    public String getParkingOption() {
        return parkingOption;
    }

    // this is the setter for the users parking option in the parking spot
    public void setParkingOption(String parkingOption) {
        this.parkingOption = parkingOption;
    }

    // this is the getter for if the user is going to park in that spot for a long or short time
    public String getLongOrShortTerm() {
        return longOrShortTerm;
    }

    // this is the setter for if the user is going to park in that spot for a long or short time
    public void setLongOrShortTerm(String longOrShortTerm) {
        this.longOrShortTerm = longOrShortTerm;
    }

    // this is the setter for how long the vehicle will be parked in the spot
    public void setTimeParked(long timeParked) {
        this.timeParked = timeParked;
    }

    // this is the getter for how long the vehicle will be parked in the spot
    public long getTimeParked() {
        return timeParked;
    }

    // this is the getter for where the vehicle is parked -- which parking spot it is in
    public Point getLocation(){
        return new Point(row, column);
    }

    // this is a setter for the information in the spot
    public void setSpotInfo(int row, int column, ParkingLot p){
            this.row = row;
            this.column = column;
            this.vehicle.setLot(p);
            this.vehicle.setSpot(this);
    }

    // this will print out how the information in the spot will be returned
    @Override
    public String toString() {
        return "ParkingSpot{" + "\n" +
           "  user=" + user + "\n" +
           "  vehicle=" + vehicle + "\n" +
           "  parkingOption='" + parkingOption + '\'' + "\n" +
           "  longOrShortTerm='" + longOrShortTerm + '\'' + "\n" +
           "  timeParked=" + timeParked + "\n" +
           "  row=" + row + "\n" +
           "  column=" + column + "\n" +
           '}';
    }
}
