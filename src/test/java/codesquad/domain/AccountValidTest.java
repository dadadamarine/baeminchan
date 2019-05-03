package codesquad.domain;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountValidTest {

    private static final Logger log = LoggerFactory.getLogger(AccountValidTest.class);

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void emptyUserId() {
        Account user = new Account("", "!Ta12345678", "testName", "test@gmail.com");
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(user);
        assertThat(constraintViolations.size()).isEqualTo(1);

        for (ConstraintViolation<Account> constraintViolation : constraintViolations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }
    }
}
