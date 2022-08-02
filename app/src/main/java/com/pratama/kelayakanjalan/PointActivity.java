package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PointActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private LinearLayout A1, A2, A3, A4, A5, A6A, A6B, ADMIN;
    private TextView SEG_DIS, NRU_DIS, SEG_DIS2, PSG_DIS;
    private double KCP;
    private String SEG, PSG, SJR, FNG, KPR, MJL, NRU, ISEG, IRU;
    private ImageButton back;
    private ImageView img1, img2, img3, img4, img5, img6, img7;
    private Boolean a11, a12, a13, a14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        init();
        onClickListener();
        getUpload1();
        getUpload2();
        getUpload3();
        getUpload4();
        getUpload5();
        getUpload6();
        getUpload7();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);

        SEG_DIS = (TextView) findViewById(R.id.SEG_DIS);
        NRU_DIS = (TextView) findViewById(R.id.NRU_DIS);
        PSG_DIS = (TextView) findViewById(R.id.PSG_DIS);
        A1 = (LinearLayout) findViewById(R.id.A1);
        A2 = (LinearLayout) findViewById(R.id.A2);
        A3 = (LinearLayout) findViewById(R.id.A3);
        A4 = (LinearLayout) findViewById(R.id.A4);
        A5 = (LinearLayout) findViewById(R.id.A5);
        A6A = (LinearLayout) findViewById(R.id.A6A);
        A6B = (LinearLayout) findViewById(R.id.A6B);
        ADMIN = (LinearLayout) findViewById(R.id.ADMIN);
        back = (ImageButton) findViewById(R.id.back);

        Intent intent = getIntent();
        IRU = intent.getStringExtra("iru");
        ISEG = intent.getStringExtra("iseg");
        SEG = intent.getStringExtra("seg");
        PSG = intent.getStringExtra("psg");
        SJR = intent.getStringExtra("sjr");
        FNG = intent.getStringExtra("fng");
        KPR = intent.getStringExtra("kpr");
        MJL = intent.getStringExtra("mjl");
        NRU = intent.getStringExtra("nru");
        KCP = intent.getDoubleExtra("kcp", 0);

        SEG_DIS.setText("Segmen: "+SEG+" :: Panjang: "+PSG);
        NRU_DIS.setText("RUAS: "+NRU);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);
        img7 = (ImageView) findViewById(R.id.img7);

    }

    private void onClickListener(){

        A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, A1Activity.class);
                intent.putExtra("iru", IRU);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, A2Activity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, A3Activity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, A4Activity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, A5Activity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A6A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, A6AActivity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A6B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, A6BActivity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ADMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PointActivity.this, AdministrasiActivity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("kcp", KCP);
                intent.putExtra("nru", NRU);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        getUpload1();
        getUpload2();
        getUpload3();
        getUpload4();
        getUpload5();
        getUpload6();
        getUpload7();

    }

    public void getUpload1(){

        getUpload11();
        getUpload12();
        getUpload13();
        getUpload14();

        if(a11 != null && a12 != null && a13 != null && a14 != null){

            if(a11 == true && a12 == true && a13 == true && a14 == true){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(a11 == false || a12 == false || a13 == false || a14 == false){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload11(){

        String upload = databaseHelper.getA111Upload(ISEG);

        String upload2 = databaseHelper.getA112Upload(ISEG);

        String upload3 = databaseHelper.getA113Upload(ISEG);

        String upload4 = databaseHelper.getA114Upload(ISEG);

        String upload5 = databaseHelper.getA115Upload(ISEG);

        String upload6 = databaseHelper.getA116Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null && upload5 != null && upload6 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T") && upload5.equals("T") && upload6.equals("T")){

                a11 = true;

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F") || upload5.equals("F") || upload6.equals("F")){

                a11 = false;

            }

        }

    }

    public void getUpload12(){

        String upload = databaseHelper.getA121Upload(ISEG);

        String upload2 = databaseHelper.getA122Upload(ISEG);

        String upload3 = databaseHelper.getA123Upload(ISEG);

        String upload4 = databaseHelper.getA124Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T")){

                a12 = true;

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F")){

                a12 = false;

            }

        }

    }

    public void getUpload13(){

        String upload = databaseHelper.getA131Upload(ISEG);

        String upload2 = databaseHelper.getA132Upload(ISEG);

        String upload3 = databaseHelper.getA133Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T")){

                a13 = true;

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F")){

                a13 = false;

            }

        }

    }

    public void getUpload14(){

        String upload = databaseHelper.getA141Upload(ISEG);

        if(upload != null){

            if(upload.equals("T")){

                a14 = true;

            } else if(upload.equals("F")){

                a14 = false;

            }

        }

    }

    public void getUpload2(){

        String upload = databaseHelper.getA21Upload(ISEG);

        String upload2 = databaseHelper.getA22Upload(ISEG);

        String upload3 = databaseHelper.getA23Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload3(){

        String upload = databaseHelper.getA31Upload(ISEG);

        String upload2 = databaseHelper.getA32Upload(ISEG);

        String upload3 = databaseHelper.getA33Upload(ISEG);

        String upload4 = databaseHelper.getA34Upload(ISEG);

        String upload5 = databaseHelper.getA35Upload(ISEG);

        String upload6 = databaseHelper.getA36Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null && upload5 != null && upload6 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T") && upload5.equals("T") && upload6.equals("T")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F") || upload5.equals("F") || upload6.equals("F")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload4(){

        String upload = databaseHelper.getA41Upload(ISEG);

        String upload2 = databaseHelper.getA42Upload(ISEG);

        String upload3 = databaseHelper.getA43Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload5(){

        String upload = databaseHelper.getA51Upload(ISEG);

        String upload2 = databaseHelper.getA52Upload(ISEG);

        String upload3 = databaseHelper.getA53Upload(ISEG);

        String upload4 = databaseHelper.getA54Upload(ISEG);

        String upload5 = databaseHelper.getA55Upload(ISEG);

        String upload6 = databaseHelper.getA56Upload(ISEG);

        String upload7 = databaseHelper.getA57Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null && upload5 != null && upload6 != null && upload7 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T") && upload5.equals("T") && upload6.equals("T") && upload7.equals("T")){

                img5.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F") || upload5.equals("F") || upload6.equals("F") || upload7.equals("F")){

                img5.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload6(){

        String upload = databaseHelper.getA6a1Upload(ISEG);

        String upload2 = databaseHelper.getA6a2Upload(ISEG);

        String upload3 = databaseHelper.getA6a3Upload(ISEG);

        String upload4 = databaseHelper.getA6a4Upload(ISEG);

        String upload5 = databaseHelper.getA6a5Upload(ISEG);

        String upload6 = databaseHelper.getA6a6Upload(ISEG);

        String upload7 = databaseHelper.getA6a7Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null && upload5 != null && upload6 != null && upload7 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T") && upload5.equals("T") && upload6.equals("T") && upload7.equals("T")){

                img6.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F") || upload5.equals("F") || upload6.equals("F") || upload7.equals("F")){

                img6.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload7(){

        String upload = databaseHelper.getA6b1Upload(ISEG);

        String upload2 = databaseHelper.getA6b2Upload(ISEG);

        String upload3 = databaseHelper.getA6b3Upload(ISEG);

        String upload4 = databaseHelper.getA6b4Upload(ISEG);

        String upload5 = databaseHelper.getA6b5Upload(ISEG);

        String upload6 = databaseHelper.getA6b6Upload(ISEG);

        String upload7 = databaseHelper.getA6b7Upload(ISEG);

        String upload8 = databaseHelper.getA6b8Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null && upload5 != null && upload6 != null && upload7 != null && upload8 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T") && upload5.equals("T") && upload6.equals("T") && upload7.equals("T") && upload8.equals("T")){

                img7.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F") || upload5.equals("F") || upload6.equals("F") || upload7.equals("F") || upload8.equals("F")){

                img7.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

}
