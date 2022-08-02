package com.pratama.kelayakanjalan;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_Sheet_Prov extends BottomSheetDialogFragment {

    private BottomSheetProvListener mListener;
    private LinearLayout edit, upload, delete, download;
    private TextView nmpro, edit_txt, upload_txt, delete_txt, download_txt;
    private ImageView edit_img, upload_img, delete_img, download_img;
    public String NMPRO = "-", ID = "-", OP = "-", UPL = "-", NMSEG = "-", DPSG = "-", KPSG = "-", DKT="-", IRU = "-";
    public double PSG;
    public Boolean CONN = false;
    public int HPS;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_shet_prov, container, false);

        edit = view.findViewById(R.id.edit);
        upload = view.findViewById(R.id.upload);
        delete = view.findViewById(R.id.delete);
        download = view.findViewById(R.id.download);
        nmpro = view.findViewById(R.id.nmpro);
        edit_txt = view.findViewById(R.id.edit_txt);
        upload_txt = view.findViewById(R.id.upload_txt);
        delete_txt = view.findViewById(R.id.delete_txt);
        download_txt = view.findViewById(R.id.download_txt);
        edit_img = view.findViewById(R.id.edit_img);
        upload_img = view.findViewById(R.id.upload_img);
        delete_img = view.findViewById(R.id.delete_img);
        download_img = view.findViewById(R.id.download_img);

        if(CONN == false){
            upload.setEnabled(false);
            delete.setEnabled(false);
            download.setEnabled(false);
            upload_txt.setTextColor(getActivity().getResources().getColor(R.color.Gray400));
            delete_txt.setTextColor(getActivity().getResources().getColor(R.color.Gray400));
            download_txt.setTextColor(getActivity().getResources().getColor(R.color.Gray400));
            upload_img.setColorFilter(ContextCompat.getColor(getActivity(), R.color.Gray400));
            delete_img.setColorFilter(ContextCompat.getColor(getActivity(), R.color.Gray400));
            download_img.setColorFilter(ContextCompat.getColor(getActivity(), R.color.Gray400));
        } else {
            if(UPL.equals("T")){
                upload.setEnabled(false);
                upload_txt.setTextColor(getActivity().getResources().getColor(R.color.Gray400));
                upload_img.setColorFilter(ContextCompat.getColor(getActivity(), R.color.Gray400));
            }
        }

        if(OP.equals("PRO")){
            nmpro.setText("PROV: "+NMPRO);
            edit_txt.setText("Edit provinsi");
            upload_txt.setText("Upload provinsi");
            delete_txt.setText("Delete provinsi");
            download.setEnabled(false);
            download_txt.setTextColor(getActivity().getResources().getColor(R.color.Gray400));
            download_img.setColorFilter(ContextCompat.getColor(getActivity(), R.color.Gray400));
        } else {
            nmpro.setText("SEGMEN: "+NMSEG);
            edit_txt.setText("Edit segmen");
            upload_txt.setText("Upload segmen");
            delete_txt.setText("Delete segmen");
            download_txt.setText("Download segmen "+NMSEG+".xls");
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClicked("Edit", ID, NMPRO, HPS, NMSEG, DPSG, KPSG, DKT, PSG, IRU);
                dismiss();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClicked("Upload", ID, NMPRO, HPS, NMSEG, DPSG, KPSG, DKT, PSG, IRU);
                dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClicked("Delete", ID, NMPRO, HPS, NMSEG, DPSG, KPSG, DKT, PSG, IRU);
                dismiss();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClicked("Download", ID, NMPRO, HPS, NMSEG, DPSG, KPSG, DKT, PSG, IRU);
                dismiss();
            }
        });

        return view;

    }

    public interface BottomSheetProvListener{

        void onClicked(String code, String id, String nm, int hapus, String nmseg, String dpsg, String kpsg, String dkt, double psg, String iru);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetProvListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" Harus Implementasikan BottomSheetProvListener");
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }
}