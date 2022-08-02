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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class A6A7Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    private ImageButton back;
    public TextView klf;
    private TextInputEditText TPK, RMP, RMP2, PPB, LPP, LPP2, LPT, LPT2, LPJ, PCT, PCT2, PGD, PTB, PTB2, FPP, FPP2, FJP, FJP2, FPR, REC;
    public double TPK_TXT, RMP_TXT, RMP2_TXT, PPB_TXT, LPP_TXT, LPP2_TXT, LPT_TXT, LPT2_TXT, LPJ_TXT, PCT_TXT, PCT2_TXT, PGD_TXT, PTB_TXT,
            PTB2_TXT, FPP_TXT, FPP2_TXT, FJP_TXT, FJP2_TXT, FPR_TXT;
    public double DEV_TPK, DEV_RMP, DEV_RMP2, DEV_PPB, DEV_LPP, DEV_LPP2, DEV_LPT, DEV_LPT2, DEV_LPJ, DEV_PCT, DEV_PCT2, DEV_PGD,
            DEV_PTB, DEV_PTB2, DEV_FPP, DEV_FPP2, DEV_FJP, DEV_FJP2, DEV_FPR;
    public String KTG_TPK, KTG_RMPP, KTG_PPB, KTG_LPJJ, KTG_PPPP, KTG_FPC, KTG_KLF;
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
    private List<A6A7_Class> a6a7ArrayList = new ArrayList<>();
    private A6A7_Adapter adapter;
    private RecyclerViewBouncy a6a7_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a6a7;
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
        setContentView(R.layout.activity_a6a7);

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

        klf = (TextView) findViewById(R.id.ktg_klf);

        TPK = (TextInputEditText) findViewById(R.id.TPK);
        RMP = (TextInputEditText) findViewById(R.id.RMP);
        RMP2 = (TextInputEditText) findViewById(R.id.RMP2);
        PPB = (TextInputEditText) findViewById(R.id.PPB);
        LPP = (TextInputEditText) findViewById(R.id.LPP);
        LPP2 = (TextInputEditText) findViewById(R.id.LPP2);
        LPT = (TextInputEditText) findViewById(R.id.LPT);
        LPT2 = (TextInputEditText) findViewById(R.id.LPT2);
        LPJ = (TextInputEditText) findViewById(R.id.LPJ);
        PCT = (TextInputEditText) findViewById(R.id.PCT);
        PCT2 = (TextInputEditText) findViewById(R.id.PCT2);
        PGD = (TextInputEditText) findViewById(R.id.PGD);
        PTB = (TextInputEditText) findViewById(R.id.PTB);
        PTB2 = (TextInputEditText) findViewById(R.id.PTB2);
        FPP = (TextInputEditText) findViewById(R.id.FPP);
        FPP2 = (TextInputEditText) findViewById(R.id.FPP2);
        FJP = (TextInputEditText) findViewById(R.id.FJP);
        FJP2 = (TextInputEditText) findViewById(R.id.FJP2);
        FPR = (TextInputEditText) findViewById(R.id.FPR);
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
        //recycler_a6a7 = (NestedScrollView) findViewById(R.id.recycler_a6a7);
        a6a7_recycler = (RecyclerViewBouncy) findViewById(R.id.a6a7_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        clear2.setVisibility(View.GONE);
        clear3.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a6a7_recycler.setHasFixedSize(true);
        a6a7_recycler.setLayoutManager(new LinearLayoutManager(this));

        a6a7_recycler.setVisibility(View.GONE);
        new getDataA6A7().execute((Void[])null);

    }

    private void getInputValue(){

        if(TPK.getText().toString().equals("")){
            TPK_TXT = -1;
        } else {
            TPK_TXT = Double.parseDouble(TPK.getText().toString());
        }

        if(RMP.getText().toString().equals("")){
            RMP_TXT = -1;
        } else {
            RMP_TXT = Double.parseDouble(RMP.getText().toString());
        }

        if(RMP2.getText().toString().equals("")){
            RMP2_TXT = -1;
        } else {
            RMP2_TXT = Double.parseDouble(RMP2.getText().toString());
        }
        if(PPB.getText().toString().equals("")){
            PPB_TXT = -1;
        } else {
            PPB_TXT = Double.parseDouble(PPB.getText().toString());
        }

        if(LPP.getText().toString().equals("")){
            LPP_TXT = -1;
        } else {
            LPP_TXT = Double.parseDouble(LPP.getText().toString());
        }

        if(LPP2.getText().toString().equals("")){
            LPP2_TXT = -1;
        } else {
            LPP2_TXT = Double.parseDouble(LPP2.getText().toString());
        }
        if(LPT.getText().toString().equals("")){
            LPT_TXT = -1;
        } else {
            LPT_TXT = Double.parseDouble(LPT.getText().toString());
        }

        if(LPT2.getText().toString().equals("")){
            LPT2_TXT = -1;
        } else {
            LPT2_TXT = Double.parseDouble(LPT2.getText().toString());
        }

        if(LPJ.getText().toString().equals("")){
            LPJ_TXT = -1;
        } else {
            LPJ_TXT = Double.parseDouble(LPJ.getText().toString());
        }
        if(PCT.getText().toString().equals("")){
            PCT_TXT = -1;
        } else {
            PCT_TXT = Double.parseDouble(PCT.getText().toString());
        }

        if(PCT2.getText().toString().equals("")){
            PCT2_TXT = -1;
        } else {
            PCT2_TXT = Double.parseDouble(PCT2.getText().toString());
        }

        if(PGD.getText().toString().equals("")){
            PGD_TXT = -1;
        } else {
            PGD_TXT = Double.parseDouble(PGD.getText().toString());
        }

        if(PTB.getText().toString().equals("")){
            PTB_TXT = -1;
        } else {
            PTB_TXT = Double.parseDouble(PTB.getText().toString());
        }

        if(PTB2.getText().toString().equals("")){
            PTB2_TXT = -1;
        } else {
            PTB2_TXT = Double.parseDouble(PTB2.getText().toString());
        }

        if(FPP.getText().toString().equals("")){
            FPP_TXT = -1;
        } else {
            FPP_TXT = Double.parseDouble(FPP.getText().toString());
        }
        if(FPP2.getText().toString().equals("")){
            FPP2_TXT = -1;
        } else {
            FPP2_TXT = Double.parseDouble(FPP2.getText().toString());
        }

        if(FJP.getText().toString().equals("")){
            FJP_TXT = -1;
        } else {
            FJP_TXT = Double.parseDouble(FJP.getText().toString());
        }

        if(FJP2.getText().toString().equals("")){
            FJP2_TXT = -1;
        } else {
            FJP2_TXT = Double.parseDouble(FJP2.getText().toString());
        }
        if(FPR.getText().toString().equals("")){
            FPR_TXT = -1;
        } else {
            FPR_TXT = Double.parseDouble(FPR.getText().toString());
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
                TPK.setText("");
                RMP.setText("");
                RMP2.setText("");
                PPB.setText("");
                LPP.setText("");
                LPP2.setText("");
                LPT.setText("");
                LPT2.setText("");
                LPJ.setText("");
                PCT.setText("");
                PCT2.setText("");
                PGD.setText("");
                PTB.setText("");
                PTB2.setText("");
                FPP.setText("");
                FPP2.setText("");
                FJP.setText("");
                FJP2.setText("");
                FPR.setText("");

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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_3.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_4.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7");
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

                    ID = ISEG + "_A6A7";

                    Boolean insertUjiA6A7 = databaseHelper.insertDataA6a7(ID, ISEG, TPK_TXT, RMP_TXT, RMP2_TXT, PPB_TXT, LPP_TXT, LPP2_TXT, LPT_TXT, LPT2_TXT, LPJ_TXT, PCT_TXT, PCT2_TXT, PGD_TXT, PTB_TXT,
                            PTB2_TXT, FPP_TXT, FPP2_TXT, FJP_TXT, FJP2_TXT, FPR_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "F");

                    if(insertUjiA6A7 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA6A7 = databaseHelper.updateDataA6a7(ID, ISEG, TPK_TXT, RMP_TXT, RMP2_TXT, PPB_TXT, LPP_TXT, LPP2_TXT, LPT_TXT, LPT2_TXT, LPJ_TXT, PCT_TXT, PCT2_TXT, PGD_TXT, PTB_TXT,
                            PTB2_TXT, FPP_TXT, FPP2_TXT, FJP_TXT, FJP2_TXT, FPR_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "F");

                    if(updateUjiA6A7 != false){
                        showPopup();
                    }
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a6a7_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                if(TPK_TXT == -1){
                    TPK.setText("");
                } else {
                    TPK.setText(String.valueOf(TPK_TXT));
                }

                if(RMP_TXT == -1){
                    RMP.setText("");
                } else {
                    RMP.setText(String.valueOf(RMP_TXT));
                }

                if(RMP2_TXT == -1){
                    RMP2.setText("");
                } else {
                    RMP2.setText(String.valueOf(RMP2_TXT));
                }

                if(PPB_TXT == -1){
                    PPB.setText("");
                } else {
                    PPB.setText(String.valueOf(PPB_TXT));
                }

                if(LPP_TXT == -1){
                    LPP.setText("");
                } else {
                    LPP.setText(String.valueOf(LPP_TXT));
                }

                if(LPP2_TXT == -1){
                    LPP2.setText("");
                } else {
                    LPP2.setText(String.valueOf(LPP2_TXT));
                }

                if(LPT_TXT == -1){
                    LPT.setText("");
                } else {
                    LPT.setText(String.valueOf(LPT_TXT));
                }

                if(LPT2_TXT == -1){
                    LPT2.setText("");
                } else {
                    LPT2.setText(String.valueOf(LPT2_TXT));
                }

                if(LPJ_TXT == -1){
                    LPJ.setText("");
                } else {
                    LPJ.setText(String.valueOf(LPJ_TXT));
                }

                if(PCT_TXT == -1){
                    PCT.setText("");
                } else {
                    PCT.setText(String.valueOf(PCT_TXT));
                }

                if(PCT2_TXT == -1){
                    PCT2.setText("");
                } else {
                    PCT2.setText(String.valueOf(PCT2_TXT));
                }

                if(PGD_TXT == -1){
                    PGD.setText("");
                } else {
                    PGD.setText(String.valueOf(PGD_TXT));
                }

                if(PTB_TXT == -1){
                    PTB.setText("");
                } else {
                    PTB.setText(String.valueOf(PTB_TXT));
                }

                if(PTB2_TXT == -1){
                    PTB2.setText("");
                } else {
                    PTB2.setText(String.valueOf(PTB2_TXT));
                }

                if(FPP_TXT == -1){
                    FPP.setText("");
                } else {
                    FPP.setText(String.valueOf(FPP_TXT));
                }

                if(FPP2_TXT == -1){
                    FPP2.setText("");
                } else {
                    FPP2.setText(String.valueOf(FPP2_TXT));
                }

                if(FJP_TXT == -1){
                    FJP.setText("");
                } else {
                    FJP.setText(String.valueOf(FJP_TXT));
                }

                if(FJP2_TXT == -1){
                    FJP2.setText("");
                } else {
                    FJP2.setText(String.valueOf(FJP2_TXT));
                }

                if(FPR_TXT == -1){
                    FPR.setText("");
                } else {
                    FPR.setText(String.valueOf(FPR_TXT));
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
                    a6a7ArrayList.clear();
                    a6a7ArrayList.addAll(databaseHelper.allDataA6a7(ISEG));

                    adapter = new A6A7_Adapter(A6A7Activity.this, a6a7ArrayList);
                    a6a7_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a6a7_recycler.setVisibility(View.VISIBLE);
                    a6a7_recycler.startAnimation(animation);
                    TPK.setText("");
                    RMP.setText("");
                    RMP2.setText("");
                    PPB.setText("");
                    LPP.setText("");
                    LPP2.setText("");
                    LPT.setText("");
                    LPT2.setText("");
                    LPJ.setText("");
                    PCT.setText("");
                    PCT2.setText("");
                    PGD.setText("");
                    PTB.setText("");
                    PTB2.setText("");
                    FPP.setText("");
                    FPP2.setText("");
                    FJP.setText("");
                    FJP2.setText("");
                    FPR.setText("");
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

                        new insertDataA6a7().execute((Void[])null);

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

        a6a7_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A6A7Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A7Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A7Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A7Activity.this, ImageViewActivity.class);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_2.png");
        }

        File mediaStorageDir4 = null;
        String state4 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_3.png");
        } else {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_3.png");
        }

        File mediaStorageDir5 = null;
        String state5 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_4.png");
        } else {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/"+ISEG+"_4.png");
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

        a6a7ArrayList.clear();

        a6a7ArrayList.addAll(databaseHelper.allDataA6a7(ISEG));

        if(a6a7ArrayList.isEmpty()){
            a6a7_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A6A7_Adapter(A6A7Activity.this, a6a7ArrayList);
            a6a7_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a6a7_recycler.setVisibility(View.VISIBLE);
            a6a7_recycler.startAnimation(animation);
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

    private class insertDataA6a7 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A6a7 (ID_A6a7, ID_SEGMEN, UJI_A6a7_a, UJI_A6a7_b1, UJI_A6a7_b2, UJI_A6a7_c, UJI_A6a7_d1_1, UJI_A6a7_d1_2, UJI_A6a7_d2_1, UJI_A6a7_d2_2, UJI_A6a7_d3, " +
                        "UJI_A6a7_e1_1, UJI_A6a7_e1_2, UJI_A6a7_e2, UJI_A6a7_f1_1, UJI_A6a7_f1_2, UJI_A6a7_f2_1, UJI_A6a7_f2_2, UJI_A6a7_f3_1, UJI_A6a7_f3_2, UJI_A6a7_f4, CATATAN, " +
                        "DEV_A6a7_a, DEV_A6a7_b1, DEV_A6a7_b2, DEV_A6a7_c, DEV_A6a7_d1_1, DEV_A6a7_d1_2, DEV_A6a7_d2_1, DEV_A6a7_d2_2, DEV_A6a7_d3, DEV_A6a7_e1_1, DEV_A6a7_e1_2, DEV_A6a7_e2, " +
                        "DEV_A6a7_f1_1, DEV_A6a7_f1_2, DEV_A6a7_f2_1, DEV_A6a7_f2_2, DEV_A6a7_f3_1, DEV_A6a7_f3_2, DEV_A6a7_f4, KTG_A6a7_a, KTG_A6a7_b, KTG_A6a7_c, KTG_A6a7_d, KTG_A6a7_e, KTG_A6a7_f, KTG_A6a7) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+TPK_TXT+"', '"+RMP_TXT+"', '"+RMP2_TXT+"', '"+PPB_TXT+"', '"+LPP_TXT+"', '"+LPP2_TXT+"', '"+LPT_TXT+"', '"+LPT2_TXT+"', '"+LPJ_TXT+"', " +
                        "'"+PCT_TXT+"', '"+PCT2_TXT+"', '"+PGD_TXT+"', '"+PTB_TXT+"', '"+PTB2_TXT+"', '"+FPP_TXT+"', '"+FPP2_TXT+"', '"+FJP_TXT+"', '"+FJP2_TXT+"', '"+FPR_TXT+"', '"+REC_TXT+"', " +
                        "'"+DEV_TPK+"', '"+DEV_RMP+"', '"+DEV_RMP2+"', '"+DEV_PPB+"', '"+DEV_LPP+"', '"+DEV_LPP2+"', '"+DEV_LPT+"', '"+DEV_LPT2+"', '"+DEV_LPJ+"', '"+DEV_PCT+"', '"+DEV_PCT2+"', " +
                        "'"+DEV_PGD+"', '"+DEV_PTB+"', '"+DEV_PTB2+"', '"+DEV_FPP+"', '"+DEV_FPP2+"', '"+DEV_FJP+"', '"+DEV_FJP2+"', '"+DEV_FPR+"', '"+KTG_TPK+"', '"+KTG_RMPP+"', '"+KTG_PPB+"', " +
                        "'"+KTG_LPJJ+"', '"+KTG_PPPP+"', '"+KTG_FPC+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a7(ID, ISEG, TPK_TXT, RMP_TXT, RMP2_TXT, PPB_TXT, LPP_TXT, LPP2_TXT, LPT_TXT, LPT2_TXT, LPJ_TXT, PCT_TXT, PCT2_TXT, PGD_TXT, PTB_TXT,
                        PTB2_TXT, FPP_TXT, FPP2_TXT, FJP_TXT, FJP2_TXT, FPR_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA6a7().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A6a7";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A7Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A6a7 SET GBR_1 = null WHERE ID_A6a7 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A6a7";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A7Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A6a7 SET GBR_2 = null WHERE ID_A6a7 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR3.equals("-")){
                try{
                    String Table = "A6a7";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A7Activity.this, uploadId, urlUpload3)
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

                    String insert = "UPDATE A6a7 SET GBR_3 = null WHERE ID_A6a7 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR4.equals("-")){
                try{
                    String Table = "A6a7";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A7Activity.this, uploadId, urlUpload4)
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

                    String insert = "UPDATE A6a7 SET GBR_4 = null WHERE ID_A6a7 = '"+ID+"'";

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

    private class updateDataA6a7 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A6a7 SET ID_SEGMEN = '"+ISEG+"', UJI_A6a7_a = '"+TPK_TXT+"', UJI_A6a7_b1 = '"+RMP_TXT+"', UJI_A6a7_b2 = '"+RMP2_TXT+"', UJI_A6a7_c = '"+PPB_TXT+"', " +
                        "UJI_A6a7_d1_1 = '"+LPP_TXT+"', UJI_A6a7_d1_2 = '"+LPP2_TXT+"', UJI_A6a7_d2_1 = '"+LPT_TXT+"', UJI_A6a7_d2_2 = '"+LPT2_TXT+"', UJI_A6a7_d3 = '"+LPJ_TXT+"', " +
                        "UJI_A6a7_e1_1 = '"+PCT_TXT+"', UJI_A6a7_e1_2 = '"+PCT2_TXT+"', UJI_A6a7_e2 = '"+PGD_TXT+"', UJI_A6a7_f1_1 = '"+PTB_TXT+"', UJI_A6a7_f1_2 = '"+PTB2_TXT+"', UJI_A6a7_f2_1 = '"+FPP_TXT+"', " +
                        "UJI_A6a7_f2_2 = '"+FPP2_TXT+"', UJI_A6a7_f3_1 = '"+FJP_TXT+"', UJI_A6a7_f3_2 = '"+FJP2_TXT+"', UJI_A6a7_f4 = '"+FPR_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A6a7_a = '"+DEV_TPK+"', DEV_A6a7_b1 = '"+DEV_RMP+"', DEV_A6a7_b2 = '"+DEV_RMP2+"', DEV_A6a7_c = '"+DEV_PPB+"', DEV_A6a7_d1_1 = '"+DEV_LPP+"', DEV_A6a7_d1_2 = '"+DEV_LPP2+"', " +
                        "DEV_A6a7_d2_1 = '"+DEV_LPT+"', DEV_A6a7_d2_2 = '"+DEV_LPT2+"', DEV_A6a7_d3 = '"+DEV_LPJ+"', DEV_A6a7_e1_1 = '"+DEV_PCT+"', DEV_A6a7_e1_2 = '"+DEV_PCT2+"', DEV_A6a7_e2 = '"+DEV_PGD+"', " +
                        "DEV_A6a7_f1_1 = '"+DEV_PTB+"', DEV_A6a7_f1_2 = '"+DEV_PTB2+"', DEV_A6a7_f2_1 = '"+DEV_FPP+"', DEV_A6a7_f2_2 = '"+DEV_FPP2+"', DEV_A6a7_f3_1 = '"+DEV_FJP+"', DEV_A6a7_f3_2 = '"+DEV_FJP2+"', " +
                        "DEV_A6a7_f4 = '"+DEV_FPR+"', KTG_A6a7_a = '"+KTG_TPK+"', KTG_A6a7_b = '"+KTG_RMPP+"', KTG_A6a7_c = '"+KTG_PPB+"', KTG_A6a7_d = '"+KTG_LPJJ+"', KTG_A6a7_e = '"+KTG_PPPP+"', KTG_A6a7_f = '"+KTG_FPC+"', " +
                        "KTG_A6a7 = '"+KTG_KLF+"' WHERE ID_A6a7 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a7(ID, ISEG, TPK_TXT, RMP_TXT, RMP2_TXT, PPB_TXT, LPP_TXT, LPP2_TXT, LPT_TXT, LPT2_TXT, LPJ_TXT, PCT_TXT, PCT2_TXT, PGD_TXT, PTB_TXT,
                        PTB2_TXT, FPP_TXT, FPP2_TXT, FJP_TXT, FJP2_TXT, FPR_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "T");

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
            if(TPK.getText().toString().equals("") && RMP.getText().toString().equals("")  &&
                    RMP2.getText().toString().equals("") && PPB.getText().toString().equals("") && LPP.getText().toString().equals("")  &&
                    LPP2.getText().toString().equals("") && LPT.getText().toString().equals("") && LPT2.getText().toString().equals("")  &&
                    LPJ.getText().toString().equals("") && PCT.getText().toString().equals("") && PCT2.getText().toString().equals("")  &&
                    PGD.getText().toString().equals("") && PTB.getText().toString().equals("") && PTB2.getText().toString().equals("")  &&
                    FPP.getText().toString().equals("") && FPP2.getText().toString().equals("") && FJP.getText().toString().equals("")  &&
                    FJP2.getText().toString().equals("") && FPR.getText().toString().equals("")  && REC.getText().toString().equals("")){
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

    private class getDataA6A7 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A6A7Activity.this)
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

                String select = "SELECT ID_A6a7, UJI_A6a7_a, UJI_A6a7_b1, UJI_A6a7_b2, UJI_A6a7_c, UJI_A6a7_d1_1, UJI_A6a7_d1_2, UJI_A6a7_d2_1, UJI_A6a7_d2_2, UJI_A6a7_d3, " +
                        "UJI_A6a7_e1_1, UJI_A6a7_e1_2, UJI_A6a7_e2, UJI_A6a7_f1_1, UJI_A6a7_f1_2, UJI_A6a7_f2_1, UJI_A6a7_f2_2, UJI_A6a7_f3_1, UJI_A6a7_f3_2, UJI_A6a7_f4, CATATAN, GBR_1, GBR_2, GBR_3, GBR_4, ID_SEGMEN FROM A6a7 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA6a7Upload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_1.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_2.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir3 = null;
                                String state3 = Environment.getExternalStorageState();

                                if (state3.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_3.png");
                                } else {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_3.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir4 = null;
                                String state4 = Environment.getExternalStorageState();

                                if (state4.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_4.png");
                                } else {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A7/Compress/"+ISEG+"_4.png");
                                }

                                FileOutputStream save4 = new FileOutputStream(mediaStorageDir4);
                                bitmap4.compress(Bitmap.CompressFormat.PNG, 100, save4);
                                save4.flush();

                                location4 = mediaStorageDir4.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        Boolean insertA6a7 = databaseHelper.insertDataA6a7(data.getString("ID_A6a7"), data.getString("ID_SEGMEN"),
                                data.getDouble("UJI_A6a7_a"), data.getDouble("UJI_A6a7_b1"), data.getDouble("UJI_A6a7_b2"),
                                data.getDouble("UJI_A6a7_c"), data.getDouble("UJI_A6a7_d1_1"), data.getDouble("UJI_A6a7_d1_2"),
                                data.getDouble("UJI_A6a7_d2_1"), data.getDouble("UJI_A6a7_d2_2"), data.getDouble("UJI_A6a7_d3"),
                                data.getDouble("UJI_A6a7_e1_1"), data.getDouble("UJI_A6a7_e1_2"), data.getDouble("UJI_A6a7_e2"),
                                data.getDouble("UJI_A6a7_f1_1"), data.getDouble("UJI_A6a7_f1_2"), data.getDouble("UJI_A6a7_f2_1"),
                                data.getDouble("UJI_A6a7_f2_2"), data.getDouble("UJI_A6a7_f3_1"), data.getDouble("UJI_A6a7_f3_2"),
                                data.getDouble("UJI_A6a7_f4"), data.getString("CATATAN"), location, location2, location3, location4, "T");

                        if(insertA6a7 == false){

                            databaseHelper.updateDataA6a7(data.getString("ID_A6a7"), data.getString("ID_SEGMEN"),
                                    data.getDouble("UJI_A6a7_a"), data.getDouble("UJI_A6a7_b1"), data.getDouble("UJI_A6a7_b2"),
                                    data.getDouble("UJI_A6a7_c"), data.getDouble("UJI_A6a7_d1_1"), data.getDouble("UJI_A6a7_d1_2"),
                                    data.getDouble("UJI_A6a7_d2_1"), data.getDouble("UJI_A6a7_d2_2"), data.getDouble("UJI_A6a7_d3"),
                                    data.getDouble("UJI_A6a7_e1_1"), data.getDouble("UJI_A6a7_e1_2"), data.getDouble("UJI_A6a7_e2"),
                                    data.getDouble("UJI_A6a7_f1_1"), data.getDouble("UJI_A6a7_f1_2"), data.getDouble("UJI_A6a7_f2_1"),
                                    data.getDouble("UJI_A6a7_f2_2"), data.getDouble("UJI_A6a7_f3_1"), data.getDouble("UJI_A6a7_f3_2"),
                                    data.getDouble("UJI_A6a7_f4"), data.getString("CATATAN"), location, location2, location3, location4, "T");

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

            a6a7ArrayList.addAll(databaseHelper.allDataA6a7(ISEG));

            if(!a6a7ArrayList.isEmpty()){
                a6a7_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A6A7_Adapter(A6A7Activity.this, a6a7ArrayList);
                        a6a7_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a6a7_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
