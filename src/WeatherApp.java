import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherApp extends App {
    private boolean isOpen;
    private BufferedImage icon;

    public WeatherApp() {
        super("Weather");
        isOpen = false;
        readImage();
        super.setIcon(icon);
    }

    private void readImage() {
        try {
            icon = ImageIO.read(new File("images/Weather.png"));
            setBounds(icon.getWidth(), icon.getHeight());
        } catch (IOException e) {
            try {
                icon = ImageIO.read(new File("images/placeholder.png"));
                setBounds(icon.getWidth(), icon.getHeight());
            } catch (IOException exception) {
                System.err.println("Error while retrieving file for placeholder image");
            }
            System.err.println("Error while retrieving file for Weather app image");
        }
    }
}
