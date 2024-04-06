import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class DrawAppInterface extends JPanel implements MouseListener {

    private HomeScreen homescreen = new HomeScreen();
    private JProgressBar progressBar;
    private ProgressBarThread progressBarThread;
    private boolean startup = true;


    public DrawAppInterface() {
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        if (startup) {
            paintStartupScreen(g);
            if (progressBar.getValue() == 100) {
                this.remove(progressBar);
                startup = false;
            }
        } else {
            super.paintComponent(g);
        }
    }

    private void paintStartupScreen(Graphics g)
    {
        int x = 950;
        int y = 400;
        try {
            g.drawImage(ImageIO.read(new File("images/blackBackground.jpg")), 0, 0, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //g.drawImage(homescreen.getDoorStartup(), x, y, null);
        g.setColor(Color.WHITE);
        g.drawString("DOORS OS", 450, 470);
        createProgressBar(0, 100, 450 ,500, 100, 20);
        progressBarThread = new ProgressBarThread(progressBar, "Startup");
        this.add(progressBar);
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

    private void createProgressBar(int min, int max, int x, int y, int width, int height) {
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(x, y, width, height);
        progressBar.setStringPainted(false);
        progressBar.setValue(0);
    }

}
