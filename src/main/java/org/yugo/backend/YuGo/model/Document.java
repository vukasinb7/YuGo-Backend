package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "Documents")
public class Document {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "image", nullable = false)
    private String image;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Document(String name, String image, Driver driver) {
        this.name = name;
        this.image = image;
        this.driver = driver;
    }
}
