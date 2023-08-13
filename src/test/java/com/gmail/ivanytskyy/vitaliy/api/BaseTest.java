package com.gmail.ivanytskyy.vitaliy.api;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.AuthorizationUserCredentialsWrapper;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.RegistrationUserCredentialsWrapper;
import com.gmail.ivanytskyy.vitaliy.utils.CookieHolder;
import com.gmail.ivanytskyy.vitaliy.utils.UserAuthorizationService;
import lombok.Data;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 13/08/2023
 */
public class BaseTest {
    protected String cookie;
    protected FakeUserCredentials credentials;

    @BeforeMethod
    public void authorizeUser(){
        UserAuthorizationService.authorizeUser();
        this.cookie = CookieHolder.getInstance().getCookie();
        this.credentials = new FakeUserCredentials();
    }
    @AfterMethod(alwaysRun = true)
    public void deleteUser(){
        UserAuthorizationService.deleteUser();
        this.cookie = null;
        this.credentials = null;
    }

    @Data
    protected static class FakeUserCredentials{
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String password;
        private final boolean remember;
        private final RegistrationUserCredentialsWrapper registrationPermit;
        private final AuthorizationUserCredentialsWrapper authorizationPermit;
        private FakeUserCredentials(){
            Faker faker = new Faker();
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            email = faker.internet().emailAddress();
            password = faker.internet()
                    .password(8, 15, true, false, true);
            remember = false;
            registrationPermit = RegistrationUserCredentialsWrapper.builder()
                    .name(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .repeatPassword(password)
                    .build();
            authorizationPermit = AuthorizationUserCredentialsWrapper.builder()
                    .email(email)
                    .password(password)
                    .remember(remember)
                    .build();
        }
    }
}