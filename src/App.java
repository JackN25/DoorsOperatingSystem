import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {
    private BufferedImage icon;
    private String name;
    private Rectangle bounds;
    private boolean highlighted;
    private JPanel appDrawer;
    private Thread appThread;
    private boolean isRunning;
    private JFrame appFrame;

    public App(String name) {
        this.name = name;
        readImage(name);
        isRunning = false;
    }

    public void runApp() {
        if (name.equals("Notes")) {
            if (appFrame == null) {
                appFrame = new NotesAppInterface();
            }
        } else if (name.equals("Weather")) {
            //TODO: Make weather app interface and calculator app interface
        }
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