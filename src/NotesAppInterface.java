import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotesAppInterface extends JFrame implements ActionListener {
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
        if (s.equals("Save current file")) {
            saveFile();
        }

    }

    private void saveFile() {
        if (currentFileName == null) {
            try {
                File f = new File("savedNotes/test");
                f.createNewFile();
                FileWriter writer = new FileWriter("savedNotes/test");
                writer.write(textArea.getText());
                writer.close();
                LOGGER.log(Level.INFO, "Created and wrote to new file");
            } catch (IOException e) {
                LOGGER.log(Level.INFO, "Error while creating new file");
            }
        }
    }
}
