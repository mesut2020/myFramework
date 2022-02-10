package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationWriter {

    public static void setProperty(String configKey, String configValue){
        String path = "configuration.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);  //Reader reader = new FileReader(path);
            Properties properties = new Properties();
            properties.load(fileInputStream); // load current properties file

            FileOutputStream fileOutputStream = new FileOutputStream(path); //Writer writer = new FileWriter(path);
            properties.setProperty(configKey,configValue);
            properties.store(fileOutputStream,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
