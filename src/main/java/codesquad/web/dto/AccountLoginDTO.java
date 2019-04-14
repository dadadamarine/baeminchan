package codesquad.web.dto;

import codesquad.constraint.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountLoginDTO {

    @NotEmpty
    String userId;

    @NotEmpty
    String password;

    public AccountLoginDTO(){

    }

    public AccountLoginDTO(String userId, String password) {
        this.userId = userId;
        this.userId = password;
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