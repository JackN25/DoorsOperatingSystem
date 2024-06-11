import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Notes {
    private String name;
    private String content;
    private String path;

    public Notes(String name, String path) {
        this.name = name;
        this.path = path;
        readFile();
    }

    private void readFile() {
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));
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
