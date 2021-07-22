package ford.com.trackandartistinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class URI {
    final private String lastFmHost = "http://ws.audioscrobbler.com";
    final private String musixMatchHost = "http://api.musixmatch.com/ws/1.1";
    public String getLastFmHost() {
		return lastFmHost;
	}
	public String getMusixMatchHost() {
		return musixMatchHost;
	}
	public String getGoole() {
		return goole;
	}
	public String getTopTrack() {
		return topTrack;
	}
	public String getArtist() {
		return artist;
	}
	public String getLyrics() {
		return lyrics;
	}
	final private String goole = "https://www.googleapis.com/customsearch/v1?num=10&" +
            "searchType=image&imgSize=medium&imgType=face&alt=json";
    final private String topTrack = lastFmHost + "/2.0/?method=geo.gettoptracks&format=json";
    final private String artist = lastFmHost + "/2.0/?method=artist.getinfo&format=json";
    final private String lyrics = musixMatchHost + "/matcher.lyrics.get?";
}
