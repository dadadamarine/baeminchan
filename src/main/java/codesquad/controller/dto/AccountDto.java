package codesquad.controller.dto;

import javax.validation.constraints.NotNull;

public class AccountDto {

    @NotNull String userId;

    @NotNull String password;

    public AccountDto(){

    }

    public AccountDto(String userId, String password) {
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