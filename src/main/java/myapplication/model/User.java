package myapplication.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * UserDetails предоставляет необходимую информацию для построения объекта Authentication из
 * DAO объектов приложения или других источников данных системы безопасности.
 * Объект UserDetails содержит имя пользователя, пароль, флаги: isAccountNonExpired, isAccountNonLocked,
 * isCredentialsNonExpired, isEnabled и Collection — прав (ролей) пользователя.
 */

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinTable(name = "permission",
            joinColumns = {@JoinColumn(name = "id_name")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")})
    private Set<Role> roles;

    public User() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
