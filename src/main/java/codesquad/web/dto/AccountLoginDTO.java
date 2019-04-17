package codesquad.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AccountLoginDTO {

    @NotBlank
    @Email
    String userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=])(?=\\S+$).{8,}$")
    String password;

    public AccountLoginDTO() {
    }

    public AccountLoginDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}