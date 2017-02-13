package ravishankar.lib.reqressample.provider;

/**
 * Created by ravi on 12/2/17.
 */

public class ApiProvider {
    private static final String BASE_URL = "http://samples.openweathermap.org/data/2.5/weather?";
    private static final String APP_ID = "b1b15e88fa797225412429c1c50c122a1";

    public static String getWeatherByCity(String city) {
        return BASE_URL + "q=" + city + "&appid=" + APP_ID;
    }
}
