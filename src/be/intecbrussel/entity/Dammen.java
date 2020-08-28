package be.intecbrussel.entity;



import java.awt.*;
import java.awt.event.*;


public class Dammen extends Frame
{
    protected Bord m;
    protected View view;
    protected Controller c;
    protected Timer t;
    
     public Dammen()
    {
        m = new Bord();
        t = new Timer();
        view = new View(m);
        add(view);
        
        c = new Controller(m, view,t);
        
        this.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               dispose();
               System.exit(0);
            }
        });
    
    }

    
}