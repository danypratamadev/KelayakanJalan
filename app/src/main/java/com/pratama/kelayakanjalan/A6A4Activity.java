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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class A6A4Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public String SJR, FNG, KPR, MJL, NRU, ISEG;
    public double KCP;
    public String ID;
    private ImageButton back;
    public TextView klf;
    private TextInputEditText JLK, TKM, DGP, DGL, DGB, DGJ, GPL, GPG, CLU, CLC, CSC, CBC, RPT, RPT2, RPT3, RPT4, REC;
    public double JLK_TXT, TKM_TXT, DGP_TXT, DGL_TXT, DGB_TXT, DGJ_TXT, GPL_TXT, GPG_TXT, CLU_TXT, CLC_TXT, CSC_TXT,
            CBC_TXT, RPT_TXT, RPT2_TXT, RPT3_TXT, RPT4_TXT;
    public double DEV_JLK, DEV_TKM, DEV_DGP, DEV_DGL, DEV_DGB, DEV_DGJ, DEV_GPL, DEV_GPG, DEV_CLU,
            DEV_CLC, DEV_CSC, DEV_CBC, DEV_RPT, DEV_RPT2, DEV_RPT3, DEV_RPT4;
    public String  KTG_JLK, KTG_TKM, KTG_DMU, KTG_KLF;
    private Button SAVE;
    public FABProgressCircle FAB_UPLOAD;
    public FloatingActionButton EDIT, UPLOAD;
    private Boolean connected = false;
    private CoordinatorLayout coordinatorLayout;
    public ImageView FT1, FT2;
    private int REQ1 = 1, REQ2 = 2, REQ4 = 3, REQ5 = 4;
    public String REC_TXT, DIR1 = "-", DIR2 = "-";
    private double STD = 100;
    private Dialog myDialog;
    private List<A6A4_Class> a6a4ArrayList = new ArrayList<>();
    private A6A4_Adapter adapter;
    private RecyclerViewBouncy a6a4_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a6a4;
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
        setContentView(R.layout.activity_a6a4);

        init();
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
        KCP = intent.getDoubleExtra("kcp", 0);

        klf = (TextView) findViewById(R.id.ktg_klf);

        JLK = (TextInputEditText) findViewById(R.id.JLK);
        TKM = (TextInputEditText) findViewById(R.id.TKM);
        DGP = (TextInputEditText) findViewById(R.id.DGP);
        DGL = (TextInputEditText) findViewById(R.id.DGL);
        DGB = (TextInputEditText) findViewById(R.id.DGB);
        DGJ = (TextInputEditText) findViewById(R.id.DGJ);
        GPL = (TextInputEditText) findViewById(R.id.GPL);
        GPG = (TextInputEditText) findViewById(R.id.GPG);
        CLU = (TextInputEditText) findViewById(R.id.CLU);
        CLC = (TextInputEditText) findViewById(R.id.CLC);
        CSC = (TextInputEditText) findViewById(R.id.CSC);
        CBC = (TextInputEditText) findViewById(R.id.CBC);
        RPT = (TextInputEditText) findViewById(R.id.RPT);
        RPT2 = (TextInputEditText) findViewById(R.id.RPT2);
        RPT3 = (TextInputEditText) findViewById(R.id.RPT3);
        RPT4 = (TextInputEditText) findViewById(R.id.RPT4);

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
        INPUT = (LinearLayout) findViewById(R.id.INPUT);
        aksi = (RelativeLayout) findViewById(R.id.aksi);
        //recycler_a6a4 = (NestedScrollView) findViewById(R.id.recycler_a6a4);
        a6a4_recycler = (RecyclerViewBouncy) findViewById(R.id.a6a4_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a6a4_recycler.setHasFixedSize(true);
        a6a4_recycler.setLayoutManager(new LinearLayoutManager(this));

        a6a4_recycler.setVisibility(View.GONE);
        new getDataA6A4().execute((Void[])null);

    }

    private void getInputValue(){

        if(JLK.getText().toString().equals("")){
            JLK_TXT = -1;
        } else {
            JLK_TXT = Double.parseDouble(JLK.getText().toString());
        }

        if(TKM.getText().toString().equals("")){
            TKM_TXT = -1;
        } else {
            TKM_TXT = Double.parseDouble(TKM.getText().toString());
        }

        if(DGP.getText().toString().equals("")){
            DGP_TXT = -1;
        } else {
            DGP_TXT = Double.parseDouble(DGP.getText().toString());
        }
        if(DGL.getText().toString().equals("")){
            DGL_TXT = -1;
        } else {
            DGL_TXT = Double.parseDouble(DGL.getText().toString());
        }
        if(DGB.getText().toString().equals("")){
            DGB_TXT = -1;
        } else {
            DGB_TXT = Double.parseDouble(DGB.getText().toString());
        }
        if(DGJ.getText().toString().equals("")){
            DGJ_TXT = -1;
        } else {
            DGJ_TXT = Double.parseDouble(DGJ.getText().toString());
        }

        if(GPL.getText().toString().equals("")){
            GPL_TXT = -1;
        } else {
            GPL_TXT = Double.parseDouble(GPL.getText().toString());
        }

        if(GPG.getText().toString().equals("")){
            GPG_TXT = -1;
        } else {
            GPG_TXT = Double.parseDouble(GPG.getText().toString());
        }

        if(CLU.getText().toString().equals("")){
            CLU_TXT = -1;
        } else {
            CLU_TXT = Double.parseDouble(CLU.getText().toString());
        }
        if(CLC.getText().toString().equals("")){
            CLC_TXT = -1;
        } else {
            CLC_TXT = Double.parseDouble(CLC.getText().toString());
        }
        if(CSC.getText().toString().equals("")){
            CSC_TXT = -1;
        } else {
            CSC_TXT = Double.parseDouble(CSC.getText().toString());
        }
        if(CBC.getText().toString().equals("")){
            CBC_TXT = -1;
        } else {
            CBC_TXT = Double.parseDouble(CBC.getText().toString());
        }

        if(RPT.getText().toString().equals("")){
            RPT_TXT = -1;
        } else {
            RPT_TXT = Double.parseDouble(RPT.getText().toString());
        }
        if(RPT2.getText().toString().equals("")){
            RPT2_TXT = -1;
        } else {
            RPT2_TXT = Double.parseDouble(RPT2.getText().toString());
        }
        if(RPT3.getText().toString().equals("")){
            RPT3_TXT = -1;
        } else {
            RPT3_TXT = Double.parseDouble(RPT3.getText().toString());
        }
        if(RPT4.getText().toString().equals("")){
            RPT4_TXT = -1;
        } else {
            RPT4_TXT = Double.parseDouble(RPT4.getText().toString());
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
                JLK.setText("");
                TKM.setText("");
                DGP.setText("");
                DGL.setText("");
                DGB.setText("");
                DGJ.setText("");
                GPL.setText("");
                GPG.setText("");
                CLU.setText("");
                CLC.setText("");
                CSC.setText("");
                CBC.setText("");
                RPT.setText("");
                RPT2.setText("");
                RPT3.setText("");
                RPT4.setText("");

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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4");
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

                    ID = ISEG + "_A6A4";

                    Boolean insertUjiA6A4 = databaseHelper.insertDataA6a4(ID, ISEG, JLK_TXT, TKM_TXT, DGP_TXT, DGL_TXT, DGB_TXT, DGJ_TXT, GPL_TXT, GPG_TXT, CLU_TXT, CLC_TXT,
                            CSC_TXT, CBC_TXT, RPT_TXT, RPT2_TXT, RPT3_TXT, RPT4_TXT, REC_TXT, DIR1, DIR2, "F");

                    if(insertUjiA6A4 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA6A4 = databaseHelper.updateDataA6a4(ID, ISEG,  JLK_TXT, TKM_TXT, DGP_TXT, DGL_TXT, DGB_TXT, DGJ_TXT, GPL_TXT, GPG_TXT, CLU_TXT, CLC_TXT,
                            CSC_TXT, CBC_TXT, RPT_TXT, RPT2_TXT, RPT3_TXT, RPT4_TXT, REC_TXT, DIR1, DIR2, "F");

                    if(updateUjiA6A4 != false){
                        showPopup();
                    }
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a6a4_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                if(JLK_TXT == -1){
                    JLK.setText("");
                } else {
                    JLK.setText(String.valueOf(JLK_TXT));
                }

                if(TKM_TXT == -1){
                    TKM.setText("");
                } else {
                    TKM.setText(String.valueOf(TKM_TXT));
                }

                if(DGP_TXT == -1){
                    DGP.setText("");
                } else {
                    DGP.setText(String.valueOf(DGP_TXT));
                }

                if(DGL_TXT == -1){
                    DGL.setText("");
                } else {
                    DGL.setText(String.valueOf(DGL_TXT));
                }

                if(DGB_TXT == -1){
                    DGB.setText("");
                } else {
                    DGB.setText(String.valueOf(DGB_TXT));
                }

                if(DGJ_TXT == -1){
                    DGJ.setText("");
                } else {
                    DGJ.setText(String.valueOf(DGJ_TXT));
                }

                if(GPL_TXT == -1){
                    GPL.setText("");
                } else {
                    GPL.setText(String.valueOf(GPL_TXT));
                }

                if(GPG_TXT == -1){
                    GPG.setText("");
                } else {
                    GPG.setText(String.valueOf(GPG_TXT));
                }

                if(CLU_TXT == -1){
                    CLU.setText("");
                } else {
                    CLU.setText(String.valueOf(CLU_TXT));
                }

                if(CLC_TXT == -1){
                    CLC.setText("");
                } else {
                    CLC.setText(String.valueOf(CLC_TXT));
                }

                if(CSC_TXT == -1){
                    CSC.setText("");
                } else {
                    CSC.setText(String.valueOf(CSC_TXT));
                }

                if(CBC_TXT == -1){
                    CBC.setText("");
                } else {
                    CBC.setText(String.valueOf(CBC_TXT));
                }

                if(RPT_TXT == -1){
                    RPT.setText("");
                } else {
                    RPT.setText(String.valueOf(RPT_TXT));
                }

                if(RPT2_TXT == -1){
                    RPT2.setText("");
                } else {
                    RPT2.setText(String.valueOf(RPT2_TXT));
                }

                if(RPT3_TXT == -1){
                    RPT3.setText("");
                } else {
                    RPT3.setText(String.valueOf(RPT3_TXT));
                }

                if(RPT4_TXT == -1){
                    RPT4.setText("");
                } else {
                    RPT4.setText(String.valueOf(RPT4_TXT));
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
                    a6a4ArrayList.clear();
                    a6a4ArrayList.addAll(databaseHelper.allDataA6a4(ISEG));

                    adapter = new A6A4_Adapter(A6A4Activity.this, a6a4ArrayList);
                    a6a4_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a6a4_recycler.setVisibility(View.VISIBLE);
                    a6a4_recycler.startAnimation(animation);
                    JLK.setText("");
                    TKM.setText("");
                    DGP.setText("");
                    DGL.setText("");
                    DGB.setText("");
                    DGJ.setText("");
                    GPL.setText("");
                    GPG.setText("");
                    CLU.setText("");
                    CLC.setText("");
                    CSC.setText("");
                    CBC.setText("");
                    RPT.setText("");
                    RPT2.setText("");
                    RPT3.setText("");
                    RPT4.setText("");
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

                        new insertDataA6a4().execute((Void[])null);

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

        a6a4_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A6A4Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6A4Activity.this, ImageViewActivity.class);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state2.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state3.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/"+ISEG+"_2.png");
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

        a6a4ArrayList.clear();

        a6a4ArrayList.addAll(databaseHelper.allDataA6a4(ISEG));

        if(a6a4ArrayList.isEmpty()){
            a6a4_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A6A4_Adapter(A6A4Activity.this, a6a4ArrayList);
            a6a4_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a6a4_recycler.setVisibility(View.VISIBLE);
            a6a4_recycler.startAnimation(animation);
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

    private class insertDataA6a4 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A6a4 (ID_A6a4, ID_SEGMEN, UJI_A6a4_a, UJI_A6a4_b, UJI_A6a4_c1_1, UJI_A6a4_c1_2, UJI_A6a4_c1_3, UJI_A6a4_c1_4, UJI_A6a4_c2_1, UJI_A6a4_c2_2, " +
                        "UJI_A6a4_c3_1, UJI_A6a4_c3_2, UJI_A6a4_c3_3, UJI_A6a4_c3_4, UJI_A6a4_c4_1, UJI_A6a4_c4_2, UJI_A6a4_c5_1, UJI_A6a4_c5_2, CATATAN, " +
                        "DEV_A6a4_a, DEV_A6a4_b, DEV_A6a4_c1_1, DEV_A6a4_c1_2, DEV_A6a4_c1_3, DEV_A6a4_c1_4, DEV_A6a4_c2_1, DEV_A6a4_c2_2, DEV_A6a4_c3_1, DEV_A6a4_c3_2, DEV_A6a4_c3_3, " +
                        "DEV_A6a4_c3_4, DEV_A6a4_c4_1, DEV_A6a4_c4_2, DEV_A6a4_c5_1, DEV_A6a4_c5_2, KTG_A6a4_a, KTG_A6a4_b, KTG_A6a4_c, KTG_A6a4) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+JLK_TXT+"', '"+TKM_TXT+"', '"+DGP_TXT+"', '"+DGL_TXT+"', '"+DGB_TXT+"', '"+DGJ_TXT+"', '"+GPL_TXT+"', " +
                        "'"+GPG_TXT+"', '"+CLU_TXT+"', '"+CLC_TXT+"', '"+CSC_TXT+"', '"+CBC_TXT+"', '"+RPT_TXT+"', '"+RPT2_TXT+"', '"+RPT3_TXT+"', '"+RPT4_TXT+"', '"+REC_TXT+"', " +
                        "'"+DEV_JLK+"', '"+DEV_TKM+"', '"+DEV_DGP+"', '"+DEV_DGL+"', '"+DEV_DGB+"', '"+DEV_DGJ+"', '"+DEV_GPL+"', '"+DEV_GPG+"', '"+DEV_CLU+"', '"+DEV_CLC+"', " +
                        "'"+DEV_CSC+"', '"+DEV_CBC+"', '"+DEV_RPT+"', '"+DEV_RPT2+"', '"+DEV_RPT3+"', '"+DEV_RPT4+"', '"+KTG_JLK+"', '"+KTG_TKM+"', '"+KTG_DMU+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a4(ID, ISEG,  JLK_TXT, TKM_TXT, DGP_TXT, DGL_TXT, DGB_TXT, DGJ_TXT, GPL_TXT, GPG_TXT, CLU_TXT, CLC_TXT,
                        CSC_TXT, CBC_TXT, RPT_TXT, RPT2_TXT, RPT3_TXT, RPT4_TXT, REC_TXT, DIR1, DIR2, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA6a4().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A6a4";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A4Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A6a4 SET GBR_1 = null WHERE ID_A6a4 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A6a4";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A6A4Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A6a4 SET GBR_2 = null WHERE ID_A6a4 = '"+ID+"'";

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

    private class updateDataA6a4 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A6a4 SET ID_SEGMEN = '"+ISEG+"', UJI_A6a4_a = '"+JLK_TXT+"', UJI_A6a4_b = '"+TKM_TXT+"', UJI_A6a4_c1_1 = '"+DGP_TXT+"', UJI_A6a4_c1_2 = '"+DGL_TXT+"', UJI_A6a4_c1_3 = '"+DGB_TXT+"', UJI_A6a4_c1_4 = '"+DGJ_TXT+"', UJI_A6a4_c2_1 = '"+GPL_TXT+"', UJI_A6a4_c2_2 = '"+GPG_TXT+"', " +
                        "UJI_A6a4_c3_1 = '"+CLU_TXT+"', UJI_A6a4_c3_2 = '"+CLC_TXT+"', UJI_A6a4_c3_3 = '"+CSC_TXT+"', UJI_A6a4_c3_4 = '"+CBC_TXT+"', UJI_A6a4_c4_1 = '"+RPT_TXT+"', UJI_A6a4_c4_2 = '"+RPT2_TXT+"', UJI_A6a4_c5_1 = '"+RPT3_TXT+"', UJI_A6a4_c5_2 = '"+RPT4_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A6a4_a = '"+DEV_JLK+"', DEV_A6a4_b = '"+DEV_TKM+"', DEV_A6a4_c1_1 = '"+DEV_DGP+"', DEV_A6a4_c1_2 = '"+DEV_DGL+"', DEV_A6a4_c1_3 = '"+DEV_DGB+"', DEV_A6a4_c1_4 = '"+DEV_DGJ+"', DEV_A6a4_c2_1 = '"+DEV_GPL+"', DEV_A6a4_c2_2 = '"+DEV_GPG+"', DEV_A6a4_c3_1 = '"+DEV_CLU+"', DEV_A6a4_c3_2 = '"+DEV_CLC+"', DEV_A6a4_c3_3 = '"+DEV_CSC+"', " +
                        "DEV_A6a4_c3_4 = '"+DEV_CBC+"', DEV_A6a4_c4_1 = '"+DEV_RPT+"', DEV_A6a4_c4_2 = '"+DEV_RPT2+"', DEV_A6a4_c5_1 = '"+DEV_RPT3+"', DEV_A6a4_c5_2 = '"+DEV_RPT4+"', KTG_A6a4_a = '"+KTG_JLK+"', KTG_A6a4_b = '"+KTG_TKM+"', KTG_A6a4_c = '"+KTG_DMU+"', KTG_A6a4 = '"+KTG_KLF+"' WHERE ID_A6a4 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA6a4(ID, ISEG,  JLK_TXT, TKM_TXT, DGP_TXT, DGL_TXT, DGB_TXT, DGJ_TXT, GPL_TXT, GPG_TXT, CLU_TXT, CLC_TXT,
                        CSC_TXT, CBC_TXT, RPT_TXT, RPT2_TXT, RPT3_TXT, RPT4_TXT, REC_TXT, DIR1, DIR2, "T");

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
            if(JLK.getText().toString().equals("") && TKM.getText().toString().equals("")  &&
                    DGP.getText().toString().equals("") && DGL.getText().toString().equals("") && DGB.getText().toString().equals("")
                    && DGJ.getText().toString().equals("") && GPL.getText().toString().equals("") && GPG.getText().toString().equals("")  &&
                    CLU.getText().toString().equals("") && CLC.getText().toString().equals("") && CSC.getText().toString().equals("")
                    && CBC.getText().toString().equals("") && RPT.getText().toString().equals("") && RPT2.getText().toString().equals("") &&
                    RPT3.getText().toString().equals("") && RPT4.getText().toString().equals("") &&  REC.getText().toString().equals("")){
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

    private class getDataA6A4 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A6A4Activity.this)
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

                String select = "SELECT ID_A6a4, UJI_A6a4_a, UJI_A6a4_b, UJI_A6a4_c1_1, UJI_A6a4_c1_2, UJI_A6a4_c1_3, UJI_A6a4_c1_4, UJI_A6a4_c2_1, UJI_A6a4_c2_2, " +
                        "UJI_A6a4_c3_1, UJI_A6a4_c3_2, UJI_A6a4_c3_3, UJI_A6a4_c3_4, UJI_A6a4_c4_1, UJI_A6a4_c4_2, UJI_A6a4_c5_1, UJI_A6a4_c5_2, CATATAN, GBR_1, GBR_2, ID_SEGMEN FROM A6a4 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA6a4Upload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

                        String dir1 = data.getString("GBR_1");
                        String dir2 = data.getString("GBR_2");

                        if(dir1 != null){

                            dir1 = dir1.replaceAll(" ", "%20");

                            Bitmap bitmap = null;
                            try {

                                InputStream fto = new java.net.URL(dir1).openStream();

                                bitmap = BitmapFactory.decodeStream(fto);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress/"+ISEG+"_1.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A6A4/Compress/"+ISEG+"_2.png");
                                }

                                FileOutputStream save = new FileOutputStream(mediaStorageDir2);
                                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, save);
                                save.flush();

                                location2 = mediaStorageDir2.getAbsolutePath();

                            } catch (Exception e) {
                                Log.e("Error Image ", e.toString());
                            }

                        }

                        Boolean insertA6a4 = databaseHelper.insertDataA6a4(data.getString("ID_A6a4"), data.getString("ID_SEGMEN"),
                                data.getDouble("UJI_A6a4_a"), data.getDouble("UJI_A6a4_b"), data.getDouble("UJI_A6a4_c1_1"),
                                data.getDouble("UJI_A6a4_c1_2"), data.getDouble("UJI_A6a4_c1_3"), data.getDouble("UJI_A6a4_c1_4"),
                                data.getDouble("UJI_A6a4_c2_1"), data.getDouble("UJI_A6a4_c2_2"), data.getDouble("UJI_A6a4_c3_1"),
                                data.getDouble("UJI_A6a4_c3_2"), data.getDouble("UJI_A6a4_c3_3"), data.getDouble("UJI_A6a4_c3_4"),
                                data.getDouble("UJI_A6a4_c4_1"), data.getDouble("UJI_A6a4_c4_2"), data.getDouble("UJI_A6a4_c5_1"),
                                data.getDouble("UJI_A6a4_c5_2"), data.getString("CATATAN"), location, location2, "T");

                        if(insertA6a4 == false){

                            databaseHelper.updateDataA6a4(data.getString("ID_A6a4"), data.getString("ID_SEGMEN"),
                                    data.getDouble("UJI_A6a4_a"), data.getDouble("UJI_A6a4_b"), data.getDouble("UJI_A6a4_c1_1"),
                                    data.getDouble("UJI_A6a4_c1_2"), data.getDouble("UJI_A6a4_c1_3"), data.getDouble("UJI_A6a4_c1_4"),
                                    data.getDouble("UJI_A6a4_c2_1"), data.getDouble("UJI_A6a4_c2_2"), data.getDouble("UJI_A6a4_c3_1"),
                                    data.getDouble("UJI_A6a4_c3_2"), data.getDouble("UJI_A6a4_c3_3"), data.getDouble("UJI_A6a4_c3_4"),
                                    data.getDouble("UJI_A6a4_c4_1"), data.getDouble("UJI_A6a4_c4_2"), data.getDouble("UJI_A6a4_c5_1"),
                                    data.getDouble("UJI_A6a4_c5_2"), data.getString("CATATAN"), location, location2, "T");

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

            a6a4ArrayList.addAll(databaseHelper.allDataA6a4(ISEG));

            if(!a6a4ArrayList.isEmpty()){
                a6a4_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A6A4_Adapter(A6A4Activity.this, a6a4ArrayList);
                        a6a4_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a6a4_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
