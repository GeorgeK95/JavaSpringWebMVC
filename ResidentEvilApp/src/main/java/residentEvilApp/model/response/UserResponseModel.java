package residentEvilApp.model.response;

/**
 * Created by George-Lenovo on 21/03/2018.
 */
public class UserResponseModel {

    private Long id;

    private String email;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
