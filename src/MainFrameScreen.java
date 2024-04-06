import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MainFrameScreen extends JFrame implements Runnable {
    private DrawAppInterface appShower;
    private Thread windowThread;

    public MainFrameScreen(String display) {
        super(display);
        int frameWidth = 1000;
        int frameHeight = 1000;
        appShower = new DrawAppInterface();
        this.add(appShower);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(200, 200);
        this.setVisible(true);
        startThread();
    }

    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }

    @Override
    public void run() {
        while (true) {
            appShower.repaint();
        }
    }
}
