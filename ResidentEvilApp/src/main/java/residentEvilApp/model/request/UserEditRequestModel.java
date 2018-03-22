package residentEvilApp.model.request;

import residentEvilApp.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
public class UserEditRequestModel {

    private Long id;

    private String username;

    private String password;

    private Boolean user;

    private Boolean moderator;

    private Boolean admin;

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

    public Boolean getUser() {
        return user;
    }

    public void setUser(Boolean user) {
        this.user = user;
    }

    public Boolean getModerator() {
        return moderator;
    }

    public void setModerator(Boolean moderator) {
        this.moderator = moderator;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
