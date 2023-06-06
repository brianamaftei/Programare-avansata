package com.example.demo.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Data
@Builder //e design pattern care ajuta in contruirea obiectului intr-un mod mai usor
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "users")
public class User  implements UserDetails {
@Id
@GeneratedValue //automat by default
//@SequenceGenerator(
//
//        name="user_sequence",
//        sequenceName="user_sequence",
//        allocationSize=1
////)
//@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_sequence")
//
    private Long id;
    private String firstname;

    private String lastname;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate dob;

    @Transient
    private Integer age;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

//    public User(Long id, String name, String email, LocalDate dob) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.dob = dob;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDate getDob() {
//        return dob;
//    }
//
//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }
//
//    public Integer getAge() {
//        return Period.between(this.dob, LocalDate.now()).getYears();
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User(String name, String email, LocalDate dob) {
//        this.name = name;
//        this.email = email;
//        this.dob = dob;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", dob=" + dob +
//                ", age=" + age +
//                '}';
//    }
}
