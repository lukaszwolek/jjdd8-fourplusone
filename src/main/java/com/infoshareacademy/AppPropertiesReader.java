package com.infoshareacademy;
import com.infoshareacademy.Language.LangKeyConfig;
import com.infoshareacademy.Language.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppPropertiesReader {
    private static final Logger stdout = LoggerFactory.getLogger("CONSOLE_OUT");
    private Properties properties = new Properties();

    private final String PATH_NAME = "config.properties";

    private File file = new File(PATH_NAME);

    public String getProp(String config) {
        try {
            FileInputStream fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException e) {
            stdout.info(Language.getMessageByKey(LangKeyConfig.NO_FILE));
        } catch (IOException ex) {
            stdout.info(Language.getMessageByKey(LangKeyConfig.FILE_READING_ERROR));
        }
        return properties.getProperty(config);
    }
}

