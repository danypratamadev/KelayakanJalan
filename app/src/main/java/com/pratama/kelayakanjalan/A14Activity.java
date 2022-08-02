package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class A14Activity extends AppCompatActivity {

    private LinearLayout A141;
    private String SJR, FNG, KPR, MJL, NRU, ISEG;
    private ImageButton back;
    private ImageView img1;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a14);

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

        A141 = (LinearLayout) findViewById(R.id.A141);
        back = (ImageButton) findViewById(R.id.back);

        img1 = (ImageView) findViewById(R.id.img1);

    }

    private void onClickListener(){

        A141.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A14Activity.this, A141Activity.class);
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

        String upload = databaseHelper.getA141Upload(ISEG);

        if(upload != null){

            if(upload.equals("F")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_upload_orange_300_18dp));

            } else if(upload.equals("T")){

                img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_done_green_500_18dp));

            }

        }

    }

}
