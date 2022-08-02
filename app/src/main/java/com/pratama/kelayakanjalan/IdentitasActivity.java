package com.pratama.kelayakanjalan;

import android.app.Dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pratama.kelayakanjalan.api.ApiRequestRuas;
import com.pratama.kelayakanjalan.api.Retroserver;
import com.pratama.kelayakanjalan.model.Responmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class IdentitasActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private String KEY_ONE = "Hallo", op;
    private String IRU, ID_KLAS;
    private TextInputLayout TIPJL, TIPKK, TIPRO, TINRU, TINOR, TIPRU, TIDKM, TIKKM, TIKCP;
    public TextInputEditText PJL, PKK, PRO, NRU, NOR, PRU, DKM, KKM, KCP;
    private TextView cap1, cap2, cap3, cap4, cap5, cap6;
    public int HPS_TXT, ID_PPK;
    private Spinner SJR, STS, FNG, KPR, KPG, MJL;
    private Button SAVE;
    private ImageButton back;
    private String PJL_TXT, PKK_TXT, PRO_TXT, NRU_TXT, NOR_TXT, DKM_TXT, KKM_TXT, ID_PRO;
    private double PRU_TXT, KCP_TXT;
    private String SJR_TXT, STS_TXT, FNG_TXT, KPR_TXT, KPG_TXT, MJL_TXT, pro;
    private Dialog myDialog;
    private Boolean hasil = false;
    private int id_ppk;
    public LinearLayout balai_data, pkk_data, pro_data;
    private LinearLayout form_layout;
    private List<Balai_Class> balaiArrayList = new ArrayList<>();
    private List<Ppk_Class> pkkArrayList = new ArrayList<>();
    private List<Pro_Class> proArrayList = new ArrayList<>();
    private RecyclerView balai_recycler, pkk_recycler, pro_recycler;
    private Balai_Adapter adapter;
    private Ppk_Adapter adapter2;
    private Pro_Adapter adapter3;
    private static final String TABEL_SEGMEN = "segmen";
    private static final String ID_RUAS = "iru";
    private static final String ID_SEGMEN = "iseg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identitas);

        init();
        savedInstanceState(savedInstanceState);
        onClickListener();

    }

    private void init(){

        databaseHelper = new DatabaseHelper(this);
        myDialog = new Dialog(this);

        Intent intent = getIntent();
        op = intent.getStringExtra("op");
        IRU = intent.getStringExtra("iru");
        pro = intent.getStringExtra("idpro");

        TIPJL = (TextInputLayout) findViewById(R.id.TIPJL);
        TIPKK = (TextInputLayout) findViewById(R.id.TIPKK);
        TIPRO = (TextInputLayout) findViewById(R.id.TIPROV);
        TINRU = (TextInputLayout) findViewById(R.id.TINRU);
        TINOR = (TextInputLayout) findViewById(R.id.TINOR);
        TIPRU = (TextInputLayout) findViewById(R.id.TIPRU);
        TIDKM = (TextInputLayout) findViewById(R.id.TIDKM);
        TIKKM = (TextInputLayout) findViewById(R.id.TIKKM);
        TIKCP = (TextInputLayout) findViewById(R.id.TIKCP);
        PJL = (TextInputEditText) findViewById(R.id.PJL);
        PKK = (TextInputEditText) findViewById(R.id.PKK);
        PRO = (TextInputEditText) findViewById(R.id.PROV);
        NRU = (TextInputEditText) findViewById(R.id.NRU);
        NOR = (TextInputEditText) findViewById(R.id.NOR);
        PRU = (TextInputEditText) findViewById(R.id.PRU);
        DKM = (TextInputEditText) findViewById(R.id.DKM);
        KKM = (TextInputEditText) findViewById(R.id.KKM);
        KCP = (TextInputEditText) findViewById(R.id.KCP);
        SJR = (Spinner) findViewById(R.id.SJR);
        STS = (Spinner) findViewById(R.id.STS);
        FNG = (Spinner) findViewById(R.id.FNG);
        KPR = (Spinner) findViewById(R.id.KPR);
        KPG = (Spinner) findViewById(R.id.KPG);
        MJL = (Spinner) findViewById(R.id.MJL);
        cap1 = (TextView) findViewById(R.id.cap1);
        cap2 = (TextView) findViewById(R.id.cap2);
        cap3 = (TextView) findViewById(R.id.cap3);
        cap4 = (TextView) findViewById(R.id.cap4);
        cap5 = (TextView) findViewById(R.id.cap5);
        cap6 = (TextView) findViewById(R.id.cap6);
        SAVE = (Button) findViewById(R.id.SAVE);
        back = (ImageButton) findViewById(R.id.back);
        balai_data = (LinearLayout) findViewById(R.id.balai_data);
        pkk_data = (LinearLayout) findViewById(R.id.pkk_data);
        pro_data = (LinearLayout) findViewById(R.id.prov_data);
        form_layout = (LinearLayout) findViewById(R.id.form_layout);
        balai_recycler = (RecyclerView) findViewById(R.id.balai_recycler);
        pkk_recycler = (RecyclerView) findViewById(R.id.pkk_recycler);
        pro_recycler = (RecyclerView) findViewById(R.id.prov_recycler);

        PRO.setText(intent.getStringExtra("pro"));

        if(op.equals("Edit")){
            SAVE.setText("update");
            PJL.setText(intent.getStringExtra("pjl"));
            PKK.setText(intent.getStringExtra("pkk"));
            NRU.setText(intent.getStringExtra("nru"));
            NOR.setText(intent.getStringExtra("nor"));
            NOR.setEnabled(false);
            PRU.setText(String.valueOf(intent.getDoubleExtra("pru", 0)));
            DKM.setText(intent.getStringExtra("dkm"));
            KKM.setText(intent.getStringExtra("kkm"));
            if(intent.getDoubleExtra("kcp", 0) != 0){
                KCP.setText(String.valueOf(intent.getDoubleExtra("kcp", 0)));
            }
            SJR_TXT = intent.getStringExtra("sjr");
            STS_TXT = intent.getStringExtra("sts");
            FNG_TXT = intent.getStringExtra("fng");
            KPR_TXT = intent.getStringExtra("kpr");
            KPG_TXT = intent.getStringExtra("kpg");
            MJL_TXT = intent.getStringExtra("mjl");

            if(SJR_TXT.equals("-")){

            } else {
                SJR.setSelection(((ArrayAdapter<String>)SJR.getAdapter()).getPosition(SJR_TXT));
            }

            if(STS_TXT.equals("-")){

            } else {
                STS.setSelection(((ArrayAdapter<String>)STS.getAdapter()).getPosition(STS_TXT));
            }

            if(FNG_TXT.equals("-")){

            } else {
                FNG.setSelection(((ArrayAdapter<String>)FNG.getAdapter()).getPosition(FNG_TXT));
            }

            if(KPR_TXT.equals("-")){

            } else {
                KPR.setSelection(((ArrayAdapter<String>)KPR.getAdapter()).getPosition(KPR_TXT));
            }

            if(KPG_TXT.equals("-")){

            } else {
                KPG.setSelection(((ArrayAdapter<String>)KPG.getAdapter()).getPosition(KPG_TXT));
            }

            if(MJL_TXT.equals("-")){

            } else {
                MJL.setSelection(((ArrayAdapter<String>)MJL.getAdapter()).getPosition(MJL_TXT));
            }

        } else {

            onTextWatcher();

        }

        PRO.setEnabled(false);
        balai_data.setVisibility(View.GONE);
        pkk_data.setVisibility(View.GONE);
        pro_data.setVisibility(View.GONE);

    }

    private void savedInstanceState(Bundle savedInstanceState){

        if(savedInstanceState != null){
            PJL.setText(savedInstanceState.getString(KEY_ONE));
        }

    }

    private void onTextWatcher(){

        NRU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Boolean hasil = databaseHelper.getNamaRuas(s.toString().toLowerCase());

                if(hasil == true){
                    SAVE.setEnabled(false);
                    TINRU.setErrorEnabled(true);
                    TINRU.setError("Already exists");
                } else {
                    SAVE.setEnabled(true);
                    TINRU.setErrorEnabled(false);
                }
            }
        });

        NOR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Boolean hasil = databaseHelper.getNomorRuas(s.toString(), pro);

                if(hasil == true){
                    SAVE.setEnabled(false);
                    TINOR.setErrorEnabled(true);
                    TINOR.setError("Already exists");
                } else {
                    SAVE.setEnabled(true);
                    TINOR.setErrorEnabled(false);
                }
            }
        });

    }

    private void getInputValue(){

        PJL_TXT = PJL.getText().toString();
        PKK_TXT = PKK.getText().toString();
        PRO_TXT = PRO.getText().toString();
        NRU_TXT = NRU.getText().toString();
        NOR_TXT = NOR.getText().toString();
        PRU_TXT = Double.parseDouble(PRU.getText().toString());
        DKM_TXT = DKM.getText().toString();
        KKM_TXT = KKM.getText().toString();
        KCP_TXT = Double.parseDouble(KCP.getText().toString());
        SJR_TXT = SJR.getSelectedItem().toString();
        STS_TXT = STS.getSelectedItem().toString();;
        FNG_TXT = FNG.getSelectedItem().toString();;
        KPR_TXT = KPR.getSelectedItem().toString();;
        KPG_TXT = KPG.getSelectedItem().toString();;
        MJL_TXT = MJL.getSelectedItem().toString();;

    }

    private void onClickListener(){

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(PJL.getText().toString().equals("") || PKK.getText().toString().equals("") || PRO.getText().toString().equals("") ||
                        NRU.getText().toString().equals("") || NOR.getText().toString().equals("") || PRU.getText().toString().equals("") ||
                        DKM.getText().toString().equals("") || KKM.getText().toString().equals("") || KCP.getText().toString().equals("") ||
                        SJR.getSelectedItem().toString().equals("Pilih salah satu") || STS.getSelectedItem().toString().equals("Pilih salah satu") ||
                        FNG.getSelectedItem().toString().equals("Pilih salah satu") || KPR.getSelectedItem().toString().equals("Pilih salah satu") ||
                        KPG.getSelectedItem().toString().equals("Pilih salah satu") || MJL.getSelectedItem().toString().equals("Pilih salah satu")){

                    if(PJL.getText().toString().equals("")){
                        TIPJL.setError("Masukkan Penyelenggara!");
                    }
                    if(PKK.getText().toString().equals("")){
                        TIPKK.setError("Masukkan PKK!");
                    }
                    if(PRO.getText().toString().equals("")){
                        TIPRO.setError("Masukkan Provinsi!");
                    }
                    if(NRU.getText().toString().equals("")){
                        TINRU.setError("Masukkan Nama Ruas!");
                    }
                    if(NOR.getText().toString().equals("")){
                        TINOR.setError("Masukkan Nomor Ruas!");
                    }
                    if(PRU.getText().toString().equals("")){
                        TIPRU.setError("Masukkan Panjang Ruas!");
                    }
                    if(DKM.getText().toString().equals("")){
                        TIDKM.setError("Masukkan KM awal!");
                    }
                    if(KKM.getText().toString().equals("")){
                        TIKKM.setError("Masukkan KM akhir!");
                    }
                    if(KCP.getText().toString().equals("")){
                        TIKCP.setError("Masukkan Kecepatan!");
                    }
                    if(SJR.getSelectedItem().toString().equals("Pilih salah satu")){
                        cap1.setText("Pilih Sistem Jaringan!");
                        cap1.setTextColor(getResources().getColor(R.color.Orange900));
                    }
                    if(STS.getSelectedItem().toString().equals("Pilih salah satu")){
                        cap2.setText("Pilih Status!");
                        cap2.setTextColor(getResources().getColor(R.color.Orange900));
                    }
                    if(FNG.getSelectedItem().toString().equals("Pilih salah satu")){
                        cap3.setText("Pilih Fungsi!");
                        cap3.setTextColor(getResources().getColor(R.color.Orange900));
                    }
                    if(KPR.getSelectedItem().toString().equals("Pilih salah satu")){
                        cap4.setText("Pilih Kelas Prasarana!");
                        cap4.setTextColor(getResources().getColor(R.color.Orange900));
                    }
                    if(KPG.getSelectedItem().toString().equals("Pilih salah satu")){
                        cap5.setText("Pilih Kelas Penggunaan!");
                        cap5.setTextColor(getResources().getColor(R.color.Orange900));
                    }
                    if(MJL.getSelectedItem().toString().equals("Pilih salah satu")){
                        cap6.setText("Pilih Medan Jalan!");
                        cap6.setTextColor(getResources().getColor(R.color.Orange900));
                    }

                } else {

                    getInputValue();

                    try{

                        Boolean chek_ppk = databaseHelper.allDataPPKWhere(PKK_TXT);

                        if(chek_ppk == false){
                            id_ppk = databaseHelper.getLastId() + 1;
                            Boolean insert_ppk = databaseHelper.insertDataPPK(id_ppk, PKK_TXT, "F");
                            if(insert_ppk != false){
                                new insertDataPpk().execute((Void[])null);
                            }
                        }

                    } catch (Exception ex){
                        Log.e("Error insert : ", ex.toString());
                    }

                    HPS_TXT = 0;
                    ID_PPK = databaseHelper.getIdPPK(PKK_TXT);
                    ID_PRO = databaseHelper.getIdProv(PRO_TXT);

                    if(IRU.equals("-")){
                        IRU = ID_PRO + "." + NOR_TXT;
                    } else {

                    }

                    ID_KLAS = IRU + "_KLS";

                    Boolean resultInRuas = false;
                    Boolean resultUpRuas = false;
                    Boolean resultInKlas = false;
                    Boolean resultUpKlas = false;

                    try{

                        Boolean insertKlas = databaseHelper.insertDataKlas(ID_KLAS, SJR_TXT,
                                STS_TXT, FNG_TXT, KPR_TXT, KPG_TXT, MJL_TXT, "F");

                        if(insertKlas != false){
                            new insertDataKlas().execute((Void[])null);
                            resultInRuas = true;
                        } else {
                            databaseHelper.updateDataKlas(ID_KLAS, SJR_TXT,
                                    STS_TXT, FNG_TXT, KPR_TXT, KPG_TXT, MJL_TXT, "F");
                            new updateDataKlas().execute((Void[])null);
                            resultUpRuas = true;
                        }

                    } catch (Exception ex){
                        Log.e("Error insert : ", ex.toString());
                    }

                    try{

                        Boolean insertRuasJalan = databaseHelper.insertDataRuas(IRU, PJL_TXT, NRU_TXT, NOR_TXT, PRU_TXT,
                                DKM_TXT, KKM_TXT, KCP_TXT, HPS_TXT, ID_PPK, ID_PRO, ID_KLAS, "F");

                        if(insertRuasJalan != false){
                            new insertRuasJalan().execute((Void[])null);
                            resultInKlas = true;
                        } else {
                            databaseHelper.updateDataRuas(IRU, PJL_TXT, NRU_TXT, NOR_TXT, PRU_TXT,
                                    DKM_TXT, KKM_TXT, KCP_TXT, HPS_TXT, ID_PPK, ID_PRO, ID_KLAS, "F");
                            new updateRuasJalan().execute((Void[])null);
                            resultUpKlas = true;

                        }

                    } catch (Exception ex){
                        Log.e("Error insert : ", ex.toString());
                    }

                    try{

                        Boolean hasil = databaseHelper.allDataBalaiWhere(PJL_TXT);

                        if(hasil == false){
                            databaseHelper.insertDataBalai(PJL_TXT);
                        }

                    } catch (Exception ex){
                        Log.e("Error insert : ", ex.toString());
                    }

                    if(resultInKlas == true || resultUpKlas == true || resultInRuas == true || resultUpRuas == true){
                        showPopup();
                        if(op.equals("Edit")){
                            new updateDataPoint().execute((Void[])null);
                        }
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

        PJL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    if(PJL.getText().toString().equals("")){
                        balai_recycler.setHasFixedSize(true);
                        balai_recycler.setLayoutManager(new LinearLayoutManager(IdentitasActivity.this));

                        balaiArrayList.clear();

                        balaiArrayList.addAll(databaseHelper.allDataBalai());

                        adapter = new Balai_Adapter(IdentitasActivity.this, balaiArrayList);

                        balai_recycler.setAdapter(adapter);

                        if(balaiArrayList.isEmpty()){
                            balai_data.setVisibility(View.GONE);
                        } else {
                            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                            balai_data.setVisibility(View.VISIBLE);
                            balai_data.startAnimation(animation);
                        }
                    } else {
                        if(op.equals("Edit")){
                            balai_recycler.setHasFixedSize(true);
                            balai_recycler.setLayoutManager(new LinearLayoutManager(IdentitasActivity.this));

                            balaiArrayList.clear();

                            balaiArrayList.addAll(databaseHelper.allDataBalai());

                            adapter = new Balai_Adapter(IdentitasActivity.this, balaiArrayList);

                            balai_recycler.setAdapter(adapter);

                            if(balaiArrayList.isEmpty()){
                                balai_data.setVisibility(View.GONE);
                            } else {
                                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                                balai_data.setVisibility(View.VISIBLE);
                                balai_data.startAnimation(animation);
                            }

                            filter(PJL.getText().toString());

                        } else {
                            filter(PJL.getText().toString());
                        }
                    }

                } else {
                    balai_data.setVisibility(View.GONE);
                }

            }
        });

        PJL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PJL.getText().toString().equals("")){

                } else {
                    filter(PJL.getText().toString());
                }
            }
        });

        PKK.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    if(PKK.getText().toString().equals("")){
                        pkk_recycler.setHasFixedSize(true);
                        pkk_recycler.setLayoutManager(new LinearLayoutManager(IdentitasActivity.this));

                        pkkArrayList.clear();

                        pkkArrayList.addAll(databaseHelper.allDataPPK());

                        adapter2 = new Ppk_Adapter(IdentitasActivity.this, pkkArrayList);

                        pkk_recycler.setAdapter(adapter2);

                        if(pkkArrayList.isEmpty()){
                            pkk_data.setVisibility(View.GONE);
                        } else {
                            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                            pkk_data.setVisibility(View.VISIBLE);
                            pkk_data.startAnimation(animation);
                        }
                    } else {
                        if(op.equals("Edit")){
                            pkk_recycler.setHasFixedSize(true);
                            pkk_recycler.setLayoutManager(new LinearLayoutManager(IdentitasActivity.this));

                            pkkArrayList.clear();

                            pkkArrayList.addAll(databaseHelper.allDataPPK());

                            adapter2 = new Ppk_Adapter(IdentitasActivity.this, pkkArrayList);

                            pkk_recycler.setAdapter(adapter2);

                            if(pkkArrayList.isEmpty()){
                                pkk_data.setVisibility(View.GONE);
                            } else {
                                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                                pkk_data.setVisibility(View.VISIBLE);
                                pkk_data.startAnimation(animation);
                            }

                            filter2(PKK.getText().toString());
                        } else {
                            filter2(PKK.getText().toString());
                        }
                    }

                } else {
                    pkk_data.setVisibility(View.GONE);
                }

            }
        });

        PKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PKK.getText().toString().equals("")){

                } else {
                    filter2(PKK.getText().toString());
                }
            }
        });

        PRO.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    if(PRO.getText().toString().equals("")){
                        pro_recycler.setHasFixedSize(true);
                        pro_recycler.setLayoutManager(new LinearLayoutManager(IdentitasActivity.this));

                        proArrayList.clear();

                        proArrayList.addAll(databaseHelper.allDataPRO());

                        adapter3 = new Pro_Adapter(IdentitasActivity.this, proArrayList);

                        pro_recycler.setAdapter(adapter3);

                        if(proArrayList.isEmpty()){
                            pro_data.setVisibility(View.GONE);
                        } else {
                            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                            pro_data.setVisibility(View.VISIBLE);
                            pro_data.startAnimation(animation);
                        }
                    } else {
                        if(op.equals("Edit")){
                            pro_recycler.setHasFixedSize(true);
                            pro_recycler.setLayoutManager(new LinearLayoutManager(IdentitasActivity.this));

                            proArrayList.clear();

                            proArrayList.addAll(databaseHelper.allDataPRO());

                            adapter3 = new Pro_Adapter(IdentitasActivity.this, proArrayList);

                            pro_recycler.setAdapter(adapter3);

                            if(proArrayList.isEmpty()){
                                pro_data.setVisibility(View.GONE);
                            } else {
                                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.recycle_bottom);
                                pro_data.setVisibility(View.VISIBLE);
                                pro_data.startAnimation(animation);
                            }

                            filter3(PRO.getText().toString());

                        } else {
                            filter3(PRO.getText().toString());
                        }
                    }

                } else {
                    pro_data.setVisibility(View.GONE);
                }

            }
        });

        PRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PRO.getText().toString().equals("")){

                } else {
                    filter3(PRO.getText().toString());
                }
            }
        });

        form_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balai_data.setVisibility(View.GONE);
                pkk_data.setVisibility(View.GONE);
                pro_data.setVisibility(View.GONE);
            }
        });

        PJL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        PKK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter2(s.toString());
            }
        });

        PRO.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter3(s.toString());
            }
        });

    }

    public void filter(String text) {
        List<Balai_Class> filteredList = new ArrayList<>();
        for (Balai_Class item : balaiArrayList) {
            if(item.getNBL() != null){
                if (item.getNBL().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        adapter.filterList(filteredList);
    }

    public void filter2(String text) {
        List<Ppk_Class> filteredList = new ArrayList<>();
        for (Ppk_Class item : pkkArrayList) {
            if (item.getNPK().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter2.filterList(filteredList);
    }

    public void filter3(String text) {
        List<Pro_Class> filteredList = new ArrayList<>();
        for (Pro_Class item : proArrayList) {
            if (item.getNPRO().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter3.filterList(filteredList);
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

        if(op.equals("Edit")){
            judul.setText("Updating");
        }

        img_success.setVisibility(View.GONE);
        yes.setEnabled(false);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                judul.setText("Success");
                if(op.equals("Edit")){
                    sub_judul.setText("Data berhasil diupdate");
                } else {
                    sub_judul.setText("Data berhasil disimpan");
                }
                load.setVisibility(View.GONE);
                judul.setTextColor(getResources().getColor(R.color.Green500));
                img_success.setVisibility(View.VISIBLE);
                yes.setText("done");
                yes.setTextColor(getResources().getColor(R.color.Green500));
                yes.setEnabled(true);
                hasil = true;

            }
        }, 2500);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 300);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
        myDialog.show();

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

    private class insertRuasJalan extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO ruas (ID_RUAS, BALAI, NAMA, PANJANG, KM_AWAL, KM_AKHIR, KECEPATAN, HAPUS, ID_PPK, ID_KLASIFIKASI, ID_PROPINSI) " +
                        "VALUES ('"+IRU+"', '"+PJL_TXT+"', '"+NRU_TXT+"', '"+PRU_TXT+"', '"+DKM_TXT+"', '"+KKM_TXT+"', '"+KCP_TXT+"', '"+HPS_TXT+"', '"+ID_PPK+"', '"+ID_KLAS+"', '"+ID_PRO+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataRuas(IRU, PJL_TXT, NRU_TXT, NOR_TXT, PRU_TXT,
                        DKM_TXT, KKM_TXT, KCP_TXT, HPS_TXT, ID_PPK, ID_PRO, ID_KLAS, "T");

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

    private class updateRuasJalan extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String update = "UPDATE ruas SET BALAI = '"+PJL_TXT+"', NAMA = '"+NRU_TXT+"', PANJANG = '"+PRU_TXT+"', KM_AWAL = '"+DKM_TXT+"', KM_AKHIR = '"+KKM_TXT+"', " +
                        "KECEPATAN = '"+KCP_TXT+"', HAPUS = '"+HPS_TXT+"', ID_PPK = '"+ID_PPK+"', ID_KLASIFIKASI = '"+ID_KLAS+"', ID_PROPINSI = '"+ID_PRO+"' WHERE ID_RUAS = '"+IRU+"'";

                statement.executeUpdate(update);

                databaseHelper.updateDataRuas(IRU, PJL_TXT, NRU_TXT, NOR_TXT, PRU_TXT,
                        DKM_TXT, KKM_TXT, KCP_TXT, HPS_TXT, ID_PPK, ID_PRO, ID_KLAS, "T");

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

    private class insertDataKlas extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO klasifikasi_jalan (ID_KLASIFIKASI, SISTEM_JARINGAN, STATUS, FUNGSI, KELAS_PRASARANA, KELAS_PENGGUNAAN, MEDAN, ID_PROPINSI) " +
                        "VALUES ('"+ID_KLAS+"', '"+SJR_TXT+"', '"+STS_TXT+"', '"+FNG_TXT+"', '"+KPR_TXT+"', '"+KPG_TXT+"', '"+MJL_TXT+"', '"+ID_PRO+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataKlas(ID_KLAS, SJR_TXT,
                        STS_TXT, FNG_TXT, KPR_TXT, KPG_TXT, MJL_TXT, "T");

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

    private class insertDataPpk extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "INSERT INTO ppk (ID_PPK, NAMA) VALUES ('"+id_ppk+"', '"+PKK_TXT+"')";

                statement.executeUpdate(insert);

                databaseHelper.updateDataPPK(id_ppk, PKK_TXT, "T");

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

    private class updateDataKlas extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String update = "UPDATE klasifikasi_jalan SET SISTEM_JARINGAN = '"+SJR_TXT+"', STATUS = '"+STS_TXT+"', FUNGSI = '"+FNG_TXT+"', " +
                        "KELAS_PRASARANA = '"+KPR_TXT+"', KELAS_PENGGUNAAN = '"+KPG_TXT+"', MEDAN = '"+MJL_TXT+"', ID_PROPINSI = '"+ID_PRO+"' WHERE ID_KLASIFIKASI = '"+ID_KLAS+"'";

                statement.executeUpdate(update);

                databaseHelper.updateDataKlas(ID_KLAS, SJR_TXT,
                        STS_TXT, FNG_TXT, KPR_TXT, KPG_TXT, MJL_TXT, "T");

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
    public void onSaveInstanceState(Bundle onSaveData) {
        onSaveData.putString(KEY_ONE, PJL.getText().toString());
        super.onSaveInstanceState(onSaveData);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {

        if(hasil == false){

            if(PJL.getText().toString().equals("") && PKK.getText().toString().equals("") &&
                    NRU.getText().toString().equals("") && NOR.getText().toString().equals("") &&
                    PRU.getText().toString().equals("") && DKM.getText().toString().equals("") &&
                    KKM.getText().toString().equals("")){

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

    private class updateDataPoint extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            String query = "SELECT " + ID_SEGMEN + " FROM " + TABEL_SEGMEN + " WHERE " + ID_RUAS + " = '" + IRU + "'";
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            //int i = 0;
            String ISEG;
            DecimalFormat df = new DecimalFormat("0.0");
            //A111
            String ID_A111 = "-";
            String FJL_TXT = "-";
            double ALL_TXT = -1, DEV_ALL = -1;
            String KTG_ALL = "-", KTG_KLF1 = "-";
            //A112
            String ID_A112 = "-";
            double LBB_TXT = -1, DEV_LBB = -1, KMB_TXT = -1, DEV_KMB = -1;
            String KTG_LBB = "-", KTG_KMB = "-", KTG_KLF2 = "-";
            //113
            double JAB_TXT, DEV_JAB, LBB_TXT2, DEV_LBB2;
            String KTG_JAB = null, KTG_LBB2 = null, KTG_BKM;

            if(cursor.moveToFirst()){
                do{

                    ISEG = cursor.getString(cursor.getColumnIndex(ID_SEGMEN));

                    //------------------------------------------------------------------------------

                    //A111

                    if(FNG_TXT.equals("Arteri") && SJR_TXT.equals("Primer (Antar Kota)")){
                        FJL_TXT = "Arteri Primer";
                    } else if(FNG_TXT.equals("Kolektor") && SJR_TXT.equals("Primer (Antar Kota)")){
                        FJL_TXT = "Kolektor Primer";
                    } else if(FNG_TXT.equals("Lokal") && SJR_TXT.equals("Primer (Antar Kota)")){
                        FJL_TXT = "Lokal Primer";
                    } else if(FNG_TXT.equals("lingkungan") && SJR_TXT.equals("Primer (Antar Kota)")){
                        FJL_TXT = "Lingkungan Primer";
                    } else if(FNG_TXT.equals("Arteri") && SJR_TXT.equals("Sekunder (Dalam Kota)")){
                        FJL_TXT = "Arteri Sekunder";
                    } else if(FNG_TXT.equals("Kolektor") && SJR_TXT.equals("Sekunder (Dalam Kota)")){
                        FJL_TXT = "Kolektor Sekunder";
                    } else if(FNG_TXT.equals("Lokal") && SJR_TXT.equals("Sekunder (Dalam Kota)")){
                        FJL_TXT = "Lokal Sekunder";
                    } else if(FNG_TXT.equals("lingkungan") && SJR_TXT.equals("Sekunder (Dalam Kota)")){
                        FJL_TXT = "Lingkungan Sekunder";
                    }

                    ID_A111 = databaseHelper.getA111ID(ISEG);
                    ALL_TXT = databaseHelper.getA111ALL(ISEG);

                    if(ALL_TXT != -1){
                        if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)") && MJL_TXT.equals("Datar (D)")){
                            if(ALL_TXT <= 78000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 78000)/78000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)") && MJL_TXT.equals("Bukit (B)")){
                            if(ALL_TXT <= 77000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 77000)/77000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)") && MJL_TXT.equals("Gunung (G)")){
                            if(ALL_TXT <= 73000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 73000)/73000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Raya (JR)") && MJL_TXT.equals("Datar (D)")){
                            if(ALL_TXT <= 61000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 61000)/61000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Raya (JR)") && MJL_TXT.equals("Bukit (B)")){
                            if(ALL_TXT <= 59000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 59000)/59000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Raya (JR)") && MJL_TXT.equals("Gunung (G)")){
                            if(ALL_TXT <= 58000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 58000)/58000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Sedang (JS)") && MJL_TXT.equals("Datar (D)")){
                            if(ALL_TXT <= 22000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 22000)/22000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Sedang (JS)") && MJL_TXT.equals("Bukit (B)")){
                            if(ALL_TXT <= 21500){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 21500)/21500;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Sedang (JS)") && MJL_TXT.equals("Gunung (G)")){
                            if(ALL_TXT <= 20800){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 20800)/20800;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Kecil (JK)") && MJL_TXT.equals("Datar (D)")){
                            if(ALL_TXT <= 17000){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 17000)/17000;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Kecil (JK)") && MJL_TXT.equals("Bukit (B)")){
                            if(ALL_TXT <= 16300){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 16300)/16300;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        } else if(KPR_TXT.equals("Jalan Kecil (JK)") && MJL_TXT.equals("Gunung (G)")){
                            if(ALL_TXT <= 15800){
                                DEV_ALL = 0;
                                KTG_ALL = "LF";
                            } else {
                                DEV_ALL = (ALL_TXT - 15800)/15800;
                                DEV_ALL = (DEV_ALL * 100);
                                if(DEV_ALL < 0){
                                    DEV_ALL = DEV_ALL * -1;
                                }
                                if(DEV_ALL > 100){
                                    DEV_ALL = 100;
                                }
                                DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                                KTG_ALL = "LS";
                            }
                        }

                    }

                    if(ALL_TXT != -1){
                        if(KTG_ALL == "LF" || KTG_ALL == "-"){
                            KTG_KLF1 = "LF";
                        } else {
                            KTG_KLF1 = "LS";
                        }

                        try{

                            Class.forName("com.mysql.jdbc.Driver");

                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                            String user = "u6887969_admin_kelaikanjalan";
                            String password = "pr0yek4ndr01d";

                            Connection conn = DriverManager.getConnection(url, user, password);

                            Statement statement = conn.createStatement();

                            String insert = "UPDATE A111 SET UJI_A111_a = '"+FJL_TXT+"', UJI_A111_b = '"+ALL_TXT+"', " +
                                    "DEV_A111_b = '"+DEV_ALL+"', KTG_A111_b = '"+KTG_ALL+"', KTG_A111 = '"+KTG_KLF1+"' WHERE ID_A111 = '"+ID_A111+"'";

                            statement.executeUpdate(insert);

                            databaseHelper.updateDataA111Iden(ID_A111, FJL_TXT, "T");

                        } catch (Exception e){
                            Log.e("Error upload ", e.toString());
                            databaseHelper.updateDataA111Iden(ID_A111, FJL_TXT, "F");
                        }
                    }

                    //------------------------------------------------------------------------------

                    //A112

                    ID_A112 = databaseHelper.getA112ID(ISEG);

                    LBB_TXT = databaseHelper.getA112LBB(ISEG);

                    if(LBB_TXT != -1){
                        if(SJR_TXT.equals("Primer (Antar Kota)")){
                            if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)") && MJL_TXT.equals("Datar (D)")){
                                if(LBB_TXT >= 3.5){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 3.5)/3.5;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)") && MJL_TXT.equals("Bukit (B)")){
                                if(LBB_TXT >= 2.5){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 2.5)/2.5;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)") && MJL_TXT.equals("Gunung (G)")){
                                if(LBB_TXT >= 2){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 2)/2;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Raya (JR)") && MJL_TXT.equals("Datar (D)")){
                                if(LBB_TXT >= 2){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 2)/2;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Raya (JR)") && MJL_TXT.equals("Bukit (B)")){
                                if(LBB_TXT >= 1.5){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1.5)/1.5;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Raya (JR)") && MJL_TXT.equals("Gunung (G)")){
                                if(LBB_TXT >= 1){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1)/1;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Sedang (JS)") && MJL_TXT.equals("Datar (D)")){
                                if(LBB_TXT >= 1){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1)/1;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Sedang (JS)") && MJL_TXT.equals("Bukit (B)")){
                                if(LBB_TXT >= 1){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1)/1;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Sedang (JS)") && MJL_TXT.equals("Gunung (G)")){
                                if(LBB_TXT >= 0.5){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 0.5)/0.5;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Kecil (JK)") && MJL_TXT.equals("Datar (D)")){
                                if(LBB_TXT >= 1){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1)/1;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Kecil (JK)") && MJL_TXT.equals("Bukit (B)")){
                                if(LBB_TXT >= 1){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1)/1;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Kecil (JK)") && MJL_TXT.equals("Gunung (G)")){
                                if(LBB_TXT >= 0.5){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 0.5)/0.5;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            }

                        } else {

                            if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)")){
                                if(LBB_TXT >= 2.5){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 2.5)/2.5;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Raya (JR)")){
                                if(LBB_TXT >= 2){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 2)/2;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Sedang (JS)")){
                                if(LBB_TXT >= 1.5){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1.5)/1.5;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            } else if(KPR_TXT.equals("Jalan Kecil (JK)")){
                                if(LBB_TXT >= 1){
                                    DEV_LBB = 0;
                                    KTG_LBB = "LF";
                                } else {
                                    DEV_LBB = (LBB_TXT - 1)/1;
                                    DEV_LBB = (DEV_LBB * 100);
                                    if(DEV_LBB < 0){
                                        DEV_LBB = DEV_LBB * -1;
                                    }
                                    if(DEV_LBB > 100){
                                        DEV_LBB = 100;
                                    }
                                    DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                                    KTG_LBB = "LS";
                                }
                            }

                        }
                    }

                    if(LBB_TXT != -1){
                        if(KTG_LBB == "LF" || KTG_LBB == "-"){
                            KTG_KLF2 = "LF";
                        } else {
                            KTG_KLF2 = "LS";
                        }

                        try {

                            Class.forName("com.mysql.jdbc.Driver");

                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                            String user = "u6887969_admin_kelaikanjalan";
                            String password = "pr0yek4ndr01d";

                            Connection conn = DriverManager.getConnection(url, user, password);

                            Statement statement = conn.createStatement();

                            String insert = "UPDATE A112 SET UJI_A112_a = '"+LBB_TXT+"', DEV_A112_a = '"+DEV_LBB+"', KTG_A112_a = '"+KTG_LBB+"'," +
                                    "KTG_A112 = '"+KTG_KLF2+"' WHERE ID_A112 = '"+ID_A112+"'";

                            statement.executeUpdate(insert);

                            databaseHelper.updateDataA112Iden(ID_A112, "T");

                        } catch (Exception ex) {
                            Log.e("Error : ", ex.toString());
                            databaseHelper.updateDataA112Iden(ID_A112, "F");
                        }
                    }

                    KMB_TXT = databaseHelper.getA112KMB(ISEG);

                    if(KMB_TXT != -1){
                        if(KPR_TXT.equals("Jalan Bebas Hambatan (JBH)")){
                            if(KMB_TXT >= 3 && KMB_TXT <= 5){
                                DEV_KMB = 0;
                                KTG_KMB = "LF";
                            } else{
                                if(KMB_TXT < 3){
                                    DEV_KMB = (KMB_TXT - 3)/3;
                                } else {
                                    DEV_KMB = (KMB_TXT - 5)/5;
                                }
                                DEV_KMB = (DEV_KMB * 100);
                                if(DEV_KMB < 0){
                                    DEV_KMB = DEV_KMB * -1;
                                }
                                if(DEV_KMB > 100){
                                    DEV_KMB = 100;
                                }
                                DEV_KMB = Double.parseDouble(df.format(DEV_KMB).replace(",", "."));
                                KTG_KMB = "LS";
                            }
                        } else {
                            if(KMB_TXT >= 3 && KMB_TXT <= 6){
                                DEV_KMB = 0;
                                KTG_KMB = "LF";
                            } else{
                                if(KMB_TXT < 3){
                                    DEV_KMB = (KMB_TXT - 3)/3;
                                } else {
                                    DEV_KMB = (KMB_TXT - 6)/6;
                                }
                                DEV_KMB = (DEV_KMB * 100);
                                if(DEV_KMB < 0){
                                    DEV_KMB = DEV_KMB * -1;
                                }
                                if(DEV_KMB > 100){
                                    DEV_KMB = 100;
                                }
                                DEV_KMB = Double.parseDouble(df.format(DEV_KMB).replace(",", "."));
                                KTG_KMB = "LS";
                            }
                        }
                    }

                    if(KMB_TXT != -1){
                        if(KTG_KMB == "LF" || KTG_KMB == "-"){
                            KTG_KLF2 = "LF";
                        } else {
                            KTG_KLF2 = "LS";
                        }

                        try {

                            Class.forName("com.mysql.jdbc.Driver");

                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                            String user = "u6887969_admin_kelaikanjalan";
                            String password = "pr0yek4ndr01d";

                            Connection conn = DriverManager.getConnection(url, user, password);

                            Statement statement = conn.createStatement();

                            String insert = "UPDATE A112 SET UJI_A112_e = '"+KMB_TXT+"', DEV_A112_e = '"+DEV_KMB+"', " +
                                    "KTG_A112_e = '"+KTG_KMB+"', KTG_A112 = '"+KTG_KLF2+"' WHERE ID_A112 = '"+ID_A112+"'";

                            statement.executeUpdate(insert);

                            databaseHelper.updateDataA112Iden(ID_A112, "T");

                        } catch (Exception ex) {
                            Log.e("Error : ", ex.toString());
                            databaseHelper.updateDataA112Iden(ID_A112, "F");
                        }
                    }

                    //------------------------------------------------------------------------------

                    //A113

                    JAB_TXT = databaseHelper.getA113JAB(ISEG);

                    if(JAB_TXT != -1){
                        if(FNG_TXT.equals("Arteri") && SJR_TXT.equals("Primer (Antar Kota)")){

                            if(JAB_TXT >= 5000){
                                DEV_JAB = 0;
                                KTG_JAB = "LF";
                            } else {
                                DEV_JAB = (JAB_TXT - 5000)/5000;
                                DEV_JAB = (DEV_JAB * 100);
                                if(DEV_JAB < 0){
                                    DEV_JAB = DEV_JAB * -1;
                                }
                                if(DEV_JAB > 100){
                                    DEV_JAB = 100;
                                }
                                DEV_JAB = Double.parseDouble(df.format(DEV_JAB).replace(",", "."));
                                KTG_JAB = "LS";
                            }

                        } else if(FNG_TXT.equals("Kolektor") && SJR_TXT.equals("Primer (Antar Kota)")){

                            if(JAB_TXT >= 3000){
                                DEV_JAB = 0;
                                KTG_JAB = "LF";
                            } else {
                                DEV_JAB = (JAB_TXT - 3000)/3000;
                                DEV_JAB = (DEV_JAB * 100);
                                if(DEV_JAB < 0){
                                    DEV_JAB = DEV_JAB * -1;
                                }
                                if(DEV_JAB > 100){
                                    DEV_JAB = 100;
                                }
                                DEV_JAB = Double.parseDouble(df.format(DEV_JAB).replace(",", "."));
                                KTG_JAB = "LS";
                            }

                        } else if(FNG_TXT.equals("Arteri") && SJR_TXT.equals("Sekunder (Dalam Kota)")){

                            if(JAB_TXT >= 500){
                                DEV_JAB = 0;
                                KTG_JAB = "LF";
                            } else {
                                DEV_JAB = (JAB_TXT - 500)/500;
                                DEV_JAB = (DEV_JAB * 100);
                                if(DEV_JAB < 0){
                                    DEV_JAB = DEV_JAB * -1;
                                }
                                if(DEV_JAB > 100){
                                    DEV_JAB = 100;
                                }
                                DEV_JAB = Double.parseDouble(df.format(DEV_JAB).replace(",", "."));
                                KTG_JAB = "LS";
                            }

                        } else if(FNG_TXT.equals("Kolektor") && SJR_TXT.equals("Sekunder (Dalam Kota)")){

                            if(JAB_TXT >= 300){
                                DEV_JAB = 0;
                                KTG_JAB = "LF";
                            } else {
                                DEV_JAB = (JAB_TXT - 300)/300;
                                DEV_JAB = (DEV_JAB * 100);
                                if(DEV_JAB < 0){
                                    DEV_JAB = DEV_JAB * -1;
                                }
                                if(DEV_JAB > 100){
                                    DEV_JAB = 100;
                                }
                                DEV_JAB = Double.parseDouble(df.format(DEV_JAB).replace(",", "."));
                                KTG_JAB = "LS";
                            }

                        }
                    } else {
                        DEV_JAB = -1;
                        KTG_JAB = "-";
                    }

                    LBB_TXT2 = databaseHelper.getA113LBB(ISEG);

                    if(LBB_TXT2 != -1){
                        if(FNG_TXT.equals("Arteri") && SJR_TXT.equals("Primer (Antar Kota)")){

                            if(LBB_TXT2 >= 7){
                                DEV_LBB2 = 0;
                                KTG_LBB2 = "LF";
                            } else {
                                DEV_LBB2 = (LBB_TXT2 - 7)/7;
                                DEV_LBB2 = (DEV_LBB2 * 100);
                                if(DEV_LBB2 < 0){
                                    DEV_LBB2 = DEV_LBB2 * -1;
                                }
                                if(DEV_LBB2 > 100){
                                    DEV_LBB2 = 100;
                                }
                                DEV_LBB2 = Double.parseDouble(df.format(DEV_LBB2).replace(",", "."));
                                KTG_LBB2 = "LS";
                            }

                        } else if(FNG_TXT.equals("Kolektor") && SJR_TXT.equals("Primer (Antar Kota)")){

                            if(LBB_TXT2 >= 4){
                                DEV_LBB2 = 0;
                                KTG_LBB2 = "LF";
                            } else {
                                DEV_LBB2 = (LBB_TXT2 - 4)/4;
                                DEV_LBB2 = (DEV_LBB2 * 100);
                                if(DEV_LBB2 < 0){
                                    DEV_LBB2 = DEV_LBB2 * -1;
                                }
                                if(DEV_LBB2 > 100){
                                    DEV_LBB2 = 100;
                                }
                                DEV_LBB2 = Double.parseDouble(df.format(DEV_LBB2).replace(",", "."));
                                KTG_LBB2 = "LS";
                            }

                        } else if(FNG_TXT.equals("Arteri") && SJR_TXT.equals("Sekunder (Dalam Kota)")){

                            if(LBB_TXT2 >= 4){
                                DEV_LBB2 = 0;
                                KTG_LBB2 = "LF";
                            } else {
                                DEV_LBB2 = (LBB_TXT2 - 4)/4;
                                DEV_LBB2 = (DEV_LBB2 * 100);
                                if(DEV_LBB2 < 0){
                                    DEV_LBB2 = DEV_LBB2 * -1;
                                }
                                if(DEV_LBB2 > 100){
                                    DEV_LBB2 = 100;
                                }
                                DEV_LBB2 = Double.parseDouble(df.format(DEV_LBB2).replace(",", "."));
                                KTG_LBB2 = "LS";
                            }

                        } else if(FNG_TXT.equals("Kolektor") && SJR_TXT.equals("Sekunder (Dalam Kota)")){

                            if(LBB_TXT2 >= 4){
                                DEV_LBB2 = 0;
                                KTG_LBB2 = "LF";
                            } else {
                                DEV_LBB2 = (LBB_TXT2 - 4)/4;
                                DEV_LBB2 = (DEV_LBB2 * 100);
                                if(DEV_LBB2 < 0){
                                    DEV_LBB2 = DEV_LBB2 * -1;
                                }
                                if(DEV_LBB2 > 100){
                                    DEV_LBB2 = 100;
                                }
                                DEV_LBB2 = Double.parseDouble(df.format(DEV_LBB2).replace(",", "."));
                                KTG_LBB2 = "LS";
                            }

                        }
                    } else {
                        DEV_LBB2 = -1;
                        KTG_LBB2 = "-";
                    }

                    if((KTG_JAB.equals("LF") || KTG_JAB.equals("-")) && (KTG_LBB2.equals("LF") || KTG_LBB2.equals("-"))){

                        if(KTG_JAB.equals("-") && KTG_LBB2.equals("-")) {
                            KTG_BKM = "-";
                        } else {
                            KTG_BKM = "LF";
                        }

                    } else {
                        KTG_BKM = "LS";
                    }

                } while (cursor.moveToNext());
            }
            db.close();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
