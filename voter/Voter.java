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

public class Voter {
    //	Data members
    private final long voterID;
    private String voterName;
    private byte voterAge;
    private String voterEmail;
    private  char[] voterPcode;

    /** Description of countInstance
     * Counter to keep track of instances of Voter class
     */
    private static int countInstance = 0;

    /** Description of Voter()
     * Default Constructor initializing values
     */
    public Voter(){

        this.voterName = "John Doe";
        this.voterAge = 1;
        this.voterEmail = "abc@gmail.com";
        this.voterPcode = "H3N1V3".toCharArray();

        countInstance++;
        this.voterID = 0;
    }

    /** Description of Voter(String name, byte age, String email, char[] pcode)
     * Parameterized Constructor setting values
     * @param name voter name (String)
     * @param age voter age (byte)
     * @param email voter email (String)
     * @param pcode voter postal code (char array)
     */
    public Voter(String name, byte age, String email, char[] pcode) {
        this.voterName = name;
        this.voterAge = age;
        this.voterEmail = email;
        this.voterPcode = pcode;

        countInstance++;
        this.voterID = countInstance;
    }

    /**
     * Accessors and Mutators (getters and setters for all
     * the data members of the voter class)
     */
    public long getVoterID(){
        return this.voterID;
    }

    public void setVoterName(String name){
        this.voterName = name;
    }
    public String getVoterName() {
        return this.voterName;
    }

    public void setVoterAge(byte age) {
        this.voterAge = age;
    }
    public byte getVoterAge() {
        return this.voterAge;
    }

    public void setVoterEmail(String email) {
        this.voterEmail = email;
    }
    public String getVoterEmail() {
        return this.voterEmail;
    }

    public void setVoterPcode(char[] pcode) {
        this.voterPcode = pcode;
    }
    public String getVoterPcode() {
        return new String(this.voterPcode);
    }

    /** Description of toString()
     * Overrides the default toString function
     * @return voter's name, age, email and postal code
     * instead of the address of the voter object
     */
    @Override
    public String toString() {
        return "Voter Name: " + voterName + "\n" + "Voter Age: " + voterAge + "\n" +
                "Voter Email: " + voterEmail + "\n" + "Voter PCode: " + new String(voterPcode);
    }

    /** Description of findNumberOfCreatedVoter()
     * @return countInstance variable which
     * stores the instances of voter objects created
     */
    public static int findNumberOfCreatedVoter() {
        return countInstance;
    }

    /** Description of equals(Voter voter)
     * Compares 2 objects and checks if they have
     * the same voterID and voterPcode
     * @param voter
     * @return boolean
     */
    public boolean equals(Voter voter) {
        if(this.voterID == voter.voterID && new String(this.voterPcode).equals(new String(voter.voterPcode))) {
            return true;
        }
        else {
            return false;
        }
    }

    /** Main method
     * For Part II A
      */
    public static void main(String[] args) {
        System.out.println(findNumberOfCreatedVoter());

        Voter v = new Voter();
        Voter v2 = new Voter();

        v.setVoterEmail("abcd@gmail");

		System.out.println(findNumberOfCreatedVoter());

        v2.setVoterPcode("Voter22".toCharArray());

        System.out.println(v.equals(v2));
    }
}


