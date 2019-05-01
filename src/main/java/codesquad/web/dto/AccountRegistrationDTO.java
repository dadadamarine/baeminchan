package codesquad.web.dto;

import codesquad.domain.AccountType;
import codesquad.validation.ValidationRegexpType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Builder
public class AccountRegistrationDTO {

    @NotBlank
    @Email
    private String userId;

    @NotBlank
    @Pattern(regexp = ValidationRegexpType.PASSWORD)
    private String password;

    @NotBlank
    @Pattern(regexp = ValidationRegexpType.PASSWORD)
    private String confirmPassword;

    @NotBlank
    private String name;

    @Pattern(regexp = ValidationRegexpType.PHONE_NUMBER)
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    @Builder.Default
    private AccountType type = AccountType.MEMBER;

    public static AccountRegistrationDTOBuilder builder(String userId, String password, String confirmPassword, String name) {
        return new AccountRegistrationDTOBuilder()
                .userId(userId)
                .password(password)
                .confirmPassword(confirmPassword)
                .name(name);
    }

    public boolean passwordConfirm() {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }
}
