package codesquad.domain;

import support.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Account extends AbstractEntity {

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    private String userId;

    @Size(min = 6, max = 20)
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
    private Long type = 1l;

    public Account() {
    }

    public Account(String userId, String password, String name, String email) {
        this(userId,password,name,email,1L);

    }

    public Account(String userId, String password, String name, String email, Long type) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public Account(Long id, String userId, String password, String name, String email, Long type) {
        super(id);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
