package weather.example.com.weathersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ListView listView;
    LayoutInflater layoutInflater;
    ArrayList<Bean> list = new ArrayList<>();
    WeatherAdapter adapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = (ListView) findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        list = databaseHelper.getalldata();

        if(list.size()==0){

            Toast.makeText(this, "No Saved History", Toast.LENGTH_SHORT).show();

        }

        adapter = new WeatherAdapter(list);
        listView.setAdapter(adapter);

    }

    class WeatherAdapter extends BaseAdapter {
        ArrayList<Bean> list;

        WeatherAdapter(ArrayList<Bean> list) {
            this.list = list;
            layoutInflater = LayoutInflater.from(HistoryActivity.this);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            MyHolder myHolder;

            if (view == null) {
                view = layoutInflater.inflate(R.layout.activity_weatherinfo, null);
                myHolder = new MyHolder();
                myHolder.longtide=(TextView)view.findViewById(R.id.longtide);
                myHolder.longitude=(TextView)view.findViewById(R.id.longitude);
                myHolder.main=(TextView)view.findViewById(R.id.main);
                myHolder.description=(TextView)view.findViewById(R.id.description);
                myHolder.temp=(TextView)view.findViewById(R.id.temp);
                myHolder.humitity=(TextView)view.findViewById(R.id.humitity);
                myHolder.windsped=(TextView)view.findViewById(R.id.windsped);




                view.setTag(myHolder);
            } else {
                myHolder = (MyHolder) view.getTag();
            }

            myHolder.longitude.setText("Long: "+list.get(i).getLongitude());
            myHolder.longtide.setText("Lat: "+list.get(i).getLat());
            myHolder.description.setText("DEsc: "+list.get(i).getDescrption());
            myHolder.humitity.setText("Huminity: "+list.get(i).getHumitity());
            myHolder.main.setText("Main: "+list.get(i).getMain());
            myHolder.windsped.setText("Speed: "+list.get(i).getWindsped());
            myHolder.temp.setText("Temp: "+list.get(i).getTemputare());

            return view;
        }


    }

    public class MyHolder {

        TextView longtide,longitude,main,description,temp,humitity,windsped;


    }



}
