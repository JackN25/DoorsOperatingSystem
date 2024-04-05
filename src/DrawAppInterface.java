import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawAppInterface extends JPanel implements MouseListener {

    private HomeScreen homescreen = new HomeScreen();

    public DrawAppInterface() {
        this.addMouseListener(this);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 950;
        int y = 400;
        try {
            g.drawImage(ImageIO.read(new File("images/blackBackground.jpg")), 0, 0, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(homescreen.getDoorStartup(), x, y, null);
        g.setColor(Color.WHITE);
        g.drawString("DOORS OS", 950, 800);
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
}
