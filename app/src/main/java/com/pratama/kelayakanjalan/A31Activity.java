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

public class A31Activity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public String SJR, FNG, KPR, MJL, NRU, ISEG;
    public String ID;
    private ImageButton back;
    public TextView klf;
    public String REC_TXT, DIR1 = "-", DIR2 = "-", DIR3 = "-", DIR4 = "-", DIR5 = "-", DIR6 = "-";
    private Spinner SKJB, TUM, TSD, TKK, TUP, TUP2, TUK, TBK, TTP, TAP;
    public String SKJB_TXT;
    private TextInputLayout TIKJB, TIKJB2, TIKJB3, TIKJB4, TIKJB5, TIKJB6, TIKJB7, TIKJB8;
    private TextInputEditText JLL, JPK, TGI, LDI, PNI, LBI, GTI, KTI, KJB, KJB2, KJB3, KJB4, KJB5,
            KJB6, KJB7, KJB8, REC;
    public double JLL_TXT, JPK_TXT, TGI_TXT, LDI_TXT, PNI_TXT, LBI_TXT, GTI_TXT, KTI_TXT, KJB_TXT, KJB2_TXT, KJB3_TXT, KJB4_TXT, KJB5_TXT,
            KJB6_TXT, KJB7_TXT, KJB8_TXT, TUM_TXT, TSD_TXT, TKK_TXT, TUP_TXT, TUP2_TXT, TUK_TXT, TBK_TXT, TTP_TXT, TAP_TXT;
    public double DEV_JLL, DEV_JPK, DEV_TGI, DEV_LDI, DEV_PNI, DEV_LBI, DEV_GTI, DEV_KTI, DEV_KJB, DEV_KJB2, DEV_KJB3, DEV_KJB4, DEV_KJB5,
            DEV_KJB6, DEV_KJB7, DEV_KJB8, DEV_TUM, DEV_TSD, DEV_TKK, DEV_TUP, DEV_TUP2, DEV_TUK, DEV_TBK, DEV_TTP, DEV_TAP;
    public String KTG_JLL, KTG_JPK, KTG_KTJ, KTG_KJBB, KTG_FPH, KTG_KLF;
    private Button SAVE;
    public FABProgressCircle FAB_UPLOAD;
    public FloatingActionButton EDIT, UPLOAD;
    private Boolean connected = false;
    private CoordinatorLayout coordinatorLayout;
    public ImageView FT1, FT2, FT3, FT4, FT5, FT6;
    private int REQ1 = 1, REQ2 = 2, REQ3 = 3, REQ4 = 4, REQ5 = 5, REQ6 = 6, REQ7 = 7, REQ8 = 8,
            REQ9 = 9, REQ10 = 10, REQ11 = 11, REQ12 = 12;
    private Dialog myDialog;
    private List<A31_Class> a31ArrayList = new ArrayList<>();
    private A31_Adapter adapter;
    private RecyclerViewBouncy a31_recycler;
    private LinearLayout INPUT;
    //private NestedScrollView recycler_a31;
    private RelativeLayout aksi;
    private ImageButton take, open, take1, open1, take2, open2, take3, open3, take4, open4, take5, open5;
    private LinearLayout aksi2, aksi3, aksi4, aksi5, aksi6, aksi7;
    private ImageButton clear, clear1, clear2, clear3, clear4, clear5;
    private Boolean op = false, op2 = false;
    private Boolean hasil = false;
    private File mediaFile;
    private File compressedImage;
    private ACProgressFlower dialog;
    private static final String urlUpload = "http://proyekjalan.net/upload_1.php";
    private static final String urlUpload2 = "http://proyekjalan.net/upload_2.php";
    private static final String urlUpload3 = "http://proyekjalan.net/upload_3.php";
    private static final String urlUpload4 = "http://proyekjalan.net/upload_4.php";
    private static final String urlUpload5 = "http://proyekjalan.net/upload_5.php";
    private static final String urlUpload6 = "http://proyekjalan.net/upload_6.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a31);

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
        JLL = (TextInputEditText) findViewById(R.id.JLL);
        JPK = (TextInputEditText) findViewById(R.id.JPK);
        TGI = (TextInputEditText) findViewById(R.id.TGI);
        LDI = (TextInputEditText) findViewById(R.id.LDI);
        PNI = (TextInputEditText) findViewById(R.id.PNI);
        LBI = (TextInputEditText) findViewById(R.id.LBI);
        GTI = (TextInputEditText) findViewById(R.id.GTI);
        KTI = (TextInputEditText) findViewById(R.id.KTI);
        SKJB = (Spinner) findViewById(R.id.SKJB);
        KJB = (TextInputEditText) findViewById(R.id.KJB);
        KJB2 = (TextInputEditText) findViewById(R.id.KJB2);
        KJB3 = (TextInputEditText) findViewById(R.id.KJB3);
        KJB4 = (TextInputEditText) findViewById(R.id.KJB4);
        KJB5 = (TextInputEditText) findViewById(R.id.KJB5);
        KJB6 = (TextInputEditText) findViewById(R.id.KJB6);
        KJB7 = (TextInputEditText) findViewById(R.id.KJB7);
        KJB8 = (TextInputEditText) findViewById(R.id.KJB8);
        TIKJB = (TextInputLayout) findViewById(R.id.TIKJB);
        TIKJB2 = (TextInputLayout) findViewById(R.id.TIKJB2);
        TIKJB3 = (TextInputLayout) findViewById(R.id.TIKJB3);
        TIKJB4 = (TextInputLayout) findViewById(R.id.TIKJB4);
        TIKJB5 = (TextInputLayout) findViewById(R.id.TIKJB5);
        TIKJB6 = (TextInputLayout) findViewById(R.id.TIKJB6);
        TIKJB7 = (TextInputLayout) findViewById(R.id.TIKJB7);
        TIKJB8 = (TextInputLayout) findViewById(R.id.TIKJB8);
        TUM = (Spinner) findViewById(R.id.STUM);
        TSD = (Spinner) findViewById(R.id.STSD);
        TKK = (Spinner) findViewById(R.id.STKK);
        TUP = (Spinner) findViewById(R.id.STUP);
        TUP2 = (Spinner) findViewById(R.id.STUP2);
        TUK = (Spinner) findViewById(R.id.STUK);
        TBK = (Spinner) findViewById(R.id.STBK);
        TTP = (Spinner) findViewById(R.id.STTP);
        TAP = (Spinner) findViewById(R.id.STAP);
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
        take3 = (ImageButton) findViewById(R.id.take3);
        open3 = (ImageButton) findViewById(R.id.open3);
        aksi5 = (LinearLayout) findViewById(R.id.aksi5);
        clear3 = (ImageButton) findViewById(R.id.clear3);
        take4 = (ImageButton) findViewById(R.id.take4);
        open4 = (ImageButton) findViewById(R.id.open4);
        aksi6 = (LinearLayout) findViewById(R.id.aksi6);
        clear4 = (ImageButton) findViewById(R.id.clear4);
        take5 = (ImageButton) findViewById(R.id.take5);
        open5 = (ImageButton) findViewById(R.id.open5);
        aksi7 = (LinearLayout) findViewById(R.id.aksi7);
        clear5 = (ImageButton) findViewById(R.id.clear5);
        EDIT = (FloatingActionButton) findViewById(R.id.edit);
        UPLOAD = (FloatingActionButton) findViewById(R.id.upload);
        FAB_UPLOAD = (FABProgressCircle) findViewById(R.id.fab_upload);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.kordinat);
        back = (ImageButton) findViewById(R.id.back);
        FT1 = (ImageView) findViewById(R.id.FT1);
        FT2 = (ImageView) findViewById(R.id.FT2);
        FT3 = (ImageView) findViewById(R.id.FT3);
        FT4 = (ImageView) findViewById(R.id.FT4);
        FT5 = (ImageView) findViewById(R.id.FT5);
        FT6 = (ImageView) findViewById(R.id.FT6);
        INPUT = (LinearLayout) findViewById(R.id.INPUT);
        aksi = (RelativeLayout) findViewById(R.id.aksi);
        //recycler_a31 = (NestedScrollView) findViewById(R.id.recycler_a31);
        a31_recycler = (RecyclerViewBouncy) findViewById(R.id.a31_recycler);

        klf.setText("");
        aksi.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        clear1.setVisibility(View.GONE);
        clear2.setVisibility(View.GONE);
        clear3.setVisibility(View.GONE);
        clear4.setVisibility(View.GONE);
        clear5.setVisibility(View.GONE);
        UPLOAD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Orange700)));

        a31_recycler.setHasFixedSize(true);
        a31_recycler.setLayoutManager(new LinearLayoutManager(this));

        a31_recycler.setVisibility(View.GONE);
        new getDataA31().execute((Void[])null);

    }

    private void getInputValue(){

        if(JLL.getText().toString().equals("")){
            JLL_TXT = -1;
        } else {
            JLL_TXT = Double.parseDouble(JLL.getText().toString());
        }

        if(JPK.getText().toString().equals("")){
            JPK_TXT = -1;
        } else {
            JPK_TXT = Double.parseDouble(JPK.getText().toString());
        }

        if(TGI.getText().toString().equals("")){
            TGI_TXT = -1;
        } else {
            TGI_TXT = Double.parseDouble(TGI.getText().toString());
        }

        if(LDI.getText().toString().equals("")){
            LDI_TXT = -1;
        } else {
            LDI_TXT = Double.parseDouble(LDI.getText().toString());
        }

        if(PNI.getText().toString().equals("")){
            PNI_TXT = -1;
        } else {
            PNI_TXT = Double.parseDouble(PNI.getText().toString());
        }

        if(LBI.getText().toString().equals("")){
            LBI_TXT = -1;
        } else {
            LBI_TXT = Double.parseDouble(LBI.getText().toString());
        }

        if(GTI.getText().toString().equals("")){
            GTI_TXT = -1;
        } else {
            GTI_TXT = Double.parseDouble(GTI.getText().toString());
        }

        if(KTI.getText().toString().equals("")){
            KTI_TXT = -1;
        } else {
            KTI_TXT = Double.parseDouble(KTI.getText().toString());
        }

        SKJB_TXT = SKJB.getSelectedItem().toString();

        if(KJB.getText().toString().equals("")){
            KJB_TXT = -1;
        } else {
            KJB_TXT = Double.parseDouble(KJB.getText().toString());
        }

        if(KJB2.getText().toString().equals("")){
            KJB2_TXT = -1;
        } else {
            KJB2_TXT = Double.parseDouble(KJB2.getText().toString());
        }

        if(KJB3.getText().toString().equals("")){
            KJB3_TXT = -1;
        } else {
            KJB3_TXT = Double.parseDouble(KJB3.getText().toString());
        }

        if(KJB4.getText().toString().equals("")){
            KJB4_TXT = -1;
        } else {
            KJB4_TXT = Double.parseDouble(KJB4.getText().toString());
        }

        if(KJB5.getText().toString().equals("")){
            KJB5_TXT = -1;
        } else {
            KJB5_TXT = Double.parseDouble(KJB5.getText().toString());
        }

        if(KJB6.getText().toString().equals("")){
            KJB6_TXT = -1;
        } else {
            KJB6_TXT = Double.parseDouble(KJB6.getText().toString());
        }

        if(KJB7.getText().toString().equals("")){
            KJB7_TXT = -1;
        } else {
            KJB7_TXT = Double.parseDouble(KJB7.getText().toString());
        }

        if(KJB8.getText().toString().equals("")){
            KJB8_TXT = -1;
        } else {
            KJB8_TXT = Double.parseDouble(KJB8.getText().toString());
        }

        String tum = TUM.getSelectedItem().toString();
        String tsd = TSD.getSelectedItem().toString();
        String tkk = TKK.getSelectedItem().toString();
        String tup = TUP.getSelectedItem().toString();
        String tup2 = TUP2.getSelectedItem().toString();
        String tuk = TUK.getSelectedItem().toString();
        String tbk = TBK.getSelectedItem().toString();
        String ttp = TTP.getSelectedItem().toString();
        String tap = TAP.getSelectedItem().toString();

        if(tum.equals("Ada")){
            TUM_TXT = 100;
        } else if(tum.equals("Tidak Ada")){
            TUM_TXT = 0;
        } else {
            TUM_TXT = -1;
        }

        if(tsd.equals("Ada")){
            TSD_TXT = 100;
        } else if(tsd.equals("Tidak Ada")){
            TSD_TXT = 0;
        } else {
            TSD_TXT = -1;
        }

        if(tkk.equals("Ada")){
            TKK_TXT = 100;
        } else if(tkk.equals("Tidak Ada")){
            TKK_TXT = 0;
        } else {
            TKK_TXT = -1;
        }

        if(tup.equals("Ada")){
            TUP_TXT = 100;
        } else if(tup.equals("Tidak Ada")){
            TUP_TXT = 0;
        } else {
            TUP_TXT = -1;
        }

        if(tup2.equals("Ada")){
            TUP2_TXT = 100;
        } else if(tup2.equals("Tidak Ada")){
            TUP2_TXT = 0;
        } else {
            TUP2_TXT = -1;
        }

        if(tuk.equals("Ada")){
            TUK_TXT = 100;
        } else if(tuk.equals("Tidak Ada")){
            TUK_TXT = 0;
        } else {
            TUK_TXT = -1;
        }

        if(tbk.equals("Ada")){
            TBK_TXT = 100;
        } else if(tbk.equals("Tidak Ada")){
            TBK_TXT = 0;
        } else {
            TBK_TXT = -1;
        }

        if(ttp.equals("Ada")){
            TTP_TXT = 100;
        } else if(ttp.equals("Tidak Ada")){
            TTP_TXT = 0;
        } else {
            TTP_TXT = -1;
        }

        if(tap.equals("Ada")){
            TAP_TXT = 100;
        } else if(tap.equals("Tidak Ada")){
            TAP_TXT = 0;
        } else {
            TAP_TXT = -1;
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
                JLL.setText("");
                JPK.setText("");
                TGI.setText("");
                LDI.setText("");
                PNI.setText("");
                LBI.setText("");
                GTI.setText("");
                KTI.setText("");
                KJB.setText("");
                KJB2.setText("");
                KJB3.setText("");
                KJB4.setText("");
                KJB5.setText("");
                KJB6.setText("");
                KJB7.setText("");
                KJB8.setText("");
                REC.setText("");
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

    }

    private void onClickListener(){

        SKJB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.equals("Batubata")){
                    KJB4.setEnabled(false);
                    KJB5.setEnabled(false);
                    KJB6.setEnabled(false);
                    KJB7.setEnabled(false);
                    KJB8.setEnabled(false);

                    TIKJB.setHint("Penurunan mutu/retak");
                    TIKJB.setHelperText("Tidak ada penurunan mutu/retak maka: 100%");

                    TIKJB2.setHint("Perubahan bentuk");
                    TIKJB2.setHelperText("Tidak ada perubahan bentuk/penggembungan \nmaka: 100%");

                    TIKJB3.setHint("Pecah/hilangnya material");
                    TIKJB3.setHelperText("Tidak ada pecah/hilangnya material maka: 100%");

                    TIKJB4.setHint("Tidak perlu di isi");
                    TIKJB4.setHelperText("Tidak perlu di isi");

                    TIKJB5.setHint("Tidak perlu di isi");
                    TIKJB5.setHelperText("Tidak perlu di isi");

                    TIKJB6.setHint("Tidak perlu di isi");
                    TIKJB6.setHelperText("Tidak perlu di isi");

                    TIKJB7.setHint("Tidak perlu di isi");
                    TIKJB7.setHelperText("Tidak perlu di isi");

                    TIKJB8.setHint("Tidak perlu di isi");
                    TIKJB8.setHelperText("Tidak perlu di isi");

                } else if(selected.equals("Beton")){
                    KJB4.setEnabled(true);
                    KJB5.setEnabled(true);
                    KJB6.setEnabled(true);
                    KJB7.setEnabled(false);
                    KJB8.setEnabled(false);

                    TIKJB.setHint("Kerontokan/kropos/dll");
                    TIKJB.setHelperText("Tidak ada kerontokan, keropos, berongga, \nmulut jelek maka: 100%");

                    TIKJB2.setHint("Keretakan");
                    TIKJB2.setHelperText("Tidak ada keretakan maka: 100%");

                    TIKJB3.setHint("Karat pada tulang baja");
                    TIKJB3.setHelperText("Tidak ada karat pada tulang baja \nmaka: 100%");

                    TIKJB4.setHint("Aus/pelapukan beton");
                    TIKJB4.setHelperText("Tidak ada aus/ppelapukan beton \nmaka: 100%");

                    TIKJB5.setHint("Pecah/hilangnya material");
                    TIKJB5.setHelperText("Tidak ada pecah/hilangnya material \nmaka: 100%");

                    TIKJB6.setHint("Penyimpangan lendutan");
                    TIKJB6.setHelperText("Tidak ada penyimpangan terhadap lendutan \nizin maka: 100%");

                    TIKJB7.setHint("Tidak perlu di isi");
                    TIKJB7.setHelperText("Tidak perlu di isi");

                    TIKJB8.setHint("Tidak perlu di isi");
                    TIKJB8.setHelperText("Tidak perlu di isi");

                } else if(selected.equals("Baja")){
                    KJB4.setEnabled(true);
                    KJB5.setEnabled(true);
                    KJB6.setEnabled(true);
                    KJB7.setEnabled(true);
                    KJB8.setEnabled(true);

                    TIKJB.setHint("Penurunan mutu cat");
                    TIKJB.setHelperText("Tidak ada penurunan mutu cat \nmaka: 100%");

                    TIKJB2.setHint("Karat/korosi");
                    TIKJB2.setHelperText("Tidak ada karat/korosi maka: 100%");

                    TIKJB3.setHint("Perubahan bentuk");
                    TIKJB3.setHelperText("Tidak ada perubahan bentuk \nmaka: 100%");

                    TIKJB4.setHint("Keretakan");
                    TIKJB4.setHelperText("Tidak ada keretakan maka: 100%");

                    TIKJB5.setHint("Elemen rusak/hilang");
                    TIKJB5.setHelperText("Tidak ada elemen rusak/hilang \nmaka: 100%");

                    TIKJB6.setHint("Elemen yang salah");
                    TIKJB6.setHelperText("Tidak ada elemen yang salah \nmaka: 100%");

                    TIKJB7.setHint("Kabel yang aus/terurai");
                    TIKJB7.setHelperText("Tidak ada kabel yang aus dan \nterurai maka: 100%");

                    TIKJB8.setHint("Ikatan/sambungan longgar");
                    TIKJB8.setHelperText("Tidak ada ikatan/sambungan longgar \nmaka: 100%");

                } else if(selected.equals("Kayu")){
                    KJB4.setEnabled(true);
                    KJB5.setEnabled(true);
                    KJB6.setEnabled(false);
                    KJB7.setEnabled(false);
                    KJB8.setEnabled(false);

                    TIKJB.setHint("Pembusukan/pelapukan");
                    TIKJB.setHelperText("Tidak ada pembusukan, pelapukan, bengkok, \ncacat maka: 100%");

                    TIKJB2.setHint("Pecah/hilangnya elemen");
                    TIKJB2.setHelperText("Tidak ada pecah/hilangnya elemen maka: 100%");

                    TIKJB3.setHint("Penyusutan");
                    TIKJB3.setHelperText("Tidak ada penyusutan maka: 100%");

                    TIKJB4.setHint("Penurunan mutu");
                    TIKJB4.setHelperText("Tidak ada penurunan mutu pelapis permukaan \nmaka: 100%");

                    TIKJB5.setHint("Elemen yang longgar");
                    TIKJB5.setHelperText("Tidak ada elemen yang longgar maka: 100%");

                    TIKJB6.setHint("Tidak perlu di isi");
                    TIKJB6.setHelperText("Tidak perlu di isi");

                    TIKJB7.setHint("Tidak perlu di isi");
                    TIKJB7.setHelperText("Tidak perlu di isi");

                    TIKJB8.setHint("Tidak perlu di isi");
                    TIKJB8.setHelperText("Tidak perlu di isi");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_1.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ7);
            }
        });

        take1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_2.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ8);
            }
        });

        take2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_3.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ9);
            }
        });

        take3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_4.png";
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

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ10);
            }
        });

        take4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_5.png";
                mediaFile = new File(path);
                Uri outputFileUri = Uri.fromFile(mediaFile);
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                startActivityForResult(intent, REQ5);

            }
        });

        open4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ11);
            }
        });

        take5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                String path = Environment.getExternalStorageDirectory() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_6.png";
                mediaFile = new File(path);
                Uri outputFileUri = Uri.fromFile(mediaFile);
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                startActivityForResult(intent, REQ6);

            }
        });

        open5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31");
                if(!folder.exists()){
                    folder.mkdirs();
                }

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQ12);
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInputValue();

                if(op == false){

                    ID = ISEG + "_A31";

                    Boolean insertUjiA31 = databaseHelper.insertDataA31(ID, ISEG, JLL_TXT, JPK_TXT, TGI_TXT, LDI_TXT, PNI_TXT,
                            LBI_TXT, GTI_TXT, KTI_TXT, SKJB_TXT, KJB_TXT, KJB2_TXT, KJB3_TXT, KJB4_TXT, KJB5_TXT, KJB6_TXT,
                            KJB7_TXT, KJB8_TXT, TUM_TXT, TSD_TXT, TKK_TXT, TUP_TXT, TUP2_TXT, TUK_TXT, TBK_TXT, TTP_TXT, TAP_TXT,
                            REC_TXT, DIR1, DIR2, DIR3, DIR4, DIR5, DIR6, "F");

                    if(insertUjiA31 != false){
                        showPopup();
                    }
                } else {
                    Boolean updateUjiA31 = databaseHelper.updateDataA31(ID, ISEG, JLL_TXT, JPK_TXT, TGI_TXT, LDI_TXT, PNI_TXT,
                            LBI_TXT, GTI_TXT, KTI_TXT, SKJB_TXT, KJB_TXT, KJB2_TXT, KJB3_TXT, KJB4_TXT, KJB5_TXT, KJB6_TXT,
                            KJB7_TXT, KJB8_TXT, TUM_TXT, TSD_TXT, TKK_TXT, TUP_TXT, TUP2_TXT, TUK_TXT, TBK_TXT, TTP_TXT, TAP_TXT,
                            REC_TXT, DIR1, DIR2, DIR3, DIR4, DIR5, DIR6, "F");

                    if(updateUjiA31 != false){
                        showPopup();
                    }
                    op2 = false;
                }

            }
        });

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a31_recycler.setVisibility(View.GONE);
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up2);
                INPUT.setVisibility(View.VISIBLE);
                INPUT.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                aksi.startAnimation(animation);
                aksi.setVisibility(View.GONE);

                SKJB.setSelection(((ArrayAdapter<String>)SKJB.getAdapter()).getPosition(SKJB_TXT));

                if(TUM_TXT == 100){
                    TUM.setSelection(((ArrayAdapter<String>)TUM.getAdapter()).getPosition("Ada"));
                } else if(TUM_TXT == 0){
                    TUM.setSelection(((ArrayAdapter<String>)TUM.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TUM.setSelection(((ArrayAdapter<String>)TUM.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TSD_TXT == 100){
                    TSD.setSelection(((ArrayAdapter<String>)TSD.getAdapter()).getPosition("Ada"));
                } else if(TSD_TXT == 0){
                    TSD.setSelection(((ArrayAdapter<String>)TSD.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TSD.setSelection(((ArrayAdapter<String>)TSD.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TKK_TXT == 100){
                    TKK.setSelection(((ArrayAdapter<String>)TKK.getAdapter()).getPosition("Ada"));
                } else if(TKK_TXT == 0){
                    TKK.setSelection(((ArrayAdapter<String>)TKK.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TKK.setSelection(((ArrayAdapter<String>)TKK.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TUP_TXT == 100){
                    TUP.setSelection(((ArrayAdapter<String>)TUP.getAdapter()).getPosition("Ada"));
                } else if(TUP_TXT == 0){
                    TUP.setSelection(((ArrayAdapter<String>)TUP.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TUP.setSelection(((ArrayAdapter<String>)TUP.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TUP2_TXT == 100){
                    TUP2.setSelection(((ArrayAdapter<String>)TUP2.getAdapter()).getPosition("Ada"));
                } else if(TUP2_TXT == 0){
                    TUP2.setSelection(((ArrayAdapter<String>)TUP2.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TUP2.setSelection(((ArrayAdapter<String>)TUP2.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TUK_TXT == 100){
                    TUK.setSelection(((ArrayAdapter<String>)TUK.getAdapter()).getPosition("Ada"));
                } else if(TUK_TXT == 0){
                    TUK.setSelection(((ArrayAdapter<String>)TUK.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TUK.setSelection(((ArrayAdapter<String>)TUK.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TBK_TXT == 100){
                    TBK.setSelection(((ArrayAdapter<String>)TBK.getAdapter()).getPosition("Ada"));
                } else if(TBK_TXT == 0){
                    TBK.setSelection(((ArrayAdapter<String>)TBK.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TBK.setSelection(((ArrayAdapter<String>)TBK.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TTP_TXT == 100){
                    TTP.setSelection(((ArrayAdapter<String>)TTP.getAdapter()).getPosition("Ada"));
                } else if(TTP_TXT == 0){
                    TTP.setSelection(((ArrayAdapter<String>)TTP.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TTP.setSelection(((ArrayAdapter<String>)TTP.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(TAP_TXT == 100){
                    TAP.setSelection(((ArrayAdapter<String>)TAP.getAdapter()).getPosition("Ada"));
                } else if(TAP_TXT == 0){
                    TAP.setSelection(((ArrayAdapter<String>)TAP.getAdapter()).getPosition("Tidak Ada"));
                } else {
                    TAP.setSelection(((ArrayAdapter<String>)TAP.getAdapter()).getPosition("Tidak Perlu"));
                }

                if(JLL_TXT == -1){
                    JLL.setText("");
                } else {
                    JLL.setText(String.valueOf(JLL_TXT));
                }

                if(JPK_TXT == -1){
                    JPK.setText("");
                } else {
                    JPK.setText(String.valueOf(JPK_TXT));
                }

                if(TGI_TXT == -1){
                    TGI.setText("");
                } else {
                    TGI.setText(String.valueOf(TGI_TXT));
                }

                if(LDI_TXT == -1){
                    LDI.setText("");
                } else {
                    LDI.setText(String.valueOf(LDI_TXT));
                }

                if(PNI_TXT == -1){
                    PNI.setText("");
                } else {
                    PNI.setText(String.valueOf(PNI_TXT));
                }

                if(LBI_TXT == -1){
                    LBI.setText("");
                } else {
                    LBI.setText(String.valueOf(LBI_TXT));
                }

                if(GTI_TXT == -1){
                    GTI.setText("");
                } else {
                    GTI.setText(String.valueOf(GTI_TXT));
                }

                if(KTI_TXT == -1){
                    KTI.setText("");
                } else {
                    KTI.setText(String.valueOf(KTI_TXT));
                }

                if(KJB_TXT == -1){
                    KJB.setText("");
                } else {
                    KJB.setText(String.valueOf(KJB_TXT));
                }

                if(KJB2_TXT == -1){
                    KJB2.setText("");
                } else {
                    KJB2.setText(String.valueOf(KJB2_TXT));
                }

                if(KJB3_TXT == -1){
                    KJB3.setText("");
                } else {
                    KJB3.setText(String.valueOf(KJB3_TXT));
                }

                if(KJB4_TXT == -1){
                    KJB4.setText("");
                } else {
                    KJB4.setText(String.valueOf(KJB4_TXT));
                }

                if(KJB5_TXT == -1){
                    KJB5.setText("");
                } else {
                    KJB5.setText(String.valueOf(KJB5_TXT));
                }

                if(KJB6_TXT == -1){
                    KJB6.setText("");
                } else {
                    KJB6.setText(String.valueOf(KJB6_TXT));
                }

                if(KJB7_TXT == -1){
                    KJB7.setText("");
                } else {
                    KJB7.setText(String.valueOf(KJB7_TXT));
                }

                if(KJB8_TXT == -1){
                    KJB8.setText("");
                } else {
                    KJB8.setText(String.valueOf(KJB8_TXT));
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

                if(DIR5.equals("-")){

                } else {
                    clear4.setVisibility(View.VISIBLE);
                    aksi6.setVisibility(View.GONE);
                    FT5.setImageBitmap(BitmapFactory.decodeFile(DIR5));
                }

                if(DIR6.equals("-")){

                } else {
                    clear5.setVisibility(View.VISIBLE);
                    aksi7.setVisibility(View.GONE);
                    FT6.setImageBitmap(BitmapFactory.decodeFile(DIR6));
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

        clear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                DIR5 = "-";
                FT5.setImageBitmap(null);
                aksi6.setVisibility(View.VISIBLE);
                aksi6.startAnimation(animation);
                clear4.startAnimation(animation2);
                clear4.setVisibility(View.GONE);
            }
        });

        clear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                DIR6 = "-";
                FT6.setImageBitmap(null);
                aksi7.setVisibility(View.VISIBLE);
                aksi7.startAnimation(animation);
                clear5.startAnimation(animation2);
                clear5.setVisibility(View.GONE);
            }
        });

        klf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(op2 == false){

                } else {
                    a31ArrayList.clear();
                    a31ArrayList.addAll(databaseHelper.allDataA31(ISEG));

                    adapter = new A31_Adapter(A31Activity.this, a31ArrayList);
                    a31_recycler.setAdapter(adapter);

                    INPUT.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                    a31_recycler.setVisibility(View.VISIBLE);
                    a31_recycler.startAnimation(animation);
                    JLL.setText("");
                    JPK.setText("");
                    TGI.setText("");
                    LDI.setText("");
                    PNI.setText("");
                    LBI.setText("");
                    GTI.setText("");
                    KTI.setText("");
                    KJB.setText("");
                    KJB2.setText("");
                    KJB3.setText("");
                    KJB4.setText("");
                    KJB5.setText("");
                    KJB6.setText("");
                    KJB7.setText("");
                    KJB8.setText("");
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

                        new insertDataA31().execute((Void[])null);

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

        a31_recycler.setOnScrollListener(new HideScrollingListener() {
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
                Intent intent = new Intent(A31Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A31Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A31Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A31Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR4);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A31Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR5);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FT6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A31Activity.this, ImageViewActivity.class);
                intent.putExtra("url", DIR6);
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
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
        }

        File mediaStorageDir2 = null;
        String state2 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_1.png");
        } else {
            mediaStorageDir2 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_1.png");
        }

        File mediaStorageDir3 = null;
        String state3 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_2.png");
        } else {
            mediaStorageDir3 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_2.png");
        }

        File mediaStorageDir4 = null;
        String state4 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_3.png");
        } else {
            mediaStorageDir4 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_3.png");
        }

        File mediaStorageDir5 = null;
        String state5 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_4.png");
        } else {
            mediaStorageDir5 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_4.png");
        }

        File mediaStorageDir6 = null;
        String state6 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir6 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_5.png");
        } else {
            mediaStorageDir6 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_5.png");
        }

        File mediaStorageDir7 = null;
        String state7 = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir7 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_6.png");
        } else {
            mediaStorageDir7 = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/"+ISEG+"_6.png");
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

            } else if (requestCode == REQ2) {

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

            } else if (requestCode == REQ3) {

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

            } else if (requestCode == REQ4) {

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

            } else if (requestCode == REQ5) {

                if(mediaFile.exists()){

                    try {
                        compressedImage = new Compressor(this)
                                .setMaxWidth(480)
                                .setMaxHeight(360)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                                .compressToFile(mediaFile);

                        DIR5 = compressedImage.getAbsolutePath();

                        mediaFile.delete();

                        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                        aksi6.setVisibility(View.GONE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clear4.setVisibility(View.VISIBLE);
                                clear4.startAnimation(animation);
                            }
                        }, 1000);
                        FT5.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if (requestCode == REQ6) {

                if(mediaFile.exists()){

                    try {
                        compressedImage = new Compressor(this)
                                .setMaxWidth(480)
                                .setMaxHeight(360)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(mediaStorageDir.getAbsolutePath())
                                .compressToFile(mediaFile);

                        DIR6 = compressedImage.getAbsolutePath();

                        mediaFile.delete();

                        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                        aksi7.setVisibility(View.GONE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clear5.setVisibility(View.VISIBLE);
                                clear5.startAnimation(animation);
                            }
                        }, 1000);
                        FT6.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } else if(requestCode == REQ7){

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

            } else if(requestCode == REQ8){

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

            } else if(requestCode == REQ9){

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

            } else if(requestCode == REQ10){

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

            } else if(requestCode == REQ11){

                Uri dataFoto = data.getData();
                final String path = getPathFromURI(dataFoto);
                if(path != null){
                    mediaFile = new File(path);

                    try{

                        FileChannel src = new FileInputStream(mediaFile).getChannel();
                        FileChannel dst = new FileOutputStream(mediaStorageDir6).getChannel();
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
                            .compressToFile(mediaStorageDir6);

                    DIR5 = compressedImage.getAbsolutePath();

                    mediaStorageDir6.delete();

                    final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                    aksi6.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear4.setVisibility(View.VISIBLE);
                            clear4.startAnimation(animation);
                        }
                    }, 1000);
                    FT5.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

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
                        FileChannel dst = new FileOutputStream(mediaStorageDir7).getChannel();
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
                            .compressToFile(mediaStorageDir7);

                    DIR6 = compressedImage.getAbsolutePath();

                    mediaStorageDir7.delete();

                    final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                    aksi7.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear5.setVisibility(View.VISIBLE);
                            clear5.startAnimation(animation);
                        }
                    }, 1000);
                    FT6.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));

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

        a31ArrayList.clear();

        a31ArrayList.addAll(databaseHelper.allDataA31(ISEG));

        if(a31ArrayList.isEmpty()){
            a31_recycler.setVisibility(View.GONE);
        } else {
            adapter = new A31_Adapter(A31Activity.this, a31ArrayList);
            a31_recycler.setAdapter(adapter);
            INPUT.setVisibility(View.GONE);
            EDIT.setImageDrawable(getResources().getDrawable(R.drawable.ic_mode_edit_white_18dp));
            EDIT.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.WindowBgActiveDark)));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
            aksi.setVisibility(View.VISIBLE);
            aksi.startAnimation(animation);
            a31_recycler.setVisibility(View.VISIBLE);
            a31_recycler.startAnimation(animation);
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

    private class insertDataA31 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO A31 (ID_A31, ID_SEGMEN, UJI_A31_a, UJI_A31_b, UJI_A31_c1, UJI_A31_c2, UJI_A31_c3, UJI_A31_c4, UJI_A31_c5, UJI_A31_c6, " +
                        "KERUSAKAN_JEMBATAN, UJI_A31_d1, UJI_A31_d2, UJI_A31_d3, UJI_A31_d4, UJI_A31_d5, UJI_A31_d6, UJI_A31_d7, UJI_A31_d8, UJI_A31_e1, UJI_A31_e2, UJI_A31_e3, UJI_A31_e4, " +
                        "UJI_A31_e5, UJI_A31_e6, UJI_A31_e7, UJI_A31_e8, UJI_A31_e9, CATATAN, " +
                        "DEV_A31_a, DEV_A31_b, DEV_A31_c1, DEV_A31_c2, DEV_A31_c3, DEV_A31_c4, DEV_A31_c5, DEV_A31_c6, DEV_A31_d1, DEV_A31_d2, DEV_A31_d3, DEV_A31_d4, " +
                        "DEV_A31_d5, DEV_A31_d6, DEV_A31_d7, DEV_A31_d8, DEV_A31_e1, DEV_A31_e2, DEV_A31_e3, DEV_A31_e4, DEV_A31_e5, DEV_A31_e6, DEV_A31_e7, DEV_A31_e8, DEV_A31_e9, " +
                        "KTG_A31_a, KTG_A31_b, KTG_A31_c, KTG_A31_d, KTG_A31_e, KTG_A31) " +
                        "VALUES ('"+ID+"', '"+ISEG+"', '"+JLL_TXT+"', '"+JPK_TXT+"', '"+TGI_TXT+"', '"+LDI_TXT+"', '"+PNI_TXT+"', '"+LBI_TXT+"', '"+GTI_TXT+"', '"+KTI_TXT+"', " +
                        "'"+SKJB_TXT+"', '"+KJB_TXT+"', '"+KJB2_TXT+"', '"+KJB3_TXT+"', '"+KJB4_TXT+"', '"+KJB5_TXT+"', '"+KJB6_TXT+"', '"+KJB7_TXT+"', '"+KJB8_TXT+"', '"+TUM_TXT+"', '"+TSD_TXT+"', " +
                        "'"+TKK_TXT+"', '"+TUP_TXT+"', '"+TUP2_TXT+"', '"+TUK_TXT+"', '"+TBK_TXT+"', '"+TTP_TXT+"', '"+TAP_TXT+"', '"+REC_TXT+"', '"+DEV_JLL+"', '"+DEV_JPK+"', '"+DEV_TGI+"', " +
                        "'"+DEV_LDI+"', '"+DEV_PNI+"', '"+DEV_LBI+"', '"+DEV_GTI+"', '"+DEV_KTI+"', '"+DEV_KJB+"', '"+DEV_KJB2+"', '"+DEV_KJB3+"', '"+DEV_KJB4+"', '"+DEV_KJB5+"', " +
                        "'"+DEV_KJB6+"', '"+DEV_KJB7+"', '"+DEV_KJB8+"', '"+DEV_TUM+"', '"+DEV_TSD+"', '"+DEV_TKK+"', '"+DEV_TUP+"', '"+DEV_TUP2+"', '"+DEV_TUK+"', '"+DEV_TBK+"', " +
                        "'"+DEV_TTP+"', '"+DEV_TAP+"', '"+KTG_JLL+"', '"+KTG_JPK+"', '"+KTG_KTJ+"', '"+KTG_KJBB+"', '"+KTG_FPH+"', '"+KTG_KLF+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA31(ID, ISEG, JLL_TXT, JPK_TXT, TGI_TXT, LDI_TXT, PNI_TXT,
                        LBI_TXT, GTI_TXT, KTI_TXT, SKJB_TXT, KJB_TXT, KJB2_TXT, KJB3_TXT, KJB4_TXT, KJB5_TXT, KJB6_TXT,
                        KJB7_TXT, KJB8_TXT, TUM_TXT, TSD_TXT, TKK_TXT, TUP_TXT, TUP2_TXT, TUK_TXT, TBK_TXT, TTP_TXT, TAP_TXT,
                        REC_TXT, DIR1, DIR2, DIR3, DIR4, DIR5, DIR6, "T");

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
                new updateDataA31().execute((Void[])null);
            }

            if(!DIR1.equals("-")){
                try{
                    String Table = "A31";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A31Activity.this, uploadId, urlUpload)
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

                    String insert = "UPDATE A31 SET GBR_1 = null WHERE ID_A31 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR2.equals("-")){
                try{
                    String Table = "A31";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A31Activity.this, uploadId, urlUpload2)
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

                    String insert = "UPDATE A31 SET GBR_2 = null WHERE ID_A31 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR3.equals("-")){
                try{
                    String Table = "A31";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A31Activity.this, uploadId, urlUpload3)
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

                    String insert = "UPDATE A31 SET GBR_3 = null WHERE ID_A31 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR4.equals("-")){
                try{
                    String Table = "A31";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A31Activity.this, uploadId, urlUpload4)
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

                    String insert = "UPDATE A31 SET GBR_4 = null WHERE ID_A31 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR5.equals("-")){
                try{
                    String Table = "A31";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A31Activity.this, uploadId, urlUpload5)
                            .addFileToUpload(DIR5, "image5")
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

                    String insert = "UPDATE A31 SET GBR_5 = null WHERE ID_A31 = '"+ID+"'";

                    state.executeUpdate(insert);

                } catch (Exception ex){
                    Log.e("Error update gambar", ex.toString());
                }

            }

            if(!DIR6.equals("-")){
                try{
                    String Table = "A31";
                    String uploadId = UUID.randomUUID().toString();
                    new MultipartUploadRequest(A31Activity.this, uploadId, urlUpload6)
                            .addFileToUpload(DIR6, "image6")
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

                    String insert = "UPDATE A31 SET GBR_6 = null WHERE ID_A31 = '"+ID+"'";

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

    private class updateDataA31 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE A31 SET ID_SEGMEN = '"+ISEG+"', UJI_A31_a = '"+JLL_TXT+"', UJI_A31_b = '"+JPK_TXT+"', UJI_A31_c1 = '"+TGI_TXT+"', " +
                        "UJI_A31_c2 = '"+LDI_TXT+"', UJI_A31_c3 = '"+PNI_TXT+"', UJI_A31_c4 = '"+LBI_TXT+"', UJI_A31_c5 = '"+GTI_TXT+"', UJI_A31_c6 = '"+KTI_TXT+"', " +
                        "KERUSAKAN_JEMBATAN = '"+SKJB_TXT+"', UJI_A31_d1 = '"+KJB_TXT+"', UJI_A31_d2 = '"+KJB2_TXT+"', UJI_A31_d3 = '"+KJB3_TXT+"', UJI_A31_d4 = '"+KJB4_TXT+"', UJI_A31_d5 = '"+KJB5_TXT+"', " +
                        "UJI_A31_d6 = '"+KJB6_TXT+"', UJI_A31_d7 = '"+KJB7_TXT+"', UJI_A31_d8 = '"+KJB8_TXT+"', UJI_A31_e1 = '"+TUM_TXT+"',  UJI_A31_e2 = '"+TSD_TXT+"', " +
                        "UJI_A31_e3 = '"+TKK_TXT+"',  UJI_A31_e4 = '"+TUP_TXT+"',  UJI_A31_e5 = '"+TUP2_TXT+"',  UJI_A31_e6 = '"+TUK_TXT+"',  UJI_A31_e7 = '"+TBK_TXT+"',  " +
                        "UJI_A31_e8 = '"+TTP_TXT+"', UJI_A31_e9 = '"+TAP_TXT+"', CATATAN = '"+REC_TXT+"', " +
                        "DEV_A31_a = '"+DEV_JLL+"', DEV_A31_b = '"+DEV_JPK+"', DEV_A31_c1 = '"+DEV_TGI+"', DEV_A31_c2 = '"+DEV_LDI+"', DEV_A31_c3 = '"+DEV_PNI+"', " +
                        "DEV_A31_c4 = '"+DEV_LBI+"', DEV_A31_c5 = '"+DEV_GTI+"', DEV_A31_c6 = '"+DEV_KTI+"', DEV_A31_d1 = '"+DEV_KJB+"', DEV_A31_d2 = '"+DEV_KJB2+"', " +
                        "DEV_A31_d3 = '"+DEV_KJB3+"', DEV_A31_d4 = '"+DEV_KJB4+"', DEV_A31_d5 = '"+DEV_KJB5+"', DEV_A31_d6 = '"+DEV_KJB6+"', DEV_A31_d7 = '"+DEV_KJB7+"', " +
                        "DEV_A31_d8 = '"+DEV_KJB8+"', DEV_A31_e1 = '"+DEV_TUM+"', DEV_A31_e2 = '"+DEV_TSD+"', DEV_A31_e3 = '"+DEV_TKK+"', DEV_A31_e4 = '"+DEV_TUP+"', " +
                        "DEV_A31_e5 = '"+DEV_TUP2+"', DEV_A31_e6 = '"+DEV_TUK+"', DEV_A31_e7 = '"+DEV_TBK+"', DEV_A31_e8 = '"+DEV_TTP+"', DEV_A31_e9 = '"+DEV_TAP+"', " +
                        "KTG_A31_a = '"+KTG_JLL+"', KTG_A31_b = '"+KTG_JPK+"', KTG_A31_c = '"+KTG_KTJ+"', KTG_A31_d = '"+KTG_KJBB+"', KTG_A31_e = '"+KTG_FPH+"', KTG_A31 = '"+KTG_KLF+"' WHERE ID_A31 = '"+ID+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataA31(ID, ISEG, JLL_TXT, JPK_TXT, TGI_TXT, LDI_TXT, PNI_TXT,
                        LBI_TXT, GTI_TXT, KTI_TXT, SKJB_TXT, KJB_TXT, KJB2_TXT, KJB3_TXT, KJB4_TXT, KJB5_TXT, KJB6_TXT,
                        KJB7_TXT, KJB8_TXT, TUM_TXT, TSD_TXT, TKK_TXT, TUP_TXT, TUP2_TXT, TUK_TXT, TBK_TXT, TTP_TXT, TAP_TXT,
                        REC_TXT, DIR1, DIR2, DIR3, DIR4, DIR5, DIR6, "T");

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
            if(JLL.getText().toString().equals("") && JPK.getText().toString().equals("") &&
                    TGI.getText().toString().equals("") && LDI.getText().toString().equals("") &&
                    PNI.getText().toString().equals("") && LBI.getText().toString().equals("") &&
                    GTI.getText().toString().equals("") && KTI.getText().toString().equals("") &&
                    KJB.getText().toString().equals("") && KJB2.getText().toString().equals("") &&
                    KJB3.getText().toString().equals("") && KJB4.getText().toString().equals("") &&
                    KJB5.getText().toString().equals("") && KJB6.getText().toString().equals("") &&
                    KJB7.getText().toString().equals("") && KJB8.getText().toString().equals("") &&
                    REC.getText().toString().equals("")){
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

    private class getDataA31 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ACProgressFlower.Builder(A31Activity.this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE).sizeRatio((float) 0.2).bgAlpha((float) 0.2)
                    .fadeColor(Color.DKGRAY).build();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String location = "-", location2 = "-", location3 = "-", location4 = "-", location5 = "-", location6 = "-";

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String select = "SELECT ID_A31, UJI_A31_a, UJI_A31_b, UJI_A31_c1, UJI_A31_c2, UJI_A31_c3, UJI_A31_c4, UJI_A31_c5, UJI_A31_c6, " +
                        "KERUSAKAN_JEMBATAN, UJI_A31_d1, UJI_A31_d2, UJI_A31_d3, UJI_A31_d4, UJI_A31_d5, UJI_A31_d6, UJI_A31_d7, UJI_A31_d8, UJI_A31_e1, UJI_A31_e2, UJI_A31_e3, UJI_A31_e4," +
                        "UJI_A31_e5, UJI_A31_e6, UJI_A31_e7, UJI_A31_e8, UJI_A31_e9, CATATAN, GBR_1, GBR_2, GBR_3, GBR_4, GBR_5, GBR_6, ID_SEGMEN FROM A31 WHERE ID_SEGMEN = '"+ISEG+"'";

                ResultSet data = statement.executeQuery(select);

                while(data.next()){

                    String upload = databaseHelper.getA31Upload(data.getString("ID_SEGMEN"));

                    if(upload.equals("T") || upload.equals("-")){

                        String dir1 = data.getString("GBR_1");
                        String dir2 = data.getString("GBR_2");
                        String dir3 = data.getString("GBR_3");
                        String dir4 = data.getString("GBR_4");
                        String dir5 = data.getString("GBR_5");
                        String dir6 = data.getString("GBR_6");

                        if(dir1 != null){

                            dir1 = dir1.replaceAll(" ", "%20");

                            Bitmap bitmap = null;
                            try {

                                InputStream fto = new java.net.URL(dir1).openStream();

                                bitmap = BitmapFactory.decodeStream(fto);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir = null;
                                String state = Environment.getExternalStorageState();

                                if (state.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_1.png");
                                } else {
                                    mediaStorageDir = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_1.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir2 = null;
                                String state2 = Environment.getExternalStorageState();

                                if (state2.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_2.png");
                                } else {
                                    mediaStorageDir2 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_2.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir3 = null;
                                String state3 = Environment.getExternalStorageState();

                                if (state3.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_3.png");
                                } else {
                                    mediaStorageDir3 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_3.png");
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

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir4 = null;
                                String state4 = Environment.getExternalStorageState();

                                if (state4.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_4.png");
                                } else {
                                    mediaStorageDir4 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_4.png");
                                }

                                FileOutputStream save4 = new FileOutputStream(mediaStorageDir4);
                                bitmap4.compress(Bitmap.CompressFormat.PNG, 100, save4);
                                save4.flush();

                                location4 = mediaStorageDir4.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        if(dir5 != null){

                            dir5 = dir5.replaceAll(" ", "%20");

                            Bitmap bitmap5 = null;
                            try {

                                InputStream fto5 = new java.net.URL(dir5).openStream();

                                bitmap5 = BitmapFactory.decodeStream(fto5);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir5 = null;
                                String state5 = Environment.getExternalStorageState();

                                if (state5.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir5 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_5.png");
                                } else {
                                    mediaStorageDir5 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_5.png");
                                }

                                FileOutputStream save5 = new FileOutputStream(mediaStorageDir5);
                                bitmap5.compress(Bitmap.CompressFormat.PNG, 100, save5);
                                save5.flush();

                                location5 = mediaStorageDir5.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        if(dir6 != null){

                            dir6 = dir6.replaceAll(" ", "%20");

                            Bitmap bitmap6 = null;
                            try {

                                InputStream fto6 = new java.net.URL(dir6).openStream();

                                bitmap6 = BitmapFactory.decodeStream(fto6);

                                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress");
                                if(!folder.exists()){
                                    folder.mkdirs();
                                }

                                File mediaStorageDir6 = null;
                                String state6 = Environment.getExternalStorageState();

                                if (state6.contains(Environment.MEDIA_MOUNTED)) {
                                    mediaStorageDir6 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_6.png");
                                } else {
                                    mediaStorageDir6 = new File(Environment
                                            .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Pictures/"+NRU+"/"+ISEG+"/A31/Compress/"+ISEG+"_6.png");
                                }

                                FileOutputStream save6 = new FileOutputStream(mediaStorageDir6);
                                bitmap6.compress(Bitmap.CompressFormat.PNG, 100, save6);
                                save6.flush();

                                location6 = mediaStorageDir6.getAbsolutePath();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        Boolean insertA31 = databaseHelper.insertDataA31(data.getString("ID_A31"), data.getString("ID_SEGMEN"),
                                data.getDouble("UJI_A31_a"), data.getDouble("UJI_A31_b"), data.getDouble("UJI_A31_c1"),
                                data.getDouble("UJI_A31_c2"), data.getDouble("UJI_A31_c3"), data.getDouble("UJI_A31_c4"),
                                data.getDouble("UJI_A31_c5"), data.getDouble("UJI_A31_c6"), data.getString("KERUSAKAN_JEMBATAN"),
                                data.getDouble("UJI_A31_d1"), data.getDouble("UJI_A31_d2"), data.getDouble("UJI_A31_d3"),
                                data.getDouble("UJI_A31_d4"), data.getDouble("UJI_A31_d5"), data.getDouble("UJI_A31_d6"),
                                data.getDouble("UJI_A31_d7"), data.getDouble("UJI_A31_d8"), data.getDouble("UJI_A31_e1"),
                                data.getDouble("UJI_A31_e2"), data.getDouble("UJI_A31_e3"), data.getDouble("UJI_A31_e4"),
                                data.getDouble("UJI_A31_e5"), data.getDouble("UJI_A31_e6"), data.getDouble("UJI_A31_e7"),
                                data.getDouble("UJI_A31_e8"),  data.getDouble("UJI_A31_e9"), data.getString("CATATAN"),
                                location,location2, location3, location4, location5, location6,"T");

                        if(insertA31 == false){

                            databaseHelper.updateDataA31(data.getString("ID_A31"), data.getString("ID_SEGMEN"),
                                    data.getDouble("UJI_A31_a"), data.getDouble("UJI_A31_b"), data.getDouble("UJI_A31_c1"),
                                    data.getDouble("UJI_A31_c2"), data.getDouble("UJI_A31_c3"), data.getDouble("UJI_A31_c4"),
                                    data.getDouble("UJI_A31_c5"), data.getDouble("UJI_A31_c6"), data.getString("KERUSAKAN_JEMBATAN"),
                                    data.getDouble("UJI_A31_d1"), data.getDouble("UJI_A31_d2"), data.getDouble("UJI_A31_d3"),
                                    data.getDouble("UJI_A31_d4"), data.getDouble("UJI_A31_d5"), data.getDouble("UJI_A31_d6"),
                                    data.getDouble("UJI_A31_d7"), data.getDouble("UJI_A31_d8"), data.getDouble("UJI_A31_e1"),
                                    data.getDouble("UJI_A31_e2"), data.getDouble("UJI_A31_e3"), data.getDouble("UJI_A31_e4"),
                                    data.getDouble("UJI_A31_e5"), data.getDouble("UJI_A31_e6"), data.getDouble("UJI_A31_e7"),
                                    data.getDouble("UJI_A31_e8"),  data.getDouble("UJI_A31_e9"), data.getString("CATATAN"),
                                    location,location2, location3, location4, location5, location6,"T");

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

            a31ArrayList.addAll(databaseHelper.allDataA31(ISEG));

            if(!a31ArrayList.isEmpty()){
                a31_recycler.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new A31_Adapter(A31Activity.this, a31ArrayList);
                        a31_recycler.setAdapter(adapter);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right2);
                        a31_recycler.startAnimation(animation);
                        aksi.setVisibility(View.VISIBLE);
                        aksi.startAnimation(animation);
                    }
                }, 100);
            }
        }
    }

}
