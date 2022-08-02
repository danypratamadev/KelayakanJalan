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

public class A133_Adapter extends RecyclerView.Adapter<A133_Adapter.A133ViewHolder> {

    private A133Activity a133Activity;
    private List<A133_Class> mdataList;
    private double DEV_LKB, DEV_KLM, DEV_KCB, DEV_KCK, DEV_JLM, DEV_JPH, DEV_JPM, DEV_AJL, DEV_AJL2, DEV_KVH,
            DEV_KVH2, DEV_KVH3, DEV_KVH4, DEV_KVH5, STD = 100;
    private String KTG_LKB, KTG_KLM, KTG_KCB, KTG_KCK, KTG_KLK, KTG_JLM, KTG_JPH, KTG_JPM, KTG_JPD, KTG_AJL, KTG_AJL2, KTG_AJLL, KTG_KVH,
            KTG_KVH2, KTG_KVH3, KTG_KVH4, KTG_KVH5, KTG_KVHH, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A133ViewHolder extends RecyclerView.ViewHolder {

        private TextView lkb_data, lkb_dev, klm_data, klm_dev, kcb_data, kcb_dev, kck_data, kck_dev, klk_hasil, jlm_data, jlm_dev,
                jph_data, jph_dev, jpm_data, jpm_dev, jpd_hasil, ajl_data, ajl_dev, ajl2_data, ajl2_dev, ajl_hasil,
                kvh_data, kvh_dev, kvh2_data, kvh2_dev, kvh3_data, kvh3_dev, kvh4_data, kvh4_dev, kvh5_data, kvh5_dev, kvh_hasil, rec_data;

        private LinearLayout klk_back, jpd_back, ajl_back, kvh_back;
        private ImageView FT1, FT2, FT3, FT4, FT5;

        public A133ViewHolder(@NonNull View itemView) {
            super(itemView);

            lkb_data = (TextView) itemView.findViewById(R.id.lkb_data);
            lkb_dev = (TextView) itemView.findViewById(R.id.lkb_dev);
            klm_data = (TextView) itemView.findViewById(R.id.klm_data);
            klm_dev = (TextView) itemView.findViewById(R.id.klm_dev);
            kcb_data = (TextView) itemView.findViewById(R.id.kcb_data);
            kcb_dev = (TextView) itemView.findViewById(R.id.kcb_dev);
            kck_data = (TextView) itemView.findViewById(R.id.kck_data);
            kck_dev = (TextView) itemView.findViewById(R.id.kck_dev);
            klk_hasil = (TextView) itemView.findViewById(R.id.klk_hasil);

            jlm_data = (TextView) itemView.findViewById(R.id.jlm_data);
            jlm_dev = (TextView) itemView.findViewById(R.id.jlm_dev);
            jph_data = (TextView) itemView.findViewById(R.id.jph_data);
            jph_dev = (TextView) itemView.findViewById(R.id.jph_dev);
            jpm_data = (TextView) itemView.findViewById(R.id.jpm_data);
            jpm_dev = (TextView) itemView.findViewById(R.id.jpm_dev);
            jpd_hasil = (TextView) itemView.findViewById(R.id.jpd_hasil);

            ajl_data = (TextView) itemView.findViewById(R.id.ajl_data);
            ajl_dev = (TextView) itemView.findViewById(R.id.ajl_dev);
            ajl2_data = (TextView) itemView.findViewById(R.id.ajl2_data);
            ajl2_dev = (TextView) itemView.findViewById(R.id.ajl2_dev);
            ajl_hasil = (TextView) itemView.findViewById(R.id.ajl_hasil);

            kvh_data = (TextView) itemView.findViewById(R.id.kvh_data);
            kvh_dev = (TextView) itemView.findViewById(R.id.kvh_dev);
            kvh2_data = (TextView) itemView.findViewById(R.id.kvh2_data);
            kvh2_dev = (TextView) itemView.findViewById(R.id.kvh2_dev);
            kvh3_data = (TextView) itemView.findViewById(R.id.kvh3_data);
            kvh3_dev = (TextView) itemView.findViewById(R.id.kvh3_dev);
            kvh4_data = (TextView) itemView.findViewById(R.id.kvh4_data);
            kvh4_dev = (TextView) itemView.findViewById(R.id.kvh4_dev);
            kvh5_data = (TextView) itemView.findViewById(R.id.kvh5_data);
            kvh5_dev = (TextView) itemView.findViewById(R.id.kvh5_dev);
            kvh_hasil = (TextView) itemView.findViewById(R.id.kvh_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);
            FT5 = (ImageView) itemView.findViewById(R.id.FT5);

            klk_back = (LinearLayout) itemView.findViewById(R.id.klk_back);
            jpd_back = (LinearLayout) itemView.findViewById(R.id.jpd_back);
            ajl_back = (LinearLayout) itemView.findViewById(R.id.ajl_back);
            kvh_back = (LinearLayout) itemView.findViewById(R.id.kvh_back);

        }

    }

    public A133_Adapter(A133Activity a133Activity, List<A133_Class> mdataList) {
        this.a133Activity = a133Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A133_Adapter.A133ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a133_layout, parent, false);
        A133_Adapter.A133ViewHolder a133 = new A133_Adapter.A133ViewHolder(view);
        return a133;
    }

    @Override
    public void onBindViewHolder(@NonNull A133_Adapter.A133ViewHolder holder, int position) {

        final A133_Class currentItem = mdataList.get(position);

        //Ketajaman Lengkungan

        try {
            if(currentItem.getLKB() == -1){
                DEV_LKB = -1;
                KTG_LKB = "-";
                holder.lkb_data.setText("-");
                holder.lkb_dev.setText("-");
            } else {
                DEV_LKB = STD - currentItem.getLKB();
                if(DEV_LKB == 0){
                    KTG_LKB = "LF";
                } else {
                    KTG_LKB = "LS";
                }
                holder.lkb_data.setText(String.valueOf(currentItem.getLKB())+"%");
                holder.lkb_dev.setText(String.valueOf(DEV_LKB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKLM() == -1){
                DEV_KLM = -1;
                KTG_KLM = "-";
                holder.klm_data.setText("-");
                holder.klm_dev.setText("-");
            } else {
                if(a133Activity.FNG.equals("Arteri")){
                    if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                } else {
                    if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                holder.klm_data.setText(String.valueOf(currentItem.getKLM())+"%");
                holder.klm_dev.setText(String.valueOf(DEV_KLM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKCB() == -1){
                DEV_KCB = -1;
                KTG_KCB = "-";
                holder.kcb_data.setText("-");
                holder.kcb_dev.setText("-");
            } else {
                if(a133Activity.FNG.equals("Arteri")){
                    if(a133Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getKCB() >= 2000){
                            DEV_KCB = 0;
                            KTG_KCB = "LF";
                        } else {
                            DEV_KCB = (currentItem.getKCB() - 2000)/2000;
                            DEV_KCB = (DEV_KCB * 100);
                            if(DEV_KCB < 0){
                                DEV_KCB = DEV_KCB * -1;
                            }
                            if(DEV_KCB > 100){
                                DEV_KCB = 100;
                            }
                            DEV_KCB = Double.parseDouble(df.format(DEV_KCB).replace(",", "."));
                            KTG_KCB = "LS";
                        }
                    } else {
                        if(currentItem.getKCB() >= 400){
                            DEV_KCB = 0;
                            KTG_KCB = "LF";
                        } else {
                            DEV_KCB = (currentItem.getKCB() - 400)/400;
                            DEV_KCB = (DEV_KCB * 100);
                            if(DEV_KCB < 0){
                                DEV_KCB = DEV_KCB * -1;
                            }
                            if(DEV_KCB > 100){
                                DEV_KCB = 100;
                            }
                            DEV_KCB = Double.parseDouble(df.format(DEV_KCB).replace(",", "."));
                            KTG_KCB = "LS";
                        }
                    }
                } else {
                    if(a133Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getKCB() >= 700){
                            DEV_KCB = 0;
                            KTG_KCB = "LF";
                        } else {
                            DEV_KCB = (currentItem.getKCB() - 700)/700;
                            DEV_KCB = (DEV_KCB * 100);
                            if(DEV_KCB < 0){
                                DEV_KCB = DEV_KCB * -1;
                            }
                            if(DEV_KCB > 100){
                                DEV_KCB = 100;
                            }
                            DEV_KCB = Double.parseDouble(df.format(DEV_KCB).replace(",", "."));
                            KTG_KCB = "LS";
                        }
                    } else {
                        if(currentItem.getKCB() >= 200){
                            DEV_KCB = 0;
                            KTG_KCB = "LF";
                        } else {
                            DEV_KCB = (currentItem.getKCB() - 200)/200;
                            DEV_KCB = (DEV_KCB * 100);
                            if(DEV_KCB < 0){
                                DEV_KCB = DEV_KCB * -1;
                            }
                            if(DEV_KCB > 100){
                                DEV_KCB = 100;
                            }
                            DEV_KCB = Double.parseDouble(df.format(DEV_KCB).replace(",", "."));
                            KTG_KCB = "LS";
                        }
                    }
                }
                holder.kcb_data.setText(String.valueOf(currentItem.getKCB())+" m");
                holder.kcb_dev.setText(String.valueOf(DEV_KCB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKCK() == -1){
                DEV_KCK = -1;
                KTG_KCK = "-";
                holder.kck_data.setText("-");
                holder.kck_dev.setText("-");
            } else {
                if(a133Activity.FNG.equals("Arteri")){
                    if(a133Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getKCK() >= 1500){
                            DEV_KCK = 0;
                            KTG_KCK = "LF";
                        } else {
                            DEV_KCK = (currentItem.getKCK() - 1500)/1500;
                            DEV_KCK = (DEV_KCK * 100);
                            if(DEV_KCK < 0){
                                DEV_KCK = DEV_KCK * -1;
                            }
                            if(DEV_KCK > 100){
                                DEV_KCK = 100;
                            }
                            DEV_KCK = Double.parseDouble(df.format(DEV_KCK).replace(",", "."));
                            KTG_KCK = "LS";
                        }
                    } else {
                        if(currentItem.getKCK() >= 400){
                            DEV_KCK = 0;
                            KTG_KCK = "LF";
                        } else {
                            DEV_KCK = (currentItem.getKCK() - 400)/400;
                            DEV_KCK = (DEV_KCK * 100);
                            if(DEV_KCK < 0){
                                DEV_KCK = DEV_KCK * -1;
                            }
                            if(DEV_KCK > 100){
                                DEV_KCK = 100;
                            }
                            DEV_KCK = Double.parseDouble(df.format(DEV_KCK).replace(",", "."));
                            KTG_KCK = "LS";
                        }
                    }
                } else {
                    if(a133Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getKCK() >= 700){
                            DEV_KCK = 0;
                            KTG_KCK = "LF";
                        } else {
                            DEV_KCK = (currentItem.getKCK() - 700)/700;
                            DEV_KCK = (DEV_KCK * 100);
                            if(DEV_KCK < 0){
                                DEV_KCK = DEV_KCK * -1;
                            }
                            if(DEV_KCK > 100){
                                DEV_KCK = 100;
                            }
                            DEV_KCK = Double.parseDouble(df.format(DEV_KCK).replace(",", "."));
                            KTG_KCK = "LS";
                        }
                    } else {
                        if(currentItem.getKCK() >= 200){
                            DEV_KCK = 0;
                            KTG_KCK = "LF";
                        } else {
                            DEV_KCK = (currentItem.getKCK() - 200)/200;
                            DEV_KCK = (DEV_KCK * 100);
                            if(DEV_KCK < 0){
                                DEV_KCK = DEV_KCK * -1;
                            }
                            if(DEV_KCK > 100){
                                DEV_KCK = 100;
                            }
                            DEV_KCK = Double.parseDouble(df.format(DEV_KCK).replace(",", "."));
                            KTG_KCK = "LS";
                        }
                    }
                }
                holder.kck_data.setText(String.valueOf(currentItem.getKCK())+" m");
                holder.kck_dev.setText(String.valueOf(DEV_KCK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LKB.equals("LF") || KTG_LKB.equals("-")) && (KTG_KLM.equals("LF") || KTG_KLM.equals("-")) && (KTG_KCB.equals("LF") || KTG_KCB.equals("-"))
                    && (KTG_KCK.equals("LF") || KTG_KCK.equals("-"))){

                if(KTG_LKB.equals("-") && KTG_KLM.equals("-") && KTG_KCB.equals("-") && KTG_KCK.equals("-")){
                    KTG_KLK = "-";
                    holder.klk_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KLK = "LF";
                    holder.klk_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KLK = "LS";
                holder.klk_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.klk_hasil.setText(KTG_KLK);
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
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
                if(a133Activity.FNG.equals("Arteri")){
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                holder.jlm_dev.setText(String.valueOf(DEV_JLM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getJPH() == -1){
                DEV_JPH = -1;
                KTG_JPH = "-";
                holder.jph_data.setText("-");
                holder.jph_dev.setText("-");
            } else {
                holder.jph_data.setText(String.valueOf(currentItem.getJPH())+" m");
                if(a133Activity.FNG.equals("Arteri")){
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){

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
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){

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
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getJPM() == -1){
                DEV_JPM = -1;
                KTG_JPM = "-";
                holder.jpm_data.setText("-");
                holder.jpm_dev.setText("-");
            } else {
                holder.jpm_data.setText(String.valueOf(currentItem.getJPM())+" m");
                if(a133Activity.FNG.equals("Arteri")){
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                    } else {
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                    }
                } else {
                    if(currentItem.getSJPD().equals("Antar Kota")){
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                    } else {
                        if(a133Activity.SJR.equals("Primer (Antar Kota)")){
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
                    }
                }
                holder.jpm_dev.setText(String.valueOf(DEV_JPM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JLM.equals("LF") || KTG_JLM.equals("-")) && (KTG_JPH.equals("LF") || KTG_JPH.equals("-")) && (KTG_JPM.equals("LF") || KTG_JPM.equals("-"))){

                if(KTG_JLM.equals("-") && KTG_JPH.equals("-") && KTG_JPM.equals("-")){
                    KTG_JPD = "-";
                    holder.jpd_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_JPD = "LF";
                    holder.jpd_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_JPD = "LS";
                holder.jpd_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.jpd_hasil.setText(KTG_JPD);
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Arah Jalan dibalik Lengkungan

        try {
            if(currentItem.getAJL() == -1){
                DEV_AJL = -1;
                KTG_AJL = "-";
                holder.ajl_data.setText("-");
                holder.ajl_dev.setText("-");
            } else {
                DEV_AJL = STD - currentItem.getAJL();
                if(DEV_AJL == 0){
                    KTG_AJL = "LF";
                } else {
                    KTG_AJL = "LS";
                }
                holder.ajl_data.setText(String.valueOf(currentItem.getAJL())+"%");
                holder.ajl_dev.setText(String.valueOf(DEV_AJL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getAJL2() == -1){
                DEV_AJL2 = -1;
                KTG_AJL2 = "-";
                holder.ajl2_data.setText("-");
                holder.ajl2_dev.setText("-");
            } else {
                DEV_AJL2 = STD - currentItem.getAJL2();
                if(DEV_AJL2 == 0){
                    KTG_AJL2 = "LF";
                } else {
                    KTG_AJL2 = "LS";
                }
                holder.ajl2_data.setText(String.valueOf(currentItem.getAJL2())+"%");
                holder.ajl2_dev.setText(String.valueOf(DEV_AJL2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_AJL.equals("LF") || KTG_AJL.equals("-")) && (KTG_AJL2.equals("LF") || KTG_AJL2.equals("-"))){

                if(KTG_AJL.equals("-") && KTG_AJL2.equals("-")){
                    KTG_AJLL = "-";
                    holder.ajl_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_AJLL = "LF";
                    holder.ajl_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_AJLL = "LS";
                holder.ajl_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ajl_hasil.setText(KTG_AJLL);
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kombinasi Lengkung Vertikal dan Tikungan Horizontal

        try {
            if(currentItem.getKVH() == -1){
                DEV_KVH = -1;
                KTG_KVH = "-";
                holder.kvh_data.setText("-");
                holder.kvh_dev.setText("-");
            } else {
                DEV_KVH = STD - currentItem.getKVH();
                if(DEV_KVH == 0){
                    KTG_KVH = "LF";
                } else {
                    KTG_KVH = "LS";
                }
                holder.kvh_data.setText(String.valueOf(currentItem.getKVH())+"%");
                holder.kvh_dev.setText(String.valueOf(DEV_KVH)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKVH2() == -1){
                DEV_KVH2 = -1;
                KTG_KVH2 = "-";
                holder.kvh2_data.setText("-");
                holder.kvh2_dev.setText("-");
            } else {
                DEV_KVH2 = STD - currentItem.getKVH2();
                if(DEV_KVH2 == 0){
                    KTG_KVH2 = "LF";
                } else {
                    KTG_KVH2 = "LS";
                }
                holder.kvh2_data.setText(String.valueOf(currentItem.getKVH2())+"%");
                holder.kvh2_dev.setText(String.valueOf(DEV_KVH2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKVH3() == -1){
                DEV_KVH3 = -1;
                KTG_KVH3 = "-";
                holder.kvh3_data.setText("-");
                holder.kvh3_dev.setText("-");
            } else {
                DEV_KVH3 = STD - currentItem.getKVH3();
                if(DEV_KVH3 == 0){
                    KTG_KVH3 = "LF";
                } else {
                    KTG_KVH3 = "LS";
                }
                holder.kvh3_data.setText(String.valueOf(currentItem.getKVH3())+"%");
                holder.kvh3_dev.setText(String.valueOf(DEV_KVH3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKVH4() == -1){
                DEV_KVH4 = -1;
                KTG_KVH4 = "-";
                holder.kvh4_data.setText("-");
                holder.kvh4_dev.setText("-");
            } else {
                DEV_KVH4 = STD - currentItem.getKVH4();
                if(DEV_KVH4 == 0){
                    KTG_KVH4 = "LF";
                } else {
                    KTG_KVH4 = "LS";
                }
                holder.kvh4_data.setText(String.valueOf(currentItem.getKVH4())+"%");
                holder.kvh4_dev.setText(String.valueOf(DEV_KVH4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKVH5() == -1){
                DEV_KVH5 = -1;
                KTG_KVH5 = "-";
                holder.kvh5_data.setText("-");
                holder.kvh5_dev.setText("-");
            } else {
                DEV_KVH5 = STD - currentItem.getKVH5();
                if(DEV_KVH5 == 0){
                    KTG_KVH5 = "LF";
                } else {
                    KTG_KVH5 = "LS";
                }
                holder.kvh5_data.setText(String.valueOf(currentItem.getKVH5())+"%");
                holder.kvh5_dev.setText(String.valueOf(DEV_KVH5)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KVH.equals("LF") || KTG_KVH.equals("-")) && (KTG_KVH2.equals("LF") || KTG_KVH2.equals("-"))
                    && (KTG_KVH3.equals("LF") || KTG_KVH3.equals("-")) && (KTG_KVH4.equals("LF") || KTG_KVH4.equals("-"))
                    && (KTG_KVH5.equals("LF") || KTG_KVH5.equals("-"))){

                if(KTG_KVH.equals("-") && KTG_KVH2.equals("-") && KTG_KVH3.equals("-") && KTG_KVH4.equals("-") && KTG_KVH5.equals("-")){
                    KTG_KVHH = "-";
                    holder.kvh_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KVHH = "LF";
                    holder.kvh_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KVHH = "LS";
                holder.kvh_back.setBackground(a133Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kvh_hasil.setText(KTG_KVHH);
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_KLK.equals("LF") || KTG_KLK.equals("-")) && (KTG_JPD.equals("LF") || KTG_JPD.equals("-"))
                    && (KTG_AJLL.equals("LF") || KTG_AJLL.equals("-")) && (KTG_KVHH.equals("LF") || KTG_KVHH.equals("-"))){

                if(KTG_KLK.equals("-") && KTG_JPD.equals("-") && KTG_AJLL.equals("-") && KTG_KVHH.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a133Activity.FAB_UPLOAD.hide();
                a133Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a133Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a133Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a133Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a133Activity.ID = currentItem.getID();
            a133Activity.LKB_TXT = currentItem.getLKB();
            a133Activity.KLM_TXT = currentItem.getKLM();
            a133Activity.KCB_TXT = currentItem.getKCB();
            a133Activity.KCK_TXT = currentItem.getKCK();
            a133Activity.SJPD_TXT = currentItem.getSJPD();
            a133Activity.JLM_TXT = currentItem.getJLM();
            a133Activity.JPH_TXT = currentItem.getJPH();
            a133Activity.JPM_TXT = currentItem.getJPM();
            a133Activity.AJL_TXT = currentItem.getAJL();
            a133Activity.AJL2_TXT = currentItem.getAJL2();
            a133Activity.KVH_TXT = currentItem.getKVH();
            a133Activity.KVH2_TXT = currentItem.getKVH2();
            a133Activity.KVH3_TXT = currentItem.getKVH3();
            a133Activity.KVH4_TXT = currentItem.getKVH4();
            a133Activity.KVH5_TXT = currentItem.getKVH5();
            a133Activity.REC_TXT = currentItem.getREC();
            a133Activity.DIR1 = currentItem.getDIR1();
            a133Activity.DIR2 = currentItem.getDIR2();
            a133Activity.DIR3 = currentItem.getDIR3();
            a133Activity.DIR4 = currentItem.getDIR4();

            a133Activity.DEV_LKB = DEV_LKB;
            a133Activity.DEV_KLM = DEV_KLM;
            a133Activity.DEV_KCB = DEV_KCB;
            a133Activity.DEV_KCK = DEV_KCK;
            a133Activity.DEV_JLM = DEV_JLM;
            a133Activity.DEV_JPH = DEV_JPH;
            a133Activity.DEV_JPM = DEV_JPM;
            a133Activity.DEV_AJL = DEV_AJL;
            a133Activity.DEV_AJL2 = DEV_AJL2;
            a133Activity.DEV_KVH = DEV_KVH;
            a133Activity.DEV_KVH2 = DEV_KVH2;
            a133Activity.DEV_KVH3 = DEV_KVH3;
            a133Activity.DEV_KVH4 = DEV_KVH4;
            a133Activity.DEV_KVH5 = DEV_KVH5;

            a133Activity.KTG_KLK = KTG_KLK;
            a133Activity.KTG_JPD = KTG_JPD;
            a133Activity.KTG_AJLL = KTG_AJLL;
            a133Activity.KTG_KVHH = KTG_KVHH;
            a133Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a133Activity.klf.setText(KTG_KLF);
                a133Activity.klf.setBackground(a133Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a133Activity.getApplicationContext(), R.anim.recycle_bottom);
                a133Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a133Activity.klf.setText("Tidak Dinilai");
                a133Activity.klf.setBackground(a133Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a133Activity.getApplicationContext(), R.anim.recycle_bottom);
                a133Activity.klf.startAnimation(animation);
            } else {
                a133Activity.klf.setText(KTG_KLF);
                a133Activity.klf.setBackground(a133Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a133Activity.getApplicationContext(), R.anim.recycle_bottom);
                a133Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a133Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
