import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeScreen{

    private BufferedImage doorStartup;
    private ArrayList<App> apps;

    public HomeScreen() {
        doorStartup = readDoorImage();
        apps = new ArrayList<App>();
        apps.add(new App("exit"));
        apps.add(new App("Notes"));
        apps.add(new App("Weather"));
        apps.add(new App("Calculator"));
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

    public ArrayList<App> getApps() {
        return apps;
    }
}
