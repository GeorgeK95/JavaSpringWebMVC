package bg.galaxi.nuggets.model.pojo;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
public class NewUserPOJO {

    private String username;

    private String preferences;

    public NewUserPOJO(String username, String preferences) {
        this.username = username;
        this.preferences = preferences;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

}