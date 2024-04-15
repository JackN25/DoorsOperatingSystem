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
    private boolean homescreenOn = false;


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
            homescreenOn = true;
        } else if (homescreenOn) {
            int x = 10;
            int y = 10;
            for (AppIcon icon : homescreen.getAppIcons()) {
                if (y < 800) {
                    if(icon.isHighlighted()) {
                        g.drawRect(x, y, icon.getIcon().getWidth(), icon.getIcon().getHeight());
                    }
                    icon.setIconBoxLocation(x, y);
                    g.drawImage(icon.getIcon(), x, y, null);
                    y = y + icon.getIcon().getHeight() + 10;
                    g.setFont(new Font("Courier New", Font.PLAIN, 9));
                    g.setColor(Color.WHITE);
                    g.drawString(icon.getName(), x + icon.getIcon().getWidth()/4, y);
                    y += 50;
                }
                if (y >= 800) {
                    x += icon.getIcon().getWidth() + 50;
                    y = 10;
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
            if(homescreenOn) {
                for (int i = 0; i < homescreen.getAppIcons().size(); i++) {
                    Rectangle box = homescreen.getAppIcons().get(i).getBounds();
                    if (box.contains(clicked)) {

                    }
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
