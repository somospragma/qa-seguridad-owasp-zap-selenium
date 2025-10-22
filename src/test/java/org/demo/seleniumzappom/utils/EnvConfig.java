package org.demo.seleniumzappom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvConfig {
    private static Properties properties = new Properties();
    
    static {
        try (InputStream input = EnvConfig.class.getResourceAsStream(".env")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Error loading .env file: " + e.getMessage());
        }
    }
    
    public static String getZapProxyHost() {
        return properties.getProperty("ZAP_PROXYHOST", "localhost");
    }
    
    public static int getZapProxyPort() {
        return Integer.parseInt(properties.getProperty("ZAP_PROXYPORT", "8080"));
    }
    
    public static String getZapApiKey() {
        return properties.getProperty("ZAP_API_KEY", "5abdrhung2c4d7o921nt6631l8");
    }
    
    public static String getScanUrl() {
        return properties.getProperty("SCAN_URL", "https://www.demoblaze.com/");
    }
}