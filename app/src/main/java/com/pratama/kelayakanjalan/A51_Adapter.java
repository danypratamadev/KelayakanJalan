package com.pratama.kelayakanjalan;

import android.graphics.BitmapFactory;
import android.os.Trace;
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

public class A51_Adapter extends RecyclerView.Adapter<A51_Adapter.A51ViewHolder> {

    private A51Activity a51Activity;
    private List<A51_Class> mdataList;

    private int MBL_IN, MBL2_IN, MBL3_IN, MBL4_IN, MBT_IN, MBT2_IN, MBT3_IN, MBT4_IN, MBT5_IN, MPR_IN, MPR2_IN, MPR3_IN, MPR4_IN, MPR5_IN, MPR6_IN, MPR7_IN;
    private double DEV_MBL, DEV_MBL2, DEV_MBL3, DEV_MBL4, DEV_MBT, DEV_MBT2, DEV_MBT3, DEV_MBT4, DEV_MBT5, DEV_MPR, DEV_MPR2, DEV_MPR3, DEV_MPR4, DEV_MPR5, DEV_MPR6, DEV_MPR7;
    private String KTG_MBL, KTG_MBL2, KTG_MBL3, KTG_MBL4, KTG_MBT, KTG_MBT2, KTG_MBT3, KTG_MBT4, KTG_MBT5, KTG_MPR, KTG_MPR2, KTG_MPR3, KTG_MPR4, KTG_MPR5, KTG_MPR6, KTG_MPR7;
    private double DEV_ZBC, DEV_ZBC2, DEV_ZBC3, STD = 100;
    private String KTG_ZBC, KTG_ZBC2, KTG_ZBC3, KTG_KLF;
    private String KTG_MBLL, KTG_MBTT, KTG_MPRR, KTG_ZBCC;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A51ViewHolder extends RecyclerView.ViewHolder {

        private TextView mbl_data, mbl_dev, mbl2_data, mbl2_dev, mbl3_data, mbl3_dev, mbl4_data, mbl4_dev, mbl_hasil;
        private TextView mbt_data, mbt_dev, mbt2_data, mbt2_dev, mbt3_data, mbt3_dev, mbt4_data, mbt4_dev, mbt5_data, mbt5_dev, mbt_hasil;
        private TextView mpr_data, mpr_dev, mpr2_data, mpr2_dev, mpr3_data, mpr3_dev, mpr4_data, mpr4_dev, mpr5_data, mpr5_dev, mpr6_data, mpr6_dev, mpr7_data, mpr7_dev, mpr_hasil;
        private TextView zbc_data, zbc_dev, zbc2_data, zbc2_dev, zbc3_data, zbc3_dev, zbc_hasil, rec_data;
        private LinearLayout mbl_back, mbt_back, mpr_back, zbc_back;
        private ImageView FT1, FT2, FT3, FT4;

        public A51ViewHolder(@NonNull View itemView) {
            super(itemView);

            mbl_data = (TextView) itemView.findViewById(R.id.mbl_data);
            mbl_dev = (TextView) itemView.findViewById(R.id.mbl_dev);
            mbl2_data = (TextView) itemView.findViewById(R.id.mbl2_data);
            mbl2_dev = (TextView) itemView.findViewById(R.id.mbl2_dev);
            mbl3_data = (TextView) itemView.findViewById(R.id.mbl3_data);
            mbl3_dev = (TextView) itemView.findViewById(R.id.mbl3_dev);
            mbl4_data = (TextView) itemView.findViewById(R.id.mbl4_data);
            mbl4_dev = (TextView) itemView.findViewById(R.id.mbl4_dev);
            mbl_hasil = (TextView) itemView.findViewById(R.id.mbl_hasil);

            mbt_data = (TextView) itemView.findViewById(R.id.mbt_data);
            mbt_dev = (TextView) itemView.findViewById(R.id.mbt_dev);
            mbt2_data = (TextView) itemView.findViewById(R.id.mbt2_data);
            mbt2_dev = (TextView) itemView.findViewById(R.id.mbt2_dev);
            mbt3_data = (TextView) itemView.findViewById(R.id.mbt3_data);
            mbt3_dev = (TextView) itemView.findViewById(R.id.mbt3_dev);
            mbt4_data = (TextView) itemView.findViewById(R.id.mbt4_data);
            mbt4_dev = (TextView) itemView.findViewById(R.id.mbt4_dev);
            mbt5_data = (TextView) itemView.findViewById(R.id.mbt5_data);
            mbt5_dev = (TextView) itemView.findViewById(R.id.mbt5_dev);
            mbt_hasil = (TextView) itemView.findViewById(R.id.mbt_hasil);

            mpr_data = (TextView) itemView.findViewById(R.id.mpr_data);
            mpr_dev = (TextView) itemView.findViewById(R.id.mpr_dev);
            mpr2_data = (TextView) itemView.findViewById(R.id.mpr2_data);
            mpr2_dev = (TextView) itemView.findViewById(R.id.mpr2_dev);
            mpr3_data = (TextView) itemView.findViewById(R.id.mpr3_data);
            mpr3_dev = (TextView) itemView.findViewById(R.id.mpr3_dev);
            mpr4_data = (TextView) itemView.findViewById(R.id.mpr4_data);
            mpr4_dev = (TextView) itemView.findViewById(R.id.mpr4_dev);
            mpr5_data = (TextView) itemView.findViewById(R.id.mpr5_data);
            mpr5_dev = (TextView) itemView.findViewById(R.id.mpr5_dev);
            mpr6_data = (TextView) itemView.findViewById(R.id.mpr6_data);
            mpr6_dev = (TextView) itemView.findViewById(R.id.mpr6_dev);
            mpr7_data = (TextView) itemView.findViewById(R.id.mpr7_data);
            mpr7_dev = (TextView) itemView.findViewById(R.id.mpr7_dev);
            mpr_hasil = (TextView) itemView.findViewById(R.id.mpr_hasil);

            zbc_data = (TextView) itemView.findViewById(R.id.zbc_data);
            zbc_dev = (TextView) itemView.findViewById(R.id.zbc_dev);
            zbc2_data = (TextView) itemView.findViewById(R.id.zbc2_data);
            zbc2_dev = (TextView) itemView.findViewById(R.id.zbc2_dev);
            zbc3_data = (TextView) itemView.findViewById(R.id.zbc3_data);
            zbc3_dev = (TextView) itemView.findViewById(R.id.zbc3_dev);
            zbc_hasil = (TextView) itemView.findViewById(R.id.zbc_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);

            mbl_back = (LinearLayout) itemView.findViewById(R.id.mbl_back);
            mbt_back = (LinearLayout) itemView.findViewById(R.id.mbt_back);
            mpr_back = (LinearLayout) itemView.findViewById(R.id.mpr_back);
            zbc_back = (LinearLayout) itemView.findViewById(R.id.zbc_back);

        }
    }

    public A51_Adapter(A51Activity a51Activity, List<A51_Class> mdataList) {
        this.a51Activity = a51Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A51_Adapter.A51ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a51_layout, parent, false);
        A51_Adapter.A51ViewHolder a51 = new A51_Adapter.A51ViewHolder(view);
        return a51;
    }

    @Override
    public void onBindViewHolder(@NonNull A51_Adapter.A51ViewHolder holder, int position) {

        final A51_Class currentItem = mdataList.get(position);

        //Marka Bagian Lurus

        try {
            if(currentItem.getMBL1().equals("Ada")){
                MBL_IN = 1;
                DEV_MBL = STD - currentItem.getMBL11();
                if(DEV_MBL == 0){
                    KTG_MBL = "LF";
                } else {
                    KTG_MBL = "LS";
                }
                holder.mbl_data.setText("("+String.valueOf(MBL_IN)+") "+String.valueOf(currentItem.getMBL11())+"%");
                holder.mbl_dev.setText(String.valueOf(DEV_MBL)+"%");
            } else if(currentItem.getMBL1().equals("Tidak Ada")){
                MBL_IN = 2;
                DEV_MBL = 100;
                KTG_MBL = "LS";
                holder.mbl_data.setText("("+String.valueOf(MBL_IN)+") "+String.valueOf(currentItem.getMBL11())+"%");
                holder.mbl_dev.setText(String.valueOf(DEV_MBL)+"%");
            } else {
                MBL_IN = 3;
                DEV_MBL = -1;
                KTG_MBL = "-";
                holder.mbl_data.setText("("+String.valueOf(MBL_IN)+")");
                holder.mbl_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMBL2().equals("Ada")){
                MBL2_IN = 1;
                DEV_MBL2 = STD - currentItem.getMBL22();
                if(DEV_MBL2 == 0){
                    KTG_MBL2 = "LF";
                } else {
                    KTG_MBL2 = "LS";
                }
                holder.mbl2_data.setText("("+String.valueOf(MBL2_IN)+") "+String.valueOf(currentItem.getMBL22())+"%");
                holder.mbl2_dev.setText(String.valueOf(DEV_MBL2)+"%");
            } else if(currentItem.getMBL2().equals("Tidak Ada")){
                MBL2_IN = 2;
                DEV_MBL2 = 100;
                KTG_MBL2 = "LS";
                holder.mbl2_data.setText("("+String.valueOf(MBL2_IN)+") "+String.valueOf(currentItem.getMBL22())+"%");
                holder.mbl2_dev.setText(String.valueOf(DEV_MBL2)+"%");
            } else {
                MBL2_IN = 3;
                DEV_MBL2 = -1;
                KTG_MBL2 = "-";
                holder.mbl2_data.setText("("+String.valueOf(MBL2_IN)+")");
                holder.mbl2_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMBL3().equals("Ada")){
                MBL3_IN = 1;
                DEV_MBL3 = STD - currentItem.getMBL33();
                if(DEV_MBL3 == 0){
                    KTG_MBL3 = "LF";
                } else {
                    KTG_MBL3 = "LS";
                }
                holder.mbl3_data.setText("("+String.valueOf(MBL3_IN)+") "+String.valueOf(currentItem.getMBL33())+"%");
                holder.mbl3_dev.setText(String.valueOf(DEV_MBL3)+"%");
            } else if(currentItem.getMBL3().equals("Tidak Ada")){
                MBL3_IN = 2;
                DEV_MBL3 = 100;
                KTG_MBL3 = "LS";
                holder.mbl3_data.setText("("+String.valueOf(MBL3_IN)+") "+String.valueOf(currentItem.getMBL33())+"%");
                holder.mbl3_dev.setText(String.valueOf(DEV_MBL3)+"%");
            } else {
                MBL3_IN = 3;
                DEV_MBL3 = -1;
                KTG_MBL3 = "-";
                holder.mbl3_data.setText("("+String.valueOf(MBL3_IN)+")");
                holder.mbl3_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMBL4().equals("Ada")){
                MBL4_IN = 1;
                DEV_MBL4 = STD - currentItem.getMBL44();
                if(DEV_MBL4 == 0){
                    KTG_MBL4 = "LF";
                } else {
                    KTG_MBL4 = "LS";
                }
                holder.mbl4_data.setText("("+String.valueOf(MBL4_IN)+") "+String.valueOf(currentItem.getMBL44())+"%");
                holder.mbl4_dev.setText(String.valueOf(DEV_MBL4)+"%");
            } else if(currentItem.getMBL4().equals("Tidak Ada")){
                MBL4_IN = 2;
                DEV_MBL4 = 100;
                KTG_MBL4 = "LS";
                holder.mbl4_data.setText("("+String.valueOf(MBL4_IN)+") "+String.valueOf(currentItem.getMBL44())+"%");
                holder.mbl4_dev.setText(String.valueOf(DEV_MBL4)+"%");
            } else {
                MBL4_IN = 3;
                DEV_MBL4 = -1;
                KTG_MBL4 = "-";
                holder.mbl4_data.setText("("+String.valueOf(MBL4_IN)+")");
                holder.mbl4_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_MBL.equals("LF") || KTG_MBL.equals("-")) && (KTG_MBL2.equals("LF") || KTG_MBL2.equals("-")) &&
                    (KTG_MBL3.equals("LF") || KTG_MBL3.equals("-")) && (KTG_MBL4.equals("LF") || KTG_MBL4.equals("-"))){

                if(KTG_MBL.equals("-") && KTG_MBL2.equals("-") && KTG_MBL3.equals("-") && KTG_MBL4.equals("-")){
                    KTG_MBLL = "-";
                    holder.mbl_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_MBLL = "LF";
                    holder.mbl_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_MBLL = "LS";
                holder.mbl_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.mbl_hasil.setText(KTG_MBLL);
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Marka Bagian Tikungan

        try {
            if(currentItem.getMBT1().equals("Ada")){
                MBT_IN = 1;
                DEV_MBT = STD - currentItem.getMBT11();
                if(DEV_MBT == 0){
                    KTG_MBT = "LF";
                } else {
                    KTG_MBT = "LS";
                }
                holder.mbt_data.setText("("+String.valueOf(MBT_IN)+") "+String.valueOf(currentItem.getMBT11())+"%");
                holder.mbt_dev.setText(String.valueOf(DEV_MBT)+"%");
            } else if(currentItem.getMBT1().equals("Tidak Ada")){
                MBT_IN = 2;
                DEV_MBT = 100;
                KTG_MBT = "LS";
                holder.mbt_data.setText("("+String.valueOf(MBT_IN)+") "+String.valueOf(currentItem.getMBT11())+"%");
                holder.mbt_dev.setText(String.valueOf(DEV_MBT)+"%");
            } else {
                MBT_IN = 3;
                DEV_MBT = -1;
                KTG_MBT = "-";
                holder.mbt_data.setText("("+String.valueOf(MBT_IN)+")");
                holder.mbt_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMBT2().equals("Ada")){
                MBT2_IN = 1;
                DEV_MBT2 = STD - currentItem.getMBT22();
                if(DEV_MBT2 == 0){
                    KTG_MBT2 = "LF";
                } else {
                    KTG_MBT2 = "LS";
                }
                holder.mbt2_data.setText("("+String.valueOf(MBT2_IN)+") "+String.valueOf(currentItem.getMBT22())+"%");
                holder.mbt2_dev.setText(String.valueOf(DEV_MBT2)+"%");
            } else if(currentItem.getMBT2().equals("Tidak Ada")){
                MBT2_IN = 2;
                DEV_MBT2 = 100;
                KTG_MBT2 = "LS";
                holder.mbt2_data.setText("("+String.valueOf(MBT2_IN)+") "+String.valueOf(currentItem.getMBT22())+"%");
                holder.mbt2_dev.setText(String.valueOf(DEV_MBT2)+"%");
            } else {
                MBT2_IN = 3;
                DEV_MBT2 = -1;
                KTG_MBT2 = "-";
                holder.mbt2_data.setText("("+String.valueOf(MBT2_IN)+")");
                holder.mbt2_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMBT3().equals("Ada")){
                MBT3_IN = 1;
                DEV_MBT3 = STD - currentItem.getMBT33();
                if(DEV_MBT3 == 0){
                    KTG_MBT3 = "LF";
                } else {
                    KTG_MBT3 = "LS";
                }
                holder.mbt3_data.setText("("+String.valueOf(MBT3_IN)+") "+String.valueOf(currentItem.getMBT33())+"%");
                holder.mbt3_dev.setText(String.valueOf(DEV_MBT3)+"%");
            } else if(currentItem.getMBT3().equals("Tidak Ada")){
                MBT3_IN = 2;
                DEV_MBT3 = 100;
                KTG_MBT3 = "LS";
                holder.mbt3_data.setText("("+String.valueOf(MBT3_IN)+") "+String.valueOf(currentItem.getMBT33())+"%");
                holder.mbt3_dev.setText(String.valueOf(DEV_MBT3)+"%");
            } else {
                MBT3_IN = 3;
                DEV_MBT3 = -1;
                KTG_MBT3 = "-";
                holder.mbt3_data.setText("("+String.valueOf(MBT3_IN)+")");
                holder.mbt3_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMBT4().equals("Ada")){
                MBT4_IN = 1;
                DEV_MBT4 = STD - currentItem.getMBT44();
                if(DEV_MBT4 == 0){
                    KTG_MBT4 = "LF";
                } else {
                    KTG_MBT4 = "LS";
                }
                holder.mbt4_data.setText("("+String.valueOf(MBT4_IN)+") "+String.valueOf(currentItem.getMBT44())+"%");
                holder.mbt4_dev.setText(String.valueOf(DEV_MBT4)+"%");
            } else if(currentItem.getMBT4().equals("Tidak Ada")){
                MBT4_IN = 2;
                DEV_MBT4 = 100;
                KTG_MBT4 = "LS";
                holder.mbt4_data.setText("("+String.valueOf(MBT4_IN)+") "+String.valueOf(currentItem.getMBT44())+"%");
                holder.mbt4_dev.setText(String.valueOf(DEV_MBT4)+"%");
            } else {
                MBT4_IN = 3;
                DEV_MBT4 = -1;
                KTG_MBT4 = "-";
                holder.mbt4_data.setText("("+String.valueOf(MBT4_IN)+")");
                holder.mbt4_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMBT5().equals("Ada")){
                MBT5_IN = 1;
                DEV_MBT5 = STD - currentItem.getMBT55();
                if(DEV_MBT5 == 0){
                    KTG_MBT5 = "LF";
                } else {
                    KTG_MBT5 = "LS";
                }
                holder.mbt5_data.setText("("+String.valueOf(MBT5_IN)+") "+String.valueOf(currentItem.getMBT55())+"%");
                holder.mbt5_dev.setText(String.valueOf(DEV_MBT5)+"%");
            } else if(currentItem.getMBT5().equals("Tidak Ada")){
                MBT5_IN = 2;
                DEV_MBT5 = 100;
                KTG_MBT5 = "LS";
                holder.mbt5_data.setText("("+String.valueOf(MBT5_IN)+") "+String.valueOf(currentItem.getMBT55())+"%");
                holder.mbt5_dev.setText(String.valueOf(DEV_MBT5)+"%");
            } else {
                MBT5_IN = 3;
                DEV_MBT5 = -1;
                KTG_MBT5 = "-";
                holder.mbt5_data.setText("("+String.valueOf(MBT5_IN)+")");
                holder.mbt5_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_MBT.equals("LF") || KTG_MBT.equals("-")) && (KTG_MBT2.equals("LF") || KTG_MBT2.equals("-")) && (KTG_MBT3.equals("LF") || KTG_MBT3.equals("-")) && (KTG_MBT4.equals("LF") || KTG_MBT4.equals("-")) && (KTG_MBT5.equals("LF") || KTG_MBT5.equals("-"))){

                if(KTG_MBT.equals("-") && KTG_MBT2.equals("-") && KTG_MBT3.equals("-") && KTG_MBT4.equals("-") && KTG_MBT5.equals("-")){
                    KTG_MBTT = "-";
                    holder.mbt_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_MBTT = "LF";
                    holder.mbt_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_MBTT = "LS";
                holder.mbt_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.mbt_hasil.setText(KTG_MBTT);
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Marka Persimpangan

        try {
            if(currentItem.getMPR1().equals("Ada")){
                MPR_IN = 1;
                DEV_MPR = STD - currentItem.getMPR11();
                if(DEV_MPR == 0){
                    KTG_MPR = "LF";
                } else {
                    KTG_MPR = "LS";
                }
                holder.mpr_data.setText("("+String.valueOf(MPR_IN)+") "+String.valueOf(currentItem.getMPR11())+"%");
                holder.mpr_dev.setText(String.valueOf(DEV_MPR)+"%");
            } else if(currentItem.getMPR1().equals("Tidak Ada")){
                MPR_IN = 2;
                DEV_MPR = 100;
                KTG_MPR = "LS";
                holder.mpr_data.setText("("+String.valueOf(MPR_IN)+") "+String.valueOf(currentItem.getMPR11())+"%");
                holder.mpr_dev.setText(String.valueOf(DEV_MPR)+"%");
            } else {
                MPR_IN = 3;
                DEV_MPR = -1;
                KTG_MPR = "-";
                holder.mpr_data.setText("("+String.valueOf(MPR_IN)+")");
                holder.mpr_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMPR2().equals("Ada")){
                MPR2_IN = 1;
                DEV_MPR2 = STD - currentItem.getMPR22();
                if(DEV_MPR2 == 0){
                    KTG_MPR2 = "LF";
                } else {
                    KTG_MPR2 = "LS";
                }
                holder.mpr2_data.setText("("+String.valueOf(MPR2_IN)+") "+String.valueOf(currentItem.getMPR22())+"%");
                holder.mpr2_dev.setText(String.valueOf(DEV_MPR2)+"%");
            } else if(currentItem.getMPR2().equals("Tidak Ada")){
                MPR2_IN = 2;
                DEV_MPR2 = 100;
                KTG_MPR2 = "LS";
                holder.mpr2_data.setText("("+String.valueOf(MPR2_IN)+") "+String.valueOf(currentItem.getMPR22())+"%");
                holder.mpr2_dev.setText(String.valueOf(DEV_MPR2)+"%");
            } else {
                MPR2_IN = 3;
                DEV_MPR2 = -1;
                KTG_MPR2 = "-";
                holder.mpr2_data.setText("("+String.valueOf(MPR2_IN)+")");
                holder.mpr2_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMPR3().equals("Ada")){
                MPR3_IN = 1;
                DEV_MPR3 = STD - currentItem.getMPR33();
                if(DEV_MPR3 == 0){
                    KTG_MPR3 = "LF";
                } else {
                    KTG_MPR3 = "LS";
                }
                holder.mpr3_data.setText("("+String.valueOf(MPR3_IN)+") "+String.valueOf(currentItem.getMPR33())+"%");
                holder.mpr3_dev.setText(String.valueOf(DEV_MPR3)+"%");
            } else if(currentItem.getMPR3().equals("Tidak Ada")){
                MPR3_IN = 2;
                DEV_MPR3 = 100;
                KTG_MPR3 = "LS";
                holder.mpr3_data.setText("("+String.valueOf(MPR3_IN)+") "+String.valueOf(currentItem.getMPR33())+"%");
                holder.mpr3_dev.setText(String.valueOf(DEV_MPR3)+"%");
            } else {
                MPR3_IN = 3;
                DEV_MPR3 = -1;
                KTG_MPR3 = "-";
                holder.mpr3_data.setText("("+String.valueOf(MPR3_IN)+")");
                holder.mpr3_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMPR4().equals("Ada")){
                MPR4_IN = 1;
                DEV_MPR4 = STD - currentItem.getMPR44();
                if(DEV_MPR4 == 0){
                    KTG_MPR4 = "LF";
                } else {
                    KTG_MPR4 = "LS";
                }
                holder.mpr4_data.setText("("+String.valueOf(MPR4_IN)+") "+String.valueOf(currentItem.getMPR44())+"%");
                holder.mpr4_dev.setText(String.valueOf(DEV_MPR4)+"%");
            } else if(currentItem.getMPR4().equals("Tidak Ada")){
                MPR4_IN = 2;
                DEV_MPR4 = 100;
                KTG_MPR4 = "LS";
                holder.mpr4_data.setText("("+String.valueOf(MPR4_IN)+") "+String.valueOf(currentItem.getMPR44())+"%");
                holder.mpr4_dev.setText(String.valueOf(DEV_MPR4)+"%");
            } else {
                MPR4_IN = 3;
                DEV_MPR4 = -1;
                KTG_MPR4 = "-";
                holder.mpr4_data.setText("("+String.valueOf(MPR4_IN)+")");
                holder.mpr4_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMPR5().equals("Ada")){
                MPR5_IN = 1;
                DEV_MPR5 = STD - currentItem.getMPR55();
                if(DEV_MPR5 == 0){
                    KTG_MPR5 = "LF";
                } else {
                    KTG_MPR5 = "LS";
                }
                holder.mpr5_data.setText("("+String.valueOf(MPR5_IN)+") "+String.valueOf(currentItem.getMPR55())+"%");
                holder.mpr5_dev.setText(String.valueOf(DEV_MPR5)+"%");
            } else if(currentItem.getMPR5().equals("Tidak Ada")){
                MPR5_IN = 2;
                DEV_MPR5 = 100;
                KTG_MPR5 = "LS";
                holder.mpr5_data.setText("("+String.valueOf(MPR5_IN)+") "+String.valueOf(currentItem.getMPR55())+"%");
                holder.mpr5_dev.setText(String.valueOf(DEV_MPR5)+"%");
            } else {
                MPR5_IN = 3;
                DEV_MPR5 = -1;
                KTG_MPR5 = "-";
                holder.mpr5_data.setText("("+String.valueOf(MPR5_IN)+")");
                holder.mpr5_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMPR6().equals("Ada")){
                MPR6_IN = 1;
                DEV_MPR6 = STD - currentItem.getMPR66();
                if(DEV_MPR6 == 0){
                    KTG_MPR6 = "LF";
                } else {
                    KTG_MPR6 = "LS";
                }
                holder.mpr6_data.setText("("+String.valueOf(MPR6_IN)+") "+String.valueOf(currentItem.getMPR66())+"%");
                holder.mpr6_dev.setText(String.valueOf(DEV_MPR6)+"%");
            } else if(currentItem.getMPR6().equals("Tidak Ada")){
                MPR6_IN = 2;
                DEV_MPR6 = 100;
                KTG_MPR6 = "LS";
                holder.mpr6_data.setText("("+String.valueOf(MPR6_IN)+") "+String.valueOf(currentItem.getMPR66())+"%");
                holder.mpr6_dev.setText(String.valueOf(DEV_MPR6)+"%");
            } else {
                MPR6_IN = 3;
                DEV_MPR6 = -1;
                KTG_MPR6 = "-";
                holder.mpr6_data.setText("("+String.valueOf(MPR6_IN)+")");
                holder.mpr6_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMPR7().equals("Ada")){
                MPR7_IN = 1;
                DEV_MPR7 = STD - currentItem.getMPR77();
                if(DEV_MPR7 == 0){
                    KTG_MPR7 = "LF";
                } else {
                    KTG_MPR7 = "LS";
                }
                holder.mpr7_data.setText("("+String.valueOf(MPR7_IN)+") "+String.valueOf(currentItem.getMPR77())+"%");
                holder.mpr7_dev.setText(String.valueOf(DEV_MPR7)+"%");
            } else if(currentItem.getMPR7().equals("Tidak Ada")){
                MPR7_IN = 2;
                DEV_MPR7 = 100;
                KTG_MPR7 = "LS";
                holder.mpr7_data.setText("("+String.valueOf(MPR7_IN)+") "+String.valueOf(currentItem.getMPR77())+"%");
                holder.mpr7_dev.setText(String.valueOf(DEV_MPR7)+"%");
            } else {
                MPR7_IN = 3;
                DEV_MPR7 = -1;
                KTG_MPR7 = "-";
                holder.mpr7_data.setText("("+String.valueOf(MPR7_IN)+")");
                holder.mpr7_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_MPR.equals("LF") || KTG_MPR.equals("-")) && (KTG_MPR2.equals("LF") || KTG_MPR2.equals("-")) &&
                    (KTG_MPR3.equals("LF") || KTG_MPR3.equals("-")) && (KTG_MPR4.equals("LF") || KTG_MPR4.equals("-")) &&
                    (KTG_MPR5.equals("LF") || KTG_MPR5.equals("-")) && (KTG_MPR6.equals("LF") || KTG_MPR6.equals("-")) &&
                    (KTG_MPR7.equals("LF") || KTG_MPR7.equals("-"))){

                if(KTG_MPR.equals("-") && KTG_MPR2.equals("-") && KTG_MPR3.equals("-") && KTG_MPR4.equals("-") &&
                        KTG_MPR5.equals("-") && KTG_MPR6.equals("-") && KTG_MPR7.equals("-")){
                    KTG_MPRR = "-";
                    holder.mpr_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_MPRR = "LF";
                    holder.mpr_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_MPRR = "LS";
                holder.mpr_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.mpr_hasil.setText(KTG_MPRR);
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Zebra Cross

        try {
            if(currentItem.getZBC1() == -1){
                DEV_ZBC = -1;
                KTG_ZBC = "-";
                holder.zbc_data.setText("-");
                holder.zbc_dev.setText("-");
            } else {
                holder.zbc_data.setText(String.valueOf(currentItem.getZBC1())+"%");
                DEV_ZBC = STD - currentItem.getZBC1();
                if(DEV_ZBC == 0){
                    KTG_ZBC = "LF";
                } else {
                    KTG_ZBC = "LS";
                }
                holder.zbc_dev.setText(String.valueOf(DEV_ZBC)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getZBC2() == -1){
                DEV_ZBC2 = -1;
                KTG_ZBC2 = "-";
                holder.zbc2_data.setText("-");
                holder.zbc2_dev.setText("-");
            } else {
                holder.zbc2_data.setText(String.valueOf(currentItem.getZBC2())+"%");
                DEV_ZBC2 = STD - currentItem.getZBC2();
                if(DEV_ZBC2 == 0){
                    KTG_ZBC2 = "LF";
                } else {
                    KTG_ZBC2 = "LS";
                }
                holder.zbc2_dev.setText(String.valueOf(DEV_ZBC2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getZBC3() == -1){
                DEV_ZBC3 = -1;
                KTG_ZBC3 = "-";
                holder.zbc3_data.setText("-");
                holder.zbc3_dev.setText("-");
            } else {
                holder.zbc3_data.setText(String.valueOf(currentItem.getZBC3())+"%");
                DEV_ZBC3 = STD - currentItem.getZBC3();
                if(DEV_ZBC3 == 0){
                    KTG_ZBC3 = "LF";
                } else {
                    KTG_ZBC3 = "LS";
                }
                holder.zbc3_dev.setText(String.valueOf(DEV_ZBC3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_ZBC.equals("LF") || KTG_ZBC.equals("-")) && (KTG_ZBC2.equals("LF") || KTG_ZBC2.equals("-")) && KTG_ZBC3.equals("LF") || KTG_ZBC3.equals("-")){
                if(KTG_ZBC.equals("-") && KTG_ZBC2.equals("-") && KTG_ZBC3.equals("-")){
                    KTG_ZBCC = "-";
                    holder.zbc_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_ZBCC = "LF";
                    holder.zbc_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_ZBCC = "LS";
                holder.zbc_back.setBackground(a51Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.zbc_hasil.setText(KTG_ZBCC);
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_MBLL.equals("LF") || KTG_MBLL.equals("-")) && (KTG_MBTT.equals("LF") || KTG_MBTT.equals("-")) && (KTG_MPRR.equals("LF") ||
                    KTG_MPRR.equals("-")) && (KTG_ZBCC.equals("LF") || KTG_ZBCC.equals("-"))){

                if(KTG_MBLL.equals("-") && KTG_MBTT.equals("-") && KTG_MPRR.equals("-") && KTG_ZBCC.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a51Activity.FAB_UPLOAD.hide();
                a51Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a51Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a51Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a51Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a51Activity.ID = currentItem.getID();
            a51Activity.SMBL1_TXT = currentItem.getMBL1();
            a51Activity.SMBL2_TXT = currentItem.getMBL2();
            a51Activity.SMBL3_TXT = currentItem.getMBL3();
            a51Activity.SMBL4_TXT = currentItem.getMBL4();
            a51Activity.SMBT1_TXT = currentItem.getMBT1();
            a51Activity.SMBT2_TXT = currentItem.getMBT2();
            a51Activity.SMBT3_TXT = currentItem.getMBT3();
            a51Activity.SMBT4_TXT = currentItem.getMBT4();
            a51Activity.SMBT5_TXT = currentItem.getMBT5();
            a51Activity.SMPR1_TXT = currentItem.getMPR1();
            a51Activity.SMPR2_TXT = currentItem.getMPR2();
            a51Activity.SMPR3_TXT = currentItem.getMPR3();
            a51Activity.SMPR4_TXT = currentItem.getMPR4();
            a51Activity.SMPR5_TXT = currentItem.getMPR5();
            a51Activity.SMPR6_TXT = currentItem.getMPR6();
            a51Activity.SMPR7_TXT = currentItem.getMPR7();
            a51Activity.MBL1_TXT = currentItem.getMBL11();
            a51Activity.MBL2_TXT = currentItem.getMBL22();
            a51Activity.MBL3_TXT = currentItem.getMBL33();
            a51Activity.MBL4_TXT = currentItem.getMBL44();
            a51Activity.MBT1_TXT = currentItem.getMBT11();
            a51Activity.MBT2_TXT = currentItem.getMBT22();
            a51Activity.MBT3_TXT = currentItem.getMBT33();
            a51Activity.MBT4_TXT = currentItem.getMBT44();
            a51Activity.MBT5_TXT = currentItem.getMBT55();
            a51Activity.MPR1_TXT = currentItem.getMPR11();
            a51Activity.MPR2_TXT = currentItem.getMPR22();
            a51Activity.MPR3_TXT = currentItem.getMPR33();
            a51Activity.MPR4_TXT = currentItem.getMPR44();
            a51Activity.MPR5_TXT = currentItem.getMPR55();
            a51Activity.MPR6_TXT = currentItem.getMPR66();
            a51Activity.MPR7_TXT = currentItem.getMPR77();
            a51Activity.MBL_IN = MBL_IN;
            a51Activity.MBL2_IN = MBL2_IN;
            a51Activity.MBL3_IN = MBL3_IN;
            a51Activity.MBL4_IN = MBL4_IN;
            a51Activity.MBT_IN = MBT_IN;
            a51Activity.MBT2_IN = MBT2_IN;
            a51Activity.MBT3_IN = MBT3_IN;
            a51Activity.MBT4_IN = MBT4_IN;
            a51Activity.MBT5_IN = MBT5_IN;
            a51Activity.MPR_IN = MPR_IN;
            a51Activity.MPR2_IN = MPR2_IN;
            a51Activity.MPR3_IN = MPR3_IN;
            a51Activity.MPR4_IN = MPR4_IN;
            a51Activity.MPR5_IN = MPR5_IN;
            a51Activity.MPR6_IN = MPR6_IN;
            a51Activity.MPR7_IN = MPR7_IN;
            a51Activity.ZBC1_TXT = currentItem.getZBC1();
            a51Activity.ZBC2_TXT = currentItem.getZBC2();
            a51Activity.ZBC3_TXT = currentItem.getZBC3();
            a51Activity.REC_TXT = currentItem.getREC();
            a51Activity.DIR1 = currentItem.getDIR1();
            a51Activity.DIR2 = currentItem.getDIR2();
            a51Activity.DIR3 = currentItem.getDIR3();
            a51Activity.DIR4 = currentItem.getDIR4();

            a51Activity.DEV_MBL = DEV_MBL;
            a51Activity.DEV_MBL2 = DEV_MBL2;
            a51Activity.DEV_MBL3 = DEV_MBL3;
            a51Activity.DEV_MBL4 = DEV_MBL4;
            a51Activity.DEV_MBT = DEV_MBT;
            a51Activity.DEV_MBT2 = DEV_MBT2;
            a51Activity.DEV_MBT3 = DEV_MBT3;
            a51Activity.DEV_MBT4 = DEV_MBT4;
            a51Activity.DEV_MBT5 = DEV_MBT5;
            a51Activity.DEV_MPR = DEV_MPR;
            a51Activity.DEV_MPR2 = DEV_MPR2;
            a51Activity.DEV_MPR3 = DEV_MPR3;
            a51Activity.DEV_MPR4 = DEV_MPR4;
            a51Activity.DEV_MPR5 = DEV_MPR5;
            a51Activity.DEV_MPR6 = DEV_MPR6;
            a51Activity.DEV_MPR7 = DEV_MPR7;
            a51Activity.DEV_ZBC = DEV_ZBC;
            a51Activity.DEV_ZBC2 = DEV_ZBC2;
            a51Activity.DEV_ZBC3 = DEV_ZBC3;

            a51Activity.KTG_MBLL = KTG_MBLL;
            a51Activity.KTG_MBTT = KTG_MBTT;
            a51Activity.KTG_MPRR = KTG_MPRR;
            a51Activity.KTG_ZBCC = KTG_ZBCC;
            a51Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a51Activity.klf.setText(KTG_KLF);
                a51Activity.klf.setBackground(a51Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a51Activity.getApplicationContext(), R.anim.recycle_bottom);
                a51Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a51Activity.klf.setText("Tidak Dinilai");
                a51Activity.klf.setBackground(a51Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a51Activity.getApplicationContext(), R.anim.recycle_bottom);
                a51Activity.klf.startAnimation(animation);
            } else {
                a51Activity.klf.setText(KTG_KLF);
                a51Activity.klf.setBackground(a51Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a51Activity.getApplicationContext(), R.anim.recycle_bottom);
                a51Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a51Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
