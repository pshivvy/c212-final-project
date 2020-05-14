/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This class is parking lot class
 */
import java.util.ArrayList;
import java.io.Serializable;

public class ParkingLot implements Serializable{
    private ParkingSpot[][] spotUserInfo;//Contain's information about the user's cars and their parking positions
    private Boolean[][] occupancy; //True or False which determines if the spot is taken
    private String name;//Name of the parkingLot
    private boolean hasSteward;// If a certain lot has a steward
    private Steward steward;// Creates an object of a Steward for each lot

    //Constructor
    public ParkingLot(String name, int rows, int columns, Steward s){
        this.name = name;
        this.hasSteward = true;
        this.steward = s;
        spotUserInfo = new ParkingSpot[rows][columns];
        occupancy = new Boolean[rows][columns];
        steward.setAssignedLot(this);
    }

    //Constructor
    public ParkingLot(String name, int rows, int columns){
        this.name = name;
        this.steward = null;
        spotUserInfo = new ParkingSpot[rows][columns];
        occupancy = new Boolean[rows][columns];
    }

    //Getter for Name to be accessed in other classes
    public String getName() {
        return name;
    }
    //Setter for Name to be accessed in other classes
    public void setName(String name) {
        this.name = name;
    }

    //Determines if the lot has a steward
    public boolean isHasSteward() {
        return hasSteward;
    }

    //Changes the parkinglot's status of having a steard
    public void setHasSteward(boolean hasSteward) {
        this.hasSteward = hasSteward;
    }

    //Getter of Steward
    public Steward getSteward() {
        return steward;
    }

    //Setter of Steward
    public void setSteward(Steward s) {
        this.steward = s;
    }

    //Gives details about the spot, given the row and column
    public boolean getOccupany(int row, int column){

        if(occupancy[row][column] == null || occupancy[row][column] == false){
            return false;
        }else{
            return true;
        }
    }

//    returns information about a spot, number of rows
    public ParkingSpot getSpotInfo(int row, int column){
        return spotUserInfo[row][column];
    }

    public TwoParkingSpots[] getTwoSpotsInfo(int row, int[]column){
        return new TwoParkingSpots[]{(TwoParkingSpots) spotUserInfo[row][(int)column[0]],(TwoParkingSpots) spotUserInfo[row][(int)column[1]]};
    }

    //Creates a spot in the parking lot for a user's car
    public void setSpot(int row, int column, ParkingSpot ps){
        if(ps == null){
            spotUserInfo[row][column] = null;
            occupancy[row][column] = null;
            getSteward().setAssignedLot(this);
        }else{
            spotUserInfo[row][column] = ps;
            occupancy[row][column] = true;
            ps.setSpotInfo(row, column, this);
            ps.getUser().setLotHistory(this.getName(), "User " + ps.getUser().getFirstName() + " parked " + ps.getVehicle() + " at " + this.getName() + " with " + ps.getParkingOption() + " parking option.");
            getSteward().setAssignedLot(this);
        }
    }


//    set the occupany of a specific spot on a parkingLot
    public void setOccupancy(int row, int column, boolean value) {
        occupancy[row][column] = value;
    }

    // add setSpot for TwoParkingSpots
    public void setSpot(int row, int[] column, TwoParkingSpots ps)
    {
        if(ps == null){
            spotUserInfo[row][column[0]] = null;
            spotUserInfo[row][column[1]] = null;
            occupancy[row][column[0]] = null;
            occupancy[row][column[1]] = null;
            getSteward().setAssignedLot(this);
        }else{
            spotUserInfo[row][column[0]] = ps;
            spotUserInfo[row][column[1]] = ps;
            occupancy[row][column[0]] = true;
            occupancy[row][column[1]] = true;
            ps.setSpotsInfo(row, column, this);
            ps.getUser().setLotHistory(this.getName(), "User " + ps.getUser().getFirstName() + " parked " + ps.getVehicle() + " at " + this.getName() + " with " + ps.getParkingOption() + " parking option.");
            getSteward().setAssignedLot(this);
        }
    }
    //Allows for the removal of a steward
    public void removeSteward(ParkingLot p)
    {
        p.setSteward(null);
    }

    //Gives the number of just rows in a lot
    public int getTotalRow(){
        return spotUserInfo.length;
    }
    //Gives the number of just  columns in a lot
    public int getTotalColumn(){
        return spotUserInfo[0].length;
    }


//    Decreases the capacity of a parking lot
    public void decreaseCapacity()
    {
        int numberOfColumns = spotUserInfo[0].length;
        int numberOfRows = spotUserInfo.length;

        ParkingSpot[][] newSpotsForInfo = new ParkingSpot[numberOfRows-1][numberOfColumns];
        Boolean[][] newOccupany = new Boolean[numberOfRows-1][numberOfColumns];

        int rowToDecrease = -1;
        for(int i = numberOfRows-1; i >= 0; i--){
            if(checkEmptyRow(i)){
                rowToDecrease = i;
                break;
            }
        }

        if(rowToDecrease == numberOfRows-1){
            for(int i = 0; i < numberOfRows; i++){
                for(int j = 0; j < numberOfColumns; j++){
                    if(getOccupany(i,j)){
                        newSpotsForInfo[i][j] = spotUserInfo[i][j];
                        newOccupany[i][j] = true;
                    }
                }
            }
            spotUserInfo = newSpotsForInfo;
            occupancy = newOccupany;
            System.out.println("Lot size decreased from: " + numberOfRows + " x " + numberOfColumns + " to: " + spotUserInfo.length + " x " + spotUserInfo[0].length);
        }else if(rowToDecrease < numberOfRows-1 && rowToDecrease >= 0){
            for(int i = 0; i < numberOfRows; i++){
                if(i < rowToDecrease){
                    for(int j = 0; j < numberOfColumns; j++){
                        if(getOccupany(i,j)){
                            newSpotsForInfo[i][j] = spotUserInfo[i][j];
                            newOccupany[i][j] = true;
                        }
                    }
                }else{
                    for(int j = 0; j < numberOfColumns; j++){
                        if(getOccupany(i,j)){
                            if(getSpotInfo(i,j).getVehicle().getSpotsTaken() == 1){
                                newSpotsForInfo[i-rowToDecrease][j] = spotUserInfo[i][j];
                                newSpotsForInfo[i-rowToDecrease][j].setSpotInfo(i-rowToDecrease,j,this);
                                newOccupany[i-rowToDecrease][j] = true;
                            }else{
                                TwoParkingSpots tp = new TwoParkingSpots(spotUserInfo[i][j].getUser(),spotUserInfo[i][j].getVehicle(),spotUserInfo[i][j].getParkingOption(),spotUserInfo[i][j].getLongOrShortTerm(),i-rowToDecrease,new int[]{j,j+1});
                                newSpotsForInfo[i-rowToDecrease][j] = tp;
                                newOccupany[i-rowToDecrease][j] = true;
                                newSpotsForInfo[i-rowToDecrease][j+1] = tp;
                                newOccupany[i-rowToDecrease][j+1] = true;
                                j++;
                            }
                        }
                    }
                }
            }
            spotUserInfo = newSpotsForInfo;
            occupancy = newOccupany;
            System.out.println("Lot size decreased from: " + numberOfRows + " x " + numberOfColumns + " to: " + spotUserInfo.length + " x " + spotUserInfo[0].length);
        }else{
            System.out.println("There are no empty rows, therefore cannot decrease parking lot.");
        }
    }

//    checks to see if a row is empty so decreaseCapacity() can be ran
    private boolean checkEmptyRow(int row)
    {
        boolean empty = false;
        for(int i = 0; i < occupancy[0].length; i++){
            if(getOccupany(row,i)){
                return false;
            }
        }
        return true;
    }

//    Increases the parkingLot by one row
    public void increaseCapacity()
    {
        int row = spotUserInfo.length;
        int column = spotUserInfo[0].length;
        ParkingSpot[][] newSpotsForInfo = new ParkingSpot[row+1][column];
        Boolean[][] newOccupany = new Boolean[row+1][column];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(getOccupany(i,j)){
                    newSpotsForInfo[i][j] = spotUserInfo[i][j];
                    newOccupany[i][j] = true;
                }
            }
        }

        spotUserInfo = newSpotsForInfo;
        occupancy = newOccupany;
        System.out.println("Increased capacity from: " + row + " x " + column + " to: " + spotUserInfo.length + " x " + spotUserInfo[0].length);
    }

//    get's the capacity of a parkingLot
    public int getCapacity()
    {
        int counter = 0;
        for(int i = 0; i < this.getTotalRow(); i++)
        {
            for (int j = 0; j < this.getTotalColumn(); j++)
            {
                if(!getOccupany(i, j))
                {
                    counter+=1;
                }
            }
        }
        return counter;
    }

//    checks if parkingLot is empty
    public boolean isEmpty()
    {
        for(int i = 0; i < this.getTotalRow(); i++)
        {
            for(int j = 0; j < this.getTotalColumn(); j++)
            {
                if(getOccupany(i,j))
                {
                    return false;
                }
            }
        }
        return true;
    }

//    Print out details as a string
    public String toString()
    {
        String output = "";
        for (int i = 0; i < occupancy.length; i++)
        {
            for (int j = 0; j < occupancy[0].length; j++)
            {
                if (getOccupany(i,j))
                {
                    output = output + "true,";
                } else {
                    output = output + "false,";
                }
            }
            output += "\n";
        }
        return "[" + output + "]";
    }
}
