package be.intecbrussel.app;

import be.intecbrussel.entity.Dammen;

public class DamApp {
    public static void main(String args[]) {
        Dammen venster = new Dammen();
        venster.setSize(800,800);
        venster.setTitle("Damspel");
        venster.setVisible(true);
    }
}
