package com.gmail.ivanytskyy.vitaliy.utils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 30/06/2023
 */
public final class TestPropertiesSupplier {
    private final Properties properties;
    private static TestPropertiesSupplier instance = null;
    private static final String PATH_TO_PROPERTIES;
    static {
        PATH_TO_PROPERTIES = new File(
                "src"
                        + File.separator
                        + "test"
                        + File.separator
                        + "resources"
                        + File.separator
                        + "test.properties")
                .getAbsolutePath();
    }
    private TestPropertiesSupplier(){
        this.properties = new Properties();
        try {
            this.properties.load(new FileReader(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static TestPropertiesSupplier getInstance(){
        if(instance == null){
            instance = new TestPropertiesSupplier();
        }
        return instance;
    }
    public String getProperty(String propertyName){
        return Objects.requireNonNull(properties.getProperty(propertyName),
                "Property " + propertyName + " isn't exist");
    }
    public void setProperties(String propertyName, String propertyValue) throws IOException {
        properties.setProperty(propertyName, propertyValue);
        properties.store(new FileWriter(PATH_TO_PROPERTIES),
                "Added property " + propertyName);
    }
}