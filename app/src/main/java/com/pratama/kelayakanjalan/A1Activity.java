package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class A1Activity extends AppCompatActivity {

    private LinearLayout A11, A12, A13, A14;
    private double KCP;
    private String SJR, FNG, KPR, MJL, NRU, ISEG, IRU;
    private ImageButton back;
    private ImageView img1, img2, img3, img4;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);

        init();
        onClickListener();
        getUpload1();
        getUpload2();
        getUpload3();
        getUpload4();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);

        A11 = (LinearLayout) findViewById(R.id.A11);
        A12 = (LinearLayout) findViewById(R.id.A12);
        A13 = (LinearLayout) findViewById(R.id.A13);
        A14 = (LinearLayout) findViewById(R.id.A14);
        back = (ImageButton) findViewById(R.id.back);

        Intent intent = getIntent();
        IRU = intent.getStringExtra("iru");
        ISEG = intent.getStringExtra("iseg");
        SJR = intent.getStringExtra("sjr");
        FNG = intent.getStringExtra("fng");
        KPR = intent.getStringExtra("kpr");
        MJL = intent.getStringExtra("mjl");
        KCP = intent.getDoubleExtra("kcp", 0);
        NRU = intent.getStringExtra("nru");

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

    }

    private void onClickListener(){

        A11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A1Activity.this, A11Activity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A1Activity.this, A12Activity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        A13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A1Activity.this, A13Activity.class);
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

        A14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A1Activity.this, A14Activity.class);
                intent.putExtra("iseg", ISEG);
                intent.putExtra("sjr", SJR);
                intent.putExtra("fng", FNG);
                intent.putExtra("kpr", KPR);
                intent.putExtra("mjl", MJL);
                intent.putExtra("nru", NRU);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    }

    public void getUpload1(){

        String upload = databaseHelper.getA111Upload(ISEG);

        String upload2 = databaseHelper.getA112Upload(ISEG);

        String upload3 = databaseHelper.getA113Upload(ISEG);

        String upload4 = databaseHelper.getA114Upload(ISEG);

        String upload5 = databaseHelper.getA115Upload(ISEG);

        String upload6 = databaseHelper.getA116Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null && upload5 != null && upload6 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T") && upload5.equals("T") && upload6.equals("T")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F") || upload5.equals("F") || upload6.equals("F")){

               img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload2(){

        String upload = databaseHelper.getA121Upload(ISEG);

        String upload2 = databaseHelper.getA122Upload(ISEG);

        String upload3 = databaseHelper.getA123Upload(ISEG);

        String upload4 = databaseHelper.getA124Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null && upload4 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T") && upload4.equals("T")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F") || upload4.equals("F")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload3(){

        String upload = databaseHelper.getA131Upload(ISEG);

        String upload2 = databaseHelper.getA132Upload(ISEG);

        String upload3 = databaseHelper.getA133Upload(ISEG);

        if(upload != null && upload2 != null && upload3 != null){

            if(upload.equals("T") && upload2.equals("T") && upload3.equals("T")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F") || upload2.equals("F") || upload3.equals("F")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

    public void getUpload4(){

        String upload = databaseHelper.getA141Upload(ISEG);

        if(upload != null){

            if(upload.equals("T")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            } else if(upload.equals("F")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            }

        }

    }

}
