package ua.learn.yourself.springbobocodebasic;

public class DemoNASA {

    private static final String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";
    //private static final String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=DEMO_KEY";

    public static void main(String[] args) {
        approachOne();
        approachTwo();
        approachThree();
        findBiggestPhoto();
    }

    /**
     * use RestTemplate + jackson
     * print all img_src from photos
     */
    public static void approachOne() { //todo RestTemplate + jackson

    }

    /**
     * use HttpClient + org.json
     * print all img_src from photos
     */
    public static void approachTwo() { //todo HttpClient + org.json

    }

    /**
     * use java.net + gson
     * print all img_src from photos
     */
    public static void approachThree() {

    }

    /**
     * find biggest photo using provided url
     */
    public static void findBiggestPhoto() {

    }

}
