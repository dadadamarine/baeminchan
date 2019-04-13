package codesquad.web.dto;

import codesquad.constraint.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountRegistrationDTO {

    @NotEmpty
    private String userId;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotEmpty
    @ValidPassword
    private String confirmPassword;

    @NotEmpty
    private String name;

    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    private Long type;


    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder{
        // Required parameters(필수 인자)
        private final String userId;
        private final String password;
        private final String confirmPassword;
        private final String name;

        // Optional parameters
        private String phoneNumber;
        private String email;
        private Long type = 1l;

        public Builder(String userId, String password, String confirmPassword, String name) {
            this.userId = userId;
            this.password = password;
            this.confirmPassword = confirmPassword;
            this.name = name;
        }

        public Builder phoneNumber(String value){
            phoneNumber = value;
            return this;
        }

        public Builder email(String value){
            email = value;
            return this;
        }

        public Builder type(Long value){
            type = value;
            return this;
        }

        public AccountRegistrationDTO build(){
            return new AccountRegistrationDTO(this);
        }
    }

    private AccountRegistrationDTO(Builder builder){
        userId = builder.userId;
        password = builder.password;
        confirmPassword = builder.confirmPassword;
        name = builder.name;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
        type = builder.type;
    }
}
