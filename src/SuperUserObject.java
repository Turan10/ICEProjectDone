public class SuperUserObject extends SuperUser {
    private String usernameOfSuperUser;
    private String passwordOfSuperUser;

    public SuperUserObject(String username, String password) {
        this.usernameOfSuperUser = username;
        this.passwordOfSuperUser = password;
    }

    public String getUsernameOfSuperUser() {
        return usernameOfSuperUser;
    }

    public String getPasswordOfSuperUser() {
        return passwordOfSuperUser;
    }

    public void setUsernameOfSuperUser(String username) {
        this.usernameOfSuperUser = username;
    }

    public void setPasswordOfSuperUser(String password) {
        this.passwordOfSuperUser = password;
    }


}
