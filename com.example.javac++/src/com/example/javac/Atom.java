package com.example.javac;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
public class Atom extends JTextPane {

    private static final long serialVersionUID = 1L;
    public final static String AUTHOR_EMAIL = "javaC++123@gmail.com";
    public final static String NAME = "JavaC++";
    public final static String EDITOR_EMAIL = "javac++123@gmail.com";
    public final static double VERSION = 2.3;

    public static void main(String[] args) throws BadLocationException {
        UI u = new UI();
        u.setVisible(true);
    }
}