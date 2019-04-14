package codesquad.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
    private static final Account TEST_ACCOUNT = new Account();

    public AccountTest() {
        TEST_ACCOUNT.setUserId("test@gmail.com");
        TEST_ACCOUNT.setPassword("password");
    }

    @Test
    public void matchPassword() {
        assertThat(TEST_ACCOUNT.matchPassword("password")).isTrue();
    }
}
