package dk.laj.quarkus;

import dk.laj.quarkus.weather.Weather;

public class CityReport {
    Weather weather;

    public CityReport() {
    }

    public CityReport(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
