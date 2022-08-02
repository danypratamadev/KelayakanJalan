package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SplashActivity extends AppCompatActivity {

    private static DatabaseHelper databaseHelper;
    private Boolean connected = false, ppk = false, prov = false, klas = false, ruas = false, segmen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        databaseHelper = new DatabaseHelper(this);

        if(chekConnection() == false){

            Toast.makeText(SplashActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, 3000);

        } else {

            new getDataPPK(new getDataPPK.AsynResponse() {
                @Override
                public void processFinish(Boolean output) {

                    ppk = output;

                }
            }).execute((Void[])null);

            new getDataProv(new getDataProv.AsynResponse() {
                @Override
                public void processFinish(Boolean output) {

                    prov = output;
                    callMainActivity();

                }
            }).execute((Void[])null);

        }

    }

    private void callMainActivity(){

        if(ppk == true && prov == true){

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }

    private boolean chekConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        else {
            connected = false;
        }

        return connected;

    }

    private static class getDataPPK extends AsyncTask<Void, Void, Void> {

        public interface AsynResponse {
            void processFinish(Boolean output);
        }

        AsynResponse asynResponse = null;

        public getDataPPK(AsynResponse asynResponse) {
            this.asynResponse = asynResponse;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_PPK, NAMA FROM ppk";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    try {

                        Boolean inserppk = databaseHelper.insertDataPPK(data.getInt("ID_PPK"), data.getString("NAMA"), "T");

                        if(inserppk == false){

                            String upload = databaseHelper.getPPKUpload(data.getInt("ID_PPK"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataPPK(data.getInt("ID_PPK"), data.getString("NAMA"), "T");
                            }

                        }

                    } catch (Exception ex){

                    }

                }


            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            asynResponse.processFinish(true);
        }

    }

    private static class getDataProv extends AsyncTask<Void, Void, Void> {

        public interface AsynResponse {
            void processFinish(Boolean output);
        }

        getDataProv.AsynResponse asynResponse = null;

        public getDataProv(getDataProv.AsynResponse asynResponse) {
            this.asynResponse = asynResponse;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_PROPINSI, NAMA_PROPINSI, HAPUS FROM propinsi";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){
                    try {

                        Boolean insertProv = databaseHelper.insertDataProv(data.getString("ID_PROPINSI"),
                                data.getString("NAMA_PROPINSI"), "T", data.getInt("HAPUS"));

                        if(insertProv == false){

                            String upload = databaseHelper.getProvUpload(data.getString("ID_PROPINSI"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataProv(data.getString("ID_PROPINSI"),
                                        data.getString("NAMA_PROPINSI"), "T", data.getInt("HAPUS"));
                            }

                        }

                    } catch (Exception ex){

                    }
                }


            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            asynResponse.processFinish(true);
        }

    }

}
