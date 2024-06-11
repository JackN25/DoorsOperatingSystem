import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotesAppInterface extends JFrame implements ActionListener {
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private File folder = new File("savedNotes");
    private ArrayList<Notes> allNotes = new ArrayList<Notes>();
    private JMenu menu1;
    private JMenu menu2;
    private JMenuBar menuBar;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem selectAll;
    private JMenuItem wordCount;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private String currentFileName;


    public NotesAppInterface() {
        super("Notes");
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,500);
        this.setLocation(100,100);
        this.setVisible(true);
        drawMenuBar();
    }

    private void drawMenuBar() {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menu1 = new JMenu("File");
        menu2 = new JMenu("Tools");
        menuBar.add(menu1);
        menuBar.add(menu2);
        save = new JMenuItem("Save current file");
        save.addActionListener(this);
        load = new JMenuItem("Load existing file");
        load.addActionListener(this);
        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        wordCount = new JMenuItem("Word Count");
        wordCount.addActionListener(this);
        menu1.add(save); menu1.add(load);
        menu2.add(copy); menu2.add(paste); menu2.add(selectAll); menu2.add(wordCount);
        textArea = new JTextArea("Enter something here...", 10, 10);
        textArea.setLineWrap(true);
        textArea.setTabSize(4);
        scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        LOGGER.log(Level.INFO, s);
        if (s.equals("Save current file") && currentFileName == null) {
            String name = JOptionPane.showInputDialog("Name this note: ");
            if (name != null) {
                File f = new File("savedNotes/" + name);
                if (!allNotes.contains(f)) {
                    saveFile(f);
                    JOptionPane.showMessageDialog(null, "Note saved!");
                    currentFileName = name;
                } else {
                    JOptionPane.showMessageDialog(null, "There is already a note with this name!");
                }
            }
        } else if (s.equals("Save current file")) {
            File f = new File("savedNotes/" + currentFileName);
            updateFile(f);
            JOptionPane.showMessageDialog(null, "Updated the note(The note will be updated when you restart the system)!");
        }
        if (s.equals("Load existing file")) {
            showAllNotes();
        }
        if (s.equals("Copy")) {
            StringSelection stringSelection = new StringSelection (textArea.getText());
            Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
            clpbrd.setContents (stringSelection, null);
        }
        if (s.equals("Paste")) {
            try {
                textArea.setText(textArea.getText() + (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor));
            } catch (UnsupportedFlavorException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (s.equals("Select All")) {
            textArea.requestFocusInWindow();
            textArea.selectAll();
        }
        if (s.equals("Word Count")) {
            String text = textArea.getText();
            String[] words = text.trim().split("\\s+");
            int wordCount = words.length;
            JOptionPane.showMessageDialog(null, "Number of words: " + wordCount);
        }

    }

    private void updateFile(File f) {
        try {
            FileWriter fileWriter = new FileWriter(f, false);
            fileWriter.write(textArea.getText());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveFile(File f) {
        if (currentFileName == null) {
            try {
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.write(textArea.getText());
                writer.close();
                LOGGER.log(Level.INFO, "Created and wrote to new file");
            } catch (IOException e) {
                LOGGER.log(Level.INFO, "Error while creating new file");
            }
        }
    }

    private void showAllNotes() {
        getAllNotes(folder);
        JFrame savedNotesShower = new JFrame("Load an existing file");
        savedNotesShower.setVisible(true);
        savedNotesShower.setLayout(new FlowLayout());
        for (Notes n : allNotes) {
            Button b = new Button(n.getName());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTextToLoad(n.getContent(), n.getName());
                    savedNotesShower.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Loaded: " + n.getName());
                }
            });
            savedNotesShower.add(b);
        }
        savedNotesShower.pack();
    }

    private void getAllNotes(File folder) {
        for (File f : folder.listFiles()) {
            Notes saved = new Notes(f.getName(), f.getPath());
            if (!allNotes.contains(saved)) {
                allNotes.add(saved);
            }
        }
    }

    private void setTextToLoad(String text, String name) {
        textArea.setText(text);
        currentFileName = name;
    }
}
