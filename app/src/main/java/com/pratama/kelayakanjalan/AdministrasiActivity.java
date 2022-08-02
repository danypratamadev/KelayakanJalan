package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chauthai.overscroll.RecyclerViewBouncy;
import com.eyalbira.loadingdots.LoadingDots;
import com.github.jorgecastilloprz.FABProgressCircle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class AdministrasiActivity extends AppCompatActivity {

    public String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    private ImageButton back;
    public TextView klf;
    private Button SAVE;
    private Spinner SKTD, SKLI, SLGT, SKTD2, SKLI2, SLGT2, SKTD3, SKLI3,
            SLGT3, SKTD4, SKLI4, SLGT4, SKTD5, SKLI5, SLGT5, SKTD6, SKLI6, SLGT6;
    private TextInputEditText REC, REC2, REC3, REC4, REC5, REC6;
    public String SKTD_TXT, SKLI_TXT, SLGT_TXT, SKTD2_TXT, SKLI2_TXT, SLGT2_TXT, SKTD3_TXT, SKLI3_TXT,
            SLGT3_TXT, SKTD4_TXT, SKLI4_TXT, SLGT4_TXT, SKTD5_TXT, SKLI5_TXT, SLGT5_TXT, SKTD6_TXT, SKLI6_TXT, SLGT6_TXT,
            KTG_KLF1, KTG_KLF2, KTG_KLF3, KTG_KLF4, KTG_KLF5, KTG_KLF6, KTG_KLF, REC_TXT, REC_TXT2, REC_TXT3, REC_TXT4, REC_TXT5, REC_TXT6;
    public FABProgressCircle FAB_UPLOAD;
    public FloatingActionButton EDIT, UPLOAD;
    private Boolean connected = false;
    private CoordinatorLayout coordinatorLayout;
    private Dialog myDialog;
    private List<Administrasi_Class> adminArrayList = new ArrayList<>();
    private Administrasi_Adapter adapter;
    private RecyclerViewBouncy admin_recycler;
    private LinearLayout INPUT;
    private RelativeLayout aksi;
    private Boolean op = false, op2 = false;
    private Boolean hasil = false;
    private DatabaseHelper databaseHelper;
    private ACProgressFlower dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrasi);

        init();
        onClickListener();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);
        myDialog = new Dialog(this);

        Intent intent = getIntent();
        ISEG = intent.getStringExtra("iseg");
        SJR = intent.getStringExtra("sjr");
        FNG = intent.getStringExtra("fng");
        KPR = intent.getStringExtra("kpr");
        MJL = intent.getStringExtra("mjl");
        NRU = intent.getStringExtra("nru");

        SKTD = (Spinner) findViewById(R.id.SKTD);
        SKLI = (Spinner) findViewById(R.id.SKLI);
        SLGT = (Spinner) findViewById(R.id.SLGT);
        REC = (TextInputEditText) findViewById(R.id.REC);

        SKTD2 = (Spinner) findViewById(R.id.SKTD2);
        SKLI2 = (Spinner) findViewById(R.id.SKLI2);
        SLGT2 = (Spinner) findViewById(R.id.SLGT2);
        REC2 = (TextInputEditText) findViewById(R.id.REC2);

        SKTD3 = (Spinner) findViewById(R.id.SKTD3);
        SKLI3 = (Spinner) findViewById(R.id.SKLI3);
        SLGT3 = (Spinner) findViewById(R.id.SLGT3);
        REC3 = (TextInputEditText) findViewById(R.id.REC3);

        SKTD4 = (Spinner) findViewById(R.id.SKTD4);
        SKLI4 = (Spinner) findViewById(R.id.SKLI4);
        SLGT4 = (Spinner) findViewById(R.id.SLGT4);
        REC4 = (TextInputEditText) findViewById(R.id.REC4);

        SKTD5 = (Spinner) findViewById(R.id.SKTD5);
        SKLI5 = (Spinner) findViewById(R.id.SKLI5);
        SLGT5 = (Spinner) findViewById(R.id.SLGT5);
        REC5 = (TextInputEditText) findViewById(R.id.REC5);

        SKTD6 = (Spinner) findViewById(R.id.SKTD6);
        SKLI6 = (Spinner) findViewById(R.id.SKLI6);
        SLGT6 = (Spinner) findViewById(R.id.SLGT6);
        REC6 = (TextInputEditText) findViewById(R.id.REC6);

        klf = (TextView) findViewById(R.id.ktg_klf);
        back = (ImageButton) findViewById(R.id.back);
        SAVE = (Button) findViewById(R.id.SAVE);
        EDIT = (FloatingActionButton) findViewById(R.id.edit);
        UPLOAD = (FloatingActionButton) findViewById(R.id.upload);
        FAB_UPLOAD = (FABProgressCircle) findViewById(R.id.fab_upload);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.kordinat);
        back = (ImageButton) findViewById(R.id.back);
        INPUT = (LinearLayout) findViewById(R.id.INPUT);
        aksi = (RelativeLayout) findViewById(R.id.aksi);
        admin_recycler = (RecyclerViewBouncy) findViewById(R.id.admin_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        admin_recycler.setHasFixedSize(true);
        admin_recycler.setLayoutManager(new LinearLayoutManager(this));

        admin_recycler.setVisibility(View.GONE);
        new getDataAdmin().execute((Void[])null);

    }

    private void getInputValue(){

        SKTD_TXT = SKTD.getSelectedItem().toString();
        SKLI_TXT = SKLI.getSelectedItem().toString();
        SLGT_TXT = SLGT.getSelectedItem().toString();
        if(REC.getText().toString().equals("")){
            REC_TXT = "-";
        } else {
            REC_TXT = REC.getText().toString().trim();
        }

        SKTD2_TXT = SKTD2.getSelectedItem().toString();
        SKLI2_TXT = SKLI2.getSelectedItem().toString();
        SLGT2_TXT = SLGT2.getSelectedItem().toString();
        if(REC2.getText().toString().equals("")){
            REC_TXT2 = "-";
        } else {
            REC_TXT2 = REC2.getText().toString().trim();
        }

        SKTD3_TXT = SKTD3.getSelectedItem().toString();
        SKLI3_TXT = SKLI3.getSelectedItem().toString();
        SLGT3_TXT = SLGT3.getSelectedItem().toString();
        if(REC3.getText().toString().equals("")){
            REC_TXT3 = "-";
        } else {
            REC_TXT3 = REC3.getText().toString().trim();
        }

        SKTD4_TXT = SKTD4.getSelectedItem().toString();
        SKLI4_TXT = SKLI4.getSelectedItem().toString();
        SLGT4_TXT = SLGT4.getSelectedItem().toString();
        if(REC4.getText().toString().equals("")){
            REC_TXT4 = "-";
        } else {
            REC_TXT4 = REC4.getText().toString().trim();
        }

        SKTD5_TXT = SKTD5.getSelectedItem().toString();
        SKLI5_TXT = SKLI5.getSelectedItem().toString();
        SLGT5_TXT = SLGT5.getSelectedItem().toString();
        if(REC5.getText().toString().equals("")){
            REC_TXT5 = "-";
        } else {
            REC_TXT5 = REC5.getText().toString().trim();
        }

        SKTD6_TXT = SKTD6.getSelectedItem().toString();
        SKLI6_TXT = SKLI6.getSelectedItem().toString();
        SLGT6_TXT = SLGT6.getSelectedItem().toString();
        if(REC6.getText().toString().equals("")){
            REC_TXT6 = "-";
        } else {
            REC_TXT6 = REC6.getText().toString().trim();
        }

    }

    public void showPopup() {

        final Button yes;
        final TextView judul, sub_judul;
        final LoadingDots load;
        final ImageView img_success;
        myDialog.setContentView(R.layout.success_popup);
        yes = (Button) myDialog.findViewById(R.id.yes);
        judul = (TextView) myDialog.findViewById(R.id.judul);
        sub_judul = (TextView) myDialog.findViewById(R.id.sub_judul);
        load = (LoadingDots) myDialog.findViewById(R.id.load);
        img_success = (ImageView) myDialog.findViewById(R.id.img_success);

        if(op == true){
            judul.setText("Updating");
        }

        img_success.setVisibility(View.GONE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                judul.setText("Success");
                if(op == true){
                    sub_judul.setText("Data berhasil diupdate");
                } else {
                    sub_judul.setText("Data berhasil disimpan");
                }
                load.setVisibility(View.GONE);
                judul.setTextColor(getResources().getColor(R.color.Green500));
                img_success.setVisibility(View.VISIBLE);
                yes.setText("done");
                yes.setTextColor(getResources().getColor(R.color.Green500));

            }
        }, 2500);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

    }

    private void onClickListener(){

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInputValue();

                if(op == false){

                    ID = ISEG + "_ADM";

                    Boolean insertAdministrasi = databaseHelper.insertDataAdmin(ID, ISEG, SKTD_TXT, SKLI_TXT, SLGT_TXT, REC_TXT,
                            SKTD2_TXT, SKLI2_TXT, SLGT2_TXT, REC_TXT2, SKTD3_TXT, SKLI3_TXT, SLGT3_TXT, REC_TXT3, SKTD4_TXT, SKLI4_TXT, SLGT4_TXT, REC_TXT4,
                            SKTD5_TXT, SKLI5_TXT, SLGT5_TXT, REC_TXT5, SKTD6_TXT, SKLI6_TXT, SLGT6_TXT, REC_TXT6,"F");

                    if(insertAdministrasi != false){
                        showPopup();
                    }
                } else {
                    Boolean updateAdministrasi = databaseHelper.updateDataAdmin(ID, ISEG, SKTD_TXT, SKLI_TXT, SLGT_TXT, REC_TXT,
                            SKTD2_TXT, SKLI2_TXT, SLGT2_TXT, REC_TXT2, SKTD3_TXT, SKLI3_TXT, SLGT3_TXT, REC_TXT3, SKTD4_TXT, SKLI4_TXT, SLGT4_TXT, REC_TXT4,
                            SKTD5_TXT, SKLI5_TXT, SLGT5_TXT, REC_TXT5, SKTD6_TXT, SKLI6_TXT, SLGT6_TXT, REC_TXT6,"F");

                    if(updateAdministrasi != false){
                        showPopup();
                    }
                    op2 = false;
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                SKTD.setSelection(((ArrayAdapter<String>)SKTD.getAdapter()).getPosition(SKTD_TXT));
                SKLI.setSelection(((ArrayAdapter<String>)SKLI.getAdapter()).getPosition(SKLI_TXT));
                SLGT.setSelection(((ArrayAdapter<String>)SLGT.getAdapter()).getPosition(SLGT_TXT));

                SKTD2.setSelection(((ArrayAdapter<String>)SKTD2.getAdapter()).getPosition(SKTD2_TXT));
                SKLI2.setSelection(((ArrayAdapter<String>)SKLI2.getAdapter()).getPosition(SKLI2_TXT));
                SLGT2.setSelection(((ArrayAdapter<String>)SLGT2.getAdapter()).getPosition(SLGT2_TXT));

                SKTD3.setSelection(((ArrayAdapter<String>)SKTD3.getAdapter()).getPosition(SKTD3_TXT));
                SKLI3.setSelection(((ArrayAdapter<String>)SKLI3.getAdapter()).getPosition(SKLI3_TXT));
                SLGT3.setSelection(((ArrayAdapter<String>)SLGT3.getAdapter()).getPosition(SLGT3_TXT));

                SKTD4.setSelection(((ArrayAdapter<String>)SKTD4.getAdapter()).getPosition(SKTD4_TXT));
                SKLI4.setSelection(((ArrayAdapter<String>)SKLI4.getAdapter()).getPosition(SKLI4_TXT));
                SLGT4.setSelection(((ArrayAdapter<String>)SLGT4.getAdapter()).getPosition(SLGT4_TXT));

                SKTD5.setSelection(((ArrayAdapter<String>)SKTD5.getAdapter()).getPosition(SKTD5_TXT));
                SKLI5.setSelection(((ArrayAdapter<String>)SKLI5.getAdapter()).getPosition(SKLI5_TXT));
                SLGT5.setSelection(((ArrayAdapter<String>)SLGT5.getAdapter()).getPosition(SLGT5_TXT));

                SKTD6.setSelection(((ArrayAdapter<String>)SKTD6.getAdapter()).getPosition(SKTD6_TXT));
                SKLI6.setSelection(((ArrayAdapter<String>)SKLI6.getAdapter()).getPosition(SKLI6_TXT));
                SLGT6.setSelection(((ArrayAdapter<String>)SLGT6.getAdapter()).getPosition(SLGT6_TXT));

                SAVE.setText("Update");
                klf.setText("CANCEL");
                klf.setTextColor(getResources().getColor(R.color.Red600));
                klf.setBackground(getResources().getDrawable(R.drawable.back_white));
                klf.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                klf.setPadding(0, 0,20,0);
                klf.setAnimation(animation2);

                op = true;
                op2 = true;

            }
        });

        klf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(op2 == false){

                } else {
                    adminArrayList.clear();
                    adminArrayList.addAll(databaseHelper.allDataAdmin(ISEG));

                    adapter = new Administrasi_Adapter(AdministrasiActivity.this, adminArrayList);
                    admin_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    admin_recycler.setVisibility(View.VISIBLE);
                    admin_recycler.startAnimation(animation);
                    klf.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    klf.setPadding(0, 3,0,3);
                    klf.setTextColor(getResources().getColor(R.color.Gray900));
                    aksi.setVisibility(View.VISIBLE);
                    aksi.startAnimation(animation);

                    op2 = false;
                }
            }
        });

        FAB_UPLOAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekConnection() == false){
                    final Boolean[] ok = {false};
                    final Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                            .setAction("Next time", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ok[0] = true;
                                }
                            });

                    snackbar.setActionTextColor(getResources().getColor(R.color.Orange600));
                    snackbar.show();

                    if(ok[0] == true){
                        snackbar.dismiss();
                    }
                } else {
                    FAB_UPLOAD.show();
                    try{

                        new insertDataAdmin().execute((Void[])null);

                    } catch (Exception ex){

                        FAB_UPLOAD.hide();
                        final Boolean[] ok = {false};
                        final Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Upload failed", Snackbar.LENGTH_LONG)
                                .setAction("Close", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ok[0] = true;
                                    }
                                });

                        snackbar.setActionTextColor(getResources().getColor(R.color.Red600));
                        snackbar.show();

                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        admin_recycler.setOnScrollListener(new HideScrollingListener() {
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

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) aksi.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        aksi.animate().translationY(aksi.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();

    }

    private void showViews() {
        //toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //cari_ruas.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //data.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        aksi.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    private void refresh(){

        adminArrayList.clear();

        adminArrayList.addAll(databaseHelper.allDataAdmin(ISEG));

        if(adminArrayList.isEmpty()){
            admin_recycler.setVisibility(View.GONE);
        } else {
            adapter = new Administrasi_Adapter(AdministrasiActivity.this, adminArrayList);
            admin_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            admin_recycler.setVisibility(View.VISIBLE);
            admin_recycler.startAnimation(animation);
            klf.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            klf.setPadding(0, 3,0,3);
            klf.setTextColor(getResources().getColor(R.color.Gray900));
        }

    }

    public void showPopupWarning() {

        Button yes, no;
        myDialog.setContentView(R.layout.info_alert);
        yes = (Button) myDialog.findViewById(R.id.yes);
        no = (Button) myDialog.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasil = true;
                myDialog.dismiss();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 500);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

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

    private class insertDataAdmin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO administrasi_jalan (ID_ADMINISTRASI, ID_SEGMEN, UJI_ADMIN_a1, UJI_ADMIN_a2, UJI_ADMIN_a3," +
                        "CATATAN, UJI_ADMIN_b1, UJI_ADMIN_b2, UJI_ADMIN_b3, CATATAN2, UJI_ADMIN_c1, UJI_ADMIN_c2, UJI_ADMIN_c3, CATATAN3, UJI_ADMIN_d1, UJI_ADMIN_d2, UJI_ADMIN_d3, CATATAN4," +
                        "UJI_ADMIN_e1, UJI_ADMIN_e2, UJI_ADMIN_e3, CATATAN5, UJI_ADMIN_f1, UJI_ADMIN_f2, UJI_ADMIN_f3, CATATAN6, KTG_ADMIN_a, KTG_ADMIN_b, KTG_ADMIN_c, KTG_ADMIN_d, " +
                        "KTG_ADMIN_e, KTG_ADMIN_f, KTG_ADMINISTRASI) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+SKTD_TXT+"', '"+SKLI_TXT+"', '"+SLGT_TXT+"', '"+REC_TXT+"', '"+SKTD2_TXT+"', '"+SKLI2_TXT+"', '"+SLGT2_TXT+"', '"+REC_TXT2+"'," +
                        "'"+SKTD3_TXT+"', '"+SKLI3_TXT+"', '"+SLGT3_TXT+"', '"+REC_TXT3+"', '"+SKTD4_TXT+"', '"+SKLI4_TXT+"', '"+SLGT4_TXT+"', '"+REC_TXT4+"', '"+SKTD5_TXT+"', '"+SKLI5_TXT+"', " +
                        "'"+SLGT5_TXT+"', '"+REC_TXT5+"', '"+SKTD6_TXT+"', '"+SKLI6_TXT+"', '"+SLGT6_TXT+"', '"+REC_TXT6+"', '"+KTG_KLF1+"', '"+KTG_KLF2+"', '"+KTG_KLF3+"', '"+KTG_KLF4+"', " +
                        "'"+KTG_KLF5+"', '"+KTG_KLF6+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataAdmin(ID, ISEG, SKTD_TXT, SKLI_TXT, SLGT_TXT, REC_TXT,
                        SKTD2_TXT, SKLI2_TXT, SLGT2_TXT, REC_TXT2, SKTD3_TXT, SKLI3_TXT, SLGT3_TXT, REC_TXT3, SKTD4_TXT, SKLI4_TXT, SLGT4_TXT, REC_TXT4,
                        SKTD5_TXT, SKLI5_TXT, SLGT5_TXT, REC_TXT5, SKTD6_TXT, SKLI6_TXT, SLGT6_TXT, REC_TXT6,"F");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataAdmin().execute((Void[])null);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            FAB_UPLOAD.beginFinalAnimation();
            final Boolean[] ok = {false};
            final Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Upload success", Snackbar.LENGTH_LONG)
                    .setAction("Done", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ok[0] = true;
                        }
                    });

            snackbar.setActionTextColor(getResources().getColor(R.color.Blue600));
            snackbar.show();

            if(ok[0] == true){
                snackbar.dismiss();
            }
        }
    }

    private class updateDataAdmin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A21 SET ID_SEGMEN = '"+ISEG+"', UJI_ADMIN_a1 = '"+SKTD_TXT+"', UJI_ADMIN_a2 = '"+SKLI_TXT+"', UJI_ADMIN_a3 = '"+SLGT_TXT+"', CATATAN = '"+REC_TXT+"'," +
                        "UJI_ADMIN_b1 = '"+SKTD2_TXT+"', UJI_ADMIN_b2 = '"+SKLI2_TXT+"', UJI_ADMIN_b3 = '"+SLGT2_TXT+"', CATATAN2 = '"+REC_TXT2+"', UJI_ADMIN_c1 = '"+SKTD3_TXT+"', UJI_ADMIN_c2 = '"+SKLI3_TXT+"', " +
                        "UJI_ADMIN_c3 = '"+SLGT3_TXT+"', CATATAN = '"+REC_TXT3+"', UJI_ADMIN_d1 = '"+SKTD4_TXT+"', UJI_ADMIN_d2 = '"+SKLI4_TXT+"', UJI_ADMIN_d3 = '"+SLGT4_TXT+"', CATATAN = '"+REC_TXT4+"'," +
                        "UJI_ADMIN_e1 = '"+SKTD5_TXT+"', UJI_ADMIN_e2 = '"+SKLI5_TXT+"', UJI_ADMIN_e3 = '"+SLGT5_TXT+"', CATATAN = '"+REC_TXT5+"', UJI_ADMIN_f1 = '"+SKTD6_TXT+"', UJI_ADMIN_f2 = '"+SKLI6_TXT+"', " +
                        "UJI_ADMIN_f3 = '"+SLGT6_TXT+"', CATATAN = '"+REC_TXT6+"', KTG_ADMIN_a = '"+KTG_KLF1+"', KTG_ADMIN_b = '"+KTG_KLF2+"', KTG_ADMIN_c = '"+KTG_KLF3+"', KTG_ADMIN_d = '"+KTG_KLF4+"', " +
                        "KTG_ADMIN_e = '"+KTG_KLF5+"', KTG_ADMIN_f = '"+KTG_KLF6+"', KTG_ADMINISTRASI = '"+KTG_KLF+"' WHERE ID_ADMINISTRASI = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataAdmin(ID, ISEG, SKTD_TXT, SKLI_TXT, SLGT_TXT, REC_TXT,
                        SKTD2_TXT, SKLI2_TXT, SLGT2_TXT, REC_TXT2, SKTD3_TXT, SKLI3_TXT, SLGT3_TXT, REC_TXT3, SKTD4_TXT, SKLI4_TXT, SLGT4_TXT, REC_TXT4,
                        SKTD5_TXT, SKLI5_TXT, SLGT5_TXT, REC_TXT5, SKTD6_TXT, SKLI6_TXT, SLGT6_TXT, REC_TXT6,"F");

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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private class getDataAdmin extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(AdministrasiActivity.this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE).sizeRatio((float) 0.2).bgAlpha((float) 0.2)
                    .fadeColor(Color.DKGRAY).build();
            dialog.show();
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

                String select = "SELECT ID_ADMINISTRASI, ID_SEGMEN, UJI_ADMIN_a1, UJI_ADMIN_a2, UJI_ADMIN_a3, CATATAN, UJI_ADMIN_b1, " +
                        "UJI_ADMIN_b2, UJI_ADMIN_b3, CATATAN2, UJI_ADMIN_c1, UJI_ADMIN_c2, UJI_ADMIN_c3, CATATAN3, UJI_ADMIN_d1, UJI_ADMIN_d2, " +
                        "UJI_ADMIN_d3, CATATAN4, UJI_ADMIN_e1, UJI_ADMIN_e2, UJI_ADMIN_e3, CATATAN5, UJI_ADMIN_f1, UJI_ADMIN_f2, UJI_ADMIN_f3, CATATAN6 FROM administrasi_jalan WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getAdminUpload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

                        Boolean insertAdministrasi = databaseHelper.insertDataAdmin(data.getString("ID_ADMINISTRASI"), data.getString("ID_SEGMEN"),
                                data.getString("UJI_ADMIN_a1"), data.getString("UJI_ADMIN_a2"), data.getString("UJI_ADMIN_a3"), data.getString("CATATAN"),
                                data.getString("UJI_ADMIN_b1"), data.getString("UJI_ADMIN_b2"), data.getString("UJI_ADMIN_b3"), data.getString("CATATAN2"),
                                data.getString("UJI_ADMIN_c1"), data.getString("UJI_ADMIN_c2"), data.getString("UJI_ADMIN_c3"), data.getString("CATATAN3"),
                                data.getString("UJI_ADMIN_d1"), data.getString("UJI_ADMIN_d2"), data.getString("UJI_ADMIN_d3"), data.getString("CATATAN4"),
                                data.getString("UJI_ADMIN_e1"), data.getString("UJI_ADMIN_e2"), data.getString("UJI_ADMIN_e3"), data.getString("CATATAN5"),
                                data.getString("UJI_ADMIN_f1"), data.getString("UJI_ADMIN_f2"), data.getString("UJI_ADMIN_f3"), data.getString("CATATAN6"),"T");

                        if(insertAdministrasi == false){

                            databaseHelper.updateDataAdmin(data.getString("ID_ADMINISTRASI"), data.getString("ID_SEGMEN"),
                                    data.getString("UJI_ADMIN_a1"), data.getString("UJI_ADMIN_a2"), data.getString("UJI_ADMIN_a3"), data.getString("CATATAN"),
                                    data.getString("UJI_ADMIN_b1"), data.getString("UJI_ADMIN_b2"), data.getString("UJI_ADMIN_b3"), data.getString("CATATAN2"),
                                    data.getString("UJI_ADMIN_c1"), data.getString("UJI_ADMIN_c2"), data.getString("UJI_ADMIN_c3"), data.getString("CATATAN3"),
                                    data.getString("UJI_ADMIN_d1"), data.getString("UJI_ADMIN_d2"), data.getString("UJI_ADMIN_d3"), data.getString("CATATAN4"),
                                    data.getString("UJI_ADMIN_e1"), data.getString("UJI_ADMIN_e2"), data.getString("UJI_ADMIN_e3"), data.getString("CATATAN5"),
                                    data.getString("UJI_ADMIN_f1"), data.getString("UJI_ADMIN_f2"), data.getString("UJI_ADMIN_f3"), data.getString("CATATAN6"),"T");

                        }

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

            dialog.dismiss();

            adminArrayList.addAll(databaseHelper.allDataAdmin(ISEG));

            if(!adminArrayList.isEmpty()){
                admin_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new Administrasi_Adapter(AdministrasiActivity.this, adminArrayList);
                        admin_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        admin_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
