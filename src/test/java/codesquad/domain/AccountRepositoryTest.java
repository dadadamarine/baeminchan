package codesquad.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void saveTest() {
        Account account = new Account("test1@google.com", "!Password1234", "자바지기", "dadadamared@ng.com");
        accountRepository.save(account);
        assertThat(accountRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void findByUserIdTest() {
        assertThat(accountRepository.findByUserId("test@google.com").get().getPassword()).isEqualTo("!Password1234");
    }
}