package codesquad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaeminchanApplicationTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String password = "aksjbfefbd";
        System.out.println(passwordEncoder.encode(password));
    }

}