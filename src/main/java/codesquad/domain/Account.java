package codesquad.domain;

import codesquad.web.dto.AccountRegistration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import support.domain.AbstractEntity;

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
public class Account extends AbstractEntity {

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    @Email
    private String userId;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=])(?=\\S+$).{8,}$")
    private String password;

    @Column(nullable = false)
    private String name;

    @Size(max = 50)
    @Column
    @Email
    private String email;

    @Column
    @Pattern(regexp = "^$|^\\d{3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType type = MemberType.MEMBER;

    public Account() {
    }

    public Account(String userId, String password, String name, String email) {
        this(userId, password, name, email, MemberType.MEMBER);

    }

    public Account(String userId, String password, String name, String email, MemberType type) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public Account(Long id, String userId, String password, String name, String email, MemberType type) {
        super(id);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public Account(AccountRegistration accountRegistration) {
        this.userId = accountRegistration.getUserId();
        this.password = accountRegistration.getPassword();
        this.name = accountRegistration.getName();
        this.email = accountRegistration.getEmail();
        this.phoneNumber = accountRegistration.getPhoneNumber();
        this.type = accountRegistration.getType();
    }

    public boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }

    public Account encode(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
        return this;
    }
}
