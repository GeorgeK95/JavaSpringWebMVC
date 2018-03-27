package bg.galaxi.nuggets.model.request;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
public class UserRegisterRequestModel {

    private String username;

    private String password;

    private String confirmPassword;

    private String preferences;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
