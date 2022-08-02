package com.pratama.kelayakanjalan;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Math.abs;
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

public final class CreateExcel {

    private WritableCellFormat StringCellFormat;
    private WritableCellFormat DoubleCellFormat;
    private WritableCellFormat IntegerCellFormat;
    private WritableCellFormat PercentCellFormat;
    private WritableCellFormat Font9CellFormat;
    private WritableCellFormat Font10CellFormat;
    private WritableCellFormat Font11CellFormat;

    //Inisialisasi variabel input data uji dan deviasi
    private Number number;
    private Label label;
    private final String sistemJaringan, status, fungsi, kelasPrasarana, kelasPenggunaan, medan;
    private int idxGambar = 1;
    private static WritableWorkbook fileXLS;
    private static WritableSheet sheet;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CreateExcel(String idSegmen, String filePath, String NRU, String SEG) throws IOException, BiffException, WriteException {
        //buka template dokumen
        //String filePath = "D:/Documents/Jobs/IT/Aplikasi Uji Laik Fungsi Jalan/";
        Workbook template = Workbook.getWorkbook(new File(filePath));

        //buat copy template
        String segmen = "Segmen " + idSegmen;
        fileXLS = Workbook.createWorkbook(new File(filePath), template);
        sheet = fileXLS.getSheet(0); //Sheet1 = 0

        //memformat CellFormat via method
        setStringCellFormat();
        setIntegerCellFormat();
        setDoubleCellFormat();
        setPercentCellFormat();
        setFont9CellFormat();
        setFont10CellFormat();
        setFont11CellFormat();

        //ambil data klasifikasi jalan dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        String[] dataKlasifikasi = koneksi.getKlasifikasiJalan(idSegmen);
        sistemJaringan = dataKlasifikasi[0];
        status = dataKlasifikasi[1];
        fungsi = dataKlasifikasi[2];
        kelasPrasarana = dataKlasifikasi[3];
        kelasPenggunaan = dataKlasifikasi[4];
        medan = dataKlasifikasi[5];

        insertIdentitasRuas(idSegmen);
        insertKlasifikasiJalan();
        insertSegmen(idSegmen);
        insertDataA111(idSegmen, NRU, SEG);
        //insertDataA112(idSegmen);

        //Eksekusi file excel
        fileXLS.write();
        fileXLS.close();
        Log.e("Excel", "Hallo export suksessss");
    }

    private void setStringCellFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
        StringCellFormat = new WritableCellFormat();

        StringCellFormat.setFont(font);
        StringCellFormat.setAlignment(Alignment.CENTRE);
        StringCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat.setWrap(true);
        StringCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
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
        NumberFormat doubleFormat = new NumberFormat("0.0%");
        DoubleCellFormat = new WritableCellFormat(doubleFormat);

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

    private void setFont9CellFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 9);
        font.setBoldStyle(WritableFont.BOLD);
        StringCellFormat = new WritableCellFormat();

        StringCellFormat.setFont(font);
        StringCellFormat.setAlignment(Alignment.CENTRE);
        StringCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat.setWrap(true);
        StringCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    }

    private void setFont10CellFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 10);
        font.setBoldStyle(WritableFont.BOLD);
        StringCellFormat = new WritableCellFormat();

        StringCellFormat.setFont(font);
        StringCellFormat.setAlignment(Alignment.CENTRE);
        StringCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat.setWrap(true);
        StringCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
    }

    private void setFont11CellFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
        font.setBoldStyle(WritableFont.BOLD);
        StringCellFormat = new WritableCellFormat();

        StringCellFormat.setFont(font);
        StringCellFormat.setAlignment(Alignment.CENTRE);
        StringCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat.setWrap(true);
        StringCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
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
    public void insertDataA111(String idSegmen, String NRU, String SEG) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        Object[] data = koneksi.getDataA111(idSegmen);

        //set font
        WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
        WritableCellFormat StringCellFormat1 = new WritableCellFormat();

        StringCellFormat1.setFont(font);
        StringCellFormat1.setAlignment(Alignment.CENTRE);
        StringCellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
        StringCellFormat1.setWrap(true);
        StringCellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
        
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
        label = new Label(9, nomorBaris, data[0].toString(), StringCellFormat1);
        sheet.addCell(label);

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
        number = new Number(9, nomorBaris, Integer.parseInt(data[1].toString()), IntegerCellFormat);    //hasil uji
        sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
        sheet.addCell(number);

        //memasukkan poin c (Jumlah lajur)
        if (fungsi.toUpperCase().equals("ARTERI")) {
            nomorBaris = 35;
        } else {
            nomorBaris = 36;
        }
        number = new Number(9, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);    //hasil uji
        sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
        sheet.addCell(number);

        //memasukkan poin d (Lebar setiap lajur)
        int LHRT = Integer.parseInt(data[1].toString());     //ambil data LHRT (smp/hari) dari data ke-1 poin b
        if (LHRT > 17000) {
            nomorBaris = 39;
        } else if (LHRT >= 3000) {
            nomorBaris = 40;
        } else {
            nomorBaris = 41;
        }
        label = new Label(9, nomorBaris, (data[5].toString() + " m"), StringCellFormat);    //hasil uji
        sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
        sheet.addCell(number);

        //memasukkan poin e (keseragaman lebar lajur)
        number = new Number(9, 42, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
        sheet.addCell(number);
        number = new Number(10, 42, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
        sheet.addCell(number);

        //memasukkan poin f (kemiringan melintang)
        number = new Number(9, 43, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
        sheet.addCell(number);
        number = new Number(10, 43, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
        sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {20, 29, 38};
        int[] jumlahBaris = {8, 8, 6};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[11].toString(), data[12].toString(), data[13].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            //Download image from server
            if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
            {
                imagePath[i] = imagePath[i].replace(" ", "%20");
            }

            System.out.println(imagePath[i]);

            InputStream img = new URL(imagePath[i]).openStream();

            File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU+"/Segmen "+SEG+"/Pictures/A111");
            if(!folder.exists()){
                folder.mkdirs();
            }

            File mediaStorageDir = null;
            String state = Environment.getExternalStorageState();

            if (state.contains(Environment.MEDIA_MOUNTED)) {
                mediaStorageDir = new File(Environment
                        .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU+"/Segmen "+SEG+"/Pictures/A111/image"+idxGambar+".png");
            } else {
                mediaStorageDir = new File(Environment
                        .getExternalStorageDirectory().toString() + "/Kelaikan Jalan/Download/"+NRU+"/Segmen "+SEG+"/Pictures/A111/image"+idxGambar+".png");
            }

            Path localPath = Paths.get(mediaStorageDir.getAbsolutePath());
            Files.copy(img, localPath, StandardCopyOption.REPLACE_EXISTING);
            idxGambar++;

            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(localPath.toString()));           //file image
            sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 20, data[14].toString(), StringCellFormat1);
        sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {29, 34, 37, 42, 43};
        int idxData = 15;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(14, barisKategori[i], data[idxData].toString(), StringCellFormat1);
            sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A111
        label = new Label(14, 44, data[idxData].toString(), StringCellFormat1);
        sheet.addCell(label);
    }

    /*public void insertDataA112(String idSegmen) throws WriteException, MalformedURLException, IOException {
        //ambil data dari DB
        KoneksiDB2 koneksi = new KoneksiDB2();
        Object[] data = koneksi.getDataA112(idSegmen);

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
        number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()), DoubleCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (keseragaman levar bahu)
        number = new Number(9, 51, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 51, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (perkerasan bahu)
        number = new Number(9, 58, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 58, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin d (posisi muka bahu thd muka jalan)
        number = new Number(9, 59, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 59, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin e (kemiringan melintang bahu)
        if (kelasPrasarana.toUpperCase().equals("JALAN BEBAS HAMBATAN (JBH)")) {
            number = new Number(9, 60, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, 60, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        } else {
            number = new Number(9, 61, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, 61, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        }

        //memasukkan gambar
        int[] barisGambar = {45, 58};
        int[] jumlahBaris = {7, 4};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[10].toString(), data[11].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            //Download image from server
            if (imagePath[i].contains(" ")) //check if the URL contains white spaces, if it exists, then replace with "%20"
            {
                imagePath[i] = imagePath[i].replace(" ", "%20");
            }

            System.out.println(imagePath[i]);

            InputStream img = new URL(imagePath[i]).openStream();
            Path localPath = Paths.get("D:/Documents/Jobs/IT/Aplikasi Uji Laik Fungsi Jalan/image" + idxGambar + ".png");
            Files.copy(img, localPath, StandardCopyOption.REPLACE_EXISTING);
            idxGambar++;

            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 45, data[12].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {45, 51, 58, 59, 60};
        int idxData = 13;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A112
        label = new Label(15, 62, data[idxData].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA113(String fungsi, Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;

        //masukkan data poin a (lebar median)
        String median = data[0].toString();
        if (median.toUpperCase().equals("MEDIAN DATAR")) {
            nomorBaris = 64;
        } else if (median.toUpperCase().equals("MEDIAN DITINGGIKAN")) {
            nomorBaris = 65;
        } else {
            nomorBaris = 66;
        }
        label = new Label(9, nomorBaris, (data[1].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

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
        label = new Label(9, nomorBaris, (data[4].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

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
        number = new Number(9, nomorBaris, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin d (Bukaan pada median)
        if (fungsi.toUpperCase().equals("ARTERI")) {
            nomorBaris = 78;
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            nomorBaris = 79;
        }
        //Jarak antar bukaan
        label = new Label(9, nomorBaris, (data[9].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, (nomorBaris + 2), Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //Lebar bukaan
        label = new Label(9, 81, (data[11].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 81, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {63, 70, 76};
        int[] jumlahBaris = {6, 5, 6};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[13].toString(), data[14].toString(), data[15].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 63, data[16].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {63, 67, 72, 77};
        int idxData = 17;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A113
        label = new Label(15, 82, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA114(Object[] data) throws WriteException {
        //masukkan data poin a (lebar/ dimensi selokan samping)
        label = new Label(9, 89, (data[0].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 89, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (bentuk selokan samping)
        //b1
        label = new Label(9, 90, ("(" + data[2].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);

        //b2
        number = new Number(9, 91, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 91, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (fungsi mengalirkan air)
        number = new Number(9, 93, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 93, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {89, 92};
        int[] jumlahBaris = {2, 2};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[7].toString(), data[8].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 89, data[9].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {89, 90, 93};
        int idxData = 10;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A114
        label = new Label(15, 94, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA115(Object[] data) throws WriteException {
        //masukkan data poin a1 (lebar ambang pengaman)
        label = new Label(9, 95, (data[0].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 95, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin a2 (lebar ambang pengaman)
        label = new Label(9, 96, (data[2].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 96, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (pengamanan konstruksi jalan)
        label = new Label(9, 97, ("(" + data[4].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 97, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {95, 100};
        int[] jumlahBaris = {4, 3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[6].toString(), data[7].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 95, data[8].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {95, 97};
        int idxData = 9;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A115
        label = new Label(15, 103, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA116(Object[] data) throws WriteException {
        //masukkan data poin a1 (Rel pengaman)
        int jumlahPoin = 9;     //fix dari dokumen
        int nomorBaris = 110;   //start dari baris ke 110
        int idxData = 0;

        for (int i = 0; i < jumlahPoin; i++) {
            if (i == 6) {
                label = new Label(9, nomorBaris, (data[idxData].toString()), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 110, data[21].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {110, 116};
        idxData = 22;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A116
        label = new Label(15, 121, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A11
        label = new Label(15, 122, data[idxData + 1].toString(), Font10CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA121(String sistemJaringan, String fungsi, String medanJalan, Object[] data) throws WriteException {
        //inisialisasi variabel lokal;
        int nomorBaris1 = 0;
        int nomorBaris2 = 0;

        //masukkan data poin a (panjang bagian jalan yang lurus)
        if (fungsi.toUpperCase().equals("ARTERI")) {
            switch (medanJalan.toUpperCase()) {
                case "DATAR":
                    nomorBaris1 = 129;
                    break;
                case "BUKIT":
                    nomorBaris1 = 130;
                    break;
                case "GUNUNG":
                    nomorBaris1 = 131;
                    break;
            }
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            switch (medanJalan.toUpperCase()) {
                case "DATAR":
                    nomorBaris1 = 132;
                    break;
                case "BUKIT":
                    nomorBaris1 = 133;
                    break;
                case "GUNUNG":
                    nomorBaris1 = 134;
                    break;
            }
        }
        label = new Label(9, nomorBaris1, (data[0].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris1, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b1 (jarak pandang)
        String jarakPandang = data[2].toString();
        if (fungsi.toUpperCase().equals("ARTERI")) {
            if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                nomorBaris1 = 136;
                nomorBaris2 = 142;
            } else {
                nomorBaris1 = 137;
                nomorBaris2 = 143;
            }
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            if (jarakPandang.toUpperCase().equals("ANTAR KOTA")) {
                nomorBaris1 = 139;
                nomorBaris2 = 144;
            } else {
                nomorBaris1 = 140;
                nomorBaris2 = 145;
            }
        }
        label = new Label(9, nomorBaris1, (data[3].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris1, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b2 (jarak pandang)
        label = new Label(9, nomorBaris2, (data[5].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris2, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c1 (lingkungan jalan)
        label = new Label(9, 146, ("(" + data[7].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);

        //masukkan data poin c2 (lingkungan jalan)
        number = new Number(9, 147, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 147, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {129, 139};
        int[] jumlahBaris = {9, 9};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[10].toString(), data[11].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 129, data[12].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {129, 135, 146};
        int idxData = 13;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A121
        label = new Label(15, 148, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA122(String sistemJaringan, String fungsi, Object[] data) throws WriteException {
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
        label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (superelevansi)
        if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
            nomorBaris = 155;
        } else {
            nomorBaris = 157;
        }

        number = new Number(9, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (jarak pandang)
        if (fungsi.toUpperCase().equals("ARTERI")) {
            nomorBaris = 159;
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            nomorBaris = 160;
        }

        label = new Label(9, nomorBaris, (data[4].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {149, 156};
        int[] jumlahBaris = {6, 6};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[6].toString(), data[7].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 149, data[8].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {149, 155, 158};
        int idxData = 9;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A122
        label = new Label(15, 162, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA123(String sistemJaringan, String fungsi, String kelasPrasarana, Object[] data) throws WriteException {
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
        number = new Number(9, nomorBaris, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (cara akses je jalan utama)
        label = new Label(9, 183, ("(" + data[2].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 183, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {169, 175, 182};
        int[] jumlahBaris = {5, 6, 5};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[4].toString(), data[5].toString(), data[6].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 169, data[7].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {169, 183};
        int idxData = 8;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A123
        label = new Label(15, 187, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA124(String sistemJaringan, String fungsi, String kelasPrasarana, Object[] data) throws WriteException {
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
        number = new Number(9, nomorBaris, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (akses ke jalan utama)
        label = new Label(9, 202, ("(" + data[2].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 202, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

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
        number = new Number(9, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {188, 202, 206, 209};
        int[] jumlahBaris = {8, 3, 2, 2};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[5].toString(), data[6].toString(), data[7].toString(), data[8].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 188, data[9].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {188, 202, 205};
        int idxData = 10;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A124
        label = new Label(15, 211, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A12
        label = new Label(15, 212, data[idxData + 1].toString(), Font10CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA131(String sistemJaringan, String fungsi, String medanJalan, Object[] data)
            throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;

        //masukkan data poin a1 (kelandaian memanjang)
        if (sistemJaringan.toUpperCase().equals("PRIMER (ANTAR KOTA)")) {
            switch (medanJalan.toUpperCase()) {
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
            switch (medanJalan.toUpperCase()) {
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
        number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin a2 (kelandaian memanjang)
        int tempData = Integer.parseInt(data[0].toString());

        if (fungsi.toUpperCase().equals("SEKUNDER (DALAM KOTA)")) {
            int mulaiBaris = 226;
            for (int i = 4; i <= 10; i++) {
                if (i == tempData) {
                    nomorBaris = mulaiBaris + (tempData - 4);
                    break;
                }
            }
        } else {
            int mulaiBaris = 235;
            for (int i = 4; i <= 10; i++) {
                if (i == tempData) {
                    nomorBaris = mulaiBaris + (tempData - 4);
                    break;
                }
            }
        }
        label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

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
        number = new Number(9, nomorBaris, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, nomorBaris1, (data[7].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris1, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, nomorBaris2, (data[9].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris2, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (lingkungan jalan)
        label = new Label(9, 256, ("(" + data[11].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);

        number = new Number(9, 257, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 257, Double.parseDouble(data[13].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {219, 229, 239, 249};
        int[] jumlahBaris = {9, 9, 9, 9};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[14].toString(), data[15].toString(), data[16].toString(), data[17].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 219, data[18].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {219, 242, 256};
        int idxData = 19;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A131
        label = new Label(15, 219, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA132(String kelasPrasarana, Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;

        //masukkan data poin a (keperluan keberadaannya)
        label = new Label(9, 265, ("(" + data[0].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 265, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

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
        label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b2 (Lebar dan panjang lajur)
        int jumData = 5;
        int idxData = 4;
        nomorBaris = 270;

        for (int i = 0; i < jumData; i++) {
            label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //masukkan data poin c (Taper masuk dan keluar lajur)
        label = new Label(9, 276, (data[14].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 276, Double.parseDouble(data[15].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {265, 271};
        int[] jumlahBaris = {5, 6};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[16].toString(), data[17].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 265, data[18].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {265, 266, 276};
        idxData = 19;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A132
        label = new Label(15, 277, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA133(String fungsi, Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;
        int nomorBaris1 = 0;
        int nomorBaris2 = 0;

        //masukkan data poin a1 (Ketajaman lengkungan)
        number = new Number(9, 278, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 278, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin a2,a3,a4 (ketajaman lengkungan)
        if (fungsi.toUpperCase().equals("ARTERI")) {
            nomorBaris = 280;
            nomorBaris = 281;
            nomorBaris = 282;
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            nomorBaris = 284;
            nomorBaris = 286;
            nomorBaris = 287;
        }
        number = new Number(9, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, nomorBaris1, (data[4].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris1, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, nomorBaris2, (data[6].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris2, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

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
        number = new Number(9, nomorBaris, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, nomorBaris1, (data[11].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris1, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, nomorBaris2, (data[13].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris2, Double.parseDouble(data[14].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c dan d (arah jalan di balik lengkungan) dan (kombinasi lengkung vertikal dan tikungan horizontal)
        int jumData = 7;
        int idxData = 15;
        nomorBaris = 309;

        for (int i = 0; i < jumData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //memasukkan gambar
        int[] barisGambar = {278, 286, 301, 308, 313};
        int[] jumlahBaris = {7, 9, 6, 4, 4};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[29].toString(), data[30].toString(), data[31].toString(), data[32].toString(), data[33].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 278, data[34].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {278, 288, 301, 309, 311};
        idxData = 35;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A133
        label = new Label(15, 317, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A134
        label = new Label(15, 318, data[idxData + 1].toString(), Font10CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA141(Object[] data) throws WriteException {
        //masukkan data poin a (overlaping kurva vertikal pada jalan yang lurus ...)
        number = new Number(9, 325, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 325, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (overlaping kurva vertikal pada begian menikung ...)
        number = new Number(9, 332, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 332, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {325, 328, 334};
        int[] jumlahBaris = {2, 5, 3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[4].toString(), data[5].toString(), data[6].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 325, data[7].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {325, 331};
        int idxData = 8;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A141
        label = new Label(15, 337, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A14
        label = new Label(15, 338, data[idxData + 1].toString(), Font10CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A1
        label = new Label(15, 339, data[idxData + 2].toString(), Font11CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA21(String kelasPrasarana, Object[] data) throws WriteException {
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

        number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {345, 348};
        int[] jumlahBaris = {2, 2};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[2].toString(), data[3].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 345, data[4].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {345};
        int idxData = 5;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A21
        label = new Label(15, 350, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA22(String kelasPrasarana, Object[] data) throws WriteException {
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

        number = new Number(9, nomorBaris, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b s.d. g --> (kedalaman lubang) s.d. (intensitas alur)
        int jumData = 8;
        int idxData = 2;
        nomorBaris = 355;

        for (int i = 0; i < jumData; i++) {
            if (nomorBaris == 357 || nomorBaris == 359 || nomorBaris == 363) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()), DoubleCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                label = new Label(9, nomorBaris, (data[idxData].toString() + " mm"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //memasukkan gambar
        int[] barisGambar = {351, 360};
        int[] jumlahBaris = {8, 6};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[22].toString(), data[23].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 351, data[24].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {351, 355, 357, 358, 359, 361, 363, 364, 365};
        idxData = 25;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A22
        label = new Label(15, 351, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA23(Object[] data) throws WriteException {
        //masukkan data poin a (perlu/ tidak pemeriksaan lebih lanjut)
        number = new Number(9, 372, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 372, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (kekuatan konstruksi)
        number = new Number(9, 373, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 373, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (drainase permukaan perkerasan jalan)
        number = new Number(9, 375, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 375, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin d1 (bahan perkerasan)
        label = new Label(9, 376, ("(" + data[6].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin d2 (bahan perkerasan)
        number = new Number(9, 377, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 377, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {372, 375};
        int[] jumlahBaris = {2, 3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[9].toString(), data[10].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 372, data[11].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {372, 373, 375, 376};
        int idxData = 12;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A23
        label = new Label(15, 378, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A2
        label = new Label(15, 379, data[idxData + 1].toString(), Font11CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA31(String kelasPrasarana, Object[] data) throws WriteException {
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
        label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (jalur pejalan kaki)
        label = new Label(9, 390, (data[2].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 390, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c1 s.d. c6 (konstruksi jembatan)
        int jumData = 6;
        int idxData = 4;
        nomorBaris = 391;

        for (int i = 0; i < jumData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    ExportSQLtoExcel.sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    ExportSQLtoExcel.sheet.addCell(number);

                    if (nomorBaris == 399) {
                        nomorBaris += 2;
                    } else {
                        nomorBaris++;
                    }

                    idxData += 2;
                }
                break;
            case "BETON":
                jumData = 6;
                nomorBaris = 402;
                for (int i = 0; i < jumData; i++) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    ExportSQLtoExcel.sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    ExportSQLtoExcel.sheet.addCell(number);

                    nomorBaris++;
                    idxData += 2;
                }
                break;
            case "BAJA":
                jumData = 8;
                nomorBaris = 409;
                for (int i = 0; i < jumData; i++) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    ExportSQLtoExcel.sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    ExportSQLtoExcel.sheet.addCell(number);

                    nomorBaris++;
                    idxData += 2;
                }
                break;
            case "KAYU":
                jumData = 5;
                nomorBaris = 422;
                for (int i = 0; i < jumData; i++) {
                    number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                    ExportSQLtoExcel.sheet.addCell(number);
                    number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                    ExportSQLtoExcel.sheet.addCell(number);

                    nomorBaris++;
                    idxData += 2;
                }
                break;
        }

        //masukkan data poin e (fasilitas untuk pemeliharaan)
        jumData = 9;
        nomorBaris = 427;

        for (int i = 0; i < jumData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        idxData = idxData + 6;
        label = new Label(15, 385, data[idxData].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {385, 390, 391, 398, 422, 426};
        idxData = idxData + 1;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A31
        label = new Label(15, 436, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA32(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris;
        int jumData;
        int idxData;

        //masukkan data poin a s.d. c
        jumData = 3;
        idxData = 0;
        nomorBaris = 437;
        for (int i = 0; i < jumData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //memasukkan gambar
        int[] barisGambar = {437};
        int[] jumlahBaris = {3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[idxData].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        idxData++;
        label = new Label(15, 437, data[idxData].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {437, 438, 439};
        idxData++;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A32
        label = new Label(15, 440, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA33(String medan, Object[] data) throws WriteException {
        //masukkan data poin a (jumlah per kilometer)
        if (medan.toUpperCase().equals("DATAR (D)")) {
            number = new Number(9, 446, Integer.parseInt(data[0].toString()), IntegerCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, 446, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        } else {
            number = new Number(9, 447, Integer.parseInt(data[2].toString()), IntegerCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, 447, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        }

        //masukkan data poin b (fungsi menyalurkan air)
        number = new Number(9, 448, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 448, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (kerusakan)
        number = new Number(9, 450, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 450, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {446, 449};
        int[] jumlahBaris = {2, 2};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[8].toString(), data[9].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 446, data[10].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {446, 448, 450};
        int idxData = 11;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A33
        label = new Label(15, 451, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA34(Object[] data) throws WriteException {
        //masukkan data poin a (posisinya terhadap jalur lalu lintas)
        number = new Number(9, 452, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 452, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (ketergangguan arus lalu lintas akibat aktivitas parkir)
        number = new Number(9, 453, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 453, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (lebar lajur lalu lintas  efektif)
        label = new Label(9, 456, (data[4].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 456, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {452, 455};
        int[] jumlahBaris = {2, 2};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[6].toString(), data[7].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 452, data[8].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {452, 453, 456};
        int idxData = 9;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A34
        label = new Label(15, 457, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA35(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris;
        int jumData;
        int idxData;

        //masukkan data poin a s.d. c
        jumData = 3;
        idxData = 0;
        nomorBaris = 463;
        for (int i = 0; i < jumData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            if (i == 1) {
                nomorBaris += 2;
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 463, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {463, 464, 467};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A35
        label = new Label(15, 468, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA36(Object[] data) throws WriteException {
        //masukkan data poin a (dimensi dan bentuk saluran)
        number = new Number(9, 469, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 469, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, 470, ("(" + data[2].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);

        //masukkan data poin b1, b2, b3 (kemiringan ke arah aliran)
        int jumData = 3;
        int idxData = 3;
        int nomorBaris = 471;
        for (int i = 0; i < jumData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //masukkan data poin c (bahan dinding saluran)
        label = new Label(9, 474, ("(" + data[9].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);

        number = new Number(9, 476, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 476, Double.parseDouble(data[11].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin d (tertutup/ terbuka sesuai lingkungan)
        number = new Number(9, 477, Double.parseDouble(data[12].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 477, Double.parseDouble(data[13].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {469, 475};
        int[] jumlahBaris = {5, 3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[14].toString(), data[15].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 469, data[16].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {469, 471, 474, 477};
        idxData = 17;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A36
        label = new Label(15, 478, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A3
        label = new Label(15, 479, data[idxData + 1].toString(), Font11CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA41(String kelasPrasarana, Object[] data) throws WriteException {
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
        label = new Label(9, nomorBaris, (data[1].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin a2 (lebar dan tinggi)
        label = new Label(9, 495, (data[3].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 495, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin a3 (lebar dan tinggi)
        label = new Label(9, 496, (data[5].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 496, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (pemanfaatan rumaja)
        number = new Number(9, 498, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 498, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (keselamatan lalu lintas)
        number = new Number(9, 499, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 499, Double.parseDouble(data[10].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {485, 490, 497};
        int[] jumlahBaris = {4, 6, 3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[11].toString(), data[12].toString(), data[13].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 485, data[14].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {485, 498, 499};
        int idxData = 15;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A41
        label = new Label(15, 500, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA42(String kelasPrasarana, String utilitas, Object[] data) throws WriteException {
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
        label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (pemanfaata rumija)
        number = new Number(9, 505, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 505, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (keberadaan dan tempat utilitas)
        if (utilitas.toUpperCase().equals("ANTAR KOTA")) {
            label = new Label(9, 511, (data[4].toString() + " m"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, 511, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            label = new Label(9, 512, (data[6].toString() + " m"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, 512, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            number = new Number(9, 513, Double.parseDouble(data[8].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, 513, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        } else {
            label = new Label(9, 515, (data[4].toString() + " m"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, 515, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            number = new Number(9, nomorBaris, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        }

        //memasukkan gambar
        int[] barisGambar = {501, 511};
        int[] jumlahBaris = {5, 3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[8].toString(), data[9].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 501, data[10].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {501, 505, 511};
        int idxData = 10;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A42
        label = new Label(15, 516, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA43(String fungsi, Object[] data) throws WriteException {
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
        label = new Label(9, nomorBaris, (data[0].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, nomorBaris, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b1, b2, b3 (pemanfaatan ruwasja)
        number = new Number(9, 523, Double.parseDouble(data[2].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 523, Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        number = new Number(9, 525, Double.parseDouble(data[4].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 525, Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        number = new Number(9, 526, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 526, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin c (penghalang pandangan pengemudi)
        if (fungsi.toUpperCase().equals("ARTERI")) {
            label = new Label(9, 528, (data[8].toString() + " m"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, 528, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            label = new Label(9, 529, (data[8].toString() + " m"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, 529, Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);
        }

        //memasukkan gambar
        int[] barisGambar = {517, 525};
        int[] jumlahBaris = {7, 6};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[10].toString(), data[11].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 517, data[12].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {517, 523, 527};
        int idxData = 13;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A42
        label = new Label(15, 531, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A4
        label = new Label(15, 532, data[idxData + 1].toString(), Font11CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA51(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;
        int idxData = 0;
        int jumlahData = 0;

        //masukkan data poin a (marka di bagian lurus)
        jumlahData = 4;
        nomorBaris = 539;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //masukkan data poin b (marka di bagian tikungan)
        jumlahData = 5;
        nomorBaris = 545;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //masukkan data poin c (marka persimpangan)
        jumlahData = 7;
        nomorBaris = 552;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //memasukkan gambar
        int[] barisGambar = {538, 545, 552, 559};
        int[] jumlahBaris = {6, 6, 6, 4};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 538, data[idxData + 4].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {538, 543, 550, 560};
        idxData += 5;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A51
        label = new Label(15, 538, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA52(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;
        int idxData = 0;
        int jumlahData = 0;

        //masukkan data poin a (kebutuhan manajemen lalu lintas)
        jumlahData = 6;
        nomorBaris = 570;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //masukkan data poin b (ketepatan jenis rambu dan penempatannya)
        nomorBaris = 576;
        for (int i = 0; i < jumlahData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //memasukkan gambar
        int[] barisGambar = {569, 576};
        int[] jumlahBaris = {6, 6};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 569, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {569, 576};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A52
        label = new Label(15, 582, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA53(Object[] data) throws WriteException {
        //masukkan data poin a1, a2, a3 (kebutuhan manajemen lalu lintas)
        int nomorBaris = 584;
        int idxData = 0;
        int jumlahData = 3;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            if (i == 1) {
                nomorBaris += 3;
            } else {
                nomorBaris++;
            }
            idxData += 2;
        }

        //masukkan data poin b (bukaan pada separator)
        number = new Number(9, 589, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 589, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {583, 587};
        int[] jumlahBaris = {3, 3};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 583, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {583, 589};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A53
        label = new Label(15, 590, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA54(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;
        int jumlahData = 0;
        int idxData = 0;

        //masukkan data poin a (kebutuhan manajemen lalu lintas) dan b (bentuk pulau jalan)
        nomorBaris = 597;
        jumlahData = 4;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //masukkan data poin c (marka), d (warna kerb), dan e (rambu pengarah)
        nomorBaris = 601;
        jumlahData = 6;
        for (int i = 0; i < jumlahData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 596, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {596, 601, 606, 607};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A54
        label = new Label(15, 608, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA55(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;
        int jumlahData = 0;
        int idxData = 0;

        //masukkan data poin a (kebutuhan manajemen lalu lintas)
        label = new Label(9, 610, ("(" + data[0].toString() + ")"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, 610, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (perkerasan dan kondisi trotoar), c (pemanfaatan oleh selain pejalan kaki), dan d (utilitas pada trotoar)
        nomorBaris = 611;
        jumlahData = 4;
        idxData = 1;
        for (int i = 0; i < jumlahData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 609, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {609, 611, 614, 615};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A55
        label = new Label(15, 616, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA56(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 622;
        int jumlahData = 7;
        int idxData = 0;

        //masukkan data semua poin
        for (int i = 0; i < jumlahData; i++) {
            if (i == 4) {
                label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
            } else {
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
        String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 622, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {622, 625, 628, 629};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A56
        label = new Label(15, 630, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA57(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;
        int jumlahData = 0;
        int idxData = 0;

        //masukkan data poin a (kebutuhan manajemen lalu lintas)
        nomorBaris = 632;
        jumlahData = 4;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //masukkan data poin b (rambu dan marka), c (APILL), dan d (perlindungan bagi pejalan kaki)
        nomorBaris = 636;
        jumlahData = 5;
        for (int i = 0; i < jumlahData; i++) {
            label = new Label(9, nomorBaris, ("(" + data[idxData].toString() + ")"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 631, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {631, 636, 640, 641};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A57
        label = new Label(15, 642, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A5
        label = new Label(15, 643, data[idxData + 1].toString(), Font11CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6a1(double kecepatan, Object[] data) throws WriteException {
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
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);

                nomorBaris += 2;
            } else {
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);

                nomorBaris++;
            }

            idxData += 2;
        }

        //masukkan data poin a11 s.d. a13 dan poin b (kondisi marka)
        nomorBaris = 678;
        jumlahData = 4;
        for (int i = 0; i < jumlahData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //memasukkan gambar
        int[] barisGambar = {649, 658, 667, 677};
        int[] jumlahBaris = {8, 8, 9, 4};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 649, data[idxData + 4].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {649, 681};
        idxData += 5;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6a1
        label = new Label(15, 682, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6a2(double kecepatan, Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 0;
        int jumlahData = 0;
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
        label = new Label(9, nomorBaris, (data[0].toString() + " mm"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, (nomorBaris), Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin a2 (warna) dan b1 (posisi)
        nomorBaris = 693;
        jumlahData = 8;
        idxData = 2;
        for (int i = 0; i < jumlahData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
            label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(label);
            number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

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
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, (nomorBaris), Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            nomorBaris++;
            idxData += 2;
        }

        //memasukkan gambar
        int[] barisGambar = {688, 695, 700, 705};
        int[] jumlahBaris = {6, 4, 4, 4};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[idxData].toString(), data[idxData + 1].toString(), data[idxData + 2].toString(), data[idxData + 3].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 688, data[idxData + 4].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {688, 699, 710};
        idxData += 5;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6a2
        label = new Label(15, 714, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6a3(String fungsi, Object[] data) throws WriteException {
        //masukkan poin a (bentuk dan ukuran separator)
        number = new Number(9, 720, Double.parseDouble(data[0].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 720, Double.parseDouble(data[1].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        int nomorBaris = 0;
        if (fungsi.toUpperCase().equals("ARTERI")) {
            nomorBaris = 722;
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            nomorBaris = 725;
        }
        label = new Label(9, nomorBaris, (data[2].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, (nomorBaris), Double.parseDouble(data[3].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, (nomorBaris + 1), (data[4].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, (nomorBaris + 1), Double.parseDouble(data[5].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //masukkan data poin b (letak dan ukuran bukaan)
        number = new Number(9, 728, Double.parseDouble(data[6].toString()) / 100, PercentCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(number);
        number = new Number(10, 728, Double.parseDouble(data[7].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        nomorBaris = 0;
        if (fungsi.toUpperCase().equals("ARTERI")) {
            nomorBaris = 730;
        } else if (fungsi.toUpperCase().equals("KOLEKTOR")) {
            nomorBaris = 733;
        }
        label = new Label(9, nomorBaris, (data[8].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, (nomorBaris), Double.parseDouble(data[9].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        label = new Label(9, (nomorBaris + 1), (data[10].toString() + " m"), StringCellFormat);    //hasil uji
        ExportSQLtoExcel.sheet.addCell(label);
        number = new Number(10, (nomorBaris + 1), Double.parseDouble(data[11].toString()) / 100, PercentCellFormat);   //deviasi
        ExportSQLtoExcel.sheet.addCell(number);

        //memasukkan gambar
        int[] barisGambar = {720, 728};
        int[] jumlahBaris = {7, 7};
        int jumlahGambar = barisGambar.length;
        String[] imagePath = {data[12].toString(), data[13].toString()};
        for (int i = 0; i < jumlahGambar; i++) {
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 720, data[14].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {720, 728};
        int idxData = 15;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6a3
        label = new Label(15, 735, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6a4(String fungsi, Object[] data) throws WriteException {
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

                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);

                nomorBaris = 748;   //lompat ke baris 749 di excel
                idxData += 2;
            } else {
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);

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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 736, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {736, 737, 738};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6a4
        label = new Label(15, 756, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6a5(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 762;
        int jumlahData = 10;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i <= 2) {
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else if (i == 4) {
                label = new Label(9, nomorBaris, (data[idxData].toString() + " cm"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 762, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {762, 765, 767, 772};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6a5
        label = new Label(15, 773, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6a6(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 774;
        int jumlahData = 9;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i == 0 || i >= 7) {
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 774, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {774, 780, 781, 782};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6a6
        label = new Label(15, 783, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6a7(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 789;
        int jumlahData = 21;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i <= 2 || (i >= 11 && i <= 13) || (i >= 16)) {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //meter 
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            }

            if (i == 5 || i == 10 || i == 14) {
                nomorBaris += 2;
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 789, data[idxData + 4].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {789, 790, 792, 793, 802, 805};
        idxData += 5;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6a7
        label = new Label(15, 813, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A6a
        label = new Label(15, 814, data[idxData + 1].toString(), Font11CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6b1(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 820;
        int jumlahData = 6;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i == 2) {
                //meter
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 820, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {820, 821, 826};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b1
        label = new Label(15, 827, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6b2(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 828;
        int jumlahData = 9;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i == 1 || i == 2 || i == 3 || i == 5) {
                //meter
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 828, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {828, 829, 838};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b2
        label = new Label(15, 839, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6b3(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 845;
        int jumlahData = 8;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i == 1 || i == 2 || i == 4) {
                //meter
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 845, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {845, 846, 854};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b3
        label = new Label(15, 855, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6b4(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 856;
        int jumlahData = 6;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i == 1 || i == 2) {
                //meter
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            }

            if (i == 2) {
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 856, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {856, 862};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b4
        label = new Label(15, 863, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6b5(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 869;
        int jumlahData = 5;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i == 0) {
                //meter
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 869, data[idxData + 1].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {869, 873};
        idxData += 2;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b5
        label = new Label(15, 874, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6b6(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 875;
        int jumlahData = 4;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i == 2) {
                //meter
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 875, data[idxData + 1].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {875, 876};
        idxData += 2;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b6
        label = new Label(15, 879, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertDataA6b7(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 880;
        int jumlahData = 5;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
            ExportSQLtoExcel.sheet.addCell(number);
            number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
            ExportSQLtoExcel.sheet.addCell(number);

            if (i == 2) {
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 880, data[idxData + 2].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {880, 881, 885};
        idxData += 3;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b7
        label = new Label(15, 887, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }

    public void InsertA6b8(Object[] data) throws WriteException {
        //inisialisasi variabel lokal
        int nomorBaris = 893;
        int jumlahData = 8;
        int idxData = 0;

        //masukkan semua data
        for (int i = 0; i < jumlahData; i++) {
            if (i <= 3) {
                //meter
                label = new Label(9, nomorBaris, (data[idxData].toString() + " m"), StringCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(label);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
            } else {
                //persen
                number = new Number(9, nomorBaris, Double.parseDouble(data[idxData].toString()) / 100, PercentCellFormat);    //hasil uji
                ExportSQLtoExcel.sheet.addCell(number);
                number = new Number(10, nomorBaris, Double.parseDouble(data[idxData + 1].toString()) / 100, PercentCellFormat);   //deviasi
                ExportSQLtoExcel.sheet.addCell(number);
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
            WritableImage image = new WritableImage(
                    12, barisGambar[i], //kolom dan baris cell yang ingin diisi image
                    1, jumlahBaris[i], //ukuran image dalam satuan cell
                    new File(imagePath[i]));           //file image
            ExportSQLtoExcel.sheet.addImage(image);
        }

        //memasukkan rekomendasi/ catatatan
        label = new Label(15, 893, data[idxData + 4].toString(), StringCellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori per poin
        int[] barisKategori = {893, 903};
        idxData += 5;
        for (int i = 0; i < barisKategori.length; i++) {
            label = new Label(15, barisKategori[i], data[idxData].toString(), StringCellFormat);
            ExportSQLtoExcel.sheet.addCell(label);
            idxData++;
        }

        //memasukkan kategori A6b8
        label = new Label(15, 904, data[idxData].toString(), Font9CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);

        //memasukkan kategori A6b
        label = new Label(15, 905, data[idxData + 1].toString(), Font11CellFormat);
        ExportSQLtoExcel.sheet.addCell(label);
    }*/
}
