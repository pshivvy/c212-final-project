/**
 * Name: Karley Conroy, Isaac Bordfeld, Shiv Patel, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final project
 * Description: This is the stewards class
 */

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Steward extends User implements Serializable {

    // parking lot user is linked to
    private ParkingLot assignedLot;


    // this is the constructor for steward that will take from the parent class of user
    public Steward(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Steward");
        assignedLot = null;
    }

    // will check if there are spots available for the vehicle they are and the type of parking they would like
    public void checkForSpot(String option, String length, Vehicle v, ParkingLot p)
    {
        ArrayList<Point> freeSpots = new ArrayList<>();
        // this will happen in parkingLotControl
        // ask if they want regular or premium
        // ask for long or short term
        // NOT FINISHED
        int width = p.getTotalRow();
        int height = p.getTotalColumn();

        // if vehicle spots taken is 2, create TwoParkingSpots...
        if (v.getSpotsTaken() == 2) {
            // for loop stuff
            // if two are available but not next to each other
            // call moveCars
            Point[] spot = checkSpotsForTwo(p);
            if(spot == null){
                System.out.println("There are not enough spots in this lot to park this car.");
                return;
            }else{
                TwoParkingSpots ps = new TwoParkingSpots(v.getOwner(),v,option,length,(int)spot[0].getX(), new int[]{(int)spot[0].getY(),(int)spot[1].getY()});
                p.setSpot(ps.getRow(),ps.getColumns(),ps);
                System.out.println("Vehicle has been parked. Taking back to main menu.");
                if(option.equals("regular")){
                    if(length.equals("long")){
                        v.setCost("Long Term Regular Parking",25.00);
                    }else{
                        v.setCost("Short Term Regular Parking",15.00);
                    }
                }else{
                    if(length.equals("long")){
                        v.setCost("Long Term Premium Parking",45.00);
                    }else{
                        v.setCost("Short Term Premium Parking",35.00);
                    }
                }
                return;
            }
        } else {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (p.getOccupany(i, j) == false) {
                        freeSpots.add(new Point(i, j));
                    }
                }
            }

            if(freeSpots.size() >= 1){
                Random spot = new Random();
                Point parkSpot = freeSpots.get(spot.nextInt(freeSpots.size()));

                ParkingSpot ps = new ParkingSpot(v.getOwner(), v, option, length);
                p.setSpot((int) parkSpot.getX(), (int) parkSpot.getY(), ps);
                System.out.println("Vehicle has been parked. Taking back to main menu.");
                if(option.equals("regular")){
                    if(length.equals("long")){
                        v.setCost("Long Term Regular Parking",25.00);
                    }else{
                        v.setCost("Short Term Regular Parking",15.00);
                    }
                }else{
                    if(length.equals("long")){
                        v.setCost("Long Term Premium Parking",45.00);
                    }else{
                        v.setCost("Short Term Premium Parking",35.00);
                    }
                }
                return;
            }else{
                System.out.println("There are not enough spots in this lot to park this car.");
                return;
            }
        }
    }

    // this method will let the steward move your vehicle if we need more room in the parkingLot for a bigger car
    public Point[] checkSpotsForTwo(ParkingLot p)
    {
        if(p.getCapacity() >= 2){
            ArrayList<Point[]> emptySpots = new ArrayList<>();
            for(int i = 0; i < p.getTotalRow(); i++){
                for(int j = 0; j < p.getTotalColumn(); j++){
                    if(!p.getOccupany(i,j)){
                        if(j+1 <= p.getTotalColumn()-1 && !p.getOccupany(i,j+1)){
                            emptySpots.add(new Point[]{new Point(i,j),new Point(i,j+1)});
                        }
                    }
                }
            }
            if(emptySpots.size() > 0){
                Random rand = new Random();
                return emptySpots.get(rand.nextInt(emptySpots.size()));
            }else{
                return moveCars(p);
            }
        }else{
            System.out.println("There are not enough spots in this lot to park this car.");
            return null;
        }
    }

//    Moves car when a new vehicle that takes up two spaces is needed
    private Point[] moveCars(ParkingLot p)
    {
        ArrayList<Point> emptySpots = new ArrayList<>();
        ArrayList<Point> oneSpotTaken = new ArrayList<>();
        for(int i = 0; i < p.getTotalRow(); i++){
            for(int j = 0; j < p.getTotalColumn(); j++){
                if(!p.getOccupany(i,j)){
                    emptySpots.add(new Point(i,j));
                }else{
                    if(p.getSpotInfo(i,j).getVehicle().getSpotsTaken() == 1){
                        oneSpotTaken.add(new Point(i,j));
                    }
                }
            }
        }

        if(oneSpotTaken.size() <= 1){
            System.out.println("There are not enough spots in this lot to park this car.");
            return null;
        }

        Random rand = new Random();
        Point randomPoint = emptySpots.get(rand.nextInt(emptySpots.size()));

        if(((int)randomPoint.getY()-1) >= 0 && oneSpotTaken.contains(new Point((int)randomPoint.getX(),(int)randomPoint.getY()-1))){
            emptySpots.remove(randomPoint);
            Point newEmpty = emptySpots.get(rand.nextInt(emptySpots.size()));
            p.setSpot((int)newEmpty.getX(),(int)newEmpty.getY(),p.getSpotInfo((int)randomPoint.getX(),(int)randomPoint.getY()-1));
            p.setSpot((int)randomPoint.getX(),(int)randomPoint.getY()-1,null);
            return new Point[]{new Point((int)randomPoint.getX(),((int)randomPoint.getY()-1)),randomPoint};
        }else if((int)randomPoint.getY()+1 <= p.getTotalColumn()-1 && oneSpotTaken.contains(new Point((int)randomPoint.getX(),(int)randomPoint.getY()+1))){
            emptySpots.remove(randomPoint);
            Point newEmpty = emptySpots.get(rand.nextInt(emptySpots.size()));
            p.setSpot((int)newEmpty.getX(),(int)newEmpty.getY(),p.getSpotInfo((int)randomPoint.getX(),(int)randomPoint.getY()+1));
            p.setSpot((int)randomPoint.getX(),(int)randomPoint.getY()+1,null);
            return new Point[]{randomPoint,new Point((int)randomPoint.getX(),((int)randomPoint.getY()+1))};
        }else{
            return moveCars(p);
        }
    }

    // this method inspects your vehicle to see if your time is not up and to make sure that your vehicle is update to
    //date with what the user provided
    public void inspect(Vehicle v) {
        v.inspectVehicle();
    }

    // the steward can offer random coupons to random customers
    public void offerRandomCoupon(Customer c)
    {
        Random random = new Random();
        int couponPercent = random.nextInt(15) + 10;
        c.setCoupon(couponPercent);
        c.setPersonalHistory("Offered Coupon worth " + couponPercent + "%");
    }


    // this will penalize the user if there vehicle is in the lot for too long and will add the cost to the bill and
    // this will show in the users history
    public void penalize(Vehicle v, double cost)
    {
        v.setCost("Penalty for overstaying", cost);
        v.getOwner().setLotHistory(v.getLot().getName(), "Customer's " + v.getModel() + " " + v.getMake() + " was penalized a total of " + cost + " for overstaying at " + v.getLot().getName());
    }


    public void setAssignedLot(ParkingLot assignedLot) {
        this.assignedLot = assignedLot;
    }
    public ParkingLot getParkingLot() {
        return assignedLot;
    }

}
