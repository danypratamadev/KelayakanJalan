package com.pratama.kelayakanjalan;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class A56_Adapter extends RecyclerView.Adapter<A56_Adapter.A56ViewHolder> {

    private A56Activity a56Activity;
    private List<A56_Class> mdataList;
    private double DEV_KML, DEV_KML2, DEV_KML3, DEV_LPG, DEV_PHK, DEV_FPC, STD = 100;
    private String KTG_KML, KTG_KML2, KTG_KML3, KTG_KMLL, KTG_LPG, KTG_PHK, KTG_FPC, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A56ViewHolder extends RecyclerView.ViewHolder {

        private TextView kml_data, kml_dev, kml2_data, kml2_dev, kml3_data, kml3_dev, kml_hasil, lpg_data, lpg_dev,
                lpg_hasil, php_data, php_dev, php_hasil, phk_data, phk_dev, phk_hasil, fpc_data, fpc_dev, fpc_hasil, rec_data;

        private LinearLayout kml_back, lpg_back, php_back, phk_back, fpc_back;
        private ImageView FT1, FT2;

        public A56ViewHolder(@NonNull View itemView) {
            super(itemView);

            kml_data = (TextView) itemView.findViewById(R.id.kml_data);
            kml_dev = (TextView) itemView.findViewById(R.id.kml_dev);
            kml2_data = (TextView) itemView.findViewById(R.id.kml2_data);
            kml2_dev = (TextView) itemView.findViewById(R.id.kml2_dev);
            kml3_data = (TextView) itemView.findViewById(R.id.kml3_data);
            kml3_dev = (TextView) itemView.findViewById(R.id.kml3_dev);
            kml_hasil = (TextView) itemView.findViewById(R.id.kml_hasil);

            lpg_data = (TextView) itemView.findViewById(R.id.lpg_data);
            lpg_dev = (TextView) itemView.findViewById(R.id.lpg_dev);
            lpg_hasil = (TextView) itemView.findViewById(R.id.lpg_hasil);

            php_data = (TextView) itemView.findViewById(R.id.php_data);
            php_dev = (TextView) itemView.findViewById(R.id.php_dev);
            php_hasil = (TextView) itemView.findViewById(R.id.php_hasil);

            phk_data = (TextView) itemView.findViewById(R.id.phk_data);
            phk_dev = (TextView) itemView.findViewById(R.id.phk_dev);
            phk_hasil = (TextView) itemView.findViewById(R.id.phk_hasil);

            fpc_data = (TextView) itemView.findViewById(R.id.fpc_data);
            fpc_dev = (TextView) itemView.findViewById(R.id.fpc_dev);
            fpc_hasil = (TextView) itemView.findViewById(R.id.fpc_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kml_back = (LinearLayout) itemView.findViewById(R.id.kml_back);
            lpg_back = (LinearLayout) itemView.findViewById(R.id.lpg_back);
            php_back = (LinearLayout) itemView.findViewById(R.id.php_back);
            phk_back = (LinearLayout) itemView.findViewById(R.id.phk_back);
            fpc_back = (LinearLayout) itemView.findViewById(R.id.fpc_back);

        }

    }

    public A56_Adapter(A56Activity a56Activity, List<A56_Class> mdataList) {
        this.a56Activity = a56Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A56_Adapter.A56ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a56_layout, parent, false);
        A56_Adapter.A56ViewHolder a56 = new A56_Adapter.A56ViewHolder(view);
        return a56;
    }

    @Override
    public void onBindViewHolder(@NonNull A56_Adapter.A56ViewHolder holder, int position) {

        final A56_Class currentItem = mdataList.get(position);

        //Kebutuhan Manajemen Lalu Lintas

        try {
            if(currentItem.getKML() == -1){
                DEV_KML = -1;
                KTG_KML = "-";
                holder.kml_data.setText("-");
                holder.kml_dev.setText("-");
            } else {
                DEV_KML = STD - currentItem.getKML();
                if(DEV_KML == 0){
                    KTG_KML = "LF";
                } else {
                    KTG_KML = "LS";
                }
                holder.kml_data.setText(String.valueOf(currentItem.getKML())+"%");
                holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML2() == -1){
                DEV_KML2 = -1;
                KTG_KML2 = "-";
                holder.kml2_data.setText("-");
                holder.kml2_dev.setText("-");
            } else {
                DEV_KML2 = STD - currentItem.getKML2();
                if(DEV_KML2 == 0){
                    KTG_KML2 = "LF";
                } else {
                    KTG_KML2 = "LS";
                }
                holder.kml2_data.setText(String.valueOf(currentItem.getKML2())+"%");
                holder.kml2_dev.setText(String.valueOf(DEV_KML2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML3() == -1){
                DEV_KML3 = -1;
                KTG_KML3 = "-";
                holder.kml3_data.setText("-");
                holder.kml3_dev.setText("-");
            } else {
                DEV_KML3 = STD - currentItem.getKML3();
                if(DEV_KML3 == 0){
                    KTG_KML3 = "LF";
                } else {
                    KTG_KML3 = "LS";
                }
                holder.kml3_data.setText(String.valueOf(currentItem.getKML3())+"%");
                holder.kml3_dev.setText(String.valueOf(DEV_KML3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KML.equals("LF") || KTG_KML.equals("-")) && (KTG_KML2.equals("LF") || KTG_KML2.equals("-"))
                    && (KTG_KML3.equals("LF") || KTG_KML3.equals("-"))){

                if(KTG_KML.equals("-") && KTG_KML2.equals("-") && KTG_KML3.equals("-")){
                    KTG_KMLL = "-";
                    holder.kml_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KMLL = "LF";
                    holder.kml_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_KMLL = "LS";
                holder.kml_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kml_hasil.setText(KTG_KMLL);
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Lampu Pengatur

        try {
            if(currentItem.getLPG() == -1){
                DEV_LPG = -1;
                KTG_LPG = "-";
                holder.lpg_data.setText("-");
                holder.lpg_dev.setText("-");
                holder.lpg_hasil.setText(KTG_LPG);
                holder.lpg_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_LPG = STD - currentItem.getLPG();
                if(DEV_LPG == 0){
                    KTG_LPG = "LF";
                    holder.lpg_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_LPG = "LS";
                    holder.lpg_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.lpg_data.setText(String.valueOf(currentItem.getLPG())+"%");
                holder.lpg_dev.setText(String.valueOf(DEV_LPG)+"%");
                holder.lpg_hasil.setText(KTG_LPG);
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Phase Pengaturan

        try {
            if(currentItem.getPHP() == -1){
                holder.php_data.setText("-");
                holder.php_dev.setText("-");
                holder.php_hasil.setText("-");
                holder.php_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.php_data.setText(String.valueOf(currentItem.getPHP()));
                holder.php_dev.setText("-");
                holder.php_hasil.setText("-");
                holder.php_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.success_style));
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Phase Pejalan Kaki

        try {
            if(currentItem.getPHK() == -1){
                DEV_PHK = -1;
                KTG_PHK = "-";
                holder.phk_data.setText("-");
                holder.phk_dev.setText("-");
                holder.phk_hasil.setText(KTG_PHK);
                holder.phk_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_PHK = STD - currentItem.getPHK();
                if(DEV_PHK == 0){
                    KTG_PHK = "LF";
                    holder.phk_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PHK = "LS";
                    holder.phk_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.phk_data.setText(String.valueOf(currentItem.getPHK())+"%");
                holder.phk_dev.setText(String.valueOf(DEV_PHK)+"%");
                holder.phk_hasil.setText(KTG_PHK);
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Fasilitas Penyandang Cacat

        try {
            if(currentItem.getFPC() == -1){
                DEV_FPC = -1;
                KTG_FPC = "-";
                holder.fpc_data.setText("-");
                holder.fpc_dev.setText("-");
                holder.fpc_hasil.setText(KTG_FPC);
                holder.fpc_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_FPC = STD - currentItem.getFPC();
                if(DEV_FPC == 0){
                    KTG_FPC = "LF";
                    holder.fpc_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_FPC = "LS";
                    holder.fpc_back.setBackground(a56Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.fpc_data.setText(String.valueOf(currentItem.getFPC())+"%");
                holder.fpc_dev.setText(String.valueOf(DEV_FPC)+"%");
                holder.fpc_hasil.setText(KTG_FPC);
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_KMLL.equals("LF") || KTG_KMLL.equals("-")) && (KTG_LPG.equals("LF") || KTG_LPG.equals("-"))
                    && (KTG_PHK.equals("LF") || KTG_PHK.equals("-")) && (KTG_FPC.equals("LF") || KTG_FPC.equals("-"))){

                if(KTG_KMLL.equals("-") && KTG_LPG.equals("-") && KTG_PHK.equals("-") && KTG_FPC.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a56Activity.FAB_UPLOAD.hide();
                a56Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a56Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a56Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a56Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a56Activity.ID = currentItem.getID();
            a56Activity.KML_TXT = currentItem.getKML();
            a56Activity.KML2_TXT = currentItem.getKML2();
            a56Activity.KML3_TXT = currentItem.getKML3();
            a56Activity.LPG_TXT = currentItem.getLPG();
            a56Activity.PHP_TXT = currentItem.getPHP();
            a56Activity.PHK_TXT = currentItem.getPHK();
            a56Activity.FPC_TXT = currentItem.getFPC();
            a56Activity.REC_TXT = currentItem.getREC();
            a56Activity.DIR1 = currentItem.getDIR1();
            a56Activity.DIR2 = currentItem.getDIR2();

            a56Activity.DEV_KML = DEV_KML;
            a56Activity.DEV_KML2 = DEV_KML2;
            a56Activity.DEV_KML3 = DEV_KML3;
            a56Activity.DEV_LPG = DEV_LPG;
            a56Activity.DEV_PHK = DEV_PHK;
            a56Activity.DEV_FPC = DEV_FPC;

            a56Activity.KTG_KMLL = KTG_KMLL;
            a56Activity.KTG_LPG = KTG_LPG;
            a56Activity.KTG_PHK = KTG_PHK;
            a56Activity.KTG_FPC = KTG_FPC;
            a56Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a56Activity.klf.setText(KTG_KLF);
                a56Activity.klf.setBackground(a56Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a56Activity.getApplicationContext(), R.anim.recycle_bottom);
                a56Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a56Activity.klf.setText("Tidak Dinilai");
                a56Activity.klf.setBackground(a56Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a56Activity.getApplicationContext(), R.anim.recycle_bottom);
                a56Activity.klf.startAnimation(animation);
            } else {
                a56Activity.klf.setText(KTG_KLF);
                a56Activity.klf.setBackground(a56Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a56Activity.getApplicationContext(), R.anim.recycle_bottom);
                a56Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a56Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
