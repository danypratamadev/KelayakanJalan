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

public class A41Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    private ImageButton back;
    public String REC_TXT, DIR1 = "-", DIR2 = "-", DIR3 = "-";
    public TextView klf;
    private Spinner SLBT;
    private TextInputEditText LBT, LBT2, LBT3, PFR, KLL, REC;
    public double LBT_TXT, LBT2_TXT, LBT3_TXT, PFR_TXT, KLL_TXT;
    public String SLBT_TXT;
    public double DEV_LBT, DEV_LBT2, DEV_LBT3, DEV_PFR, DEV_KLL;
    public String KTG_LBTT, KTG_PFR, KTG_KLL, KTG_KLF;
    private Button SAVE;
    public FABProgressCircle FAB_UPLOAD;
    public FloatingActionButton EDIT, UPLOAD;
    private Boolean connected = false;
    private CoordinatorLayout coordinatorLayout;
    public ImageView FT1, FT2, FT3;
    private int REQ1 = 1, REQ2 = 2, REQ3 = 3, REQ4 = 4, REQ5 = 5, REQ6 = 6;
    private double STD = 100;
    private Dialog myDialog;
    private List<A41_Class> a41ArrayList = new ArrayList<>();
    private A41_Adapter adapter;
    private RecyclerViewBouncy a41_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a41;
    private RelativeLayout aksi;
    private ImageButton take, open, take1, open1, take2, open2;
    private LinearLayout aksi2, aksi3, aksi4;
    private ImageButton clear, clear1, clear2;
    private Boolean op = false, op2 = false;
    private Boolean hasil = false;
    private File mediaFile;
    private File compressedImage;
    private ACProgressFlower dialog;
    private String[] list_lebar;
    private static final String urlUpload = "http://proyekjalan.net/upload_1.php";
    private static final String urlUpload2 = "http://proyekjalan.net/upload_2.php";
    private static final String urlUpload3 = "http://proyekjalan.net/upload_3.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a41);

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
        SLBT = (Spinner) findViewById(R.id.SLBT);
        LBT = (TextInputEditText) findViewById(R.id.LBT);
        LBT2 = (TextInputEditText) findViewById(R.id.LBT2);
        LBT3 = (TextInputEditText) findViewById(R.id.LBT3);
        PFR = (TextInputEditText) findViewById(R.id.PFR);
        KLL = (TextInputEditText) findViewById(R.id.KLL);
        REC = (TextInputEditText) findViewById(R.id.REC);
        back = (ImageButton) findViewById(R.id.back);
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
        EDIT = (FloatingActionButton) findViewById(R.id.edit);
        UPLOAD = (FloatingActionButton) findViewById(R.id.upload);
        FAB_UPLOAD = (FABProgressCircle) findViewById(R.id.fab_upload);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.kordinat);
        back = (ImageButton) findViewById(R.id.back);
        FT1 = (ImageView) findViewById(R.id.FT1);
        FT2 = (ImageView) findViewById(R.id.FT2);
        FT3 = (ImageView) findViewById(R.id.FT3);
        INPUT = (LinearLayout) findViewById(R.id.INPUT);
        aksi = (RelativeLayout) findViewById(R.id.aksi);
        //recycler_a41 = (NestedScrollView) findViewById(R.id.recycler_a41);
        a41_recycler = (RecyclerViewBouncy) findViewById(R.id.a41_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        clear2.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a41_recycler.setHasFixedSize(true);
        a41_recycler.setLayoutManager(new LinearLayoutManager(this));

        a41_recycler.setVisibility(View.GONE);
        new getDataA41().execute((Void[])null);

        if(KPR.equals("Jalan Bebas Hambatan (JBH)")){
            list_lebar = new String[] {"2 x 14 m", "2 x 11 m", "2 x 7 m"};
        } else if(KPR.equals("Jalan Raya (JR)")){
            list_lebar = new String[] {"2 x 14 m", "2 x 11 m", "2 x 7 m"};
        } else if(KPR.equals("Jalan Sedang (JS)")){
            list_lebar = new String[] {"7 m"};
        } else {
            list_lebar = new String[] {"5.5 m", "2.5 m"};
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(A41Activity.this, R.layout.simple_spinner_item, list_lebar);
        adapter.setDropDownViewResource(R.layout.simple_spinner_item);
        SLBT.setAdapter(adapter);

    }

    private void getInputValue(){

        SLBT_TXT = SLBT.getSelectedItem().toString();

        if(LBT.getText().toString().equals("")){
            LBT_TXT = -1;
        } else {
            LBT_TXT = Double.parseDouble(LBT.getText().toString());
        }

        if(LBT2.getText().toString().equals("")){
            LBT2_TXT = -1;
        } else {
            LBT2_TXT = Double.parseDouble(LBT2.getText().toString());
        }

        if(LBT3.getText().toString().equals("")){
            LBT3_TXT = -1;
        } else {
            LBT3_TXT = Double.parseDouble(LBT3.getText().toString());
        }

        if(PFR.getText().toString().equals("")){
            PFR_TXT = -1;
        } else {
            PFR_TXT = Double.parseDouble(PFR.getText().toString());
        }

        if(KLL.getText().toString().equals("")){
            KLL_TXT = -1;
        } else {
            KLL_TXT = Double.parseDouble(KLL.getText().toString());
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
                LBT.setText("");
                LBT2.setText("");
                LBT3.setText("");
                PFR.setText("");
                KLL.setText("");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41");
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ5);
            }
        });

        take2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_3.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ6);
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInputValue();

                if(op == false){

                    ID = ISEG + "_A41";

                    Boolean insertUjiA41 = databaseHelper.insertDataA41(ID, ISEG, SLBT_TXT, LBT_TXT, LBT2_TXT, LBT3_TXT,
                            PFR_TXT, KLL_TXT, REC_TXT, DIR1, DIR2, DIR3, "F");

                    if(insertUjiA41 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA41 = databaseHelper.updateDataA41(ID, ISEG, SLBT_TXT, LBT_TXT, LBT2_TXT, LBT3_TXT,
                            PFR_TXT, KLL_TXT, REC_TXT, DIR1, DIR2, DIR3, "F");

                    if(updateUjiA41 != false){
                        showPopup();
                    }
                    op2 = false;
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a41_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                SLBT.setSelection(((ArrayAdapter<String>)SLBT.getAdapter()).getPosition(SLBT_TXT));

                if(LBT_TXT == -1){
                    LBT.setText("");
                } else {
                    LBT.setText(String.valueOf(LBT_TXT));
                }

                if(LBT2_TXT == -1){
                    LBT2.setText("");
                } else {
                    LBT2.setText(String.valueOf(LBT2_TXT));
                }

                if(LBT3_TXT == -1){
                    LBT3.setText("");
                } else {
                    LBT3.setText(String.valueOf(LBT3_TXT));
                }

                if(PFR_TXT == -1){
                    PFR.setText("");
                } else {
                    PFR.setText(String.valueOf(PFR_TXT));
                }

                if(KLL_TXT == -1){
                    KLL.setText("");
                } else {
                    KLL.setText(String.valueOf(KLL_TXT));
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

        klf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(op2 == false){

                } else {
                    a41ArrayList.clear();
                    a41ArrayList.addAll(databaseHelper.allDataA41(ISEG));

                    adapter = new A41_Adapter(A41Activity.this, a41ArrayList);
                    a41_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a41_recycler.setVisibility(View.VISIBLE);
                    a41_recycler.startAnimation(animation);
                    LBT.setText("");
                    LBT2.setText("");
                    LBT3.setText("");
                    PFR.setText("");
                    KLL.setText("");
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

                        new insertDataA41().execute((Void[])null);

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

        a41_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A41Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A41Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A41Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR3);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_2.png");
        }

        File mediaStorageDir4 = null;
        String state4 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_3.png");
        } else {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/"+ISEG+"_3.png");
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

            } else {

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

        a41ArrayList.clear();

        a41ArrayList.addAll(databaseHelper.allDataA41(ISEG));

        if(a41ArrayList.isEmpty()){
            a41_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A41_Adapter(A41Activity.this, a41ArrayList);
            a41_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a41_recycler.setVisibility(View.VISIBLE);
            a41_recycler.startAnimation(animation);
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

    private class insertDataA41 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A41 (ID_A41, ID_SEGMEN, LEBAR_JALUR, UJI_A41_a1, UJI_A41_a2, UJI_A41_a3, UJI_A41_b, UJI_A41_c, CATATAN, " +
                        "DEV_A41_a1, DEV_A41_a2, DEV_A41_a3, DEV_A41_b, DEV_A41_c, KTG_A41_a, KTG_A41_b, KTG_A41_c, KTG_A41) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+SLBT_TXT+"', '"+LBT_TXT+"', '"+LBT2_TXT+"', '"+LBT3_TXT+"', '"+PFR_TXT+"', '"+KLL_TXT+"', '"+REC_TXT+"', " +
                        "'"+DEV_LBT+"', '"+DEV_LBT2+"', '"+DEV_LBT3+"', '"+DEV_PFR+"', '"+DEV_KLL+"', '"+KTG_LBTT+"', '"+KTG_PFR+"', '"+KTG_KLL+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA41(ID, ISEG, SLBT_TXT, LBT_TXT, LBT2_TXT, LBT3_TXT,
                        PFR_TXT, KLL_TXT, REC_TXT, DIR1, DIR2, DIR3, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA41().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A41";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A41Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A41 SET GBR_1 = null WHERE ID_A41 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A41";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A41Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A41 SET GBR_2 = null WHERE ID_A41 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR3.equals("-")){
                try{
                    String Table = "A41";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A41Activity.this, uploadId, urlUpload3)
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

                    String insert = "UPDATE A41 SET GBR_3 = null WHERE ID_A41 = '"+ID+"'";

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

    private class updateDataA41 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A41 SET ID_SEGMEN = '"+ISEG+"', LEBAR_JALUR = '"+SLBT_TXT+"', UJI_A41_a1 = '"+LBT_TXT+"', UJI_A41_a2 = '"+LBT2_TXT+"', UJI_A41_a3 = '"+LBT3_TXT+"', " +
                        "UJI_A41_b = '"+PFR_TXT+"', UJI_A41_c = '"+KLL_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A41_a1 = '"+DEV_LBT+"', DEV_A41_a2 = '"+DEV_LBT2+"', DEV_A41_a3 = '"+DEV_LBT3+"', DEV_A41_b = '"+DEV_PFR+"', DEV_A41_c = '"+DEV_KLL+"', " +
                        "KTG_A41_a = '"+KTG_LBTT+"', KTG_A41_b = '"+KTG_PFR+"', KTG_A41_c = '"+KTG_KLL+"', KTG_A41 = '"+KTG_KLF+"' WHERE ID_A41 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA41(ID, ISEG, SLBT_TXT, LBT_TXT, LBT2_TXT, LBT3_TXT,
                        PFR_TXT, KLL_TXT, REC_TXT, DIR1, DIR2, DIR3, "T");

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
            if(LBT.getText().toString().equals("") && LBT2.getText().toString().equals("") &&
                    LBT3.getText().toString().equals("") && PFR.getText().toString().equals("") &&
                    KLL.getText().toString().equals("") && REC.getText().toString().equals("")){
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

    private class getDataA41 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A41Activity.this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE).sizeRatio((float) 0.2).bgAlpha((float) 0.2)
                    .fadeColor(Color.DKGRAY).build();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String location = "-", location2 = "-", location3 = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_A41, LEBAR_JALUR, UJI_A41_a1, UJI_A41_a2, UJI_A41_a3, UJI_A41_b, UJI_A41_c, CATATAN, GBR_1, GBR_2, GBR_3, ID_SEGMEN FROM A41 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA41Upload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

                        String dir1 = data.getString("GBR_1");
                        String dir2 = data.getString("GBR_2");
                        String dir3 = data.getString("GBR_3");

                        if(dir1 != null){

                            dir1 = dir1.replaceAll(" ", "%20");

                            Bitmap bitmap = null;
                            try {

                                InputStream fto = new java.net.URL(dir1).openStream();

                                bitmap = BitmapFactory.decodeStream(fto);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress/"+ISEG+"_1.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress/"+ISEG+"_2.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir3 = null;
                                String state3 = Environment.getExternalStorageState();

                                if (state3.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress/"+ISEG+"_3.png");
                                } else {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A41/Compress/"+ISEG+"_3.png");
                                }

                                FileOutputStream save3 = new FileOutputStream(mediaStorageDir3);
                                bitmap3.compress(Bitmap.CompressFormat.PNG, 100, save3);
                                save3.flush();

                                location3 = mediaStorageDir3.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        Boolean insertA41 = databaseHelper.insertDataA41(data.getString("ID_A41"), data.getString("ID_SEGMEN"),
                                data.getString("LEBAR_JALUR"), data.getDouble("UJI_A41_a1"), data.getDouble("UJI_A41_a2"),
                                data.getDouble("UJI_A41_a3"), data.getDouble("UJI_A41_b"), data.getDouble("UJI_A41_c"),
                                data.getString("CATATAN"), location, location2, location3, "T");

                        if(insertA41 == false){

                            databaseHelper.updateDataA41(data.getString("ID_A41"), data.getString("ID_SEGMEN"),
                                    data.getString("LEBAR_JALUR"), data.getDouble("UJI_A41_a1"), data.getDouble("UJI_A41_a2"),
                                    data.getDouble("UJI_A41_a3"), data.getDouble("UJI_A41_b"), data.getDouble("UJI_A41_c"),
                                    data.getString("CATATAN"), location, location2, location3, "T");

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

            a41ArrayList.addAll(databaseHelper.allDataA41(ISEG));

            if(!a41ArrayList.isEmpty()){
                a41_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A41_Adapter(A41Activity.this, a41ArrayList);
                        a41_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a41_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
