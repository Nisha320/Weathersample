package weather.example.com.weathersample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String Database_name = "weather";
    public final static int Database_version = 1;

    // Student info

    public final static String Table_name = "weather_info";
    public final static String lat="lat";
    public final static String main="main";
    public final static String descrption="descp";
    public final static String temputare="temp";
    public final static String humitity="hum";
    public final static String windsped="sped";
    public final static String longitude="long";



    public DatabaseHelper(Context context) {
        super(context, Database_name, null, Database_version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL("create table " + Table_name + "(" + lat + " text," + main + " text," +
                descrption + " text," + temputare + " text," + humitity + " text," + windsped + " text," + longitude + " text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);

    }

    public long new_data(Bean bean) {

        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(lat, bean.getLat());
            values.put(main, bean.getMain());
            values.put(descrption, bean.getDescrption());
            values.put(temputare,bean.getTemputare());
            values.put(humitity, bean.getHumitity());
            values.put(windsped, bean.getWindsped());
            values.put(longitude, bean.getLongitude());
            long id = database.insert(Table_name, null, values);
            return id;
        } catch (Exception e) {
            return -1;
        }

    }


    public ArrayList<Bean> getalldata() {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + Table_name, null);
        cursor.moveToFirst();
        ArrayList<Bean> bookList = new ArrayList<>();
        while (cursor.isAfterLast() == false) {

            Bean bean = new Bean();
            bean.setLat(cursor.getString(cursor.getColumnIndex(lat)));
            bean.setLongitude(cursor.getString(cursor.getColumnIndex(longitude)));
            bean.setMain(cursor.getString(cursor.getColumnIndex(main)));
            bean.setWindsped(cursor.getString(cursor.getColumnIndex(windsped)));
            bean.setDescrption(cursor.getString(cursor.getColumnIndex(descrption)));
            bean.setHumitity(cursor.getString(cursor.getColumnIndex(humitity)));
            bean.setTemputare(cursor.getString(cursor.getColumnIndex(temputare)));
            bookList.add(bean);
            cursor.moveToNext();
        }

        cursor.close();
        return bookList;

    }

}
