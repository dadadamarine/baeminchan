package codesquad.web.dto;

import codesquad.validation.ValidationRegexpType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountLoginDTO {

    @NotBlank
    @Email
    String userId;

    @NotBlank
    @Pattern(regexp = ValidationRegexpType.PASSWORD)
    String password;

    public AccountLoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}