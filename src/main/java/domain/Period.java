package domain;

import com.google.gson.annotations.SerializedName;

public class Period {

    @SerializedName("validTime")
    public String validTime;
    @SerializedName("parameters")
    public Weather[] weathers;

    public Period(String validTime, Weather[] weathers) {
        this.validTime = validTime;
        this.weathers = weathers;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public Weather[] getWeathers() {
        return weathers;
    }

    public void setWeather(Weather[] weathers) {
        this.weathers = weathers;
    }


}
