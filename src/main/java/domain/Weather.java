package domain;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("name")
    public String name;
    @SerializedName("levelType")
    public String levelType;
    @SerializedName("level")
    public String level;
    @SerializedName("unit")
    public String unit;
    @SerializedName("values")
    public double[] values;

    public Weather() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public double getValue() {return values[0];}

    public void setValue(int value) {this.values[0] = value;}







}
