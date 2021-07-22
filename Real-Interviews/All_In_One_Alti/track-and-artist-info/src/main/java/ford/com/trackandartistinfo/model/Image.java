package ford.com.trackandartistinfo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Image {
    private String url;
    private String contextURL;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContextURL() {
		return contextURL;
	}
	public void setContextURL(String contextURL) {
		this.contextURL = contextURL;
	}
	/**
	 * @param url
	 * @param contextURL
	 */
	public Image(String url, String contextURL) {
		super();
		this.url = url;
		this.contextURL = contextURL;
	}

}
