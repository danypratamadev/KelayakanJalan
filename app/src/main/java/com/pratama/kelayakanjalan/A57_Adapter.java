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

public class A57_Adapter extends RecyclerView.Adapter<A57_Adapter.A57ViewHolder> {

    private A57Activity a57Activity;
    private List<A57_Class> mdataList;
    private double DEV_KML, DEV_KML2, DEV_KML3, DEV_KML4, DEV_RMK, DEV_RMK2, DEV_RMK3, DEV_APL, DEV_PPK, STD = 100;
    private String KTG_KML, KTG_KML2, KTG_KML3, KTG_KML4, KTG_KMLL, KTG_RMK, KTG_RMK2, KTG_RMK3, KTG_RMKK, KTG_APL, KTG_PPK, KTG_KLF;
    private int KML_IN, KML2_IN, KML3_IN, KML4_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A57ViewHolder extends RecyclerView.ViewHolder {

        private TextView kml_data, kml_dev, kml2_data, kml2_dev, kml3_data, kml3_dev, kml4_data, kml4_dev, kml_hasil, rmk_data,
                rmk_dev, rmk2_data, rmk2_dev, rmk3_data, rmk3_dev, rmk_hasil, apl_data, apl_dev, apl_hasil, ppk_data, ppk_dev,
                ppk_hasil, rec_data;

        private LinearLayout kml_back, rmk_back, apl_back, ppk_back;
        private ImageView FT1, FT2;

        public A57ViewHolder(@NonNull View itemView) {
            super(itemView);

            kml_data = (TextView) itemView.findViewById(R.id.kml_data);
            kml_dev = (TextView) itemView.findViewById(R.id.kml_dev);
            kml2_data = (TextView) itemView.findViewById(R.id.kml2_data);
            kml2_dev = (TextView) itemView.findViewById(R.id.kml2_dev);
            kml3_data = (TextView) itemView.findViewById(R.id.kml3_data);
            kml3_dev = (TextView) itemView.findViewById(R.id.kml3_dev);
            kml4_data = (TextView) itemView.findViewById(R.id.kml4_data);
            kml4_dev = (TextView) itemView.findViewById(R.id.kml4_dev);
            kml_hasil = (TextView) itemView.findViewById(R.id.kml_hasil);

            rmk_data = (TextView) itemView.findViewById(R.id.rmk_data);
            rmk_dev = (TextView) itemView.findViewById(R.id.rmk_dev);
            rmk2_data = (TextView) itemView.findViewById(R.id.rmk2_data);
            rmk2_dev = (TextView) itemView.findViewById(R.id.rmk2_dev);
            rmk3_data = (TextView) itemView.findViewById(R.id.rmk3_data);
            rmk3_dev = (TextView) itemView.findViewById(R.id.rmk3_dev);
            rmk_hasil = (TextView) itemView.findViewById(R.id.rmk_hasil);

            apl_data = (TextView) itemView.findViewById(R.id.apl_data);
            apl_dev = (TextView) itemView.findViewById(R.id.apl_dev);
            apl_hasil = (TextView) itemView.findViewById(R.id.apl_hasil);

            ppk_data = (TextView) itemView.findViewById(R.id.ppk_data);
            ppk_dev = (TextView) itemView.findViewById(R.id.ppk_dev);
            ppk_hasil = (TextView) itemView.findViewById(R.id.ppk_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kml_back = (LinearLayout) itemView.findViewById(R.id.kml_back);
            rmk_back = (LinearLayout) itemView.findViewById(R.id.rmk_back);
            apl_back = (LinearLayout) itemView.findViewById(R.id.apl_back);
            ppk_back = (LinearLayout) itemView.findViewById(R.id.ppk_back);

        }
    }

    public A57_Adapter(A57Activity a57Activity, List<A57_Class> mdataList) {
        this.a57Activity = a57Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A57_Adapter.A57ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a57_layout, parent, false);
        A57_Adapter.A57ViewHolder a57 = new A57_Adapter.A57ViewHolder(view);
        return a57;
    }

    @Override
    public void onBindViewHolder(@NonNull A57_Adapter.A57ViewHolder holder, int position) {

        final A57_Class currentItem = mdataList.get(position);

        //Kebutuhan Manajemen Lalu Lintas

        try {
            if(currentItem.getKML().equals("Ada")){
                KML_IN = 1;
                DEV_KML = STD - currentItem.getKML11();
                if(DEV_KML == 0){
                    KTG_KML = "LF";
                } else {
                    KTG_KML = "LS";
                }
                holder.kml_data.setText("("+String.valueOf(KML_IN)+") "+String.valueOf(currentItem.getKML11())+"%");
                holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
            } else if(currentItem.getKML().equals("Tidak Ada")){
                KML_IN = 2;
                DEV_KML = 100;
                KTG_KML = "LS";
                holder.kml_data.setText("("+String.valueOf(KML_IN)+") "+String.valueOf(currentItem.getKML11())+"%");
                holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
            } else {
                KML_IN = 3;
                DEV_KML = -1;
                KTG_KML = "-";
                holder.kml_data.setText("("+String.valueOf(KML_IN)+")");
                holder.kml_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML2().equals("Ada")){
                KML2_IN = 1;
                DEV_KML2 = STD - currentItem.getKML22();
                if(DEV_KML2 == 0){
                    KTG_KML2 = "LF";
                } else {
                    KTG_KML2 = "LS";
                }
                holder.kml2_data.setText("("+String.valueOf(KML2_IN)+") "+String.valueOf(currentItem.getKML22())+"%");
                holder.kml2_dev.setText(String.valueOf(DEV_KML2)+"%");
            } else if(currentItem.getKML2().equals("Tidak Ada")){
                KML2_IN = 2;
                DEV_KML2 = 100;
                KTG_KML2 = "LS";
                holder.kml2_data.setText("("+String.valueOf(KML2_IN)+") "+String.valueOf(currentItem.getKML22())+"%");
                holder.kml2_dev.setText(String.valueOf(DEV_KML2)+"%");
            } else {
                KML2_IN = 3;
                DEV_KML2 = -1;
                KTG_KML2 = "-";
                holder.kml2_data.setText("("+String.valueOf(KML2_IN)+")");
                holder.kml2_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML3().equals("Ada")){
                KML3_IN = 1;
                DEV_KML3 = STD - currentItem.getKML33();
                if(DEV_KML3 == 0){
                    KTG_KML3 = "LF";
                } else {
                    KTG_KML3 = "LS";
                }
                holder.kml3_data.setText("("+String.valueOf(KML3_IN)+") "+String.valueOf(currentItem.getKML33())+"%");
                holder.kml3_dev.setText(String.valueOf(DEV_KML3)+"%");
            } else if(currentItem.getKML3().equals("Tidak Ada")){
                KML3_IN = 2;
                DEV_KML3 = 100;
                KTG_KML3 = "LS";
                holder.kml3_data.setText("("+String.valueOf(KML3_IN)+") "+String.valueOf(currentItem.getKML33())+"%");
                holder.kml3_dev.setText(String.valueOf(DEV_KML3)+"%");
            } else {
                KML3_IN = 3;
                DEV_KML3 = -1;
                KTG_KML3 = "-";
                holder.kml3_data.setText("("+String.valueOf(KML3_IN)+")");
                holder.kml3_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML4().equals("Ada")){
                KML4_IN = 1;
                DEV_KML4 = STD - currentItem.getKML44();
                if(DEV_KML4 == 0){
                    KTG_KML4 = "LF";
                } else {
                    KTG_KML4 = "LS";
                }
                holder.kml4_data.setText("("+String.valueOf(KML4_IN)+") "+String.valueOf(currentItem.getKML44())+"%");
                holder.kml4_dev.setText(String.valueOf(DEV_KML4)+"%");
            } else if(currentItem.getKML4().equals("Tidak Ada")){
                KML4_IN = 2;
                DEV_KML4 = 100;
                KTG_KML4 = "LS";
                holder.kml4_data.setText("("+String.valueOf(KML4_IN)+") "+String.valueOf(currentItem.getKML44())+"%");
                holder.kml4_dev.setText(String.valueOf(DEV_KML4)+"%");
            } else {
                KML4_IN = 3;
                DEV_KML4 = -1;
                KTG_KML4 = "-";
                holder.kml4_data.setText("("+String.valueOf(KML4_IN)+")");
                holder.kml4_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KML.equals("LF") || KTG_KML.equals("-")) && (KTG_KML2.equals("LF") || KTG_KML2.equals("-"))
                    && (KTG_KML3.equals("LF") || KTG_KML3.equals("-")) && (KTG_KML4.equals("LF") || KTG_KML4.equals("-"))){

                if(KTG_KML.equals("-") && KTG_KML2.equals("-") && KTG_KML3.equals("-") && KTG_KML4.equals("-")){
                    KTG_KMLL = "-";
                    holder.kml_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KMLL = "LF";
                    holder.kml_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_KMLL = "LS";
                holder.kml_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kml_hasil.setText(KTG_KMLL);
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Rambu dan Marka

        try {
            if(currentItem.getRMK() == -1){
                DEV_RMK = -1;
                KTG_RMK = "-";
                holder.rmk_data.setText("-");
                holder.rmk_dev.setText("-");
            } else {
                DEV_RMK = STD - currentItem.getRMK();
                if(DEV_RMK == 0){
                    KTG_RMK = "LF";
                } else {
                    KTG_RMK = "LS";
                }
                holder.rmk_data.setText(String.valueOf(currentItem.getRMK())+"%");
                holder.rmk_dev.setText(String.valueOf(DEV_RMK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRMK2() == -1){
                DEV_RMK2 = -1;
                KTG_RMK2 = "-";
                holder.rmk2_data.setText("-");
                holder.rmk2_dev.setText("-");
            } else {
                DEV_RMK2 = STD - currentItem.getRMK2();
                if(DEV_RMK2 == 0){
                    KTG_RMK2 = "LF";
                } else {
                    KTG_RMK2 = "LS";
                }
                holder.rmk2_data.setText(String.valueOf(currentItem.getRMK2())+"%");
                holder.rmk2_dev.setText(String.valueOf(DEV_RMK2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRMK3() == -1){
                DEV_RMK3 = -1;
                KTG_RMK3 = "-";
                holder.rmk3_data.setText("-");
                holder.rmk3_dev.setText("-");
            } else {
                DEV_RMK3 = STD - currentItem.getRMK3();
                if(DEV_RMK3 == 0){
                    KTG_RMK3 = "LF";
                } else {
                    KTG_RMK3 = "LS";
                }
                holder.rmk3_data.setText(String.valueOf(currentItem.getRMK3())+"%");
                holder.rmk3_dev.setText(String.valueOf(DEV_RMK3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_RMK.equals("LF") || KTG_RMK.equals("-")) && (KTG_RMK2.equals("LF") || KTG_RMK2.equals("-"))
                    && (KTG_RMK3.equals("LF") || KTG_RMK3.equals("-"))){

                if(KTG_RMK.equals("-") && KTG_RMK2.equals("-") && KTG_RMK3.equals("-")){
                    KTG_RMKK = "-";
                    holder.rmk_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_RMKK = "LF";
                    holder.rmk_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_RMKK = "LS";
                holder.rmk_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.rmk_hasil.setText(KTG_RMKK);
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Apill

        try {
            if(currentItem.getAPL() == -1){
                DEV_APL = -1;
                KTG_APL = "-";
                holder.apl_data.setText("-");
                holder.apl_dev.setText("-");
                holder.apl_hasil.setText(KTG_APL);
                holder.apl_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_APL = STD - currentItem.getAPL();
                if(DEV_APL == 0){
                    KTG_APL = "LF";
                    holder.apl_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_APL = "LS";
                    holder.apl_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.apl_data.setText(String.valueOf(currentItem.getAPL())+"%");
                holder.apl_dev.setText(String.valueOf(DEV_APL)+"%");
                holder.apl_hasil.setText(KTG_APL);
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Perlindungan Pejalan Kaki

        try {
            if(currentItem.getPPK() == -1){
                DEV_PPK = -1;
                KTG_PPK = "-";
                holder.ppk_data.setText("-");
                holder.ppk_dev.setText("-");
                holder.ppk_hasil.setText(KTG_APL);
                holder.ppk_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_PPK = STD - currentItem.getPPK();
                if(DEV_PPK == 0){
                    KTG_PPK = "LF";
                    holder.ppk_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PPK = "LS";
                    holder.ppk_back.setBackground(a57Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ppk_data.setText(String.valueOf(currentItem.getPPK())+"%");
                holder.ppk_dev.setText(String.valueOf(DEV_PPK)+"%");
                holder.ppk_hasil.setText(KTG_PPK);
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_KMLL.equals("LF") || KTG_KMLL.equals("-")) && (KTG_RMKK.equals("LF") || KTG_RMKK.equals("-"))
                    && (KTG_APL.equals("LF") || KTG_APL.equals("-")) && (KTG_PPK.equals("LF") || KTG_PPK.equals("-"))){

                if(KTG_KMLL.equals("-") && KTG_RMKK.equals("-") && KTG_APL.equals("-") && KTG_PPK.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a57Activity.FAB_UPLOAD.hide();
                a57Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a57Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a57Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a57Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a57Activity.ID = currentItem.getID();
            a57Activity.KML_IN = KML_IN;
            a57Activity.KML2_IN = KML2_IN;
            a57Activity.KML3_IN = KML3_IN;
            a57Activity.KML4_IN = KML4_IN;
            a57Activity.KML_TXT = currentItem.getKML();
            a57Activity.KML2_TXT = currentItem.getKML2();
            a57Activity.KML3_TXT = currentItem.getKML3();
            a57Activity.KML4_TXT = currentItem.getKML4();
            a57Activity.KML_TXT11 = currentItem.getKML11();
            a57Activity.KML2_TXT22 = currentItem.getKML22();
            a57Activity.KML3_TXT33 = currentItem.getKML33();
            a57Activity.KML4_TXT44 = currentItem.getKML44();
            a57Activity.RMK_TXT = currentItem.getRMK();
            a57Activity.RMK2_TXT = currentItem.getRMK2();
            a57Activity.RMK3_TXT = currentItem.getRMK3();
            a57Activity.APL_TXT = currentItem.getAPL();
            a57Activity.PPK_TXT = currentItem.getPPK();
            a57Activity.REC_TXT = currentItem.getREC();
            a57Activity.DIR1 = currentItem.getDIR1();
            a57Activity.DIR2 = currentItem.getDIR2();

            a57Activity.DEV_KML = DEV_KML;
            a57Activity.DEV_KML2 = DEV_KML2;
            a57Activity.DEV_KML3 = DEV_KML3;
            a57Activity.DEV_KML4 = DEV_KML4;
            a57Activity.DEV_RMK = DEV_RMK;
            a57Activity.DEV_RMK2 = DEV_RMK2;
            a57Activity.DEV_RMK3 = DEV_RMK3;
            a57Activity.DEV_APL = DEV_APL;
            a57Activity.DEV_PPK = DEV_PPK;

            a57Activity.KTG_KMLL = KTG_KMLL;
            a57Activity.KTG_RMKK = KTG_RMKK;
            a57Activity.KTG_APL = KTG_APL;
            a57Activity.KTG_PPK = KTG_PPK;
            a57Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a57Activity.klf.setText(KTG_KLF);
                a57Activity.klf.setBackground(a57Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a57Activity.getApplicationContext(), R.anim.recycle_bottom);
                a57Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a57Activity.klf.setText("Tidak Dinilai");
                a57Activity.klf.setBackground(a57Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a57Activity.getApplicationContext(), R.anim.recycle_bottom);
                a57Activity.klf.startAnimation(animation);
            } else {
                a57Activity.klf.setText(KTG_KLF);
                a57Activity.klf.setBackground(a57Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a57Activity.getApplicationContext(), R.anim.recycle_bottom);
                a57Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a57Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
