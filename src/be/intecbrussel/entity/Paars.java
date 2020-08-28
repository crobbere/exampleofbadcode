package be.intecbrussel.entity;

/**
 * Write a description of class paars here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Paars extends Stuk {
    private Timer t;

    public void dam() {
        isDam = false;
    }

    public boolean activeerbaar() {
        if (immuun) {
            return true;
        } else {
            if (aanZet) {
                return true;
            } else {
                return false;
            }
        }

    }

    private boolean geactiveerd;

    public void Disactiveren() {
        geactiveerd = false;
        immuun = false;
    }

    public Paars() {
    }

    public Paars(boolean klik) {
        geactiveerd = klik;
    }

    public boolean geKlikt() {
        return geactiveerd;

    }

    public void ActiveerHetNu() {
        geactiveerd = true;
    }

    public void stro() {
        immuun = true;
    }
}
   

