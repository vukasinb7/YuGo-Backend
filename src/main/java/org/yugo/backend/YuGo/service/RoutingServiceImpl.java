package org.yugo.backend.YuGo.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.client.RestTemplate;
import org.yugo.backend.YuGo.dto.RouteProperties;

@Service
public class RoutingServiceImpl implements RoutingService {
    private static final String osrmUrl = "http://router.project-osrm.org/route/v1/driving/%s,%s;%s,%s?annotations=distance&overview=false";
    private static final String reverseSearchUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat=%s&lon=%s";

    @Override
    public String reverseAddressSearch(double lat, double lng){
        String url = String.format(reverseSearchUrl, Double.toString(lat), Double.toString(lng));
        String responseBody = sendRequest(url);
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getString("display_name");
    }
    @Override
    public RouteProperties getRouteProperties(double fromLat, double fromLng, double toLat, double toLng) {
        String url = String.format(osrmUrl, Double.toString(fromLng), Double.toString(fromLat), Double.toString(toLng), Double.toString(toLat));
        String responseBody = sendRequest(url);
        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONArray routes = jsonResponse.getJSONArray("routes");
        JSONObject route = routes.getJSONObject(0);
        double distance = route.getDouble("distance");
        double duration = route.getDouble("duration");
        return new RouteProperties(distance, duration);
    }

    private String sendRequest(String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result =
                restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return (String) result.getBody();
    }
}
