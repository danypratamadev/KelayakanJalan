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

public class A23_Adapter extends RecyclerView.Adapter<A23_Adapter.A23ViewHolder> {

    private A23Activity a23Activity;
    private List<A23_Class> mdataList;
    private double DEV_PLL, DEV_KKT, DEV_DPP, DEV_BPK, STD = 100;
    private String KTG_PLL, KTG_KKT, KTG_DPP, KTG_BPK, KTG_KLF;
    private int SBPK_IN;

    public class A23ViewHolder extends RecyclerView.ViewHolder {

        private TextView pll_data, pll_dev, pll_hasil, kkt_data, kkt_dev, kkt_hasil,
                dpp_data, dpp_dev, dpp_hasil, sbpk_data, bpk_data, bpk_dev, bpk_hasil, rec_data;

        private LinearLayout pll_back, kkt_back, dpp_back, bpk_back;
        private ImageView FT1, FT2;

        public A23ViewHolder(@NonNull View itemView) {
            super(itemView);

            pll_data = (TextView) itemView.findViewById(R.id.pll_data);
            pll_dev = (TextView) itemView.findViewById(R.id.pll_dev);
            pll_hasil = (TextView) itemView.findViewById(R.id.pll_hasil);

            kkt_data = (TextView) itemView.findViewById(R.id.kkt_data);
            kkt_dev = (TextView) itemView.findViewById(R.id.kkt_dev);
            kkt_hasil = (TextView) itemView.findViewById(R.id.kkt_hasil);

            dpp_data = (TextView) itemView.findViewById(R.id.dpp_data);
            dpp_dev = (TextView) itemView.findViewById(R.id.dpp_dev);
            dpp_hasil = (TextView) itemView.findViewById(R.id.dpp_hasil);

            sbpk_data = (TextView) itemView.findViewById(R.id.sbpk_data);
            bpk_data = (TextView) itemView.findViewById(R.id.bpk_data);
            bpk_dev = (TextView) itemView.findViewById(R.id.bpk_dev);
            bpk_hasil = (TextView) itemView.findViewById(R.id.bpk_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            pll_back = (LinearLayout) itemView.findViewById(R.id.pll_back);
            kkt_back = (LinearLayout) itemView.findViewById(R.id.kkt_back);
            dpp_back = (LinearLayout) itemView.findViewById(R.id.dpp_back);
            bpk_back = (LinearLayout) itemView.findViewById(R.id.bpk_back);

        }

    }

    public A23_Adapter(A23Activity a23Activity, List<A23_Class> mdataList) {
        this.a23Activity = a23Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A23_Adapter.A23ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a23_layout, parent, false);
        A23_Adapter.A23ViewHolder a23 = new A23_Adapter.A23ViewHolder(view);
        return a23;
    }

    @Override
    public void onBindViewHolder(@NonNull A23_Adapter.A23ViewHolder holder, int position) {

        final A23_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getPLL() == -1){
                holder.pll_data.setText("-");
                DEV_PLL = -1;
                KTG_PLL = "-";
                holder.pll_dev.setText("-");
                holder.pll_hasil.setText(KTG_PLL);
                holder.pll_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.pll_data.setText(String.valueOf(currentItem.getPLL())+"%");
                DEV_PLL = STD - currentItem.getPLL();
                if(DEV_PLL == 0){
                    KTG_PLL = "LF";
                    holder.pll_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PLL = "LS";
                    holder.pll_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.pll_dev.setText(String.valueOf(DEV_PLL)+"%");
                holder.pll_hasil.setText(KTG_PLL);
            }
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKKT() == -1){
                holder.kkt_data.setText("-");
                DEV_KKT = -1;
                KTG_KKT = "-";
                holder.kkt_dev.setText("-");
                holder.kkt_hasil.setText(KTG_KKT);
                holder.kkt_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.kkt_data.setText(String.valueOf(currentItem.getKKT())+"%");
                DEV_KKT = STD - currentItem.getKKT();
                if(DEV_KKT == 0){
                    KTG_KKT = "LF";
                    holder.kkt_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KKT = "LS";
                    holder.kkt_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kkt_dev.setText(String.valueOf(DEV_KKT)+"%");
                holder.kkt_hasil.setText(KTG_KKT);
            }
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDPP() == -1){
                holder.dpp_data.setText("-");
                DEV_DPP = -1;
                KTG_DPP = "-";
                holder.dpp_dev.setText("-");
                holder.dpp_hasil.setText(KTG_DPP);
                holder.dpp_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.dpp_data.setText(String.valueOf(currentItem.getDPP())+"%");
                DEV_DPP = STD - currentItem.getDPP();
                if(DEV_DPP == 0){
                    KTG_DPP = "LF";
                    holder.dpp_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_DPP = "LS";
                    holder.dpp_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.dpp_dev.setText(String.valueOf(DEV_DPP)+"%");
                holder.dpp_hasil.setText(KTG_DPP);
            }
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getSBPK().equals("Perkerasan Lentur")){
                SBPK_IN = 1;
            } else {
                SBPK_IN = 2;
            }

            holder.sbpk_data.setText(String.valueOf(SBPK_IN));
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getBPK() == -1){
                DEV_BPK = -1;
                KTG_BPK = "-";
                holder.bpk_data.setText("-");
                holder.bpk_dev.setText("-");
                holder.bpk_hasil.setText(KTG_BPK);
                holder.bpk_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_BPK = STD - currentItem.getBPK();
                if(DEV_BPK == 0){
                    KTG_BPK = "LF";
                    holder.bpk_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_BPK = "LS";
                    holder.bpk_back.setBackground(a23Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.bpk_data.setText(String.valueOf(currentItem.getBPK())+"%");
                holder.bpk_dev.setText(String.valueOf(DEV_BPK)+"%");
                holder.bpk_hasil.setText(KTG_BPK);
            }
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PLL.equals("LF") || KTG_PLL.equals("-")) && (KTG_KKT.equals("LF") || KTG_KKT.equals("-")) &&
                    (KTG_DPP.equals("LF") || KTG_DPP.equals("-")) && (KTG_BPK.equals("LF") || KTG_BPK.equals("-"))){

                if(KTG_PLL.equals("-") && KTG_KKT.equals("-") && KTG_DPP.equals("-") && KTG_BPK.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a23Activity.FAB_UPLOAD.hide();
                a23Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a23Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a23Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a23Activity.FAB_UPLOAD.setEnabled(false);
            }

            a23Activity.ID = currentItem.getID();
            a23Activity.PLL_TXT = currentItem.getPLL();
            a23Activity.KKT_TXT = currentItem.getKKT();
            a23Activity.DPP_TXT = currentItem.getDPP();
            a23Activity.SBPK_IN = SBPK_IN;
            a23Activity.SBPK_TXT = currentItem.getSBPK();
            a23Activity.BPK_TXT = currentItem.getBPK();
            a23Activity.REC_TXT = currentItem.getREC();
            a23Activity.DIR1 = currentItem.getDIR1();
            a23Activity.DIR2 = currentItem.getDIR2();

            a23Activity.DEV_PLL = DEV_PLL;
            a23Activity.DEV_KKT = DEV_KKT;
            a23Activity.DEV_DPP = DEV_DPP;
            a23Activity.DEV_BPK = DEV_BPK;

            a23Activity.KTG_PLL = KTG_PLL;
            a23Activity.KTG_KKT = KTG_KKT;
            a23Activity.KTG_DPP = KTG_DPP;
            a23Activity.KTG_BPK = KTG_BPK;
            a23Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a23Activity.klf.setText(KTG_KLF);
                a23Activity.klf.setBackground(a23Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a23Activity.getApplicationContext(), R.anim.recycle_bottom);
                a23Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a23Activity.klf.setText("Tidak Dinilai");
                a23Activity.klf.setBackground(a23Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a23Activity.getApplicationContext(), R.anim.recycle_bottom);
                a23Activity.klf.startAnimation(animation);
            } else {
                a23Activity.klf.setText(KTG_KLF);
                a23Activity.klf.setBackground(a23Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a23Activity.getApplicationContext(), R.anim.recycle_bottom);
                a23Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a23Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
