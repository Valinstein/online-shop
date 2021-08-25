package configLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private String url;
    private String user;
    private String password;

    public ConfigLoader(String path) {
        Properties properties = new Properties();

        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(path)) {
            properties.load(input);
            this.url = properties.getProperty("db.url");
            this.user = properties.getProperty("db.user");
            this.password = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException("Can't load configuration properties", e);
        }
    }

    public ConfigLoader() {

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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
