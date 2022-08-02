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

public class A131_Adapter extends RecyclerView.Adapter<A131_Adapter.A131ViewHolder> {

    private A131Activity a131Activity;
    private List<A131_Class> mdataList;
    private double DEV_KLM, DEV_PLK, DEV_JLM, DEV_JPH, DEV_JPM, DEV_LKJ, STD = 100;
    private String KTG_KLM, KTG_PLK, KTG_KLM2, KTG_JLM, KTG_JPH, KTG_JPM, KTG_JPD, KTG_LKJ, KTG_KLF;
    private int SLKJ_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A131ViewHolder extends RecyclerView.ViewHolder {

        private TextView kml_data, kml_dev, plk_data, plk_dev, klm_hasil, jlm_data, jlm_dev, jph_data,
                jph_dev, jpm_data, jpm_dev, jpd_hasil, slkj_data, lkj_data, lkj_dev, lkj_hasil, rec_data;
        private LinearLayout klm_back, jpd_back, lkj_back;
        private ImageView FT1, FT2, FT3, FT4;

        public A131ViewHolder(@NonNull View itemView) {
            super(itemView);

            kml_data = (TextView) itemView.findViewById(R.id.kml_data);
            kml_dev = (TextView) itemView.findViewById(R.id.kml_dev);
            plk_data = (TextView) itemView.findViewById(R.id.plk_data);
            plk_dev = (TextView) itemView.findViewById(R.id.plk_dev);
            klm_hasil = (TextView) itemView.findViewById(R.id.klm_hasil);

            jlm_data = (TextView) itemView.findViewById(R.id.jlm_data);
            jlm_dev = (TextView) itemView.findViewById(R.id.jlm_dev);
            jph_data = (TextView) itemView.findViewById(R.id.jph_data);
            jph_dev = (TextView) itemView.findViewById(R.id.jph_dev);
            jpm_data = (TextView) itemView.findViewById(R.id.jpm_data);
            jpm_dev = (TextView) itemView.findViewById(R.id.jpm_dev);
            jpd_hasil = (TextView) itemView.findViewById(R.id.jpd_hasil);

            slkj_data = (TextView) itemView.findViewById(R.id.slkj_data);
            lkj_data = (TextView) itemView.findViewById(R.id.lkj_data);
            lkj_dev = (TextView) itemView.findViewById(R.id.lkj_dev);
            lkj_hasil = (TextView) itemView.findViewById(R.id.lkj_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);

            klm_back = (LinearLayout) itemView.findViewById(R.id.klm_back);
            jpd_back = (LinearLayout) itemView.findViewById(R.id.jpd_back);
            lkj_back = (LinearLayout) itemView.findViewById(R.id.lkj_back);

        }

    }

    public A131_Adapter(A131Activity a131Activity, List<A131_Class> mdataList) {
        this.a131Activity = a131Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A131_Adapter.A131ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a131_layout, parent, false);
        A131_Adapter.A131ViewHolder a131 = new A131_Adapter.A131ViewHolder(view);
        return a131;
    }

    @Override
    public void onBindViewHolder(@NonNull A131_Adapter.A131ViewHolder holder, int position) {

        final A131_Class currentItem = mdataList.get(position);

        //Kelandaian Memanjang

        try {
            if(currentItem.getKLM() == -1){
                DEV_KLM = -1;
                KTG_KLM = "-";
                holder.kml_data.setText("-");
                holder.kml_dev.setText("-");
            } else {
                holder.kml_data.setText(String.valueOf(currentItem.getKLM())+"%");
                if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                    if(a131Activity.MJL.equals("Datar (D)") && a131Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        if(currentItem.getKLM() <= 4){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 4)/4;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Datar (D)") && a131Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getKLM() <= 5){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 5)/5;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Datar (D)") && a131Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getKLM() <= 6){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 6)/6;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Datar (D)") && a131Activity.KPR.equals("Jalan Kecil (JK)")){
                        if(currentItem.getKLM() <= 6){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 6)/6;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Bukit (B)") && a131Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        if(currentItem.getKLM() <= 5){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 5)/5;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Bukit (B)") && a131Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getKLM() <= 6){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 6)/6;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Bukit (B)") && a131Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getKLM() <= 7){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 7)/7;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Bukit (B)") && a131Activity.KPR.equals("Jalan Kecil (JK)")){
                        if(currentItem.getKLM() <= 8){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 8)/8;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Gunung (G)") && a131Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        if(currentItem.getKLM() <= 6){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 6)/6;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Gunung (G)") && a131Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getKLM() <= 10){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 10)/10;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.MJL.equals("Gunung (G)") && a131Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getKLM() <= 10){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 10)/10;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else {
                        if(currentItem.getKLM() <= 12){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 12)/12;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    }
                } else {
                    if(a131Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        if(currentItem.getKLM() <= 4){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 4)/4;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getKLM() <= 5){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 5)/5;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else if(a131Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getKLM() <= 6){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 6)/6;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    } else {
                        if(currentItem.getKLM() <= 10){
                            DEV_KLM = 0;
                            KTG_KLM = "LF";
                        } else {
                            DEV_KLM = (currentItem.getKLM() - 10)/10;
                            DEV_KLM = (DEV_KLM * 100);
                            if(DEV_KLM < 0){
                                DEV_KLM = DEV_KLM * -1;
                            }
                            if(DEV_KLM > 100){
                                DEV_KLM = 100;
                            }
                            DEV_KLM = Double.parseDouble(df.format(DEV_KLM).replace(",", "."));
                            KTG_KLM = "LS";
                        }
                    }
                }
                holder.kml_dev.setText(String.valueOf(DEV_KLM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPLK() == -1){
                DEV_PLK = -1;
                KTG_PLK = "-";
                holder.plk_data.setText("-");
                holder.plk_dev.setText("-");
            } else {
                holder.plk_data.setText(String.valueOf(currentItem.getPLK()));
                if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                    if(currentItem.getKCP() >= 60 && currentItem.getKCP() < 80){
                        if(currentItem.getKLM() >= 4 && currentItem.getKLM() < 5){
                            if(currentItem.getPLK() <= 320){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 320)/320;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 5 && currentItem.getKLM() < 6){
                            if(currentItem.getPLK() <= 210){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 210)/210;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 6 && currentItem.getKLM() < 7){
                            if(currentItem.getPLK() <= 160){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 160)/160;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 7 && currentItem.getKLM() < 8){
                            if(currentItem.getPLK() <= 120){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 120)/120;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 8 && currentItem.getKLM() < 9){
                            if(currentItem.getPLK() <= 110){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 110)/110;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 9 && currentItem.getKLM() < 10){
                            if(currentItem.getPLK() <= 90){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 90)/90;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 10 && currentItem.getKLM() < 11){
                            if(currentItem.getPLK() <= 80){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 80)/80;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        }
                    } else if(currentItem.getKCP() >= 80){
                        if(currentItem.getKLM() >= 4 && currentItem.getKLM() < 5){
                            if(currentItem.getPLK() <= 630){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 630)/630;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 5 && currentItem.getKLM() < 6){
                            if(currentItem.getPLK() <= 460){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 460)/460;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 6 && currentItem.getKLM() < 7){
                            if(currentItem.getPLK() <= 360){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 360)/360;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 7 && currentItem.getKLM() < 8){
                            if(currentItem.getPLK() <= 270){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 270)/270;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 8 && currentItem.getKLM() < 9){
                            if(currentItem.getPLK() <= 230){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 230)/230;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 9 && currentItem.getKLM() < 10){
                            if(currentItem.getPLK() <= 230){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 230)/230;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 10 && currentItem.getKLM() < 11){
                            if(currentItem.getPLK() <= 200){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 200)/200;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        }
                    }
                } else {
                    if(currentItem.getKCP() >= 40 && currentItem.getKCP() < 50){
                        if(currentItem.getKLM() >= 8 && currentItem.getKLM() < 9){
                            if(currentItem.getPLK() <= 400){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 400)/400;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 9 && currentItem.getKLM() < 10){
                            if(currentItem.getPLK() <= 300){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 300)/300;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 10 && currentItem.getKLM() < 11){
                            if(currentItem.getPLK() <= 200){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 200)/200;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        }
                    } else if(currentItem.getKCP() >= 50 && currentItem.getKCP() < 60){
                        if(currentItem.getKLM() >= 7 && currentItem.getKLM() < 8){
                            if(currentItem.getPLK() <= 500){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 500)/500;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 8 && currentItem.getKLM() < 9){
                            if(currentItem.getPLK() <= 400){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 400)/400;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 9 && currentItem.getKLM() < 10){
                            if(currentItem.getPLK() <= 300){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 300)/300;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        }
                    } else if(currentItem.getKCP() >= 60 && currentItem.getKCP() < 80) {
                        if(currentItem.getKLM() >= 6 && currentItem.getKLM() < 7){
                            if(currentItem.getPLK() <= 500){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 500)/500;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 7 && currentItem.getKLM() < 8){
                            if(currentItem.getPLK() <= 400){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 400)/400;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 8 && currentItem.getKLM() < 9){
                            if(currentItem.getPLK() <= 300){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 300)/300;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else {
                            DEV_PLK = (currentItem.getPLK() - 500)/500;
                            DEV_PLK = (DEV_PLK * 100);
                            if(DEV_PLK < 0){
                                DEV_PLK = DEV_PLK * -1;
                            }
                            if(DEV_PLK > 100){
                                DEV_PLK = 100;
                            }
                            DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                            KTG_PLK = "LS";
                        }
                    } else if(currentItem.getKCP() >= 80 && currentItem.getKCP() < 100){
                        if(currentItem.getKLM() >= 5 && currentItem.getKLM() < 6){
                            if(currentItem.getPLK() <= 600){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 600)/600;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 6 && currentItem.getKLM() < 7){
                            if(currentItem.getPLK() <= 500){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 500)/500;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 7 && currentItem.getKLM() < 8){
                            if(currentItem.getPLK() <= 400){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 400)/400;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        }
                    } else if(currentItem.getKCP() >= 100){
                        if(currentItem.getKLM() >= 4 && currentItem.getKLM() < 5){
                            if(currentItem.getPLK() <= 700){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 700)/700;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 5 && currentItem.getKLM() < 6){
                            if(currentItem.getPLK() <= 500){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 500)/500;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else if(currentItem.getKLM() >= 6 && currentItem.getKLM() < 7){
                            if(currentItem.getPLK() <= 400){
                                DEV_PLK = 0;
                                KTG_PLK = "LF";
                            } else {
                                DEV_PLK = (currentItem.getPLK() - 400)/400;
                                DEV_PLK = (DEV_PLK * 100);
                                if(DEV_PLK < 0){
                                    DEV_PLK = DEV_PLK * -1;
                                }
                                if(DEV_PLK > 100){
                                    DEV_PLK = 100;
                                }
                                DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                                KTG_PLK = "LS";
                            }
                        } else {
                            DEV_PLK = (currentItem.getPLK() - 500)/500;
                            DEV_PLK = (DEV_PLK * 100);
                            if(DEV_PLK < 0){
                                DEV_PLK = DEV_PLK * -1;
                            }
                            if(DEV_PLK > 100){
                                DEV_PLK = 100;
                            }
                            DEV_PLK = Double.parseDouble(df.format(DEV_PLK).replace(",", "."));
                            KTG_PLK = "LS";
                        }
                    }
                }
                holder.plk_dev.setText(String.valueOf(DEV_PLK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KLM.equals("LF") || KTG_KLM.equals("-")) && (KTG_PLK.equals("LF") || KTG_PLK.equals("-"))){

                if(KTG_KLM.equals("-") && KTG_PLK.equals("-")){
                    KTG_KLM2 = "-";
                    holder.klm_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KLM2 = "LF";
                    holder.klm_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KLM2 = "LS";
                holder.klm_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.klm_hasil.setText(KTG_KLM2);
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Jarak Pandang

        try {
            if(currentItem.getJLM() == -1){
                DEV_JLM = -1;
                KTG_JLM = "-";
                holder.jlm_data.setText("-");
                holder.jlm_dev.setText("-");
            } else {
                holder.jlm_data.setText(String.valueOf(currentItem.getJLM())+"%");
                if(a131Activity.FNG.equals("Arteri")){
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJLM() <= 8){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 8)/8;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }

                        } else {
                            if(currentItem.getJLM() <= 10){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 10)/10;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }

                        }

                    } else {
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJLM() <= 5){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 5)/5;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }

                        } else {
                            if(currentItem.getJLM() <= 8){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 8)/8;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }

                        }
                    }
                } else {
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJLM() <= 10){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 10)/10;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }

                        } else {
                            if(currentItem.getJLM() <= 10){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 10)/10;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }

                        }

                    } else {
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJLM() <= 7){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 7)/7;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }

                        } else {
                            if(currentItem.getJLM() <= 9){
                                DEV_JLM = 0;
                                KTG_JLM = "LF";
                            } else {
                                DEV_JLM = (currentItem.getJLM() - 9)/9;
                                DEV_JLM = (DEV_JLM * 100);
                                if(DEV_JLM < 0){
                                    DEV_JLM = DEV_JLM * -1;
                                }
                                if(DEV_JLM > 100){
                                    DEV_JLM = 100;
                                }
                                DEV_JLM = Double.parseDouble(df.format(DEV_JLM).replace(",", "."));
                                KTG_JLM = "LS";
                            }
                        }
                    }
                }
                holder.jlm_dev.setText(String.valueOf(DEV_JLM));
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getJPH() == -1){
                DEV_JPH = -1;
                KTG_JPH = "-";
                holder.jph_data.setText("-");
                holder.jph_dev.setText("-");
            } else {
                holder.jph_data.setText(String.valueOf(currentItem.getJPH())+" m");
                if(a131Activity.FNG.equals("Arteri")){
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){

                            if(currentItem.getJPH() >= 75){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 75)/75;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }

                        } else {

                            if(currentItem.getJPH() >= 30){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 30)/30;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }

                        }

                    } else {
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){

                            if(currentItem.getJPH() >= 85){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 85)/85;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }

                        } else {
                            if(currentItem.getJPH() >= 35){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 35)/35;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }

                        }
                    }
                } else {
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJPH() >= 40){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 40)/40;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }

                        } else {
                            if(currentItem.getJPH() >= 20){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 20)/20;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }

                        }

                    } else {
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJPH() >= 50){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 50)/50;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }

                        } else {
                            if(currentItem.getJPH() >= 20){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 20)/20;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        }
                    }
                }
                holder.jph_dev.setText(String.valueOf(DEV_JPH)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getJPM() == -1){
                DEV_JPM = -1;
                KTG_JPM = "-";
                holder.jpm_data.setText("-");
                holder.jpm_dev.setText("-");
            } else {
                holder.jpm_data.setText(String.valueOf(currentItem.getJPM())+" m");
                if(a131Activity.FNG.equals("Arteri")){
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJPM() >= 250){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 250)/250;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }

                        } else {
                            if(currentItem.getJPM() >= 100){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 100)/100;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        }
                    } else {
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJPM() >= 350){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 350)/350;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }

                        } else {
                            if(currentItem.getJPM() >= 150){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 150)/150;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }

                        }
                    }
                } else {
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJPM() >= 150){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 150)/150;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }

                        } else {
                            if(currentItem.getJPM() >= 70){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 70)/70;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }

                        }

                    } else {
                        if(a131Activity.SJR.equals("Primer (Antar Kota)")){
                            if(currentItem.getJPM() >= 200){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 200)/200;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }

                        } else {
                            if(currentItem.getJPM() >= 100){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 100)/100;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }

                        }
                    }
                }
                holder.jpm_dev.setText(String.valueOf(DEV_JPM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JLM.equals("LF") || KTG_JLM.equals("-")) && (KTG_JPH.equals("LF") || KTG_JPH.equals("-")) && (KTG_JPM.equals("LF") || KTG_JPM.equals("-"))){

                if(KTG_JLM.equals("-") && KTG_JPH.equals("-") && KTG_JPM.equals("-")){
                    KTG_JPD = "-";
                    holder.jpd_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_JPD = "LF";
                    holder.jpd_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_JPD = "LS";
                holder.jpd_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.jpd_hasil.setText(KTG_JPD);
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Lingkungan Jalan

        try {
            if(currentItem.getSLKJ().equals("Permukiman")){
                SLKJ_IN = 1;
            } else if(currentItem.getSLKJ().equals("Komersial")){
                SLKJ_IN = 2;
            } else {
                SLKJ_IN = 3;
            }

            if(currentItem.getLKJ() == -1){
                DEV_LKJ = -1;
                KTG_LKJ = "-";
                holder.slkj_data.setText("-");
                holder.lkj_data.setText("-");
                holder.lkj_dev.setText("-");
                holder.lkj_hasil.setText(KTG_LKJ);
                holder.lkj_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.slkj_data.setText("("+String.valueOf(SLKJ_IN)+") "+currentItem.getSLKJ());
                holder.lkj_data.setText(String.valueOf(currentItem.getLKJ())+"%");
                DEV_LKJ = STD - currentItem.getLKJ();
                if(DEV_LKJ == 0){
                    KTG_LKJ = "LF";
                    holder.lkj_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_LKJ = "LS";
                    holder.lkj_back.setBackground(a131Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.lkj_dev.setText(String.valueOf(DEV_LKJ)+"%");
                holder.lkj_hasil.setText(KTG_LKJ);
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_KLM2.equals("LF") || KTG_KLM2.equals("-")) && (KTG_JPD.equals("LF") || KTG_JPD.equals("-"))
                    && (KTG_LKJ.equals("LF") || KTG_LKJ.equals("-"))){

                if(KTG_KLM2.equals("-") && KTG_JPD.equals("-") && KTG_LKJ.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a131Activity.FAB_UPLOAD.hide();
                a131Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a131Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a131Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a131Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a131Activity.ID = currentItem.getID();
            a131Activity.KLM_TXT = currentItem.getKLM();
            a131Activity.PLK_TXT = currentItem.getPLK();
            a131Activity.SLKJ_IN = SLKJ_IN;
            a131Activity.SLKJ_TXT = currentItem.getSLKJ();
            a131Activity.SJPD_TXT = currentItem.getSJPD();
            a131Activity.JLM_TXT = currentItem.getJLM();
            a131Activity.JPH_TXT = currentItem.getJPH();
            a131Activity.JPM_TXT = currentItem.getJPM();
            a131Activity.LKJ_TXT = currentItem.getLKJ();
            a131Activity.REC_TXT = currentItem.getREC();
            a131Activity.DIR1 = currentItem.getDIR1();
            a131Activity.DIR2 = currentItem.getDIR2();
            a131Activity.DIR3 = currentItem.getDIR3();
            a131Activity.DIR4 = currentItem.getDIR4();

            a131Activity.DEV_KLM = DEV_KLM;
            a131Activity.DEV_PLK = DEV_PLK;
            a131Activity.DEV_JLM = DEV_JLM;
            a131Activity.DEV_JPH = DEV_JPH;
            a131Activity.DEV_JPM = DEV_JPM;
            a131Activity.DEV_LKJ = DEV_LKJ;

            a131Activity.KTG_KLM2 = KTG_KLM2;
            a131Activity.KTG_JPD = KTG_JPD;
            a131Activity.KTG_LKJ = KTG_LKJ;
            a131Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a131Activity.klf.setText(KTG_KLF);
                a131Activity.klf.setBackground(a131Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a131Activity.getApplicationContext(), R.anim.recycle_bottom);
                a131Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a131Activity.klf.setText("Tidak Dinilai");
                a131Activity.klf.setBackground(a131Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a131Activity.getApplicationContext(), R.anim.recycle_bottom);
                a131Activity.klf.startAnimation(animation);
            } else {
                a131Activity.klf.setText(KTG_KLF);
                a131Activity.klf.setBackground(a131Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a131Activity.getApplicationContext(), R.anim.recycle_bottom);
                a131Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a131Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
