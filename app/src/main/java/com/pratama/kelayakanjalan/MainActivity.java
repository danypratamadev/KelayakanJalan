package com.pratama.kelayakanjalan;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.overscroll.RecyclerViewBouncy;
import com.eyalbira.loadingdots.LoadingDots;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.imgscalr.Main;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Bottom_Sheet_Prov.BottomSheetProvListener{

    private DatabaseHelper databaseHelper;
    public boolean connected = false;
    private Dialog myDialog;
    private long mBack;
    private static final int INTERVAL = 2000;
    private static final int REQ = 21;
    public List<Pro_Class> proArrayList = new ArrayList<>();
    public Pro_Adapter3 adapter3;
    public RecyclerView pro_recycler;
    private ShimmerFrameLayout shimmer_load;
    private ImageButton add, orderby, search, clear, setting;
    private boolean hasil = false;
    public String IDPRO_TXT, NMPRO_TXT;
    private int HAPUS;
    public SwipeRefreshLayout refresh;
    private EditText cari_prov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        verifyPermissions();

        if(chekConnection() == false){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showPopupWarning();
                }
            }, 1500);
        }

        databaseHelper = new DatabaseHelper(this);
        myDialog = new Dialog(this);

        pro_recycler = (RecyclerView) findViewById(R.id.pro_recycler);
        shimmer_load = (ShimmerFrameLayout) findViewById(R.id.pro_load);
        add = (ImageButton) findViewById(R.id.add_prov);
        orderby = (ImageButton) findViewById(R.id.orderby);
        search = (ImageButton) findViewById(R.id.search);
        clear = (ImageButton) findViewById(R.id.clear);
        setting = (ImageButton) findViewById(R.id.setting);
        cari_prov = (EditText) findViewById(R.id.cari_prov);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);

        refresh.setColorScheme(R.color.Blue600, R.color.Green600, R.color.Orange600, R.color.Red600);

        clear.setVisibility(View.GONE);
        cari_prov.clearFocus();

        pro_recycler.setHasFixedSize(true);
        pro_recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        proArrayList.addAll(databaseHelper.allDataPRO());

        shimmer_load.startShimmerAnimation();

        if(proArrayList.isEmpty()){
            pro_recycler.setVisibility(View.GONE);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    shimmer_load.stopShimmerAnimation();
                    shimmer_load.setVisibility(View.GONE);
                    pro_recycler.setVisibility(View.VISIBLE);
                    adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                    pro_recycler.setAdapter(adapter3);
                    pro_recycler.setNestedScrollingEnabled(false);

                }
            }, 1500);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        orderby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.MyPopupStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, orderby);
                popupMenu.inflate(R.menu.option_menu3);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.ascen:
                                try {
                                    proArrayList.clear();

                                    Context context = pro_recycler.getContext();
                                    LayoutAnimationController controller = null;
                                    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                                    proArrayList.addAll(databaseHelper.allDataPROOrder("ASC"));

                                    adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                                    pro_recycler.setAdapter(adapter3);
                                    pro_recycler.setNestedScrollingEnabled(false);
                                    pro_recycler.setLayoutAnimation(controller);
                                    pro_recycler.getAdapter().notifyDataSetChanged();
                                    pro_recycler.scheduleLayoutAnimation();
                                } catch (Exception e){
                                    Log.e("Error Urutkan ", e.toString());
                                }
                                break;
                            case R.id.descen:
                                proArrayList.clear();

                                Context context2 = pro_recycler.getContext();
                                LayoutAnimationController controller2 = null;
                                controller2 = AnimationUtils.loadLayoutAnimation(context2, R.anim.layout_recycle);

                                proArrayList.addAll(databaseHelper.allDataPROOrder("DESC"));

                                adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                                pro_recycler.setAdapter(adapter3);
                                pro_recycler.setNestedScrollingEnabled(false);
                                pro_recycler.setLayoutAnimation(controller2);
                                pro_recycler.getAdapter().notifyDataSetChanged();
                                pro_recycler.scheduleLayoutAnimation();
                                break;
                        }
                        return false;
                    }
                });
                try {
                    Field[] fields = popupMenu.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popupMenu);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                popupMenu.setGravity(Gravity.CENTER_HORIZONTAL);
                popupMenu.show();
            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new getDataProv().execute((Void[])null);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear.setVisibility(View.VISIBLE);
                cari_prov.setBackground(getResources().getDrawable(R.drawable.back_cari));
                cari_prov.setHint("Cari Provinsi");
                cari_prov.setEnabled(true);
                cari_prov.requestFocus();
                cari_prov.setText("");
                //cari_prov.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                cari_prov.setPadding(30, 25, 25, 25);
                cari_prov.setTextColor(getResources().getColor(R.color.Gray900));
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(cari_prov, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clear.setVisibility(View.GONE);
                cari_prov.setHint("");
                cari_prov.setEnabled(false);
                cari_prov.clearFocus();
                cari_prov.setText("Semua Provinsi");
                cari_prov.setBackground(null);
                cari_prov.setTextColor(getResources().getColor(R.color.Gray800));
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(cari_prov.getWindowToken(), 0);

                proArrayList.clear();

                Context context = pro_recycler.getContext();
                LayoutAnimationController controller = null;
                controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                proArrayList.addAll(databaseHelper.allDataPRO());

                adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                pro_recycler.setAdapter(adapter3);
                pro_recycler.setNestedScrollingEnabled(false);
                pro_recycler.setLayoutAnimation(controller);
                pro_recycler.getAdapter().notifyDataSetChanged();
                pro_recycler.scheduleLayoutAnimation();

            }
        });

        cari_prov.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == true){
                    InputMethodManager key = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    key.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    InputMethodManager key = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    key.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            }
        });

        cari_prov.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    @Override
    public void onClicked(String code, final String id, final String nm, final int hapus, String nmseg, String dpsg, String kpsg, String dkt, double psg, String iru) {

        if(code.equals("Edit")){
            final TextInputLayout TIIDPRO, TINMPRO;
            final TextInputEditText IDPRO, NMPRO;
            final LoadingDots load;
            final Button yes, no;
            final RelativeLayout line;
            final ImageView img_success;
            final TextView label;
            myDialog.setContentView(R.layout.add_prov);
            TIIDPRO = (TextInputLayout) myDialog.findViewById(R.id.TIIDPRO);
            TINMPRO = (TextInputLayout) myDialog.findViewById(R.id.TINMPRO);
            IDPRO = (TextInputEditText) myDialog.findViewById(R.id.IDPRO);
            NMPRO = (TextInputEditText) myDialog.findViewById(R.id.NMPRO);
            load = (LoadingDots) myDialog.findViewById(R.id.load);
            label = (TextView) myDialog.findViewById(R.id.label);
            yes = (Button) myDialog.findViewById(R.id.yes);
            no = (Button) myDialog.findViewById(R.id.no);
            line = (RelativeLayout) myDialog.findViewById(R.id.line);
            img_success = (ImageView) myDialog.findViewById(R.id.img_success);

            load.setVisibility(View.GONE);
            img_success.setVisibility(View.GONE);
            label.setText("EDIT PROV "+nm);
            yes.setText("edit");

            IDPRO.setText(id);
            IDPRO.setEnabled(false);
            NMPRO.setText(nm);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (IDPRO.getText().toString().equals("") || NMPRO.getText().toString().equals("")) {

                        if(IDPRO.getText().toString().equals("")){
                            TIIDPRO.setError("Masukkan ID!");
                        }
                        if(NMPRO.getText().toString().equals("")){
                            TINMPRO.setError("Masukkan Nama!");
                        }
                    } else {
                        IDPRO_TXT = IDPRO.getText().toString();
                        NMPRO_TXT = NMPRO.getText().toString();
                        HAPUS = 0;

                        try {
                            databaseHelper.updateDataProv(IDPRO_TXT, NMPRO_TXT, "F", HAPUS);
                        } catch (Exception ex){
                            Log.e("Error Update Prov : ", ex.toString());
                        }

                        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            new updateDataProv().execute((Void[])null);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Upload Failed! No internet connection", Toast.LENGTH_SHORT).show();
                        }

                        load.setVisibility(View.VISIBLE);
                        TIIDPRO.setVisibility(View.GONE);
                        TINMPRO.setVisibility(View.GONE);
                        no.setVisibility(View.GONE);
                        line.setVisibility(View.GONE);
                        yes.setText("saving...");
                        yes.setEnabled(false);
                        hasil = true;

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
                    if(hasil == true){
                        proArrayList.clear();

                        Context context = pro_recycler.getContext();
                        LayoutAnimationController controller = null;
                        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                        proArrayList.addAll(databaseHelper.allDataPRO());

                        adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                        pro_recycler.setAdapter(adapter3);
                        pro_recycler.setNestedScrollingEnabled(false);
                        pro_recycler.setLayoutAnimation(controller);
                        pro_recycler.getAdapter().notifyDataSetChanged();
                        pro_recycler.scheduleLayoutAnimation();
                        hasil = false;
                    }
                    myDialog.dismiss();
                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
            myDialog.show();

        } else if(code.equals("Upload")){

            class updateDataProv2 extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {

                    try {

                        Class.forName("com.mysql.jdbc.Driver");

                        String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                        String user = "u6887969_admin_kelaikanjalan";
                        String password = "pr0yek4ndr01d";

                        Connection conn = DriverManager.getConnection(url, user, password);

                        Statement statement = conn.createStatement();

                        String update = "UPDATE propinsi SET ID_PROPINSI = '"+id+"', NAMA_PROPINSI = '"+nm+"', HAPUS = '"+hapus+"' WHERE ID_PROPINSI = '"+id+"'";

                        statement.executeUpdate(update);

                        databaseHelper.updateDataProv(id, nm, "T", hapus);

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

            class insertDataProv extends AsyncTask<Void, Void, Void>{

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    //holder.spinKitView.setVisibility(View.VISIBLE);
                    //holder.indikator.setVisibility(View.GONE);
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

                        String insert = "INSERT INTO propinsi (ID_PROPINSI, NAMA_PROPINSI, HAPUS) VALUES ('"+id+"', '"+nm+"', '"+hapus+"')";

                        statement.executeUpdate(insert);

                        databaseHelper.updateDataProv(id, nm, "T", hapus);

                    } catch (Exception ex) {
                        Log.e("Error : ", ex.toString());
                        new updateDataProv2().execute((Void[])null);
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    //holder.spinKitView.setVisibility(View.GONE);
                    //holder.indikator.setVisibility(View.VISIBLE);
                    //holder.indikator.setBackground(mainActivity.getResources().getDrawable(R.drawable.color_after));
                    proArrayList.clear();

                    Context context = pro_recycler.getContext();
                    LayoutAnimationController controller = null;
                    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                    proArrayList.addAll(databaseHelper.allDataPRO());

                    adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                    pro_recycler.setAdapter(adapter3);
                    pro_recycler.setNestedScrollingEnabled(false);
                    pro_recycler.setLayoutAnimation(controller);
                    pro_recycler.getAdapter().notifyDataSetChanged();
                    pro_recycler.scheduleLayoutAnimation();
                    Toast.makeText(MainActivity.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                }
            }

            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                new insertDataProv().execute((Void[])null);
            }
            else {
                Toast.makeText(MainActivity.this, "Upload Failed! No internet connection", Toast.LENGTH_SHORT).show();
            }

        } else {

            final Button no2, yes2;
            final TextView title, sub_title, NRU;
            final ImageView icon;
            final LoadingDots load2;
            final RelativeLayout line2;
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
            IDPRO_TXT = id;
            NMPRO_TXT = nm;
            HAPUS = 1;
            NRU.setText(nm);

            no2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });

            yes2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(hasil != true){
                        title.setText("Deleting");
                        sub_title.setText("Mohon tunggu...");
                        no2.setVisibility(View.GONE);
                        NRU.setVisibility(View.GONE);
                        line2.setVisibility(View.GONE);
                        yes2.setTextColor(getResources().getColor(R.color.Red600));
                        yes2.setText("Loading");
                        load2.setVisibility(View.VISIBLE);
                        yes2.setEnabled(false);

                        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            new updateDataProv().execute((Void[])null);
                        }
                        else {
                            myDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Deleting Failed! No internet connection", Toast.LENGTH_SHORT).show();
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
                                hasil = true;
                                yes2.setEnabled(true);

                            }
                        }, 2000);

                    } else {
                        proArrayList.clear();

                        Context context = pro_recycler.getContext();
                        LayoutAnimationController controller = null;
                        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                        proArrayList.addAll(databaseHelper.allDataPRO());

                        adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                        pro_recycler.setAdapter(adapter3);
                        pro_recycler.setNestedScrollingEnabled(false);
                        pro_recycler.setLayoutAnimation(controller);
                        pro_recycler.getAdapter().notifyDataSetChanged();
                        pro_recycler.scheduleLayoutAnimation();
                        hasil = false;
                        myDialog.dismiss();
                    }

                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;

            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                myDialog.show();
            }
            else {
                Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void filter(String text) {
        List<Pro_Class> filteredList = new ArrayList<>();

        for (Pro_Class item : proArrayList) {
            if (item.getNPRO().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter3.filterList(filteredList);
    }

    private class getDataProv extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proArrayList.clear();
            pro_recycler.setVisibility(View.GONE);
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

            proArrayList.addAll(databaseHelper.allDataPRO());

            refresh.setRefreshing(false);
            shimmer_load.stopShimmerAnimation();
            shimmer_load.setVisibility(View.GONE);
            pro_recycler.setVisibility(View.VISIBLE);
            adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
            pro_recycler.setAdapter(adapter3);
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

    public void showPopupWarning() {

        Button yes;
        myDialog.setContentView(R.layout.alert_layout);
        yes = (Button) myDialog.findViewById(R.id.yes);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

    }

    public void showPopup() {
        final TextInputLayout TIIDPRO, TINMPRO;
        final TextInputEditText IDPRO, NMPRO;
        final LoadingDots load;
        final Button yes, no;
        final RelativeLayout line;
        final ImageView img_success;
        myDialog.setContentView(R.layout.add_prov);
        TIIDPRO = (TextInputLayout) myDialog.findViewById(R.id.TIIDPRO);
        TINMPRO = (TextInputLayout) myDialog.findViewById(R.id.TINMPRO);
        IDPRO = (TextInputEditText) myDialog.findViewById(R.id.IDPRO);
        NMPRO = (TextInputEditText) myDialog.findViewById(R.id.NMPRO);
        load = (LoadingDots) myDialog.findViewById(R.id.load);
        yes = (Button) myDialog.findViewById(R.id.yes);
        no = (Button) myDialog.findViewById(R.id.no);
        line = (RelativeLayout) myDialog.findViewById(R.id.line);
        img_success = (ImageView) myDialog.findViewById(R.id.img_success);

        load.setVisibility(View.GONE);
        img_success.setVisibility(View.GONE);

        IDPRO.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Boolean hasil = databaseHelper.getIdProv2(s.toString());

                if(hasil == true){
                    yes.setEnabled(false);
                    TIIDPRO.setErrorEnabled(true);
                    TIIDPRO.setError("Already exists");
                } else {
                    yes.setEnabled(true);
                    TIIDPRO.setErrorEnabled(false);
                }
            }
        });

        NMPRO.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Boolean hasil = databaseHelper.getNamaProv2(s.toString().toLowerCase());

                if(hasil == true){
                    yes.setEnabled(false);
                    TINMPRO.setErrorEnabled(true);
                    TINMPRO.setError("Already exists");
                } else {
                    yes.setEnabled(true);
                    TINMPRO.setErrorEnabled(false);
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IDPRO.getText().toString().equals("") || NMPRO.getText().toString().equals("")) {

                    if(IDPRO.getText().toString().equals("")){
                        TIIDPRO.setError("Masukkan ID!");
                    }
                    if(NMPRO.getText().toString().equals("")){
                        TINMPRO.setError("Masukkan Nama!");
                    }
                } else {
                    NMPRO.setFocusable(false);
                    IDPRO_TXT = IDPRO.getText().toString();
                    NMPRO_TXT = NMPRO.getText().toString();
                    HAPUS = 0;

                    try {
                        databaseHelper.insertDataProv(IDPRO_TXT, NMPRO_TXT, "F", HAPUS);
                    } catch (Exception ex){
                        Log.e("Error Insert Segmen : ", ex.toString());
                    }

                    if(connected == true){
                        new insertDataProv().execute((Void[])null);
                    }

                    load.setVisibility(View.VISIBLE);
                    TIIDPRO.setVisibility(View.GONE);
                    TINMPRO.setVisibility(View.GONE);
                    no.setVisibility(View.GONE);
                    line.setVisibility(View.GONE);
                    yes.setText("saving...");
                    yes.setEnabled(false);
                    hasil = true;

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
                if(hasil == true){
                    proArrayList.clear();

                    Context context = pro_recycler.getContext();
                    LayoutAnimationController controller = null;
                    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                    proArrayList.addAll(databaseHelper.allDataPRO());

                    adapter3 = new Pro_Adapter3(MainActivity.this, proArrayList);
                    pro_recycler.setAdapter(adapter3);
                    pro_recycler.setNestedScrollingEnabled(false);
                    pro_recycler.setLayoutAnimation(controller);
                    pro_recycler.getAdapter().notifyDataSetChanged();
                    pro_recycler.scheduleLayoutAnimation();
                    hasil = false;
                }
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();
    }

    @Override
    public void onBackPressed() {
        if(mBack + INTERVAL > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(MainActivity.this, "Double tap to exit", Toast.LENGTH_SHORT).show();
        }
        mBack = System.currentTimeMillis();
    }

    private void verifyPermissions(){

        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[2]) == PackageManager.PERMISSION_GRANTED){

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, permission, REQ);
        }

    }

    private class insertDataProv extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO propinsi (ID_PROPINSI, NAMA_PROPINSI, HAPUS) VALUES ('"+IDPRO_TXT+"', '"+NMPRO_TXT+"', '"+HAPUS+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataProv(IDPRO_TXT, NMPRO_TXT, "T", HAPUS);

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

    private class updateDataProv extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE propinsi SET ID_PROPINSI = '"+IDPRO_TXT+"', NAMA_PROPINSI = '"+NMPRO_TXT+"', HAPUS = '"+HAPUS+"' WHERE ID_PROPINSI = '"+IDPRO_TXT+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataProv(IDPRO_TXT, NMPRO_TXT, "T", HAPUS);

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

}
