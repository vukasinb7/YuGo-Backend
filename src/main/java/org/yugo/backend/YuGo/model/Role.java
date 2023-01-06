package org.yugo.backend.YuGo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;

    @JsonIgnore
    public String getAuthority() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    @JsonIgnore
    public Integer getId() {
        return this.id;
    }
}