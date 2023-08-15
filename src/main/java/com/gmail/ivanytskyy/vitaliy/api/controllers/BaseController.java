package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.utils.ApiPropertiesSupplier;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public abstract class BaseController {
    private static final String API_BASE_URL = ApiPropertiesSupplier.getInstance().getProperty("api_base_url");
    protected String getApiBaseUrl(){
        return API_BASE_URL;
    }
}