/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This is the customer class
 */

import java.util.*;
import java.io.Serializable;


public class Customer extends User implements Serializable {

    private Map<String,ParkingLot> blackListedParkingLots = new HashMap<>(); // creates a set

    // This variable will make the receipt for the user
    private Receipt payment = new Receipt();

    // This variable will tell us which vehicle is the users
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    // This variable will show the users history
    private History history = new History();

    private int coupon = 0;

    // This is a constructor that takes from the parent class User
    public Customer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Customer");
    }


    // The customer is able to add vehicles to there name incase they want to park more then one vehicle
    public void addVehicle(Vehicle v) {
        vehicles.add(v); // this adds the vehicle
        history.addToHistory(this.getFullName() + " added a " + v.getMake() + " " + v.getModel() + " which is colored " + v.getColor());
        // this will add the vehicle to the history
    }

    // The customer is able to delete vehicles to there name incase the got a new vehicle or no longer need to park it
    public void deleteVehicle(Vehicle v) {
        vehicles.remove(v); // this remove the vehicle
        history.addToHistory(this.getFullName() + " removed " + v.getMake() + " " + v.getModel() + " which is colored " + v.getColor());
        // this will show in history that the vehicle was removed
    }

    // This is how a user can view where the vehicle is located and the other details to how there vehicle is in the lot
    public String viewVehicles() {
        String display = ""; // creates a string
        for (Vehicle v : vehicles) {
            if (v.getSpot() == null || v.getLot() == null) {
                display = display + v.getMake() + " " + v.getModel() + "'s info: \n   Color: " + v.getColor() + "\n   Dimensions: " + v.getWidth() + " x " + v.getHeight()
                        + "\n   Location: not parked yet" + ")\n"; // formats it
            } else {
                display = display + v.getMake() + " " + v.getModel() + "'s info: \n   Color: " + v.getColor() + "\n   Dimensions: " + v.getWidth() + " x " + v.getHeight()
                        + "\n   Location: " + v.getLot().getName() + " at spot (" + (int) v.getSpot().getLocation().getX() + "," + (int) v.getSpot().getLocation().getY() + ")\n"; // formats it
            }
        }
        return display; // returns the users vehicle the way its formatted above
    }

    // This is how the person is able to get there vehicle back from when there time is up
    public void requestVehicle(Vehicle v, ParkingLot p, Steward s) {
            if(v.getSpotsTaken() == 1){
                int currentVehicle = vehicles.indexOf(v);
                p.setSpot((int) v.getSpot().getLocation().getX(), (int) v.getSpot().getLocation().getY(), null);
                s.setAssignedLot(p);
                v.setLot(null); // sets lot to null since they are getting there vehicle back
                v.setSpot(null); // sets spot to null since they are getting there vehicle back
                vehicles.set(currentVehicle, v);
            }else{
                int currentVehicle = vehicles.indexOf(v);
                p.setSpot((int)v.getTwoSpot().getAllLocation()[0].getX(),new int[]{(int)v.getTwoSpot().getAllLocation()[0].getY(),(int)v.getTwoSpot().getAllLocation()[1].getY()},null);
                s.setAssignedLot(p);
                v.setLot(null);  // sets lot to null since they are getting there vehicle back
                v.setSpot(null); // sets spot to null since they are getting there vehicle back
                vehicles.set(currentVehicle, v);
            }
    }

    // This is how the person is able to park a vehicle
    public ParkingLot parkVehicle(String option, String lenght, Vehicle v, ParkingLot parkingLot, Steward s) {
        parkingLot.getSteward().checkForSpot(option,lenght,v,parkingLot);
        s.setAssignedLot(parkingLot);
        return null;
    }

    // this will set the history of the car, like where it is located, which row and column to look for it
    public void setLotHistory(String parkingLot, String lineItem) {
        history.addToHistory(parkingLot, lineItem);
    }

    // This history will return just where the vehicle is parked as in which parking lot
    public String getLotHistory(String parkingLot) {
        return "Customer's history at " + parkingLot + "\n" + history.returnLotHistory(parkingLot);
    }

    // this is for the personal history of just the user to view
    public void setPersonalHistory(String lineItem){
        history.addToHistory(lineItem);
    }

    // this will return all of the history on the customer, everything that they have on their vehicle
    public String getPersonalHistory() {
        return history.returnPersonalHistory();
    }

    // this returns everything that the user has ever done in the parking spot system
    public String getAllHistory() {
        return history.allHistory();
    }

    // this is a getter for the history
    public History getHistory() {
        return history;
    }

    // this is a getter for the vehicles in the array
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    // this is a setter for the vehicles in the array
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    // this is a setter for the history of the customer
    public void setHistory(History history) {
        this.history = history;
    }


    // this is a getter for parking a vehicle
    public ArrayList<Vehicle> getParkedVehicles(){
        ArrayList<Vehicle> parkedVehicles = new ArrayList<>(); // creates the arraylist
        for(Vehicle v : vehicles){
            if(v.getLot() != null){
                parkedVehicles.add(v); // adds the vehicle to the arraylist
            }
        }
        return parkedVehicles;
    }

    // this will make how much the vehicle will cost depending on the make and model of the vehicle
    public void makePayment(Vehicle v, double pay) {
        v.setCost("Payment", -pay);
    }

    public Map<String,ParkingLot> getBlackListedParkingLots() {
        return blackListedParkingLots;
    }

    // this will add to the customer the lot they can no longer park in
    public void addBlackListLot(ParkingLot lot) {
        blackListedParkingLots.put(lot.getName(),lot);
    }

    // this is a setter for blacklisting
    public void setBlackListedParkingLots(Map<String,ParkingLot> blackListedParkingLots) {
        this.blackListedParkingLots = blackListedParkingLots;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }
}