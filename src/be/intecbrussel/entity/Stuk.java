package be.intecbrussel.entity;

/**
 * Write a description of class be.intecbrussel.entity.Stuk here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
abstract public class Stuk {
    private Timer t;

    abstract public void ActiveerHetNu();

    abstract public boolean activeerbaar();

    abstract public boolean geKlikt();

    public boolean geactiveerd;
    public boolean isDam;
    public boolean aanZet;

    abstract public void Disactiveren();

    public boolean bezet() {
        return true;

    }

    //wat is dit??
    abstract void stro();

    public boolean immuun;
}
