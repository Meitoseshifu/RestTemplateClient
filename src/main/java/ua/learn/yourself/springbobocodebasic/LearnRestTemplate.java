package ua.learn.yourself.springbobocodebasic;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LearnRestTemplate {

    public static void main(String[] args) {
        getMessages();
        postMessage();
        postRequestEntityWithHeader();
        postRequestEntityAndGetResponseEntity();
    }

    static void getMessages() {
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject("http://localhost:8889/messages", String.class); //4oxo ne povertae message
        System.out.println(s);
    }

    static void postMessage() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Message> httpEntity = new HttpEntity<>(new Message(2L, "popostuv", "9"));
        Message message = restTemplate.postForObject("http://localhost:8889/messages", httpEntity, Message.class);
        System.out.println(message);
    }

    static void postRequestEntityWithHeader() {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Message> body = RequestEntity.post("http://localhost:8889/messages")
                .header("X-Mood", "4erepashka")
                .body(new Message(3L, "I'm in request entity baby", "pyzdata 4erepashka"));
        Message message = restTemplate.postForObject("http://localhost:8889/messages", body, Message.class);
        System.out.println(message);
    }

    static void postRequestEntityAndGetResponseEntity() {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Message> body = RequestEntity.post("http://localhost:8889/messages")
                .header("X-Mood", "depressed")
                .body(new Message(5L, "why I dont have any questions? Am I stupid?", "master lomaster"));

        ResponseEntity<Message> exchange = restTemplate
                .exchange("http://localhost:8889/messages", HttpMethod.POST, body, Message.class);
        System.out.println(exchange);

    }
}
