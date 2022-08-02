package com.pratama.kelayakanjalan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAMA_DATABASE = "laik_jalan.db";
    //Daftar nama tabel
    private static final String TABEL_BALAI = "balai";
    private static final String TABEL_PPK = "pkk";
    private static final String TABEL_PRO = "pro";
    private static final String TABEL_KLAS = "klas";
    private static final String TABEL_RUAS = "ruas";
    private static final String TABEL_SEGMEN = "segmen";
    private static final String TABEL_ADMIN = "admin";
    private static final String TABEL_COMPRESS = "compress";
    private static final String TABEL_ASSS = "asss";
    private static final String TABEL_ASSD = "assd";
    private static final String TABEL_ASST = "asst";
    private static final String TABEL_ASSE = "asse";
    private static final String TABEL_ASSL = "assl";
    private static final String TABEL_ASSEN = "assen";
    private static final String TABEL_ASDS = "asds";
    private static final String TABEL_ASDD = "asdd";
    private static final String TABEL_ASDT = "asdt";
    private static final String TABEL_ASDE = "asde";
    private static final String TABEL_ASTS = "asts";
    private static final String TABEL_ASTD = "astd";
    private static final String TABEL_ASTT = "astt";
    private static final String TABEL_ASES = "ases";
    private static final String TABEL_ADS = "ads";
    private static final String TABEL_ADD = "addu";
    private static final String TABEL_ADT = "adt";
    private static final String TABEL_ATS = "ats";
    private static final String TABEL_ATD = "atd";
    private static final String TABEL_ATT = "att";
    private static final String TABEL_ATE = "ate";
    private static final String TABEL_ATL = "atl";
    private static final String TABEL_ATEN = "aten";
    private static final String TABEL_AES = "aes";
    private static final String TABEL_AED = "aed";
    private static final String TABEL_AET = "aet";
    private static final String TABEL_ALS = "als";
    private static final String TABEL_ALD = "ald";
    private static final String TABEL_ALT = "alt";
    private static final String TABEL_ALE = "ale";
    private static final String TABEL_ALL = "alli";
    private static final String TABEL_ALEN = "alen";
    private static final String TABEL_ALTJ = "altj";
    private static final String TABEL_ANAS = "anas";
    private static final String TABEL_ANAD = "anad";
    private static final String TABEL_ANAT = "anat";
    private static final String TABEL_ANAE = "anae";
    private static final String TABEL_ANALI = "anali";
    private static final String TABEL_ANAN = "anan";
    private static final String TABEL_ANATU = "anatu";
    private static final String TABEL_ANBS = "anbs";
    private static final String TABEL_ANBD = "anbd";
    private static final String TABEL_ANBT = "anbt";
    private static final String TABEL_ANBE = "anbe";
    private static final String TABEL_ANBL = "anbl";
    private static final String TABEL_ANBN = "anbn";
    private static final String TABEL_ANBTU = "anbtu";
    private static final String TABEL_ANBDE = "anbde";
    //Atribut tabel balai
    private static final String NAMA_BALAI = "nbl";
    //Atribut tabel PPK
    private static final String ID_PPK = "idpk";
    private static final String NAMA_PPK = "npk";
    //Atribut tabel PPK
    private static final String ID_PROV = "idpro";
    private static final String NAMA_PRO = "npro";
    //Atribut tabel ruas
    private static final String ID_RUAS = "iru";
    private static final String ID_KLAS = "iklas";
    private static final String PENYELENGGARA = "pjl";
    private static final String PKK = "pkk";
    private static final String PRO = "pro";
    private static final String NAMA_RUAS = "nrs";
    private static final String NOMOR_RUAS = "nor";
    private static final String PANJANG_RUAS = "prs";
    private static final String DKM = "dkm";
    private static final String KKM = "kkm";
    private static final String KOTA = "kta";
    private static final String SISTEM_JARINGAN = "sjr";
    private static final String STATUS = "sts";
    private static final String FUNGSI = "fng";
    private static final String KELAS_PRASARANA = "kpr";
    private static final String KELAS_PENGGUNAAN = "kpg";
    private static final String MEDAN_JALAN = "mjl";
    private static final String KECEPATAN = "kcp";
    private static final String HAPUS = "hps";
    //Atribut tabel segmen
    private static final String ID_SEGMEN = "iseg";
    private static final String SEGMEN = "seg";
    private static final String PANJANG_SEGMEN = "psg";
    private static final String DPSG = "dpsg";
    private static final String KPSG = "kpsg";
    //Atribut tabel admin
    private static final String KETERSEDIAAN = "ktd";
    private static final String KELENGKAPAN = "kli";
    private static final String LEGALITAS = "lgt";
    private static final String KETERSEDIAAN2 = "ktd2";
    private static final String KELENGKAPAN2 = "kli2";
    private static final String LEGALITAS2 = "lgt2";
    private static final String KETERSEDIAAN3 = "ktd3";
    private static final String KELENGKAPAN3 = "kli3";
    private static final String LEGALITAS3 = "lgt3";
    private static final String KETERSEDIAAN4 = "ktd4";
    private static final String KELENGKAPAN4 = "kli4";
    private static final String LEGALITAS4 = "lgt4";
    private static final String KETERSEDIAAN5 = "ktd5";
    private static final String KELENGKAPAN5 = "kli5";
    private static final String LEGALITAS5 = "lgt5";
    private static final String KETERSEDIAAN6 = "ktd6";
    private static final String KELENGKAPAN6 = "kli6";
    private static final String LEGALITAS6 = "lgt6";
    //Atribut tabel compres
    private static final String COM_ID_POINT = "id_point";
    private static final String COM_NRU = "nm_ruas";
    private static final String COM_ISEG = "iseg";
    private static final String COM_TABEL = "tabel";
    private static final String COM_DIR_ORI = "dir_ori";
    private static final String COM_DIR_COM = "dir_com";
    private static final String COM_COMPRESS = "compress";
    //Foto
    private static final String DIR_1 = "ft1";
    private static final String DIR_2 = "ft2";
    private static final String DIR_3 = "ft3";
    private static final String DIR_4 = "ft4";
    private static final String DIR_5 = "ft5";
    private static final String DIR_6 = "ft6";
    //Rekomendasi
    private static final String RECOMENDASI = "rec";
    private static final String RECOMENDASI2 = "rec2";
    private static final String RECOMENDASI3 = "rec3";
    private static final String RECOMENDASI4 = "rec4";
    private static final String RECOMENDASI5 = "rec5";
    private static final String RECOMENDASI6 = "rec6";
    //Atribut tabel a111
    private static final String ID_POINT = "id";
    private static final String FUNGSI_JALAN = "fjl";
    private static final String ARUS_DILAYANI = "adl";
    private static final String JUMLAH_LAJUR = "jlr";
    private static final String LEBAR_LAJUR = "lsl";
    private static final String KESERAGAMAN_LAJUR = "klj";
    private static final String KEMIRINGAN_MELINTANG = "kml";
    private static final String UPLOAD = "upl";
    //Atribut tabel a112
    private static final String LEBAR_BAHU = "lbb";
    private static final String KESERAGAMAN_BAHU = "klb";
    private static final String PERKERASAN_BAHU = "pkb";
    private static final String POSISI_BAHU = "pmb";
    private static final String KEMIRINGAN_BAHU = "kmb";
    //Atribut tabel a113
    private static final String LEBAR_MEDIAN_TP = "lbmt";
    private static final String LEBAR_MEDIAN = "lbm";
    private static final String TIPE_MEDIAN_TP = "tpmt";
    private static final String TIPE_MEDIAN = "tpm";
    private static final String PERKERASAN_MEDIAN_TP = "pkmt";
    private static final String PERKERASAN_MEDIAN = "pkm";
    private static final String JARAK_BUKAAN = "jab";
    private static final String LEBAR_BUKAAN = "lbb";
    //Atribut tabel a114
    private static final String LEBAR_SELOKAN = "lss";
    private static final String BENTUK_SELOKAN1 = "bss1";
    private static final String BENTUK_SELOKAN2 = "bss2";
    private static final String FUNGSI_AIR = "fma";
    //Atribut tabel a115
    private static final String LEBAR_AMBANG = "lap";
    private static final String LEBAR_AMBANG2 = "lap2";
    private static final String PENGAMANAN_KONSTRUKSI = "pkj";
    //Atribut tabel a116
    private static final String JARAK_MARKA = "jtj";
    private static final String TINGGI_VERTIKAL = "tvm";
    private static final String KEDALAMAN_VERTIKAL = "ktv";
    private static final String JARAK_VERTIKAL = "jtv";
    private static final String TANDA_REFLEKTOR = "ttr";
    private static final String JARAK_MARKA2 = "jtj2";
    private static final String MUTU_BETON = "mtb";
    private static final String TINGGI_MUKA = "tmt";
    private static final String BAGIAN_TERTANAM = "bgt";
    //atribut tabel a121
    private static final String PANJANG_BAGIAN = "pbj";
    private static final String JARAK_PANDANG_SPIN = "sjpd";
    private static final String JARAK_PANDANG_H = "jph";
    private static final String JARAK_PANDANG_M = "jpm";
    private static final String LINGKUNGAN_JALAN_SPINNER = "slkj";
    private static final String LINGKUNGAN_JALAN = "lkj";
    //atribut tabel a122
    private static final String RADIUS_TIKUNGAN_SPIN = "srdt";
    private static final String RADIUS_TIKUNGAN = "rdt";
    private static final String SUPERELEVASI = "sup";
    private static final String JARAK_PANDANG_ASDD = "jpd";
    //atribut tabel a123
    private static final String JUMLAH_PERSIMPANGAN = "jpk";
    private static final String CARA_AKSES_JU = "scaj";
    //atribut tabel a124
    private static final String BANYAKNYA_AKSES_PERSIL = "bap";
    private static final String AKSES_JALAN_UTAMA = "sajl";
    private static final String BENTUK_AKSES = "bta";
    //atribut tabel a131
    private static final String KELANDAIAN_MEMANJANG = "klm";
    private static final String PANJANG_LANDAI_KRITIS = "plk";
    private static final String JARAK_PANDANG_LANDAI_MAX = "jlm";
    private static final String JARAK_PANDANG_H_ASTS = "jph";
    private static final String JARAK_PANDANG_M_ASTS = "jpm";
    private static final String LINGKUNGAN_JALAN_SPINNER_ASTS = "slkj";
    private static final String LINGKUNGAN_JALAN_ASTS = "lkj";
    //atribut tabel a132
    private static final String KEPERLUAN_KEBERADAAN = "skkb";
    private static final String LEBAR_LAJUR_ASTD = "lbl";
    private static final String PANJANG_AWAL = "pja";
    private static final String PANJANG_SERONGAN_SATU = "pjs";
    private static final String PANJANG_BAGIAN_LURUS = "pjl";
    private static final String PANJANG_SETELAH_PUNCAK = "pjp";
    private static final String PANJANG_SERONGAN_DUA = "pjs2";
    private static final String TAPER_MASUK_KELUAR = "tmk";
    //atribut tabel a133
    private static final String LENGKUNG_PARABOLA = "lkb";
    private static final String KETAJAMAN_LINGKUNGAN = "klm";
    private static final String KETAJAMAN_LINGKUNGAN_CEMBUNG = "kcb";
    private static final String KETAJAMAN_LINGKUNGAN_CEKUNG = "kck";
    private static final String JARAK_PANDANG_SPIN_ASTT = "sjpd";
    private static final String JARAK_PANDANG_ASTT = "jlm";
    private static final String JARAK_PANDANG_H_ASTT = "jph";
    private static final String JARAK_PANDANG_M_ASTT = "jpm";
    private static final String ARAH_JALAN_SATU = "ajl1";
    private static final String ARAH_JALAN_DUA = "ajl2";
    private static final String KOMBINASI_LENGKUNG_VH1 = "kvh1";
    private static final String KOMBINASI_LENGKUNG_VH2 = "kvh2";
    private static final String KOMBINASI_LENGKUNG_VH3 = "kvh3";
    private static final String KOMBINASI_LENGKUNG_VH4 = "kvh4";
    private static final String KOMBINASI_LENGKUNG_VH5 = "kvh5";
    //atribut tabel a141
    private static final String OVERLOADING_KURVA_LURUS = "okvl";
    private static final String OVERLOADING_KURVA_MENIKUNG = "okvm";
    //atribut tabel a21
    private static final String KESESUAIAN_STRUKTUR = "ksp";
    //atribut tabel a22
    private static final String KERATAAN_JALAN = "kjl";
    private static final String KEDALAMAN_LUBANG_D = "kdl";
    private static final String KEDALAMAN_LUBANG_K = "kkl";
    private static final String INTENSITAS_LUBANG = "ibl";
    private static final String LEBAR_RETAK = "lbr";
    private static final String INTENSITAS_RETAK = "irt";
    private static final String KERUSAKAN_ALUR_DANGKAL = "kal";
    private static final String KERUSAKAN_ALUR_DALAM = "kam";
    private static final String INTENSITAS_ALUR = "ial";
    private static final String TEKSTUR_PERKERASAN = "tpk";
    private static final String ASPAL_MELELEH = "asm";
    //atribut tabel a23
    private static final String PEMERIKSAAN_LANJUT = "pll";
    private static final String KEKUATAN_KONSTRUKSI = "kkt";
    private static final String DRAINASE_PERMUKAAN = "dpp";
    private static final String BAHAN_PERKERASAN_SPIN = "sbpk";
    private static final String BAHAN_PERKERASAN = "bpk";
    //atribut tabel a31
    private static final String JALUR_LALU_LINTAS = "jll";
    private static final String JALUR_PEJALAN_KAKI = "jpk";
    private static final String TEGANGAN_IZIN = "tgi";
    private static final String LENDUTAN_IZIN = "ldi";
    private static final String PENURUNAN_IZIN = "pni";
    private static final String LEBAR_RETAK_IZIN = "lbi";
    private static final String GETARAN_IZIN = "gti";
    private static final String KETAHANAN_IZIN = "kti";
    private static final String KERUSAKAN_JEMBATAN_SPIN = "skjb";
    private static final String KERUSAKAN_JEMBATAN = "kjb";
    private static final String KERUSAKAN_JEMBATAN2 = "kjb2";
    private static final String KERUSAKAN_JEMBATAN3 = "kjb3";
    private static final String KERUSAKAN_JEMBATAN4 = "kjb4";
    private static final String KERUSAKAN_JEMBATAN5 = "kjb5";
    private static final String KERUSAKAN_JEMBATAN6 = "kjb6";
    private static final String KERUSAKAN_JEMBATAN7 = "kjb7";
    private static final String KERUSAKAN_JEMBATAN8 = "kjb8";
    private static final String UNIT_MOBIL = "tum";
    private static final String SUMBER_DAYA = "tsd";
    private static final String KELENGKAPAN_KERJA = "tkk";
    private static final String UNIT_PEMELIHARAAN = "tup";
    private static final String UNIT_PENGECATAN = "tup2";
    private static final String UNIT_KONTROL = "tuk";
    private static final String ALAT_BANTU_KERJA = "tbk";
    private static final String TANDA_PENGAMAN_KERJA = "ttp";
    private static final String ALAT_PENGGANTUNG = "tap";
    //atribut tabel a32
    private static final String FUNGSI_ATD = "fng";
    private static final String KONSTRUKSI_PONTON = "ktp";
    private static final String KERUSAKAN_PONTON = "krp";
    //atribut tabel a33
    private static final String JUMLAH_KILOMETER_DATAR = "dtr";
    private static final String JUMLAH_KILOMETER_PEGUNUNGAN = "pgn";
    private static final String FUNGSI_MENYALURKAN_AIR = "fma";
    private static final String KERUSAKAN = "krs";
    //atribut tabel a34
    private static final String POSISI_LAJUR = "ptj";
    private static final String KETERGANGGUAN_ARUS = "kal";
    private static final String LEBAR_LAJUR_ATE = "lle";
    //atribut tabel a35
    private static final String KESTABILAN_KONSTRUKSI = "kkt";
    private static final String KERUSAKAN_EROSI_LONGSOR = "kel";
    private static final String SALURAN_AIR = "sar";
    //atribut tabel a36
    private static final String DIMENSI_DMA = "dma";
    private static final String DIMENSI_SDMA = "sdma";
    private static final String KEMIRINGAN_TANAH = "kat";
    private static final String KEMIRINGAN_KRIKIL = "kak";
    private static final String KEMIRINGAN_BATU = "kab";
    private static final String BAHAN_DINDING_SBDS = "sbds";
    private static final String BAHAN_DINDING_BDS = "bds";
    private static final String TERTUTUP_TERBUKA = "tbl";
    //atribut tabel a41
    private static final String LEBAR_TINGGI_SPIN = "slbt";
    private static final String LEBAR_TINGGI1 = "lbt1";
    private static final String LEBAR_TINGGI2 = "lbt2";
    private static final String LEBAR_TINGGI3 = "lbt3";
    private static final String PEMANFAATAN_RUMAJA = "pfr";
    private static final String KESELAMATAN_LALULINTAS = "kll";
    //atribut tabel a42
    private static final String LEBAR_RUMIJA = "lbr";
    private static final String PEMANFAATAN_RUMIJA_AED = "pfr";
    private static final String KEBERADAAN_UTILITAS_SPIN = "sktu";
    private static final String KEBERADAAN_UTILITAS = "ktu";
    private static final String KEBERADAAN_UTILITAS2 = "ktu2";
    private static final String KEBERADAAN_UTILITAS3 = "ktu3";
    //atribut tabel a43
    private static final String LEBAR_RUWASJA = "lrw";
    private static final String PEMANFAATAN_RUWASJA1 = "prw";
    private static final String PEMANFAATAN_RUWASJA2 = "prw2";
    private static final String PEMANFAATAN_RUWASJA3 = "prw3";
    private static final String PENGHALANG_PANDANGAN_PENGEMUDI = "ppp";
    //atribut tabel a51
    private static final String MARKA_BAGIAN_LURUS1 = "mbl";
    private static final String MARKA_BAGIAN_LURUS2 = "mbl2";
    private static final String MARKA_BAGIAN_LURUS3 = "mbl3";
    private static final String MARKA_BAGIAN_LURUS4 = "mbl4";
    private static final String MARKA_BAGIAN_TIKUNGAN1 = "mbt";
    private static final String MARKA_BAGIAN_TIKUNGAN2 = "mbt2";
    private static final String MARKA_BAGIAN_TIKUNGAN3 = "mbt3";
    private static final String MARKA_BAGIAN_TIKUNGAN4 = "mbt4";
    private static final String MARKA_BAGIAN_TIKUNGAN5 = "mbt5";
    private static final String MARKA_PERSIMPANGAN1 = "mpr";
    private static final String MARKA_PERSIMPANGAN2 = "mpr2";
    private static final String MARKA_PERSIMPANGAN3 = "mpr3";
    private static final String MARKA_PERSIMPANGAN4 = "mpr4";
    private static final String MARKA_PERSIMPANGAN5 = "mpr5";
    private static final String MARKA_PERSIMPANGAN6 = "mpr6";
    private static final String MARKA_PERSIMPANGAN7 = "mpr7";
    private static final String MARKA_BAGIAN_LURUS11 = "mbl11";
    private static final String MARKA_BAGIAN_LURUS22 = "mbl22";
    private static final String MARKA_BAGIAN_LURUS33 = "mbl33";
    private static final String MARKA_BAGIAN_LURUS44 = "mbl44";
    private static final String MARKA_BAGIAN_TIKUNGAN11 = "mbt11";
    private static final String MARKA_BAGIAN_TIKUNGAN22 = "mbt22";
    private static final String MARKA_BAGIAN_TIKUNGAN33 = "mbt33";
    private static final String MARKA_BAGIAN_TIKUNGAN44 = "mbt44";
    private static final String MARKA_BAGIAN_TIKUNGAN55 = "mbt55";
    private static final String MARKA_PERSIMPANGAN11 = "mpr11";
    private static final String MARKA_PERSIMPANGAN22 = "mpr22";
    private static final String MARKA_PERSIMPANGAN33 = "mpr33";
    private static final String MARKA_PERSIMPANGAN44 = "mpr44";
    private static final String MARKA_PERSIMPANGAN55 = "mpr55";
    private static final String MARKA_PERSIMPANGAN66 = "mpr66";
    private static final String MARKA_PERSIMPANGAN77 = "mpr77";
    private static final String ZEBRA_CROSS1 = "zbc";
    private static final String ZEBRA_CROSS2 = "zbc2";
    private static final String ZEBRA_CROSS3 = "zbc3";
    //atribut tabel a52
    private static final String KEBUTUHAN_MANAJEMEN_LL1 = "kml";
    private static final String KEBUTUHAN_MANAJEMEN_LL2 = "kml2";
    private static final String KEBUTUHAN_MANAJEMEN_LL3 = "kml3";
    private static final String KEBUTUHAN_MANAJEMEN_LL4 = "kml4";
    private static final String KEBUTUHAN_MANAJEMEN_LL5 = "kml5";
    private static final String KEBUTUHAN_MANAJEMEN_LL6 = "kml6";
    private static final String KEBUTUHAN_MANAJEMEN_LL11 = "kml11";
    private static final String KEBUTUHAN_MANAJEMEN_LL22 = "kml22";
    private static final String KEBUTUHAN_MANAJEMEN_LL33 = "kml33";
    private static final String KEBUTUHAN_MANAJEMEN_LL44 = "kml44";
    private static final String KEBUTUHAN_MANAJEMEN_LL55 = "kml55";
    private static final String KEBUTUHAN_MANAJEMEN_LL66 = "kml66";
    private static final String KETEPATAN_RAMBU1 = "krp";
    private static final String KETEPATAN_RAMBU2 = "krp2";
    private static final String KETEPATAN_RAMBU3 = "krp3";
    private static final String KETEPATAN_RAMBU4 = "krp4";
    private static final String KETEPATAN_RAMBU5 = "krp5";
    private static final String KETEPATAN_RAMBU6 = "krp6";
    //atribut tabel a53
    private static final String KEBUTUHAN_MANAJEMEN_LL_ALT = "kml";
    private static final String KEBUTUHAN_MANAJEMEN_LL2_ALT = "kml2";
    private static final String KEBUTUHAN_MANAJEMEN_LL3_ALT = "kml3";
    private static final String KEBUTUHAN_MANAJEMEN_LL11_ALT = "kml11";
    private static final String KEBUTUHAN_MANAJEMEN_LL22_ALT = "kml22";
    private static final String KEBUTUHAN_MANAJEMEN_LL33_ALT = "kml33";
    private static final String BUKAAN_SEPARATOR = "bsp";
    //atribut tabel a54
    private static final String KEBUTUHAN_MANAJEMEN_LL1_ALE = "kml";
    private static final String KEBUTUHAN_MANAJEMEN_LL2_ALE = "kml2";
    private static final String KEBUTUHAN_MANAJEMEN_LL3_ALE = "kml3";
    private static final String BENTUK_PULAU_JALAN = "sbpj";
    private static final String MARKA1 = "mrk";
    private static final String MARKA2 = "mrk2";
    private static final String MARKA3 = "mrk3";
    private static final String MARKA4 = "mrk4";
    private static final String WARNA_KERB = "wrk";
    private static final String RAMBU_PENGARAH = "rbp";
    //atribut tabel a55
    private static final String KEBUTUHAN_MANAJEMEN_ALL = "kml";
    private static final String PERKERASAN_TROTOAR1 = "pkt";
    private static final String PERKERASAN_TROTOAR2 = "pkt2";
    private static final String PEMANFAATAN_PEJALAN = "psp";
    private static final String UTILITAS_TROTOAR = "utr";
    //atribut tabel a56
    private static final String KEBUTUHAN_MANAJEMEN_LL1_ALEN = "kml";
    private static final String KEBUTUHAN_MANAJEMEN_LL2_ALEN = "kml2";
    private static final String KEBUTUHAN_MANAJEMEN_LL3_ALEN = "kml3";
    private static final String LAMPU_PENGATUR = "lpg";
    private static final String PHASE_PENGATURAN = "php";
    private static final String PHASE_PEJALAN = "phk";
    private static final String FASILITAS_PENYANDANG = "fpc";
    //atribut a57
    private static final String KEBUTUHAN_MANAJEMEN_LL1_ALTJ = "kml";
    private static final String KEBUTUHAN_MANAJEMEN_LL2_ALTJ = "kml2";
    private static final String KEBUTUHAN_MANAJEMEN_LL3_ALTJ = "kml3";
    private static final String KEBUTUHAN_MANAJEMEN_LL4_ALTJ = "kml4";
    private static final String KEBUTUHAN_MANAJEMEN_LL11_ALTJ = "kml11";
    private static final String KEBUTUHAN_MANAJEMEN_LL22_ALTJ = "kml22";
    private static final String KEBUTUHAN_MANAJEMEN_LL33_ALTJ = "kml33";
    private static final String KEBUTUHAN_MANAJEMEN_LL44_ALTJ = "kml44";
    private static final String RAMBU_MARKA = "mrk";
    private static final String RAMBU_MARKA2 = "mrk2";
    private static final String RAMBU_MARKA3 = "mrk3";
    private static final String APILL = "apl";
    private static final String PERLINDUNGAN_PEJALAN = "ppk";
    //atribut tabel a6a1
    private static final String GARIS_SUMBU_PANJANG = "gsp";
    private static final String GARIS_SUMBU_JARAK = "gsj";
    private static final String GARIS_SUMBU_LEBAR = "gsl";
    private static final String GARIS_PERINGATAN_PANJANG = "gpp";
    private static final String GARIS_PERINGATAN_LEBAR = "gpl";
    private static final String GARIS_PERINGATAN_JARAK = "gpj";
    private static final String YIELD_LINE_PANJANG = "ylp";
    private static final String YIELD_LINE_LEBAR = "yll";
    private static final String YIELD_LINE_JARAK = "ylj";
    private static final String ZEBRA_CROSS_PANJANG = "zcp";
    private static final String ZEBRA_CROSS_LEBAR = "zcl";
    private static final String ZEBRA_CROSS_JARAK_GARIS = "zca";
    private static final String ZEBRA_CROSS_JARAK_STOP = "zcg";
    private static final String GARIS_PENGARAH_PANJANG = "gpm";
    private static final String GARIS_PENGARAH_LEBAR = "gpl2";
    private static final String GARIS_PENDEKAT_PANJANG = "gpp2";
    private static final String GARIS_PENDEKAT_LEBAR = "gpl3";
    private static final String MC_LEBAR_UJUNG = "mcu";
    private static final String MC_LEBAR_DALAM = "mcd";
    private static final String MC_LEBAR_GARIS_SERONG = "mcs";
    private static final String MC_JARAK_BATAS = "mcj";
    private static final String GARIS_STOP_LEBAR = "gsl2";
    private static final String TANDA_PENGARAH_PANJANG = "tjp";
    private static final String TANDA_PENGARAH_LEBAR = "tjl";
    private static final String YELLOW_BOX_LEBAR = "ybl";
    private static final String WARNA_MARKA_PUTIH = "wmg";
    private static final String WARNA_MARKA_KUNING = "wmj";
    private static final String WARNA_MARKA_MERAH = "wml";
    private static final String KONDISI_MARKA = "kmb";
    //atribut tabel a6a2
    private static final String UKURAN_DAUN_RAMBU = "udr";
    private static final String WARNA_RAMBU_PERINGATAN = "uwr";
    private static final String WARNA_RAMBU_LARANGAN = "uwr2";
    private static final String WARNA_RAMBU_PERINTAH = "uwr3";
    private static final String WARNA_RAMBU_PETUNJUK = "uwr4";
    private static final String WARNA_PAPAN_TAMBAHAN = "uwr5";
    private static final String POSISI_RAMBU1 = "ljp";
    private static final String POSISI_RAMBU2 = "ljp2";
    private static final String POSISI_RAMBU3 = "ljp3";
    private static final String JARAK_06 = "ljj";
    private static final String JARAK_03 = "ljj2";
    private static final String TINGGI1 = "ljt";
    private static final String TINGGI2 = "ljt2";
    private static final String TINGGI3 = "ljt3";
    private static final String TINGGI4 = "ljt4";
    private static final String PONDASI_TIANG_PAPAN_RAMBU1 = "ppr";
    private static final String PONDASI_TIANG_PAPAN_RAMBU2 = "ppr2";
    private static final String PONDASI_TIANG_PAPAN_RAMBU3 = "ppr3";
    private static final String PONDASI_TIANG_PAPAN_RAMBU4 = "ppr4";
    //atribut tabel a6a3
    private static final String BENTUK_UKURAN_SISILUAR = "bus";
    private static final String BENTUK_UKURAN_TINGGI = "but";
    private static final String BENTUK_UKURAN_LEBAR = "bul";
    private static final String LETAK_UKURAN_BUKAAN = "lub";
    private static final String LETAK_UKURAN_LEBAR_BUKAAN = "lul";
    private static final String BENTUK_UKURAN_JARAK_BUKAAN = "luj";
    //atribut tabel a6a4
    private static final String LAJUR_LAPAK_KENDARAAN = "jlk";
    private static final String TINGGI_KERB_ANAE = "tkm";
    private static final String GARIS_PERINGATAN_PANJANG_ANAE = "dgp";
    private static final String GARIS_PERINGATAN_LEBAR_ANAE = "dgl";
    private static final String GARIS_PERINGATAN_JARAK1 = "dgb";
    private static final String GARIS_PERINGATAN_JARAK2 = "dgj";
    private static final String GARIS_PENDEKAT_LEBAR_ANAE = "gpl";
    private static final String GARIS_PENDEKAT_PANJANG_ANAE = "gpg";
    private static final String CHEVRON_LEBAR_UJUNG = "clu";
    private static final String CHEVRON_LEBAR_GARIS = "clc";
    private static final String CHEVRON_SUDUT_GARIS = "csc";
    private static final String CHEVRON_PANJANG_JARAK = "cbc";
    private static final String RAMBU_TIKUNGAN_TINGGI_TIANG = "rpt";
    private static final String RAMBU_TIKUNGAN_UKURAN_DAUN = "rpt2";
    private static final String RAMBU_PERINTAH_TINGGI_TIANG = "rpt3";
    private static final String RAMBU_PERINTAH_UKURAN_DAUN = "rpt4";
    //atribut tabel a6a5
    private static final String LEBAR_TROTOAR_TEROWONGAN = "lbt";
    private static final String LEBAR_TROTOAR_PERMUKIMAN = "lbt2";
    private static final String LEBAR_TROTOAR_PERKANTORAN = "lbt3";
    private static final String BENTUK_KERB = "btk";
    private static final String TINGGI_KERB = "btk2";
    private static final String PERKERASAN_TROTOAR_BALOK_BETON = "pkt";
    private static final String PERKERASAN_TROTOAR_BETON = "pkt2";
    private static final String PERKERASAN_TROTOAR_LATASIR = "pkt3";
    private static final String PERKERASAN_TROTOAR_PLESTERAN = "pkt4";
    private static final String FASILITAS_PENYANDANG_CACAT = "fpc";
    //atribut tabel a6a6
    private static final String LAMPU_APILL_DAPAT_DILIHAT = "lta";
    private static final String LETAK_TIANG_LAMPU1 = "ltl";
    private static final String LETAK_TIANG_LAMPU2 = "ltl2";
    private static final String TINGGI_PENEMPATAN_ARMATUR1 = "tpa";
    private static final String TINGGI_PENEMPATAN_ARMATUR2 = "tpa2";
    private static final String TINGGI_PENEMPATAN_ARMATUR3 = "tpa3";
    private static final String DIMENSI_LAMPU_APILL = "dla";
    private static final String INTENSITAS_CAHAYA_LAMPU_APILL = "icl";
    private static final String KEAMANAN_ALAT_APILL = "kaa";
    //atribut tabel a6a7
    private static final String TEMPAT_PARKIR = "tpk";
    private static final String RAMBU_PETUNJUK_PARKIR = "rmp";
    private static final String RAMBU_LARANGAN_PARKIR = "rmp2";
    private static final String PEMBERHENTIAN_BUS_ANGKOT = "ppb";
    private static final String LAMPU_PENERANGAN_PENEMPATAN = "lpp";
    private static final String LAMPU_PENERANGAN_PENEMPATAN2 = "lpp2";
    private static final String LAMPU_PENERANGAN_TINGGI_TIANG_STANDAR = "lpt";
    private static final String LAMPU_PENERANGAN_TINGGI_TIANG_MONARA = "lpt2";
    private static final String JARAK_INTERNAL_TIANG_LAMPU = "lpj";
    private static final String PIPA_CARBON_TEBAL2 = "pct";
    private static final String PIPA_CARBON_TEBAL3 = "pct2";
    private static final String PIPA_GALVANISED = "pgd";
    private static final String PELANDAIAN_TROTOAR1 = "ptb";
    private static final String PELANDAIAN_TROTOAR2 = "ptb2";
    private static final String PENYEBRANGAN_PELICAN1 = "fpp";
    private static final String PENYEBRANGAN_PELICAN2 = "fpp2";
    private static final String JEMBATAN_PENYEBRAGAN1 = "fjp";
    private static final String JEMBATAN_PENYEBRAGAN2 = "fjp2";
    private static final String RAMBU_MARKA_AKSESBILITAS = "fpr";
    //atribut tabel a6b1
    private static final String SESUAI_KEBUTUHAN = "skm";
    private static final String LETAK_LUAR_BADAN_JALAN = "lbw";
    private static final String JARAK_PATOK_PENGARAH = "lbw2";
    private static final String BENTUK_PERSEGI = "lbw3";
    private static final String WARNA_REFLEKTIF = "lbw4";
    private static final String KONDISI_FISIK = "kfb";
    //atribut tabel a6b2
    private static final String KELENGKAPAN_PER_KM = "kkh";
    private static final String DIMENSI_TINGGI = "dld";
    private static final String DIMENSI_LEBAR = "dld2";
    private static final String DIMENSI_GARIS = "dld3";
    private static final String LETAK_DILUAR_BADAN_JALAN = "dll";
    private static final String LETAK_PADA_MEDIAN_JALAN = "dll2";
    private static final String WARNA_REFLEKTIF_ANBD = "dlw";
    private static final String TULISAN_TERBACA_JELAS = "dlt";
    private static final String KONDISI_FISIK_ANBD = "kfb";
    //atribut tabel a6b3
    private static final String KELENGKAPAN_PER_KM_ANBT = "kkh";
    private static final String DIMENSI_TINGGI_ANBT = "dld";
    private static final String DIMENSI_UKURAN_ANBT = "dld2";
    private static final String LETAK_DILUAR_BADAN_JALAN_ANBT = "dll";
    private static final String LETAK_PADA_MEDIAN_JALAN_ANBT = "dll2";
    private static final String WARNA_REFLEKTIF_ANBD_ANBT = "dlw";
    private static final String TULISAN_TERBACA_JELAS_ANBT = "dlt";
    private static final String KONDISI_FISIK_ANBT = "kfb";
    //atribut tabel a6b4
    private static final String BENTUK_KOLOM_BETON1= "kbk";
    private static final String BENTUK_KOLOM_BETON2 = "kbk2";
    private static final String BENTUK_KOLOM_BETON3 = "kbk3";
    private static final String DIPASANG_SETIAP_JARAK50 = "kdr";
    private static final String TULISAN_TERBACA_JELAS_ANBE = "ktj";
    private static final String KONDISI_FISIK_ANBE = "kfb";
    //atribut tabel a6b5
    private static final String LETAK_PADA_TEPI_JALAN= "klj";
    private static final String BERBENTUK_TIANG = "kbt";
    private static final String WARNA_REFLEKTIF_ANBL = "kwr";
    private static final String TULISAN_TERBACA_JELAS_ANBL = "ktj";
    private static final String KONDISI_FISIK_ANBL = "kft";
    //atribut tabel a6b6
    private static final String PERLINDUNGAN_THDP_PEJALANKAKI = "ppk";
    private static final String TERLETAK_DILUAR_RUANG = "kfp";
    private static final String TINGGI09 = "kfp2";
    private static final String PIPA_YANG_DIPASANG = "kfp3";
    //atribut tabel a6b7
    private static final String KEBUTUHAN_ANBTU = "kmi";
    private static final String KETERGANGGUAN_THDP_ARUSLL1 = "kal";
    private static final String KETERGANGGUAN_THDP_ARUSLL2 = "kal2";
    private static final String KONDISI_FISIK_ANBTU1 = "kfi";
    private static final String KONDISI_FISIK_ANBTU2 = "kfi2";
    //atribut tabel a6b8
    private static final String JARI_DARI_MARKA_ANBDE = "rpp";
    private static final String TINGGI_DARI_MUKA_TANAH_ANBDE = "rpp2";
    private static final String KEDALAMAN_REL_PENGAMAN_ANBDE = "rpp3";
    private static final String JARAK_ANTAR_TIANG_VERTIKAL_ANBDE = "rpp4";
    private static final String KONDISI_BAIK_ANBDE = "rpp5";
    private static final String TAHAN_BENTURAN_ANBDE = "rpp6";
    private static final String TERDAPAT_TANDA_REFLEKTOR_ANBDE = "rpp7";
    private static final String POS_POLISI_ANBDE = "ppj";

    private static final String CREATE_TABEL_BALAI = "CREATE TABLE " + TABEL_BALAI + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAMA_BALAI + " TEXT NULL)";

    private static final String CREATE_TABEL_PPK = "CREATE TABLE " + TABEL_PPK + " ("+ ID_PPK +" INTEGER PRIMARY KEY NULL, " +
            NAMA_PPK + " TEXT NULL, " +
            UPLOAD + " TEXT NULL)";

    private static final String CREATE_TABEL_PRO = "CREATE TABLE " + TABEL_PRO + " ("+ ID_PROV +" TEXT PRIMARY KEY NULL, " +
            NAMA_PRO + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            HAPUS + " INTEGER NULL)";

    private static final String CREATE_TABEL_KLAS = "CREATE TABLE " + TABEL_KLAS + " ("+ ID_KLAS +" TEXT PRIMARY KEY NULL, " +
            SISTEM_JARINGAN + " TEXT NULL, " +
            STATUS + " TEXT NULL, " +
            FUNGSI + " TEXT NULL, " +
            KELAS_PRASARANA + " TEXT NULL, " +
            KELAS_PENGGUNAAN + " TEXT NULL, " +
            MEDAN_JALAN + " TEXT NULL, " +
            UPLOAD + " TEXT NULL)";

    private static final String CREATE_TABEL_RUAS = "CREATE TABLE " + TABEL_RUAS + " ("+ ID_RUAS +" TEXT PRIMARY KEY NULL, " +
            PENYELENGGARA + " TEXT NULL, " +
            NAMA_RUAS + " TEXT NULL, " +
            NOMOR_RUAS + " TEXT NULL, " +
            PANJANG_RUAS + " REAL NULL, " +
            DKM + " TEXT NULL, " +
            KKM + " TEXT NULL, " +
            KECEPATAN + " REAL NULL, " +
            HAPUS + " INTEGER NULL, " +
            ID_PPK + " INTEGER NULL, " +
            ID_PROV + " TEXT NULL, " +
            ID_KLAS + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_PPK+") REFERENCES " + TABEL_PPK + "(idpk), " +
            "FOREIGN KEY ("+ID_PROV+") REFERENCES " + TABEL_PRO + "(idpro), "+
            "FOREIGN KEY ("+ID_KLAS+") REFERENCES " + TABEL_KLAS + "(iklas))";

    private static final String CREATE_TABEL_SEGMEN = "CREATE TABLE " + TABEL_SEGMEN + " ("+ID_SEGMEN +" TEXT PRIMARY KEY, " +
            ID_RUAS + " TEXT NULL, " +
            SEGMEN + " TEXT NULL, " +
            PANJANG_SEGMEN + " REAL NULL, " +
            DPSG + " TEXT NULL, " +
            KPSG + " TEXT NULL, " +
            KOTA + " TEXT NULL, " +
            HAPUS + " INTEGER NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_RUAS+") REFERENCES " + TABEL_RUAS + "(iru))";

    private static final String CREATE_TABEL_ADMIN = "CREATE TABLE " + TABEL_ADMIN + " ("+ID_POINT +" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KETERSEDIAAN + " TEXT NULL, " +
            KELENGKAPAN + " TEXT NULL, " +
            LEGALITAS + " TEXT NULL, " +
            RECOMENDASI + " TEXT NULL, "+
            KETERSEDIAAN2 + " TEXT NULL, " +
            KELENGKAPAN2 + " TEXT NULL, " +
            LEGALITAS2 + " TEXT NULL, " +
            RECOMENDASI2 + " TEXT NULL, "+
            KETERSEDIAAN3 + " TEXT NULL, " +
            KELENGKAPAN3 + " TEXT NULL, " +
            LEGALITAS3 + " TEXT NULL, " +
            RECOMENDASI3 + " TEXT NULL, "+
            KETERSEDIAAN4 + " TEXT NULL, " +
            KELENGKAPAN4 + " TEXT NULL, " +
            LEGALITAS4 + " TEXT NULL, " +
            RECOMENDASI4 + " TEXT NULL, "+
            KETERSEDIAAN5 + " TEXT NULL, " +
            KELENGKAPAN5 + " TEXT NULL, " +
            LEGALITAS5 + " TEXT NULL, " +
            RECOMENDASI5 + " TEXT NULL, "+
            KETERSEDIAAN6 + " TEXT NULL, " +
            KELENGKAPAN6 + " TEXT NULL, " +
            LEGALITAS6 + " TEXT NULL, " +
            RECOMENDASI6 + " TEXT NULL, "+
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iru))";

    private static final String CREATE_TABEL_COMPRESS = "CREATE TABLE " + TABEL_COMPRESS + " ("+COM_ID_POINT+" TEXT PRIMARY KEY, " +
            COM_NRU + " TEXT NULL, " +
            COM_ISEG + " TEXT NULL, " +
            COM_TABEL + " REAL NULL, " +
            COM_DIR_ORI + " TEXT NULL, " +
            COM_DIR_COM + " TEXT NULL, " +
            COM_COMPRESS + " TEXT NULL, " +
            UPLOAD + " TEXT NULL)";

    private static final String CREATE_TABEL_ASSS = "CREATE TABLE " + TABEL_ASSS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            FUNGSI_JALAN + " TEXT NULL, " +
            ARUS_DILAYANI + " REAL NULL, " +
            JUMLAH_LAJUR + " REAL NULL, " +
            LEBAR_LAJUR + " REAL NULL, " +
            KESERAGAMAN_LAJUR + " REAL NULL, " +
            KEMIRINGAN_MELINTANG + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASSD = "CREATE TABLE " + TABEL_ASSD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_BAHU + " REAL NULL, " +
            KESERAGAMAN_BAHU + " REAL NULL, " +
            PERKERASAN_BAHU + " REAL NULL, " +
            POSISI_BAHU + " REAL NULL, " +
            KEMIRINGAN_BAHU + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASST = "CREATE TABLE " + TABEL_ASST + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_MEDIAN_TP + " TEXT NULL, " +
            LEBAR_MEDIAN + " REAL NULL, " +
            TIPE_MEDIAN_TP + " TEXT NULL, " +
            TIPE_MEDIAN + " REAL NULL, " +
            PERKERASAN_MEDIAN_TP + " REAL NULL, " +
            PERKERASAN_MEDIAN + " REAL NULL, " +
            JARAK_BUKAAN + " REAL NULL, " +
            LEBAR_BUKAAN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASSE = "CREATE TABLE " + TABEL_ASSE + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_SELOKAN + " REAL NULL, " +
            BENTUK_SELOKAN1 + " TEXT NULL, " +
            BENTUK_SELOKAN2 + " REAL NULL, " +
            FUNGSI_AIR + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASSL = "CREATE TABLE " + TABEL_ASSL + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_AMBANG + " REAL NULL, " +
            LEBAR_AMBANG2 + " REAL NULL, " +
            PENGAMANAN_KONSTRUKSI + " TEXT NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASSEN = "CREATE TABLE " + TABEL_ASSEN + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            JARAK_MARKA + " REAL NULL, " +
            TINGGI_VERTIKAL + " REAL NULL, " +
            KEDALAMAN_VERTIKAL + " REAL NULL, " +
            JARAK_VERTIKAL + " REAL NULL, " +
            TANDA_REFLEKTOR + " REAL NULL, " +
            JARAK_MARKA2 + " REAL NULL, " +
            MUTU_BETON + " TEXT NULL, " +
            TINGGI_MUKA + " REAL NULL, " +
            BAGIAN_TERTANAM + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASDS = "CREATE TABLE " + TABEL_ASDS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            PANJANG_BAGIAN + " REAL NULL, " +
            JARAK_PANDANG_SPIN + " TEXT NULL, " +
            JARAK_PANDANG_H + " REAL NULL, " +
            JARAK_PANDANG_M + " REAL NULL, " +
            LINGKUNGAN_JALAN_SPINNER + " TEXT NULL, " +
            LINGKUNGAN_JALAN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASDD = "CREATE TABLE " + TABEL_ASDD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            RADIUS_TIKUNGAN_SPIN + " TEXT NULL, " +
            RADIUS_TIKUNGAN + " REAL NULL, " +
            SUPERELEVASI + " REAL NULL, " +
            JARAK_PANDANG_ASDD + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASDT = "CREATE TABLE " + TABEL_ASDT + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            JUMLAH_PERSIMPANGAN + " INTEGER NULL, " +
            CARA_AKSES_JU + " TEXT NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASDE = "CREATE TABLE " + TABEL_ASDE + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            BANYAKNYA_AKSES_PERSIL + " INTEGER NULL, " +
            AKSES_JALAN_UTAMA + " TEXT NULL, " +
            BENTUK_AKSES + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASTS = "CREATE TABLE " + TABEL_ASTS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KELANDAIAN_MEMANJANG + " REAL NULL, " +
            PANJANG_LANDAI_KRITIS + " REAL NULL, " +
            JARAK_PANDANG_SPIN + " TEXT NULL, " +
            JARAK_PANDANG_LANDAI_MAX + " REAL NULL, " +
            JARAK_PANDANG_H_ASTS + " REAL NULL, " +
            JARAK_PANDANG_M_ASTS + " REAL NULL, " +
            LINGKUNGAN_JALAN_SPINNER_ASTS + " TEXT NULL, " +
            LINGKUNGAN_JALAN_ASTS + " REAL NULL, " +
            KECEPATAN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASTD = "CREATE TABLE " + TABEL_ASTD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEPERLUAN_KEBERADAAN + " TEXT NULL, " +
            LEBAR_LAJUR_ASTD + " REAL NULL, " +
            PANJANG_AWAL + " REAL NULL, " +
            PANJANG_SERONGAN_SATU + " REAL NULL, " +
            PANJANG_BAGIAN_LURUS + " REAL NULL, " +
            PANJANG_SETELAH_PUNCAK + " REAL NULL, " +
            PANJANG_SERONGAN_DUA + " REAL NULL, " +
            TAPER_MASUK_KELUAR + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASTT = "CREATE TABLE " + TABEL_ASTT + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LENGKUNG_PARABOLA + " REAL NULL, " +
            KETAJAMAN_LINGKUNGAN + " REAL NULL, " +
            KETAJAMAN_LINGKUNGAN_CEMBUNG + " REAL NULL, " +
            KETAJAMAN_LINGKUNGAN_CEKUNG + " REAL NULL, " +
            JARAK_PANDANG_SPIN_ASTT + " TEXT NULL, " +
            JARAK_PANDANG_ASTT + " REAL NULL, " +
            JARAK_PANDANG_H_ASTT + " REAL NULL, " +
            JARAK_PANDANG_M_ASTT + " REAL NULL, " +
            ARAH_JALAN_SATU + " REAL NULL, " +
            ARAH_JALAN_DUA + " REAL NULL, " +
            KOMBINASI_LENGKUNG_VH1 + " REAL NULL, " +
            KOMBINASI_LENGKUNG_VH2 + " REAL NULL, " +
            KOMBINASI_LENGKUNG_VH3 + " REAL NULL, " +
            KOMBINASI_LENGKUNG_VH4 + " REAL NULL, " +
            KOMBINASI_LENGKUNG_VH5 + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ASES = "CREATE TABLE " + TABEL_ASES + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            OVERLOADING_KURVA_LURUS + " REAL NULL, " +
            OVERLOADING_KURVA_MENIKUNG + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ADS = "CREATE TABLE " + TABEL_ADS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KESESUAIAN_STRUKTUR + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ADD = "CREATE TABLE " + TABEL_ADD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KERATAAN_JALAN + " REAL NULL, " +
            KEDALAMAN_LUBANG_D + " REAL NULL, " +
            KEDALAMAN_LUBANG_K + " REAL NULL, " +
            INTENSITAS_LUBANG + " REAL NULL, " +
            LEBAR_RETAK + " REAL NULL, " +
            INTENSITAS_RETAK + " REAL NULL, " +
            KERUSAKAN_ALUR_DANGKAL + " REAL NULL, " +
            KERUSAKAN_ALUR_DALAM + " REAL NULL, " +
            INTENSITAS_ALUR + " REAL NULL, " +
            TEKSTUR_PERKERASAN + " REAL NULL, " +
            ASPAL_MELELEH + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ADT = "CREATE TABLE " + TABEL_ADT + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            PEMERIKSAAN_LANJUT + " REAL NULL, " +
            KEKUATAN_KONSTRUKSI + " REAL NULL, " +
            DRAINASE_PERMUKAAN + " REAL NULL, " +
            BAHAN_PERKERASAN_SPIN + " TEXT NULL, " +
            BAHAN_PERKERASAN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ATS = "CREATE TABLE " + TABEL_ATS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            JALUR_LALU_LINTAS + " REAL NULL, " +
            JALUR_PEJALAN_KAKI + " REAL NULL, " +
            TEGANGAN_IZIN + " REAL NULL, " +
            LENDUTAN_IZIN + " REAL NULL, " +
            PENURUNAN_IZIN + " REAL NULL, " +
            LEBAR_RETAK_IZIN + " REAL NULL, " +
            GETARAN_IZIN + " REAL NULL, " +
            KETAHANAN_IZIN + " REAL NULL, " +
            KERUSAKAN_JEMBATAN_SPIN + " TEXT NULL, " +
            KERUSAKAN_JEMBATAN + " REAL NULL, " +
            KERUSAKAN_JEMBATAN2 + " REAL NULL, " +
            KERUSAKAN_JEMBATAN3 + " REAL NULL, " +
            KERUSAKAN_JEMBATAN4 + " REAL NULL, " +
            KERUSAKAN_JEMBATAN5 + " REAL NULL, " +
            KERUSAKAN_JEMBATAN6 + " REAL NULL, " +
            KERUSAKAN_JEMBATAN7 + " REAL NULL, " +
            KERUSAKAN_JEMBATAN8 + " REAL NULL, " +
            UNIT_MOBIL + " REAL NULL, " +
            SUMBER_DAYA + " REAL NULL, " +
            KELENGKAPAN_KERJA + " REAL NULL, " +
            UNIT_PEMELIHARAAN + " REAL NULL, " +
            UNIT_PENGECATAN + " REAL NULL, " +
            UNIT_KONTROL + " REAL NULL, " +
            ALAT_BANTU_KERJA + " REAL NULL, " +
            TANDA_PENGAMAN_KERJA + " REAL NULL, " +
            ALAT_PENGGANTUNG + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            DIR_5 + " TEXT NULL, " +
            DIR_6 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ATD = "CREATE TABLE " + TABEL_ATD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            FUNGSI_ATD + " REAL NULL, " +
            KONSTRUKSI_PONTON + " REAL NULL, " +
            KERUSAKAN_PONTON + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ATT = "CREATE TABLE " + TABEL_ATT + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            JUMLAH_KILOMETER_DATAR + " INTEGER NULL, " +
            JUMLAH_KILOMETER_PEGUNUNGAN + " INTEGER NULL, " +
            FUNGSI_MENYALURKAN_AIR + " REAL NULL, " +
            KERUSAKAN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ATE = "CREATE TABLE " + TABEL_ATE + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            POSISI_LAJUR + " REAL NULL, " +
            KETERGANGGUAN_ARUS + " REAL NULL, " +
            LEBAR_LAJUR_ATE + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ATL = "CREATE TABLE " + TABEL_ATL + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KESTABILAN_KONSTRUKSI + " REAL NULL, " +
            KERUSAKAN_EROSI_LONGSOR + " REAL NULL, " +
            SALURAN_AIR + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ATEN = "CREATE TABLE " + TABEL_ATEN + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            DIMENSI_DMA + " REAL NULL, " +
            DIMENSI_SDMA + " TEXT NULL, " +
            KEMIRINGAN_TANAH + " REAL NULL, " +
            KEMIRINGAN_KRIKIL + " REAL NULL, " +
            KEMIRINGAN_BATU + " REAL NULL, " +
            BAHAN_DINDING_SBDS + " TEXT NULL, " +
            BAHAN_DINDING_BDS + " REAL NULL, " +
            TERTUTUP_TERBUKA + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_AES = "CREATE TABLE " + TABEL_AES + " ("+ID_POINT+" TEXTPRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_TINGGI_SPIN + " TEXT NULL, " +
            LEBAR_TINGGI1 + " REAL NULL, " +
            LEBAR_TINGGI2 + " REAL NULL, " +
            LEBAR_TINGGI3 + " REAL NULL, " +
            PEMANFAATAN_RUMAJA + " REAL NULL, " +
            KESELAMATAN_LALULINTAS + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_AED = "CREATE TABLE " + TABEL_AED + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_RUMIJA + " REAL NULL, " +
            PEMANFAATAN_RUMIJA_AED + " REAL NULL, " +
            KEBERADAAN_UTILITAS_SPIN + " TEXT NULL, "+
            KEBERADAAN_UTILITAS + " REAL NULL, " +
            KEBERADAAN_UTILITAS2 + " REAL NULL, " +
            KEBERADAAN_UTILITAS3 + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_AET = "CREATE TABLE " + TABEL_AET + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_RUWASJA + " REAL NULL, " +
            PEMANFAATAN_RUWASJA1 + " REAL NULL, " +
            PEMANFAATAN_RUWASJA2 + " REAL NULL, " +
            PEMANFAATAN_RUWASJA3 + " REAL NULL, " +
            PENGHALANG_PANDANGAN_PENGEMUDI + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ALS = "CREATE TABLE " + TABEL_ALS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            MARKA_BAGIAN_LURUS1 + " TEXT NULL, " +
            MARKA_BAGIAN_LURUS2 + " TEXT NULL, " +
            MARKA_BAGIAN_LURUS3 + " TEXT NULL, " +
            MARKA_BAGIAN_LURUS4 + " TEXT NULL, " +
            MARKA_BAGIAN_TIKUNGAN1 + " TEXT NULL, " +
            MARKA_BAGIAN_TIKUNGAN2 + " TEXT NULL, " +
            MARKA_BAGIAN_TIKUNGAN3 + " TEXT NULL, " +
            MARKA_BAGIAN_TIKUNGAN4 + " TEXT NULL, " +
            MARKA_BAGIAN_TIKUNGAN5 + " TEXT NULL, " +
            MARKA_PERSIMPANGAN1 + " TEXT NULL, " +
            MARKA_PERSIMPANGAN2 + " TEXT NULL, " +
            MARKA_PERSIMPANGAN3 + " TEXT NULL, " +
            MARKA_PERSIMPANGAN4 + " TEXT NULL, " +
            MARKA_PERSIMPANGAN5 + " TEXT NULL, " +
            MARKA_PERSIMPANGAN6 + " TEXT NULL, " +
            MARKA_PERSIMPANGAN7 + " TEXT NULL, " +
            MARKA_BAGIAN_LURUS11 + " REAL NULL, " +
            MARKA_BAGIAN_LURUS22 + " REAL NULL, " +
            MARKA_BAGIAN_LURUS33 + " REAL NULL, " +
            MARKA_BAGIAN_LURUS44 + " REAL NULL, " +
            MARKA_BAGIAN_TIKUNGAN11 + " REAL NULL, " +
            MARKA_BAGIAN_TIKUNGAN22 + " REAL NULL, " +
            MARKA_BAGIAN_TIKUNGAN33 + " REAL NULL, " +
            MARKA_BAGIAN_TIKUNGAN44 + " REAL NULL, " +
            MARKA_BAGIAN_TIKUNGAN55 + " REAL NULL, " +
            MARKA_PERSIMPANGAN11 + " REAL NULL, " +
            MARKA_PERSIMPANGAN22 + " REAL NULL, " +
            MARKA_PERSIMPANGAN33 + " REAL NULL, " +
            MARKA_PERSIMPANGAN44 + " REAL NULL, " +
            MARKA_PERSIMPANGAN55 + " REAL NULL, " +
            MARKA_PERSIMPANGAN66 + " REAL NULL, " +
            MARKA_PERSIMPANGAN77 + " REAL NULL, " +
            ZEBRA_CROSS1 + " REAL NULL, " +
            ZEBRA_CROSS2 + " REAL NULL, " +
            ZEBRA_CROSS3 + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ALD = "CREATE TABLE " + TABEL_ALD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL1 + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL2 + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL3 + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL4 + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL5 + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL6 + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL11 + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL22 + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL33 + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL44 + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL55 + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL66 + " REAL NULL, " +
            KETEPATAN_RAMBU1 + " REAL NULL, " +
            KETEPATAN_RAMBU2 + " REAL NULL, " +
            KETEPATAN_RAMBU3 + " REAL NULL, " +
            KETEPATAN_RAMBU4 + " REAL NULL, " +
            KETEPATAN_RAMBU5 + " REAL NULL, " +
            KETEPATAN_RAMBU6 + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ALT = "CREATE TABLE " + TABEL_ALT + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL_ALT + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL2_ALT + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL3_ALT + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL11_ALT + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL22_ALT + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL33_ALT + " REAL NULL, " +
            BUKAAN_SEPARATOR + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ALE = "CREATE TABLE " + TABEL_ALE + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL1_ALE + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL2_ALE + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL3_ALE + " TEXT NULL, " +
            BENTUK_PULAU_JALAN + " TEXT NULL, " +
            MARKA1 + " REAL NULL, " +
            MARKA2 + " REAL NULL, " +
            MARKA3 + " REAL NULL, " +
            MARKA4 + " REAL NULL, " +
            WARNA_KERB + " REAL NULL, " +
            RAMBU_PENGARAH + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ALL = "CREATE TABLE " + TABEL_ALL + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_ALL + " TEXT NULL, " +
            PERKERASAN_TROTOAR1 + " REAL NULL, " +
            PERKERASAN_TROTOAR2 + " REAL NULL, " +
            PEMANFAATAN_PEJALAN + " REAL NULL, " +
            UTILITAS_TROTOAR + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ALEN = "CREATE TABLE " + TABEL_ALEN + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL1_ALEN + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL2_ALEN + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL3_ALEN + " REAL NULL, " +
            LAMPU_PENGATUR + " REAL NULL, " +
            PHASE_PENGATURAN + " INTEGER NULL, " +
            PHASE_PEJALAN + " REAL NULL, " +
            FASILITAS_PENYANDANG + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ALTJ = "CREATE TABLE " + TABEL_ALTJ + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL1_ALTJ + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL2_ALTJ + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL3_ALTJ + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL4_ALTJ + " TEXT NULL, " +
            KEBUTUHAN_MANAJEMEN_LL11_ALTJ + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL22_ALTJ + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL33_ALTJ + " REAL NULL, " +
            KEBUTUHAN_MANAJEMEN_LL44_ALTJ + " REAL NULL, " +
            RAMBU_MARKA + " REAL NULL, " +
            RAMBU_MARKA2 + " REAL NULL, " +
            RAMBU_MARKA3 + " REAL NULL, " +
            APILL + " REAL NULL, " +
            PERLINDUNGAN_PEJALAN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANAS = "CREATE TABLE " + TABEL_ANAS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            GARIS_SUMBU_PANJANG + " REAL NULL, " +
            GARIS_SUMBU_JARAK + " REAL NULL, " +
            GARIS_SUMBU_LEBAR + " REAL NULL, " +
            GARIS_PERINGATAN_PANJANG + " REAL NULL, " +
            GARIS_PERINGATAN_LEBAR + " REAL NULL, " +
            GARIS_PERINGATAN_JARAK + " REAL NULL, " +
            YIELD_LINE_PANJANG + " REAL NULL, " +
            YIELD_LINE_LEBAR + " REAL NULL, " +
            YIELD_LINE_JARAK + " REAL NULL, " +
            ZEBRA_CROSS_PANJANG + " REAL NULL, " +
            ZEBRA_CROSS_LEBAR + " REAL NULL, " +
            ZEBRA_CROSS_JARAK_GARIS + " REAL NULL, " +
            ZEBRA_CROSS_JARAK_STOP + " REAL NULL, " +
            GARIS_PENGARAH_PANJANG + " REAL NULL, " +
            GARIS_PENGARAH_LEBAR + " REAL NULL, " +
            GARIS_PENDEKAT_PANJANG + " REAL NULL, " +
            GARIS_PENDEKAT_LEBAR + " REAL NULL, " +
            MC_LEBAR_UJUNG + " REAL NULL, " +
            MC_LEBAR_DALAM + " REAL NULL, " +
            MC_LEBAR_GARIS_SERONG + " REAL NULL, " +
            MC_JARAK_BATAS + " REAL NULL, " +
            GARIS_STOP_LEBAR + " REAL NULL, " +
            TANDA_PENGARAH_PANJANG + " REAL NULL, " +
            TANDA_PENGARAH_LEBAR + " REAL NULL, " +
            YELLOW_BOX_LEBAR + " REAL NULL, " +
            WARNA_MARKA_PUTIH + " REAL NULL, " +
            WARNA_MARKA_KUNING + " REAL NULL, " +
            WARNA_MARKA_MERAH + " REAL NULL, " +
            KONDISI_MARKA + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANAD = "CREATE TABLE " + TABEL_ANAD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            UKURAN_DAUN_RAMBU + " REAL NULL, " +
            WARNA_RAMBU_PERINGATAN + " REAL NULL, " +
            WARNA_RAMBU_LARANGAN + " REAL NULL, " +
            WARNA_RAMBU_PERINTAH + " REAL NULL, " +
            WARNA_RAMBU_PETUNJUK + " REAL NULL, " +
            WARNA_PAPAN_TAMBAHAN + " REAL NULL, " +
            POSISI_RAMBU1 + " REAL NULL, " +
            POSISI_RAMBU2 + " REAL NULL, " +
            POSISI_RAMBU3 + " REAL NULL, " +
            JARAK_06 + " REAL NULL, " +
            JARAK_03 + " REAL NULL, " +
            TINGGI1 + " REAL NULL, " +
            TINGGI2 + " REAL NULL, " +
            TINGGI3 + " REAL NULL, " +
            TINGGI4 + " REAL NULL, " +
            PONDASI_TIANG_PAPAN_RAMBU1 + " REAL NULL, " +
            PONDASI_TIANG_PAPAN_RAMBU2 + " REAL NULL, " +
            PONDASI_TIANG_PAPAN_RAMBU3 + " REAL NULL, " +
            PONDASI_TIANG_PAPAN_RAMBU4 + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";


    private static final String CREATE_TABEL_ANAT = "CREATE TABLE " + TABEL_ANAT + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            BENTUK_UKURAN_SISILUAR + " REAL NULL, " +
            BENTUK_UKURAN_TINGGI + " REAL NULL, " +
            BENTUK_UKURAN_LEBAR + " REAL NULL, " +
            LETAK_UKURAN_BUKAAN + " REAL NULL, " +
            LETAK_UKURAN_LEBAR_BUKAAN + " REAL NULL, " +
            BENTUK_UKURAN_JARAK_BUKAAN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";


    private static final String CREATE_TABEL_ANAE = "CREATE TABLE " + TABEL_ANAE + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LAJUR_LAPAK_KENDARAAN + " REAL NULL, " +
            TINGGI_KERB_ANAE + " REAL NULL, " +
            GARIS_PERINGATAN_PANJANG_ANAE + " REAL NULL, " +
            GARIS_PERINGATAN_LEBAR_ANAE + " REAL NULL, " +
            GARIS_PERINGATAN_JARAK1 + " REAL NULL, " +
            GARIS_PERINGATAN_JARAK2 + " REAL NULL, " +
            GARIS_PENDEKAT_LEBAR_ANAE + " REAL NULL, " +
            GARIS_PENDEKAT_PANJANG_ANAE + " REAL NULL, " +
            CHEVRON_LEBAR_UJUNG + " REAL NULL, " +
            CHEVRON_LEBAR_GARIS + " REAL NULL, " +
            CHEVRON_SUDUT_GARIS + " REAL NULL, " +
            CHEVRON_PANJANG_JARAK + " REAL NULL, " +
            RAMBU_TIKUNGAN_TINGGI_TIANG + " REAL NULL, " +
            RAMBU_TIKUNGAN_UKURAN_DAUN + " REAL NULL, " +
            RAMBU_PERINTAH_TINGGI_TIANG + " REAL NULL, " +
            RAMBU_PERINTAH_UKURAN_DAUN + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";


    private static final String CREATE_TABEL_ANALI = "CREATE TABLE " + TABEL_ANALI + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LEBAR_TROTOAR_TEROWONGAN + " REAL NULL, " +
            LEBAR_TROTOAR_PERMUKIMAN + " REAL NULL, " +
            LEBAR_TROTOAR_PERKANTORAN + " REAL NULL, " +
            BENTUK_KERB + " REAL NULL, " +
            TINGGI_KERB + " REAL NULL, " +
            PERKERASAN_TROTOAR_BALOK_BETON + " REAL NULL, " +
            PERKERASAN_TROTOAR_BETON + " REAL NULL, " +
            PERKERASAN_TROTOAR_LATASIR + " REAL NULL, " +
            PERKERASAN_TROTOAR_PLESTERAN + " REAL NULL, " +
            FASILITAS_PENYANDANG_CACAT + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANAN = "CREATE TABLE " + TABEL_ANAN + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LAMPU_APILL_DAPAT_DILIHAT + " REAL NULL, " +
            LETAK_TIANG_LAMPU1 + " REAL NULL, " +
            LETAK_TIANG_LAMPU2 + " REAL NULL, " +
            TINGGI_PENEMPATAN_ARMATUR1 + " REAL NULL, " +
            TINGGI_PENEMPATAN_ARMATUR2 + " REAL NULL, " +
            TINGGI_PENEMPATAN_ARMATUR3 + " REAL NULL, " +
            DIMENSI_LAMPU_APILL + " REAL NULL, " +
            INTENSITAS_CAHAYA_LAMPU_APILL + " REAL NULL, " +
            KEAMANAN_ALAT_APILL + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANATU = "CREATE TABLE " + TABEL_ANATU + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            TEMPAT_PARKIR + " REAL NULL, " +
            RAMBU_PETUNJUK_PARKIR + " REAL NULL, " +
            RAMBU_LARANGAN_PARKIR + " REAL NULL, " +
            PEMBERHENTIAN_BUS_ANGKOT + " REAL NULL, " +
            LAMPU_PENERANGAN_PENEMPATAN + " REAL NULL, " +
            LAMPU_PENERANGAN_PENEMPATAN2 + " REAL NULL, " +
            LAMPU_PENERANGAN_TINGGI_TIANG_STANDAR + " REAL NULL, " +
            LAMPU_PENERANGAN_TINGGI_TIANG_MONARA + " REAL NULL, " +
            JARAK_INTERNAL_TIANG_LAMPU + " REAL NULL, " +
            PIPA_CARBON_TEBAL2 + " REAL NULL, " +
            PIPA_CARBON_TEBAL3 + " REAL NULL, " +
            PIPA_GALVANISED + " REAL NULL, " +
            PELANDAIAN_TROTOAR1 + " REAL NULL, " +
            PELANDAIAN_TROTOAR2 + " REAL NULL, " +
            PENYEBRANGAN_PELICAN1 + " REAL NULL, " +
            PENYEBRANGAN_PELICAN2 + " REAL NULL, " +
            JEMBATAN_PENYEBRAGAN1 + " REAL NULL, " +
            JEMBATAN_PENYEBRAGAN2 + " REAL NULL, " +
            RAMBU_MARKA_AKSESBILITAS + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBS = "CREATE TABLE " + TABEL_ANBS + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            SESUAI_KEBUTUHAN + " REAL NULL, " +
            LETAK_LUAR_BADAN_JALAN + " REAL NULL, " +
            JARAK_PATOK_PENGARAH + " REAL NULL, " +
            BENTUK_PERSEGI + " REAL NULL, " +
            WARNA_REFLEKTIF + " REAL NULL, " +
            KONDISI_FISIK + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBD = "CREATE TABLE " + TABEL_ANBD + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KELENGKAPAN_PER_KM + " REAL NULL, " +
            DIMENSI_TINGGI + " REAL NULL, " +
            DIMENSI_LEBAR + " REAL NULL, " +
            DIMENSI_GARIS + " REAL NULL, " +
            LETAK_DILUAR_BADAN_JALAN + " REAL NULL, " +
            LETAK_PADA_MEDIAN_JALAN + " REAL NULL, " +
            WARNA_REFLEKTIF_ANBD + " REAL NULL, " +
            TULISAN_TERBACA_JELAS + " REAL NULL, " +
            KONDISI_FISIK_ANBD + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBT = "CREATE TABLE " + TABEL_ANBT + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KELENGKAPAN_PER_KM_ANBT + " REAL NULL, " +
            DIMENSI_TINGGI_ANBT + " REAL NULL, " +
            DIMENSI_UKURAN_ANBT + " REAL NULL, " +
            LETAK_DILUAR_BADAN_JALAN_ANBT + " REAL NULL, " +
            LETAK_PADA_MEDIAN_JALAN_ANBT + " REAL NULL, " +
            WARNA_REFLEKTIF_ANBD_ANBT + " REAL NULL, " +
            TULISAN_TERBACA_JELAS_ANBT + " REAL NULL, " +
            KONDISI_FISIK_ANBT + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBE = "CREATE TABLE " + TABEL_ANBE + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            BENTUK_KOLOM_BETON1 + " REAL NULL, " +
            BENTUK_KOLOM_BETON2 + " REAL NULL, " +
            BENTUK_KOLOM_BETON3 + " REAL NULL, " +
            DIPASANG_SETIAP_JARAK50 + " REAL NULL, " +
            TULISAN_TERBACA_JELAS_ANBE + " REAL NULL, " +
            KONDISI_FISIK_ANBE + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBL = "CREATE TABLE " + TABEL_ANBL + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            LETAK_PADA_TEPI_JALAN + " REAL NULL, " +
            BERBENTUK_TIANG + " REAL NULL, " +
            WARNA_REFLEKTIF_ANBL + " REAL NULL, " +
            TULISAN_TERBACA_JELAS_ANBL + " REAL NULL, " +
            KONDISI_FISIK_ANBL + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBN = "CREATE TABLE " + TABEL_ANBN + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            PERLINDUNGAN_THDP_PEJALANKAKI + " REAL NULL, " +
            TERLETAK_DILUAR_RUANG + " REAL NULL, " +
            TINGGI09 + " REAL NULL, " +
            PIPA_YANG_DIPASANG + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            " FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBTU = "CREATE TABLE " + TABEL_ANBTU + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            KEBUTUHAN_ANBTU + " REAL NULL, " +
            KETERGANGGUAN_THDP_ARUSLL1 + " REAL NULL, " +
            KETERGANGGUAN_THDP_ARUSLL2 + " REAL NULL, " +
            KONDISI_FISIK_ANBTU1 + " REAL NULL, " +
            KONDISI_FISIK_ANBTU2 + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    private static final String CREATE_TABEL_ANBDE = "CREATE TABLE " + TABEL_ANBDE + " ("+ID_POINT+" TEXT PRIMARY KEY, " +
            ID_SEGMEN + " TEXT NULL, " +
            JARI_DARI_MARKA_ANBDE + " REAL NULL, " +
            TINGGI_DARI_MUKA_TANAH_ANBDE + " REAL NULL, " +
            KEDALAMAN_REL_PENGAMAN_ANBDE + " REAL NULL, " +
            JARAK_ANTAR_TIANG_VERTIKAL_ANBDE + " REAL NULL, " +
            KONDISI_BAIK_ANBDE + " REAL NULL, " +
            TAHAN_BENTURAN_ANBDE + " REAL NULL, " +
            TERDAPAT_TANDA_REFLEKTOR_ANBDE + " REAL NULL, " +
            POS_POLISI_ANBDE + " REAL NULL, " +
            RECOMENDASI + " TEXT NULL, " +
            DIR_1 + " TEXT NULL, " +
            DIR_2 + " TEXT NULL, " +
            DIR_3 + " TEXT NULL, " +
            DIR_4 + " TEXT NULL, " +
            UPLOAD + " TEXT NULL, " +
            "FOREIGN KEY ("+ID_SEGMEN+") REFERENCES " + TABEL_SEGMEN + "(iseg))";

    public DatabaseHelper(Context context) {
        super(context, NAMA_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABEL_BALAI);
        db.execSQL(CREATE_TABEL_PPK);
        db.execSQL(CREATE_TABEL_PRO);
        db.execSQL(CREATE_TABEL_KLAS);
        db.execSQL(CREATE_TABEL_RUAS);
        db.execSQL(CREATE_TABEL_SEGMEN);
        db.execSQL(CREATE_TABEL_ADMIN);
        db.execSQL(CREATE_TABEL_COMPRESS);
        db.execSQL(CREATE_TABEL_ASSS);
        db.execSQL(CREATE_TABEL_ASSD);
        db.execSQL(CREATE_TABEL_ASST);
        db.execSQL(CREATE_TABEL_ASSE);
        db.execSQL(CREATE_TABEL_ASSL);
        db.execSQL(CREATE_TABEL_ASSEN);
        db.execSQL(CREATE_TABEL_ASDS);
        db.execSQL(CREATE_TABEL_ASDD);
        db.execSQL(CREATE_TABEL_ASDT);
        db.execSQL(CREATE_TABEL_ASDE);
        db.execSQL(CREATE_TABEL_ASTS);
        db.execSQL(CREATE_TABEL_ASTD);
        db.execSQL(CREATE_TABEL_ASTT);
        db.execSQL(CREATE_TABEL_ASES);
        db.execSQL(CREATE_TABEL_ADS);
        db.execSQL(CREATE_TABEL_ADD);
        db.execSQL(CREATE_TABEL_ADT);
        db.execSQL(CREATE_TABEL_ATS);
        db.execSQL(CREATE_TABEL_ATD);
        db.execSQL(CREATE_TABEL_ATT);
        db.execSQL(CREATE_TABEL_ATE);
        db.execSQL(CREATE_TABEL_ATL);
        db.execSQL(CREATE_TABEL_ATEN);
        db.execSQL(CREATE_TABEL_AES);
        db.execSQL(CREATE_TABEL_AED);
        db.execSQL(CREATE_TABEL_AET);
        db.execSQL(CREATE_TABEL_ALS);
        db.execSQL(CREATE_TABEL_ALD);
        db.execSQL(CREATE_TABEL_ALT);
        db.execSQL(CREATE_TABEL_ALE);
        db.execSQL(CREATE_TABEL_ALL);
        db.execSQL(CREATE_TABEL_ALEN);
        db.execSQL(CREATE_TABEL_ALTJ);
        db.execSQL(CREATE_TABEL_ANAS);
        db.execSQL(CREATE_TABEL_ANAD);
        db.execSQL(CREATE_TABEL_ANAT);
        db.execSQL(CREATE_TABEL_ANAE);
        db.execSQL(CREATE_TABEL_ANALI);
        db.execSQL(CREATE_TABEL_ANAN);
        db.execSQL(CREATE_TABEL_ANATU);
        db.execSQL(CREATE_TABEL_ANBS);
        db.execSQL(CREATE_TABEL_ANBD);
        db.execSQL(CREATE_TABEL_ANBT);
        db.execSQL(CREATE_TABEL_ANBE);
        db.execSQL(CREATE_TABEL_ANBL);
        db.execSQL(CREATE_TABEL_ANBN);
        db.execSQL(CREATE_TABEL_ANBTU);
        db.execSQL(CREATE_TABEL_ANBDE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_BALAI);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_PPK);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_PRO);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_KLAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_RUAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_SEGMEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_COMPRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASSS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASSD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASST);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASSL);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASSEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASDD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASDT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASDE);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASTD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASTT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ASES);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ADS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ADD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ADT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ATD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ATT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ATL);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ATEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_AES);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_AED);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_AET);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ALD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ALT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ALE);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ALL);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ALEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ALTJ);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANAE);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANALI);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANATU);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBS);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBD);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBE);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBL);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBTU);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_ANBDE);
        onCreate(db);
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel Balai
    public Boolean insertDataBalai(String nbl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_BALAI, nbl);

        long result = db.insert(TABEL_BALAI, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<Balai_Class> allDataBalai(){
        List<Balai_Class> balaiArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_BALAI + " ORDER BY id DESC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Balai_Class balai = new Balai_Class();
                balai.setID(cursor.getInt(cursor.getColumnIndex("id")));
                balai.setNBL(cursor.getString(cursor.getColumnIndex(NAMA_BALAI)));
                balaiArrayList.add(balai);
            } while (cursor.moveToNext());
        }
        db.close();
        return balaiArrayList;
    }

    public Boolean allDataBalaiWhere(String text){
        String query = "SELECT * FROM " + TABEL_BALAI;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Boolean hasil = false;

        if(cursor.moveToFirst()){
            do{
                if(text.equals(cursor.getString(cursor.getColumnIndex(NAMA_BALAI)))){
                    hasil = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return hasil;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel PPK
    public Boolean insertDataPPK(int idpk, String npk, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PPK, idpk);
        contentValues.put(NAMA_PPK, npk);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_PPK, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateDataPPK(int idpk, String npk, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PPK, idpk);
        contentValues.put(NAMA_PPK, npk);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_PPK, contentValues, ID_PPK + " LIKE '%" + idpk + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public int getIdPPK(String ppk){
        int idpk = 0;
        String query = "SELECT "+ ID_PPK +" FROM " + TABEL_PPK + " WHERE " + NAMA_PPK + " LIKE '%" + ppk + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                idpk = cursor.getInt(cursor.getColumnIndex(ID_PPK));
            } while (cursor.moveToNext());
        }
        db.close();
        return idpk;
    }

    public String getNamaPPK(int idpk){
        String ppk = null;
        String query = "SELECT "+ NAMA_PPK +" FROM " + TABEL_PPK + " WHERE " + ID_PPK + " = " + idpk;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                ppk = cursor.getString(cursor.getColumnIndex(NAMA_PPK));
            } while (cursor.moveToNext());
        }
        db.close();
        return ppk;
    }

    public List<Ppk_Class> allDataPPK(){
        List<Ppk_Class> pkkArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_PPK + " ORDER BY idpk DESC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Ppk_Class pkk = new Ppk_Class();
                pkk.setID(cursor.getInt(cursor.getColumnIndex("idpk")));
                pkk.setNPK(cursor.getString(cursor.getColumnIndex(NAMA_PPK)));
                pkk.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                pkkArrayList.add(pkk);
            } while (cursor.moveToNext());
        }
        db.close();
        return pkkArrayList;
    }

    public String getPPKUpload(int idpk){
        String upl = "-";
        String query = "SELECT "+ UPLOAD +" FROM " + TABEL_PPK + " WHERE " + ID_PPK + " = " + idpk;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    public Boolean allDataPPKWhere(String text){
        String query = "SELECT * FROM " + TABEL_PPK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Boolean hasil = false;

        if(cursor.moveToFirst()){
            do{
                if(text.equals(cursor.getString(cursor.getColumnIndex(NAMA_PPK)))){
                    hasil = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return hasil;
    }

    public Integer getLastId(){
        int id = 0;
        String query = "SELECT "+ ID_PPK +" FROM " + TABEL_PPK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(ID_PPK));
            } while (cursor.moveToNext());
        }
        db.close();
        return id;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel Prov
    public Boolean insertDataProv(String idpro, String npro, String upl, int hps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PROV, idpro);
        contentValues.put(NAMA_PRO, npro);
        contentValues.put(UPLOAD, upl);
        contentValues.put(HAPUS, hps);

        long result = db.insert(TABEL_PRO, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateDataProv(String idpro, String npro, String upl, int hps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_PROV, idpro);
        contentValues.put(NAMA_PRO, npro);
        contentValues.put(UPLOAD, upl);
        contentValues.put(HAPUS, hps);

        long result = db.update(TABEL_PRO, contentValues, ID_PROV + " = '" + idpro + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public String getIdProv(String prov){
        String idpro = null;
        String query = "SELECT "+ ID_PROV +" FROM " + TABEL_PRO + " WHERE " + NAMA_PRO + " = '" + prov + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                idpro = cursor.getString(cursor.getColumnIndex(ID_PROV));
            } while (cursor.moveToNext());
        }
        db.close();
        return idpro;
    }

    public String getNamaProv(String idpro){
        String prov = null;
        String query = "SELECT "+ NAMA_PRO +" FROM " + TABEL_PRO + " WHERE " + ID_PROV + " LIKE '%" + idpro + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                prov = cursor.getString(cursor.getColumnIndex(NAMA_PRO));
            } while (cursor.moveToNext());
        }
        db.close();
        return prov;
    }

    public Boolean getNamaProv2(String nama){
        Boolean prov = false;
        String query = "SELECT "+ NAMA_PRO +","+ HAPUS +" FROM " + TABEL_PRO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(nama.equals(cursor.getString(cursor.getColumnIndex(NAMA_PRO)).toLowerCase()) &&
                        cursor.getInt(cursor.getColumnIndex(HAPUS)) == 0){
                    prov = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return prov;
    }

    public Boolean getIdProv2(String id){
        Boolean prov = false;
        String query = "SELECT "+ ID_PROV +","+ HAPUS +" FROM " + TABEL_PRO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(id.equals(cursor.getString(cursor.getColumnIndex(ID_PROV))) &&
                        cursor.getInt(cursor.getColumnIndex(HAPUS)) == 0){
                    prov = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return prov;
    }

    public List<Pro_Class> allDataPRO(){
        List<Pro_Class> proArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_PRO + " WHERE " + HAPUS + " = 0 ORDER BY idpro ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Pro_Class pro = new Pro_Class();
                pro.setID(cursor.getString(cursor.getColumnIndex(ID_PROV)));
                pro.setNPRO(cursor.getString(cursor.getColumnIndex(NAMA_PRO)));
                pro.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                pro.setHAPUS(cursor.getInt(cursor.getColumnIndex(HAPUS)));
                proArrayList.add(pro);
            } while (cursor.moveToNext());
        }
        db.close();
        return proArrayList;
    }

    public Boolean allDataPROWhere(String text){
        String query = "SELECT * FROM " + TABEL_PRO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Boolean hasil = false;

        if(cursor.moveToFirst()){
            do{
                if(text.equals(cursor.getString(cursor.getColumnIndex(NAMA_PRO)))){
                    hasil = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return hasil;
    }

    public List<Pro_Class> allDataPROOrder(String ord){
        List<Pro_Class> proArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_PRO + " WHERE " + HAPUS + " = 0 ORDER BY "+NAMA_PRO+" "+ord+"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Boolean hasil = false;

        if(cursor.moveToFirst()){
            do{
                Pro_Class pro = new Pro_Class();
                pro.setID(cursor.getString(cursor.getColumnIndex(ID_PROV)));
                pro.setNPRO(cursor.getString(cursor.getColumnIndex(NAMA_PRO)));
                pro.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                pro.setHAPUS(cursor.getInt(cursor.getColumnIndex(HAPUS)));
                proArrayList.add(pro);
            } while (cursor.moveToNext());
        }
        db.close();
        return proArrayList;
    }

    public String getProvUpload(String idpro){
        String upl = "-";
        String query = "SELECT "+ UPLOAD +" FROM " + TABEL_PRO + " WHERE " + ID_PROV + " = '" + idpro + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel Klas
    public Boolean insertDataKlas(String idklas, String sjr, String sts, String fng, String kpr,
                                  String kpg, String mjl, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_KLAS, idklas);
        contentValues.put(SISTEM_JARINGAN, sjr);
        contentValues.put(STATUS, sts);
        contentValues.put(FUNGSI, fng);
        contentValues.put(KELAS_PRASARANA, kpr);
        contentValues.put(KELAS_PENGGUNAAN, kpg);
        contentValues.put(MEDAN_JALAN, mjl);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_KLAS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public String getSistemJaringan(String idklas){
        String sjr = "-";
        String query = "SELECT "+ SISTEM_JARINGAN +" FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " LIKE '%" + idklas + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                sjr = cursor.getString(cursor.getColumnIndex(SISTEM_JARINGAN));
            } while (cursor.moveToNext());
        }
        db.close();
        return sjr;
    }

    public String getStatus(String idklas){
        String sts = "-";
        String query = "SELECT " + STATUS + " FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " LIKE '%" + idklas + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                sts = cursor.getString(cursor.getColumnIndex(STATUS));
            } while (cursor.moveToNext());
        }
        db.close();
        return sts;
    }

    public String getFungsi(String idklas){
        String fng = "-";
        String query = "SELECT " + FUNGSI + " FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " LIKE '%" + idklas + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                fng = cursor.getString(cursor.getColumnIndex(FUNGSI));
            } while (cursor.moveToNext());
        }
        db.close();
        return fng;
    }

    public String getKelasPrasarana(String idklas){
        String kpr = "-";
        String query = "SELECT " + KELAS_PRASARANA + " FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " LIKE '%" + idklas + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                kpr = cursor.getString(cursor.getColumnIndex(KELAS_PRASARANA));
            } while (cursor.moveToNext());
        }
        db.close();
        return kpr;
    }

    public String getKelasPenggunaan(String idklas){
        String kpg = "-";
        String query = "SELECT " + KELAS_PENGGUNAAN + " FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " LIKE '%" + idklas + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                kpg = cursor.getString(cursor.getColumnIndex(KELAS_PENGGUNAAN));
            } while (cursor.moveToNext());
        }
        db.close();
        return kpg;
    }

    public String getMedanJalan(String idklas){
        String mjl = "-";
        String query = "SELECT " + MEDAN_JALAN + " FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " LIKE '%" + idklas + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mjl = cursor.getString(cursor.getColumnIndex(MEDAN_JALAN));
            } while (cursor.moveToNext());
        }
        db.close();
        return mjl;
    }

    public String getKlasUpload(String idklas){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_KLAS + " WHERE " + ID_KLAS + " LIKE '%" + idklas + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    public List<Klas_Class> allDataKlas(String idklas){
        List<Klas_Class> klasArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_KLAS + " WHERE " + ID_PROV + " LIKE " + idklas;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Klas_Class klas = new Klas_Class();
                klas.setID(cursor.getString(cursor.getColumnIndex(ID_KLAS)));
                klas.setSJR(cursor.getString(cursor.getColumnIndex(SISTEM_JARINGAN)));
                klas.setSTS(cursor.getString(cursor.getColumnIndex(STATUS)));
                klas.setFNG(cursor.getString(cursor.getColumnIndex(FUNGSI)));
                klas.setKPR(cursor.getString(cursor.getColumnIndex(KELAS_PRASARANA)));
                klas.setKPG(cursor.getString(cursor.getColumnIndex(KELAS_PENGGUNAAN)));
                klas.setMJL(cursor.getString(cursor.getColumnIndex(MEDAN_JALAN)));
                klas.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                klasArrayList.add(klas);
            } while (cursor.moveToNext());
        }
        db.close();
        return klasArrayList;
    }

    public Boolean updateDataKlas(String idklas, String sjr, String sts, String fng, String kpr,
                                  String kpg, String mjl, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_KLAS, idklas);
        contentValues.put(SISTEM_JARINGAN, sjr);
        contentValues.put(STATUS, sts);
        contentValues.put(FUNGSI, fng);
        contentValues.put(KELAS_PRASARANA, kpr);
        contentValues.put(KELAS_PENGGUNAAN, kpg);
        contentValues.put(MEDAN_JALAN, mjl);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_KLAS, contentValues, ID_KLAS + " LIKE '%" + idklas + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel Ruas
    public Boolean insertDataRuas(String iru, String pjl, String nrs, String nor, double pru, String dkm,
                                  String kkm, double kcp, int hps, int idpk, String idpro, String idklas, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_RUAS, iru);
        contentValues.put(PENYELENGGARA, pjl);
        contentValues.put(NAMA_RUAS, nrs);
        contentValues.put(NOMOR_RUAS, nor);
        contentValues.put(PANJANG_RUAS, pru);
        contentValues.put(DKM, dkm);
        contentValues.put(KKM, kkm);
        contentValues.put(KECEPATAN, kcp);
        contentValues.put(HAPUS, hps);
        contentValues.put(ID_PPK, idpk);
        contentValues.put(ID_PROV, idpro);
        contentValues.put(ID_KLAS, idklas);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_RUAS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<Ruas_Class> allDataRuas(String idpro){
        List<Ruas_Class> ruasArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_RUAS + " WHERE " + ID_PROV + " = '" + idpro +"' AND " + HAPUS + " = 0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Ruas_Class ruas = new Ruas_Class();
                ruas.setIRU(cursor.getString(cursor.getColumnIndex(ID_RUAS)));
                ruas.setPJL(cursor.getString(cursor.getColumnIndex(PENYELENGGARA)));
                ruas.setNRU(cursor.getString(cursor.getColumnIndex(NAMA_RUAS)));
                ruas.setNOR(cursor.getString(cursor.getColumnIndex(NOMOR_RUAS)));
                ruas.setPRU(cursor.getDouble(cursor.getColumnIndex(PANJANG_RUAS)));
                ruas.setDKM(cursor.getString(cursor.getColumnIndex(DKM)));
                ruas.setKKM(cursor.getString(cursor.getColumnIndex(KKM)));
                ruas.setKCP(cursor.getDouble(cursor.getColumnIndex(KECEPATAN)));
                ruas.setHPS(cursor.getInt(cursor.getColumnIndex(HAPUS)));
                ruas.setIDPPK(cursor.getInt(cursor.getColumnIndex(ID_PPK)));
                ruas.setIDKLAS(cursor.getString(cursor.getColumnIndex(ID_KLAS)));
                ruas.setIDPRO(cursor.getString(cursor.getColumnIndex(ID_PROV)));
                ruas.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                ruasArrayList.add(ruas);
            } while (cursor.moveToNext());
        }
        db.close();
        return ruasArrayList;
    }

    public Boolean deleteDataRuas(String iru, int hps, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HAPUS, hps);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_RUAS, contentValues, ID_RUAS + " = '" + iru + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateDataRuas(String iru, String pjl, String nrs, String nor, double pru, String dkm,
                                  String kkm, double kcp, int hps, int idpk, String idpro, String idklas, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PENYELENGGARA, pjl);
        contentValues.put(NAMA_RUAS, nrs);
        contentValues.put(NOMOR_RUAS, nor);
        contentValues.put(PANJANG_RUAS, pru);
        contentValues.put(DKM, dkm);
        contentValues.put(KKM, kkm);
        contentValues.put(KECEPATAN, kcp);
        contentValues.put(HAPUS, hps);
        contentValues.put(ID_PPK, idpk);
        contentValues.put(ID_PROV, idpro);
        contentValues.put(ID_KLAS, idklas);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_RUAS, contentValues, ID_RUAS + " = '" + iru + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public String getRuasUpload(String iru){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_RUAS + " WHERE " + ID_RUAS + " = '" + iru + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    public Double getKecepatan(String iru){
        double kcp = 0;
        String query = "SELECT " + KECEPATAN + " FROM " + TABEL_RUAS + " WHERE " + ID_RUAS + " = '" + iru + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                kcp = cursor.getDouble(cursor.getColumnIndex(KECEPATAN));
            } while (cursor.moveToNext());
        }
        db.close();
        return kcp;
    }

    public Boolean getNamaRuas(String nama){
        Boolean ruas = false;
        String query = "SELECT "+ NAMA_RUAS +","+ HAPUS +" FROM " + TABEL_RUAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(nama.equals(cursor.getString(cursor.getColumnIndex(NAMA_RUAS)).toLowerCase()) &&
                        cursor.getInt(cursor.getColumnIndex(HAPUS)) == 0){
                    ruas = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();

        return ruas;
    }

    public Boolean getNomorRuas(String nomor, String idpro){
        Boolean ruas = false;
        String query = "SELECT "+ NOMOR_RUAS +","+ HAPUS +" FROM " + TABEL_RUAS +" WHERE "+ ID_PROV + " = '"+idpro+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(nomor.equals(cursor.getString(cursor.getColumnIndex(NOMOR_RUAS)).toLowerCase()) &&
                        cursor.getInt(cursor.getColumnIndex(HAPUS)) == 0){
                    ruas = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();

        return ruas;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel Segmen
    public Boolean insertDataSegmen(String iseg, String iru, String seg, double psg, String dpsg, String kpsg, String dkt, int hps, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(ID_RUAS, iru);
        contentValues.put(SEGMEN, seg);
        contentValues.put(PANJANG_SEGMEN, psg);
        contentValues.put(DPSG, dpsg);
        contentValues.put(KPSG, kpsg);
        contentValues.put(KOTA, dkt);
        contentValues.put(HAPUS, hps);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_SEGMEN, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<Segmen_Class> allDataSegmen(String iru, String nru){
        List<Segmen_Class> segmenArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_SEGMEN + " WHERE " + ID_RUAS + " = '" + iru + "' AND " + HAPUS + " = 0 ORDER BY "+ ID_SEGMEN +" ASC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Segmen_Class segmen = new Segmen_Class();
                segmen.setNRU(nru);
                segmen.setIRU(cursor.getString(cursor.getColumnIndex(ID_RUAS)));
                segmen.setIDS(cursor.getString(cursor.getColumnIndex(ID_SEGMEN)));
                segmen.setSEG(cursor.getString(cursor.getColumnIndex(SEGMEN)));
                segmen.setPSG(cursor.getDouble(cursor.getColumnIndex(PANJANG_SEGMEN)));
                segmen.setDPSG(cursor.getString(cursor.getColumnIndex(DPSG)));
                segmen.setKPSG(cursor.getString(cursor.getColumnIndex(KPSG)));
                segmen.setDKT(cursor.getString(cursor.getColumnIndex(KOTA)));
                segmen.setHPS(cursor.getInt(cursor.getColumnIndex(HAPUS)));
                segmen.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                segmenArrayList.add(segmen);
            } while (cursor.moveToNext());
        }
        db.close();
        return segmenArrayList;
    }

    public Boolean deleteDataSegmen(String iseg, int hps, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HAPUS, hps);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_SEGMEN, contentValues, ID_SEGMEN + " = '" + iseg + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateDataSegmen(String iseg, String iru, String seg, double psg, String dpsg, String kpsg, String dkt, int hps, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(ID_RUAS, iru);
        contentValues.put(SEGMEN, seg);
        contentValues.put(PANJANG_SEGMEN, psg);
        contentValues.put(DPSG, dpsg);
        contentValues.put(KPSG, kpsg);
        contentValues.put(KOTA, dkt);
        contentValues.put(HAPUS, hps);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_SEGMEN, contentValues, ID_SEGMEN + " = '" + iseg + "'", null);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public String getSegmenUpload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_SEGMEN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    public Boolean getSegmenNomor(String segmen, String iru){
        Boolean seg = false;
        String query = "SELECT "+ SEGMEN +","+ HAPUS +" FROM " + TABEL_SEGMEN + " WHERE " + ID_RUAS + " = '"+iru+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                if(segmen.equals(cursor.getString(cursor.getColumnIndex(SEGMEN))) &&
                        cursor.getInt(cursor.getColumnIndex(HAPUS)) == 0){
                    seg = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return seg;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel Admin
    public Boolean insertDataAdmin(String id, String iseg, String ktd, String kli, String lgt, String rec, String ktd2, String kli2, String lgt2, String rec2,
                                   String ktd3, String kli3, String lgt3, String rec3, String ktd4, String kli4, String lgt4, String rec4, String ktd5, String kli5, String lgt5, String rec5,
                                   String ktd6, String kli6, String lgt6, String rec6, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KETERSEDIAAN, ktd);
        contentValues.put(KELENGKAPAN, kli);
        contentValues.put(LEGALITAS, lgt);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(KETERSEDIAAN2, ktd2);
        contentValues.put(KELENGKAPAN2, kli2);
        contentValues.put(LEGALITAS2, lgt2);
        contentValues.put(RECOMENDASI2, rec2);
        contentValues.put(KETERSEDIAAN3, ktd3);
        contentValues.put(KELENGKAPAN3, kli3);
        contentValues.put(LEGALITAS3, lgt3);
        contentValues.put(RECOMENDASI3, rec3);
        contentValues.put(KETERSEDIAAN4, ktd4);
        contentValues.put(KELENGKAPAN4, kli4);
        contentValues.put(LEGALITAS4, lgt4);
        contentValues.put(RECOMENDASI4, rec4);
        contentValues.put(KETERSEDIAAN5, ktd5);
        contentValues.put(KELENGKAPAN5, kli5);
        contentValues.put(LEGALITAS5, lgt5);
        contentValues.put(RECOMENDASI5, rec5);
        contentValues.put(KETERSEDIAAN6, ktd6);
        contentValues.put(KELENGKAPAN6, kli6);
        contentValues.put(LEGALITAS6, lgt6);
        contentValues.put(RECOMENDASI6, rec6);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ADMIN, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<Administrasi_Class> allDataAdmin(String iseg) {
        List<Administrasi_Class> adminArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ADMIN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Administrasi_Class admin = new Administrasi_Class();
                admin.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                admin.setKTD(cursor.getString(cursor.getColumnIndex(KETERSEDIAAN)));
                admin.setKLI(cursor.getString(cursor.getColumnIndex(KELENGKAPAN)));
                admin.setLGT(cursor.getString(cursor.getColumnIndex(LEGALITAS)));
                admin.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                admin.setKTD2(cursor.getString(cursor.getColumnIndex(KETERSEDIAAN2)));
                admin.setKLI2(cursor.getString(cursor.getColumnIndex(KELENGKAPAN2)));
                admin.setLGT2(cursor.getString(cursor.getColumnIndex(LEGALITAS2)));
                admin.setREC2(cursor.getString(cursor.getColumnIndex(RECOMENDASI2)));
                admin.setKTD3(cursor.getString(cursor.getColumnIndex(KETERSEDIAAN3)));
                admin.setKLI3(cursor.getString(cursor.getColumnIndex(KELENGKAPAN3)));
                admin.setLGT3(cursor.getString(cursor.getColumnIndex(LEGALITAS3)));
                admin.setREC3(cursor.getString(cursor.getColumnIndex(RECOMENDASI3)));
                admin.setKTD4(cursor.getString(cursor.getColumnIndex(KETERSEDIAAN4)));
                admin.setKLI4(cursor.getString(cursor.getColumnIndex(KELENGKAPAN4)));
                admin.setLGT4(cursor.getString(cursor.getColumnIndex(LEGALITAS4)));
                admin.setREC4(cursor.getString(cursor.getColumnIndex(RECOMENDASI4)));
                admin.setKTD5(cursor.getString(cursor.getColumnIndex(KETERSEDIAAN5)));
                admin.setKLI5(cursor.getString(cursor.getColumnIndex(KELENGKAPAN5)));
                admin.setLGT5(cursor.getString(cursor.getColumnIndex(LEGALITAS5)));
                admin.setREC5(cursor.getString(cursor.getColumnIndex(RECOMENDASI5)));
                admin.setKTD6(cursor.getString(cursor.getColumnIndex(KETERSEDIAAN6)));
                admin.setKLI6(cursor.getString(cursor.getColumnIndex(KELENGKAPAN6)));
                admin.setLGT6(cursor.getString(cursor.getColumnIndex(LEGALITAS6)));
                admin.setREC6(cursor.getString(cursor.getColumnIndex(RECOMENDASI6)));
                admin.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                adminArrayList.add(admin);
            } while (cursor.moveToNext());
        }
        db.close();
        return adminArrayList;

    }

    public Boolean updateDataAdmin(String id, String iseg, String ktd, String kli, String lgt, String rec, String ktd2, String kli2, String lgt2, String rec2,
                                   String ktd3, String kli3, String lgt3, String rec3, String ktd4, String kli4, String lgt4, String rec4, String ktd5, String kli5, String lgt5, String rec5,
                                   String ktd6, String kli6, String lgt6, String rec6, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KETERSEDIAAN, ktd);
        contentValues.put(KELENGKAPAN, kli);
        contentValues.put(LEGALITAS, lgt);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(KETERSEDIAAN2, ktd2);
        contentValues.put(KELENGKAPAN2, kli2);
        contentValues.put(LEGALITAS2, lgt2);
        contentValues.put(RECOMENDASI2, rec2);
        contentValues.put(KETERSEDIAAN3, ktd3);
        contentValues.put(KELENGKAPAN3, kli3);
        contentValues.put(LEGALITAS3, lgt3);
        contentValues.put(RECOMENDASI3, rec3);
        contentValues.put(KETERSEDIAAN4, ktd4);
        contentValues.put(KELENGKAPAN4, kli4);
        contentValues.put(LEGALITAS4, lgt4);
        contentValues.put(RECOMENDASI4, rec4);
        contentValues.put(KETERSEDIAAN5, ktd5);
        contentValues.put(KELENGKAPAN5, kli5);
        contentValues.put(LEGALITAS5, lgt5);
        contentValues.put(RECOMENDASI5, rec5);
        contentValues.put(KETERSEDIAAN6, ktd6);
        contentValues.put(KELENGKAPAN6, kli6);
        contentValues.put(LEGALITAS6, lgt6);
        contentValues.put(RECOMENDASI6, rec6);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ADMIN, contentValues, ID_SEGMEN + " = '" + iseg + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getAdminUpload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ADMIN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel Compress
    public Boolean insertDataCompress(String idp, String nru, String iseg, String tbl, String diro, String dirc, String comp, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COM_ID_POINT, idp);
        contentValues.put(COM_NRU, nru);
        contentValues.put(COM_ISEG, iseg);
        contentValues.put(COM_TABEL, tbl);
        contentValues.put(COM_DIR_ORI, diro);
        contentValues.put(COM_DIR_COM, dirc);
        contentValues.put(COM_COMPRESS, comp);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_COMPRESS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<Compress_Class> allDataCompress(String tbl){
        List<Compress_Class> compressArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_COMPRESS + " WHERE " + COM_TABEL + " = '" + tbl + "' AND " + COM_COMPRESS + " = 'F' AND " + UPLOAD + " = 'F' ORDER BY "+ COM_ID_POINT +" ASC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Compress_Class compress = new Compress_Class();
                compress.setIDP(cursor.getString(cursor.getColumnIndex(COM_ID_POINT)));
                compress.setNRU(cursor.getString(cursor.getColumnIndex(COM_NRU)));
                compress.setISEG(cursor.getString(cursor.getColumnIndex(COM_ISEG)));
                compress.setTABEL(cursor.getString(cursor.getColumnIndex(COM_TABEL)));
                compress.setDIRO(cursor.getString(cursor.getColumnIndex(COM_DIR_ORI)));
                compress.setDIRC(cursor.getString(cursor.getColumnIndex(COM_DIR_COM)));
                compress.setCOMP(cursor.getString(cursor.getColumnIndex(COM_COMPRESS)));
                compress.setUPL(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                compressArrayList.add(compress);
            } while (cursor.moveToNext());
        }
        db.close();
        return compressArrayList;
    }

    public Boolean updateDataCompress(String idp, String nru, String iseg, String tbl, String diro, String dirc, String comp, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COM_ID_POINT, idp);
        contentValues.put(COM_NRU, nru);
        contentValues.put(COM_ISEG, iseg);
        contentValues.put(COM_TABEL, tbl);
        contentValues.put(COM_DIR_ORI, diro);
        contentValues.put(COM_DIR_COM, dirc);
        contentValues.put(COM_COMPRESS, comp);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_COMPRESS, contentValues, COM_ID_POINT + " = '" + idp + "'", null);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public String getCompressUpload(String idp){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_COMPRESS + " WHERE " + COM_ID_POINT + " = '" + idp + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A111
    public Boolean insertDataA111(String id, String iseg, String fjl, double all, double jlr, double lsl, double kjl, double kml,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(FUNGSI_JALAN, fjl);
        contentValues.put(ARUS_DILAYANI, all);
        contentValues.put(JUMLAH_LAJUR, jlr);
        contentValues.put(LEBAR_LAJUR, lsl);
        contentValues.put(KESERAGAMAN_LAJUR, kjl);
        contentValues.put(KEMIRINGAN_MELINTANG, kml);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASSS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A111_Class> allDataA111(String iseg) {
        List<A111_Class> a111ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASSS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A111_Class a111 = new A111_Class();
                a111.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a111.setFJL(cursor.getString(cursor.getColumnIndex(FUNGSI_JALAN)));
                a111.setALL(cursor.getDouble(cursor.getColumnIndex(ARUS_DILAYANI)));
                a111.setJLA(cursor.getDouble(cursor.getColumnIndex(JUMLAH_LAJUR)));
                a111.setLSL(cursor.getDouble(cursor.getColumnIndex(LEBAR_LAJUR)));
                a111.setKLL(cursor.getDouble(cursor.getColumnIndex(KESERAGAMAN_LAJUR)));
                a111.setKML(cursor.getDouble(cursor.getColumnIndex(KEMIRINGAN_MELINTANG)));
                a111.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a111.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a111.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a111.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a111.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a111ArrayList.add(a111);
            } while (cursor.moveToNext());
        }
        db.close();
        return a111ArrayList;

    }

    public Boolean updateDataA111(String id, String iseg, String fjl, double all, double jlr, double lsl, double kjl, double kml,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(FUNGSI_JALAN, fjl);
        contentValues.put(ARUS_DILAYANI, all);
        contentValues.put(JUMLAH_LAJUR, jlr);
        contentValues.put(LEBAR_LAJUR, lsl);
        contentValues.put(KESERAGAMAN_LAJUR, kjl);
        contentValues.put(KEMIRINGAN_MELINTANG, kml);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASSS, contentValues, ID_SEGMEN + " = '" + iseg + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    /*public Boolean updateDataA111FungsiJalan(String id, String iseg, String fjl, double all, double jlr, double lsl, double kjl, double kml,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(FUNGSI_JALAN, fjl);

        long result = db.update(TABEL_ASSS, contentValues, ID_SEGMEN + " = '" + iseg + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }*/

    public String getA111Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASSS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A112
    public Boolean insertDataA112(String id, String iseg, double lbb, double klb, double pkb, double pmb, double kmb,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_BAHU, lbb);
        contentValues.put(KESERAGAMAN_BAHU, klb);
        contentValues.put(PERKERASAN_BAHU, pkb);
        contentValues.put(POSISI_BAHU, pmb);
        contentValues.put(KEMIRINGAN_BAHU, kmb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASSD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A112_Class> allDataA112(String iseg) {
        List<A112_Class> a112ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASSD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A112_Class a112 = new A112_Class();
                a112.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a112.setLBB(cursor.getDouble(cursor.getColumnIndex(LEBAR_BAHU)));
                a112.setKLB(cursor.getDouble(cursor.getColumnIndex(KESERAGAMAN_BAHU)));
                a112.setPKB(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_BAHU)));
                a112.setPMB(cursor.getDouble(cursor.getColumnIndex(POSISI_BAHU)));
                a112.setKMB(cursor.getDouble(cursor.getColumnIndex(KEMIRINGAN_BAHU)));
                a112.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a112.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a112.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a112.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a112ArrayList.add(a112);
            } while (cursor.moveToNext());
        }

        db.close();
        return a112ArrayList;

    }

    public Boolean updateDataA112(String id, String iseg, double lbb, double klb, double pkb, double pmb, double kmb,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_BAHU, lbb);
        contentValues.put(KESERAGAMAN_BAHU, klb);
        contentValues.put(PERKERASAN_BAHU, pkb);
        contentValues.put(POSISI_BAHU, pmb);
        contentValues.put(KEMIRINGAN_BAHU, kmb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASSD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA112Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASSD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A113
    public Boolean insertDataA113(String id, String iseg, String lbmt, double lbm, String tpmt, double tpm, String pkmt, double pkm, double jab, double lbb,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_MEDIAN_TP, lbmt);
        contentValues.put(LEBAR_MEDIAN, lbm);
        contentValues.put(TIPE_MEDIAN_TP, tpmt);
        contentValues.put(TIPE_MEDIAN, tpm);
        contentValues.put(PERKERASAN_MEDIAN_TP, pkmt);
        contentValues.put(PERKERASAN_MEDIAN, pkm);
        contentValues.put(JARAK_BUKAAN, jab);
        contentValues.put(LEBAR_BUKAAN, lbb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASST, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A113_Class> allDataA113(String iseg) {
        List<A113_Class> a113ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASST + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A113_Class a113 = new A113_Class();
                a113.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a113.setLBMT(cursor.getString(cursor.getColumnIndex(LEBAR_MEDIAN_TP)));
                a113.setLBM(cursor.getDouble(cursor.getColumnIndex(LEBAR_MEDIAN)));
                a113.setTPMT(cursor.getString(cursor.getColumnIndex(TIPE_MEDIAN_TP)));
                a113.setTPM(cursor.getDouble(cursor.getColumnIndex(TIPE_MEDIAN)));
                a113.setPKMT(cursor.getString(cursor.getColumnIndex(PERKERASAN_MEDIAN_TP)));
                a113.setPKM(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_MEDIAN)));
                a113.setJAB(cursor.getDouble(cursor.getColumnIndex(JARAK_BUKAAN)));
                a113.setLBB(cursor.getDouble(cursor.getColumnIndex(LEBAR_BUKAAN)));
                a113.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a113.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a113.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a113.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a113.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a113ArrayList.add(a113);
            } while (cursor.moveToNext());
        }

        db.close();
        return a113ArrayList;

    }

    public Boolean updateDataA113(String id, String iseg, String lbmt, double lbm, String tpmt, double tpm, String pkmt, double pkm, double jab, double lbb,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_MEDIAN_TP, lbmt);
        contentValues.put(LEBAR_MEDIAN, lbm);
        contentValues.put(TIPE_MEDIAN_TP, tpmt);
        contentValues.put(TIPE_MEDIAN, tpm);
        contentValues.put(PERKERASAN_MEDIAN_TP, pkmt);
        contentValues.put(PERKERASAN_MEDIAN, pkm);
        contentValues.put(JARAK_BUKAAN, jab);
        contentValues.put(LEBAR_BUKAAN, lbb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASST,  contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA113Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASST + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A114
    public Boolean insertDataA114(String id, String iseg, double lss, String bss1, double bss2, double fma,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_SELOKAN, lss);
        contentValues.put(BENTUK_SELOKAN1, bss1);
        contentValues.put(BENTUK_SELOKAN2, bss2);
        contentValues.put(FUNGSI_AIR, fma);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASSE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A114_Class> allDataA114(String iseg) {
        List<A114_Class> a114ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASSE + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A114_Class a114 = new A114_Class();
                a114.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a114.setLSS(cursor.getDouble(cursor.getColumnIndex(LEBAR_SELOKAN)));
                a114.setBSS1(cursor.getString(cursor.getColumnIndex(BENTUK_SELOKAN1)));
                a114.setBSS2(cursor.getDouble(cursor.getColumnIndex(BENTUK_SELOKAN2)));
                a114.setFMA(cursor.getDouble(cursor.getColumnIndex(FUNGSI_AIR)));
                a114.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a114.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a114.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a114.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a114ArrayList.add(a114);

            } while (cursor.moveToNext());
        }

        db.close();
        return a114ArrayList;

    }

    public Boolean updateDataA114(String id, String iseg, double lss, String bss1, double bss2, double fma,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_SELOKAN, lss);
        contentValues.put(BENTUK_SELOKAN1, bss1);
        contentValues.put(BENTUK_SELOKAN2, bss2);
        contentValues.put(FUNGSI_AIR, fma);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASSE, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA114Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASSE + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A115
    public Boolean insertDataA115(String id, String iseg, double lap, double lap2, String pkj, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_AMBANG, lap);
        contentValues.put(LEBAR_AMBANG2, lap2);
        contentValues.put(PENGAMANAN_KONSTRUKSI, pkj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASSL, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A115_Class> allDataA115(String iseg) {
        List<A115_Class> a115ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASSL + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A115_Class a115 = new A115_Class();
                a115.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a115.setLAP(cursor.getDouble(cursor.getColumnIndex(LEBAR_AMBANG)));
                a115.setLAP2(cursor.getDouble(cursor.getColumnIndex(LEBAR_AMBANG2)));
                a115.setPKJ(cursor.getString(cursor.getColumnIndex(PENGAMANAN_KONSTRUKSI)));
                a115.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a115.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a115.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a115.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a115ArrayList.add(a115);

            } while (cursor.moveToNext());
        }

        db.close();
        return a115ArrayList;

    }

    public Boolean updateDataA115(String id, String iseg, double lap, double lap2, String pkj, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_AMBANG, lap);
        contentValues.put(LEBAR_AMBANG2, lap2);
        contentValues.put(PENGAMANAN_KONSTRUKSI, pkj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASSL, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);

        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA115Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASSL + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A116
    public Boolean insertDataA116(String id, String iseg, double jtj, double tvm, double ktv, double jtv, double ttr,
                                  double jtj2, String mtb, double tmt, double bgt,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JARAK_MARKA, jtj);
        contentValues.put(TINGGI_VERTIKAL, tvm);
        contentValues.put(KEDALAMAN_VERTIKAL, ktv);
        contentValues.put(JARAK_VERTIKAL, jtv);
        contentValues.put(TANDA_REFLEKTOR, ttr);
        contentValues.put(JARAK_MARKA2, jtj2);
        contentValues.put(MUTU_BETON, mtb);
        contentValues.put(TINGGI_MUKA, tmt);
        contentValues.put(BAGIAN_TERTANAM, bgt);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASSEN, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A116_Class> allDataA116(String iseg) {
        List<A116_Class> a116ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASSEN + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A116_Class a116 = new A116_Class();
                a116.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a116.setJTJ(cursor.getDouble(cursor.getColumnIndex(JARAK_MARKA)));
                a116.setTVM(cursor.getDouble(cursor.getColumnIndex(TINGGI_VERTIKAL)));
                a116.setKTV(cursor.getDouble(cursor.getColumnIndex(KEDALAMAN_VERTIKAL)));
                a116.setJTV(cursor.getDouble(cursor.getColumnIndex(JARAK_VERTIKAL)));
                a116.setTTR(cursor.getDouble(cursor.getColumnIndex(TANDA_REFLEKTOR)));
                a116.setJTJ2(cursor.getDouble(cursor.getColumnIndex(JARAK_MARKA2)));
                a116.setMTB(cursor.getString(cursor.getColumnIndex(MUTU_BETON)));
                a116.setTMT(cursor.getDouble(cursor.getColumnIndex(TINGGI_MUKA)));
                a116.setBGT(cursor.getDouble(cursor.getColumnIndex(BAGIAN_TERTANAM)));
                a116.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a116.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a116.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a116.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a116.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a116ArrayList.add(a116);

            } while (cursor.moveToNext());
        }

        db.close();
        return a116ArrayList;

    }

    public Boolean updateDataA116(String id, String iseg, double jtj, double tvm, double ktv, double jtv, double ttr,
                                  double jtj2, String mtb, double tmt, double bgt,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JARAK_MARKA, jtj);
        contentValues.put(TINGGI_VERTIKAL, tvm);
        contentValues.put(KEDALAMAN_VERTIKAL, ktv);
        contentValues.put(JARAK_VERTIKAL, jtv);
        contentValues.put(TANDA_REFLEKTOR, ttr);
        contentValues.put(JARAK_MARKA2, jtj2);
        contentValues.put(MUTU_BETON, mtb);
        contentValues.put(TINGGI_MUKA, tmt);
        contentValues.put(BAGIAN_TERTANAM, bgt);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASSEN, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA116Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASSEN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A121
    public Boolean insertDataA121(String id, String iseg, double pbj, String sjpd, double jph, double jpm, String slkj, double lkj,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(PANJANG_BAGIAN, pbj);
        contentValues.put(JARAK_PANDANG_SPIN, sjpd);
        contentValues.put(JARAK_PANDANG_H, jph);
        contentValues.put(JARAK_PANDANG_M, jpm);
        contentValues.put(LINGKUNGAN_JALAN_SPINNER, slkj);
        contentValues.put(LINGKUNGAN_JALAN, lkj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASDS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A121_Class> allDataA121(String iseg) {
        List<A121_Class> a121ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASDS + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A121_Class a121 = new A121_Class();
                a121.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a121.setPBJ(cursor.getDouble(cursor.getColumnIndex(PANJANG_BAGIAN)));
                a121.setSJPD(cursor.getString(cursor.getColumnIndex(JARAK_PANDANG_SPIN)));
                a121.setJPH(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_H)));
                a121.setJPM(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_M)));
                a121.setSLKJ(cursor.getString(cursor.getColumnIndex(LINGKUNGAN_JALAN_SPINNER)));
                a121.setLKJ(cursor.getDouble(cursor.getColumnIndex(LINGKUNGAN_JALAN)));
                a121.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a121.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a121.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a121.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a121ArrayList.add(a121);
            } while (cursor.moveToNext());
        }
        db.close();
        return a121ArrayList;

    }

    public Boolean updateDataA121(String id, String iseg, double pbj, String sjpd, double jph, double jpm, String slkj, double lkj,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(PANJANG_BAGIAN, pbj);
        contentValues.put(JARAK_PANDANG_SPIN, sjpd);
        contentValues.put(JARAK_PANDANG_H, jph);
        contentValues.put(JARAK_PANDANG_M, jpm);
        contentValues.put(LINGKUNGAN_JALAN_SPINNER, slkj);
        contentValues.put(LINGKUNGAN_JALAN, lkj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASDS, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA121Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASDS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A122
    public Boolean insertDataA122(String id, String iseg, String srdt, double rdt, double sup, double jpd,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(RADIUS_TIKUNGAN_SPIN, srdt);
        contentValues.put(RADIUS_TIKUNGAN, rdt);
        contentValues.put(SUPERELEVASI, sup);
        contentValues.put(JARAK_PANDANG_ASDD, jpd);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASDD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A122_Class> allDataA122(String iseg) {
        List<A122_Class> a122ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASDD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A122_Class a122 = new A122_Class();
                a122.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a122.setSRDT(cursor.getString(cursor.getColumnIndex(RADIUS_TIKUNGAN_SPIN)));
                a122.setRDT(cursor.getDouble(cursor.getColumnIndex(RADIUS_TIKUNGAN)));
                a122.setSUP(cursor.getDouble(cursor.getColumnIndex(SUPERELEVASI)));
                a122.setJPD(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_ASDD)));
                a122.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a122.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a122.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a122.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a122ArrayList.add(a122);
            } while (cursor.moveToNext());
        }
        db.close();
        return a122ArrayList;

    }

    public Boolean updateDataA122(String id, String iseg, String srdt, double rdt, double sup, double jpd,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(RADIUS_TIKUNGAN_SPIN, srdt);
        contentValues.put(RADIUS_TIKUNGAN, rdt);
        contentValues.put(SUPERELEVASI, sup);
        contentValues.put(JARAK_PANDANG_ASDD, jpd);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASDD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA122Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASDD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A123
    public Boolean insertDataA123(String id, String iseg, int jpk, String scaj,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JUMLAH_PERSIMPANGAN, jpk);
        contentValues.put(CARA_AKSES_JU, scaj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASDT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A123_Class> allDataA123(String iseg) {
        List<A123_Class> a123ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASDT + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A123_Class a123 = new A123_Class();
                a123.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a123.setJPK(cursor.getInt(cursor.getColumnIndex(JUMLAH_PERSIMPANGAN)));
                a123.setSCAJ(cursor.getString(cursor.getColumnIndex(CARA_AKSES_JU)));
                a123.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a123.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a123.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a123.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a123.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a123ArrayList.add(a123);
            } while (cursor.moveToNext());
        }
        db.close();
        return a123ArrayList;

    }

    public Boolean updateDataA123(String id, String iseg, int jpk, String scaj,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JUMLAH_PERSIMPANGAN, jpk);
        contentValues.put(CARA_AKSES_JU, scaj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASDT, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA123Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASDT + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A124
    public Boolean insertDataA124(String id, String iseg, int bap, String sajl, double bta,
                                  String rec, String dir1, String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(BANYAKNYA_AKSES_PERSIL, bap);
        contentValues.put(AKSES_JALAN_UTAMA, sajl);
        contentValues.put(BENTUK_AKSES, bta);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASDE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A124_Class> allDataA124(String iseg) {
        List<A124_Class> a124ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASDE + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A124_Class a124 = new A124_Class();
                a124.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a124.setBAP(cursor.getInt(cursor.getColumnIndex(BANYAKNYA_AKSES_PERSIL)));
                a124.setSAJL(cursor.getString(cursor.getColumnIndex(AKSES_JALAN_UTAMA)));
                a124.setBTA(cursor.getDouble(cursor.getColumnIndex(BENTUK_AKSES)));
                a124.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a124.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a124.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a124.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a124.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a124.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a124ArrayList.add(a124);
            } while (cursor.moveToNext());
        }
        db.close();
        return a124ArrayList;

    }

    public Boolean updateDataA124(String id, String iseg, int bap, String sajl, double bta,
                                  String rec, String dir1, String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(BANYAKNYA_AKSES_PERSIL, bap);
        contentValues.put(AKSES_JALAN_UTAMA, sajl);
        contentValues.put(BENTUK_AKSES, bta);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASDE, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA124Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASDE + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A131
    public Boolean insertDataA131(String id, String iseg, double klm, double plk, String sjpd, double jlm, double jph, double jpm, String slkj, double lkj,
                                  String rec, String dir1, String dir2, String dir3, String dir4, double kcp, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KELANDAIAN_MEMANJANG, klm);
        contentValues.put(PANJANG_LANDAI_KRITIS, plk);
        contentValues.put(JARAK_PANDANG_SPIN, sjpd);
        contentValues.put(JARAK_PANDANG_LANDAI_MAX, jlm);
        contentValues.put(JARAK_PANDANG_H_ASTS, jph);
        contentValues.put(JARAK_PANDANG_M_ASTS, jpm);
        contentValues.put(LINGKUNGAN_JALAN_SPINNER_ASTS, slkj);
        contentValues.put(LINGKUNGAN_JALAN_ASTS, lkj);
        contentValues.put(KECEPATAN, kcp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASTS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A131_Class> allDataA131(String iseg) {
        List<A131_Class> a131ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASTS + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A131_Class a131 = new A131_Class();
                a131.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a131.setKLM(cursor.getDouble(cursor.getColumnIndex(KELANDAIAN_MEMANJANG)));
                a131.setPLK(cursor.getDouble(cursor.getColumnIndex(PANJANG_LANDAI_KRITIS)));
                a131.setSJPD(cursor.getString(cursor.getColumnIndex(JARAK_PANDANG_SPIN)));
                a131.setJLM(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_LANDAI_MAX)));
                a131.setJPH(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_H_ASTS)));
                a131.setJPM(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_M_ASTS)));
                a131.setSLKJ(cursor.getString(cursor.getColumnIndex(LINGKUNGAN_JALAN_SPINNER_ASTS)));
                a131.setLKJ(cursor.getDouble(cursor.getColumnIndex(LINGKUNGAN_JALAN_ASTS)));
                a131.setKCP(cursor.getDouble(cursor.getColumnIndex(KECEPATAN)));
                a131.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a131.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a131.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a131.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a131.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a131.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a131ArrayList.add(a131);
            } while (cursor.moveToNext());
        }
        db.close();
        return a131ArrayList;

    }

    public Boolean updateDataA131(String id, String iseg, double klm, double plk, String sjpd, double jlm, double jph, double jpm, String slkj, double lkj,
                                  String rec, String dir1, String dir2, String dir3, String dir4, double kcp, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KELANDAIAN_MEMANJANG, klm);
        contentValues.put(PANJANG_LANDAI_KRITIS, plk);
        contentValues.put(JARAK_PANDANG_SPIN, sjpd);
        contentValues.put(JARAK_PANDANG_LANDAI_MAX, jlm);
        contentValues.put(JARAK_PANDANG_H_ASTS, jph);
        contentValues.put(JARAK_PANDANG_M_ASTS, jpm);
        contentValues.put(LINGKUNGAN_JALAN_SPINNER_ASTS, slkj);
        contentValues.put(LINGKUNGAN_JALAN_ASTS, lkj);
        contentValues.put(KECEPATAN, kcp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASTS, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA131Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASTS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A132
    public Boolean insertDataA132(String id, String iseg, String skkb, double lbl, double pja, double pjs, double pjl, double pjp, double pjs2, double tmk,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEPERLUAN_KEBERADAAN, skkb);
        contentValues.put(LEBAR_LAJUR_ASTD, lbl);
        contentValues.put(PANJANG_AWAL, pja);
        contentValues.put(PANJANG_SERONGAN_SATU, pjs);
        contentValues.put(PANJANG_BAGIAN_LURUS, pjl);
        contentValues.put(PANJANG_SETELAH_PUNCAK, pjp);
        contentValues.put(PANJANG_SERONGAN_DUA, pjs2);
        contentValues.put(TAPER_MASUK_KELUAR, tmk);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASTD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A132_Class> allDataA132(String iseg) {
        List<A132_Class> a132ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASTD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A132_Class a132 = new A132_Class();
                a132.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a132.setSKKB(cursor.getString(cursor.getColumnIndex(KEPERLUAN_KEBERADAAN)));
                a132.setLBL(cursor.getDouble(cursor.getColumnIndex(LEBAR_LAJUR_ASTD)));
                a132.setPJA(cursor.getDouble(cursor.getColumnIndex(PANJANG_AWAL)));
                a132.setPJS(cursor.getDouble(cursor.getColumnIndex(PANJANG_SERONGAN_SATU)));
                a132.setPJL(cursor.getDouble(cursor.getColumnIndex(PANJANG_BAGIAN_LURUS)));
                a132.setPJP(cursor.getDouble(cursor.getColumnIndex(PANJANG_SETELAH_PUNCAK)));
                a132.setPJS2(cursor.getDouble(cursor.getColumnIndex(PANJANG_SERONGAN_DUA)));
                a132.setTMK(cursor.getDouble(cursor.getColumnIndex(TAPER_MASUK_KELUAR)));
                a132.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a132.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a132.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a132.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a132ArrayList.add(a132);
            } while (cursor.moveToNext());
        }
        db.close();
        return a132ArrayList;

    }

    public Boolean updateDataA132(String id, String iseg, String skkb, double lbl, double pja, double pjs, double pjl, double pjp, double pjs2, double tmk,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEPERLUAN_KEBERADAAN, skkb);
        contentValues.put(LEBAR_LAJUR_ASTD, lbl);
        contentValues.put(PANJANG_AWAL, pja);
        contentValues.put(PANJANG_SERONGAN_SATU, pjs);
        contentValues.put(PANJANG_BAGIAN_LURUS, pjl);
        contentValues.put(PANJANG_SETELAH_PUNCAK, pjp);
        contentValues.put(PANJANG_SERONGAN_DUA, pjs2);
        contentValues.put(TAPER_MASUK_KELUAR, tmk);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASTD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA132Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASTD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A133
    public Boolean insertDataA133(String id, String iseg, double lkb, double klm, double kcb, double kck, String sjpd, double jlm, double jph,
                                  double jpm, double ajl, double ajl2, double kvh, double kvh2, double kvh3,
                                  double kvh4, double kvh5, String rec, String dir1, String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LENGKUNG_PARABOLA, lkb);
        contentValues.put(KETAJAMAN_LINGKUNGAN, klm);
        contentValues.put(KETAJAMAN_LINGKUNGAN_CEMBUNG, kcb);
        contentValues.put(KETAJAMAN_LINGKUNGAN_CEKUNG, kck);
        contentValues.put(JARAK_PANDANG_SPIN_ASTT, sjpd);
        contentValues.put(JARAK_PANDANG_LANDAI_MAX, jlm);
        contentValues.put(JARAK_PANDANG_H_ASTT, jph);
        contentValues.put(JARAK_PANDANG_M_ASTT, jpm);
        contentValues.put(ARAH_JALAN_SATU, ajl);
        contentValues.put(ARAH_JALAN_DUA, ajl2);
        contentValues.put(KOMBINASI_LENGKUNG_VH1, kvh);
        contentValues.put(KOMBINASI_LENGKUNG_VH2, kvh2);
        contentValues.put(KOMBINASI_LENGKUNG_VH3, kvh3);
        contentValues.put(KOMBINASI_LENGKUNG_VH4, kvh4);
        contentValues.put(KOMBINASI_LENGKUNG_VH5, kvh5);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASTT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A133_Class> allDataA133(String iseg) {
        List<A133_Class> a133ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASTT + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A133_Class a133 = new A133_Class();
                a133.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a133.setLKB(cursor.getDouble(cursor.getColumnIndex(LENGKUNG_PARABOLA)));
                a133.setKLM(cursor.getDouble(cursor.getColumnIndex(KETAJAMAN_LINGKUNGAN)));
                a133.setKCB(cursor.getDouble(cursor.getColumnIndex(KETAJAMAN_LINGKUNGAN_CEMBUNG)));
                a133.setKCK(cursor.getDouble(cursor.getColumnIndex(KETAJAMAN_LINGKUNGAN_CEKUNG)));
                a133.setSJPD(cursor.getString(cursor.getColumnIndex(JARAK_PANDANG_SPIN_ASTT)));
                a133.setJLM(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_LANDAI_MAX)));
                a133.setJPH(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_H_ASTT)));
                a133.setJPM(cursor.getDouble(cursor.getColumnIndex(JARAK_PANDANG_M_ASTT)));
                a133.setAJL(cursor.getDouble(cursor.getColumnIndex(ARAH_JALAN_SATU)));
                a133.setAJL2(cursor.getDouble(cursor.getColumnIndex(ARAH_JALAN_DUA)));
                a133.setKVH(cursor.getDouble(cursor.getColumnIndex(KOMBINASI_LENGKUNG_VH1)));
                a133.setKVH2(cursor.getDouble(cursor.getColumnIndex(KOMBINASI_LENGKUNG_VH2)));
                a133.setKVH3(cursor.getDouble(cursor.getColumnIndex(KOMBINASI_LENGKUNG_VH3)));
                a133.setKVH4(cursor.getDouble(cursor.getColumnIndex(KOMBINASI_LENGKUNG_VH4)));
                a133.setKVH5(cursor.getDouble(cursor.getColumnIndex(KOMBINASI_LENGKUNG_VH5)));
                a133.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a133.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a133.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a133.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a133.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a133.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a133ArrayList.add(a133);
            } while (cursor.moveToNext());
        }
        db.close();
        return a133ArrayList;

    }

    public Boolean updateDataA133(String id, String iseg, double lkb, double klm, double kcb, double kck, String sjpd, double jlm, double jph,
                                  double jpm, double ajl, double ajl2, double kvh, double kvh2, double kvh3,
                                  double kvh4, double kvh5, String rec, String dir1, String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LENGKUNG_PARABOLA, lkb);
        contentValues.put(KETAJAMAN_LINGKUNGAN, klm);
        contentValues.put(KETAJAMAN_LINGKUNGAN_CEMBUNG, kcb);
        contentValues.put(KETAJAMAN_LINGKUNGAN_CEKUNG, kck);
        contentValues.put(JARAK_PANDANG_SPIN_ASTT, sjpd);
        contentValues.put(JARAK_PANDANG_LANDAI_MAX, jlm);
        contentValues.put(JARAK_PANDANG_H_ASTT, jph);
        contentValues.put(JARAK_PANDANG_M_ASTT, jpm);
        contentValues.put(ARAH_JALAN_SATU, ajl);
        contentValues.put(ARAH_JALAN_DUA, ajl2);
        contentValues.put(KOMBINASI_LENGKUNG_VH1, kvh);
        contentValues.put(KOMBINASI_LENGKUNG_VH2, kvh2);
        contentValues.put(KOMBINASI_LENGKUNG_VH3, kvh3);
        contentValues.put(KOMBINASI_LENGKUNG_VH4, kvh4);
        contentValues.put(KOMBINASI_LENGKUNG_VH5, kvh5);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASTT, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA133Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASTT + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A141
    public Boolean insertDataA141(String id, String iseg, double okvl, double okvm,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(OVERLOADING_KURVA_LURUS, okvl);
        contentValues.put(OVERLOADING_KURVA_MENIKUNG, okvm);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ASES, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A141_Class> allDataA141(String iseg) {
        List<A141_Class> a141ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ASES + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A141_Class a141 = new A141_Class();
                a141.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a141.setOKVL(cursor.getDouble(cursor.getColumnIndex(OVERLOADING_KURVA_LURUS)));
                a141.setOKVM(cursor.getDouble(cursor.getColumnIndex(OVERLOADING_KURVA_MENIKUNG)));
                a141.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a141.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a141.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a141.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a141.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a141ArrayList.add(a141);
            } while (cursor.moveToNext());
        }
        db.close();
        return a141ArrayList;

    }

    public Boolean updateDataA141(String id, String iseg, double okvl, double okvm,
                                  String rec, String dir1, String dir2, String dir3, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(OVERLOADING_KURVA_LURUS, okvl);
        contentValues.put(OVERLOADING_KURVA_MENIKUNG, okvm);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASES, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA141Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ASES + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A21
    public Boolean insertDataA21(String id, String iseg, double ksp, String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KESESUAIAN_STRUKTUR, ksp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ADS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A21_Class> allDataA21(String iseg) {
        List<A21_Class> a21ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ADS + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A21_Class a21 = new A21_Class();
                a21.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a21.setKSP(cursor.getDouble(cursor.getColumnIndex(KESESUAIAN_STRUKTUR)));
                a21.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a21.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a21.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a21.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a21ArrayList.add(a21);
            } while (cursor.moveToNext());
        }
        db.close();
        return a21ArrayList;

    }

    public Boolean updateDataA21(String id, String iseg, double ksp, String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KESESUAIAN_STRUKTUR, ksp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ADS, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA21Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ADS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A22
    public Boolean insertDataA22(String id, String iseg, double kjl, double kdl, double kkl, double ilb, double lbr,
                                 double irt, double kal, double kam, double ial, double tpk, double asm,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KERATAAN_JALAN, kjl);
        contentValues.put(KEDALAMAN_LUBANG_D, kdl);
        contentValues.put(KEDALAMAN_LUBANG_K, kkl);
        contentValues.put(INTENSITAS_LUBANG, ilb);
        contentValues.put(LEBAR_RETAK, lbr);
        contentValues.put(INTENSITAS_RETAK, irt);
        contentValues.put(KERUSAKAN_ALUR_DANGKAL, kal);
        contentValues.put(KERUSAKAN_ALUR_DALAM, kam);
        contentValues.put(INTENSITAS_ALUR, ial);
        contentValues.put(TEKSTUR_PERKERASAN, tpk);
        contentValues.put(ASPAL_MELELEH, asm);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ADD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A22_Class> allDataA22(String iseg) {
        List<A22_Class> a22ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ADD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A22_Class a22 = new A22_Class();
                a22.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a22.setKJL(cursor.getDouble(cursor.getColumnIndex(KERATAAN_JALAN)));
                a22.setKDL(cursor.getDouble(cursor.getColumnIndex(KEDALAMAN_LUBANG_D)));
                a22.setKKL(cursor.getDouble(cursor.getColumnIndex(KEDALAMAN_LUBANG_K)));
                a22.setILB(cursor.getDouble(cursor.getColumnIndex(INTENSITAS_LUBANG)));
                a22.setLBR(cursor.getDouble(cursor.getColumnIndex(LEBAR_RETAK)));
                a22.setIRT(cursor.getDouble(cursor.getColumnIndex(INTENSITAS_RETAK)));
                a22.setKAL(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_ALUR_DANGKAL)));
                a22.setKAM(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_ALUR_DALAM)));
                a22.setIAL(cursor.getDouble(cursor.getColumnIndex(INTENSITAS_ALUR)));
                a22.setTPK(cursor.getDouble(cursor.getColumnIndex(TEKSTUR_PERKERASAN)));
                a22.setASM(cursor.getDouble(cursor.getColumnIndex(ASPAL_MELELEH)));
                a22.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a22.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a22.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a22.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a22ArrayList.add(a22);
            } while (cursor.moveToNext());
        }
        db.close();
        return a22ArrayList;

    }

    public Boolean updateDataA22(String id, String iseg, double kjl, double kdl, double kkl, double ilb, double lbr,
                                 double irt, double kal, double kam, double ial, double tpk, double asm,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KERATAAN_JALAN, kjl);
        contentValues.put(KEDALAMAN_LUBANG_D, kdl);
        contentValues.put(KEDALAMAN_LUBANG_K, kkl);
        contentValues.put(INTENSITAS_LUBANG, ilb);
        contentValues.put(LEBAR_RETAK, lbr);
        contentValues.put(INTENSITAS_RETAK, irt);
        contentValues.put(KERUSAKAN_ALUR_DANGKAL, kal);
        contentValues.put(KERUSAKAN_ALUR_DALAM, kam);
        contentValues.put(INTENSITAS_ALUR, ial);
        contentValues.put(TEKSTUR_PERKERASAN, tpk);
        contentValues.put(ASPAL_MELELEH, asm);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ADD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA22Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ADD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A23
    public Boolean insertDataA23(String id, String iseg, double pll, double kkt, double dpp, String sbpk,
                                 double bpk, String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(PEMERIKSAAN_LANJUT, pll);
        contentValues.put(KEKUATAN_KONSTRUKSI, kkt);
        contentValues.put(DRAINASE_PERMUKAAN, dpp);
        contentValues.put(BAHAN_PERKERASAN_SPIN, sbpk);
        contentValues.put(BAHAN_PERKERASAN, bpk);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ADT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A23_Class> allDataA23(String iseg) {
        List<A23_Class> a23ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ADT + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A23_Class a23 = new A23_Class();
                a23.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a23.setPLL(cursor.getDouble(cursor.getColumnIndex(PEMERIKSAAN_LANJUT)));
                a23.setKKT(cursor.getDouble(cursor.getColumnIndex(KEKUATAN_KONSTRUKSI)));
                a23.setDPP(cursor.getDouble(cursor.getColumnIndex(DRAINASE_PERMUKAAN)));
                a23.setSBPK(cursor.getString(cursor.getColumnIndex(BAHAN_PERKERASAN_SPIN)));
                a23.setBPK(cursor.getDouble(cursor.getColumnIndex(BAHAN_PERKERASAN)));
                a23.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a23.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a23.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a23.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a23ArrayList.add(a23);
            } while (cursor.moveToNext());
        }
        db.close();
        return a23ArrayList;

    }

    public Boolean updateDataA23(String id, String iseg, double pll, double kkt, double dpp, String sbpk,
                                 double bpk, String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(PEMERIKSAAN_LANJUT, pll);
        contentValues.put(KEKUATAN_KONSTRUKSI, kkt);
        contentValues.put(DRAINASE_PERMUKAAN, dpp);
        contentValues.put(BAHAN_PERKERASAN_SPIN, sbpk);
        contentValues.put(BAHAN_PERKERASAN, bpk);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ADT, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA23Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ADT + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A31
    public Boolean insertDataA31(String id, String iseg, double jll, double jpk, double tgi, double ldi, double pni, double lbi,
                                 double gti, double kti, String skjb, double kjb, double kjb2, double kjb3, double kjb4,
                                 double kjb5, double kjb6, double kjb7, double kjb8, double tum, double tsd,
                                 double tkk, double tup, double tup2, double tuk, double tbk, double ttp,
                                 double tap, String rec, String dir1, String dir2, String dir3, String dir4,
                                 String dir5, String dir6, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JALUR_LALU_LINTAS, jll);
        contentValues.put(JALUR_PEJALAN_KAKI, jpk);
        contentValues.put(TEGANGAN_IZIN, tgi);
        contentValues.put(LENDUTAN_IZIN, ldi);
        contentValues.put(PENURUNAN_IZIN, pni);
        contentValues.put(LEBAR_RETAK_IZIN, lbi);
        contentValues.put(GETARAN_IZIN, gti);
        contentValues.put(KETAHANAN_IZIN, kti);
        contentValues.put(KERUSAKAN_JEMBATAN_SPIN, skjb);
        contentValues.put(KERUSAKAN_JEMBATAN, kjb);
        contentValues.put(KERUSAKAN_JEMBATAN2, kjb2);
        contentValues.put(KERUSAKAN_JEMBATAN3, kjb3);
        contentValues.put(KERUSAKAN_JEMBATAN4, kjb4);
        contentValues.put(KERUSAKAN_JEMBATAN5, kjb5);
        contentValues.put(KERUSAKAN_JEMBATAN6, kjb6);
        contentValues.put(KERUSAKAN_JEMBATAN7, kjb7);
        contentValues.put(KERUSAKAN_JEMBATAN8, kjb8);
        contentValues.put(UNIT_MOBIL, tum);
        contentValues.put(SUMBER_DAYA, tsd);
        contentValues.put(KELENGKAPAN_KERJA, tkk);
        contentValues.put(UNIT_PEMELIHARAAN, tup);
        contentValues.put(UNIT_PENGECATAN, tup2);
        contentValues.put(UNIT_KONTROL, tuk);
        contentValues.put(ALAT_BANTU_KERJA, tbk);
        contentValues.put(TANDA_PENGAMAN_KERJA, ttp);
        contentValues.put(ALAT_PENGGANTUNG, tap);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(DIR_5, dir5);
        contentValues.put(DIR_6, dir6);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ATS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A31_Class> allDataA31(String iseg) {
        List<A31_Class> a31ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ATS + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A31_Class a31 = new A31_Class();
                a31.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a31.setJLL(cursor.getDouble(cursor.getColumnIndex(JALUR_LALU_LINTAS)));
                a31.setJPK(cursor.getDouble(cursor.getColumnIndex(JALUR_PEJALAN_KAKI)));
                a31.setTGI(cursor.getDouble(cursor.getColumnIndex(TEGANGAN_IZIN)));
                a31.setLDI(cursor.getDouble(cursor.getColumnIndex(LENDUTAN_IZIN)));
                a31.setPNI(cursor.getDouble(cursor.getColumnIndex(PENURUNAN_IZIN)));
                a31.setLBI(cursor.getDouble(cursor.getColumnIndex(LEBAR_RETAK_IZIN)));
                a31.setGTI(cursor.getDouble(cursor.getColumnIndex(GETARAN_IZIN)));
                a31.setKTI(cursor.getDouble(cursor.getColumnIndex(KETAHANAN_IZIN)));
                a31.setSKJB(cursor.getString(cursor.getColumnIndex(KERUSAKAN_JEMBATAN_SPIN)));
                a31.setKJB(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN)));
                a31.setKJB2(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN2)));
                a31.setKJB3(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN3)));
                a31.setKJB4(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN4)));
                a31.setKJB5(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN5)));
                a31.setKJB6(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN6)));
                a31.setKJB7(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN7)));
                a31.setKJB8(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_JEMBATAN8)));
                a31.setTUM(cursor.getDouble(cursor.getColumnIndex(UNIT_MOBIL)));
                a31.setTSD(cursor.getDouble(cursor.getColumnIndex(SUMBER_DAYA)));
                a31.setTKK(cursor.getDouble(cursor.getColumnIndex(KELENGKAPAN_KERJA)));
                a31.setTUP(cursor.getDouble(cursor.getColumnIndex(UNIT_PEMELIHARAAN)));
                a31.setTUP2(cursor.getDouble(cursor.getColumnIndex(UNIT_PENGECATAN)));
                a31.setTUK(cursor.getDouble(cursor.getColumnIndex(UNIT_KONTROL)));
                a31.setTBK(cursor.getDouble(cursor.getColumnIndex(ALAT_BANTU_KERJA)));
                a31.setTTP(cursor.getDouble(cursor.getColumnIndex(TANDA_PENGAMAN_KERJA)));
                a31.setTAP(cursor.getDouble(cursor.getColumnIndex(ALAT_PENGGANTUNG)));
                a31.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a31.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a31.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a31.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a31.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a31.setDIR5(cursor.getString(cursor.getColumnIndex(DIR_5)));
                a31.setDIR6(cursor.getString(cursor.getColumnIndex(DIR_6)));
                a31.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a31ArrayList.add(a31);
            } while (cursor.moveToNext());
        }
        db.close();
        return a31ArrayList;

    }

    public Boolean updateDataA31(String id, String iseg, double jll, double jpk, double tgi, double ldi, double pni, double lbi,
                                 double gti, double kti, String skjb, double kjb, double kjb2, double kjb3, double kjb4,
                                 double kjb5, double kjb6, double kjb7, double kjb8, double tum, double tsd,
                                 double tkk, double tup, double tup2, double tuk, double tbk, double ttp,
                                 double tap, String rec, String dir1, String dir2, String dir3, String dir4,
                                 String dir5, String dir6, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JALUR_LALU_LINTAS, jll);
        contentValues.put(JALUR_PEJALAN_KAKI, jpk);
        contentValues.put(TEGANGAN_IZIN, tgi);
        contentValues.put(LENDUTAN_IZIN, ldi);
        contentValues.put(PENURUNAN_IZIN, pni);
        contentValues.put(LEBAR_RETAK_IZIN, lbi);
        contentValues.put(GETARAN_IZIN, gti);
        contentValues.put(KETAHANAN_IZIN, kti);
        contentValues.put(KERUSAKAN_JEMBATAN_SPIN, skjb);
        contentValues.put(KERUSAKAN_JEMBATAN, kjb);
        contentValues.put(KERUSAKAN_JEMBATAN2, kjb2);
        contentValues.put(KERUSAKAN_JEMBATAN3, kjb3);
        contentValues.put(KERUSAKAN_JEMBATAN4, kjb4);
        contentValues.put(KERUSAKAN_JEMBATAN5, kjb5);
        contentValues.put(KERUSAKAN_JEMBATAN6, kjb6);
        contentValues.put(KERUSAKAN_JEMBATAN7, kjb7);
        contentValues.put(KERUSAKAN_JEMBATAN8, kjb8);
        contentValues.put(UNIT_MOBIL, tum);
        contentValues.put(SUMBER_DAYA, tsd);
        contentValues.put(KELENGKAPAN_KERJA, tkk);
        contentValues.put(UNIT_PEMELIHARAAN, tup);
        contentValues.put(UNIT_PENGECATAN, tup2);
        contentValues.put(UNIT_KONTROL, tuk);
        contentValues.put(ALAT_BANTU_KERJA, tbk);
        contentValues.put(TANDA_PENGAMAN_KERJA, ttp);
        contentValues.put(ALAT_PENGGANTUNG, tap);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(DIR_5, dir5);
        contentValues.put(DIR_6, dir6);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ATS, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA31Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ATS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A32
    public Boolean insertDataA32(String id, String iseg, double fng, double ktp, double krp,
                                 String rec, String dir1, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(FUNGSI_ATD, fng);
        contentValues.put(KONSTRUKSI_PONTON, ktp);
        contentValues.put(KERUSAKAN_PONTON, krp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ATD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A32_Class> allDataA32(String iseg) {
        List<A32_Class> a32ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ATD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A32_Class a32 = new A32_Class();
                a32.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a32.setFNG(cursor.getDouble(cursor.getColumnIndex(FUNGSI_ATD)));
                a32.setKTP(cursor.getDouble(cursor.getColumnIndex(KONSTRUKSI_PONTON)));
                a32.setKRP(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_PONTON)));
                a32.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a32.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a32.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a32ArrayList.add(a32);
            } while (cursor.moveToNext());
        }
        db.close();
        return a32ArrayList;

    }

    public Boolean updateDataA32(String id, String iseg, double fng, double ktp, double krp,
                                 String rec, String dir1, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(FUNGSI_ATD, fng);
        contentValues.put(KONSTRUKSI_PONTON, ktp);
        contentValues.put(KERUSAKAN_PONTON, krp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ATD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA32Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ATD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A33
    public Boolean insertDataA33(String id, String iseg, int dtr, int pgn, double fma, double krs,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JUMLAH_KILOMETER_DATAR, dtr);
        contentValues.put(JUMLAH_KILOMETER_PEGUNUNGAN, pgn);
        contentValues.put(FUNGSI_MENYALURKAN_AIR, fma);
        contentValues.put(KERUSAKAN, krs);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ATT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A33_Class> allDataA33(String iseg) {
        List<A33_Class> a33ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ATT + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A33_Class a33 = new A33_Class();
                a33.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a33.setDTR(cursor.getInt(cursor.getColumnIndex(JUMLAH_KILOMETER_DATAR)));
                a33.setPGN(cursor.getInt(cursor.getColumnIndex(JUMLAH_KILOMETER_PEGUNUNGAN)));
                a33.setFMA(cursor.getDouble(cursor.getColumnIndex(FUNGSI_MENYALURKAN_AIR)));
                a33.setKRS(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN)));
                a33.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a33.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a33.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a33.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a33ArrayList.add(a33);
            } while (cursor.moveToNext());
        }
        db.close();
        return a33ArrayList;

    }

    public Boolean updateDataA33(String id, String iseg, int dtr, int pgn, double fma, double krs,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JUMLAH_KILOMETER_DATAR, dtr);
        contentValues.put(JUMLAH_KILOMETER_PEGUNUNGAN, pgn);
        contentValues.put(FUNGSI_MENYALURKAN_AIR, fma);
        contentValues.put(KERUSAKAN, krs);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ATT, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA33Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ATT + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A34
    public Boolean insertDataA34(String id, String iseg, double ptj, double kal, double lle,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(POSISI_LAJUR, ptj);
        contentValues.put(KETERGANGGUAN_ARUS, kal);
        contentValues.put(LEBAR_LAJUR_ATE, lle);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ATE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A34_Class> allDataA34(String iseg) {
        List<A34_Class> a34ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ATE + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A34_Class a34 = new A34_Class();
                a34.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a34.setPTJ(cursor.getDouble(cursor.getColumnIndex(POSISI_LAJUR)));
                a34.setKAL(cursor.getDouble(cursor.getColumnIndex(KETERGANGGUAN_ARUS)));
                a34.setLLE(cursor.getDouble(cursor.getColumnIndex(LEBAR_LAJUR_ATE)));
                a34.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a34.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a34.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a34.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a34ArrayList.add(a34);
            } while (cursor.moveToNext());
        }
        db.close();
        return a34ArrayList;

    }

    public Boolean updateDataA34(String id, String iseg, double ptj, double kal, double lle,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(POSISI_LAJUR, ptj);
        contentValues.put(KETERGANGGUAN_ARUS, kal);
        contentValues.put(LEBAR_LAJUR_ATE, lle);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ATE, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA34Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ATE + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A35
    public Boolean insertDataA35(String id, String iseg, double kkt, double kel, double sar,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KESTABILAN_KONSTRUKSI, kkt);
        contentValues.put(KERUSAKAN_EROSI_LONGSOR, kel);
        contentValues.put(SALURAN_AIR, sar);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ATL, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A35_Class> allDataA35(String iseg) {
        List<A35_Class> a35ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ATL + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A35_Class a35 = new A35_Class();
                a35.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a35.setKKT(cursor.getDouble(cursor.getColumnIndex(KESTABILAN_KONSTRUKSI)));
                a35.setKEL(cursor.getDouble(cursor.getColumnIndex(KERUSAKAN_EROSI_LONGSOR)));
                a35.setSAR(cursor.getDouble(cursor.getColumnIndex(SALURAN_AIR)));
                a35.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a35.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a35.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a35.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a35ArrayList.add(a35);
            } while (cursor.moveToNext());
        }
        db.close();
        return a35ArrayList;

    }

    public Boolean updateDataA35(String id, String iseg, double kkt, double kel, double sar,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KESTABILAN_KONSTRUKSI, kkt);
        contentValues.put(KERUSAKAN_EROSI_LONGSOR, kel);
        contentValues.put(SALURAN_AIR, sar);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ATL, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA35Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ATL + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A36
    public Boolean insertDataA36(String id, String iseg, double dma, String sdma, double kat, double kak, double kab,
                                 String sbds, double bds, double tbl, String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(DIMENSI_DMA, dma);
        contentValues.put(DIMENSI_SDMA, sdma);
        contentValues.put(KEMIRINGAN_TANAH, kat);
        contentValues.put(KEMIRINGAN_KRIKIL, kak);
        contentValues.put(KEMIRINGAN_BATU, kab);
        contentValues.put(BAHAN_DINDING_SBDS, sbds);
        contentValues.put(BAHAN_DINDING_BDS, bds);
        contentValues.put(TERTUTUP_TERBUKA, tbl);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ATEN, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A36_Class> allDataA36(String iseg) {
        List<A36_Class> a36ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ATEN + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A36_Class a36 = new A36_Class();
                a36.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a36.setDMA(cursor.getDouble(cursor.getColumnIndex(DIMENSI_DMA)));
                a36.setSDMA(cursor.getString(cursor.getColumnIndex(DIMENSI_SDMA)));
                a36.setKAT(cursor.getDouble(cursor.getColumnIndex(KEMIRINGAN_TANAH)));
                a36.setKAK(cursor.getDouble(cursor.getColumnIndex(KEMIRINGAN_KRIKIL)));
                a36.setKAB(cursor.getDouble(cursor.getColumnIndex(KEMIRINGAN_BATU)));
                a36.setSBDS(cursor.getString(cursor.getColumnIndex(BAHAN_DINDING_SBDS)));
                a36.setBDS(cursor.getDouble(cursor.getColumnIndex(BAHAN_DINDING_BDS)));
                a36.setTBL(cursor.getDouble(cursor.getColumnIndex(TERTUTUP_TERBUKA)));
                a36.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a36.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a36.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a36.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a36ArrayList.add(a36);
            } while (cursor.moveToNext());
        }
        db.close();
        return a36ArrayList;

    }

    public Boolean updateDataA36(String id, String iseg, double dma, String sdma, double kat, double kak, double kab,
                                 String sbds, double bds, double tbl, String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(DIMENSI_DMA, dma);
        contentValues.put(DIMENSI_SDMA, sdma);
        contentValues.put(KEMIRINGAN_TANAH, kat);
        contentValues.put(KEMIRINGAN_KRIKIL, kak);
        contentValues.put(KEMIRINGAN_BATU, kab);
        contentValues.put(BAHAN_DINDING_SBDS, sbds);
        contentValues.put(BAHAN_DINDING_BDS, bds);
        contentValues.put(TERTUTUP_TERBUKA, tbl);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ATEN, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA36Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ATEN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A41
    public Boolean insertDataA41(String id, String iseg, String slbt, double lbt, double lbt2, double lbt3, double pfr, double kll,
                                 String rec, String dir1, String dir2, String dir3, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_TINGGI_SPIN, slbt);
        contentValues.put(LEBAR_TINGGI1, lbt);
        contentValues.put(LEBAR_TINGGI2, lbt2);
        contentValues.put(LEBAR_TINGGI3, lbt3);
        contentValues.put(PEMANFAATAN_RUMAJA, pfr);
        contentValues.put(KESELAMATAN_LALULINTAS, kll);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_AES, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A41_Class> allDataA41(String iseg) {
        List<A41_Class> a41ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_AES + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A41_Class a41 = new A41_Class();
                a41.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a41.setSLBT(cursor.getString(cursor.getColumnIndex(LEBAR_TINGGI_SPIN)));
                a41.setLBT(cursor.getDouble(cursor.getColumnIndex(LEBAR_TINGGI1)));
                a41.setLBT2(cursor.getDouble(cursor.getColumnIndex(LEBAR_TINGGI2)));
                a41.setLBT3(cursor.getDouble(cursor.getColumnIndex(LEBAR_TINGGI3)));
                a41.setPFR(cursor.getDouble(cursor.getColumnIndex(PEMANFAATAN_RUMAJA)));
                a41.setKLL(cursor.getDouble(cursor.getColumnIndex(KESELAMATAN_LALULINTAS)));
                a41.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a41.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a41.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a41.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a41.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a41ArrayList.add(a41);
            } while (cursor.moveToNext());
        }
        db.close();
        return a41ArrayList;

    }

    public Boolean updateDataA41(String id, String iseg, String slbt, double lbt, double lbt2, double lbt3, double pfr, double kll,
                                 String rec, String dir1, String dir2, String dir3, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_TINGGI_SPIN, slbt);
        contentValues.put(LEBAR_TINGGI1, lbt);
        contentValues.put(LEBAR_TINGGI2, lbt2);
        contentValues.put(LEBAR_TINGGI3, lbt3);
        contentValues.put(PEMANFAATAN_RUMAJA, pfr);
        contentValues.put(KESELAMATAN_LALULINTAS, kll);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_AES, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA41Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_AES + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A42
    public Boolean insertDataA42(String id, String iseg, double lbr, double pfr, String sktu, double ktu, double ktu2, double ktu3,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_RUMIJA, lbr);
        contentValues.put(PEMANFAATAN_RUMIJA_AED, pfr);
        contentValues.put(KEBERADAAN_UTILITAS_SPIN, sktu);
        contentValues.put(KEBERADAAN_UTILITAS, ktu);
        contentValues.put(KEBERADAAN_UTILITAS2, ktu2);
        contentValues.put(KEBERADAAN_UTILITAS3, ktu3);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_AED, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A42_Class> allDataA42(String iseg) {
        List<A42_Class> a42ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_AED + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A42_Class a42 = new A42_Class();
                a42.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a42.setLBR(cursor.getDouble(cursor.getColumnIndex(LEBAR_RUMIJA)));
                a42.setPFR(cursor.getDouble(cursor.getColumnIndex(PEMANFAATAN_RUMIJA_AED)));
                a42.setSKTU(cursor.getString(cursor.getColumnIndex(KEBERADAAN_UTILITAS_SPIN)));
                a42.setKTU(cursor.getDouble(cursor.getColumnIndex(KEBERADAAN_UTILITAS)));
                a42.setKTU2(cursor.getDouble(cursor.getColumnIndex(KEBERADAAN_UTILITAS2)));
                a42.setKTU3(cursor.getDouble(cursor.getColumnIndex(KEBERADAAN_UTILITAS3)));
                a42.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a42.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a42.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a42.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a42ArrayList.add(a42);
            } while (cursor.moveToNext());
        }
        db.close();
        return a42ArrayList;

    }

    public Boolean updateDataA42(String id, String iseg, double lbr, double pfr, String sktu, double ktu, double ktu2, double ktu3,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_RUMIJA, lbr);
        contentValues.put(PEMANFAATAN_RUMIJA_AED, pfr);
        contentValues.put(KEBERADAAN_UTILITAS_SPIN, sktu);
        contentValues.put(KEBERADAAN_UTILITAS, ktu);
        contentValues.put(KEBERADAAN_UTILITAS2, ktu2);
        contentValues.put(KEBERADAAN_UTILITAS3, ktu3);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_AED, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA42Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_AED + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A43
    public Boolean insertDataA43(String id, String iseg, double lrw, double prw, double prw2, double prw3, double ppp,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_RUWASJA, lrw);
        contentValues.put(PEMANFAATAN_RUWASJA1, prw);
        contentValues.put(PEMANFAATAN_RUWASJA2, prw2);
        contentValues.put(PEMANFAATAN_RUWASJA3, prw3);
        contentValues.put(PENGHALANG_PANDANGAN_PENGEMUDI, ppp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_AET, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A43_Class> allDataA43(String iseg) {
        List<A43_Class> a43ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_AET + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A43_Class a43 = new A43_Class();
                a43.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a43.setLRW(cursor.getDouble(cursor.getColumnIndex(LEBAR_RUWASJA)));
                a43.setPRW(cursor.getDouble(cursor.getColumnIndex(PEMANFAATAN_RUWASJA1)));
                a43.setPRW2(cursor.getDouble(cursor.getColumnIndex(PEMANFAATAN_RUWASJA2)));
                a43.setPRW3(cursor.getDouble(cursor.getColumnIndex(PEMANFAATAN_RUWASJA3)));
                a43.setPPP(cursor.getDouble(cursor.getColumnIndex(PENGHALANG_PANDANGAN_PENGEMUDI)));
                a43.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a43.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a43.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a43.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a43ArrayList.add(a43);
            } while (cursor.moveToNext());
        }
        db.close();
        return a43ArrayList;

    }

    public Boolean updateDataA43(String id, String iseg, double lrw, double prw, double prw2, double prw3, double ppp,
                                 String rec, String dir1, String dir2, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_RUWASJA, lrw);
        contentValues.put(PEMANFAATAN_RUWASJA1, prw);
        contentValues.put(PEMANFAATAN_RUWASJA2, prw2);
        contentValues.put(PEMANFAATAN_RUWASJA3, prw3);
        contentValues.put(PENGHALANG_PANDANGAN_PENGEMUDI, ppp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_AET, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA43Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_AET + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A51
    public Boolean insertDataA51(String id, String iseg, String mbl1, String mbl2, String mbl3, String mbl4, String mbt1, String mbt2, String mbt3,
                                 String mbt4, String mbt5, String mpr1, String mpr2, String mpr3, String mpr4, String mpr5, String mpr6,
                                 String mpr7, double mbl11, double mbl22, double mbl33, double mbl44, double mbt11, double mbt22, double mbt33,
                                 double mbt44, double mbt55, double mpr11, double mpr22, double mpr33, double mpr44, double mpr55, double mpr66,
                                 double mpr77, double zbc1, double zbc2, double zbc3,
                                 String rec, String dir1, String dir2, String dir3, String dir4, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(MARKA_BAGIAN_LURUS1, mbl1);
        contentValues.put(MARKA_BAGIAN_LURUS2, mbl2);
        contentValues.put(MARKA_BAGIAN_LURUS3, mbl3);
        contentValues.put(MARKA_BAGIAN_LURUS4, mbl4);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN1, mbt1);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN2, mbt2);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN3, mbt3);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN4, mbt4);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN5, mbt5);
        contentValues.put(MARKA_PERSIMPANGAN1, mpr1);
        contentValues.put(MARKA_PERSIMPANGAN2, mpr2);
        contentValues.put(MARKA_PERSIMPANGAN3, mpr3);
        contentValues.put(MARKA_PERSIMPANGAN4, mpr4);
        contentValues.put(MARKA_PERSIMPANGAN5, mpr5);
        contentValues.put(MARKA_PERSIMPANGAN6, mpr6);
        contentValues.put(MARKA_PERSIMPANGAN7, mpr7);
        contentValues.put(MARKA_BAGIAN_LURUS11, mbl11);
        contentValues.put(MARKA_BAGIAN_LURUS22, mbl22);
        contentValues.put(MARKA_BAGIAN_LURUS33, mbl33);
        contentValues.put(MARKA_BAGIAN_LURUS44, mbl44);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN11, mbt11);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN22, mbt22);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN33, mbt33);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN44, mbt44);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN55, mbt55);
        contentValues.put(MARKA_PERSIMPANGAN11, mpr11);
        contentValues.put(MARKA_PERSIMPANGAN22, mpr22);
        contentValues.put(MARKA_PERSIMPANGAN33, mpr33);
        contentValues.put(MARKA_PERSIMPANGAN44, mpr44);
        contentValues.put(MARKA_PERSIMPANGAN55, mpr55);
        contentValues.put(MARKA_PERSIMPANGAN66, mpr66);
        contentValues.put(MARKA_PERSIMPANGAN77, mpr77);
        contentValues.put(ZEBRA_CROSS1, zbc1);
        contentValues.put(ZEBRA_CROSS2, zbc2);
        contentValues.put(ZEBRA_CROSS3, zbc3);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ALS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A51_Class> allDataA51(String iseg) {
        List<A51_Class> a51ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ALS + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A51_Class a51 = new A51_Class();
                a51.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a51.setMBL1(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_LURUS1)));
                a51.setMBL2(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_LURUS2)));
                a51.setMBL3(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_LURUS3)));
                a51.setMBL4(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_LURUS4)));
                a51.setMBT1(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN1)));
                a51.setMBT2(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN2)));
                a51.setMBT3(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN3)));
                a51.setMBT4(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN4)));
                a51.setMBT5(cursor.getString(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN5)));
                a51.setMPR1(cursor.getString(cursor.getColumnIndex(MARKA_PERSIMPANGAN1)));
                a51.setMPR2(cursor.getString(cursor.getColumnIndex(MARKA_PERSIMPANGAN2)));
                a51.setMPR3(cursor.getString(cursor.getColumnIndex(MARKA_PERSIMPANGAN3)));
                a51.setMPR4(cursor.getString(cursor.getColumnIndex(MARKA_PERSIMPANGAN4)));
                a51.setMPR5(cursor.getString(cursor.getColumnIndex(MARKA_PERSIMPANGAN5)));
                a51.setMPR6(cursor.getString(cursor.getColumnIndex(MARKA_PERSIMPANGAN6)));
                a51.setMPR7(cursor.getString(cursor.getColumnIndex(MARKA_PERSIMPANGAN7)));
                a51.setMBL11(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_LURUS11)));
                a51.setMBL22(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_LURUS22)));
                a51.setMBL33(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_LURUS33)));
                a51.setMBL44(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_LURUS44)));
                a51.setMBT11(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN11)));
                a51.setMBT22(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN22)));
                a51.setMBT33(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN33)));
                a51.setMBT44(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN44)));
                a51.setMBT55(cursor.getDouble(cursor.getColumnIndex(MARKA_BAGIAN_TIKUNGAN55)));
                a51.setMPR11(cursor.getDouble(cursor.getColumnIndex(MARKA_PERSIMPANGAN11)));
                a51.setMPR22(cursor.getDouble(cursor.getColumnIndex(MARKA_PERSIMPANGAN22)));
                a51.setMPR33(cursor.getDouble(cursor.getColumnIndex(MARKA_PERSIMPANGAN33)));
                a51.setMPR44(cursor.getDouble(cursor.getColumnIndex(MARKA_PERSIMPANGAN44)));
                a51.setMPR55(cursor.getDouble(cursor.getColumnIndex(MARKA_PERSIMPANGAN55)));
                a51.setMPR66(cursor.getDouble(cursor.getColumnIndex(MARKA_PERSIMPANGAN66)));
                a51.setMPR77(cursor.getDouble(cursor.getColumnIndex(MARKA_PERSIMPANGAN77)));
                a51.setZBC1(cursor.getDouble(cursor.getColumnIndex(ZEBRA_CROSS1)));
                a51.setZBC2(cursor.getDouble(cursor.getColumnIndex(ZEBRA_CROSS2)));
                a51.setZBC3(cursor.getDouble(cursor.getColumnIndex(ZEBRA_CROSS3)));
                a51.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a51.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a51.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a51.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a51.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a51.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a51ArrayList.add(a51);
            } while (cursor.moveToNext());
        }
        db.close();
        return a51ArrayList;

    }

    public Boolean updateDataA51(String id, String iseg, String mbl1, String mbl2, String mbl3, String mbl4, String mbt1, String mbt2, String mbt3,
                                 String mbt4, String mbt5, String mpr1, String mpr2, String mpr3, String mpr4, String mpr5, String mpr6,
                                 String mpr7, double mbl11, double mbl22, double mbl33, double mbl44, double mbt11, double mbt22, double mbt33,
                                 double mbt44, double mbt55, double mpr11, double mpr22, double mpr33, double mpr44, double mpr55, double mpr66,
                                 double mpr77, double zbc1, double zbc2, double zbc3,
                                 String rec, String dir1, String dir2, String dir3, String dir4, String upl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(MARKA_BAGIAN_LURUS1, mbl1);
        contentValues.put(MARKA_BAGIAN_LURUS2, mbl2);
        contentValues.put(MARKA_BAGIAN_LURUS3, mbl3);
        contentValues.put(MARKA_BAGIAN_LURUS4, mbl4);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN1, mbt1);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN2, mbt2);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN3, mbt3);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN4, mbt4);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN5, mbt5);
        contentValues.put(MARKA_PERSIMPANGAN1, mpr1);
        contentValues.put(MARKA_PERSIMPANGAN2, mpr2);
        contentValues.put(MARKA_PERSIMPANGAN3, mpr3);
        contentValues.put(MARKA_PERSIMPANGAN4, mpr4);
        contentValues.put(MARKA_PERSIMPANGAN5, mpr5);
        contentValues.put(MARKA_PERSIMPANGAN6, mpr6);
        contentValues.put(MARKA_PERSIMPANGAN7, mpr7);
        contentValues.put(MARKA_BAGIAN_LURUS11, mbl11);
        contentValues.put(MARKA_BAGIAN_LURUS22, mbl22);
        contentValues.put(MARKA_BAGIAN_LURUS33, mbl33);
        contentValues.put(MARKA_BAGIAN_LURUS44, mbl44);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN11, mbt11);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN22, mbt22);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN33, mbt33);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN44, mbt44);
        contentValues.put(MARKA_BAGIAN_TIKUNGAN55, mbt55);
        contentValues.put(MARKA_PERSIMPANGAN11, mpr11);
        contentValues.put(MARKA_PERSIMPANGAN22, mpr22);
        contentValues.put(MARKA_PERSIMPANGAN33, mpr33);
        contentValues.put(MARKA_PERSIMPANGAN44, mpr44);
        contentValues.put(MARKA_PERSIMPANGAN55, mpr55);
        contentValues.put(MARKA_PERSIMPANGAN66, mpr66);
        contentValues.put(MARKA_PERSIMPANGAN77, mpr77);
        contentValues.put(ZEBRA_CROSS1, zbc1);
        contentValues.put(ZEBRA_CROSS2, zbc2);
        contentValues.put(ZEBRA_CROSS3, zbc3);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ALS, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA51Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ALS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A52
    public Boolean insertDataA52(String id, String iseg, String kml, String kml2, String kml3, String kml4, String kml5, String kml6,
                                 double kml11, double kml22, double kml33, double kml44, double kml55, double kml66, double krp, double krp2,
                                 double krp3, double krp4, double krp5, double krp6,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3, kml3);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL4, kml4);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL5, kml5);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL6, kml6);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL11, kml11);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL22, kml22);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL33, kml33);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL44, kml44);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL55, kml55);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL66, kml66);
        contentValues.put(KETEPATAN_RAMBU1, krp);
        contentValues.put(KETEPATAN_RAMBU2, krp2);
        contentValues.put(KETEPATAN_RAMBU3, krp3);
        contentValues.put(KETEPATAN_RAMBU4, krp4);
        contentValues.put(KETEPATAN_RAMBU5, krp5);
        contentValues.put(KETEPATAN_RAMBU6, krp6);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ALD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A52_Class> allDataA52(String iseg) {
        List<A52_Class> a52ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ALD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A52_Class a52 = new A52_Class();
                a52.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a52.setKML(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL1)));
                a52.setKML2(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL2)));
                a52.setKML3(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL3)));
                a52.setKML4(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL4)));
                a52.setKML5(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL5)));
                a52.setKML6(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL6)));
                a52.setKML11(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL11)));
                a52.setKML22(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL22)));
                a52.setKML33(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL33)));
                a52.setKML44(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL44)));
                a52.setKML55(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL55)));
                a52.setKML66(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL66)));
                a52.setKRP(cursor.getDouble(cursor.getColumnIndex(KETEPATAN_RAMBU1)));
                a52.setKRP2(cursor.getDouble(cursor.getColumnIndex(KETEPATAN_RAMBU2)));
                a52.setKRP3(cursor.getDouble(cursor.getColumnIndex(KETEPATAN_RAMBU3)));
                a52.setKRP4(cursor.getDouble(cursor.getColumnIndex(KETEPATAN_RAMBU4)));
                a52.setKRP5(cursor.getDouble(cursor.getColumnIndex(KETEPATAN_RAMBU5)));
                a52.setKRP6(cursor.getDouble(cursor.getColumnIndex(KETEPATAN_RAMBU6)));
                a52.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a52.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a52.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a52.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a52ArrayList.add(a52);
            } while (cursor.moveToNext());
        }

        db.close();
        return a52ArrayList;

    }

    public Boolean updateDataA52(String id, String iseg, String kml, String kml2, String kml3, String kml4, String kml5, String kml6,
                                 double kml11, double kml22, double kml33, double kml44, double kml55, double kml66, double krp, double krp2,
                                 double krp3, double krp4, double krp5, double krp6,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3, kml3);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL4, kml4);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL5, kml5);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL6, kml6);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL11, kml11);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL22, kml22);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL33, kml33);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL44, kml44);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL55, kml55);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL66, kml66);
        contentValues.put(KETEPATAN_RAMBU1, krp);
        contentValues.put(KETEPATAN_RAMBU2, krp2);
        contentValues.put(KETEPATAN_RAMBU3, krp3);
        contentValues.put(KETEPATAN_RAMBU4, krp4);
        contentValues.put(KETEPATAN_RAMBU5, krp5);
        contentValues.put(KETEPATAN_RAMBU6, krp6);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ALD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA52Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ALD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A53
    public Boolean insertDataA53(String id, String iseg, String kml, String kml2, String kml3, double kml11,
                                 double kml22, double kml33, double bps,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL_ALT, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALT, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALT, kml3);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL11_ALT, kml11);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL22_ALT, kml22);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL33_ALT, kml33);
        contentValues.put(BUKAAN_SEPARATOR, bps);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ALT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A53_Class> allDataA53(String iseg) {
        List<A53_Class> a53ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ALT + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A53_Class a53 = new A53_Class();
                a53.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a53.setKML(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL_ALT)));
                a53.setKML2(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL2_ALT)));
                a53.setKML3(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL3_ALT)));
                a53.setKML11(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL11_ALT)));
                a53.setKML22(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL22_ALT)));
                a53.setKML33(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL33_ALT)));
                a53.setBSP(cursor.getDouble(cursor.getColumnIndex(BUKAAN_SEPARATOR)));
                a53.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a53.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a53.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a53.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a53ArrayList.add(a53);
            } while (cursor.moveToNext());
        }

        db.close();
        return a53ArrayList;

    }

    public Boolean updateDataA53(String id, String iseg, String kml, String kml2, String kml3, double kml11,
                                 double kml22, double kml33, double bps,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL_ALT, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALT, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALT, kml3);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL11_ALT, kml11);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL22_ALT, kml22);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL33_ALT, kml33);
        contentValues.put(BUKAAN_SEPARATOR, bps);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ALT, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA53Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ALT + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A54
    public Boolean insertDataA54(String id, String iseg, String kml, String kml2, String kml3, String bpj, double mrk,
                                 double mrk2, double mrk3, double mrk4, double wrk, double rbp,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1_ALE, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALE, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALE, kml3);
        contentValues.put(BENTUK_PULAU_JALAN, bpj);
        contentValues.put(MARKA1, mrk);
        contentValues.put(MARKA2, mrk2);
        contentValues.put(MARKA3, mrk3);
        contentValues.put(MARKA4, mrk4);
        contentValues.put(WARNA_KERB, wrk);
        contentValues.put(RAMBU_PENGARAH, rbp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ALE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A54_Class> allDataA54(String iseg) {
        List<A54_Class> a54ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ALE + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A54_Class a54 = new A54_Class();
                a54.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a54.setKML(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL1_ALE)));
                a54.setKML2(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL2_ALE)));
                a54.setKML3(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL3_ALE)));
                a54.setSBPJ(cursor.getString(cursor.getColumnIndex(BENTUK_PULAU_JALAN)));
                a54.setMRK(cursor.getDouble(cursor.getColumnIndex(MARKA1)));
                a54.setMRK2(cursor.getDouble(cursor.getColumnIndex(MARKA2)));
                a54.setMRK3(cursor.getDouble(cursor.getColumnIndex(MARKA3)));
                a54.setMRK4(cursor.getDouble(cursor.getColumnIndex(MARKA4)));
                a54.setWRK(cursor.getDouble(cursor.getColumnIndex(WARNA_KERB)));
                a54.setRBP(cursor.getDouble(cursor.getColumnIndex(RAMBU_PENGARAH)));
                a54.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a54.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a54.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a54.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a54ArrayList.add(a54);
            } while (cursor.moveToNext());
        }

        db.close();
        return a54ArrayList;

    }

    public Boolean updateDataA54(String id, String iseg, String kml, String kml2, String kml3, String bpj, double mrk,
                                 double mrk2, double mrk3, double mrk4, double wrk, double rbp,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_ALL, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1_ALE, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALE, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALE, kml3);
        contentValues.put(BENTUK_PULAU_JALAN, bpj);
        contentValues.put(MARKA1, mrk);
        contentValues.put(MARKA2, mrk2);
        contentValues.put(MARKA3, mrk3);
        contentValues.put(MARKA4, mrk4);
        contentValues.put(WARNA_KERB, wrk);
        contentValues.put(RAMBU_PENGARAH, rbp);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ALE, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA54Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ALE + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A55
    public Boolean insertDataA55(String id, String iseg, String kml, double pkt, double pkt2, double psp, double utr,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_ALL, kml);
        contentValues.put(PERKERASAN_TROTOAR1, pkt);
        contentValues.put(PERKERASAN_TROTOAR2, pkt2);
        contentValues.put(PEMANFAATAN_PEJALAN, psp);
        contentValues.put(UTILITAS_TROTOAR, utr);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ALL, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A55_Class> allDataA55(String iseg) {
        List<A55_Class> a55ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ALL + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A55_Class a55 = new A55_Class();
                a55.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a55.setSKML(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_ALL)));
                a55.setPKT(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_TROTOAR1)));
                a55.setPKT2(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_TROTOAR2)));
                a55.setPSP(cursor.getDouble(cursor.getColumnIndex(PEMANFAATAN_PEJALAN)));
                a55.setUTR(cursor.getDouble(cursor.getColumnIndex(UTILITAS_TROTOAR)));
                a55.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a55.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a55.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a55.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a55ArrayList.add(a55);
            } while (cursor.moveToNext());
        }

        db.close();
        return a55ArrayList;

    }

    public Boolean updateDataA55(String id, String iseg, String kml, double pkt, double pkt2, double psp, double utr,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_ALL, kml);
        contentValues.put(PERKERASAN_TROTOAR1, pkt);
        contentValues.put(PERKERASAN_TROTOAR2, pkt2);
        contentValues.put(PEMANFAATAN_PEJALAN, psp);
        contentValues.put(UTILITAS_TROTOAR, utr);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ALL, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA55Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ALL + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A56
    public Boolean insertDataA56(String id, String iseg, double kml, double kml2, double kml3, double lpg,
                                 int php, double phk, double fpc, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1_ALEN, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALEN, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALEN, kml3);
        contentValues.put(LAMPU_PENGATUR, lpg);
        contentValues.put(PHASE_PENGATURAN, php);
        contentValues.put(PHASE_PEJALAN, phk);
        contentValues.put(FASILITAS_PENYANDANG, fpc);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ALEN, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A56_Class> allDataA56(String iseg) {
        List<A56_Class> a56ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ALEN + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A56_Class a56 = new A56_Class();
                a56.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a56.setKML(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL1_ALEN)));
                a56.setKML2(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL2_ALEN)));
                a56.setKML3(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL3_ALEN)));
                a56.setLPG(cursor.getDouble(cursor.getColumnIndex(LAMPU_PENGATUR)));
                a56.setPHP(cursor.getInt(cursor.getColumnIndex(PHASE_PENGATURAN)));
                a56.setPHK(cursor.getDouble(cursor.getColumnIndex(PHASE_PEJALAN)));
                a56.setFPC(cursor.getDouble(cursor.getColumnIndex(FASILITAS_PENYANDANG)));
                a56.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a56.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a56.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a56.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a56ArrayList.add(a56);
            } while (cursor.moveToNext());
        }

        db.close();
        return a56ArrayList;

    }

    public Boolean updateDataA56(String id, String iseg, double kml, double kml2, double kml3, double lpg,
                                 int php, double phk, double fpc, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1_ALEN, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALEN, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALEN, kml3);
        contentValues.put(LAMPU_PENGATUR, lpg);
        contentValues.put(PHASE_PENGATURAN, php);
        contentValues.put(PHASE_PEJALAN, phk);
        contentValues.put(FASILITAS_PENYANDANG, fpc);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ALEN, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA56Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ALEN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A57
    public Boolean insertDataA57(String id, String iseg, String kml, String kml2, String kml3, String kml4, double kml11,
                                 double kml22, double kml33, double kml44, double rmk, double rmk2, double rmk3, double apl,
                                 double ppk, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1_ALTJ, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALTJ, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALTJ, kml3);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL4_ALTJ, kml4);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL11_ALTJ, kml11);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL22_ALTJ, kml22);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL33_ALTJ, kml33);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL44_ALTJ, kml44);
        contentValues.put(RAMBU_MARKA, rmk);
        contentValues.put(RAMBU_MARKA2, rmk2);
        contentValues.put(RAMBU_MARKA3, rmk3);
        contentValues.put(APILL, apl);
        contentValues.put(PERLINDUNGAN_PEJALAN, ppk);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ALTJ, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A57_Class> allDataA57(String iseg) {
        List<A57_Class> a57ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ALTJ + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A57_Class a57 = new A57_Class();
                a57.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a57.setKML(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL1_ALTJ)));
                a57.setKML2(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL2_ALTJ)));
                a57.setKML3(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL3_ALTJ)));
                a57.setKML4(cursor.getString(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL4_ALTJ)));
                a57.setKML11(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL11_ALTJ)));
                a57.setKML22(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL22_ALTJ)));
                a57.setKML33(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL33_ALTJ)));
                a57.setKML44(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_MANAJEMEN_LL44_ALTJ)));
                a57.setRMK(cursor.getDouble(cursor.getColumnIndex(RAMBU_MARKA)));
                a57.setRMK2(cursor.getInt(cursor.getColumnIndex(RAMBU_MARKA2)));
                a57.setRMK3(cursor.getDouble(cursor.getColumnIndex(RAMBU_MARKA3)));
                a57.setAPL(cursor.getDouble(cursor.getColumnIndex(APILL)));
                a57.setPPK(cursor.getDouble(cursor.getColumnIndex(PERLINDUNGAN_PEJALAN)));
                a57.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a57.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a57.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a57.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a57ArrayList.add(a57);
            } while (cursor.moveToNext());
        }

        db.close();
        return a57ArrayList;

    }

    public Boolean updateDataA57(String id, String iseg, String kml, String kml2, String kml3, String kml4, double kml11,
                                 double kml22, double kml33, double kml44, double rmk, double rmk2, double rmk3, double apl,
                                 double ppk, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL1_ALTJ, kml);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL2_ALTJ, kml2);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL3_ALTJ, kml3);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL4_ALTJ, kml4);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL11_ALTJ, kml11);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL22_ALTJ, kml22);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL33_ALTJ, kml33);
        contentValues.put(KEBUTUHAN_MANAJEMEN_LL44_ALTJ, kml44);
        contentValues.put(RAMBU_MARKA, rmk);
        contentValues.put(RAMBU_MARKA2, rmk2);
        contentValues.put(RAMBU_MARKA3, rmk3);
        contentValues.put(APILL, apl);
        contentValues.put(PERLINDUNGAN_PEJALAN, ppk);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ALTJ, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA57Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ALTJ + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6a1
    public Boolean insertDataA6a1(String id, String iseg, double gsp, double gsj, double gsl, double gpp, double gpl, double gpj,
                                  double ylp, double yll, double ylj, double zcp, double zcl, double zca,
                                  double zcg, double gpm, double gpl2, double gpp2, double gpl3, double mcu,
                                  double mcd, double mcs, double mcj, double gsl2, double tjp, double tjl,
                                  double ybl, double wmg, double wmj, double wml, double kmb, String rec, String dir1,
                                  String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(GARIS_SUMBU_PANJANG, gsp);
        contentValues.put(GARIS_SUMBU_LEBAR, gsl);
        contentValues.put(GARIS_SUMBU_JARAK, gsj);
        contentValues.put(GARIS_PERINGATAN_PANJANG, gpp);
        contentValues.put(GARIS_PERINGATAN_LEBAR, gpl);
        contentValues.put(GARIS_PERINGATAN_JARAK, gpj);
        contentValues.put(YIELD_LINE_PANJANG, ylp);
        contentValues.put(YIELD_LINE_LEBAR, yll);
        contentValues.put(YIELD_LINE_JARAK, ylj);
        contentValues.put(ZEBRA_CROSS_PANJANG, zcp);
        contentValues.put(ZEBRA_CROSS_LEBAR, zcl);
        contentValues.put(ZEBRA_CROSS_JARAK_GARIS, zca);
        contentValues.put(ZEBRA_CROSS_JARAK_STOP, zcg);
        contentValues.put(GARIS_PENGARAH_PANJANG, gpm);
        contentValues.put(GARIS_PENGARAH_LEBAR, gpl2);
        contentValues.put(GARIS_PENDEKAT_PANJANG, gpp2);
        contentValues.put(GARIS_PENDEKAT_LEBAR, gpl3);
        contentValues.put(MC_LEBAR_UJUNG, mcu);
        contentValues.put(MC_LEBAR_DALAM, mcd);
        contentValues.put(MC_LEBAR_GARIS_SERONG, mcs);
        contentValues.put(MC_JARAK_BATAS, mcj);
        contentValues.put(GARIS_STOP_LEBAR, gsl2);
        contentValues.put(TANDA_PENGARAH_PANJANG, tjp);
        contentValues.put(TANDA_PENGARAH_LEBAR, tjl);
        contentValues.put(YELLOW_BOX_LEBAR, ybl);
        contentValues.put(WARNA_MARKA_PUTIH, wmg);
        contentValues.put(WARNA_MARKA_KUNING, wmj);
        contentValues.put(WARNA_MARKA_MERAH, wml);
        contentValues.put(KONDISI_MARKA, kmb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANAS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6A1_Class> allDataA6a1(String iseg) {
        List<A6A1_Class> a6a1ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANAS + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6A1_Class a6a1 = new A6A1_Class();
                a6a1.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6a1.setGSP(cursor.getDouble(cursor.getColumnIndex(GARIS_SUMBU_PANJANG)));
                a6a1.setGSJ(cursor.getDouble(cursor.getColumnIndex(GARIS_SUMBU_JARAK)));
                a6a1.setGSL(cursor.getDouble(cursor.getColumnIndex(GARIS_SUMBU_LEBAR)));
                a6a1.setGPP(cursor.getDouble(cursor.getColumnIndex(GARIS_PERINGATAN_PANJANG)));
                a6a1.setGPL(cursor.getDouble(cursor.getColumnIndex(GARIS_PERINGATAN_LEBAR)));
                a6a1.setGPJ(cursor.getDouble(cursor.getColumnIndex(GARIS_PERINGATAN_JARAK)));
                a6a1.setYLP(cursor.getDouble(cursor.getColumnIndex(YIELD_LINE_PANJANG)));
                a6a1.setYLL(cursor.getDouble(cursor.getColumnIndex(YIELD_LINE_LEBAR)));
                a6a1.setYLJ(cursor.getDouble(cursor.getColumnIndex(YIELD_LINE_JARAK)));
                a6a1.setZCP(cursor.getDouble(cursor.getColumnIndex(ZEBRA_CROSS_PANJANG)));
                a6a1.setZCL(cursor.getDouble(cursor.getColumnIndex(ZEBRA_CROSS_LEBAR)));
                a6a1.setZCA(cursor.getDouble(cursor.getColumnIndex(ZEBRA_CROSS_JARAK_GARIS)));
                a6a1.setZCG(cursor.getDouble(cursor.getColumnIndex(ZEBRA_CROSS_JARAK_STOP)));
                a6a1.setGPM(cursor.getDouble(cursor.getColumnIndex(GARIS_PENGARAH_PANJANG)));
                a6a1.setGPL2(cursor.getDouble(cursor.getColumnIndex(GARIS_PENGARAH_LEBAR)));
                a6a1.setGPP2(cursor.getDouble(cursor.getColumnIndex(GARIS_PENDEKAT_PANJANG)));
                a6a1.setGPL3(cursor.getDouble(cursor.getColumnIndex(GARIS_PENDEKAT_LEBAR)));
                a6a1.setMCU(cursor.getDouble(cursor.getColumnIndex(MC_LEBAR_UJUNG)));
                a6a1.setMCD(cursor.getDouble(cursor.getColumnIndex(MC_LEBAR_DALAM)));
                a6a1.setMCS(cursor.getDouble(cursor.getColumnIndex(MC_LEBAR_GARIS_SERONG)));
                a6a1.setMCJ(cursor.getDouble(cursor.getColumnIndex(MC_JARAK_BATAS)));
                a6a1.setGSL2(cursor.getDouble(cursor.getColumnIndex(GARIS_STOP_LEBAR)));
                a6a1.setTJP(cursor.getDouble(cursor.getColumnIndex(TANDA_PENGARAH_PANJANG)));
                a6a1.setTJL(cursor.getDouble(cursor.getColumnIndex(TANDA_PENGARAH_LEBAR)));
                a6a1.setYBL(cursor.getDouble(cursor.getColumnIndex(YELLOW_BOX_LEBAR)));
                a6a1.setWMG(cursor.getDouble(cursor.getColumnIndex(WARNA_MARKA_PUTIH)));
                a6a1.setWMJ(cursor.getDouble(cursor.getColumnIndex(WARNA_MARKA_KUNING)));
                a6a1.setWML(cursor.getDouble(cursor.getColumnIndex(WARNA_MARKA_MERAH)));
                a6a1.setKMB(cursor.getDouble(cursor.getColumnIndex(KONDISI_MARKA)));
                a6a1.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6a1.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6a1.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6a1.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a6a1.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a6a1.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6a1ArrayList.add(a6a1);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6a1ArrayList;

    }

    public Boolean updateDataA6a1(String id, String iseg, double gsp, double gsj, double gsl, double gpp, double gpl, double gpj,
                                  double ylp, double yll, double ylj, double zcp, double zcl, double zca,
                                  double zcg, double gpm, double gpl2, double gpp2, double gpl3, double mcu,
                                  double mcd, double mcs, double mcj, double gsl2, double tjp, double tjl,
                                  double ybl, double wmg, double wmj, double wml, double kmb, String rec, String dir1,
                                  String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(GARIS_SUMBU_PANJANG, gsp);
        contentValues.put(GARIS_SUMBU_LEBAR, gsl);
        contentValues.put(GARIS_SUMBU_JARAK, gsj);
        contentValues.put(GARIS_PERINGATAN_PANJANG, gpp);
        contentValues.put(GARIS_PERINGATAN_LEBAR, gpl);
        contentValues.put(GARIS_PERINGATAN_JARAK, gpj);
        contentValues.put(YIELD_LINE_PANJANG, ylp);
        contentValues.put(YIELD_LINE_LEBAR, yll);
        contentValues.put(YIELD_LINE_JARAK, ylj);
        contentValues.put(ZEBRA_CROSS_PANJANG, zcp);
        contentValues.put(ZEBRA_CROSS_LEBAR, zcl);
        contentValues.put(ZEBRA_CROSS_JARAK_GARIS, zca);
        contentValues.put(ZEBRA_CROSS_JARAK_STOP, zcg);
        contentValues.put(GARIS_PENGARAH_PANJANG, gpm);
        contentValues.put(GARIS_PENGARAH_LEBAR, gpl2);
        contentValues.put(GARIS_PENDEKAT_PANJANG, gpp2);
        contentValues.put(GARIS_PENDEKAT_LEBAR, gpl3);
        contentValues.put(MC_LEBAR_UJUNG, mcu);
        contentValues.put(MC_LEBAR_DALAM, mcd);
        contentValues.put(MC_LEBAR_GARIS_SERONG, mcs);
        contentValues.put(MC_JARAK_BATAS, mcj);
        contentValues.put(GARIS_STOP_LEBAR, gsl2);
        contentValues.put(TANDA_PENGARAH_PANJANG, tjp);
        contentValues.put(TANDA_PENGARAH_LEBAR, tjl);
        contentValues.put(YELLOW_BOX_LEBAR, ybl);
        contentValues.put(WARNA_MARKA_PUTIH, wmg);
        contentValues.put(WARNA_MARKA_KUNING, wmj);
        contentValues.put(WARNA_MARKA_MERAH, wml);
        contentValues.put(KONDISI_MARKA, kmb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANAS, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6a1Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANAS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6a2
    public Boolean insertDataA6a2(String id, String iseg, double udr, double uwr, double uwr2, double uwr3, double uwr4, double uwr5,
                                  double ljp, double ljp2, double ljp3, double ljj, double ljj2, double ljt,
                                  double ljt2, double ljt3, double ljt4, double ppr, double ppr2, double ppr3,
                                  double ppr4, String rec, String dir1, String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(UKURAN_DAUN_RAMBU, udr);
        contentValues.put(WARNA_RAMBU_PERINGATAN, uwr);
        contentValues.put(WARNA_RAMBU_LARANGAN, uwr2);
        contentValues.put(WARNA_RAMBU_PERINTAH, uwr3);
        contentValues.put(WARNA_RAMBU_PETUNJUK, uwr4);
        contentValues.put(WARNA_PAPAN_TAMBAHAN, uwr5);
        contentValues.put(POSISI_RAMBU1, ljp);
        contentValues.put(POSISI_RAMBU2, ljp2);
        contentValues.put(POSISI_RAMBU3, ljp3);
        contentValues.put(JARAK_06, ljj);
        contentValues.put(JARAK_03, ljj2);
        contentValues.put(TINGGI1, ljt);
        contentValues.put(TINGGI2, ljt2);
        contentValues.put(TINGGI3, ljt3);
        contentValues.put(TINGGI4, ljt4);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU1, ppr);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU2, ppr2);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU3, ppr3);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU4, ppr4);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANAD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6A2_Class> allDataA6a2(String iseg) {
        List<A6A2_Class> a6a2ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANAD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6A2_Class a6a2 = new A6A2_Class();
                a6a2.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6a2.setUDR(cursor.getDouble(cursor.getColumnIndex(UKURAN_DAUN_RAMBU)));
                a6a2.setUWR(cursor.getDouble(cursor.getColumnIndex(WARNA_RAMBU_PERINGATAN)));
                a6a2.setUWR2(cursor.getDouble(cursor.getColumnIndex(WARNA_RAMBU_LARANGAN)));
                a6a2.setUWR3(cursor.getDouble(cursor.getColumnIndex(WARNA_RAMBU_PERINTAH)));
                a6a2.setUWR4(cursor.getDouble(cursor.getColumnIndex(WARNA_RAMBU_PETUNJUK)));
                a6a2.setUWR5(cursor.getDouble(cursor.getColumnIndex(WARNA_PAPAN_TAMBAHAN)));
                a6a2.setLJP(cursor.getDouble(cursor.getColumnIndex(POSISI_RAMBU1)));
                a6a2.setLJP2(cursor.getDouble(cursor.getColumnIndex(POSISI_RAMBU2)));
                a6a2.setLJP3(cursor.getDouble(cursor.getColumnIndex(POSISI_RAMBU3)));
                a6a2.setLJJ(cursor.getDouble(cursor.getColumnIndex(JARAK_06)));
                a6a2.setLJJ2(cursor.getDouble(cursor.getColumnIndex(JARAK_03)));
                a6a2.setLJT(cursor.getDouble(cursor.getColumnIndex(TINGGI1)));
                a6a2.setLJT2(cursor.getDouble(cursor.getColumnIndex(TINGGI2)));
                a6a2.setLJT3(cursor.getDouble(cursor.getColumnIndex(TINGGI3)));
                a6a2.setLJT4(cursor.getDouble(cursor.getColumnIndex(TINGGI4)));
                a6a2.setPPR(cursor.getDouble(cursor.getColumnIndex(PONDASI_TIANG_PAPAN_RAMBU1)));
                a6a2.setPPR2(cursor.getDouble(cursor.getColumnIndex(PONDASI_TIANG_PAPAN_RAMBU2)));
                a6a2.setPPR3(cursor.getDouble(cursor.getColumnIndex(PONDASI_TIANG_PAPAN_RAMBU3)));
                a6a2.setPPR4(cursor.getDouble(cursor.getColumnIndex(PONDASI_TIANG_PAPAN_RAMBU4)));
                a6a2.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6a2.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6a2.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6a2.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a6a2.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a6a2.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6a2ArrayList.add(a6a2);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6a2ArrayList;

    }

    public Boolean updateDataA6a2(String id, String iseg, double udr, double uwr, double uwr2, double uwr3, double uwr4, double uwr5,
                                  double ljp, double ljp2, double ljp3, double ljj, double ljj2, double ljt,
                                  double ljt2, double ljt3, double ljt4, double ppr, double ppr2, double ppr3,
                                  double ppr4, String rec, String dir1, String dir2, String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(UKURAN_DAUN_RAMBU, udr);
        contentValues.put(WARNA_RAMBU_PERINGATAN, uwr);
        contentValues.put(WARNA_RAMBU_LARANGAN, uwr2);
        contentValues.put(WARNA_RAMBU_PERINTAH, uwr3);
        contentValues.put(WARNA_RAMBU_PETUNJUK, uwr4);
        contentValues.put(WARNA_PAPAN_TAMBAHAN, uwr5);
        contentValues.put(POSISI_RAMBU1, ljp);
        contentValues.put(POSISI_RAMBU2, ljp2);
        contentValues.put(POSISI_RAMBU3, ljp3);
        contentValues.put(JARAK_06, ljj);
        contentValues.put(JARAK_03, ljj2);
        contentValues.put(TINGGI1, ljt);
        contentValues.put(TINGGI2, ljt2);
        contentValues.put(TINGGI3, ljt3);
        contentValues.put(TINGGI4, ljt4);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU1, ppr);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU2, ppr2);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU3, ppr3);
        contentValues.put(PONDASI_TIANG_PAPAN_RAMBU4, ppr4);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANAD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6a2Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANAD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6a3
    public Boolean insertDataA6a3(String id, String iseg, double bus, double but, double bul, double lub,
                                  double lul, double luj, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(BENTUK_UKURAN_SISILUAR, bus);
        contentValues.put(BENTUK_UKURAN_TINGGI, but);
        contentValues.put(BENTUK_UKURAN_LEBAR, bul);
        contentValues.put(LETAK_UKURAN_BUKAAN, lub);
        contentValues.put(LETAK_UKURAN_LEBAR_BUKAAN, lul);
        contentValues.put(BENTUK_UKURAN_JARAK_BUKAAN, luj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANAT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6A3_Class> allDataA6a3(String iseg) {
        List<A6A3_Class> a6a3ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANAT + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6A3_Class a6a3 = new A6A3_Class();
                a6a3.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6a3.setBUS(cursor.getDouble(cursor.getColumnIndex(BENTUK_UKURAN_SISILUAR)));
                a6a3.setBUT(cursor.getDouble(cursor.getColumnIndex(BENTUK_UKURAN_TINGGI)));
                a6a3.setBUL(cursor.getDouble(cursor.getColumnIndex(BENTUK_UKURAN_LEBAR)));
                a6a3.setLUB(cursor.getDouble(cursor.getColumnIndex(LETAK_UKURAN_BUKAAN)));
                a6a3.setLUL(cursor.getDouble(cursor.getColumnIndex(LETAK_UKURAN_LEBAR_BUKAAN)));
                a6a3.setLUJ(cursor.getDouble(cursor.getColumnIndex(BENTUK_UKURAN_JARAK_BUKAAN)));
                a6a3.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6a3.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6a3.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6a3.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6a3ArrayList.add(a6a3);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6a3ArrayList;

    }

    public Boolean updateDataA6a3(String id, String iseg, double bus, double but, double bul, double lub,
                                  double lul, double luj, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(BENTUK_UKURAN_SISILUAR, bus);
        contentValues.put(BENTUK_UKURAN_TINGGI, but);
        contentValues.put(BENTUK_UKURAN_LEBAR, bul);
        contentValues.put(LETAK_UKURAN_BUKAAN, lub);
        contentValues.put(LETAK_UKURAN_LEBAR_BUKAAN, lul);
        contentValues.put(BENTUK_UKURAN_JARAK_BUKAAN, luj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANAT, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6a3Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANAT + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6a4
    public Boolean insertDataA6a4(String id, String iseg, double jlk, double tkm, double dgp, double dgl,
                                  double dgb, double dgj, double gpl, double gpg, double clu, double clc,
                                  double csc, double cbc, double rpt, double rpt2, double rpt3, double rpt4,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LAJUR_LAPAK_KENDARAAN, jlk);
        contentValues.put(TINGGI_KERB_ANAE, tkm);
        contentValues.put(GARIS_PERINGATAN_PANJANG_ANAE, dgp);
        contentValues.put(GARIS_PERINGATAN_LEBAR_ANAE, dgl);
        contentValues.put(GARIS_PERINGATAN_JARAK1, dgb);
        contentValues.put(GARIS_PERINGATAN_JARAK2, dgj);
        contentValues.put(GARIS_PENDEKAT_LEBAR_ANAE, gpl);
        contentValues.put(GARIS_PENDEKAT_PANJANG_ANAE, gpg);
        contentValues.put(CHEVRON_LEBAR_UJUNG, clu);
        contentValues.put(CHEVRON_LEBAR_GARIS, clc);
        contentValues.put(CHEVRON_SUDUT_GARIS, csc);
        contentValues.put(CHEVRON_PANJANG_JARAK, cbc);
        contentValues.put(RAMBU_TIKUNGAN_TINGGI_TIANG, rpt);
        contentValues.put(RAMBU_TIKUNGAN_UKURAN_DAUN, rpt2);
        contentValues.put(RAMBU_PERINTAH_TINGGI_TIANG, rpt3);
        contentValues.put(RAMBU_PERINTAH_UKURAN_DAUN, rpt4);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANAE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6A4_Class> allDataA6a4(String iseg) {
        List<A6A4_Class> a6a4ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANAE + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6A4_Class a6a4 = new A6A4_Class();
                a6a4.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6a4.setJLK(cursor.getDouble(cursor.getColumnIndex(LAJUR_LAPAK_KENDARAAN)));
                a6a4.setTKM(cursor.getDouble(cursor.getColumnIndex(TINGGI_KERB_ANAE)));
                a6a4.setDGP(cursor.getDouble(cursor.getColumnIndex(GARIS_PERINGATAN_PANJANG_ANAE)));
                a6a4.setDGL(cursor.getDouble(cursor.getColumnIndex(GARIS_PERINGATAN_LEBAR_ANAE)));
                a6a4.setDGB(cursor.getDouble(cursor.getColumnIndex(GARIS_PERINGATAN_JARAK1)));
                a6a4.setDGJ(cursor.getDouble(cursor.getColumnIndex(GARIS_PERINGATAN_JARAK2)));
                a6a4.setGPL(cursor.getDouble(cursor.getColumnIndex(GARIS_PENDEKAT_LEBAR_ANAE)));
                a6a4.setGPG(cursor.getDouble(cursor.getColumnIndex(GARIS_PENDEKAT_PANJANG_ANAE)));
                a6a4.setCLU(cursor.getDouble(cursor.getColumnIndex(CHEVRON_LEBAR_UJUNG)));
                a6a4.setCLC(cursor.getDouble(cursor.getColumnIndex(CHEVRON_LEBAR_GARIS)));
                a6a4.setCSC(cursor.getDouble(cursor.getColumnIndex(CHEVRON_SUDUT_GARIS)));
                a6a4.setCBC(cursor.getDouble(cursor.getColumnIndex(CHEVRON_PANJANG_JARAK)));
                a6a4.setRPT(cursor.getDouble(cursor.getColumnIndex(RAMBU_TIKUNGAN_TINGGI_TIANG)));
                a6a4.setRPT2(cursor.getDouble(cursor.getColumnIndex(RAMBU_TIKUNGAN_UKURAN_DAUN)));
                a6a4.setRPT3(cursor.getDouble(cursor.getColumnIndex(RAMBU_PERINTAH_TINGGI_TIANG)));
                a6a4.setRPT4(cursor.getDouble(cursor.getColumnIndex(RAMBU_PERINTAH_UKURAN_DAUN)));
                a6a4.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6a4.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6a4.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6a4.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6a4ArrayList.add(a6a4);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6a4ArrayList;

    }

    public Boolean updateDataA6a4(String id, String iseg, double jlk, double tkm, double dgp, double dgl,
                                  double dgb, double dgj, double gpl, double gpg, double clu, double clc,
                                  double csc, double cbc, double rpt, double rpt2, double rpt3, double rpt4,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LAJUR_LAPAK_KENDARAAN, jlk);
        contentValues.put(TINGGI_KERB_ANAE, tkm);
        contentValues.put(GARIS_PERINGATAN_PANJANG_ANAE, dgp);
        contentValues.put(GARIS_PERINGATAN_LEBAR_ANAE, dgl);
        contentValues.put(GARIS_PERINGATAN_JARAK1, dgb);
        contentValues.put(GARIS_PERINGATAN_JARAK2, dgj);
        contentValues.put(GARIS_PENDEKAT_LEBAR_ANAE, gpl);
        contentValues.put(GARIS_PENDEKAT_PANJANG_ANAE, gpg);
        contentValues.put(CHEVRON_LEBAR_UJUNG, clu);
        contentValues.put(CHEVRON_LEBAR_GARIS, clc);
        contentValues.put(CHEVRON_SUDUT_GARIS, csc);
        contentValues.put(CHEVRON_PANJANG_JARAK, cbc);
        contentValues.put(RAMBU_TIKUNGAN_TINGGI_TIANG, rpt);
        contentValues.put(RAMBU_TIKUNGAN_UKURAN_DAUN, rpt2);
        contentValues.put(RAMBU_PERINTAH_TINGGI_TIANG, rpt3);
        contentValues.put(RAMBU_PERINTAH_UKURAN_DAUN, rpt4);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANAE, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6a4Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANAE + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6a5
    public Boolean insertDataA6a5(String id, String iseg, double lbt, double lbt2, double lbt3, double btk, double btk2, double pkt,
                                  double pkt2, double pkt3, double pkt4, double fpc, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_TROTOAR_TEROWONGAN, lbt);
        contentValues.put(LEBAR_TROTOAR_PERMUKIMAN, lbt2);
        contentValues.put(LEBAR_TROTOAR_PERKANTORAN, lbt3);
        contentValues.put(BENTUK_KERB, btk);
        contentValues.put(TINGGI_KERB, btk2);
        contentValues.put(PERKERASAN_TROTOAR_BALOK_BETON, pkt);
        contentValues.put(PERKERASAN_TROTOAR_BETON, pkt2);
        contentValues.put(PERKERASAN_TROTOAR_LATASIR, pkt3);
        contentValues.put(PERKERASAN_TROTOAR_PLESTERAN, pkt4);
        contentValues.put(FASILITAS_PENYANDANG_CACAT, fpc);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANALI, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6A5_Class> allDataA6a5(String iseg) {
        List<A6A5_Class> a6a5ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANALI + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6A5_Class a6a5 = new A6A5_Class();
                a6a5.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6a5.setLBT(cursor.getDouble(cursor.getColumnIndex(LEBAR_TROTOAR_TEROWONGAN)));
                a6a5.setLBT2(cursor.getDouble(cursor.getColumnIndex(LEBAR_TROTOAR_PERMUKIMAN)));
                a6a5.setLBT3(cursor.getDouble(cursor.getColumnIndex(LEBAR_TROTOAR_PERKANTORAN)));
                a6a5.setBTK(cursor.getDouble(cursor.getColumnIndex(BENTUK_KERB)));
                a6a5.setBTK2(cursor.getDouble(cursor.getColumnIndex(TINGGI_KERB)));
                a6a5.setPKT(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_TROTOAR_BALOK_BETON)));
                a6a5.setPKT2(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_TROTOAR_BETON)));
                a6a5.setPKT3(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_TROTOAR_LATASIR)));
                a6a5.setPKT4(cursor.getDouble(cursor.getColumnIndex(PERKERASAN_TROTOAR_PLESTERAN)));
                a6a5.setFPC(cursor.getDouble(cursor.getColumnIndex(FASILITAS_PENYANDANG_CACAT)));
                a6a5.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6a5.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6a5.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6a5.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6a5ArrayList.add(a6a5);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6a5ArrayList;

    }

    public Boolean updateDataA6a5(String id, String iseg, double lbt, double lbt2, double lbt3, double btk, double btk2, double pkt,
                                  double pkt2, double pkt3, double pkt4, double fpc, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LEBAR_TROTOAR_TEROWONGAN, lbt);
        contentValues.put(LEBAR_TROTOAR_PERMUKIMAN, lbt2);
        contentValues.put(LEBAR_TROTOAR_PERKANTORAN, lbt3);
        contentValues.put(BENTUK_KERB, btk);
        contentValues.put(TINGGI_KERB, btk2);
        contentValues.put(PERKERASAN_TROTOAR_BALOK_BETON, pkt);
        contentValues.put(PERKERASAN_TROTOAR_BETON, pkt2);
        contentValues.put(PERKERASAN_TROTOAR_LATASIR, pkt3);
        contentValues.put(PERKERASAN_TROTOAR_PLESTERAN, pkt4);
        contentValues.put(FASILITAS_PENYANDANG_CACAT, fpc);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANALI, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6a5Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANALI + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6a6
    public Boolean insertDataA6a6(String id, String iseg, double lta, double ltl, double ltl2, double tpa, double tpa2, double tpa3,
                                  double dla, double icl, double kaa, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LAMPU_APILL_DAPAT_DILIHAT, lta);
        contentValues.put(LETAK_TIANG_LAMPU1, ltl);
        contentValues.put(LETAK_TIANG_LAMPU2, ltl2);
        contentValues.put(TINGGI_PENEMPATAN_ARMATUR1, tpa);
        contentValues.put(TINGGI_PENEMPATAN_ARMATUR2, tpa2);
        contentValues.put(TINGGI_PENEMPATAN_ARMATUR3, tpa3);
        contentValues.put(DIMENSI_LAMPU_APILL, dla);
        contentValues.put(INTENSITAS_CAHAYA_LAMPU_APILL, icl);
        contentValues.put(KEAMANAN_ALAT_APILL, kaa);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANAN, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6A6_Class> allDataA6a6(String iseg) {
        List<A6A6_Class> a6a6ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANAN + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6A6_Class a6a6 = new A6A6_Class();
                a6a6.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6a6.setLTA(cursor.getDouble(cursor.getColumnIndex(LAMPU_APILL_DAPAT_DILIHAT)));
                a6a6.setLTL(cursor.getDouble(cursor.getColumnIndex(LETAK_TIANG_LAMPU1)));
                a6a6.setLTL2(cursor.getDouble(cursor.getColumnIndex(LETAK_TIANG_LAMPU2)));
                a6a6.setTPA(cursor.getDouble(cursor.getColumnIndex(TINGGI_PENEMPATAN_ARMATUR1)));
                a6a6.setTPA2(cursor.getDouble(cursor.getColumnIndex(TINGGI_PENEMPATAN_ARMATUR2)));
                a6a6.setTPA3(cursor.getDouble(cursor.getColumnIndex(TINGGI_PENEMPATAN_ARMATUR3)));
                a6a6.setDLA(cursor.getDouble(cursor.getColumnIndex(DIMENSI_LAMPU_APILL)));
                a6a6.setICL(cursor.getDouble(cursor.getColumnIndex(INTENSITAS_CAHAYA_LAMPU_APILL)));
                a6a6.setKAA(cursor.getDouble(cursor.getColumnIndex(KEAMANAN_ALAT_APILL)));
                a6a6.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6a6.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6a6.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6a6.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6a6ArrayList.add(a6a6);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6a6ArrayList;

    }

    public Boolean updateDataA6a6(String id, String iseg, double lta, double ltl, double ltl2, double tpa, double tpa2, double tpa3,
                                  double dla, double icl, double kaa, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LAMPU_APILL_DAPAT_DILIHAT, lta);
        contentValues.put(LETAK_TIANG_LAMPU1, ltl);
        contentValues.put(LETAK_TIANG_LAMPU2, ltl2);
        contentValues.put(TINGGI_PENEMPATAN_ARMATUR1, tpa);
        contentValues.put(TINGGI_PENEMPATAN_ARMATUR2, tpa2);
        contentValues.put(TINGGI_PENEMPATAN_ARMATUR3, tpa3);
        contentValues.put(DIMENSI_LAMPU_APILL, dla);
        contentValues.put(INTENSITAS_CAHAYA_LAMPU_APILL, icl);
        contentValues.put(KEAMANAN_ALAT_APILL, kaa);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);


        long result = db.update(TABEL_ANAN, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6a6Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANAN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6a7
    public Boolean insertDataA6a7(String id, String iseg, double tpk, double rmp, double rmp2, double ppb, double lpp, double lpp2,
                                  double lpt, double lpt2, double lpj, double pct, double pct2, double pgd, double ptb, double ptb2,
                                  double fpp, double fpp2, double fjp, double fjp2, double fpr, String rec, String dir1, String dir2,
                                  String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(TEMPAT_PARKIR, tpk);
        contentValues.put(RAMBU_PETUNJUK_PARKIR, rmp);
        contentValues.put(RAMBU_LARANGAN_PARKIR, rmp2);
        contentValues.put(PEMBERHENTIAN_BUS_ANGKOT, ppb);
        contentValues.put(LAMPU_PENERANGAN_PENEMPATAN, lpp);
        contentValues.put(LAMPU_PENERANGAN_PENEMPATAN2, lpp2);
        contentValues.put(LAMPU_PENERANGAN_TINGGI_TIANG_STANDAR, lpt);
        contentValues.put(LAMPU_PENERANGAN_TINGGI_TIANG_MONARA, lpt2);
        contentValues.put(JARAK_INTERNAL_TIANG_LAMPU, lpj);
        contentValues.put(PIPA_CARBON_TEBAL2, pct);
        contentValues.put(PIPA_CARBON_TEBAL3, pct2);
        contentValues.put(PIPA_GALVANISED, pgd);
        contentValues.put(PELANDAIAN_TROTOAR1, ptb);
        contentValues.put(PELANDAIAN_TROTOAR2, ptb2);
        contentValues.put(PENYEBRANGAN_PELICAN1, fpp);
        contentValues.put(PENYEBRANGAN_PELICAN2, fpp2);
        contentValues.put(JEMBATAN_PENYEBRAGAN1, fjp);
        contentValues.put(JEMBATAN_PENYEBRAGAN2, fjp2);
        contentValues.put(RAMBU_MARKA_AKSESBILITAS, fpr);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANATU, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6A7_Class> allDataA6a7(String iseg) {
        List<A6A7_Class> a6a7ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANATU + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6A7_Class a6a7 = new A6A7_Class();
                a6a7.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6a7.setTPK(cursor.getDouble(cursor.getColumnIndex(TEMPAT_PARKIR)));
                a6a7.setRMP(cursor.getDouble(cursor.getColumnIndex(RAMBU_PETUNJUK_PARKIR)));
                a6a7.setRMP2(cursor.getDouble(cursor.getColumnIndex(RAMBU_LARANGAN_PARKIR)));
                a6a7.setPPB(cursor.getDouble(cursor.getColumnIndex(PEMBERHENTIAN_BUS_ANGKOT)));
                a6a7.setLPP(cursor.getDouble(cursor.getColumnIndex(LAMPU_PENERANGAN_PENEMPATAN)));
                a6a7.setLPP2(cursor.getDouble(cursor.getColumnIndex(LAMPU_PENERANGAN_PENEMPATAN2)));
                a6a7.setLPT(cursor.getDouble(cursor.getColumnIndex(LAMPU_PENERANGAN_TINGGI_TIANG_STANDAR)));
                a6a7.setLPT2(cursor.getDouble(cursor.getColumnIndex(LAMPU_PENERANGAN_TINGGI_TIANG_MONARA)));
                a6a7.setLPJ(cursor.getDouble(cursor.getColumnIndex(JARAK_INTERNAL_TIANG_LAMPU)));
                a6a7.setPCT(cursor.getDouble(cursor.getColumnIndex(PIPA_CARBON_TEBAL2)));
                a6a7.setPCT2(cursor.getDouble(cursor.getColumnIndex(PIPA_CARBON_TEBAL3)));
                a6a7.setPGD(cursor.getDouble(cursor.getColumnIndex(PIPA_GALVANISED)));
                a6a7.setPTB(cursor.getDouble(cursor.getColumnIndex(PELANDAIAN_TROTOAR1)));
                a6a7.setPTB2(cursor.getDouble(cursor.getColumnIndex(PELANDAIAN_TROTOAR2)));
                a6a7.setFPP(cursor.getDouble(cursor.getColumnIndex(PENYEBRANGAN_PELICAN1)));
                a6a7.setFPP2(cursor.getDouble(cursor.getColumnIndex(PENYEBRANGAN_PELICAN2)));
                a6a7.setFJP(cursor.getDouble(cursor.getColumnIndex(JEMBATAN_PENYEBRAGAN1)));
                a6a7.setFJP2(cursor.getDouble(cursor.getColumnIndex(JEMBATAN_PENYEBRAGAN2)));
                a6a7.setFPR(cursor.getDouble(cursor.getColumnIndex(RAMBU_MARKA_AKSESBILITAS)));
                a6a7.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6a7.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6a7.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6a7.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a6a7.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a6a7.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6a7ArrayList.add(a6a7);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6a7ArrayList;

    }

    public Boolean updateDataA6a7(String id, String iseg, double tpk, double rmp, double rmp2, double ppb, double lpp, double lpp2,
                                  double lpt, double lpt2, double lpj, double pct, double pct2, double pgd, double ptb, double ptb2,
                                  double fpp, double fpp2, double fjp, double fjp2, double fpr, String rec, String dir1, String dir2,
                                  String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(TEMPAT_PARKIR, tpk);
        contentValues.put(RAMBU_PETUNJUK_PARKIR, rmp);
        contentValues.put(RAMBU_LARANGAN_PARKIR, rmp2);
        contentValues.put(PEMBERHENTIAN_BUS_ANGKOT, ppb);
        contentValues.put(LAMPU_PENERANGAN_PENEMPATAN, lpp);
        contentValues.put(LAMPU_PENERANGAN_PENEMPATAN2, lpp2);
        contentValues.put(LAMPU_PENERANGAN_TINGGI_TIANG_STANDAR, lpt);
        contentValues.put(LAMPU_PENERANGAN_TINGGI_TIANG_MONARA, lpt2);
        contentValues.put(JARAK_INTERNAL_TIANG_LAMPU, lpj);
        contentValues.put(PIPA_CARBON_TEBAL2, pct);
        contentValues.put(PIPA_CARBON_TEBAL3, pct2);
        contentValues.put(PIPA_GALVANISED, pgd);
        contentValues.put(PELANDAIAN_TROTOAR1, ptb);
        contentValues.put(PELANDAIAN_TROTOAR2, ptb2);
        contentValues.put(PENYEBRANGAN_PELICAN1, fpp);
        contentValues.put(PENYEBRANGAN_PELICAN2, fpp2);
        contentValues.put(JEMBATAN_PENYEBRAGAN1, fjp);
        contentValues.put(JEMBATAN_PENYEBRAGAN2, fjp2);
        contentValues.put(RAMBU_MARKA_AKSESBILITAS, fpr);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);


        long result = db.update(TABEL_ANATU, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6a7Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANATU + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b1
    public Boolean insertDataA6b1(String id, String iseg, double skm, double lbw, double lbw2, double lbw3, double lbw4, double kfb,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(SESUAI_KEBUTUHAN, skm);
        contentValues.put(LETAK_LUAR_BADAN_JALAN, lbw);
        contentValues.put(JARAK_PATOK_PENGARAH, lbw2);
        contentValues.put(BENTUK_PERSEGI, lbw3);
        contentValues.put(WARNA_REFLEKTIF, lbw4);
        contentValues.put(KONDISI_FISIK, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBS, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B1_Class> allDataA6b1(String iseg) {
        List<A6B1_Class> a6b1ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBS + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B1_Class a6b1 = new A6B1_Class();
                a6b1.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b1.setSKM(cursor.getDouble(cursor.getColumnIndex(SESUAI_KEBUTUHAN)));
                a6b1.setLBW(cursor.getDouble(cursor.getColumnIndex(LETAK_LUAR_BADAN_JALAN)));
                a6b1.setLBW2(cursor.getDouble(cursor.getColumnIndex(JARAK_PATOK_PENGARAH)));
                a6b1.setLBW3(cursor.getDouble(cursor.getColumnIndex(BENTUK_PERSEGI)));
                a6b1.setLBW4(cursor.getDouble(cursor.getColumnIndex(WARNA_REFLEKTIF)));
                a6b1.setKFB(cursor.getDouble(cursor.getColumnIndex(KONDISI_FISIK)));
                a6b1.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b1.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b1.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6b1.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b1ArrayList.add(a6b1);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b1ArrayList;

    }

    public Boolean updateDataA6b1(String id, String iseg, double skm, double lbw, double lbw2, double lbw3, double lbw4, double kfb,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(SESUAI_KEBUTUHAN, skm);
        contentValues.put(LETAK_LUAR_BADAN_JALAN, lbw);
        contentValues.put(JARAK_PATOK_PENGARAH, lbw2);
        contentValues.put(BENTUK_PERSEGI, lbw3);
        contentValues.put(WARNA_REFLEKTIF, lbw4);
        contentValues.put(KONDISI_FISIK, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBS, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b1Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b2
    public Boolean insertDataA6b2(String id, String iseg, double kkh, double dld, double dld2, double dld3, double dll, double dll2,
                                  double dlw, double dlt, double kfb, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KELENGKAPAN_PER_KM, kkh);
        contentValues.put(DIMENSI_TINGGI, dld);
        contentValues.put(DIMENSI_LEBAR, dld2);
        contentValues.put(DIMENSI_GARIS, dld3);
        contentValues.put(LETAK_DILUAR_BADAN_JALAN, dll);
        contentValues.put(LETAK_PADA_MEDIAN_JALAN, dll2);
        contentValues.put(WARNA_REFLEKTIF_ANBD, dlw);
        contentValues.put(TULISAN_TERBACA_JELAS, dlt);
        contentValues.put(KONDISI_FISIK_ANBD, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBD, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B2_Class> allDataA6b2(String iseg) {
        List<A6B2_Class> a6b2ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBD + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B2_Class a6b2 = new A6B2_Class();
                a6b2.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b2.setKKH(cursor.getDouble(cursor.getColumnIndex(KELENGKAPAN_PER_KM)));
                a6b2.setDLD(cursor.getDouble(cursor.getColumnIndex(DIMENSI_TINGGI)));
                a6b2.setDLD2(cursor.getDouble(cursor.getColumnIndex(DIMENSI_LEBAR)));
                a6b2.setDLD3(cursor.getDouble(cursor.getColumnIndex(DIMENSI_GARIS)));
                a6b2.setDLL(cursor.getDouble(cursor.getColumnIndex(LETAK_DILUAR_BADAN_JALAN)));
                a6b2.setDLL2(cursor.getDouble(cursor.getColumnIndex(LETAK_PADA_MEDIAN_JALAN)));
                a6b2.setDLW(cursor.getDouble(cursor.getColumnIndex(WARNA_REFLEKTIF_ANBD)));
                a6b2.setDLT(cursor.getDouble(cursor.getColumnIndex(TULISAN_TERBACA_JELAS)));
                a6b2.setKFB(cursor.getDouble(cursor.getColumnIndex(KONDISI_FISIK_ANBD)));
                a6b2.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b2.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b2.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6b2.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b2ArrayList.add(a6b2);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b2ArrayList;

    }

    public Boolean updateDataA6b2(String id, String iseg, double kkh, double dld, double dld2, double dld3, double dll, double dll2,
                                  double dlw, double dlt, double kfb, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KELENGKAPAN_PER_KM, kkh);
        contentValues.put(DIMENSI_TINGGI, dld);
        contentValues.put(DIMENSI_LEBAR, dld2);
        contentValues.put(DIMENSI_GARIS, dld3);
        contentValues.put(LETAK_DILUAR_BADAN_JALAN, dll);
        contentValues.put(LETAK_PADA_MEDIAN_JALAN, dll2);
        contentValues.put(WARNA_REFLEKTIF_ANBD, dlw);
        contentValues.put(TULISAN_TERBACA_JELAS, dlt);
        contentValues.put(KONDISI_FISIK_ANBD, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBD, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b2Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b3
    public Boolean insertDataA6b3(String id, String iseg, double kkh, double dld, double dld2, double dll, double dll2,
                                  double dlw, double dlt, double kfb, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KELENGKAPAN_PER_KM_ANBT, kkh);
        contentValues.put(DIMENSI_TINGGI_ANBT, dld);
        contentValues.put(DIMENSI_UKURAN_ANBT, dld2);
        contentValues.put(LETAK_DILUAR_BADAN_JALAN_ANBT, dll);
        contentValues.put(LETAK_PADA_MEDIAN_JALAN_ANBT, dll2);
        contentValues.put(WARNA_REFLEKTIF_ANBD_ANBT, dlw);
        contentValues.put(TULISAN_TERBACA_JELAS_ANBT, dlt);
        contentValues.put(KONDISI_FISIK_ANBT, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBT, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B3_Class> allDataA6b3(String iseg) {
        List<A6B3_Class> a6b3ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBT + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B3_Class a6b3 = new A6B3_Class();
                a6b3.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b3.setKKH(cursor.getDouble(cursor.getColumnIndex(KELENGKAPAN_PER_KM_ANBT)));
                a6b3.setDLD(cursor.getDouble(cursor.getColumnIndex(DIMENSI_TINGGI_ANBT)));
                a6b3.setDLD2(cursor.getDouble(cursor.getColumnIndex(DIMENSI_UKURAN_ANBT)));
                a6b3.setDLL(cursor.getDouble(cursor.getColumnIndex(LETAK_DILUAR_BADAN_JALAN_ANBT)));
                a6b3.setDLL2(cursor.getDouble(cursor.getColumnIndex(LETAK_PADA_MEDIAN_JALAN_ANBT)));
                a6b3.setDLW(cursor.getDouble(cursor.getColumnIndex(WARNA_REFLEKTIF_ANBD_ANBT)));
                a6b3.setDLT(cursor.getDouble(cursor.getColumnIndex(TULISAN_TERBACA_JELAS_ANBT)));
                a6b3.setKFB(cursor.getDouble(cursor.getColumnIndex(KONDISI_FISIK_ANBT)));
                a6b3.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b3.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b3.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6b3.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b3ArrayList.add(a6b3);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b3ArrayList;

    }

    public Boolean updateDataA6b3(String id, String iseg, double kkh, double dld, double dld2, double dll, double dll2,
                                  double dlw, double dlt, double kfb, String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KELENGKAPAN_PER_KM_ANBT, kkh);
        contentValues.put(DIMENSI_TINGGI_ANBT, dld);
        contentValues.put(DIMENSI_UKURAN_ANBT, dld2);
        contentValues.put(LETAK_DILUAR_BADAN_JALAN_ANBT, dll);
        contentValues.put(LETAK_PADA_MEDIAN_JALAN_ANBT, dll2);
        contentValues.put(WARNA_REFLEKTIF_ANBD_ANBT, dlw);
        contentValues.put(TULISAN_TERBACA_JELAS_ANBT, dlt);
        contentValues.put(KONDISI_FISIK_ANBT, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBT, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b3Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBT + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b4
    public Boolean insertDataA6b4(String id, String iseg, double kbk2, double kbk3, double kdr, double ktj, double kfb,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(BENTUK_KOLOM_BETON2, kbk2);
        contentValues.put(BENTUK_KOLOM_BETON3, kbk3);
        contentValues.put(DIPASANG_SETIAP_JARAK50, kdr);
        contentValues.put(TULISAN_TERBACA_JELAS_ANBE, ktj);
        contentValues.put(KONDISI_FISIK_ANBE, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B4_Class> allDataA6b4(String iseg) {
        List<A6B4_Class> a6b4ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBE + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B4_Class a6b4 = new A6B4_Class();
                a6b4.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b4.setKBK2(cursor.getDouble(cursor.getColumnIndex(BENTUK_KOLOM_BETON2)));
                a6b4.setKBK3(cursor.getDouble(cursor.getColumnIndex(BENTUK_KOLOM_BETON3)));
                a6b4.setKDR(cursor.getDouble(cursor.getColumnIndex(DIPASANG_SETIAP_JARAK50)));
                a6b4.setKTJ(cursor.getDouble(cursor.getColumnIndex(TULISAN_TERBACA_JELAS_ANBE)));
                a6b4.setKFB(cursor.getDouble(cursor.getColumnIndex(KONDISI_FISIK_ANBE)));
                a6b4.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b4.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b4.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6b4.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b4ArrayList.add(a6b4);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b4ArrayList;

    }

    public Boolean updateDataA6b4(String id, String iseg, double kbk2, double kbk3, double kdr, double ktj, double kfb,
                                  String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(BENTUK_KOLOM_BETON2, kbk2);
        contentValues.put(BENTUK_KOLOM_BETON3, kbk3);
        contentValues.put(DIPASANG_SETIAP_JARAK50, kdr);
        contentValues.put(TULISAN_TERBACA_JELAS_ANBE, ktj);
        contentValues.put(KONDISI_FISIK_ANBE, kfb);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBE, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b4Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBE + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b5
    public Boolean insertDataA6b5(String id, String iseg, double klj, double kbt, double kwr, double ktj, double kft,
                                  String rec, String dir1, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LETAK_PADA_TEPI_JALAN, klj);
        contentValues.put(BERBENTUK_TIANG, kbt);
        contentValues.put(WARNA_REFLEKTIF_ANBL, kwr);
        contentValues.put(TULISAN_TERBACA_JELAS_ANBL, ktj);
        contentValues.put(KONDISI_FISIK_ANBL, kft);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBL, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B5_Class> allDataA6b5(String iseg) {
        List<A6B5_Class> a6b5ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBL + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B5_Class a6b5 = new A6B5_Class();
                a6b5.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b5.setKLJ(cursor.getDouble(cursor.getColumnIndex(LETAK_PADA_TEPI_JALAN)));
                a6b5.setKBT(cursor.getDouble(cursor.getColumnIndex(BERBENTUK_TIANG)));
                a6b5.setKWR(cursor.getDouble(cursor.getColumnIndex(WARNA_REFLEKTIF_ANBL)));
                a6b5.setKTJ(cursor.getDouble(cursor.getColumnIndex(TULISAN_TERBACA_JELAS_ANBL)));
                a6b5.setKFT(cursor.getDouble(cursor.getColumnIndex(KONDISI_FISIK_ANBL)));
                a6b5.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b5.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b5.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b5ArrayList.add(a6b5);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b5ArrayList;

    }

    public Boolean updateDataA6b5(String id, String iseg, double klj, double kbt, double kwr, double ktj, double kft,
                                  String rec, String dir1, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(LETAK_PADA_TEPI_JALAN, klj);
        contentValues.put(BERBENTUK_TIANG, kbt);
        contentValues.put(WARNA_REFLEKTIF_ANBL, kwr);
        contentValues.put(TULISAN_TERBACA_JELAS_ANBL, ktj);
        contentValues.put(KONDISI_FISIK_ANBL, kft);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBL, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b5Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBL + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b6
    public Boolean insertDataA6b6(String id, String iseg, double ppk, double kfp, double kfp2, double kfp3,
                                  String rec, String dir1, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(PERLINDUNGAN_THDP_PEJALANKAKI, ppk);
        contentValues.put(TERLETAK_DILUAR_RUANG, kfp);
        contentValues.put(TINGGI09, kfp2);
        contentValues.put(PIPA_YANG_DIPASANG, kfp3);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBN, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B6_Class> allDataA6b6(String iseg) {
        List<A6B6_Class> a6b6ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBN + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B6_Class a6b6 = new A6B6_Class();
                a6b6.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b6.setPPk(cursor.getDouble(cursor.getColumnIndex(PERLINDUNGAN_THDP_PEJALANKAKI)));
                a6b6.setKFP(cursor.getDouble(cursor.getColumnIndex(TERLETAK_DILUAR_RUANG)));
                a6b6.setKFP2(cursor.getDouble(cursor.getColumnIndex(TINGGI09)));
                a6b6.setKFP3(cursor.getDouble(cursor.getColumnIndex(PIPA_YANG_DIPASANG)));
                a6b6.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b6.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b6.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b6ArrayList.add(a6b6);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b6ArrayList;

    }

    public Boolean updateDataA6b6(String id, String iseg, double ppk, double kfp, double kfp2, double kfp3,
                                  String rec, String dir1, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(PERLINDUNGAN_THDP_PEJALANKAKI, ppk);
        contentValues.put(TERLETAK_DILUAR_RUANG, kfp);
        contentValues.put(TINGGI09, kfp2);
        contentValues.put(PIPA_YANG_DIPASANG, kfp3);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBN, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b6Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBN + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b7
    public Boolean insertDataA6b7(String id, String iseg, double kmi, double kal, double kal2, double kfi, double kfi2,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_ANBTU, kmi);
        contentValues.put(KETERGANGGUAN_THDP_ARUSLL1, kal);
        contentValues.put(KETERGANGGUAN_THDP_ARUSLL1, kal2);
        contentValues.put(KONDISI_FISIK_ANBTU1, kfi);
        contentValues.put(KONDISI_FISIK_ANBTU2, kfi2);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBTU, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B7_Class> allDataA6b7(String iseg) {
        List<A6B7_Class> a6b7ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBTU + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B7_Class a6b7 = new A6B7_Class();
                a6b7.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b7.setKMI(cursor.getDouble(cursor.getColumnIndex(KEBUTUHAN_ANBTU)));
                a6b7.setKAL(cursor.getDouble(cursor.getColumnIndex(KETERGANGGUAN_THDP_ARUSLL1)));
                a6b7.setKAL2(cursor.getDouble(cursor.getColumnIndex(KETERGANGGUAN_THDP_ARUSLL2)));
                a6b7.setKFI(cursor.getDouble(cursor.getColumnIndex(KONDISI_FISIK_ANBTU1)));
                a6b7.setKFI2(cursor.getDouble(cursor.getColumnIndex(KONDISI_FISIK_ANBTU2)));
                a6b7.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b7.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b7.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6b7.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b7ArrayList.add(a6b7);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b7ArrayList;

    }

    public Boolean updateDataA6b7(String id, String iseg, double kmi, double kal, double kal2, double kfi, double kfi2,
                                 String rec, String dir1, String dir2, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(KEBUTUHAN_ANBTU, kmi);
        contentValues.put(KETERGANGGUAN_THDP_ARUSLL1, kal);
        contentValues.put(KETERGANGGUAN_THDP_ARUSLL2, kal2);
        contentValues.put(KONDISI_FISIK_ANBTU1, kfi);
        contentValues.put(KONDISI_FISIK_ANBTU2, kfi2);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBTU, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b7Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBTU + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    //----------------------------------------------------------------------------------------------

    //CRUD Tabel A6b8
    public Boolean insertDataA6b8(String id, String iseg, double rpp, double rpp2, double rpp3, double rpp4, double rpp5,
                                  double rpp6, double rpp7, double ppj, String rec, String dir1, String dir2,
                                  String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_POINT, id);
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JARI_DARI_MARKA_ANBDE, rpp);
        contentValues.put(TINGGI_DARI_MUKA_TANAH_ANBDE, rpp2);
        contentValues.put(KEDALAMAN_REL_PENGAMAN_ANBDE, rpp3);
        contentValues.put(JARAK_ANTAR_TIANG_VERTIKAL_ANBDE, rpp4);
        contentValues.put(KONDISI_BAIK_ANBDE, rpp5);
        contentValues.put(TAHAN_BENTURAN_ANBDE, rpp6);
        contentValues.put(TERDAPAT_TANDA_REFLEKTOR_ANBDE, rpp7);
        contentValues.put(POS_POLISI_ANBDE, ppj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.insert(TABEL_ANBDE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<A6B8_Class> allDataA6b8(String iseg) {
        List<A6B8_Class> a6b8ArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABEL_ANBDE + " WHERE " + ID_SEGMEN + " LIKE '%" + iseg + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                A6B8_Class a6b8 = new A6B8_Class();
                a6b8.setID(cursor.getString(cursor.getColumnIndex(ID_POINT)));
                a6b8.setRPP(cursor.getDouble(cursor.getColumnIndex(JARI_DARI_MARKA_ANBDE)));
                a6b8.setRPP2(cursor.getDouble(cursor.getColumnIndex(TINGGI_DARI_MUKA_TANAH_ANBDE)));
                a6b8.setRPP3(cursor.getDouble(cursor.getColumnIndex(KEDALAMAN_REL_PENGAMAN_ANBDE)));
                a6b8.setRPP4(cursor.getDouble(cursor.getColumnIndex(JARAK_ANTAR_TIANG_VERTIKAL_ANBDE)));
                a6b8.setRPP5(cursor.getDouble(cursor.getColumnIndex(KONDISI_BAIK_ANBDE)));
                a6b8.setRPP6(cursor.getDouble(cursor.getColumnIndex(TAHAN_BENTURAN_ANBDE)));
                a6b8.setRPP7(cursor.getDouble(cursor.getColumnIndex(TERDAPAT_TANDA_REFLEKTOR_ANBDE)));
                a6b8.setPPJ(cursor.getDouble(cursor.getColumnIndex(POS_POLISI_ANBDE)));
                a6b8.setREC(cursor.getString(cursor.getColumnIndex(RECOMENDASI)));
                a6b8.setDIR1(cursor.getString(cursor.getColumnIndex(DIR_1)));
                a6b8.setDIR2(cursor.getString(cursor.getColumnIndex(DIR_2)));
                a6b8.setDIR3(cursor.getString(cursor.getColumnIndex(DIR_3)));
                a6b8.setDIR4(cursor.getString(cursor.getColumnIndex(DIR_4)));
                a6b8.setUPLOAD(cursor.getString(cursor.getColumnIndex(UPLOAD)));
                a6b8ArrayList.add(a6b8);
            } while (cursor.moveToNext());
        }

        db.close();
        return a6b8ArrayList;

    }

    public Boolean updateDataA6b8(String id, String iseg, double rpp, double rpp2, double rpp3, double rpp4, double rpp5,
                                  double rpp6, double rpp7, double ppj, String rec, String dir1, String dir2,
                                  String dir3, String dir4, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SEGMEN, iseg);
        contentValues.put(JARI_DARI_MARKA_ANBDE, rpp);
        contentValues.put(TINGGI_DARI_MUKA_TANAH_ANBDE, rpp2);
        contentValues.put(KEDALAMAN_REL_PENGAMAN_ANBDE, rpp3);
        contentValues.put(JARAK_ANTAR_TIANG_VERTIKAL_ANBDE, rpp4);
        contentValues.put(KONDISI_BAIK_ANBDE, rpp5);
        contentValues.put(TAHAN_BENTURAN_ANBDE, rpp6);
        contentValues.put(TERDAPAT_TANDA_REFLEKTOR_ANBDE, rpp7);
        contentValues.put(POS_POLISI_ANBDE, ppj);
        contentValues.put(RECOMENDASI, rec);
        contentValues.put(DIR_1, dir1);
        contentValues.put(DIR_2, dir2);
        contentValues.put(DIR_3, dir3);
        contentValues.put(DIR_4, dir4);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ANBDE, contentValues, ID_SEGMEN + " LIKE '%" + iseg + "%'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public String getA6b8Upload(String iseg){
        String upl = "-";
        String query = "SELECT " + UPLOAD + " FROM " + TABEL_ANBDE + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                upl = cursor.getString(cursor.getColumnIndex(UPLOAD));
            } while (cursor.moveToNext());
        }
        db.close();
        return upl;
    }

    public int getProfilesCount(String id) {
        String countQuery = "SELECT  * FROM " + TABEL_RUAS + " WHERE " + ID_PROV + " = '"+id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getProfilesCount2(String id) {
        String countQuery = "SELECT  * FROM " + TABEL_SEGMEN + " WHERE " + ID_RUAS + " = '"+id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //----------------------------------------------------------------------------------------------
    // A111

    public String getA111ID(String iseg){
        String id = "-";
        String query = "SELECT " + ID_POINT + " FROM " + TABEL_ASSS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                id = cursor.getString(cursor.getColumnIndex(ID_POINT));
            } while (cursor.moveToNext());
        }
        db.close();
        return id;
    }

    public double getA111ALL(String iseg){
        double all = -1;
        String query = "SELECT " + ARUS_DILAYANI + " FROM " + TABEL_ASSS + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                all = cursor.getDouble(cursor.getColumnIndex(ARUS_DILAYANI));
            } while (cursor.moveToNext());
        }
        db.close();
        return all;
    }

    public Boolean updateDataA111Iden(String id, String fjl, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FUNGSI_JALAN, fjl);
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASSS, contentValues, ID_POINT + " = '" + id + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    //----------------------------------------------------------------------------------------------
    // A112

    public String getA112ID(String iseg){
        String id = "-";
        String query = "SELECT " + ID_POINT + " FROM " + TABEL_ASSD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                id = cursor.getString(cursor.getColumnIndex(ID_POINT));
            } while (cursor.moveToNext());
        }
        db.close();
        return id;
    }

    public double getA112LBB(String iseg){
        double lbb = -1;
        String query = "SELECT " + LEBAR_BAHU + " FROM " + TABEL_ASSD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                lbb = cursor.getDouble(cursor.getColumnIndex(LEBAR_BAHU));
            } while (cursor.moveToNext());
        }
        db.close();
        return lbb;
    }

    public double getA112KMB(String iseg){
        double kmb = -1;
        String query = "SELECT " + KEMIRINGAN_BAHU + " FROM " + TABEL_ASSD + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                kmb = cursor.getDouble(cursor.getColumnIndex(KEMIRINGAN_BAHU));
            } while (cursor.moveToNext());
        }
        db.close();
        return kmb;
    }

    public Boolean updateDataA112Iden(String id, String upl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UPLOAD, upl);

        long result = db.update(TABEL_ASSD, contentValues, ID_POINT + " = '" + id + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    //----------------------------------------------------------------------------------------------
    // A113

    public double getA113JAB(String iseg){
        double jab = -1;
        String query = "SELECT " + JARAK_BUKAAN + " FROM " + TABEL_ASST + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                jab = cursor.getDouble(cursor.getColumnIndex(JARAK_BUKAAN));
            } while (cursor.moveToNext());
        }
        db.close();
        return jab;
    }

    public double getA113LBB(String iseg){
        double lbb = -1;
        String query = "SELECT " + LEBAR_BUKAAN + " FROM " + TABEL_ASST + " WHERE " + ID_SEGMEN + " = '" + iseg + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                lbb = cursor.getDouble(cursor.getColumnIndex(LEBAR_BUKAAN));
            } while (cursor.moveToNext());
        }
        db.close();
        return lbb;
    }

}
