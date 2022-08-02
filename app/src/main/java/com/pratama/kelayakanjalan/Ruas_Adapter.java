package com.pratama.kelayakanjalan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.github.ybq.android.spinkit.SpinKitView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Ruas_Adapter extends RecyclerView.Adapter<Ruas_Adapter.RuasViewHolder> {

    private RuasActivity ruasActivity;
    private List<Ruas_Class> mdataList;
    private Dialog myDialog;
    private DatabaseHelper databaseHelper;
    private Boolean hasil = false;
    private String IRU_TXT;
    private int HPS_TXT;
    private Boolean connected;

    public static class RuasViewHolder extends RecyclerView.ViewHolder {
        private TextView NRU, PJL, NOR, PRU, PKK, KM, KTA, CAP;
        private CardView back_ruas;
        private Button edit, hapus, upload;
        private View indikator;
        private SpinKitView spinKitView;

        public RuasViewHolder(View itemView) {
            super(itemView);

            NRU = (TextView) itemView.findViewById(R.id.NRU);
            PJL = (TextView) itemView.findViewById(R.id.PJL);
            NOR = (TextView) itemView.findViewById(R.id.NOR);
            PRU = (TextView) itemView.findViewById(R.id.PRU);
            PKK = (TextView) itemView.findViewById(R.id.PKK);
            KM = (TextView) itemView.findViewById(R.id.KM);
            KTA = (TextView) itemView.findViewById(R.id.KTA);
            CAP = (TextView) itemView.findViewById(R.id.caption);
            indikator = (View) itemView.findViewById(R.id.indicator);

            edit = (Button) itemView.findViewById(R.id.edit);
            hapus = (Button) itemView.findViewById(R.id.hapus);
            upload = (Button) itemView.findViewById(R.id.upload);
            spinKitView = (SpinKitView) itemView.findViewById(R.id.spin_kit);
            back_ruas = (CardView) itemView.findViewById(R.id.back_ruas);

        }
    }

    public Ruas_Adapter(RuasActivity ruasActivity, List<Ruas_Class> dataList) {
        this.ruasActivity = ruasActivity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public RuasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ruas_layout, parent, false);
        RuasViewHolder rsu = new RuasViewHolder(view);
        return rsu;

    }

    @Override
    public void onBindViewHolder(@NonNull final RuasViewHolder holder, final int position) {

        final Ruas_Class currentItem = mdataList.get(position);
        myDialog = new Dialog(ruasActivity);
        databaseHelper = new DatabaseHelper(ruasActivity);
        holder.spinKitView.setVisibility(View.GONE);

        holder.NRU.setText("RUAS : "+ currentItem.getNRU().toUpperCase());
        holder.PJL.setText("Balai : "+ currentItem.getPJL());
        holder.NOR.setText("Nomor Ruas : "+ currentItem.getNOR());
        holder.PRU.setText(currentItem.getPRU() + " KM");
        holder.PKK.setText("PPK : "+ databaseHelper.getNamaPPK(currentItem.getIDPPK()));
        holder.KM.setText("KM "+ currentItem.getDKM() + " - " + currentItem.getKKM());
        holder.KTA.setText("Provinsi : "+ databaseHelper.getNamaProv(currentItem.getIDPRO()).toUpperCase());

        if(currentItem.getUPLOAD().equals("F")){
            holder.indikator.setBackground(ruasActivity.getResources().getDrawable(R.drawable.color_befor));
        } else {
            holder.indikator.setBackground(ruasActivity.getResources().getDrawable(R.drawable.color_after));
            holder.upload.setVisibility(View.GONE);
        }

        holder.back_ruas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ruasActivity.pos = position;
                if(currentItem.getPJL() == null){
                    Intent intent = new Intent(ruasActivity, IdentitasActivity.class);
                    intent.putExtra("op", "Edit");
                    intent.putExtra("iru", currentItem.getIRU());
                    intent.putExtra("pjl", currentItem.getPJL());
                    intent.putExtra("pkk", databaseHelper.getNamaPPK(currentItem.getIDPPK()));
                    intent.putExtra("pro", databaseHelper.getNamaProv(currentItem.getIDPRO()));
                    intent.putExtra("nru", currentItem.getNRU());
                    intent.putExtra("nor", currentItem.getNOR());
                    intent.putExtra("pru", currentItem.getPRU());
                    intent.putExtra("dkm", currentItem.getDKM());
                    intent.putExtra("kkm", currentItem.getKKM());
                    intent.putExtra("kcp", currentItem.getKCP());
                    intent.putExtra("sjr", databaseHelper.getSistemJaringan(currentItem.getIDKLAS()));
                    intent.putExtra("sts", databaseHelper.getStatus(currentItem.getIDKLAS()));
                    intent.putExtra("fng", databaseHelper.getFungsi(currentItem.getIDKLAS()));
                    intent.putExtra("kpr", databaseHelper.getKelasPrasarana(currentItem.getIDKLAS()));
                    intent.putExtra("kpg", databaseHelper.getKelasPenggunaan(currentItem.getIDKLAS()));
                    intent.putExtra("mjl", databaseHelper.getMedanJalan(currentItem.getIDKLAS()));
                    ruasActivity.startActivity(intent);
                    ruasActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    Intent intent = new Intent(ruasActivity, SegmenActivity.class);
                    intent.putExtra("iru", currentItem.getIRU());
                    intent.putExtra("pjl", currentItem.getPJL());
                    intent.putExtra("nru", currentItem.getNRU());
                    intent.putExtra("nor", currentItem.getNOR());
                    intent.putExtra("pru", currentItem.getPRU());
                    intent.putExtra("sjr", databaseHelper.getSistemJaringan(currentItem.getIDKLAS()));
                    intent.putExtra("fng", databaseHelper.getFungsi(currentItem.getIDKLAS()));
                    intent.putExtra("kpr", databaseHelper.getKelasPrasarana(currentItem.getIDKLAS()));
                    intent.putExtra("mjl", databaseHelper.getMedanJalan(currentItem.getIDKLAS()));
                    intent.putExtra("kcp", currentItem.getKCP());
                    ruasActivity.startActivity(intent);
                    ruasActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ruasActivity.pos = position;
                Intent intent = new Intent(ruasActivity, IdentitasActivity.class);
                intent.putExtra("op", "Edit");
                intent.putExtra("iru", currentItem.getIRU());
                intent.putExtra("pjl", currentItem.getPJL());
                intent.putExtra("pkk", databaseHelper.getNamaPPK(currentItem.getIDPPK()));
                intent.putExtra("pro", databaseHelper.getNamaProv(currentItem.getIDPRO()));
                intent.putExtra("nru", currentItem.getNRU());
                intent.putExtra("nor", currentItem.getNOR());
                intent.putExtra("pru", currentItem.getPRU());
                intent.putExtra("dkm", currentItem.getDKM());
                intent.putExtra("kkm", currentItem.getKKM());
                intent.putExtra("kcp", currentItem.getKCP());
                intent.putExtra("sjr", databaseHelper.getSistemJaringan(currentItem.getIDKLAS()));
                intent.putExtra("sts", databaseHelper.getStatus(currentItem.getIDKLAS()));
                intent.putExtra("fng", databaseHelper.getFungsi(currentItem.getIDKLAS()));
                intent.putExtra("kpr", databaseHelper.getKelasPrasarana(currentItem.getIDKLAS()));
                intent.putExtra("kpg", databaseHelper.getKelasPenggunaan(currentItem.getIDKLAS()));
                intent.putExtra("mjl", databaseHelper.getMedanJalan(currentItem.getIDKLAS()));
                ruasActivity.startActivity(intent);
                ruasActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button no, yes;
                final TextView title, sub_title, NRU;
                final ImageView icon;
                final LoadingDots load;
                final RelativeLayout line;
                myDialog.setContentView(R.layout.delete_layout);
                title = (TextView) myDialog.findViewById(R.id.judul);
                sub_title = (TextView) myDialog.findViewById(R.id.sub_judul);
                icon = (ImageView) myDialog.findViewById(R.id.image);
                load = (LoadingDots) myDialog.findViewById(R.id.load);
                no = (Button) myDialog.findViewById(R.id.no);
                yes = (Button) myDialog.findViewById(R.id.yes);
                NRU = (TextView) myDialog.findViewById(R.id.NRU);
                line = (RelativeLayout) myDialog.findViewById(R.id.line);

                load.setVisibility(View.GONE);
                icon.setVisibility(View.GONE);
                NRU.setText(currentItem.getNRU());
                IRU_TXT = currentItem.getIRU();
                HPS_TXT = 1;

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(hasil != true){
                            title.setText("Deleting");
                            sub_title.setText("Mohon tunggu...");
                            no.setVisibility(View.GONE);
                            NRU.setVisibility(View.GONE);
                            line.setVisibility(View.GONE);
                            yes.setTextColor(ruasActivity.getResources().getColor(R.color.Red600));
                            yes.setText("Loading");
                            load.setVisibility(View.VISIBLE);
                            yes.setEnabled(false);

                            try{
                                databaseHelper.deleteDataRuas(IRU_TXT, HPS_TXT, "F");
                            } catch (Exception ex){
                                Log.e("Error Delete : ", ex.toString());
                            }

                            if(ruasActivity.connected == true){
                                new updateRuasJalan().execute((Void[])null);
                            }

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    title.setText("Success");
                                    sub_title.setText("Data berhasil dihapus");
                                    load.setVisibility(View.GONE);
                                    icon.setVisibility(View.VISIBLE);
                                    yes.setText("Done");
                                    hasil = true;
                                    yes.setEnabled(true);
                                }
                            }, 2000);

                        } else {
                            ruasActivity.ruasArrayList.clear();

                            Context context = ruasActivity.ruas_recycler.getContext();
                            LayoutAnimationController controller = null;
                            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                            ruasActivity.ruasArrayList.addAll(databaseHelper.allDataRuas(currentItem.getIDPRO()));

                            if(ruasActivity.ruasArrayList.isEmpty()){
                                ruasActivity.filter.setVisibility(View.GONE);
                                ruasActivity.alert.setVisibility(View.VISIBLE);
                                myDialog.dismiss();
                            } else {
                                ruasActivity.ruas_adapter = new Ruas_Adapter(ruasActivity, ruasActivity.ruasArrayList);
                                ruasActivity.ruas_recycler.setAdapter(ruasActivity.ruas_adapter);
                                ruasActivity.ruas_recycler.setNestedScrollingEnabled(false);
                                ruasActivity.ruas_recycler.setLayoutAnimation(controller);
                                ruasActivity.ruas_recycler.getAdapter().notifyDataSetChanged();
                                ruasActivity.ruas_recycler.scheduleLayoutAnimation();
                                hasil = false;
                                myDialog.dismiss();
                            }
                        }

                    }
                });

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;

                ConnectivityManager connectivityManager = (ConnectivityManager)ruasActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    connected = true;
                    myDialog.show();
                }
                else {
                    connected = false;
                    Toast.makeText(ruasActivity, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class updateDataKlas extends AsyncTask<Void, Void, Void>{

                    @Override
                    protected Void doInBackground(Void... voids) {

                        try {

                            Class.forName("com.mysql.jdbc.Driver");

                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                            String user = "u6887969_admin_kelaikanjalan";
                            String password = "pr0yek4ndr01d";

                            Connection conn = DriverManager.getConnection(url, user, password);

                            Statement statement = conn.createStatement();

                            String sjr = databaseHelper.getSistemJaringan(currentItem.getIDKLAS());
                            String sts = databaseHelper.getStatus(currentItem.getIDKLAS());
                            String fng = databaseHelper.getFungsi(currentItem.getIDKLAS());
                            String kpr = databaseHelper.getKelasPrasarana(currentItem.getIDKLAS());
                            String kpg = databaseHelper.getKelasPenggunaan(currentItem.getIDKLAS());
                            String mjl = databaseHelper.getMedanJalan(currentItem.getIDKLAS());

                            String update = "UPDATE klasifikasi_jalan SET SISTEM_JARINGAN = '"+sjr+"', STATUS = '"+sts+"', FUNGSI = '"+fng+"', " +
                                    "KELAS_PRASARANA = '"+kpr+"', KELAS_PENGGUNAAN = '"+kpg+"', MEDAN = '"+mjl+"', ID_PROPINSI = '"+currentItem.getIDPRO()+"' WHERE ID_KLASIFIKASI = '"+currentItem.getIDKLAS()+"'";

                            statement.executeUpdate(update);

                            databaseHelper.updateDataKlas(currentItem.getIDKLAS(), sjr, sts, fng, kpr, kpg, mjl, "T");

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

                class insertDataKlas extends AsyncTask<Void, Void, Void>{

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        holder.indikator.setVisibility(View.GONE);
                        holder.spinKitView.setVisibility(View.VISIBLE);
                        holder.CAP.setText("Uploading...");
                        holder.CAP.setTextColor(ruasActivity.getResources().getColor(R.color.Blue300));
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

                            String sjr = databaseHelper.getSistemJaringan(currentItem.getIDKLAS());
                            String sts = databaseHelper.getStatus(currentItem.getIDKLAS());
                            String fng = databaseHelper.getFungsi(currentItem.getIDKLAS());
                            String kpr = databaseHelper.getKelasPrasarana(currentItem.getIDKLAS());
                            String kpg = databaseHelper.getKelasPenggunaan(currentItem.getIDKLAS());
                            String mjl = databaseHelper.getMedanJalan(currentItem.getIDKLAS());

                            String insert = "INSERT INTO klasifikasi_jalan (ID_KLASIFIKASI, SISTEM_JARINGAN, STATUS, FUNGSI, KELAS_PRASARANA, KELAS_PENGGUNAAN, MEDAN, ID_PROPINSI) " +
                                    "VALUES ('"+currentItem.getIDKLAS()+"', '"+sjr+"', '"+sts+"', '"+fng+"', '"+kpr+"', '"+kpg+"', '"+mjl+"', '"+currentItem.getIDPRO()+"')";

                            statement.executeUpdate(insert);

                            databaseHelper.updateDataKlas(currentItem.getIDKLAS(), sjr, sts, fng, kpr, kpg, mjl, "T");

                        } catch (Exception ex) {
                            Log.e("Error : ", ex.toString());
                            new updateDataKlas().execute((Void[])null);
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                    }
                }

                class insertDataPpk extends AsyncTask<Void, Void, Void>{

                    @Override
                    protected Void doInBackground(Void... voids) {

                        try {

                            Class.forName("com.mysql.jdbc.Driver");

                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                            String user = "u6887969_admin_kelaikanjalan";
                            String password = "pr0yek4ndr01d";

                            Connection conn = DriverManager.getConnection(url, user, password);

                            Statement statement = conn.createStatement();

                            String ppk = databaseHelper.getNamaPPK(currentItem.getIDPPK());

                            String insert = "INSERT INTO ppk (ID_PPK, NAMA) VALUES ('"+currentItem.getIDPPK()+"', '"+ppk+"')";

                            statement.executeUpdate(insert);

                            databaseHelper.updateDataPPK(currentItem.getIDPPK(), ppk, "T");

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

                class updateRuasJalan2 extends AsyncTask<Void, Void, Void>{

                    @Override
                    protected Void doInBackground(Void... voids) {

                        try {

                            Class.forName("com.mysql.jdbc.Driver");

                            String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                            String user = "u6887969_admin_kelaikanjalan";
                            String password = "pr0yek4ndr01d";

                            Connection conn = DriverManager.getConnection(url, user, password);

                            Statement statement = conn.createStatement();

                            String update = "UPDATE ruas SET BALAI = '"+currentItem.getPJL()+"', NAMA = '"+currentItem.getNRU()+"', PANJANG = '"+currentItem.getPRU()+"', KM_AWAL = '"+currentItem.getDKM()+"', KM_AKHIR = '"+currentItem.getKKM()+"', " +
                                    "KECEPATAN = '"+currentItem.getKCP()+"', HAPUS = '"+currentItem.getHPS()+"', ID_PPK = '"+currentItem.getIDPPK()+"', ID_KLASIFIKASI = '"+currentItem.getIDKLAS()+"', ID_PROPINSI = '"+currentItem.getIDPRO()+"' WHERE ID_RUAS = '"+currentItem.getIRU()+"'";

                            statement.executeUpdate(update);

                            databaseHelper.updateDataRuas(currentItem.getIRU(), currentItem.getPJL(), currentItem.getNRU(), currentItem.getNOR(), currentItem.getPRU(),
                                    currentItem.getDKM(), currentItem.getKKM(), currentItem.getKCP(), currentItem.getHPS(), currentItem.getIDPPK(), currentItem.getIDPRO(), currentItem.getIDKLAS(), "T");

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

                class insertRuasJalan extends AsyncTask<Void, Void, Void>{

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
                                    "VALUES ('"+currentItem.getIRU()+"', '"+currentItem.getPJL()+"', '"+currentItem.getNRU()+"', '"+currentItem.getPRU()+"', '"+currentItem.getDKM()+"', '"+currentItem.getKKM()+"', '"+currentItem.getKCP()+"', '"+currentItem.getHPS()+"', '"+currentItem.getIDPPK()+"', '"+currentItem.getIDKLAS()+"', '"+currentItem.getIDPRO()+"')";

                            statement.executeUpdate(insert);

                            databaseHelper.updateDataRuas(currentItem.getIRU(), currentItem.getPJL(), currentItem.getNRU(), currentItem.getNOR(), currentItem.getPRU(),
                                    currentItem.getDKM(), currentItem.getKKM(), currentItem.getKCP(), currentItem.getHPS(), currentItem.getIDPPK(), currentItem.getIDPRO(), currentItem.getIDKLAS(), "T");

                        } catch (Exception ex) {
                            Log.e("Error : ", ex.toString());
                            new updateRuasJalan2().execute((Void[])null);
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        holder.spinKitView.setVisibility(View.GONE);
                        holder.indikator.setVisibility(View.VISIBLE);
                        holder.indikator.setBackground(ruasActivity.getResources().getDrawable(R.drawable.color_after));
                        holder.CAP.setText("Mulai uji laik fungsi");
                        holder.CAP.setTextColor(ruasActivity.getResources().getColor(R.color.Gray600));
                        Toast.makeText(ruasActivity, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                        holder.upload.setVisibility(View.GONE);
                    }
                }

                ConnectivityManager connectivityManager = (ConnectivityManager)ruasActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    connected = true;
                    new insertDataKlas().execute((Void[])null);
                    new insertDataPpk().execute((Void[])null);
                    new insertRuasJalan().execute((Void[])null);
                }
                else {
                    connected = false;
                    Toast.makeText(ruasActivity, "No internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    public void filterList(List<Ruas_Class> filteredList) {
        mdataList = filteredList;
        notifyDataSetChanged();
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

                String update = "UPDATE ruas SET HAPUS = '"+HPS_TXT+"' WHERE ID_RUAS = '"+IRU_TXT+"'";

                statement.executeUpdate(update);

                databaseHelper.deleteDataRuas(IRU_TXT, HPS_TXT, "T");

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

}
