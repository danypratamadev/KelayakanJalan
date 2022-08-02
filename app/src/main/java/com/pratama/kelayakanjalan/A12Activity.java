package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class A12Activity extends AppCompatActivity {

    private LinearLayout A121, A122, A123, A124;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    private ImageButton back;
    private ImageView img1, img2, img3, img4;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a12);

        init();
        onClickListener();
        getUpload();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        ISEG = intent.getStringExtra("iseg");
        SJR = intent.getStringExtra("sjr");
        FNG = intent.getStringExtra("fng");
        KPR = intent.getStringExtra("kpr");
        MJL = intent.getStringExtra("mjl");
        NRU = intent.getStringExtra("nru");

        A121 = (LinearLayout) findViewById(R.id.A121);
        A122 = (LinearLayout) findViewById(R.id.A122);
        A123 = (LinearLayout) findViewById(R.id.A123);
        A124 = (LinearLayout) findViewById(R.id.A124);
        back = (ImageButton) findViewById(R.id.back);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

    }

    private void onClickListener(){

        A121.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A12Activity.this, A121Activity.class);
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

        A122.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A12Activity.this, A122Activity.class);
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

        A123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A12Activity.this, A123Activity.class);
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

        A124.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A12Activity.this, A124Activity.class);
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

        getUpload();

    }

    public void getUpload(){

        String upload = databaseHelper.getA121Upload(ISEG);

        if(upload != null){

            if(upload.equals("F")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload.equals("T")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload2 = databaseHelper.getA122Upload(ISEG);

        if(upload2 != null){

            if(upload2.equals("F")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload2.equals("T")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload3 = databaseHelper.getA123Upload(ISEG);

        if(upload3 != null){

            if(upload3.equals("F")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload3.equals("T")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload4 = databaseHelper.getA124Upload(ISEG);

        if(upload4 != null){

            if(upload4.equals("F")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload4.equals("T")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

    }

}
