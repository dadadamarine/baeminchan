package codesquad.domain;

import codesquad.web.dto.AccountRegistrationDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import support.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Account extends AbstractEntity {

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Size(max = 50)
    @Column
    private String email;

    @Size(min = 12, max = 13)
    @Column
    private String phoneNumber;

    @Column(nullable = false)
    private MemberType type = MemberType.MEMBER;

    public Account() {
    }

    public Account(String userId, String password, String name, String email) {
        this(userId,password,name,email, MemberType.MEMBER);

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

    public Account(AccountRegistrationDTO accountRegistrationDTO) {
        this.userId = accountRegistrationDTO.getUserId();
        this.password = accountRegistrationDTO.getPassword();
        this.name = accountRegistrationDTO.getName();
        this.email = accountRegistrationDTO.getEmail();
        this.phoneNumber = accountRegistrationDTO.getPhoneNumber();
        this.type = accountRegistrationDTO.getType();
    }

    public boolean matchPassword(String targetPassword){
        return password.equals(targetPassword);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account encode(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
        return this;
    }
}
