/**
 * Name: Karley Conroy, Shiv Patel, Isaac Bordfeld, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final Project
 * Description: This is the admin class which controls all the power of an admin
 */
import java.awt.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

public class ParkingLotControl {
    private static Customer currentCustomer = null;
    private static Steward currentSteward = null;
    private static Admin currentAdmin = null;
    private static Map<String,User> allUsers = new HashMap<>();
    private static Map<String,Customer> allCustomers = new HashMap<>();
    private static Map<String,Steward> allStewards = new HashMap<>();
    private static Map<String,Admin> allAdmins = new HashMap<>();
    private static Map<String,ParkingLot> allParkingLots = new HashMap<>();

    static Scanner newSc = new Scanner(System.in); // creates the scanner

    // login Portal
    public static void login() throws IOException {
        System.out.println("Let's get you logged in."); // what is first prompted to the user
        System.out.println("At any point, you can enter 'Exit' to go to the main menu.");
        System.out.print("Enter your username: ");
        String username = newSc.next(); // this will take the users input
        username += newSc.nextLine();
        if(username.toLowerCase().equals("exit")){
            mainScreen(); // recursives back to the main menu
            return; // stops the program
        }
        String password = "";
        if(password.toLowerCase().equals("exit")){
            mainScreen(); // recursives back to the main menu
            return;
        }
        if(allUsers.containsKey(username)){
            System.out.print("Enter your password: ");
            password = newSc.next(); // takes in the user input for a password
            password += newSc.nextLine();
            if(password.toLowerCase().equals("exit")){
                mainScreen(); // recursives back to the main menu
                return;
            }
            if(!allUsers.get(username).getPassword().equals(password)){
                int counter = 0; // creates a variable instances
                while(counter < 3){
                    System.out.println("Incorrect password. Try again.\nYou will be logged out after " + (3 - counter) + " tries, you can reset your password thereafter.");
                    System.out.print("Enter password: ");
                    password = newSc.next(); // takes the user input if the password they entered is incorrect
                    password += newSc.nextLine();
                    if(password.toLowerCase().equals("exit")){
                        mainScreen();
                        return;
                        // this will exit the user if they wish to leave
                    }
                    if(!allUsers.get(username).getPassword().equals(password)){
                        counter++; // increases the counter
                        if(counter >= 3){
                            mainScreen(); // returns back to the main screen after three tries on the password
                            return;
                        }
                    } else {
                        System.out.println("Logged in");
                        System.out.println("=========================================================");
                        accessLevel(allUsers.get(username)); // if you are all to login in
                        return;
                    }
                }
            } else{
                System.out.println("Logged in");
                System.out.println("=========================================================");
                accessLevel(allUsers.get(username)); // if you are all to login in
                return;
            }
        }else {
            System.out.println("Username does not exist, let's try again.");
            System.out.println("=========================================================");
            login(); // recusive call
            return;
            // if you enter the wrong username then it will prompt the user to re-enter there username
        }
    }

    // how the file saves
    private static void save() throws IOException {
        File f = new File("objectFiles/users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allUsers); // saves for all users
        f = new File("objectFiles/admins.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allAdmins); // saves for all admins
        f = new File("objectFiles/customers.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allCustomers); // saves for all customers
        f = new File("objectFiles/stewards.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allStewards); // saves for all stewards
        f = new File("objectFiles/parkinglots.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allParkingLots); // saves for all parking lot
        oos.flush();
        oos.close(); // closes the reader
        return;
    }

    // logout Portal
    public static void logout() throws IOException {
        // logs user out and asks if they want to exit the application or to leave it running
        File f = new File("objectFiles/users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allUsers);
        f = new File("objectFiles/admins.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allAdmins);
        f = new File("objectFiles/customers.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allCustomers);
        f = new File("objectFiles/stewards.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allStewards);
        f = new File("objectFiles/parkinglots.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allParkingLots);
        oos.flush();
        oos.close();
        mainScreen();
        return;
        // this method will save all the files and it will end the program entirely
    }

    // Registration
    public static void createUser() throws IOException {
        //prompts user to create new Customer account
        // also checks for duplicate usernames
        System.out.println("Welcome to the User Creation Portal.\nAt any point in the process, you can type 'Exit' to go to the main menu.");
        System.out.println("*Note: This means you cannot have the username 'exit'.");
        System.out.print("Enter a username, we recommend your username be longer than 2 characters: ");
        String username = newSc.next(); // takes the user input
        username += newSc.nextLine();
        if(username.toLowerCase().equals("exit")){
            mainScreen(); // returns them to the main screen if they dont want to create a user
            return;
        }
        if(allUsers.containsKey(username)){
            System.out.println("This username already exists, try again.");
            boolean same = true;
            while(same){
                System.out.print("Try a different username: ");
                // checks if the username already exists in the program and then prompts them to change it if it does
                username = newSc.next(); // takes user input
                username += newSc.nextLine();
                if(username.toLowerCase().equals("exit")){
                    mainScreen(); // goes to main menu if they wish to exit the program
                    return;
                }
                if(allUsers.containsKey(username)){
                    System.out.println("Please try again!"); // checks again for the same username
                } else{
                    System.out.println("Unique username! Let's get your password.");
                    same = false; // if they are not the same then it will let them create a password
                }
            }
        }else{
            System.out.println("Unique username! Let's get your password."); // prompts to create a password
        }
        System.out.print("Enter a password: ");
        String password = newSc.next(); // takes input for a password
        password += newSc.nextLine();
        if(password.toLowerCase().equals("exit")){
            mainScreen(); // returns them to main screen if the exit
            return;
        }
        System.out.print("Enter your first name: ");
        String firstName = newSc.next(); // takes in the users first name
        firstName += newSc.nextLine();
        if(firstName.toLowerCase().equals("exit")){
            mainScreen(); // allows to exit
            return;
        }
        System.out.print("Enter your last name: ");
        String lastName = newSc.next(); // takes in the users last name
        lastName += newSc.nextLine();
        if(lastName.toLowerCase().equals("exit")){
            mainScreen(); // allows to exit
            return;
        }
        System.out.println("Almost done, promise.");
        System.out.print("Enter your role. You can either be a Customer or Admin: ");
        String role = newSc.next(); // allows the user to pick the role they would like to be
        role += newSc.nextLine();
        if(role.toLowerCase().equals("exit")){
            mainScreen(); // allows them to exit
            return;
        }
        role = role.toLowerCase(); // allows the user to enter the role lowercase and still accept it
        if(role.equals("customer") || role.equals("admin")){
            System.out.println("User created! Let me log you in..."); // can be either two roles
            if(role.toLowerCase().equals("customer")){
                currentCustomer = new Customer(username, password, firstName, lastName);
                allUsers.put(username,currentCustomer); // it will add the user to the user  map
                allCustomers.put(username,currentCustomer);// it will add the user to the customer map
                accessLevel(currentCustomer); // prompts them with the  customers main menu
                File f = new File("objectFiles/users.txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allUsers);
                f = new File("objectFiles/customers.txt");
                oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allCustomers);
                oos.flush();
                oos.close();
                // it then saves the data from the user to the user file and the customer file
                return;
            } else if(role.toLowerCase().equals("admin")){
                currentAdmin = new Admin(username, password, firstName, lastName);
                allUsers.put(username,currentAdmin); // it will add the user to the user map
                allAdmins.put(username,currentAdmin);// it will add the user to the admin map
                accessLevel(currentAdmin); // prompts them with the admin main menu
                File f = new File("objectFiles/users.txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allUsers);
                f = new File("objectFiles/admins.txt");
                oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allAdmins);
                oos.flush();
                oos.close();
                // will save the user to the admin and users files
                return;
            }
        } else {
            System.out.println("Invalid role, enter a valid role!\n The roles available are Customer or Admin");
            boolean same = true; // if the enter an invalid role
            while(same){
                System.out.print("Enter role: ");
                role = newSc.next(); // takes the users inputs
                role = role.toLowerCase(); // converts the input
                if(role.equals("exit")){
                    mainScreen(); // allows them to leave the program
                    return;
                }
                if(role.equals("customer") || role.equals("admin")){
                    System.out.println("User created! Let me log you in...");
                    System.out.println("=========================================================");
                    same = false;
                    if(role.toLowerCase().equals("customer")){
                        currentCustomer = new Customer(username, password, firstName, lastName);
                        allUsers.put(username,currentCustomer);  // it will add the user to the user map
                        allCustomers.put(username,currentCustomer);  // it will add the user to the customer map
                        accessLevel(currentCustomer);// prompts them with the customer main menu
                        File f = new File("objectFiles/users.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allUsers);
                        f = new File("objectFiles/customers.txt");
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allCustomers);
                        oos.flush();
                        oos.close();
                        // will save the user to the customer and users files
                        return;
                    } else if(role.toLowerCase().equals("admin")){
                        currentAdmin = new Admin(username, password, firstName, lastName);
                        allUsers.put(username,currentAdmin);  // it will add the user to the user map
                        allAdmins.put(username,currentAdmin);  // it will add the user to the admin map
                        accessLevel(currentAdmin); // prompts them with the admin main menu
                        File f = new File("objectFiles/users.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allUsers);
                        f = new File("objectFiles/admins.txt");
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allAdmins);
                        oos.flush();
                        oos.close();
                        // will save the user to the admin and users files
                        return;
                    }
                } else {
                    System.out.println("Invalid role, enter a valid role!\n The roles available are Customer or Admin");
                    // this will be prompted if the user enters the wrong role
                }
            }
        }
    }

    // Resets the password for all roles
    public static void resetPassword() throws IOException {
        System.out.println("Let's reset your password.\nAt any moment in the program, type 'Exit' to go to the main menu.");
        System.out.print("Enter your username: ");
        String username = newSc.next(); // takes the users input
        if(username.toLowerCase().equals("exit")){
            mainScreen(); // allows them to exit the program option
            return;
        }
        if(allUsers.containsKey(username)){
            System.out.print("Enter your first name: ");
            String firstName = newSc.next(); // takes the users first name
            if(firstName.toLowerCase().equals("exit")){
                mainScreen(); // allows them to exit the program option
                return;
            }
            System.out.print("Enter your last name: ");
            String lastName = newSc.next(); // takes in the users last name
            if(lastName.toLowerCase().equals("exit")){
                mainScreen(); // allows them to exit the program option
                return;
            }
            if(allUsers.get(username).getFirstName().toLowerCase().equals(firstName.toLowerCase()) && allUsers.get(username).getLastName().toLowerCase().equals(lastName.toLowerCase())){
                System.out.print("Enter your new password: ");
                String password = newSc.next(); // takes in what the password is the user wants
                if(password.toLowerCase().equals("exit")){
                    mainScreen(); // allows them to exit the program option
                    return;
                }
                allUsers.get(username).setPassword(password); // this will re set the users passwords
                String role = allUsers.get(username).getRole(); // this gets the role of the user
                if(role.toLowerCase().equals("exit")){
                    mainScreen(); // allows them to exit the program option
                    return;
                }
                if(role.equals("Customer")){
                    allCustomers.get(username).setPassword(password); // this will get the customers username and reset the password
                } else if(role.equals("Admin")){
                    allAdmins.get(username).setPassword(password); // this will get the admins username and reset the password
                } else {
                    allStewards.get(username).setPassword(password); // this will get the stewards username and reset their password
                }
                System.out.println("Password reset successful. Go log in!");
                File f = new File("objectFiles/users.txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allUsers);
                f = new File("objectFiles/admins.txt");
                oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allAdmins);
                f = new File("objectFiles/customers.txt");
                oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allCustomers);
                f = new File("objectFiles/stewards.txt");
                oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allStewards);
                oos.flush();
                oos.close();
                // this will save all of the information so that the new password is the password the just made is their password
                login(); // re makes them login
                return;
            } else {
                System.out.println("Information incorrect. Let's restart");
                resetPassword();
                // if they enter in the wrong information it will send them back to the beginning of the program
                return;
            }
        } else {
            System.out.println("Your username does not exist. Try creating a new user or reset password again.");
            mainScreen();
            // if the username is not in the system it will send them to the beginnning of the program altogether
            return;
        }

    }

    public static void exit(){
        System.out.println("Exiting Program...");
        // this is prompted when the user wants to exit the program
    }

    // this will be the intial login and how to register for the program
    public static void mainScreen() throws IOException {
        System.out.println("=========================================================");
        System.out.print("1) Create New User\n2) Login\n3) Reset Password\n4) Exit\nSelect option: ");
        if(newSc.hasNextInt()){
            int chooseOption = newSc.nextInt(); // this will take number input from the user
            System.out.println("=========================================================");
            if(chooseOption == 1){
                createUser(); // if the choose option one then they will go to the create user function
                return;
            } else if (chooseOption == 2){
                login(); // if they choose option 2 then they will go to the login function
                return;
            } else if (chooseOption == 3)
            {
                resetPassword(); // if they choose option 3 then they will go to the reset password function
                return;
            } else if(chooseOption == 4)
            {
                exit(); // if they choose option 4 then they will exit the program
                save(); // this will save the information
                return;
            } else{
                System.out.print("You entered an invalid entry. Try again");
                mainScreen();
                return;
            }
        } else {
            System.out.print("You entered an invalid entry. Try again");
            newSc.next();
            mainScreen();
            // this is if the user did not input any of the options, then it will recall the function and make
            // them try again
            return;
        }
    }

    // this is where all the main menus get printed from and all the functions are called from
    public static void accessLevel(User activeUser) throws IOException {
        System.out.println("Welcome, " + activeUser.getFirstName() + "!");
        System.out.println("***NOTE: Remember to log out! If you do not, changes you made will not be saved.***");
        if(activeUser.getRole().equals("Customer")){
            currentCustomer = (Customer) activeUser; // if they are a customer
            System.out.print("1) Add/Remove Vehicle\n2) Park/Request Vehicle\n3) View All Vehicles\n4) View History\n5) Logout\nSelect option: ");
            // prompts them will options to choose from
            int option = 0;
            if(newSc.hasNextInt()){
                option = newSc.nextInt(); // takes the users inputs
                System.out.println("=========================================================");
                if(option == 1){
                    System.out.print("1) Add Vehicle\n2) Remove Vehicle\n3) Exit to Main Menu\n4) Logout\nSelect option: ");
                    // if they chgoose 1 then they get prompted with this menu to choose from
                    if(newSc.hasNextInt()){
                        option = newSc.nextInt(); // takes user input
                        if(option == 1){
                            addVehicle(); // if choosen 1 then they go to add a vehicle
                            return;
                        } else if(option == 2){
                            removeVehicle();  // if choosen 2 then they go to remove a vehicle
                            return;
                        }else if(option == 3){
                            accessLevel(currentCustomer); // will return to the main menu of the customer
                            return;
                        }else if(option == 4){
                            logout(); // will log the customer out
                            return;
                        }else{
                            System.out.println("Invalid option. Going back to main menu...");
                            accessLevel(currentCustomer);
                            return;
                            // if they dont enter a valid input returns to top function
                        }
                    }else{
                        System.out.println("Invalid option, taking back to main menu.");
                        newSc.next();
                        accessLevel(currentCustomer);
                        // if they dont enter a valid input returns to top function
                    }
                }else if(option == 2){
                    System.out.print("1) Park Vehicle\n2) Request Vehicle\n3) Exit to Main Menu\n4) Logout\nSelect option:");
                    if(newSc.hasNextInt()){
                        option = newSc.nextInt();
                        if(option == 1){
                            if(allParkingLots.size() <= 0){
                                System.out.println("No lots exist yet, please try again later.");
                                accessLevel(currentCustomer);
                                return;
                            }else{
                                parkVehicle();
                                return;
                            }
                        } else if(option == 2){
                            requestVehicle();
                            return;
                        }else if(option == 3){
                            accessLevel(currentCustomer);
                            return;
                        }else if(option == 4){
                            logout();
                            return;
                        }else{
                            System.out.println("Invalid option. Going back to main menu...");
                            accessLevel(currentCustomer);
                            return;
                        }
                    }else{
                        System.out.println("Invalid option, taking back to main menu.");
                        newSc.next();
                        accessLevel(currentCustomer);
                        return;
                    }
                }else if(option == 3){
                    if(currentCustomer.getVehicles().size() > 0) {
                        System.out.println(currentCustomer.viewVehicles());
                        System.out.print("Type anything once you are ready to go back to the main menu or type 'Logout' to log out: ");
                        String back = newSc.next();
                        if(back.toLowerCase().equals("logout")){
                            logout();
                            return;
                        }
                        accessLevel(currentCustomer);
                        return;
                    }else{
                        System.out.println("You have no cars, add a vehicle.");
                        accessLevel(currentCustomer);
                        return;
                    }
                } else if(option == 4){
                    viewHistory();
                    return;
                } else if(option == 5){
                    logout();
                    return;
                } else {
                    System.out.println("Enter a valid option.");
                    System.out.println("=========================================================");
                    accessLevel(currentCustomer);
                    return;
                }
            }else {
                System.out.println("Enter a valid number...");
                System.out.println("=========================================================");
                newSc.next();
                accessLevel(currentCustomer);
                return;
            }
        }
        else if(activeUser.getRole().equals("Admin")){
            currentAdmin = (Admin)activeUser; // if the user is an admin
            System.out.print("1) Hire/Fire Stewards\n2) Edit Parking Lot\n3) Blacklist Customers\n4) Logout\nSelect option: ");
            int option = 0;
            // prompts the user with this menu
            if(newSc.hasNextInt()) {
                option = newSc.nextInt(); // this will take the users input
                if(option == 1){
                    System.out.print("1) Hire Steward\n2) Fire Steward\n3) Exit to Main Menu\n4) Logout\nSelect option: ");
                    // if the choose 1 it will then prompt them with this menu
                    if(newSc.hasNextInt()){
                        option = newSc.nextInt();
                        if(option == 1){
                            hireSteward(currentAdmin); // if the choose 1 it will go to hire a steward
                            return;
                        }else if(option == 2){
                            fireSteward();// if the choose 2 it will go to fire  a steward
                            return;
                        }else if(option == 3){
                            accessLevel(currentAdmin); // returns to the top of this function
                            return;
                        }else if(option == 4){
                            logout(); // logs them out
                            return;
                        }else{
                            System.out.println("Invalid option, going back to the main menu...");
                            accessLevel(currentAdmin);
                            return;
                            // if it is an invalid option prompts them this
                        }
                    }else{
                        System.out.println("Invalid option, going back to the main menu...");
                        newSc.next();
                        accessLevel(currentAdmin);
                        return;
                        // if it is an invalid option prompts them this
                    }
                }else if(option == 2){
                    System.out.print("1) Add Parking Lot\n2) Remove Parking Lot\n3) Adjust Capacity\n4) Edit Stewards (for Lots)\n5) Exit to Main Menu\n6) Logout\nSelect option: ");
                    // if they choose 2 it prompts them with this menu
                    if(newSc.hasNextInt()){
                        option = newSc.nextInt();
                        if(option == 1){
                            addLot(); // choose 1 it will add it to the lot
                            return;
                        }else if(option == 2){
                            removeLot(); // choose 2 it will remove the lot
                            return;
                        }else if(option == 3){
                            if(allParkingLots.size() > 0){
                                System.out.print("1) Increase Lot Size\n2) Decrease Lot Size\n3) Exit to Main Menu\n4) Logout\nSelect option: ");
                                // if the user chooses option 3 it will prompt this menu
                                if(newSc.hasNextInt()){
                                    option = newSc.nextInt();
                                    if(option == 1){
                                        adjustCapacity("increase"); // this will send them to the increase capacity
                                        return;
                                    }else if(option == 2){
                                        adjustCapacity("decrease");// this will send them to the decrease capacity
                                        return;
                                    }else if(option == 3){
                                        accessLevel(currentAdmin); // recur
                                        return;
                                    }else if(option == 4){
                                        logout(); // this will log out the user
                                        return;
                                    }else{
                                        System.out.println("Invalid option, going back to the main menu...");
                                        accessLevel(currentAdmin);
                                        return;
                                        // returns to the top if the function if the enter an invalid function
                                    }
                                }else{
                                    System.out.println("Invalid option, going back to the main menu...");
                                    newSc.next();
                                    accessLevel(currentAdmin);
                                    return;
                                    // returns to the top if the function if the enter an invalid function
                                }
                            }else{
                                System.out.println("There are not lots. Please add one and try again later.");
                                accessLevel(currentAdmin);
                                return;
                                // returns to the top if the function if the enter an invalid function
                            }
                        }else if(option == 4){
                            System.out.print("1) Assign Steward to Lot\n2) Remove Steward from Lot\n3) Exit to Main Menu\n4) Logout\nSelect an option: ");
                            // if they choose option 4 it prompts them with this menu
                            if(newSc.hasNextInt()){
                                option = newSc.nextInt();
                                if(option == 1){
                                    assignSteward(); // if they choose option 1 it will assign a steward
                                    return;
                                }else if(option == 2){
                                    removeSteward(); // choose option 2 it will remove a steward
                                    return;
                                }else if(option == 3){
                                    accessLevel(currentAdmin); // recur
                                    return;
                                }else if(option == 4){
                                    logout(); // will log out the user
                                    return;
                                }else{
                                    System.out.println("Invalid option, going back to main menu...");
                                    accessLevel(currentAdmin);
                                    return;
                                    // returns to the top if the function if the enter an invalid function
                                }
                            }else{
                                System.out.println("Invalid option. Taking back to main menu.");
                                newSc.next();
                                accessLevel(currentAdmin);
                                return;
                                // returns to the top if the function if the enter an invalid function
                            }
                        }else if(option == 5){
                            accessLevel(currentAdmin); // recur if option 5
                            return;
                        }else if(option == 6){
                            logout(); // logout of the program if it is option 6
                            return;
                        }else{
                            System.out.println("Invalid option, going back to the main menu...");
                            accessLevel(currentAdmin);
                            return;
                            // returns to the top if the function if the enter an invalid function
                        }
                    }else{
                        System.out.println("Invalid option, going back to the main menu...");
                        newSc.next();
                        accessLevel(currentAdmin);
                        return;
                        // returns to the top if the function if the enter an invalid function
                    }
                }else if(option == 3){
                    blacklistCustomer();
                }else if(option == 4){
                    logout();
                    return;
                }else{
                    System.out.println("Invalid option. Try again.");
                    accessLevel(currentAdmin);
                    // returns to the top if the function if the enter an invalid function
                }
            }else{
                System.out.println("Invalid option. Try again.");
                newSc.next();
                accessLevel(currentAdmin);
                // returns to the top if the function if the enter an invalid function
            }
        }
        else{
            currentSteward = (Steward) activeUser; // if the user is a steward
            System.out.print("1) Inspect Vehicle\n2) Assign Gift Coupon\n3) Logout\nSelect option: ");
            int option = 0;
            // prompts them with this menu
            if(newSc.hasNextInt()) {
                option = newSc.nextInt();
                if(option == 1){
                    showParkedCars(currentSteward); // if they choose option 1 then it will inspect the vehicles in the lot
                } else if(option == 2){
                    assignGift(currentSteward); // choose option 2 then it will assign a coupn to a user
                } else if(option == 3){
                    logout(); // will logout of the program
                    return;
                } else {
                    System.out.println("Invalid option. Try again.");
                    accessLevel(currentSteward);
                    return;
                    // returns to the top if the function if the enter an invalid function
                }
            }else{
                System.out.println("Invalid option. Try again.");
                newSc.next();
                accessLevel(currentSteward);
                return;
                // returns to the top if the function if the enter an invalid function
            }
        }
    }

    // Customer's helper functions

    // removes the vehicle
    private static void removeVehicle() throws IOException {
        if(currentCustomer.getVehicles().size() > 0){
            System.out.println("=========================================================");
            for(int i = 0; i < currentCustomer.getVehicles().size(); i++){
                System.out.println(i+1 + ": " + currentCustomer.getVehicles().get(i).getMake() + " " + currentCustomer.getVehicles().get(i).getModel());
            } // displays all the vehicles they want
            System.out.println(currentCustomer.getVehicles().size()+1 + " Exit to main menu");
            System.out.println(currentCustomer.getVehicles().size()+2 + " Logout");
            System.out.print("Enter number to remove or exit/logout: ");
            // theses are the menu for this method
            int option = 0;
            if(newSc.hasNextInt()){
                option = newSc.nextInt(); // user input
                if(option <= currentCustomer.getVehicles().size() && option > 0){
                    currentCustomer.deleteVehicle(currentCustomer.getVehicles().get(option-1)); // will delete the vehicle
                }else if(option == currentCustomer.getVehicles().size()+1){
                    accessLevel(currentCustomer); // returns to the main menu
                    return;
                }else if(option == currentCustomer.getVehicles().size()+2){
                    logout(); // log outs
                    return;
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    removeVehicle();
                    return;
                    // if it is an invalid option
                }
            }else{
                System.out.println("Invalid option. Try again.");
                System.out.println("=========================================================");
                newSc.next();
                removeVehicle();
                return;
                // if it is an invalid option
            }
            System.out.println("Vehicle has been removed. Taking you back to the main menu...");
            System.out.println("=========================================================");
            save();
            accessLevel(currentCustomer);
            return;
            // if it is an invalid option
        }else{
            System.out.println("You do not have any vehicles. Add some vehicles first.\nTaking back to main menu");
            System.out.println("=========================================================");
            accessLevel(currentCustomer);
            return;
            // if it is an invalid option
        }
    }

    // adds a vehicle
    private static void addVehicle() throws IOException {
        System.out.println("=========================================================");
        System.out.println("At any point, if you want to go to the main menu, enter 'Exit' and 'Logout' to logout.");
        System.out.print("Enter the make of your car: ");
        String make = newSc.next(); // takes the make of the car
        //make += newSc.next();
        if(make.toLowerCase().equals("exit")){
            System.out.println("Taking back to main menu");
            accessLevel(currentCustomer); // if the want to exit
            return;
        }else if(make.toLowerCase().equals("logout")){
            System.out.println("Logging out");
            logout(); // if they want to logout of the program
            return;
        }
        System.out.print("Enter the model of your car: ");
        String model = newSc.next(); // takes the model of the car
        if(model.toLowerCase().equals("exit")){
            System.out.println("Taking back to main menu");
            accessLevel(currentCustomer); // main menu
            return;
        }else if(model.toLowerCase().equals("logout")){
            System.out.println("Logging out");
            logout(); // logs the user out
            return;
        }
        System.out.println("Choose a category number:\n1. Sedan\n2. Hatchback\n3. Motorcycle\n4. SUV\n5. Pickup Truck\n6. Van");
        System.out.print("Enter the category number of your car: ");
        int type = 0;
        String category ="";
        if(newSc.hasNextInt()){
            type = newSc.nextInt();
            if (type == 1) {
                category = "Sedan"; // if they choose 1
            }else if(type == 2){
                category = "Hatchback"; // if they choose 2
            }else if(type == 3){
                category = "Motorcycle"; // if they choose 3
            }else if(type == 4){
                category = "SUV"; // if they choose 4
            }else if(type == 5){
                category = "Pickup Truck"; // if they choose 5
            }else if(type == 6){
                category = "Van"; // if they choose 6
            }else{
                System.out.println("=========================================================");
                System.out.println("That is not a valid option. Lets remake your vehicle.");
                addVehicle(); // if it is not valid send them to the beginning
            }
        }else if(newSc.hasNext()){
            category = newSc.next(); // takes the input for category
            if(category.toLowerCase().equals("exit")){
                System.out.println("Taking back to main menu");
                accessLevel(currentCustomer); // if they exit
                return;
            }else if(category.toLowerCase().equals("logout")){
                System.out.println("Logging out");
                logout(); // log outs
                return;
            }else{
                System.out.println("That is not a valid option. Lets remake your vehicle.");
                addVehicle();
                return;
                // if it is not a valid input
            }
        }else{
            System.out.println("=========================================================");
            System.out.println("That is not a valid option. Lets remake your vehicle.");
            addVehicle();
            return;
            // if it is not a valid input
        }
        System.out.print("Enter the color of your car: ");
        String color = newSc.next(); // takes the color
        if(color.toLowerCase().equals("exit")){
            System.out.println("Taking back to main menu");
            accessLevel(currentCustomer); // if they exit
            return;
        }else if(color.toLowerCase().equals("logout")){
            System.out.println("Logging out");
            logout(); // if they log out
            return;
        }
        System.out.print("Enter the year of your car: ");
        int year = 0;
        if(newSc.hasNextInt()){
            year = newSc.nextInt(); // takes the year of the car
        }else if(newSc.hasNext()){
            String yearStr = newSc.next();
            if(yearStr.toLowerCase().equals("exit")){
                System.out.println("Taking back to main menu");
                accessLevel(currentCustomer); // if they exit the program
                return;
            }else if(yearStr.toLowerCase().equals("logout")){
                System.out.println("Logging out"); // if they log out of the program
                logout();
                return;
            }else{
                System.out.println("=========================================================");
                System.out.println("That is not a valid option. Lets remake your vehicle.");
                addVehicle();
                return;
                // if it is not a valid input
            }
        } else{
            System.out.println("=========================================================");
            System.out.println("That is not a valid option. Lets remake your vehicle.");
            addVehicle();
            return;
            // if it is not a valid input
        }
        System.out.print("Enter the width of your car: ");
        int width = 0;
        if(newSc.hasNextInt()){
            width = newSc.nextInt(); // takes in the width of the car
        }else if(newSc.hasNext()){
            String widthStr = newSc.next();
            if(widthStr.toLowerCase().equals("exit")){
                System.out.println("Taking back to main menu");
                accessLevel(currentCustomer); // if they exit
                return;
            }else if(widthStr.toLowerCase().equals("logout")){
                System.out.println("Logging out");
                logout(); // if they logout
                return;
            }else{
                System.out.println("=========================================================");
                System.out.println("That is not a valid option. Lets remake your vehicle.");
                addVehicle();
                return;
                // if it is not a valid input
            }
        } else{
            System.out.println("=========================================================");
            System.out.println("That is not a valid option. Lets remake your vehicle.");
            addVehicle();
            return;
            // if it is not a valid input
        }
        System.out.print("Enter the height of your car: ");
        int height = 0;
        if(newSc.hasNextInt()){
            height = newSc.nextInt(); // takes in the height of the car
        } else if(newSc.hasNext()){
            String heightStr = newSc.next();
            if(heightStr.toLowerCase().equals("exit")){
                System.out.println("Taking back to main menu");
                accessLevel(currentCustomer); // if they exit the program
                return;
            }else if(heightStr.toLowerCase().equals("logout")){
                System.out.println("Logging out");
                logout(); // if they want to log out
                return;
            }else{
                System.out.println("=========================================================");
                System.out.println("That is not a valid option. Lets remake your vehicle.");
                addVehicle();
                return;
                // if it is not a valid input
            }
        } else{
            System.out.println("=========================================================");
            System.out.println("That is not a valid option. Lets remake your vehicle.");
            addVehicle();
            return;
            // if it is not a valid input
        }
        currentCustomer.addVehicle(new Vehicle(make,model,category,color,year,width,height,currentCustomer)); // adds the vehicle to the customer
        System.out.println("Vehicle added successfully. Returning to main menu");
        save(); // saves the vehicle to the customer
        System.out.println("=========================================================");
        accessLevel(currentCustomer);
        return;
    }

    // view the history of the customer
    private static void viewHistory() throws IOException{
        Map<String,ArrayList<String>> history = currentCustomer.getHistory().getHistoryList();
        if(history.get("User Activity").size() > 0){
            System.out.print("Select a number below:\n1) Personal History\n2) Lot History\n3) All History\n4) Exit to Main Menu\n5) Logout\nEnter option: ");
            // prompts the menu
            int option = 0;
            if(newSc.hasNextInt()){
                option = newSc.nextInt(); // nexts the input
                if(option == 1){
                    System.out.println(currentCustomer.getPersonalHistory()); // views there personal history
                    System.out.print("Type anything once you are ready to go back or 'Logout' to log out: ");
                    String back = newSc.next(); // can tyoe anything to leave
                    if(back.toLowerCase().equals("logout")){
                        logout(); // logouts
                        return;
                    }
                    accessLevel(currentCustomer); // main menu
                    return;
                }else if(option == 2){
                    if(history.size() > 1) {
                        ArrayList<String> lots = new ArrayList<>();
                        for (Map.Entry<String, ArrayList<String>> element : history.entrySet()) {
                            if (!element.getKey().equals("User Activity")) {
                                lots.add(element.getKey());
                            }
                        }
                        System.out.println("Select a number below: ");
                        for (int i = 0; i < lots.size(); i++) {
                            System.out.println(i + 1 + ": " + lots.get(i));
                        } // gets teh lot history
                        System.out.print("Enter option: ");
                        int value = 0;
                        if (newSc.hasNextInt()) {
                            value = newSc.nextInt();
                            value--;
                            if (value <= lots.size() - 1 && value >= 0) {
                                System.out.println(currentCustomer.getLotHistory(lots.get(value))); // allows you to view the lot history
                                System.out.print("Type anything once you are ready to go back or 'Logout' to log out: ");
                                String back = newSc.next(); // takes in anything to leave w=once your done
                                if(back.toLowerCase().equals("logout")){
                                    logout();
                                    return;
                                }
                                accessLevel(currentCustomer); // back to main menu
                                return;
                            } else {
                                System.out.println("This is an invalid option. Please try again.");
                                newSc.next();
                                viewHistory();
                                return;
                                // if it is not a valid input
                            }
                        }else{
                            System.out.println("Invalid option, try again.");
                            newSc.next();
                            viewHistory();
                            return;
                        }
                    }else{
                        System.out.println("You have no lot history. Park a vehicle to get lot history.");
                        accessLevel(currentCustomer);
                        return;
                        // if you have no history in the program
                    }
                }else if(option == 3){
                    System.out.println(currentCustomer.getAllHistory());
                    System.out.print("Type anything once you are ready to go back or 'Logout' to log out: ");
                    String back = newSc.next();
                    // you can view all the history you have all together
                    if(back.toLowerCase().equals("logout")){
                        logout();
                        return;
                    }
                    accessLevel(currentCustomer); // main menu
                    return;
                }else if(option == 4){
                    accessLevel(currentCustomer);
                    return;
                }else if(option == 5){
                    logout(); // logout
                    return;
                }else{
                    System.out.println("This is an invalid option. Please try again.");
                    viewHistory();
                    return;
                    // if it is not a valid input
                }
            }else{
                System.out.println("This is an invalid option. Please try again.");
                newSc.next();
                viewHistory();
                return;
                // if it is not a valid input
            }

        }else{
            System.out.println("You have no history. Add a vehicle to get history.");
            accessLevel(currentCustomer);
            return;
            // if you have no history at all
        }
    }

    // park vehicle
    private static void parkVehicle() throws IOException {
        if(currentCustomer.getVehicles().size() > 0){
            System.out.println("=========================================================");
            ArrayList<Integer> alreadyParked = new ArrayList<>();
            for(int i = 0; i < currentCustomer.getVehicles().size(); i++){
                if(currentCustomer.getVehicles().get(i).getLot() != null){
                    alreadyParked.add(i+1);
                    System.out.println(i+1 + ": " + currentCustomer.getVehicles().get(i).getMake() + " " + currentCustomer.getVehicles().get(i).getModel() + "; Parked at: " + currentCustomer.getVehicles().get(i).getLot().getName());
                }else{
                    System.out.println(i+1 + ": " + currentCustomer.getVehicles().get(i).getMake() + " " + currentCustomer.getVehicles().get(i).getModel());
                } // this is how they choose which car to park
            }
            System.out.println(currentCustomer.getVehicles().size()+1 + " Exit to main menu");
            System.out.println(currentCustomer.getVehicles().size()+2 + " Logout");
            System.out.print("Enter number to remove or exit/logout: ");
            // prompts the main menu
            int option = 0;
            if(newSc.hasNextInt()){
                option = newSc.nextInt(); // user input
                if(option <= currentCustomer.getVehicles().size() && option > 0 && !alreadyParked.contains(option)){
                    presentLots(currentCustomer.getVehicles().get(option-1));
                    return;
                }else if(option <= currentCustomer.getVehicles().size() && option > 0 && alreadyParked.contains(option)){
                    System.out.println("Vehicle is already parked. Request vehicle from main menu and try again.");
                    accessLevel(currentCustomer); // if the vehicle is parked
                    return;
                }else if(option == currentCustomer.getVehicles().size()+1){
                    accessLevel(currentCustomer);
                    return;
                }else if(option == currentCustomer.getVehicles().size()+2){
                    logout(); // logs you out
                    return;
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    parkVehicle();
                    return;
                    // if it is not a valid input
                }
            }else{
                System.out.println("Invalid option. Try again.");
                System.out.println("=========================================================");
                newSc.next();
                parkVehicle();
                return;
                // if it is not a valid input
            }
        }else{
            System.out.println("You do not have any vehicles. Add some vehicles first.\nTaking back to main menu");
            System.out.println("=========================================================");
            accessLevel(currentCustomer);
            return;
            // if you have no vehicles in our system
        }
    }

    private static void presentLots(Vehicle v) throws IOException {
        Set<String> openLots = new HashSet<>();
        for(Map.Entry<String,ParkingLot> p : allParkingLots.entrySet()){
            if(!currentCustomer.getBlackListedParkingLots().containsKey(p.getKey()) && p.getValue().getSteward() != null){
                openLots.add(p.getKey());
                System.out.println("- " + p.getKey());
            } // if they are blacklisted
        }
        System.out.println("- Exit");
        System.out.println("- Logout");
        System.out.print("Enter a lot name or exit/logout: ");
        // prompts the menu
        String option = "";
        if(openLots.size() > 0){
            if(newSc.hasNext()){
                option = newSc.next(); // user input
                option += newSc.nextLine();
                if(openLots.contains(option) && allParkingLots.containsKey(option)){
                    boolean open = checkForSpace(v,allParkingLots.get(option)); // checks for open spaces in lot
                    if(open){
                        parkingOption(v,allParkingLots.get(option)); // return the option
                        return;
                    }else{
                        openLots.remove(option);
                        for(String p : openLots){
                            open = checkForSpace(v,allParkingLots.get(p));
                            if(open){
                                option = p;
                                break;
                                // if there are other lots
                            }
                        }
                        if(!open){
                            System.out.println("No current lots have enough space to let you park. Please come back another time.");
                            System.out.println("Taking back to main menu...");
                            accessLevel(currentCustomer);
                            return;
                            // no room in any lot
                        }else{
                            System.out.print("The lot you picked did not have enough space. We can offer you: " + option + "\nWould you like to go to this lot? Type 'Yes' to proceed and anything to go back: ");
                            String newOption = "";
                            // if they lot they picked is full
                            if(newSc.hasNext()){
                                newOption = newSc.next();
                                newOption += newSc.nextLine();
                                if(newOption.toLowerCase().equals("yes")){
                                    parkingOption(v,allParkingLots.get(option));
                                    return;
                                    // if they wanted the recommended lot
                                }else{
                                    System.out.println("Taking back to main menu...");
                                    accessLevel(currentCustomer); // to main meu
                                    return;
                                }
                            }else{
                                System.out.println("Taking back to main menu...");
                                accessLevel(currentCustomer); // to main menu
                                return;
                            }
                        }
                    }
                }else if(option.toLowerCase().equals("exit")){
                    accessLevel(currentCustomer); // exits
                    return;
                }else if(option.toLowerCase().equals("logout")){
                    logout();
                    return;
                }else{
                    System.out.println("Invalid option, pick lot again.");
                    presentLots(v);
                    return;
                    // if it is not a valid input
                }
            }else{
                System.out.println("Invalid option. Pick lot listed or exit/logout");
                presentLots(v);
                return;
                // if it is not a valid input
            }
        }else{
            System.out.println("\nYou cannot park at this time.\nThis is either due to you being blacklisted in all lots or no lots having any steward.\nTry again later.");
            accessLevel(currentCustomer);
            return;
            // cant park
        }
    }

    private static boolean checkForSpace(Vehicle v, ParkingLot p){
        boolean space = false;
        if(v.getSpotsTaken() == 1){
            return p.getCapacity() >= 1; // gets teh capacity of the lot
        }else{
            Point[] pt = p.getSteward().checkSpotsForTwo(p); // if the car takes up two spots
            if(pt == null){
                return false; // if it is
            }else{
                return true; // returns this if it not null
            }
        }
    }

    private static void parkingOption(Vehicle v, ParkingLot p) throws IOException {
        System.out.print("1) Regular Parking\n2) Premium Parking (Car Wash included!)\n3) Exit to Main Menu\n4) Logout\nSelect option: ");
        String type = "";
        String length = "";
        // prompts this menu of the parking optiond
        if(newSc.hasNextInt()){
            int typeNum = newSc.nextInt();
            if(typeNum == 1){
                type = "regular"; // if the type 1
                System.out.print("1) Long Term Parking\n2) Short Term Parking\n3) Exit to Main Menu\n4) Logout\nSelect option: ");
                if(newSc.hasNextInt()){
                    typeNum = newSc.nextInt(); // takes in the next input
                    if(typeNum == 1){
                        length = "long"; // if they take long
                    }else if(typeNum == 2){
                        length = "short"; // if they take short
                    }else if(typeNum == 3){
                        accessLevel(currentCustomer); // main menu
                        return;
                    }else if(typeNum == 4){
                        logout(); // logout
                        return;
                    }else{
                        System.out.println("Invalid option. Try again.");
                        System.out.println("=========================================================");
                        parkingOption(v,p);
                        return;
                        // if it is not a valid input
                    }
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    newSc.next();
                    parkingOption(v,p);
                    return;
                    // if it is not a valid input
                }
                currentCustomer.parkVehicle(type,length,v,allParkingLots.get(p.getName()),allStewards.get(allParkingLots.get(p.getName()).getSteward().getUsername()));
                save();
                accessLevel(currentCustomer);
                return;
                // parks and saves the vehicle in the spot
            }else if(typeNum == 2){
                type = "premium";
                System.out.print("1) Long Term Parking\n2) Short Term Parking\n3) Exit to Main Menu\n 4) Logout\nSelect option: ");
                if(newSc.hasNextInt()){
                    typeNum = newSc.nextInt();
                    if(typeNum == 1){
                        length = "long"; // if they choose this
                    }else if(typeNum == 2){
                        length = "short"; // if they choose this
                    }else if(typeNum == 3){
                        accessLevel(currentCustomer); // main menu
                        return;
                    }else if(typeNum == 4){
                        logout(); // log out
                        return;
                    }else{
                        System.out.println("Invalid option. Try again.");
                        System.out.println("=========================================================");
                        parkingOption(v,p);
                        return;
                        // if it is not a valid input
                    }
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    newSc.next();
                    parkingOption(v,p);
                    return;
                    // if it is not a valid input
                }
                currentCustomer.parkVehicle(type,length,v,allParkingLots.get(p.getName()),allStewards.get(allParkingLots.get(p.getName()).getSteward().getUsername()));
                save();
                accessLevel(currentCustomer);
                // this will park and save the car in the spot
                return;
            }else if(typeNum == 3){
                accessLevel(currentCustomer); // main menu
                return;
            }else if(typeNum == 4){
                logout(); // logout
                return;
            }else{
                System.out.println("Invalid option. Try again.");
                System.out.println("=========================================================");
                parkingOption(v,p);
                return;
                // if it is not a valid input
            }
        }else{
            System.out.println("Invalid option. Try again.");
            System.out.println("=========================================================");
            newSc.next();
            parkingOption(v,p);
            return;
            // if it is not a valid input
        }
    }

    private static void requestVehicle() throws IOException{
        if(currentCustomer.getParkedVehicles().size() > 0){
            for(int i = 0; i < currentCustomer.getParkedVehicles().size(); i++){
                Vehicle v = currentCustomer.getParkedVehicles().get(i);
                if(v.getSpotsTaken() == 1){
                    System.out.println(i+1 + ": " +v.getMake() + " " + v.getModel() + "; Parked in: " + v.getLot().getName() + " at spot: (" + v.getSpot().getLocation().getX() + "," + v.getSpot().getLocation().getY() + ")");
                }else{
                    System.out.println(i+1 + ": " +v.getMake() + " " + v.getModel() + "; Parked in: " + v.getLot().getName() + " at spots: ("
                            + (int)v.getTwoSpot().getAllLocation()[0].getX() + "," + (int)v.getTwoSpot().getAllLocation()[0].getY() + ") and (" + (int)v.getTwoSpot().getAllLocation()[0].getX() + "," + (int)v.getTwoSpot().getAllLocation()[1].getY() + ")");
                }
                // will print out the vehicles that they have parked to choose from
            }
            System.out.println(currentCustomer.getParkedVehicles().size()+1 + " Exit to main menu");
            System.out.println(currentCustomer.getParkedVehicles().size()+2 + " Logout");
            System.out.print("Enter number to request or exit/logout: ");
            // they options they have here
            int option = 0;
            if(newSc.hasNextInt()){
                option = newSc.nextInt();  // user input
                if(option <= currentCustomer.getParkedVehicles().size() && option > 0){
                    if(currentCustomer.getVehicles().get(option-1).getSpotsTaken() == 1){
                        if(currentCustomer.getVehicles().get(option-1).getSpot().getLongOrShortTerm().toLowerCase().equals("short")){
                            double timeTaken = (System.currentTimeMillis() - currentCustomer.getVehicles().get(option-1).getSpot().getTimeParked()) / 1000.0;
                            if(timeTaken > 30.0){
                                currentCustomer.getVehicles().get(option-1).getLot().getSteward().penalize(currentCustomer.getVehicles().get(option-1),(timeTaken - 30.0) * .1);
                            } // will penalize if they go over the time limit
                        }else{
                            double timeTaken = (System.currentTimeMillis() - currentCustomer.getVehicles().get(option-1).getSpot().getTimeParked()) / 1000.0;
                            if(timeTaken > 60.0){
                                currentCustomer.getVehicles().get(option-1).getLot().getSteward().penalize(currentCustomer.getVehicles().get(option-1),(timeTaken - 60.0) * .1);
                            } // if they go over the time limit
                        }
                    }else{
                        if(currentCustomer.getVehicles().get(option-1).getTwoSpot().getLongOrShortTerm().toLowerCase().equals("short")){
                            double timeTaken = (System.currentTimeMillis() - currentCustomer.getVehicles().get(option-1).getTwoSpot().getTimeParked()) / 1000.0;
                            if(timeTaken > 30.0){
                                currentCustomer.getVehicles().get(option-1).getLot().getSteward().penalize(currentCustomer.getVehicles().get(option-1),(timeTaken - 30.0) * .1);
                            } // if they go over the time limit
                        }else{
                            double timeTaken = (System.currentTimeMillis() - currentCustomer.getVehicles().get(option-1).getTwoSpot().getTimeParked()) / 1000.0;
                            if(timeTaken > 60.0){
                                currentCustomer.getVehicles().get(option-1).getLot().getSteward().penalize(currentCustomer.getVehicles().get(option-1),(timeTaken - 60.0) * .1);
                            } // if they go over the time limit
                        }
                    }
                    currentCustomer.requestVehicle(currentCustomer.getVehicles().get(option-1), allParkingLots.get(currentCustomer.getVehicles().get(option-1).getLot().getName()),
                            allStewards.get(allParkingLots.get(currentCustomer.getVehicles().get(option-1).getLot().getName()).getSteward().getUsername()));
                    // will give them there car
                }else if(option == currentCustomer.getParkedVehicles().size()+1){
                    accessLevel(currentCustomer); // main menu
                    return;
                }else if(option == currentCustomer.getParkedVehicles().size()+2){
                    logout(); // logout
                    return;
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    removeVehicle();
                    return;
                    // if it is not a valid input
                }
            }else{
                System.out.println("Invalid option. Try again.");
                System.out.println("=========================================================");
                newSc.next();
                removeVehicle();
                return;
                // if it is not a valid input
            }
            System.out.println("Vehicle has been requested, here is your bill.");
            System.out.println("=========================================================");
            save();
            paymentPortal(option-1,currentCustomer.getCoupon() > 0);
            return;
            // gives them there bill
        }else{
            System.out.println("No cars have been parked. Try again later.");
            accessLevel(currentCustomer);
            return;
            // no cars
        }
    }

    private static void paymentPortal(int v, boolean hasCoupon) throws IOException{
        Receipt receipt = new Receipt();
        if(hasCoupon){
            System.out.print("It seems like you have a coupon available.\nIt is worth " + currentCustomer.getCoupon() + "%\nType 'Redeem' to redeem the coupon or anything else to save it for later: ");
            String selection = "";
            // if they have a coupon
            if(newSc.hasNext()){
                selection = newSc.next(); // user input
                selection += newSc.nextLine();
                if(selection.toLowerCase().equals("redeem")){
                    System.out.println(receipt.getReceipt(currentCustomer.getVehicles().get(v),true));
                    currentCustomer.setCoupon(0); // to redeem the coupon
                }else{
                    System.out.println("Saving coupon for later.");
                    System.out.println(receipt.getReceipt(currentCustomer.getVehicles().get(v),false));
                } // they save it
            }
        }else{
            System.out.println(receipt.getReceipt(currentCustomer.getVehicles().get(v),false));
        }
        paymentPortalHelper(v,hasCoupon);
        return;
    }

    private static void paymentPortalHelper(int v, boolean hasCoupon) throws IOException {
        System.out.print("Select a payment option to pay $" + currentCustomer.getVehicles().get(v).totalCost() + "\n1) Credit Card\n2) Cash\nSelect option: ");
        int option = 0; // there prompted menu
        if(newSc.hasNextInt()){
            option = newSc.nextInt();
            if(option == 1){
                System.out.println("Enter card type, you can pay with 'AMEX', 'VISA', 'MSC' (MasterCard), and 'DISCOVER': ");
                String paymentType = ""; // there payment types
                if(newSc.hasNext()){
                    paymentType = newSc.next(); // user inputn
                    paymentType += newSc.nextLine();
                    paymentType = paymentType.toLowerCase(); // what they are paying with
                    if(paymentType.equals("amex") || paymentType.equals("visa") || paymentType.equals("msc") || paymentType.equals("discover")){
                        actualPayment(v, currentCustomer.getVehicles().get(v).totalCost());
                        return;
                    }else{
                        System.out.println("Invalid option. SELECT THE RIGHT OPTION!! YOU HAVE TO PAY. HERE ARE THE OPTIONS.");
                        paymentPortalHelper(v,hasCoupon);
                        return;
                        // if it is not a valid input
                    }
                }
            }else if(option == 2){
                actualPayment(v, currentCustomer.getVehicles().get(v).totalCost());
                return; // if there paying
            }else{
                System.out.println("Invalid option. YOU HAVE TO PAY. HERE ARE THE OPTIONS.");
                paymentPortalHelper(v,hasCoupon);
                return;
                // if it is not a valid input
            }
        }else{
            newSc.next();
            System.out.println("Invalid option. YOU HAVE TO PAY. HERE ARE THE OPTIONS.");
            paymentPortalHelper(v,hasCoupon);
            return;
            // if it is not a valid input
        }
    }

    private static void actualPayment(int v, double amountRemaining) throws IOException {
        System.out.print("Please pay $" + currentCustomer.getVehicles().get(v).totalCost() + ": ");
        double paymentAmount = 0.0; // amount to check
        if(newSc.hasNextDouble()){
            paymentAmount = newSc.nextDouble();
            if(paymentAmount == amountRemaining){
                System.out.println("Thanks for the payment :) Have a good day. 😊");
                currentCustomer.getVehicles().get(v).setPrices(new ArrayList<>());
                currentCustomer.getVehicles().get(v).setCostDescription(new ArrayList<>());
                accessLevel(currentCustomer);
                // if they pay the whole amount
                return;
            }else if(paymentAmount > amountRemaining){
                System.out.println("You overpaid. Here is the overpayment refund: " + ((double)paymentAmount - amountRemaining));
                currentCustomer.getVehicles().get(v).setPrices(new ArrayList<>());
                currentCustomer.getVehicles().get(v).setCostDescription(new ArrayList<>());
                accessLevel(currentCustomer);
                // if they over pay the bill
                return;
            }else{
                currentCustomer.getVehicles().get(v).setCost("Payment",-1*paymentAmount);
                System.out.println("You have $" + currentCustomer.getVehicles().get(v).totalCost() + " remaining.");
                actualPayment(v,currentCustomer.getVehicles().get(v).totalCost());
                return;
                // if they dont pay enough
            }
        }else{
            newSc.next();
            actualPayment(v, amountRemaining);
            return;
        }
    }

    // Admin's helper functions
    private static void hireSteward(Admin a) throws IOException {
        System.out.println("Welcome to the Steward Hiring Portal.\nAt any point in the process, you can type 'Exit' to go to the main menu.");
        System.out.println("*Note: This means a steward cannot have the username 'exit'.");
        System.out.print("Enter steward's username: ");
        String username = newSc.next();
        username += newSc.nextLine();
        if(username.toLowerCase().equals("exit")){
            accessLevel(currentAdmin); // the main menu
            return;
        }
        if(allUsers.containsKey(username)){
            System.out.println("This username already exists, try again.");
            boolean same = true; // if it exists
            while(same){
                System.out.print("Try a different username: ");
                username = newSc.next(); // user input
                if(username.toLowerCase().equals("exit")){
                    accessLevel(currentAdmin); // if they exit
                    return;
                }
                if(username.toLowerCase().equals("logout")){
                    logout(); // if they log out
                    return;
                }
                if(allUsers.containsKey(username)){
                    System.out.println("Please try again!"); // need to retry
                } else{
                    System.out.println("Unique username! Let's get your password.");
                    same = false;
                }
            }
        }else{
            System.out.println("Unique username! Let's get the steward a password.");
        }
        System.out.print("Enter a password: ");
        String password = newSc.next(); // user input for password
        password += newSc.nextLine();
        if(password.toLowerCase().equals("exit")){
            mainScreen(); // if they exit
            return;
        }
        System.out.print("Enter steward's first name: ");
        String firstName = newSc.next(); // what there name is
        firstName += newSc.nextLine();
        if(firstName.toLowerCase().equals("exit")){
            mainScreen(); // if they exit
            return;
        }
        System.out.print("Enter steward's last name: ");
        String lastName = newSc.next(); // what there last name is
        lastName += newSc.nextLine();
        if(lastName.toLowerCase().equals("exit")){
            mainScreen();
            return;
        }
        currentSteward = new Steward(username, password, firstName, lastName);
        allUsers.put(username,currentSteward); // buts them as a steward file
        allStewards.put(username,currentSteward);
        File f = new File("objectFiles/users.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allUsers);
        f = new File("objectFiles/stewards.txt");
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allStewards);
        oos.flush();
        oos.close();
        System.out.println("Steward added! Going back to main menu.");
        accessLevel(a);
        // saves to the files and returns to the main menu
        return;
    }

    private static void fireSteward() throws IOException{
        if(allStewards.size() > 0){
            System.out.println("=========================================================");
            int i = 0;
            for(Map.Entry<String,Steward> st : allStewards.entrySet()){
                if(st.getValue().getParkingLot() != null){
                    System.out.println("- " + st.getValue().getFullName() + "; in-charge of lot: " + st.getValue().getParkingLot().getName() + "; username: " + st.getKey());
                }else{
                    System.out.println("- " + st.getValue().getFullName() + "; in-charge of lot: none" + "; username: " + st.getKey());
                }// prints out the stewards and there lots
            }
            System.out.println("- 'Exit' to main menu");
            System.out.println("- 'Logout' to log out");
            System.out.print("Enter username to remove or exit/logout: ");
            // this is the menu they get
            String option = "";
            if(newSc.hasNext()){
                option = newSc.next(); // take suser inout
                option += newSc.nextLine();
                if(allStewards.containsKey(option)){
                    allStewards.remove(option); // removes the steward from the map
                    allUsers.remove(option);
                    File f = new File("objectFiles/users.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                    oos.writeObject(allUsers);
                    f = new File("objectFiles/stewards.txt");
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                    oos.writeObject(allStewards);
                    oos.flush();
                    oos.close();
                    // saves it to the files
                }else if(option.toLowerCase().equals("exit")){
                    accessLevel(currentAdmin); // main menu
                    return;
                }else if(option.toLowerCase().equals("logout")){
                    logout(); // logs out
                    return;
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    fireSteward();
                    return;
                    // if it is not a valid input
                }
            }else{
                System.out.println("Invalid option. Try again.");
                System.out.println("=========================================================");
                fireSteward();
                return;
                // if it is not a valid input
            }
            System.out.println("Steward has been removed. Taking you back to the main menu...");
            System.out.println("=========================================================");
            accessLevel(currentAdmin);
            return;
            // it was all good
        }else{
            System.out.println("There are no stewards.\nTaking back to main menu");
            System.out.println("=========================================================");
            accessLevel(currentAdmin);
            return;
            // you can non
        }

    }

    private static void blacklistCustomer() throws IOException{
        if(allCustomers.size() > 0){
            System.out.println("=========================================================");
            int i = 0;
            for(Map.Entry<String,Customer> st : allCustomers.entrySet()){
                System.out.println("- " + st.getValue().getFullName() + "; username: " + st.getKey());
            } // gets there information
            System.out.println("- 'Exit' to main menu");
            System.out.println("- 'Logout' to log out");
            System.out.print("Enter username to remove or exit/logout: ");
            // prompts them this menu
            String option = "";
            if(newSc.hasNext()){
                option = newSc.next(); // user input
                option += newSc.nextLine();
                if(allCustomers.containsKey(option)){
                    presentParkingLotsToBlackList(allCustomers.get(option)); // blacklists them
                    return;
                }else if(option.toLowerCase().equals("exit")){
                    accessLevel(currentAdmin); // main menu
                    return;
                }else if(option.toLowerCase().equals("logout")){
                    logout(); // log out
                    return;
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    blacklistCustomer();
                    return;
                    // if it is not a valid input
                }
            }else{
                System.out.println("Invalid option. Try again.");
                System.out.println("=========================================================");
                blacklistCustomer();
                return;
                // if it is not a valid input
            }
        }else{
            System.out.println("No customers.\nTaking back to main menu");
            System.out.println("=========================================================");
            accessLevel(currentAdmin);
            return;
            // no customer s
        }
    }

    private static void presentParkingLotsToBlackList(Customer customer) throws IOException{
        if(allParkingLots.size() > 0){
            System.out.println("Enter a lot name to blacklist customer from: ");
            for(Map.Entry<String,ParkingLot> e : allParkingLots.entrySet()){
                System.out.println("- " + e.getKey());
            }
            // takes the lot
            System.out.println("- Exit");
            System.out.println("- Logout");
            System.out.print("Enter lot name to remove or exit/logout: ");
            // prompts the menu
            String option = "";
            if(newSc.hasNext()){
                option = newSc.next(); // usr input
                option += newSc.nextLine();
                if(allParkingLots.containsKey(option)){
                    currentAdmin.blacklistCustomer(allCustomers.get(customer.getUsername()), allParkingLots.get(option));
                }else if(option.toLowerCase().equals("exit")){
                    accessLevel(currentAdmin);
                    return;
                }else if(option.toLowerCase().equals("logout")){
                    logout();
                    return;
                }else{
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    presentParkingLotsToBlackList(customer);
                    return;
                    // if it is not a valid input
                }
            }else{
                System.out.println("Invalid option. Try again.");
                System.out.println("=========================================================");
                presentParkingLotsToBlackList(customer);;
                return;
                // if it is not a valid input
            }
            System.out.println("Customer has been blacklists. Taking you back to the main menu...");
            System.out.println("=========================================================");
            save();
            accessLevel(currentAdmin);
            return;
            // it was correct
        }else{
            System.out.println("No lots exist, unable to blacklist customer. Taking back to main menu");
            accessLevel(currentAdmin);
            return;
            // no lots
        }
    }

    private static void addLot() throws IOException{
        System.out.println("Welcome to the Parking Lot Creation Portal.");
        System.out.println("At any time in the program, enter 'Exit' to exit to main menu or 'Logout' to logout.");
        System.out.print("Enter the number of rows you want the lot to have: ");
        int row = 0;
        String other = "";
        if(newSc.hasNextInt()){
            row = newSc.nextInt();
            if(row <= 0){
                System.out.println("Row cannot be less than 1. Try again.");
                boolean rowNum = true;
                while(rowNum){
                    System.out.print("Enter total numbers of row: ");
                    if(newSc.hasNextInt()){
                        row = newSc.nextInt();
                        if(row <= 0){
                            rowNum = true;
                            System.out.println("Row cannot be less than 1. Try again.");
                        }else{
                            rowNum = false;
                        }
                    }else{
                        System.out.println("Invalid entry. Let's restart.");
                        addLot();
                        rowNum = false;
                        return;
                    }
                }
            }
        }else if(newSc.hasNext()) {
            other = newSc.next();
            if (other.toLowerCase().equals("exit")) {
                System.out.println("Taking back to main menu");
                accessLevel(currentAdmin);
                return;
            } else if (other.toLowerCase().equals("logout")) {
                System.out.println("Logging out");
                logout();
                return;
            } else {
                System.out.println("That is not a valid option. Lets remake your vehicle.");
                addLot();
                return;
            }
        }else{
            System.out.println("Invalid entry. Let's restart.");
            addLot();
            return;
        }
        System.out.print("Now enter total number of column: ");
        int column = 0;
        if(newSc.hasNextInt()){
            column = newSc.nextInt();
            if(column <= 0){
                System.out.println("Column cannot be less than 1. Try again.");
                boolean columnNum = true;
                while(columnNum){
                    System.out.print("Enter total numbers of column: ");
                    if(newSc.hasNextInt()){
                        column = newSc.nextInt();
                        if(column <= 0){
                            columnNum = true;
                            System.out.println("Column cannot be less than 1. Try again.");
                        }else{
                            columnNum = false;
                        }
                    }else{
                        System.out.println("Invalid entry. Let's restart.");
                        addLot();
                        columnNum = false;
                        return;
                    }
                }
            }
        }else if(newSc.hasNext()) {
            other = newSc.next();
            if (other.toLowerCase().equals("exit")) {
                System.out.println("Taking back to main menu");
                accessLevel(currentCustomer);
                return;
            } else if (other.toLowerCase().equals("logout")) {
                System.out.println("Logging out");
                logout();
                return;
            } else {
                System.out.println("That is not a valid option. Lets remake your vehicle.");
                addLot();
                return;
            }
        }else{
            System.out.println("Invalid entry. Let's restart.");
            addLot();
            return;
        }
        int name =  allParkingLots.size()+1;
        currentAdmin.addParkingLot(allParkingLots,new ParkingLot("Lot " + name,row,column));
        File f = new File("objectFiles/parkinglots.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(allParkingLots);
        oos.flush();
        oos.close();
        System.out.println("Parking lot added. To assign stewards, please select option 4 from the main menu.");
        save();
        accessLevel(currentAdmin);
        return;
    }

    private static void removeLot() throws IOException{
        ArrayList<String> empty = new ArrayList<>();
        for(Map.Entry<String,ParkingLot> s : allParkingLots.entrySet()){
            if(s.getValue().isEmpty()){
                empty.add(s.getKey());
                System.out.println("- " + s.getKey());
            }
        }
        if(empty.size() > 0){
            System.out.println("- Exit");
            System.out.println("- Logout");
            System.out.print("Enter lot name or exit/logout: ");
            String option = "";
            if(newSc.hasNext()){
                option = newSc.next();
                option += newSc.nextLine();
                if(allParkingLots.containsKey(option) && empty.contains(option)){
                    currentAdmin.removeParkingLot(allParkingLots,allParkingLots.get(option));
                    File f = new File("objectFiles/parkinglots.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                    oos.writeObject(allParkingLots);
                    oos.flush();
                    oos.close();
                }else if(option.toLowerCase().equals("exit")){
                    accessLevel(currentAdmin);
                    return;
                }else if(option.toLowerCase().equals("logout")){
                    logout();
                    return;
                }else{
                    System.out.println("Invalid option. Try again");
                    removeLot();
                    return;
                }
                System.out.println("Lot removed. Heading back to main menu.");
                save();
                accessLevel(currentAdmin);
                return;
            }else{
                System.out.println("Invalid option. Try again");
                removeLot();
                return;
            }
        }else{
            System.out.println("No lots are empty, you can only remove empty lots. Try again later.");
            accessLevel(currentAdmin);
            return;
        }
        // this will allow the admin to create a new lot if they want to. They can create the size of it. and it
        // will prompt them if they are doing it the wrong way
    }

    private static void assignSteward() throws IOException {
        if(allStewards.size() > 0){
            ArrayList<String> unassigned = new ArrayList<>();
            for(Map.Entry<String,Steward> s : allStewards.entrySet()){
                if(s.getValue().getParkingLot() == null){
                    unassigned.add(s.getKey());
                    System.out.println("- " + s.getKey());
                }
            }
            if(unassigned.size() > 0){
                System.out.println("- Exit");
                System.out.println("- Logout");
                System.out.print("Enter lot name or exit/logout: ");
                String option = "";
                if(newSc.hasNext()){
                    option = newSc.next();
                    option += newSc.nextLine();
                    if(allStewards.containsKey(option) && unassigned.contains(option)){
                        assignStewardHelper(allStewards.get(option));
                        return;
                    }else if(option.toLowerCase().equals("exit")){
                        accessLevel(currentAdmin);
                        return;
                    }else if(option.toLowerCase().equals("logout")){
                        logout();
                        return;
                    }else{
                        System.out.println("Invalid option. Try again");
                        assignSteward();
                        return;
                    }
                }else{
                    System.out.println("Invalid option. Try again");
                    assignSteward();
                    return;
                }
            }else{
                System.out.println("All stewards are already assigned, please hire a new steward and try again.");
                accessLevel(currentAdmin);
                return;
            }
        }else{
            System.out.println("No Stewards exist. Try again later.");
            accessLevel(currentAdmin);
            return;
        }
        // this will assign a steward to any lot that you want to put them in. It will prompt you
        // if you are doing something wrong or if no stewards exists or if they all have a lot assigned
    }

    private static void assignStewardHelper(Steward s) throws IOException {
        if(allParkingLots.size() > 0){
            ArrayList<String> empty = new ArrayList<>();
            for(Map.Entry<String,ParkingLot> p : allParkingLots.entrySet()){
                if(p.getValue().getSteward() == null){
                    empty.add(p.getKey());
                    System.out.println("- " + p.getKey());
                } // this will happen if it is empty
            }
            if(empty.size() > 0){
                System.out.println("- Exit");
                System.out.println("- Logout");
                System.out.print("Enter lot name or exit/logout: ");
                String option = "";
                // this will prompt the menu
                if(newSc.hasNext()){
                    option = newSc.next();
                    option += newSc.nextLine();
                    if(allParkingLots.containsKey(option) && empty.contains(option)){
                        currentAdmin.addSteward(s,allParkingLots.get(option));
                        File f = new File("objectFiles/parkinglots.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allParkingLots);
                        f = new File("objectFiles/stewards.txt");
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allStewards);
                        f = new File("objectFiles/users.txt");
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allStewards);
                        oos.flush();
                        oos.close();
                        // this will save the files after everyhting

                    }else if(option.toLowerCase().equals("exit")){
                        accessLevel(currentAdmin); // to exit
                        return;
                    }else if(option.toLowerCase().equals("logout")){
                        logout(); // log out
                        return;
                    }else{
                        System.out.println("Invalid option. Try again");
                        assignStewardHelper(s);
                        return;
                        // invalid option
                    }
                    System.out.println("Steward assigned. Heading back to main menu.");
                    accessLevel(currentAdmin);
                    return;
                    // if it corrected
                }else{
                    System.out.println("Invalid option. Try again");
                    assignStewardHelper(s);
                    return;
                    // invalid option
                }

            }else{
                System.out.println("All lots are taken, add a new lot and try again later.");
                accessLevel(currentAdmin);
                return;
                // invalid option
            }
        }else{
            System.out.println("No lots exist, add a new lot and try again later.");
            accessLevel(currentAdmin);
            return;
            // invalid option
        }
    }

    private static void removeSteward() throws IOException{
        if(allStewards.size() > 0){
            ArrayList<String> assigned = new ArrayList<>();
            for(Map.Entry<String,Steward> s : allStewards.entrySet()){
                if(s.getValue().getParkingLot() != null){
                    assigned.add(s.getKey());
                    System.out.println("- " + s.getKey());
                }
            }
            if(assigned.size() > 0){
                System.out.println("- Exit");
                System.out.println("- Logout");
                System.out.print("Enter lot name or exit/logout: ");
                String option = "";
                if(newSc.hasNext()){
                    option = newSc.next();
                    option += newSc.nextLine();
                    if(allStewards.containsKey(option) && assigned.contains(option) && allParkingLots.get(allStewards.get(option).getParkingLot().getName()).isEmpty()){
                        currentAdmin.removeSteward(allStewards.get(option).getParkingLot(),allParkingLots.get(allStewards.get(option).getParkingLot().getName()).getSteward());
                        File f = new File("objectFiles/parkinglots.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allParkingLots);
                        f = new File("objectFiles/stewards.txt");
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allStewards);
                        f = new File("objectFiles/users.txt");
                        oos = new ObjectOutputStream(new FileOutputStream(f));
                        oos.writeObject(allStewards);
                        oos.flush();
                        oos.close();
                    }else if(allStewards.containsKey(option) && assigned.contains(option) && !allParkingLots.get(allStewards.get(option).getParkingLot().getName()).isEmpty()){
                        System.out.println("Steward cannot be removed as the lot is not empty. Going back to main menu");
                        accessLevel(currentAdmin);
                        return;
                    }else if(option.toLowerCase().equals("exit")){
                        accessLevel(currentAdmin);
                        return;
                    }else if(option.toLowerCase().equals("logout")){
                        logout();
                        return;
                    }else{
                        System.out.println("Invalid option. Try again");
                        removeSteward();
                        return;
                    }
                    System.out.println("Steward removed from lot. Going back to main menu.");
                    accessLevel(currentAdmin);
                    return;
                }else{
                    System.out.println("Invalid option. Try again");
                    removeSteward();
                    return;
                }
            }else{
                System.out.println("No stewards have been assigned yet, try again later.");
                accessLevel(currentAdmin);
                return;
            }
        }else{
            System.out.println("No Stewards exist. Try again later.");
            accessLevel(currentAdmin);
            return;
        }
        // this method will remove the steward all together from the map. They will no other
        // exists in our program.
    }

    private static void adjustCapacity(String choice) throws IOException {
        for(Map.Entry<String,ParkingLot> p : allParkingLots.entrySet()) {
            System.out.println("- " + p.getKey());
        }
        System.out.println("- Exit to Main Menu");
        System.out.println("- Logout.");
        System.out.print("Enter name of lot or exit/logout: ");
        String option = "";
        if(newSc.hasNext()) {
            option = newSc.next();
            option += newSc.nextLine();
            if (allParkingLots.containsKey(option)) {
                if(allParkingLots.get(option).getSteward() != null){
                    currentAdmin.adjustCapacity(choice,allParkingLots.get(option),allParkingLots.get(option).getSteward());
                }else{
                    currentAdmin.adjustCapacity(choice,allParkingLots.get(option),null);
                }
                File f = new File("objectFiles/parkinglots.txt");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(allParkingLots);
                oos.flush();
                oos.close();
            } else if (option.toLowerCase().equals("exit")) {
                accessLevel(currentAdmin);
                return;
            } else if (option.toLowerCase().equals("logout")) {
                logout();
                return;
            } else {
                System.out.println("Invalid option. Try again");
                adjustCapacity(choice);
                return;
            }
            accessLevel(currentAdmin);
            return;
        }else{
            System.out.println("Invalid option. Try again");
            adjustCapacity(choice);
            return;
        }
        // this will change the amount of cars the lot can hold. It will be able to
        // increase and decrease the capacity. it will prompt the user if they are doing it wrong
    }

    // Steward's helper functions
    private static void showParkedCars(Steward s) throws IOException {
        if(s.getParkingLot() != null){
            if (!s.getParkingLot().isEmpty()) {
                ArrayList<Vehicle> parkedCars = new ArrayList<>();
                for (int i = 0; i < s.getParkingLot().getTotalRow(); i++) {
                    for (int j = 0; j < s.getParkingLot().getTotalColumn(); j++) {
                        if (s.getParkingLot().getOccupany(i,j)) {
                            parkedCars.add(s.getParkingLot().getSpotInfo(i, j).getVehicle());
                        }
                    }
                }
                for (int i = 0; i < parkedCars.size(); i++) {
                    System.out.println(i + 1 + ": " + parkedCars.get(i).getMake() + " " + parkedCars.get(i).getModel());
                }
                System.out.println(parkedCars.size() + 1 + " Exit to main menu");
                System.out.println(parkedCars.size() + 2 + " Logout");
                System.out.print("Enter number to inspect a vehicle or exit/logout: ");
                int option = 0;
                if (newSc.hasNextInt()) {
                    option = newSc.nextInt();
                    if (option <= parkedCars.size() && option > 0) {
                        System.out.println(parkedCars.get(option-1).inspectVehicle());
                    } else if (option == parkedCars.size() + 1) {
                        accessLevel(s);
                        return;
                    } else if (option == parkedCars.size() + 2) {
                        logout();
                        return;
                    } else {
                        System.out.println("Invalid option. Try again.");
                        System.out.println("=========================================================");
                        showParkedCars(s);
                        return;
                    }
                } else {
                    System.out.println("Invalid option. Try again.");
                    System.out.println("=========================================================");
                    newSc.next();
                    showParkedCars(s);
                    return;
                }
                System.out.print("Type anything once you are ready to go back to the main menu or type 'exit' to go to main menu or 'Logout' to log out: ");
                String back = newSc.next();
                if(back.toLowerCase().equals("logout")){
                    logout();
                    return;
                }
                if(back.toLowerCase().equals("exit")){
                    accessLevel(currentSteward);
                    return;
                }
                System.out.println("=========================================================");
                accessLevel(s);
                return;
            } else {
                System.out.println("No vehicles are parked in your lot.\nTaking back to main menu");
                System.out.println("=========================================================");
                accessLevel(s);
                return;
            }
        }else{
            System.out.println("You have not been assigned to any lot. Try again later.");
            accessLevel(currentSteward);
            return;
        }
        // this will inspect the vehicles if they are a steward. They will be able to see all the vehicles that
        // are in the parking lot and all there information
    }

    private static void assignGift(Steward s) throws IOException {
        if(s.getParkingLot() != null){
            if (!s.getParkingLot().isEmpty()) {
                ArrayList<Customer> parkedCustomers = new ArrayList<>();
                for (int i = 0; i < s.getParkingLot().getTotalRow(); i++) {
                    for (int j = 0; j < allParkingLots.get("Lot 1").getTotalColumn(); j++) {
                        if (s.getParkingLot().getOccupany(i, j)) {
                            parkedCustomers.add(s.getParkingLot().getSpotInfo(i, j).getVehicle().getOwner());
                        }
                    }
                }
                Random r = new Random();
                s.offerRandomCoupon(allCustomers.get(parkedCustomers.get(r.nextInt(parkedCustomers.size())).getUsername()));
                System.out.println("Coupon offered.");
                System.out.print("1) Gift More Coupons\n2) Exit\n3) Logout\nSelect option: ");
                if(newSc.hasNextInt()){
                    int option = newSc.nextInt();
                    if(option == 1){
                        assignGift(s);
                        System.out.println("Coupon assigned. Select an option below.");
                        return;
                    }else if(option == 2){
                        accessLevel(s);
                        return;
                    }else if(option == 3){
                        logout();
                        return;
                    }else{
                        System.out.println("Invalid option. Taking back to main menu");
                        assignGift(s);
                        return;
                    }
                }else{
                    System.out.println("Invalid option. Taking back to main menu");
                    newSc.next();
                    assignGift(s);
                    return;
                }
            } else {
                System.out.println("No vehicles are parked in your lot so cannot give coupons.\nTaking back to main menu");
                System.out.println("=========================================================");
                accessLevel(s);
                return;
            }
        }else{
            System.out.println("You have not been assigned to any lot. Try again later.");
            accessLevel(currentSteward);
            return;
        }
        // this will assign a random customer a coupn that they can apply to there bill if they want to
        // it is not a set amount it varies
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //function will be used to read in TXT files, which store info on customers, parkinglots, etc
        // will be also used to write to TXT files once program is closed
        try{
            File f = new File("objectFiles/users.txt");
            ObjectInputStream ool = new ObjectInputStream(new FileInputStream(f));
            allUsers = (Map<String, User>) ool.readObject();
            ool.close();
            for(User u : allUsers.values()){
                if(u instanceof Customer){
                    allCustomers.put(u.getUsername(),(Customer)u);
                } else if(u instanceof Admin){
                    allAdmins.put(u.getUsername(),(Admin)u);
                } else {
                    allStewards.put(u.getUsername(),(Steward)u);
                }
            }
        }catch (EOFException e){

        }
        try{
            File f =new File("objectFiles/parkinglots.txt");;
            ObjectInputStream ool = new ObjectInputStream(new FileInputStream(f));
            allParkingLots = (Map<String, ParkingLot>) ool.readObject();
            ool.close();
        }catch (EOFException e){

        }
        System.out.println("Hello! Welcome to the Parking Lot Manager.");
        mainScreen();
        newSc.close();
        System.out.println(allParkingLots.get("Lot 1"));
        System.out.println(allParkingLots.get("Lot 2"));
        System.out.println(allParkingLots.get("Lot 3"));
    }
}