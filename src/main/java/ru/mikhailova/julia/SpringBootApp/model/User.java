package ru.mikhailova.julia.SpringBootApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should not be empty")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "This field should not be empty")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
    private String lastName;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    // constructors
    public User() {}

    public User(String name, String lastName, String password, String username, Integer age) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.age = age;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "This field should not be empty")
                            String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "This field should not be empty")
                            String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name should not be empty")
                        @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
                        String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "Name should not be empty")
                            @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
                            String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(@Min(value = 0, message = "Age should be greater than 0")
                       Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // UserDetails methods
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    // for UserDetailsService
    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isEnabled(), user.isAccountNonLocked(),
                user.getRoles());
    }
}
