package ru.mikhailova.julia.SpringBootApp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username")
//    @NotEmpty(message = "This field should not be empty")
//    private String username;

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

    @Column(name = "email", unique = true)
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Name should not be empty")
    private String username;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    private Integer age;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();




    public User() {

    }

    public User(String name, String lastName, String password, String username, Integer age) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.age = age;
      //  this.email = email;
    }

    public User(String username, String name, String lastName, String password, Integer age, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        //this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setPassword(@NotEmpty(message = "This field should not be empty")
                            String password) {
        this.password = password;
    }
    public void setUsername(@NotEmpty(message = "This field should not be empty") String username) {
        this.username = username;
    }
    public @NotEmpty(message = "Name should not be empty")
            @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
    String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "Name should not be empty")
                            @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
                            String lastName) {
        this.lastName = lastName;
    }


    public @Min(value = 0, message = "Age should be greater than 0")
    Integer getAge() {
        return age;
    }

    public void setAge(@Min(value = 0, message = "Age should be greater than 0")
                       Integer age) {
        this.age = age;
    }

    public @NotEmpty(message = "Name should not be empty")
        @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
        String getName() {
            return name;
    }

    public void setName(@NotEmpty(message = "Name should not be empty") @Size(min = 3, max = 30, message = "Name should be between 2 and 30 chars")
                        String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRolesAsString(Role[] roles) {
        this.roles.addAll(Arrays.asList(roles));
    }
//    public @Email(message = "Email should be valid") @NotEmpty(message = "Name should not be empty") String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@Email(message = "Email should be valid") @NotEmpty(message = "Name should not be empty") String email) {
//        this.email = email;
//    }
}
