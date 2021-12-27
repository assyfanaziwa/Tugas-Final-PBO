package koperasi.ControlDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBhelper {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://localhost/Koperasi";

    public static Connection getConection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Koneksi Terhubung Ke Data Base");
        }catch (ClassNotFoundException | SQLException x){
            x.printStackTrace();
            System.out.println("Gagal Terhubung Ke Data Base");
            connection = null;
        }
        return connection;
    }
    public static void createTable(Connection connection){
        String sql = "CREATE TABLE IF NOT EXISTS `nasabah` ("
                + "  `id_nasabah` int(10) NOT NULL ,"
                + "  `nama` varchar(100) DEFAULT NULL,"
                + "  `alamat` varchar(100) DEFAULT NULL,"
                + "  PRIMARY KEY (`id_nasabah`)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                + "CREATE TABLE IF NOT EXISTS `rekening` ("
                + "  `noRekening` int(25) NOT NULL ,"
                + "  `saldo` double(16,2) DEFAULT NULL,"
                + "  `id_nasabah` int(10) DEFAULT NULL ,"
                + "  PRIMARY KEY (`noRekening`),"
                + "  KEY `id_nasabah` (`id_nasabah`),"
                + "  FOREIGN KEY (`id_nasabah`) REFERENCES `nasabah` (`id_nasabah`) ON UPDATE CASCADE"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                + "CREATE TABLE IF NOT EXISTS `perusahaan` ("
                + "  `id_nasabah` int(10) NOT NULL ,"
                + "  `nib` varchar(100) DEFAULT NULL,"
                + "  PRIMARY KEY (`id_nasabah`),"
                + "  FOREIGN KEY (`id_nasabah`) REFERENCES `nasabah` (`id_nasabah`) ON UPDATE CASCADE"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                + "CREATE TABLE IF NOT EXISTS `individu` ("
                + "  `id_nasabah` int(10) NOT NULL ,"
                + "  `nik` bigint(255) DEFAULT NULL,"
                + "  `npwp` bigint(255) DEFAULT NULL,"
                + "  PRIMARY KEY (`id_nasabah`),"
                + "  FOREIGN KEY (`id_nasabah`) REFERENCES `nasabah` (`id_nasabah`) ON UPDATE CASCADE"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        try{
            String[] sqls = sql.split(";");
            for (String sFor : sqls){
                PreparedStatement preparedStatement = connection.prepareStatement(sFor);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}