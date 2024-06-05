import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Notes {
    private String name;
    private String content;
    private String path;

    public Notes(String name, String path) {
        this.name = name;
        this.path = path;
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

        } catch (IOException e) {

        }
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getPath() {
        return path;
    }

    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Notes note = (Notes) object;
            if (this.name.equals(note.getName())) {
                result = true;
            }
        }
        return result;
    }
}
