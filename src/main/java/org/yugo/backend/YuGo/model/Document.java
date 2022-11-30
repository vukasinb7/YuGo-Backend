package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Documents")
public class Document {
    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @Getter @Setter
    private Driver driver;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
