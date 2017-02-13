package ravishankar.lib.reqressample.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ravishankar.lib.reqres.callbacks.ConnectionListener;
import ravishankar.lib.reqres.factory.ConnectionFactory;
import ravishankar.lib.reqres.utils.RequestType;
import ravishankar.lib.reqressample.R;
import ravishankar.lib.reqressample.model.CityWeatherResponse;
import ravishankar.lib.reqressample.provider.ApiProvider;
import ravishankar.lib.reqressample.util.ApiTag;

public class MainActivity extends AppCompatActivity implements ConnectionListener {
    private Context mContext;
    private ConnectionFactory mConnection;
    private TextView mResponse;
    private TextView mCityName;
    private TextView mTemprature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        mResponse = (TextView)this.findViewById(R.id.response);
        mCityName = (TextView)this.findViewById(R.id.city_name);
        mTemprature = (TextView)this.findViewById(R.id.temprature);

        sendCityWeatherRequest();
    }

    public void sendCityWeatherRequest() {
        String api = ApiProvider.getWeatherByCity("London");
        /**
         * Can check the response on browser by hitting below url
         */
//      String api ="http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";

        mConnection = new ConnectionFactory(mContext, RequestType.GET, api, this);
        mConnection.createConnection(ApiTag.WEATHER_BY_CITY);
    }

    @Override
    public void onResponse(int responseResult, String response) {
        CityWeatherResponse cityWeatherResponse = parseJSON(response);
        mResponse.setText(response);

        mCityName.setText("City Name : " + cityWeatherResponse.getName());
        mTemprature.setText("Temperature : " + cityWeatherResponse.getMain().getTemp());
    }

    @Override
    public void onError(int i, int i1, String s) {
        Toast.makeText(mContext,"onError",Toast.LENGTH_SHORT).show();
    }

    public CityWeatherResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        CityWeatherResponse cityWeatherResponse = gson.fromJson(response, CityWeatherResponse.class);
        return cityWeatherResponse;
    }
}
