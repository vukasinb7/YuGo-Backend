package org.yugo.backend.YuGo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.yugo.backend.YuGo.dto.UserDetailedIn;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name="Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class User implements UserDetails {
    @Column(name = "user_type", insertable = false, updatable = false, nullable = false)
    private String role;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;
    @Column(name = "telephone_number", nullable = false)
    private String telephoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )}
    )
    private List<Role> roles;
    @Column(
            name = "last_password_reset_date"
    )
    private Timestamp lastPasswordResetDate;

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp((new Date()).getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public boolean isEnabled() {
        return this.isActive;
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public User(UserDetailedIn userDetailedIn) {
        this.name = userDetailedIn.getName();
        this.surname = userDetailedIn.getSurname();
        this.profilePicture = userDetailedIn.getProfilePicture();
        this.telephoneNumber = userDetailedIn.getTelephoneNumber();
        this.email = userDetailedIn.getEmail();
        this.address = userDetailedIn.getAddress();
        this.password = userDetailedIn.getPassword();
        this.isBlocked = false;
        this.isActive = false;
    }
}
