import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeScreen{

    private BufferedImage doorStartup;
    private ArrayList<AppIcon> apps;

    public HomeScreen() {
        doorStartup = readDoorImage();
        apps = new ArrayList<AppIcon>();
        apps.add(new AppIcon("Calculator"));
        apps.add(new AppIcon("Notes"));
        apps.add(new AppIcon("browser"));
        apps.add(new AppIcon("random1"));
        apps.add(new AppIcon("random2"));
        apps.add(new AppIcon("random3"));
        apps.add(new AppIcon("random4"));
        apps.add(new AppIcon("random5"));
        apps.add(new AppIcon("random6"));
        apps.add(new AppIcon("random7"));
        apps.add(new AppIcon("exit"));

    }


    public BufferedImage getDoorStartup() {
        return doorStartup;
    }

    public BufferedImage readDoorImage() {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("images/door.png"));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<AppIcon> getAppIcons() {
        return apps;
    }
}
