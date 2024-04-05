import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MainFrameScreen extends JFrame implements Runnable {
    private DrawAppInterface appShower;
    private Thread windowThread;

    public MainFrameScreen(String display) {
        super(display);
        int frameWidth = 500;
        int frameHeight = 500;
        appShower = new DrawAppInterface();
        this.add(appShower);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(600, 100);
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
