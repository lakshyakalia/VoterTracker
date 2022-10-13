package com.assignment.voter;

public class Voter {
    //	Data members
    private final long voterID;
    private String voterName;
    private byte voterAge;
    private String voterEmail;
    private  char[] voterPcode;

    //	Counter to keep track of instances of Voter class
    private static int countInstance = 0;

    //	Default Constructor initializing values
    public Voter(){

        this.voterName = "John Doe";
        this.voterAge = 0;
        this.voterEmail = "abc@gmail.com";
        this.voterPcode = "H3N1V3".toCharArray();

        countInstance++;
        this.voterID = countInstance;
    }

    //	Parameterized Constructor setting values
    public Voter(String name, byte age, String email, char[] pcode) {
        this.voterName = name;
        this.voterAge = age;
        this.voterEmail = email;
        this.voterPcode = pcode;

        countInstance++;
        this.voterID = countInstance;
    }

    //	Accessors and Mutators
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

    @Override
    public String toString() {
        return voterName + " " + voterAge + " " + voterEmail + " " + new String(voterPcode);
    }

    public static int findNumberOfCreatedVoter() {
        return countInstance;
    }

    @Override
    public boolean equals(Object obj) {
        Voter voter = (Voter) obj;
        if(this.voterID == voter.voterID && new String(this.voterPcode).equals(new String(voter.voterPcode))) {
            return true;
        }
        else {
            return false;
        }
    }

    //	Main method
//    public static void main(String[] args) {
//        Voter v = new Voter();
//        Voter v2 = new Voter();
//        v.setVoterEmail("abcd@gmail");
//		System.out.println(findNumberOfCreatedVoter());
//        v2.setVoterPcode("Voter22".toCharArray());
//        System.out.println(v.equals(v2));
//
//    }
}


