package codesquad.constraint;

import codesquad.web.dto.AccountRegistrationDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.Assert.*;

public class PasswordConstraintValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void invalidPassword(){
        AccountRegistrationDTO accountRegistrationDTO = new AccountRegistrationDTO();
    }

}