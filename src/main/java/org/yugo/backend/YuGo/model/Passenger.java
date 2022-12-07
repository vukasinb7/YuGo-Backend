package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.UserDetailedIn;

import java.util.Set;

@Entity
@NoArgsConstructor
@DiscriminatorValue("1")
public class Passenger extends User{
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "passenger_ride")
    @Getter @Setter
    private Set<Ride> rides;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "favourite_paths")
    @Getter @Setter
    private Set<Path> favouritePaths;

    public Passenger(UserDetailedIn userDetailedIn) {
        super(userDetailedIn);
    }

}
