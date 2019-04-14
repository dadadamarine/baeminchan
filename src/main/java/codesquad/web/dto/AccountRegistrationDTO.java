package codesquad.web.dto;

import codesquad.constraint.ValidPassword;
import codesquad.domain.MemberType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountRegistrationDTO {

    @NotEmpty
    private final String userId;

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
    private MemberType type;
    
    public static class Builder {
        private final String userId;
        private final String password;
        private final String confirmPassword;
        private final String name;

        private String phoneNumber;
        private String email;
        private MemberType type = MemberType.MEMBER;

        public Builder(String userId, String password, String confirmPassword, String name) {
            this.userId = userId;
            this.password = password;
            this.confirmPassword = confirmPassword;
            this.name = name;
        }

        public Builder phoneNumber(String value) {
            phoneNumber = value;
            return this;
        }

        public Builder email(String value) {
            email = value;
            return this;
        }

        public Builder type(MemberType memberType) {
            type = memberType;
            return this;
        }

        public AccountRegistrationDTO build() {
            return new AccountRegistrationDTO(this);
        }
    }

    private AccountRegistrationDTO(Builder builder) {
        userId = builder.userId;
        password = builder.password;
        confirmPassword = builder.confirmPassword;
        name = builder.name;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
        type = builder.type;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public MemberType getType() {
        return type;
    }

    public boolean passwordConfirm() {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }
}
