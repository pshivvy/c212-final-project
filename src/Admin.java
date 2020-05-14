/*
   * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
   * Group 10
   * Date: 13 April 2020
   * Final Project
   * Description: This is the admin class which controls all the power of an admin
*/
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Admin extends User implements Serializable
{

    //Constructor
    public Admin(String username, String password, String firstName, String lastName)
    {
        super(username, password, firstName, lastName, "Admin");
    }

    //Adds a lot to the garage
    public void addParkingLot(Map<String,ParkingLot> lot, ParkingLot p)
    {
        lot.put(p.getName(),p);
    }

    //Delete a lot in the garage
    public void removeParkingLot(Map<String,ParkingLot> lots, ParkingLot lot)
    {
        lots.remove(lot.getName());
    }

    //Change the parking capacity in a lot
    public void adjustCapacity(String adjust, ParkingLot p, Steward s)
    {
        adjust=adjust.toLowerCase();
        if(s != null){
            if(adjust.equals("decrease"))
            {
                p.decreaseCapacity();
                s.setAssignedLot(p);
            } else if(adjust.equals("increase"))
            {
                p.increaseCapacity();
                s.setAssignedLot(p);
            }
        }else{
            if(adjust.equals("decrease"))
            {
                p.decreaseCapacity();
            } else if(adjust.equals("increase"))
            {
                p.increaseCapacity();
            }
        }
    }

    //Add a steward to a lot
    public void addSteward(Steward s, ParkingLot p)
    {
        p.setSteward(s);
        s.setAssignedLot(p);
    }

    //Can fire or remove a steward from a lot
    public void removeSteward(ParkingLot lot, Steward s)
    {
        s.getParkingLot().setSteward(null);
        s.setAssignedLot(null);
        lot.getSteward().setAssignedLot(null);
        lot.setSteward(null);
    }

    //If customer has violated the rules, the admin can blacklist a customer from specific parking lots
    public void blacklistCustomer(Customer c, ParkingLot p)
    {
        c.addBlackListLot(p);
    }
}