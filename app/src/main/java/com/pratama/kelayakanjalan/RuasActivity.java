package com.pratama.kelayakanjalan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.chauthai.overscroll.RecyclerViewBouncy;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pratama.kelayakanjalan.api.ApiRequestRuas;
import com.pratama.kelayakanjalan.api.Retroserver;
import com.pratama.kelayakanjalan.model.Responmodel;
import com.pratama.kelayakanjalan.model.Ruas_Model;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RuasActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public RecyclerView ruas_recycler;
    public Ruas_Adapter ruas_adapter;
    public RelativeLayout alert, filter, layout_prov;
    public List<Ruas_Class> ruasArrayList = new ArrayList<>();
    private ImageButton back, clear;
    private FloatingActionButton add_ruas;
    private ShimmerFrameLayout shimmer_load;
    private SwipeRefreshLayout data;
    public boolean connected = false;
    private String ID_PRO;
    private String NM_PRO;
    private EditText cari_ruas;
    private int REQ_CODE = 2112;
    private String balai = "-";
    private int pkk = 0;
    private LinearLayout sub_title, hasil_filter, layout_aksi;
    private TextView balai_dis, pkk_dis, pro_dis, jml_dis;
    private ImageView img;
    private boolean operasi = false;
    public int pos;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruas);

        init();
        new chekConnection().execute((Void[])null);
        onClickListener();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        ID_PRO = intent.getStringExtra("idpro");
        NM_PRO = intent.getStringExtra("pro");

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        ruas_recycler = (RecyclerView) findViewById(R.id.ruas_recycler);
        add_ruas = (FloatingActionButton) findViewById(R.id.add_ruas);
        back = (ImageButton) findViewById(R.id.back);
        clear = (ImageButton) findViewById(R.id.clear);
        alert = (RelativeLayout) findViewById(R.id.alert);
        filter = (RelativeLayout) findViewById(R.id.filter);
        data = (SwipeRefreshLayout) findViewById(R.id.data);
        shimmer_load = (ShimmerFrameLayout) findViewById(R.id.shimmer_load);
        cari_ruas = (EditText) findViewById(R.id.cari_ruas);
        sub_title = (LinearLayout) findViewById(R.id.sub_title);
        hasil_filter = (LinearLayout) findViewById(R.id.hasil_filter);
        layout_aksi = (LinearLayout) findViewById(R.id.layout_aksi);
        layout_prov = (RelativeLayout) findViewById(R.id.layout_prof);
        balai_dis = (TextView) findViewById(R.id.balai_dis);
        pkk_dis = (TextView) findViewById(R.id.pkk_dis);
        pro_dis = (TextView) findViewById(R.id.pro_dis);
        jml_dis = (TextView) findViewById(R.id.jml_dis);
        img = (ImageView) findViewById(R.id.img);

        hasil_filter.setVisibility(View.GONE);
        filter.setVisibility(View.GONE);
        alert.setVisibility(View.GONE);
        pro_dis.setText(NM_PRO);

        if(cari_ruas.getText().toString().equals("")){
            clear.setVisibility(View.GONE);
            cari_ruas.clearFocus();
        } else {
            clear.setVisibility(View.VISIBLE);
            cari_ruas.requestFocus();
        }

        data.setColorScheme(R.color.Blue600, R.color.Green600, R.color.Orange600, R.color.Red600);

        ruas_recycler.setHasFixedSize(true);
        ruas_recycler.setLayoutManager(new LinearLayoutManager(this));

        new getDataKlas().execute((Void[])null);
        new getDataRuas().execute((Void[])null);

    }

    private void onClickListener(){

        add_ruas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RuasActivity.this, IdentitasActivity.class);
                intent.putExtra("op", "Add");
                intent.putExtra("iru", "-");
                intent.putExtra("pro", NM_PRO);
                intent.putExtra("idpro", ID_PRO);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operasi == false){
                    Intent intent = new Intent(RuasActivity.this, FilterActivity.class);
                    startActivityForResult(intent, REQ_CODE);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    ruasArrayList.clear();
                    ruasArrayList.addAll(databaseHelper.allDataRuas(ID_PRO));

                    if(ruasArrayList.isEmpty()){
                        alert.setVisibility(View.VISIBLE);
                    } else {
                        alert.setVisibility(View.GONE);
                        data.setVisibility(View.VISIBLE);
                    }

                    ruas_adapter = new Ruas_Adapter(RuasActivity.this, ruasArrayList);
                    ruas_recycler.setAdapter(ruas_adapter);

                    Context context = ruas_recycler.getContext();
                    LayoutAnimationController controller = null;
                    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                    ruas_recycler.setLayoutAnimation(controller);
                    ruas_recycler.getAdapter().notifyDataSetChanged();
                    ruas_recycler.scheduleLayoutAnimation();

                    balai = "-";
                    pkk = 0;
                    operasi = false;
                    filter.setBackground(getResources().getDrawable(R.drawable.back_filter));
                    img.setImageDrawable(getResources().getDrawable(R.drawable.ic_filter_list_white_24dp));
                    hasil_filter.setVisibility(View.GONE);
                }
            }
        });

        cari_ruas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    clear.setVisibility(View.VISIBLE);
                } else {
                    clear.setVisibility(View.GONE);
                }

            }
        });

        cari_ruas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!ruasArrayList.isEmpty()){
                    filter(s.toString());
                    clear.setVisibility(View.VISIBLE);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cari_ruas.setText("");
                cari_ruas.clearFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(cari_ruas.getWindowToken(), 0);
                clear.setVisibility(View.GONE);
            }
        });

        data.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new getDataKlas2().execute((Void[])null);
                new getDataRuas2().execute((Void[])null);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ruas_recycler.setOnScrollListener(new HideScrollingListener() {
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

    private void hideViews() {
        //toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        //cari_ruas.animate().translationY(-sub_title.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        //data.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        //RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layout_aksi.getLayoutParams();
        //int fabBottomMargin = lp.bottomMargin;
        //layout_aksi.animate().translationY(layout_aksi.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();

        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) layout_prov.getLayoutParams();
        int layout_pro = lp2.bottomMargin;
        layout_prov.animate().translationY(layout_prov.getHeight()+layout_pro).setInterpolator(new AccelerateInterpolator(2)).start();

    }

    private void showViews() {
        //toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //cari_ruas.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //data.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //layout_aksi.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
        layout_prov.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    public void filter(String text) {
        List<Ruas_Class> filteredList = new ArrayList<>();

        for (Ruas_Class item : ruasArrayList) {
            if (item.getNRU().toLowerCase().contains(text.toLowerCase()) || item.getNOR().contains(text)) {
                filteredList.add(item);
            }
        }

        ruas_adapter.filterList(filteredList);
    }

    public void filter2(String text, int text2) {
        List<Ruas_Class> filteredList = new ArrayList<>();

        for (Ruas_Class item : ruasArrayList) {
            if(item.getPJL() != null){
                if (item.getPJL().toLowerCase().contains(text.toLowerCase()) && item.getIDPPK() == text2) {
                    filteredList.add(item);
                }
            }
        }

        ruas_adapter.filterList(filteredList);
    }

    public void filter3(String text) {
        List<Ruas_Class> filteredList = new ArrayList<>();

        for (Ruas_Class item : ruasArrayList) {
            if(item.getPJL() != null){
                if (item.getPJL().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        ruas_adapter.filterList(filteredList);
    }

    public void filter4(int text) {
        List<Ruas_Class> filteredList = new ArrayList<>();

        for (Ruas_Class item : ruasArrayList) {
            if (item.getIDPPK() == text) {
                filteredList.add(item);
            }
        }

        ruas_adapter.filterList(filteredList);
    }

    private void refresh(){

        ruasArrayList.clear();
        ruasArrayList.addAll(databaseHelper.allDataRuas(ID_PRO));

        if(ruasArrayList.isEmpty()){
            alert.setVisibility(View.VISIBLE);
            data.setVisibility(View.GONE);
            filter.setVisibility(View.GONE);
        } else {
            alert.setVisibility(View.GONE);
            data.setVisibility(View.VISIBLE);
            filter.setVisibility(View.VISIBLE);
        }

        ruas_adapter = new Ruas_Adapter(RuasActivity.this, ruasArrayList);
        ruas_recycler.setAdapter(ruas_adapter);

        if(!cari_ruas.getText().toString().equals("")){
            filter(cari_ruas.getText().toString());
        }

        ruas_recycler.scrollToPosition(pos);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        refresh();

        if(!balai.equals("-") && pkk != 0){
            hasil_filter.setVisibility(View.VISIBLE);
            balai_dis.setVisibility(View.VISIBLE);
            pkk_dis.setVisibility(View.VISIBLE);
            balai_dis.setText(balai);
            pkk_dis.setText(databaseHelper.getNamaPPK(pkk));
            filter2(balai, pkk);
        } else if(!balai.equals("-") && pkk == 0){
            hasil_filter.setVisibility(View.VISIBLE);
            pkk_dis.setVisibility(View.GONE);
            balai_dis.setText(balai);
            filter3(balai);
        } else if(balai.equals("-") && pkk != 0){
            hasil_filter.setVisibility(View.VISIBLE);
            balai_dis.setVisibility(View.GONE);
            pkk_dis.setText(databaseHelper.getNamaPPK(pkk));
            filter4(pkk);
        } else {
            balai = "-";
            pkk = 0;
            operasi = false;
            img.setImageDrawable(getResources().getDrawable(R.drawable.ic_filter_list_white_24dp));
            hasil_filter.setVisibility(View.GONE);
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK){
                balai = data.getStringExtra("balai");
                pkk = data.getIntExtra("pkk", 0);

                filter.setBackground(getResources().getDrawable(R.drawable.back_filter3));
                img.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_red_500_24dp));

                operasi = true;

            }
        }
    }

    private class getDataRuas extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_RUAS, BALAI, NAMA, PANJANG, KM_AWAL, KM_AKHIR, KECEPATAN, HAPUS, ID_PPK, ID_KLASIFIKASI, ID_PROPINSI FROM ruas WHERE ID_PROPINSI = '"+ID_PRO+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NOR = "";
                    String ID = "";

                    ID = data.getString("ID_RUAS");

                    int length = ID.length();

                    NOR = ID.substring(3, length);

                    try {

                        Boolean insertRuas = databaseHelper.insertDataRuas(data.getString("ID_RUAS"), data.getString("BALAI"),
                                data.getString("NAMA"), NOR, data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                data.getString("KM_AKHIR"), data.getDouble("KECEPATAN"), data.getInt("HAPUS"),
                                data.getInt("ID_PPK"), data.getString("ID_PROPINSI"), data.getString("ID_KLASIFIKASI"), "T");

                        if(insertRuas == false){

                            String upload = databaseHelper.getRuasUpload(data.getString("ID_RUAS"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataRuas(data.getString("ID_RUAS"), data.getString("BALAI"),
                                        data.getString("NAMA"), NOR, data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                        data.getString("KM_AKHIR"), data.getDouble("KECEPATAN"), data.getInt("HAPUS"),
                                        data.getInt("ID_PPK"), data.getString("ID_PROPINSI"), data.getString("ID_KLASIFIKASI"), "T");
                            }

                        }

                    } catch (Exception ex){
                        Log.e("Error Get Data : ", ex.toString());
                    }

                    try{

                        if(data.getString("BALAI") != null){

                            Boolean hasil = databaseHelper.allDataBalaiWhere(data.getString("BALAI"));

                            if(hasil == false){
                                databaseHelper.insertDataBalai(data.getString("BALAI"));
                            }

                        }

                    } catch (Exception ex){
                        Log.e("Error insert : ", ex.toString());
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

            try {
                ruasArrayList.addAll(databaseHelper.allDataRuas(ID_PRO));
            } catch (Exception ex){
                Log.e("Error Select Ruas : ", ex.toString());
            }

            if(ruasArrayList.isEmpty()){
                filter.setVisibility(View.GONE);
                shimmer_load.stopShimmerAnimation();
                shimmer_load.setVisibility(View.GONE);
                alert.setVisibility(View.VISIBLE);
            } else {
                alert.setVisibility(View.GONE);
                shimmer_load.stopShimmerAnimation();
                shimmer_load.setVisibility(View.GONE);
                filter.setVisibility(View.VISIBLE);
                ruas_adapter = new Ruas_Adapter(RuasActivity.this, ruasArrayList);
                ruas_recycler.setAdapter(ruas_adapter);
                if(!cari_ruas.getText().toString().equals("")){
                    filter(cari_ruas.getText().toString());
                }
                jml_dis.setText(String.valueOf(databaseHelper.getProfilesCount(ID_PRO))+" Ruas jalan");
            }

        }

    }

    private class getDataKlas extends AsyncTask<Void, Void, Void> {

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

                String selectklaf = "SELECT ID_KLASIFIKASI, SISTEM_JARINGAN, STATUS, FUNGSI, KELAS_PRASARANA, KELAS_PENGGUNAAN, MEDAN FROM klasifikasi_jalan WHERE ID_PROPINSI = '"+ID_PRO+"'";

                ResultSet klaf = statement.executeQuery(selectklaf);

                while(klaf.next()){

                    try{

                        Boolean insertKlas = databaseHelper.insertDataKlas(klaf.getString("ID_KLASIFIKASI"), klaf.getString("SISTEM_JARINGAN"),
                                klaf.getString("STATUS"), klaf.getString("FUNGSI"), klaf.getString("KELAS_PRASARANA"),
                                klaf.getString("KELAS_PENGGUNAAN"), klaf.getString("MEDAN"), "T");

                        if(insertKlas == false){

                            String upload = databaseHelper.getKlasUpload(klaf.getString("ID_KLASIFIKASI"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataKlas(klaf.getString("ID_KLASIFIKASI"), klaf.getString("SISTEM_JARINGAN"),
                                        klaf.getString("STATUS"), klaf.getString("FUNGSI"), klaf.getString("KELAS_PRASARANA"),
                                        klaf.getString("KELAS_PENGGUNAAN"), klaf.getString("MEDAN"), "T");
                            }

                        }

                    } catch (Exception ex){

                    }

                }

            } catch (Exception ex){
                Log.e("Error Klas : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }

    private class getDataRuas2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_RUAS, BALAI, NAMA, PANJANG, KM_AWAL, KM_AKHIR, KECEPATAN, HAPUS, ID_PPK, ID_KLASIFIKASI, ID_PROPINSI FROM ruas WHERE ID_PROPINSI = '"+ID_PRO+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String NOR = "";
                    String ID = "";

                    ID = data.getString("ID_RUAS");

                    int length = ID.length();

                    NOR = ID.substring(3, length);

                    try {

                        Boolean insertRuas = databaseHelper.insertDataRuas(data.getString("ID_RUAS"), data.getString("BALAI"),
                                data.getString("NAMA"), NOR, data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                data.getString("KM_AKHIR"), data.getDouble("KECEPATAN"), data.getInt("HAPUS"),
                                data.getInt("ID_PPK"), data.getString("ID_PROPINSI"), data.getString("ID_KLASIFIKASI"), "T");

                        if(insertRuas == false){

                            String upload = databaseHelper.getRuasUpload(data.getString("ID_RUAS"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataRuas(data.getString("ID_RUAS"), data.getString("BALAI"),
                                        data.getString("NAMA"), NOR, data.getDouble("PANJANG"), data.getString("KM_AWAL"),
                                        data.getString("KM_AKHIR"), data.getDouble("KECEPATAN"), data.getInt("HAPUS"),
                                        data.getInt("ID_PPK"), data.getString("ID_PROPINSI"), data.getString("ID_KLASIFIKASI"), "T");
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

            try {
                ruasArrayList.addAll(databaseHelper.allDataRuas(ID_PRO));
            } catch (Exception ex){
                Log.e("Error Select Ruas : ", ex.toString());
            }

            if(ruasArrayList.isEmpty()){
                filter.setVisibility(View.GONE);
                shimmer_load.stopShimmerAnimation();
                shimmer_load.setVisibility(View.GONE);
                alert.setVisibility(View.VISIBLE);
                data.setRefreshing(false);
            } else {
                alert.setVisibility(View.GONE);
                shimmer_load.stopShimmerAnimation();
                shimmer_load.setVisibility(View.GONE);
                ruas_recycler.setVisibility(View.VISIBLE);
                filter.setVisibility(View.VISIBLE);
                ruas_adapter = new Ruas_Adapter(RuasActivity.this, ruasArrayList);
                ruas_recycler.setAdapter(ruas_adapter);
                if(!balai.equals("-") && pkk != 0){
                    hasil_filter.setVisibility(View.VISIBLE);
                    balai_dis.setVisibility(View.VISIBLE);
                    pkk_dis.setVisibility(View.VISIBLE);
                    balai_dis.setText(balai);
                    pkk_dis.setText(pkk);
                    filter2(balai, pkk);
                } else if(!balai.equals("-") && pkk == 0){
                    hasil_filter.setVisibility(View.VISIBLE);
                    pkk_dis.setVisibility(View.GONE);
                    balai_dis.setText(balai);
                    filter3(balai);
                } else if(balai.equals("-") && pkk != 0){
                    hasil_filter.setVisibility(View.VISIBLE);
                    balai_dis.setVisibility(View.GONE);
                    pkk_dis.setText(databaseHelper.getNamaPPK(pkk));
                    filter4(pkk);
                } else {
                    balai = "-";
                    pkk = 0;
                    operasi = false;
                    img.setImageDrawable(getResources().getDrawable(R.drawable.filter_icon));
                    hasil_filter.setVisibility(View.GONE);
                }
                if(!cari_ruas.getText().toString().equals("")){
                    filter(cari_ruas.getText().toString());
                }
                data.setRefreshing(false);
            }

        }

    }

    private class getDataKlas2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ruasArrayList.clear();
            ruas_recycler.setVisibility(View.GONE);
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

                String selectklaf = "SELECT ID_KLASIFIKASI, SISTEM_JARINGAN, STATUS, FUNGSI, KELAS_PRASARANA, KELAS_PENGGUNAAN, MEDAN FROM klasifikasi_jalan WHERE ID_PROPINSI = '"+ID_PRO+"'";

                ResultSet klaf = statement.executeQuery(selectklaf);

                while(klaf.next()){

                    try{

                        Boolean insertKlas = databaseHelper.insertDataKlas(klaf.getString("ID_KLASIFIKASI"), klaf.getString("SISTEM_JARINGAN"),
                                klaf.getString("STATUS"), klaf.getString("FUNGSI"), klaf.getString("KELAS_PRASARANA"),
                                klaf.getString("KELAS_PENGGUNAAN"), klaf.getString("MEDAN"), "T");

                        if(insertKlas == false){

                            String upload = databaseHelper.getKlasUpload(klaf.getString("ID_KLASIFIKASI"));

                            if(upload.equals("T")){
                                databaseHelper.updateDataKlas(klaf.getString("ID_KLASIFIKASI"), klaf.getString("SISTEM_JARINGAN"),
                                        klaf.getString("STATUS"), klaf.getString("FUNGSI"), klaf.getString("KELAS_PRASARANA"),
                                        klaf.getString("KELAS_PENGGUNAAN"), klaf.getString("MEDAN"), "T");
                            }

                        }

                    } catch (Exception ex){

                    }

                }

            } catch (Exception ex){
                Log.e("Error Klas : ", ex.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }

}
