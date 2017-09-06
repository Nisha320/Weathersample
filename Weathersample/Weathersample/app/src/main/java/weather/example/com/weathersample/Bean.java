package weather.example.com.weathersample;


public class Bean {

    String longitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    String lat;
    String main;
    String descrption;
    String temputare;
    String humitity;
    String windsped;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getTemputare() {
        return temputare;
    }

    public void setTemputare(String temputare) {
        this.temputare = temputare;
    }

    public String getHumitity() {
        return humitity;
    }

    public void setHumitity(String humitity) {
        this.humitity = humitity;
    }

    public String getWindsped() {
        return windsped;
    }

    public void setWindsped(String windsped) {
        this.windsped = windsped;
    }
}
