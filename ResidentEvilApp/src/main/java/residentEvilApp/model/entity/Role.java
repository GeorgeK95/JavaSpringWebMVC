package residentEvilApp.model.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    /*@ManyToMany(mappedBy = "authorities")
    private Set<User> users;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Role() {
//        this.users = new HashSet<>();
    }

    public Role(String authority) {
//        this();
        this.authority = authority;
    }

    /*public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }*/

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
