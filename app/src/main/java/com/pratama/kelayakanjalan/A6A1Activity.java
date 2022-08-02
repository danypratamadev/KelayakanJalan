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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class A6A1Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    public double KCP;
    private ImageButton back;
    public TextView klf;
    private TextInputEditText GSP, GSJ, GSL, GPP, GPL, GPJ, YLP, YLL, YLJ, ZCP, ZCL, ZCA, ZCG,
            GPM, GPL2, GPP2, GPL3, MCU, MCD, MCS, MCJ, GSL2, TJP, TJL, YBL,
            WMG, WMJ, WML, KMB, REC;
    public double GSP_TXT, GSJ_TXT, GSL_TXT, GPP_TXT, GPL_TXT, GPJ_TXT, YLP_TXT, YLL_TXT, YLJ_TXT,
            ZCP_TXT, ZCL_TXT, ZCA_TXT, ZCG_TXT, GPM_TXT, GPL2_TXT, GPP2_TXT, GPL3_TXT, MCU_TXT, MCD_TXT,
            MCS_TXT, MCJ_TXT, GSL2_TXT, TJP_TXT, TJL_TXT, YBL_TXT, WMG_TXT, WMJ_TXT, WML_TXT, KMB_TXT;
    public double DEV_GSP, DEV_GSJ, DEV_GSL, DEV_GPP, DEV_GPL, DEV_GPJ, DEV_YLP, DEV_YLL, DEV_YLJ, DEV_ZCP, DEV_ZCL, DEV_ZCA, DEV_ZCG,
            DEV_GPM, DEV_GPL2, DEV_GPP2, DEV_GPL3, DEV_MCU, DEV_MCD, DEV_MCS, DEV_MCJ, DEV_GSL2, DEV_TJP, DEV_TJL, DEV_YBL,
            DEV_WMG, DEV_WMJ, DEV_WML, DEV_KMB;
    public String KTG_UKW, KTG_KMB, KTG_KLF;
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
    private List<A6A1_Class> a6a1ArrayList = new ArrayList<>();
    private A6A1_Adapter adapter;
    private RecyclerViewBouncy a6a1_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a6a1;
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
        setContentView(R.layout.activity_a6a1);

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

        GSP = (TextInputEditText) findViewById(R.id.GSP);
        GSJ = (TextInputEditText) findViewById(R.id.GSJ);
        GSL = (TextInputEditText) findViewById(R.id.GSL);
        GPP = (TextInputEditText) findViewById(R.id.GPP);
        GPL = (TextInputEditText) findViewById(R.id.GPL);
        GPJ = (TextInputEditText) findViewById(R.id.GPJ);
        YLP = (TextInputEditText) findViewById(R.id.YLP);
        YLL = (TextInputEditText) findViewById(R.id.YLL);
        YLJ = (TextInputEditText) findViewById(R.id.YLJ);
        ZCP = (TextInputEditText) findViewById(R.id.ZCP);
        ZCL = (TextInputEditText) findViewById(R.id.ZCL);
        ZCA = (TextInputEditText) findViewById(R.id.ZCA);
        ZCG = (TextInputEditText) findViewById(R.id.ZCG);
        GPM = (TextInputEditText) findViewById(R.id.GPM);
        GPL2 = (TextInputEditText) findViewById(R.id.GPL2);
        GPP2 = (TextInputEditText) findViewById(R.id.GPP2);
        GPL3 = (TextInputEditText) findViewById(R.id.GPL3);
        MCU = (TextInputEditText) findViewById(R.id.MCU);
        MCD = (TextInputEditText) findViewById(R.id.MCD);
        MCS = (TextInputEditText) findViewById(R.id.MCS);
        MCJ = (TextInputEditText) findViewById(R.id.MCJ);
        GSL2 = (TextInputEditText) findViewById(R.id.GSL2);
        TJP = (TextInputEditText) findViewById(R.id.TJP);
        TJL = (TextInputEditText) findViewById(R.id.TJL);
        YBL = (TextInputEditText) findViewById(R.id.YBL);
        WMG = (TextInputEditText) findViewById(R.id.WMG);
        WMJ = (TextInputEditText) findViewById(R.id.WMJ);
        WML = (TextInputEditText) findViewById(R.id.WML);
        KMB = (TextInputEditText) findViewById(R.id.KMB);

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
        //recycler_a6a1 = (NestedScrollView) findViewById(R.id.recycler_a6a1);
        a6a1_recycler = (RecyclerViewBouncy) findViewById(R.id.a6a1_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        clear2.setVisibility(View.GONE);
        clear3.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a6a1_recycler.setHasFixedSize(true);
        a6a1_recycler.setLayoutManager(new LinearLayoutManager(this));

        a6a1_recycler.setVisibility(View.GONE);
        new getDataA6A1().execute((Void[])null);

    }

    private void getInputValue(){

        if(GSP.getText().toString().equals("")){
            GSP_TXT = -1;
        } else {
            GSP_TXT = Double.parseDouble(GSP.getText().toString());
        }

        if(GSJ.getText().toString().equals("")){
            GSJ_TXT = -1;
        } else {
            GSJ_TXT = Double.parseDouble(GSJ.getText().toString());
        }

        if(GSL.getText().toString().equals("")){
            GSL_TXT = -1;
        } else {
            GSL_TXT = Double.parseDouble(GSL.getText().toString());
        }
        if(GPP.getText().toString().equals("")){
            GPP_TXT = -1;
        } else {
            GPP_TXT = Double.parseDouble(GPP.getText().toString());
        }

        if(GPL.getText().toString().equals("")){
            GPL_TXT = -1;
        } else {
            GPL_TXT = Double.parseDouble(GPL.getText().toString());
        }

        if(GPJ.getText().toString().equals("")){
            GPJ_TXT = -1;
        } else {
            GPJ_TXT = Double.parseDouble(GPJ.getText().toString());
        }
        if(YLP.getText().toString().equals("")){
            YLP_TXT = -1;
        } else {
            YLP_TXT = Double.parseDouble(YLP.getText().toString());
        }

        if(YLL.getText().toString().equals("")){
            YLL_TXT = -1;
        } else {
            YLL_TXT = Double.parseDouble(YLL.getText().toString());
        }

        if(YLJ.getText().toString().equals("")){
            YLJ_TXT = -1;
        } else {
            YLJ_TXT = Double.parseDouble(YLJ.getText().toString());
        }
        if(ZCP.getText().toString().equals("")){
            ZCP_TXT = -1;
        } else {
            ZCP_TXT = Double.parseDouble(ZCP.getText().toString());
        }

        if(ZCL.getText().toString().equals("")){
            ZCL_TXT = -1;
        } else {
            ZCL_TXT = Double.parseDouble(ZCL.getText().toString());
        }

        if(ZCA.getText().toString().equals("")){
            ZCA_TXT = -1;
        } else {
            ZCA_TXT = Double.parseDouble(ZCA.getText().toString());
        }

        if(ZCG.getText().toString().equals("")){
            ZCG_TXT = -1;
        } else {
            ZCG_TXT = Double.parseDouble(ZCG.getText().toString());
        }

        if(GPM.getText().toString().equals("")){
            GPM_TXT = -1;
        } else {
            GPM_TXT = Double.parseDouble(GPM.getText().toString());
        }

        if(GPL2.getText().toString().equals("")){
            GPL2_TXT = -1;
        } else {
            GPL2_TXT = Double.parseDouble(GPL2.getText().toString());
        }
        if(GPP2.getText().toString().equals("")){
            GPP2_TXT = -1;
        } else {
            GPP2_TXT = Double.parseDouble(GPP2.getText().toString());
        }

        if(GPL3.getText().toString().equals("")){
            GPL3_TXT = -1;
        } else {
            GPL3_TXT = Double.parseDouble(GPL3.getText().toString());
        }

        if(MCU.getText().toString().equals("")){
            MCU_TXT = -1;
        } else {
            MCU_TXT = Double.parseDouble(MCU.getText().toString());
        }
        if(MCD.getText().toString().equals("")){
            MCD_TXT = -1;
        } else {
            MCD_TXT = Double.parseDouble(MCD.getText().toString());
        }

        if(MCS.getText().toString().equals("")){
            MCS_TXT = -1;
        } else {
            MCS_TXT = Double.parseDouble(MCS.getText().toString());
        }

        if(MCJ.getText().toString().equals("")){
            MCJ_TXT = -1;
        } else {
            MCJ_TXT = Double.parseDouble(MCJ.getText().toString());
        }
        if(GSL2.getText().toString().equals("")){
            GSL2_TXT = -1;
        } else {
            GSL2_TXT = Double.parseDouble(GSL2.getText().toString());
        }

        if(TJP.getText().toString().equals("")){
            TJP_TXT = -1;
        } else {
            TJP_TXT = Double.parseDouble(TJP.getText().toString());
        }

        if(TJL.getText().toString().equals("")){
            TJL_TXT = -1;
        } else {
            TJL_TXT = Double.parseDouble(TJL.getText().toString());
        }

        if(YBL.getText().toString().equals("")){
            YBL_TXT = -1;
        } else {
            YBL_TXT = Double.parseDouble(YBL.getText().toString());
        }

        if(WMG.getText().toString().equals("")){
            WMG_TXT = -1;
        } else {
            WMG_TXT = Double.parseDouble(WMG.getText().toString());
        }
        if(WMJ.getText().toString().equals("")){
            WMJ_TXT = -1;
        } else {
            WMJ_TXT = Double.parseDouble(WMJ.getText().toString());
        }

        if(WML.getText().toString().equals("")){
            WML_TXT = -1;
        } else {
            WML_TXT = Double.parseDouble(WML.getText().toString());
        }

        if(KMB.getText().toString().equals("")){
            KMB_TXT = -1;
        } else {
            KMB_TXT = Double.parseDouble(KMB.getText().toString());
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
                GSP.setText("");
                GSJ.setText("");
                GSL.setText("");
                GPP.setText("");
                GPL.setText("");
                GPJ.setText("");
                YLP.setText("");
                YLL.setText("");
                YLJ.setText("");
                ZCP.setText("");
                ZCL.setText("");
                ZCA.setText("");
                ZCG.setText("");
                GPM.setText("");
                GPL2.setText("");
                GPP2.setText("");
                GPL3.setText("");
                MCU.setText("");
                MCD.setText("");
                MCS.setText("");
                MCJ.setText("");
                GSL2.setText("");
                TJP.setText("");
                TJL.setText("");
                YBL.setText("");
                WMG.setText("");
                WMJ.setText("");
                WML.setText("");
                KMB.setText("");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_3.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_4.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1");
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

                    ID = ISEG + "_A6A1";

                    Boolean insertUjiA6A1 = databaseHelper.insertDataA6a1(ID, ISEG, GSP_TXT, GSJ_TXT, GSL_TXT, GPP_TXT, GPL_TXT, GPJ_TXT, YLP_TXT,
                            YLL_TXT, YLJ_TXT, ZCP_TXT, ZCL_TXT, ZCA_TXT, ZCG_TXT, GPM_TXT, GPL2_TXT, GPP2_TXT, GPL3_TXT, MCU_TXT, MCD_TXT,
                            MCS_TXT, MCJ_TXT, GSL2_TXT, TJP_TXT, TJL_TXT, YBL_TXT, WMG_TXT, WMJ_TXT, WML_TXT, KMB_TXT, REC_TXT, DIR1, DIR2,
                            DIR3, DIR4, "F");

                    if(insertUjiA6A1 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA6A1 = databaseHelper.updateDataA6a1(ID, ISEG, GSP_TXT, GSJ_TXT, GSL_TXT, GPP_TXT, GPL_TXT, GPJ_TXT,
                            YLP_TXT, YLL_TXT, YLJ_TXT, ZCP_TXT, ZCL_TXT, ZCA_TXT, ZCG_TXT, GPM_TXT, GPL2_TXT, GPP2_TXT, GPL3_TXT, MCU_TXT, MCD_TXT,
                            MCS_TXT, MCJ_TXT, GSL2_TXT, TJP_TXT, TJL_TXT, YBL_TXT, WMG_TXT, WMJ_TXT, WML_TXT, KMB_TXT, REC_TXT, DIR1, DIR2, DIR3,
                            DIR4, "F");

                    if(updateUjiA6A1 != false){
                        showPopup();
                    }
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a6a1_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                if(GSP_TXT == -1){
                    GSP.setText("");
                } else {
                    GSP.setText(String.valueOf(GSP_TXT));
                }

                if(GSJ_TXT == -1){
                    GSJ.setText("");
                } else {
                    GSJ.setText(String.valueOf(GSJ_TXT));
                }

                if(GSL_TXT == -1){
                    GSL.setText("");
                } else {
                    GSL.setText(String.valueOf(GSL_TXT));
                }

                if(GPP_TXT == -1){
                    GPP.setText("");
                } else {
                    GPP.setText(String.valueOf(GPP_TXT));
                }

                if(GPL_TXT == -1){
                    GPL.setText("");
                } else {
                    GPL.setText(String.valueOf(GPL_TXT));
                }

                if(GPJ_TXT == -1){
                    GPJ.setText("");
                } else {
                    GPJ.setText(String.valueOf(GPJ_TXT));
                }

                if(YLP_TXT == -1){
                    YLP.setText("");
                } else {
                    YLP.setText(String.valueOf(YLP_TXT));
                }

                if(YLL_TXT == -1){
                    YLL.setText("");
                } else {
                    YLL.setText(String.valueOf(YLL_TXT));
                }

                if(YLJ_TXT == -1){
                    YLJ.setText("");
                } else {
                    YLJ.setText(String.valueOf(YLJ_TXT));
                }

                if(ZCP_TXT == -1){
                    ZCP.setText("");
                } else {
                    ZCP.setText(String.valueOf(ZCP_TXT));
                }

                if(ZCL_TXT == -1){
                    ZCL.setText("");
                } else {
                    ZCL.setText(String.valueOf(ZCL_TXT));
                }

                if(ZCA_TXT == -1){
                    ZCA.setText("");
                } else {
                    ZCA.setText(String.valueOf(ZCA_TXT));
                }

                if(ZCG_TXT == -1){
                    ZCG.setText("");
                } else {
                    ZCG.setText(String.valueOf(ZCG_TXT));
                }

                if(GPM_TXT == -1){
                    GPM.setText("");
                } else {
                    GPM.setText(String.valueOf(GPM_TXT));
                }

                if(GPL2_TXT == -1){
                    GPL2.setText("");
                } else {
                    GPL2.setText(String.valueOf(GPL2_TXT));
                }

                if(GPP2_TXT == -1){
                    GPP2.setText("");
                } else {
                    GPP2.setText(String.valueOf(GPP2_TXT));
                }

                if(GPL3_TXT == -1){
                    GPL3.setText("");
                } else {
                    GPL3.setText(String.valueOf(GPL3_TXT));
                }

                if(MCU_TXT == -1){
                    MCU.setText("");
                } else {
                    MCU.setText(String.valueOf(MCU_TXT));
                }

                if(MCD_TXT == -1){
                    MCD.setText("");
                } else {
                    MCD.setText(String.valueOf(MCD_TXT));
                }

                if(MCS_TXT == -1){
                    MCS.setText("");
                } else {
                    MCS.setText(String.valueOf(MCS_TXT));
                }

                if(MCJ_TXT == -1){
                    MCJ.setText("");
                } else {
                    MCJ.setText(String.valueOf(MCJ_TXT));
                }

                if(GSL2_TXT == -1){
                    GSL2.setText("");
                } else {
                    GSL2.setText(String.valueOf(GSL2_TXT));
                }

                if(TJP_TXT == -1){
                    TJP.setText("");
                } else {
                    TJP.setText(String.valueOf(TJP_TXT));
                }

                if(TJL_TXT == -1){
                    TJL.setText("");
                } else {
                    TJL.setText(String.valueOf(TJL_TXT));
                }

                if(YBL_TXT == -1){
                    YBL.setText("");
                } else {
                    YBL.setText(String.valueOf(YBL_TXT));
                }

                if(WMG_TXT == -1){
                    WMG.setText("");
                } else {
                    WMG.setText(String.valueOf(WMG_TXT));
                }

                if(WMJ_TXT == -1){
                    WMJ.setText("");
                } else {
                    WMJ.setText(String.valueOf(WMJ_TXT));
                }

                if(WML_TXT == -1){
                    WML.setText("");
                } else {
                    WML.setText(String.valueOf(WML_TXT));
                }

                if(KMB_TXT == -1){
                    KMB.setText("");
                } else {
                    KMB.setText(String.valueOf(KMB_TXT));
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
                    a6a1ArrayList.clear();
                    a6a1ArrayList.addAll(databaseHelper.allDataA6a1(ISEG));

                    adapter = new A6A1_Adapter(A6A1Activity.this, a6a1ArrayList);
                    a6a1_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a6a1_recycler.setVisibility(View.VISIBLE);
                    a6a1_recycler.startAnimation(animation);
                    GSP.setText("");
                    GSJ.setText("");
                    GSL.setText("");
                    GPP.setText("");
                    GPL.setText("");
                    GPJ.setText("");
                    YLP.setText("");
                    YLL.setText("");
                    YLJ.setText("");
                    ZCP.setText("");
                    ZCL.setText("");
                    ZCA.setText("");
                    ZCG.setText("");
                    GPM.setText("");
                    GPL2.setText("");
                    GPP2.setText("");
                    GPL3.setText("");
                    MCU.setText("");
                    MCD.setText("");
                    MCS.setText("");
                    MCJ.setText("");
                    GSL2.setText("");
                    TJP.setText("");
                    TJL.setText("");
                    YBL.setText("");
                    WMG.setText("");
                    WMJ.setText("");
                    WML.setText("");
                    KMB.setText("");
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

                        new insertDataA6a1().execute((Void[])null);

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

        a6a1_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A6A1Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A1Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A1Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A1Activity.this, ImageViewActivity.class);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_2.png");
        }

        File mediaStorageDir4 = null;
        String state4 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_3.png");
        } else {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_3.png");
        }

        File mediaStorageDir5 = null;
        String state5 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_4.png");
        } else {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/"+ISEG+"_4.png");
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

        a6a1ArrayList.clear();

        a6a1ArrayList.addAll(databaseHelper.allDataA6a1(ISEG));

        if(a6a1ArrayList.isEmpty()){
            a6a1_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A6A1_Adapter(A6A1Activity.this, a6a1ArrayList);
            a6a1_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a6a1_recycler.setVisibility(View.VISIBLE);
            a6a1_recycler.startAnimation(animation);
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

    private class insertDataA6a1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A6a1 (ID_A6a1, ID_SEGMEN, UJI_A6a1_a1_1, UJI_A6a1_a1_2, UJI_A6a1_a1_3, UJI_A6a1_a2_1, UJI_A6a1_a2_2, UJI_A6a1_a2_3, UJI_A6a1_a3_1, UJI_A6a1_a3_2, UJI_A6a1_a3_3, " +
                        "UJI_A6a1_a4_1, UJI_A6a1_a4_2, UJI_A6a1_a4_3, UJI_A6a1_a4_4, UJI_A6a1_a5_1, UJI_A6a1_a5_2, UJI_A6a1_a6_1, UJI_A6a1_a6_2, UJI_A6a1_a7_1, UJI_A6a1_a7_2, UJI_A6a1_a7_3, UJI_A6a1_a7_4, " +
                        "UJI_A6a1_a8, UJI_A6a1_a9_1, UJI_A6a1_a9_2, UJI_A6a1_a10, UJI_A6a1_a11, UJI_A6a1_a12, UJI_A6a1_a13, UJI_A6a1_b, CATATAN, " +
                        "DEV_A6a1_a1_1, DEV_A6a1_a1_2, DEV_A6a1_a1_3, DEV_A6a1_a2_1, DEV_A6a1_a2_2, DEV_A6a1_a2_3, DEV_A6a1_a3_1, DEV_A6a1_a3_2, DEV_A6a1_a3_3, DEV_A6a1_a4_1, DEV_A6a1_a4_2, DEV_A6a1_a4_3, " +
                        "DEV_A6a1_a4_4, DEV_A6a1_a5_1, DEV_A6a1_a5_2, DEV_A6a1_a6_1, DEV_A6a1_a6_2, DEV_A6a1_a7_1, DEV_A6a1_a7_2, DEV_A6a1_a7_3, DEV_A6a1_a7_4, DEV_A6a1_a8, DEV_A6a1_a9_1, DEV_A6a1_a9_2, DEV_A6a1_a10, " +
                        "DEV_A6a1_a11, DEV_A6a1_a12, DEV_A6a1_a13, DEV_A6a1_b, KTG_A6a1_a, KTG_A6a1_b, KTG_A6a1) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+GSP_TXT+"', '"+GSJ_TXT+"', '"+GSL_TXT+"', '"+GPP_TXT+"', '"+GPL_TXT+"', '"+GPJ_TXT+"', '"+YLP_TXT+"', '"+YLL_TXT+"', '"+YLJ_TXT+"', " +
                        "'"+ZCP_TXT+"', '"+ZCL_TXT+"', '"+ZCA_TXT+"', '"+ZCG_TXT+"', '"+GPM_TXT+"', '"+GPL2_TXT+"', '"+GPP2_TXT+"', '"+GPL3_TXT+"', '"+MCU_TXT+"', '"+MCD_TXT+"', '"+MCS_TXT+"', " +
                        "'"+MCJ_TXT+"', '"+GSL2_TXT+"', '"+TJP_TXT+"', '"+TJL_TXT+"', '"+YBL_TXT+"', '"+WMG_TXT+"', '"+WMJ_TXT+"', '"+WML_TXT+"', '"+KMB_TXT+"', '"+REC_TXT+"', " +
                        "'"+DEV_GSP+"', '"+DEV_GSJ+"', '"+DEV_GSL+"', '"+DEV_GPP+"', '"+DEV_GPL+"', '"+DEV_GPJ+"', '"+DEV_YLP+"', '"+DEV_YLL+"', '"+DEV_YLJ+"', '"+DEV_ZCP+"', '"+DEV_ZCL+"', " +
                        "'"+DEV_ZCA+"', '"+DEV_ZCG+"', '"+DEV_GPM+"', '"+DEV_GPL2+"', '"+DEV_GPP2+"', '"+DEV_GPL3+"', '"+DEV_MCU+"', '"+DEV_MCD+"', '"+DEV_MCS+"', '"+DEV_MCJ+"', '"+DEV_GSL2+"', " +
                        "'"+DEV_TJP+"', '"+DEV_TJL+"', '"+DEV_YBL+"', '"+DEV_WMG+"', '"+DEV_WMJ+"', '"+DEV_WML+"', '"+DEV_KMB+"', '"+KTG_UKW+"', '"+KTG_KMB+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a1(ID, ISEG, GSP_TXT, GSJ_TXT, GSL_TXT, GPP_TXT, GPL_TXT, GPJ_TXT,
                        YLP_TXT, YLL_TXT, YLJ_TXT, ZCP_TXT, ZCL_TXT, ZCA_TXT, ZCG_TXT, GPM_TXT, GPL2_TXT, GPP2_TXT, GPL3_TXT, MCU_TXT, MCD_TXT,
                        MCS_TXT, MCJ_TXT, GSL2_TXT, TJP_TXT, TJL_TXT, YBL_TXT, WMG_TXT, WMJ_TXT, WML_TXT, KMB_TXT, REC_TXT, DIR1, DIR2, DIR3,
                        DIR4, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA6a1().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A6a1";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A1Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A6a1 SET GBR_1 = null WHERE ID_A6a1 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A6a1";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A1Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A6a1 SET GBR_2 = null WHERE ID_A6a1 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR3.equals("-")){
                try{
                    String Table = "A6a1";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A1Activity.this, uploadId, urlUpload3)
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

                    String insert = "UPDATE A6a1 SET GBR_3 = null WHERE ID_A6a1 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR4.equals("-")){
                try{
                    String Table = "A6a1";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A1Activity.this, uploadId, urlUpload4)
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

                    String insert = "UPDATE A6a1 SET GBR_4 = null WHERE ID_A6a1 = '"+ID+"'";

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

    private class updateDataA6a1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A6a1 SET ID_SEGMEN = '"+ISEG+"', UJI_A6a1_a1_1 = '"+GSP_TXT+"', UJI_A6a1_a1_2 = '"+GSJ_TXT+"', UJI_A6a1_a1_3 = '"+GSL_TXT+"', UJI_A6a1_a2_1 = '"+GPP_TXT+"', " +
                        "UJI_A6a1_a2_2 = '"+GPL_TXT+"', UJI_A6a1_a2_3 = '"+GPJ_TXT+"', UJI_A6a1_a3_1 = '"+YLP_TXT+"', UJI_A6a1_a3_2 = '"+YLL_TXT+"', UJI_A6a1_a3_3 = '"+YLJ_TXT+"', " +
                        "UJI_A6a1_a4_1 = '"+ZCP_TXT+"', UJI_A6a1_a4_2 = '"+ZCL_TXT+"', UJI_A6a1_a4_3 =  '"+ZCA_TXT+"', UJI_A6a1_a4_4 = '"+ZCG_TXT+"', UJI_A6a1_a5_1 = '"+GPM_TXT+"', UJI_A6a1_a5_2 = '"+GPL2_TXT+"', " +
                        "UJI_A6a1_a6_1 = '"+GPP2_TXT+"', UJI_A6a1_a6_2 = '"+GPL3_TXT+"', UJI_A6a1_a7_1 = '"+MCU_TXT+"', UJI_A6a1_a7_2 = '"+MCD_TXT+"', UJI_A6a1_a7_3 = '"+MCS_TXT+"', UJI_A6a1_a7_4 = '"+MCJ_TXT+"', " +
                        "UJI_A6a1_a8 = '"+GSL2_TXT+"', UJI_A6a1_a9_1 = '"+TJP_TXT+"', UJI_A6a1_a9_2 = '"+TJL_TXT+"', UJI_A6a1_a10 = '"+YBL_TXT+"', UJI_A6a1_a11 = '"+WMG_TXT+"', UJI_A6a1_a12 = '"+WMJ_TXT+"', " +
                        "UJI_A6a1_a13 = '"+WML_TXT+"', UJI_A6a1_b = '"+KMB_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A6a1_a1_1 = '"+DEV_GSP+"', DEV_A6a1_a1_2 = '"+DEV_GSJ+"', DEV_A6a1_a1_3 = '"+DEV_GSL+"', DEV_A6a1_a2_1 = '"+DEV_GPP+"', DEV_A6a1_a2_2 = '"+DEV_GPL+"', DEV_A6a1_a2_3 = '"+DEV_GPJ+"', " +
                        "DEV_A6a1_a3_1 = '"+DEV_YLP+"', DEV_A6a1_a3_2 = '"+DEV_YLL+"', DEV_A6a1_a3_3 = '"+DEV_YLJ+"', DEV_A6a1_a4_1 = '"+DEV_ZCP+"', DEV_A6a1_a4_2 = '"+DEV_ZCL+"', DEV_A6a1_a4_3 = '"+DEV_ZCA+"', " +
                        "DEV_A6a1_a4_4 = '"+DEV_ZCG+"', DEV_A6a1_a5_1 = '"+DEV_GPM+"', DEV_A6a1_a5_2 = '"+DEV_GPL2+"', DEV_A6a1_a6_1 = '"+DEV_GPP2+"', DEV_A6a1_a6_2 = '"+DEV_GPL3+"', DEV_A6a1_a7_1 = '"+DEV_MCU+"', " +
                        "DEV_A6a1_a7_2 = '"+DEV_MCD+"', DEV_A6a1_a7_3 = '"+DEV_MCS+"', DEV_A6a1_a7_4 = '"+DEV_MCJ+"', DEV_A6a1_a8 = '"+DEV_GSL2+"', DEV_A6a1_a9_1 = '"+DEV_TJP+"', DEV_A6a1_a9_2 = '"+DEV_TJL+"', " +
                        "DEV_A6a1_a10 = '"+DEV_YBL+"', DEV_A6a1_a11 = '"+DEV_WMG+"', DEV_A6a1_a12 = '"+DEV_WMJ+"', DEV_A6a1_a13 = '"+DEV_WML+"', DEV_A6a1_b = '"+DEV_KMB+"', KTG_A6a1_a = '"+KTG_UKW+"', KTG_A6a1_b = '"+KTG_KMB+"', " +
                        "KTG_A6a1 = '"+KTG_KLF+"' WHERE ID_A6a1 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a1(ID, ISEG, GSP_TXT, GSJ_TXT, GSL_TXT, GPP_TXT, GPL_TXT, GPJ_TXT,
                        YLP_TXT, YLL_TXT, YLJ_TXT, ZCP_TXT, ZCL_TXT, ZCA_TXT, ZCG_TXT, GPM_TXT, GPL2_TXT, GPP2_TXT, GPL3_TXT, MCU_TXT, MCD_TXT,
                        MCS_TXT, MCJ_TXT, GSL2_TXT, TJP_TXT, TJL_TXT, YBL_TXT, WMG_TXT, WMJ_TXT, WML_TXT, KMB_TXT, REC_TXT, DIR1, DIR2, DIR3,
                        DIR4, "T");

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
            if(GSP.getText().toString().equals("") && GSJ.getText().toString().equals("")  &&
                    GSL.getText().toString().equals("") && GPP.getText().toString().equals("") && GPL.getText().toString().equals("")  &&
                    GPJ.getText().toString().equals("") && YLP.getText().toString().equals("") && YLL.getText().toString().equals("")  &&
                    YLJ.getText().toString().equals("") && ZCP.getText().toString().equals("") && ZCL.getText().toString().equals("")  &&
                    ZCA.getText().toString().equals("") && ZCG.getText().toString().equals("") && GPM.getText().toString().equals("")  &&
                    GPL2.getText().toString().equals("") && GPP2.getText().toString().equals("") && GPL3.getText().toString().equals("")  &&
                    MCU.getText().toString().equals("") && MCD.getText().toString().equals("") && MCS.getText().toString().equals("")  &&
                    MCJ.getText().toString().equals("") && GSL2.getText().toString().equals("") && TJP.getText().toString().equals("")  &&
                    TJL.getText().toString().equals("") && YBL.getText().toString().equals("")  && WMG.getText().toString().equals("") &&
                    WMJ.getText().toString().equals("") && WML.getText().toString().equals("")  && KMB.getText().toString().equals("") && REC.getText().toString().equals("")){
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

    private class getDataA6A1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A6A1Activity.this)
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

                String select = "SELECT ID_A6a1, UJI_A6a1_a1_1, UJI_A6a1_a1_2, UJI_A6a1_a1_3, UJI_A6a1_a2_1, UJI_A6a1_a2_2, UJI_A6a1_a2_3, UJI_A6a1_a3_1, UJI_A6a1_a3_2, UJI_A6a1_a3_3, " +
                        "UJI_A6a1_a4_1, UJI_A6a1_a4_2, UJI_A6a1_a4_3, UJI_A6a1_a4_4, UJI_A6a1_a5_1, UJI_A6a1_a5_2, UJI_A6a1_a6_1, UJI_A6a1_a6_2, UJI_A6a1_a7_1, UJI_A6a1_a7_2, UJI_A6a1_a7_3, UJI_A6a1_a7_4, " +
                        "UJI_A6a1_a8, UJI_A6a1_a9_1, UJI_A6a1_a9_2, UJI_A6a1_a10, UJI_A6a1_a11, UJI_A6a1_a12, UJI_A6a1_a13, UJI_A6a1_b, CATATAN, GBR_1, GBR_2, GBR_3, GBR_4, ID_SEGMEN FROM A6a1 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA6a1Upload(data.getString("ID_SEGMEN"));

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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_1.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_2.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir3 = null;
                                String state3 = Environment.getExternalStorageState();

                                if (state3.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_3.png");
                                } else {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_3.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir4 = null;
                                String state4 = Environment.getExternalStorageState();

                                if (state4.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_4.png");
                                } else {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A1/Compress/"+ISEG+"_4.png");
                                }

                                FileOutputStream save4 = new FileOutputStream(mediaStorageDir4);
                                bitmap4.compress(Bitmap.CompressFormat.PNG, 100, save4);
                                save4.flush();

                                location4 = mediaStorageDir4.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        Boolean insertA6a1 = databaseHelper.insertDataA6a1(data.getString("ID_A6a1"), data.getString("ID_SEGMEN"),
                                data.getDouble("UJI_A6a1_a1_1"), data.getDouble("UJI_A6a1_a1_2"), data.getDouble("UJI_A6a1_a1_3"),
                                data.getDouble("UJI_A6a1_a2_1"), data.getDouble("UJI_A6a1_a2_2"), data.getDouble("UJI_A6a1_a2_3"),
                                data.getDouble("UJI_A6a1_a3_1"), data.getDouble("UJI_A6a1_a3_2"), data.getDouble("UJI_A6a1_a3_3"),
                                data.getDouble("UJI_A6a1_a4_1"), data.getDouble("UJI_A6a1_a4_2"), data.getDouble("UJI_A6a1_a4_3"),
                                data.getDouble("UJI_A6a1_a4_4"), data.getDouble("UJI_A6a1_a5_1"), data.getDouble("UJI_A6a1_a5_2"),
                                data.getDouble("UJI_A6a1_a6_1"), data.getDouble("UJI_A6a1_a6_2"), data.getDouble("UJI_A6a1_a7_1"),
                                data.getDouble("UJI_A6a1_a7_2"), data.getDouble("UJI_A6a1_a7_3"), data.getDouble("UJI_A6a1_a7_4"),
                                data.getDouble("UJI_A6a1_a8"), data.getDouble("UJI_A6a1_a9_1"), data.getDouble("UJI_A6a1_a9_2"),
                                data.getDouble("UJI_A6a1_a10"), data.getDouble("UJI_A6a1_a11"), data.getDouble("UJI_A6a1_a12"),
                                data.getDouble("UJI_A6a1_a13"), data.getDouble("UJI_A6a1_b"),
                                data.getString("CATATAN"), location, location2, location3, location4, "T");

                        if(insertA6a1 == false){

                            databaseHelper.updateDataA6a1(data.getString("ID_A6a1"), data.getString("ID_SEGMEN"),
                                    data.getDouble("UJI_A6a1_a1_1"), data.getDouble("UJI_A6a1_a1_2"), data.getDouble("UJI_A6a1_a1_3"),
                                    data.getDouble("UJI_A6a1_a2_1"), data.getDouble("UJI_A6a1_a2_2"), data.getDouble("UJI_A6a1_a2_3"),
                                    data.getDouble("UJI_A6a1_a3_1"), data.getDouble("UJI_A6a1_a3_2"), data.getDouble("UJI_A6a1_a3_3"),
                                    data.getDouble("UJI_A6a1_a4_1"), data.getDouble("UJI_A6a1_a4_2"), data.getDouble("UJI_A6a1_a4_3"),
                                    data.getDouble("UJI_A6a1_a4_4"), data.getDouble("UJI_A6a1_a5_1"), data.getDouble("UJI_A6a1_a5_2"),
                                    data.getDouble("UJI_A6a1_a6_1"), data.getDouble("UJI_A6a1_a6_2"), data.getDouble("UJI_A6a1_a7_1"),
                                    data.getDouble("UJI_A6a1_a7_2"), data.getDouble("UJI_A6a1_a7_3"), data.getDouble("UJI_A6a1_a7_4"),
                                    data.getDouble("UJI_A6a1_a8"), data.getDouble("UJI_A6a1_a9_1"), data.getDouble("UJI_A6a1_a9_2"),
                                    data.getDouble("UJI_A6a1_a10"), data.getDouble("UJI_A6a1_a11"), data.getDouble("UJI_A6a1_a12"),
                                    data.getDouble("UJI_A6a1_a13"), data.getDouble("UJI_A6a1_b"),
                                    data.getString("CATATAN"), location, location2, location3, location4, "T");

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

            a6a1ArrayList.addAll(databaseHelper.allDataA6a1(ISEG));

            if(!a6a1ArrayList.isEmpty()){
                a6a1_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A6A1_Adapter(A6A1Activity.this, a6a1ArrayList);
                        a6a1_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a6a1_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
