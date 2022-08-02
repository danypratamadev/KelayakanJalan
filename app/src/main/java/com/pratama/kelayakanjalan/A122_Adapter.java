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

public class A122_Adapter extends RecyclerView.Adapter<A122_Adapter.A122ViewHolder> {

    private A122Activity a122Activity;
    private List<A122_Class> mdataList;
    private double DEV_RDT, DEV_SUP, DEV_JPD;
    private String KTG_RDT, KTG_SUP, KTG_JPD, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A122ViewHolder extends RecyclerView.ViewHolder {
        private TextView rdt_data, rdt_dev, rdt_hasil, sup_data, sup_dev, sup_hasil, jpd_data,
                jpd_dev, jpd_hasil, rec_data;
        private LinearLayout rdt_back, sup_back, jpd_back;
        private ImageView FT1, FT2;

        public A122ViewHolder(View itemView) {
            super(itemView);

            rdt_data = (TextView) itemView.findViewById(R.id.rdt_data);
            rdt_dev = (TextView) itemView.findViewById(R.id.rdt_dev);
            rdt_hasil = (TextView) itemView.findViewById(R.id.rdt_hasil);
            sup_data = (TextView) itemView.findViewById(R.id.sup_data);
            sup_dev = (TextView) itemView.findViewById(R.id.sup_dev);
            sup_hasil = (TextView) itemView.findViewById(R.id.sup_hasil);
            jpd_data = (TextView) itemView.findViewById(R.id.jpd_data);
            jpd_dev = (TextView) itemView.findViewById(R.id.jpd_dev);
            jpd_hasil = (TextView) itemView.findViewById(R.id.jpd_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            rdt_back = (LinearLayout) itemView.findViewById(R.id.rdt_back);
            sup_back = (LinearLayout) itemView.findViewById(R.id.sup_back);
            jpd_back = (LinearLayout) itemView.findViewById(R.id.jpd_back);

        }
    }

    public A122_Adapter(A122Activity a122Activity, List<A122_Class> dataList) {
        this.a122Activity = a122Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A122_Adapter.A122ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a122_layout, parent, false);
        A122_Adapter.A122ViewHolder rsu = new A122_Adapter.A122ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A122_Adapter.A122ViewHolder holder, int position) {

        final A122_Class currentItem = mdataList.get(position);

        //Radius Tikungan

        try {
            if(currentItem.getRDT() == -1){
                holder.rdt_data.setText("-");
                DEV_RDT = -1;
                KTG_RDT = "-";
                holder.rdt_dev.setText("-");
                holder.rdt_hasil.setText(KTG_RDT);
                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.rdt_data.setText(String.valueOf(currentItem.getRDT())+" m");
                if(a122Activity.FNG.equals("Arteri")){
                    if(a122Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getSRDT().equals("60 Km/jam")){
                            if(currentItem.getRDT() >= 110){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 110)/110;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        } else {
                            if(currentItem.getRDT() >= 30){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 30)/30;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        }
                    } else {
                        if(currentItem.getSRDT().equals("60 Km/jam")){
                            if(currentItem.getRDT() >= 200){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 200)/200;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        } else {
                            if(currentItem.getRDT() >= 65){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 65)/65;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        }
                    }
                } else {
                    if(a122Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getSRDT().equals("40 Km/jam")){
                            if(currentItem.getRDT() >= 50){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 50)/50;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        } else {
                            if(currentItem.getRDT() >= 15){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 15)/15;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        }
                    } else {
                        if(currentItem.getSRDT().equals("40 Km/jam")){
                            if(currentItem.getRDT() >= 100){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 100)/100;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        } else {
                            if(currentItem.getRDT() >= 30){
                                DEV_RDT = 0;
                                KTG_RDT = "LF";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                            } else {
                                DEV_RDT = (currentItem.getRDT() - 30)/30;
                                DEV_RDT = (DEV_RDT * 100);
                                if(DEV_RDT < 0){
                                    DEV_RDT = DEV_RDT * -1;
                                }
                                if(DEV_RDT > 100){
                                    DEV_RDT = 100;
                                }
                                DEV_RDT = Double.parseDouble(df.format(DEV_RDT).replace(",", "."));
                                KTG_RDT = "LS";
                                holder.rdt_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                            }
                        }
                    }
                }
                holder.rdt_dev.setText(String.valueOf(DEV_RDT)+"%");
                holder.rdt_hasil.setText(KTG_RDT);
            }
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Supervelasi

        try {
            if(currentItem.getSUP() == -1){
                holder.sup_data.setText("-");
                DEV_SUP = -1;
                KTG_SUP = "-";
                holder.sup_dev.setText("-");
                holder.sup_hasil.setText(KTG_SUP);
                holder.sup_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.sup_data.setText(String.valueOf(currentItem.getSUP())+"%");
                if(a122Activity.SJR.equals("Primer (Antar Kota)")){
                    if(currentItem.getSUP() <= 8){
                        DEV_SUP = 0;
                        KTG_SUP = "LF";
                        holder.sup_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_SUP = (currentItem.getSUP() - 8)/8;
                        DEV_SUP = (DEV_SUP * 100);
                        if(DEV_SUP < 0){
                            DEV_SUP = DEV_SUP * -1;
                        }
                        if(DEV_SUP > 100){
                            DEV_SUP = 100;
                        }
                        DEV_SUP = Double.parseDouble(df.format(DEV_SUP).replace(",", "."));
                        KTG_SUP = "LS";
                        holder.sup_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else {
                    if(currentItem.getSUP() <= 8){
                        DEV_SUP = 0;
                        KTG_SUP = "LF";
                        holder.sup_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_SUP = (currentItem.getSUP() - 8)/8;
                        DEV_SUP = (DEV_SUP * 100);
                        if(DEV_SUP < 0){
                            DEV_SUP = DEV_SUP * -1;
                        }
                        if(DEV_SUP > 100){
                            DEV_SUP = 100;
                        }
                        DEV_SUP = Double.parseDouble(df.format(DEV_SUP).replace(",", "."));
                        KTG_SUP = "LS";
                        holder.sup_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
                holder.sup_dev.setText(String.valueOf(DEV_SUP)+"%");
                holder.sup_hasil.setText(KTG_SUP);
            }
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Jarak Pandang

        try {
            if(currentItem.getJPD() == -1){
                holder.jpd_data.setText("-");
                DEV_JPD = -1;
                KTG_JPD = "-";
                holder.jpd_dev.setText("-");
                holder.jpd_hasil.setText(KTG_JPD);
                holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.jpd_data.setText(String.valueOf(currentItem.getJPD())+" m");
                if(a122Activity.FNG.equals("Arteri") && a122Activity.SJR.equals("Primer (Antar Kota)")){
                    if(currentItem.getJPD() >= 4){
                        DEV_JPD = 0;
                        KTG_JPD = "LF";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JPD = (currentItem.getJPD() - 4)/4;
                        DEV_JPD = (DEV_JPD * 100);
                        if(DEV_JPD < 0){
                            DEV_JPD = DEV_JPD * -1;
                        }
                        if(DEV_JPD > 100){
                            DEV_JPD = 100;
                        }
                        DEV_JPD = Double.parseDouble(df.format(DEV_JPD).replace(",", "."));
                        KTG_JPD = "LS";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a122Activity.FNG.equals("Arteri") && a122Activity.SJR.equals("Sekunder (Dalam Kota)")){
                    if(currentItem.getJPD() >= 2){
                        DEV_JPD = 0;
                        KTG_JPD = "LF";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JPD = (currentItem.getJPD() - 2)/2;
                        DEV_JPD = (DEV_JPD * 100);
                        if(DEV_JPD < 0){
                            DEV_JPD = DEV_JPD * -1;
                        }
                        if(DEV_JPD > 100){
                            DEV_JPD = 100;
                        }
                        DEV_JPD = Double.parseDouble(df.format(DEV_JPD).replace(",", "."));
                        KTG_JPD = "LS";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a122Activity.FNG.equals("Kolektor") && a122Activity.SJR.equals("Primer (Antar Kota)")){
                    if(currentItem.getJPD() >= 1){
                        DEV_JPD = 0;
                        KTG_JPD = "LF";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JPD = (currentItem.getJPD() - 1)/1;
                        DEV_JPD = (DEV_JPD * 100);
                        if(DEV_JPD < 0){
                            DEV_JPD = DEV_JPD * -1;
                        }
                        if(DEV_JPD > 100){
                            DEV_JPD = 100;
                        }
                        DEV_JPD = Double.parseDouble(df.format(DEV_JPD).replace(",", "."));
                        KTG_JPD = "LS";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else {
                    if(currentItem.getJPD() >= 1){
                        DEV_JPD = 0;
                        KTG_JPD = "LF";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JPD = (currentItem.getJPD() - 1)/1;
                        DEV_JPD = (DEV_JPD * 100);
                        if(DEV_JPD < 0){
                            DEV_JPD = DEV_JPD * -1;
                        }
                        if(DEV_JPD > 100){
                            DEV_JPD = 100;
                        }
                        DEV_JPD = Double.parseDouble(df.format(DEV_JPD).replace(",", "."));
                        KTG_JPD = "LS";
                        holder.jpd_back.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
                holder.jpd_dev.setText(String.valueOf(DEV_JPD)+"%");
                holder.jpd_hasil.setText(KTG_JPD);
            }
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_RDT.equals("LF") || KTG_RDT.equals("-")) && (KTG_SUP.equals("LF") || KTG_SUP.equals("-")) && (KTG_JPD.equals("LF") || KTG_JPD.equals("-"))){

                if(KTG_RDT.equals("-") && KTG_SUP.equals("-") && KTG_JPD.equals("-")) {
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a122Activity.FAB_UPLOAD.hide();
                a122Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a122Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a122Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a122Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a122Activity.ID = currentItem.getID();
            a122Activity.SRDT_TXT = currentItem.getSRDT();
            a122Activity.RDT_TXT = currentItem.getRDT();
            a122Activity.SUP_TXT = currentItem.getSUP();
            a122Activity.JPD_TXT = currentItem.getJPD();
            a122Activity.REC_TXT = currentItem.getREC();
            a122Activity.DIR1 = currentItem.getDIR1();
            a122Activity.DIR2 = currentItem.getDIR2();

            a122Activity.DEV_RDT = DEV_RDT;
            a122Activity.DEV_SUP = DEV_SUP;
            a122Activity.DEV_JPD = DEV_JPD;

            a122Activity.KTG_RDT = KTG_RDT;
            a122Activity.KTG_SUP = KTG_SUP;
            a122Activity.KTG_JPD = KTG_JPD;
            a122Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a122Activity.klf.setText(KTG_KLF);
                a122Activity.klf.setBackground(a122Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a122Activity.getApplicationContext(), R.anim.recycle_bottom);
                a122Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a122Activity.klf.setText("Tidak Dinilai");
                a122Activity.klf.setBackground(a122Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a122Activity.getApplicationContext(), R.anim.recycle_bottom);
                a122Activity.klf.startAnimation(animation);
            } else {
                a122Activity.klf.setText(KTG_KLF);
                a122Activity.klf.setBackground(a122Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a122Activity.getApplicationContext(), R.anim.recycle_bottom);
                a122Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a122Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
