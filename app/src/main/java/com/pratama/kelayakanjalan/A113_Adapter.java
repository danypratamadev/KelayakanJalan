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

public class A113_Adapter extends RecyclerView.Adapter<A113_Adapter.A113ViewHolder> {

    private A113Activity a113Activity;
    private List<A113_Class> mdataList;
    private double DEV_LBM, DEV_TPM, DEV_PKM, DEV_JAB, DEV_LBB;
    private String KTG_LBM, KTG_TPM, KTG_PKM, KTG_JAB, KTG_LBB, KTG_BKM, KTG_KLF;
    private double STD = 100;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A113ViewHolder extends RecyclerView.ViewHolder {
        private TextView lbm_data, lbm_dev, lbm_hasil, tpm_data, tpm_dev, tpm_hasil, pkm_data,
                pkm_dev, pkm_hasil, jab_data, jab_dev, lbb_data, lbb_dev, bkm_hasil, rec_data;
        private LinearLayout lbm_back, tpm_back, pkm_back, bkm_back;
        private ImageView FT1, FT2, FT3;

        public A113ViewHolder(View itemView) {
            super(itemView);

            lbm_data = (TextView) itemView.findViewById(R.id.lbm_data);
            lbm_dev = (TextView) itemView.findViewById(R.id.lbm_dev);
            lbm_hasil = (TextView) itemView.findViewById(R.id.lbm_hasil);
            tpm_data = (TextView) itemView.findViewById(R.id.tpm_data);
            tpm_dev = (TextView) itemView.findViewById(R.id.tpm_dev);
            tpm_hasil = (TextView) itemView.findViewById(R.id.tpm_hasil);
            pkm_data = (TextView) itemView.findViewById(R.id.pkm_data);
            pkm_dev = (TextView) itemView.findViewById(R.id.pkm_dev);
            pkm_hasil = (TextView) itemView.findViewById(R.id.pkm_hasil);
            jab_data = (TextView) itemView.findViewById(R.id.jab_data);
            jab_dev = (TextView) itemView.findViewById(R.id.jab_dev);
            lbb_data = (TextView) itemView.findViewById(R.id.lbb_data);
            lbb_dev = (TextView) itemView.findViewById(R.id.lbb_dev);
            bkm_hasil = (TextView) itemView.findViewById(R.id.bkm_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);

            lbm_back = (LinearLayout) itemView.findViewById(R.id.lbm_back);
            tpm_back = (LinearLayout) itemView.findViewById(R.id.tpm_back);
            pkm_back = (LinearLayout) itemView.findViewById(R.id.pkm_back);
            bkm_back = (LinearLayout) itemView.findViewById(R.id.bkm_back);
        }
    }

    public A113_Adapter(A113Activity a113Activity, List<A113_Class> dataList) {
        this.a113Activity = a113Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A113_Adapter.A113ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a113_layout, parent, false);
        A113_Adapter.A113ViewHolder rsu = new A113_Adapter.A113ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A113_Adapter.A113ViewHolder holder, int position) {

        final A113_Class currentItem = mdataList.get(position);

        //Lebar Median

        try {
            if(currentItem.getLBM() == -1){
                holder.lbm_data.setText("-");
                DEV_LBM = -1;
                KTG_LBM = "-";
                holder.lbm_dev.setText("-");
                holder.lbm_hasil.setText(KTG_LBM);
                holder.lbm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.lbm_data.setText(String.valueOf(currentItem.getLBM())+" m");
                if(currentItem.getLBMT().equals("Median Datar")){

                    if(currentItem.getLBM() >= 0.18){
                        DEV_LBM = 0;
                        KTG_LBM = "LF";
                        holder.lbm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LBM = (currentItem.getLBM() - 0.18)/0.18;
                        DEV_LBM = (DEV_LBM * 100);
                        if(DEV_LBM < 0){
                            DEV_LBM = DEV_LBM * -1;
                        }
                        if(DEV_LBM > 100){
                            DEV_LBM = 100;
                        }
                        DEV_LBM = Double.parseDouble(df.format(DEV_LBM).replace(",", "."));
                        KTG_LBM = "LS";
                        holder.lbm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }


                } else if(currentItem.getLBMT().equals("Median Diturunkan")){

                    if(currentItem.getLBM() >= 9){
                        DEV_LBM = 0;
                        KTG_LBM = "LF";
                        holder.lbm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LBM = (currentItem.getLBM() - 9)/9;
                        DEV_LBM = (DEV_LBM * 100);
                        if(DEV_LBM < 0){
                            DEV_LBM = DEV_LBM * -1;
                        }
                        if(DEV_LBM > 100){
                            DEV_LBM = 100;
                        }
                        DEV_LBM = Double.parseDouble(df.format(DEV_LBM).replace(",", "."));
                        KTG_LBM = "LS";
                        holder.lbm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                } else {

                    if(currentItem.getLBM() >= 1.2){
                        DEV_LBM = 0;
                        KTG_LBM = "LF";
                        holder.lbm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LBM = (currentItem.getLBM() - 1.2)/1.2;
                        DEV_LBM = (DEV_LBM * 100);
                        if(DEV_LBM < 0){
                            DEV_LBM = DEV_LBM * -1;
                        }
                        if(DEV_LBM > 100){
                            DEV_LBM = 100;
                        }
                        DEV_LBM = Double.parseDouble(df.format(DEV_LBM).replace(",", "."));
                        KTG_LBM = "LS";
                        holder.lbm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                }

                holder.lbm_dev.setText(String.valueOf(DEV_LBM)+"%");
                holder.lbm_hasil.setText(KTG_LBM);
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Tipe Median

        try {
            if(currentItem.getTPM() == -1){
                holder.tpm_data.setText("-");
                DEV_TPM = -1;
                KTG_TPM = "-";
                holder.tpm_dev.setText("-");
                holder.tpm_hasil.setText(KTG_TPM);
                holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.tpm_data.setText(String.valueOf(currentItem.getTPM())+" m");
                if(currentItem.getTPMT().equals("2 buah garis utuh")){

                    if(currentItem.getTPM() >= 0.18){
                        DEV_TPM = 0;
                        KTG_TPM = "LF";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_TPM = (currentItem.getTPM() - 0.18)/0.18;
                        DEV_TPM = (DEV_TPM * 100);
                        if(DEV_TPM < 0){
                            DEV_TPM = DEV_TPM * -1;
                        }
                        if(DEV_TPM > 100){
                            DEV_TPM = 100;
                        }
                        DEV_TPM = Double.parseDouble(df.format(DEV_TPM).replace(",", "."));
                        KTG_TPM = "LS";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                } else if(currentItem.getTPMT().equals("Cekung")){
                    if(currentItem.getTPM() >= 6){
                        DEV_TPM = 0;
                        KTG_TPM = "LF";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_TPM = (currentItem.getTPM() - 6)/6;
                        DEV_TPM = (DEV_TPM * 100);
                        if(DEV_TPM < 0){
                            DEV_TPM = DEV_TPM * -1;
                        }
                        if(DEV_TPM > 100){
                            DEV_TPM = 100;
                        }
                        DEV_TPM = Double.parseDouble(df.format(DEV_TPM).replace(",", "."));
                        KTG_TPM = "LS";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(currentItem.getTPMT().equals("Menggunakan kerb")){

                    if(currentItem.getTPM() >= 0.18){
                        DEV_TPM = 0;
                        KTG_TPM = "LF";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_TPM = (currentItem.getTPM() - 0.18)/0.18;
                        DEV_TPM = (DEV_TPM * 100);
                        if(DEV_TPM < 0){
                            DEV_TPM = DEV_TPM * -1;
                        }
                        if(DEV_TPM > 100){
                            DEV_TPM = 100;
                        }
                        DEV_TPM = Double.parseDouble(df.format(DEV_TPM).replace(",", "."));
                        KTG_TPM = "LS";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                } else {

                    if(currentItem.getTPM() >= 1.1){
                        DEV_TPM = 0;
                        KTG_TPM = "LF";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_TPM = (currentItem.getTPM() - 1.1)/1.1;
                        DEV_TPM = (DEV_TPM * 100);
                        if(DEV_TPM < 0){
                            DEV_TPM = DEV_TPM * -1;
                        }
                        if(DEV_TPM > 100){
                            DEV_TPM = 100;
                        }
                        DEV_TPM = Double.parseDouble(df.format(DEV_TPM).replace(",", "."));
                        KTG_TPM = "LS";
                        holder.tpm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                }
                holder.tpm_dev.setText(String.valueOf(DEV_TPM)+"%");
                holder.tpm_hasil.setText(KTG_TPM);
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Perkerasan Median

        try {
            if(currentItem.getPKM() == -1){
                holder.pkm_data.setText("-");
                DEV_PKM = -1;
                KTG_PKM = "-";
                holder.pkm_dev.setText("-");
                holder.pkm_hasil.setText("-");
                holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.pkm_data.setText(String.valueOf(currentItem.getPKM())+"%");
                if(currentItem.getPKMT().equals("Hanya Menggunakan Marka")){

                    DEV_PKM = STD - currentItem.getPKM();
                    if(DEV_PKM == 0){
                        KTG_PKM = "LF";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        KTG_PKM = "LS";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                } else if(currentItem.getPKMT().equals("Material yang mampu meredam kecepatan")){

                    DEV_PKM = STD - currentItem.getPKM();
                    if(DEV_PKM == 0){
                        KTG_PKM = "LF";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        KTG_PKM = "LS";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                } else if(currentItem.getPKMT().equals("Kerb")){

                    DEV_PKM = STD - currentItem.getPKM();
                    if(DEV_PKM == 0){
                        KTG_PKM = "LF";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        KTG_PKM = "LS";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                    //if(currentItem.getPKM() >= 0.18 && currentItem.getPKM() <= 0.25){
                    //    DEV_PKM = 0;
                    //    KTG_PKM = "LF";
                    //    holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    //} else {
                    //    if(currentItem.getPKM() < 0.18){
                    //        DEV_PKM = (currentItem.getPKM() - 0.18)/0.18;
                    //    } else {
                    //        DEV_PKM = (currentItem.getPKM() - 0.25)/0.25;
                    //    }
                    //    DEV_PKM = (DEV_PKM * 100) * -1;
                    //    DEV_PKM = Double.parseDouble(df.format(DEV_PKM));
                    //    KTG_PKM = "LS";
                    //    holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    //}

                } else {

                    DEV_PKM = STD - currentItem.getPKM();
                    if(DEV_PKM == 0){
                        KTG_PKM = "LF";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        KTG_PKM = "LS";
                        holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    }

                    //if(currentItem.getPKM() >= 1.1){
                    //    DEV_PKM = 0;
                    //    KTG_PKM = "LF";
                    //    holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                    //} else {
                    //    DEV_PKM = (currentItem.getPKM() - 1.1)/1.1;
                    //    DEV_PKM = (DEV_PKM * 100) * -1;
                    //    DEV_PKM = Double.parseDouble(df.format(DEV_PKM));
                    //    KTG_PKM = "LS";
                    //    holder.pkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                    //}

                }

                holder.pkm_dev.setText(String.valueOf(DEV_PKM)+"%");
                holder.pkm_hasil.setText(KTG_PKM);
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Bukaan pada Median

        try {
            if(currentItem.getJAB() == -1){
                holder.jab_data.setText("-");
                DEV_JAB = -1;
                KTG_JAB = "-";
                holder.jab_dev.setText("-");
            } else {
                holder.jab_data.setText(String.valueOf(currentItem.getJAB())+" m");
                if(a113Activity.FNG.equals("Arteri") && a113Activity.SJR.equals("Primer (Antar Kota)")){

                    if(currentItem.getJAB() >= 5000){
                        DEV_JAB = 0;
                        KTG_JAB = "LF";
                    } else {
                        DEV_JAB = (currentItem.getJAB() - 5000)/5000;
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

                } else if(a113Activity.FNG.equals("Kolektor") && a113Activity.SJR.equals("Primer (Antar Kota)")){

                    if(currentItem.getJAB() >= 3000){
                        DEV_JAB = 0;
                        KTG_JAB = "LF";
                    } else {
                        DEV_JAB = (currentItem.getJAB() - 3000)/3000;
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

                } else if(a113Activity.FNG.equals("Arteri") && a113Activity.SJR.equals("Sekunder (Dalam Kota)")){

                    if(currentItem.getJAB() >= 500){
                        DEV_JAB = 0;
                        KTG_JAB = "LF";
                    } else {
                        DEV_JAB = (currentItem.getJAB() - 500)/500;
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

                } else if(a113Activity.FNG.equals("Kolektor") && a113Activity.SJR.equals("Sekunder (Dalam Kota)")){

                    if(currentItem.getJAB() >= 300){
                        DEV_JAB = 0;
                        KTG_JAB = "LF";
                    } else {
                        DEV_JAB = (currentItem.getJAB() - 300)/300;
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
                holder.jab_dev.setText(String.valueOf(DEV_JAB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBB() == -1){
                holder.lbb_data.setText("-");
                DEV_LBB = -1;
                KTG_LBB = "-";
                holder.lbb_dev.setText("-");
            } else {
                holder.lbb_data.setText(String.valueOf(currentItem.getLBB())+" m");
                if(a113Activity.FNG.equals("Arteri") && a113Activity.SJR.equals("Primer (Antar Kota)")){

                    if(currentItem.getLBB() >= 7){
                        DEV_LBB = 0;
                        KTG_LBB = "LF";
                    } else {
                        DEV_LBB = (currentItem.getLBB() - 7)/7;
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

                } else if(a113Activity.FNG.equals("Kolektor") && a113Activity.SJR.equals("Primer (Antar Kota)")){

                    if(currentItem.getLBB() >= 4){
                        DEV_LBB = 0;
                        KTG_LBB = "LF";
                    } else {
                        DEV_LBB = (currentItem.getLBB() - 4)/4;
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

                } else if(a113Activity.FNG.equals("Arteri") && a113Activity.SJR.equals("Sekunder (Dalam Kota)")){

                    if(currentItem.getLBB() >= 4){
                        DEV_LBB = 0;
                        KTG_LBB = "LF";
                    } else {
                        DEV_LBB = (currentItem.getLBB() - 4)/4;
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

                } else if(a113Activity.FNG.equals("Kolektor") && a113Activity.SJR.equals("Sekunder (Dalam Kota)")){

                    if(currentItem.getLBB() >= 4){
                        DEV_LBB = 0;
                        KTG_LBB = "LF";
                    } else {
                        DEV_LBB = (currentItem.getLBB() - 4)/4;
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

                holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JAB.equals("LF") || KTG_JAB.equals("-")) && (KTG_LBB.equals("LF") || KTG_LBB.equals("-"))){

                if(KTG_JAB.equals("-") && KTG_LBB.equals("-")) {
                    KTG_BKM = "-";
                    holder.bkm_hasil.setText("-");
                    holder.bkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_BKM = "LF";
                    holder.bkm_hasil.setText(KTG_BKM);
                    holder.bkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_BKM = "LS";
                holder.bkm_hasil.setText(KTG_BKM);
                holder.bkm_back.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_LBM.equals("LF") || KTG_LBM.equals("-")) && (KTG_TPM.equals("LF") || KTG_TPM.equals("-"))
                    && (KTG_PKM.equals("LF") || KTG_PKM.equals("-")) && (KTG_BKM.equals("LF") || KTG_BKM.equals("-"))){

                if(KTG_LBM.equals("-") && KTG_TPM.equals("-") && KTG_PKM.equals("-") && KTG_BKM.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a113Activity.FAB_UPLOAD.hide();
                a113Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a113Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a113Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a113Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a113Activity.ID = currentItem.getID();
            a113Activity.SLBM_TXT = currentItem.getLBMT();
            a113Activity.STPM_TXT = currentItem.getTPMT();
            a113Activity.SPKM_TXT = currentItem.getPKMT();
            a113Activity.LBM_TXT = currentItem.getLBM();
            a113Activity.TPM_TXT = currentItem.getTPM();
            a113Activity.PKM_TXT = currentItem.getPKM();
            a113Activity.JAB_TXT = currentItem.getJAB();
            a113Activity.LBB_TXT = currentItem.getLBB();
            a113Activity.REC_TXT = currentItem.getREC();
            a113Activity.DIR1 = currentItem.getDIR1();
            a113Activity.DIR2 = currentItem.getDIR2();
            a113Activity.DIR3 = currentItem.getDIR3();

            a113Activity.DEV_LBM = DEV_LBM;
            a113Activity.DEV_TPM = DEV_TPM;
            a113Activity.DEV_PKM = DEV_PKM;
            a113Activity.DEV_JAB = DEV_JAB;
            a113Activity.DEV_LBB = DEV_LBB;

            a113Activity.KTG_LBM = KTG_LBM;
            a113Activity.KTG_TPM = KTG_TPM;
            a113Activity.KTG_PKM = KTG_PKM;
            a113Activity.KTG_JAB = KTG_JAB;
            a113Activity.KTG_LBB = KTG_LBB;
            a113Activity.KTG_BKM = KTG_BKM;
            a113Activity.KTG_KLF = KTG_KLF;

            a113Activity.SLBM_TXT = currentItem.getLBMT();
            a113Activity.STPM_TXT = currentItem.getTPMT();
            a113Activity.SPKM_TXT = currentItem.getPKMT();

            if(KTG_KLF.equals("LS")){
                a113Activity.klf.setText(KTG_KLF);
                a113Activity.klf.setBackground(a113Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a113Activity.getApplicationContext(), R.anim.recycle_bottom);
                a113Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a113Activity.klf.setText("Tidak Dinilai");
                a113Activity.klf.setBackground(a113Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a113Activity.getApplicationContext(), R.anim.recycle_bottom);
                a113Activity.klf.startAnimation(animation);
            } else {
                a113Activity.klf.setText(KTG_KLF);
                a113Activity.klf.setBackground(a113Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a113Activity.getApplicationContext(), R.anim.recycle_bottom);
                a113Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a113Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
