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
    private boolean displayStartupWords = true;
    private boolean wordsDisplayed = false;


    public DrawAppInterface() {
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("images/blackBackground.jpg")), 0, 0, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //STARTUP DONE
        if (startup) {
            paintStartupScreen(g);
            progressBar = progressBarThread.getProgressBar();
            if (progressBar.getValue() == 100) {
                startup = false;
            }
        } else if (displayStartupWords){
            this.remove(progressBar);
            g.setColor(Color.WHITE);
            g.drawString("Open a door to a new world", 450, 500);
            displayStartupWords = false;
            wordsDisplayed = true;
        } else if (wordsDisplayed) {
            wordsDisplayed = false;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
        g.setColor(Color.BLACK);
        if (progressBar == null) {
            progressBarThread = new ProgressBarThread(0, 100, 450, 500, 100, 20, "Startup");
            progressBar = progressBarThread.getProgressBar();
            this.add(progressBar);
        }
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
