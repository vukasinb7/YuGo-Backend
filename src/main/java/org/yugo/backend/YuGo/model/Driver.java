package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@DiscriminatorValue("2")
public class Driver extends User{
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "documents_id")
    @Getter @Setter
    private Set<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ride_id")
    @Getter @Setter
    private Set<Ride> rides;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @Column(name = "vehicle", nullable = false)
    private Vehicle vehicle;

}
