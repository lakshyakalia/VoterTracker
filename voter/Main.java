package com.assignment.voter;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static int maxVoters = 0;
    private static final String password = "password";

    private static int voterCount = 0;
    private static Voter[] voterBase;
    public static void displayWelcomeMessage(){
        System.out.println(" ");
        System.out.println("Welcome to Parti Québécois!");
        System.out.println("Please enter the maximum number of Voters in your neighbourhood >");
    }

    public static void setMaxVoters(String maxNumOfVoters){
        maxVoters = Integer.parseInt(maxNumOfVoters);
    }

    public static void displayMainMenu(){
        System.out.println("What do you want to do?");
        System.out.println("\t" + "1. Enter new voters (password required)");
        System.out.println("\t" + "2. Change information of a voter (password required)");
        System.out.println("\t" + "3. Display all voters by a specific voterPcode");
        System.out.println("\t" + "4. Display all voters under a certain age.");
        System.out.println("\t" + "5. Quit");
        System.out.println("Please enter your choice >");

    }

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
                    findVotersBy(pcode);
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
                addVoterSubmenuCount++;
            }

        }
        if(addVoterSubmenuCount == 4){
            System.out.println("Program detected suspicious activities and will terminate immediately!");
            System.exit(0);
        }

    }

    public static void closeApp(){
        System.out.println("Application is going to terminate...");
        System.exit(0);
    }

    public static int addNewVotersCheck(int attempts){
        if(attempts == 0){
            return 0;
        }
        System.out.println("Please enter your password >");
        String pass = sc.nextLine();
        if(pass.equals(password)){
            System.out.println("How many voters you want to enter? >");
            int numOfVoterToEnter = Integer.parseInt(sc.nextLine());
            if(voterBase.length - voterCount >= numOfVoterToEnter){
                addVoters(numOfVoterToEnter);
            }
            else{
                System.out.println("You can add " + (voterBase.length - voterCount) + " number of voter(s).");
            }
        }
        else{
            attempts--;
            System.out.println("Password Incorrect. (Attempts remaining : " + attempts + ")");
            attempts = addNewVotersCheck(attempts);
        }
        return attempts;
    }

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

    public static int updateVoterCheck(int attempts){
        if(attempts == 0){
            return 0;
        }
        System.out.println("Please enter your password >");
        String pass = sc.nextLine();
        if(pass.equals(password)){
            updateVoter();
        }
        else{
            attempts--;
            System.out.println("Password Incorrect. (Attempts remaining : " + attempts + ")");
            attempts = updateVoterCheck(attempts);
        }
        return attempts;
    }
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

    public static void voterInfo(int index){
        System.out.println("Voter: #" + index);
        System.out.println("ID: " + voterBase[index].getVoterID());
        System.out.println("Name: " + voterBase[index].getVoterName());
        System.out.println("Age: " + voterBase[index].getVoterAge());
        System.out.println("Email: " + voterBase[index].getVoterEmail());
        System.out.println("PCode: " + voterBase[index].getVoterPcode());
    }

    public static void updateMenu(){
        System.out.println("What information would you like to change?");
        System.out.println("\t" + "1. Name");
        System.out.println("\t" + "2. Age");
        System.out.println("\t" + "3. Email");
        System.out.println("\t" + "4. PCode");
        System.out.println("\t" + "5. Back to main menu");
        System.out.println("Enter your choice >");
    }

    public static void findYoungerThan(byte age){
        boolean flag = false;
        for(int i = 0 ; i < voterCount ; i++){
            if(voterBase[i].getVoterAge() < age){
                flag = true;
                System.out.println(voterBase[i]);
            }
        }
        if(!flag){
            System.out.println("No voters found!");
        }
    }

    public static void findVotersBy(String pcode){
        boolean flag = false;
        for(int i = 0 ; i < voterCount ; i++){
            if(voterBase[i].getVoterPcode().equalsIgnoreCase(pcode)){
                flag = true;
                System.out.println(voterBase[i]);
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