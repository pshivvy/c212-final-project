/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This is the reciept class here we compute the total charges made for individual veichles
 */

import java.io.Serializable;

public class Receipt implements Serializable {

    private String paymentType; //Describes what the payment method is (ex: cash, credit card, etc)

    //Constructor, will initalize the payment and it's type
    public Receipt(){}

    //Totals the bill, and the veichle it is linked with
    public String getReceipt(Vehicle v, boolean hasCoupon){
        // generates bill for given vehicle
        String bill = "";
        double total = 0.0;
        for(int i = 0; i < v.getCostDescription().size(); i++){
            total = total + v.getPrices().get(i);
            bill = bill + String.format("%-30s $%.2f%n", v.getCostDescription().get(i), v.getPrices().get(i));
        }
        if(hasCoupon){
            bill = bill + "\n" + String.format("%-30s %.2f%n", "Random coupon", (double)v.getOwner().getCoupon());
            bill = bill + "\n" + String.format("%-30s $%.2f%n", "Total:", total - total * ((double)v.getOwner().getCoupon()/100));
        } else {
            bill = bill + "\n" + String.format("%-30s $%.2f%n", "Total:", total);
        }

        return v.getMake() + " " + v.getModel() + "\n\n" + bill;
    }

    //Getter for the paymentType, will be used to create the presentable reciept in parkingLotManager
    public String getPaymentType() {
        return paymentType;
    }

    //Setter for the paymentType, also will be used to create the presentable reciept in parkingLotManager
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
