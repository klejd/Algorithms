package ford.com.trackandartistinfo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Artist {
    private String name;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOntour() {
		return ontour;
	}
	public void setOntour(String ontour) {
		this.ontour = ontour;
	}
	public Image[] getImage() {
		return image;
	}
	public void setImage(Image[] image) {
		this.image = image;
	}
	private String ontour;
    private Image[] image;
}
