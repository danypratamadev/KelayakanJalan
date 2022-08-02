package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.wang.avi.AVLoadingIndicatorView;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import id.zelory.compressor.Compressor;
import jxl.write.WritableImage;

public class CompressActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ImageButton back;
    private AVLoadingIndicatorView load;
    private ImageView scan;
    private Spinner max_size, point_scan;
    private TextView scan_progress, size_display, scan_operation;
    private RelativeLayout scan_back;
    private String size, point;
    private int lengthStandar;
    private File compressedImage;
    private NumberProgressBar scan_percent;
    private static final String urlUpload = "http://proyekjalan.net/upload_1.php";
    private static final String urlUpload2 = "http://proyekjalan.net/upload_2.php";
    private static final String urlUpload3 = "http://proyekjalan.net/upload_3.php";
    private static final String urlUpload4 = "http://proyekjalan.net/upload_4.php";
    private static final String urlUpload5 = "http://proyekjalan.net/upload_5.php";
    private static final String urlUpload6 = "http://proyekjalan.net/upload_6.php";
    private static DecimalFormat df = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress);

        databaseHelper = new DatabaseHelper(this);

        back = findViewById(R.id.back);
        scan_back = findViewById(R.id.scan_back);
        scan = findViewById(R.id.scan);
        load = findViewById(R.id.load);
        max_size = findViewById(R.id.size);
        point_scan = findViewById(R.id.point);
        scan_progress = findViewById(R.id.scan_process);
        size_display = findViewById(R.id.size_display);
        scan_operation = findViewById(R.id.scan_operation);
        scan_percent = findViewById(R.id.scan_percent);

        load.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scan_back.setBackgroundTintList(getResources().getColorStateList(R.color.White100T));
                load.smoothToShow();

                size = max_size.getSelectedItem().toString();
                point = point_scan.getSelectedItem().toString();

                max_size.setEnabled(false);
                point_scan.setEnabled(false);
                scan.setEnabled(false);

                if(size.equals("> 100 Kb")){
                    lengthStandar = 153600;
                } else if(size.equals("> 200 Kb")){
                    lengthStandar = 202400;
                } else if(size.equals("> 300 Kb")){
                    lengthStandar = 302400;
                } else if(size.equals("> 400 Kb")){
                    lengthStandar = 402400;
                } else if(size.equals("> 500 Kb")){
                    lengthStandar = 502400;
                } else if(size.equals("> 600 Kb")){
                    lengthStandar = 602400;
                } else if(size.equals("> 700 Kb")){
                    lengthStandar = 702400;
                } else if(size.equals("> 800 Kb")){
                    lengthStandar = 802400;
                } else if(size.equals("> 900 Kb")){
                    lengthStandar = 902400;
                } else {
                    lengthStandar = 1024000;
                }

                if(point.equals("A31")){
                    new scanSixImage().execute((Void[])null);
                } else if(point.equals("A124") || point.equals("A131") || point.equals("A133") ||
                        point.equals("A51") || point.equals("A6a1") || point.equals("A6a2") ||
                        point.equals("A6a7") || point.equals("A6b8")){
                    new scanFourImage().execute((Void[])null);
                } else if(point.equals("A111") || point.equals("A113") || point.equals("A116") ||
                        point.equals("A123") || point.equals("A141") || point.equals("A41")){
                    new scanThreeImage().execute((Void[])null);
                } else if(point.equals("A32") || point.equals("A6b5") || point.equals("A6b6")){
                    new scanOneImage().execute((Void[])null);
                } else {
                    new scanTwoImage().execute((Void[])null);
                }

            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private class scanSixImage extends AsyncTask<Void, Void, Void> {

        public String getNamaRuas(String IRU){
            String NMRU = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT NAMA FROM ruas WHERE ID_RUAS = '"+IRU+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    NMRU = data.getString("NAMA");

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return NMRU;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            scan_progress.setText("Prepairing to scanning image...");
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.Blue100));
            load.setVisibility(View.VISIBLE);
            load.smoothToShow();
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

                String select = "SELECT ID_"+point+", GBR_1, GBR_2, GBR_3, GBR_4, GBR_5, GBR_6, ID_SEGMEN FROM "+point;

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NRU = "-";
                    String NRUID = "-";
                    String ID = data.getString("ID_"+point);
                    final String ISEG = data.getString("ID_SEGMEN");
                    String dir1 = data.getString("GBR_1");
                    String dir2 = data.getString("GBR_2");
                    String dir3 = data.getString("GBR_3");
                    String dir4 = data.getString("GBR_4");
                    String dir5 = data.getString("GBR_5");
                    String dir6 = data.getString("GBR_6");

                    int length = ISEG.length();

                    String carac = ID.substring(0 + (length - 3), length - 2);
                    if(carac.equals("_")){
                        NRUID = ID.substring(0, length - 3);
                    } else {
                        NRUID = ID.substring(0, length - 2);
                    }
                    Log.e("ID RUAS", NRUID);

                    NRU = getNamaRuas(NRUID);
                    Log.e("NAMA RUAS", NRU);

                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    File localPath = null;
                    String state = Environment.getExternalStorageState();

                    if (state.contains(Environment.MEDIA_MOUNTED)) {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    } else {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    }

                    if(dir1 != null){

                        dir1 = dir1.replaceAll(" ", "%20");

                        final String finalDir = dir1;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir1);

                        URL url2 = new URL(dir1);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir1);

                                InputStream img = new URL(dir1).openStream();
                                mediaStorageDir = new File(localPath + "_1.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_1.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir2 != null){

                        dir2 = dir2.replaceAll(" ", "%20");

                        final String finalDir = dir2;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir2);

                        URL url2 = new URL(dir2);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir2);

                                InputStream img = new URL(dir2).openStream();
                                mediaStorageDir = new File(localPath + "_2.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_2.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload2, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir3 != null){

                        dir3 = dir3.replaceAll(" ", "%20");

                        final String finalDir = dir3;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir3);

                        URL url2 = new URL(dir3);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir3);

                                InputStream img = new URL(dir3).openStream();
                                mediaStorageDir = new File(localPath + "_3.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_3.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload3, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir4 != null){

                        dir4 = dir4.replaceAll(" ", "%20");

                        final String finalDir = dir4;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir4);

                        URL url2 = new URL(dir4);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir4);

                                InputStream img = new URL(dir4).openStream();
                                mediaStorageDir = new File(localPath + "_4.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_4.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_4.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_4.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_4.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload4, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_4.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir5 != null){

                        dir5 = dir5.replaceAll(" ", "%20");

                        final String finalDir = dir5;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir5);

                        URL url2 = new URL(dir5);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir5);

                                InputStream img = new URL(dir5).openStream();
                                mediaStorageDir = new File(localPath + "_5.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_5.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_5.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_5.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_5.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload5, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_5.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir6 != null){

                        dir6 = dir6.replaceAll(" ", "%20");

                        final String finalDir = dir6;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir6);

                        URL url2 = new URL(dir6);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir6);

                                InputStream img = new URL(dir6).openStream();
                                mediaStorageDir = new File(localPath + "_6.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_1.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_6.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_6.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_6.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload6, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_6.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            scan_progress.setText("Done scanning");
            load.smoothToHide();
            load.setVisibility(View.GONE);
            scan_back.setBackgroundTintList(getResources().getColorStateList(R.color.White20T));
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.White));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scan_progress.setText("Select size and point than press button to start scanning.");
                }
            }, 3000);
        }
    }

    private class scanFiveImage extends AsyncTask<Void, Void, Void> {

        public String getNamaRuas(String IRU){
            String NMRU = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT NAMA FROM ruas WHERE ID_RUAS = '"+IRU+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    NMRU = data.getString("NAMA");

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return NMRU;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            scan_progress.setText("Prepairing to scanning image...");
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.Blue100));
            load.setVisibility(View.VISIBLE);
            load.smoothToShow();
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

                String select = "SELECT ID_"+point+", GBR_1, GBR_2, GBR_3, GBR_4, GBR_5, ID_SEGMEN FROM "+point;

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NRU = "-";
                    String NRUID = "-";
                    String ID = data.getString("ID_"+point);
                    final String ISEG = data.getString("ID_SEGMEN");
                    String dir1 = data.getString("GBR_1");
                    String dir2 = data.getString("GBR_2");
                    String dir3 = data.getString("GBR_3");
                    String dir4 = data.getString("GBR_4");
                    String dir5 = data.getString("GBR_5");

                    int length = ISEG.length();

                    String carac = ID.substring(0 + (length - 3), length - 2);
                    if(carac.equals("_")){
                        NRUID = ID.substring(0, length - 3);
                    } else {
                        NRUID = ID.substring(0, length - 2);
                    }
                    Log.e("ID RUAS", NRUID);

                    NRU = getNamaRuas(NRUID);
                    Log.e("NAMA RUAS", NRU);

                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    File localPath = null;
                    String state = Environment.getExternalStorageState();

                    if (state.contains(Environment.MEDIA_MOUNTED)) {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    } else {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    }

                    if(dir1 != null){

                        dir1 = dir1.replaceAll(" ", "%20");

                        final String finalDir = dir1;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir1);

                        URL url2 = new URL(dir1);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir1);

                                InputStream img = new URL(dir1).openStream();
                                mediaStorageDir = new File(localPath + "_1.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_1.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir2 != null){

                        dir2 = dir2.replaceAll(" ", "%20");

                        final String finalDir = dir2;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir2);

                        URL url2 = new URL(dir2);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir2);

                                InputStream img = new URL(dir2).openStream();
                                mediaStorageDir = new File(localPath + "_2.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_2.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload2, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir3 != null){

                        dir3 = dir3.replaceAll(" ", "%20");

                        final String finalDir = dir3;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir3);

                        URL url2 = new URL(dir3);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir3);

                                InputStream img = new URL(dir3).openStream();
                                mediaStorageDir = new File(localPath + "_3.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_3.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload3, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir4 != null){

                        dir4 = dir4.replaceAll(" ", "%20");

                        final String finalDir = dir4;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir4);

                        URL url2 = new URL(dir4);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir4);

                                InputStream img = new URL(dir4).openStream();
                                mediaStorageDir = new File(localPath + "_4.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_4.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_4.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_4.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_4.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload4, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_4.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir5 != null){

                        dir5 = dir5.replaceAll(" ", "%20");

                        final String finalDir = dir5;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir5);

                        URL url2 = new URL(dir5);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir5);

                                InputStream img = new URL(dir5).openStream();
                                mediaStorageDir = new File(localPath + "_5.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_5.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_5.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_5.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_5.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload5, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_5.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            scan_progress.setText("Done scanning");
            load.smoothToHide();
            load.setVisibility(View.GONE);
            scan_back.setBackgroundTintList(getResources().getColorStateList(R.color.White20T));
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.White));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scan_progress.setText("Select size and point than press button to start scanning.");
                }
            }, 3000);
        }
    }

    private class scanFourImage extends AsyncTask<Void, Void, Void> {

        public String getNamaRuas(String IRU){
            String NMRU = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT NAMA FROM ruas WHERE ID_RUAS = '"+IRU+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    NMRU = data.getString("NAMA");

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return NMRU;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            scan_progress.setText("Prepairing to scanning image...");
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.Blue100));
            load.setVisibility(View.VISIBLE);
            load.smoothToShow();
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

                String select = "SELECT ID_"+point+", GBR_1, GBR_2, GBR_3, GBR_4, ID_SEGMEN FROM "+point;

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NRU = "-";
                    String NRUID = "-";
                    String ID = data.getString("ID_"+point);
                    final String ISEG = data.getString("ID_SEGMEN");
                    String dir1 = data.getString("GBR_1");
                    String dir2 = data.getString("GBR_2");
                    String dir3 = data.getString("GBR_3");
                    String dir4 = data.getString("GBR_4");

                    int length = ISEG.length();

                    String carac = ID.substring(0 + (length - 3), length - 2);
                    if(carac.equals("_")){
                        NRUID = ID.substring(0, length - 3);
                    } else {
                        NRUID = ID.substring(0, length - 2);
                    }
                    Log.e("ID RUAS", NRUID);

                    NRU = getNamaRuas(NRUID);
                    Log.e("NAMA RUAS", NRU);

                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    File localPath = null;
                    String state = Environment.getExternalStorageState();

                    if (state.contains(Environment.MEDIA_MOUNTED)) {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    } else {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    }

                    if(dir1 != null){

                        dir1 = dir1.replaceAll(" ", "%20");

                        final String finalDir = dir1;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir1);

                        URL url2 = new URL(dir1);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir1);

                                InputStream img = new URL(dir1).openStream();
                                mediaStorageDir = new File(localPath + "_1.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_1.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir2 != null){

                        dir2 = dir2.replaceAll(" ", "%20");

                        final String finalDir = dir2;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir2);

                        URL url2 = new URL(dir2);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir2);

                                InputStream img = new URL(dir2).openStream();
                                mediaStorageDir = new File(localPath + "_2.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_2.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload2, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir3 != null){

                        dir3 = dir3.replaceAll(" ", "%20");

                        final String finalDir = dir3;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir3);

                        URL url2 = new URL(dir3);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir3);

                                InputStream img = new URL(dir3).openStream();
                                mediaStorageDir = new File(localPath + "_3.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_3.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload3, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir4 != null){

                        dir4 = dir4.replaceAll(" ", "%20");

                        final String finalDir = dir4;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir4);

                        URL url2 = new URL(dir4);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir4);

                                InputStream img = new URL(dir4).openStream();
                                mediaStorageDir = new File(localPath + "_4.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_4.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_4.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_4.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_4.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload4, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_4.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            scan_progress.setText("Done scanning");
            load.smoothToHide();
            load.setVisibility(View.GONE);
            scan_back.setBackgroundTintList(getResources().getColorStateList(R.color.White20T));
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.White));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scan_progress.setText("Select size and point than press button to start scanning.");
                }
            }, 3000);
        }
    }

    private class scanThreeImage extends AsyncTask<Void, Void, Void> {

        public String getNamaRuas(String IRU){
            String NMRU = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT NAMA FROM ruas WHERE ID_RUAS = '"+IRU+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    NMRU = data.getString("NAMA");

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return NMRU;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            scan_progress.setText("Prepairing to scanning image...");
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.Blue100));
            load.setVisibility(View.VISIBLE);
            load.smoothToShow();
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

                String select = "SELECT ID_"+point+", GBR_1, GBR_2, GBR_3, ID_SEGMEN FROM "+point;

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NRU = "-";
                    String NRUID = "-";
                    String ID = data.getString("ID_"+point);
                    final String ISEG = data.getString("ID_SEGMEN");
                    String dir1 = data.getString("GBR_1");
                    String dir2 = data.getString("GBR_2");
                    String dir3 = data.getString("GBR_3");

                    int length = ISEG.length();

                    String carac = ID.substring(0 + (length - 3), length - 2);
                    if(carac.equals("_")){
                        NRUID = ID.substring(0, length - 3);
                    } else {
                        NRUID = ID.substring(0, length - 2);
                    }
                    Log.e("ID RUAS", NRUID);

                    NRU = getNamaRuas(NRUID);
                    Log.e("NAMA RUAS", NRU);

                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    File localPath = null;
                    String state = Environment.getExternalStorageState();

                    if (state.contains(Environment.MEDIA_MOUNTED)) {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    } else {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    }

                    if(dir1 != null){

                        dir1 = dir1.replaceAll(" ", "%20");

                        final String finalDir = dir1;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir1);

                        URL url2 = new URL(dir1);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir1);

                                InputStream img = new URL(dir1).openStream();
                                mediaStorageDir = new File(localPath + "_1.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_1.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir2 != null){

                        dir2 = dir2.replaceAll(" ", "%20");

                        final String finalDir = dir2;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir2);

                        URL url2 = new URL(dir2);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir2);

                                InputStream img = new URL(dir2).openStream();
                                mediaStorageDir = new File(localPath + "_2.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_2.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload2, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir3 != null){

                        dir3 = dir3.replaceAll(" ", "%20");

                        final String finalDir = dir3;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir3);

                        URL url2 = new URL(dir3);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir3);

                                InputStream img = new URL(dir3).openStream();
                                mediaStorageDir = new File(localPath + "_3.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_3.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_3.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload3, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_3.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            scan_progress.setText("Done scanning");
            load.smoothToHide();
            load.setVisibility(View.GONE);
            scan_back.setBackgroundTintList(getResources().getColorStateList(R.color.White20T));
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.White));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scan_progress.setText("Select size and point than press button to start scanning.");
                }
            }, 3000);
        }
    }

    private class scanTwoImage extends AsyncTask<Void, Void, Void> {

        public String getNamaRuas(String IRU){
            String NMRU = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT NAMA FROM ruas WHERE ID_RUAS = '"+IRU+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    NMRU = data.getString("NAMA");

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return NMRU;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            scan_progress.setText("Prepairing to scanning image...");
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.Blue100));
            load.setVisibility(View.VISIBLE);
            load.smoothToShow();
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

                String select = "SELECT ID_"+point+", GBR_1, GBR_2, ID_SEGMEN FROM "+point;

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NRU = "-";
                    String NRUID = "-";
                    String ID = data.getString("ID_"+point);
                    final String ISEG = data.getString("ID_SEGMEN");
                    String dir1 = data.getString("GBR_1");
                    String dir2 = data.getString("GBR_2");

                    int length = ISEG.length();

                    String carac = ID.substring(0 + (length - 3), length - 2);
                    if(carac.equals("_")){
                        NRUID = ID.substring(0, length - 3);
                    } else {
                        NRUID = ID.substring(0, length - 2);
                    }
                    Log.e("ID RUAS", NRUID);

                    NRU = getNamaRuas(NRUID);
                    Log.e("NAMA RUAS", NRU);

                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    File localPath = null;
                    String state = Environment.getExternalStorageState();

                    if (state.contains(Environment.MEDIA_MOUNTED)) {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    } else {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    }

                    if(dir1 != null){

                        dir1 = dir1.replaceAll(" ", "%20");

                        final String finalDir = dir1;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir1);

                        URL url2 = new URL(dir1);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir1);

                                InputStream img = new URL(dir1).openStream();
                                mediaStorageDir = new File(localPath + "_1.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_1.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                    if(dir2 != null){

                        dir2 = dir2.replaceAll(" ", "%20");

                        final String finalDir = dir2;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir2);

                        URL url2 = new URL(dir2);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir2);

                                InputStream img = new URL(dir2).openStream();
                                mediaStorageDir = new File(localPath + "_2.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_2.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_2.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload2, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_2.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            scan_progress.setText("Done scanning");
            load.smoothToHide();
            load.setVisibility(View.GONE);
            scan_back.setBackgroundTintList(getResources().getColorStateList(R.color.White20T));
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.White));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scan_progress.setText("Select size and point than press button to start scanning.");
                }
            }, 3000);
        }
    }

    private class scanOneImage extends AsyncTask<Void, Void, Void> {

        public String getNamaRuas(String IRU){
            String NMRU = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT NAMA FROM ruas WHERE ID_RUAS = '"+IRU+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    NMRU = data.getString("NAMA");

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return NMRU;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            scan_progress.setText("Prepairing to scanning image...");
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.Blue100));
            load.setVisibility(View.VISIBLE);
            load.smoothToShow();
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

                String select = "SELECT ID_"+point+", GBR_1, ID_SEGMEN FROM "+point;

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NRU = "-";
                    String NRUID = "-";
                    String ID = data.getString("ID_"+point);
                    final String ISEG = data.getString("ID_SEGMEN");
                    String dir1 = data.getString("GBR_1");

                    int length = ISEG.length();

                    String carac = ID.substring(0 + (length - 3), length - 2);
                    if(carac.equals("_")){
                        NRUID = ID.substring(0, length - 3);
                    } else {
                        NRUID = ID.substring(0, length - 2);
                    }
                    Log.e("ID RUAS", NRUID);

                    NRU = getNamaRuas(NRUID);
                    Log.e("NAMA RUAS", NRU);

                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    File localPath = null;
                    String state = Environment.getExternalStorageState();

                    if (state.contains(Environment.MEDIA_MOUNTED)) {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    } else {
                        localPath = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/"+ISEG);
                    }

                    if(dir1 != null){

                        dir1 = dir1.replaceAll(" ", "%20");

                        final String finalDir = dir1;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                scan_progress.setText("Scanning...\n"+ finalDir);

                            }
                        });

                        Log.e("SCANNING", dir1);

                        URL url2 = new URL(dir1);
                        double fileLength = url2.openConnection().getContentLength();
                        double kb = fileLength / 1024;
                        kb = Double.parseDouble(df.format(kb).replace(",", "."));

                        if(kb >= 1024){
                            double mb = kb / 1024;
                            mb = Double.parseDouble(df.format(mb).replace(",", "."));
                            final double finalMb = mb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalMb)+" MB");
                                }
                            });
                        } else {
                            final double finalKb = kb;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    size_display.setText(String.valueOf(finalKb)+" KB");
                                }
                            });
                        }

                        Log.e("SIZE", String.valueOf(fileLength/1024)+" Kb");

                        if(fileLength > lengthStandar){

                            File mediaStorageDir = null;

                            try {

                                Log.e("COMPRESS", dir1);

                                InputStream img = new URL(dir1).openStream();
                                mediaStorageDir = new File(localPath + "_1.png");

                                DataInputStream out = new DataInputStream(img);

                                byte[] buffer = new byte[1024];
                                int flength;

                                long total = 0;
                                FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                                while ((flength = out.read(buffer))>0) {
                                    total += flength;
                                    final int download = (int) ((total * 100) / fileLength);
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Downloading "+ISEG+"_1.png");
                                            scan_percent.setProgress(download);
                                        }
                                    });
                                    fos.write(buffer, 0, flength);
                                }

                                File localPath2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                } else {
                                    localPath2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Compress/"+point+"/"+NRU+"/Segmen "+ISEG+"/Pictures/Compress/");
                                }

                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png");
                                            scan_percent.setProgress(0);
                                        }
                                    });
                                    compressedImage = new Compressor(CompressActivity.this)
                                            .setMaxWidth(480)
                                            .setMaxHeight(360)
                                            .setQuality(80)
                                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                            .setDestinationDirectoryPath(localPath2.getAbsolutePath())
                                            .compressToFile(mediaStorageDir);

                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            scan_operation.setText("Compressing "+ISEG+"_1.png is done");
                                            scan_percent.setProgress(100);
                                        }
                                    });
                                } catch (Exception e){
                                    Log.e("Error compress", e.toString());
                                }

                                double sizecom = compressedImage.length();
                                double kb2 = sizecom / 1024;
                                kb2 = Double.parseDouble(df.format(kb2).replace(",", "."));

                                if(kb2 >= 1024){
                                    double mb2 = kb2 / 1024;
                                    mb2 = Double.parseDouble(df.format(mb2).replace(",", "."));
                                    final double finalMb = mb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalMb)+" MB");
                                        }
                                    });
                                } else {
                                    final double finalKb = kb2;
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            String get = size_display.getText().toString();
                                            size_display.setText("From "+get+" to "+String.valueOf(finalKb)+" KB");
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png");
                                        scan_percent.setProgress(0);
                                    }
                                });

                                uploadToServer(urlUpload, compressedImage.getAbsolutePath(), NRU, ISEG, ID, point);

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        scan_operation.setText("Uploading "+ISEG+"_1.png is done");
                                        scan_percent.setProgress(100);
                                    }
                                });

                            } catch (Exception e){
                                Log.e("Error save 1", e.toString());
                            }

                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scan_operation.setText("Skiping...");
                                    scan_percent.setProgress(0);
                                }
                            });
                        }

                    }

                }

                statement.close();

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            scan_progress.setText("Done scanning");
            load.smoothToHide();
            load.setVisibility(View.GONE);
            scan_back.setBackgroundTintList(getResources().getColorStateList(R.color.White20T));
            scan.setBackgroundTintList(getResources().getColorStateList(R.color.White));
            max_size.setEnabled(true);
            point_scan.setEnabled(true);
            scan.setEnabled(true);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scan_progress.setText("Scan and compress.");
                    size_display.setText("0 KB");
                    scan_operation.setText("Progress");
                    scan_percent.setProgress(0);
                }
            }, 3000);
        }
    }

    public void uploadToServer(String url, String dir, String nru, String iseg, String id, String table){

        try{
            scan_percent.setProgress(20);
            String uploadId = UUID.randomUUID().toString();
            new MultipartUploadRequest(CompressActivity.this, uploadId, url)
                    .addFileToUpload(dir, "image")
                    .addParameter("nru", nru)
                    .addParameter("iseg", iseg)
                    .addParameter("id", id)
                    .addParameter("table", table)
                    .setMaxRetries(3)
                    .startUpload();
            scan_percent.setProgress(80);
        } catch (Exception ex){
            Log.e("Error upload : ", ex.toString());
        }

    }

}
