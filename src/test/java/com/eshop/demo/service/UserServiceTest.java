package com.eshop.demo.service;

import com.eshop.demo.exception.UserAlreadyExists;
import com.eshop.demo.exception.UserNotFound;
import com.eshop.demo.exception.UserNotVerifiedException;
import com.eshop.demo.model.LoginBody;
import com.eshop.demo.model.RegistrationBody;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void testAddUser(){
        RegistrationBody registrationBody = new RegistrationBody(
                "Chris"
                ,"xristos@hotmail.com"
                ,"testPassword"
                ,"Christos"
                ,"Maltezos"
                ,"6980349495"
                ,"06/04/1999"
                ,"Greece"
                ,"Athens"
                ,"Aretis 87 Ilion"
                ,"13122");
        Assertions.assertThrows(UserAlreadyExists.class,()->userService.addUser(registrationBody),"Username or Email already exists");
        registrationBody.setEmail("christos123@gmail.com");
        Assertions.assertDoesNotThrow(()->userService.addUser(registrationBody));
    }
    @Test
    public void testLoginUser(){
        LoginBody loginBody = new LoginBody("ChrisMi","testpass");
        Assertions.assertThrows(UserNotVerifiedException.class,()->userService.loginUser(loginBody),"Wrong username");
        loginBody.setUsername("ChrisMi1");
        loginBody.setPassword("testpass1");
        Assertions.assertThrows(UserNotVerifiedException.class,()->userService.loginUser(loginBody),"Wrong password");
        loginBody.setPassword("testpass");
        Assertions.assertDoesNotThrow(()->userService.loginUser(loginBody));
    }

    @Test
    public void testDeleteUser() throws UserNotFound {
        Assertions.assertThrows(UserNotFound.class,()->userService.deleteUser(4));
        Assertions.assertEquals("The user deleted successfully with id 1",userService.deleteUser(1));
    }
    @Test
    public void testDefineUser(){
        Assertions.assertFalse(userService.defineUser("ChrisMi1"));
    }


}