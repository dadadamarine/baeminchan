package codesquad.web.dto;

import codesquad.domain.MemberType;
import lombok.Getter;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.*;

@Getter
public class AccountRegistration {

    @NotBlank
    @Email
    private String userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=])(?=\\S+$).{8,}$")
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=])(?=\\S+$).{8,}$")
    private String confirmPassword;

    @NotBlank
    private String name;

    @Pattern(regexp = "^$|^\\d{3}-\\d{3,4}-\\d{4}$")
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

        public AccountRegistration build() {
            return new AccountRegistration(this);
        }
    }

    public AccountRegistration() {
    }

    private AccountRegistration(Builder builder) {
        userId = builder.userId;
        password = builder.password;
        confirmPassword = builder.confirmPassword;
        name = builder.name;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
        type = builder.type;
    }

    public boolean passwordConfirm() {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }
}
