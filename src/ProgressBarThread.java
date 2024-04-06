import javax.swing.*;

public class ProgressBarThread implements Runnable {

    private JProgressBar progressBar;
    private Thread thread;

    public ProgressBarThread(int min, int max, int x, int y, int width, int height, String name) {
        createProgressBar(min, max, x, y, width, height);
        thread = new Thread(this, name);
        thread.start();
    }

    private void createProgressBar(int min, int max, int x, int y, int width, int height) {
        progressBar = new JProgressBar(min, max);
        progressBar.setBounds(x, y, width, height);
        progressBar.setStringPainted(false);
    }

    @Override
    public void run() {
        while (progressBar.getValue() < 100) {
            while (progressBar.getValue() < 20) {
                progressBar.setValue(progressBar.getValue() + 1);
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.exit(0);
            }
            while (progressBar.getValue() < 70) {
                progressBar.setValue(progressBar.getValue() + 5);

            }
            while (progressBar.getValue()<99) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.exit(0);
                }
                progressBar.setValue(progressBar.getValue() + 5);
            }
            progressBar.setValue(100);
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e){};
        thread.interrupt();
    }

    public int getProgressBarValue() {
        return progressBar.getValue();
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }
}
