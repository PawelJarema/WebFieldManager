package web.field.model.json;

public class JsonRequest {

	private String UserToken;
	private Double Latitude;
	private Double Longitude;

	public String getUserToken() {
		return UserToken;
	}

	public void setUserToken(String userToken) {
		UserToken = userToken;
	}

	public Double getLatitude() {
		return Latitude;
	}

	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}

	public Double getLongitude() {
		return Longitude;
	}

	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}

}
