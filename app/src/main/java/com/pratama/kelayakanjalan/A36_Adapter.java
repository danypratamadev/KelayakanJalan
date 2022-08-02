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

public class A36_Adapter extends RecyclerView.Adapter<A36_Adapter.A36ViewHolder> {

    private A36Activity a36Activity;
    private List<A36_Class> mdataList;
    private double DEV_DMA, DEV_KAT, DEV_KAK, DEV_KAB, DEV_BDS, DEV_TBL, STD = 100;
    private int SDMA_IN, SBDS_IN;
    private String KTG_DMA, KTG_KAT, KTG_KAK, KTG_KAB, KTG_KAA, KTG_BDS, KTG_TBL, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A36ViewHolder extends RecyclerView.ViewHolder {

        private TextView dma_data, dma_dev, sdma_data, dma_hasil, kat_data, kat_dev, kak_data, kak_dev,
                kab_data, kab_dev, kaa_hasil, bds_data, bds_dev, sbds_data, bds_hasil, tbl_data, tbl_dev,
                tbl_hasil, rec_data;

        private LinearLayout dma_back, kaa_back, bds_back, tbl_back;
        private ImageView FT1, FT2;

        public A36ViewHolder(@NonNull View itemView) {
            super(itemView);

            dma_data = (TextView) itemView.findViewById(R.id.dma_data);
            dma_dev = (TextView) itemView.findViewById(R.id.dma_dev);
            sdma_data = (TextView) itemView.findViewById(R.id.sdma_data);
            dma_hasil = (TextView) itemView.findViewById(R.id.dma_hasil);

            kat_data = (TextView) itemView.findViewById(R.id.kat_data);
            kat_dev = (TextView) itemView.findViewById(R.id.kat_dev);
            kak_data = (TextView) itemView.findViewById(R.id.kak_data);
            kak_dev = (TextView) itemView.findViewById(R.id.kak_dev);
            kab_data = (TextView) itemView.findViewById(R.id.kab_data);
            kab_dev = (TextView) itemView.findViewById(R.id.kab_dev);
            kaa_hasil = (TextView) itemView.findViewById(R.id.kaa_hasil);

            bds_data = (TextView) itemView.findViewById(R.id.bds_data);
            bds_dev = (TextView) itemView.findViewById(R.id.bds_dev);
            sbds_data = (TextView) itemView.findViewById(R.id.sbds_data);
            bds_hasil = (TextView) itemView.findViewById(R.id.bds_hasil);

            tbl_data = (TextView) itemView.findViewById(R.id.tbl_data);
            tbl_dev = (TextView) itemView.findViewById(R.id.tbl_dev);
            tbl_hasil = (TextView) itemView.findViewById(R.id.tbl_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            dma_back = (LinearLayout) itemView.findViewById(R.id.dma_back);
            kaa_back = (LinearLayout) itemView.findViewById(R.id.kaa_back);
            bds_back = (LinearLayout) itemView.findViewById(R.id.bds_back);
            tbl_back = (LinearLayout) itemView.findViewById(R.id.tbl_back);

        }

    }

    public A36_Adapter(A36Activity a36Activity, List<A36_Class> mdataList) {
        this.a36Activity = a36Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A36_Adapter.A36ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a36_layout, parent, false);
        A36_Adapter.A36ViewHolder a36 = new A36_Adapter.A36ViewHolder(view);
        return a36;
    }

    @Override
    public void onBindViewHolder(@NonNull A36_Adapter.A36ViewHolder holder, int position) {

        final A36_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getDMA() == -1){
                DEV_DMA = -1;
                KTG_DMA = "-";
                holder.sdma_data.setText("-");
                holder.dma_data.setText("-");
                holder.dma_dev.setText("-");
                holder.dma_hasil.setText(KTG_DMA);
                holder.dma_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getSDMA().equals("Trapesium")){
                    SDMA_IN = 1;
                } else if(currentItem.getSDMA().equals("Segitiga")) {
                    SDMA_IN = 2;
                } else if(currentItem.getSDMA().equals("Segiempat")) {
                    SDMA_IN = 3;
                } else if(currentItem.getSDMA().equals("Lingkaran")){
                    SDMA_IN = 4;
                } else {
                    SDMA_IN = 5;
                }
                holder.sdma_data.setText("("+String.valueOf(SDMA_IN)+") "+currentItem.getSDMA());
                DEV_DMA = STD - currentItem.getDMA();
                if(DEV_DMA == 0){
                    KTG_DMA = "LF";
                    holder.dma_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_DMA = "LS";
                    holder.dma_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.dma_data.setText(String.valueOf(currentItem.getDMA())+"%");
                holder.dma_dev.setText(String.valueOf(DEV_DMA)+"%");
                holder.dma_hasil.setText(KTG_DMA);
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAT() == -1){
                DEV_KAT = -1;
                KTG_KAT = "-";
                holder.kat_data.setText("-");
                holder.kat_dev.setText("-");
            } else {
                if(currentItem.getKAT() >= 0 && currentItem.getKAT() <= 5){
                    DEV_KAT = 0;
                    KTG_KAT = "LF";
                } else {
                    DEV_KAT = (currentItem.getKAT() - 5)/5;
                    DEV_KAT = (DEV_KAT * 100);
                    if(DEV_KAT < 0){
                        DEV_KAT = DEV_KAT * -1;
                    }
                    if(DEV_KAT > 100){
                        DEV_KAT = 100;
                    }
                    DEV_KAT = Double.parseDouble(df.format(DEV_KAT).replace(",", "."));
                    KTG_KAT = "LS";
                }
                holder.kat_data.setText(String.valueOf(currentItem.getKAT())+"%");
                holder.kat_dev.setText(String.valueOf(DEV_KAT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAK() == -1){
                DEV_KAK = -1;
                KTG_KAK = "-";
                holder.kak_data.setText("-");
                holder.kak_dev.setText("-");
            } else {
                if(currentItem.getKAK() >= 5 && currentItem.getKAK() <= 7.5){
                    DEV_KAK = 0;
                    KTG_KAK = "LF";
                } else {
                    if(currentItem.getKAK() < 5){
                        DEV_KAK = (currentItem.getKAK() -5)/5;
                    } else {
                        DEV_KAK = (currentItem.getKAK() -7.5)/7.5;
                    }
                    DEV_KAK = (DEV_KAK * 100);
                    if(DEV_KAK < 0){
                        DEV_KAK = DEV_KAK * -1;
                    }
                    if(DEV_KAK > 100){
                        DEV_KAK = 100;
                    }
                    DEV_KAK = Double.parseDouble(df.format(DEV_KAK).replace(",", "."));
                    KTG_KAK = "LS";
                }
                holder.kak_data.setText(String.valueOf(currentItem.getKAK())+"%");
                holder.kak_dev.setText(String.valueOf(DEV_KAK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAB() == -1){
                DEV_KAB = -1;
                KTG_KAB = "-";
                holder.kab_data.setText("-");
                holder.kab_dev.setText("-");
            } else {
                if(currentItem.getKAB() <= 7.5){
                    DEV_KAB = 0;
                    KTG_KAB = "LF";
                } else {
                    DEV_KAB = (currentItem.getKAB() -7.5)/7.5;
                    DEV_KAB = (DEV_KAB * 100);
                    if(DEV_KAB < 0){
                        DEV_KAB = DEV_KAB * -1;
                    }
                    if(DEV_KAB > 100){
                        DEV_KAB = 100;
                    }
                    DEV_KAB = Double.parseDouble(df.format(DEV_KAB).replace(",", "."));
                    KTG_KAB = "LS";
                }
                holder.kab_data.setText(String.valueOf(currentItem.getKAB())+"%");
                holder.kab_dev.setText(String.valueOf(DEV_KAB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KAT.equals("LF") || KTG_KAT.equals("-")) && (KTG_KAK.equals("LF") || KTG_KAK.equals("-"))
                    && (KTG_KAB.equals("LF") || KTG_KAB.equals("-"))){

                if(KTG_KAT.equals("-") && KTG_KAK.equals("-") && KTG_KAB.equals("-")){
                    KTG_KAA = "-";
                    holder.kaa_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KAA = "LF";
                    holder.kaa_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KAA = "LS";
                holder.kaa_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kaa_hasil.setText(KTG_KAA);
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getBDS() == -1){
                DEV_BDS = -1;
                KTG_BDS = "-";
                holder.sbds_data.setText("-");
                holder.bds_data.setText("-");
                holder.bds_dev.setText("-");
                holder.bds_hasil.setText(KTG_BDS);
                holder.bds_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getSBDS().equals("Pasir Halus")){
                    SBDS_IN = 1;
                } else if(currentItem.getSBDS().equals("Lempung Kepasiran")) {
                    SBDS_IN = 2;
                } else if(currentItem.getSBDS().equals("Lanau Alluvial")) {
                    SBDS_IN = 3;
                } else if(currentItem.getSBDS().equals("Kerikil Halus")) {
                    SBDS_IN = 4;
                } else if(currentItem.getSBDS().equals("Lempung Kokoh")) {
                    SBDS_IN = 5;
                } else if(currentItem.getSBDS().equals("Lempung Padat")) {
                    SBDS_IN = 6;
                } else if(currentItem.getSBDS().equals("Kerikil Kasar")) {
                    SBDS_IN = 7;
                } else if(currentItem.getSBDS().equals("Batu-batu Besar")) {
                    SBDS_IN = 8;
                } else if(currentItem.getSBDS().equals("Pasangan Batu")) {
                    SBDS_IN = 9;
                } else if(currentItem.getSBDS().equals("Beton")) {
                    SBDS_IN = 10;
                } else if(currentItem.getSBDS().equals("Beton Bertulang")){
                    SBDS_IN = 11;
                } else {
                    SBDS_IN = 12;
                }
                holder.sbds_data.setText("("+String.valueOf(SBDS_IN)+") "+currentItem.getSBDS());
                DEV_BDS = STD - currentItem.getBDS();
                if(DEV_BDS == 0){
                    KTG_BDS = "LF";
                    holder.bds_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_BDS = "LS";
                    holder.bds_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.bds_data.setText(String.valueOf(currentItem.getBDS())+"%");
                holder.bds_dev.setText(String.valueOf(DEV_BDS)+"%");
                holder.bds_hasil.setText(KTG_BDS);
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTBL() == -1){
                DEV_TBL = -1;
                KTG_TBL = "-";
                holder.tbl_data.setText("-");
                holder.tbl_dev.setText("-");
                holder.tbl_hasil.setText(KTG_TBL);
                holder.tbl_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_TBL = STD - currentItem.getTBL();
                if(DEV_TBL == 0){
                    KTG_TBL = "LF";
                    holder.tbl_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_TBL = "LS";
                    holder.tbl_back.setBackground(a36Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.tbl_data.setText(String.valueOf(currentItem.getTBL())+"%");
                holder.tbl_dev.setText(String.valueOf(DEV_TBL)+"%");
                holder.tbl_hasil.setText(KTG_TBL);
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_DMA.equals("LF") || KTG_DMA.equals("-")) && (KTG_KAA.equals("LF") || KTG_KAA.equals("-"))
                    && (KTG_BDS.equals("LF") || KTG_BDS.equals("-")) && (KTG_TBL.equals("LF") || KTG_TBL.equals("-"))){

                if(KTG_DMA.equals("-") && KTG_KAA.equals("-") && KTG_BDS.equals("-") && KTG_TBL.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a36Activity.FAB_UPLOAD.hide();
                a36Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a36Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a36Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a36Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a36Activity.ID = currentItem.getID();
            a36Activity.DMA_TXT = currentItem.getDMA();
            a36Activity.SDMA_IN = SDMA_IN;
            a36Activity.SDMA_TXT = currentItem.getSDMA();
            a36Activity.KAT_TXT = currentItem.getKAT();
            a36Activity.KAK_TXT = currentItem.getKAK();
            a36Activity.KAB_TXT = currentItem.getKAB();
            a36Activity.SBDS_TXT = currentItem.getSBDS();
            a36Activity.SBDS_IN = SBDS_IN;
            a36Activity.BDS_TXT = currentItem.getBDS();
            a36Activity.TBL_TXT = currentItem.getTBL();
            a36Activity.REC_TXT = currentItem.getREC();
            a36Activity.DIR1 = currentItem.getDIR1();
            a36Activity.DIR2 = currentItem.getDIR2();

            a36Activity.DEV_DMA = DEV_DMA;
            a36Activity.DEV_KAT = DEV_KAT;
            a36Activity.DEV_KAK = DEV_KAK;
            a36Activity.DEV_KAB = DEV_KAB;
            a36Activity.DEV_BDS = DEV_BDS;
            a36Activity.DEV_TBL = DEV_TBL;

            a36Activity.KTG_DMA = KTG_DMA;
            a36Activity.KTG_KAA = KTG_KAA;
            a36Activity.KTG_BDS = KTG_BDS;
            a36Activity.KTG_TBL = KTG_TBL;
            a36Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a36Activity.klf.setText(KTG_KLF);
                a36Activity.klf.setBackground(a36Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a36Activity.getApplicationContext(), R.anim.recycle_bottom);
                a36Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a36Activity.klf.setText("Tidak Dinilai");
                a36Activity.klf.setBackground(a36Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a36Activity.getApplicationContext(), R.anim.recycle_bottom);
                a36Activity.klf.startAnimation(animation);
            } else {
                a36Activity.klf.setText(KTG_KLF);
                a36Activity.klf.setBackground(a36Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a36Activity.getApplicationContext(), R.anim.recycle_bottom);
                a36Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a36Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
