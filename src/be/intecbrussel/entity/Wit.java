package be.intecbrussel.entity;

/**
 * Write a description of class wit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wit extends Stuk {

    private boolean geactiveerd;
    public boolean immuun;

    public void dam() {
        isDam = false;
    }

    public Wit() {
    }

    public Wit(boolean klik) {
        geactiveerd = klik;
    }

    public boolean geKlikt() {
        return geactiveerd;

    }

    public void ActiveerHetNu() {
        geactiveerd = true;
    }

    public void Disactiveren() {
        geactiveerd = false;
        immuun = false;
    }

    public boolean activeerbaar() {
        if (immuun) {
            System.out.println("hoera");
            return true;
        } else {
            if (aanZet) {
                return true;
            } else {
                return false;
            }
        }


    }

    public void stro() {
        immuun = true;


    }


}
