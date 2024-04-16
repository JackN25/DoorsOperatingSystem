import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MainFrameScreen extends JFrame implements Runnable {
    private DrawAppInterface appShower;
    private Thread windowThread;
    private boolean systemOn = true;

    public MainFrameScreen(String display) {
        super(display);
        int frameWidth = 1920;
        int frameHeight = 1080;
        appShower = new DrawAppInterface();
        this.add(appShower);
        //force fullscreen
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        //outside border with close, max, and min
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(0, 0);
        this.setVisible(true);
        startThread();
    }

    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }

    @Override
    public void run() {
        while (systemOn) {
            appShower.repaint();
        }
    }
}
