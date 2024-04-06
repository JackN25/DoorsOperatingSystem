import javax.swing.*;

public class ProgressBarThread implements Runnable {

    private JProgressBar progressBar;
    private Thread thread;

    public ProgressBarThread(JProgressBar progressBar, String name) {
        this.progressBar = progressBar;
        thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        while (progressBar.getValue() < 20) {
            progressBar.setValue(progressBar.getValue() + 1);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (progressBar.getValue() < 60) {
            progressBar.setValue(progressBar.getValue() + 5);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (progressBar.getValue() < progressBar.getMaximum()) {
            progressBar.setValue(progressBar.getValue() + 5);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
