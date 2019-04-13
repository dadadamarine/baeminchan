package codesquad.web.dto;

import javax.validation.constraints.NotNull;

public class AccountLoginDTO {

    @NotNull String userId;

    @NotNull String password;

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