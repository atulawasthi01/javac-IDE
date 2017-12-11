package com.example.javac;
import javax.swing.*;
import java.awt.FlowLayout;
public class About {

    private final JFrame frame;
    private final JPanel panel;
    private String contentText;
    private final JLabel text;

    public About() {
        panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500,400);
        frame.setResizable(false);
        frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\Author.jpg")));
        frame. setLayout(new FlowLayout());
        text = new JLabel();     
    }
    public void me() {
        frame.setTitle("About Me - " + Atom.NAME);

        contentText =
        "<html><body><p>" +
        "Author: Ankit Mishra & Atul Awasthi<br />" +
        "Contact us at: " +
        "<a href='mail_us:" + Atom.AUTHOR_EMAIL + "?subject=About JavaC++ Compiler'>" + Atom.AUTHOR_EMAIL + "</a>" +
                "<br /><br />" +
                "Revised By: Atul Awasthi<br />" +
                "Contact me at: <a href='mailto:" + Atom.EDITOR_EMAIL + "?subject=About the JavaC++'>" + Atom.EDITOR_EMAIL + "</a>" +
        "</p></body></html>";

        text.setText(contentText);
        panel.add(text);
        frame.add(panel);
    }
    public void software() {
       
        frame.setTitle("About Me - " + Atom.NAME);
        contentText =
        "<html><body><p>" +
        "Name: " + Atom.NAME + "<br />" +
        "Version: " + Atom.VERSION +
        "</p></body></html>";
        text.setText(contentText);
        panel.add(text);
        frame.add(panel);
    }

}