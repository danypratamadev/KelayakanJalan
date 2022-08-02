package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class A2Activity extends AppCompatActivity {

    private LinearLayout A21, A22, A23;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    private ImageButton back;
    private ImageView img1, img2, img3;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);

        init();
        setOnclickListener();
        getUpload();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);

        A21 = (LinearLayout) findViewById(R.id.A21);
        A22 = (LinearLayout) findViewById(R.id.A22);
        A23 = (LinearLayout) findViewById(R.id.A23);
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

    }

    private void setOnclickListener(){

        A21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A2Activity.this, A21Activity.class);
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

        A22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A2Activity.this, A22Activity.class);
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

        A23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A2Activity.this, A23Activity.class);
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

        String upload = databaseHelper.getA21Upload(ISEG);

        if(upload != null){

            if(upload.equals("F")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload.equals("T")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload2 = databaseHelper.getA22Upload(ISEG);

        if(upload2 != null){

            if(upload2.equals("F")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload2.equals("T")){

                img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

        String upload3 = databaseHelper.getA23Upload(ISEG);

        if(upload3 != null){

            if(upload3.equals("F")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload3.equals("T")){

                img3.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

    }

}
