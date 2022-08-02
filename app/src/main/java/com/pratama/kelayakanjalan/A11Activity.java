package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class A11Activity extends AppCompatActivity {

    private LinearLayout A111, A112, A113, A114, A115, A116;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    private ImageButton back;
    private DatabaseHelper databaseHelper;
    private ImageView img1, img2, img3, img4, img5, img6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a11);

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

        A111 = (LinearLayout) findViewById(R.id.A111);
        A112 = (LinearLayout) findViewById(R.id.A112);
        A113 = (LinearLayout) findViewById(R.id.A113);
        A114 = (LinearLayout) findViewById(R.id.A114);
        A115 = (LinearLayout) findViewById(R.id.A115);
        A116 = (LinearLayout) findViewById(R.id.A116);
        back = (ImageButton) findViewById(R.id.back);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);


    }

    private void onClickListener(){

        A111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A11Activity.this, A111Activity.class);
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

        A112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A11Activity.this, A112Activity.class);
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

        A113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A11Activity.this, A113Activity.class);
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

        A114.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A11Activity.this, A114Activity.class);
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

        A115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A11Activity.this, A115Activity.class);
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

        A116.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A11Activity.this, A116Activity.class);
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

        String upload = databaseHelper.getA111Upload(ISEG);

        if(upload != null){

            if(upload.equals("F")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload.equals("T")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload2 = databaseHelper.getA112Upload(ISEG);

        if(upload2 != null){

            if(upload2.equals("F")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload2.equals("T")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload3 = databaseHelper.getA113Upload(ISEG);

        if(upload3 != null){

            if(upload3.equals("F")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload3.equals("T")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload4 = databaseHelper.getA114Upload(ISEG);

        if(upload4 != null){

            if(upload4.equals("F")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload4.equals("T")){

                img4.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload5 = databaseHelper.getA115Upload(ISEG);

        if(upload5 != null){

            if(upload5.equals("F")){

                img5.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload5.equals("T")){

                img5.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload6 = databaseHelper.getA116Upload(ISEG);

        if(upload6 != null){

            if(upload6.equals("F")){

                img6.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload6.equals("T")){

                img6.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

    }

}
