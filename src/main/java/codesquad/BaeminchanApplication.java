package codesquad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BaeminchanApplication {

    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(BaeminchanApplication.class, args);
    }
}
