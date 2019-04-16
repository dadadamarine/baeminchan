package codesquad.service;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.exception.account.CannotRegistrationException;
import codesquad.web.dto.AccountRegistration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static final String ENCODED_PASSWORD = "encodedpassword";
    private static final String TEST_USERID = "test@gmail.com";
    private static final String TEST_PASSWORD = "!Test1234";

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void saveTest() {
        AccountRegistration accountRegistration =
                new AccountRegistration.Builder(TEST_USERID, TEST_PASSWORD, TEST_PASSWORD, "testname").build();
        Account account = new Account(accountRegistration);
        Account mockSavedAccount = new Account(accountRegistration);
        mockSavedAccount.setPassword(ENCODED_PASSWORD);
        when(accountRepository.save(account)).thenReturn(mockSavedAccount);
        when(passwordEncoder.encode(account.getPassword())).thenReturn(ENCODED_PASSWORD);

        Account savedAccount = accountService.save(accountRegistration);

        assertThat(savedAccount.getPassword()).isEqualTo(ENCODED_PASSWORD);
    }

    @Test(expected = CannotRegistrationException.class)
    public void save_unmatch_password_test() {
        AccountRegistration accountRegistration =
                new AccountRegistration.Builder(TEST_USERID, TEST_PASSWORD, "!Ttest1234", "testname").build();

        Account savedAccount = accountService.save(accountRegistration);
    }

}