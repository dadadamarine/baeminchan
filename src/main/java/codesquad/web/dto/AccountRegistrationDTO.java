package codesquad.web.dto;

import codesquad.domain.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@Builder
public class AccountRegistrationDTO {

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
    @lombok.Builder.Default
    private MemberType type = MemberType.MEMBER;

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
