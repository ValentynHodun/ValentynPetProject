package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherResponse {

    private Current current;
    private List<Hourly> hourly;

    public static class Current {
        private long dt;
        private double temp;
        private double pressure;
        private double humidity;

        @JsonProperty("dt")
        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        @JsonProperty("temp")
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        @JsonProperty("pressure")
        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        @JsonProperty("humidity")
        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }
    }

    public static class Hourly {
        private long dt;
        private double temp;

        @JsonProperty("dt")
        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        @JsonProperty("temp")
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }
}
