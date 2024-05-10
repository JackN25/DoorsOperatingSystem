import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DrawAppInterface extends JPanel implements MouseListener {

    private HomeScreen homescreen = new HomeScreen();
    private JProgressBar progressBar;
    private ProgressBarThread progressBarThread;
    private boolean startup = true;
    private boolean displayStartupWords = true;
    private boolean wordsDisplayed = false;
    private boolean homescreenOn = false;
    private NotesApp notesApp = new NotesApp();
    private boolean notesOn = false;
    private boolean calculatorOn = false;
    private WeatherApp weatherApp = new WeatherApp();
    private boolean weatherAppOn = false;
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public DrawAppInterface() {
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //STARTUP DONE
        if (startup) {
            this.setBackground(Color.BLACK);
            paintStartupScreen(g);
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
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else{
            int x = 10;
            int y = 10;
            g.setColor(Color.WHITE);
            g.fillRect(0, super.getHeight() - 50, super.getWidth(), 50);
            g.setColor(Color.BLACK);
            for (App icon : homescreen.getAppIcons()) {
                if (!icon.getName().equals("exit")) {
                    if (y < super.getHeight() - 200) {
                        if (icon.isHighlighted()) {
                            g.setColor(Color.WHITE);
                            g.drawRect(x, y, icon.getIcon().getWidth(), icon.getIcon().getHeight());
                            g.setColor(Color.BLACK);

                        }
                        icon.setIconBoxLocation(x, y);
                        g.drawImage(icon.getIcon(), x, y, null);
                        y = y + icon.getIcon().getHeight() + 10;
                        g.setFont(new Font("Courier New", Font.PLAIN, 9));
                        g.setColor(Color.WHITE);
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
        try {
            g.drawImage(ImageIO.read(new File("images/blackBackground.png")), 0, 0, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(homescreen.getDoorStartup(), x, y, null);
        g.setColor(Color.WHITE);
        g.drawString("DOORS OS", 450, 470);
        g.setColor(Color.BLACK);
        if (progressBar == null) {
            progressBarThread = new ProgressBarThread(0, 100, 450, 500, 100, 10, "Startup");
            progressBar = progressBarThread.getProgressBar();
            this.add(progressBar);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
                //loop through all apps on home screen
                for (int i = 0; i < homescreen.getAppIcons().size(); i++) {
                    Rectangle box = homescreen.getAppIcons().get(i).getBounds();
                    //if app clicked but not highlighted, change the highlight status
                    if (box.contains(clicked) && !homescreen.getAppIcons().get(i).isHighlighted()) {
                        homescreen.getAppIcons().get(i).changeHighlighted();
                    }
                    //if app clicked is highlighted, change the highlight status, close home screen, open the app
                    else if (box.contains(clicked) && homescreen.getAppIcons().get(i).isHighlighted()) {
                        homescreen.getAppIcons().get(i).changeHighlighted();
                        changeHomeScreenStatus();
                        String appName = homescreen.getAppIcons().get(i).getName();
                        if (!appName.equals("exit")) {
                            LOGGER.log(Level.INFO, appName + " opened");
                            if (appName.equals("Calculator")) {
                                calculatorOn = true;
                            } else if (appName.equals("Weather")) {
                                weatherAppOn = true;
                            }
                        } else {
                            LOGGER.log(Level.INFO, "User initiated system shutdown");
                            System.exit(0);
                        }
                    }
                    //if app is not clicked and is highlighted, change highlight status
                    else if (homescreen.getAppIcons().get(i).isHighlighted()){
                        homescreen.getAppIcons().get(i).changeHighlighted();
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

    private void changeHomeScreenStatus() {
        homescreenOn = !homescreenOn;
        if (homescreenOn) {
            LOGGER.log(Level.INFO, "Home screen turned on");
        } else {
            LOGGER.log(Level.INFO, "Home screen turned off");
        }
    }
}
