package com.pratama.kelayakanjalan;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KoneksiDB3 {

    //Membuat variabel bertipe Connection
    private static Connection koneksi;

    public static Connection getKoneksi() {
        //method ini berfungsi untuk membuat koneksi ke MySQL
        if (koneksi == null) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                koneksi = DriverManager.getConnection(url, user, password);

            } catch (Exception e){
                Log.e("Error Koneksi ", e.toString());
            }

        }

        return koneksi;
    }

    public String[] getDataRuasSegmen(String idSegmen) {
        String[] data = new String[9];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT r.BALAI, r.NAMA, r.PANJANG, r.ID_RUAS, r.KM_AWAL, r.KM_AKHIR, s.SEGMEN, s.DARI_KOTA, s.PANJANG, s.KM_AWAL, s.KM_AKHIR, r.KECEPATAN "
                    + "from ruas r, segmen s WHERE r.ID_RUAS = s.ID_RUAS AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject(1).toString();   //balai
                data[1] = result.getObject(2).toString();   //nama
                data[2] = result.getObject(3).toString() + " KM";   //panjang
                data[3] = result.getObject(4).toString().substring(3);   //nomor ruas (bukan id_ruas)
                data[4] = "KM " + result.getObject(5).toString() + " - KM " + result.getObject(6).toString();
                data[5] = result.getObject(7).toString();
                data[6] = result.getObject(8).toString();
                data[7] = result.getObject(9).toString() + " KM: (KM " + result.getObject(10).toString() + " - KM " + result.getObject(11).toString() + ")";
                data[8] = result.getObject(12).toString() + " KM/ JAM";

                for (int i = 0; i < 9; i++) {
                    System.out.println(data[i]);
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data ruas segmen");
            e.printStackTrace();
        }

        return data;
    }

    public double getKecepatanOperasional(String idSegmen) {
        double data = 0;

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT r.KECEPATAN from ruas r, segmen s WHERE r.ID_RUAS = s.ID_RUAS AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data = Double.parseDouble(result.getObject(1).toString());
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading kecepatan operasional");
            e.printStackTrace();
        }

        return data;
    }

    public String[] getKlasifikasiJalan(String idSegmen) {

        String[] data = new String[6];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "select k.* from klasifikasi_jalan k, ruas r, segmen s "
                    + "WHERE s.ID_RUAS = r.ID_RUAS AND r.ID_KLASIFIKASI = k.ID_KLASIFIKASI "
                    + "AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("SISTEM_JARINGAN").toString();
                data[1] = result.getObject("STATUS").toString();
                data[2] = result.getObject("FUNGSI").toString();
                data[3] = result.getObject("KELAS_PRASARANA").toString();
                data[4] = result.getObject("KELAS_PENGGUNAAN").toString();
                data[5] = result.getObject("MEDAN").toString();

                System.out.println(data[0] + " " + data[1]);
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data klasifikasi jalan");
            e.printStackTrace();
        }

        return data;
    }

    public Object[] getDataA111(String idSegmen) {
        Object[] data = new Object[21];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A111 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A111_a");
                data[1] = result.getObject("UJI_A111_b");
                data[2] = result.getObject("DEV_A111_b");
                data[3] = result.getObject("UJI_A111_c");
                data[4] = result.getObject("DEV_A111_c");
                data[5] = result.getObject("UJI_A111_d");
                data[6] = result.getObject("DEV_A111_d");
                data[7] = result.getObject("UJI_A111_e");
                data[8] = result.getObject("DEV_A111_e");
                data[9] = result.getObject("UJI_A111_f");
                data[10] = result.getObject("DEV_A111_f");
                data[11] = result.getObject("GBR_1");
                data[12] = result.getObject("GBR_2");
                data[13] = result.getObject("GBR_3");
                data[14] = result.getObject("CATATAN");
                data[15] = result.getObject("KTG_A111_b");
                data[16] = result.getObject("KTG_A111_c");
                data[17] = result.getObject("KTG_A111_d");
                data[18] = result.getObject("KTG_A111_e");
                data[19] = result.getObject("KTG_A111_f");
                data[20] = result.getObject("KTG_A111");

                System.out.println(data[20]);
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 11; i <= 13; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data A111");
            e.printStackTrace();
        }

        return data;
    }

    public Object[] getDataA112(String idSegmen) {
        Object[] data = new Object[19];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A112 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A112_a");
                data[1] = result.getObject("DEV_A112_a");
                data[2] = result.getObject("UJI_A112_b");
                data[3] = result.getObject("DEV_A112_b");
                data[4] = result.getObject("UJI_A112_c");
                data[5] = result.getObject("DEV_A112_c");
                data[6] = result.getObject("UJI_A112_d");
                data[7] = result.getObject("DEV_A112_d");
                data[8] = result.getObject("UJI_A112_e");
                data[9] = result.getObject("DEV_A112_e");
                data[10] = result.getObject("GBR_1");
                data[11] = result.getObject("GBR_2");
                data[12] = result.getObject("CATATAN");
                data[13] = result.getObject("KTG_A112_a");
                data[14] = result.getObject("KTG_A112_b");
                data[15] = result.getObject("KTG_A112_c");
                data[16] = result.getObject("KTG_A112_d");
                data[17] = result.getObject("KTG_A112_e");
                data[18] = result.getObject("KTG_A112");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 10; i <= 11; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data A112");
            e.printStackTrace();
        }

        return data;
    }

    public Object[] getDataA113(String idSegmen) {
        Object[] data = new Object[22];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A113 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("MEDIAN");
                data[1] = result.getObject("UJI_A113_a");
                data[2] = result.getObject("DEV_A113_a");
                data[3] = result.getObject("TIPE_MEDIAN");
                data[4] = result.getObject("UJI_A113_b");
                data[5] = result.getObject("DEV_A113_b");
                data[6] = result.getObject("PERKERASAN_MEDIAN");
                data[7] = result.getObject("UJI_A113_c");
                data[8] = result.getObject("DEV_A113_c");
                data[9] = result.getObject("UJI_A113_d_1");
                data[10] = result.getObject("DEV_A113_d_1");
                data[11] = result.getObject("UJI_A113_d_2");
                data[12] = result.getObject("DEV_A113_d_2");
                data[13] = result.getObject("GBR_1");
                data[14] = result.getObject("GBR_2");
                data[15] = result.getObject("GBR_3");
                data[16] = result.getObject("CATATAN");
                data[17] = result.getObject("KTG_A113_a");
                data[18] = result.getObject("KTG_A113_b");
                data[19] = result.getObject("KTG_A113_c");
                data[20] = result.getObject("KTG_A113_d");
                data[21] = result.getObject("KTG_A113");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 13; i <= 15; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA114(String idSegmen) {
        Object[] data = new Object[14];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A114 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A114_a");
                data[1] = result.getObject("DEV_A114_a");
                data[2] = result.getObject("UJI_A114_b1");
                data[3] = result.getObject("UJI_A114_b2");
                data[4] = result.getObject("DEV_A114_b2");
                data[5] = result.getObject("UJI_A114_c");
                data[6] = result.getObject("DEV_A114_c");
                data[7] = result.getObject("GBR_1");
                data[8] = result.getObject("GBR_2");
                data[9] = result.getObject("CATATAN");
                data[10] = result.getObject("KTG_A114_a");
                data[11] = result.getObject("KTG_A114_b");
                data[12] = result.getObject("KTG_A114_c");
                data[13] = result.getObject("KTG_A114");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 7; i <= 8; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA115(String idSegmen) {
        Object[] data = new Object[12];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A115 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A115_a1");
                data[1] = result.getObject("DEV_A115_a1");
                data[2] = result.getObject("UJI_A115_a2");
                data[3] = result.getObject("DEV_A115_a2");
                data[4] = result.getObject("UJI_A115_b");
                data[5] = result.getObject("DEV_A115_b");
                data[6] = result.getObject("GBR_1");
                data[7] = result.getObject("GBR_2");
                data[8] = result.getObject("CATATAN");
                data[9] = result.getObject("KTG_A115_a");
                data[10] = result.getObject("KTG_A115_b");
                data[11] = result.getObject("KTG_A115");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 6; i <= 7; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA116(String idSegmen) {
        Object[] data = new Object[25];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A116 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A116_a1");
                data[1] = result.getObject("DEV_A116_a1");
                data[2] = result.getObject("UJI_A116_a2");
                data[3] = result.getObject("DEV_A116_a2");
                data[4] = result.getObject("UJI_A116_a3");
                data[5] = result.getObject("DEV_A116_a3");
                data[6] = result.getObject("UJI_A116_a4");
                data[7] = result.getObject("DEV_A116_a4");
                data[8] = result.getObject("UJI_A116_a5");
                data[9] = result.getObject("DEV_A116_a5");
                data[10] = result.getObject("UJI_A116_b1");
                data[11] = result.getObject("DEV_A116_b1");
                data[12] = result.getObject("UJI_A116_b2");
                data[13] = result.getObject("DEV_A116_b2");
                data[14] = result.getObject("UJI_A116_b3");
                data[15] = result.getObject("DEV_A116_b3");
                data[16] = result.getObject("UJI_A116_b4");
                data[17] = result.getObject("DEV_A116_b4");
                data[18] = result.getObject("GBR_1");
                data[19] = result.getObject("GBR_2");
                data[20] = result.getObject("GBR_3");
                data[21] = result.getObject("CATATAN");
                data[22] = result.getObject("KTG_A116_a");
                data[23] = result.getObject("KTG_A116_b");
                data[24] = result.getObject("KTG_A116");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 18; i <= 20; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA121(String idSegmen) {
        Object[] data = new Object[17];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A121 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A121_a");
                data[1] = result.getObject("DEV_A121_a");
                data[2] = result.getObject("JARAK_PANDANG");
                data[3] = result.getObject("UJI_A121_b1");
                data[4] = result.getObject("DEV_A121_b1");
                data[5] = result.getObject("UJI_A121_b2");
                data[6] = result.getObject("DEV_A121_b2");
                data[7] = result.getObject("UJI_A121_c1");
                data[8] = result.getObject("UJI_A121_c2");
                data[9] = result.getObject("DEV_A121_c2");
                data[10] = result.getObject("GBR_1");
                data[11] = result.getObject("GBR_2");
                data[12] = result.getObject("CATATAN");
                data[13] = result.getObject("KTG_A121_a");
                data[14] = result.getObject("KTG_A121_b");
                data[15] = result.getObject("KTG_A121_c");
                data[16] = result.getObject("KTG_A121");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 10; i <= 11; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA122(String idSegmen) {
        Object[] data = new Object[13];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A122 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A122_a");
                data[1] = result.getObject("DEV_A122_a");
                data[2] = result.getObject("UJI_A122_b");
                data[3] = result.getObject("DEV_A122_b");
                data[4] = result.getObject("UJI_A122_c");
                data[5] = result.getObject("DEV_A122_c");
                data[6] = result.getObject("GBR_1");
                data[7] = result.getObject("GBR_2");
                data[8] = result.getObject("CATATAN");
                data[9] = result.getObject("KTG_A122_a");
                data[10] = result.getObject("KTG_A122_b");
                data[11] = result.getObject("KTG_A122_c");
                data[12] = result.getObject("KTG_A122");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 6; i <= 7; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA123(String idSegmen) {
        Object[] data = new Object[11];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A123 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A123_a");
                data[1] = result.getObject("DEV_A123_a");
                data[2] = result.getObject("UJI_A123_b");
                data[3] = result.getObject("DEV_A123_b");
                data[4] = result.getObject("GBR_1");
                data[5] = result.getObject("GBR_2");
                data[6] = result.getObject("GBR_3");
                data[7] = result.getObject("CATATAN");
                data[8] = result.getObject("KTG_A123_a");
                data[9] = result.getObject("KTG_A123_b");
                data[10] = result.getObject("KTG_A123");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 4; i <= 6; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA124(String idSegmen) {
        Object[] data = new Object[15];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A124 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A124_a");
                data[1] = result.getObject("DEV_A124_a");
                data[2] = result.getObject("UJI_A124_b");
                data[3] = result.getObject("DEV_A124_b");
                data[4] = result.getObject("UJI_A124_c");
                data[5] = result.getObject("DEV_A124_c");
                data[6] = result.getObject("GBR_1");
                data[7] = result.getObject("GBR_2");
                data[8] = result.getObject("GBR_3");
                data[9] = result.getObject("GBR_4");
                data[10] = result.getObject("CATATAN");
                data[11] = result.getObject("KTG_A124_a");
                data[12] = result.getObject("KTG_A124_b");
                data[13] = result.getObject("KTG_A124_c");
                data[14] = result.getObject("KTG_A124");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 6; i <= 9; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA131(String idSegmen) {
        Object[] data = new Object[23];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A131 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A131_a1");
                data[1] = result.getObject("DEV_A131_a1");
                data[2] = result.getObject("UJI_A131_a2");
                data[3] = result.getObject("DEV_A131_a2");
                data[4] = result.getObject("JARAK_PANDANG");
                data[5] = result.getObject("UJI_A131_b1");
                data[6] = result.getObject("DEV_A131_b1");
                data[7] = result.getObject("UJI_A131_b2");
                data[8] = result.getObject("DEV_A131_b2");
                data[9] = result.getObject("UJI_A131_b3");
                data[10] = result.getObject("DEV_A131_b3");
                data[11] = result.getObject("UJI_A131_c1");
                data[12] = result.getObject("UJI_A131_c2");
                data[13] = result.getObject("DEV_A131_c2");
                data[14] = result.getObject("GBR_1");
                data[15] = result.getObject("GBR_2");
                data[16] = result.getObject("GBR_3");
                data[17] = result.getObject("GBR_4");
                data[18] = result.getObject("CATATAN");
                data[19] = result.getObject("KTG_A131_a");
                data[20] = result.getObject("KTG_A131_b");
                data[21] = result.getObject("KTG_A131_c");
                data[22] = result.getObject("KTG_A131");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 14; i <= 17; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA132(String idSegmen) {
        Object[] data = new Object[23];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A132 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A132_a");
                data[1] = result.getObject("DEV_A132_a");
                data[2] = result.getObject("UJI_A132_b1");
                data[3] = result.getObject("DEV_A132_b1");
                data[4] = result.getObject("UJI_A132_b2_1");
                data[5] = result.getObject("DEV_A132_b2_1");
                data[6] = result.getObject("UJI_A132_b2_2");
                data[7] = result.getObject("DEV_A132_b2_2");
                data[8] = result.getObject("UJI_A132_b2_3");
                data[9] = result.getObject("DEV_A132_b2_3");
                data[10] = result.getObject("UJI_A132_b2_4");
                data[11] = result.getObject("DEV_A132_b2_4");
                data[12] = result.getObject("UJI_A132_b2_5");
                data[13] = result.getObject("DEV_A132_b2_5");
                data[14] = result.getObject("UJI_A132_c");
                data[15] = result.getObject("DEV_A132_c");
                data[16] = result.getObject("GBR_1");
                data[17] = result.getObject("GBR_2");
                data[18] = result.getObject("CATATAN");
                data[19] = result.getObject("KTG_A132_a");
                data[20] = result.getObject("KTG_A132_b");
                data[21] = result.getObject("KTG_A132_c");
                data[22] = result.getObject("KTG_A132");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 16; i <= 17; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA133(String idSegmen) {
        Object[] data = new Object[40];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A133 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A133_a1");
                data[1] = result.getObject("DEV_A133_a1");
                data[2] = result.getObject("UJI_A133_a2");
                data[3] = result.getObject("DEV_A133_a2");
                data[4] = result.getObject("UJI_A133_a3");
                data[5] = result.getObject("DEV_A133_a3");
                data[6] = result.getObject("UJI_A133_a4");
                data[7] = result.getObject("DEV_A133_a4");
                data[8] = result.getObject("JARAK_PANDANG");
                data[9] = result.getObject("UJI_A133_b1");
                data[10] = result.getObject("DEV_A133_b1");
                data[11] = result.getObject("UJI_A133_b2");
                data[12] = result.getObject("DEV_A133_b2");
                data[13] = result.getObject("UJI_A133_b3");
                data[14] = result.getObject("DEV_A133_b3");
                data[15] = result.getObject("UJI_A133_c1");
                data[16] = result.getObject("DEV_A133_c1");
                data[17] = result.getObject("UJI_A133_c2");
                data[18] = result.getObject("DEV_A133_c2");
                data[19] = result.getObject("UJI_A133_d1");
                data[20] = result.getObject("DEV_A133_d1");
                data[21] = result.getObject("UJI_A133_d2");
                data[22] = result.getObject("DEV_A133_d2");
                data[23] = result.getObject("UJI_A133_d3");
                data[24] = result.getObject("DEV_A133_d3");
                data[25] = result.getObject("UJI_A133_d4");
                data[26] = result.getObject("DEV_A133_d4");
                data[27] = result.getObject("UJI_A133_d5");
                data[28] = result.getObject("DEV_A133_d5");
                data[29] = result.getObject("GBR_1");
                data[30] = result.getObject("GBR_2");
                data[31] = result.getObject("GBR_3");
                data[32] = result.getObject("GBR_4");
                data[33] = result.getObject("GBR_5");
                data[34] = result.getObject("CATATAN");
                data[35] = result.getObject("KTG_A133_a");
                data[36] = result.getObject("KTG_A133_b");
                data[37] = result.getObject("KTG_A133_c");
                data[38] = result.getObject("KTG_A133_d");
                data[39] = result.getObject("KTG_A133");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 29; i <= 33; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA141(String idSegmen) {
        Object[] data = new Object[11];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A141 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A141_a");
                data[1] = result.getObject("DEV_A141_a");
                data[2] = result.getObject("UJI_A141_b");
                data[3] = result.getObject("DEV_A141_b");
                data[4] = result.getObject("GBR_1");
                data[5] = result.getObject("GBR_2");
                data[6] = result.getObject("GBR_3");
                data[7] = result.getObject("CATATAN");
                data[8] = result.getObject("KTG_A141_a");
                data[9] = result.getObject("KTG_A141_b");
                data[10] = result.getObject("KTG_A141");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 4; i <= 6; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA21(String idSegmen) {
        Object[] data = new Object[6];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A21 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A21");
                data[1] = result.getObject("DEV_A21");
                data[2] = result.getObject("GBR_1");
                data[3] = result.getObject("GBR_2");
                data[4] = result.getObject("CATATAN");
                data[5] = result.getObject("KTG_A21");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 2; i <= 3; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA22(String idSegmen) {
        Object[] data = new Object[35];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A22 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A22_a");
                data[1] = result.getObject("DEV_A22_a");
                data[2] = result.getObject("UJI_A22_b1");
                data[3] = result.getObject("DEV_A22_b1");
                data[4] = result.getObject("UJI_A22_b2");
                data[5] = result.getObject("DEV_A22_b2");
                data[6] = result.getObject("UJI_A22_c");
                data[7] = result.getObject("DEV_A22_c");
                data[8] = result.getObject("UJI_A22_d");
                data[9] = result.getObject("DEV_A22_d");
                data[10] = result.getObject("UJI_A22_e");
                data[11] = result.getObject("DEV_A22_e");
                data[12] = result.getObject("UJI_A22_f1");
                data[13] = result.getObject("DEV_A22_f1");
                data[14] = result.getObject("UJI_A22_f2");
                data[15] = result.getObject("DEV_A22_f2");
                data[16] = result.getObject("UJI_A22_g");
                data[17] = result.getObject("DEV_A22_g");
                data[18] = result.getObject("UJI_A22_h");
                data[19] = result.getObject("DEV_A22_h");
                data[20] = result.getObject("UJI_A22_i");
                data[21] = result.getObject("DEV_A22_i");
                data[22] = result.getObject("GBR_1");
                data[23] = result.getObject("GBR_2");
                data[24] = result.getObject("CATATAN");
                data[25] = result.getObject("KTG_A22_a");
                data[26] = result.getObject("KTG_A22_b");
                data[27] = result.getObject("KTG_A22_c");
                data[28] = result.getObject("KTG_A22_d");
                data[29] = result.getObject("KTG_A22_e");
                data[30] = result.getObject("KTG_A22_f");
                data[31] = result.getObject("KTG_A22_g");
                data[32] = result.getObject("KTG_A22_h");
                data[33] = result.getObject("KTG_A22_i");
                data[34] = result.getObject("KTG_A22");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 22; i <= 23; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA23(String idSegmen) {
        Object[] data = new Object[17];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A23 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A23_a");
                data[1] = result.getObject("DEV_A23_a");
                data[2] = result.getObject("UJI_A23_b");
                data[3] = result.getObject("DEV_A23_b");
                data[4] = result.getObject("UJI_A23_c");
                data[5] = result.getObject("DEV_A23_c");
                data[6] = result.getObject("UJI_A23_d1");
                data[7] = result.getObject("UJI_A23_d2");
                data[8] = result.getObject("DEV_A23_d2");
                data[9] = result.getObject("GBR_1");
                data[10] = result.getObject("GBR_2");
                data[11] = result.getObject("CATATAN");
                data[12] = result.getObject("KTG_A23_a");
                data[13] = result.getObject("KTG_A23_b");
                data[14] = result.getObject("KTG_A23_c");
                data[15] = result.getObject("KTG_A23_d");
                data[16] = result.getObject("KTG_A23");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 9; i <= 10; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA31(String idSegmen) {
        Object[] data = new Object[64];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A31 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A31_a");
                data[1] = result.getObject("DEV_A31_a");
                data[2] = result.getObject("UJI_A31_b");
                data[3] = result.getObject("DEV_A31_b");
                data[4] = result.getObject("UJI_A31_c1");
                data[5] = result.getObject("DEV_A31_c1");
                data[6] = result.getObject("UJI_A31_c2");
                data[7] = result.getObject("DEV_A31_c2");
                data[8] = result.getObject("UJI_A31_c3");
                data[9] = result.getObject("DEV_A31_c3");
                data[10] = result.getObject("UJI_A31_c4");
                data[11] = result.getObject("DEV_A31_c4");
                data[12] = result.getObject("UJI_A31_c5");
                data[13] = result.getObject("DEV_A31_c5");
                data[14] = result.getObject("UJI_A31_c6");
                data[15] = result.getObject("DEV_A31_c6");
                data[16] = result.getObject("UJI_A31_d1");
                data[17] = result.getObject("DEV_A31_d1");
                data[18] = result.getObject("UJI_A31_d2");
                data[19] = result.getObject("DEV_A31_d2");
                data[20] = result.getObject("UJI_A31_d3");
                data[21] = result.getObject("DEV_A31_d3");
                data[22] = result.getObject("UJI_A31_d4");
                data[23] = result.getObject("DEV_A31_d4");
                data[24] = result.getObject("UJI_A31_d5");
                data[25] = result.getObject("DEV_A31_d5");
                data[26] = result.getObject("UJI_A31_d6");
                data[27] = result.getObject("DEV_A31_d6");
                data[28] = result.getObject("UJI_A31_d7");
                data[29] = result.getObject("DEV_A31_d7");
                data[30] = result.getObject("UJI_A31_d8");
                data[31] = result.getObject("DEV_A31_d8");
                data[32] = result.getObject("UJI_A31_e1");
                data[33] = result.getObject("DEV_A31_e1");
                data[34] = result.getObject("UJI_A31_e2");
                data[35] = result.getObject("DEV_A31_e2");
                data[36] = result.getObject("UJI_A31_e3");
                data[37] = result.getObject("DEV_A31_e3");
                data[38] = result.getObject("UJI_A31_e4");
                data[39] = result.getObject("DEV_A31_e4");
                data[40] = result.getObject("UJI_A31_e5");
                data[41] = result.getObject("DEV_A31_e5");
                data[42] = result.getObject("UJI_A31_e6");
                data[43] = result.getObject("DEV_A31_e6");
                data[44] = result.getObject("UJI_A31_e7");
                data[45] = result.getObject("DEV_A31_e7");
                data[46] = result.getObject("UJI_A31_e8");
                data[47] = result.getObject("DEV_A31_e8");
                data[48] = result.getObject("UJI_A31_e9");
                data[49] = result.getObject("DEV_A31_e9");
                data[50] = result.getObject("GBR_1");
                data[51] = result.getObject("GBR_2");
                data[52] = result.getObject("GBR_3");
                data[53] = result.getObject("GBR_4");
                data[54] = result.getObject("GBR_5");
                data[55] = result.getObject("GBR_6");
                data[56] = result.getObject("CATATAN");
                data[57] = result.getObject("KTG_A31_a");
                data[58] = result.getObject("KTG_A31_b");
                data[59] = result.getObject("KTG_A31_c");
                data[60] = result.getObject("KTG_A31_d");
                data[61] = result.getObject("KTG_A31_e");
                data[62] = result.getObject("KTG_A31");
                data[63] = result.getObject("KERUSAKAN_JEMBATAN");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 50; i <= 55; i++) {
                //System.out.println("data dalam DB " + data[i]);

                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA32(String idSegmen) {
        Object[] data = new Object[12];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A32 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A32_a");
                data[1] = result.getObject("DEV_A32_a");
                data[2] = result.getObject("UJI_A32_b");
                data[3] = result.getObject("DEV_A32_b");
                data[4] = result.getObject("UJI_A32_c");
                data[5] = result.getObject("DEV_A32_c");
                data[6] = result.getObject("GBR_1");
                data[7] = result.getObject("CATATAN");
                data[8] = result.getObject("KTG_A32_a");
                data[9] = result.getObject("KTG_A32_b");
                data[10] = result.getObject("KTG_A32_c");
                data[11] = result.getObject("KTG_A32");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 6; i <= 6; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA33(String idSegmen) {
        Object[] data = new Object[15];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A33 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A33_a1");
                data[1] = result.getObject("DEV_A33_a1");
                data[2] = result.getObject("UJI_A33_a2");
                data[3] = result.getObject("DEV_A33_a2");
                data[4] = result.getObject("UJI_A33_b");
                data[5] = result.getObject("DEV_A33_b");
                data[6] = result.getObject("UJI_A33_c");
                data[7] = result.getObject("DEV_A33_c");
                data[8] = result.getObject("GBR_1");
                data[9] = result.getObject("GBR_2");
                data[10] = result.getObject("CATATAN");
                data[11] = result.getObject("KTG_A33_a");
                data[12] = result.getObject("KTG_A33_b");
                data[13] = result.getObject("KTG_A33_c");
                data[14] = result.getObject("KTG_A33");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 8; i <= 9; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA34(String idSegmen) {
        Object[] data = new Object[13];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A34 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A34_a");
                data[1] = result.getObject("DEV_A34_a");
                data[2] = result.getObject("UJI_A34_b");
                data[3] = result.getObject("DEV_A34_b");
                data[4] = result.getObject("UJI_A34_c");
                data[5] = result.getObject("DEV_A34_c");
                data[6] = result.getObject("GBR_1");
                data[7] = result.getObject("GBR_2");
                data[8] = result.getObject("CATATAN");
                data[9] = result.getObject("KTG_A34_a");
                data[10] = result.getObject("KTG_A34_b");
                data[11] = result.getObject("KTG_A34_c");
                data[12] = result.getObject("KTG_A34");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 6; i <= 7; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA35(String idSegmen) {
        Object[] data = new Object[13];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A35 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A35_a");
                data[1] = result.getObject("DEV_A35_a");
                data[2] = result.getObject("UJI_A35_b");
                data[3] = result.getObject("DEV_A35_b");
                data[4] = result.getObject("UJI_A35_c");
                data[5] = result.getObject("DEV_A35_c");
                data[6] = result.getObject("GBR_1");
                data[7] = result.getObject("GBR_2");
                data[8] = result.getObject("CATATAN");
                data[9] = result.getObject("KTG_A35_a");
                data[10] = result.getObject("KTG_A35_b");
                data[11] = result.getObject("KTG_A35_c");
                data[12] = result.getObject("KTG_A35");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 6; i <= 7; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA36(String idSegmen) {
        Object[] data = new Object[22];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A36 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A36_a1");
                data[1] = result.getObject("DEV_A36_a1");
                data[2] = result.getObject("UJI_A36_a2");
                data[3] = result.getObject("UJI_A36_b1");
                data[4] = result.getObject("DEV_A36_b1");
                data[5] = result.getObject("UJI_A36_b2");
                data[6] = result.getObject("DEV_A36_b2");
                data[7] = result.getObject("UJI_A36_b3");
                data[8] = result.getObject("DEV_A36_b3");
                data[9] = result.getObject("UJI_A36_c1");
                data[10] = result.getObject("UJI_A36_c2");
                data[11] = result.getObject("DEV_A36_c2");
                data[12] = result.getObject("UJI_A36_d");
                data[13] = result.getObject("DEV_A36_d");
                data[14] = result.getObject("GBR_1");
                data[15] = result.getObject("GBR_2");
                data[16] = result.getObject("CATATAN");
                data[17] = result.getObject("KTG_A36_a");
                data[18] = result.getObject("KTG_A36_b");
                data[19] = result.getObject("KTG_A36_c");
                data[20] = result.getObject("KTG_A36_d");
                data[21] = result.getObject("KTG_A36");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 14; i <= 15; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA41(String idSegmen) {
        Object[] data = new Object[19];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A41 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("LEBAR_JALUR");
                data[1] = result.getObject("UJI_A41_a1");
                data[2] = result.getObject("DEV_A41_a1");
                data[3] = result.getObject("UJI_A41_a2");
                data[4] = result.getObject("DEV_A41_a2");
                data[5] = result.getObject("UJI_A41_a3");
                data[6] = result.getObject("DEV_A41_a3");
                data[7] = result.getObject("UJI_A41_b");
                data[8] = result.getObject("DEV_A41_b");
                data[9] = result.getObject("UJI_A41_c");
                data[10] = result.getObject("DEV_A41_c");
                data[11] = result.getObject("GBR_1");
                data[12] = result.getObject("GBR_2");
                data[13] = result.getObject("GBR_3");
                data[14] = result.getObject("CATATAN");
                data[15] = result.getObject("KTG_A41_a");
                data[16] = result.getObject("KTG_A41_b");
                data[17] = result.getObject("KTG_A41_c");
                data[18] = result.getObject("KTG_A41");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 11; i <= 13; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA42(String idSegmen) {
        Object[] data = new Object[18];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A42 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A42_a");
                data[1] = result.getObject("DEV_A42_a");
                data[2] = result.getObject("UJI_A42_b");
                data[3] = result.getObject("DEV_A42_b");
                data[4] = result.getObject("KEBERADAAN");
                data[5] = result.getObject("UJI_A42_c1");
                data[6] = result.getObject("DEV_A42_c1");
                data[7] = result.getObject("UJI_A42_c2");
                data[8] = result.getObject("DEV_A42_c2");
                data[9] = result.getObject("UJI_A42_c3");
                data[10] = result.getObject("DEV_A42_c3");
                data[11] = result.getObject("GBR_1");
                data[12] = result.getObject("GBR_2");
                data[13] = result.getObject("CATATAN");
                data[14] = result.getObject("KTG_A42_a");
                data[15] = result.getObject("KTG_A42_b");
                data[16] = result.getObject("KTG_A42_c");
                data[17] = result.getObject("KTG_A42");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 11; i <= 12; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA43(String idSegmen) {
        Object[] data = new Object[17];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A43 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A43_a");
                data[1] = result.getObject("DEV_A43_a");
                data[2] = result.getObject("UJI_A43_b1");
                data[3] = result.getObject("DEV_A43_b1");
                data[4] = result.getObject("UJI_A43_b2");
                data[5] = result.getObject("DEV_A43_b2");
                data[6] = result.getObject("UJI_A43_b3");
                data[7] = result.getObject("DEV_A43_b3");
                data[8] = result.getObject("UJI_A43_c");
                data[9] = result.getObject("DEV_A43_c");
                data[10] = result.getObject("GBR_1");
                data[11] = result.getObject("GBR_2");
                data[12] = result.getObject("CATATAN");
                data[13] = result.getObject("KTG_A43_a");
                data[14] = result.getObject("KTG_A43_b");
                data[15] = result.getObject("KTG_A43_c");
                data[16] = result.getObject("KTG_A43");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 10; i <= 11; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA51(String idSegmen) {
        Object[] data = new Object[48];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A51 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A51_a1");
                data[1] = result.getObject("DEV_A51_a1");
                data[2] = result.getObject("UJI_A51_a2");
                data[3] = result.getObject("DEV_A51_a2");
                data[4] = result.getObject("UJI_A51_a3");
                data[5] = result.getObject("DEV_A51_a3");
                data[6] = result.getObject("UJI_A51_a4");
                data[7] = result.getObject("DEV_A51_a4");
                data[8] = result.getObject("UJI_A51_b1");
                data[9] = result.getObject("DEV_A51_b1");
                data[10] = result.getObject("UJI_A51_b2");
                data[11] = result.getObject("DEV_A51_b2");
                data[12] = result.getObject("UJI_A51_b3");
                data[13] = result.getObject("DEV_A51_b3");
                data[14] = result.getObject("UJI_A51_b4");
                data[15] = result.getObject("DEV_A51_b4");
                data[16] = result.getObject("UJI_A51_b5");
                data[17] = result.getObject("DEV_A51_b5");
                data[18] = result.getObject("UJI_A51_c1");
                data[19] = result.getObject("DEV_A51_c1");
                data[20] = result.getObject("UJI_A51_c2");
                data[21] = result.getObject("DEV_A51_c2");
                data[22] = result.getObject("UJI_A51_c3");
                data[23] = result.getObject("DEV_A51_c3");
                data[24] = result.getObject("UJI_A51_c4");
                data[25] = result.getObject("DEV_A51_c4");
                data[26] = result.getObject("UJI_A51_c5");
                data[27] = result.getObject("DEV_A51_c5");
                data[28] = result.getObject("UJI_A51_c6");
                data[29] = result.getObject("DEV_A51_c6");
                data[30] = result.getObject("UJI_A51_c7");
                data[31] = result.getObject("DEV_A51_c7");
                data[32] = result.getObject("UJI_A51_d1");
                data[33] = result.getObject("DEV_A51_d1");
                data[34] = result.getObject("UJI_A51_d2");
                data[35] = result.getObject("DEV_A51_d2");
                data[36] = result.getObject("UJI_A51_d3");
                data[37] = result.getObject("DEV_A51_d3");
                data[38] = result.getObject("GBR_1");
                data[39] = result.getObject("GBR_2");
                data[40] = result.getObject("GBR_3");
                data[41] = result.getObject("GBR_4");
                data[42] = result.getObject("CATATAN");
                data[43] = result.getObject("KTG_A51_a");
                data[44] = result.getObject("KTG_A51_b");
                data[45] = result.getObject("KTG_A51_c");
                data[46] = result.getObject("KTG_A51_d");
                data[47] = result.getObject("KTG_A51");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 38; i <= 41; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA52(String idSegmen) {
        Object[] data = new Object[30];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A52 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A52_a1");
                data[1] = result.getObject("DEV_A52_a1");
                data[2] = result.getObject("UJI_A52_a2");
                data[3] = result.getObject("DEV_A52_a2");
                data[4] = result.getObject("UJI_A52_a3");
                data[5] = result.getObject("DEV_A52_a3");
                data[6] = result.getObject("UJI_A52_a4");
                data[7] = result.getObject("DEV_A52_a4");
                data[8] = result.getObject("UJI_A52_a5");
                data[9] = result.getObject("DEV_A52_a5");
                data[10] = result.getObject("UJI_A52_a6");
                data[11] = result.getObject("DEV_A52_a6");
                data[12] = result.getObject("UJI_A52_b1");
                data[13] = result.getObject("DEV_A52_b1");
                data[14] = result.getObject("UJI_A52_b2");
                data[15] = result.getObject("DEV_A52_b2");
                data[16] = result.getObject("UJI_A52_b3");
                data[17] = result.getObject("DEV_A52_b3");
                data[18] = result.getObject("UJI_A52_b4");
                data[19] = result.getObject("DEV_A52_b4");
                data[20] = result.getObject("UJI_A52_b5");
                data[21] = result.getObject("DEV_A52_b5");
                data[22] = result.getObject("UJI_A52_b6");
                data[23] = result.getObject("DEV_A52_b6");
                data[24] = result.getObject("GBR_1");
                data[25] = result.getObject("GBR_2");
                data[26] = result.getObject("CATATAN");
                data[27] = result.getObject("KTG_A52_a");
                data[28] = result.getObject("KTG_A52_b");
                data[29] = result.getObject("KTG_A52");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 24; i <= 25; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA53(String idSegmen) {
        Object[] data = new Object[14];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A53 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A53_a1");
                data[1] = result.getObject("DEV_A53_a1");
                data[2] = result.getObject("UJI_A53_a2");
                data[3] = result.getObject("DEV_A53_a2");
                data[4] = result.getObject("UJI_A53_a3");
                data[5] = result.getObject("DEV_A53_a3");
                data[6] = result.getObject("UJI_A53_b");
                data[7] = result.getObject("DEV_A53_b");
                data[8] = result.getObject("GBR_1");
                data[9] = result.getObject("GBR_2");
                data[10] = result.getObject("CATATAN");
                data[11] = result.getObject("KTG_A53_a");
                data[12] = result.getObject("KTG_A53_b");
                data[13] = result.getObject("KTG_A53");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 8; i <= 9; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA54(String idSegmen) {
        Object[] data = new Object[27];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A54 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A54_a1");
                data[1] = result.getObject("DEV_A54_a1");
                data[2] = result.getObject("UJI_A54_a2");
                data[3] = result.getObject("DEV_A54_a2");
                data[4] = result.getObject("UJI_A54_a3");
                data[5] = result.getObject("DEV_A54_a3");
                data[6] = result.getObject("UJI_A54_b");
                data[7] = result.getObject("UJI_A54_c1");
                data[8] = result.getObject("DEV_A54_c1");
                data[9] = result.getObject("UJI_A54_c2");
                data[10] = result.getObject("DEV_A54_c2");
                data[11] = result.getObject("UJI_A54_c3");
                data[12] = result.getObject("DEV_A54_c3");
                data[13] = result.getObject("UJI_A54_c4");
                data[14] = result.getObject("DEV_A54_c4");
                data[15] = result.getObject("UJI_A54_d");
                data[16] = result.getObject("DEV_A54_d");
                data[17] = result.getObject("UJI_A54_e");
                data[18] = result.getObject("DEV_A54_e");
                data[19] = result.getObject("GBR_1");
                data[20] = result.getObject("GBR_2");
                data[21] = result.getObject("CATATAN");
                data[22] = result.getObject("KTG_A54_a");
                data[23] = result.getObject("KTG_A54_c");
                data[24] = result.getObject("KTG_A54_d");
                data[25] = result.getObject("KTG_A54_e");
                data[26] = result.getObject("KTG_A54");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 19; i <= 20; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA55(String idSegmen) {
        Object[] data = new Object[18];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A55 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A55_a");
                data[1] = result.getObject("DEV_A55_a");
                data[2] = result.getObject("UJI_A55_b1");
                data[3] = result.getObject("DEV_A55_b1");
                data[4] = result.getObject("UJI_A55_b2");
                data[5] = result.getObject("DEV_A55_b2");
                data[6] = result.getObject("UJI_A55_c");
                data[7] = result.getObject("DEV_A55_c");
                data[8] = result.getObject("UJI_A55_d");
                data[9] = result.getObject("DEV_A55_d");
                data[10] = result.getObject("GBR_1");
                data[11] = result.getObject("GBR_2");
                data[12] = result.getObject("CATATAN");
                data[13] = result.getObject("KTG_A55_a");
                data[14] = result.getObject("KTG_A55_b");
                data[15] = result.getObject("KTG_A55_c");
                data[16] = result.getObject("KTG_A55_d");
                data[17] = result.getObject("KTG_A55");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 10; i <= 11; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA56(String idSegmen) {
        Object[] data = new Object[21];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A56 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A56_a1");
                data[1] = result.getObject("DEV_A56_a1");
                data[2] = result.getObject("UJI_A56_a2");
                data[3] = result.getObject("DEV_A56_a2");
                data[4] = result.getObject("UJI_A56_a3");
                data[5] = result.getObject("DEV_A56_a3");
                data[6] = result.getObject("UJI_A56_b");
                data[7] = result.getObject("DEV_A56_b");
                data[8] = result.getObject("UJI_A56_c");
                data[9] = result.getObject("UJI_A56_d");
                data[10] = result.getObject("DEV_A56_d");
                data[11] = result.getObject("UJI_A56_e");
                data[12] = result.getObject("DEV_A56_e");
                data[13] = result.getObject("GBR_1");
                data[14] = result.getObject("GBR_2");
                data[15] = result.getObject("CATATAN");
                data[16] = result.getObject("KTG_A56_a");
                data[17] = result.getObject("KTG_A56_b");
                data[18] = result.getObject("KTG_A56_d");
                data[19] = result.getObject("KTG_A56_e");
                data[20] = result.getObject("KTG_A56");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 13; i <= 14; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA57(String idSegmen) {
        Object[] data = new Object[26];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A57 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A57_a1");
                data[1] = result.getObject("DEV_A57_a1");
                data[2] = result.getObject("UJI_A57_a2");
                data[3] = result.getObject("DEV_A57_a2");
                data[4] = result.getObject("UJI_A57_a3");
                data[5] = result.getObject("DEV_A57_a3");
                data[6] = result.getObject("UJI_A57_a4");
                data[7] = result.getObject("DEV_A57_a4");
                data[8] = result.getObject("UJI_A57_b1");
                data[9] = result.getObject("DEV_A57_b1");
                data[10] = result.getObject("UJI_A57_b2");
                data[11] = result.getObject("DEV_A57_b2");
                data[12] = result.getObject("UJI_A57_b3");
                data[13] = result.getObject("DEV_A57_b3");
                data[14] = result.getObject("UJI_A57_c");
                data[15] = result.getObject("DEV_A57_c");
                data[16] = result.getObject("UJI_A57_d");
                data[17] = result.getObject("DEV_A57_d");
                data[18] = result.getObject("GBR_1");
                data[19] = result.getObject("GBR_2");
                data[20] = result.getObject("CATATAN");
                data[21] = result.getObject("KTG_A57_a");
                data[22] = result.getObject("KTG_A57_b");
                data[23] = result.getObject("KTG_A57_c");
                data[24] = result.getObject("KTG_A57_d");
                data[25] = result.getObject("KTG_A57");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 18; i <= 19; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6a1(String idSegmen) {
        Object[] data = new Object[66];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6a1 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6a1_a1_1");
                data[1] = result.getObject("DEV_A6a1_a1_1");
                data[2] = result.getObject("UJI_A6a1_a1_2");
                data[3] = result.getObject("DEV_A6a1_a1_2");
                data[4] = result.getObject("UJI_A6a1_a1_3");
                data[5] = result.getObject("DEV_A6a1_a1_3");
                data[6] = result.getObject("UJI_A6a1_a2_1");
                data[7] = result.getObject("DEV_A6a1_a2_1");
                data[8] = result.getObject("UJI_A6a1_a2_2");
                data[9] = result.getObject("DEV_A6a1_a2_2");
                data[10] = result.getObject("UJI_A6a1_a2_3");
                data[11] = result.getObject("DEV_A6a1_a2_3");
                data[12] = result.getObject("UJI_A6a1_a3_1");
                data[13] = result.getObject("DEV_A6a1_a3_1");
                data[14] = result.getObject("UJI_A6a1_a3_2");
                data[15] = result.getObject("DEV_A6a1_a3_2");
                data[16] = result.getObject("UJI_A6a1_a3_3");
                data[17] = result.getObject("DEV_A6a1_a3_3");
                data[18] = result.getObject("UJI_A6a1_a4_1");
                data[19] = result.getObject("DEV_A6a1_a4_1");
                data[20] = result.getObject("UJI_A6a1_a4_2");
                data[21] = result.getObject("DEV_A6a1_a4_2");
                data[22] = result.getObject("UJI_A6a1_a4_3");
                data[23] = result.getObject("DEV_A6a1_a4_3");
                data[24] = result.getObject("UJI_A6a1_a4_4");
                data[25] = result.getObject("DEV_A6a1_a4_4");
                data[26] = result.getObject("UJI_A6a1_a5_1");
                data[27] = result.getObject("DEV_A6a1_a5_1");
                data[28] = result.getObject("UJI_A6a1_a5_2");
                data[29] = result.getObject("DEV_A6a1_a5_2");
                data[30] = result.getObject("UJI_A6a1_a6_1");
                data[31] = result.getObject("DEV_A6a1_a6_1");
                data[32] = result.getObject("UJI_A6a1_a6_2");
                data[33] = result.getObject("DEV_A6a1_a6_2");
                data[34] = result.getObject("UJI_A6a1_a7_1");
                data[35] = result.getObject("DEV_A6a1_a7_1");
                data[36] = result.getObject("UJI_A6a1_a7_2");
                data[37] = result.getObject("DEV_A6a1_a7_2");
                data[38] = result.getObject("UJI_A6a1_a7_3");
                data[39] = result.getObject("DEV_A6a1_a7_3");
                data[40] = result.getObject("UJI_A6a1_a7_4");
                data[41] = result.getObject("DEV_A6a1_a7_4");
                data[42] = result.getObject("UJI_A6a1_a8");
                data[43] = result.getObject("DEV_A6a1_a8");
                data[44] = result.getObject("UJI_A6a1_a9_1");
                data[45] = result.getObject("DEV_A6a1_a9_1");
                data[46] = result.getObject("UJI_A6a1_a9_2");
                data[47] = result.getObject("DEV_A6a1_a9_2");
                data[48] = result.getObject("UJI_A6a1_a10");
                data[49] = result.getObject("DEV_A6a1_a10");
                data[50] = result.getObject("UJI_A6a1_a11");
                data[51] = result.getObject("DEV_A6a1_a11");
                data[52] = result.getObject("UJI_A6a1_a12");
                data[53] = result.getObject("DEV_A6a1_a12");
                data[54] = result.getObject("UJI_A6a1_a13");
                data[55] = result.getObject("DEV_A6a1_a13");
                data[56] = result.getObject("UJI_A6a1_b");
                data[57] = result.getObject("DEV_A6a1_b");
                data[58] = result.getObject("GBR_1");
                data[59] = result.getObject("GBR_2");
                data[60] = result.getObject("GBR_3");
                data[61] = result.getObject("GBR_4");
                data[62] = result.getObject("CATATAN");
                data[63] = result.getObject("KTG_A6a1_a");
                data[64] = result.getObject("KTG_A6a1_b");
                data[65] = result.getObject("KTG_A6a1");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 58; i <= 61; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6a2(String idSegmen) {
        Object[] data = new Object[47];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6a2 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6a2_a1");
                data[1] = result.getObject("DEV_A6a2_a1");
                data[2] = result.getObject("UJI_A6a2_a2_1");
                data[3] = result.getObject("DEV_A6a2_a2_1");
                data[4] = result.getObject("UJI_A6a2_a2_2");
                data[5] = result.getObject("DEV_A6a2_a2_2");
                data[6] = result.getObject("UJI_A6a2_a2_3");
                data[7] = result.getObject("DEV_A6a2_a2_3");
                data[8] = result.getObject("UJI_A6a2_a2_4");
                data[9] = result.getObject("DEV_A6a2_a2_4");
                data[10] = result.getObject("UJI_A6a2_a2_5");
                data[11] = result.getObject("DEV_A6a2_a2_5");
                data[12] = result.getObject("UJI_A6a2_b1_1");
                data[13] = result.getObject("DEV_A6a2_b1_1");
                data[14] = result.getObject("UJI_A6a2_b1_2");
                data[15] = result.getObject("DEV_A6a2_b1_2");
                data[16] = result.getObject("UJI_A6a2_b1_3");
                data[17] = result.getObject("DEV_A6a2_b1_3");
                data[18] = result.getObject("UJI_A6a2_b2_1");
                data[19] = result.getObject("DEV_A6a2_b2_1");
                data[20] = result.getObject("UJI_A6a2_b2_2");
                data[21] = result.getObject("DEV_A6a2_b2_2");
                data[22] = result.getObject("UJI_A6a2_b3_1");
                data[23] = result.getObject("DEV_A6a2_b3_1");
                data[24] = result.getObject("UJI_A6a2_b3_2");
                data[25] = result.getObject("DEV_A6a2_b3_2");
                data[26] = result.getObject("UJI_A6a2_b3_3");
                data[27] = result.getObject("DEV_A6a2_b3_3");
                data[28] = result.getObject("UJI_A6a2_b3_4");
                data[29] = result.getObject("DEV_A6a2_b3_4");
                data[30] = result.getObject("UJI_A6a2_c1");
                data[31] = result.getObject("DEV_A6a2_c1");
                data[32] = result.getObject("UJI_A6a2_c2");
                data[33] = result.getObject("DEV_A6a2_c2");
                data[34] = result.getObject("UJI_A6a2_c3");
                data[35] = result.getObject("DEV_A6a2_c3");
                data[36] = result.getObject("UJI_A6a2_c4");
                data[37] = result.getObject("DEV_A6a2_c4");
                data[38] = result.getObject("GBR_1");
                data[39] = result.getObject("GBR_2");
                data[40] = result.getObject("GBR_3");
                data[41] = result.getObject("GBR_4");
                data[42] = result.getObject("CATATAN");
                data[43] = result.getObject("KTG_A6a2_a");
                data[44] = result.getObject("KTG_A6a2_b");
                data[45] = result.getObject("KTG_A6a2_c");
                data[46] = result.getObject("KTG_A6a2");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 38; i <= 41; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6a3(String idSegmen) {
        Object[] data = new Object[18];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6a3 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6a3_a1");
                data[1] = result.getObject("DEV_A6a3_a1");
                data[2] = result.getObject("UJI_A6a3_a2_1");
                data[3] = result.getObject("DEV_A6a3_a2_1");
                data[4] = result.getObject("UJI_A6a3_a2_2");
                data[5] = result.getObject("DEV_A6a3_a2_2");
                data[6] = result.getObject("UJI_A6a3_b1");
                data[7] = result.getObject("DEV_A6a3_b1");
                data[8] = result.getObject("UJI_A6a3_b2_1");
                data[9] = result.getObject("DEV_A6a3_b2_1");
                data[10] = result.getObject("UJI_A6a3_b2_2");
                data[11] = result.getObject("DEV_A6a3_b2_2");
                data[12] = result.getObject("GBR_1");
                data[13] = result.getObject("GBR_1");
                data[14] = result.getObject("CATATAN");
                data[15] = result.getObject("KTG_A6a3_a");
                data[16] = result.getObject("KTG_A6a3_b");
                data[17] = result.getObject("KTG_A6a3");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 12; i <= 13; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6a4(String idSegmen) {
        Object[] data = new Object[39];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6a4 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6a4_a");
                data[1] = result.getObject("DEV_A6a4_a");
                data[2] = result.getObject("UJI_A6a4_b");
                data[3] = result.getObject("DEV_A6a4_b");
                data[4] = result.getObject("UJI_A6a4_c1_1");
                data[5] = result.getObject("DEV_A6a4_c1_1");
                data[6] = result.getObject("UJI_A6a4_c1_2");
                data[7] = result.getObject("DEV_A6a4_c1_2");
                data[8] = result.getObject("UJI_A6a4_c1_3");
                data[9] = result.getObject("DEV_A6a4_c1_3");
                data[10] = result.getObject("UJI_A6a4_c1_4");
                data[11] = result.getObject("DEV_A6a4_c1_4");
                data[12] = result.getObject("UJI_A6a4_c2_1");
                data[13] = result.getObject("DEV_A6a4_c2_1");
                data[14] = result.getObject("UJI_A6a4_c2_2");
                data[15] = result.getObject("DEV_A6a4_c2_2");
                data[16] = result.getObject("UJI_A6a4_c3_1");
                data[17] = result.getObject("DEV_A6a4_c3_1");
                data[18] = result.getObject("UJI_A6a4_c3_2");
                data[19] = result.getObject("DEV_A6a4_c3_2");
                data[20] = result.getObject("UJI_A6a4_c3_3");
                data[21] = result.getObject("DEV_A6a4_c3_3");
                data[22] = result.getObject("UJI_A6a4_c3_4");
                data[23] = result.getObject("DEV_A6a4_c3_4");
                data[24] = result.getObject("UJI_A6a4_c4_1");
                data[25] = result.getObject("DEV_A6a4_c4_1");
                data[26] = result.getObject("UJI_A6a4_c4_2");
                data[27] = result.getObject("DEV_A6a4_c4_2");
                data[28] = result.getObject("UJI_A6a4_c5_1");
                data[29] = result.getObject("DEV_A6a4_c5_1");
                data[30] = result.getObject("UJI_A6a4_c5_2");
                data[31] = result.getObject("DEV_A6a4_c5_2");
                data[32] = result.getObject("GBR_1");
                data[33] = result.getObject("GBR_2");
                data[34] = result.getObject("CATATAN");
                data[35] = result.getObject("KTG_A6a4_a");
                data[36] = result.getObject("KTG_A6a4_b");
                data[37] = result.getObject("KTG_A6a4_c");
                data[38] = result.getObject("KTG_A6a4");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 32; i <= 33; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6a5(String idSegmen) {
        Object[] data = new Object[28];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6a5 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6a5_a1");
                data[1] = result.getObject("DEV_A6a5_a1");
                data[2] = result.getObject("UJI_A6a5_a2");
                data[3] = result.getObject("DEV_A6a5_a2");
                data[4] = result.getObject("UJI_A6a5_a3");
                data[5] = result.getObject("DEV_A6a5_a3");
                data[6] = result.getObject("UJI_A6a5_b1");
                data[7] = result.getObject("DEV_A6a5_b1");
                data[8] = result.getObject("UJI_A6a5_b2");
                data[9] = result.getObject("DEV_A6a5_b2");
                data[10] = result.getObject("UJI_A6a5_c1");
                data[11] = result.getObject("DEV_A6a5_c1");
                data[12] = result.getObject("UJI_A6a5_c2");
                data[13] = result.getObject("DEV_A6a5_c2");
                data[14] = result.getObject("UJI_A6a5_c3");
                data[15] = result.getObject("DEV_A6a5_c3");
                data[16] = result.getObject("UJI_A6a5_c4");
                data[17] = result.getObject("DEV_A6a5_c4");
                data[18] = result.getObject("UJI_A6a5_d");
                data[19] = result.getObject("DEV_A6a5_d");
                data[20] = result.getObject("GBR_1");
                data[21] = result.getObject("GBR_2");
                data[22] = result.getObject("CATATAN");
                data[23] = result.getObject("KTG_A6a5_a");
                data[24] = result.getObject("KTG_A6a5_b");
                data[25] = result.getObject("KTG_A6a5_c");
                data[26] = result.getObject("KTG_A6a5_d");
                data[27] = result.getObject("KTG_A6a5");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 20; i <= 21; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6a6(String idSegmen) {
        Object[] data = new Object[26];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6a6 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6a6_a1");
                data[1] = result.getObject("DEV_A6a6_a1");
                data[2] = result.getObject("UJI_A6a6_a2_1");
                data[3] = result.getObject("DEV_A6a6_a2_1");
                data[4] = result.getObject("UJI_A6a6_a2_2");
                data[5] = result.getObject("DEV_A6a6_a2_2");
                data[6] = result.getObject("UJI_A6a6_a3_1");
                data[7] = result.getObject("DEV_A6a6_a3_1");
                data[8] = result.getObject("UJI_A6a6_a3_2");
                data[9] = result.getObject("DEV_A6a6_a3_2");
                data[10] = result.getObject("UJI_A6a6_a3_3");
                data[11] = result.getObject("DEV_A6a6_a3_3");
                data[12] = result.getObject("UJI_A6a6_b");
                data[13] = result.getObject("DEV_A6a6_b");
                data[14] = result.getObject("UJI_A6a6_c");
                data[15] = result.getObject("DEV_A6a6_c");
                data[16] = result.getObject("UJI_A6a6_d");
                data[17] = result.getObject("DEV_A6a6_d");
                data[18] = result.getObject("GBR_1");
                data[19] = result.getObject("GBR_2");
                data[20] = result.getObject("CATATAN");
                data[21] = result.getObject("KTG_A6a6_a");
                data[22] = result.getObject("KTG_A6a6_b");
                data[23] = result.getObject("KTG_A6a6_c");
                data[24] = result.getObject("KTG_A6a6_d");
                data[25] = result.getObject("KTG_A6a6");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 18; i <= 19; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6a7(String idSegmen) {
        Object[] data = new Object[50];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6a7 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6a7_a");
                data[1] = result.getObject("DEV_A6a7_a");
                data[2] = result.getObject("UJI_A6a7_b1");
                data[3] = result.getObject("DEV_A6a7_b1");
                data[4] = result.getObject("UJI_A6a7_b2");
                data[5] = result.getObject("DEV_A6a7_b2");
                data[6] = result.getObject("UJI_A6a7_c");
                data[7] = result.getObject("DEV_A6a7_c");
                data[8] = result.getObject("UJI_A6a7_d1_1");
                data[9] = result.getObject("DEV_A6a7_d1_1");
                data[10] = result.getObject("UJI_A6a7_d1_2");
                data[11] = result.getObject("DEV_A6a7_d1_2");
                data[12] = result.getObject("UJI_A6a7_d2_1");
                data[13] = result.getObject("DEV_A6a7_d2_1");
                data[14] = result.getObject("UJI_A6a7_d2_2");
                data[15] = result.getObject("DEV_A6a7_d2_2");
                data[16] = result.getObject("UJI_A6a7_d3");
                data[17] = result.getObject("DEV_A6a7_d3");
                data[18] = result.getObject("UJI_A6a7_e1_1");
                data[19] = result.getObject("DEV_A6a7_e1_1");
                data[20] = result.getObject("UJI_A6a7_e1_2");
                data[21] = result.getObject("DEV_A6a7_e1_2");
                data[22] = result.getObject("UJI_A6a7_e2");
                data[23] = result.getObject("DEV_A6a7_e2");
                data[24] = result.getObject("UJI_A6a7_f1_1");
                data[25] = result.getObject("DEV_A6a7_f1_1");
                data[26] = result.getObject("UJI_A6a7_f1_2");
                data[27] = result.getObject("DEV_A6a7_f1_2");
                data[28] = result.getObject("UJI_A6a7_f2_1");
                data[29] = result.getObject("DEV_A6a7_f2_1");
                data[30] = result.getObject("UJI_A6a7_f2_2");
                data[31] = result.getObject("DEV_A6a7_f2_2");
                data[32] = result.getObject("UJI_A6a7_f3_1");
                data[33] = result.getObject("DEV_A6a7_f3_1");
                data[34] = result.getObject("UJI_A6a7_f3_2");
                data[35] = result.getObject("DEV_A6a7_f3_2");
                data[36] = result.getObject("UJI_A6a7_f4");
                data[37] = result.getObject("DEV_A6a7_f4");
                data[38] = result.getObject("GBR_1");
                data[39] = result.getObject("GBR_2");
                data[40] = result.getObject("GBR_3");
                data[41] = result.getObject("GBR_4");
                data[42] = result.getObject("CATATAN");
                data[43] = result.getObject("KTG_A6a7_a");
                data[44] = result.getObject("KTG_A6a7_b");
                data[45] = result.getObject("KTG_A6a7_c");
                data[46] = result.getObject("KTG_A6a7_d");
                data[47] = result.getObject("KTG_A6a7_e");
                data[48] = result.getObject("KTG_A6a7_f");
                data[49] = result.getObject("KTG_A6a7");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 38; i <= 41; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b1(String idSegmen) {
        Object[] data = new Object[19];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b1 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b1_a");
                data[1] = result.getObject("DEV_A6b1_a");
                data[2] = result.getObject("UJI_A6b1_b1_1");
                data[3] = result.getObject("DEV_A6b1_b1_1");
                data[4] = result.getObject("UJI_A6b1_b1_2");
                data[5] = result.getObject("DEV_A6b1_b1_2");
                data[6] = result.getObject("UJI_A6b1_b2");
                data[7] = result.getObject("DEV_A6b1_b2");
                data[8] = result.getObject("UJI_A6b1_b3");
                data[9] = result.getObject("DEV_A6b1_b3");
                data[10] = result.getObject("UJI_A6b1_c");
                data[11] = result.getObject("DEV_A6b1_c");
                data[12] = result.getObject("GBR_1");
                data[13] = result.getObject("GBR_2");
                data[14] = result.getObject("CATATAN");
                data[15] = result.getObject("KTG_A6b1_a");
                data[16] = result.getObject("KTG_A6b1_b");
                data[17] = result.getObject("KTG_A6b1_c");
                data[18] = result.getObject("KTG_A6b1");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 12; i <= 13; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b2(String idSegmen) {
        Object[] data = new Object[25];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b2 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b2_a");
                data[1] = result.getObject("DEV_A6b2_a");
                data[2] = result.getObject("UJI_A6b2_b1_1");
                data[3] = result.getObject("DEV_A6b2_b1_1");
                data[4] = result.getObject("UJI_A6b2_b1_2");
                data[5] = result.getObject("DEV_A6b2_b1_2");
                data[6] = result.getObject("UJI_A6b2_b1_3");
                data[7] = result.getObject("DEV_A6b2_b1_3");
                data[8] = result.getObject("UJI_A6b2_b2_1");
                data[9] = result.getObject("DEV_A6b2_b2_1");
                data[10] = result.getObject("UJI_A6b2_b2_2");
                data[11] = result.getObject("DEV_A6b2_b2_2");
                data[12] = result.getObject("UJI_A6b2_b3");
                data[13] = result.getObject("DEV_A6b2_b3");
                data[14] = result.getObject("UJI_A6b2_b4");
                data[15] = result.getObject("DEV_A6b2_b4");
                data[16] = result.getObject("UJI_A6b2_c");
                data[17] = result.getObject("DEV_A6b2_c");
                data[18] = result.getObject("GBR_1");
                data[19] = result.getObject("GBR_2");
                data[20] = result.getObject("CATATAN");
                data[21] = result.getObject("KTG_A6b2_a");
                data[22] = result.getObject("KTG_A6b2_b");
                data[23] = result.getObject("KTG_A6b2_c");
                data[24] = result.getObject("KTG_A6b2");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 18; i <= 19; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b3(String idSegmen) {
        Object[] data = new Object[23];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b3 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b3_a");
                data[1] = result.getObject("DEV_A6b3_a");
                data[2] = result.getObject("UJI_A6b3_b1_1");
                data[3] = result.getObject("DEV_A6b3_b1_1");
                data[4] = result.getObject("UJI_A6b3_b1_2");
                data[5] = result.getObject("DEV_A6b3_b2_2");
                data[6] = result.getObject("UJI_A6b3_b2_1");
                data[7] = result.getObject("DEV_A6b3_b2_1");
                data[8] = result.getObject("UJI_A6b3_b2_2");
                data[9] = result.getObject("DEV_A6b3_b2_2");
                data[10] = result.getObject("UJI_A6b3_b3");
                data[11] = result.getObject("DEV_A6b3_b3");
                data[12] = result.getObject("UJI_A6b3_b4");
                data[13] = result.getObject("DEV_A6b3_b4");
                data[14] = result.getObject("UJI_A6b3_c");
                data[15] = result.getObject("DEV_A6b3_c");
                data[16] = result.getObject("GBR_1");
                data[17] = result.getObject("GBR_2");
                data[18] = result.getObject("CATATAN");
                data[19] = result.getObject("KTG_A6b3_a");
                data[20] = result.getObject("KTG_A6b3_b");
                data[21] = result.getObject("KTG_A6b3_c");
                data[22] = result.getObject("KTG_A6b3");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 16; i <= 17; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b4(String idSegmen) {
        Object[] data = new Object[18];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b4 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b4_a1_2");
                data[1] = result.getObject("DEV_A6b4_a1_2");
                data[2] = result.getObject("UJI_A6b4_a1_3");
                data[3] = result.getObject("DEV_A6b4_a1_3");
                data[4] = result.getObject("UJI_A6b4_a2");
                data[5] = result.getObject("DEV_A6b4_a2");
                data[6] = result.getObject("UJI_A6b4_a3");
                data[7] = result.getObject("DEV_A6b4_a3");
                data[8] = result.getObject("UJI_A6b4_b");
                data[9] = result.getObject("DEV_A6b4_b");
                data[10] = result.getObject("GBR_1");
                data[11] = result.getObject("GBR_2");
                data[12] = result.getObject("CATATAN");
                data[13] = result.getObject("KTG_A6b4_a");
                data[14] = result.getObject("KTG_A6b4_b");
                data[15] = result.getObject("KTG_A6b4");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 12; i <= 13; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b5(String idSegmen) {
        Object[] data = new Object[15];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b5 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b5_a1");
                data[1] = result.getObject("DEV_A6b5_a1");
                data[2] = result.getObject("UJI_A6b5_a2");
                data[3] = result.getObject("DEV_A6b5_a2");
                data[4] = result.getObject("UJI_A6b5_a3");
                data[5] = result.getObject("DEV_A6b5_a3");
                data[6] = result.getObject("UJI_A6b5_a4");
                data[7] = result.getObject("DEV_A6b5_a4");
                data[8] = result.getObject("UJI_A6b5_b");
                data[9] = result.getObject("DEV_A6b5_b");
                data[10] = result.getObject("GBR_1");
                data[11] = result.getObject("CATATAN");
                data[12] = result.getObject("KTG_A6b5_a");
                data[13] = result.getObject("KTG_A6b5_b");
                data[14] = result.getObject("KTG_A6b5");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 10; i <= 10; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b6(String idSegmen) {
        Object[] data = new Object[13];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b6 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b6_a");
                data[1] = result.getObject("DEV_A6b6_a");
                data[2] = result.getObject("UJI_A6b6_b1");
                data[3] = result.getObject("DEV_A6b6_b1");
                data[4] = result.getObject("UJI_A6b6_b2");
                data[5] = result.getObject("DEV_A6b6_b2");
                data[6] = result.getObject("UJI_A6b6_b3");
                data[7] = result.getObject("DEV_A6b6_b3");
                data[8] = result.getObject("GBR_1");
                data[9] = result.getObject("CATATAN");
                data[10] = result.getObject("KTG_A6b6_a");
                data[11] = result.getObject("KTG_A6b6_b");
                data[12] = result.getObject("KTG_A6b6");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 8; i <= 8; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b7(String idSegmen) {
        Object[] data = new Object[17];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b7 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b7_a");
                data[1] = result.getObject("DEV_A6b7_a");
                data[2] = result.getObject("UJI_A6b7_b1");
                data[3] = result.getObject("DEV_A6b7_b1");
                data[4] = result.getObject("UJI_A6b7_b2");
                data[5] = result.getObject("DEV_A6b7_b2");
                data[6] = result.getObject("UJI_A6b7_c1");
                data[7] = result.getObject("DEV_A6b7_c1");
                data[8] = result.getObject("UJI_A6b7_c2");
                data[9] = result.getObject("DEV_A6b7_c2");
                data[10] = result.getObject("GBR_1");
                data[11] = result.getObject("GBR_2");
                data[12] = result.getObject("CATATAN");
                data[13] = result.getObject("KTG_A6b7_a");
                data[14] = result.getObject("KTG_A6b7_b");
                data[15] = result.getObject("KTG_A6b7_c");
                data[16] = result.getObject("KTG_A6b7");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 10; i <= 11; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getDataA6b8(String idSegmen) {
        Object[] data = new Object[24];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, A6b8 a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_A6b8_a1");
                data[1] = result.getObject("DEV_A6b8_a1");
                data[2] = result.getObject("UJI_A6b8_a2");
                data[3] = result.getObject("DEV_A6b8_a2");
                data[4] = result.getObject("UJI_A6b8_a3");
                data[5] = result.getObject("DEV_A6b8_a3");
                data[6] = result.getObject("UJI_A6b8_a4");
                data[7] = result.getObject("DEV_A6b8_a4");
                data[8] = result.getObject("UJI_A6b8_a5");
                data[9] = result.getObject("DEV_A6b8_a5");
                data[10] = result.getObject("UJI_A6b8_a6");
                data[11] = result.getObject("DEV_A6b8_a6");
                data[12] = result.getObject("UJI_A6b8_a7");
                data[13] = result.getObject("DEV_A6b8_a7");
                data[14] = result.getObject("UJI_A6b8_b");
                data[15] = result.getObject("DEV_A6b8_b");
                data[16] = result.getObject("GBR_1");
                data[17] = result.getObject("GBR_2");
                data[18] = result.getObject("GBR_3");
                data[19] = result.getObject("GBR_4");
                data[20] = result.getObject("CATATAN");
                data[21] = result.getObject("KTG_A6b8_a");
                data[22] = result.getObject("KTG_A6b8_b");
                data[23] = result.getObject("KTG_A6b8");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 16; i <= 19; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data");
        }

        return data;
    }

    public Object[] getAdministrasiJalan(String idSegmen) {
        Object[] data = new Object[24];

        //read data from DB
        try {
            //create connection
            Connection conn = getKoneksi();

            //create statement
            Statement stmt = conn.createStatement();

            //Create query 
            String query = "SELECT a.* FROM ruas r, segmen s, administrasi_jalan a WHERE r.ID_RUAS = s.ID_RUAS "
                    + "AND s.ID_SEGMEN = a.ID_SEGMEN AND s.ID_SEGMEN = '" + idSegmen + "'";

            //Execute the query and save the result
            ResultSet result = stmt.executeQuery(query);

            //Move the result into 'data'
            while (result.next()) {
                data[0] = result.getObject("UJI_ADMIN_a1");
                data[1] = result.getObject("UJI_ADMIN_a2");
                data[2] = result.getObject("UJI_ADMIN_a3");
                data[3] = result.getObject("KTG_ADMIN_a");
                data[4] = result.getObject("UJI_ADMIN_b1");
                data[5] = result.getObject("UJI_ADMIN_b2");
                data[6] = result.getObject("UJI_ADMIN_b3");
                data[7] = result.getObject("KTG_ADMIN_b");
                data[8] = result.getObject("UJI_ADMIN_c1");
                data[9] = result.getObject("UJI_ADMIN_c2");
                data[10] = result.getObject("UJI_ADMIN_c3");
                data[11] = result.getObject("KTG_ADMIN_c");
                data[12] = result.getObject("UJI_ADMIN_d1");
                data[13] = result.getObject("UJI_ADMIN_d2");
                data[14] = result.getObject("UJI_ADMIN_d3");
                data[15] = result.getObject("KTG_ADMIN_d");
                data[16] = result.getObject("UJI_ADMIN_e1");
                data[17] = result.getObject("UJI_ADMIN_e2");
                data[18] = result.getObject("UJI_ADMIN_e3");
                data[19] = result.getObject("KTG_ADMIN_e");
                data[20] = result.getObject("UJI_ADMIN_f1");
                data[21] = result.getObject("UJI_ADMIN_f2");
                data[22] = result.getObject("UJI_ADMIN_f3");
                data[23] = result.getObject("KTG_ADMIN_f");
            }

            //cek apakah GBR_1 Null atau tidak
            for (int i = 16; i <= 19; i++) {
                if (data[i] == null) {
                    data[i] = "empty";
                }
            }

            result.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error reading data Administrasi Jalan");
        }

        return data;
    }
}
