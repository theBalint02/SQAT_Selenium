import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConfigLoader {

    private Properties props;

    public ConfigLoader() {
        props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("config.properties not found");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public String getBaseUrl() {
        return props.getProperty("base.url");
    }

    public String getWebDriverRemoteUrl() {
        return props.getProperty("webdriver.remote.url");
    }

    public String getValidEmail() {
        return props.getProperty("valid.email");
    }

    public String getValidPassword() {
        return props.getProperty("valid.password");
    }
}
