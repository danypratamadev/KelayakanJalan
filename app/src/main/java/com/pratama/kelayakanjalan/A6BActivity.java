package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class A6BActivity extends AppCompatActivity {

    private LinearLayout A6B1, A6B2, A6B3, A6B4, A6B5, A6B6, A6B7, A6B8;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    private ImageButton back;
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6b);

        init();
        onClickListener();
        getUpload();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);

        A6B1 = (LinearLayout) findViewById(R.id.A6B1);
        A6B2 = (LinearLayout) findViewById(R.id.A6B2);
        A6B3 = (LinearLayout) findViewById(R.id.A6B3);
        A6B4 = (LinearLayout) findViewById(R.id.A6B4);
        A6B5 = (LinearLayout) findViewById(R.id.A6B5);
        A6B6 = (LinearLayout) findViewById(R.id.A6B6);
        A6B7 = (LinearLayout) findViewById(R.id.A6B7);
        A6B8 = (LinearLayout) findViewById(R.id.A6B8);
        back = (ImageButton) findViewById(R.id.back);

        Intent intent = getIntent();
        ISEG = intent.getStringExtra("iseg");
        SJR = intent.getStringExtra("sjr");
        FNG = intent.getStringExtra("fng");
        KPR = intent.getStringExtra("kpr");
        MJL = intent.getStringExtra("mjl");
        NRU = intent.getStringExtra("nru");

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);
        img7 = (ImageView) findViewById(R.id.img7);
        img8 = (ImageView) findViewById(R.id.img8);

    }

    private void onClickListener(){

        A6B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B1Activity.class);
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

        A6B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B2Activity.class);
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

        A6B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B3Activity.class);
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

        A6B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B4Activity.class);
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

        A6B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B5Activity.class);
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

        A6B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B6Activity.class);
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

        A6B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B7Activity.class);
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

        A6B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6BActivity.this, A6B8Activity.class);
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

        String upload = databaseHelper.getA6b1Upload(ISEG);

        if(upload != null){

            if(upload.equals("F")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload.equals("T")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload2 = databaseHelper.getA6b2Upload(ISEG);

        if(upload2 != null){

            if(upload2.equals("F")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload2.equals("T")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload3 = databaseHelper.getA6b3Upload(ISEG);

        if(upload3 != null){

            if(upload3.equals("F")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload3.equals("T")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload4 = databaseHelper.getA6b4Upload(ISEG);

        if(upload4 != null){

            if(upload4.equals("F")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload4.equals("T")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload5 = databaseHelper.getA6b5Upload(ISEG);

        if(upload5 != null){

            if(upload5.equals("F")){

                img5.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload5.equals("T")){

                img5.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload6 = databaseHelper.getA6b6Upload(ISEG);

        if(upload6 != null){

            if(upload6.equals("F")){

                img6.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload6.equals("T")){

                img6.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload7 = databaseHelper.getA6b7Upload(ISEG);

        if(upload7 != null){

            if(upload7.equals("F")){

                img7.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload7.equals("T")){

                img7.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload8 = databaseHelper.getA6b8Upload(ISEG);

        if(upload8 != null){

            if(upload8.equals("F")){

                img8.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload8.equals("T")){

                img8.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

    }

}
