package com.example.javac;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author AkM
 */
public class XYComponent extends Thread{
        JTextArea textArea;
    JFrame frame;
    public XYComponent(JFrame frame,JTextArea textArea)
    {
                this.frame= frame;
        this.textArea = textArea;
    }
    public void run()
    {
        while(true)
        {
             Rectangle r = frame.getBounds();
   int h = r.height;
   int w = r.width;
    textArea.setBounds(w-150,h-180,100,40);
    
        }
    }
}
