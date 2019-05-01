package codesquad.domain;

import support.domain.AbstractEntity;
import codesquad.validation.ValidationRegexpType;
import codesquad.web.dto.AccountRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends AbstractEntity {

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    @Email
    private String userId;

    @Column(nullable = false)
    @Pattern(regexp = ValidationRegexpType.PASSWORD)
    private String password;

    @Column(nullable = false)
    private String name;

    @Size(max = 50)
    @Column
    @Email
    private String email;

    @Column
    @Pattern(regexp = ValidationRegexpType.PHONE_NUMBER)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType type = AccountType.MEMBER;

    public Account(String userId, String password, String name, String email) {
        this(userId, password, name, email, AccountType.MEMBER);

    }

    public Account(String userId, String password, String name, String email, AccountType type) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public Account(Long id, String userId, String password, String name, String email, AccountType type) {
        super(id);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public Account(AccountRegistrationDTO accountRegistrationDTO) {
        this.userId = accountRegistrationDTO.getUserId();
        this.password = accountRegistrationDTO.getPassword();
        this.name = accountRegistrationDTO.getName();
        this.email = accountRegistrationDTO.getEmail();
        this.phoneNumber = accountRegistrationDTO.getPhoneNumber();
        this.type = accountRegistrationDTO.getType();
    }

    public boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }

    public Account encode(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
        return this;
    }

}
