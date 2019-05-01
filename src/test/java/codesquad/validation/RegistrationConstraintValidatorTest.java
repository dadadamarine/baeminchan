package codesquad.validation;

import codesquad.web.dto.AccountRegistrationDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationConstraintValidatorTest {
    private static final Logger log = LoggerFactory.getLogger(RegistrationConstraintValidatorTest.class);

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void valid() {
        AccountRegistrationDTO registrationDTO = AccountRegistrationDTO.builder("tests@gmail.com",
                "!testT1234",
                "!testT1234",
                "테스트이름")
                .email("test@email.com")
                .phoneNumber("010-0302-0301")
                .build();
        Set<ConstraintViolation<AccountRegistrationDTO>> constraintViolations = validator.validate(registrationDTO);
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void NoUpperCasePassword() {
        AccountRegistrationDTO registrationDTO = AccountRegistrationDTO
                .builder("tests@gmail.com",
                        "!test1234",
                        "!testT1234",
                        "테스트이름")
                .email("test@email.com")
                .phoneNumber("010-0302-0301")
                .build();
        Set<ConstraintViolation<AccountRegistrationDTO>> constraintViolations = validator.validate(registrationDTO);
        assertThat(constraintViolations.size()).isEqualTo(1);

        for (ConstraintViolation<AccountRegistrationDTO> constraintViolation : constraintViolations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }
    }

    @Test
    public void noSpeicialChracterPassword() {
        AccountRegistrationDTO registrationDTO = AccountRegistrationDTO
                .builder("tests@gmail.com",
                        "testT1234",
                        "!testT1234",
                        "테스트이름")
                .email("test@email.com")
                .phoneNumber("010-0302-0301")
                .build();
        Set<ConstraintViolation<AccountRegistrationDTO>> constraintViolations = validator.validate(registrationDTO);
        assertThat(constraintViolations.size()).isEqualTo(1);

        for (ConstraintViolation<AccountRegistrationDTO> constraintViolation : constraintViolations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }
    }

    @Test
    public void Allow_empty_phone_number() {
        AccountRegistrationDTO registrationDTO = AccountRegistrationDTO
                .builder("tests@gmail.com",
                        "!testT1234",
                        "!testT1234",
                        "테스트이름")
                .email("test@email.com")
                .phoneNumber("")
                .build();
        Set<ConstraintViolation<AccountRegistrationDTO>> constraintViolations = validator.validate(registrationDTO);
        assertThat(constraintViolations.size()).isEqualTo(0);

        for (ConstraintViolation<AccountRegistrationDTO> constraintViolation : constraintViolations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }
    }

    @Test
    public void Allow_Null_Phone_Number() {
        AccountRegistrationDTO registrationDTO = AccountRegistrationDTO
                .builder("tests@gmail.com",
                        "!testT1234",
                        "!testT1234",
                        "테스트이름")
                .email("test@email.com")
                .build();
        Set<ConstraintViolation<AccountRegistrationDTO>> constraintViolations = validator.validate(registrationDTO);
        assertThat(constraintViolations.size()).isEqualTo(0);

        for (ConstraintViolation<AccountRegistrationDTO> constraintViolation : constraintViolations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }
    }
}