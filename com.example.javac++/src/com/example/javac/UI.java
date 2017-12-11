package com.example.javac;
import com.sun.scenario.Settings;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.synth.ColorType;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class UI extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private final Container container;
    private final JTextArea textArea,textArea1;
    private final JMenuBar menuBar;
    private final JComboBox fontSize, fontType;
    private final JMenu menuFile, menuEdit, menuFind, menuAbout, build;
    /**
     *
     */
   // public static Settings settings = new Settings();

   // public static TabbedPane tabbedPane;
    
    JPanel panel,panel1;
    String path,nameFile;

    private final JMenuItem newFile, openFile, saveFile, close, cut, copy, paste, clearFile, selectAll, quickFind,
            aboutMe, aboutSoftware, wordWrap, comp, build1;



    private final JToolBar mainToolbar;
    JButton newButton, openButton, saveButton, clearButton, quickButton, aboutMeButton, aboutButton, closeButton , compButton, buildButton;
          
    private final Action selectAllAction;

    private final ImageIcon newIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\new.png");
    private final ImageIcon openIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\open.png");
    private final ImageIcon saveIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\save.png");
    private final ImageIcon closeIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\close.png");

    private final ImageIcon clearIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\clear.png");
    private final ImageIcon cutIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\cut.png");
    private final ImageIcon copyIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\copy.png");
    private final ImageIcon pasteIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\paste.png");
    private final ImageIcon selectAllIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\selectall.png");
    private final ImageIcon wordwrapIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\wordwrap.png");

    private final ImageIcon searchIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\search.png");

    private final ImageIcon aboutMeIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\about_me.png");
    private final ImageIcon aboutIcon = new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\about.png");
    private final ImageIcon bldIcon = new ImageIcon(new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\run.jpg").getImage().getScaledInstance(27,27, Image.SCALE_DEFAULT));
    private final ImageIcon compIcon = new ImageIcon(new ImageIcon("C:\\Users\\AkM\\Documents\\NetBeansProjects\\com.example.javac++\\icons\\exit.jpg").getImage().getScaledInstance(27,27, Image.SCALE_DEFAULT));
  

    private SupportedKeywords kw = new SupportedKeywords();
    private HighlightText languageHighlighter = new HighlightText(Color.CYAN);
    AutoComplete autocomplete;
    private boolean hasListener = false;
     private int width = 1300;
    private int height = 1000;
    
        final JPopupMenu jpu=new JPopupMenu();
        JMenuItem jmiCut=new JMenuItem("Cut",cutIcon);     
        JMenuItem jmiCopy=new JMenuItem("Copy",copyIcon);
        JMenuItem jmiPaste=new JMenuItem("Paste",pasteIcon);
    
    public UI() throws BadLocationException, BadLocationException
    {       
    
        container = getContentPane();       
        setSize(width, height);  
        panel=new JPanel();
        
        // Set the title of the window
        setTitle("Untitled | " + Atom.NAME);
        nameFile = getTitle();
        path = "unknown";

        // Set the default close operation (exit when it gets closed)
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set a default font for the TextArea
        textArea = new JTextArea("",1260,height);
        textArea.setFont(new Font("Arial", Font.BOLD, 20));

        /* SETTING BY DEFAULT WORD WRAP ENABLED OR TRUE */
        textArea.setLineWrap(true);

        // Set an higlighter to the JTextArea
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                languageHighlighter.highLight(textArea, kw.getCppKeywords());
                languageHighlighter.highLight(textArea, kw.getJavaKeywords());
                languageHighlighter.highLight(textArea, kw.getCKeywords());
            }
        });
        

        // This is why we didn't have to worry about the size of the TextArea!
        getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
        getContentPane().add(textArea);

         // splitting the frames
        
        panel = new JPanel( new BorderLayout() );
        panel.add(textArea, BorderLayout.CENTER);
        panel.setSize(1260,height);

       
       //Line Numbering
     
        JPanel glass = new JPanel(new BorderLayout());
        glass.setSize(20, height);
      
        TextLineNumber lineNumber = new TextLineNumber(textArea,3);
        glass.add(lineNumber, BorderLayout.WEST);
        glass.setBackground(Color.BLUE);       

        glass.setVisible(true);
        JPanel parent = new JPanel(new BorderLayout());
        parent.setSize(width,height);
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setSize(width, height);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(55);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(glass);
        splitPane.setRightComponent(panel);
        parent.add(splitPane);
        this.add(parent);
        
        //end of the spitt addition
        
        //  creating a popupmenu
        
        //jmiPaste.setEnabled(false);
        jpu.add(jmiCut);
        jpu.add(jmiCopy);
        jpu.add(jmiPaste);
     
     //   setVisible(true);

        //PopUp Menu Works here 
        
        jmiCut.addActionListener(this);
        jmiCopy.addActionListener(this);
        jmiPaste.addActionListener(this);
               
         
        textArea.setInheritsPopupMenu(true);  //important
        panel.add(BorderLayout.CENTER, textArea);
        textArea.setComponentPopupMenu(jpu);
        
        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                switch(e.getModifiers()) {
                    case InputEvent.BUTTON3_MASK: {
                        jpu.show(e.getComponent(), e.getX(), e.getY());
                        break;
                    }
                }
            }
        });
       
        // end of popup work */
    
        JScrollPane scroll = new JScrollPane(parent);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll);
  
        
        // Set the Menus
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFind = new JMenu("Search");
        menuAbout = new JMenu("About");
        build = new JMenu("Build");
        //Font Settings menu

        // Set the Items Menu
        newFile = new JMenuItem("New", newIcon);
        openFile = new JMenuItem("Open", openIcon);
        saveFile = new JMenuItem("Save", saveIcon);
        close = new JMenuItem("Quit", closeIcon);
        clearFile = new JMenuItem("Clear", clearIcon);
        quickFind = new JMenuItem("Quick", searchIcon);
        aboutMe = new JMenuItem("About Me", aboutMeIcon);
        aboutSoftware = new JMenuItem("About Software", aboutIcon);
        comp = new JMenuItem("Compile", compIcon);
        build1= new JMenuItem("Build", bldIcon);
       // menuItemLineNumbers = new JMenuItem("Line Numbers",lineIcon);
        //menuItemLineNumbers.setIcon(ImageIcons.iconActive);

  
        menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFind);
        menuBar.add(menuAbout);
        menuBar.add(build);


        this.setJMenuBar(menuBar);

        // Set Actions:
        selectAllAction = new SelectAllAction("Select All", clearIcon, "Select all text", new Integer(KeyEvent.VK_A),
                textArea);

        this.setJMenuBar(menuBar);

        //compile
        comp.addActionListener(this);  // Adding an action listener (so we know when it's been clicked).
        comp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.CTRL_MASK)); // Set a keyboard shortcut
        build.add(comp); // Adding the file menu

        //build
        build1.addActionListener(this);  // Adding an action listener (so we know when it's been clicked).
        build1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.CTRL_MASK)); // Set a keyboard shortcut
        build.add(build1); // Adding the file menu

        // New File
        newFile.addActionListener(this);  // Adding an action listener (so we know when it's been clicked).
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)); // Set a keyboard shortcut
        menuFile.add(newFile); // Adding the file menu

        // Open File
        openFile.addActionListener(this);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuFile.add(openFile);

        // Save File
        saveFile.addActionListener(this);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuFile.add(saveFile);

      

        // Close File
        /*
         * Along with our "CTRL+F4" shortcut to close the window, we also have
         * the default closer, as stated at the beginning of this tutorial. this
         * means that we actually have TWO shortcuts to close:
         * 1) the default close operation (example, Alt+F4 on Windows)
         * 2) CTRL+F4, which we are
         * about to define now: (this one will appear in the label).
         */
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        close.addActionListener(this);
        menuFile.add(close);


        // Select All Text
        selectAll = new JMenuItem(selectAllAction);
        selectAll.setText("Select All");
        selectAll.setIcon(selectAllIcon);
        selectAll.setToolTipText("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        menuEdit.add(selectAll);

        // Clear File (Code)
        clearFile.addActionListener(this);
        clearFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
        menuEdit.add(clearFile);

        // Cut Text
        cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setIcon(cutIcon);
        cut.setToolTipText("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        menuEdit.add(cut);


        // WordWrap
        wordWrap = new JMenuItem();
        wordWrap.setText("Word Wrap");
        wordWrap.setIcon(wordwrapIcon);
        wordWrap.setToolTipText("Word Wrap");

        //Short cut key or key stroke
        wordWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        menuEdit.add(wordWrap);

        /* CODE FOR WORD WRAP OPERATION
         * BY DEFAULT WORD WRAPPING IS ENABLED.
        */
        wordWrap.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent ev) {
                    // If wrapping is false then after clicking on menuitem the word wrapping will be enabled
                    if(textArea.getLineWrap()==false) {
                        /* Setting word wrapping to true */
                        textArea.setLineWrap(true);
                    } else {
                        // else  if wrapping is true then after clicking on menuitem the word wrapping will be disabled
                        /* Setting word wrapping to false */
                        textArea.setLineWrap(false);
                }
            }
        });
        try {
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
       // caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        Rectangle rectangle;
            rectangle = textArea.modelToView( textArea.getCaretPosition() );
        }catch(Exception e)
        {} 
        
        
  
        
        // Copy Text
        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setIcon(copyIcon);
        copy.setToolTipText("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        menuEdit.add(copy);
        
       
        
        // Paste Text
        paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setIcon(pasteIcon);
        paste.setToolTipText("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        menuEdit.add(paste);

        // Find Word
        quickFind.addActionListener(this);
        quickFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        menuFind.add(quickFind);

        // About Me
        aboutMe.addActionListener(this);
        aboutMe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        menuAbout.add(aboutMe);

        // About Software
        aboutSoftware.addActionListener(this);
        aboutSoftware.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        menuAbout.add(aboutSoftware);
        
        //quitIcon
    //    quitIcon.addActionListener(this);
        
        

        mainToolbar = new JToolBar();
        this.add(mainToolbar, BorderLayout.NORTH);
        // used to create space between button groups
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 48);

        newButton = new JButton(newIcon);
        newButton.setToolTipText("New");
        newButton.addActionListener(this);
        mainToolbar.add(newButton);
        mainToolbar.addSeparator();

        openButton = new JButton(openIcon);
        openButton.setToolTipText("Open");
        openButton.addActionListener(this);
        mainToolbar.add(openButton);
        mainToolbar.addSeparator();

        saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(this);
        mainToolbar.add(saveButton);
        mainToolbar.addSeparator();

        clearButton = new JButton(clearIcon);
        clearButton.setToolTipText("Clear All");
        clearButton.addActionListener(this);
        mainToolbar.add(clearButton);
        mainToolbar.addSeparator();

        quickButton = new JButton(searchIcon);
        quickButton.setToolTipText("Quick Search");
        quickButton.addActionListener(this);
        mainToolbar.add(quickButton);
        mainToolbar.addSeparator();


        aboutMeButton = new JButton(aboutMeIcon);
        aboutMeButton.setToolTipText("About Me");
        aboutMeButton.addActionListener(this);
        mainToolbar.add(aboutMeButton);
        mainToolbar.addSeparator();

        aboutButton = new JButton(aboutIcon);
        aboutButton.setToolTipText("About Editor");
        aboutButton.addActionListener(this);
        mainToolbar.add(aboutButton);
        mainToolbar.addSeparator();
               
        buildButton = new JButton(bldIcon);
        buildButton.setToolTipText("Run");
        buildButton.addActionListener(this);
        mainToolbar.add(buildButton);
        mainToolbar.addSeparator();

        compButton = new JButton(compIcon);
        compButton.setToolTipText("Compile");
        compButton.addActionListener(this);
        mainToolbar.add(compButton);
        mainToolbar.addSeparator();


        closeButton = new JButton(closeIcon);
        closeButton.setToolTipText("Quit");
        closeButton.addActionListener(this);
        mainToolbar.add(closeButton);
        mainToolbar.addSeparator();
        
        
          
        //Undo and Redo
        
   final UndoManager undo = new UndoManager();
   Document doc = textArea.getDocument();
    
   // Listen for undo and redo events
   doc.addUndoableEditListener(new UndoableEditListener() {
       public void undoableEditHappened(UndoableEditEvent evt) {
           undo.addEdit(evt.getEdit());
       }
   });
    
   // Create an undo action and add it to the text component
   textArea.getActionMap().put("Undo",
       new AbstractAction("Undo") {
           public void actionPerformed(ActionEvent evt) {
               try {
                   if (undo.canUndo()) {
                       undo.undo();
                   }
               } catch (CannotUndoException e) {
               }
           }
      });
    
   // Bind the undo action to ctl-Z
   textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    
   // Create a redo action and add it to the text component
   textArea.getActionMap().put("Redo",
       new AbstractAction("Redo") {
           public void actionPerformed(ActionEvent evt) {
               try {
                   if (undo.canRedo()) {
                       undo.redo();
                   }
               } catch (CannotRedoException e) {
               }
           }
       });
    
   

 
   // Bind the redo action to ctl-Y
   textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
        
        
   Rectangle r = this.getBounds();
   int h = r.height;
   int w = r.width;
    textArea1 = new JTextArea();
    textArea1.setBounds(w-150,h-180,100,40);
    textArea.add(textArea1);
    //this.addWindowStateListener(new WindowEventDemo(this,textArea1));
   XYComponent xy = new XYComponent(this,textArea1);
   xy.start();
    textArea.addMouseMotionListener(new MouseMotionEventDemo(textArea1));
   
   
       
        
       
   
    
  /****************** FONT SETTINGS SECTION ***********************/

        //FONT FAMILY SETTINGS SECTION START

        fontType = new JComboBox();

          //GETTING ALL AVAILABLE FONT FAMILY NAMES
        String [] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++)
        {
            //Adding font family names to font[] array
             fontType.addItem ( fonts [i] );
        }
        //Setting maximize size of the fontType ComboBox
        fontType.setMaximumSize( new Dimension ( 170, 30 ));
        mainToolbar.add( fontType );
        mainToolbar.addSeparator();
        
        
        
        
        
        // this is for Auto Intentation
        AbstractDocument doc1;
        doc1 = (AbstractDocument)textArea.getDocument();
        doc1.setDocumentFilter( new com.example.javac.NewLineFilter() );
        // till 

        //Adding Action Listener on fontType JComboBox

        fontType.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent ev)
                {
                    //Getting the selected fontType value from ComboBox
                    String p = fontType.getSelectedItem().toString();
                    //Getting size of the current font or text
                    int s = textArea.getFont().getSize();
                    textArea.setFont( new Font( p, Font.PLAIN, s));
                }
        });

        //FONT FAMILY SETTINGS SECTION END


        //FONT SIZE SETTINGS START

        fontSize = new JComboBox();

            for( int i = 15 ; i <= 100 ; i++)
            {
                fontSize.addItem( i );
            }
        fontSize.setMaximumSize( new Dimension( 70,30 ));
        mainToolbar.add( fontSize );

        fontSize.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent ev)
                {
                   String sizeValue = fontSize.getSelectedItem().toString();
                    int sizeOfFont = Integer.parseInt( sizeValue );
                    String fontFamily = textArea.getFont().getFamily();

                    Font font1 = new Font( fontFamily , Font.PLAIN , sizeOfFont );
                    textArea.setFont( font1 );

                }
        });
        //FONT SIZE SETTINGS SECTION END
        
      
    }
    
    
    // UI constructor Ends here
  //  ---------------------------------------------------
  //  ---------------------------------------------------
  //  ---------------------------------------------------
        
 
    // Make the TextArea available to the autocomplete handler
    protected JTextArea getEditor() {
        return textArea;
    }

    // Enable autocomplete option
    public void enableAutoComplete(File file) {
        if (hasListener) {
            textArea.getDocument().removeDocumentListener(autocomplete);
            hasListener = false;
        }

        ArrayList<String> arrayList;
        String[] list = kw.getSupportedLangage();

        for (int i = 0; i < list.length; i++) {
            if (file.getName().endsWith(list[i])) {
                switch (i) {
                    case 0:
                        String[] jk = kw.getJavaKeywords();
                        arrayList = kw.setKeywords(jk);
                        autocomplete = new AutoComplete(this, arrayList);
                        textArea.getDocument().addDocumentListener(autocomplete);
                        hasListener = true;
                        break;
                    case 1:
                        String[] ck = kw.getCppKeywords();
                        arrayList = kw.setKeywords(ck);
                        autocomplete = new AutoComplete(this, arrayList);
                        textArea.getDocument().addDocumentListener(autocomplete);
                        hasListener = true;
                        break;
                }
            }
        }
    }

    public void actionPerformed (ActionEvent e) {
        // If the source of the event was our "close" option
        if (e.getSource() == close || e.getSource() == closeButton) {
            this.dispose(); // dispose all resources and close the application
        }
        // If the source was the "new" file option
        else if (e.getSource() == newFile || e.getSource() == newButton) {
            //FEdit.clear(textArea);
           // setTitle("");
           //FEdit.another();
           
            try {
                new UI().setVisible(true);
            } catch (BadLocationException ex) {
                
            }
           
        }

        // If the source was the "open" option
        else if (e.getSource() == openFile || e.getSource() == openButton) {
           JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to  browse files to open)
            int option = open.showOpenDialog(this); // get the option that the user selected (approve or cancel)

            /*
             * NOTE: because we are OPENing a file, we call showOpenDialog~ if
             * the user clicked OK, we have "APPROVE_OPTION" so we want to open
             * the file
             */
            if (option == JFileChooser.APPROVE_OPTION) {
                FEdit.clear(textArea); // clear the TextArea before applying the file contents
                try {
                    File openFile = open.getSelectedFile();
                    setTitle(openFile.getName() + " | " + Atom.NAME);
                    nameFile = openFile.getName();
                    path = openFile.getPath();
                    Scanner scan = new Scanner(new FileReader(openFile.getPath()));
                    while (scan.hasNext())
                        textArea.append(scan.nextLine() + "\n");

                    enableAutoComplete(openFile);
                } catch (Exception ex) { // catch any exceptions, and...
                    // ...write to the debug console
                    System.out.println(ex.getMessage());
                }
            }
        }
        // If the source of the event was the "save" option
        else if (e.getSource() == saveFile || e.getSource() == saveButton) {
            if(getTitle()=="Untitled | " + Atom.NAME){
            JFileChooser fileChoose = new JFileChooser();
            // Open the file, only this time we call
            int option = fileChoose.showSaveDialog(this);

            /*
             * ShowSaveDialog instead of showOpenDialog if the user clicked OK
             * (and not cancel)
             */
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File openFile = fileChoose.getSelectedFile();
                    setTitle(openFile.getName() + " | " + Atom.NAME);
                    nameFile = openFile.getName();
                    BufferedWriter out = new BufferedWriter(new FileWriter(openFile.getPath()));
                    
                    path = openFile.getPath();   // get the path of the file
                    
                //    System.out.println(v); 
                    
                    out.write(textArea.getText());
                    out.close();

                    enableAutoComplete(openFile);

                } catch (Exception ex) { // again, catch any exceptions and...
                    // ...write to the debug console
                    System.out.println(ex.getMessage());
                }
            }
            }
            else{
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

        // Clear File (Code)
        if (e.getSource() == clearFile || e.getSource() == clearButton) {
            FEdit.clear(textArea);
        }
        // Find
        if (e.getSource() == quickFind || e.getSource() == quickButton) {
            new Find(textArea);
        }

        // About Me
        else if (e.getSource() == aboutMe || e.getSource() == aboutMeButton) {
            new About().me();
        }
        // About Software
        else if (e.getSource() == aboutSoftware || e.getSource() == aboutButton) {
            new About().software();
        }
        
        else if(e.getSource()==comp || e.getSource()==compButton) {
            
            CompileAndRun c =  new CompileAndRun(this,path,nameFile);
               c.jButton1ActionPerformed(e);
            
        }
        
        else if(e.getSource()==build1)
        {
            CompileAndRun c =new CompileAndRun(this,path,nameFile);
            c.jButton2ActionPerformed(e);
        }
        
        
        if (e.getSource() == jmiCut) {
            JTextArea jte = (JTextArea)jpu.getInvoker();
            jte.cut();
        }
        if (e.getSource() == jmiCopy) {
            JTextArea jte = (JTextArea)jpu.getInvoker();
            jte.copy();
        }
        if (e.getSource() == jmiPaste) {
            JTextArea jte = (JTextArea)jpu.getInvoker();
            jte.paste();
        }

}


    class SelectAllAction extends AbstractAction {
        /**
         * Used for Select All function
         */
        private static final long serialVersionUID = 1L;

        public SelectAllAction(String text, ImageIcon icon, String desc, Integer mnemonic, final JTextArea textArea) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            textArea.selectAll();
        }
    }
    
    
    
    //auto intentation
    
    public class NewLineFilter extends DocumentFilter
    {
    public void insertString(DocumentFilter.FilterBypass fb, int offs, String str, AttributeSet a)
        throws BadLocationException
    {
        if ("\n".equals(str))
            str = addWhiteSpace(fb.getDocument(), offs);

        super.insertString(fb, offs, str, a);
    }

    public void replace(DocumentFilter.FilterBypass fb, int offs, int length, String str, AttributeSet a)
        throws BadLocationException
    {
        if ("\n".equals(str))
            str = addWhiteSpace(fb.getDocument(), offs);

        super.replace(fb, offs, length, str, a);
    }

    private String addWhiteSpace(Document doc, int offset)
        throws BadLocationException
    {
        StringBuilder whiteSpace = new StringBuilder("\n");
        Element rootElement = doc.getDefaultRootElement();
        int line = rootElement.getElementIndex( offset );
        int i = rootElement.getElement(line).getStartOffset();

        while (true)
        {
            String temp = doc.getText(i, 1);

            if (temp.equals(" ") || temp.equals("\t"))
            {
                whiteSpace.append(temp);
                i++;
            }
            else
                break;
        }

        return whiteSpace.toString();
    } 
    }
  
}


