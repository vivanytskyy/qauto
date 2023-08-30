package com.gmail.ivanytskyy.vitaliy.api;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.auth.AuthorizationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.auth.RegistrationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.utils.CookieHolder;
import com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService;
import com.gmail.ivanytskyy.vitaliy.utils.TestPropertiesSupplier;
import com.gmail.ivanytskyy.vitaliy.utils.UserAuthorizationService;
import org.testng.annotations.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 13/08/2023
 */
public class BaseTest {
    protected String cookie;
    protected FakeUserCredentials credentials;
    private static final String DEFAULT_FIRST_NAME;
    private static final String DEFAULT_LAST_NAME;
    static {
        DEFAULT_FIRST_NAME = TestPropertiesSupplier.getInstance().getProperty("user_first_name");
        DEFAULT_LAST_NAME = TestPropertiesSupplier.getInstance().getProperty("user_last_name");
    }

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
    protected String getDefaultFirstName(){
        return DEFAULT_FIRST_NAME;
    }
    protected String getDefaultLastName(){
        return DEFAULT_LAST_NAME;
    }
    @lombok.Data
    protected static class FakeUserCredentials{
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String password;
        private final boolean remember;
        private final RegistrationUserCredentials registrationPermit;
        private final AuthorizationUserCredentials authorizationPermit;
        private FakeUserCredentials(){
            Faker faker = new Faker();
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            email = faker.internet().emailAddress();
            password = new PasswordGenerateService.Builder()
                    .useDigits(true)
                    .useUpperCaseLetters(true)
                    .useLowerCaseLetters(true)
                    .build()
                    .generatePassword(8, 15);
            remember = false;
            registrationPermit = RegistrationUserCredentials.builder()
                    .name(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .repeatPassword(password)
                    .build();
            authorizationPermit = AuthorizationUserCredentials.builder()
                    .email(email)
                    .password(password)
                    .remember(remember)
                    .build();
        }
    }
}