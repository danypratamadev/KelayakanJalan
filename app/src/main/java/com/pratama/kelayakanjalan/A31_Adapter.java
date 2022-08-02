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

public class A31_Adapter extends RecyclerView.Adapter<A31_Adapter.A31ViewHolder> {

    private A31Activity a31Activity;
    private List<A31_Class> mdataList;
    private double DEV_JLL, DEV_JPK, DEV_TGI, DEV_LDI, DEV_PNI, DEV_LBI, DEV_GTI, DEV_KTI, DEV_KJB, DEV_KJB2, DEV_KJB3, DEV_KJB4, DEV_KJB5,
            DEV_KJB6, DEV_KJB7, DEV_KJB8, DEV_TUM, DEV_TSD, DEV_TKK, DEV_TUP, DEV_TUP2, DEV_TUK, DEV_TBK, DEV_TTP, DEV_TAP, STD = 100;
    private String KTG_JLL, KTG_JPK, KTG_TGI, KTG_LDI, KTG_PNI, KTG_LBI, KTG_GTI, KTG_KTI, KTG_KTJ, KTG_KJB, KTG_KJB2, KTG_KJB3, KTG_KJB4, KTG_KJB5,
            KTG_KJB6, KTG_KJB7, KTG_KJB8, KTG_KJBB, KTG_TUM, KTG_TSD, KTG_TKK, KTG_TUP, KTG_TUP2, KTG_TUK, KTG_TBK, KTG_TTP, KTG_TAP, KTG_FPH, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A31ViewHolder extends RecyclerView.ViewHolder {

        private TextView jll_data, jll_dev, jll_hasil, jpk_data, jpk_dev, jpk_hasil, tgi_data, tgi_dev, ldi_data, ldi_dev, pni_data, pni_dev, lbi_data,
                lbi_dev, gti_data, gti_dev, kti_data, kti_dev, ktj_hasil, kjb_data, kjb_dev, kjb2_data, kjb2_dev, kjb3_data, kjb3_dev, kjb4_data, kjb4_dev, kjb5_data,
                kjb5_dev, kjb6_data, kjb6_dev, kjb7_data, kjb7_dev, kjb8_data, kjb8_dev, kjb_hasil, tum_data, tum_dev, tsd_data, tsd_dev, tkk_data, tkk_dev,
                tup_data, tup_dev, tup2_data, tup2_dev, tuk_data, tuk_dev, tbk_data, tbk_dev, ttp_data, ttp_dev, tap_data, tap_dev, fph_hasil, rec_data;

        private LinearLayout jll_back, jpk_back, ktj_back, kjb_back, fph_back;
        private ImageView FT1, FT2, FT3, FT4, FT5, FT6;

        public A31ViewHolder(@NonNull View itemView) {
            super(itemView);

            jll_data = (TextView) itemView.findViewById(R.id.jll_data);
            jll_dev = (TextView) itemView.findViewById(R.id.jll_dev);
            jll_hasil = (TextView) itemView.findViewById(R.id.jll_hasil);

            jpk_data = (TextView) itemView.findViewById(R.id.jpk_data);
            jpk_dev = (TextView) itemView.findViewById(R.id.jpk_dev);
            jpk_hasil = (TextView) itemView.findViewById(R.id.jpk_hasil);

            tgi_data = (TextView) itemView.findViewById(R.id.tgi_data);
            tgi_dev = (TextView) itemView.findViewById(R.id.tgi_dev);
            ldi_data = (TextView) itemView.findViewById(R.id.ldi_data);
            ldi_dev = (TextView) itemView.findViewById(R.id.ldi_dev);
            pni_data = (TextView) itemView.findViewById(R.id.pni_data);
            pni_dev = (TextView) itemView.findViewById(R.id.pni_dev);
            lbi_data = (TextView) itemView.findViewById(R.id.lbi_data);
            lbi_dev = (TextView) itemView.findViewById(R.id.lbi_dev);
            gti_data = (TextView) itemView.findViewById(R.id.gti_data);
            gti_dev = (TextView) itemView.findViewById(R.id.gti_dev);
            kti_data = (TextView) itemView.findViewById(R.id.kti_data);
            kti_dev = (TextView) itemView.findViewById(R.id.kti_dev);
            ktj_hasil = (TextView) itemView.findViewById(R.id.ktj_hasil);

            kjb_data = (TextView) itemView.findViewById(R.id.kjb_data);
            kjb_dev = (TextView) itemView.findViewById(R.id.kjb_dev);
            kjb2_data = (TextView) itemView.findViewById(R.id.kjb2_data);
            kjb2_dev = (TextView) itemView.findViewById(R.id.kjb2_dev);
            kjb3_data = (TextView) itemView.findViewById(R.id.kjb3_data);
            kjb3_dev = (TextView) itemView.findViewById(R.id.kjb3_dev);
            kjb4_data = (TextView) itemView.findViewById(R.id.kjb4_data);
            kjb4_dev = (TextView) itemView.findViewById(R.id.kjb4_dev);
            kjb5_data = (TextView) itemView.findViewById(R.id.kjb5_data);
            kjb5_dev = (TextView) itemView.findViewById(R.id.kjb5_dev);
            kjb6_data = (TextView) itemView.findViewById(R.id.kjb6_data);
            kjb6_dev = (TextView) itemView.findViewById(R.id.kjb6_dev);
            kjb7_data = (TextView) itemView.findViewById(R.id.kjb7_data);
            kjb7_dev = (TextView) itemView.findViewById(R.id.kjb7_dev);
            kjb8_data = (TextView) itemView.findViewById(R.id.kjb8_data);
            kjb8_dev = (TextView) itemView.findViewById(R.id.kjb8_dev);
            kjb_hasil = (TextView) itemView.findViewById(R.id.kjb_hasil);

            tum_data = (TextView) itemView.findViewById(R.id.tum_data);
            tum_dev = (TextView) itemView.findViewById(R.id.tum_dev);
            tsd_data = (TextView) itemView.findViewById(R.id.tsd_data);
            tsd_dev = (TextView) itemView.findViewById(R.id.tsd_dev);
            tkk_data = (TextView) itemView.findViewById(R.id.tkk_data);
            tkk_dev = (TextView) itemView.findViewById(R.id.tkk_dev);
            tup_data = (TextView) itemView.findViewById(R.id.tup_data);
            tup_dev = (TextView) itemView.findViewById(R.id.tup_dev);
            tup2_data = (TextView) itemView.findViewById(R.id.tup2_data);
            tup2_dev = (TextView) itemView.findViewById(R.id.tup2_dev);
            tuk_data = (TextView) itemView.findViewById(R.id.tuk_data);
            tuk_dev = (TextView) itemView.findViewById(R.id.tuk_dev);
            tbk_data = (TextView) itemView.findViewById(R.id.tbk_data);
            tbk_dev = (TextView) itemView.findViewById(R.id.tbk_dev);
            ttp_data = (TextView) itemView.findViewById(R.id.ttp_data);
            ttp_dev = (TextView) itemView.findViewById(R.id.ttp_dev);
            tap_data = (TextView) itemView.findViewById(R.id.tap_data);
            tap_dev = (TextView) itemView.findViewById(R.id.tap_dev);
            fph_hasil = (TextView) itemView.findViewById(R.id.fph_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);
            FT5 = (ImageView) itemView.findViewById(R.id.FT5);
            FT6 = (ImageView) itemView.findViewById(R.id.FT6);

            jll_back = (LinearLayout) itemView.findViewById(R.id.jll_back);
            jpk_back = (LinearLayout) itemView.findViewById(R.id.jpk_back);
            ktj_back = (LinearLayout) itemView.findViewById(R.id.ktj_back);
            kjb_back = (LinearLayout) itemView.findViewById(R.id.kjb_back);
            fph_back = (LinearLayout) itemView.findViewById(R.id.fph_back);

        }

    }

    public A31_Adapter(A31Activity a31Activity, List<A31_Class> mdataList) {
        this.a31Activity = a31Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A31_Adapter.A31ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a31_layout, parent, false);
        A31_Adapter.A31ViewHolder a31 = new A31_Adapter.A31ViewHolder(view);
        return a31;
    }

    @Override
    public void onBindViewHolder(@NonNull A31_Adapter.A31ViewHolder holder, int position) {

        final A31_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getJLL() == -1){
                DEV_JLL = -1;
                KTG_JLL = "-";
                holder.jll_data.setText("-");
                holder.jll_dev.setText("-");
                holder.jll_hasil.setText(KTG_JLL);
                holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(a31Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                    if(currentItem.getJLL() >= 14){
                        DEV_JLL = 0;
                        KTG_JLL = "LF";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JLL = (currentItem.getJLL() - 14)/14;
                        DEV_JLL = (DEV_JLL * 100);
                        if(DEV_JLL < 0){
                            DEV_JLL = DEV_JLL * -1;
                        }
                        if(DEV_JLL > 100){
                            DEV_JLL = 100;
                        }
                        DEV_JLL = Double.parseDouble(df.format(DEV_JLL).replace(",", "."));
                        KTG_JLL = "LS";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a31Activity.KPR.equals("Jalan Raya (JR)")){
                    if(currentItem.getJLL() >= 14){
                        DEV_JLL = 0;
                        KTG_JLL = "LF";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JLL = (currentItem.getJLL() - 14)/14;
                        DEV_JLL = (DEV_JLL * 100);
                        if(DEV_JLL < 0){
                            DEV_JLL = DEV_JLL * -1;
                        }
                        if(DEV_JLL > 100){
                            DEV_JLL = 100;
                        }
                        DEV_JLL = Double.parseDouble(df.format(DEV_JLL).replace(",", "."));
                        KTG_JLL = "LS";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a31Activity.KPR.equals("Jalan Sedang (JS)")){
                    if(currentItem.getJLL() >= 7){
                        DEV_JLL = 0;
                        KTG_JLL = "LF";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JLL = (currentItem.getJLL() - 7)/7;
                        DEV_JLL = (DEV_JLL * 100);
                        if(DEV_JLL < 0){
                            DEV_JLL = DEV_JLL * -1;
                        }
                        if(DEV_JLL > 100){
                            DEV_JLL = 100;
                        }
                        DEV_JLL = Double.parseDouble(df.format(DEV_JLL).replace(",", "."));
                        KTG_JLL = "LS";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else {
                    if(currentItem.getJLL() >= 5.5){
                        DEV_JLL = 0;
                        KTG_JLL = "LF";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_JLL = (currentItem.getJLL() - 5.5)/5.5;
                        DEV_JLL = (DEV_JLL * 100);
                        if(DEV_JLL < 0){
                            DEV_JLL = DEV_JLL * -1;
                        }
                        if(DEV_JLL > 100){
                            DEV_JLL = 100;
                        }
                        DEV_JLL = Double.parseDouble(df.format(DEV_JLL).replace(",", "."));
                        KTG_JLL = "LS";
                        holder.jll_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
                holder.jll_data.setText(String.valueOf(currentItem.getJLL())+" m");
                holder.jll_dev.setText(String.valueOf(DEV_JLL)+"%");
                holder.jll_hasil.setText(KTG_JLL);
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getJPK() == -1){
                DEV_JPK = -1;
                KTG_JPK = "-";
                holder.jpk_data.setText("-");
                holder.jpk_dev.setText("-");
                holder.jpk_hasil.setText(KTG_JPK);
                holder.jpk_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getJPK() >= 0.5){
                    DEV_JPK = 0;
                    KTG_JPK = "LF";
                    holder.jpk_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_JPK = (currentItem.getJPK() - 0.5)/0.5;
                    DEV_JPK = (DEV_JPK * 100);
                    if(DEV_JPK < 0){
                        DEV_JPK = DEV_JPK * -1;
                    }
                    if(DEV_JPK > 100){
                        DEV_JPK = 100;
                    }
                    DEV_JPK = Double.parseDouble(df.format(DEV_JPK).replace(",", "."));
                    KTG_JPK = "LS";
                    holder.jpk_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.jpk_data.setText(String.valueOf(currentItem.getJPK())+" m");
                holder.jpk_dev.setText(String.valueOf(DEV_JPK)+"%");
                holder.jpk_hasil.setText(KTG_JPK);
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTGI() == -1){
                DEV_TGI = -1;
                KTG_TGI = "-";
                holder.tgi_data.setText("-");
                holder.tgi_dev.setText("-");
            } else {
                DEV_TGI = STD - currentItem.getTGI();
                if(DEV_TGI == 0){
                    KTG_TGI = "LF";
                } else {
                    KTG_TGI = "LS";
                }
                holder.tgi_data.setText(String.valueOf(currentItem.getTGI())+"%");
                holder.tgi_dev.setText(String.valueOf(DEV_TGI)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLDI() == -1){
                DEV_LDI = -1;
                KTG_LDI = "-";
                holder.ldi_data.setText("-");
                holder.ldi_dev.setText("-");
            } else {
                DEV_LDI = STD - currentItem.getLDI();
                if(DEV_LDI == 0){
                    KTG_LDI = "LF";
                } else {
                    KTG_LDI = "LS";
                }
                holder.ldi_data.setText(String.valueOf(currentItem.getLDI())+"%");
                holder.ldi_dev.setText(String.valueOf(DEV_LDI)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPNI() == -1){
                DEV_PNI = -1;
                KTG_PNI = "-";
                holder.pni_data.setText("-");
                holder.pni_dev.setText("-");
            } else {
                DEV_PNI = STD - currentItem.getPNI();
                if(DEV_PNI == 0){
                    KTG_PNI = "LF";
                } else {
                    KTG_PNI = "LS";
                }
                holder.pni_data.setText(String.valueOf(currentItem.getPNI())+"%");
                holder.pni_dev.setText(String.valueOf(DEV_PNI)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBI() == -1){
                DEV_LBI = -1;
                KTG_LBI = "-";
                holder.lbi_data.setText("-");
                holder.lbi_dev.setText("-");
            } else {
                DEV_LBI = STD - currentItem.getLBI();
                if(DEV_LBI == 0){
                    KTG_LBI = "LF";
                } else {
                    KTG_LBI = "LS";
                }
                holder.lbi_data.setText(String.valueOf(currentItem.getLBI())+"%");
                holder.lbi_dev.setText(String.valueOf(DEV_LBI)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGTI() == -1){
                DEV_GTI = -1;
                KTG_GTI = "-";
                holder.gti_data.setText("-");
                holder.gti_dev.setText("-");
            } else {
                DEV_GTI = STD - currentItem.getGTI();
                if(DEV_GTI == 0){
                    KTG_GTI = "LF";
                } else {
                    KTG_GTI = "LS";
                }
                holder.gti_data.setText(String.valueOf(currentItem.getGTI())+"%");
                holder.gti_dev.setText(String.valueOf(DEV_GTI)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTI() == -1){
                DEV_KTI = -1;
                KTG_KTI = "-";
                holder.kti_data.setText("-");
                holder.kti_dev.setText("-");
            } else {
                DEV_KTI = STD - currentItem.getKTI();
                if(DEV_KTI == 0){
                    KTG_KTI = "LF";
                } else {
                    KTG_KTI = "LS";
                }
                holder.kti_data.setText(String.valueOf(currentItem.getKTI())+"%");
                holder.kti_dev.setText(String.valueOf(DEV_KTI)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_TGI.equals("LF") || KTG_TGI.equals("-")) && (KTG_LDI.equals("LF") || KTG_LDI.equals("-")) && (KTG_PNI.equals("LF") || KTG_PNI.equals("-")) &&
                    (KTG_LBI.equals("LF") || KTG_LBI.equals("-")) && (KTG_GTI.equals("LF") || KTG_GTI.equals("-")) && (KTG_KTI.equals("LF") || KTG_KTI.equals("-"))){

                if(KTG_TGI.equals("-") && KTG_LDI.equals("-") && KTG_PNI.equals("-") && KTG_LBI.equals("-") && KTG_GTI.equals("-") && KTG_KTI.equals("-")){
                    KTG_KTJ = "-";
                    holder.ktj_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KTJ = "LF";
                    holder.ktj_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KTJ = "LS";
                holder.ktj_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktj_hasil.setText(KTG_KTJ);
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB() == -1){
                DEV_KJB = -1;
                KTG_KJB = "-";
                holder.kjb_data.setText("-");
                holder.kjb_dev.setText("-");
            } else {
                DEV_KJB = STD - currentItem.getKJB();
                if(DEV_KJB == 0){
                    KTG_KJB = "LF";
                } else {
                    KTG_KJB = "LS";
                }
                holder.kjb_data.setText(String.valueOf(currentItem.getKJB())+"%");
                holder.kjb_dev.setText(String.valueOf(DEV_KJB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB2() == -1){
                DEV_KJB2 = -1;
                KTG_KJB2 = "-";
                holder.kjb2_data.setText("-");
                holder.kjb2_dev.setText("-");
            } else {
                DEV_KJB2 = STD - currentItem.getKJB2();
                if(DEV_KJB2 == 0){
                    KTG_KJB2 = "LF";
                } else {
                    KTG_KJB2 = "LS";
                }
                holder.kjb2_data.setText(String.valueOf(currentItem.getKJB2())+"%");
                holder.kjb2_dev.setText(String.valueOf(DEV_KJB2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB3() == -1){
                DEV_KJB3 = -1;
                KTG_KJB3 = "-";
                holder.kjb3_data.setText("-");
                holder.kjb3_dev.setText("-");
            } else {
                DEV_KJB3 = STD - currentItem.getKJB3();
                if(DEV_KJB3 == 0){
                    KTG_KJB3 = "LF";
                } else {
                    KTG_KJB3 = "LS";
                }
                holder.kjb3_data.setText(String.valueOf(currentItem.getKJB3())+"%");
                holder.kjb3_dev.setText(String.valueOf(DEV_KJB3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB4() == -1){
                DEV_KJB4 = -1;
                KTG_KJB4 = "-";
                holder.kjb4_data.setText("-");
                holder.kjb4_dev.setText("-");
            } else {
                DEV_KJB4 = STD - currentItem.getKJB4();
                if(DEV_KJB4 == 0){
                    KTG_KJB4 = "LF";
                } else {
                    KTG_KJB4 = "LS";
                }
                holder.kjb4_data.setText(String.valueOf(currentItem.getKJB4())+"%");
                holder.kjb4_dev.setText(String.valueOf(DEV_KJB4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB5() == -1){
                DEV_KJB5 = -1;
                KTG_KJB5 = "-";
                holder.kjb5_data.setText("-");
                holder.kjb5_dev.setText("-");
            } else {
                DEV_KJB5 = STD - currentItem.getKJB5();
                if(DEV_KJB5 == 0){
                    KTG_KJB5 = "LF";
                } else {
                    KTG_KJB5 = "LS";
                }
                holder.kjb5_data.setText(String.valueOf(currentItem.getKJB5())+"%");
                holder.kjb5_dev.setText(String.valueOf(DEV_KJB5)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB6() == -1){
                DEV_KJB6 = -1;
                KTG_KJB6 = "-";
                holder.kjb6_data.setText("-");
                holder.kjb6_dev.setText("-");
            } else {
                DEV_KJB6 = STD - currentItem.getKJB6();
                if(DEV_KJB6 == 0){
                    KTG_KJB6 = "LF";
                } else {
                    KTG_KJB6 = "LS";
                }
                holder.kjb6_data.setText(String.valueOf(currentItem.getKJB6())+"%");
                holder.kjb6_dev.setText(String.valueOf(DEV_KJB6)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB7() == -1){
                DEV_KJB7 = -1;
                KTG_KJB7 = "-";
                holder.kjb7_data.setText("-");
                holder.kjb7_dev.setText("-");
            } else {
                DEV_KJB7 = STD - currentItem.getKJB7();
                if(DEV_KJB7 == 0){
                    KTG_KJB7 = "LF";
                } else {
                    KTG_KJB7 = "LS";
                }
                holder.kjb7_data.setText(String.valueOf(currentItem.getKJB7())+"%");
                holder.kjb7_dev.setText(String.valueOf(DEV_KJB7)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKJB8() == -1){
                DEV_KJB8 = -1;
                KTG_KJB8 = "-";
                holder.kjb8_data.setText("-");
                holder.kjb8_dev.setText("-");
            } else {
                DEV_KJB8 = STD - currentItem.getKJB8();
                if(DEV_KJB8 == 0){
                    KTG_KJB8 = "LF";
                } else {
                    KTG_KJB8 = "LS";
                }
                holder.kjb8_data.setText(String.valueOf(currentItem.getKJB8())+"%");
                holder.kjb8_dev.setText(String.valueOf(DEV_KJB8)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KJB.equals("LF") || KTG_KJB.equals("-")) && (KTG_KJB2.equals("LF") || KTG_KJB2.equals("-")) && (KTG_KJB3.equals("LF") || KTG_KJB3.equals("-")) &&
                    (KTG_KJB4.equals("LF") || KTG_KJB4.equals("-")) && (KTG_KJB5.equals("LF") || KTG_KJB5.equals("-")) && (KTG_KJB6.equals("LF") || KTG_KJB6.equals("-")) &&
                    (KTG_KJB7.equals("LF") || KTG_KJB7.equals("-")) && (KTG_KJB8.equals("LF") || KTG_KJB8.equals("-"))){

                if(KTG_KJB.equals("-") && KTG_KJB2.equals("-") && KTG_KJB3.equals("-") && KTG_KJB4.equals("-") &&
                        KTG_KJB5.equals("-") && KTG_KJB6.equals("-") && KTG_KJB7.equals("-") && KTG_KJB8.equals("-")){
                    KTG_KJBB = "-";
                    holder.kjb_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KJBB = "LF";
                    holder.kjb_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KJBB = "LS";
                holder.kjb_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kjb_hasil.setText(KTG_KJBB);
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTUM() == -1){
                DEV_TUM = -1;
                KTG_TUM = "-";
                holder.tum_data.setText("-");
                holder.tum_dev.setText("-");
            } else {
                DEV_TUM = STD - currentItem.getTUM();
                if(DEV_TUM == 0){
                    KTG_TUM = "LF";
                } else {
                    KTG_TUM = "LS";
                }
                holder.tum_data.setText(String.valueOf(currentItem.getTUM())+"%");
                holder.tum_dev.setText(String.valueOf(DEV_TUM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTSD() == -1){
                DEV_TSD = -1;
                KTG_TSD = "-";
                holder.tsd_data.setText("-");
                holder.tsd_dev.setText("-");
            } else {
                DEV_TSD = STD - currentItem.getTSD();
                if(DEV_TSD == 0){
                    KTG_TSD = "LF";
                } else {
                    KTG_TSD = "LS";
                }
                holder.tsd_data.setText(String.valueOf(currentItem.getTSD())+"%");
                holder.tsd_dev.setText(String.valueOf(DEV_TSD)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTKK() == -1){
                DEV_TKK = -1;
                KTG_TKK = "-";
                holder.tkk_data.setText("-");
                holder.tkk_dev.setText("-");
            } else {
                DEV_TKK = STD - currentItem.getTKK();
                if(DEV_TKK == 0){
                    KTG_TKK = "LF";
                } else {
                    KTG_TKK = "LS";
                }
                holder.tkk_data.setText(String.valueOf(currentItem.getTKK())+"%");
                holder.tkk_dev.setText(String.valueOf(DEV_TKK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTUP() == -1){
                DEV_TUP = -1;
                KTG_TUP = "-";
                holder.tup_data.setText("-");
                holder.tup_dev.setText("-");
            } else {
                DEV_TUP = STD - currentItem.getTUP();
                if(DEV_TUP == 0){
                    KTG_TUP = "LF";
                } else {
                    KTG_TUP = "LS";
                }
                holder.tup_data.setText(String.valueOf(currentItem.getTUP())+"%");
                holder.tup_dev.setText(String.valueOf(DEV_TUP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTUP2() == -1){
                DEV_TUP2 = -1;
                KTG_TUP2 = "-";
                holder.tup2_data.setText("-");
                holder.tup2_dev.setText("-");
            } else {
                DEV_TUP2 = STD - currentItem.getTUP2();
                if(DEV_TUP2 == 0){
                    KTG_TUP2 = "LF";
                } else {
                    KTG_TUP2 = "LS";
                }
                holder.tup2_data.setText(String.valueOf(currentItem.getTUP2())+"%");
                holder.tup2_dev.setText(String.valueOf(DEV_TUP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTUK() == -1){
                DEV_TUK = -1;
                KTG_TUK = "-";
                holder.tuk_data.setText("-");
                holder.tuk_dev.setText("-");
            } else {
                DEV_TUK = STD - currentItem.getTUK();
                if(DEV_TUK == 0){
                    KTG_TUK = "LF";
                } else {
                    KTG_TUK = "LS";
                }
                holder.tuk_data.setText(String.valueOf(currentItem.getTUK())+"%");
                holder.tuk_dev.setText(String.valueOf(DEV_TUK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTBK() == -1){
                DEV_TBK = -1;
                KTG_TBK = "-";
                holder.tbk_data.setText("-");
                holder.tbk_dev.setText("-");
            } else {
                DEV_TBK = STD - currentItem.getTBK();
                if(DEV_TBK == 0){
                    KTG_TBK = "LF";
                } else {
                    KTG_TBK = "LS";
                }
                holder.tbk_data.setText(String.valueOf(currentItem.getTBK())+"%");
                holder.tbk_dev.setText(String.valueOf(DEV_TBK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTTP() == -1){
                DEV_TTP = -1;
                KTG_TTP = "-";
                holder.ttp_data.setText("-");
                holder.ttp_dev.setText("-");
            } else {
                DEV_TTP = STD - currentItem.getTTP();
                if(DEV_TTP == 0){
                    KTG_TTP = "LF";
                } else {
                    KTG_TTP = "LS";
                }
                holder.ttp_data.setText(String.valueOf(currentItem.getTTP())+"%");
                holder.ttp_dev.setText(String.valueOf(DEV_TTP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTAP() == -1){
                DEV_TAP = -1;
                KTG_TAP = "-";
                holder.tap_data.setText("-");
                holder.tap_dev.setText("-");
            } else {
                DEV_TAP = STD - currentItem.getTAP();
                if(DEV_TAP == 0){
                    KTG_TAP = "LF";
                } else {
                    KTG_TAP = "LS";
                }
                holder.tap_data.setText(String.valueOf(currentItem.getTAP())+"%");
                holder.tap_dev.setText(String.valueOf(DEV_TAP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_TUM.equals("LF") || KTG_TUM.equals("-")) && (KTG_TSD.equals("LF") || KTG_TSD.equals("-")) && (KTG_TKK.equals("LF") || KTG_TKK.equals("-")) &&
                    (KTG_TUP.equals("LF") || KTG_TUP.equals("-")) && (KTG_TUP2.equals("LF") || KTG_TUP2.equals("-")) && (KTG_TUK.equals("LF") || KTG_TUK.equals("-")) &&
                    (KTG_TBK.equals("LF") || KTG_TBK.equals("-")) && (KTG_TTP.equals("LF") || KTG_TTP.equals("-")) && (KTG_TAP.equals("LF") || KTG_TAP.equals("-"))){

                if(KTG_TUM.equals("-") && KTG_TSD.equals("-") && KTG_TKK.equals("-") && KTG_TUP.equals("-") && KTG_TUP2.equals("-") &&
                        KTG_TUK.equals("-") && KTG_TBK.equals("-") && KTG_TTP.equals("-") && KTG_TAP.equals("-")){
                    KTG_FPH = "-";
                    holder.fph_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_FPH = "LF";
                    holder.fph_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_FPH = "LS";
                holder.fph_back.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.fph_hasil.setText(KTG_FPH);
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JLL.equals("LF") || KTG_JLL.equals("-")) && (KTG_JPK.equals("LF") || KTG_JPK.equals("-")) && (KTG_KTJ.equals("LF") || KTG_KTJ.equals("-")) &&
                    (KTG_KJBB.equals("LF") || KTG_KJBB.equals("-")) && (KTG_FPH.equals("LF") || KTG_FPH.equals("-"))){

                if(KTG_JLL.equals("-") && KTG_JPK.equals("-") && KTG_KTJ.equals("-") && KTG_KJBB.equals("-") && KTG_FPH.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT5.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR5()));
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT6.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR6()));
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a31Activity.FAB_UPLOAD.hide();
                a31Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a31Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a31Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a31Activity.FAB_UPLOAD.setEnabled(false);
            }

            a31Activity.ID = currentItem.getID();
            a31Activity.JLL_TXT = currentItem.getJLL();
            a31Activity.JPK_TXT = currentItem.getJPK();
            a31Activity.TGI_TXT = currentItem.getTGI();
            a31Activity.LDI_TXT = currentItem.getLDI();
            a31Activity.PNI_TXT = currentItem.getPNI();
            a31Activity.LBI_TXT = currentItem.getLBI();
            a31Activity.GTI_TXT = currentItem.getGTI();
            a31Activity.KTI_TXT = currentItem.getKTI();
            a31Activity.SKJB_TXT = currentItem.getSKJB();
            a31Activity.KJB_TXT = currentItem.getKJB();
            a31Activity.KJB2_TXT = currentItem.getKJB2();
            a31Activity.KJB3_TXT = currentItem.getKJB3();
            a31Activity.KJB4_TXT = currentItem.getKJB4();
            a31Activity.KJB5_TXT = currentItem.getKJB5();
            a31Activity.KJB6_TXT = currentItem.getKJB6();
            a31Activity.KJB7_TXT = currentItem.getKJB7();
            a31Activity.KJB8_TXT = currentItem.getKJB8();
            a31Activity.TUM_TXT = currentItem.getTUM();
            a31Activity.TSD_TXT = currentItem.getTSD();
            a31Activity.TKK_TXT = currentItem.getTKK();
            a31Activity.TUP_TXT = currentItem.getTUP();
            a31Activity.TUP2_TXT = currentItem.getTUP2();
            a31Activity.TUK_TXT = currentItem.getTUK();
            a31Activity.TBK_TXT = currentItem.getTBK();
            a31Activity.TTP_TXT = currentItem.getTTP();
            a31Activity.TAP_TXT = currentItem.getTAP();
            a31Activity.REC_TXT = currentItem.getREC();
            a31Activity.DIR1 = currentItem.getDIR1();
            a31Activity.DIR2 = currentItem.getDIR2();
            a31Activity.DIR3 = currentItem.getDIR3();
            a31Activity.DIR4 = currentItem.getDIR4();
            a31Activity.DIR5 = currentItem.getDIR5();
            a31Activity.DIR6 = currentItem.getDIR6();

            a31Activity.DEV_JLL = DEV_JLL;
            a31Activity.DEV_JPK = DEV_JPK;
            a31Activity.DEV_TGI = DEV_TGI;
            a31Activity.DEV_LDI = DEV_LDI;
            a31Activity.DEV_PNI = DEV_PNI;
            a31Activity.DEV_LBI = DEV_LBI;
            a31Activity.DEV_GTI = DEV_GTI;
            a31Activity.DEV_KTI = DEV_KTI;
            a31Activity.DEV_KJB = DEV_KJB;
            a31Activity.DEV_KJB2 = DEV_KJB2;
            a31Activity.DEV_KJB3 = DEV_KJB3;
            a31Activity.DEV_KJB4 = DEV_KJB4;
            a31Activity.DEV_KJB5 = DEV_KJB5;
            a31Activity.DEV_KJB6 = DEV_KJB6;
            a31Activity.DEV_KJB7 = DEV_KJB7;
            a31Activity.DEV_KJB8 = DEV_KJB8;
            a31Activity.DEV_TUM = DEV_TUM;
            a31Activity.DEV_TSD = DEV_TSD;
            a31Activity.DEV_TKK = DEV_TKK;
            a31Activity.DEV_TUP = DEV_TUP;
            a31Activity.DEV_TUP2 = DEV_TUP2;
            a31Activity.DEV_TUK = DEV_TUK;
            a31Activity.DEV_TBK = DEV_TBK;
            a31Activity.DEV_TTP = DEV_TTP;
            a31Activity.DEV_TAP = DEV_TAP;

            a31Activity.KTG_JLL = KTG_JLL;
            a31Activity.KTG_JPK = KTG_JPK;
            a31Activity.KTG_KTJ = KTG_KTJ;
            a31Activity.KTG_FPH = KTG_FPH;
            a31Activity.KTG_KJBB = KTG_KJBB;
            a31Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a31Activity.klf.setText(KTG_KLF);
                a31Activity.klf.setBackground(a31Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a31Activity.getApplicationContext(), R.anim.recycle_bottom);
                a31Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a31Activity.klf.setText("Tidak Dinilai");
                a31Activity.klf.setBackground(a31Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a31Activity.getApplicationContext(), R.anim.recycle_bottom);
                a31Activity.klf.startAnimation(animation);
            } else {
                a31Activity.klf.setText(KTG_KLF);
                a31Activity.klf.setBackground(a31Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a31Activity.getApplicationContext(), R.anim.recycle_bottom);
                a31Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a31Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
