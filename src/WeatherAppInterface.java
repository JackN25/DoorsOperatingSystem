import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WeatherAppInterface extends JFrame implements ActionListener {
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String API_KEY = "08cba78ec96ca7cbc57b64a6b0f2b9f9";
    private JTextField textField;
    private static String recentWeather;
    private static String recentTempK;
    private static Double recentTempC;
    private static Double recentTempF;
    private static String recentPressure;
    private static String recentHumidity;
    private JLabel weatherLabel;
    private JLabel temperatureLabel;
    private JLabel temperatureCLabel;
    private JLabel temperatureFLabel;
    private JLabel pressureLabel;
    private JLabel humidityLabel;

    public WeatherAppInterface() {
        super("Weather");
        this.setLayout(new FlowLayout());
        this.setSize(500, 332);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(1000, 100);
        addComponents();
    }

    private void addComponents() {
        JLabel prompt = new JLabel("Enter a city to search the weather for:");
        this.add(prompt);
        textField = new JTextField("Enter a CITY...");
        this.add(textField);
        JButton confirm = new JButton("Submit");
        confirm.addActionListener(this);
        this.add(confirm);
    }

    private void retrieveData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(WeatherAppInterface::parse)
                .join();

    }

    public static void parse(String responseBody) {
        responseBody = responseBody.substring(responseBody.indexOf("\"description\":\"") + 15);
        System.out.println(responseBody);
        recentWeather = responseBody.substring(0, responseBody.indexOf("\""));
        responseBody = responseBody.substring(responseBody.indexOf("\"temp\":") + 7);
        System.out.println(responseBody);
        recentTempK = responseBody.substring(0, responseBody.indexOf(","));
        responseBody = responseBody.substring(responseBody.indexOf("\"pressure\":") + 11);
        recentPressure = responseBody.substring(0, responseBody.indexOf(","));
        responseBody = responseBody.substring(responseBody.indexOf("\"humidity\":") + 11);
        System.out.println(responseBody);
        recentHumidity = responseBody.substring(0, responseBody.indexOf("}"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Submit")) {

            if (weatherLabel != null) {
                this.remove(weatherLabel);
                this.remove(temperatureLabel);
                this.remove(temperatureCLabel);
                this.remove(temperatureFLabel);
                this.remove(pressureLabel);
                this.remove(humidityLabel);
            }

            LOGGER.log(Level.INFO, "Submitted city");
            try {
                retrieveData(BASE_URL + "q=" + textField.getText().replaceAll(" ", "%20") + "&appid=" + API_KEY);
                weatherLabel = new JLabel("The weather now is: " + recentWeather);
                temperatureLabel = new JLabel("The temperature in Kelvins is: " + recentTempK + " K");
                recentTempC = Double.parseDouble(recentTempK) - 273;
                temperatureCLabel = new JLabel("The temperature in Celsius is: " + recentTempC);
                recentTempF = (recentTempC * 9 / 5) + 32;
                temperatureFLabel  = new JLabel("The temperature in Fahrenheit is: " + recentTempF);
                pressureLabel = new JLabel("The pressure is :" + recentPressure + " kPa");
                humidityLabel = new JLabel("The humidity is: " + recentHumidity + "%");
                this.add(weatherLabel);
                this.add(temperatureLabel);
                this.add(temperatureCLabel);
                this.add(temperatureFLabel);
                this.add(pressureLabel);
                this.add(humidityLabel);
                revalidate();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while retrieving data for " + textField.getText());
            }
        }
    }
}
