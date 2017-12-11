package com.example.javac;
import static com.sun.glass.ui.Application.run;
import static com.sun.org.apache.regexp.internal.RETest.test;
import java.awt.Color;
import static java.awt.Color.RED;
import static java.awt.Color.red;
import static java.awt.SystemColor.text;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import static sun.misc.PostVMInitHook.run;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shawon
 */
public class CompileAndRun{
    JFrame f;
    String path,nameFile;
    /**
     * Creates new form comp
     */
    String exe;
    public CompileAndRun(JFrame f,String path,String nameFile) {
        this.f  = f;
        this.path = path;
        System.out.println("patg = "+path);
        this.nameFile = nameFile;
                try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CompileAndRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompileAndRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompileAndRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompileAndRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
    }

    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if((path == "unknown")){
         JOptionPane.showMessageDialog(f, "Save the file first", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
        else{
                CompileFrame cf = new CompileFrame();
                
                cf.setTitle("Compilation And Run");
                ProcessBuilder pb=new ProcessBuilder();
                  try {
                    if (nameFile.endsWith(".c"))
                    {
                        System.out.println("gcc "  + path);
                      pb = new ProcessBuilder("cmd", "/C", "gcc "  + path);
                    }
                    else if (nameFile.endsWith(".cpp"))
                    {
                      pb = new ProcessBuilder("cmd", "/C", "g++ "+  path);

                    }

                    else if (nameFile.endsWith(".java"))
                    {
                      pb = new ProcessBuilder("cmd", "/C", "javac "  + path);

                    }
                    else {
                        JOptionPane.showMessageDialog(f, "Save the file with proper extension", "Error", JOptionPane.ERROR_MESSAGE);
                        
                        return;
                    }

                    cf.setVisible(true);
                    Process p = pb.start();
                    p.waitFor();
                    int x = p.exitValue();

                    if (x == 0) {
                       
                       JTextArea l2 =new JTextArea("  ===== 0 error.. Compilation Finished");
                       l2.setBounds(0,0,400,300);
                       cf.p2.add(l2);
                       l2.setEditable(false);
                    } else {

                        BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        //BufferedWriter rm=new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));


                        String out;
                        String result = "";


                        while ((out = r.readLine()) != null)
                        {
                            result += out;
                            result +="\n";
                        }
                        JTextArea l2 = new JTextArea();
                        l2.setBounds(0,0,400,300);
                        l2.setLineWrap(true);
                        l2.setText(result);
                        
                        cf.p2.add(l2);
                        l2.setEditable(false);
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if(path == "unknown"){
            JOptionPane.showMessageDialog(f, "Save the file first", "Error", JOptionPane.ERROR_MESSAGE);                
        }
        else {

                ProcessBuilder pb=new ProcessBuilder();
                //System.out.println("java "+nameFile);
                  try {
                    if (nameFile.endsWith(".c"))
                    {
                  //    System.out.println("gcc " + path);
                      pb = new ProcessBuilder("cmd", "/C", "gcc " + path);
                    }
                    else if (nameFile.endsWith(".cpp"))
                    {
                       pb = new ProcessBuilder("cmd", "/C", "g++ "+  path);
                    }
                    else if(nameFile.endsWith(".java"))
                    {
                        
                        pb = new ProcessBuilder("cmd", "/C", "javac "  + path);
                    }
                    else {JOptionPane.showMessageDialog(f, "Save the file with proper extension", "Error", JOptionPane.ERROR_MESSAGE);
                    return ;
                    }
                    Process p = pb.start();
                    p.waitFor();
                    int x = p.exitValue();
                    //System.out.println("Hello1");
                    if (x == 0) {
                        Runtime rt = Runtime.getRuntime();
                        CompileFrame cf = new CompileFrame();
                        
                        cf.setTitle("Compilation And Run");
      
                        String v = path.substring(0, path.lastIndexOf('\\'));
                        Process p1;
                        ProcessBuilder pb1 = new ProcessBuilder();
                       
                        if (nameFile.endsWith(".c")||nameFile.endsWith(".cpp")){
                         pb1 = new ProcessBuilder( "cmd" , "/C" , "C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++" + "\\" + "a < " + "C:\\Users\\AkM\\Documents" + "\\Input.txt dir > " + "C:\\Users\\AkM\\Documents"+ "\\" + "Output.txt");
                         p1 = pb1.start();

                        }
                        else {                        
                        String s = "java -cp "+ v + " " +nameFile.substring(0,nameFile.lastIndexOf('.'));
                        System.out.println(s);
                            System.out.println(path);
                             pb1 = new ProcessBuilder( "cmd" , "/C" , s +"< " + v + "\\Input.txt dir > " + v + "\\" + "Output.txt");  
                            p1 = pb1.start();
                        }
                    }
                        else {
                        JOptionPane.showMessageDialog(f, "Compilation Error", "Error", JOptionPane.ERROR_MESSAGE);                        
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JTextField filename;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JEditorPane m_monitor;
    private javax.swing.JTextArea text;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param s
     */

}
