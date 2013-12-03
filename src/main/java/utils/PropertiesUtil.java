package utils;

import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: dragos.serghie
 */
public class PropertiesUtil {

    public static String readPropertiesFile(String propFile, String property) {

        Properties prop = new Properties();

        try {
            // load a properties file
            prop.load(new FileInputStream(propFile));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return prop.getProperty(property);
    }
}
