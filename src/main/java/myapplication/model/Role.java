package myapplication.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 *GrantedAuthority отражает разрешения выданные пользователю в масштабе всего приложения,
 * такие разрешения (как правило называются «роли»), например  Role USER, Role ADMIN.
 */

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinTable(name = "permission",
            joinColumns = {@JoinColumn(name = "id_role")},
            inverseJoinColumns = {@JoinColumn(name = "id_name")})
    private List<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return name.equals(role.name);
    }
}
