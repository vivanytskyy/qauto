package com.gmail.ivanytskyy.vitaliy.utils;

import com.gmail.ivanytskyy.vitaliy.api.antities.request.auth.AuthorizationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.antities.request.auth.RegistrationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.controllers.AuthController;
import com.gmail.ivanytskyy.vitaliy.api.controllers.UsersController;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class UserAuthorizationService {
    public static void authorizeUser(){
        String firstName = TestPropertiesSupplier.getInstance().getProperty("user_first_name");
        String lastName = TestPropertiesSupplier.getInstance().getProperty("user_last_name");
        String email = TestPropertiesSupplier.getInstance().getProperty("user_email");
        String password = TestPropertiesSupplier.getInstance().getProperty("user_password");
        AuthorizationUserCredentials authorizationPermit = AuthorizationUserCredentials.builder()
                .email(email)
                .password(password)
                .remember(false)
                .build();
        AuthController authController = new AuthController();
        String cookie = authController.getCookie(authorizationPermit );
        if (cookie == null){
            RegistrationUserCredentials registrationPermit = RegistrationUserCredentials
                    .builder()
                    .name(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .repeatPassword(password)
                    .build();
            authController.signUp(registrationPermit);
            cookie = authController.getCookie(authorizationPermit );
        }
        CookieHolder.getInstance().setCookie(cookie);
    }
    public static void deleteUser(){
        String cookie = CookieHolder.getInstance().getCookie();
        UsersController usersController = new UsersController(cookie);
        usersController.deleteUser();
    }
}