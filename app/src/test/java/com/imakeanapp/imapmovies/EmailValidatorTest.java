package com.imakeanapp.imapmovies;

import com.imakeanapp.imapmovies.SignupAndLogin.EmailValidator;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {

    @Test
    public void emailValidation_CorrectEmailSimple_ReturnTrue(){
        assertTrue(EmailValidator.isValidEmail("name@email.com"));
    }

    @Test
    public void emailValidation_CorrectEmailSubdomain_ReturnTrue(){
        assertTrue(EmailValidator.isValidEmail("name@email.com.uk"));
    }

    @Test
    public void emailValidation_InvalidEmailNotId_ReturnFalse(){
        assertFalse(EmailValidator.isValidEmail("name@email"));
    }

    @Test
    public void emailValidation_InvalidEmailDoubleDot_ReturnFalse(){
        assertFalse(EmailValidator.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidation_InvalidEmailNoUserName_ReturnTrue(){
        assertFalse(EmailValidator.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidation_EmptyString_ReturnTrue(){
        assertFalse(EmailValidator.isValidEmail(""));
    }

    @Test
    public void emailValidation_NullEmail_ReturnTrue(){
        assertFalse(EmailValidator.isValidEmail(null));
    }

}

