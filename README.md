# Table Of Content

- [Group Info](#group-info)
- [Project Info](#project-info)
- [Project Structure](#project-structure)
- [Classes and their functions](#classes-and-their-functions)
- [Program Execution and Instructions](#program-execution-and-instructions)

***
## Group Info

Group number: 10

Project Choice: Parking Lot Management System

Members: Isaac Bordfeld, Karley Conroy, Shiv Patel, Saiesha Sharma

***
## Project Info
Our group decided to work on the Parking Lot Management System.
We created the following classes:
Admin, User, Customer, Steward, ParkingLot, ParkingSpot, TwoParkingSpots, History, Receipt, and ParkingLotControl

Information on these classes can be found in their respective skeleton class files in "src" and additional detail can be found on the report submitted on Canvas.

UML diagrams can be found in the report as well.

***
## Project Structure

Our project has the following folders which contain all the code/misc. information.

Folder (below each folder you can find the files they need to have):
      
      out - contains .class files when compiling
      
      objectFiles - contains user info files
                  * users.txt
                  * customers.txt
                  * stewards.txt
                  * admins.txt
                  * parkinglots.txt
                  
      src - contains source/.java files
                  * User.java
                  * Customer.java
                  * Admin.java
                  * Steward.java
                  * Vehicle.java
                  * ParkingLot.java
                  * ParkingSpot.java
                  * TwoParkingSpots.java
                  * Receipt.java
                  * History.java
                  * ParkingLotControl.java (main file)
      objectFiles - contains files which save program data
                  * admins.txt
                  * customers.txt
                  * users.txt
                  * stewards.txt
                  * parkinglots.txt

# Classes and their Functions

Detailed information on classes can be found on the report submitted to Canvas. Below is some brief information on class:

  -User: Abstract class; all other roles (Customer, Admin, etc) extend the User class.
 
  -Customer: Implements User; contains all information related to Customer ie, Vehicles, History, etc.
 
  -Admin: Implements User; contains functions that admin can carry out.
 
  -Steward: Implements User; contains functions available to Steward only.
 
  -Vehicle: Class that stores information of a Vehicle, all Vehicles can take up either 1 or 2 spots.
 
  -ParkingLot: Contains all information relating to lots, lot size, and information on parked vehicle.
 
  -ParkingSpot: Used when car takes up 1 spot.
 
  -TwoParkingSpots: Used when car takes up 2 spots.
 
  -History: Tracks User's history in lots they have parked in as well as Personal History.
 
  -Receipt: Used to present bill and payment portal when Customer is requesting their car.
 
  -ParkingLotControl: The main driver on which all the code runs on, contains menu, stores/reads in files for data persistence.
 
  NOTE: All classes except ParkingLotControl implement "Serializable" to read/write objects to maintina data persistence
 
# Program Execution and Instructions
  
  The program can be executed while <b>files in objectFiles are empty.</b> objectFiles still needs to contain the following (empty or not):
  
      * admins.txt
      * customers.txt
      * users.txt
      * stewards.txt
      * parkinglots.txt
  
  NOTE: Delete "-- REMOVE THIS ELEMENT, IT IS A PLACEHOLDER TO COMBAT GITHUB'S EMPTY FILE THING --" from all files in objectFiles for the program to work correctly.
  
  The only class that can be executed is ParkingLotControl. It is the test driver function. As long as the [Project Structure](#project-structure) is followed. 
  
  Execution of the code:
  
      - Run the ParkingLotControl file.
      - On the main menu, select the appropriate option to create user.
            - This option only lets your create Admin and Customer classes, as per the rubric.
      - Once user is created, you will be logged into the respective view. You can carry out all functions the logged in class is supposed to have.
      - Program will auto-save information as it is being processed. Think of it like checkpoints in video games but it is advised that once doing the task you needed to do, you should make sure to log out. This will ensure everything is saved.
      - NOTE: All views but 1 have the logout screen. The 1 that does not is the Payment Portal. The Payment Portal is called after a user requests their vehicle, the program ensures that the Customer pay before taking their car out the lot.
      - NOTE: If a Customer is blacklisted in a lot and they have cars parked in the lot, the Customer will be able to take their car out the lot but they will be unable to park.
