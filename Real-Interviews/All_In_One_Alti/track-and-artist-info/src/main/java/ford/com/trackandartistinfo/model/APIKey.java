package ford.com.trackandartistinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class APIKey {
    private String lastFm = "55304d584694c53305f1d99eddd82095";
    private String musixMatch = "8cd1901540a4ce0c540c5821045d9ddf";
    private String goolge = "AIzaSyArUJqaUZmkeHtW26duMgIjLKEVhterLdQ";
    private String cx = "009976367278009039396:la-yg83ejjg";
	public String getLastFm() {
		return lastFm;
	}
	public void setLastFm(String lastFm) {
		this.lastFm = lastFm;
	}
	public String getMusixMatch() {
		return musixMatch;
	}
	public void setMusixMatch(String musixMatch) {
		this.musixMatch = musixMatch;
	}
	public String getGoolge() {
		return goolge;
	}
	public void setGoolge(String goolge) {
		this.goolge = goolge;
	}
	public String getCx() {
		return cx;
	}
	public void setCx(String cx) {
		this.cx = cx;
	}
}
