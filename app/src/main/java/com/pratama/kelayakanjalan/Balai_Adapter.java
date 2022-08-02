package com.pratama.kelayakanjalan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Balai_Adapter extends RecyclerView.Adapter<Balai_Adapter.BalaiViewHolder> {

    private IdentitasActivity identitasActivity;
    private List<Balai_Class> mdataList;

    public class BalaiViewHolder extends RecyclerView.ViewHolder {

        private TextView NBL;
        private CardView posisi;

        public BalaiViewHolder(@NonNull View itemView) {
            super(itemView);

            NBL = (TextView) itemView.findViewById(R.id.NBL);
            posisi = (CardView) itemView.findViewById(R.id.posisi);

        }

    }

    public Balai_Adapter(IdentitasActivity identitasActivity, List<Balai_Class> mdataList) {
        this.identitasActivity = identitasActivity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public Balai_Adapter.BalaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balai_layout, parent, false);
        Balai_Adapter.BalaiViewHolder balai = new Balai_Adapter.BalaiViewHolder(view);
        return balai;
    }

    @Override
    public void onBindViewHolder(@NonNull Balai_Adapter.BalaiViewHolder holder, int position) {

        final Balai_Class currentItem = mdataList.get(position);

        holder.NBL.setText(currentItem.getNBL());

        holder.posisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identitasActivity.PJL.setText(currentItem.getNBL());
                identitasActivity.balai_data.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    public void filterList(List<Balai_Class> filteredList) {
        mdataList = filteredList;
        notifyDataSetChanged();

        if(mdataList.isEmpty()){
            identitasActivity.balai_data.setVisibility(View.GONE);
        } else {
            identitasActivity.balai_data.setVisibility(View.VISIBLE);
        }

    }

}
