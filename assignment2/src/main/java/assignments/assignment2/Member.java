package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    /**
     * Instance variables:
     * 
     * nama         = name of current Member object
     * noHp         = phone number of current Member object
     * id           = id of current Member object, counted using generateId from NotaGenerator
     * bonusCounter = counter to determine if member is eligible for bonus
     */
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;
    /*
     * Constructor for Member class
     */
    public Member(String nama, String noHp) {
        // Assigning parameters to their respective instance variables
        this.nama = nama;
        this.noHp = noHp;
        // Generate id using generateId from NotaGenerator
        this.id = NotaGenerator.generateId(this.nama, this.noHp);
        this.bonusCounter = 0;
    }
    /*
     * Getters and Setters
     */
    public String getNama(){
        return this.nama;
    }
    public String getNoHp(){
        return this.noHp;
    }
    public String getId(){
        return this.id;
    }
    public int getBonusCounter(){
        return this.bonusCounter;
    }
    /*
     * Method that increments bonusCounter
     */
    public void addBonusCounter(){
        this.bonusCounter += 1;
    }
    /*
     * Method that resets bonusCounter
     */
    public void resetBonusCounter(){
        this.bonusCounter = 0;
    }
    /*
     * Method that calculates current member's eligibility to get discount
     * (that is, if bonusCounter == 3)
     */
    public boolean getDiscount(){
        if (this.getBonusCounter() == 3){
            // resets bonusCounter to 0 if member is eligible
            this.resetBonusCounter();
            return true;
        }
        else return false;
    }
}
