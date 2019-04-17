package codesquad.validation;

public interface ValidationRegexpType {
    String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=])(?=\\S+$).{8,}$";
    String PHONE_NUMBER ="^$|^\\d{3}-\\d{3,4}-\\d{4}$";
}
