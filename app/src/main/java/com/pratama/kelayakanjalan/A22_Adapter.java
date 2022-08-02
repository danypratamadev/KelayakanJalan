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

public class A22_Adapter extends RecyclerView.Adapter<A22_Adapter.A22ViewHolder> {

    private A22Activity a22Activity;
    private List<A22_Class> mdataList;
    private double DEV_KJL, DEV_KDL, DEV_KKL, DEV_ILB, DEV_LBR, DEV_IRT, DEV_KAL, DEV_KAM, DEV_IAL, DEV_TPK, STD = 100, DEV_ASM;
    private String KTG_KJL, KTG_KDL, KTG_KKL, KTG_KLB, KTG_ILB, KTG_LBR, KTG_IRT, KTG_KAL, KTG_KAM, KTG_KAL2, KTG_IAL, KTG_TPK, KTG_ASM, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A22ViewHolder extends RecyclerView.ViewHolder {

        private TextView kjl_data, kjl_dev, kjl_hasil, kdl_data, kdl_dev, kkl_data, kkl_dev, klb_hasil, ilb_data, ilb_dev, ilb_hasil,
                lbr_data, lbr_dev, lbr_hasil, irt_data, irt_dev, irt_hasil, kal_data, kal_dev, kam_data, kam_dev, kal2_hasil,
                ial_data, ial_dev, ial_hasil, tpk_data, tpk_dev, tpk_hasil, asm_data, asm_dev, asm_hasil, rec_data;

        private LinearLayout kjl_back, klb_back, ilb_back, lbr_back, irt_back, kal2_back, ial_back, tpk_back, asm_back;
        private ImageView FT1, FT2;

        public A22ViewHolder(@NonNull View itemView) {
            super(itemView);

            kjl_data = (TextView) itemView.findViewById(R.id.kjl_data);
            kjl_dev = (TextView) itemView.findViewById(R.id.kjl_dev);
            kjl_hasil = (TextView) itemView.findViewById(R.id.kjl_hasil);
            kdl_data = (TextView) itemView.findViewById(R.id.kdl_data);
            kdl_dev = (TextView) itemView.findViewById(R.id.kdl_dev);
            kkl_data = (TextView) itemView.findViewById(R.id.kkl_data);
            kkl_dev = (TextView) itemView.findViewById(R.id.kkl_dev);
            klb_hasil = (TextView) itemView.findViewById(R.id.klb_hasil);
            ilb_data = (TextView) itemView.findViewById(R.id.ilb_data);
            ilb_dev = (TextView) itemView.findViewById(R.id.ilb_dev);
            ilb_hasil = (TextView) itemView.findViewById(R.id.ilb_hasil);
            lbr_data = (TextView) itemView.findViewById(R.id.lbr_data);
            lbr_dev = (TextView) itemView.findViewById(R.id.lbr_dev);
            lbr_hasil = (TextView) itemView.findViewById(R.id.lbr_hasil);
            irt_data = (TextView) itemView.findViewById(R.id.irt_data);
            irt_dev = (TextView) itemView.findViewById(R.id.irt_dev);
            irt_hasil = (TextView) itemView.findViewById(R.id.irt_hasil);
            kal_data = (TextView) itemView.findViewById(R.id.kal_data);
            kal_dev = (TextView) itemView.findViewById(R.id.kal_dev);
            kam_data = (TextView) itemView.findViewById(R.id.kam_data);
            kam_dev = (TextView) itemView.findViewById(R.id.kam_dev);
            kal2_hasil = (TextView) itemView.findViewById(R.id.kal2_hasil);
            ial_data = (TextView) itemView.findViewById(R.id.ial_data);
            ial_dev = (TextView) itemView.findViewById(R.id.ial_dev);
            ial_hasil = (TextView) itemView.findViewById(R.id.ial_hasil);
            tpk_data = (TextView) itemView.findViewById(R.id.tpk_data);
            tpk_dev = (TextView) itemView.findViewById(R.id.tpk_dev);
            tpk_hasil = (TextView) itemView.findViewById(R.id.tpk_hasil);
            asm_data = (TextView) itemView.findViewById(R.id.asm_data);
            asm_dev = (TextView) itemView.findViewById(R.id.asm_dev);
            asm_hasil = (TextView) itemView.findViewById(R.id.asm_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kjl_back = (LinearLayout) itemView.findViewById(R.id.kjl_back);
            klb_back = (LinearLayout) itemView.findViewById(R.id.klb_back);
            ilb_back = (LinearLayout) itemView.findViewById(R.id.ilb_back);
            lbr_back = (LinearLayout) itemView.findViewById(R.id.lbr_back);
            irt_back = (LinearLayout) itemView.findViewById(R.id.irt_back);
            kal2_back = (LinearLayout) itemView.findViewById(R.id.kal2_back);
            ial_back = (LinearLayout) itemView.findViewById(R.id.ial_back);
            tpk_back = (LinearLayout) itemView.findViewById(R.id.tpk_back);
            asm_back = (LinearLayout) itemView.findViewById(R.id.asm_back);

        }

    }

    public A22_Adapter(A22Activity a22Activity, List<A22_Class> mdataList) {
        this.a22Activity = a22Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A22ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a22_layout, parent, false);
        A22_Adapter.A22ViewHolder a22 = new A22_Adapter.A22ViewHolder(view);
        return a22;
    }

    @Override
    public void onBindViewHolder(@NonNull A22ViewHolder holder, int position) {

        final A22_Class currentItem = mdataList.get(position);

        try {
            if(a22Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                if(currentItem.getKJL() == -1){
                    DEV_KJL = -1;
                    KTG_KJL = "-";
                    holder.kjl_data.setText("-");
                    holder.kjl_dev.setText("-");
                    holder.kjl_hasil.setText(KTG_KJL);
                    holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    holder.kjl_data.setText(String.valueOf(currentItem.getKJL())+" m/Km");
                    if(currentItem.getKJL() <= 4){
                        DEV_KJL = 0;
                        KTG_KJL = "LF";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_KJL = (currentItem.getKJL() - 4)/4;
                        DEV_KJL = (DEV_KJL * 100);
                        if(DEV_KJL < 0){
                            DEV_KJL = DEV_KJL * -1;
                        }
                        if(DEV_KJL > 100){
                            DEV_KJL = 100;
                        }
                        DEV_KJL = Double.parseDouble(df.format(DEV_KJL).replace(",", "."));
                        KTG_KJL = "LS";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                    holder.kjl_dev.setText(String.valueOf(DEV_KJL)+"%");
                    holder.kjl_hasil.setText(KTG_KJL);
                }
            } else if(a22Activity.KPR.equals("Jalan Raya (JR)")){
                if(currentItem.getKJL() == -1){
                    DEV_KJL = -1;
                    KTG_KJL = "-";
                    holder.kjl_data.setText("-");
                    holder.kjl_dev.setText("-");
                    holder.kjl_hasil.setText(KTG_KJL);
                    holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    holder.kjl_data.setText(String.valueOf(currentItem.getKJL())+" m/Km");
                    if(currentItem.getKJL() <= 6){
                        DEV_KJL = 0;
                        KTG_KJL = "LF";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_KJL = (currentItem.getKJL() - 6)/6;
                        DEV_KJL = (DEV_KJL * 100);
                        if(DEV_KJL < 0){
                            DEV_KJL = DEV_KJL * -1;
                        }
                        if(DEV_KJL > 100){
                            DEV_KJL = 100;
                        }
                        DEV_KJL = Double.parseDouble(df.format(DEV_KJL).replace(",", "."));
                        KTG_KJL = "LS";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                    holder.kjl_dev.setText(String.valueOf(DEV_KJL)+"%");
                    holder.kjl_hasil.setText(KTG_KJL);
                }
            } else if(a22Activity.KPR.equals("Jalan Sedang (JS)")){
                if(currentItem.getKJL() == -1){
                    DEV_KJL = -1;
                    KTG_KJL = "-";
                    holder.kjl_data.setText("-");
                    holder.kjl_dev.setText("-");
                    holder.kjl_hasil.setText(KTG_KJL);
                    holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    holder.kjl_data.setText(String.valueOf(currentItem.getKJL())+" m/Km");
                    if(currentItem.getKJL() <= 8){
                        DEV_KJL = 0;
                        KTG_KJL = "LF";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_KJL = (currentItem.getKJL() - 8)/8;
                        DEV_KJL = (DEV_KJL * 100);
                        if(DEV_KJL < 0){
                            DEV_KJL = DEV_KJL * -1;
                        }
                        if(DEV_KJL > 100){
                            DEV_KJL = 100;
                        }
                        DEV_KJL = Double.parseDouble(df.format(DEV_KJL).replace(",", "."));
                        KTG_KJL = "LS";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                    holder.kjl_dev.setText(String.valueOf(DEV_KJL)+"%");
                    holder.kjl_hasil.setText(KTG_KJL);
                }
            } else {
                if(currentItem.getKJL() == -1){
                    DEV_KJL = -1;
                    KTG_KJL = "-";
                    holder.kjl_data.setText("-");
                    holder.kjl_dev.setText("-");
                    holder.kjl_hasil.setText(KTG_KJL);
                    holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    holder.kjl_data.setText(String.valueOf(currentItem.getKJL())+" m/Km");
                    if(currentItem.getKJL() <= 10){
                        DEV_KJL = 0;
                        KTG_KJL = "LF";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_KJL = (currentItem.getKJL() - 10)/10;
                        DEV_KJL = (DEV_KJL * 100);
                        if(DEV_KJL < 0){
                            DEV_KJL = DEV_KJL * -1;
                        }
                        if(DEV_KJL > 100){
                            DEV_KJL = 100;
                        }
                        DEV_KJL = Double.parseDouble(df.format(DEV_KJL).replace(",", "."));
                        KTG_KJL = "LS";
                        holder.kjl_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                    holder.kjl_dev.setText(String.valueOf(DEV_KJL)+"%");
                    holder.kjl_hasil.setText(KTG_KJL);
                }
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKDL() == -1){
                holder.kdl_data.setText("-");
                DEV_KDL = -1;
                KTG_KDL = "-";
                holder.kdl_dev.setText("-");
            } else {
                holder.kdl_data.setText(String.valueOf(currentItem.getKDL())+" mm");
                if(currentItem.getKDL() <= 10){
                    DEV_KDL = 0;
                    KTG_KDL = "LF";
                } else {
                    DEV_KDL = (currentItem.getKDL() - 10)/10;
                    DEV_KDL = (DEV_KDL * 100);
                    if(DEV_KDL < 0){
                        DEV_KDL = DEV_KDL * -1;
                    }
                    if(DEV_KDL > 100){
                        DEV_KDL = 100;
                    }
                    DEV_KDL = Double.parseDouble(df.format(DEV_KDL).replace(",", "."));
                    KTG_KDL = "LS";
                }
                holder.kdl_dev.setText(String.valueOf(DEV_KDL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKKL() == -1){
                holder.kkl_data.setText("-");
                DEV_KKL = -1;
                KTG_KKL = "-";
                holder.kkl_dev.setText("-");
            } else {
                holder.kkl_data.setText(String.valueOf(currentItem.getKKL())+" mm");
                if(currentItem.getKKL() <= 40){
                    DEV_KKL = 0;
                    KTG_KKL = "LF";
                } else {
                    DEV_KKL = (currentItem.getKKL() - 40)/40;
                    DEV_KKL = (DEV_KKL * 100);
                    if(DEV_KKL < 0){
                        DEV_KKL = DEV_KKL * -1;
                    }
                    if(DEV_KKL > 100){
                        DEV_KKL = 100;
                    }
                    DEV_KKL = Double.parseDouble(df.format(DEV_KKL).replace(",", "."));
                    KTG_KKL = "LS";
                }
                holder.kkl_dev.setText(String.valueOf(DEV_KKL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KDL.equals("LF") || KTG_KDL.equals("-")) && (KTG_KKL.equals("LF") || KTG_KKL.equals("-"))){
                if(KTG_KDL.equals("-") && KTG_KKL.equals("-")){
                    KTG_KLB = "-";
                    holder.klb_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KLB = "LF";
                    holder.klb_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_KLB = "LS";
                holder.klb_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.klb_hasil.setText(KTG_KLB);
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getILB() == -1){
                holder.ilb_data.setText("-");
                DEV_ILB = -1;
                KTG_ILB = "-";
                holder.ilb_dev.setText("-");
                holder.ilb_hasil.setText(KTG_ILB);
                holder.ilb_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.ilb_data.setText(String.valueOf(currentItem.getILB())+" m/km");
                if(currentItem.getILB() <= 40){
                    DEV_ILB = 0;
                    KTG_ILB = "LF";
                    holder.ilb_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_ILB = (currentItem.getILB() - 40)/40;
                    DEV_ILB = (DEV_ILB * 100);
                    if(DEV_ILB < 0){
                        DEV_ILB = DEV_ILB * -1;
                    }
                    if(DEV_ILB > 100){
                        DEV_ILB = 100;
                    }
                    DEV_ILB = Double.parseDouble(df.format(DEV_ILB).replace(",", "."));
                    KTG_ILB = "LS";
                    holder.ilb_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ilb_dev.setText(String.valueOf(DEV_ILB)+"%");
                holder.ilb_hasil.setText(KTG_ILB);
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBR() == -1){
                holder.lbr_data.setText("-");
                DEV_LBR = -1;
                KTG_LBR = "-";
                holder.lbr_dev.setText("-");
                holder.lbr_hasil.setText(KTG_LBR);
                holder.lbr_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.lbr_data.setText(String.valueOf(currentItem.getLBR())+" mm");
                if(currentItem.getLBR() <= 6){
                    DEV_LBR = 0;
                    KTG_LBR = "LF";
                    holder.lbr_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_LBR = (currentItem.getLBR() - 6)/6;
                    DEV_LBR = (DEV_LBR * 100);
                    if(DEV_LBR < 0){
                        DEV_LBR = DEV_LBR * -1;
                    }
                    if(DEV_LBR > 100){
                        DEV_LBR = 100;
                    }
                    DEV_LBR = Double.parseDouble(df.format(DEV_LBR).replace(",", "."));
                    KTG_LBR = "LS";
                    holder.lbr_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.lbr_dev.setText(String.valueOf(DEV_LBR)+"%");
                holder.lbr_hasil.setText(KTG_LBR);
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getIRT() == -1){
                holder.irt_data.setText("-");
                DEV_IRT = -1;
                KTG_IRT = "-";
                holder.irt_dev.setText("-");
                holder.irt_hasil.setText(KTG_IRT);
                holder.irt_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.irt_data.setText(String.valueOf(currentItem.getIRT())+" m/Km");
                if(currentItem.getIRT() <= 100){
                    DEV_IRT = 0;
                    KTG_IRT = "LF";
                    holder.irt_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_IRT = (currentItem.getIRT() - 100)/100;
                    DEV_IRT = (DEV_IRT * 100);
                    if(DEV_IRT < 0){
                        DEV_IRT = DEV_IRT * -1;
                    }
                    if(DEV_IRT > 100){
                        DEV_IRT = 100;
                    }
                    DEV_IRT = Double.parseDouble(df.format(DEV_IRT).replace(",", "."));
                    KTG_IRT = "LS";
                    holder.irt_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.irt_dev.setText(String.valueOf(DEV_IRT)+"%");
                holder.irt_hasil.setText(KTG_IRT);
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAL() == -1){
                holder.kal_data.setText("-");
                holder.kal_dev.setText("-");
                DEV_KAL = -1;
                KTG_KAL = "-";
            } else {
                holder.kal_data.setText(String.valueOf(currentItem.getKAL())+" mm");
                if(currentItem.getKAL() <= 30){
                    DEV_KAL = 0;
                    KTG_KAL = "LF";
                } else {
                    DEV_KAL = (currentItem.getKAL() - 30)/30;
                    DEV_KAL = (DEV_KAL * 100);
                    if(DEV_KAL < 0){
                        DEV_KAL = DEV_KAL * -1;
                    }
                    if(DEV_KAL > 100){
                        DEV_KAL = 100;
                    }
                    DEV_KAL = Double.parseDouble(df.format(DEV_KAL).replace(",", "."));
                    KTG_KAL = "LS";
                }
                holder.kal_dev.setText(String.valueOf(DEV_KAL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAM() == -1){
                holder.kam_data.setText("-");
                holder.kam_dev.setText("-");
                DEV_KAM = -1;
                KTG_KAM = "-";
            } else {
                holder.kam_data.setText(String.valueOf(currentItem.getKAM())+" mm");
                if(currentItem.getKAM() <= 30){
                    DEV_KAM = 0;
                    KTG_KAM = "LF";
                } else {
                    DEV_KAM = (currentItem.getKAM() - 30)/30;
                    DEV_KAM = (DEV_KAM * 100);
                    if(DEV_KAM < 0){
                        DEV_KAM = DEV_KAM * -1;
                    }
                    if(DEV_KAM > 100){
                        DEV_KAM = 100;
                    }
                    DEV_KAM = Double.parseDouble(df.format(DEV_KAM).replace(",", "."));
                    KTG_KAM = "LS";
                }
                holder.kam_dev.setText(String.valueOf(DEV_KAM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KAL.equals("LF") || KTG_KAL.equals("-")) && (KTG_KAM.equals("LF") || KTG_KAM.equals("-"))){

                if(KTG_KAL.equals("-") && KTG_KAM.equals("-")){
                    KTG_KAL2 = "-";
                    holder.kal2_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KAL2 = "LF";
                    holder.kal2_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KAL2 = "LS";
                holder.kal2_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kal2_hasil.setText(KTG_KAL2);
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getIAL() == -1){
                holder.ial_data.setText("-");
                DEV_IAL = -1;
                KTG_IAL = "-";
                holder.ial_dev.setText("-");
                holder.ial_hasil.setText(KTG_IAL);
                holder.ial_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.ial_data.setText(String.valueOf(currentItem.getIAL())+" m/Km");
                if(currentItem.getIAL() <= 100){
                    DEV_IAL =0;
                    KTG_IAL = "LF";
                    holder.ial_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_IAL = (currentItem.getIAL() - 100)/100;
                    DEV_IAL = (DEV_IAL * 100);
                    if(DEV_IAL < 0){
                        DEV_IAL = DEV_IAL * -1;
                    }
                    if(DEV_IAL > 100){
                        DEV_IAL = 100;
                    }
                    DEV_IAL = Double.parseDouble(df.format(DEV_IAL).replace(",", "."));
                    KTG_IAL = "LS";
                    holder.ial_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ial_dev.setText(String.valueOf(DEV_IAL)+"%");
                holder.ial_hasil.setText(KTG_IAL);
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTPK() == -1){
                holder.tpk_data.setText("-");
                DEV_TPK = -1;
                KTG_TPK = "-";
                holder.tpk_dev.setText("-");
                holder.tpk_hasil.setText(KTG_TPK);
                holder.tpk_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.tpk_data.setText(String.valueOf(currentItem.getTPK())+"%");
                DEV_TPK = STD - currentItem.getTPK();
                if(DEV_TPK == 0){
                    KTG_TPK = "LF";
                    holder.tpk_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_TPK = "LS";
                    holder.tpk_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.tpk_dev.setText(String.valueOf(DEV_TPK)+"%");
                holder.tpk_hasil.setText(KTG_TPK);
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getASM() == -1){
                holder.asm_data.setText("-");
                DEV_ASM = -1;
                KTG_ASM = "-";
                holder.asm_dev.setText("-");
                holder.asm_hasil.setText(KTG_ASM);
                holder.asm_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.asm_data.setText(String.valueOf(currentItem.getASM())+"%");
                DEV_ASM = STD - currentItem.getASM();
                if(DEV_ASM == 0){
                    KTG_ASM = "LF";
                    holder.asm_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_ASM = "LS";
                    holder.asm_back.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.asm_dev.setText(String.valueOf(DEV_ASM)+"%");
                holder.asm_hasil.setText(KTG_ASM);
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KJL.equals("LF") || KTG_KJL.equals("-")) && (KTG_KLB.equals("LF") || KTG_KLB.equals("-")) &&
                    (KTG_ILB.equals("LF") || KTG_ILB.equals("-")) && (KTG_LBR.equals("LF") || KTG_LBR.equals("-")) &&
                    (KTG_IRT.equals("LF") || KTG_IRT.equals("-")) && (KTG_KAL2.equals("LF") || KTG_KAL2.equals("-")) &&
                    (KTG_IAL.equals("LF") || KTG_IAL.equals("-")) && (KTG_TPK.equals("LF") || KTG_TPK.equals("-")) &&
                    (KTG_ASM.equals("LF") || KTG_IRT.equals("-"))){

                if(KTG_KJL.equals("-") && KTG_KLB.equals("-") && KTG_ILB.equals("-") &&  KTG_LBR.equals("-") &&
                        KTG_IRT.equals("-") &&  KTG_KAL2.equals("-") && KTG_IAL.equals("-") &&  KTG_TPK.equals("-") &&
                        KTG_IRT.equals("-")){

                    KTG_KLF = "Tidak Dinilai";

                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a22Activity.FAB_UPLOAD.hide();
                a22Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a22Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a22Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a22Activity.FAB_UPLOAD.setEnabled(false);
            }

            a22Activity.ID = currentItem.getID();
            a22Activity.KJL_TXT = currentItem.getKJL();
            a22Activity.KDL_TXT = currentItem.getKDL();
            a22Activity.KKL_TXT = currentItem.getKKL();
            a22Activity.ILB_TXT = currentItem.getILB();
            a22Activity.LBR_TXT = currentItem.getLBR();
            a22Activity.IRT_TXT = currentItem.getIRT();
            a22Activity.KAL_TXT = currentItem.getKAL();
            a22Activity.KAM_TXT = currentItem.getKAM();
            a22Activity.IAL_TXT = currentItem.getIAL();
            a22Activity.TPK_TXT = currentItem.getTPK();
            a22Activity.ASM_TXT = currentItem.getASM();
            a22Activity.REC_TXT = currentItem.getREC();
            a22Activity.DIR1 = currentItem.getDIR1();
            a22Activity.DIR2 = currentItem.getDIR2();

            a22Activity.DEV_KJL = DEV_KJL;
            a22Activity.DEV_KDL = DEV_KDL;
            a22Activity.DEV_KKL = DEV_KKL;
            a22Activity.DEV_ILB = DEV_ILB;
            a22Activity.DEV_LBR = DEV_LBR;
            a22Activity.DEV_IRT = DEV_IRT;
            a22Activity.DEV_KAL = DEV_KAL;
            a22Activity.DEV_KAM = DEV_KAM;
            a22Activity.DEV_IAL = DEV_IAL;
            a22Activity.DEV_TPK = DEV_TPK;
            a22Activity.DEV_ASM = DEV_ASM;

            a22Activity.KTG_KJL = KTG_KJL;
            a22Activity.KTG_KLB = KTG_KLB;
            a22Activity.KTG_ILB = KTG_ILB;
            a22Activity.KTG_LBR = KTG_LBR;
            a22Activity.KTG_IRT = KTG_IRT;
            a22Activity.KTG_KAL2 = KTG_KAL2;
            a22Activity.KTG_IAL = KTG_IAL;
            a22Activity.KTG_TPK = KTG_TPK;
            a22Activity.KTG_ASM = KTG_ASM;
            a22Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a22Activity.klf.setText(KTG_KLF);
                a22Activity.klf.setBackground(a22Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a22Activity.getApplicationContext(), R.anim.recycle_bottom);
                a22Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a22Activity.klf.setText("Tidak Dinilai");
                a22Activity.klf.setBackground(a22Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a22Activity.getApplicationContext(), R.anim.recycle_bottom);
                a22Activity.klf.startAnimation(animation);
            } else {
                a22Activity.klf.setText(KTG_KLF);
                a22Activity.klf.setBackground(a22Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a22Activity.getApplicationContext(), R.anim.recycle_bottom);
                a22Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a22Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
