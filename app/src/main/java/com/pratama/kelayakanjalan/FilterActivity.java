package com.pratama.kelayakanjalan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Balai_Adapter2 balai_adapter;
    private Ppk_Adapter2 pkk_adapter;
    private List<Balai_Class> balaiArrayList = new ArrayList<>();
    private List<Ppk_Class> pkkArrayList = new ArrayList<>();
    private RecyclerView balai_recycler, pkk_recycler;
    private Button kirim;
    private ImageButton back;
    public String balai = "-", pro = "-";
    public int ppk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        databaseHelper = new DatabaseHelper(this);

        balai_recycler = (RecyclerView) findViewById(R.id.balai_recycler);
        pkk_recycler = (RecyclerView) findViewById(R.id.pkk_recycler);
        kirim = (Button) findViewById(R.id.kirim);
        back = (ImageButton) findViewById(R.id.back);

        balai_recycler.setHasFixedSize(true);
        balai_recycler.setLayoutManager(new GridLayoutManager(this, 2));

        balaiArrayList = databaseHelper.allDataBalai();

        balai_adapter = new Balai_Adapter2(FilterActivity.this, balaiArrayList);

        balai_recycler.setAdapter(balai_adapter);

        pkk_recycler.setHasFixedSize(true);
        pkk_recycler.setLayoutManager(new GridLayoutManager(this, 2));

        pkkArrayList = databaseHelper.allDataPPK();

        pkk_adapter = new Ppk_Adapter2(FilterActivity.this, pkkArrayList);

        pkk_recycler.setAdapter(pkk_adapter);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("balai", balai);
                intent.putExtra("pkk", ppk);
                setResult(RESULT_OK, intent);
                finish();
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

}
