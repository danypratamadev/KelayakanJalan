package com.pratama.kelayakanjalan;

import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.util.TimerTask;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public final class CreateExcel2 {

    private WritableCellFormat DoubleCellFormat;
    private WritableCellFormat IntegerCellFormat;
    private WritableCellFormat PercentCellFormat;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CreateExcel2(String idSegmen, String filePath, String NRU, String SEG) throws IOException, BiffException, WriteException {
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
        KoneksiDB2 koneksi = new KoneksiDB2();
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

        try{

            insertIdentitasRuas(idSegmen);
            //segmenActivity.progress.setProgress(2);

        } catch (Exception e){
            Log.e("Error identitas ", e.toString());
        }

        try{

            insertKlasifikasiJalan();
            //segmenActivity.progress.setProgress(4);

        } catch (Exception e){
            Log.e("Error klasifikasi ", e.toString());
        }

        try{

            insertSegmen(idSegmen);
            //segmenActivity.progress.setProgress(6);

        } catch (Exception e){
            Log.e("Error segmen ", e.toString());
        }

        try{

            insertDataA111(idSegmen);       //A111
            //segmenActivity.progress.setProgress(8);

        } catch (Exception e){
            Log.e("Error a111 ", e.toString());
        }

        try{

            insertDataA112(idSegmen);       //A112
            //segmenActivity.progress.setProgress(10);

        } catch (Exception e){
            Log.e("Error a112 ", e.toString());
        }

        try{

            insertDataA113(idSegmen);       //A113
            //segmenActivity.progress.setProgress(12);

        } catch (Exception e){
            Log.e("Error a113 ", e.toString());
        }

        try{

            insertDataA114(idSegmen);       //A114
            //segmenActivity.progress.setProgress(14);

        } catch (Exception e){
            Log.e("Error a114 ", e.toString());
        }

        try{

            insertDataA115(idSegmen);       //A115
            //segmenActivity.progress.setProgress(16);

        } catch (Exception e){
            Log.e("Error a115 ", e.toString());
        }

        try{

            insertDataA116(idSegmen);       //A116
            //segmenActivity.progress.setProgress(18);

        } catch (Exception e){
            Log.e("Error a116 ", e.toString());
        }

        try{

            insertDataA121(idSegmen);       //A121
            //segmenActivity.progress.setProgress(20);

        } catch (Exception e){
            Log.e("Error a121 ", e.toString());
        }

        try{

            insertDataA122(idSegmen);       //A122

        } catch (Exception e){
            Log.e("Error a122 ", e.toString());
        }

        try{

            insertDataA123(idSegmen);       //A123

        } catch (Exception e){
            Log.e("Error a123 ", e.toString());
        }

        try{

            insertDataA124(idSegmen);       //A124

        } catch (Exception e){
            Log.e("Error a124 ", e.toString());
        }

        try{

            insertDataA131(idSegmen);       //A131

        } catch (Exception e){
            Log.e("Error a131 ", e.toString());
        }

        try{

            insertDataA132(idSegmen);       //A132

        } catch (Exception e){
            Log.e("Error a132 ", e.toString());
        }

        try{

            insertDataA133(idSegmen);       //A133

        } catch (Exception e){
            Log.e("Error a133 ", e.toString());
        }

        try{

            insertDataA141(idSegmen);       //A141

        } catch (Exception e){
            Log.e("Error a141 ", e.toString());
        }

        try{

            insertDataA21(idSegmen);       //A21

        } catch (Exception e){
            Log.e("Error a21 ", e.toString());
        }

        try{

            insertDataA22(idSegmen);       //A22

        } catch (Exception e){
            Log.e("Error a22 ", e.toString());
        }

        try{

            insertDataA23(idSegmen);       //A23

        } catch (Exception e){
            Log.e("Error a23 ", e.toString());
        }

        try{

            insertDataA31(idSegmen);       //A31

        } catch (Exception e){
            Log.e("Error a31 ", e.toString());
        }

        try{

            insertDataA32(idSegmen);       //A32

        } catch (Exception e){
            Log.e("Error a32 ", e.toString());
        }

        try{

            insertDataA33(idSegmen);       //A33

        } catch (Exception e){
            Log.e("Error a33 ", e.toString());
        }

        try{

            insertDataA34(idSegmen);       //A34

        } catch (Exception e){
            Log.e("Error a34 ", e.toString());
        }

        try{

            insertDataA35(idSegmen);       //A35

        } catch (Exception e){
            Log.e("Error a35 ", e.toString());
        }

        try{

            insertDataA36(idSegmen);       //A36

        } catch (Exception e){
            Log.e("Error a36 ", e.toString());
        }

        try{

            insertDataA41(idSegmen);       //A41

        } catch (Exception e){
            Log.e("Error a41 ", e.toString());
        }

        try{

            insertDataA42(idSegmen);       //A42

        } catch (Exception e){
            Log.e("Error a42 ", e.toString());
        }

        try{

            insertDataA43(idSegmen);       //A43

        } catch (Exception e){
            Log.e("Error a43 ", e.toString());
        }

        try{

            insertDataA51(idSegmen);       //A51

        } catch (Exception e){
            Log.e("Error a51 ", e.toString());
        }

        try{

            insertDataA52(idSegmen);       //A52

        } catch (Exception e){
            Log.e("Error a52 ", e.toString());
        }

        try{

            insertDataA53(idSegmen);       //A53

        } catch (Exception e){
            Log.e("Error a53 ", e.toString());
        }

        try{

            insertDataA54(idSegmen);       //A54

        } catch (Exception e){
            Log.e("Error a54 ", e.toString());
        }

        try{

            insertDataA55(idSegmen);       //A55

        } catch (Exception e){
            Log.e("Error a55 ", e.toString());
        }

        try{

            insertDataA56(idSegmen);       //A56

        } catch (Exception e){
            Log.e("Error a56 ", e.toString());
        }

        try{

            insertDataA57(idSegmen);       //A57

        } catch (Exception e){
            Log.e("Error a57 ", e.toString());
        }

        try{

            insertDataA6a1(idSegmen);      //A6a1

        } catch (Exception e){
            Log.e("Error a6a1 ", e.toString());
        }

        try{

            insertDataA6a2(idSegmen);      //A6a2

        } catch (Exception e){
            Log.e("Error a6a2 ", e.toString());
        }

        try{

            insertDataA6a3(idSegmen);      //A6a3

        } catch (Exception e){
            Log.e("Error a6a3 ", e.toString());
        }

        try{

            insertDataA6a4(idSegmen);      //A6a4

        } catch (Exception e){
            Log.e("Error a6a4 ", e.toString());
        }

        try{

            insertDataA6a5(idSegmen);      //A6a5

        } catch (Exception e){
            Log.e("Error a6a5 ", e.toString());
        }

        try{

            insertDataA6a6(idSegmen);      //A6a6

        } catch (Exception e){
            Log.e("Error a6a6 ", e.toString());
        }

        try{

            insertDataA6a7(idSegmen);      //A6a7

        } catch (Exception e){
            Log.e("Error a6a7 ", e.toString());
        }

        try{

            insertDataA6b1(idSegmen);      //A6b1

        } catch (Exception e){
            Log.e("Error a6b1 ", e.toString());
        }

        try{

            insertDataA6b2(idSegmen);      //A6b2

        } catch (Exception e){
            Log.e("Error a6b2 ", e.toString());
        }

        try{

            insertDataA6b3(idSegmen);      //A6b3

        } catch (Exception e){
            Log.e("Error a6b3 ", e.toString());
        }

        try{

            insertDataA6b4(idSegmen);      //A6b4

        } catch (Exception e){
            Log.e("Error a6b4 ", e.toString());
        }

        try{

            insertDataA6b5(idSegmen);      //A6b5

        } catch (Exception e){
            Log.e("Error a6b5 ", e.toString());
        }

        try{

            insertDataA6b6(idSegmen);      //A6b6

        } catch (Exception e){
            Log.e("Error a6b6 ", e.toString());
        }

        try{

            insertDataA6b7(idSegmen);      //A6b7

        } catch (Exception e){
            Log.e("Error a6b7 ", e.toString());
        }

        try{

            insertDataA6b8(idSegmen);      //A6b8

        } catch (Exception e){
            Log.e("Error a6b8 ", e.toString());
        }

        //Eksekusi file excel
        fileXLS.write();
        fileXLS.close();

        //menghapus gambar2 hasil unduhan excel agar tidak memenuhi storage hp
        deleteTemporaryImages();
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
        //ambil data ruas dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        String[] dataRuas = koneksi.getDataRuasSegmen(idSegmen);

        //Inisialisasi nomor baris dan nomor kolom
        int[] nomorKolom = {5, 2, 15, 2, 15, 2, 15, 2, 5};     //F:5, P:15, C:2
        int[] nomorBaris = {2, 3, 3, 4, 4, 5, 5, 6, 6};

        // Create cell font and format 
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 11);
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setFont(cellFont);
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

        //tulis data
        for (int i = 0; i < nomorBaris.length; i++) {
            label = new Label(nomorKolom[i], nomorBaris[i], dataRuas[i], cellFormat);
            sheet.addCell(label);
        }

        //perbaiki alignment teks pada cell P4 (kolom 15, baris 3)
        WritableFont cellFont1 = new WritableFont(WritableFont.ARIAL, 11);
        WritableCellFormat cellFormat1 = new WritableCellFormat();
        cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat1.setFont(cellFont1);
        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat1.setAlignment(Alignment.RIGHT);
        label = new Label(15, 3, sheet.getCell(15, 3).getContents(), cellFormat1);
        sheet.addCell(label);
    }

    public void insertKlasifikasiJalan() throws WriteException {
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
            nomorBaris = 9;
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
        int panjangIdSegmen = idSegmen.length();
        String nomorSegmen = idSegmen.substring(panjangIdSegmen - 2, panjangIdSegmen);  //ambil 2 digit terakhir

        //inisialisasi nomor kolom dan nomor2 baris
        int nomorKolom = 15;    //kolom 'P' = huruf ke-16
        int[] nomorBaris = {16, 54, 85, 106, 125, 165, 198, 215, 261, 297, 321, 342, 369, 382, 419, 443, 460, 482,
            508, 535, 566, 593, 619, 646, 685, 717, 759, 786, 817, 842, 866, 890};

        //membuat objek untuk memformat cell dan font
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 14);
        cellFont.setBoldStyle(WritableFont.BOLD);

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setFont(cellFont);
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat.setAlignment(Alignment.CENTRE);

        //tulis nomor segmen ke tiap halaman
        String teks = "SEGMEN: " + nomorSegmen;
        for (int i = 0; i < nomorBaris.length; i++) {
            label = new Label(nomorKolom, nomorBaris[i], teks, cellFormat);
            sheet.addCell(label);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void deleteTemporaryImages() throws IOException {
        for (int i = 1; i < idxGambar; i++) {
            Path path = Paths.get(localPath + i + ".png");
            Files.delete(path);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA111(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialiasasi variabel lokal
            int nomorBaris = 0;

            //memasukkan poin a (Fungsi Jalan)
            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                switch (fungsi.toUpperCase()) {
                    case "ARTERI":
                        //Arteri Primer
                        nomorBaris = 20;
                        break;
                    case "KOLEKTOR":
                        //Kolektor Primer
                        nomorBaris = 21;
                        break;
                    case "LOKAL":
                        //Lokal Primer
                        nomorBaris = 22;
                        break;
                    case "LINGKUNGAN":
                        //Lingkungan Primer
                        nomorBaris = 23;
                        break;
                }
            } else {
                switch (fungsi.toUpperCase()) {
                    case "ARTERI":
                        //Arteri Primer
                        nomorBaris = 24;
                        break;
                    case "KOLEKTOR":
                        //Kolektor Primer
                        nomorBaris = 25;
                        break;
                    case "LOKAL":
                        //Lokal Primer
                        nomorBaris = 26;
                        break;
                    case "LINGKUNGAN":
                        //Lingkungan Primer
                        nomorBaris = 27;
                        break;
                }
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris, data[0].toString(), StringCellFormat8pt);
                sheet.addCell(label);
            }

            //memasukkan poin b (Arus lalu lintas yang dilayani)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 30;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 31;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 32;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 33;
                    break;
            }

            if (!data[1].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Integer.parseInt(data[1].toString()), IntegerCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan poin c (Jumlah lajur)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 35;
            } else {
                nomorBaris = 36;
            }
            if (!data[3].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan poin d (Lebar setiap lajur)
            int LHRT = Integer.parseInt(data[1].toString());     //ambil data LHRT (smp/hari) dari data ke-1 poin b
            if (LHRT > 17000) {
                nomorBaris = 39;
            } else if (LHRT >= 3000) {
                nomorBaris = 40;
            } else {
                nomorBaris = 41;
            }
            if (!data[5].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan poin e (keseragaman lebar lajur)
            if (!data[7].toString().equals("-1")) {
                number = new Number(9, 42, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 42, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan poin f (kemiringan melintang)
            if (!data[9].toString().equals("-1")) {
                number = new Number(9, 43, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 43, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {20, 29, 38};
            int[] jumlahBaris = {8, 8, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[11].toString(), data[12].toString(), data[13].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 20, data[14].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {29, 34, 37, 42, 43};
            int idxData = 15;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A111
            label = new Label(14, 44, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A111
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA112(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masuukan data poin a (lebar bahu)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 47;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 48;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 49;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 50;
                    break;
            }
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()), DoubleCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (keseragaman levar bahu)
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, 51, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 51, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (perkerasan bahu)
            if (!data[4].toString().equals("-1")) {
                number = new Number(9, 58, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 58, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d (posisi muka bahu thd muka jalan)
            if (!data[6].toString().equals("-1")) {
                number = new Number(9, 59, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 59, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin e (kemiringan melintang bahu)
            if (kelasPrasarana.toUpperCase().equals("JALAN BEBAS HAMBATAN (JBH)")) {
                if (!data[8].toString().equals("-1")) {
                    number = new Number(9, 60, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 60, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }
            } else {
                if (!data[8].toString().equals("-1")) {
                    number = new Number(9, 61, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 61, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }
            }

            //memasukkan gambar
            int[] barisGambar = {45, 58};
            int[] jumlahBaris = {7, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[10].toString(), data[11].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 45, data[12].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {45, 51, 58, 59, 60};
            int idxData = 13;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A112
            label = new Label(14, 62, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A112
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA113(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris;

            //masukkan data poin a (lebar median)
            String median = data[0].toString();
            if (median.toUpperCase().equals("MEDIAN DATAR")) {
                nomorBaris = 64;
            } else if (median.toUpperCase().equals("MEDIAN DITINGGIKAN")) {
                nomorBaris = 65;
            } else {
                nomorBaris = 66;
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[1].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (tipe median)
            String tipeMedian = data[3].toString();
            if (median.toUpperCase().equals("MEDIAN DATAR")) {
                nomorBaris = 67;
            } else if (median.toUpperCase().equals("MEDIAN DITURUNKAN")) {
                nomorBaris = 68;
            } else {
                if (tipeMedian.toUpperCase().equals("KERB")) {
                    nomorBaris = 69;
                } else {
                    nomorBaris = 71;
                }
            }
            if (!data[4].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (perkerasan median)
            String perkerasanMedian = data[6].toString();
            if (median.toUpperCase().equals("MEDIAN DATAR")) {
                nomorBaris = 72;
            } else if (median.toUpperCase().equals("MEDIAN DITURUNKAN")) {
                nomorBaris = 73;
            } else {
                if (perkerasanMedian.toUpperCase().equals("KERB")) {
                    nomorBaris = 74;
                } else {
                    nomorBaris = 75;
                }
            }
            if (!data[7].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d (Bukaan pada median)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 78;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 79;
            }
            //Jarak antar bukaan
            if (!data[9].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[9].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //Lebar bukaan
            if (!data[11].toString().equals("-1")) {
                label = new Label(9, 81, (data[11].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 81, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {63, 70, 76};
            int[] jumlahBaris = {6, 5, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[13].toString(), data[14].toString(), data[15].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 63, data[16].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {63, 67, 72, 77};
            int idxData = 17;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A113
            label = new Label(14, 82, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A113
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA114(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a (lebar/ dimensi selokan samping)
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, 89, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 89, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (bentuk selokan samping)
            //b1
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, 90, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            //b2
            if (!data[3].toString().equals("-1")) {
                number = new Number(9, 91, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //hasil uji
                sheet.addCell(number);
                number = new Number(10, 91, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (fungsi mengalirkan air)
            if (!data[5].toString().equals("-1")) {
                number = new Number(9, 93, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //hasil uji
                sheet.addCell(number);
                number = new Number(10, 93, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {89, 92};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[7].toString(), data[8].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 89, data[9].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {89, 90, 93};
            int idxData = 10;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A114
            label = new Label(14, 94, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A114
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA115(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //masukkan data poin a1 (lebar ambang pengaman)
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, 95, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 95, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a2 (lebar ambang pengaman)
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, 96, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 96, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (pengamanan konstruksi jalan)
            if (!data[4].toString().equals("-1")) {
                label = new Label(9, 97, ("(" + data[4].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 97, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {95, 100};
            int[] jumlahBaris = {4, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 95, data[8].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {95, 97};
            int idxData = 9;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A115
            label = new Label(14, 103, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A11 berdasarkan nilai A115
            if (data[idxData].toString().equals("LS")) {
                kategoriA11 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA116(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 110;   //start dari baris ke 110
            int idxData = 0;

            for (int i = 0; i < jumlahPoin; i++) {
                if (i == 6) {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString()), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 3 || i == 6) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {110, 114, 118};
            int[] jumlahBaris = {3, 3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[18].toString(), data[19].toString(), data[20].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 110, data[21].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {110, 116};
            idxData = 22;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A116
            label = new Label(14, 121, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 122, kategoriA11, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A11
        if (kategoriA11.equals("LS")) {
            kategoriA1 = "LS";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA121(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal;
            int nomorBaris1 = 0;
            int nomorBaris2 = 0;

            //masukkan data poin a (panjang bagian jalan yang lurus)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorBaris1 = 129;
                        break;
                    case "BUKIT (B)":
                        nomorBaris1 = 130;
                        break;
                    case "GUNUNG (G)":
                        nomorBaris1 = 131;
                        break;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorBaris1 = 132;
                        break;
                    case "BUKIT (B)":
                        nomorBaris1 = 133;
                        break;
                    case "GUNUNG (G)":
                        nomorBaris1 = 134;
                        break;
                }
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris1, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b1 (jarak pandang)
            String jarakPandang = data[2].toString();
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris1 = 136;
                    nomorBaris2 = 137;
                } else {
                    nomorBaris1 = 139;
                    nomorBaris2 = 140;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris1 = 142;
                    nomorBaris2 = 143;
                } else {
                    nomorBaris1 = 144;
                    nomorBaris2 = 145;
                }
            }
            if (!data[3].toString().equals("-1")) {
                label = new Label(9, nomorBaris1, (data[3].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b2 (jarak pandang)
            if (!data[5].toString().equals("-1")) {
                label = new Label(9, nomorBaris2, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c1 (lingkungan jalan)
            if (!data[7].toString().equals("-1")) {
                label = new Label(9, 146, ("(" + data[7].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            //masukkan data poin c2 (lingkungan jalan)
            if (!data[8].toString().equals("-1")) {
                number = new Number(9, 147, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 147, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {129, 139};
            int[] jumlahBaris = {9, 9};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[10].toString(), data[11].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 129, data[12].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {129, 135, 146};
            int idxData = 13;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A121
            label = new Label(14, 148, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A12 berdasarkan nilai A121
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA122(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        Object[] data = koneksi.getDataA122(idSegmen);

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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 150;
                } else {
                    nomorBaris = 151;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 153;
                } else {
                    nomorBaris = 154;
                }
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (superelevansi)
            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                nomorBaris = 155;
            } else {
                nomorBaris = 157;
            }
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (jarak pandang)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 159;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 160;
            }
            if (!data[4].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {149, 156};
            int[] jumlahBaris = {6, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 149, data[8].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {149, 155, 158};
            int idxData = 9;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A122
            label = new Label(14, 162, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A12 berdasarkan nilai A122
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA123(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            int nomorBaris = 0;

            //masukkan data poin a (jumlah persimpangan per KM)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    switch (kelasPrasarana.toUpperCase()) {
                        case "JALAN BEBAS HAMBATAN (JBH)":
                            nomorBaris = 169;
                            break;
                        case "JALAN RAYA (JR)":
                            nomorBaris = 170;
                            break;
                        case "JALAN SEDANG (JS)":
                            nomorBaris = 171;
                            break;
                        case "JALAN KECIL (JK)":
                            nomorBaris = 172;
                            break;
                    }
                } else {
                    switch (kelasPrasarana.toUpperCase()) {
                        case "JALAN BEBAS HAMBATAN (JBH)":
                            nomorBaris = 173;
                            break;
                        case "JALAN RAYA (JR)":
                            nomorBaris = 174;
                            break;
                        case "JALAN SEDANG (JS)":
                            nomorBaris = 176;
                            break;
                        case "JALAN KECIL (JK)":
                            nomorBaris = 177;
                            break;
                    }
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                switch (kelasPrasarana.toUpperCase()) {
                    case "JALAN BEBAS HAMBATAN (JBH)":
                        nomorBaris = 178;
                        break;
                    case "JALAN RAYA (JR)":
                        nomorBaris = 179;
                        break;
                    case "JALAN SEDANG (JS)":
                        nomorBaris = 180;
                        break;
                    case "JALAN KECIL (JK)":
                        nomorBaris = 181;
                        break;
                }
            }
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (cara akses je jalan utama)
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, 183, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 183, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {169, 175, 182};
            int[] jumlahBaris = {5, 6, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[4].toString(), data[5].toString(), data[6].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 169, data[7].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {169, 183};
            int idxData = 8;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A123
            label = new Label(14, 187, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A12 berdasarkan nilai A123
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA124(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a(banyaknya akses persil)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                switch (kelasPrasarana.toUpperCase()) {
                    case "JALAN BEBAS HAMBATAN (JBH)":
                        nomorBaris = 188;
                        break;
                    case "JALAN RAYA (JR)":
                        nomorBaris = 189;
                        break;
                    case "JALAN SEDANG (JS)":
                        nomorBaris = 190;
                        break;
                    case "JALAN KECIL (JK)":
                        nomorBaris = 191;
                        break;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                switch (kelasPrasarana.toUpperCase()) {
                    case "JALAN BEBAS HAMBATAN (JBH)":
                        nomorBaris = 192;
                        break;
                    case "JALAN RAYA (JR)":
                        nomorBaris = 193;
                        break;
                    case "JALAN SEDANG (JS)":
                        nomorBaris = 194;
                        break;
                    case "JALAN KECIL (JK)":
                        nomorBaris = 195;
                        break;
                }
            }
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (akses ke jalan utama)
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, 202, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 202, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (bentuk akses)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 205;
                } else {
                    nomorBaris = 207;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                    nomorBaris = 208;
                } else {
                    nomorBaris = 210;
                }
            }
            if (!data[4].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {188, 202, 206, 209};
            int[] jumlahBaris = {8, 3, 2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString(), data[8].toString(), data[9].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 188, data[10].toString(), StringCellFormat8pt);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {188, 202, 205};
            int idxData = 11;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A124
            label = new Label(14, 211, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 212, kategoriA12, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A12
        if (kategoriA12.equals("LS")) {
            kategoriA1 = "LS";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA131(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        Object[] data = koneksi.getDataA131(idSegmen);

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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;
            int mulaiBaris;

            //masukkan data poin a1 (kelandaian memanjang)
            if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorBaris = 220;
                        break;
                    case "BUKIT (B)":
                        nomorBaris = 221;
                        break;
                    case "GUNUNG (G)":
                        nomorBaris = 222;
                        break;
                }
            } else {
                switch (medan.toUpperCase()) {
                    case "DATAR (D)":
                        nomorBaris = 220;
                        break;
                    case "BUKIT (B)":
                        nomorBaris = 221;
                        break;
                    case "GUNUNG (G)":
                        nomorBaris = 222;
                        break;
                }
            }
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a2 (kelandaian memanjang)
            int tempData = (int) Double.parseDouble(data[0].toString());

            if (fungsi.toUpperCase().equals("SEKUNDER (DALAM KOTA)")) {
                mulaiBaris = 226;
                for (int i = 4; i <= 10; i++) {
                    if (i == tempData) {
                        nomorBaris = mulaiBaris + (tempData - 4);
                        break;
                    }
                }
            } else {
                mulaiBaris = 235;
                for (int i = 4; i <= 10; i++) {
                    if (i == tempData) {
                        nomorBaris = mulaiBaris + (tempData - 4);
                        break;
                    }
                }
            }
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, mulaiBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (jarak pandang)
            int nomorBaris1 = 0;
            int nomorBaris2 = 0;
            String jarakPandang = data[4].toString();
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 243;
                    nomorBaris1 = 244;
                    nomorBaris2 = 245;
                } else {
                    nomorBaris = 243;
                    nomorBaris1 = 244;
                    nomorBaris2 = 245;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 250;
                    nomorBaris1 = 251;
                    nomorBaris2 = 252;
                } else {
                    nomorBaris = 253;
                    nomorBaris1 = 254;
                    nomorBaris2 = 255;
                }
            }
            if (!data[5].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[7].toString().equals("-1")) {
                label = new Label(9, nomorBaris1, (data[7].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[9].toString().equals("-1")) {
                label = new Label(9, nomorBaris2, (data[9].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (lingkungan jalan)
            if (!data[11].toString().equals("-1")) {
                label = new Label(9, 256, ("(" + data[11].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            if (!data[12].toString().equals("-1")) {
                number = new Number(9, 257, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 257, Double.parseDouble(data[13].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {219, 229, 239, 249};
            int[] jumlahBaris = {9, 9, 9, 9};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[14].toString(), data[15].toString(), data[16].toString(), data[17].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 219, data[18].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {219, 242, 256};
            int idxData = 19;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A131
            label = new Label(14, 258, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A13 berdasarkan nilai A131
            if (data[idxData].toString().equals("LS")) {
                kategoriA12 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA132(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringCellFormat11pt = new WritableCellFormat();
            StringCellFormat11pt.setFont(font11pt);
            StringCellFormat11pt.setAlignment(Alignment.CENTRE);
            StringCellFormat11pt.setVerticalAlignment(VerticalAlignment.CENTRE);
            StringCellFormat11pt.setWrap(true);
            StringCellFormat11pt.setBorder(Border.ALL, BorderLineStyle.THIN);

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a (keperluan keberadaannya)
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, 265, ("(" + data[0].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 265, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b1 (Lebar dan panjang lajur)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HABATAN (JBH)":
                    nomorBaris = 266;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 267;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 268;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 269;
                    break;
            }
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b2 (Lebar dan panjang lajur)
            int jumData = 5;
            int idxData = 4;
            nomorBaris = 270;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin c (Taper masuk dan keluar lajur)
            if (!data[14].toString().equals("-1")) {
                label = new Label(9, 276, (data[14].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 276, Double.parseDouble(data[15].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {265, 271};
            int[] jumlahBaris = {5, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[16].toString(), data[17].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 265, data[18].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {265, 266, 276};
            idxData = 19;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A132
            label = new Label(14, 277, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A13 berdasarkan nilai A132
            if (data[idxData].toString().equals("LS")) {
                kategoriA13 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA133(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        Object[] data = koneksi.getDataA133(idSegmen);

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
            int nomorBaris1 = 0;
            int nomorBaris2 = 0;

            //masukkan data poin a1 (Ketajaman lengkungan)
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, 278, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 278, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a2,a3,a4 (ketajaman lengkungan)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 280;
                nomorBaris1 = 281;
                nomorBaris2 = 282;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 284;
                nomorBaris1 = 286;
                nomorBaris2 = 287;
            }
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[4].toString().equals("-1")) {
                label = new Label(9, nomorBaris1, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[6].toString().equals("-1")) {
                label = new Label(9, nomorBaris2, (data[6].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b1, b2, b3 (jarak pandang)
            String jarakPandang = data[8].toString();
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 289;
                    nomorBaris1 = 290;
                    nomorBaris2 = 291;
                } else {
                    nomorBaris = 292;
                    nomorBaris1 = 293;
                    nomorBaris2 = 294;
                }
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                    nomorBaris = 302;
                    nomorBaris1 = 303;
                    nomorBaris2 = 304;
                } else {
                    nomorBaris = 305;
                    nomorBaris1 = 306;
                    nomorBaris2 = 307;
                }
            }
            if (!data[9].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[11].toString().equals("-1")) {
                label = new Label(9, nomorBaris1, (data[11].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris1, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[13].toString().equals("-1")) {
                label = new Label(9, nomorBaris2, (data[13].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris2, Double.parseDouble(data[14].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c dan d (arah jalan di balik lengkungan) dan (kombinasi lengkung vertikal dan tikungan horizontal)
            int jumData = 7;
            int idxData = 15;
            nomorBaris = 309;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {278, 286, 301, 308, 313};
            int[] jumlahBaris = {7, 9, 6, 4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[29].toString(), data[30].toString(), data[31].toString(), data[32].toString(), data[33].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 278, data[34].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {278, 288, 309, 311};
            idxData = 35;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A133
            label = new Label(14, 317, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 318, kategoriA13, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A13
        if (kategoriA13.equals("LS")) {
            kategoriA1 = "LS";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA141(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, 325, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 325, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (overlaping kurva vertikal pada begian menikung ...)
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, 331, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 331, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {325, 328, 334};
            int[] jumlahBaris = {2, 5, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[4].toString(), data[5].toString(), data[6].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 325, data[7].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {325, 331};
            int idxData = 8;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A141
            label = new Label(14, 337, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 338, kategoriA14, StringCellFormat10pt);
        sheet.addCell(label);

        //set nilai kategori A1 berdasarkan nilai A14
        if (kategoriA14.equals("LS")) {
            kategoriA1 = "LS";
        }

        //memasukkan kategori A1
        label = new Label(14, 339, kategoriA1, StringCellFormat11pt);
        sheet.addCell(label);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA21(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //set font String untuk Rekomendasi
            WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
            font10pt.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat StringRekomendasi = new WritableCellFormat();
            StringRekomendasi.setFont(font);
            StringRekomendasi.setAlignment(Alignment.JUSTIFY);
            StringRekomendasi.setVerticalAlignment(VerticalAlignment.TOP);
            StringRekomendasi.setWrap(true);
            StringRekomendasi.setBorder(Border.ALL, BorderLineStyle.THIN);

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a ()
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 345;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 346;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 348;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 349;
                    break;
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris, data[0].toString(), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {345, 348};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[2].toString(), data[3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 345, data[4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {345};
            int idxData = 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A21
            label = new Label(14, 350, data[5].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A2 berdasarkan nilai A21
            if (data[5].toString().equals("LS")) {
                kategoriA2 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA22(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a (kerataan jalan, IRI)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 351;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 352;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 353;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 354;
                    break;
            }
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b s.d. g --> (kedalaman lubang) s.d. (intensitas alur)
            int jumData = 8;
            int idxData = 2;
            nomorBaris = 355;

            for (int i = 0; i < jumData; i++) {
                if (nomorBaris == 357 || nomorBaris == 359 || nomorBaris == 363) {
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()), DoubleCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " mm"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                }

                if (nomorBaris == 359) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //masukkan data poin h dan i --> (tekstur perkerasan) dan (aspal yang meleleh)
            jumData = 2;
            idxData = 18;
            nomorBaris = 364;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {351, 360};
            int[] jumlahBaris = {8, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[22].toString(), data[23].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 351, data[24].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {351, 355, 357, 358, 359, 361, 363, 364, 365};
            idxData = 25;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A22
            label = new Label(14, 366, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A2 berdasarkan nilai A22
            if (data[idxData].toString().equals("LS")) {
                kategoriA2 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA23(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, 372, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 372, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (kekuatan konstruksi)
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, 373, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 373, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (drainase permukaan perkerasan jalan)
            if (!data[4].toString().equals("-1")) {
                number = new Number(9, 375, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 375, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d1 (bahan perkerasan)
            if (!data[6].toString().equals("-1")) {
                label = new Label(9, 376, ("(" + data[6].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            //masukkan data poin d2 (bahan perkerasan)
            if (!data[7].toString().equals("-1")) {
                number = new Number(9, 377, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 377, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {372, 375};
            int[] jumlahBaris = {2, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[9].toString(), data[10].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 372, data[11].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {372, 373, 375, 376};
            int idxData = 12;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A23
            label = new Label(14, 378, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 379, kategoriA2, StringCellFormat10pt);
        sheet.addCell(label);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA31(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal;
            int nomorBaris = 0;

            //masukkan data poin a (jalur lalu lintas)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 386;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 387;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 388;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 389;
                    break;
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (jalur pejalan kaki)
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, 390, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 390, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c1 s.d. c6 (konstruksi jembatan)
            int jumData = 6;
            int idxData = 4;
            nomorBaris = 391;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (nomorBaris == 391) {
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
                    nomorBaris = 398;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().equals("-1")) {
                            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                            sheet.addCell(number);
                            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                            sheet.addCell(number);
                        }

                        if (nomorBaris == 399) {
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
                    nomorBaris = 402;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().equals("-1")) {
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
                    nomorBaris = 409;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().equals("-1")) {
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
                    nomorBaris = 422;
                    for (int i = 0; i < jumData; i++) {
                        if (!data[idxData].toString().equals("-1")) {
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
            nomorBaris = 427;

            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {385, 393, 401, 409, 422, 429};
            int[] jumlahBaris = {7, 7, 7, 8, 6, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(),
                data[idxData + 3].toString(), data[idxData + 4].toString(), data[idxData + 5].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            idxData = idxData + 6;      //idxData = 56
            label = new Label(15, 385, data[idxData].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {385, 390, 391, 398, 426};
            idxData = idxData + 1;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A31
            label = new Label(14, 436, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A31
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA32(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            nomorBaris = 437;
            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {437};
            int[] jumlahBaris = {3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            idxData++;
            label = new Label(15, 437, data[idxData].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {437, 438, 439};
            idxData++;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A32
            label = new Label(14, 440, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A32
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA33(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //masukkan data poin a (jumlah per kilometer)
            if (medan.toUpperCase().equals("DATAR (D)")) {
                if (!data[0].toString().equals("-1")) {
                    number = new Number(9, 446, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 446, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

            } else {
                if (!data[2].toString().equals("-1")) {
                    number = new Number(9, 447, Integer.parseInt(data[2].toString()), IntegerCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 447, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

            }

            //masukkan data poin b (fungsi menyalurkan air)
            if (!data[4].toString().equals("-1")) {
                number = new Number(9, 448, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 448, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (kerusakan)
            if (!data[6].toString().equals("-1")) {
                number = new Number(9, 450, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 450, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {446, 449};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[8].toString(), data[9].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 446, data[10].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {446, 448, 450};
            int idxData = 11;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A33
            label = new Label(14, 451, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A33
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA34(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, 452, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 452, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (ketergangguan arus lalu lintas akibat aktivitas parkir)
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, 453, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 453, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (lebar lajur lalu lintas  efektif)
            if (!data[4].toString().equals("-1")) {
                label = new Label(9, 456, (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 456, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {452, 455};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[6].toString(), data[7].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 452, data[8].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {452, 453, 456};
            int idxData = 9;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A34
            label = new Label(14, 457, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A34
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA35(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            nomorBaris = 463;
            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 1) {
                    nomorBaris += 3;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {463, 466};
            int[] jumlahBaris = {2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 463, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {463, 464, 467};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A35
            label = new Label(14, 468, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A3 berdasarkan nilai A35
            if (data[idxData].toString().equals("LS")) {
                kategoriA3 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA36(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, 469, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 469, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[2].toString().equals("-1")) {
                label = new Label(9, 470, ("(" + data[2].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            //masukkan data poin b1, b2, b3 (kemiringan ke arah aliran)
            int jumData = 3;
            int idxData = 3;
            int nomorBaris = 471;
            for (int i = 0; i < jumData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin c (bahan dinding saluran)
            if (!data[9].toString().equals("-1")) {
                label = new Label(9, 474, ("(" + data[9].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            if (!data[10].toString().equals("-1")) {
                number = new Number(9, 476, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 476, Double.parseDouble(data[11].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin d (tertutup/ terbuka sesuai lingkungan)
            if (!data[12].toString().equals("-1")) {
                number = new Number(9, 477, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 477, Double.parseDouble(data[13].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {469, 475};
            int[] jumlahBaris = {5, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[14].toString(), data[15].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 469, data[16].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {469, 471, 474, 477};
            idxData = 17;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A36
            label = new Label(14, 478, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 479, kategoriA3, StringCellFormat11pt);
        sheet.addCell(label);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA41(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a1 (lebar dan tinggi)
            String lebarJalur = data[0].toString();
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    switch (lebarJalur.toUpperCase()) {
                        case "2 X 14 M":
                            nomorBaris = 485;
                            break;
                        case "2 X 11 M":
                            nomorBaris = 486;
                            break;
                        case "2 X 7 M":
                            nomorBaris = 487;
                            break;
                        default:
                            break;
                    }
                    break;
                case "JALAN RAYA (JR)":
                    switch (lebarJalur.toUpperCase()) {
                        case "2 X 14 M":
                            nomorBaris = 488;
                            break;
                        case "2 X 11 M":
                            nomorBaris = 489;
                            break;
                        case "2 X 7 M":
                            nomorBaris = 491;
                            break;
                        default:
                            break;
                    }
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 492;
                    break;
                case "JALAN KECIL (JK)":
                    switch (lebarJalur.toUpperCase()) {
                        case "5,5 M":
                            nomorBaris = 493;
                            break;
                        case "2,5 M":
                            nomorBaris = 494;
                            break;
                    }
                    break;
            }
            if (!data[1].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[1].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a2 (lebar dan tinggi)
            if (!data[3].toString().equals("-1")) {
                label = new Label(9, 495, (data[3].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 495, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a3 (lebar dan tinggi)
            if (!data[5].toString().equals("-1")) {
                label = new Label(9, 496, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 496, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (pemanfaatan rumaja)
            if (!data[7].toString().equals("-1")) {
                number = new Number(9, 498, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 498, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (keselamatan lalu lintas)
            if (!data[9].toString().equals("-1")) {
                number = new Number(9, 499, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 499, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {485, 490, 497};
            int[] jumlahBaris = {4, 6, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[11].toString(), data[12].toString(), data[13].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 485, data[14].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {485, 498, 499};
            int idxData = 15;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A41
            label = new Label(14, 500, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A4 berdasarkan nilai A41
            if (data[idxData].toString().equals("LS")) {
                kategoriA4 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA42(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a (lebar rumija)
            switch (kelasPrasarana.toUpperCase()) {
                case "JALAN BEBAS HAMBATAN (JBH)":
                    nomorBaris = 501;
                    break;
                case "JALAN RAYA (JR)":
                    nomorBaris = 502;
                    break;
                case "JALAN SEDANG (JS)":
                    nomorBaris = 503;
                    break;
                case "JALAN KECIL (JK)":
                    nomorBaris = 504;
                    break;
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (pemanfaata rumija)
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, 505, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 505, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (keberadaan dan tempat utilitas)
            String keberadaan = data[4].toString();
            if (keberadaan.toUpperCase().equals("ANTAR KOTA")) {
                if (!data[5].toString().equals("-1")) {
                    label = new Label(9, 511, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 511, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (!data[7].toString().equals("-1")) {
                    label = new Label(9, 512, (data[7].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 512, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (!data[9].toString().equals("-1")) {
                    number = new Number(9, 513, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, 513, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

            } else {
                if (!data[5].toString().equals("-1")) {
                    label = new Label(9, 515, (data[5].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 515, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (!data[7].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

            }

            //memasukkan gambar
            int[] barisGambar = {501, 511};
            int[] jumlahBaris = {5, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[11].toString(), data[12].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 501, data[13].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {501, 505, 511};
            int idxData = 14;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A42
            label = new Label(14, 516, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A4 berdasarkan nilai A42
            if (data[idxData].toString().equals("LS")) {
                kategoriA4 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA43(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal
            int nomorBaris = 0;

            //masukkan data poin a (lebar ruwasja)
            switch (fungsi.toUpperCase()) {
                case "ARTERI":
                    nomorBaris = 518;
                    break;
                case "KOLEKTOR":
                    nomorBaris = 519;
                    break;
                case "LOKAL":
                    nomorBaris = 520;
                    break;
                case "LINGKUNGAN":
                    nomorBaris = 521;
                    break;
            }
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b1, b2, b3 (pemanfaatan ruwasja)
            if (!data[2].toString().equals("-1")) {
                number = new Number(9, 523, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 523, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[4].toString().equals("-1")) {
                number = new Number(9, 525, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 525, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[6].toString().equals("-1")) {
                number = new Number(9, 526, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 526, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin c (penghalang pandangan pengemudi)
            if (fungsi.toUpperCase().equals("ARTERI")) {
                if (!data[8].toString().equals("-1")) {
                    label = new Label(9, 528, (data[8].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 528, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                if (!data[8].toString().equals("-1")) {
                    label = new Label(9, 529, (data[8].toString() + " m"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, 529, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

            }

            //memasukkan gambar
            int[] barisGambar = {517, 525};
            int[] jumlahBaris = {7, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[10].toString(), data[11].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 517, data[12].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {517, 523, 527};
            int idxData = 13;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A42
            label = new Label(14, 531, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 532, kategoriA4, StringCellFormat11pt);
        sheet.addCell(label);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA51(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            nomorBaris = 539;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (marka di bagian tikungan)
            jumlahData = 5;
            nomorBaris = 545;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin c (marka persimpangan)
            jumlahData = 7;
            nomorBaris = 552;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
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
            nomorBaris = 560;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {538, 545, 552, 559};
            int[] jumlahBaris = {6, 6, 6, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 538, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {538, 543, 550, 560};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A51
            label = new Label(14, 563, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A51
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA52(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            nomorBaris = 570;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (ketepatan jenis rambu dan penempatannya)
            nomorBaris = 576;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {569, 576};
            int[] jumlahBaris = {6, 6};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 569, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {569, 576};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A52
            label = new Label(14, 582, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A52
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA53(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 584;
            int idxData = 0;
            int jumlahData = 3;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                if (i == 1) {
                    nomorBaris += 3;
                } else {
                    nomorBaris++;
                }
                idxData += 2;
            }

            //masukkan data poin b (bukaan pada separator)
            if (!data[idxData].toString().equals("-1")) {
                number = new Number(9, 589, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 589, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {583, 587};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[8].toString(), data[9].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 583, data[10].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {583, 589};
            idxData = 11;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A53
            label = new Label(14, 590, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A53
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA54(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 0;
            int jumlahData = 0;
            int idxData = 0;

            //masukkan data poin a (kebutuhan manajemen lalu lintas) 
            nomorBaris = 597;
            jumlahData = 3;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (bentuk pulau jalan)
            if (!data[idxData].toString().equals("-1")) {
                label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
            }

            //masukkan data poin c (marka), d (warna kerb), dan e (rambu pengarah)
            nomorBaris = 601;
            jumlahData = 6;
            idxData++;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
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
            int[] barisGambar = {596, 603};
            int[] jumlahBaris = {6, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 596, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {596, 601, 606, 607};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A54
            label = new Label(14, 608, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A54
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA55(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            if (!data[0].toString().equals("-1")) {
                label = new Label(9, 610, ("(" + data[0].toString() + ")"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, 610, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (perkerasan dan kondisi trotoar), c (pemanfaatan oleh selain pejalan kaki), dan d (utilitas pada trotoar)
            nomorBaris = 611;
            jumlahData = 4;
            idxData = 2;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
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
            int[] barisGambar = {609, 613};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 609, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {609, 611, 614, 615};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A55
            label = new Label(14, 616, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A55
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA56(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 622;
            int jumlahData = 7;
            int idxData = 0;

            //masukkan data semua poin
            for (int i = 0; i < jumlahData; i++) {
                if (i == 4) {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                    }

                } else {
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                }

                if (i == 3) {
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
            int[] barisGambar = {622, 626};
            int[] jumlahBaris = {3, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[13].toString(), data[14].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 622, data[15].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {622, 625, 628, 629};
            idxData = 16;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A56
            label = new Label(14, 630, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A5 berdasarkan nilai A56
            if (data[idxData].toString().equals("LS")) {
                kategoriA5 = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA57(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 0;
            int jumlahData = 0;
            int idxData = 0;

            //masukkan data poin a (kebutuhan manajemen lalu lintas)
            nomorBaris = 632;
            jumlahData = 4;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //masukkan data poin b (rambu dan marka), c (APILL), dan d (perlindungan bagi pejalan kaki)
            nomorBaris = 636;
            jumlahData = 5;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat8pt);    //hasil uji
                    sheet.addCell(label);
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
            int[] barisGambar = {631, 637};
            int[] jumlahBaris = {5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 631, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {631, 636, 640, 641};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A57
            label = new Label(14, 642, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 643, kategoriA5, StringCellFormat11pt);
        sheet.addCell(label);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6a1(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal;
            int nomorBaris = 0;
            int jumlahData = 0;
            int idxData = 0;

            //masukkan data poin a1 (garis sumbu) s.d. a10 (Yellow Box)
            jumlahData = 25;
            if (kecepatan <= 60) {
                nomorBaris = 650;
            } else {
                nomorBaris = 651;
            }

            for (int i = 0; i < jumlahData; i++) {
                if (i <= 1) {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                    nomorBaris += 2;
                } else {
                    if (!data[idxData].toString().equals("-1")) {
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
            nomorBaris = 678;
            jumlahData = 4;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {649, 658, 667, 677};
            int[] jumlahBaris = {8, 8, 9, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 649, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {649, 681};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a1
            label = new Label(14, 682, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a1
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6a2(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal
            int nomorBaris;
            int jumlahData;
            int idxData = 0;

            //masukkan data poin a1 (ukuran daun rambu)
            if (kecepatan <= 30) {
                nomorBaris = 689;
            } else if (kecepatan <= 60) {
                nomorBaris = 690;
            } else if (kecepatan <= 80) {
                nomorBaris = 691;
            } else {
                nomorBaris = 692;
            }
            if (!data[idxData].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[0].toString() + " mm"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin a2 (warna) dan b1 (posisi)
            nomorBaris = 693;
            jumlahData = 8;
            idxData = 2;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
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
            nomorBaris = 703;
            jumlahData = 8;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
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
            nomorBaris = 712;
            jumlahData = 2;
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    sheet.addCell(number);
                    number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    sheet.addCell(number);
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {688, 695, 700, 705};
            int[] jumlahBaris = {6, 4, 4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 688, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {688, 699, 710};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a2
            label = new Label(14, 714, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a2
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6a3(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //masukkan poin a (bentuk dan ukuran separator)
            if (!data[0].toString().equals("-1")) {
                number = new Number(9, 720, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 720, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            int nomorBaris = 0;
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 722;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 725;
            }
            if (!data[2].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[4].toString().equals("-1")) {
                label = new Label(9, (nomorBaris + 1), (data[4].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris + 1), Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //masukkan data poin b (letak dan ukuran bukaan)
            if (!data[6].toString().equals("-1")) {
                number = new Number(9, 728, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
                sheet.addCell(number);
                number = new Number(10, 728, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            nomorBaris = 0;
            if (fungsi.toUpperCase().equals("ARTERI")) {
                nomorBaris = 730;
            } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                nomorBaris = 733;
            }
            if (!data[8].toString().equals("-1")) {
                label = new Label(9, nomorBaris, (data[8].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            if (!data[10].toString().equals("-1")) {
                label = new Label(9, (nomorBaris + 1), (data[10].toString() + " m"), StringCellFormat8pt);    //hasil uji
                sheet.addCell(label);
                number = new Number(10, (nomorBaris + 1), Double.parseDouble(data[11].toString()) / 100, PercentCellFormat);   //deviasi
                sheet.addCell(number);
            }

            //memasukkan gambar
            int[] barisGambar = {720, 728};
            int[] jumlahBaris = {7, 7};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[12].toString(), data[13].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 720, data[14].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {720, 728};
            int idxData = 15;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a3
            label = new Label(14, 735, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a3
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6a4(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        Object[] data = koneksi.getDataA6a4(idSegmen);

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
            int nomorBaris = 736;
            int jumlahData = 16;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 7) {
                    if (fungsi.toUpperCase().equals("ARTERI")) {
                        nomorBaris = 745;
                    } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
                        nomorBaris = 747;
                    }

                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                    nomorBaris = 748;   //lompat ke baris 749 di excel
                    idxData += 2;
                } else {
                    if (!data[idxData].toString().equals("-1")) {
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
            int[] barisGambar = {736, 747};
            int[] jumlahBaris = {10, 8};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 736, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {736, 737, 738};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a4
            label = new Label(14, 756, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a4
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6a5(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 762;
            int jumlahData = 10;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i <= 2) {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else if (i == 4) {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " cm"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else {
                    if (!data[idxData].toString().equals("-1")) {
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
            int[] barisGambar = {762, 769};
            int[] jumlahBaris = {6, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 762, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {762, 765, 767, 772};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a5
            label = new Label(14, 773, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a5
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6a6(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 774;
            int jumlahData = 9;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 0 || i >= 7) {
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {774, 779};
            int[] jumlahBaris = {4, 4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 774, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {774, 780, 781, 782};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a6
            label = new Label(14, 783, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6a berdasarkan nilai A6a6
            if (data[idxData].toString().equals("LS")) {
                kategoriA6a = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6a7(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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

            //inisialisasi variabel lokal
            int nomorBaris = 789;
            int jumlahData = 19;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i <= 2 || (i >= 11 && i <= 13) || (i >= 16)) {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
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
                                nomorBaris = 798;
                                break;
                            case "KOLEKTOR":
                                nomorBaris = 799;
                                break;
                            case "LOKAL":
                                nomorBaris = 800;
                                break;
                        }
                    }

                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
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
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {789, 795, 801, 807};
            int[] jumlahBaris = {5, 5, 5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 789, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {789, 790, 792, 793, 802, 805};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6a7
            label = new Label(14, 813, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 814, kategoriA6a, StringCellFormat11pt);
        sheet.addCell(label);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b1(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 820;
            int jumlahData = 6;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 2) {
                    //meter
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }

                } else {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 3) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {820, 824};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 820, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {820, 821, 826};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b1
            label = new Label(14, 827, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b1
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b2(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 828;
            int jumlahData = 9;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 1 || i == 2 || i == 3 || i == 5) {
                    //meter
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
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
            int[] barisGambar = {828, 834};
            int[] jumlahBaris = {5, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 828, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {828, 829, 838};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b2
            label = new Label(14, 839, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b2
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b3(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 845;
            int jumlahData = 8;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 1 || i == 2 || i == 4) {
                    //meter
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 3) {
                    nomorBaris += 3;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {845, 850};
            int[] jumlahBaris = {4, 5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 845, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {845, 846, 854};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b3
            label = new Label(14, 855, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b3
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b4(String idSegmen) throws WriteException, IOException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 856;
            int jumlahData = 6;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 1 || i == 2) {
                    //meter
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 3) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {856, 860};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 856, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {856, 862};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b4
            label = new Label(14, 863, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b4
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b5(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 869;
            int jumlahData = 5;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 0) {
                    //meter
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {869};
            int[] jumlahBaris = {5};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 869, data[idxData + 1].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {869, 873};
            idxData += 2;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b5
            label = new Label(14, 874, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b5
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b6(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 875;
            int jumlahData = 4;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i == 2) {
                    //meter
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                nomorBaris++;
                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {875};
            int[] jumlahBaris = {4};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 875, data[idxData + 1].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {875, 876};
            idxData += 2;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b6
            label = new Label(14, 879, data[idxData].toString(), StringCellFormat8pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b6
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b7(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 880;
            int jumlahData = 5;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (!data[idxData].toString().equals("-1")) {
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

            //memasukkan gambar
            int[] barisGambar = {880, 884};
            int[] jumlahBaris = {3, 3};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 880, data[idxData + 2].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {880, 881, 885};
            idxData += 3;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b7
            label = new Label(14, 887, data[idxData].toString(), StringCellFormat9pt);
            sheet.addCell(label);

            //set nilai kategori A6b berdasarkan nilai A6b7
            if (data[idxData].toString().equals("LS")) {
                kategoriA6b = "LS";
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertDataA6b8(String idSegmen) throws WriteException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
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
            int nomorBaris = 893;
            int jumlahData = 8;
            int idxData = 0;

            //masukkan semua data
            for (int i = 0; i < jumlahData; i++) {
                if (i <= 3) {
                    //meter
                    if (!data[idxData].toString().equals("-1")) {
                        label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat8pt);    //hasil uji
                        sheet.addCell(label);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                } else {
                    //persen
                    if (!data[idxData].toString().equals("-1")) {
                        number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                        sheet.addCell(number);
                        number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                        sheet.addCell(number);
                    }
                }

                if (i == 2 || i == 4 || i == 6) {
                    nomorBaris += 2;
                } else {
                    nomorBaris++;
                }

                idxData += 2;
            }

            //memasukkan gambar
            int[] barisGambar = {893, 896, 899, 902};
            int[] jumlahBaris = {2, 2, 2, 2};
            int jumlahGambar = barisGambar.length;
            String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
            for (int i = 0; i < jumlahGambar; i++) {
                //Download image from server
                if (!imagePath[i].equals("empty")) {
                    if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
                    {
                        imagePath[i] = imagePath[i].replace(" ", "%20");
                    }

                    System.out.println(imagePath[i]);

                    InputStream img = new URL(imagePath[i]).openStream();
                    Path path = Paths.get(localPath + idxGambar + ".png");
                    Files.copy(img, path, StandardCopyOption.REPLACE_EXISTING);
                    idxGambar++;

                    WritableImage image = new WritableImage(
                            12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                            1, jumlahBaris[i], //ukuran image dalam satuan cell
                            new File(path.toString()));           //file image
                    sheet.addImage(image);
                }
            }

            //memasukkan rekomendasi/ catatatan
            label = new Label(15, 893, data[idxData + 4].toString(), StringRekomendasi);
            sheet.addCell(label);

            //memasukkan kategori per poin
            int[] barisKategori = {893, 903};
            idxData += 5;
            for (int i = 0; i < barisKategori.length; i++) {
                label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat8pt);
                sheet.addCell(label);
                idxData++;
            }

            //memasukkan kategori A6b8
            label = new Label(14, 904, data[idxData].toString(), StringCellFormat9pt);
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
        label = new Label(14, 905, kategoriA6b, StringCellFormat11pt);
        sheet.addCell(label);
    }
}
