package com.pratama.kelayakanjalan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Ppk_Adapter2 extends RecyclerView.Adapter<Ppk_Adapter2.PkkViewHolder> {

    private FilterActivity filterActivity;
    private List<Ppk_Class> mdataList;
    private int row_index = -1;

    public class PkkViewHolder extends RecyclerView.ViewHolder {

        private TextView NBL;
        private CardView posisi;
        private RelativeLayout back_balai;

        public PkkViewHolder(@NonNull View itemView) {
            super(itemView);

            NBL = (TextView) itemView.findViewById(R.id.NBL);
            posisi = (CardView) itemView.findViewById(R.id.back_click);
            back_balai = (RelativeLayout) itemView.findViewById(R.id.back_balai);

        }
    }

    public Ppk_Adapter2(FilterActivity filterActivity, List<Ppk_Class> mdataList) {
        this.filterActivity = filterActivity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public Ppk_Adapter2.PkkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balai2_layout, parent, false);
        Ppk_Adapter2.PkkViewHolder pkk = new Ppk_Adapter2.PkkViewHolder(view);
        return pkk;
    }

    @Override
    public void onBindViewHolder(@NonNull Ppk_Adapter2.PkkViewHolder holder, final int position) {
        final Ppk_Class currentItem = mdataList.get(position);

        holder.NBL.setText(currentItem.getNPK());

        holder.posisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                filterActivity.ppk = currentItem.getID();
            }
        });

        if(row_index == position){
            holder.back_balai.setBackgroundColor(filterActivity.getResources().getColor(R.color.Blue200));
        } else {
            holder.back_balai.setBackgroundColor(filterActivity.getResources().getColor(R.color.Gray200));
        }
    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
