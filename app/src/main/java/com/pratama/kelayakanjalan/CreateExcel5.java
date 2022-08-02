package com.pratama.kelayakanjalan;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public final class CreateExcel5 {

    private WritableCellFormat DoubleCellFormat;
    private WritableCellFormat IntegerCellFormat;
    private WritableCellFormat PercentCellFormat;
    private TextView progress;
    private NumberProgressBar percent;
    private String idSegmen, filePath, NRU, SEG;

    //Inisialisasi variabel input data uji dan deviasi
    private Number number;
    private Label label;
    private final String sistemJaringan, status, fungsi, kelasPrasarana, kelasPenggunaan, medan;
    private int idxGambar;

    //inisialisasi variabel kategori per poin
    private String kategoriA11;
    private String kategoriA12;
    private String kategoriA13;
    private String kategoriA14;
    private String kategoriA1;
    private String kategoriA2;
    private String kategoriA3;
    private String kategoriA4;
    private String kategoriA5;
    private String kategoriA6a;
    private String kategoriA6b;

    //inisialisasi variabel localpath --> direktori penyimpanan foto di dalam HP
    private final String localPath;

    private static WritableWorkbook fileXLS;
    private static WritableSheet sheet;

    public CreateExcel5(String idsegmen, String filepath, String nru, String seg, TextView progres, NumberProgressBar percen) throws IOException, BiffException, WriteException {

        this.idSegmen = idsegmen;
        this.filePath = filepath;
        this.NRU = nru;
        this.SEG = seg;
        this.progress = progres;
        this.percent = percen;

        Log.e("Laporan", "Tereksekusi");

        //buka template dokumen
        //String filePath = "D:/Documents/Jobs/IT/Aplikasi Uji Laik Fungsi Jalan/";
        Workbook template = Workbook.getWorkbook(new File(filePath));

        //buat copy template
        String segmen = "Segmen " + idSegmen;
        fileXLS = Workbook.createWorkbook(new File(filePath), template);
        sheet = fileXLS.getSheet(0); //Sheet1 = 0

        //memformat CellFormat via method
        setIntegerCellFormat();
        setDoubleCellFormat();
        setPercentCellFormat();

        //set variabel kategori
        kategoriA11 = "LF";
        kategoriA12 = "LF";
        kategoriA13 = "LF";
        kategoriA14 = "LF";
        kategoriA1 = "LF";
        kategoriA2 = "LF";
        kategoriA3 = "LF";
        kategoriA4 = "LF";
        kategoriA5 = "LF";
        kategoriA6a = "LF";
        kategoriA6b = "LF";

        //ambil data klasifikasi jalan dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        String[] dataKlasifikasi = koneksi.getKlasifikasiJalan(idSegmen);
        sistemJaringan = dataKlasifikasi[0];
        status = dataKlasifikasi[1];
        fungsi = dataKlasifikasi[2];
        kelasPrasarana = dataKlasifikasi[3];
        kelasPenggunaan = dataKlasifikasi[4];
        medan = dataKlasifikasi[5];

        //set nilai awal idxGambar
        idxGambar = 1;

        //set nilai localPath foto di dalam HP
        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU+"/Segmen "+SEG+"/Pictures/");
        if(!folder.exists()){
            folder.mkdirs();
        }

        File mediaStorageDir = null;
        String state = Environment.getExternalStorageState();

        if (state.contains(Environment.MEDIA_MOUNTED)) {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU+"/Segmen "+SEG+"/Pictures/image");
        } else {
            mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU+"/Segmen "+SEG+"/Pictures/image");
        }

        localPath = mediaStorageDir.getAbsolutePath();

        //insert data
        try{

            insertIdentitasRuas(idSegmen);
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(2);
                }
            });

        } catch (Exception e){
            Log.e("Error identitas ", e.toString());
        }

        try{

            insertKlasifikasiJalan();
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(4);
                }
            });

        } catch (Exception e){
            Log.e("Error klasifikasi ", e.toString());
        }

        try{

            insertSegmen(idSegmen);
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(6);
                }
            });

        } catch (Exception e){
            Log.e("Error segmen ", e.toString());
        }

        try{

            insertDataA111(idSegmen);       //A111
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(8);
                }
            });

        } catch (Exception e){
            Log.e("Error a111 ", e.toString());
        }

        try{

            insertDataA112(idSegmen);       //A112
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(10);
                }
            });

        } catch (Exception e){
            Log.e("Error a112 ", e.toString());
        }

        try{

            insertDataA113(idSegmen);       //A113
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(12);
                }
            });

        } catch (Exception e){
            Log.e("Error a113 ", e.toString());
        }

        try{

            insertDataA114(idSegmen);       //A114
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(14);
                }
            });

        } catch (Exception e){
            Log.e("Error a114 ", e.toString());
        }

        try{

            insertDataA115(idSegmen);       //A115
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(16);
                }
            });

        } catch (Exception e){
            Log.e("Error a115 ", e.toString());
        }

        try{

            insertDataA116(idSegmen);       //A116
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(18);
                }
            });

        } catch (Exception e){
            Log.e("Error a116 ", e.toString());
        }

        try{

            insertDataA121(idSegmen);       //A121
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(20);
                }
            });

        } catch (Exception e){
            Log.e("Error a121 ", e.toString());
        }

        try{

            insertDataA122(idSegmen);       //A122
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(22);
                }
            });

        } catch (Exception e){
            Log.e("Error a122 ", e.toString());
        }

        try{

            insertDataA123(idSegmen);       //A123
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(24);
                }
            });

        } catch (Exception e){
            Log.e("Error a123 ", e.toString());
        }

        try{

            insertDataA124(idSegmen);       //A124
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(26);
                }
            });

        } catch (Exception e){
            Log.e("Error a124 ", e.toString());
        }

        try{

            insertDataA131(idSegmen);       //A131
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(28);
                }
            });

        } catch (Exception e){
            Log.e("Error a131 ", e.toString());
        }

        try{

            insertDataA132(idSegmen);       //A132
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(30);
                }
            });

        } catch (Exception e){
            Log.e("Error a132 ", e.toString());
        }

        try{

            insertDataA133(idSegmen);       //A133
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(32);
                }
            });

        } catch (Exception e){
            Log.e("Error a133 ", e.toString());
        }

        try{

            insertDataA141(idSegmen);       //A141
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(34);
                }
            });

        } catch (Exception e){
            Log.e("Error a141 ", e.toString());
        }

        try{

            insertDataA21(idSegmen);       //A21
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(36);
                }
            });

        } catch (Exception e){
            Log.e("Error a21 ", e.toString());
        }

        try{

            insertDataA22(idSegmen);       //A22
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(38);
                }
            });

        } catch (Exception e){
            Log.e("Error a22 ", e.toString());
        }

        try{

            insertDataA23(idSegmen);       //A23
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(40);
                }
            });

        } catch (Exception e){
            Log.e("Error a23 ", e.toString());
        }

        try{

            insertDataA31(idSegmen);       //A31
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(42);
                }
            });

        } catch (Exception e){
            Log.e("Error a31 ", e.toString());
        }

        try{

            insertDataA32(idSegmen);       //A32
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(44);
                }
            });

        } catch (Exception e){
            Log.e("Error a32 ", e.toString());
        }

        try{

            insertDataA33(idSegmen);       //A33
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(46);
                }
            });

        } catch (Exception e){
            Log.e("Error a33 ", e.toString());
        }

        try{

            insertDataA34(idSegmen);       //A34
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(48);
                }
            });

        } catch (Exception e){
            Log.e("Error a34 ", e.toString());
        }

        try{

            insertDataA35(idSegmen);       //A35
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(50);
                }
            });

        } catch (Exception e){
            Log.e("Error a35 ", e.toString());
        }

        try{

            insertDataA36(idSegmen);       //A36
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(52);
                }
            });

        } catch (Exception e){
            Log.e("Error a36 ", e.toString());
        }

        try{

            insertDataA41(idSegmen);       //A41
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(54);
                }
            });

        } catch (Exception e){
            Log.e("Error a41 ", e.toString());
        }

        try{

            insertDataA42(idSegmen);       //A42
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(56);
                }
            });

        } catch (Exception e){
            Log.e("Error a42 ", e.toString());
        }

        try{

            insertDataA43(idSegmen);       //A43
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(58);
                }
            });

        } catch (Exception e){
            Log.e("Error a43 ", e.toString());
        }

        try{

            insertDataA51(idSegmen);       //A51
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(60);
                }
            });

        } catch (Exception e){
            Log.e("Error a51 ", e.toString());
        }

        try{

            insertDataA52(idSegmen);       //A52
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(62);
                }
            });

        } catch (Exception e){
            Log.e("Error a52 ", e.toString());
        }

        try{

            insertDataA53(idSegmen);       //A53
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(64);
                }
            });

        } catch (Exception e){
            Log.e("Error a53 ", e.toString());
        }

        try{

            insertDataA54(idSegmen);       //A54
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(66);
                }
            });

        } catch (Exception e){
            Log.e("Error a54 ", e.toString());
        }

        try{

            insertDataA55(idSegmen);       //A55
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(68);
                }
            });

        } catch (Exception e){
            Log.e("Error a55 ", e.toString());
        }

        try{

            insertDataA56(idSegmen);       //A56
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(70);
                }
            });

        } catch (Exception e){
            Log.e("Error a56 ", e.toString());
        }

        try{

            insertDataA57(idSegmen);       //A57
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(72);
                }
            });

        } catch (Exception e){
            Log.e("Error a57 ", e.toString());
        }

        try{

            insertDataA6a1(idSegmen);      //A6a1
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(74);
                }
            });

        } catch (Exception e){
            Log.e("Error a6a1 ", e.toString());
        }

        try{

            insertDataA6a2(idSegmen);      //A6a2
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(76);
                }
            });

        } catch (Exception e){
            Log.e("Error a6a2 ", e.toString());
        }

        try{

            insertDataA6a3(idSegmen);      //A6a3
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(78);
                }
            });

        } catch (Exception e){
            Log.e("Error a6a3 ", e.toString());
        }

        try{

            insertDataA6a4(idSegmen);      //A6a4
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(80);
                }
            });

        } catch (Exception e){
            Log.e("Error a6a4 ", e.toString());
        }

        try{

            insertDataA6a5(idSegmen);      //A6a5
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(82);
                }
            });

        } catch (Exception e){
            Log.e("Error a6a5 ", e.toString());
        }

        try{

            insertDataA6a6(idSegmen);      //A6a6
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(84);
                }
            });

        } catch (Exception e){
            Log.e("Error a6a6 ", e.toString());
        }

        try{

            insertDataA6a7(idSegmen);      //A6a7
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(86);
                }
            });

        } catch (Exception e){
            Log.e("Error a6a7 ", e.toString());
        }

        try{

            insertDataA6b1(idSegmen);      //A6b1
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(88);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b1 ", e.toString());
        }

        try{

            insertDataA6b2(idSegmen);      //A6b2
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(90);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b2 ", e.toString());
        }

        try{

            insertDataA6b3(idSegmen);      //A6b3
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(92);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b3 ", e.toString());
        }

        try{

            insertDataA6b4(idSegmen);      //A6b4
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(94);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b4 ", e.toString());
        }

        try{

            insertDataA6b5(idSegmen);      //A6b5
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(95);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b5 ", e.toString());
        }

        try{

            insertDataA6b6(idSegmen);      //A6b6
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(96);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b6 ", e.toString());
        }

        try{

            insertDataA6b7(idSegmen);      //A6b7
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(98);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b7 ", e.toString());
        }

        try{

            insertDataA6b8(idSegmen);      //A6b8
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(100);
                }
            });

        } catch (Exception e){
            Log.e("Error a6b8 ", e.toString());
        }

        try{

            insertAdministrasiJalan(idSegmen);      //Administrasi
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    percent.setProgress(100);
                }
            });

        } catch (Exception e){
            Log.e("Error administrasi ", e.toString());
        }

        try {
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    progress.setText("finishing the download...");
                }
            });
            fileXLS.write();
            fileXLS.close();
        } catch (Exception e) {
            Log.e("Error close xls ", e.toString());
        }

        try {
            //menghapus gambar2 hasil unduhan excel agar tidak memenuhi storage hp
            deleteTemporaryImages();
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    progress.setText("downloading success...");
                }
            });
            this.idSegmen = null;
            this.filePath = null;
            this.NRU = null;
            this.SEG = null;
        } catch (Exception e){
            Log.e("Error delete image ", e.toString());
        }
    }

    private void setIntegerCellFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
        NumberFormat integerFormat = new NumberFormat("0");
        IntegerCellFormat = new WritableCellFormat(integerFormat);

        IntegerCellFormat.setFont(font);
        IntegerCellFormat.setAlignment(Alignment.CENTRE);
        IntegerCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        IntegerCellFormat.setWrap(true);
        IntegerCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    }

    private void setDoubleCellFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
        //NumberFormat doubleFormat = new NumberFormat("0.0%");
        DoubleCellFormat = new WritableCellFormat();

        DoubleCellFormat.setFont(font);
        DoubleCellFormat.setAlignment(Alignment.CENTRE);
        DoubleCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        DoubleCellFormat.setWrap(true);
        DoubleCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    }

    private void setPercentCellFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
        NumberFormat percentFormat = new NumberFormat("0.0%");
        PercentCellFormat = new WritableCellFormat(percentFormat);

        PercentCellFormat.setFont(font);
        PercentCellFormat.setAlignment(Alignment.CENTRE);
        PercentCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        PercentCellFormat.setWrap(true);
        PercentCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    }

    public void insertIdentitasRuas(String idSegmen) throws WriteException, IOException {

        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                progress.setText("downloading identitas ruas jalan...");
            }
        });

        //ambil data ruas dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        String[] dataRuas = koneksi.getDataRuasSegmen(idSegmen);

        //Inisialisasi nomor baris dan nomor kolom
        int[] nomorKolom = {5, 2, 15, 2, 15, 2, 15, 2, 0};     //F:5, P:15, C:2
        int[] nomorBaris = {3, 4, 4, 5, 5, 6, 6, 7, 15};

        // Create cell font and format 
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 11);
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setFont(cellFont);
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat.setAlignment(Alignment.LEFT);

        //tulis data
        for (int i = 0; i < nomorBaris.length; i++) {
            if (i == 8) {
                String teks = "Batas kecepatan operasional maksimal yang diizinkan: " + dataRuas[i];
                label = new Label(nomorKolom[i], nomorBaris[i], teks, cellFormat);
                sheet.addCell(label);
            } else {
                label = new Label(nomorKolom[i], nomorBaris[i], dataRuas[i], cellFormat);
                sheet.addCell(label);
            }
        }

        setHeader(dataRuas[1], dataRuas[5]);
    }

    public void setHeader(String namaRuas, String nomorSegmen) throws WriteException {
        String header = "HASIL SURVEY DAN ANALISIS ULFJ RUAS " + namaRuas + " PADA SEGMEN " + nomorSegmen;
        int nomorKolom = 0;
        int[] nomorBaris = {0, 17, 57, 90, 116, 137, 180, 214, 235, 282, 320, 346, 369, 399, 414,
                452, 479, 501, 528, 558, 587, 620, 650, 679, 709, 749, 782, 824, 854,
                886, 914, 940, 971, 995};

        WritableCellFormat headerFormat = new WritableCellFormat();
        WritableFont font = new WritableFont(WritableFont.ARIAL, 14);
        font.setBoldStyle(WritableFont.BOLD);
        headerFormat.setFont(font);
        headerFormat.setAlignment(Alignment.RIGHT);
        headerFormat.setWrap(true);
        headerFormat.setBorder(Border.BOTTOM, BorderLineStyle.THICK);

        //tulis header pada cell
        for (int i = 0; i < nomorBaris.length; i++) {
            label = new Label(nomorKolom, nomorBaris[i], header, headerFormat);
            sheet.addCell(label);
        }
    }

    public void insertKlasifikasiJalan() throws WriteException {

        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                progress.setText("downloading klasifikasi jalan...");
            }
        });

        String[] data = new String[6];
        data[0] = sistemJaringan;
        data[1] = status;
        data[2] = fungsi;
        data[3] = kelasPrasarana;
        data[4] = kelasPenggunaan;
        data[5] = medan;

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        int[] nomorKolom = {0, 3, 7, 10, 14, 17};
        int nomorBaris;
        int[] jumOpsi = {2, 5, 4, 4, 4, 3};
        int jumlahData = nomorKolom.length;

        //input data
        for (int i = 0; i < jumlahData; i++) {
            //set nomorBaris selalu diawali 9 (mulai baris ke 9, indeks mulai dari 0)
            nomorBaris = 10;
            for (int j = 0; j < jumOpsi[i]; j++) {
                String teks = sheet.getCell(nomorKolom[i], nomorBaris).getContents();
                if (data[i].toUpperCase().equals(teks.toUpperCase().substring(5))) {
                    teks = teks.substring(0, 1) + "x" + teks.substring(3);
                } else {
                    teks = teks.substring(0, 1) + "  " + teks.substring(3);
                }

                teks = teks.toUpperCase();
                label = new Label(nomorKolom[i], nomorBaris, teks, cellFormat);
                sheet.addCell(label);
                nomorBaris++;
            }
        }

        //perbaiki garis pada cell D15 bagian atas (kolom 3, baris 14)
        WritableCellFormat cellFormat1 = new WritableCellFormat();
        cellFormat1.setBorder(Border.TOP, BorderLineStyle.THIN);
        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
        label = new Label(3, 14, "", cellFormat1);
        sheet.addCell(label);
    }

    public void insertSegmen(String idSegmen) throws WriteException, IOException {

        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                progress.setText("downloading segmen...");
            }
        });

        int panjangIdSegmen = idSegmen.length();
        String nomorSegmen = idSegmen.substring(panjangIdSegmen - 2, panjangIdSegmen);  //ambil 2 digit terakhir

        //inisialisasi nomor kolom dan nomor2 baris
        int nomorKolom = 15;    //kolom 'P' = huruf ke-16
        int[] nomorBaris = {19, 59, 92, 118, 139, 182, 216, 237, 284, 322, 348, 371, 401, 416, 454, 481, 503, 530,
                560, 589, 622, 652, 681, 711, 751, 784, 827, 857, 889, 917, 943, 974};

        //membuat objek untuk memformat cell dan font
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 14);
        cellFont.setBoldStyle(WritableFont.BOLD);

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setFont(cellFont);
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat.setAlignment(Alignment.CENTRE);
        cellFormat.setBackground(Colour.GREY_25_PERCENT);

        //tulis nomor segmen ke tiap halaman
        String teks = "SEGMEN: " + nomorSegmen;
        for (int i = 0; i < nomorBaris.length; i++) {
            label = new Label(nomorKolom, nomorBaris[i], teks, cellFormat);
            sheet.addCell(label);
        }
    }

    public void deleteTemporaryImages() throws IOException {
        for (int i = 1; i < idxGambar; i++) {
            File mediaStorageDir = new File(localPath + i + ".png");
            mediaStorageDir.delete();
        }
    }

    public void insertDataA111(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA111(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialiasasi variabel lokal
            int nomorBaris = 0;
            int nomorKolom = 0;     //untuk urusan BG color
            String A111_a = "";

            //memasukkan poin a (Fungsi Jalan)
            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                switch (fungsi.toUpperCase()) {
                    case "ARTERI":
                        //Arteri Primer
                        nomorBaris = 23;
                        A111_a = "Arteri Primer";
                        break;
                    case "KOLEKTOR":
                        //Kolektor Primer
                        nomorBaris = 24;
                        A111_a = "Kolektor Primer";
                        break;
                    case "LOKAL":
                        //Lokal Primer
                        nomorBaris = 25;
                        A111_a = "Lokal Primer";
                        break;
                    case "LINGKUNGAN":
                        //Lingkungan Primer
                        nomorBaris = 26;
                        A111_a = "Lingkungan Primer";
                        break;
                }
            } else {
                switch (fungsi.toUpperCase()) {
                    case "ARTERI":
                        //Arteri Sekunder
                        nomorBaris = 27;
                        A111_a = "Arteri Sekunder";
                        break;
                    case "KOLEKTOR":
                        //Kolektor Sekunder
                        nomorBaris = 28;
                        A111_a = "Kolektor Sekunder";
                        break;
                    case "LOKAL":
                        //Lokal Sekunder
                        nomorBaris = 29;
                        A111_a = "Lokal Sekunder";
                        break;
                    case "LINGKUNGAN":
                        //Lingkungan Sekunder
                        nomorBaris = 30;
                        A111_a = "Lingkungan Sekunder";
                        break;
                }
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, A111_a, StringCellFormat8pt);
                sheet.addCell(label);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(3, nomorBaris);
                cell.setCellFormat(bgColorLeft);
            }

            //memasukkan poin b (Arus lalu lintas yang dilayani)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 33;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 34;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 35;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 36;
                    break;
            }

            switch (medan.toUpperCase()) {
                case "DATAR (D)":
                    nomorKolom = 3;
                    break;
                case "BUKIT (B)":
                    nomorKolom = 5;
                    break;
                case "GUNUNG (G)":
                    nomorKolom = 7;
                    break;
            }

            if (!data[1].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Integer.parseInt(data[1].toString()), IntegerCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //memasukkan poin c (Jumlah lajur)
            /*if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 35;
            } else {
                nomorBaris = 36;
            }
            if (!data[3].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }*/
            //memasukkan poin c (Jumlah lajur) - versi 2 (Putra)
            if (!data[3].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 37, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 37, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan poin d (Lebar setiap lajur)
            int LHRT = Integer.parseInt(data[1].toString());     //ambil data LHRT (smp/hari) dari data ke-1 poin b
            if (LHRT > 17000) {
                nomorBaris = 42;
            } else if (LHRT >= 3000) {
                nomorBaris = 43;
            } else {
                nomorBaris = 44;
            }
            if (!data[5].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //memasukkan poin e (keseragaman lebar lajur)
            if (!data[7].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 45, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 45, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan poin f (kemiringan melintang)
            if (!data[9].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 46, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 46, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {24, 32, 41};
            int[] jumlahBaris = {6, 7, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[11].toString(), data[12].toString(), data[13].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a111 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image

                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 23, data[14].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {32, 37, 40, 45, 46};
            int idxData = 15;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A111
            label = new Label(14, 47, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A111
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    public void insertDataA112(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA112(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int nomorKolom = 0;     //untuk keperluan bg colour

            //masuukan data poin a (lebar bahu)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 50;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 51;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 52;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 53;
                    break;
            }

            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorKolom = 4;
                        break;
                    case "BUKIT (B)":
                        nomorKolom = 5;
                        break;
                    case "GUNUNG (G)":
                        nomorKolom = 6;
                        break;
                }
            } else {
                nomorKolom = 7;
            }

            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()), DoubleCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (keseragaman levar bahu)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 54, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 54, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (perkerasan bahu)
            if (!data[4].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 63, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 63, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d (posisi muka bahu thd muka jalan)
            if (!data[6].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 65, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 65, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin e (kemiringan melintang bahu)
            if (kelasPrasarana.toUpperCase().equals("JALAN BEBAS HAMBATAN (JBH)")) {
                if (!data[8].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, 66, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 66, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(7, 66);
                    cell.setCellFormat(bgColorCenter);
                }
            } else {
                if (!data[8].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, 67, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 67, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(7, 67);
                    cell.setCellFormat(bgColorCenter);
                }
            }

            //memasukkan gambar
            int[] barisGambar = {49, 64};
            int[] jumlahBaris = {6, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[10].toString(), data[11].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a112 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 48, data[12].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {48, 54, 63, 65, 66};
            int idxData = 13;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A112
            label = new Label(14, 68, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A112
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    public void insertDataA113(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA113(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;
            int nomorKolom;     //untuk urusan bg color

            //masukkan data poin a (lebar median)
            String median = data[0].toString();
            switch (median.toUpperCase()) {
                case "MEDIAN DATAR":
                    nomorBaris = 70;
                    break;
                case "MEDIAN DITINGGIKAN":
                    nomorBaris = 71;
                    break;
                default:
                    nomorBaris = 72;
                    break;
            }
            if (!data[1].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[1].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (tipe median)
            String tipeMedian = data[3].toString();
            switch (median.toUpperCase()) {
                case "MEDIAN DATAR":
                    nomorBaris = 73;
                    break;
                case "MEDIAN DITURUNKAN":
                    nomorBaris = 74;
                    break;
                default:
                    if (tipeMedian.toUpperCase().equals("KERB")) {
                        nomorBaris = 75;
                    } else {
                        nomorBaris = 77;
                    }
                    break;
            }
            if (!data[4].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (perkerasan median)
            String perkerasanMedian = data[6].toString();
            switch (median.toUpperCase()) {
                case "MEDIAN DATAR":
                    nomorBaris = 78;
                    break;
                case "MEDIAN DITURUNKAN":
                    nomorBaris = 79;
                    break;
                default:
                    if (perkerasanMedian.toUpperCase().equals("KERB")) {
                        nomorBaris = 80;
                    } else {
                        nomorBaris = 82;
                    }
                    break;
            }
            if (!data[7].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d (Bukaan pada median)
            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorKolom = 5;
            } else {
                nomorKolom = 7;
            }

            //Jarak antar bukaan
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 84;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 85;
            }

            if (!data[9].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[9].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //Lebar bukaan
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 86;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 87;
            }

            if (!data[11].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[11].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //memasukkan gambar
            int[] barisGambar = {70, 76, 81};
            int[] jumlahBaris = {5, 4, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[13].toString(), data[14].toString(), data[15].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a113 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 69, data[16].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {69, 73, 78, 83};
            int idxData = 17;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A113
            label = new Label(14, 88, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A113
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    public void insertDataA114(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA114(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a (lebar/ dimensi selokan samping)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 96, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 96, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (bentuk selokan samping)
            //b1
            if (!data[2].toString().substring(0, 1).equals("0")) {
                if (data[2].toString().equals("5")) {
                    label = new Label(9, 98, "Tidak ada", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                } else {
                    label = new Label(9, 98, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                }
            }

            //b2
            if (!data[3].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 99, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //hasil uji
                sheet.addCell(number);
                number = new Number(10, 99, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (fungsi mengalirkan air)
            if (!data[5].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 101, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //hasil uji
                sheet.addCell(number);
                number = new Number(10, 101, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {97, 100};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[7].toString(), data[8].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a114 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 96, data[9].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {96, 98, 101};
            int idxData = 10;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A114
            label = new Label(14, 103, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A114
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    public void insertDataA115(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA115(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a1 (lebar ambang pengaman)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 104, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 104, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a2 (lebar ambang pengaman)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 106, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 106, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (pengamanan konstruksi jalan)
            if (!data[4].toString().substring(0, 1).equals("0")) {
                if (data[4].toString().equals("6")) {
                    label = new Label(9, 107, "Tidak ada, tidak diperlukan", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[5].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, 107, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else if (data[4].toString().equals("7")) {
                    label = new Label(9, 107, "Tidak ada, tapi diperlukan", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[5].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, 107, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    label = new Label(9, 107, ("(" + data[4].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[5].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, 107, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                    //cek nomorBaris
                    int nomorBaris = 107;
                    if (data[5].equals("2")) {
                        nomorBaris = 108;
                    } else if (data[5].equals("3")) {
                        nomorBaris = 110;
                    } else if (data[5].equals("4")) {
                        nomorBaris = 111;
                    } else if (data[5].equals("5")) {
                        nomorBaris = 112;
                    }

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(2, nomorBaris);
                    cell.setCellFormat(bgColorLeft);
                }
            }

            //memasukkan gambar
            int[] barisGambar = {105, 109};
            int[] jumlahBaris = {3, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a115 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 104, data[8].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {104, 107};
            int idxData = 9;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A115
            label = new Label(14, 114, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A115
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    public void insertDataA116(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA116(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a1 (Rel pengaman)
            int jumlahPoin = 9;     //fix dari dokumen
            int nomorBaris = 122;   //start dari baris ke 122
            int idxData = 0;

            for (int i = 0; i < jumlahPoin; i++) {
                if (i == 4) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else if (i == 6) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString()), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0 || i == 3 || i == 6) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {123, 126, 130};
            int[] jumlahBaris = {2, 3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[18].toString(), data[19].toString(), data[20].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a116 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 122, data[21].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {122, 129};
            idxData = 22;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A116
            label = new Label(14, 134, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A116
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }

        //set font String 10pt
        WritableFont font10pt = new WritableFont(WritableFont.ARIAL, 10);
        font10pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat10pt = new WritableCellFormat();
        StringCellFormat10pt.setFont(font10pt);
        StringCellFormat10pt.setAlignment(Alignment.CENTRE);
        StringCellFormat10pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat10pt.setWrap(true);
        StringCellFormat10pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A11
        label = new Label(14, 135, kategoriA11, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A11
        if (kategoriA11.equals("LS")) {
            kategoriA1 = "LS";
        }
    }

    public void insertDataA121(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA121(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal;
            int nomorBaris1 = 0;
            int nomorBaris2 = 0;
            int nomorKolom = 0;

            //masukkan data poin a (panjang bagian jalan yang lurus)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorBaris1 = 143;
                        break;
                    case "BUKIT (B)":
                        nomorBaris1 = 144;
                        break;
                    case "GUNUNG (G)":
                        nomorBaris1 = 145;
                        break;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorBaris1 = 146;
                        break;
                    case "BUKIT (B)":
                        nomorBaris1 = 147;
                        break;
                    case "GUNUNG (G)":
                        nomorBaris1 = 148;
                        break;
                }
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris1, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(6, nomorBaris1);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b1 (jarak pandang)
            String jarakPandang = data[2].toString();
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris1 = 150;
                    nomorBaris2 = 151;
                } else {
                    nomorBaris1 = 153;
                    nomorBaris2 = 154;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris1 = 156;
                    nomorBaris2 = 157;
                } else {
                    nomorBaris1 = 158;
                    nomorBaris2 = 159;
                }
            }

            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorKolom = 5;
            } else {
                nomorKolom = 7;
            }

            if (!data[3].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris1, (data[3].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris1);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b2 (jarak pandang)
            if (!data[5].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris2, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris2);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin c1 (lingkungan jalan)
            if (!data[7].toString().substring(0, 1).equals("0")) {
                label = new Label(9, 160, ("(" + data[7].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            //masukkan data poin c2 (lingkungan jalan)
            if (!data[8].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 161, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 161, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {144, 153};
            int[] jumlahBaris = {7, 7};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[10].toString(), data[11].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a121 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 143, data[12].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {143, 149, 160};
            int idxData = 13;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A121
            label = new Label(14, 162, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A12 berdasarkan nilai A121
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    public void insertDataA122(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA122(idSegmen);
        double kecepatan = koneksi.getKecepatanOperasional(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int nomorKolom = 0;

            //masukkan data poin a (radius tikungan)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 165;
                } else {
                    nomorBaris = 166;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 168;
                } else {
                    nomorBaris = 169;
                }
            }

            if (kecepatan > 60.0 || kecepatan == 40.0) {
                nomorKolom = 5;
            } else {
                nomorKolom = 7;
            }

            if (!data[0].toString().substring(0, 2).equals("-1")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (superelevansi)
            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorBaris = 170;
            } else {
                nomorBaris = 172;
            }

            if (!data[2].toString().substring(0, 2).equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin c (jarak pandang)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 174;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 175;
            }

            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorKolom = 3;
            } else {
                nomorKolom = 6;
            }

            if (!data[4].toString().substring(0, 2).equals("-1")) {
                label = new Label(9, nomorBaris, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //memasukkan gambar
            int[] barisGambar = {164, 171};
            int[] jumlahBaris = {6, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a122 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 163, data[8].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {163, 170, 173};
            int idxData = 9;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A122
            label = new Label(14, 178, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A12 berdasarkan nilai A122
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    public void insertDataA123(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA123(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            int nomorBaris = 0;

            //masukkan data poin a (jumlah persimpangan per KM)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    switch (kelasPrasarana.toUpperCase()) {
                        case "JALAN BEBAS HAMBATAN (JBH)":
                            nomorBaris = 186;
                            break;
                        case "JALAN RAYA (JR)":
                            nomorBaris = 188;
                            break;
                        case "JALAN SEDANG (JS)":
                            nomorBaris = 189;
                            break;
                        case "JALAN KECIL (JK)":
                            nomorBaris = 190;
                            break;
                    }
                } else {
                    switch (kelasPrasarana.toUpperCase()) {
                        case "JALAN BEBAS HAMBATAN (JBH)":
                            nomorBaris = 191;
                            break;
                        case "JALAN RAYA (JR)":
                            nomorBaris = 192;
                            break;
                        case "JALAN SEDANG (JS)":
                            nomorBaris = 194;
                            break;
                        case "JALAN KECIL (JK)":
                            nomorBaris = 195;
                            break;
                    }
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                switch (kelasPrasarana.toUpperCase()) {
                    case "JALAN BEBAS HAMBATAN (JBH)":
                        nomorBaris = 196;
                        break;
                    case "JALAN RAYA (JR)":
                        nomorBaris = 197;
                        break;
                    case "JALAN SEDANG (JS)":
                        nomorBaris = 198;
                        break;
                    case "JALAN KECIL (JK)":
                        nomorBaris = 199;
                        break;
                }
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(4, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (cara akses ke jalan utama)
            if (!data[2].toString().substring(0, 1).equals("0")) {
                if (data[2].toString().equals("5")) {
                    label = new Label(9, 200, "Tidak ada", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[3].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, 200, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    label = new Label(9, 200, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[3].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, 200, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);

                        //fill color pada cell
                        switch (data[2].toString()) {
                            case "1":
                                nomorBaris = 200;
                                break;
                            case "2":
                                nomorBaris = 201;
                                break;
                            case "3":
                                nomorBaris = 202;
                                break;
                            case "4":
                                nomorBaris = 203;
                                break;
                            default:
                                break;
                        }

                        WritableCell cell = sheet.getWritableCell(2, nomorBaris);
                        cell.setCellFormat(bgColorLeft);
                    }
                }
            }

            //memasukkan gambar
            int[] barisGambar = {187, 193, 200};
            int[] jumlahBaris = {5, 6, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[4].toString(), data[5].toString(), data[6].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a123 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 186, data[7].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {186, 200};
            int idxData = 8;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A123
            label = new Label(14, 204, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A12 berdasarkan nilai A123
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    public void insertDataA124(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA124(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);
            bgColorCenter.setWrap(true);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
            bgColorLeft.setWrap(true);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a(banyaknya akses persil)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                switch (kelasPrasarana.toUpperCase()) {
                    case "JALAN BEBAS HAMBATAN (JBH)":
                        nomorBaris = 205;
                        break;
                    case "JALAN RAYA (JR)":
                        nomorBaris = 206;
                        break;
                    case "JALAN SEDANG (JS)":
                        nomorBaris = 207;
                        break;
                    case "JALAN KECIL (JK)":
                        nomorBaris = 208;
                        break;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                switch (kelasPrasarana.toUpperCase()) {
                    case "JALAN BEBAS HAMBATAN (JBH)":
                        nomorBaris = 209;
                        break;
                    case "JALAN RAYA (JR)":
                        nomorBaris = 210;
                        break;
                    case "JALAN SEDANG (JS)":
                        nomorBaris = 211;
                        break;
                    case "JALAN KECIL (JK)":
                        nomorBaris = 212;
                        break;
                }
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(4, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (akses ke jalan utama)
            if (!data[2].toString().substring(0, 1).equals("0")) {
                if (data[2].toString().equals("4")) {
                    label = new Label(9, 220, "Tidak ada", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[3].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, 220, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }
                label = new Label(9, 220, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);

                if (!data[3].toString().substring(0, 1).equals("-")) {
                    number = new Number(10, 220, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    switch (data[2].toString()) {
                        case "1":
                            nomorBaris = 220;
                            break;
                        case "2":
                            nomorBaris = 221;
                            break;
                        case "3":
                            nomorBaris = 223;
                            break;
                    }

                    WritableCell cell = sheet.getWritableCell(2, nomorBaris);
                    cell.setCellFormat(bgColorLeft);
                }
            }

            //masukkan data poin c (bentuk akses)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 224;
                } else {
                    nomorBaris = 226;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 228;
                } else {
                    nomorBaris = 230;
                }
            }
            if (!data[4].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(5, nomorBaris);
                cell.setCellFormat(bgColorLeft);
            }

            //memasukkan gambar
            int[] barisGambar = {206, 221, 224, 228};
            int[] jumlahBaris = {5, 2, 3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString(), data[8].toString(), data[9].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a124 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 205, data[10].toString(), StringCellFormat8pt);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {205, 220, 224};
            int idxData = 11;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A124
            label = new Label(14, 232, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A12 berdasarkan nilai A124
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }

        //set font String 10pt
        WritableFont font10pt = new WritableFont(WritableFont.ARIAL, 10);
        font10pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat10pt = new WritableCellFormat();
        StringCellFormat10pt.setFont(font10pt);
        StringCellFormat10pt.setAlignment(Alignment.CENTRE);
        StringCellFormat10pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat10pt.setWrap(true);
        StringCellFormat10pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A12
        label = new Label(14, 233, kategoriA12, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A12
        if (kategoriA12.equals("LS")) {
            kategoriA1 = "LS";
        }
    }

    public void insertDataA131(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA131(idSegmen);
        double kecepatan = koneksi.getKecepatanOperasional(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int nomorKolom = 0;     //untuk urusan bg color
            int mulaiBaris;

            //masukkan data poin a1 (kelandaian memanjang)
            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorBaris = 242;
                        break;
                    case "BUKIT (B)":
                        nomorBaris = 243;
                        break;
                    case "GUNUNG (G)":
                        nomorBaris = 244;
                        break;
                }
            } else {
                nomorBaris = 245;
            }

            if (kelasPrasarana.toUpperCase().equals("JALAN BEBAS HAMBATAN (JBH)")) {
                nomorKolom = 5;
            } else if (kelasPrasarana.toUpperCase().equals("JALAN RAYA (JR)")) {
                nomorKolom = 6;
            } else if (kelasPrasarana.toUpperCase().equals("JALAN SEDANG (JS)")) {
                nomorKolom = 7;
            } else if (kelasPrasarana.toUpperCase().equals("JALAN KECIL (JK)")) {
                nomorKolom = 8;
            }

            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 242, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin a2 (kelandaian memanjang)
            int tempData = (int) Double.parseDouble(data[0].toString());

            if (fungsi.toUpperCase().equals("SEKUNDER (DALAM KOTA)")) {
                mulaiBaris = 248;
                for (int i = 4; i <= 10; i++) {
                    if (i == tempData) {
                        nomorBaris = mulaiBaris + (tempData - 4);
                        break;
                    }
                }
            } else {
                mulaiBaris = 257;
                for (int i = 4; i <= 10; i++) {
                    if (i == tempData) {
                        nomorBaris = mulaiBaris + (tempData - 4);
                        break;
                    }
                }
            }

            if (kecepatan == 40.0) {
                nomorKolom = 4;
            } else if (kecepatan == 50.0) {
                nomorKolom = 5;
            } else if (kecepatan == 60.0) {
                nomorKolom = 6;
            } else if (kecepatan == 80.0) {
                nomorKolom = 7;
            } else if (kecepatan == 100.0) {
                nomorKolom = 8;
            }

            if (!data[2].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, mulaiBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (jarak pandang)
            int nomorBaris1 = 0;
            int nomorBaris2 = 0;
            String jarakPandang = data[4].toString();
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 265;
                    nomorBaris1 = 266;
                    nomorBaris2 = 267;
                } else {
                    nomorBaris = 268;
                    nomorBaris1 = 269;
                    nomorBaris2 = 270;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 272;
                    nomorBaris1 = 273;
                    nomorBaris2 = 274;
                } else {
                    nomorBaris = 275;
                    nomorBaris1 = 276;
                    nomorBaris2 = 277;
                }
            }

            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorKolom = 5;
            } else {
                nomorKolom = 7;
            }

            if (!data[5].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[7].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris1, (data[7].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris1);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[9].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris2, (data[9].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris2);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin c (lingkungan jalan)
            if (!data[11].toString().substring(0, 1).equals("0")) {
                label = new Label(9, 278, ("(" + data[11].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            if (!data[12].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 279, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 279, Double.parseDouble(data[13].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {242, 251, 260, 269};
            int[] jumlahBaris = {8, 8, 8, 8};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[14].toString(), data[15].toString(), data[16].toString(), data[17].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a131 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 241, data[18].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {241, 264, 278};
            int idxData = 19;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A131
            label = new Label(14, 280, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A13 berdasarkan nilai A131
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    public void insertDataA132(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA132(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a (keperluan keberadaannya)
            if (!data[0].toString().substring(0, 1).equals("0")) {
                label = new Label(9, 288, ("(" + data[0].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);

                if (!data[1].toString().substring(0, 1).equals("-")) {
                    number = new Number(10, 288, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }
            }

            //masukkan data poin b1 (Lebar dan panjang lajur)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HABATAN (JBH)":
                    nomorBaris = 290;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 291;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 292;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 293;
                    break;
            }

            if (!data[2].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b2 (Lebar dan panjang lajur)
            int jumData = 5;
            int idxData = 4;
            nomorBaris = 294;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                    cell.setCellFormat(bgColorCenter);
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //masukkan data poin c (Taper masuk dan keluar lajur)
            if (!data[14].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 300, (data[14].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 300, Double.parseDouble(data[15].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {289, 295};
            int[] jumlahBaris = {5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[16].toString(), data[17].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a132 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 288, data[18].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {288, 290, 300};
            idxData = 19;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A132
            label = new Label(14, 301, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A13 berdasarkan nilai A132
            if (data[idxData].toString().equals("LS")) {
                kategoriA13 = "LS";
            }
        }
    }

    public void insertDataA133(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA133(idSegmen);
        double kecepatan = koneksi.getKecepatanOperasional(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int nomorBaris1 = 0;
            int nomorBaris2 = 0;
            int nomorKolom = 0;

            //masukkan data poin a1 (Ketajaman lengkungan)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 302, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 302, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a2,a3,a4 (ketajaman lengkungan)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 304;
                nomorBaris1 = 305;
                nomorBaris2 = 306;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 308;
                nomorBaris1 = 310;
                nomorBaris2 = 311;
            }

            if (kecepatan == 60.0 || kecepatan == 40) {
                nomorKolom = 3;
            } else if (kecepatan == 30.0 || kecepatan == 20.0) {
                nomorKolom = 6;
            }

            if (!data[2].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[4].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris1, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris1);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[6].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris2, (data[6].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris2);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b1, b2, b3 (jarak pandang)
            String jarakPandang = data[8].toString();
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 313;
                    nomorBaris1 = 314;
                    nomorBaris2 = 315;
                } else {
                    nomorBaris = 316;
                    nomorBaris1 = 317;
                    nomorBaris2 = 318;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 327;
                    nomorBaris1 = 328;
                    nomorBaris2 = 329;
                } else {
                    nomorBaris = 330;
                    nomorBaris1 = 331;
                    nomorBaris2 = 332;
                }
            }

            if (kecepatan == 60.0 || kecepatan == 40.0) {
                nomorKolom = 5;
            } else if (kecepatan == 30.0 || kecepatan == 20.0) {
                nomorKolom = 7;
            }

            if (!data[9].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[11].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris1, (data[11].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris1);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[13].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris2, (data[13].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[14].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris2);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin c dan d (arah jalan di balik lengkungan) dan (kombinasi lengkung vertikal dan tikungan horizontal)
            int jumData = 7;
            int idxData = 15;
            nomorBaris = 334;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 0 || i == 3) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {303, 311, 327, 335, 338};
            int[] jumlahBaris = {7, 6, 6, 2, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[29].toString(), data[30].toString(), data[31].toString(), data[32].toString(), data[33].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a133 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 302, data[34].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {302, 312, 334, 337};
            idxData = 35;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A133
            label = new Label(14, 343, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A13 berdasarkan nilai A133
            if (data[idxData].toString().equals("LS")) {
                kategoriA13 = "LS";
            }
        }

        //set font String 10pt
        WritableFont font10pt = new WritableFont(WritableFont.ARIAL, 10);
        font10pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat10pt = new WritableCellFormat();
        StringCellFormat10pt.setFont(font10pt);
        StringCellFormat10pt.setAlignment(Alignment.CENTRE);
        StringCellFormat10pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat10pt.setWrap(true);
        StringCellFormat10pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A13
        label = new Label(14, 344, kategoriA13, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A13
        if (kategoriA13.equals("LS")) {
            kategoriA1 = "LS";
        }
    }

    public void insertDataA141(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA141(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a (overlaping kurva vertikal pada jalan yang lurus ...)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 352, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 352, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (overlaping kurva vertikal pada begian menikung ...)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 358, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 358, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {353, 355, 361};
            int[] jumlahBaris = {1, 5, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[4].toString(), data[5].toString(), data[6].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a141 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 352, data[7].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {352, 358};
            int idxData = 8;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A141
            label = new Label(14, 365, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A14 berdasarkan nilai A141
            if (data[idxData].toString().equals("LS")) {
                kategoriA14 = "LS";
            }
        }

        //set font String 10pt
        WritableFont font10pt = new WritableFont(WritableFont.ARIAL, 10);
        font10pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat10pt = new WritableCellFormat();
        StringCellFormat10pt.setFont(font10pt);
        StringCellFormat10pt.setAlignment(Alignment.CENTRE);
        StringCellFormat10pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat10pt.setWrap(true);
        StringCellFormat10pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //set font String 11pt
        WritableFont font11pt = new WritableFont(WritableFont.ARIAL, 11);
        font11pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
        StringCellFormat11pt.setFont(font11pt);
        StringCellFormat11pt.setAlignment(Alignment.CENTRE);
        StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat11pt.setWrap(true);
        StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A14
        label = new Label(14, 366, kategoriA14, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A14
        if (kategoriA14.equals("LS")) {
            kategoriA1 = "LS";
        }

        //memasukkan kategori A1
        label = new Label(14, 367, kategoriA1, StringCellFormat11pt);
        sheet.addCell(label);
    }

    public void insertDataA21(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA21(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a ()
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 374;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 376;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 378;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 379;
                    break;
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(3, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //memasukkan gambar
            int[] barisGambar = {375, 378};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[2].toString(), data[3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a21 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 374, data[4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {374};
            int idxData = 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A21
            label = new Label(14, 381, data[5].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A2 berdasarkan nilai A21
            if (data[5].toString().equals("LS")) {
                kategoriA2 = "LS";
            }
        }
    }

    public void insertDataA22(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA22(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a (kerataan jalan, IRI)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 382;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 383;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 384;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 385;
                    break;
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(5, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b s.d. g --> (kedalaman lubang) s.d. (intensitas alur)
            int jumData = 8;
            int idxData = 2;
            nomorBaris = 386;

            for (int i = 0; i < jumData; i++) {
                if (i == 2 || i == 4 || i == 7) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m2/km"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " mm"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 4) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //masukkan data poin h dan i --> (tekstur perkerasan) dan (aspal yang meleleh)
            jumData = 2;
            idxData = 18;
            nomorBaris = 395;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {383, 390};
            int[] jumlahBaris = {6, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[22].toString(), data[23].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a22 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 382, data[24].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {382, 386, 388, 389, 390, 392, 394, 395, 396};
            idxData = 25;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A22
            label = new Label(14, 397, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A2 berdasarkan nilai A22
            if (data[idxData].toString().equals("LS")) {
                kategoriA2 = "LS";
            }
        }
    }

    public void insertDataA23(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA23(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a (perlu/ tidak pemeriksaan lebih lanjut)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 404, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 404, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (kekuatan konstruksi)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 406, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 406, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (drainase permukaan perkerasan jalan)
            if (!data[4].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 408, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 408, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d1 (bahan perkerasan)
            if (!data[6].toString().substring(0, 1).equals("0")) {
                label = new Label(9, 409, ("(" + data[6].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            //masukkan data poin d2 (bahan perkerasan)
            if (!data[7].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 410, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 410, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {405, 407};
            int[] jumlahBaris = {1, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[9].toString(), data[10].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a23 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 404, data[11].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {404, 406, 408, 409};
            int idxData = 12;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A23
            label = new Label(14, 411, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A2 berdasarkan nilai A23
            if (data[idxData].toString().equals("LS")) {
                kategoriA2 = "LS";
            }
        }

        //set font String 10pt
        WritableFont font10pt = new WritableFont(WritableFont.ARIAL, 10);
        font10pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat10pt = new WritableCellFormat();
        StringCellFormat10pt.setFont(font10pt);
        StringCellFormat10pt.setAlignment(Alignment.CENTRE);
        StringCellFormat10pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat10pt.setWrap(true);
        StringCellFormat10pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A2
        label = new Label(14, 412, kategoriA2, StringCellFormat10pt);
        sheet.addCell(label);
    }

    public void insertDataA31(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA31(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal;
            int nomorBaris = 0;

            //masukkan data poin a (jalur lalu lintas)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 420;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 421;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 422;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 423;
                    break;
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (jalur pejalan kaki)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 424, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 424, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c1 s.d. c6 (konstruksi jembatan)
            int jumData = 6;
            int idxData = 4;
            nomorBaris = 425;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //masukkan data poin d (kerusakan jembatan)
            String kerusakanJembatan = data[63].toString();
            idxData = 16;
            switch (kerusakanJembatan.toUpperCase()) {
                case "BATUBATA":
                    jumData = 3;
                    nomorBaris = 432;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().substring(0, 1).equals("-")) {
                            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                            sheet.addCell(number);
                            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                            sheet.addCell(number);
                        }

                        if (i == 1) {
                            nomorBaris += 2;
                        } else {
                            nomorBaris++;
                        }

                        idxData += 2;
                    }

                    //ubah nilai idxData untuk lompat ke idxData untuk data gambar
                    idxData = idxData + (5 * 2);    //lompat 5 data dikali 2 (2 --> karena ada uji lapangan dan deviasi)
                    break;
                case "BETON":
                    jumData = 6;
                    nomorBaris = 436;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().substring(0, 1).equals("-")) {
                            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                            sheet.addCell(number);
                            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                            sheet.addCell(number);
                        }

                        nomorBaris++;
                        idxData += 2;
                    }

                    //ubah nilai idxData untuk lompat ke idxData untuk data gambar
                    idxData = idxData + (2 * 2);    //lompat 2 data dikali 2 (2 --> karena ada uji lapangan dan deviasi)
                    break;
                case "BAJA":
                    jumData = 8;
                    nomorBaris = 443;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().substring(0, 1).equals("-")) {
                            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                            sheet.addCell(number);
                            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                            sheet.addCell(number);
                        }

                        nomorBaris++;
                        idxData += 2;
                    }
                    break;
                case "KAYU":
                    jumData = 5;
                    nomorBaris = 457;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().substring(0, 1).equals("-")) {
                            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                            sheet.addCell(number);
                            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                            sheet.addCell(number);
                        }

                        nomorBaris++;
                        idxData += 2;
                    }

                    //ubah nilai idxData untuk lompat ke idxData untuk data gambar
                    idxData = idxData + (3 * 2);    //lompat 3 data dikali 2 (2 --> karena ada uji lapangan dan deviasi)
                    break;
            }

            //masukkan data poin e (fasilitas untuk pemeliharaan)
            jumData = 9;
            nomorBaris = 462;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {420, 428, 435, 441, 458, 463};
            int[] jumlahBaris = {6, 5, 5, 6, 4, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(),
                    data[idxData + 3].toString(), data[idxData + 4].toString(), data[idxData + 5].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a31 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            idxData = idxData + 6;      //idxData = 56
            label = new Label(15, 419, data[idxData].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {419, 424, 425, 432, 461};
            idxData = idxData + 1;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A31
            label = new Label(14, 471, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A31
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    public void insertDataA32(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA32(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;
            int jumData;
            int idxData;

            //masukkan data poin a s.d. c
            jumData = 3;
            idxData = 0;
            nomorBaris = 472;
            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {473};
            int[] jumlahBaris = {3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a32 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            idxData++;
            label = new Label(15, 472, data[idxData].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {472, 474, 475};
            idxData++;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A32
            label = new Label(14, 477, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A32
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    public void insertDataA33(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA33(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a (jumlah per kilometer)
            if (medan.toUpperCase().equals("DATAR (D)")) {
                if (!data[0].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, 484, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 484, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(5, 484);
                    cell.setCellFormat(bgColorCenter);
                }

            } else {
                if (!data[2].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, 486, Integer.parseInt(data[2].toString()), IntegerCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 486, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(5, 486);
                    cell.setCellFormat(bgColorCenter);
                }

            }

            //masukkan data poin b (fungsi menyalurkan air)
            if (!data[4].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 487, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 487, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (kerusakan)
            if (!data[6].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 489, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 489, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {485, 488};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[8].toString(), data[9].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a33 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 484, data[10].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {484, 487, 489};
            int idxData = 11;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A33
            label = new Label(14, 491, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A33
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    public void insertDataA34(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA34(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a (posisinya terhadap jalur lalu lintas)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 492, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 492, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (ketergangguan arus lalu lintas akibat aktivitas parkir)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 494, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 494, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (lebar lajur lalu lintas  efektif)
            if (!data[4].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 497, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 497, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {493, 496};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a34 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 492, data[8].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {492, 494, 497};
            int idxData = 9;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A34
            label = new Label(14, 499, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A34
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    public void insertDataA35(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA35(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;
            int jumData;
            int idxData;

            //masukkan data poin a s.d. c
            jumData = 3;
            idxData = 0;
            nomorBaris = 506;
            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris += 3;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {507, 510};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a35 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 506, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {506, 508, 511};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A35
            label = new Label(14, 513, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A35
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    public void insertDataA36(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA36(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a (dimensi dan bentuk saluran)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 514, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 514, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[2].toString().substring(0, 1).equals("0")) {
                if (data[2].toString().equals("5")) {
                    label = new Label(9, 516, "Tidak ada", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                } else {
                    label = new Label(9, 516, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                }
            }

            //masukkan data poin b1, b2, b3 (kemiringan ke arah aliran)
            int jumData = 3;
            int idxData = 3;
            int nomorBaris = 517;
            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin c (bahan dinding saluran)
            if (!data[9].toString().substring(0, 1).equals("0")) {
                if (data[9].toString().equals("12")) {
                    label = new Label(9, 520, "Tidak ada", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                } else {
                    label = new Label(9, 520, ("(" + data[9].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                }
            }

            if (!data[10].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 522, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 522, Double.parseDouble(data[11].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d (tertutup/ terbuka sesuai lingkungan)
            if (!data[12].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 523, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 523, Double.parseDouble(data[13].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {515, 521};
            int[] jumlahBaris = {5, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[14].toString(), data[15].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a36 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 514, data[16].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {514, 517, 520, 523};
            idxData = 17;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A36
            label = new Label(14, 525, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A36
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }

        //set font String 11pt
        WritableFont font11pt = new WritableFont(WritableFont.ARIAL, 11);
        font11pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
        StringCellFormat11pt.setFont(font11pt);
        StringCellFormat11pt.setAlignment(Alignment.CENTRE);
        StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat11pt.setWrap(true);
        StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A3
        label = new Label(14, 526, kategoriA3, StringCellFormat11pt);
        sheet.addCell(label);
    }

    public void insertDataA41(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA41(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a1 (lebar dan tinggi)
            String lebarJalur = data[0].toString();
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    switch (lebarJalur.toUpperCase()) {
                        case "2 X 14 M":
                            nomorBaris = 533;
                            break;
                        case "2 X 11 M":
                            nomorBaris = 534;
                            break;
                        case "2 X 7 M":
                            nomorBaris = 535;
                            break;
                        default:
                            break;
                    }
                    break;
                case "JALAN RAYA (JR)":
                    switch (lebarJalur.toUpperCase()) {
                        case "2 X 14 M":
                            nomorBaris = 536;
                            break;
                        case "2 X 11 M":
                            nomorBaris = 537;
                            break;
                        case "2 X 7 M":
                            nomorBaris = 539;
                            break;
                        default:
                            break;
                    }
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 540;
                    break;
                case "JALAN KECIL (JK)":
                    switch (lebarJalur.toUpperCase()) {
                        case "5,5 M":
                            nomorBaris = 541;
                            break;
                        case "2,5 M":
                            nomorBaris = 542;
                            break;
                    }
                    break;
            }
            if (!data[1].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[1].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(3, nomorBaris);
                cell.setCellFormat(bgColorCenter);
                cell = sheet.getWritableCell(5, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin a2 (lebar dan tinggi)
            if (!data[3].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 543, (data[3].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 543, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a3 (lebar dan tinggi)
            if (!data[5].toString().substring(0, 1).equals("-")) {
                label = new Label(9, 544, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 544, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (pemanfaatan rumaja)
            if (!data[7].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 546, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 546, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (keselamatan lalu lintas)
            if (!data[9].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 547, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 547, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {534, 540, 545};
            int[] jumlahBaris = {5, 4, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[11].toString(), data[12].toString(), data[13].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a41 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 533, data[14].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {533, 546, 547};
            int idxData = 15;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A41
            label = new Label(14, 549, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A4 berdasarkan nilai A41
            if (data[idxData].toString().equals("LS")) {
                kategoriA4 = "LS";
            }
        }
    }

    public void insertDataA42(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA42(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a (lebar rumija)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 550;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 552;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 553;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 554;
                    break;
            }
            if (!data[0].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (pemanfaata rumija)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 555, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 555, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (keberadaan dan tempat utilitas)
            String keberadaan = data[4].toString();
            if (keberadaan.toUpperCase().equals("ANTAR KOTA")) {
                if (!data[5].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, 563, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 563, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (!data[7].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, 565, (data[7].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 565, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (!data[9].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, 566, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 566, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }
            } else {
                if (!data[5].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, 567, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 567, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (!data[7].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, 568, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 568, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }
            }

            //memasukkan gambar
            int[] barisGambar = {551, 564};
            int[] jumlahBaris = {5, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[11].toString(), data[12].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a42 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 550, data[13].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {550, 555, 563};
            int idxData = 14;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A42
            label = new Label(14, 569, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A4 berdasarkan nilai A42
            if (data[idxData].toString().equals("LS")) {
                kategoriA4 = "LS";
            }
        }
    }

    public void insertDataA43(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA43(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int nomorKolom = 0;     //untuk keperluan bg color

            //masukkan data poin a (lebar ruwasja)
            switch (fungsi.toUpperCase()) {
                case "ARTERI":
                    nomorBaris = 571;
                    break;
                case "KOLEKTOR":
                    nomorBaris = 572;
                    break;
                case "LOKAL":
                    nomorBaris = 573;
                    break;
                case "LINGKUNGAN":
                    nomorBaris = 574;
                    break;
                case "JEMBATAN":
                    nomorBaris = 575;
                    break;
            }

            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorKolom = 3;
            } else {
                nomorKolom = 6;
            }

            if (!data[0].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b1, b2, b3 (pemanfaatan ruwasja)
            if (!data[2].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 576, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 576, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[4].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 578, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 578, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[6].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 579, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 579, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (penghalang pandangan pengemudi)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (!data[8].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, 581, (data[8].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 581, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(nomorKolom, 581);
                    cell.setCellFormat(bgColorCenter);
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (!data[8].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, 582, (data[8].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 582, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);

                    //fill color pada cell
                    WritableCell cell = sheet.getWritableCell(nomorKolom, 582);
                    cell.setCellFormat(bgColorCenter);
                }

            }

            //memasukkan gambar
            int[] barisGambar = {571, 577};
            int[] jumlahBaris = {5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[10].toString(), data[11].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a43 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 570, data[12].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {570, 576, 580};
            int idxData = 13;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A42
            label = new Label(14, 584, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A4 berdasarkan nilai A43
            if (data[idxData].toString().equals("LS")) {
                kategoriA4 = "LS";
            }
        }

        //set font String 11pt
        WritableFont font11pt = new WritableFont(WritableFont.ARIAL, 11);
        font11pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
        StringCellFormat11pt.setFont(font11pt);
        StringCellFormat11pt.setAlignment(Alignment.CENTRE);
        StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat11pt.setWrap(true);
        StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A4
        label = new Label(14, 585, kategoriA4, StringCellFormat11pt);
        sheet.addCell(label);
    }

    public void insertDataA51(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA51(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int idxData = 0;
            int jumlahData = 0;

            //masukkan data poin a (marka di bagian lurus)
            jumlahData = 4;
            nomorBaris = 594;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("0")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (marka di bagian tikungan)
            jumlahData = 5;
            nomorBaris = 600;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("0")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin c (marka persimpangan)
            jumlahData = 7;
            nomorBaris = 607;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("0")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 5) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //masukkan data poin d (zebra cross)
            jumlahData = 3;
            nomorBaris = 615;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {593, 599, 605, 611};
            int[] jumlahBaris = {5, 5, 5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a51 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 592, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {592, 598, 605, 615};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A51
            label = new Label(14, 618, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A51
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    public void insertDataA52(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA52(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int idxData = 0;
            int jumlahData = 0;

            //masukkan data poin a (kebutuhan manajemen lalu lintas)
            jumlahData = 6;
            nomorBaris = 626;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("0")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (ketepatan jenis rambu dan penempatannya)
            nomorBaris = 632;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {626, 632};
            int[] jumlahBaris = {5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a52 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 625, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {625, 632};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A52
            label = new Label(14, 638, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A52
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    public void insertDataA53(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA53(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a1, a2, a3 (kebutuhan manajemen lalu lintas)
            int nomorBaris = 641;
            int idxData = 0;
            int jumlahData = 3;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("0")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 1) {
                    nomorBaris += 3;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //masukkan data poin b (bukaan pada separator)
            if (!data[idxData].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 646, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);

                if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                    number = new Number(10, 646, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }
            }

            //memasukkan gambar
            int[] barisGambar = {640, 644};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[8].toString(), data[9].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a53 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 639, data[10].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {639, 646};
            idxData = 11;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A53
            label = new Label(14, 648, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A53
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    public void insertDataA54(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA54(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;
            int jumlahData;
            int idxData = 0;

            //masukkan data poin a (kebutuhan manajemen lalu lintas)
            nomorBaris = 656;
            jumlahData = 3;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("0")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (bentuk pulau jalan)
            if (!data[idxData].toString().substring(0, 1).equals("0")) {
                if (data[idxData].toString().equals("3")) {
                    label = new Label(9, 659, "Tidak ada", StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                } else {
                    label = new Label(9, 659, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                }
            }

            //masukkan data poin c (marka), d (warna kerb), dan e (rambu pengarah)
            nomorBaris = 660;
            jumlahData = 6;
            idxData++;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {656, 660};
            int[] jumlahBaris = {3, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a54 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 655, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {655, 660, 665, 666};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A54
            label = new Label(14, 667, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A54
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    public void insertDataA55(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA55(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;
            int jumlahData;
            int idxData;

            //masukkan data poin a (kebutuhan manajemen lalu lintas)
            if (!data[0].toString().substring(0, 1).equals("0")) {
                label = new Label(9, 670, ("(" + data[0].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);

                if (!data[1].toString().substring(0, 1).equals("-")) {
                    number = new Number(10, 670, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }
            }

            //masukkan data poin b (perkerasan dan kondisi trotoar), c (pemanfaatan oleh selain pejalan kaki), dan d (utilitas pada trotoar)
            nomorBaris = 671;
            jumlahData = 4;
            idxData = 2;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {669, 673};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a55 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 668, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {668, 671, 674, 675};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A55
            label = new Label(14, 677, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A55
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    public void insertDataA56(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA56(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 684;
            int jumlahData = 7;
            int idxData = 0;

            //masukkan data semua poin
            for (int i = 0; i < jumlahData; i++) {
                if (i == 4) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                    }

                } else {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);

                        if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                            sheet.addCell(number);
                        }
                    }
                }

                if (i == 0 || i == 3) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                if (i == 4) {
                    idxData++;
                } else {
                    idxData += 2;
                }
            }

            //memasukkan gambar
            int[] barisGambar = {685, 689};
            int[] jumlahBaris = {3, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[13].toString(), data[14].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a56 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 684, data[15].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {684, 688, 691, 692};
            idxData = 16;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A56
            label = new Label(14, 694, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A56
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    public void insertDataA57(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA57(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;
            int jumlahData;
            int idxData = 0;

            //masukkan data poin a (kebutuhan manajemen lalu lintas)
            nomorBaris = 696;
            jumlahData = 4;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (rambu dan marka), c (APILL), dan d (perlindungan bagi pejalan kaki)
            nomorBaris = 700;
            jumlahData = 5;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);

                    if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {696, 701};
            int[] jumlahBaris = {4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a57 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 695, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {695, 700, 704, 705};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A57
            label = new Label(14, 706, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A57
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }

        //set font String 11pt
        WritableFont font11pt = new WritableFont(WritableFont.ARIAL, 11);
        font11pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
        StringCellFormat11pt.setFont(font11pt);
        StringCellFormat11pt.setAlignment(Alignment.CENTRE);
        StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat11pt.setWrap(true);
        StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A5
        label = new Label(14, 707, kategoriA5, StringCellFormat11pt);
        sheet.addCell(label);
    }

    public void insertDataA6a1(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6a1(idSegmen);
        double kecepatan = koneksi.getKecepatanOperasional(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.TOP, BorderLineStyle.THIN);
            bgColorLeft.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font RIGHT
            WritableCellFormat bgColorRight = new WritableCellFormat();
            bgColorRight.setFont(fontBGcolor);
            bgColorRight.setBackground(Colour.YELLOW);
            bgColorRight.setAlignment(Alignment.RIGHT);
            bgColorRight.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorRight.setBorder(Border.TOP, BorderLineStyle.THIN);
            bgColorRight.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

            //inisialisasi variabel lokal;
            int nomorBaris;
            int jumlahData;
            int idxData = 0;

            //masukkan data poin a1 (garis sumbu) s.d. a10 (Yellow Box)
            jumlahData = 25;
            if (kecepatan <= 60) {
                nomorBaris = 715;
            } else {
                nomorBaris = 716;
            }

            for (int i = 0; i < jumlahData; i++) {
                if (i <= 1) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);

                        //fill color pada cell
                        WritableCell cell = sheet.getWritableCell(7, nomorBaris);
                        cell.setCellFormat(bgColorRight);
                        cell = sheet.getWritableCell(8, nomorBaris);
                        cell.setCellFormat(bgColorLeft);
                    }

                    nomorBaris += 2;
                } else {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                    nomorBaris++;
                }

                idxData += 2;
            }

            //masukkan data poin a11 s.d. a13 dan poin b (kondisi marka)
            nomorBaris = 743;
            jumlahData = 4;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {715, 723, 731, 739};
            int[] jumlahBaris = {7, 7, 7, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6a1 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 714, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {714, 746};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a1
            label = new Label(14, 747, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a1
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    public void insertDataA6a2(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6a2(idSegmen);
        double kecepatan = koneksi.getKecepatanOperasional(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;
            int jumlahData;
            int idxData = 0;

            //masukkan data poin a1 (ukuran daun rambu)
            if (kecepatan <= 30) {
                nomorBaris = 755;
            } else if (kecepatan <= 60) {
                nomorBaris = 756;
            } else if (kecepatan <= 80) {
                nomorBaris = 757;
            } else {
                nomorBaris = 758;
            }
            if (!data[idxData].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " mm"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(7, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin a2 (warna) dan b1 (posisi)
            nomorBaris = 759;
            jumlahData = 8;
            idxData = 2;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 1 || i == 5) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //masukkan data poin b2 (jarak), b3 (tinggi), c1 (kedalaman pondasi dari permukaan), dan c2 (lebar pondasi)
            nomorBaris = 769;
            jumlahData = 8;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 1) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //masukkan data poin c3 (tiang terbuat dari bahan logam) dan c4 (papan rambu terbuat dari aluminium)
            nomorBaris = 778;
            jumlahData = 2;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {755, 761, 766, 771};
            int[] jumlahBaris = {5, 4, 4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6a2 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 754, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {754, 765, 776};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a2
            label = new Label(14, 780, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a2
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    public void insertDataA6a3(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6a3(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan poin a (bentuk dan ukuran separator)
            if (!data[0].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 787, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 787, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            int nomorBaris = 0;
            int nomorKolom = 0;
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 789;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 792;
            }

            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorKolom = 5;
            } else {
                nomorKolom = 7;
            }

            if (!data[2].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[4].toString().substring(0, 1).equals("-")) {
                label = new Label(9, (nomorBaris + 1), (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris + 1), Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris + 1);
                cell.setCellFormat(bgColorCenter);
            }

            //masukkan data poin b (letak dan ukuran bukaan)
            if (!data[6].toString().substring(0, 1).equals("-")) {
                number = new Number(9, 795, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 795, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            nomorBaris = 0;
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 797;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 800;
            }
            if (!data[8].toString().substring(0, 1).equals("-")) {
                label = new Label(9, nomorBaris, (data[8].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                cell.setCellFormat(bgColorCenter);
            }

            if (!data[10].toString().substring(0, 1).equals("-")) {
                label = new Label(9, (nomorBaris + 1), (data[10].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris + 1), Double.parseDouble(data[11].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);

                //fill color pada cell
                WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris + 1);
                cell.setCellFormat(bgColorCenter);
            }

            //memasukkan gambar
            int[] barisGambar = {788, 795};
            int[] jumlahBaris = {6, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[12].toString(), data[13].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6a3 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 787, data[14].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {787, 795};
            int idxData = 15;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a3
            label = new Label(14, 802, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a3
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    public void insertDataA6a4(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6a4(idSegmen);
        double kecepatan = koneksi.getKecepatanOperasional(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 803;
            int nomorKolom = 0;
            int jumlahData = 16;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 7) {
                    if (fungsi.toUpperCase().substring(0, 2).equals("ARTERI")) {
                        nomorBaris = 812;
                    } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                        nomorBaris = 814;
                    }

                    if (kecepatan == 60.0 || kecepatan == 40.0) {
                        nomorKolom = 5;
                    } else if (kecepatan == 30.0 || kecepatan == 20.0) {
                        nomorKolom = 7;
                    }

                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);

                        //fill color pada cell
                        WritableCell cell = sheet.getWritableCell(nomorKolom, nomorBaris);
                        cell.setCellFormat(bgColorCenter);
                    }

                    nomorBaris = 815;   //lompat ke baris 816 di excel
                    idxData += 2;
                } else {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                    nomorBaris++;
                    idxData += 2;
                }
            }

            //memasukkan gambar
            int[] barisGambar = {804, 813};
            int[] jumlahBaris = {8, 8};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6a4 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 803, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {803, 804, 805};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a4
            label = new Label(14, 823, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a4
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    public void insertDataA6a5(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6a5(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 829;
            int jumlahData = 10;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i <= 2) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else if (i == 4) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " cm"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                }

                if (i == 6) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {830, 836};
            int[] jumlahBaris = {5, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6a5 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 829, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {829, 832, 834, 839};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a5
            label = new Label(14, 840, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a5
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    public void insertDataA6a6(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6a6(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 841;
            int jumlahData = 9;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 0 || i >= 7) {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {842, 847};
            int[] jumlahBaris = {4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6a6 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 841, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {841, 848, 849, 850};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a6
            label = new Label(14, 852, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a6
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    public void insertDataA6a7(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6a7(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font CENTER
            WritableFont fontBGcolor = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat bgColorCenter = new WritableCellFormat();
            bgColorCenter.setFont(fontBGcolor);
            bgColorCenter.setBackground(Colour.YELLOW);
            bgColorCenter.setAlignment(Alignment.CENTRE);
            bgColorCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font dan format untuk background kuning di cell dengan font LEFT
            WritableCellFormat bgColorLeft = new WritableCellFormat();
            bgColorLeft.setFont(fontBGcolor);
            bgColorLeft.setBackground(Colour.YELLOW);
            bgColorLeft.setAlignment(Alignment.LEFT);
            bgColorLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
            bgColorLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 859;
            int jumlahData = 19;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i <= 2 || (i >= 11 && i <= 13) || (i >= 16)) {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //meter
                    if (i == 8) {
                        switch (fungsi.toUpperCase()) {
                            case "ARTERI":
                                nomorBaris = 868;
                                break;
                            case "KOLEKTOR":
                                nomorBaris = 869;
                                break;
                            case "LOKAL":
                                nomorBaris = 870;
                                break;
                        }
                    }

                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);

                        //fill color pada cell
                        if (i == 8) {
                            WritableCell cell = sheet.getWritableCell(6, nomorBaris);
                            cell.setCellFormat(bgColorCenter);
                        }
                    }
                }

                //atur perpindahan nomorBaris pada saat i = 8 (data Jarak Interval Antar Lampu)
                if (i == 8) {
                    switch (fungsi.toUpperCase()) {
                        case "ARTERI":
                            nomorBaris += 4;
                            break;
                        case "KOLEKTOR":
                            nomorBaris += 3;
                            break;
                        case "LOKAL":
                            nomorBaris += 2;
                            break;
                    }
                } else if (i == 5) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {860, 865, 871, 877};
            int[] jumlahBaris = {4, 5, 5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6a7 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 859, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {859, 860, 862, 863, 872, 875};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a7
            label = new Label(14, 883, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a7
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }

        //set font String 11pt
        WritableFont font11pt = new WritableFont(WritableFont.ARIAL, 11);
        font11pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
        StringCellFormat11pt.setFont(font11pt);
        StringCellFormat11pt.setAlignment(Alignment.CENTRE);
        StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat11pt.setWrap(true);
        StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A6a
        label = new Label(14, 884, kategoriA6a, StringCellFormat11pt);
        sheet.addCell(label);
    }

    public void insertDataA6b1(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b1(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 891;
            int jumlahData = 6;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 2) {
                    //meter
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0 || i == 3) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {892, 896};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b1 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 891, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {891, 893, 898};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b1
            label = new Label(14, 900, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b1
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    public void insertDataA6b2(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b2(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 901;
            int jumlahData = 9;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 1 || i == 2 || i == 3 || i == 5) {
                    //meter
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 1 || i == 4) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {902, 907};
            int[] jumlahBaris = {4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b2 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 901, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {901, 902, 911};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b2
            label = new Label(14, 912, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b2
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    public void insertDataA6b3(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b3(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 919;
            int jumlahData = 8;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 1 || i == 2 || i == 4) {
                    //meter
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else if (i == 3) {
                    nomorBaris += 3;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {920, 925};
            int[] jumlahBaris = {4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b3 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 919, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {919, 921, 929};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b3
            label = new Label(14, 830, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b3
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    public void insertDataA6b4(String idSegmen) throws WriteException, IOException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b4(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 931;
            int jumlahData = 5;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 0 || i == 1) {
                    //meter
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0 || i == 2) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {932, 935};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b4 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 931, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {931, 937};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b4
            label = new Label(14, 938, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b4
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    public void insertDataA6b5(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b5(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 945;
            int jumlahData = 5;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 0) {
                    //meter
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {946};
            int[] jumlahBaris = {5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b5 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 945, data[idxData + 1].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {945, 950};
            idxData += 2;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b5
            label = new Label(14, 952, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b5
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    public void insertDataA6b6(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b6(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 953;
            int jumlahData = 4;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 2) {
                    //meter
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {954};
            int[] jumlahBaris = {4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b6 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 953, data[idxData + 1].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {953, 955};
            idxData += 2;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b6
            label = new Label(14, 959, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b6
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    public void insertDataA6b7(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b7(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 960;
            int jumlahData = 5;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 0 || i == 1) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {961, 965};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b7 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 960, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {960, 962, 966};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b7
            label = new Label(14, 969, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b7
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    public void insertDataA6b8(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getDataA6b8(idSegmen);

        if (data[0] != null) {
            //set font String 8pt
            WritableFont font8pt = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringCellFormat8pt = new WritableCellFormat();
            StringCellFormat8pt.setFont(font8pt);
            StringCellFormat8pt.setAlignment(Alignment.CENTRE);
            StringCellFormat8pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat8pt.setWrap(true);
            StringCellFormat8pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            font9pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 976;
            int jumlahData = 8;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i <= 3) {
                    //meter
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().substring(0, 1).equals("-")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 0 || i == 2 || i == 4 || i == 6) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {977, 980, 983, 986};
            int[] jumlahBaris = {2, 2, 2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        progress.setText("downloading point a6b8 image "+String.valueOf(idxGambar)+"...");
                    }
                });
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    File mediaStorageDir = null;
                    try{
                        InputStream img = new URL(imagePath[i]).openStream();
                        mediaStorageDir = new File(localPath + idxGambar + ".png");

                        DataInputStream out = new DataInputStream(img);

                        byte[] buffer = new byte[1024];
                        int flength;

                        FileOutputStream fos = new FileOutputStream(new File(mediaStorageDir.getAbsolutePath()));
                        while ((flength = out.read(buffer))>0) {
                            fos.write(buffer, 0, flength);
                        }
                    } catch (Exception e) {
                        Log.e("Error Download", e.toString());
                    }
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(mediaStorageDir.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 976, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {976, 987};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b8
            label = new Label(14, 989, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b8
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }

        //set font String 11pt
        WritableFont font11pt = new WritableFont(WritableFont.ARIAL, 11);
        font11pt.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
        StringCellFormat11pt.setFont(font11pt);
        StringCellFormat11pt.setAlignment(Alignment.CENTRE);
        StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat11pt.setWrap(true);
        StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

        //memasukkan kategori A6b
        label = new Label(14, 990, kategoriA6b, StringCellFormat11pt);
        sheet.addCell(label);
    }

    public void insertAdministrasiJalan(String idSegmen) throws WriteException {

        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                progress.setText("downloading administrasi jalan...");
            }
        });

        //ambil data dari DB
        KoneksiDB5 koneksi = new KoneksiDB5();
        Object[] data = koneksi.getAdministrasiJalan(idSegmen);

        if (data[0] != null) {
            //set font String 9pt
            WritableFont font9pt = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat StringCellFormat9pt = new WritableCellFormat();
            StringCellFormat9pt.setFont(font9pt);
            StringCellFormat9pt.setAlignment(Alignment.CENTRE);
            StringCellFormat9pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat9pt.setWrap(true);
            StringCellFormat9pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font untuk CATATAN
            WritableCellFormat formatCatatan = new WritableCellFormat();
            formatCatatan.setFont(font9pt);
            formatCatatan.setAlignment(Alignment.LEFT);
            formatCatatan.setVerticalAlignment(VerticalAlignment.CENTRE);
            formatCatatan.setWrap(true);
            formatCatatan.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 1000;
            int idxData = 0;
            int jumData = 30;

            for (int i = 0; i < jumData; i += 5) {
                if (!data[idxData].toString().substring(0, 1).equals("-")) {
                    label = new Label(3, nomorBaris, data[idxData].toString(), StringCellFormat9pt);    //hasil uji
                    sheet.addCell(label);
                }

                if (!data[idxData + 1].toString().substring(0, 1).equals("-")) {
                    label = new Label(6, nomorBaris, data[idxData + 1].toString(), StringCellFormat9pt);    //hasil uji
                    sheet.addCell(label);
                }

                if (!data[idxData + 2].toString().substring(0, 1).equals("-")) {
                    label = new Label(9, nomorBaris, data[idxData + 2].toString(), StringCellFormat9pt);    //hasil uji
                    sheet.addCell(label);
                }

                if (!data[idxData + 3].toString().substring(0, 1).equals("-")) {
                    label = new Label(10, nomorBaris, data[idxData + 3].toString(), StringCellFormat9pt);    //hasil uji
                    sheet.addCell(label);
                }

                if (!data[idxData + 4].toString().substring(0, 1).equals("-")) {
                    label = new Label(12, nomorBaris, data[idxData + 4].toString(), formatCatatan);    //hasil uji
                    sheet.addCell(label);
                }

                idxData += 5;
                nomorBaris++;
            }
        }
    }
}
