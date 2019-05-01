package codesquad.domain;

public enum AccountType {
    MEMBER(false),
    ADMIN(true);

    boolean isAdmin;

    AccountType(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
