/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This is the parking spot class for vehicles that take up two spots.
 */

import java.awt.*;
import java.io.Serializable;

public class TwoParkingSpots extends ParkingSpot implements Serializable {

    private int row;
    private int[] column;

    // this is a constructor that takes from the parent class
    public TwoParkingSpots(Customer u, Vehicle v, String parkingOption, String longOrShortTerm, int row, int[] column)
    {
        super(u, v, parkingOption, longOrShortTerm);
        this.row = row;
        this.column = column;
        this.getVehicle().setTwoSpot(this);
    }

    // this is a getter for the row
    public int getRow() {
        return row;
    }

    // this is a setter for the row
    public void setRow(int row) {
        this.row = row;
    }

    // this is the getter for the column
    public int[] getColumns() {
        return column;
    }

    // this is a setter for the column the uses an int array
    public void setColumn(int[] column) {
        this.column = column;
    }

    // this will get both spots that the car is parked in since the car will be taking two spots
    public Point[] getAllLocation() {
        return new Point[]{new Point(row,column[0]), new Point(row,column[1])};
    }

    // this will set both the spots information so you know which car is in them
     public void setSpotsInfo(int row, int column[], ParkingLot p) {
        this.row = row;
        this.column = column;
        super.getVehicle().setLot(p);
        super.getVehicle().setTwoSpot(this);
     }

     // this is how it will print out
    @Override
    public String toString() {
        return "ParkingSpot{" + "\n" +
                "  user=" + super.getUser() + "\n" +
                "  vehicle=" + super.getVehicle() + "\n" +
                "  parkingOption='" + super.getParkingOption() + '\'' + "\n" +
                "  longOrShortTerm='" + super.getLongOrShortTerm() + '\'' + "\n" +
                "  timeParked=" + super.getTimeParked() + "\n" +
                "  row= " + row + "\n" +
                "  columns= " + column[0] + " and " + column[1] + "\n" +
                '}';
    }
}
