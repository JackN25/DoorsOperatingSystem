import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NotesApp extends App implements Runnable{
    private boolean isOpen = false;
    private NotesAppInterface notesAppDrawer;
    private Thread notesThread;

    private BufferedImage icon;

    public NotesApp() {
        super("Notes");
        readImage();
        super.setIcon(icon);
        int frameWidth = 100;
        int frameHeight = 100;
        notesAppDrawer = new NotesAppInterface();
        this.add(notesAppDrawer);
        //force fullscreen
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        //outside border with close, max, and min buttons   true = hide
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(200, 200);
        this.setVisible(false);
    }


    public void startThread() {
        notesThread = new Thread(this);
        notesThread.start();
    }
    @Override
    public void run() {
        startThread();
        setVisible(true);
        while (true) {
            System.out.println("Running notes");
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void readImage() {
        try {
            icon = ImageIO.read(new File("images/Notes.png"));
            setBounds(icon.getWidth(), icon.getHeight());
        } catch (IOException e) {
            try {
                icon = ImageIO.read(new File("images/placeholder.png"));
                setBounds(icon.getWidth(), icon.getHeight());
            } catch (IOException exception) {
                System.err.println("Error while retrieving file for placeholder image");
            }
            System.err.println("Error while retrieving file for Notes app image");
        }
    }
}
