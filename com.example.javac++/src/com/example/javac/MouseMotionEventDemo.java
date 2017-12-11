package com.example.javac;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputAdapter;

public class MouseMotionEventDemo extends JPanel 
                                  implements MouseMotionListener {
    //...in initialization code:
        //Register for mouse events on blankArea and panel.
    JTextArea ta; 
    public MouseMotionEventDemo(JTextArea ta)
            {
                this.ta = ta;
               
            }
        

    @Override
    public void mouseDragged(MouseEvent e) {
        saySomething("Mouse dragged", e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       saySomething("Mouse moved", e);
    }

    private void saySomething(String mouse_dragged, MouseEvent e) {
        ta.setText("x - "+e.getX()+"\n"+"y  - "+e.getY());
    }
        
       
    }




