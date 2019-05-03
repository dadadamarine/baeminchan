package codesquad.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.util.Password;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"support"})
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    private PasswordEncoder encoder= new BCryptPasswordEncoder();

    @Test
    public void saveTest() {
        assertThat(accountRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void findByUserIdTest() {
        assertThat(encoder.matches("!Test1234", accountRepository.findByUserId("test@google.com").get().getPassword())).isEqualTo(true);
    }
}