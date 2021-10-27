package ua.learn.yourself.springbobocodebasic;

public class LearnRestTemplate {

    public static void main(String[] args) {
        getMessages();
        postMessage();
        postRequestEntityWithHeader();
        postRequestEntityAndGetResponseEntity();
    }

    /**
     * use RestTemplate
     * get messages from "http://localhost:8889/messages" endpoint
     */
    static void getMessages() {

    }

    /**
     * post message to "http://localhost:8889/messages" and get Message as response
     */
    static void postMessage() {

    }

    /**
     * post RequestEntity to http://localhost:8889/messages endpoint
     * in RequestEntity should be header X-Mood and message
     */
    static void postRequestEntityWithHeader() {

    }

    /**
     * post RequestEntity with header and message and get ResponseEntity as response
     */
    static void postRequestEntityAndGetResponseEntity() {

    }
}
