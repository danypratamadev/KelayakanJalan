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

public class A123_Adapter extends RecyclerView.Adapter<A123_Adapter.A123ViewHolder> {

    private A123Activity a123Activity;
    private List<A123_Class> mdataList;
    private double DEV_JPK, DEV_SCAJ;
    private String KTG_JPK, KTG_SCAJ, KTG_KLF;
    private int SCAJ_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A123ViewHolder extends RecyclerView.ViewHolder {
        private TextView jpk_data, jpk_dev, jpk_hasil, scaj_data, scaj_dev, scaj_hasil, rec_data;
        private LinearLayout jpk_back, scaj_back;
        private ImageView FT1, FT2, FT3;

        public A123ViewHolder(View itemView) {
            super(itemView);

            jpk_data = (TextView) itemView.findViewById(R.id.jpk_data);
            jpk_dev = (TextView) itemView.findViewById(R.id.jpk_dev);
            jpk_hasil = (TextView) itemView.findViewById(R.id.jpk_hasil);
            scaj_data = (TextView) itemView.findViewById(R.id.scaj_data);
            scaj_dev = (TextView) itemView.findViewById(R.id.scaj_dev);
            scaj_hasil = (TextView) itemView.findViewById(R.id.scaj_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);

            jpk_back = (LinearLayout) itemView.findViewById(R.id.jpk_back);
            scaj_back = (LinearLayout) itemView.findViewById(R.id.scaj_back);

        }
    }

    public A123_Adapter(A123Activity a123Activity, List<A123_Class> dataList) {
        this.a123Activity = a123Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A123_Adapter.A123ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a123_layout, parent, false);
        A123_Adapter.A123ViewHolder rsu = new A123_Adapter.A123ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A123_Adapter.A123ViewHolder holder, int position) {

        final A123_Class currentItem = mdataList.get(position);

        //Jumlah Persimpangan

        try {
            if(currentItem.getJPK() == -1){
                holder.jpk_data.setText("-");
                DEV_JPK = -1;
                KTG_JPK = "-";
                holder.jpk_dev.setText("-");
                holder.jpk_hasil.setText(KTG_JPK);
                holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.jpk_data.setText(String.valueOf(currentItem.getJPK())+"/Km");
                if(a123Activity.FNG.equals("Arteri") && a123Activity.SJR.equals("Primer (Antar Kota)")){
                    if(a123Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        DEV_JPK = -1;
                        KTG_JPK = "-";
                    } else if(a123Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getJPK() == 1){
                            DEV_JPK = 0;
                            KTG_JPK = "LF";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_JPK = (currentItem.getJPK() - 1)/1;
                            DEV_JPK = (DEV_JPK * 100);
                            if(DEV_JPK < 0){
                                DEV_JPK = DEV_JPK * -1;
                            }
                            if(DEV_JPK > 100){
                                DEV_JPK = 100;
                            }
                            DEV_JPK = Double.parseDouble(df.format(DEV_JPK).replace(",", "."));
                            KTG_JPK = "LS";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else if(a123Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getJPK() == 1){
                            DEV_JPK = 0;
                            KTG_JPK = "LF";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_JPK = (currentItem.getJPK() - 1)/1;
                            DEV_JPK = (DEV_JPK * 100);
                            if(DEV_JPK < 0){
                                DEV_JPK = DEV_JPK * -1;
                            }
                            if(DEV_JPK > 100){
                                DEV_JPK = 100;
                            }
                            DEV_JPK = Double.parseDouble(df.format(DEV_JPK).replace(",", "."));
                            KTG_JPK = "LS";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else {
                        DEV_JPK = -1;
                        KTG_JPK = "-";
                    }
                } else if(a123Activity.FNG.equals("Arteri") && a123Activity.SJR.equals("Sekunder (Dalam Kota)")){
                    if(a123Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        DEV_JPK = -1;
                        KTG_JPK = "-";
                    } else if(a123Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getJPK() == 1){
                            DEV_JPK = 0;
                            KTG_JPK = "LF";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_JPK = (currentItem.getJPK() - 1)/1;
                            DEV_JPK = (DEV_JPK * 100);
                            if(DEV_JPK < 0){
                                DEV_JPK = DEV_JPK * -1;
                            }
                            if(DEV_JPK > 100){
                                DEV_JPK = 100;
                            }
                            DEV_JPK = Double.parseDouble(df.format(DEV_JPK).replace(",", "."));
                            KTG_JPK = "LS";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else if(a123Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getJPK() == 1){
                            DEV_JPK = 0;
                            KTG_JPK = "LF";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_JPK = (currentItem.getJPK() - 1)/1;
                            DEV_JPK = (DEV_JPK * 100);
                            if(DEV_JPK < 0){
                                DEV_JPK = DEV_JPK * -1;
                            }
                            if(DEV_JPK > 100){
                                DEV_JPK = 100;
                            }
                            DEV_JPK = Double.parseDouble(df.format(DEV_JPK).replace(",", "."));
                            KTG_JPK = "LS";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else {
                        DEV_JPK = -1;
                        KTG_JPK = "-";
                    }
                } else if(a123Activity.FNG.equals("Kolektor")){
                    if(a123Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        DEV_JPK = -1;
                        KTG_JPK = "-";
                    } else if(a123Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getJPK() == 1){
                            DEV_JPK = 0;
                            KTG_JPK = "LF";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_JPK = (currentItem.getJPK() - 1)/1;
                            DEV_JPK = (DEV_JPK * 100);
                            if(DEV_JPK < 0){
                                DEV_JPK = DEV_JPK * -1;
                            }
                            if(DEV_JPK > 100){
                                DEV_JPK = 100;
                            }
                            DEV_JPK = Double.parseDouble(df.format(DEV_JPK).replace(",", "."));
                            KTG_JPK = "LS";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else if(a123Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getJPK() == 1){
                            DEV_JPK = 0;
                            KTG_JPK = "LF";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_JPK = (currentItem.getJPK() - 1)/1;
                            DEV_JPK = (DEV_JPK * 100);
                            if(DEV_JPK < 0){
                                DEV_JPK = DEV_JPK * -1;
                            }
                            if(DEV_JPK > 100){
                                DEV_JPK = 100;
                            }
                            DEV_JPK = Double.parseDouble(df.format(DEV_JPK).replace(",", "."));
                            KTG_JPK = "LS";
                            holder.jpk_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else {
                        DEV_JPK = -1;
                        KTG_JPK = "-";
                    }
                }
                holder.jpk_dev.setText(String.valueOf(DEV_JPK)+"%");
                holder.jpk_hasil.setText(KTG_JPK);
            }
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Cara Akses ke Jalan Utama

        try {
            if(currentItem.getSCAJ().equals("Melalui Bukaan pada samping ke Jalur Utama")){
                SCAJ_IN = 1;
                DEV_SCAJ = 0;
                KTG_SCAJ = "LF";
                holder.scaj_dev.setText(String.valueOf(DEV_SCAJ)+"%");
                holder.scaj_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
            } else if(currentItem.getSCAJ().equals("Menggunakan APILL")){
                SCAJ_IN = 2;
                DEV_SCAJ = 0;
                KTG_SCAJ = "LF";
                holder.scaj_dev.setText(String.valueOf(DEV_SCAJ)+"%");
                holder.scaj_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
            } else if(currentItem.getSCAJ().equals("Langsung dengan Pengendalian Lalu Lintas")){
                SCAJ_IN = 3;
                DEV_SCAJ = 0;
                KTG_SCAJ = "LF";
                holder.scaj_dev.setText(String.valueOf(DEV_SCAJ)+"%");
                holder.scaj_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
            } else if(currentItem.getSCAJ().equals("Langsung tanpa Pengendalian Lalu Lintas")){
                SCAJ_IN = 4;
                DEV_SCAJ = 100;
                KTG_SCAJ = "LS";
                holder.scaj_dev.setText(String.valueOf(DEV_SCAJ)+"%");
                holder.scaj_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
            } else if(currentItem.getSCAJ().equals("Tidak Ada")){
                SCAJ_IN = 5;
                DEV_SCAJ = 100;
                KTG_SCAJ = "LS";
                holder.scaj_dev.setText(String.valueOf(DEV_SCAJ)+"%");
                holder.scaj_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
            } else {
                SCAJ_IN = 6;
                DEV_SCAJ = -1;
                KTG_SCAJ = "-";
                holder.scaj_dev.setText(String.valueOf("-"));
                holder.scaj_back.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.scaj_data.setText("("+String.valueOf(SCAJ_IN)+") "+currentItem.getSCAJ());
            holder.scaj_hasil.setText(KTG_SCAJ);
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_JPK.equals("LF") || KTG_JPK.equals("-")) && (KTG_SCAJ.equals("LF") || KTG_SCAJ.equals("-"))){

                if(KTG_JPK.equals("-") && KTG_SCAJ.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }
            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a123Activity.FAB_UPLOAD.hide();
                a123Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a123Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a123Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a123Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a123Activity.ID = currentItem.getID();
            a123Activity.JPK_TXT = currentItem.getJPK();
            a123Activity.SCAJ_IN = SCAJ_IN;
            a123Activity.SCAJ_TXT = currentItem.getSCAJ();
            a123Activity.REC_TXT = currentItem.getREC();
            a123Activity.DIR1 = currentItem.getDIR1();
            a123Activity.DIR2 = currentItem.getDIR2();
            a123Activity.DIR3 = currentItem.getDIR3();

            a123Activity.DEV_JPK = DEV_JPK;
            a123Activity.DEV_SCAJ = DEV_SCAJ;

            a123Activity.KTG_JPK = KTG_JPK;
            a123Activity.KTG_SCAJ = KTG_SCAJ;
            a123Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a123Activity.klf.setText(KTG_KLF);
                a123Activity.klf.setBackground(a123Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a123Activity.getApplicationContext(), R.anim.recycle_bottom);
                a123Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a123Activity.klf.setText("Tidak Dinilai");
                a123Activity.klf.setBackground(a123Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a123Activity.getApplicationContext(), R.anim.recycle_bottom);
                a123Activity.klf.startAnimation(animation);
            } else {
                a123Activity.klf.setText(KTG_KLF);
                a123Activity.klf.setBackground(a123Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a123Activity.getApplicationContext(), R.anim.recycle_bottom);
                a123Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a123Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
