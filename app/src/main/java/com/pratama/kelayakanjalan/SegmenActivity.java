package com.pratama.kelayakanjalan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.overscroll.RecyclerViewBouncy;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.eyalbira.loadingdots.LoadingDots;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pratama.kelayakanjalan.api.ApiRequestRuas;
import com.pratama.kelayakanjalan.api.Retroserver;
import com.pratama.kelayakanjalan.model.Responmodel;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

public class SegmenActivity extends AppCompatActivity implements Bottom_Sheet_Prov.BottomSheetProvListener {

    private DatabaseHelper databaseHelper;
    public String PJL_TXT, NRU_TXT, NOR_TXT, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, SEG_TXT, DPSG_TXT, KPSG_TXT, DKT_TXT;
    private double PRU_TXT, PSG_TXT, KCP_TXT;
    public String IRU_TXT, ISEG;
    public int HPS_TXT;
    public boolean connected = false;
    private TextView NRU_DIS, NOR_DIS, PRU_DIS;
    public TextView PROGRESS;
    public NumberProgressBar PERCENT;
    public TextView NMA_FILE;
    private Dialog myDialog;
    private ImageButton back;
    private FloatingActionButton add_seg;
    public RecyclerView segmen_recycler;
    public Segmen_Adapter segmen_adapter;
    public RelativeLayout alert;
    public List<Segmen_Class> segmenArrayList = new ArrayList<>();
    //private boolean hasil = false;
    private ShimmerFrameLayout shimmer_load;
    private SwipeRefreshLayout data;
    public LinearLayout alertdownload;
    private Button detail;
    private File mediaStorageDir = null;
    private static final String TABEL_RUAS = "ruas";
    private static final String TABEL_KLAS = "klas";
    private static final String ID_RUAS = "iru";
    private static final String ID_KLAS = "iklas";
    private static final String PENYELENGGARA = "pjl";
    private static final String PKK = "pkk";
    private static final String PRO = "pro";
    private static final String NAMA_RUAS = "nrs";
    private static final String NOMOR_RUAS = "nor";
    private static final String PANJANG_RUAS = "prs";
    private static final String DKM = "dkm";
    private static final String KKM = "kkm";
    private static final String KOTA = "kta";
    private static final String SISTEM_JARINGAN = "sjr";
    private static final String STATUS = "sts";
    private static final String FUNGSI = "fng";
    private static final String KELAS_PRASARANA = "kpr";
    private static final String KELAS_PENGGUNAAN = "kpg";
    private static final String MEDAN_JALAN = "mjl";
    private static final String KECEPATAN = "kcp";
    private TextInputLayout TISEG, TIPSG, TIDPSG, TIKPSG, TIDKT;
    private TextInputEditText SEG, PSG, DPSG, KPSG, DKT;
    private LoadingDots load;
    private Button yes, no;
    private RelativeLayout line;
    private ImageView img_success;
    private boolean hasilll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segmen);

        init();
        new chekConnection().execute((Void[])null);
        onClickListener();

    }

    private void init(){

        Intent intent = getIntent();

        NRU_DIS = (TextView) findViewById(R.id.NRU_DIS);
        NOR_DIS = (TextView) findViewById(R.id.NOR_DIS);
        PRU_DIS = (TextView) findViewById(R.id.PRU_DIS);
        NMA_FILE = (TextView) findViewById(R.id.nama_file);
        PROGRESS = (TextView) findViewById(R.id.progress);
        PERCENT = (NumberProgressBar) findViewById(R.id.percent);
        add_seg = (FloatingActionButton) findViewById(R.id.add_seg);
        back = (ImageButton) findViewById(R.id.back);
        shimmer_load = (ShimmerFrameLayout) findViewById(R.id.shimmer_load);
        data = (SwipeRefreshLayout) findViewById(R.id.data);
        segmen_recycler = (RecyclerView) findViewById(R.id.segmen_recycler);
        alert = (RelativeLayout) findViewById(R.id.alert);
        alertdownload = (LinearLayout) findViewById(R.id.alertdownload);
        detail = (Button) findViewById(R.id.detail);

        alert.setVisibility(View.GONE);

        myDialog = new Dialog(this);
        databaseHelper = new DatabaseHelper(this);

        IRU_TXT = intent.getStringExtra("iru");
        PJL_TXT = intent.getStringExtra("pjl");
        NRU_TXT = intent.getStringExtra("nru");
        NOR_TXT = intent.getStringExtra("nor");
        PRU_TXT = intent.getDoubleExtra("pru",0);
        SJR_TXT = intent.getStringExtra("sjr");
        FNG_TXT = intent.getStringExtra("fng");
        KPR_TXT = intent.getStringExtra("kpr");
        MJL_TXT = intent.getStringExtra("mjl");
        KCP_TXT = intent.getDoubleExtra("kcp",0);

        NRU_DIS.setText("RUAS: "+NRU_TXT);
        NOR_DIS.setText("No Ruas: "+NOR_TXT+" :: Panjang: "+String.valueOf(PRU_TXT)+" Km");

        data.setColorScheme(R.color.Blue600, R.color.Green600, R.color.Orange600, R.color.Red600);

        segmen_recycler.setHasFixedSize(true);
        segmen_recycler.setLayoutManager(new GridLayoutManager(this, 3));

        new getDataSegmen().execute((Void[])null);

        alertdownload.setVisibility(View.GONE);

    }

    private void onClickListener(){

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder bkBuilder = new AlertDialog.Builder(SegmenActivity.this);
                final View bkView = getLayoutInflater().inflate(R.layout.welcome, null);
                
                String ID_KLASS = null, TEXT = null;

                TextView NRU_DIS = (TextView) bkView.findViewById(R.id.NRU_DIS);
                TextView DETAIL = (TextView) bkView.findViewById(R.id.detail);
                Button yes = (Button) bkView.findViewById(R.id.yes);

                bkBuilder.setView(bkView);
                final AlertDialog dialog = bkBuilder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                String query = "SELECT * FROM " + TABEL_RUAS + " WHERE " + ID_RUAS + " = '" + IRU_TXT +"'";
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery(query, null);

                if(cursor.moveToFirst()){
                    do{

                        ID_KLASS = cursor.getString(cursor.getColumnIndex(ID_KLAS));
                        
                        NRU_DIS.setText(cursor.getString(cursor.getColumnIndex(NAMA_RUAS)));
                        DETAIL.setText("IDENTITAS RUAS JALAN\n\nPenyelenggara\t: "+ cursor.getString(cursor.getColumnIndex(PENYELENGGARA))+"\n" +
                                "Nomor Ruas\t: "+cursor.getString(cursor.getColumnIndex(NOMOR_RUAS))+"\n" +
                                "Panjang Ruas\t: "+String.valueOf(cursor.getDouble(cursor.getColumnIndex(PANJANG_RUAS)))+" KM\n" +
                                "KM Awal\t: KM "+cursor.getString(cursor.getColumnIndex(DKM))+"\n" +
                                "KM Akhir\t: KM"+cursor.getString(cursor.getColumnIndex(KKM)));
                        
                        TEXT = DETAIL.getText().toString();

                    } while (cursor.moveToNext());
                }

                String query2 = "SELECT * FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " = '" + ID_KLASS +"'";
                Cursor cursor2 = db.rawQuery(query2, null);

                if(cursor2.moveToFirst()){
                    do{

                        DETAIL.setText(TEXT+"\n\nKLASIFIKASI JALAN\n\nSistem Jaringan\t: "+cursor2.getString(cursor2.getColumnIndex(SISTEM_JARINGAN))+"\n" +
                                "Status\t: "+cursor2.getString(cursor2.getColumnIndex(STATUS))+"\n" +
                                "Fungsi\t: "+cursor2.getString(cursor2.getColumnIndex(FUNGSI))+"\n" +
                                "Kelas Prasarana\t: "+cursor2.getString(cursor2.getColumnIndex(KELAS_PRASARANA))+"\n" +
                                "Kelas Penggunaan\t: "+cursor2.getString(cursor2.getColumnIndex(KELAS_PENGGUNAAN))+"\n" +
                                "Medan Jalan\t: "+cursor2.getString(cursor2.getColumnIndex(MEDAN_JALAN)));

                    } while (cursor.moveToNext());
                }
                
                db.close();

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        add_seg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        data.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new getDataSegmen2().execute((Void[])null);
            }
        });

        segmen_recycler.setOnScrollListener(new HideScrollingListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });

    }

    @Override
    public void onClicked(String code, final String id, final String nm, final int hapus, final String nmseg, final String dpsg, final String kpsg, final String dkt, final double psg, final String iru) {

        if(code.equals("Edit")){

            final TextInputLayout TISEG, TIPSG, TIDPSG, TIKPSG, TIDKT;
            final TextInputEditText SEG, PSG, DPSG, KPSG, DKT;
            final LoadingDots load;
            final Button yes, no;
            final RelativeLayout line;
            final ImageView img_success;
            final TextView label;
            final boolean[] hasil = {false};
            myDialog.setContentView(R.layout.add_segmen);
            TISEG = (TextInputLayout) myDialog.findViewById(R.id.TISEG);
            TIPSG = (TextInputLayout) myDialog.findViewById(R.id.TIPSG);
            TIDPSG = (TextInputLayout) myDialog.findViewById(R.id.TIDPSG);
            TIKPSG = (TextInputLayout) myDialog.findViewById(R.id.TIKPSG);
            TIDKT = (TextInputLayout) myDialog.findViewById(R.id.TIDKT);
            SEG = (TextInputEditText) myDialog.findViewById(R.id.SEG);
            PSG = (TextInputEditText) myDialog.findViewById(R.id.PSG);
            DPSG = (TextInputEditText) myDialog.findViewById(R.id.DPSG);
            KPSG = (TextInputEditText) myDialog.findViewById(R.id.KPSG);
            DKT = (TextInputEditText) myDialog.findViewById(R.id.DKT);
            label = (TextView) myDialog.findViewById(R.id.label);
            load = (LoadingDots) myDialog.findViewById(R.id.load);
            yes = (Button) myDialog.findViewById(R.id.yes);
            no = (Button) myDialog.findViewById(R.id.no);
            line = (RelativeLayout) myDialog.findViewById(R.id.line);
            img_success = (ImageView) myDialog.findViewById(R.id.img_success);

            SEG.setText(nmseg);
            SEG.setEnabled(false);
            PSG.setText(String.valueOf(psg));
            DPSG.setText(dpsg);
            KPSG.setText(kpsg);
            DKT.setText(dkt);

            load.setVisibility(View.GONE);
            img_success.setVisibility(View.GONE);
            label.setText("EDIT SEGMEN "+nmseg);
            yes.setText("edit");

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SEG.getText().toString().equals("") || PSG.getText().toString().equals("") ||
                            DPSG.getText().toString().equals("") || KPSG.getText().toString().equals("")) {
                        if(SEG.getText().toString().equals("")){
                            TISEG.setError("Masukkan Segmen!");
                        }
                        if(PSG.getText().toString().equals("")){
                            TIPSG.setError("Masukkan Panjang!");
                        }
                        if(DPSG.getText().toString().equals("")){
                            TIDPSG.setError("Masukkan Km Awal!");
                        }
                        if(KPSG.getText().toString().equals("")){
                            TIKPSG.setError("Masukkan Km Akhir!");
                        }
                        if(DKT.getText().toString().equals("")){
                            TIDKT.setError("Masukkan Kota!");
                        }
                    } else {
                        SEG_TXT = SEG.getText().toString();
                        PSG_TXT = Float.parseFloat(PSG.getText().toString());
                        DPSG_TXT = DPSG.getText().toString();
                        KPSG_TXT = KPSG.getText().toString();
                        DKT_TXT = DKT.getText().toString();
                        ISEG = id;
                        HPS_TXT = hapus;
                        IRU_TXT = iru;

                        try {

                            databaseHelper.updateDataSegmen(ISEG, IRU_TXT, SEG_TXT, PSG_TXT, DPSG_TXT,
                                    KPSG_TXT, DKT_TXT, HPS_TXT, "F");

                        } catch (Exception ex){

                            Log.e("Error update : ", ex.toString());

                        }

                        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            new updateDataSegmen().execute((Void[])null);
                        }
                        else {
                            Toast.makeText(SegmenActivity.this, "Upload Failed! No internet connection", Toast.LENGTH_SHORT).show();
                        }

                        load.setVisibility(View.VISIBLE);
                        TISEG.setVisibility(View.GONE);
                        TIPSG.setVisibility(View.GONE);
                        TIDPSG.setVisibility(View.GONE);
                        TIKPSG.setVisibility(View.GONE);
                        TIDKT.setVisibility(View.GONE);
                        no.setVisibility(View.GONE);
                        line.setVisibility(View.GONE);
                        yes.setText("is updating...");
                        yes.setEnabled(false);
                        hasil[0] = true;

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                load.setVisibility(View.GONE);
                                img_success.setVisibility(View.VISIBLE);
                                no.setVisibility(View.VISIBLE);
                                no.setText("done");
                                no.setTextColor(getResources().getColor(R.color.WindowBgActiveDark));
                                yes.setVisibility(View.GONE);
                            }
                        }, 2000);

                    }
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(hasil[0] == true){

                        segmenArrayList.clear();

                        Context context = segmen_recycler.getContext();
                        LayoutAnimationController controller = null;
                        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                        segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

                        if(segmenArrayList.isEmpty()){
                            alert.setVisibility(View.VISIBLE);
                        } else {
                            alert.setVisibility(View.GONE);
                        }

                        segmen_adapter = new Segmen_Adapter(SegmenActivity.this, segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                        segmen_recycler.setAdapter(segmen_adapter);
                        segmen_recycler.setLayoutAnimation(controller);
                        segmen_recycler.getAdapter().notifyDataSetChanged();
                        segmen_recycler.scheduleLayoutAnimation();
                        hasil[0] = false;
                    }
                    myDialog.dismiss();
                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
            myDialog.show();

        } else if(code.equals("Upload")){

            class updateDataSegmen2 extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {

                    try {

                        Class.forName("com.mysql.jdbc.Driver");

                        String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                        String user = "u6887969_admin_kelaikanjalan";
                        String password = "pr0yek4ndr01d";

                        Connection conn = DriverManager.getConnection(url, user, password);

                        Statement statement = conn.createStatement();

                        String update = "UPDATE segmen SET SEGMEN = '"+nmseg+"', PANJANG = '"+psg+"', " +
                                "KM_AWAL = '"+dpsg+"', KM_AKHIR = '"+kpsg+"', DARI_KOTA = '"+dkt+"', KTG_A1 = NULL, " +
                                "KTG_A2 = NULL, KTG_A3 = NULL, KTG_A4 = NULL, KTG_A5 = NULL, KTG_A6a = NULL, KTG_A6b = NULL, HAPUS = '"+hapus+"', " +
                                "ID_RUAS = '"+iru+"' WHERE ID_SEGMEN = '"+id+"'";

                        statement.executeUpdate(update);

                        databaseHelper.updateDataSegmen(id, iru, nmseg, psg, dpsg, kpsg, dkt, hapus, "T");

                    } catch (Exception ex) {
                        Log.e("Error : ", ex.toString());
                    }

                    return null;

                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                }
            }

            class insertRuasSegmen extends AsyncTask<Void, Void, Void>{

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    //holder.spinKitView.setVisibility(View.VISIBLE);
                    //holder.indikator.setVisibility(View.GONE);
                    //holder.CAP.setText("Uploading...");
                    //holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Blue300));
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

                        String insert = "INSERT INTO segmen (ID_SEGMEN, SEGMEN, PANJANG, KM_AWAL, KM_AKHIR, DARI_KOTA, HAPUS, KTG_A1, KTG_A2, KTG_A3, " +
                                "KTG_A4, KTG_A5, KTG_A6a, KTG_A6b, ID_RUAS) VALUES ('"+id+"', '"+nmseg+"', " +
                                "'"+psg+"', '"+dpsg+"', '"+kpsg+"', '"+dkt+"', " +
                                "'"+hapus+"', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', '"+iru+"')";

                        statement.executeUpdate(insert);

                        databaseHelper.updateDataSegmen(id, iru, nmseg, psg, dpsg, kpsg, dkt, hapus, "T");

                    } catch (Exception ex) {
                        Log.e("Error : ", ex.toString());
                        new updateDataSegmen2().execute((Void[])null);
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    //holder.spinKitView.setVisibility(View.GONE);
                    //holder.indikator.setVisibility(View.VISIBLE);
                    //holder.indikator.setBackground(segmenActivity.getResources().getDrawable(R.drawable.color_after));
                    //holder.CAP.setText("Long press to show menu");
                    //holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Gray600));
                    segmenArrayList.clear();

                    Context context = segmen_recycler.getContext();
                    LayoutAnimationController controller = null;
                    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                    segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

                    if(segmenArrayList.isEmpty()){
                        alert.setVisibility(View.VISIBLE);
                    } else {
                        alert.setVisibility(View.GONE);
                    }

                    segmen_adapter = new Segmen_Adapter(SegmenActivity.this, segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                    segmen_recycler.setAdapter(segmen_adapter);
                    segmen_recycler.setLayoutAnimation(controller);
                    segmen_recycler.getAdapter().notifyDataSetChanged();
                    segmen_recycler.scheduleLayoutAnimation();
                    Toast.makeText(SegmenActivity.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                }
            }

            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
                new insertRuasSegmen().execute((Void[])null);
            }
            else {
                connected = false;
                Toast.makeText(SegmenActivity.this, "Upload Failed! No internet connection", Toast.LENGTH_SHORT).show();
            }

        } else if(code.equals("Delete")){

            final Button no2, yes2;
            final TextView title, sub_title, NRU;
            final ImageView icon;
            final LoadingDots load2;
            final RelativeLayout line2;
            final boolean[] hasil = {false};
            myDialog.setContentView(R.layout.delete_layout);
            title = (TextView) myDialog.findViewById(R.id.judul);
            sub_title = (TextView) myDialog.findViewById(R.id.sub_judul);
            icon = (ImageView) myDialog.findViewById(R.id.image);
            load2 = (LoadingDots) myDialog.findViewById(R.id.load);
            no2 = (Button) myDialog.findViewById(R.id.no);
            yes2 = (Button) myDialog.findViewById(R.id.yes);
            NRU = (TextView) myDialog.findViewById(R.id.NRU);
            line2 = (RelativeLayout) myDialog.findViewById(R.id.line);

            load2.setVisibility(View.GONE);
            icon.setVisibility(View.GONE);
            ISEG = id;
            HPS_TXT = 1;
            IRU_TXT = iru;
            SEG_TXT = nmseg;
            PSG_TXT = psg;
            DPSG_TXT = dpsg;
            KPSG_TXT = kpsg;
            DKT_TXT = dkt;
            NRU.setText("SEGMEN "+nmseg);

            no2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });

            yes2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(hasil[0] != true){
                        title.setText("Deleting");
                        sub_title.setText("Mohon tunggu...");
                        no2.setVisibility(View.GONE);
                        NRU.setVisibility(View.GONE);
                        line2.setVisibility(View.GONE);
                        yes2.setTextColor(getResources().getColor(R.color.Red600));
                        yes2.setText("Loading");
                        load2.setVisibility(View.VISIBLE);
                        yes2.setEnabled(false);

                        ConnectivityManager connectivityManager3 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        if(connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            new updateDataSegmen().execute((Void[])null);
                        }
                        else {
                            myDialog.dismiss();
                            Toast.makeText(SegmenActivity.this, "Deleting Failed! No internet connection", Toast.LENGTH_SHORT).show();
                        }

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                title.setText("Success");
                                sub_title.setText("Data berhasil dihapus");
                                load2.setVisibility(View.GONE);
                                icon.setVisibility(View.VISIBLE);
                                yes2.setText("Done");
                                hasil[0] = true;
                                yes2.setEnabled(true);

                            }
                        }, 2000);

                    } else {
                        segmenArrayList.clear();

                        Context context = segmen_recycler.getContext();
                        LayoutAnimationController controller = null;
                        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                        segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

                        segmen_adapter = new Segmen_Adapter(SegmenActivity.this, segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                        segmen_recycler.setAdapter(segmen_adapter);
                        segmen_recycler.setNestedScrollingEnabled(false);
                        segmen_recycler.setLayoutAnimation(controller);
                        segmen_recycler.getAdapter().notifyDataSetChanged();
                        segmen_recycler.scheduleLayoutAnimation();
                        hasil[0] = false;
                        myDialog.dismiss();
                    }

                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;

            ConnectivityManager connectivityManager3 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                myDialog.show();
            }
            else {
                Toast.makeText(SegmenActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }

        } else {

            class insertToExcel extends AsyncTask<Void, Void, Void> {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    //holder.spinKitView.setVisibility(View.VISIBLE);
                    //holder.indikator.setVisibility(View.GONE);
                    //holder.CAP.setText("Downloading...");
                    //holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Blue300));
                    alertdownload.setVisibility(View.VISIBLE);
                    final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                    alertdownload.setAnimation(animation);
                    NMA_FILE.setText("Downloading Laporan ruas "+NRU_TXT.toLowerCase()+" segmen "+nmseg+".xls");
                    PROGRESS.setText("calculating...");
                }

                @Override
                protected Void doInBackground(Void... voids) {

                    File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU_TXT+"/Segmen "+nmseg+"/Excel");
                    if(!folder.exists()){
                        folder.mkdirs();
                    }

                    String state = Environment.getExternalStorageState();

                    if (state.contains(Environment.MEDIA_MOUNTED)) {
                        mediaStorageDir = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU_TXT+"/Segmen "+nmseg+"/Excel", "Laporan ruas "+NRU_TXT.toLowerCase()+" segmen "+nmseg+".xls");
                    } else {
                        mediaStorageDir = new File(Environment
                                .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU_TXT+"/Segmen "+nmseg+"/Excel", "Laporan ruas "+NRU_TXT.toLowerCase()+" segmen "+nmseg+".xls");
                    }

                    if(!mediaStorageDir.exists()){

                        try {

                            InputStream inputStream = new URL("http://proyekjalan.net/template/template2.xls").openStream();
                            DataInputStream xls = new DataInputStream(inputStream);

                            byte[] buffer = new byte[1024];
                            int length;

                            FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                            while ((length = xls.read(buffer))>0) {
                                fos.write(buffer, 0, length);
                            }

                        } catch (Exception e){
                            Log.e("Error Download XLS ", e.toString());
                        }

                    } else {

                        for (int num = 1; mediaStorageDir.exists(); num++) {
                            mediaStorageDir = new File(Environment
                                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU_TXT+"/Segmen "+nmseg+"/Excel", "Laporan ruas "+NRU_TXT.toLowerCase()+" segmen "+nmseg+"("+num+").xls");
                        }

                        try {

                            InputStream inputStream = new URL("http://proyekjalan.net/template/template2.xls").openStream();
                            DataInputStream xls = new DataInputStream(inputStream);

                            byte[] buffer = new byte[1024];
                            int length;

                            FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                            while ((length = xls.read(buffer))>0) {
                                fos.write(buffer, 0, length);
                            }

                        } catch (Exception e){
                            Log.e("Error copy ", e.toString());
                        }

                    }

                    try {
                        CreateExcel5 excel = new CreateExcel5(id, mediaStorageDir.getAbsolutePath(), NRU_TXT, nmseg, PROGRESS, PERCENT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }

                    return null;

                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    //holder.spinKitView.setVisibility(View.GONE);
                    //holder.indikator.setVisibility(View.VISIBLE);
                    //holder.indikator.setBackground(segmenActivity.getResources().getDrawable(R.drawable.color_after));
                    //holder.CAP.setText("Long press to show menu");
                    //holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Gray600));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down2);
                            alertdownload.setAnimation(animation);
                            alertdownload.setVisibility(View.GONE);
                            PERCENT.setProgress(0);

                            String type = getMimeTypeByFile(mediaStorageDir.getAbsolutePath());

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                            Uri apkURI = FileProvider.getUriForFile(SegmenActivity.this,getApplicationContext().getPackageName() + ".provider", mediaStorageDir);

                            intent.setDataAndType(apkURI, type);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            try {
                                startActivity(intent);
                            }
                            catch (ActivityNotFoundException e) {
                                Toast.makeText(SegmenActivity.this, "No Application Available to View Excel",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, 1500);

                }
            }

            ConnectivityManager connectivityManager2 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager2.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
                new insertToExcel().execute((Void[])null);
            }
            else {
                connected = false;
                Toast.makeText(SegmenActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public String getMimeTypeByFile(String filePath) {
        MimeTypeMap type = MimeTypeMap.getSingleton();
        return type.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(filePath));
    }

    private void hideViews() {
        //toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        //cari_ruas.animate().translationY(-sub_title.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        //data.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) add_seg.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        add_seg.animate().translationY(add_seg.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();

    }

    private void showViews() {
        //toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //cari_ruas.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //data.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        add_seg.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    public void showPopup() {

        myDialog.setContentView(R.layout.add_segmen);
        TISEG = (TextInputLayout) myDialog.findViewById(R.id.TISEG);
        TIPSG = (TextInputLayout) myDialog.findViewById(R.id.TIPSG);
        TIDPSG = (TextInputLayout) myDialog.findViewById(R.id.TIDPSG);
        TIKPSG = (TextInputLayout) myDialog.findViewById(R.id.TIKPSG);
        TIDKT = (TextInputLayout) myDialog.findViewById(R.id.TIDKT);
        SEG = (TextInputEditText) myDialog.findViewById(R.id.SEG);
        PSG = (TextInputEditText) myDialog.findViewById(R.id.PSG);
        DPSG = (TextInputEditText) myDialog.findViewById(R.id.DPSG);
        KPSG = (TextInputEditText) myDialog.findViewById(R.id.KPSG);
        DKT = (TextInputEditText) myDialog.findViewById(R.id.DKT);
        load = (LoadingDots) myDialog.findViewById(R.id.load);
        yes = (Button) myDialog.findViewById(R.id.yes);
        no = (Button) myDialog.findViewById(R.id.no);
        line = (RelativeLayout) myDialog.findViewById(R.id.line);
        img_success = (ImageView) myDialog.findViewById(R.id.img_success);

        load.setVisibility(View.GONE);
        img_success.setVisibility(View.GONE);

        SEG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Boolean cari = databaseHelper.getSegmenNomor(s.toString(), IRU_TXT);

                if(cari == true){
                    yes.setEnabled(false);
                    TISEG.setErrorEnabled(true);
                    TISEG.setError("Already exists");
                } else {
                    yes.setEnabled(true);
                    TISEG.setErrorEnabled(false);
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SEG.getText().toString().equals("") || PSG.getText().toString().equals("") ||
                        DPSG.getText().toString().equals("") || KPSG.getText().toString().equals("")) {
                    if(SEG.getText().toString().equals("")){
                        TISEG.setError("Masukkan Segmen!");
                    }
                    if(PSG.getText().toString().equals("")){
                        TIPSG.setError("Masukkan Panjang!");
                    }
                    if(DPSG.getText().toString().equals("")){
                        TIDPSG.setError("Masukkan Km Awal!");
                    }
                    if(KPSG.getText().toString().equals("")){
                        TIKPSG.setError("Masukkan Km Akhir!");
                    }
                    if(DKT.getText().toString().equals("")){
                        TIDKT.setError("Masukkan Kota!");
                    }
                } else {
                    SEG_TXT = SEG.getText().toString();
                    PSG_TXT = Double.parseDouble(PSG.getText().toString());
                    DPSG_TXT = DPSG.getText().toString();
                    KPSG_TXT = KPSG.getText().toString();
                    DKT_TXT = DKT.getText().toString();

                    ISEG = IRU_TXT + "_" +SEG_TXT;
                    HPS_TXT = 0;

                    try {

                        Boolean insertDataSegmen = databaseHelper.insertDataSegmen(ISEG, IRU_TXT, SEG_TXT, PSG_TXT, DPSG_TXT, KPSG_TXT, DKT_TXT, HPS_TXT, "F");

                        if(insertDataSegmen != false){

                            ConnectivityManager connectivityManager3 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                            if(connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                    connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                new insertRuasSegmen().execute((Void[])null);
                            }
                            else {
                                Toast.makeText(SegmenActivity.this, "Upload Failed! No internet connection", Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            databaseHelper.updateDataSegmen(ISEG, IRU_TXT, SEG_TXT, PSG_TXT, DPSG_TXT, KPSG_TXT, DKT_TXT, HPS_TXT, "F");

                            ConnectivityManager connectivityManager3 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                            if(connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                    connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                new updateDataSegmen().execute((Void[])null);
                            }
                            else {
                                Toast.makeText(SegmenActivity.this, "Upload Failed! No internet connection", Toast.LENGTH_SHORT).show();
                            }

                        }

                    } catch (Exception ex){
                        Log.e("Error Insert Segmen : ", ex.toString());
                    }

                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasilll == true){
                    segmenArrayList.clear();

                    Context context = segmen_recycler.getContext();
                    LayoutAnimationController controller = null;
                    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                    segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

                    if(segmenArrayList.isEmpty()){
                        alert.setVisibility(View.VISIBLE);
                    } else {
                        alert.setVisibility(View.GONE);
                    }

                    segmen_adapter = new Segmen_Adapter(SegmenActivity.this, segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                    segmen_recycler.setAdapter(segmen_adapter);
                    segmen_recycler.setLayoutAnimation(controller);
                    segmen_recycler.getAdapter().notifyDataSetChanged();
                    segmen_recycler.scheduleLayoutAnimation();
                    hasilll = false;
                }
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

    }

    private void refresh(){

        segmenArrayList.clear();

        segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

        if(segmenArrayList.isEmpty()){
            alert.setVisibility(View.VISIBLE);
        } else {
            alert.setVisibility(View.GONE);
            data.setVisibility(View.VISIBLE);
        }

        segmen_adapter = new Segmen_Adapter(SegmenActivity.this, segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
        segmen_recycler.setAdapter(segmen_adapter);

    }

    private class insertRuasSegmen extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            load.setVisibility(View.VISIBLE);
            TISEG.setVisibility(View.GONE);
            TIPSG.setVisibility(View.GONE);
            TIDPSG.setVisibility(View.GONE);
            TIKPSG.setVisibility(View.GONE);
            TIDKT.setVisibility(View.GONE);
            no.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
            yes.setText("saving...");
            yes.setEnabled(false);
            hasilll = true;
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

                String insert = "INSERT INTO segmen (ID_SEGMEN, SEGMEN, PANJANG, KM_AWAL, KM_AKHIR, DARI_KOTA, HAPUS, KTG_A1, KTG_A2, KTG_A3, KTG_A4, KTG_A5, KTG_A6a, KTG_A6b, ID_RUAS) " +
                        "VALUES ('"+ISEG+"', '"+SEG_TXT+"', '"+PSG_TXT+"', '"+DPSG_TXT+"', '"+KPSG_TXT+"', '"+DKT_TXT+"', '"+HPS_TXT+"', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', '"+IRU_TXT+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataSegmen(ISEG, IRU_TXT, SEG_TXT, PSG_TXT, DPSG_TXT, KPSG_TXT, DKT_TXT, HPS_TXT, "T");

            } catch (Exception ex) {
                Log.e("Error Upload : ", ex.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            load.setVisibility(View.GONE);
            img_success.setVisibility(View.VISIBLE);
            no.setVisibility(View.VISIBLE);
            no.setText("done");
            no.setTextColor(getResources().getColor(R.color.WindowBgActiveDark));
            yes.setVisibility(View.GONE);
        }
    }

    private class updateDataSegmen extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            load.setVisibility(View.VISIBLE);
            TISEG.setVisibility(View.GONE);
            TIPSG.setVisibility(View.GONE);
            TIDPSG.setVisibility(View.GONE);
            TIKPSG.setVisibility(View.GONE);
            TIDKT.setVisibility(View.GONE);
            no.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
            yes.setText("saving...");
            yes.setEnabled(false);
            hasilll = true;
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

                String update = "UPDATE segmen SET SEGMEN = '"+SEG_TXT+"', PANJANG = '"+PSG_TXT+"', " +
                        "KM_AWAL = '"+DPSG_TXT+"', KM_AKHIR = '"+KPSG_TXT+"', DARI_KOTA = '"+DKT_TXT+"', KTG_A1 = NULL, " +
                        "KTG_A2 = NULL, KTG_A3 = NULL, KTG_A4 = NULL, KTG_A5 = NULL, KTG_A6a = NULL, " +
                        "KTG_A6b = NULL, HAPUS = '"+HPS_TXT+"', ID_RUAS = '"+IRU_TXT+"' WHERE ID_SEGMEN = '"+ISEG+"'";

                statement.executeUpdate(update);

                databaseHelper.updateDataSegmen(ISEG, IRU_TXT, SEG_TXT, PSG_TXT, DPSG_TXT,
                        KPSG_TXT, DKT_TXT, HPS_TXT, "T");

            } catch (Exception ex) {
                Log.e("Error update : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            load.setVisibility(View.GONE);
            img_success.setVisibility(View.VISIBLE);
            no.setVisibility(View.VISIBLE);
            no.setText("done");
            no.setTextColor(getResources().getColor(R.color.WindowBgActiveDark));
            yes.setVisibility(View.GONE);
        }
    }

    private class chekConnection extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
            else {
                connected = false;
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private class getDataSegmen extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            shimmer_load.startShimmerAnimation();
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

                String select = "SELECT ID_SEGMEN, ID_RUAS, SEGMEN, PANJANG, KM_AWAL, KM_AKHIR, DARI_KOTA, HAPUS FROM segmen WHERE ID_RUAS = '"+IRU_TXT+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    try {
                        Boolean inserSegmen = databaseHelper.insertDataSegmen(data.getString("ID_SEGMEN"), data.getString("ID_RUAS"),
                                data.getString("SEGMEN"), data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                data.getString("KM_AKHIR"), data.getString("DARI_KOTA"), data.getInt("HAPUS"), "T");

                        if(inserSegmen == false){

                            String upload = databaseHelper.getSegmenUpload(data.getString("ID_SEGMEN"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataSegmen(data.getString("ID_SEGMEN"), data.getString("ID_RUAS"),
                                        data.getString("SEGMEN"), data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                        data.getString("KM_AKHIR"), data.getString("DARI_KOTA"), data.getInt("HAPUS"), "T");
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

            segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

            if(segmenArrayList.isEmpty()){
                alert.setVisibility(View.VISIBLE);
                shimmer_load.stopShimmerAnimation();
                shimmer_load.setVisibility(View.GONE);
            } else {
                alert.setVisibility(View.GONE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shimmer_load.stopShimmerAnimation();
                        shimmer_load.setVisibility(View.GONE);
                        segmen_adapter = new Segmen_Adapter(SegmenActivity.this, segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                        segmen_recycler.setAdapter(segmen_adapter);
                        PRU_DIS.setText(String.valueOf(databaseHelper.getProfilesCount2(IRU_TXT))+" Segmen");
                    }
                }, 1500);
            }

        }

    }

    private class getDataSegmen2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            segmenArrayList.clear();
            segmen_recycler.setVisibility(View.GONE);
            alert.setVisibility(View.GONE);
            shimmer_load.setVisibility(View.VISIBLE);
            shimmer_load.startShimmerAnimation();
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

                String select = "SELECT ID_SEGMEN, ID_RUAS, SEGMEN, PANJANG, KM_AWAL, KM_AKHIR, DARI_KOTA, HAPUS FROM segmen WHERE ID_RUAS = '"+IRU_TXT+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    try {
                        Boolean inserSegmen = databaseHelper.insertDataSegmen(data.getString("ID_SEGMEN"), data.getString("ID_RUAS"),
                                data.getString("SEGMEN"), data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                data.getString("KM_AKHIR"), data.getString("DARI_KOTA"), data.getInt("HAPUS"), "T");

                        if(inserSegmen == false){

                            String upload = databaseHelper.getSegmenUpload(data.getString("ID_SEGMEN"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataSegmen(data.getString("ID_SEGMEN"), data.getString("ID_RUAS"),
                                        data.getString("SEGMEN"), data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                        data.getString("KM_AKHIR"), data.getString("DARI_KOTA"), data.getInt("HAPUS"), "T");
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

            segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

            if(segmenArrayList.isEmpty()){
                alert.setVisibility(View.VISIBLE);
                shimmer_load.stopShimmerAnimation();
                shimmer_load.setVisibility(View.GONE);
                data.setRefreshing(false);
            } else {
                alert.setVisibility(View.GONE);
                shimmer_load.stopShimmerAnimation();
                shimmer_load.setVisibility(View.GONE);
                segmen_recycler.setVisibility(View.VISIBLE);
                segmen_adapter = new Segmen_Adapter(SegmenActivity.this, segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                segmen_recycler.setAdapter(segmen_adapter);
                data.setRefreshing(false);
            }

        }

    }

}
