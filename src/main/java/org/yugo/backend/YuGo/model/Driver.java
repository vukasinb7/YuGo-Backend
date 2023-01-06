package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.UserDetailedIn;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
@DiscriminatorValue("DRIVER")
public class Driver extends User{
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Set<Document> documents;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH,mappedBy = "driver")
    private Set<Ride> rides;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Driver(UserDetailedIn userDetailedIn) {
        super(userDetailedIn);
    }
}
