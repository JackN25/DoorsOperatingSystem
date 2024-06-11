import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WeatherAppInterface extends JFrame implements ActionListener {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String API_KEY = "08cba78ec96ca7cbc57b64a6b0f2b9f9";
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private JTextField textField;

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

    public static String parse(String responseBody) {
        responseBody = responseBody.substring(responseBody.indexOf("\"description\":\"") + 15);
        return responseBody.substring(0, responseBody.indexOf("\""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Submit")) {
            LOGGER.log(Level.INFO, "Submitted city");
            retrieveData(BASE_URL+"q="+textField.getText().replaceAll(" ", "%20")+"&appid="+API_KEY);
        }
    }
}
