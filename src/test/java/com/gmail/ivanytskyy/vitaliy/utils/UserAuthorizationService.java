package com.gmail.ivanytskyy.vitaliy.utils;

import com.gmail.ivanytskyy.vitaliy.api.antities.auth.AuthorizationUserCredentialsWrapper;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.RegistrationUserCredentialsWrapper;
import com.gmail.ivanytskyy.vitaliy.api.controllers.AuthController;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class UserAuthorizationService {
    public static void authorize(){
        String firstName = TestPropertiesSupplier.getInstance().getProperty("user_first_name");
        String lastName = TestPropertiesSupplier.getInstance().getProperty("user_last_name");
        String email = TestPropertiesSupplier.getInstance().getProperty("user_email");
        String password = TestPropertiesSupplier.getInstance().getProperty("user_password");
        RegistrationUserCredentialsWrapper registrationPermit = RegistrationUserCredentialsWrapper
                .builder()
                .name(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .repeatPassword(password)
                .build();
        AuthorizationUserCredentialsWrapper authorizationPermit = AuthorizationUserCredentialsWrapper.builder()
                .email(email)
                .password(password)
                .remember(false)
                .build();
        AuthController authController = new AuthController();
        String cookie = authController.getCookie(authorizationPermit );
        if (cookie == null){
            cookie = authController.getCookie(registrationPermit);
        }
        CookieHolder.getInstance().setCookie(cookie);
    }
}