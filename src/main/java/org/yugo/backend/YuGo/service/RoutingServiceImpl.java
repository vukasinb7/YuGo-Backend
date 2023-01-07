package org.yugo.backend.YuGo.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.yugo.backend.YuGo.dto.RouteProperties;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RoutingServiceImpl implements RoutingService {
    private static final String url = "http://router.project-osrm.org/route/v1/driving/%s,%s;%s,%s?annotations=distance&overview=false";


    @Override
    public RouteProperties getRouteProperties(double fromLat, double fromLng, double toLat, double toLng) {
        String urlFinal = String.format(url, Double.toString(fromLng), Double.toString(fromLat), Double.toString(toLng), Double.toString(toLat));
        WebClient client = WebClient.create();
        WebClient.ResponseSpec responseSpec = client.get().uri(urlFinal).retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        if(responseBody == null){
            return null;
        }
        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONArray routes = jsonResponse.getJSONArray("routes");
        JSONObject route = routes.getJSONObject(0);
        double distance = route.getDouble("distance");
        double duration = route.getDouble("duration");
        return new RouteProperties(distance, duration);
    }
}
