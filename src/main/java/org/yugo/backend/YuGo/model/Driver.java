package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.UserRequest;
import org.yugo.backend.YuGo.dto.UserResponse;

import java.util.Set;

@Entity
@NoArgsConstructor
@DiscriminatorValue("2")
public class Driver extends User{
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    @Getter @Setter
    private Set<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ride_id")
    @Getter @Setter
    private Set<Ride> rides;

    @Getter @Setter
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Driver(UserRequest userRequest) {
        super(userRequest);
    }
}
