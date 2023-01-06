package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.dto.RouteProperties;

public interface RoutingService {
    RouteProperties getRouteProperties(double fromLat, double fromLng, double toLat, double toLng);
}
