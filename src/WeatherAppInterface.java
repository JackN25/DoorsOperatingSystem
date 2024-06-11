import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class WeatherAppInterface extends JFrame implements ActionListener {

    public WeatherAppInterface() {
        super("Weather");
        this.setLayout(new FlowLayout());
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(1000, 100);
        addComponents();
    }

    private void addComponents() {
        JLabel prompt = new JLabel("Enter a city to search the weather for:");
        this.add(prompt);
        JTextField textField = new JTextField("Enter a CITY...");
        this.add(textField);
        JButton confirm = new JButton("Submit");
        this.add(confirm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Submit")) {

        }
    }
}
