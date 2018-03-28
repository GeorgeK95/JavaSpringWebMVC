package bg.galaxi.nuggets.model.pojo;

import java.util.List;

/**
 * Created by George-Lenovo on 27/03/2018.
 */
public class NewUserPOJO {

    private List<String> ids;

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

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
