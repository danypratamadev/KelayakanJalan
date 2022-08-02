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

public class A6A2Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public String SJR, FNG, KPR, MJL, NRU, ISEG;
    public double KCP;
    public String ID;
    private ImageButton back;
    public TextView klf;
    private TextInputEditText UDR, UWR, UWR2, UWR3, UWR4, UWR5, LJP, LJP2, LJP3, LJJ, LJJ2, LJT, LJT2, LJT3, LJT4,
            PPR, PPR2, PPR3, PPR4, REC;
    public double UDR_TXT, UWR_TXT, UWR2_TXT, UWR3_TXT, UWR4_TXT, UWR5_TXT, LJP_TXT, LJP2_TXT, LJP3_TXT, LJJ_TXT,
            LJJ2_TXT, LJT_TXT, LJT2_TXT, LJT3_TXT, LJT4_TXT, PPR_TXT, PPR2_TXT, PPR3_TXT, PPR4_TXT;
    public double DEV_UDR, DEV_UWR, DEV_UWR2, DEV_UWR3, DEV_UWR4, DEV_UWR5, DEV_LJP, DEV_LJP2, DEV_LJP3, DEV_LJJ,
            DEV_LJJ2, DEV_LJT, DEV_LJT2, DEV_LJT3, DEV_LJT4, DEV_PPR, DEV_PPR2, DEV_PPR3, DEV_PPR4;
    public String KTG_UKW, KTG_LRJ, KTG_PPRR, KTG_KLF;
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
    private List<A6A2_Class> a6a2ArrayList = new ArrayList<>();
    private A6A2_Adapter adapter;
    private RecyclerViewBouncy a6a2_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a6a2;
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
        setContentView(R.layout.activity_a6a2);

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
        KCP = intent.getDoubleExtra("kcp", 0);

        klf = (TextView) findViewById(R.id.ktg_klf);
        UDR = (TextInputEditText) findViewById(R.id.UDR);
        UWR = (TextInputEditText) findViewById(R.id.UWR);
        UWR2 = (TextInputEditText) findViewById(R.id.UWR2);
        UWR3 = (TextInputEditText) findViewById(R.id.UWR3);
        UWR4 = (TextInputEditText) findViewById(R.id.UWR4);
        UWR5 = (TextInputEditText) findViewById(R.id.UWR5);
        LJP = (TextInputEditText) findViewById(R.id.LJP);
        LJP2 = (TextInputEditText) findViewById(R.id.LJP2);
        LJP3 = (TextInputEditText) findViewById(R.id.LJP3);
        LJJ = (TextInputEditText) findViewById(R.id.LJJ);
        LJJ2 = (TextInputEditText) findViewById(R.id.LJJ2);
        LJT = (TextInputEditText) findViewById(R.id.LJT);
        LJT2 = (TextInputEditText) findViewById(R.id.LJT2);
        LJT3 = (TextInputEditText) findViewById(R.id.LJT3);
        LJT4 = (TextInputEditText) findViewById(R.id.LJT4);
        PPR = (TextInputEditText) findViewById(R.id.PPR);
        PPR2 = (TextInputEditText) findViewById(R.id.PPR2);
        PPR3 = (TextInputEditText) findViewById(R.id.PPR3);
        PPR4 = (TextInputEditText) findViewById(R.id.PPR4);
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
        //recycler_a6a2 = (NestedScrollView) findViewById(R.id.recycler_a6a2);
        a6a2_recycler = (RecyclerViewBouncy) findViewById(R.id.a6a2_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        clear2.setVisibility(View.GONE);
        clear3.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a6a2_recycler.setHasFixedSize(true);
        a6a2_recycler.setLayoutManager(new LinearLayoutManager(this));

        a6a2_recycler.setVisibility(View.GONE);
        new getDataA6A2().execute((Void[])null);

    }

    private void getInputValue(){

        if(UDR.getText().toString().equals("")){
            UDR_TXT = -1;
        } else {
            UDR_TXT = Double.parseDouble(UDR.getText().toString());
        }

        if(UWR.getText().toString().equals("")){
            UWR_TXT = -1;
        } else {
            UWR_TXT = Double.parseDouble(UWR.getText().toString());
        }

        if(UWR2.getText().toString().equals("")){
            UWR2_TXT = -1;
        } else {
            UWR2_TXT = Double.parseDouble(UWR2.getText().toString());
        }
        if(UWR3.getText().toString().equals("")){
            UWR3_TXT = -1;
        } else {
            UWR3_TXT = Double.parseDouble(UWR3.getText().toString());
        }

        if(UWR4.getText().toString().equals("")){
            UWR4_TXT = -1;
        } else {
            UWR4_TXT = Double.parseDouble(UWR4.getText().toString());
        }

        if(UWR5.getText().toString().equals("")){
            UWR5_TXT = -1;
        } else {
            UWR5_TXT = Double.parseDouble(UWR5.getText().toString());
        }
        if(LJP.getText().toString().equals("")){
            LJP_TXT = -1;
        } else {
            LJP_TXT = Double.parseDouble(LJP.getText().toString());
        }

        if(LJP2.getText().toString().equals("")){
            LJP2_TXT = -1;
        } else {
            LJP2_TXT = Double.parseDouble(LJP2.getText().toString());
        }

        if(LJP3.getText().toString().equals("")){
            LJP3_TXT = -1;
        } else {
            LJP3_TXT = Double.parseDouble(LJP3.getText().toString());
        }
        if(LJJ.getText().toString().equals("")){
            LJJ_TXT = -1;
        } else {
            LJJ_TXT = Double.parseDouble(LJJ.getText().toString());
        }

        if(LJJ2.getText().toString().equals("")){
            LJJ2_TXT = -1;
        } else {
            LJJ2_TXT = Double.parseDouble(LJJ2.getText().toString());
        }

        if(LJT.getText().toString().equals("")){
            LJT_TXT = -1;
        } else {
            LJT_TXT = Double.parseDouble(LJT.getText().toString());
        }

        if(LJT2.getText().toString().equals("")){
            LJT2_TXT = -1;
        } else {
            LJT2_TXT = Double.parseDouble(LJT2.getText().toString());
        }

        if(LJT3.getText().toString().equals("")){
            LJT3_TXT = -1;
        } else {
            LJT3_TXT = Double.parseDouble(LJT3.getText().toString());
        }

        if(LJT4.getText().toString().equals("")){
            LJT4_TXT = -1;
        } else {
            LJT4_TXT = Double.parseDouble(LJT4.getText().toString());
        }
        if(PPR.getText().toString().equals("")){
            PPR_TXT = -1;
        } else {
            PPR_TXT = Double.parseDouble(PPR.getText().toString());
        }

        if(PPR2.getText().toString().equals("")){
            PPR2_TXT = -1;
        } else {
            PPR2_TXT = Double.parseDouble(PPR2.getText().toString());
        }

        if(PPR3.getText().toString().equals("")){
            PPR3_TXT = -1;
        } else {
            PPR3_TXT = Double.parseDouble(PPR3.getText().toString());
        }
        if(PPR4.getText().toString().equals("")){
            PPR4_TXT = -1;
        } else {
            PPR4_TXT = Double.parseDouble(PPR4.getText().toString());
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
                UDR.setText("");
                UWR.setText("");
                UWR2.setText("");
                UWR3.setText("");
                UWR4.setText("");
                UWR5.setText("");
                LJP.setText("");
                LJP2.setText("");
                LJP3.setText("");
                LJJ.setText("");
                LJJ2.setText("");
                LJT.setText("");
                LJT2.setText("");
                LJT3.setText("");
                LJT4.setText("");
                PPR.setText("");
                PPR2.setText("");
                PPR3.setText("");
                PPR4.setText("");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_3.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_4.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2");
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

                    ID = ISEG + "_A6A2";

                    Boolean insertUjiA6A2 = databaseHelper.insertDataA6a2(ID, ISEG, UDR_TXT, UWR_TXT, UWR2_TXT, UWR3_TXT, UWR4_TXT, UWR5_TXT, LJP_TXT, LJP2_TXT,
                            LJP3_TXT, LJJ_TXT, LJJ2_TXT, LJT_TXT, LJT2_TXT, LJT3_TXT, LJT4_TXT, PPR_TXT, PPR2_TXT, PPR3_TXT, PPR4_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "F");

                    if(insertUjiA6A2 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA6A2 = databaseHelper.updateDataA6a2(ID, ISEG, UDR_TXT, UWR_TXT, UWR2_TXT, UWR3_TXT, UWR4_TXT, UWR5_TXT, LJP_TXT, LJP2_TXT,
                            LJP3_TXT, LJJ_TXT, LJJ2_TXT, LJT_TXT, LJT2_TXT, LJT3_TXT, LJT4_TXT, PPR_TXT, PPR2_TXT, PPR3_TXT, PPR4_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "F");

                    if(updateUjiA6A2 != false){
                        showPopup();
                    }
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a6a2_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                if(UDR_TXT == -1){
                    UDR.setText("");
                } else {
                    UDR.setText(String.valueOf(UDR_TXT));
                }

                if(UWR_TXT == -1){
                    UWR.setText("");
                } else {
                    UWR.setText(String.valueOf(UWR_TXT));
                }

                if(UWR2_TXT == -1){
                    UWR2.setText("");
                } else {
                    UWR2.setText(String.valueOf(UWR2_TXT));
                }

                if(UWR3_TXT == -1){
                    UWR3.setText("");
                } else {
                    UWR3.setText(String.valueOf(UWR3_TXT));
                }

                if(UWR4_TXT == -1){
                    UWR4.setText("");
                } else {
                    UWR4.setText(String.valueOf(UWR4_TXT));
                }

                if(UWR5_TXT == -1){
                    UWR5.setText("");
                } else {
                    UWR5.setText(String.valueOf(UWR5_TXT));
                }

                if(LJP_TXT == -1){
                    LJP.setText("");
                } else {
                    LJP.setText(String.valueOf(LJP_TXT));
                }

                if(LJP2_TXT == -1){
                    LJP2.setText("");
                } else {
                    LJP2.setText(String.valueOf(LJP2_TXT));
                }

                if(LJP3_TXT == -1){
                    LJP3.setText("");
                } else {
                    LJP3.setText(String.valueOf(LJP3_TXT));
                }

                if(LJJ_TXT == -1){
                    LJJ.setText("");
                } else {
                    LJJ.setText(String.valueOf(LJJ_TXT));
                }

                if(LJJ2_TXT == -1){
                    LJJ2.setText("");
                } else {
                    LJJ2.setText(String.valueOf(LJJ2_TXT));
                }

                if(LJT_TXT == -1){
                    LJT.setText("");
                } else {
                    LJT.setText(String.valueOf(LJT_TXT));
                }

                if(LJT2_TXT == -1){
                    LJT2.setText("");
                } else {
                    LJT2.setText(String.valueOf(LJT2_TXT));
                }

                if(LJT3_TXT == -1){
                    LJT3.setText("");
                } else {
                    LJT3.setText(String.valueOf(LJT3_TXT));
                }

                if(LJT4_TXT == -1){
                    LJT4.setText("");
                } else {
                    LJT4.setText(String.valueOf(LJT4_TXT));
                }

                if(PPR_TXT == -1){
                    PPR.setText("");
                } else {
                    PPR.setText(String.valueOf(PPR_TXT));
                }

                if(PPR2_TXT == -1){
                    PPR2.setText("");
                } else {
                    PPR2.setText(String.valueOf(PPR2_TXT));
                }

                if(PPR3_TXT == -1){
                    PPR3.setText("");
                } else {
                    PPR3.setText(String.valueOf(PPR3_TXT));
                }

                if(PPR4_TXT == -1){
                    PPR4.setText("");
                } else {
                    PPR4.setText(String.valueOf(PPR4_TXT));
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
                    a6a2ArrayList.clear();
                    a6a2ArrayList.addAll(databaseHelper.allDataA6a2(ISEG));

                    adapter = new A6A2_Adapter(A6A2Activity.this, a6a2ArrayList);
                    a6a2_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a6a2_recycler.setVisibility(View.VISIBLE);
                    a6a2_recycler.startAnimation(animation);
                    UDR.setText("");
                    UWR.setText("");
                    UWR2.setText("");
                    UWR3.setText("");
                    UWR4.setText("");
                    UWR5.setText("");
                    LJP.setText("");
                    LJP2.setText("");
                    LJP3.setText("");
                    LJJ.setText("");
                    LJJ2.setText("");
                    LJT.setText("");
                    LJT2.setText("");
                    LJT3.setText("");
                    LJT4.setText("");
                    PPR.setText("");
                    PPR2.setText("");
                    PPR3.setText("");
                    PPR4.setText("");
                    REC.setText("");
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

                        new insertDataA6a2().execute((Void[])null);

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

        a6a2_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A6A2Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A2Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A2Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A2Activity.this, ImageViewActivity.class);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_2.png");
        }

        File mediaStorageDir4 = null;
        String state4 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_3.png");
        } else {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_3.png");
        }

        File mediaStorageDir5 = null;
        String state5 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_4.png");
        } else {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/"+ISEG+"_4.png");
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

        a6a2ArrayList.clear();

        a6a2ArrayList.addAll(databaseHelper.allDataA6a2(ISEG));

        if(a6a2ArrayList.isEmpty()){
            a6a2_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A6A2_Adapter(A6A2Activity.this, a6a2ArrayList);
            a6a2_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a6a2_recycler.setVisibility(View.VISIBLE);
            a6a2_recycler.startAnimation(animation);
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

    private class insertDataA6a2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A6a2 (ID_A6a2, ID_SEGMEN, UJI_A6a2_a1, UJI_A6a2_a2_1, UJI_A6a2_a2_2, UJI_A6a2_a2_3, UJI_A6a2_a2_4, UJI_A6a2_a2_5, UJI_A6a2_b1_1, UJI_A6a2_b1_2, UJI_A6a2_b1_3, " +
                        "UJI_A6a2_b2_1, UJI_A6a2_b2_2, UJI_A6a2_b3_1, UJI_A6a2_b3_2, UJI_A6a2_b3_3, UJI_A6a2_b3_4, UJI_A6a2_c1, UJI_A6a2_c2, UJI_A6a2_c3, UJI_A6a2_c4, CATATAN, " +
                        "DEV_A6a2_a1, DEV_A6a2_a2_1, DEV_A6a2_a2_2, DEV_A6a2_a2_3, DEV_A6a2_a2_4, DEV_A6a2_a2_5, DEV_A6a2_b1_1, DEV_A6a2_b1_2, DEV_A6a2_b1_3, DEV_A6a2_b2_1, DEV_A6a2_b2_2, DEV_A6a2_b3_1, " +
                        "DEV_A6a2_b3_2, DEV_A6a2_b3_3, DEV_A6a2_b3_4, DEV_A6a2_c1, DEV_A6a2_c2, DEV_A6a2_c3, DEV_A6a2_c4, KTG_A6a2_a, KTG_A6a2_b, KTG_A6a2_c, KTG_A6a2) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+UDR_TXT+"', '"+UWR_TXT+"', '"+UWR2_TXT+"', '"+UWR3_TXT+"', '"+UWR4_TXT+"', '"+UWR5_TXT+"', '"+LJP_TXT+"', '"+LJP2_TXT+"', '"+LJP3_TXT+"', " +
                        "'"+LJJ_TXT+"', '"+LJJ2_TXT+"', '"+LJT_TXT+"', '"+LJT2_TXT+"', '"+LJT3_TXT+"', '"+LJT4_TXT+"', '"+PPR_TXT+"', '"+PPR2_TXT+"', '"+PPR3_TXT+"', '"+PPR4_TXT+"', '"+REC_TXT+"', " +
                        "'"+DEV_UDR+"', '"+DEV_UWR+"', '"+DEV_UWR2+"', '"+DEV_UWR3+"', '"+DEV_UWR4+"', '"+DEV_UWR5+"', '"+DEV_LJP+"', '"+DEV_LJP2+"', '"+DEV_LJP3+"', '"+DEV_LJJ+"', '"+DEV_LJJ2+"', " +
                        "'"+DEV_LJT+"', '"+DEV_LJT2+"', '"+DEV_LJT3+"', '"+DEV_LJT4+"', '"+DEV_PPR+"', '"+DEV_PPR2+"', '"+DEV_PPR3+"', '"+DEV_PPR4+"', '"+KTG_UKW+"', '"+KTG_LRJ+"', '"+KTG_PPRR+"', " +
                        "'"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a2(ID, ISEG, UDR_TXT, UWR_TXT, UWR2_TXT, UWR3_TXT, UWR4_TXT, UWR5_TXT, LJP_TXT, LJP2_TXT,
                        LJP3_TXT, LJJ_TXT, LJJ2_TXT, LJT_TXT, LJT2_TXT, LJT3_TXT, LJT4_TXT, PPR_TXT, PPR2_TXT, PPR3_TXT, PPR4_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA6a2().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A6a2";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A2Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A6a2 SET GBR_1 = null WHERE ID_A6a2 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A6a2";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A2Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A6a2 SET GBR_2 = null WHERE ID_A6a2 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR3.equals("-")){
                try{
                    String Table = "A6a2";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A2Activity.this, uploadId, urlUpload3)
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

                    String insert = "UPDATE A6a2 SET GBR_3 = null WHERE ID_A6a2 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR4.equals("-")){
                try{
                    String Table = "A6a2";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A2Activity.this, uploadId, urlUpload4)
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

                    String insert = "UPDATE A6a2 SET GBR_4 = null WHERE ID_A6a2 = '"+ID+"'";

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

    private class updateDataA6a2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A6a2 SET ID_SEGMEN = '"+ISEG+"', UJI_A6a2_a1 = '"+UDR_TXT+"', UJI_A6a2_a2_1 = '"+UWR_TXT+"', UJI_A6a2_a2_2 = '"+UWR2_TXT+"', UJI_A6a2_a2_3 = '"+UWR3_TXT+"', " +
                        "UJI_A6a2_a2_4 = '"+UWR4_TXT+"', UJI_A6a2_a2_5 = '"+UWR5_TXT+"', UJI_A6a2_b1_1 = '"+LJP_TXT+"', UJI_A6a2_b1_2 = '"+LJP2_TXT+"', UJI_A6a2_b1_3 = '"+LJP3_TXT+"', " +
                        "UJI_A6a2_b2_1 = '"+LJJ_TXT+"', UJI_A6a2_b2_2 = '"+LJJ2_TXT+"', UJI_A6a2_b3_1 = '"+LJT_TXT+"', UJI_A6a2_b3_2 = '"+LJT2_TXT+"', UJI_A6a2_b3_3 = '"+LJT3_TXT+"', UJI_A6a2_b3_4 = '"+LJT4_TXT+"', " +
                        "UJI_A6a2_c1 = '"+PPR_TXT+"', UJI_A6a2_c2 = '"+PPR2_TXT+"', UJI_A6a2_c3 = '"+PPR3_TXT+"', UJI_A6a2_c4 = '"+PPR4_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A6a2_a1 = '"+DEV_UDR+"', DEV_A6a2_a2_1 = '"+DEV_UWR+"', DEV_A6a2_a2_2 = '"+DEV_UWR2+"', DEV_A6a2_a2_3 = '"+DEV_UWR3+"', DEV_A6a2_a2_4 = '"+DEV_UWR4+"', DEV_A6a2_a2_5 = '"+DEV_UWR5+"', " +
                        "DEV_A6a2_b1_1 = '"+DEV_LJP+"', DEV_A6a2_b1_2 = '"+DEV_LJP2+"', DEV_A6a2_b1_3 = '"+DEV_LJP3+"', DEV_A6a2_b2_1 = '"+DEV_LJJ+"', DEV_A6a2_b2_2 = '"+DEV_LJJ2+"', DEV_A6a2_b3_1 = '"+DEV_LJT+"', " +
                        "DEV_A6a2_b3_2 = '"+DEV_LJT2+"', DEV_A6a2_b3_3 = '"+DEV_LJT3+"', DEV_A6a2_b3_4 = '"+DEV_LJT4+"', DEV_A6a2_c1 = '"+DEV_PPR+"', DEV_A6a2_c2 = '"+DEV_PPR2+"', DEV_A6a2_c3 = '"+DEV_PPR3+"', " +
                        "DEV_A6a2_c4 = '"+DEV_PPR4+"', KTG_A6a2_a = '"+KTG_UKW+"', KTG_A6a2_b = '"+KTG_LRJ+"', KTG_A6a2_c = '"+KTG_PPRR+"', KTG_A6a2 = '"+KTG_KLF+"' WHERE ID_A6a2 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a2(ID, ISEG, UDR_TXT, UWR_TXT, UWR2_TXT, UWR3_TXT, UWR4_TXT, UWR5_TXT, LJP_TXT, LJP2_TXT,
                        LJP3_TXT, LJJ_TXT, LJJ2_TXT, LJT_TXT, LJT2_TXT, LJT3_TXT, LJT4_TXT, PPR_TXT, PPR2_TXT, PPR3_TXT, PPR4_TXT, REC_TXT, DIR1, DIR2, DIR3, DIR4, "T");

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
            if(UDR.getText().toString().equals("") && UWR.getText().toString().equals("")  &&
                    UWR2.getText().toString().equals("") && UWR3.getText().toString().equals("") && UWR4.getText().toString().equals("")  &&
                    UWR5.getText().toString().equals("") && LJP.getText().toString().equals("") && LJP2.getText().toString().equals("")  &&
                    LJP3.getText().toString().equals("") && LJJ.getText().toString().equals("") && LJJ2.getText().toString().equals("")  &&
                    LJT.getText().toString().equals("") && LJT2.getText().toString().equals("") && LJT3.getText().toString().equals("")  &&
                    LJT4.getText().toString().equals("") && PPR.getText().toString().equals("") && PPR2.getText().toString().equals("")  &&
                    PPR3.getText().toString().equals("") && PPR4.getText().toString().equals("") && REC.getText().toString().equals("")){
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

    private class getDataA6A2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A6A2Activity.this)
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

                String select = "SELECT ID_A6a2, UJI_A6a2_a1, UJI_A6a2_a2_1, UJI_A6a2_a2_2, UJI_A6a2_a2_3, UJI_A6a2_a2_4, UJI_A6a2_a2_5, UJI_A6a2_b1_1, UJI_A6a2_b1_2, UJI_A6a2_b1_3, " +
                        "UJI_A6a2_b2_1, UJI_A6a2_b2_2, UJI_A6a2_b3_1, UJI_A6a2_b3_2, UJI_A6a2_b3_3, UJI_A6a2_b3_4, UJI_A6a2_c1, UJI_A6a2_c2, UJI_A6a2_c3, UJI_A6a2_c4, CATATAN, GBR_1, GBR_2, GBR_3, GBR_4, ID_SEGMEN FROM A6a2 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA6a2Upload(data.getString("ID_SEGMEN"));

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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_1.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_2.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir3 = null;
                                String state3 = Environment.getExternalStorageState();

                                if (state3.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_3.png");
                                } else {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_3.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir4 = null;
                                String state4 = Environment.getExternalStorageState();

                                if (state4.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_4.png");
                                } else {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A2/Compress/"+ISEG+"_4.png");
                                }

                                FileOutputStream save4 = new FileOutputStream(mediaStorageDir4);
                                bitmap4.compress(Bitmap.CompressFormat.PNG, 100, save4);
                                save4.flush();

                                location4 = mediaStorageDir4.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        Boolean insertA6a2 = databaseHelper.insertDataA6a2(data.getString("ID_A6a2"), data.getString("ID_SEGMEN"),
                                data.getDouble("UJI_A6a2_a1"), data.getDouble("UJI_A6a2_a2_1"), data.getDouble("UJI_A6a2_a2_2"),
                                data.getDouble("UJI_A6a2_a2_3"), data.getDouble("UJI_A6a2_a2_4"), data.getDouble("UJI_A6a2_a2_5"),
                                data.getDouble("UJI_A6a2_b1_1"), data.getDouble("UJI_A6a2_b1_2"), data.getDouble("UJI_A6a2_b1_3"),
                                data.getDouble("UJI_A6a2_b2_1"), data.getDouble("UJI_A6a2_b2_2"), data.getDouble("UJI_A6a2_b3_1"),
                                data.getDouble("UJI_A6a2_b3_2"), data.getDouble("UJI_A6a2_b3_3"), data.getDouble("UJI_A6a2_b3_4"),
                                data.getDouble("UJI_A6a2_c1"), data.getDouble("UJI_A6a2_c2"), data.getDouble("UJI_A6a2_c3"),
                                data.getDouble("UJI_A6a2_c4"), data.getString("CATATAN"), location, location2, location3, location4, "T");

                        if(insertA6a2 == false){

                            databaseHelper.updateDataA6a2(data.getString("ID_A6a2"), data.getString("ID_SEGMEN"),
                                    data.getDouble("UJI_A6a2_a1"), data.getDouble("UJI_A6a2_a2_1"), data.getDouble("UJI_A6a2_a2_2"),
                                    data.getDouble("UJI_A6a2_a2_3"), data.getDouble("UJI_A6a2_a2_4"), data.getDouble("UJI_A6a2_a2_5"),
                                    data.getDouble("UJI_A6a2_b1_1"), data.getDouble("UJI_A6a2_b1_2"), data.getDouble("UJI_A6a2_b1_3"),
                                    data.getDouble("UJI_A6a2_b2_1"), data.getDouble("UJI_A6a2_b2_2"), data.getDouble("UJI_A6a2_b3_1"),
                                    data.getDouble("UJI_A6a2_b3_2"), data.getDouble("UJI_A6a2_b3_3"), data.getDouble("UJI_A6a2_b3_4"),
                                    data.getDouble("UJI_A6a2_c1"), data.getDouble("UJI_A6a2_c2"), data.getDouble("UJI_A6a2_c3"),
                                    data.getDouble("UJI_A6a2_c4"), data.getString("CATATAN"), location, location2, location3, location4, "T");

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

            a6a2ArrayList.addAll(databaseHelper.allDataA6a2(ISEG));

            if(!a6a2ArrayList.isEmpty()){
                a6a2_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A6A2_Adapter(A6A2Activity.this, a6a2ArrayList);
                        a6a2_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a6a2_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
