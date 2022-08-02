package com.pratama.kelayakanjalan;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Segmen_Adapter extends RecyclerView.Adapter<Segmen_Adapter.SegmenViewHolder> {

    private DatabaseHelper databaseHelper;
    private SegmenActivity segmenActivity;
    private List<Segmen_Class> mdataList;
    private String SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, IRU_TXT;
    private double KCP_TXT, PSG_TXT;
    private Dialog myDialog;
    private Boolean hasil = false;
    private String ISEG, SEG_TXT, DPSG_TXT, KPSG_TXT;
    private String ISEG2;
    private String SEG_TXT2;
    private double PSG_TXT2;
    private String DPSG_TXT2;
    private String KPSG_TXT2;
    private String DKT_TXT2;
    private int HPS_TXT;
    private Boolean connected;
    private File mediaStorageDir = null;
    private long fileSize = 0;
    private int progressStatus = 0;

    public static class SegmenViewHolder extends RecyclerView.ViewHolder {
        private TextView SEG, PSG, KM, CAP;
        private CardView back_segmen;
        private View indikator;
        private SpinKitView spinKitView;

        public SegmenViewHolder(View itemView) {
            super(itemView);

            SEG = (TextView) itemView.findViewById(R.id.SEG);
            PSG = (TextView) itemView.findViewById(R.id.PSG);
            KM = (TextView) itemView.findViewById(R.id.KM);
            CAP = (TextView) itemView.findViewById(R.id.caption);
            indikator = (View) itemView.findViewById(R.id.indicator);
            spinKitView = (SpinKitView) itemView.findViewById(R.id.spin_kit);
            back_segmen = (CardView) itemView.findViewById(R.id.back_segmen);

        }
    }

    public Segmen_Adapter(SegmenActivity segmenActivity, List<Segmen_Class> dataList, String sjr, String fng, String kpr, String mjl, String nru, double kcp) {
        this.segmenActivity = segmenActivity;
        this.mdataList = dataList;
        this.SJR_TXT = sjr;
        this.FNG_TXT = fng;
        this.KPR_TXT = kpr;
        this.MJL_TXT = mjl;
        this.NRU_TXT = nru;
        this.KCP_TXT = kcp;
    }

    @NonNull
    @Override
    public SegmenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.segmen_layout, parent, false);
        Segmen_Adapter.SegmenViewHolder seg = new Segmen_Adapter.SegmenViewHolder(view);
        return seg;

    }

    @Override
    public void onBindViewHolder(@NonNull final SegmenViewHolder holder, int position) {

        final Segmen_Class currentItem = mdataList.get(position);

        myDialog = new Dialog(segmenActivity);
        databaseHelper = new DatabaseHelper(segmenActivity);
        holder.spinKitView.setVisibility(View.GONE);

        holder.SEG.setText(currentItem.getSEG());
        holder.PSG.setText(String.valueOf(currentItem.getPSG())+" KM");
        holder.KM.setText("KM "+String.valueOf(currentItem.getDPSG())+" - KM "+String.valueOf(currentItem.getKPSG()));

        ISEG = currentItem.getIDS();
        SEG_TXT = currentItem.getSEG();
        DPSG_TXT = currentItem.getDPSG();
        KPSG_TXT = currentItem.getKPSG();
        PSG_TXT = currentItem.getPSG();
        IRU_TXT = segmenActivity.IRU_TXT;

        if(currentItem.getUPLOAD().equals("F")){
            holder.indikator.setBackground(segmenActivity.getResources().getDrawable(R.drawable.color_befor));
        } else {
            holder.indikator.setBackground(segmenActivity.getResources().getDrawable(R.drawable.color_after));
        }

        holder.back_segmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(segmenActivity, PointActivity.class);
                intent.putExtra("iru", segmenActivity.IRU_TXT);
                intent.putExtra("iseg", currentItem.getIDS());
                intent.putExtra("seg", currentItem.getSEG());
                intent.putExtra("psg", holder.PSG.getText().toString());
                intent.putExtra("sjr", SJR_TXT);
                intent.putExtra("fng", FNG_TXT);
                intent.putExtra("kpr", KPR_TXT);
                intent.putExtra("mjl", MJL_TXT);
                intent.putExtra("nru", NRU_TXT);
                intent.putExtra("kcp", KCP_TXT);
                segmenActivity.startActivity(intent);
                segmenActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        holder.back_segmen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager)segmenActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    connected = true;
                }
                else {
                    connected = false;
                }

                Bottom_Sheet_Prov bottomSheetProv = new Bottom_Sheet_Prov();
                bottomSheetProv.NMSEG = currentItem.getSEG();
                bottomSheetProv.ID = currentItem.getIDS();
                bottomSheetProv.HPS = currentItem.getHPS();
                bottomSheetProv.UPL = currentItem.getUPLOAD();
                bottomSheetProv.PSG = currentItem.getPSG();
                bottomSheetProv.DPSG = currentItem.getDPSG();
                bottomSheetProv.KPSG = currentItem.getKPSG();
                bottomSheetProv.DKT = currentItem.getDKT();
                bottomSheetProv.IRU = currentItem.getIRU();
                bottomSheetProv.CONN = connected;
                bottomSheetProv.OP = "SEG";
                bottomSheetProv.show(segmenActivity.getSupportFragmentManager(), "Show");

                /*Context wrapper = new ContextThemeWrapper(segmenActivity, R.style.MyPopupStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, holder.back_segmen);
                popupMenu.inflate(R.menu.option_menu2);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                final TextInputLayout TISEG, TIPSG, TIDPSG, TIKPSG, TIDKT;
                                final TextInputEditText SEG, PSG, DPSG, KPSG, DKT;
                                final LoadingDots load;
                                final Button yes, no;
                                final RelativeLayout line;
                                final ImageView img_success;
                                final TextView label;
                                myDialog.setContentView(R.layout.add_segmen);
                                TISEG = (TextInputLayout) myDialog.findViewById(R.id.TISEG);
                                TIPSG = (TextInputLayout) myDialog.findViewById(R.id.TIPSG);
                                TIDPSG = (TextInputLayout) myDialog.findViewById(R.id.TIDPSG);
                                TIKPSG = (TextInputLayout) myDialog.findViewById(R.id.TIKPSG);
                                TIDKT = (TextInputLayout) myDialog.findViewById(R.id.TIDKT);
                                SEG = (TextInputEditText) myDialog.findViewById(R.id.SEG);
                                PSG = (TextInputEditText) myDialog.findViewById(R.id.PSG);
                                DPSG = (TextInputEditText) myDialog.findViewById(R.id.DPSG);
                                KPSG = (TextInputEditText) myDialog.findViewById(R.id.KPSG);
                                DKT = (TextInputEditText) myDialog.findViewById(R.id.DKT);
                                label = (TextView) myDialog.findViewById(R.id.label);
                                load = (LoadingDots) myDialog.findViewById(R.id.load);
                                yes = (Button) myDialog.findViewById(R.id.yes);
                                no = (Button) myDialog.findViewById(R.id.no);
                                line = (RelativeLayout) myDialog.findViewById(R.id.line);
                                img_success = (ImageView) myDialog.findViewById(R.id.img_success);

                                SEG.setText(currentItem.getSEG());
                                SEG.setEnabled(false);
                                PSG.setText(String.valueOf(currentItem.getPSG()));
                                DPSG.setText(currentItem.getDPSG());
                                KPSG.setText(currentItem.getKPSG());
                                DKT.setText(currentItem.getDKT());

                                load.setVisibility(View.GONE);
                                img_success.setVisibility(View.GONE);
                                label.setText("EDIT SEGMEN "+currentItem.getSEG());
                                yes.setText("edit");

                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (SEG.getText().toString().equals("") || PSG.getText().toString().equals("") ||
                                                DPSG.getText().toString().equals("") || KPSG.getText().toString().equals("")) {
                                            if(SEG.getText().toString().equals("")){
                                                TISEG.setError("Masukkan Segmen!");
                                            }
                                            if(PSG.getText().toString().equals("")){
                                                TIPSG.setError("Masukkan Panjang!");
                                            }
                                            if(DPSG.getText().toString().equals("")){
                                                TIDPSG.setError("Masukkan Km Awal!");
                                            }
                                            if(KPSG.getText().toString().equals("")){
                                                TIKPSG.setError("Masukkan Km Akhir!");
                                            }
                                            if(DKT.getText().toString().equals("")){
                                                TIDKT.setError("Masukkan Kota!");
                                            }
                                        } else {
                                            SEG_TXT2 = SEG.getText().toString();
                                            PSG_TXT2 = Float.parseFloat(PSG.getText().toString());
                                            DPSG_TXT2 = DPSG.getText().toString();
                                            KPSG_TXT2 = KPSG.getText().toString();
                                            DKT_TXT2 = DKT.getText().toString();
                                            ISEG2 = currentItem.getIDS();
                                            HPS_TXT = currentItem.getHPS();
                                            IRU_TXT = segmenActivity.IRU_TXT;

                                            try {

                                                updateDataSegmen(ISEG2, IRU_TXT, SEG_TXT2, PSG_TXT2, DPSG_TXT2,
                                                        KPSG_TXT2, DKT_TXT2, HPS_TXT, "F");

                                            } catch (Exception ex){

                                                Log.e("Error update : ", ex.toString());

                                            }

                                            if(segmenActivity.connected == true){
                                                new updateDataSegmen().execute((Void[])null);
                                            }

                                            load.setVisibility(View.VISIBLE);
                                            TISEG.setVisibility(View.GONE);
                                            TIPSG.setVisibility(View.GONE);
                                            TIDPSG.setVisibility(View.GONE);
                                            TIKPSG.setVisibility(View.GONE);
                                            TIDKT.setVisibility(View.GONE);
                                            no.setVisibility(View.GONE);
                                            line.setVisibility(View.GONE);
                                            yes.setText("is updating...");
                                            yes.setEnabled(false);
                                            hasil = true;

                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    load.setVisibility(View.GONE);
                                                    img_success.setVisibility(View.VISIBLE);
                                                    no.setVisibility(View.VISIBLE);
                                                    no.setText("done");
                                                    no.setTextColor(segmenActivity.getResources().getColor(R.color.WindowBgActiveDark));
                                                    yes.setVisibility(View.GONE);
                                                }
                                            }, 2000);

                                        }
                                    }
                                });

                                no.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(hasil == true){

                                            segmenActivity.segmenArrayList.clear();

                                            Context context = segmenActivity.segmen_recycler.getContext();
                                            LayoutAnimationController controller = null;
                                            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                                            segmenActivity.segmenArrayList.addAll(databaseHelper.allDataSegmen(segmenActivity.IRU_TXT, NRU_TXT));

                                            if(segmenActivity.segmenArrayList.isEmpty()){
                                                segmenActivity.alert.setVisibility(View.VISIBLE);
                                            } else {
                                                segmenActivity.alert.setVisibility(View.GONE);
                                            }

                                            segmenActivity.segmen_adapter = new Segmen_Adapter(segmenActivity, segmenActivity.segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                                            segmenActivity.segmen_recycler.setAdapter(segmenActivity.segmen_adapter);
                                            segmenActivity.segmen_recycler.setLayoutAnimation(controller);
                                            segmenActivity.segmen_recycler.getAdapter().notifyDataSetChanged();
                                            segmenActivity.segmen_recycler.scheduleLayoutAnimation();
                                            hasil = false;
                                        }
                                        //Toast.makeText(segmenActivity, "Klick", Toast.LENGTH_SHORT).show();
                                        myDialog.dismiss();
                                    }
                                });


                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
                                myDialog.show();
                                break;
                            case R.id.upload:

                                class updateDataSegmen2 extends AsyncTask<Void, Void, Void> {

                                    @Override
                                    protected Void doInBackground(Void... voids) {

                                        try {

                                            Class.forName("com.mysql.jdbc.Driver");

                                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                                            String user = "u6887969_admin_kelaikanjalan";
                                            String password = "pr0yek4ndr01d";

                                            Connection conn = DriverManager.getConnection(url, user, password);

                                            Statement statement = conn.createStatement();

                                            String update = "UPDATE segmen SET SEGMEN = '"+currentItem.getSEG()+"', PANJANG = '"+currentItem.getPSG()+"', " +
                                                    "KM_AWAL = '"+currentItem.getDPSG()+"', KM_AKHIR = '"+currentItem.getKPSG()+"', DARI_KOTA = '"+currentItem.getDKT()+"', KTG_A1 = NULL, " +
                                                    "KTG_A2 = NULL, KTG_A3 = NULL, KTG_A4 = NULL, KTG_A5 = NULL, KTG_A6a = NULL, KTG_A6b = NULL, HAPUS = '"+currentItem.getHPS()+"', " +
                                                    "ID_RUAS = '"+currentItem.getIRU()+"' WHERE ID_SEGMEN = '"+currentItem.getIDS()+"'";

                                            statement.executeUpdate(update);

                                            databaseHelper.updateDataSegmen(currentItem.getIDS(), currentItem.getIRU(), currentItem.getSEG(), currentItem.getPSG(),
                                                    currentItem.getDPSG(), currentItem.getKPSG(), currentItem.getDKT(), currentItem.getHPS(), "T");

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

                                class insertRuasSegmen extends AsyncTask<Void, Void, Void>{

                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        holder.spinKitView.setVisibility(View.VISIBLE);
                                        holder.indikator.setVisibility(View.GONE);
                                        holder.CAP.setText("Uploading...");
                                        holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Blue300));
                                    }

                                    @Override
                                    protected Void doInBackground(Void... voids) {

                                        try {

                                            Class.forName("com.mysql.jdbc.Driver");

                                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                                            String user = "u6887969_admin_kelaikanjalan";
                                            String password = "pr0yek4ndr01d";

                                            Connection conn = DriverManager.getConnection(url, user, password);

                                            Statement statement = conn.createStatement();

                                            String insert = "INSERT INTO segmen (ID_SEGMEN, SEGMEN, PANJANG, KM_AWAL, KM_AKHIR, DARI_KOTA, HAPUS, KTG_A1, KTG_A2, KTG_A3, " +
                                                    "KTG_A4, KTG_A5, KTG_A6a, KTG_A6b, ID_RUAS) VALUES ('"+currentItem.getIDS()+"', '"+currentItem.getSEG()+"', " +
                                                    "'"+currentItem.getPSG()+"', '"+currentItem.getDPSG()+"', '"+currentItem.getKPSG()+"', '"+currentItem.getDKT()+"', " +
                                                    "'"+currentItem.getHPS()+"', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', '"+currentItem.getIRU()+"')";

                                            statement.executeUpdate(insert);

                                            databaseHelper.updateDataSegmen(currentItem.getIDS(), currentItem.getIRU(), currentItem.getSEG(), currentItem.getPSG(),
                                                    currentItem.getDPSG(), currentItem.getKPSG(), currentItem.getDKT(), currentItem.getHPS(), "T");

                                        } catch (Exception ex) {
                                            Log.e("Error : ", ex.toString());
                                            new updateDataSegmen2().execute((Void[])null);
                                        }

                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(Void aVoid) {
                                        super.onPostExecute(aVoid);
                                        holder.spinKitView.setVisibility(View.GONE);
                                        holder.indikator.setVisibility(View.VISIBLE);
                                        holder.indikator.setBackground(segmenActivity.getResources().getDrawable(R.drawable.color_after));
                                        holder.CAP.setText("Long press to show menu");
                                        holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Gray600));
                                        Toast.makeText(segmenActivity, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                ConnectivityManager connectivityManager = (ConnectivityManager)segmenActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                    connected = true;
                                    new insertRuasSegmen().execute((Void[])null);
                                }
                                else {
                                    connected = false;
                                    Toast.makeText(segmenActivity, "No internet connection", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case R.id.unduh:

                                class insertToExcel extends AsyncTask<Void, Void, Void> {

                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        holder.spinKitView.setVisibility(View.VISIBLE);
                                        holder.indikator.setVisibility(View.GONE);
                                        holder.CAP.setText("Downloading...");
                                        holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Blue300));
                                        segmenActivity.alertdownload.setVisibility(View.VISIBLE);
                                        final Animation animation = AnimationUtils.loadAnimation(segmenActivity.getApplicationContext(), R.anim.slide_up2);
                                        segmenActivity.alertdownload.setAnimation(animation);
                                        segmenActivity.NMA_FILE.setText("Downloading Laporan ruas "+segmenActivity.NRU_TXT.toLowerCase()+" segmen "+currentItem.getSEG()+".xls");
                                    }

                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    protected Void doInBackground(Void... voids) {

                                        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+segmenActivity.NRU_TXT+"/Segmen "+currentItem.getSEG()+"/Excel");
                                        if(!folder.exists()){
                                            folder.mkdirs();
                                        }

                                        String state = Environment.getExternalStorageState();

                                        if (state.contains(Environment.MEDIA_MOUNTED)) {
                                            mediaStorageDir = new File(Environment
                                                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+segmenActivity.NRU_TXT+"/Segmen "+currentItem.getSEG()+"/Excel", "Laporan ruas "+segmenActivity.NRU_TXT.toLowerCase()+" segmen "+currentItem.getSEG()+".xls");
                                        } else {
                                            mediaStorageDir = new File(Environment
                                                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+segmenActivity.NRU_TXT+"/Segmen "+currentItem.getSEG()+"/Excel", "Laporan ruas "+segmenActivity.NRU_TXT.toLowerCase()+" segmen "+currentItem.getSEG()+".xls");
                                        }

                                        if(!mediaStorageDir.exists()){

                                            try {

                                                InputStream inputStream = new URL("http://proyekjalan.net/template/template.xls").openStream();
                                                Files.copy(inputStream, Paths.get(mediaStorageDir.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                                            } catch (Exception e){
                                                Log.e("Error Download XLS ", e.toString());
                                            }

                                        } else {

                                            for (int num = 1; mediaStorageDir.exists(); num++) {
                                                mediaStorageDir = new File(Environment
                                                        .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+segmenActivity.NRU_TXT+"/Segmen "+currentItem.getSEG()+"/Excel", "Laporan ruas "+segmenActivity.NRU_TXT.toLowerCase()+" segmen "+currentItem.getSEG()+"("+num+").xls");
                                            }

                                            try {

                                                InputStream inputStream = new URL("http://proyekjalan.net/template/template.xls").openStream();
                                                Files.copy(inputStream, Paths.get(mediaStorageDir.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                                            } catch (Exception e){
                                                Log.e("Error copy ", e.toString());
                                            }

                                        }

                                        try {
                                            CreateExcel2 excel = new CreateExcel2(currentItem.getIDS(), mediaStorageDir.getAbsolutePath(), currentItem.getNRU(), currentItem.getSEG());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (BiffException e) {
                                            e.printStackTrace();
                                        } catch (WriteException e) {
                                            e.printStackTrace();
                                        }

                                        return null;

                                    }

                                    @Override
                                    protected void onPostExecute(Void aVoid) {
                                        super.onPostExecute(aVoid);
                                        holder.spinKitView.setVisibility(View.GONE);
                                        holder.indikator.setVisibility(View.VISIBLE);
                                        holder.indikator.setBackground(segmenActivity.getResources().getDrawable(R.drawable.color_after));
                                        holder.CAP.setText("Long press to show menu");
                                        holder.CAP.setTextColor(segmenActivity.getResources().getColor(R.color.Gray600));
                                        final Animation animation = AnimationUtils.loadAnimation(segmenActivity.getApplicationContext(), R.anim.slide_down2);
                                        segmenActivity.alertdownload.setAnimation(animation);
                                        segmenActivity.alertdownload.setVisibility(View.GONE);
                                        Toast.makeText(segmenActivity, "Successfully downloaded", Toast.LENGTH_LONG).show();

                                        String type = getMimeTypeByFile(mediaStorageDir.getAbsolutePath());

                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                                        Uri apkURI = FileProvider.getUriForFile(segmenActivity,segmenActivity.getApplicationContext().getPackageName() + ".provider", mediaStorageDir);

                                        intent.setDataAndType(apkURI, type);
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                                        try {
                                            segmenActivity.startActivity(intent);
                                        }
                                        catch (ActivityNotFoundException e) {
                                            Toast.makeText(segmenActivity, "No Application Available to View Excel",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }

                                ConnectivityManager connectivityManager2 = (ConnectivityManager)segmenActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                                if(connectivityManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                        connectivityManager2.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                    connected = true;
                                    new insertToExcel().execute((Void[])null);
                                }
                                else {
                                    connected = false;
                                    Toast.makeText(segmenActivity, "No internet connection", Toast.LENGTH_SHORT).show();
                                }


                                break;
                            case R.id.delete:
                                final Button no2, yes2;
                                final TextView title, sub_title, NRU;
                                final ImageView icon;
                                final LoadingDots load2;
                                final RelativeLayout line2;
                                myDialog.setContentView(R.layout.delete_layout);
                                title = (TextView) myDialog.findViewById(R.id.judul);
                                sub_title = (TextView) myDialog.findViewById(R.id.sub_judul);
                                icon = (ImageView) myDialog.findViewById(R.id.image);
                                load2 = (LoadingDots) myDialog.findViewById(R.id.load);
                                no2 = (Button) myDialog.findViewById(R.id.no);
                                yes2 = (Button) myDialog.findViewById(R.id.yes);
                                NRU = (TextView) myDialog.findViewById(R.id.NRU);
                                line2 = (RelativeLayout) myDialog.findViewById(R.id.line);

                                load2.setVisibility(View.GONE);
                                icon.setVisibility(View.GONE);
                                ISEG2 = currentItem.getIDS();
                                HPS_TXT = 1;
                                IRU_TXT = currentItem.getIRU();
                                NRU_TXT = currentItem.getNRU();
                                SEG_TXT2 = currentItem.getSEG();
                                PSG_TXT2 = currentItem.getPSG();
                                DPSG_TXT2 = currentItem.getDPSG();
                                KPSG_TXT2 = currentItem.getKPSG();
                                DKT_TXT2 = currentItem.getDKT();
                                NRU.setText("SEGMEN "+currentItem.getSEG());

                                no2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        myDialog.dismiss();
                                    }
                                });

                                yes2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(hasil != true){
                                            title.setText("Deleting");
                                            sub_title.setText("Mohon tunggu...");
                                            no2.setVisibility(View.GONE);
                                            NRU.setVisibility(View.GONE);
                                            line2.setVisibility(View.GONE);
                                            yes2.setTextColor(segmenActivity.getResources().getColor(R.color.Red600));
                                            yes2.setText("Loading");
                                            load2.setVisibility(View.VISIBLE);
                                            yes2.setEnabled(false);

                                            try{
                                                databaseHelper.deleteDataSegmen(ISEG2, HPS_TXT, "F");
                                            } catch (Exception ex){
                                                Log.e("Error Delete Prov : ", ex.toString());
                                            }

                                            if(segmenActivity.connected == true){
                                                new updateDataSegmen().execute((Void[])null);
                                            }

                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    title.setText("Success");
                                                    sub_title.setText("Data berhasil dihapus");
                                                    load2.setVisibility(View.GONE);
                                                    icon.setVisibility(View.VISIBLE);
                                                    yes2.setText("Done");
                                                    hasil = true;
                                                    yes2.setEnabled(true);

                                                }
                                            }, 2000);

                                        } else {
                                            segmenActivity.segmenArrayList.clear();

                                            Context context = segmenActivity.segmen_recycler.getContext();
                                            LayoutAnimationController controller = null;
                                            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                                            segmenActivity.segmenArrayList.addAll(databaseHelper.allDataSegmen(IRU_TXT, NRU_TXT));

                                            segmenActivity.segmen_adapter = new Segmen_Adapter(segmenActivity, segmenActivity.segmenArrayList, SJR_TXT, FNG_TXT, KPR_TXT, MJL_TXT, NRU_TXT, KCP_TXT);
                                            segmenActivity.segmen_recycler.setAdapter(segmenActivity.segmen_adapter);
                                            segmenActivity.segmen_recycler.setNestedScrollingEnabled(false);
                                            segmenActivity.segmen_recycler.setLayoutAnimation(controller);
                                            segmenActivity.segmen_recycler.getAdapter().notifyDataSetChanged();
                                            segmenActivity.segmen_recycler.scheduleLayoutAnimation();
                                            hasil = false;
                                            myDialog.dismiss();
                                        }

                                    }
                                });

                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;

                                ConnectivityManager connectivityManager3 = (ConnectivityManager)segmenActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                                if(connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                        connectivityManager3.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                    connected = true;
                                    myDialog.show();
                                }
                                else {
                                    connected = false;
                                    Toast.makeText(segmenActivity, "No internet connection", Toast.LENGTH_SHORT).show();
                                }

                                break;
                        }
                        return false;
                    }
                });
                try {
                    Field[] fields = popupMenu.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popupMenu);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                popupMenu.setGravity(Gravity.CENTER);
                popupMenu.show();

                ConnectivityManager connectivityManager = (ConnectivityManager)segmenActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    connected = true;
                    if(currentItem.getUPLOAD().equals("T")){
                        popupMenu.getMenu().findItem(R.id.upload).setVisible(false);
                    }
                }
                else {
                    connected = false;
                    popupMenu.getMenu().findItem(R.id.upload).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.unduh).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.delete).setVisible(false);
                }*/

                return true;

            }
        });

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    private Boolean updateDataSegmen(String iseg, String iru, String seg, double psg, String dpsg, String kpsg, String kta, int hps, String upl){

        Boolean insertData = databaseHelper.updateDataSegmen(iseg, iru, seg, psg, dpsg, kpsg, kta, hps, upl);

        if(insertData != false){
            hasil = true;

        } else {
            hasil = false;
        }

        return hasil;

    }

    private class updateDataSegmen extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String update = "UPDATE segmen SET SEGMEN = '"+SEG_TXT2+"', PANJANG = '"+PSG_TXT2+"', " +
                    "KM_AWAL = '"+DPSG_TXT2+"', KM_AKHIR = '"+KPSG_TXT2+"', DARI_KOTA = '"+DKT_TXT2+"', KTG_A1 = NULL, " +
                        "KTG_A2 = NULL, KTG_A3 = NULL, KTG_A4 = NULL, KTG_A5 = NULL, KTG_A6a = NULL, " +
                    "KTG_A6b = NULL, HAPUS = '"+HPS_TXT+"', ID_RUAS = '"+IRU_TXT+"' WHERE ID_SEGMEN = '"+ISEG2+"'";

                statement.executeUpdate(update);

                databaseHelper.updateDataSegmen(ISEG2, IRU_TXT, SEG_TXT2, PSG_TXT2, DPSG_TXT2,
                        KPSG_TXT2, DKT_TXT2, HPS_TXT, "T");

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

    private void insertImageToXls(String imgPath, String imgPath2, String imgPath3) throws IOException, BiffException, WriteException {

        Workbook copy = Workbook.getWorkbook(new File(mediaStorageDir.getAbsolutePath()));

        WritableWorkbook filebaru = Workbook.createWorkbook(new File(mediaStorageDir.getAbsolutePath()), copy);

        WritableSheet sheet = filebaru.getSheet(0); //Sheet1 = 0

        WritableImage image = new WritableImage(
                12, 20, //column, row
                1, 8, //width, height in terms of number of cells
                new File(imgPath)); //Supports only 'png' images

        sheet.addImage(image);

        //insert image again under the previous one
        WritableImage image2 = new WritableImage(
                12, 29, //column, row
                1, 8, //width, height in term.s of number of cells
                new File(imgPath2)); //Supports only 'png' images

        sheet.addImage(image2);

        //insert image again under the previous one
        WritableImage image3 = new WritableImage(
                12, 38, //column, row
                1, 6, //width, height in terms of number of cells
                new File(imgPath3)); //Supports only 'png' images

        sheet.addImage(image3);

        //INSERT TEKS
        // Create cell font and format
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 14);
        cellFont.setBoldStyle(WritableFont.BOLD);
        cellFont.setUnderlineStyle(UnderlineStyle.DOUBLE);

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setFont(cellFont);
        cellFormat.setAlignment(Alignment.CENTRE);

        //tulis teks (label)
        String teks = sheet.getCell(15, 16).getContents(); //baca teks yg sudah ada
        System.out.println(teks);
        Label label = new Label(15, 16, teks, cellFormat);
        sheet.addCell(label);

        //Writes out the data held in this workbook in Excel format
        filebaru.write();

        //Close and free allocated memory
        filebaru.close();

    }

    public String getMimeTypeByFile(String filePath) {
        MimeTypeMap type = MimeTypeMap.getSingleton();
        return type.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(filePath));
    }

}
