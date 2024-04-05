import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JPanel implements MouseListener {

    private BufferedImage doorStartup = readDoorImage();

    public HomeScreen() {
        this.addMouseListener(this);

    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
}
