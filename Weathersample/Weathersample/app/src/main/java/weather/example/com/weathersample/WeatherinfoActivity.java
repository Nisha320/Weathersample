package weather.example.com.weathersample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherinfoActivity extends AppCompatActivity {

    String tag_json_obj = "json_obj_req";
    ProgressDialog pDialog;

    DatabaseHelper databaseHelper;
    TextView longtide,longitude,main,description,temp,humitity,windsped;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherinfo);

        databaseHelper=new DatabaseHelper(WeatherinfoActivity.this);


        longtide=(TextView)findViewById(R.id.longtide);
        longitude=(TextView)findViewById(R.id.longitude);
        main=(TextView)findViewById(R.id.main);
        description=(TextView)findViewById(R.id.description);
         temp=(TextView)findViewById(R.id.temp);
        humitity=(TextView)findViewById(R.id.humitity);
         windsped=(TextView)findViewById(R.id.windsped);

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        url="http://api.openweathermap.org/data/2.5/weather?lat="+MapsActivity.newLatLng.latitude+"&lon="+MapsActivity.newLatLng.latitude+"&appid=ce27942848110e76ad593e37d19f30a4";


          pDialog = new ProgressDialog(WeatherinfoActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONObject coord=response.getJSONObject("coord");


                            JSONArray weather=response.getJSONArray("weather");

                            JSONObject mainjson=response.getJSONObject("main");

                            JSONObject wind=response.getJSONObject("wind");
                            windsped.setText("Wind Speed: "+wind.getString("speed").toString());

                            Bean bean=new Bean();
                            bean.setLat(coord.getString("lat").toString());
                            bean.setLongitude(coord.getString("lon").toString());
                            bean.setTemputare(mainjson.getString("temp").toString());
                            bean.setHumitity(mainjson.getString("humidity").toString());
                            bean.setDescrption(weather.getJSONObject(0).getString("description").toString());
                            bean.setMain(weather.getJSONObject(0).getString("main").toString());
                            bean.setWindsped(wind.getString("speed").toString());

                            longtide.setText("Longitude: "+bean.getLongitude());
                            longitude.setText("Latitude:" +bean.getLat());
                            main.setText("MAin: "+bean.getMain());
                            description.setText("Description: "+bean.getDescrption());
                            temp.setText("Temp: "+bean.getTemputare());
                            humitity.setText("Humidity: "+bean.getHumitity());

                            databaseHelper.new_data(bean);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(WeatherinfoActivity.this, ":"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // hide the progress dialog
                pDialog.hide();
            }
        });

        requestQueue.add(jsonObjReq);




    }
}
