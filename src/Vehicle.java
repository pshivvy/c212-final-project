/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This is the customer class that tells all the functions that a customer has
 */
import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Arrays;

public class Vehicle implements Serializable{
    // car categories will be:
    //   Motorcycle = takes up 1 parking space
    //   Hatchback - takes up 1 parking space
    //   Sedan - takes up 1 parking space
    //   SUV - takes up 2 parking spaces
    //   Pickup Truck - takes up 2 parking spaces
    //   Van - takes up 2 spaces
    private String category;
    private String model;      // gets the model of the vehicle
    private String make;       // gets the make of the vehicle
    private String color;      // gets the color of the vehicle
    private int year;          // gets the year the vehicle was made
    private int width;         // gets the width of the vehicle
    private int height;        // gets the height of the vehicle
    private int spotsTaken;    // tells which spots are taken in the lot
    private Customer owner;    // tells who owners the vehicle
    private ParkingLot lot;    // tells which lot the vehicle is in
    private ParkingSpot spot;  // tells which spot the vehicle is in
    private TwoParkingSpots twoSpot;  // tells which spot the vehicle is in
    private ArrayList<Double> prices = new ArrayList<>();
    private ArrayList<String> costDescription = new ArrayList<>();

    // This is the constuctor for the vehicle that will access all of the variables that come with a car
    public Vehicle(String make, String model, String category, String color, int year, int width, int height, Customer owner) {
        this.model = model;
        this.make = make;
        this.category = category;
        this.color = color;
        this.year = year;
        this.width = width;
        this.height = height;
        this.owner = owner;
        this.spotsTaken = setSpotsTaken(category);
    }

    // This is the setter for if a spot is taken
    public int setSpotsTaken(String cat){
        if(category.contains("Motorcycle") || category.contains("Hatchback") || category.contains("Sedan")){
            return 1;
        } else  {
            return 2;
        }
    }

    // this will get the model of the vehicle trying to be parked
    public String getModel() {
        return model;
    }

    // this will get the make of the vehicle trying to be parked
    public String getMake() {
        return make;
    }

    // this will get the color of the vehicle trying to be parked
    public String getColor() {
        return color;
    }

    // this will get the year of the vehicle trying to be parked
    public int getYear() {
        return year;
    }

    // this will get the width of the vehicle trying to be parked
    public int getWidth() {
        return width;
    }

    // this will get the height of the vehicle trying to be parked
    public int getHeight() {
        return height;
    }

    // this will get the owner of the vehicle trying to be parked
    public Customer getOwner() {
        return owner;
    }

    // This is the setter for which parking lot the vehicle will be assigned to
    public void setLot(ParkingLot p) {
        this.lot = p;
    }

    // this will get the spot of where the vehicle is parked
    public ParkingSpot getSpot() {
        return spot;
    }

    public TwoParkingSpots getTwoSpot() {
        return twoSpot;
    }

    // this will get the price of the vehicle or vehicles that they are parking
    public ArrayList<Double> getPrices() {
        return prices;
    }

    // this will get the what they are paying for in the vehicle
    public ArrayList<String> getCostDescription() {
        return costDescription;
    }

    // This is the setter for the cost of the vehicle
    public void setCost(String description, double cost) {
        prices.add(cost);
        costDescription.add(description);
    }

    // this will get the type of vehicle they are parking
    public String getCategory() {
        return category;
    }

    // This is the setter for giving the vehicle a spot
    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public void setTwoSpot(TwoParkingSpots twoSpot) {
        this.twoSpot = twoSpot;
    }

    // this will get the lot they parked the vehicle in
    public ParkingLot getLot() {
        return lot;
    }

    // This is for the steward for how they can inspect the vehicle
    public String inspectVehicle()
    {
        return "Vehicle{" +
                "category='" + category + '\'' +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", width=" + width +
                ", height=" + height +
                ", spotsTaken=" + spotsTaken +
                '}';
    }

    public double totalCost(){
        double cost = 0.0;
        for(double x : prices){
            cost += x;
        }
        return Math.round(cost * 100.0) / 100.0;
    }

    public void setPrices(ArrayList<Double> prices) {
        this.prices = prices;
    }

    public void setCostDescription(ArrayList<String> costDescription) {
        this.costDescription = costDescription;
    }

    public int getSpotsTaken() {
        return spotsTaken;
    }

    // This is how the vehicle will print out will all of the owners information on their vehicle
    @Override
    public String toString() {
        if(this.getLot() == null){
            return "Vehicle{" +
                    "category='" + category + '\'' +
                    ", model='" + model + '\'' +
                    ", make='" + make + '\'' +
                    ", color='" + color + '\'' +
                    ", year=" + year +
                    ", width=" + width +
                    ", height=" + height +
                    ", spotsTaken=" + spotsTaken +
                    '}';
        } else {
            if(spotsTaken == 1){
                return "Vehicle{" +
                        "category='" + category + '\'' +
                        ", model='" + model + '\'' +
                        ", make='" + make + '\'' +
                        ", color='" + color + '\'' +
                        ", year=" + year +
                        ", width=" + width +
                        ", height=" + height +
                        ", spotsTaken=" + spotsTaken +
                        ", spot=" + spot.getLocation() +
                        ", lot=" + lot.getName() +
                        '}';
            }else{
                return "Vehicle{" +
                        "category='" + category + '\'' +
                        ", model='" + model + '\'' +
                        ", make='" + make + '\'' +
                        ", color='" + color + '\'' +
                        ", year=" + year +
                        ", width=" + width +
                        ", height=" + height +
                        ", spotsTaken=" + spotsTaken +
                        ", spot=" + Arrays.toString(twoSpot.getAllLocation()) +
                        ", lot=" + lot.getName() +
                        '}';
            }
        }
    }
}
