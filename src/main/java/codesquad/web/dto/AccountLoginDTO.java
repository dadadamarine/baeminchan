package codesquad.web.dto;

import codesquad.constraint.ValidPassword;

import javax.validation.constraints.NotEmpty;

public class AccountLoginDTO {

    @NotEmpty
    String userId;

    @NotEmpty
    @ValidPassword
    String password;

    public AccountLoginDTO() {

    }

    public AccountLoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
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
}