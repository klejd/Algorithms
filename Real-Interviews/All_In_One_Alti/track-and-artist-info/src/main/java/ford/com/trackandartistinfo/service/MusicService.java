package ford.com.trackandartistinfo.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import ford.com.trackandartistinfo.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    APIKey apiKey;

    @Autowired
    URI uri;

    public Track getTrackByRegion(String city)throws JSONException {

        JSONObject obj = new JSONObject(restTemplate.getForObject(getTopTrackString(city), String.class));
        JSONArray trackArray = obj.getJSONObject("tracks").getJSONArray("track");

        if(trackArray.length() == 0 )
            return null;

        obj = trackArray.getJSONObject(0);
        trackArray = null;    // releasing memory

        Track track = new Track();
        track.setName(obj.getString("name"));
        track.setListeners(obj.getString("listeners"));
        track.setArtist( getArtistInfo(obj.getJSONObject("artist").getString("name")));
        track.setLyrics(getLyrics(track.getName(), track.getArtist().getName()));

        return track;
    }



    public Artist getArtistInfo(String name){
        JSONObject obj = new JSONObject( restTemplate
                .getForObject(getArtistString(name), String.class));

        Artist artist = new Artist();
        artist.setName(obj.getJSONObject("artist").getString("name"));
        artist.setOntour((obj.getJSONObject("artist").getString("ontour").equals("1")) ? "Yes" : "No");

        obj = new JSONObject(restTemplate.getForObject(getGoogleURI(name), String.class));


        JSONArray imageArray = obj.getJSONArray("items");
        List<Image> images = null;
        if(imageArray.length()>0) images = new ArrayList();
        for(int i = 0; i < imageArray.length(); i++){
            if(i == 6) break;
            images.add(new Image(
                    imageArray.getJSONObject(i).getJSONObject("image").getString("thumbnailLink"),
                    imageArray.getJSONObject(i).getJSONObject("image").getString("contextLink")
            ));

        }

        Image[] img = images.toArray(new Image[images.size()]);
        artist.setImage(img);
        return artist;
    }



    public String getLyrics(String trackName, String artistName){

        JSONObject obj = new JSONObject( restTemplate
                .getForObject(getLyricsURI(trackName, artistName), String.class));

        return obj.getJSONObject("message")
                .getJSONObject("body").getJSONObject("lyrics").getString("lyrics_body");
    }



    private String getTopTrackString(String city){

        return uri.getTopTrack() + "&country=" + city
                + "&api_key=" + apiKey.getLastFm();
    }



    private String getArtistString(String name){

        return uri.getArtist() + "&artist=" + name
                + "&api_key=" + apiKey.getLastFm();
    }



    private String getLyricsURI(String trackName, String artistName){

        return uri.getLyrics() + "&q_track=" + trackName
                + "&q_artist=" + artistName + "&apikey=" + apiKey.getMusixMatch();
    }


    private String getGoogleURI(String artistName){
        return uri.getGoole() + "&q=" + artistName
                + "&key=" + apiKey.getGoolge() + "&cx=" + apiKey.getCx();
    }
}
