import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App extends JFrame implements Runnable {
    private BufferedImage icon;
    private String name;
    private Rectangle bounds;
    private boolean highlighted;
    private JPanel appDrawer;
    private Thread appThread;
    private boolean isRunning;

    public App(String name) {
        super(name);
        this.name = name;
        readImage(name);
        isRunning = false;
    }

    public void runApp() {
        if (name.equals("Notes")) {
            appDrawer = new NotesAppInterface();
        } else if (name.equals("Weather")) {
            //TODO: Make weather app interface and calculator app interface
        }
        int width = 500;
        int height = 500;
        this.add(appDrawer);
        //force fullscreen
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        //outside border with close, max, and min buttons   true = hide
        this.setUndecorated(false);
        this.setSize(width, height);
        this.setAlwaysOnTop(true);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(0, 0);
        this.setVisible(true);
        startThread();
        System.out.println("runapp");
    }

    public void startThread() {
        appThread = new Thread(this);
        appThread.start();
        isRunning = true;
        System.out.println("running");
    }

    @Override
    public void run() {
        while (isRunning) appDrawer.repaint();
    }

    public BufferedImage getIcon() {
        return icon;
    }
    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setIconBoxLocation(int x, int y) {
        bounds.setLocation(x,y);
    }

    private void readImage(String iconName) {
        try {
            icon = ImageIO.read(new File("images/" + iconName + ".png"));
            setBounds(icon.getWidth(), icon.getHeight());
        } catch (IOException exception) {
            System.err.println("Error while retrieving file for placeholder image");
        }
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void changeHighlighted() {
        highlighted = !highlighted;
    }

    public void setBounds(int width, int height) {
        bounds = new Rectangle(-100, -100, width, height);
    }

}