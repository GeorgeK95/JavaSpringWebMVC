package residentEvilApp.model.request;

/**
 * Created by George-Lenovo on 22/03/2018.
 */
public class UserDeleteRequestModel {

    private Long id;

    private String email;

    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
