import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App extends JInternalFrame {
    private BufferedImage icon;
    private String name;
    private Rectangle bounds;
    private boolean highlighted;

    public App(String name) {
        this.name = name;
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