package koperasi.ControlDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import koperasi.Individu;
import koperasi.Perusahaan;
import koperasi.Rekening;

import java.sql.*;
import java.util.ArrayList;

public class NasabahDataModel {
    public final Connection connection;

    public NasabahDataModel() {
        this.connection = DBhelper.getConection();
    }

    public void addIndvidu(Individu individu){
        try {
            String sqlNasabah = "INSERT INTO nasabah (id_nasabah,nama,alamat)"+" VALUES (?,?,?)";
            String sqlIndividual = "INSERT INTO individual (id_nasabah,nik,npwp)"+" VALUES (?,?,?)";
            String sqlRekening = "INSERT INTO rekening (noRekening,saldo,id_nasabah)"+ " VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlNasabah);
            preparedStatement.setInt(1,individu.getId());
            preparedStatement.setString(2,individu.getNama());
            preparedStatement.setString(3,individu.getAlamat());
            preparedStatement.execute();
            preparedStatement.close();

            PreparedStatement preparedIndividual = connection.prepareStatement(sqlIndividual);
            preparedIndividual.setInt(1,individu.getId());
            preparedIndividual.setLong(2,individu.getNik());
            preparedIndividual.setLong(3,individu.getNpwp());
            preparedIndividual.execute();
            preparedIndividual.close();

            PreparedStatement preparedRekening = connection.prepareStatement(sqlRekening);
            preparedRekening.setInt(1,individu.getRekening().get(0).getNoRekening());
            preparedRekening.setDouble(2,individu.getRekening().get(0).getSaldo());
            preparedRekening.setInt(3,individu.getId());
            preparedRekening.execute();
            preparedRekening.close();

        }catch (SQLException X){
            System.out.println(X);
        }
    }
    public void addPerusahaan(Perusahaan perusahaan){
        try {
            String sqlNasabah = "INSERT INTO nasabah(id_nasabah,nama,alamat) VALUES"+"(?,?,?)";
            String sqlIPerusahaan = "insert into perusahaan(id_nasabah,nib) values "+"(?,?)";
            String sqlRekening = "insert into rekening(noRekening,saldo,id_nasabah) values "+ "(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlNasabah);
            preparedStatement.setInt(1,perusahaan.getId());
            preparedStatement.setString(2,perusahaan.getNama());
            preparedStatement.setString(3,perusahaan.getAlamat());
            preparedStatement.execute();
            preparedStatement.close();

            PreparedStatement preparedPerusahaan = connection.prepareStatement(sqlIPerusahaan);
            preparedPerusahaan.setInt(1,perusahaan.getId());
            preparedPerusahaan.setString(2,perusahaan.getNib());
            preparedPerusahaan.execute();
            preparedPerusahaan.close();

            PreparedStatement preparedRekening = connection.prepareStatement(sqlRekening);
            preparedRekening.setInt(1,perusahaan.getRekening().get(0).getNoRekening());
            preparedRekening.setDouble(2,perusahaan.getRekening().get(0).getSaldo());
            preparedRekening.setInt(3,perusahaan.getId());
            preparedRekening.execute();
            preparedRekening.close();

        }catch (SQLException X){
            System.out.println(X);
        }
    }
    public ObservableList<Individu> getIndividu(){
        ObservableList<Individu> data = FXCollections.observableArrayList();
        String sql = "SELECT id_nasabah, nama, alamat, nik, npwp "+
                "FROM nasabah NATURAL JOIN individual "+
                "ORDER BY nama";
        try{
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                String sqlRekening = "SELECT noRekening, saldo, id_nasabah "+
                        "FROM rekening WHERE id_nasabah="+ resultSet.getInt(1);
                ResultSet rsRekening = connection.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> rekenings = new ArrayList<>();
                while (rsRekening.next()){
                    rekenings.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }
                data.add(new Individu(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getLong(4),resultSet.getLong(5),rekenings ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }
    public ObservableList<Perusahaan> getPerusahaan(){
        ObservableList<Perusahaan> data = FXCollections.observableArrayList();
        try{
            String sql = "SELECT id_nasabah, nama, alamat, nib "+
                    "FROM nasabah NATURAL JOIN perusahaan "+
                    "ORDER BY nama";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                String sqlRekening = "SELECT noRekening, saldo, id_nasabah "+
                        "FROM Rekening WHERE id_nasabah = " + resultSet.getInt(1);
                ResultSet rsRekening = connection.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> rekenings = new ArrayList<>();
                while (rsRekening.next()){
                    rekenings.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }
                data.add(new Perusahaan(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getString(4), rekenings));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }
    public ObservableList<Rekening>getRekening(int ID){
        ObservableList<Rekening> data = FXCollections.observableArrayList();
        try {
            String sql = "SELECT noRekening, saldo "+
                    "FROM rekening "+
                    "WHERE id_nasabah="+ID;
            ResultSet resultSet  = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                data.add(new Rekening(resultSet.getInt(1),resultSet.getDouble(2)));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }
    public int nextNasabahID(){
        try {
            String sql = "SELECT MAX(id_nasabah) FROM nasabah";
            ResultSet resultSet  = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                return resultSet.getInt(1)==0 ? 1000001 : resultSet.getInt(1)+1;
            }
        }catch (SQLException e){
            System.out.println(e+"\neror n");
        }
        return 1000001;
    }
    public int nextNoRekening(int ID){
        try {
            String sql = "SELECT MAX(noRekening) FROM rekening WHERE id_nasabah="+ID;
            ResultSet resultSet  = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                return resultSet.getInt(1)+1;
            }
        }
        catch (SQLException throwables) {
            System.out.println(throwables);
        }
        return 0;
    }
    public void addRekening(int holderId, Rekening rek){
        try {
            String insertNasabah = "INSERT INTO rekening (id_nasabah, noRekening, saldo)"
                    + " VALUES (?, ?, ?)";

            PreparedStatement preparedRekening = connection.prepareStatement(insertNasabah);
            preparedRekening.setInt(1, holderId);
            preparedRekening.setInt(2, rek.getNoRekening());
            preparedRekening.setDouble(3, rek.getSaldo());
            preparedRekening.execute();
        }catch (Exception E){
            System.out.println(E);
        }
    }
}