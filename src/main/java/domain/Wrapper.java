package domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wrapper {

    @SerializedName("timeSeries")
    public Period[] periods;

    public Wrapper(Period[] periods) {
        this.periods = periods;
    }

    public Period[] getPeriods() {
        return periods;
    }

    public void setPeriods(Period[] periods) {
        this.periods = periods;
    }


}
