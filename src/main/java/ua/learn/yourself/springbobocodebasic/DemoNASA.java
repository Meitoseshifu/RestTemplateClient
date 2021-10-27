package ua.learn.yourself.springbobocodebasic;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class DemoNASA {

    private static final String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";
    //private static final String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=DEMO_KEY";

    public static void main(String[] args) {
        approachOne();
        approachTwo();
        approachThree();
        findBiggestPhoto();
    }

    public static void approachOne() { //todo RestTemplate + jackson
        new RestTemplate().getForObject(URL, JsonNode.class).get("photos")
                .forEach(it -> System.out.println(it.get("img_src")));
    }

    @SneakyThrows
    public static void approachTwo() { //todo HttpClient + org.json
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray jsonArray = jsonObject.getJSONArray("photos");

        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println(jsonArray.getJSONObject(i).getString("img_src"));
        }
    }

    @SneakyThrows
    public static void approachThree() { //todo java.net + gson
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(URL).openConnection();
        httpURLConnection.setRequestMethod("GET");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder("");
        String line = bufferedReader.readLine();

        while(Objects.nonNull(line)) {
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }

        Gson gson = new Gson();
        JsonObject body = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        JsonArray photos = body.get("photos").getAsJsonArray();

        for (int i = 0; i < photos.size(); i++) {
            System.out.println(photos.get(i).getAsJsonObject().get("img_src").getAsString());
        }

        httpURLConnection.disconnect();
    }

    public static void findBiggestPhoto() {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(URL, JsonNode.class);
        JsonNode jsonNodePhotos = jsonNode.get("photos");

        long contentLength = 0;
        String linkToBiggestImg = "";
        for (int i = 0; i < jsonNodePhotos.size(); i++) {
            String imageLink = jsonNodePhotos.get(i).get("img_src").textValue();
            HttpHeaders imageHeaders = restTemplate.headForHeaders(imageLink);
            URI uri = imageHeaders.getLocation();
            long temp = restTemplate.headForHeaders(uri).getContentLength();
            if (temp > contentLength) {
                contentLength = temp;
                linkToBiggestImg = imageLink;
            }
        }
        System.out.println("linkToBiggestImg: " + linkToBiggestImg);
        System.out.println("contentLength: " + contentLength);
    }

}
