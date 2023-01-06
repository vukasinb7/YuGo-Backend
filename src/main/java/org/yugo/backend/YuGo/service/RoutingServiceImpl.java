package org.yugo.backend.YuGo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.yugo.backend.YuGo.dto.RouteProperties;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RoutingServiceImpl implements RoutingService {
    private static final String url = "http://router.project-osrm.org/route/v1/driving/%s,%s;%s,%s?annotations=distance";


    @Override
    public RouteProperties getRouteProperties(double fromLat, double fromLng, double toLat, double toLng) {
        String urlFinal = String.format(url, Double.toString(fromLng), Double.toString(fromLat), Double.toString(toLng), Double.toString(toLat));
        WebClient client = WebClient.create();
        WebClient.ResponseSpec responseSpec = client.get().uri(urlFinal).retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        if(responseBody == null){
            return null;
        }
        double distance = this.getDistanceFromResponse(responseBody);//TODO izmeniti nacin parsisranja respons-a
        double duration = this.getDurationFromResponse(responseBody);//TODO izmeniti nacin parsisranja respons-a
        return new RouteProperties(distance, duration);
    }
    private double getDistanceFromResponse(String responseBody){
        int distanceIndex = responseBody.indexOf("distance:");
        int closingBracesIndex = responseBody.indexOf("}", distanceIndex);
        return Double.parseDouble(responseBody.substring(distanceIndex + 9, closingBracesIndex));
    }
    private double getDurationFromResponse(String responseBody){
        int distanceIndex = responseBody.indexOf("duration:");
        int closingBracesIndex = responseBody.indexOf("}", distanceIndex);
        return Double.parseDouble(responseBody.substring(distanceIndex + 9, closingBracesIndex));
    }
}
