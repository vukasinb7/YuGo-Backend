package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.dto.RouteProperties;

public interface RoutingService {
    String reverseAddressSearch(double lat, double lng);

    RouteProperties getRouteProperties(double fromLat, double fromLng, double toLat, double toLng);
}
