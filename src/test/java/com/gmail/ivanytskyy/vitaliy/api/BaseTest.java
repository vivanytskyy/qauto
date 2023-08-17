package com.gmail.ivanytskyy.vitaliy.api;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.api.antities.request.auth.AuthorizationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.antities.request.auth.RegistrationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.ProfileData;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.UserProfileResponse;
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
    protected UserProfileResponse defaultProfile;

    @BeforeClass
    public void beforeClass(){
        ProfileData data = ProfileData
                .builder()
                .name(TestPropertiesSupplier.getInstance().getProperty("user_first_name"))
                .lastName(TestPropertiesSupplier.getInstance().getProperty("user_last_name"))
                .build();
        defaultProfile = UserProfileResponse
                .builder()
                .status("ok")
                .data(data)
                .build();
    }
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        defaultProfile = null;
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