package ford.com.trackandartistinfo;

import ford.com.trackandartistinfo.model.APIKey;
import ford.com.trackandartistinfo.model.Tracks;
import ford.com.trackandartistinfo.model.URI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TrackAndArtistInfoApplication {

    @Bean
    public APIKey getAPIKey(){
        return new APIKey();
    }

    @Bean
    public URI getURI(){
        return new URI();
    }

    @Bean
    public Tracks getTracks(){
        return new Tracks();
    }

    @Bean
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(TrackAndArtistInfoApplication.class, args);
    }

}
