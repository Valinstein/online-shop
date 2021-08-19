package configLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private String url;
    private String user;
    private String password;

    public ConfigLoader(String path) {
        Properties property = new Properties();

        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(path)) {
            property.load(input);
            this.url = property.getProperty("url");
            this.user = property.getProperty("user");
            this.password = property.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Can't load configuration properties", e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
