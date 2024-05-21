import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MainFrameScreen extends JFrame implements Runnable {
    private DrawAppInterface appShower;
    private Thread windowThread;
    private boolean systemOn = true;
    public MainFrameScreen(String display) {
        super(display);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        int frameWidth = 1920;
        int frameHeight = 1080;
        appShower = new DrawAppInterface();
        this.add(appShower);
        //force fullscreen
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        //outside border with close, max, and min buttons   true = hide
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(0, 0);
        this.setVisible(true);
        startThread();
        System.out.println(getGraphics());
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
