import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DrawAppInterface extends JPanel implements MouseListener {
    private JProgressBar progressBar;
    private ProgressBarThread progressBarThread;
    private boolean startup = true;
    private boolean displayStartupWords = true;
    private boolean wordsDisplayed = false;
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private App[] apps = {new App("exit"), new App("Notes"), new App("Weather"), new App("Calculator")};

    public DrawAppInterface() {
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //STARTUP DONE
        if (startup) {
            this.setBackground(Color.BLACK);
            paintStartupScreen(g);
            g.setColor(Color.WHITE);
            progressBar = progressBarThread.getProgressBar();
            if (progressBar.getValue() == 100) {
                LOGGER.log(Level.INFO, "Startup finished");
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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else{
            int x = 10;
            int y = 10;
            g.fillRect(0, super.getHeight() - 50, super.getWidth(), 50);
            for (App icon : apps) {
                g.setColor(Color.white);
                if (!icon.getName().equals("exit")) {
                    if (y < super.getHeight() - 200) {
                        if (icon.isHighlighted()) {
                            g.drawRect(x, y, icon.getIcon().getWidth(), icon.getIcon().getHeight());

                        }
                        icon.setIconBoxLocation(x, y);
                        g.drawImage(icon.getIcon(), x, y, null);
                        y = y + icon.getIcon().getHeight() + 10;
                        g.setFont(new Font("Courier New", Font.PLAIN, 9));
                        g.drawString(icon.getName(), x + icon.getIcon().getWidth() / 4, y);
                        y += 50;
                    }
                    if (y >= 800) {
                        x += icon.getIcon().getWidth() + 50;
                        y = 10;
                    }
                } else {
                    g.drawImage(icon.getIcon(), 0, super.getHeight() - 50, null);
                    icon.setIconBoxLocation(0, super.getHeight() - 50);
                }
            }
        }
    }

    private void paintStartupScreen(Graphics g)
    {
        int x = 950;
        int y = 400;
        g.drawImage(readImage("images/door.png"), x, y, null);
        g.setColor(Color.WHITE);
        g.drawString("DOORS OS", 450, 470);
        if (progressBar == null) {
            progressBarThread = new ProgressBarThread(0, 100, 450, 500, 100, 10, "Startup");
            progressBar = progressBarThread.getProgressBar();
            this.add(progressBar);
        }
    }

    private BufferedImage readImage(String filePath) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));
            return bufferedImage;
        }catch (IOException e) {
            System.out.println("L");
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
                //loop through all apps on home screen
                for (int i = 0; i < apps.length; i++) {
                    Rectangle box = apps[i].getBounds();
                    //if app clicked but not highlighted, change the highlight status
                    if (box.contains(clicked) && !apps[i].isHighlighted()) {
                        apps[i].changeHighlighted();
                    }
                    //if app clicked is highlighted, change the highlight status, close home screen, open the app
                    else if (box.contains(clicked) && apps[i].isHighlighted()) {
                        apps[i].changeHighlighted();
                        String appName = apps[i].getName();
                        if (!appName.equals("exit")) {
                            LOGGER.log(Level.INFO, appName + " opened");
                            if (appName.equals("Calculator")) {
                                //TODO: Run Calc
                            } else if (appName.equals("Weather")) {
                                //TODO: Run Weather
                            }
                            if (appName.equals("Notes")) {
                                apps[1].runApp();
                            }
                        } else {
                            LOGGER.log(Level.INFO, "User initiated system shutdown");
                            System.exit(0);
                        }
                    }
                    //if app is not clicked and is highlighted, change highlight status
                    else if (apps[i].isHighlighted()){
                        apps[i].changeHighlighted();
                    }
                }
            }
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
