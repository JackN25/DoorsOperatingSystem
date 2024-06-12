import javax.swing.*;

public class MainFrameScreen extends JFrame implements Runnable {
    private DesktopDrawer appShower;
    private Thread windowThread;
    private boolean systemOn = true;
    public MainFrameScreen(String display) {
        super(display);
        int frameWidth = 1920;
        int frameHeight = 1080;
        appShower = new DesktopDrawer();
        this.add(appShower);
        //force fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        //outside border with close, max, and min buttons   true = hide
        this.setUndecorated(true);
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
