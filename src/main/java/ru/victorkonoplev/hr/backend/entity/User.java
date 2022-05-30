package ru.victorkonoplev.hr.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user", schema = "public")
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() WHERE id=?")
public class User implements UserDetails {
    private static String ROLE_PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(value = "full_name")
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;
    @Column(name = "mobile_phone", unique = true, updatable = false, nullable = false)
    @JsonProperty(value = "mobile_phone")
    private String mobilePhone;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    @Getter(value = AccessLevel.NONE)
    private String password;
    @Column(name = "created_at")
    @JsonIgnore
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @JsonIgnore
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    @JsonIgnore
    private LocalDateTime deletedAt;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vacancy> vacancies;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return list;
    }

    @Override
    public String getUsername() {
        return this.mobilePhone;
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
        return !(this.deletedAt == null);
    }
}
