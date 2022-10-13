package com.assignment.voter;

/**
 * ----------------------------------
 * Assignment 1
 * ©Lakshya Kalia
 * Written by: Lakshya Kalia 40220721
 * @author Lakshya Kalia
 * @version 1.0
 * ----------------------------------
 */

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static int maxVoters = 0;
    private static final String password = "password";
    /** Description of voterCount
     *
     * Keeps track of the number of voters
     * in the voterBase array
     */
    private static int voterCount = 0;
    private static Voter[] voterBase;

    /** Description for displayWelcomeMessage()
     *
     * For displaying the welcome message
     */
    public static void displayWelcomeMessage(){
        System.out.println("\n" + "Welcome to Parti Québécois!" +
                "\n" + "Please enter the maximum number of Voters in your neighbourhood >");
    }

    /** Description for setMaxVoters(String maxNumOfVoters)
     *
     * @param maxNumOfVoters of type String
     */
    public static void setMaxVoters(String maxNumOfVoters){
        maxVoters = Integer.parseInt(maxNumOfVoters);
    }

    /** Description for displayMainMenu()
     *
     * For displaying the main menu
     */
    public static void displayMainMenu(){
        System.out.println("What do you want to do?" + "\n" +
                "\t" + "1. Enter new voters (password required)" + "\n" +
                "\t" + "2. Change information of a voter (password required)" + "\n" +
                "\t" + "3. Display all voters by a specific voterPcode" + "\n" +
                "\t" + "4. Display all voters under a certain age." + "\n" +
                "\t" + "5. Quit" + "\n" +
                "Please enter your choice >");
    }

    /** Description for mainMenuInput()
     *
     * Takes user input and displays the main menu
     * until the user selects option 5 (to quit)
     * or types the wrong password for option 1 for
     * a total of 12 times and prints the 'suspicious
     * activity' message
     */
    public static void mainMenuInput(){
        int goBack = -999;
        int addVoterSubmenuCount = 0;
        int attempts = 3;

        while(addVoterSubmenuCount != 4) {
            displayMainMenu();
            int mainMenuOption = Integer.parseInt(sc.nextLine());
            switch (mainMenuOption){
                case 1: goBack = addNewVotersCheck(attempts);
                    break;
                case 2:
                    goBack = -999;
                    addVoterSubmenuCount = 0;
                    updateVoterCheck(attempts);
                    break;
                case 3:
                    goBack = -999;
                    addVoterSubmenuCount = 0;
                    System.out.println("Please enter a postal code to display all voters residing there >");
                    String pcode = sc.nextLine();
                    findVotersBy(pcode.toCharArray());
                    break;
                case 4:
                    goBack = -999;
                    addVoterSubmenuCount = 0;
                    System.out.println("Please enter an age to display all voters younger than that >");
                    byte age = Byte.parseByte(sc.nextLine());
                    findYoungerThan(age);
                    break;
                case 5: closeApp();
//                    break;
                default:
                    break;
            }
            if(goBack == 0){
                /**
                 * 3 failed attempts from users are fulfilled
                 * then increase the option 1 submenu count
                 */
                addVoterSubmenuCount++;
            }

        }
        if(addVoterSubmenuCount == 4){
            /**
             * If the total number of attempts from the user
             * 3 X 4 = 12 are fulfilled for wrong password
             * then show message and terminate the application
             */
            System.out.println("Program detected suspicious activities and will terminate immediately!");
            System.exit(0);
        }

    }

    /** Description for closeApp()
     *
     * Terminates the application if option 5 is selection
     */
    public static void closeApp(){
        System.out.println("Application is going to terminate...");
        System.exit(0);
    }

    /** Description for addNewVotersCheck(int attempts)
     *
     * Checks for the wrong password
     * as a recursive function
     * till 3 attempts and return 0
     * @param attempts
     * @return 0 if 3 failed attempts
     */
    public static int addNewVotersCheck(int attempts){
        /**
         * check for 0 attempts remaining
         * in case of wrong password
         * (end condition for recursive
         * function)
         */
        if(attempts == 0){

            return 0;
        }
        System.out.println("Please enter your password >");
        String pass = sc.nextLine();
        /**
         * Correct password
         */
        if(pass.equals(password)){
            System.out.println("How many voters you want to enter? >");
            int numOfVoterToEnter = Integer.parseInt(sc.nextLine());
            if(voterBase.length - voterCount >= numOfVoterToEnter && numOfVoterToEnter!=0){
                addVoters(numOfVoterToEnter);
            }
            else{
                System.out.println("You can add " + (voterBase.length - voterCount) + " number of voter(s).");
            }
        }
        /**
         * Incorrect password
         */
        else{
            attempts--;
            /**
             * Show number of attempts remaining
             */
            System.out.println("Password Incorrect. (Attempts remaining : " + attempts + ")");
            /**
             * Recursive approach
             */
            attempts = addNewVotersCheck(attempts);
        }
        return attempts;
    }

    /** Description for addVoters(int numOfVoterToEnter)
     *
     * Takes the voter details and adds
     * it to the voterBase array
     * @param numOfVoterToEnter Integer
     */
    public static void addVoters(int numOfVoterToEnter){
        for(int i = voterCount ; i < (numOfVoterToEnter + voterCount) ; i++){
            System.out.println("Enter Voter Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Voter Age: ");
            byte age = Byte.parseByte(sc.nextLine());
            System.out.println("Enter Voter Email: ");
            String email = sc.nextLine();
            System.out.println("Enter Voter Postal Code: ");
            char[] pcode = sc.nextLine().toCharArray();

            voterBase[i] = new Voter(name,age,email,pcode);


        }
        System.out.println(numOfVoterToEnter + " Voter(s) added.");
        voterCount+= numOfVoterToEnter;
    }

    /** Description for updateVoterCheck(int attempts)
     *
     * Checks for the wrong password
     * as a recursive function
     * till 3 attempts and return 0
     * @param attempts
     * @return 0 if 3 failed attempts
     */
    public static int updateVoterCheck(int attempts){
        if(attempts == 0){
            return 0;
        }
        System.out.println("Please enter your password >");
        String pass = sc.nextLine();
        /**
         * Correct password
         */
        if(pass.equals(password)){
            updateVoter();
        }
        /**
         * Incorrect password
         */
        else{
            attempts--;
            /**
             * Show number of attempts remaining
             */
            System.out.println("Password Incorrect. (Attempts remaining : " + attempts + ")");
            /**
             * Recursive approach
             */
            attempts = updateVoterCheck(attempts);
        }
        return attempts;
    }

    /** Description for updateVoter()
     *
     * Updates the voter details (except
     * voterID) by taking voterID input
     * from user
     *
     * If voterID is not found in voterBase
     * array, user has the option to either
     * enter another voterID or go back to
     * the previous menu
     */
    public static void updateVoter(){
        boolean repeat = true;
        while(repeat){
            System.out.println("Please enter VoterID of voter to update >");
            int voterID = Integer.parseInt(sc.nextLine());
            int index = -1;
            for(int i = 0 ; i < voterCount ; i++){
                if(voterID == voterBase[i].getVoterID()){
                    index = i;
                    break;
                }
            }
            if(index == -1){
                System.out.println("No voter found. Do you want to re-enter another voter? (y/n)");
                if(sc.nextLine().equalsIgnoreCase("n")){
                    repeat = false;
                }
            }
            else{
                repeat = false;
                voterInfo(index);
                updateMenu();

                int updateMenuOption = Integer.parseInt(sc.nextLine());
                while (updateMenuOption!= 5){
                    switch (updateMenuOption){
                        case 1:
                            System.out.println("Enter Voter Name: ");
                            String name = sc.nextLine();
                            voterBase[index].setVoterName(name);
                            voterInfo(index);
                            break;
                        case 2:
                            System.out.println("Enter Voter Age: ");
                            byte age = Byte.parseByte(sc.nextLine());
                            voterBase[index].setVoterAge(age);
                            voterInfo(index);
                            break;
                        case 3:
                            System.out.println("Enter Voter Email: ");
                            String email = sc.nextLine();
                            voterBase[index].setVoterEmail(email);
                            voterInfo(index);
                            break;
                        case 4:
                            System.out.println("Enter Voter Postal Code: ");
                            char[] pcode = sc.nextLine().toCharArray();
                            voterBase[index].setVoterPcode(pcode);
                            voterInfo(index);
                            break;
                        default:
                            break;
                    }
                    updateMenu();
                    updateMenuOption = Integer.parseInt(sc.nextLine());
                }

            }
        }
    }

    /** Description for voterInfo(int index)
     *
     * Response structure for individual
     * voters in the voterBase array
     * @param index integer
     */
    public static void voterInfo(int index){
        System.out.println("Voter: #" + index + "\n" +
                "ID: " + voterBase[index].getVoterID() + "\n" +
                "Name: " + voterBase[index].getVoterName() + "\n" +
                "Age: " + voterBase[index].getVoterAge() + "\n" +
                "Email: " + voterBase[index].getVoterEmail() + "\n" +
                "PCode: " + voterBase[index].getVoterPcode());
    }

    /** Description for updateMenu()
     *
     * Structure for the update menu
     */
    public static void updateMenu(){
        System.out.println("What information would you like to change?" + "\n" +
                "\t" + "1. Name" + "\n" +
                "\t" + "2. Age" + "\n" +
                "\t" + "3. Email" + "\n" +
                "\t" + "4. PCode" + "\n" +
                "\t" + "5. Back to main menu" + "\n" +
                "Enter your choice >");
    }

    /** Description for findYoungerThan(byte age)
     *
     * Finds all the voters whose ages
     * are less than the age entered
     * by the user
     * @param age byte type
     */
    public static void findYoungerThan(byte age){
        boolean flag = false;
        for(int i = 0 ; i < voterCount ; i++){
            if(voterBase[i].getVoterAge() < age){
                flag = true;
                System.out.println(voterBase[i]);
                System.out.println("");
            }
        }
        if(!flag){
            System.out.println("No voters found!");
        }
    }

    /** Description for findVotersBy(String pcode)
     *
     * Finds all the voters who live in that
     * postal code which is entered by the
     * user
     * @param pcode of type char array
     */
    public static void findVotersBy(char[] pcode){
        boolean flag = false;
        for(int i = 0 ; i < voterCount ; i++){
            if(voterBase[i].getVoterPcode().equalsIgnoreCase(new String(pcode))){
                flag = true;
                System.out.println(voterBase[i]);
                System.out.println("");
            }
        }
        if(!flag){
            System.out.println("No voters found!");
        }
    }


    public static void main(String[] args) {

        displayWelcomeMessage();
        setMaxVoters(sc.nextLine());
        voterBase = new Voter[maxVoters];
        mainMenuInput();

    }
}