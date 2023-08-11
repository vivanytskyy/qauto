package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.utils.ApiPropertiesSupplier;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class BaseController {
    protected final OkHttpClient httpClient;
    protected final MediaType mediaType;
    private static final String API_BASE_URL = ApiPropertiesSupplier.getInstance().getProperty("api_base_url");
    public BaseController(){
        httpClient = new OkHttpClient();
        mediaType = MediaType.get("application/json; charset=utf-8");
    }
    protected String getApiBaseUrl(){
        return API_BASE_URL;
    }
}