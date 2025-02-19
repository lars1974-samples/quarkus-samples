package dk.laj.quarkus;

public class Weather {
    private int temperature;
    private int humidity;

    public Weather() {

    }

    public Weather(int temperature, int humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemperature() {
        return temperature;
    }
}