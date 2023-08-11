package com.gmail.ivanytskyy.vitaliy.api;

import com.gmail.ivanytskyy.vitaliy.api.antities.User;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.AuthorizationUserCredentialsWrapper;
import com.gmail.ivanytskyy.vitaliy.api.controllers.AuthController;
import com.gmail.ivanytskyy.vitaliy.api.controllers.UsersController;
import com.gmail.ivanytskyy.vitaliy.utils.CookieHolder;
import com.gmail.ivanytskyy.vitaliy.utils.TestPropertiesSupplier;
import com.gmail.ivanytskyy.vitaliy.utils.UserAuthorizationService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 09/08/2023
 */
public class AuthTest {
    @Test
    public void authorizationTest(){
        UserAuthorizationService.authorize();
        String cookie = CookieHolder.getInstance().getCookie();
        //System.out.println(cookie);
        AuthController controller = new AuthController();
        String firstName = TestPropertiesSupplier.getInstance().getProperty("user_first_name");
        String lastName = TestPropertiesSupplier.getInstance().getProperty("user_last_name");
        String email = TestPropertiesSupplier.getInstance().getProperty("user_email");
        String password = TestPropertiesSupplier.getInstance().getProperty("user_password");
        AuthorizationUserCredentialsWrapper authorizationPermit = AuthorizationUserCredentialsWrapper.builder()
                .email(email)
                .password(password)
                .remember(false)
                .build();
        User user = controller.signIn(authorizationPermit);
        System.out.println(user);
        UsersController usersController = new UsersController(cookie);
        int statusCode = usersController.deleteUser();
        Assert.assertEquals(statusCode, 200);
    }
}
