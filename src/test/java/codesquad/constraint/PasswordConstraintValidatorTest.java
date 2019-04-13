package codesquad.constraint;

import codesquad.web.dto.AccountRegistrationDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordConstraintValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void NoUpperCasePassword() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO
                .Builder("tests@gmail.com",
                "!test1234",
                "!testT1234",
                "테스트이름")
                .email("test@email.com")
                .phoneNumber("010-0302-0301")
                .build();
        Set<ConstraintViolation<AccountRegistrationDTO>> constraintViolations = validator.validate(dto);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void noSpeicialChracterPassword() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO
                .Builder("tests@gmail.com",
                "test1234",
                "!testT1234",
                "테스트이름")
                .email("test@email.com")
                .phoneNumber("010-0302-0301")
                .build();
        Set<ConstraintViolation<AccountRegistrationDTO>> constraintViolations = validator.validate(dto);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}