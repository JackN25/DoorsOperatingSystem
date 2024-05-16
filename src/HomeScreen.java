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
        apps.add(new App("exit", "exit"));
        apps.add(new NotesApp());
        apps.add(new WeatherApp());
        apps.add(new CalculatorApp());
        apps.add(new App("random2", "placeholder"));
        apps.add(new App("random3", "placeholder"));
        apps.add(new App("random4", "placeholder"));
        apps.add(new App("random5", "placeholder"));
        apps.add(new App("random6", "placeholder"));
        apps.add(new App("random7", "placeholder"));
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
