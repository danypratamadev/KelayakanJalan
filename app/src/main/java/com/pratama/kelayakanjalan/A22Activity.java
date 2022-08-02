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

public class A22Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    private ImageButton back;
    public TextView klf;
    public String REC_TXT, DIR1 = "-", DIR2 = "-";
    private TextInputEditText KJL, KDL, KKL, ILB, LBR, IRT, KAL, KAM, IAL, TPK, ASM, REC;
    public double KJL_TXT, KDL_TXT, KKL_TXT, ILB_TXT, LBR_TXT, IRT_TXT, KAL_TXT, KAM_TXT, IAL_TXT, TPK_TXT, ASM_TXT;
    public double DEV_KJL, DEV_KDL, DEV_KKL, DEV_ILB, DEV_LBR, DEV_IRT, DEV_KAL, DEV_KAM, DEV_IAL, DEV_TPK, DEV_ASM;
    public String KTG_KJL, KTG_KLB, KTG_ILB, KTG_LBR, KTG_IRT, KTG_KAL2, KTG_IAL, KTG_TPK, KTG_ASM, KTG_KLF;
    private Button SAVE;
    public FABProgressCircle FAB_UPLOAD;
    public FloatingActionButton EDIT, UPLOAD;
    private Boolean connected = false;
    private CoordinatorLayout coordinatorLayout;
    public ImageView FT1, FT2;
    private int REQ1 = 1, REQ2 = 2, REQ4 = 3, REQ5 = 4;
    private double STD = 100;
    private Dialog myDialog;
    private List<A22_Class> a22ArrayList = new ArrayList<>();
    private A22_Adapter adapter;
    private RecyclerViewBouncy a22_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a22;
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
        setContentView(R.layout.activity_a22);

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
        KJL = (TextInputEditText) findViewById(R.id.KJL);
        KDL = (TextInputEditText) findViewById(R.id.KDL);
        KKL = (TextInputEditText) findViewById(R.id.KKL);
        ILB = (TextInputEditText) findViewById(R.id.ILB);
        LBR = (TextInputEditText) findViewById(R.id.LBR);
        IRT = (TextInputEditText) findViewById(R.id.IRT);
        KAL = (TextInputEditText) findViewById(R.id.KAL);
        KAM = (TextInputEditText) findViewById(R.id.KAM);
        IAL = (TextInputEditText) findViewById(R.id.IAL);
        TPK = (TextInputEditText) findViewById(R.id.TPK);
        ASM = (TextInputEditText) findViewById(R.id.ASM);
        REC = (TextInputEditText) findViewById(R.id.REC);
        back = (ImageButton) findViewById(R.id.back);
        take = (ImageButton) findViewById(R.id.take);
        open = (ImageButton) findViewById(R.id.open);
        aksi2 = (LinearLayout) findViewById(R.id.aksi2);
        clear = (ImageButton) findViewById(R.id.clear);
        take1 = (ImageButton) findViewById(R.id.take1);
        open1 = (ImageButton) findViewById(R.id.open1);
        aksi3 = (LinearLayout) findViewById(R.id.aksi3);
        clear1 = (ImageButton) findViewById(R.id.clear1);
        SAVE = (Button) findViewById(R.id.SAVE);
        EDIT = (FloatingActionButton) findViewById(R.id.edit);
        UPLOAD = (FloatingActionButton) findViewById(R.id.upload);
        FAB_UPLOAD = (FABProgressCircle) findViewById(R.id.fab_upload);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.kordinat);
        back = (ImageButton) findViewById(R.id.back);
        FT1 = (ImageView) findViewById(R.id.FT1);
        FT2 = (ImageView) findViewById(R.id.FT2);
        INPUT = (LinearLayout) findViewById(R.id.INPUT);
        aksi = (RelativeLayout) findViewById(R.id.aksi);
        //recycler_a22 = (NestedScrollView) findViewById(R.id.recycler_a22);
        a22_recycler = (RecyclerViewBouncy) findViewById(R.id.a22_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a22_recycler.setHasFixedSize(true);
        a22_recycler.setLayoutManager(new LinearLayoutManager(this));

        a22_recycler.setVisibility(View.GONE);
        new getDataA22().execute((Void[])null);

    }

    private void getInputValue(){

        if(KJL.getText().toString().equals("")){
            KJL_TXT = -1;
        } else {
            KJL_TXT = Double.parseDouble(KJL.getText().toString());
        }

        if(KDL.getText().toString().equals("")){
            KDL_TXT = -1;
        } else {
            KDL_TXT = Double.parseDouble(KDL.getText().toString());
        }

        if(KKL.getText().toString().equals("")){
            KKL_TXT = -1;
        } else {
            KKL_TXT = Double.parseDouble(KKL.getText().toString());
        }

        if(ILB.getText().toString().equals("")){
            ILB_TXT = -1;
        } else {
            ILB_TXT = Double.parseDouble(ILB.getText().toString());
        }

        if(LBR.getText().toString().equals("")){
            LBR_TXT = -1;
        } else {
            LBR_TXT = Double.parseDouble(LBR.getText().toString());
        }

        if(IRT.getText().toString().equals("")){
            IRT_TXT = -1;
        } else {
            IRT_TXT = Double.parseDouble(IRT.getText().toString());
        }

        if(KAL.getText().toString().equals("")){
            KAL_TXT = -1;
        } else {
            KAL_TXT = Double.parseDouble(KAL.getText().toString());
        }

        if(KAM.getText().toString().equals("")){
            KAM_TXT = -1;
        } else {
            KAM_TXT = Double.parseDouble(KAM.getText().toString());
        }

        if(IAL.getText().toString().equals("")){
            IAL_TXT = -1;
        } else {
            IAL_TXT = Double.parseDouble(IAL.getText().toString());
        }

        if(TPK.getText().toString().equals("")){
            TPK_TXT = -1;
        } else {
            TPK_TXT = Double.parseDouble(TPK.getText().toString());
        }

        if(ASM.getText().toString().equals("")){
            ASM_TXT = -1;
        } else {
            ASM_TXT = Double.parseDouble(ASM.getText().toString());
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
                KJL.setText("");
                KDL.setText("");
                KKL.setText("");
                ILB.setText("");
                LBR.setText("");
                IRT.setText("");
                KAL.setText("");
                KAM.setText("");
                IAL.setText("");
                TPK.setText("");
                ASM.setText("");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22");
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

                    ID = ISEG + "_A22";

                    Boolean insertUjiA22 = databaseHelper.insertDataA22(ID, ISEG, KJL_TXT, KDL_TXT, KKL_TXT,
                            ILB_TXT, LBR_TXT, IRT_TXT, KAL_TXT, KAM_TXT, IAL_TXT, TPK_TXT, ASM_TXT, REC_TXT, DIR1, DIR2, "F");

                    if(insertUjiA22 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA22 = databaseHelper.updateDataA22(ID, ISEG, KJL_TXT, KDL_TXT, KKL_TXT,
                            ILB_TXT, LBR_TXT, IRT_TXT, KAL_TXT, KAM_TXT, IAL_TXT, TPK_TXT, ASM_TXT, REC_TXT, DIR1, DIR2, "F");

                    if(updateUjiA22 != false){
                        showPopup();
                    }
                    op2 = false;
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a22_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                if(KJL_TXT == -1){
                    KJL.setText("");
                } else {
                    KJL.setText(String.valueOf(KJL_TXT));
                }

                if(KDL_TXT == -1){
                    KDL.setText("");
                } else {
                    KDL.setText(String.valueOf(KDL_TXT));
                }

                if(KKL_TXT == -1){
                    KKL.setText("");
                } else {
                    KKL.setText(String.valueOf(KKL_TXT));
                }

                if(ILB_TXT == -1){
                    ILB.setText("");
                } else {
                    ILB.setText(String.valueOf(ILB_TXT));
                }

                if(LBR_TXT == -1){
                    LBR.setText("");
                } else {
                    LBR.setText(String.valueOf(LBR_TXT));
                }

                if(IRT_TXT == -1){
                    IRT.setText("");
                } else {
                    IRT.setText(String.valueOf(IRT_TXT));
                }

                if(KAL_TXT == -1){
                    KAL.setText("");
                } else {
                    KAL.setText(String.valueOf(KAL_TXT));
                }

                if(KAM_TXT == -1){
                    KAM.setText("");
                } else {
                    KAM.setText(String.valueOf(KAM_TXT));
                }

                if(IAL_TXT == -1){
                    IAL.setText("");
                } else {
                    IAL.setText(String.valueOf(IAL_TXT));
                }

                if(TPK_TXT == -1){
                    TPK.setText("");
                } else {
                    TPK.setText(String.valueOf(TPK_TXT));
                }

                if(ASM_TXT == -1){
                    ASM.setText("");
                } else {
                    ASM.setText(String.valueOf(ASM_TXT));
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
                    a22ArrayList.clear();
                    a22ArrayList.addAll(databaseHelper.allDataA22(ISEG));

                    adapter = new A22_Adapter(A22Activity.this, a22ArrayList);
                    a22_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a22_recycler.setVisibility(View.VISIBLE);
                    a22_recycler.startAnimation(animation);
                    KJL.setText("");
                    KDL.setText("");
                    KKL.setText("");
                    ILB.setText("");
                    LBR.setText("");
                    IRT.setText("");
                    KAL.setText("");
                    KAM.setText("");
                    IAL.setText("");
                    TPK.setText("");
                    ASM.setText("");
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

                        new insertDataA22().execute((Void[])null);

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

        a22_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A22Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A22Activity.this, ImageViewActivity.class);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state2.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state3.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/"+ISEG+"_2.png");
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

        a22ArrayList.clear();

        a22ArrayList.addAll(databaseHelper.allDataA22(ISEG));

        if(a22ArrayList.isEmpty()){
            a22_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A22_Adapter(A22Activity.this, a22ArrayList);
            a22_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a22_recycler.setVisibility(View.VISIBLE);
            a22_recycler.startAnimation(animation);
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

    private class insertDataA22 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A22 (ID_A22, ID_SEGMEN, UJI_A22_a, UJI_A22_b1, UJI_A22_b2, UJI_A22_c, UJI_A22_d, UJI_A22_e, UJI_A22_f1, UJI_A22_f2, UJI_A22_g, UJI_A22_h, UJI_A22_i, CATATAN, " +
                        "DEV_A22_a, DEV_A22_b1, DEV_A22_b2, DEV_A22_c, DEV_A22_d, DEV_A22_e, DEV_A22_f1, DEV_A22_f2, DEV_A22_g, DEV_A22_h, DEV_A22_i, KTG_A22_a, KTG_A22_b, KTG_A22_c, KTG_A22_d, KTG_A22_e, KTG_A22_f, KTG_A22_g, KTG_A22_h, KTG_A22_i, KTG_A22) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+KJL_TXT+"', '"+KDL_TXT+"', '"+KKL_TXT+"', '"+ILB_TXT+"', '"+LBR_TXT+"', '"+IRT_TXT+"', '"+KAL_TXT+"', '"+KAM_TXT+"', '"+IAL_TXT+"', '"+TPK_TXT+"', '"+ASM_TXT+"', '"+REC_TXT+"', " +
                        "'"+DEV_KJL+"', '"+DEV_KDL+"', '"+DEV_KKL+"', '"+DEV_ILB+"', '"+DEV_LBR+"', '"+DEV_IRT+"', '"+DEV_KAL+"', '"+DEV_KAM+"', '"+DEV_IAL+"', '"+DEV_TPK+"', '"+DEV_ASM+"', '"+KTG_KJL+"', '"+KTG_KLB+"', " +
                        "'"+KTG_ILB+"', '"+KTG_LBR+"', '"+KTG_IRT+"', '"+KTG_KAL2+"', '"+KTG_IAL+"', '"+KTG_TPK+"', '"+KTG_ASM+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA22(ID, ISEG, KJL_TXT, KDL_TXT, KKL_TXT,
                        ILB_TXT, LBR_TXT, IRT_TXT, KAL_TXT, KAM_TXT, IAL_TXT, TPK_TXT, ASM_TXT, REC_TXT, DIR1, DIR2, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA22().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A22";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A22Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A22 SET GBR_1 = null WHERE ID_A22 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A22";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A22Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A22 SET GBR_2 = null WHERE ID_A22 = '"+ID+"'";

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

    private class updateDataA22 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A22 SET ID_SEGMEN = '"+ISEG+"', UJI_A22_a = '"+KJL_TXT+"', UJI_A22_b1 = '"+KDL_TXT+"', UJI_A22_b2 = '"+KKL_TXT+"', UJI_A22_c = '"+ILB_TXT+"', UJI_A22_d = '"+LBR_TXT+"', " +
                        "UJI_A22_e = '"+IRT_TXT+"', UJI_A22_f1 = '"+KAL_TXT+"', UJI_A22_f2 = '"+KAM_TXT+"', UJI_A22_g = '"+IAL_TXT+"', UJI_A22_h = '"+TPK_TXT+"', UJI_A22_i = '"+ASM_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A22_a = '"+DEV_KJL+"', DEV_A22_b1 = '"+DEV_KDL+"', DEV_A22_b2 = '"+DEV_KKL+"', DEV_A22_c = '"+DEV_ILB+"', DEV_A22_d = '"+DEV_LBR+"', DEV_A22_e = '"+DEV_IRT+"', DEV_A22_f1 = '"+DEV_KAL+"', " +
                        "DEV_A22_f2 = '"+DEV_KAM+"', DEV_A22_g = '"+DEV_IAL+"', DEV_A22_h = '"+DEV_TPK+"', DEV_A22_i = '"+DEV_ASM+"', KTG_A22_a = '"+KTG_KJL+"', KTG_A22_b = '"+KTG_KLB+"', KTG_A22_c = '"+KTG_ILB+"', KTG_A22_d = '"+KTG_LBR+"', " +
                        "KTG_A22_e = '"+KTG_IRT+"', KTG_A22_f = '"+KTG_KAL2+"', KTG_A22_g = '"+KTG_IAL+"', KTG_A22_h = '"+KTG_TPK+"', KTG_A22_i = '"+KTG_ASM+"', KTG_A22 = '"+KTG_KLF+"' WHERE ID_A22 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA22(ID, ISEG, KJL_TXT, KDL_TXT, KKL_TXT,
                        ILB_TXT, LBR_TXT, IRT_TXT, KAL_TXT, KAM_TXT, IAL_TXT, TPK_TXT, ASM_TXT, REC_TXT, DIR1, DIR2, "T");

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
            if(KJL.getText().toString().equals("") && KDL.getText().toString().equals("") &&
                    KKL.getText().toString().equals("") && ILB.getText().toString().equals("") &&
                    LBR.getText().toString().equals("") && IRT.getText().toString().equals("") &&
                    KAL.getText().toString().equals("") && KAM.getText().toString().equals("") &&
                    IAL.getText().toString().equals("") && TPK.getText().toString().equals("") &&
                    ASM.getText().toString().equals("") && REC.getText().toString().equals("")){
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

    private class getDataA22 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A22Activity.this)
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

                String select = "SELECT ID_A22, UJI_A22_a, UJI_A22_b1, UJI_A22_b2, UJI_A22_c, UJI_A22_d, UJI_A22_e, UJI_A22_f1, UJI_A22_f2, UJI_A22_g, UJI_A22_h, UJI_A22_i, CATATAN, GBR_1, GBR_2, ID_SEGMEN FROM A22 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA22Upload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

                        String dir1 = data.getString("GBR_1");
                        String dir2 = data.getString("GBR_2");

                        if(dir1 != null){

                            dir1 = dir1.replaceAll(" ", "%20");

                            Bitmap bitmap = null;
                            try {

                                InputStream fto = new java.net.URL(dir1).openStream();

                                bitmap = BitmapFactory.decodeStream(fto);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress/"+ISEG+"_1.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A22/Compress/"+ISEG+"_2.png");
                                }

                                FileOutputStream save = new FileOutputStream(mediaStorageDir2);
                                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, save);
                                save.flush();

                                location2 = mediaStorageDir2.getAbsolutePath();

                            } catch (Exception e) {
                                Log.e("Error Image ", e.toString());
                            }

                        }

                        Boolean insertA22 = databaseHelper.insertDataA22(data.getString("ID_A22"), data.getString("ID_SEGMEN"),
                                data.getDouble("UJI_A22_a"), data.getDouble("UJI_A22_b1"), data.getDouble("UJI_A22_b2"),
                                data.getDouble("UJI_A22_c"), data.getDouble("UJI_A22_d"), data.getDouble("UJI_A22_e"),
                                data.getDouble("UJI_A22_f1"), data.getDouble("UJI_A22_f2"), data.getDouble("UJI_A22_g"),
                                data.getDouble("UJI_A22_h"), data.getDouble("UJI_A22_i"), data.getString("CATATAN"), location, location2,"T");

                        if(insertA22 == false){

                            databaseHelper.updateDataA22(data.getString("ID_A22"), data.getString("ID_SEGMEN"),
                                    data.getDouble("UJI_A22_a"), data.getDouble("UJI_A22_b1"), data.getDouble("UJI_A22_b2"),
                                    data.getDouble("UJI_A22_c"), data.getDouble("UJI_A22_d"), data.getDouble("UJI_A22_e"),
                                    data.getDouble("UJI_A22_f1"), data.getDouble("UJI_A22_f2"), data.getDouble("UJI_A22_g"),
                                    data.getDouble("UJI_A22_h"), data.getDouble("UJI_A22_i"), data.getString("CATATAN"), location, location2,"T");

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

            a22ArrayList.addAll(databaseHelper.allDataA22(ISEG));

            if(!a22ArrayList.isEmpty()){
                a22_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A22_Adapter(A22Activity.this, a22ArrayList);
                        a22_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a22_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
