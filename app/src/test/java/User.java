public class User {
    private String userName ,password, email;

    public User(String userName, String password, String email) {
        this.userName=userName ;
        this.password = password;
        this.email = email;
    }

    public User() {//for firebase
    }

    public String getUserName() {
        return getUserName();
    }

    public void setUserName(String setUserName) {
        this.userName=userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
