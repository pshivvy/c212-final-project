/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This is the customer's history
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

//This class creates the history of a customer's car, so that the customer can view any changes made

public class History implements Serializable {
    // Key = parking lot, Value = history of a lot
    private Map<String, ArrayList<String>> historyList = new HashMap<>();

    //Inputs the history for user
    public History(){
        historyList.put("User Activity", new ArrayList<>());
    }

    //Adds it to the Map
    public void addToHistory(String parkingLot, String lineItem){
        //The history is added depending on the parking lot that the car was in
        if(!historyList.containsKey(parkingLot)){
            historyList.put(parkingLot, new ArrayList<>());
            historyList.get(parkingLot).add(lineItem);
        }
        //If the parking lot is already found in the map then you formally add it here
        else {
            historyList.get(parkingLot).add(lineItem);
        }
    }

    public void addToHistory(String lineItem){
        historyList.get("User Activity").add(lineItem);
    }



    //Returns the history if the user requests it
    public String returnLotHistory(String parkingLot){
        //returns the history in a string format
        String hist = "";
        for(int i = 0; i < historyList.get(parkingLot).size(); i++){
            hist = hist + "\n   " + historyList.get(parkingLot).get(i);
        }
        return hist;
    }

    public String returnPersonalHistory(){
        //retunr the user activity in a string format
        String hist = "";
        for(int i = 0; i < historyList.get("User Activity").size(); i++){
            hist = hist + "\n   " + historyList.get("User Activity").get(i);
        }
        return "User Activity" + hist;
    }


    //A to string for the history of an indvidual and their vehicle depending on the parking lot
    // is returned in parking lot control
    public String allHistory(){
        String hist = "";
        hist += returnPersonalHistory() + "\n\n";
        for(Map.Entry<String, ArrayList<String>> e : historyList.entrySet()){
            if(!e.getKey().contains("User Activity")){
                hist = hist + "Customer's history at " + e.getKey() + "\n   " + returnLotHistory(e.getKey()) + "\n";
            }
        }
        return hist;
    }

    public Map<String, ArrayList<String>> getHistoryList() {
        return historyList;
    }
}
