package configLoader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    private static final String APPLICATION_PROPERTIES = "application.properties";

    private ConfigLoader configLoader;

    @BeforeEach
    void init(){
        configLoader = new ConfigLoader(APPLICATION_PROPERTIES);
    }

    @DisplayName("Url from properties")
    @Test
    void getUrl() {
        assertEquals(configLoader.getUrl(), "jdbc:postgresql://localhost:5432/shop");
    }

    @DisplayName("UserName from properties")
    @Test
    void getUser() {
        assertEquals(configLoader.getUser(), "postgres");
    }

    @DisplayName("Password from properties")
    @Test
    void getPassword() {
        assertEquals(configLoader.getPassword(), "postgres");
    }
}