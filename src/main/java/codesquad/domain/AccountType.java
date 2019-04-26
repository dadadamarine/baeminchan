package codesquad.domain;

public enum AccountType {
    MEMBER(false),
    MANAGER(true);

    boolean isManager;

    AccountType(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isManager() {
        return isManager;
    }
}
