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

public class Balai_Adapter2 extends RecyclerView.Adapter<Balai_Adapter2.BalaiViewHolder> {

    private FilterActivity filterActivity;
    private List<Balai_Class> mdataList;
    private int row_index = -1;

    public class BalaiViewHolder extends RecyclerView.ViewHolder {

        private TextView NBL;
        private CardView posisi;
        private RelativeLayout back_balai;

        public BalaiViewHolder(@NonNull View itemView) {
            super(itemView);

            NBL = (TextView) itemView.findViewById(R.id.NBL);
            posisi = (CardView) itemView.findViewById(R.id.back_click);
            back_balai = (RelativeLayout) itemView.findViewById(R.id.back_balai);

        }

    }

    public Balai_Adapter2(FilterActivity filterActivity, List<Balai_Class> balaiArrayList) {
        this.filterActivity = filterActivity;
        this.mdataList = balaiArrayList;
    }

    @NonNull
    @Override
    public Balai_Adapter2.BalaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balai2_layout, parent, false);
        Balai_Adapter2.BalaiViewHolder balai = new Balai_Adapter2.BalaiViewHolder(view);
        return balai;
    }

    @Override
    public void onBindViewHolder(@NonNull final Balai_Adapter2.BalaiViewHolder holder, final int position) {

        final Balai_Class currentItem = mdataList.get(position);

        holder.NBL.setText(currentItem.getNBL());

        holder.posisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                filterActivity.balai = currentItem.getNBL();
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
