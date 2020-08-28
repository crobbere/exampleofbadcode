package be.intecbrussel.entity;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Controller
        implements MouseListener {
    private Timer t;
    private Bord b;
    private View v;


    public Controller(Bord bord, View view, Timer timer) {
        b = bord;
        v = view;
        v.addMouseListener(this);
        t = timer;
        b.beurtwissel();
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 60;
        int y = e.getY() / 60;

        if (x < 10 && y < 10 && x > -1 && y > -1) {

            switch (t.deTijd % 2) {
                case 0:
                    b.moetPakken();
                    b.scannen();
                    b.activeren(x, y);

                    if (b.geactiveerd) {

                        t.tijden();


                    }

                    break;
                case 1:
                    b.scannen();
                    b.bewegen(x, y);
                    if (b.klaar) {
                        b.checkItOut();
                        t.tijden();
                        b.beurtwissel();

                    }


                    break;
                default:


                    System.out.println(t.deTijd);
                    break;
            }
        }
    }


    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        v.repaint();

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

}

