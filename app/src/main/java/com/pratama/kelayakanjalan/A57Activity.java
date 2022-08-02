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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class A57Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    private ImageButton back;
    public TextView klf;
    private TextInputEditText KML11, KML22, KML33, KML44;
    private Spinner KML, KML2, KML3, KML4, RMK, RMK2, RMK3;
    private TextInputEditText APL, PPK, REC;
    public String KML_TXT, KML2_TXT, KML3_TXT, KML4_TXT;
    public double RMK_TXT, RMK2_TXT, RMK3_TXT, APL_TXT, PPK_TXT, KML_TXT11, KML2_TXT22, KML3_TXT33, KML4_TXT44;
    public int KML_IN, KML2_IN, KML3_IN, KML4_IN;
    public double DEV_KML, DEV_KML2, DEV_KML3, DEV_KML4, DEV_RMK, DEV_RMK2, DEV_RMK3, DEV_APL, DEV_PPK;
    public String KTG_KMLL, KTG_RMKK, KTG_APL, KTG_PPK, KTG_KLF;
    private Button SAVE;
    public FABProgressCircle FAB_UPLOAD;
    public FloatingActionButton EDIT, UPLOAD;
    private Boolean connected = false;
    private CoordinatorLayout coordinatorLayout;
    public ImageView FT1, FT2, FT3, FT4;
    private int REQ1 = 1, REQ2 = 2, REQ4 = 3, REQ5 = 4;
    public String REC_TXT, DIR1 = "-", DIR2 = "-";
    private double STD = 100;
    private Dialog myDialog;
    private List<A57_Class> a57ArrayList = new ArrayList<>();
    private A57_Adapter adapter;
    private RecyclerViewBouncy a57_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a57;
    private RelativeLayout aksi;
    private ImageButton take, open, take1, open1;
    private LinearLayout aksi2, aksi3;
    private ImageButton clear, clear1;
    private Boolean op = false, op2 = false;
    private Boolean hasil = false;
    private File mediaFile;
    private File compressedImage;
    private ACProgressFlower dialog;
    private static final String urlUpload = "http://proyekjalan.net/upload_1.php";
    private static final String urlUpload2 = "http://proyekjalan.net/upload_2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a57);

        init();
        onSelectedItem();
        onCLickListener();

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
        KML = (Spinner) findViewById(R.id.SKML1);
        KML2 = (Spinner) findViewById(R.id.SKML2);
        KML3 = (Spinner) findViewById(R.id.SKML3);
        KML4 = (Spinner) findViewById(R.id.SKML4);
        KML11 = (TextInputEditText) findViewById(R.id.KML1);
        KML22 = (TextInputEditText) findViewById(R.id.KML2);
        KML33 = (TextInputEditText) findViewById(R.id.KML3);
        KML44 = (TextInputEditText) findViewById(R.id.KML4);
        RMK = (Spinner) findViewById(R.id.SRMK1);
        RMK2 = (Spinner) findViewById(R.id.SRMK2);
        RMK3 = (Spinner) findViewById(R.id.SRMK3);
        APL = (TextInputEditText) findViewById(R.id.APL);
        PPK = (TextInputEditText) findViewById(R.id.PPK);

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
        //recycler_a57 = (NestedScrollView) findViewById(R.id.recycler_a57);
        a57_recycler = (RecyclerViewBouncy) findViewById(R.id.a57_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a57_recycler.setHasFixedSize(true);
        a57_recycler.setLayoutManager(new LinearLayoutManager(this));

        a57_recycler.setVisibility(View.GONE);
        new getDataA57().execute((Void[])null);

    }

    private void onSelectedItem(){

        KML.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    KML11.setEnabled(true);
                    KML11.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    KML11.setEnabled(false);
                    KML11.setText("0");
                } else{
                    KML11.setEnabled(false);
                    KML11.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        KML2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    KML22.setEnabled(true);
                    KML22.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    KML22.setEnabled(false);
                    KML22.setText("0");
                } else{
                    KML22.setEnabled(false);
                    KML22.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        KML3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    KML33.setEnabled(true);
                    KML33.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    KML33.setEnabled(false);
                    KML33.setText("0");
                } else{
                    KML33.setEnabled(false);
                    KML33.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        KML4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Ada")){
                    KML44.setEnabled(true);
                    KML44.setText("100");
                } else if(selected.equals("Tidak Ada")) {
                    KML44.setEnabled(false);
                    KML44.setText("0");
                } else{
                    KML44.setEnabled(false);
                    KML44.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getInputValue(){

        KML_TXT = KML.getSelectedItem().toString();
        KML2_TXT = KML2.getSelectedItem().toString();
        KML3_TXT = KML3.getSelectedItem().toString();
        KML4_TXT = KML4.getSelectedItem().toString();

        if(KML11.getText().toString().equals("")){
            KML_TXT11 = -1;
        } else {
            KML_TXT11 = Double.parseDouble(KML11.getText().toString());
        }

        if(KML22.getText().toString().equals("")){
            KML2_TXT22 = -1;
        } else {
            KML2_TXT22 = Double.parseDouble(KML22.getText().toString());
        }

        if(KML33.getText().toString().equals("")){
            KML3_TXT33 = -1;
        } else {
            KML3_TXT33 = Double.parseDouble(KML33.getText().toString());
        }

        if(KML44.getText().toString().equals("")){
            KML4_TXT44 = -1;
        } else {
            KML4_TXT44 = Double.parseDouble(KML44.getText().toString());
        }

        String rmk1 = RMK.getSelectedItem().toString();
        String rmk2 = RMK2.getSelectedItem().toString();
        String rmk3 = RMK3.getSelectedItem().toString();

        if(rmk1.equals("Ada")){
            RMK_TXT = 100;
        } else if(rmk1.equals("Tidak Ada")){
            RMK_TXT = 0;
        } else {
            RMK_TXT = -1;
        }

        if(rmk2.equals("Ada")){
            RMK2_TXT = 100;
        } else if(rmk2.equals("Tidak Ada")){
            RMK2_TXT = 0;
        } else {
            RMK2_TXT = -1;
        }

        if(rmk3.equals("Ada")){
            RMK3_TXT = 100;
        } else if(rmk3.equals("Tidak Ada")){
            RMK3_TXT = 0;
        } else {
            RMK3_TXT = -1;
        }

        if(APL.getText().toString().equals("")){
            APL_TXT = -1;
        } else {
            APL_TXT = Double.parseDouble(APL.getText().toString());
        }
        if(PPK.getText().toString().equals("")){
            PPK_TXT = -1;
        } else {
            PPK_TXT = Double.parseDouble(PPK.getText().toString());
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
                KML11.setText("");
                KML22.setText("");
                KML33.setText("");
                KML44.setText("");
                APL.setText("");
                PPK.setText("");
                REC.setText("");
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

    }

    private void onCLickListener(){

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ4);
            }
        });

        take1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ5);
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInputValue();

                if(op == false){

                    ID = ISEG + "_A57";

                    Boolean insertUjiA57 = databaseHelper.insertDataA57(ID, ISEG, KML_TXT, KML2_TXT, KML3_TXT, KML4_TXT,
                            KML_TXT11, KML2_TXT22, KML3_TXT33, KML4_TXT44, RMK_TXT, RMK2_TXT, RMK3_TXT, APL_TXT, PPK_TXT, REC_TXT, DIR1, DIR2, "F");

                    if(insertUjiA57 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA57 = databaseHelper.updateDataA57(ID, ISEG, KML_TXT, KML2_TXT, KML3_TXT, KML4_TXT,
                            KML_TXT11, KML2_TXT22, KML3_TXT33, KML4_TXT44, RMK_TXT, RMK2_TXT, RMK3_TXT, APL_TXT, PPK_TXT, REC_TXT, DIR1, DIR2, "F");

                    if(updateUjiA57 != false){
                        showPopup();
                    }
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a57_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                KML.setSelection(((ArrayAdapter<String>)KML.getAdapter()).getPosition(KML_TXT));

                KML2.setSelection(((ArrayAdapter<String>)KML2.getAdapter()).getPosition(KML2_TXT));

                KML3.setSelection(((ArrayAdapter<String>)KML3.getAdapter()).getPosition(KML3_TXT));

                KML4.setSelection(((ArrayAdapter<String>)KML4.getAdapter()).getPosition(KML4_TXT));

                if(KML_TXT11 == -1){
                    KML11.setText("");
                } else {
                    KML11.setText(String.valueOf(KML_TXT11));
                }

                if(KML2_TXT22 == -1){
                    KML22.setText("");
                } else {
                    KML22.setText(String.valueOf(KML2_TXT22));
                }

                if(KML3_TXT33 == -1){
                    KML33.setText("");
                } else {
                    KML33.setText(String.valueOf(KML3_TXT33));
                }

                if(KML4_TXT44 == -1){
                    KML44.setText("");
                } else {
                    KML44.setText(String.valueOf(KML4_TXT44));
                }

                if(RMK_TXT == 100){
                    RMK.setSelection(((ArrayAdapter<String>)RMK.getAdapter()).getPosition("Ada"));
                } else if(RMK_TXT == 0){
                    RMK.setSelection(((ArrayAdapter<String>)RMK.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    RMK.setSelection(((ArrayAdapter<String>)RMK.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(RMK2_TXT == 100){
                    RMK2.setSelection(((ArrayAdapter<String>)RMK2.getAdapter()).getPosition("Ada"));
                } else if(RMK2_TXT == 0){
                    RMK2.setSelection(((ArrayAdapter<String>)RMK2.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    RMK2.setSelection(((ArrayAdapter<String>)RMK2.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(RMK3_TXT == 100){
                    RMK3.setSelection(((ArrayAdapter<String>)RMK3.getAdapter()).getPosition("Ada"));
                } else if(RMK3_TXT == 0){
                    RMK3.setSelection(((ArrayAdapter<String>)RMK3.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    RMK3.setSelection(((ArrayAdapter<String>)RMK3.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(APL_TXT == -1){
                    APL.setText("");
                } else {
                    APL.setText(String.valueOf(APL_TXT));
                }

                if(PPK_TXT == -1){
                    PPK.setText("");
                } else {
                    PPK.setText(String.valueOf(PPK_TXT));
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

        klf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(op2 == false){

                } else {
                    a57ArrayList.clear();
                    a57ArrayList.addAll(databaseHelper.allDataA57(ISEG));

                    adapter = new A57_Adapter(A57Activity.this, a57ArrayList);
                    a57_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a57_recycler.setVisibility(View.VISIBLE);
                    a57_recycler.startAnimation(animation);
                    APL.setText("");
                    PPK.setText("");
                    REC.setText("");
                    KML11.setText("");
                    KML22.setText("");
                    KML33.setText("");
                    KML44.setText("");
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

                        new insertDataA57().execute((Void[])null);

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

        a57_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A57Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A57Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR2);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state2.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state3.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/"+ISEG+"_2.png");
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

            } else if(requestCode == REQ4){

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

            } else if(requestCode == REQ5){

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

        a57ArrayList.clear();

        a57ArrayList.addAll(databaseHelper.allDataA57(ISEG));

        if(a57ArrayList.isEmpty()){
            a57_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A57_Adapter(A57Activity.this, a57ArrayList);
            a57_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a57_recycler.setVisibility(View.VISIBLE);
            a57_recycler.startAnimation(animation);
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

    private class insertDataA57 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A57 (ID_A57, ID_SEGMEN, UJI_A57_a1, UJI_A57_a2, UJI_A57_a3, UJI_A57_a4, UJI_A57_a11, UJI_A57_a22, UJI_A57_a33, UJI_A57_a44, UJI_A57_b1, UJI_A57_b2, UJI_A57_b3, UJI_A57_c, UJI_A57_d, CATATAN, " +
                        "DEV_A57_a1, DEV_A57_a2, DEV_A57_a3, DEV_A57_a4, DEV_A57_b1, DEV_A57_b2, DEV_A57_b3, DEV_A57_c, DEV_A57_d, KTG_A57_a, KTG_A57_b, KTG_A57_c, KTG_A57_d, KTG_A57) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+KML_IN+"', '"+KML2_IN+"', '"+KML3_IN+"', '"+KML4_IN+"', '"+KML_TXT11+"', '"+KML2_TXT22+"', '"+KML3_TXT33+"', '"+KML4_TXT44+"', '"+RMK_TXT+"', '"+RMK2_TXT+"', " +
                        "'"+RMK3_TXT+"', '"+APL_TXT+"', '"+PPK_TXT+"', '"+REC_TXT+"', '"+DEV_KML+"', '"+DEV_KML2+"', '"+DEV_KML3+"', '"+DEV_KML4+"', '"+DEV_RMK+"', " +
                        "'"+DEV_RMK2+"', '"+DEV_RMK3+"', '"+DEV_APL+"', '"+DEV_PPK+"', '"+KTG_KMLL+"', '"+KTG_RMKK+"', '"+KTG_APL+"', '"+KTG_PPK+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA57(ID, ISEG, KML_TXT, KML2_TXT, KML3_TXT, KML4_TXT,
                        KML_TXT11, KML2_TXT22, KML3_TXT33, KML4_TXT44, RMK_TXT, RMK2_TXT, RMK3_TXT, APL_TXT, PPK_TXT, REC_TXT, DIR1, DIR2, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA57().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A57";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A57Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A57 SET GBR_1 = null WHERE ID_A57 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A57";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A57Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A57 SET GBR_2 = null WHERE ID_A57 = '"+ID+"'";

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

    private class updateDataA57 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A57 SET ID_SEGMEN = '"+ISEG+"', UJI_A57_a1 = '"+KML_IN+"', UJI_A57_a2 = '"+KML2_IN+"', UJI_A57_a3 = '"+KML3_IN+"', UJI_A57_a4 = '"+KML4_IN+"', UJI_A57_a11 = '"+KML_TXT11+"', UJI_A57_a22 = '"+KML2_TXT22+"', UJI_A57_a33 = '"+KML3_TXT33+"', UJI_A57_a44 = '"+KML4_TXT44+"', " +
                        "UJI_A57_b1 = '"+RMK_TXT+"', UJI_A57_b2 = '"+RMK2_TXT+"', UJI_A57_b3 = '"+RMK3_TXT+"', UJI_A57_c = '"+APL_TXT+"', UJI_A57_d = '"+PPK_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A57_a1 = '"+DEV_KML+"', DEV_A57_a2 = '"+DEV_KML2+"', DEV_A57_a3 = '"+DEV_KML3+"', DEV_A57_a4 = '"+DEV_KML4+"', DEV_A57_b1 = '"+DEV_RMK+"', DEV_A57_b2 = '"+DEV_RMK2+"', " +
                        "DEV_A57_b3 = '"+DEV_RMK3+"', DEV_A57_c = '"+DEV_APL+"', DEV_A57_d = '"+DEV_PPK+"', KTG_A57_a = '"+KTG_KMLL+"', KTG_A57_b = '"+KTG_RMKK+"', KTG_A57_c = '"+KTG_APL+"', " +
                        "KTG_A57_d = '"+KTG_PPK+"', KTG_A57 = '"+KTG_KLF+"' WHERE ID_A57 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA57(ID, ISEG, KML_TXT, KML2_TXT, KML3_TXT, KML4_TXT,
                        KML_TXT11, KML2_TXT22, KML3_TXT33, KML4_TXT44, RMK_TXT, RMK2_TXT, RMK3_TXT, APL_TXT, PPK_TXT, REC_TXT, DIR1, DIR2, "T");

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
            if(APL.getText().toString().equals("") && PPK.getText().toString().equals("") && REC.getText().toString().equals("")){
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

    private class getDataA57 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A57Activity.this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE).sizeRatio((float) 0.2).bgAlpha((float) 0.2)
                    .fadeColor(Color.DKGRAY).build();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String location = "-", location2 = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_A57, UJI_A57_a1, UJI_A57_a2, UJI_A57_a3, UJI_A57_a4, UJI_A57_a11, UJI_A57_a22, UJI_A57_a33, UJI_A57_a44, UJI_A57_b1, UJI_A57_b2, UJI_A57_b3, UJI_A57_c, UJI_A57_d, CATATAN, GBR_1, GBR_2, ID_SEGMEN FROM A57 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA57Upload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

                        String kml, kml2, kml3, kml4;

                        if(data.getInt("UJI_A57_a1") == 1){
                            kml = "Ada";
                        } else if(data.getInt("UJI_A57_a1") == 2){
                            kml = "Tidak Ada";
                        } else {
                            kml = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A57_a2") == 1){
                            kml2 = "Ada";
                        } else if(data.getInt("UJI_A57_a2") == 2){
                            kml2 = "Tidak Ada";
                        } else {
                            kml2 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A57_a3") == 1){
                            kml3 = "Ada";
                        } else if(data.getInt("UJI_A57_a3") == 2){
                            kml3 = "Tidak Ada";
                        } else {
                            kml3 = "Tidak Perlu";
                        }

                        if(data.getInt("UJI_A57_a4") == 1){
                            kml4 = "Ada";
                        } else if(data.getInt("UJI_A57_a4") == 2){
                            kml4 = "Tidak Ada";
                        } else {
                            kml4 = "Tidak Perlu";
                        }

                        String dir1 = data.getString("GBR_1");
                        String dir2 = data.getString("GBR_2");

                        if(dir1 != null){

                            dir1 = dir1.replaceAll(" ", "%20");

                            Bitmap bitmap = null;
                            try {

                                InputStream fto = new java.net.URL(dir1).openStream();

                                bitmap = BitmapFactory.decodeStream(fto);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress/"+ISEG+"_1.png");
                                }

                                FileOutputStream save = new FileOutputStream(mediaStorageDir);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, save);
                                save.flush();

                                location = mediaStorageDir.getAbsolutePath();

                            } catch (Exception e) {
                                Log.e("Error Image ", e.toString());
                            }

                        }

                        if(dir2 != null){

                            dir2 = dir2.replaceAll(" ", "%20");

                            Bitmap bitmap2 = null;
                            try {

                                InputStream fto2 = new java.net.URL(dir2).openStream();

                                bitmap2 = BitmapFactory.decodeStream(fto2);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A57/Compress/"+ISEG+"_2.png");
                                }

                                FileOutputStream save = new FileOutputStream(mediaStorageDir2);
                                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, save);
                                save.flush();

                                location2 = mediaStorageDir2.getAbsolutePath();

                            } catch (Exception e) {
                                Log.e("Error Image ", e.toString());
                            }

                        }

                        Boolean insertA57 = databaseHelper.insertDataA57(data.getString("ID_A57"), data.getString("ID_SEGMEN"),
                                kml, kml2, kml3, kml4, data.getDouble("UJI_A57_a11"), data.getDouble("UJI_A57_a22"), data.getDouble("UJI_A57_a33"),
                                data.getDouble("UJI_A57_a44"), data.getDouble("UJI_A57_b1"), data.getDouble("UJI_A57_b2"),
                                data.getDouble("UJI_A57_b3"), data.getDouble("UJI_A57_c"), data.getDouble("UJI_A57_d"),
                                data.getString("CATATAN"), location, location2,"T");

                        if(insertA57 == false){

                            databaseHelper.updateDataA57(data.getString("ID_A57"), data.getString("ID_SEGMEN"),
                                    kml, kml2, kml3, kml4, data.getDouble("UJI_A57_a11"), data.getDouble("UJI_A57_a22"), data.getDouble("UJI_A57_a33"),
                                    data.getDouble("UJI_A57_a44"), data.getDouble("UJI_A57_b1"), data.getDouble("UJI_A57_b2"),
                                    data.getDouble("UJI_A57_b3"), data.getDouble("UJI_A57_c"), data.getDouble("UJI_A57_d"),
                                    data.getString("CATATAN"), location, location2,"T");

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

            a57ArrayList.addAll(databaseHelper.allDataA57(ISEG));

            if(!a57ArrayList.isEmpty()){
                a57_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A57_Adapter(A57Activity.this, a57ArrayList);
                        a57_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a57_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
