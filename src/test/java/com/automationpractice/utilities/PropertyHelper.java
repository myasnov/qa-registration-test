package com.automationpractice.utilities;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class PropertyHelper {
    private static final String CONFIG_PROPS_FILE_PATH = System.getProperty("user.dir") +
            "\\src\\test\\resources\\testdata\\config.properties";
    private static PropertyHelper instance;
    private String siteUrl;
    private String chromeWebDriverPath;
    private int timeout;
    private String jsonInvData;
    private String jsonValidData;


    public static synchronized PropertyHelper getInstance() {
        if (instance == null) {
            instance = new PropertyHelper();
            instance.loadProperties();
        }
        return instance;
    }

    private void loadProperties() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(CONFIG_PROPS_FILE_PATH));
        } catch (IOException e) {
            System.out.println("Error. Configuration file cannot be found.");
        }

        siteUrl = props.getProperty("siteUrl");
        chromeWebDriverPath = System.getProperty("user.dir") + props.getProperty("chromeWebDriverPath");
        timeout = Integer.parseInt(props.getProperty("timeout"));
        jsonInvData = props.getProperty("jsonDataInvalid");
        jsonValidData = props.getProperty("jsonDataValid");
    }

}
