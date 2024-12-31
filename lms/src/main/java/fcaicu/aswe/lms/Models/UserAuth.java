//package com.example.security_demo.models;
package fcaicu.aswe.lms.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "usertable")
public class UserAuth implements UserDetails {

    @Id
    @Column(name = "UID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment integers
    private int id;

    @Column(name = "Name", length = 30)
    private String name;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "Password", nullable = false, length = 100) // Length increased for hashed passwords
    private String password;

    @Column(name = "Role", nullable = false, length = 8)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "creationadminid")
    private Integer creationAdminId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
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
}
