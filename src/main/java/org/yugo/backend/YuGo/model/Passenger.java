package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.UserRequest;

import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Passenger extends User{
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "passenger_ride")
    @Getter @Setter
    private Set<Ride> rides;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "favourite_paths")
    @Getter @Setter
    private Set<Path> favouritePaths;

    public Passenger(UserRequest userRequest) {
        super(userRequest);
    }

    public Passenger() {
        super();
    }
}
