package com.pratama.kelayakanjalan;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import id.zelory.compressor.Compressor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
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
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import com.google.android.material.textfield.TextInputLayout;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class A51Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    private ImageButton back;
    public TextView klf;
    private TextInputEditText MBL1, MBL2, MBL3, MBL4, MBT1, MBT2, MBT3, MBT4, MBT5, MPR1,
            MPR2, MPR3, MPR4, MPR5, MPR6, MPR7;
    private Spinner SMBL1, SMBL2, SMBL3, SMBL4, SMBT1, SMBT2, SMBT3, SMBT4, SMBT5, SMPR1,
            SMPR2, SMPR3, SMPR4, SMPR5, SMPR6, SMPR7;
    private TextInputEditText ZBC1, ZBC2, ZBC3, REC;
    public String SMBL1_TXT, SMBL2_TXT, SMBL3_TXT, SMBL4_TXT, SMBT1_TXT, SMBT2_TXT, SMBT3_TXT,
            SMBT4_TXT, SMBT5_TXT, SMPR1_TXT, SMPR2_TXT, SMPR3_TXT, SMPR4_TXT, SMPR5_TXT, SMPR6_TXT,
            SMPR7_TXT;
    public double MBL1_TXT, MBL2_TXT, MBL3_TXT, MBL4_TXT, MBT1_TXT, MBT2_TXT, MBT3_TXT,
            MBT4_TXT, MBT5_TXT, MPR1_TXT, MPR2_TXT, MPR3_TXT, MPR4_TXT, MPR5_TXT, MPR6_TXT,
            MPR7_TXT, ZBC1_TXT, ZBC2_TXT, ZBC3_TXT;
    public int MBL_IN, MBL2_IN, MBL3_IN, MBL4_IN, MBT_IN, MBT2_IN, MBT3_IN, MBT4_IN, MBT5_IN, MPR_IN, MPR2_IN, MPR3_IN, MPR4_IN, MPR5_IN, MPR6_IN, MPR7_IN;
    public double DEV_MBL, DEV_MBL2, DEV_MBL3, DEV_MBL4, DEV_MBT, DEV_MBT2, DEV_MBT3, DEV_MBT4, DEV_MBT5, DEV_MPR, DEV_MPR2, DEV_MPR3, DEV_MPR4, DEV_MPR5, DEV_MPR6, DEV_MPR7, DEV_ZBC, DEV_ZBC2, DEV_ZBC3;
    public String KTG_MBLL, KTG_MBTT, KTG_MPRR, KTG_ZBCC, KTG_KLF;
    private Button SAVE;
    public FABProgressCircle FAB_UPLOAD;
    public FloatingActionButton EDIT, UPLOAD;
    private Boolean connected = false;
    private CoordinatorLayout coordinatorLayout;
    public ImageView FT1, FT2, FT3, FT4;
    private int REQ1 = 1, REQ2 = 2, REQ3 = 3, REQ4 = 4, REQ5 = 5, REQ6 = 6, REQ7 = 7, REQ8 = 8;
    public String REC_TXT, DIR1 = "-", DIR2 = "-", DIR3 = "-", DIR4 = "-";
    private double STD = 100;
    private Dialog myDialog;
    private List<A51_Class> a51ArrayList = new ArrayList<>();
    private A51_Adapter adapter;
    private RecyclerViewBouncy a51_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a51;
    private RelativeLayout aksi;
    private ImageButton take, open, take1, open1, take2, open2, take3, open3;
    private LinearLayout aksi2, aksi3, aksi4, aksi5;
    private ImageButton clear, clear1, clear2, clear3;
    private Boolean op = false, op2 = false;
    private Boolean hasil = false;
    private File mediaFile;
    private File compressedImage;
    private ACProgressFlower dialog;
    private static final String urlUpload = "http://proyekjalan.net/upload_1.php";
    private static final String urlUpload2 = "http://proyekjalan.net/upload_2.php";
    private static final String urlUpload3 = "http://proyekjalan.net/upload_3.php";
    private static final String urlUpload4 = "http://proyekjalan.net/upload_4.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a51);

        init();
        onSelectedItem();
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

        klf = (TextView) findViewById(R.id.ktg_klf);
        SMBL1 = (Spinner) findViewById(R.id.SMBL1);
        SMBL2 = (Spinner) findViewById(R.id.SMBL2);
        SMBL3 = (Spinner) findViewById(R.id.SMBL3);
        SMBL4 = (Spinner) findViewById(R.id.SMBL4);
        SMBT1 = (Spinner) findViewById(R.id.SMBT1);
        SMBT2 = (Spinner) findViewById(R.id.SMBT2);
        SMBT3 = (Spinner) findViewById(R.id.SMBT3);
        SMBT4 = (Spinner) findViewById(R.id.SMBT4);
        SMBT5 = (Spinner) findViewById(R.id.SMBT5);
        SMPR1 = (Spinner) findViewById(R.id.SMPR1);
        SMPR2 = (Spinner) findViewById(R.id.SMPR2);
        SMPR3 = (Spinner) findViewById(R.id.SMPR3);
        SMPR4 = (Spinner) findViewById(R.id.SMPR4);
        SMPR5 = (Spinner) findViewById(R.id.SMPR5);
        SMPR6 = (Spinner) findViewById(R.id.SMPR6);
        SMPR7 = (Spinner) findViewById(R.id.SMPR7);
        MBL1 = (TextInputEditText) findViewById(R.id.MBL1);
        MBL2 = (TextInputEditText) findViewById(R.id.MBL2);
        MBL3 = (TextInputEditText) findViewById(R.id.MBL3);
        MBL4 = (TextInputEditText) findViewById(R.id.MBL4);
        MBT1 = (TextInputEditText) findViewById(R.id.MBT1);
        MBT2 = (TextInputEditText) findViewById(R.id.MBT2);
        MBT3 = (TextInputEditText) findViewById(R.id.MBT3);
        MBT4 = (TextInputEditText) findViewById(R.id.MBT4);
        MBT5 = (TextInputEditText) findViewById(R.id.MBT5);
        MPR1 = (TextInputEditText) findViewById(R.id.MPR1);
        MPR2 = (TextInputEditText) findViewById(R.id.MPR2);
        MPR3 = (TextInputEditText) findViewById(R.id.MPR3);
        MPR4 = (TextInputEditText) findViewById(R.id.MPR4);
        MPR5 = (TextInputEditText) findViewById(R.id.MPR5);
        MPR6 = (TextInputEditText) findViewById(R.id.MPR6);
        MPR7 = (TextInputEditText) findViewById(R.id.MPR7);
        ZBC1 = (TextInputEditText) findViewById(R.id.ZBC1);
        ZBC2 = (TextInputEditText) findViewById(R.id.ZBC2);
        ZBC3 = (TextInputEditText) findViewById(R.id.ZBC3);
        REC = (TextInputEditText) findViewById(R.id.REC);
        SAVE = (Button) findViewById(R.id.SAVE);
        take = (ImageButton) findViewById(R.id.take);
        open = (ImageButton) findViewById(R.id.open);
        aksi2 = (LinearLayout) findViewById(R.id.aksi2);
        clear = (ImageButton) findViewById(R.id.clear);
        take1 = (ImageButton) findViewById(R.id.take1);
        open1 = (ImageButton) findViewById(R.id.open1);
        aksi3 = (LinearLayout) findViewById(R.id.aksi3);
        clear1 = (ImageButton) findViewById(R.id.clear1);
        take2 = (ImageButton) findViewById(R.id.take2);
        open2 = (ImageButton) findViewById(R.id.open2);
        aksi4 = (LinearLayout) findViewById(R.id.aksi4);
        clear2 = (ImageButton) findViewById(R.id.clear2);
        take3 = (ImageButton) findViewById(R.id.take3);
        open3 = (ImageButton) findViewById(R.id.open3);
        aksi5 = (LinearLayout) findViewById(R.id.aksi5);
        clear3 = (ImageButton) findViewById(R.id.clear3);
        EDIT = (FloatingActionButton) findViewById(R.id.edit);
        UPLOAD = (FloatingActionButton) findViewById(R.id.upload);
        FAB_UPLOAD = (FABProgressCircle) findViewById(R.id.fab_upload);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.kordinat);
        back = (ImageButton) findViewById(R.id.back);
        FT1 = (ImageView) findViewById(R.id.FT1);
        FT2 = (ImageView) findViewById(R.id.FT2);
        FT3 = (ImageView) findViewById(R.id.FT3);
        FT4 = (ImageView) findViewById(R.id.FT4);
        INPUT = (LinearLayout) findViewById(R.id.INPUT);
        aksi = (RelativeLayout) findViewById(R.id.aksi);
        //recycler_a51 = (NestedScrollView) findViewById(R.id.recycler_a51);
        a51_recycler = (RecyclerViewBouncy) findViewById(R.id.a51_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        clear2.setVisibility(View.GONE);
        clear3.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a51_recycler.setHasFixedSize(true);
        a51_recycler.setLayoutManager(new LinearLayoutManager(this));

        a51_recycler.setVisibility(View.GONE);
        new getDataA51().execute((Void[])null);

    }

    private void onSelectedItem(){

        SMBL1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBL1.setEnabled(true);
                    MBL1.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBL1.setEnabled(false);
                    MBL1.setText("0");
                } else{
                    MBL1.setEnabled(false);
                    MBL1.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBL2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBL2.setEnabled(true);
                    MBL2.setText("100");

                } else if(selected.equals("Tidak Ada")) {
                    MBL2.setEnabled(false);
                    MBL2.setText("0");
                } else{
                    MBL2.setEnabled(false);
                    MBL2.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBL3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBL3.setEnabled(true);
                    MBL3.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBL3.setEnabled(false);
                    MBL3.setText("0");
                } else{
                    MBL3.setEnabled(false);
                    MBL3.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBL4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBL4.setEnabled(true);
                    MBL4.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBL4.setEnabled(false);
                    MBL4.setText("0");
                } else{
                    MBL4.setEnabled(false);
                    MBL4.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBT1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBT1.setEnabled(true);
                    MBT1.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBT1.setEnabled(false);
                    MBT1.setText("0");
                } else{
                    MBT1.setEnabled(false);
                    MBT1.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBT2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBT2.setEnabled(true);
                    MBT2.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBT2.setEnabled(false);
                    MBT2.setText("0");
                } else{
                    MBT2.setEnabled(false);
                    MBT2.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBT3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBT3.setEnabled(true);
                    MBT3.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBT3.setEnabled(false);
                    MBT3.setText("0");
                } else{
                    MBT3.setEnabled(false);
                    MBT3.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBT4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBT4.setEnabled(true);
                    MBT4.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBT4.setEnabled(false);
                    MBT4.setText("0");
                } else{
                    MBT4.setEnabled(false);
                    MBT4.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMBT5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MBT5.setEnabled(true);
                    MBT5.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MBT5.setEnabled(false);
                    MBT5.setText("0");
                } else{
                    MBT5.setEnabled(false);
                    MBT5.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMPR1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MPR1.setEnabled(true);
                    MPR1.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MPR1.setEnabled(false);
                    MPR1.setText("0");
                } else{
                    MPR1.setEnabled(false);
                    MPR1.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMPR2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MPR2.setEnabled(true);
                    MPR2.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MPR2.setEnabled(false);
                    MPR2.setText("0");
                } else{
                    MPR2.setEnabled(false);
                    MPR2.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMPR3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MPR3.setEnabled(true);
                    MPR3.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MPR3.setEnabled(false);
                    MPR3.setText("0");
                } else{
                    MPR3.setEnabled(false);
                    MPR3.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMPR4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MPR4.setEnabled(true);
                    MPR4.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MPR4.setEnabled(false);
                    MPR4.setText("0");
                } else{
                    MPR4.setEnabled(false);
                    MPR4.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMPR5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MPR5.setEnabled(true);
                    MPR5.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MPR5.setEnabled(false);
                    MPR5.setText("0");
                } else{
                    MPR5.setEnabled(false);
                    MPR5.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMPR6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MPR6.setEnabled(true);
                    MPR6.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MPR6.setEnabled(false);
                    MPR6.setText("0");
                } else{
                    MPR6.setEnabled(false);
                    MPR6.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SMPR7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    MPR7.setEnabled(true);
                    MPR7.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    MPR7.setEnabled(false);
                    MPR7.setText("0");
                } else{
                    MPR7.setEnabled(false);
                    MPR7.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getInputValue(){

        SMBL1_TXT = SMBL1.getSelectedItem().toString();
        SMBL2_TXT = SMBL2.getSelectedItem().toString();
        SMBL3_TXT = SMBL3.getSelectedItem().toString();
        SMBL4_TXT = SMBL4.getSelectedItem().toString();

        SMBT1_TXT = SMBT1.getSelectedItem().toString();
        SMBT2_TXT = SMBT2.getSelectedItem().toString();
        SMBT3_TXT = SMBT3.getSelectedItem().toString();
        SMBT4_TXT = SMBT4.getSelectedItem().toString();
        SMBT5_TXT = SMBT5.getSelectedItem().toString();

        SMPR1_TXT = SMPR1.getSelectedItem().toString();
        SMPR2_TXT = SMPR2.getSelectedItem().toString();
        SMPR3_TXT = SMPR3.getSelectedItem().toString();
        SMPR4_TXT = SMPR4.getSelectedItem().toString();
        SMPR5_TXT = SMPR5.getSelectedItem().toString();
        SMPR6_TXT = SMPR6.getSelectedItem().toString();
        SMPR7_TXT = SMPR7.getSelectedItem().toString();

        if(ZBC1.getText().toString().equals("")){
            ZBC1_TXT = -1;
        } else {
            ZBC1_TXT = Double.parseDouble(ZBC1.getText().toString());
        }

        if(ZBC2.getText().toString().equals("")){
            ZBC2_TXT = -1;
        } else {
            ZBC2_TXT = Double.parseDouble(ZBC2.getText().toString());
        }

        if(ZBC3.getText().toString().equals("")){
            ZBC3_TXT = -1;
        } else {
            ZBC3_TXT = Double.parseDouble(ZBC3.getText().toString());
        }

        if(MBL1.getText().toString().equals("")){
            MBL1_TXT = -1;
        } else {
            MBL1_TXT = Double.parseDouble(MBL1.getText().toString());
        }

        if(MBL2.getText().toString().equals("")){
            MBL2_TXT = -1;
        } else {
            MBL2_TXT = Double.parseDouble(MBL2.getText().toString());
        }

        if(MBL3.getText().toString().equals("")){
            MBL3_TXT = -1;
        } else {
            MBL3_TXT = Double.parseDouble(MBL3.getText().toString());
        }

        if(MBL4.getText().toString().equals("")){
            MBL4_TXT = -1;
        } else {
            MBL4_TXT = Double.parseDouble(MBL4.getText().toString());
        }

        if(MBT1.getText().toString().equals("")){
            MBT1_TXT = -1;
        } else {
            MBT1_TXT = Double.parseDouble(MBT1.getText().toString());
        }

        if(MBT2.getText().toString().equals("")){
            MBT2_TXT = -1;
        } else {
            MBT2_TXT = Double.parseDouble(MBT2.getText().toString());
        }

        if(MBT3.getText().toString().equals("")){
            MBT3_TXT = -1;
        } else {
            MBT3_TXT = Double.parseDouble(MBT3.getText().toString());
        }

        if(MBT4.getText().toString().equals("")){
            MBT4_TXT = -1;
        } else {
            MBT4_TXT = Double.parseDouble(MBT4.getText().toString());
        }

        if(MBT5.getText().toString().equals("")){
            MBT5_TXT = -1;
        } else {
            MBT5_TXT = Double.parseDouble(MBT5.getText().toString());
        }

        if(MPR1.getText().toString().equals("")){
            MPR1_TXT = -1;
        } else {
            MPR1_TXT = Double.parseDouble(MPR1.getText().toString());
        }

        if(MPR2.getText().toString().equals("")){
            MPR2_TXT = -1;
        } else {
            MPR2_TXT = Double.parseDouble(MPR2.getText().toString());
        }

        if(MPR3.getText().toString().equals("")){
            MPR3_TXT = -1;
        } else {
            MPR3_TXT = Double.parseDouble(MPR3.getText().toString());
        }

        if(MPR4.getText().toString().equals("")){
            MPR4_TXT = -1;
        } else {
            MPR4_TXT = Double.parseDouble(MPR4.getText().toString());
        }

        if(MPR5.getText().toString().equals("")){
            MPR5_TXT = -1;
        } else {
            MPR5_TXT = Double.parseDouble(MPR5.getText().toString());
        }

        if(MPR6.getText().toString().equals("")){
            MPR6_TXT = -1;
        } else {
            MPR6_TXT = Double.parseDouble(MPR6.getText().toString());
        }

        if(MPR7.getText().toString().equals("")){
            MPR7_TXT = -1;
        } else {
            MPR7_TXT = Double.parseDouble(MPR7.getText().toString());
        }

        if(REC.getText().toString().equals("")){
            REC_TXT = "-";
        } else {
            REC_TXT = REC.getText().toString().trim();
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
                MBL1.setText("");
                MBL2.setText("");
                MBL3.setText("");
                MBL4.setText("");
                MBT1.setText("");
                MBT2.setText("");
                MBT3.setText("");
                MBT4.setText("");
                MBT5.setText("");
                MPR1.setText("");
                MPR2.setText("");
                MPR3.setText("");
                MPR4.setText("");
                MPR5.setText("");
                MPR6.setText("");
                MPR7.setText("");
                ZBC1.setText("");
                ZBC2.setText("");
                ZBC3.setText("");
                REC.setText("");
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

    }

    private void onClickListener(){

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_1.png";
                mediaFile = new File(path);
                Uri outputFileUri = Uri.fromFile(mediaFile);
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                startActivityForResult(intent, REQ1);

            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ5);
            }
        });

        take1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_2.png";
                mediaFile = new File(path);
                Uri outputFileUri = Uri.fromFile(mediaFile);
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri);

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                startActivityForResult(intent, REQ2);
            }
        });

        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ6);
            }
        });

        take2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_3.png";
                mediaFile = new File(path);
                Uri outputFileUri = Uri.fromFile(mediaFile);
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri);

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                startActivityForResult(intent, REQ3);
            }
        });

        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ7);
            }
        });

        take3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_4.png";
                mediaFile = new File(path);
                Uri outputFileUri = Uri.fromFile(mediaFile);
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri);

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                startActivityForResult(intent, REQ4);
            }
        });

        open3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ8);
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInputValue();

                if(op == false){

                    ID = ISEG + "_A51";

                    Boolean insertUjiA51 = databaseHelper.insertDataA51(ID, ISEG, SMBL1_TXT, SMBL2_TXT, SMBL3_TXT,
                            SMBL4_TXT, SMBT1_TXT, SMBT2_TXT, SMBT3_TXT, SMBT4_TXT, SMBT5_TXT, SMPR1_TXT, SMPR2_TXT,
                            SMPR3_TXT, SMPR4_TXT, SMPR5_TXT, SMPR6_TXT, SMPR7_TXT, MBL1_TXT, MBL2_TXT, MBL3_TXT,
                            MBL4_TXT, MBT1_TXT, MBT2_TXT, MBT3_TXT, MBT4_TXT, MBT5_TXT, MPR1_TXT, MPR2_TXT,
                            MPR3_TXT, MPR4_TXT, MPR5_TXT, MPR6_TXT, MPR7_TXT, ZBC1_TXT, ZBC2_TXT, ZBC3_TXT,
                            REC_TXT, DIR1, DIR2, DIR3, DIR4, "F");

                    if(insertUjiA51 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA51 = databaseHelper.updateDataA51(ID, ISEG, SMBL1_TXT, SMBL2_TXT, SMBL3_TXT,
                            SMBL4_TXT, SMBT1_TXT, SMBT2_TXT, SMBT3_TXT, SMBT4_TXT, SMBT5_TXT, SMPR1_TXT, SMPR2_TXT,
                            SMPR3_TXT, SMPR4_TXT, SMPR5_TXT, SMPR6_TXT, SMPR7_TXT, MBL1_TXT, MBL2_TXT, MBL3_TXT,
                            MBL4_TXT, MBT1_TXT, MBT2_TXT, MBT3_TXT, MBT4_TXT, MBT5_TXT, MPR1_TXT, MPR2_TXT,
                            MPR3_TXT, MPR4_TXT, MPR5_TXT, MPR6_TXT, MPR7_TXT, ZBC1_TXT, ZBC2_TXT, ZBC3_TXT,
                            REC_TXT, DIR1, DIR2, DIR3, DIR4, "F");

                    if(updateUjiA51 != false){
                        showPopup();
                    }
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a51_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                SMBL1.setSelection(((ArrayAdapter<String>)SMBL1.getAdapter()).getPosition(SMBL1_TXT));

                SMBL2.setSelection(((ArrayAdapter<String>)SMBL2.getAdapter()).getPosition(SMBL2_TXT));

                SMBL3.setSelection(((ArrayAdapter<String>)SMBL3.getAdapter()).getPosition(SMBL3_TXT));

                SMBL4.setSelection(((ArrayAdapter<String>)SMBL4.getAdapter()).getPosition(SMBL4_TXT));

                SMBT1.setSelection(((ArrayAdapter<String>)SMBT1.getAdapter()).getPosition(SMBT1_TXT));

                SMBT2.setSelection(((ArrayAdapter<String>)SMBT2.getAdapter()).getPosition(SMBT2_TXT));

                SMBT3.setSelection(((ArrayAdapter<String>)SMBT3.getAdapter()).getPosition(SMBT3_TXT));

                SMBT4.setSelection(((ArrayAdapter<String>)SMBT4.getAdapter()).getPosition(SMBT4_TXT));

                SMBT5.setSelection(((ArrayAdapter<String>)SMBT5.getAdapter()).getPosition(SMBT5_TXT));

                SMPR1.setSelection(((ArrayAdapter<String>)SMPR1.getAdapter()).getPosition(SMPR1_TXT));

                SMPR2.setSelection(((ArrayAdapter<String>)SMPR2.getAdapter()).getPosition(SMPR2_TXT));

                SMPR3.setSelection(((ArrayAdapter<String>)SMPR3.getAdapter()).getPosition(SMPR3_TXT));

                SMPR4.setSelection(((ArrayAdapter<String>)SMPR4.getAdapter()).getPosition(SMPR4_TXT));

                SMPR5.setSelection(((ArrayAdapter<String>)SMPR5.getAdapter()).getPosition(SMPR5_TXT));

                SMPR6.setSelection(((ArrayAdapter<String>)SMPR6.getAdapter()).getPosition(SMPR6_TXT));

                SMPR7.setSelection(((ArrayAdapter<String>)SMPR7.getAdapter()).getPosition(SMPR7_TXT));

                if(MBL1_TXT == -1){
                    MBL1.setText("");
                } else {
                    MBL1.setText(String.valueOf(MBL1_TXT));
                }

                if(MBL2_TXT == -1){
                    MBL2.setText("");
                } else {
                    MBL2.setText(String.valueOf(MBL2_TXT));
                }

                if(MBL3_TXT == -1){
                    MBL3.setText("");
                } else {
                    MBL3.setText(String.valueOf(MBL3_TXT));
                }

                if(MBL4_TXT == -1){
                    MBL4.setText("");
                } else {
                    MBL4.setText(String.valueOf(MBL4_TXT));
                }

                if(MBT1_TXT == -1){
                    MBT1.setText("");
                } else {
                    MBT1.setText(String.valueOf(MBT1_TXT));
                }

                if(MBT2_TXT == -1){
                    MBT2.setText("");
                } else {
                    MBT2.setText(String.valueOf(MBT2_TXT));
                }

                if(MBT3_TXT == -1){
                    MBT3.setText("");
                } else {
                    MBT3.setText(String.valueOf(MBT3_TXT));
                }

                if(MBT4_TXT == -1){
                    MBT4.setText("");
                } else {
                    MBT4.setText(String.valueOf(MBT4_TXT));
                }

                if(MBT5_TXT == -1){
                    MBT5.setText("");
                } else {
                    MBT5.setText(String.valueOf(MBT5_TXT));
                }

                if(MPR1_TXT == -1){
                    MPR1.setText("");
                } else {
                    MPR1.setText(String.valueOf(MPR1_TXT));
                }

                if(MPR2_TXT == -1){
                    MPR2.setText("");
                } else {
                    MPR2.setText(String.valueOf(MPR2_TXT));
                }

                if(MPR3_TXT == -1){
                    MPR3.setText("");
                } else {
                    MPR3.setText(String.valueOf(MPR3_TXT));
                }

                if(MPR4_TXT == -1){
                    MPR4.setText("");
                } else {
                    MPR4.setText(String.valueOf(MPR4_TXT));
                }

                if(MPR5_TXT == -1){
                    MPR5.setText("");
                } else {
                    MPR5.setText(String.valueOf(MPR5_TXT));
                }

                if(MPR6_TXT == -1){
                    MPR6.setText("");
                } else {
                    MPR6.setText(String.valueOf(MPR6_TXT));
                }

                if(MPR7_TXT == -1){
                    MPR7.setText("");
                } else {
                    MPR7.setText(String.valueOf(MPR7_TXT));
                }

                if(ZBC1_TXT == -1){
                    ZBC1.setText("");
                } else {
                    ZBC1.setText(String.valueOf(ZBC1_TXT));
                }

                if(ZBC2_TXT == -1){
                    ZBC2.setText("");
                } else {
                    ZBC2.setText(String.valueOf(ZBC2_TXT));
                }

                if(ZBC3_TXT == -1){
                    ZBC3.setText("");
                } else {
                    ZBC3.setText(String.valueOf(ZBC3_TXT));
                }

                if(REC_TXT.equals("-")){
                    REC.setText("");
                } else {
                    REC.setText(REC_TXT);
                }

                if(DIR1.equals("-")){

                } else {
                    clear.setVisibility(View.VISIBLE);
                    aksi2.setVisibility(View.GONE);
                    FT1.setImageBitmap(BitmapFactory.decodeFile(DIR1));
                }

                if(DIR2.equals("-")){

                } else {
                    clear1.setVisibility(View.VISIBLE);
                    aksi3.setVisibility(View.GONE);
                    FT2.setImageBitmap(BitmapFactory.decodeFile(DIR2));
                }

                if(DIR3.equals("-")){

                } else {
                    clear2.setVisibility(View.VISIBLE);
                    aksi4.setVisibility(View.GONE);
                    FT3.setImageBitmap(BitmapFactory.decodeFile(DIR3));
                }

                if(DIR4.equals("-")){

                } else {
                    clear3.setVisibility(View.VISIBLE);
                    aksi5.setVisibility(View.GONE);
                    FT4.setImageBitmap(BitmapFactory.decodeFile(DIR4));
                }

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

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                DIR1 = "-";
                FT1.setImageBitmap(null);
                aksi2.setVisibility(View.VISIBLE);
                aksi2.startAnimation(animation);
                clear.startAnimation(animation2);
                clear.setVisibility(View.GONE);
            }
        });

        clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                DIR2 = "-";
                FT2.setImageBitmap(null);
                aksi3.setVisibility(View.VISIBLE);
                aksi3.startAnimation(animation);
                clear1.startAnimation(animation2);
                clear1.setVisibility(View.GONE);
            }
        });

        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                DIR3 = "-";
                FT3.setImageBitmap(null);
                aksi4.setVisibility(View.VISIBLE);
                aksi4.startAnimation(animation);
                clear2.startAnimation(animation2);
                clear2.setVisibility(View.GONE);
            }
        });

        clear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                DIR4 = "-";
                FT4.setImageBitmap(null);
                aksi5.setVisibility(View.VISIBLE);
                aksi5.startAnimation(animation);
                clear3.startAnimation(animation2);
                clear3.setVisibility(View.GONE);
            }
        });

        klf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(op2 == false){

                } else {
                    a51ArrayList.clear();
                    a51ArrayList.addAll(databaseHelper.allDataA51(ISEG));

                    adapter = new A51_Adapter(A51Activity.this, a51ArrayList);
                    a51_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a51_recycler.setVisibility(View.VISIBLE);
                    a51_recycler.startAnimation(animation);
                    ZBC1.setText("");
                    ZBC2.setText("");
                    ZBC3.setText("");
                    REC.setText("");
                    MBL1.setText("");
                    MBL2.setText("");
                    MBL3.setText("");
                    MBL4.setText("");
                    MBT1.setText("");
                    MBT2.setText("");
                    MBT3.setText("");
                    MBT4.setText("");
                    MBT5.setText("");
                    MPR1.setText("");
                    MPR2.setText("");
                    MPR3.setText("");
                    MPR4.setText("");
                    MPR5.setText("");
                    MPR6.setText("");
                    MPR7.setText("");
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

                        new insertDataA51().execute((Void[])null);

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

        a51_recycler.setOnScrollListener(new HideScrollingListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });

        FT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A51Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A51Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A51Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A51Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR4);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        File mediaStorageDir = null;
        String state = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_2.png");
        }

        File mediaStorageDir4 = null;
        String state4 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_3.png");
        } else {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_3.png");
        }

        File mediaStorageDir5 = null;
        String state5 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_4.png");
        } else {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/"+ISEG+"_4.png");
        }

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ1) {

                if(mediaFile.exists()){

                    try {
                        compressedImage = new Compressor(this)
                                .setMaxWidth(480)
                                .setMaxHeight(360)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                                .compressToFile(mediaFile);

                        DIR1 = compressedImage.getAbsolutePath();

                        mediaFile.delete();

                        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                        aksi2.setVisibility(View.GONE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clear.setVisibility(View.VISIBLE);
                                clear.startAnimation(animation);
                            }
                        }, 1000);
                        FT1.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if(requestCode == REQ2){

                if(mediaFile.exists()){

                    try {
                        compressedImage = new Compressor(this)
                                .setMaxWidth(480)
                                .setMaxHeight(360)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                                .compressToFile(mediaFile);

                        DIR2 = compressedImage.getAbsolutePath();

                        mediaFile.delete();

                        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                        aksi3.setVisibility(View.GONE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clear1.setVisibility(View.VISIBLE);
                                clear1.startAnimation(animation);
                            }
                        }, 1000);
                        FT2.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if(requestCode == REQ3){

                if(mediaFile.exists()){

                    try {
                        compressedImage = new Compressor(this)
                                .setMaxWidth(480)
                                .setMaxHeight(360)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                                .compressToFile(mediaFile);

                        DIR3 = compressedImage.getAbsolutePath();

                        mediaFile.delete();

                        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                        aksi4.setVisibility(View.GONE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clear2.setVisibility(View.VISIBLE);
                                clear2.startAnimation(animation);
                            }
                        }, 1000);
                        FT3.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if(requestCode == REQ4){

                if(mediaFile.exists()){

                    try {
                        compressedImage = new Compressor(this)
                                .setMaxWidth(480)
                                .setMaxHeight(360)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                                .compressToFile(mediaFile);

                        DIR4 = compressedImage.getAbsolutePath();

                        mediaFile.delete();

                        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                        aksi5.setVisibility(View.GONE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clear3.setVisibility(View.VISIBLE);
                                clear3.startAnimation(animation);
                            }
                        }, 1000);
                        FT4.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if(requestCode == REQ5){

                Uri dataFoto = data.getData();
                final String path = getPathFromURI(dataFoto);
                if(path != null){
                    mediaFile = new File(path);

                    try{

                        FileChannel src = new FileInputStream(mediaFile).getChannel();
                        FileChannel dst = new FileOutputStream(mediaStorageDir2).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();

                    } catch (Exception e){
                        Log.e("Error copy ", e.toString());
                    }

                }

                try {
                    compressedImage = new Compressor(this)
                            .setMaxWidth(480)
                            .setMaxHeight(360)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                            .compressToFile(mediaStorageDir2);

                    DIR1 = compressedImage.getAbsolutePath();

                    mediaStorageDir2.delete();

                    final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                    aksi2.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear.setVisibility(View.VISIBLE);
                            clear.startAnimation(animation);
                        }
                    }, 1000);
                    FT1.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if(requestCode == REQ6){

                Uri dataFoto = data.getData();
                final String path = getPathFromURI(dataFoto);
                if(path != null){
                    mediaFile = new File(path);

                    try{

                        FileChannel src = new FileInputStream(mediaFile).getChannel();
                        FileChannel dst = new FileOutputStream(mediaStorageDir3).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();

                    } catch (Exception e){
                        Log.e("Error copy ", e.toString());
                    }

                }

                try {
                    compressedImage = new Compressor(this)
                            .setMaxWidth(480)
                            .setMaxHeight(360)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                            .compressToFile(mediaStorageDir3);

                    DIR2 = compressedImage.getAbsolutePath();

                    mediaStorageDir3.delete();

                    final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                    aksi3.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear1.setVisibility(View.VISIBLE);
                            clear1.startAnimation(animation);
                        }
                    }, 1000);
                    FT2.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if(requestCode == REQ7){

                Uri dataFoto = data.getData();
                final String path = getPathFromURI(dataFoto);
                if(path != null){
                    mediaFile = new File(path);

                    try{

                        FileChannel src = new FileInputStream(mediaFile).getChannel();
                        FileChannel dst = new FileOutputStream(mediaStorageDir4).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();

                    } catch (Exception e){
                        Log.e("Error copy ", e.toString());
                    }

                }

                try {
                    compressedImage = new Compressor(this)
                            .setMaxWidth(480)
                            .setMaxHeight(360)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                            .compressToFile(mediaStorageDir4);

                    DIR3 = compressedImage.getAbsolutePath();

                    mediaStorageDir4.delete();

                    final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                    aksi4.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear2.setVisibility(View.VISIBLE);
                            clear2.startAnimation(animation);
                        }
                    }, 1000);
                    FT3.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {

                Uri dataFoto = data.getData();
                final String path = getPathFromURI(dataFoto);
                if(path != null){
                    mediaFile = new File(path);

                    try{

                        FileChannel src = new FileInputStream(mediaFile).getChannel();
                        FileChannel dst = new FileOutputStream(mediaStorageDir5).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();

                    } catch (Exception e){
                        Log.e("Error copy ", e.toString());
                    }

                }

                try {
                    compressedImage = new Compressor(this)
                            .setMaxWidth(480)
                            .setMaxHeight(360)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                            .compressToFile(mediaStorageDir5);

                    DIR4 = compressedImage.getAbsolutePath();

                    mediaStorageDir5.delete();

                    final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                    aksi5.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear3.setVisibility(View.VISIBLE);
                            clear3.startAnimation(animation);
                        }
                    }, 1000);
                    FT4.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private void refresh(){

        a51ArrayList.clear();

        a51ArrayList.addAll(databaseHelper.allDataA51(ISEG));

        if(a51ArrayList.isEmpty()){
            a51_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A51_Adapter(A51Activity.this, a51ArrayList);
            a51_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a51_recycler.setVisibility(View.VISIBLE);
            a51_recycler.startAnimation(animation);
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

    private class insertDataA51 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A51 (ID_A51, ID_SEGMEN, UJI_A51_a1, UJI_A51_a2, UJI_A51_a3, UJI_A51_a4, UJI_A51_b1, UJI_A51_b2, UJI_A51_b3, UJI_A51_b4, UJI_A51_b5, " +
                        "UJI_A51_c1, UJI_A51_c2, UJI_A51_c3, UJI_A51_c4, UJI_A51_c5, UJI_A51_c6, UJI_A51_c7, UJI_A51_a11, UJI_A51_a22, UJI_A51_a33, UJI_A51_a44, UJI_A51_b11, UJI_A51_b22, " +
                        "UJI_A51_b33, UJI_A51_b44, UJI_A51_b55, UJI_A51_c11, UJI_A51_c22, UJI_A51_c33, UJI_A51_c44, UJI_A51_c55, UJI_A51_c66, UJI_A51_c77, UJI_A51_d1, UJI_A51_d2, UJI_A51_d3, CATATAN, " +
                        "DEV_A51_a1, DEV_A51_a2, DEV_A51_a3, DEV_A51_a4, DEV_A51_b1, DEV_A51_b2, DEV_A51_b3, DEV_A51_b4, DEV_A51_b5, DEV_A51_c1, DEV_A51_c2, " +
                        "DEV_A51_c3, DEV_A51_c4, DEV_A51_c5, DEV_A51_c6, DEV_A51_c7, DEV_A51_d1, DEV_A51_d2, DEV_A51_d3, KTG_A51_a, KTG_A51_b, KTG_A51_c, KTG_A51_d, KTG_A51) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+MBL_IN+"', '"+MBL2_IN+"', '"+MBL3_IN+"', '"+MBL4_IN+"', '"+MBT_IN+"', '"+MBT2_IN+"', '"+MBT3_IN+"', '"+MBT4_IN+"', " +
                        "'"+MBT5_IN+"', '"+MPR_IN+"', '"+MPR2_IN+"', '"+MPR3_IN+"', '"+MPR4_IN+"', '"+MPR5_IN+"', '"+MPR6_IN+"', '"+MPR7_IN+"', '"+MBL1_TXT+"', '"+MBL2_TXT+"', " +
                        "'"+MBL3_TXT+"', '"+MBL4_TXT+"', '"+MBT1_TXT+"', '"+MBT2_TXT+"', '"+MBT3_TXT+"', '"+MBT4_TXT+"', '"+MBT5_TXT+"', '"+MPR1_TXT+"', '"+MPR2_TXT+"', '"+MPR3_TXT+"', '"+MPR4_TXT+"', " +
                        "'"+MPR5_TXT+"', '"+MPR6_TXT+"', '"+MPR7_TXT+"', '"+ZBC1_TXT+"', '"+ZBC2_TXT+"', '"+ZBC3_TXT+"', '"+REC_TXT+"', " +
                        "'"+DEV_MBL+"', '"+DEV_MBL2+"', '"+DEV_MBL3+"', '"+DEV_MBL4+"', '"+DEV_MBT+"', '"+DEV_MBT2+"', '"+DEV_MBT3+"', '"+DEV_MBT4+"', '"+DEV_MBT5+"', '"+DEV_MPR+"', '"+DEV_MPR2+"', '"+DEV_MPR3+"', " +
                        "'"+DEV_MPR4+"', '"+DEV_MPR5+"', '"+DEV_MPR6+"', '"+DEV_MPR7+"', '"+DEV_ZBC+"', '"+DEV_ZBC2+"', '"+DEV_ZBC3+"', '"+KTG_MBLL+"', '"+KTG_MBTT+"', '"+KTG_MPRR+"', '"+KTG_ZBCC+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA51(ID, ISEG, SMBL1_TXT, SMBL2_TXT, SMBL3_TXT,
                        SMBL4_TXT, SMBT1_TXT, SMBT2_TXT, SMBT3_TXT, SMBT4_TXT, SMBT5_TXT, SMPR1_TXT, SMPR2_TXT,
                        SMPR3_TXT, SMPR4_TXT, SMPR5_TXT, SMPR6_TXT, SMPR7_TXT, MBL1_TXT, MBL2_TXT, MBL3_TXT,
                        MBL4_TXT, MBT1_TXT, MBT2_TXT, MBT3_TXT, MBT4_TXT, MBT5_TXT, MPR1_TXT, MPR2_TXT,
                        MPR3_TXT, MPR4_TXT, MPR5_TXT, MPR6_TXT, MPR7_TXT, ZBC1_TXT, ZBC2_TXT, ZBC3_TXT,
                        REC_TXT, DIR1, DIR2, DIR3, DIR4, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA51().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A51";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A51Activity.this, uploadId, urlUpload)
                            .addFileToUpload(DIR1, "image")
                            .addParameter("nru", NRU)
                            .addParameter("iseg", ISEG)
                            .addParameter("id", ID)
                            .addParameter("table", Table)
                            .setMaxRetries(3)
                            .startUpload();
                } catch (Exception ex){
                    Log.e("Error upload : ", ex.toString());
                }
            } else {

                try{

                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                    String user = "u6887969_admin_kelaikanjalan";
                    String password = "pr0yek4ndr01d";

                    Connection con = DriverManager.getConnection(url, user, password);

                    Statement state = con.createStatement();

                    String insert = "UPDATE A51 SET GBR_1 = null WHERE ID_A51 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A51";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A51Activity.this, uploadId, urlUpload2)
                            .addFileToUpload(DIR2, "image2")
                            .addParameter("nru", NRU)
                            .addParameter("iseg", ISEG)
                            .addParameter("id", ID)
                            .addParameter("table", Table)
                            .setMaxRetries(3)
                            .startUpload();
                } catch (Exception ex){
                    Log.e("Error upload : ", ex.toString());
                }
            } else {

                try{

                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                    String user = "u6887969_admin_kelaikanjalan";
                    String password = "pr0yek4ndr01d";

                    Connection con = DriverManager.getConnection(url, user, password);

                    Statement state = con.createStatement();

                    String insert = "UPDATE A51 SET GBR_2 = null WHERE ID_A51 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR3.equals("-")){
                try{
                    String Table = "A51";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A51Activity.this, uploadId, urlUpload3)
                            .addFileToUpload(DIR3, "image3")
                            .addParameter("nru", NRU)
                            .addParameter("iseg", ISEG)
                            .addParameter("id", ID)
                            .addParameter("table", Table)
                            .setMaxRetries(3)
                            .startUpload();
                } catch (Exception ex){
                    Log.e("Error upload : ", ex.toString());
                }
            } else {

                try{

                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                    String user = "u6887969_admin_kelaikanjalan";
                    String password = "pr0yek4ndr01d";

                    Connection con = DriverManager.getConnection(url, user, password);

                    Statement state = con.createStatement();

                    String insert = "UPDATE A51 SET GBR_3 = null WHERE ID_A51 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR4.equals("-")){
                try{
                    String Table = "A51";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A51Activity.this, uploadId, urlUpload4)
                            .addFileToUpload(DIR4, "image4")
                            .addParameter("nru", NRU)
                            .addParameter("iseg", ISEG)
                            .addParameter("id", ID)
                            .addParameter("table", Table)
                            .setMaxRetries(3)
                            .startUpload();
                } catch (Exception ex){
                    Log.e("Error upload : ", ex.toString());
                }
            } else {

                try{

                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                    String user = "u6887969_admin_kelaikanjalan";
                    String password = "pr0yek4ndr01d";

                    Connection con = DriverManager.getConnection(url, user, password);

                    Statement state = con.createStatement();

                    String insert = "UPDATE A51 SET GBR_4 = null WHERE ID_A51 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

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

    private class updateDataA51 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A51 SET ID_SEGMEN = '"+ISEG+"', UJI_A51_a1 = '"+MBL_IN+"', UJI_A51_a2 = '"+MBL2_IN+"', UJI_A51_a3 = '"+MBL3_IN+"', UJI_A51_a4 = '"+MBL4_IN+"', " +
                        "UJI_A51_b1 = '"+MBT_IN+"', UJI_A51_b2 = '"+MBT2_IN+"', UJI_A51_b3 = '"+MBT3_IN+"', UJI_A51_b4 = '"+MBT4_IN+"', UJI_A51_b5 = '"+MBT5_IN+"', " +
                        "UJI_A51_c1 = '"+MPR_IN+"', UJI_A51_c2 = '"+MPR2_IN+"', UJI_A51_c3 = '"+MPR3_IN+"', UJI_A51_c4 = '"+MPR4_IN+"', UJI_A51_c5 = '"+MPR5_IN+"', UJI_A51_c6 = '"+MPR6_IN+"', " +
                        "UJI_A51_c7 = '"+MPR7_IN+"', UJI_A51_a11 = '"+MBL1_TXT+"', UJI_A51_a22 = '"+MBL2_TXT+"', UJI_A51_a33 = '"+MBL3_TXT+"', UJI_A51_a44 = '"+MBL4_TXT+"', UJI_A51_b11 = '"+MBT1_TXT+"', UJI_A51_b22 = '"+MBT2_TXT+"', " +
                        "UJI_A51_b33 = '"+MBT3_TXT+"', UJI_A51_b44 = '"+MBT4_TXT+"', UJI_A51_b55 = '"+MBT5_TXT+"', UJI_A51_c11 = '"+MPR1_TXT+"', UJI_A51_c22 = '"+MPR2_TXT+"', UJI_A51_c33 = '"+MPR3_TXT+"', UJI_A51_c44 = '"+MPR4_TXT+"', " +
                        "UJI_A51_c55 = '"+MPR5_TXT+"', UJI_A51_c66 = '"+MPR6_TXT+"', UJI_A51_c77 = '"+MPR7_TXT+"', UJI_A51_d1 = '"+ZBC1_TXT+"', UJI_A51_d2 = '"+ZBC2_TXT+"', UJI_A51_d3 = '"+ZBC3_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A51_a1 = '"+DEV_MBL+"', DEV_A51_a2 = '"+DEV_MBL2+"', DEV_A51_a3 = '"+DEV_MBL3+"', DEV_A51_a4 = '"+DEV_MBL4+"', DEV_A51_b1 = '"+DEV_MBT+"', DEV_A51_b2 = '"+DEV_MBT2+"', " +
                        "DEV_A51_b3 = '"+DEV_MBT3+"', DEV_A51_b4 = '"+DEV_MBT4+"', DEV_A51_b5 = '"+DEV_MBT5+"', DEV_A51_c1 = '"+DEV_MPR+"', DEV_A51_c2 = '"+DEV_MPR2+"', " +
                        "DEV_A51_c3 = '"+DEV_MPR3+"', DEV_A51_c4 = '"+DEV_MPR4+"', DEV_A51_c5 = '"+DEV_MPR5+"', DEV_A51_c6 = '"+DEV_MPR6+"', DEV_A51_c7 = '"+DEV_MPR7+"', DEV_A51_d1 = '"+DEV_ZBC+"', " +
                        "DEV_A51_d2 = '"+DEV_ZBC2+"', DEV_A51_d3 = '"+DEV_ZBC3+"', KTG_A51_a = '"+KTG_MBLL+"', KTG_A51_b = '"+KTG_MBTT+"', KTG_A51_c = '"+KTG_MPRR+"', " +
                        "KTG_A51_d = '"+KTG_ZBCC+"', KTG_A51 = '"+KTG_KLF+"' WHERE ID_A51 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA51(ID, ISEG, SMBL1_TXT, SMBL2_TXT, SMBL3_TXT,
                        SMBL4_TXT, SMBT1_TXT, SMBT2_TXT, SMBT3_TXT, SMBT4_TXT, SMBT5_TXT, SMPR1_TXT, SMPR2_TXT,
                        SMPR3_TXT, SMPR4_TXT, SMPR5_TXT, SMPR6_TXT, SMPR7_TXT, MBL1_TXT, MBL2_TXT, MBL3_TXT,
                        MBL4_TXT, MBT1_TXT, MBT2_TXT, MBT3_TXT, MBT4_TXT, MBT5_TXT, MPR1_TXT, MPR2_TXT,
                        MPR3_TXT, MPR4_TXT, MPR5_TXT, MPR6_TXT, MPR7_TXT, ZBC1_TXT, ZBC2_TXT, ZBC3_TXT,
                        REC_TXT, DIR1, DIR2, DIR3, DIR4, "T");

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
    public void onBackPressed() {

        if(hasil == false){
            if(ZBC1.getText().toString().equals("") && ZBC2.getText().toString().equals("")  &&
                    ZBC3.getText().toString().equals("") &&  REC.getText().toString().equals("")){
                super.onBackPressed();
            } else {
                showPopupWarning();
            }
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private class getDataA51 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A51Activity.this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE).sizeRatio((float) 0.2).bgAlpha((float) 0.2)
                    .fadeColor(Color.DKGRAY).build();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String location = "-", location2 = "-", location3 = "-", location4 = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_A51, UJI_A51_a1, UJI_A51_a2, UJI_A51_a3, UJI_A51_a4, UJI_A51_b1, UJI_A51_b2, UJI_A51_b3, UJI_A51_b4, UJI_A51_b5, UJI_A51_c1, " +
                        "UJI_A51_c2, UJI_A51_c3, UJI_A51_c4, UJI_A51_c5, UJI_A51_c6, UJI_A51_c7, UJI_A51_a11, UJI_A51_a22, UJI_A51_a33, UJI_A51_a44, UJI_A51_b11, UJI_A51_b22, " +
                        "UJI_A51_b33, UJI_A51_b44, UJI_A51_b55, UJI_A51_c11, UJI_A51_c22, UJI_A51_c33, UJI_A51_c44, UJI_A51_c55, UJI_A51_c66, UJI_A51_c77, UJI_A51_d1, UJI_A51_d2, " +
                        "UJI_A51_d3, CATATAN, GBR_1, GBR_2, GBR_3, GBR_4, ID_SEGMEN FROM A51 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA51Upload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

                        String mbl, mbl2, mbl3, mbl4, mbt, mbt2, mbt3, mbt4, mbt5, mpr, mpr2, mpr3, mpr4, mpr5, mpr6, mpr7;

                        if(data.getInt("UJI_A51_a1") == 1){
                            mbl = "Ada";
                        } else if(data.getInt("UJI_A51_a1") == 2){
                            mbl = "Tidak Ada";
                        } else {
                            mbl = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_a2") == 1){
                            mbl2 = "Ada";
                        } else if(data.getInt("UJI_A51_a2") == 2){
                            mbl2 = "Tidak Ada";
                        } else {
                            mbl2 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_a3") == 1){
                            mbl3 = "Ada";
                        } else if(data.getInt("UJI_A51_a3") == 2){
                            mbl3 = "Tidak Ada";
                        } else {
                            mbl3 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_a4") == 1){
                            mbl4 = "Ada";
                        } else if(data.getInt("UJI_A51_a4") == 2){
                            mbl4 = "Tidak Ada";
                        } else {
                            mbl4 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_b1") == 1){
                            mbt = "Ada";
                        } else if(data.getInt("UJI_A51_b1") == 2){
                            mbt = "Tidak Ada";
                        } else {
                            mbt = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_b2") == 1){
                            mbt2 = "Ada";
                        } else if(data.getInt("UJI_A51_b2") == 2){
                            mbt2 = "Tidak Ada";
                        } else {
                            mbt2 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_b3") == 1){
                            mbt3 = "Ada";
                        } else if(data.getInt("UJI_A51_b3") == 2){
                            mbt3 = "Tidak Ada";
                        } else {
                            mbt3 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_b4") == 1){
                            mbt4 = "Ada";
                        } else if(data.getInt("UJI_A51_b4") == 2){
                            mbt4 = "Tidak Ada";
                        } else {
                            mbt4 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_b5") == 1){
                            mbt5 = "Ada";
                        } else if(data.getInt("UJI_A51_b5") == 2){
                            mbt5 = "Tidak Ada";
                        } else {
                            mbt5 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_c1") == 1){
                            mpr = "Ada";
                        } else if(data.getInt("UJI_A51_c1") == 2){
                            mpr = "Tidak Ada";
                        } else {
                            mpr = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_c2") == 1){
                            mpr2 = "Ada";
                        } else if(data.getInt("UJI_A51_c2") == 2){
                            mpr2 = "Tidak Ada";
                        } else {
                            mpr2 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_c3") == 1){
                            mpr3 = "Ada";
                        } else if(data.getInt("UJI_A51_c3") == 2){
                            mpr3 = "Tidak Ada";
                        } else {
                            mpr3 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_c4") == 1){
                            mpr4 = "Ada";
                        } else if(data.getInt("UJI_A51_c4") == 2){
                            mpr4 = "Tidak Ada";
                        } else {
                            mpr4 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_c5") == 1){
                            mpr5 = "Ada";
                        } else if(data.getInt("UJI_A51_c5") == 2){
                            mpr5 = "Tidak Ada";
                        } else {
                            mpr5 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_c6") == 1){
                            mpr6 = "Ada";
                        } else if(data.getInt("UJI_A51_c6") == 2){
                            mpr6 = "Tidak Ada";
                        } else {
                            mpr6 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A51_c7") == 1){
                            mpr7 = "Ada";
                        } else if(data.getInt("UJI_A51_c7") == 2){
                            mpr7 = "Tidak Ada";
                        } else {
                            mpr7 = "Tidak Perlu";
                        }

                        String dir1 = data.getString("GBR_1");
                        String dir2 = data.getString("GBR_2");
                        String dir3 = data.getString("GBR_3");
                        String dir4 = data.getString("GBR_4");

                        if(dir1 != null){

                            dir1 = dir1.replaceAll(" ", "%20");

                            Bitmap bitmap = null;
                            try {

                                InputStream fto = new java.net.URL(dir1).openStream();

                                bitmap = BitmapFactory.decodeStream(fto);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_1.png");
                                }

                                FileOutputStream save = new FileOutputStream(mediaStorageDir);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, save);
                                save.flush();

                                location = mediaStorageDir.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        if(dir2 != null){

                            dir2 = dir2.replaceAll(" ", "%20");

                            Bitmap bitmap2 = null;
                            try {

                                InputStream fto2 = new java.net.URL(dir2).openStream();

                                bitmap2 = BitmapFactory.decodeStream(fto2);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_2.png");
                                }

                                FileOutputStream save2 = new FileOutputStream(mediaStorageDir2);
                                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, save2);
                                save2.flush();

                                location2 = mediaStorageDir2.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        if(dir3 != null){

                            dir3 = dir3.replaceAll(" ", "%20");

                            Bitmap bitmap3 = null;
                            try {

                                InputStream fto3 = new java.net.URL(dir3).openStream();

                                bitmap3 = BitmapFactory.decodeStream(fto3);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir3 = null;
                                String state3 = Environment.getExternalStorageState();

                                if (state3.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_3.png");
                                } else {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_3.png");
                                }

                                FileOutputStream save3 = new FileOutputStream(mediaStorageDir3);
                                bitmap3.compress(Bitmap.CompressFormat.PNG, 100, save3);
                                save3.flush();

                                location3 = mediaStorageDir3.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        if(dir4 != null){

                            dir4 = dir4.replaceAll(" ", "%20");

                            Bitmap bitmap4 = null;
                            try {

                                InputStream fto4 = new java.net.URL(dir4).openStream();

                                bitmap4 = BitmapFactory.decodeStream(fto4);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir4 = null;
                                String state4 = Environment.getExternalStorageState();

                                if (state4.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_4.png");
                                } else {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A51/Compress/"+ISEG+"_4.png");
                                }

                                FileOutputStream save4 = new FileOutputStream(mediaStorageDir4);
                                bitmap4.compress(Bitmap.CompressFormat.PNG, 100, save4);
                                save4.flush();

                                location4 = mediaStorageDir4.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        Boolean insertA51 = databaseHelper.insertDataA51(data.getString("ID_A51"), data.getString("ID_SEGMEN"),
                                mbl, mbl2, mbl3, mbl4, mbt, mbt2, mbt3, mbt4, mbt5, mpr, mpr2, mpr3, mpr4, mpr5, mpr6, mpr7, data.getDouble("UJI_A51_a11"),
                                data.getDouble("UJI_A51_a22"), data.getDouble("UJI_A51_a33"), data.getDouble("UJI_A51_a44"), data.getDouble("UJI_A51_b11"),
                                data.getDouble("UJI_A51_b22"), data.getDouble("UJI_A51_b33"), data.getDouble("UJI_A51_b44"), data.getDouble("UJI_A51_b55"),
                                data.getDouble("UJI_A51_c11"), data.getDouble("UJI_A51_c22"), data.getDouble("UJI_A51_c33"), data.getDouble("UJI_A51_c44"),
                                data.getDouble("UJI_A51_c55"), data.getDouble("UJI_A51_c66"), data.getDouble("UJI_A51_c77"), data.getDouble("UJI_A51_d1"),
                                data.getDouble("UJI_A51_d2"), data.getDouble("UJI_A51_d3"), data.getString("CATATAN"), location, location2, location3, location4,"T");

                        if(insertA51 == false){

                            databaseHelper.updateDataA51(data.getString("ID_A51"), data.getString("ID_SEGMEN"),
                                    mbl, mbl2, mbl3, mbl4, mbt, mbt2, mbt3, mbt4, mbt5, mpr, mpr2, mpr3, mpr4, mpr5, mpr6, mpr7, data.getDouble("UJI_A51_a11"),
                                    data.getDouble("UJI_A51_a22"), data.getDouble("UJI_A51_a33"), data.getDouble("UJI_A51_a44"), data.getDouble("UJI_A51_b11"),
                                    data.getDouble("UJI_A51_b22"), data.getDouble("UJI_A51_b33"), data.getDouble("UJI_A51_b44"), data.getDouble("UJI_A51_b55"),
                                    data.getDouble("UJI_A51_c11"), data.getDouble("UJI_A51_c22"), data.getDouble("UJI_A51_c33"), data.getDouble("UJI_A51_c44"),
                                    data.getDouble("UJI_A51_c55"), data.getDouble("UJI_A51_c66"), data.getDouble("UJI_A51_c77"), data.getDouble("UJI_A51_d1"),
                                    data.getDouble("UJI_A51_d2"), data.getDouble("UJI_A51_d3"), data.getString("CATATAN"), location, location2, location3, location4,"T");

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

            a51ArrayList.addAll(databaseHelper.allDataA51(ISEG));

            if(!a51ArrayList.isEmpty()){
                a51_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A51_Adapter(A51Activity.this, a51ArrayList);
                        a51_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a51_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
