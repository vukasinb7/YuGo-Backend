package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RideIn {

    @Getter @Setter
    private List<PathInOut> locations;

    @Getter @Setter
    private List<UserSimplifiedOut> passengers;

    @Getter @Setter
    private String vehicleType;

    @Getter @Setter
    private boolean babyTransport;

    @Getter @Setter
    private boolean petTransport;

}
