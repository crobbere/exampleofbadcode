package be.intecbrussel.entity;

import be.intecbrussel.entity.Bord;

import java.awt.*;

/**
 * Write a description of class be.intecbrussel.entity.View here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class View extends Canvas {
    private Bord bord;
    private int grootte = 60;

    public View(Bord dambord) {
        bord = dambord;

    }

    public void paint(Graphics g) {
        g.fillRect(0, 0, bord.getHor() * grootte, bord.getVer() * grootte);

        for (int i = 0; i < bord.getHor(); i++) {
            for (int j = 0; j < bord.getVer(); j++) {
                Stuk x = bord.getInhoud(i, j);
                teken(g, x, i * grootte, j * grootte);


            }
        }
    }

    protected void teken(Graphics g, Stuk st, int x, int y) {
        if (st instanceof Paars) {
            Paars p = (Paars) st;
            if (!p.geKlikt()) {
                g.setColor(new Color(255, 255, 255));
                g.fillRect(x, y, grootte, grootte);
                g.setColor(new Color(221, 160, 221));
                g.fillOval(x, y, grootte, grootte);
            } else if (p.geKlikt()) {
                g.setColor(new Color(100, 150, 100));
                g.fillRect(x, y, grootte, grootte);
                g.setColor(new Color(221, 160, 221));
                g.fillOval(x, y, grootte, grootte);

            }


        } else if (st instanceof Wit) {
            Wit w = (Wit) st;
            if (!w.geKlikt()) {
                g.setColor(new Color(255, 255, 255));
                g.fillRect(x, y, grootte, grootte);
                g.setColor(new Color(231, 206, 0));
                g.fillOval(x, y, grootte, grootte);
            } else if (w.geKlikt()) {
                g.setColor(new Color(100, 150, 100));
                g.fillRect(x, y, grootte, grootte);
                g.setColor(new Color(231, 206, 0));
                g.fillOval(x, y, grootte, grootte);

            }
        } else if ((x + y) % 2 == 0) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(x, y, grootte, grootte);
        } else {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(x, y, grootte, grootte);
        }


    }
}

