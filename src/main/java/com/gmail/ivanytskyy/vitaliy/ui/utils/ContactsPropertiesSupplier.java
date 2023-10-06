package com.gmail.ivanytskyy.vitaliy.ui.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 06/10/2023
 */
public class ContactsPropertiesSupplier {
    private final Properties properties;
    private static ContactsPropertiesSupplier instance = null;
    private static final String PATH_TO_PROPERTIES;
    static {
        PATH_TO_PROPERTIES = new File(
                "src"
                        + File.separator
                        + "main"
                        + File.separator
                        + "resources"
                        + File.separator
                        + "contacts.properties")
                .getAbsolutePath();
    }
    private ContactsPropertiesSupplier(){
        properties = new Properties();
        try {
            properties.load(new FileReader(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ContactsPropertiesSupplier getInstance(){
        if(instance == null){
            instance = new ContactsPropertiesSupplier();
        }
        return instance;
    }
    public String getProperty(String name){
       return Objects.requireNonNull(properties.getProperty(name),
               "End point " + name + " isn't exist");
    }
}