import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NotesApp extends App {
    private boolean isOpen = false;

    private BufferedImage icon;

    public NotesApp() {
        super("Notes");
        readImage();
        super.setIcon(icon);
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
