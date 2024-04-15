import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AppIcon {
    private BufferedImage icon;
    private String name;
    private Rectangle bounds;
    private boolean isHighlighted;

    public AppIcon(String name) {
        this.name = name;
        readImage();
    }

    private void readImage() {
        try {
            icon = ImageIO.read(new File("images/" + name + ".png"));
            this.bounds = new Rectangle(-100, -100, icon.getWidth(), icon.getHeight());
        } catch (IOException e) {
            try {
                icon = ImageIO.read(new File("images/placeholder.png"));
                this.bounds = new Rectangle(-100, -100, icon.getWidth(), icon.getHeight());
            } catch (IOException exception) {
                System.err.println("Error while retrieving file for " + name);
            }
        }
    }

    public BufferedImage getIcon() {
        return icon;
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
        return isHighlighted;
    }
}
