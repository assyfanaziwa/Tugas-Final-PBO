package koperasi.Control;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import koperasi.ControlDB.NasabahDataModel;
import koperasi.Individu;
import koperasi.Perusahaan;
import koperasi.Rekening;
import javax.swing.*;


public class Controller implements Initializable {

    private NasabahDataModel nasabahDataModel;
    private Rekening rekeningsGB;

    @FXML
    private TextField tfIDIndividual;

    @FXML
    private TextField tfNamaIndividual;

    @FXML
    private TextField tfAlamatIndividual;

    @FXML
    private TextField tfNIKIndividual;

    @FXML
    private TextField tfNPWPIndividual;

    @FXML
    private TextField tfNoRekIndividual;

    @FXML
    private TextField tfSaldoRekIndividual;

    @FXML
    private Button btnPerbaruiIndividual;

    @FXML
    private Button BTNclearIndividu;

    @FXML
    private Label lblAddStatusindividual;

    @FXML
    private TableView<Individu> tblDataIndividu;

    @FXML
    private TableColumn<Individu, Integer> colIDIndividu;

    @FXML
    private TableColumn<Individu, String> colNamaIndividu;

    @FXML
    private TableColumn<Individu, String> colAlamatIndividu;

    @FXML
    private TableColumn<Individu, Long> colNIK;

    @FXML
    private TableColumn<Individu, Long> colNPWP;

    @FXML
    private TableColumn<Individu, Integer> colJumAkunIndividu;

    @FXML
    private TableView<Rekening> tblRekeningIndividu;

    @FXML
    private TableColumn<Rekening, Integer> colNumRekIndividu;

    @FXML
    private TableColumn<Rekening, Double> colSaldoIndividu;

    @FXML
    private TextField tfIDNasabahBaruIndividual;

    @FXML
    private TextField tfNoRekBaruIndividual;

    @FXML
    private TextField tfSaldoRekBaruIndividual;

    @FXML
    private Button btnTambahRekBaruIndividual;

    @FXML
    private TextField tfRekeningDipilihIndividual;

    @FXML
    private TextField tfNominalIndividual;

    @FXML
    private Button btnTarikTunaiIndividual;

    @FXML
    private Button btnTambahSaldoIndividual;

    @FXML
    private TextField tfIDPerusahaan;

    @FXML
    private TextField tfNamaPerusahaan;

    @FXML
    private TextField tfAlamatPerusahaan;

    @FXML
    private TextField tfNIBPerusahaan;

    @FXML
    private TextField tfNoRekPerusahaan;

    @FXML
    private TextField tfSaldoPerusahaan;


    @FXML
    private Button btnPerbaruiPerusahaan;

    @FXML
    private Button btnHapusPerusahaan;

    @FXML
    private Label lblAddStatusPerusahaan;

    @FXML
    private TableView<Perusahaan> tblDataPerusahaan;

    @FXML
    private TableColumn<Perusahaan, Integer> colIDPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colNamaPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colAlamatPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colNIB;

    @FXML
    private TableColumn<Perusahaan, Integer> colJumAkunPerusahaan;

    @FXML
    private TableView<Rekening> tblRekeningPerusahaan;

    @FXML
    private TableColumn<Rekening, Integer> colNumRekPerusahaan;

    @FXML
    private TableColumn<Rekening, Double> colSaldoPerusahaan;

    @FXML
    private TextField tfIDNasabahBaruPerusahaan;

    @FXML
    private TextField tfNoRekBaruPerusahaan;

    @FXML
    private TextField tfSaldoRekBaruPerusahaan;

    @FXML
    private Button btnTambahRekBaruPerusahaan;

    @FXML
    private TextField tfRekeningDipilihPerusahaan;

    @FXML
    private TextField tfNominalPerusahaan;

    @FXML
    private Button btnTarikTunaiPerusahaan;

    @FXML
    private Button btnTambahSaldoPerusahaan;

    @FXML
    private Label lblDBStatus;

    @FXML
    private Label lblTarikTambahIndividu;

    @FXML
    private Label lblTambahTarikPerusahaan;


    @FXML
    void handleClearIndividu(ActionEvent event){
        try {
            tfIDIndividual.setText("" + nasabahDataModel.nextNasabahID());
            tfNoRekIndividual.setText(tfIDIndividual.getText() + "01");
            tfNamaIndividual.setText("");
            tfAlamatIndividual.setText("");
            tfNIKIndividual.setText("");
            tfNPWPIndividual.setText("");
            tfSaldoRekIndividual.setText("");
            tfSaldoRekBaruIndividual.setText("");
            tfNominalIndividual.setText("");
        }catch (Exception E){
            System.out.println(E);
        }
    }

    @FXML
    void handleClearPerusahaan(ActionEvent event) {
        tfIDPerusahaan.setText("" + nasabahDataModel.nextNasabahID());
        tfNoRekPerusahaan.setText(tfIDIndividual.getText() + "01");
        tfNamaPerusahaan.setText("");
        tfAlamatPerusahaan.setText("");
        tfNIBPerusahaan.setText("");
        tfSaldoPerusahaan.setText("");
        tfSaldoRekBaruPerusahaan.setText("");
        tfNominalPerusahaan.setText("");
    }

    @FXML
    void handleRefreshTabelIndividu(ActionEvent event) {
        ObservableList<Individu> data = nasabahDataModel.getIndividu();
        colIDIndividu.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNamaIndividu.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamatIndividu.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNIK.setCellValueFactory(new PropertyValueFactory<>("nik"));
        colNPWP.setCellValueFactory(new PropertyValueFactory<>("npwp"));
        colJumAkunIndividu.setCellValueFactory(new PropertyValueFactory<>("rekNum"));
        tblDataIndividu.setItems(null);
        tblDataIndividu.setItems(data);
        btnTambahRekBaruIndividual.setDisable(true);
        btnTambahSaldoIndividual.setDisable(true);
        btnTarikTunaiIndividual.setDisable(true);
        tfNominalIndividual.setDisable(true);
        tfSaldoRekBaruIndividual.setDisable(true);
        lblAddStatusindividual.setText("");
        tblRekeningIndividu.setItems(null);
        lblTarikTambahIndividu.setText("");
        BTNclearIndividu.fire();
    }

    @FXML
    void handleRefreshTabelPerusahaan(ActionEvent event) {
        ObservableList<Perusahaan> data = nasabahDataModel.getPerusahaan();
        colIDPerusahaan.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNamaPerusahaan.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamatPerusahaan.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNIB.setCellValueFactory(new PropertyValueFactory<>("nib"));
        colJumAkunPerusahaan.setCellValueFactory(new PropertyValueFactory<>("rekNum"));
        tblDataPerusahaan.setItems(null);
        tblDataPerusahaan.setItems(data);
        btnTambahRekBaruPerusahaan.setDisable(true);
        btnTambahSaldoPerusahaan.setDisable(true);
        btnTarikTunaiPerusahaan.setDisable(true);
        tfSaldoRekBaruPerusahaan.setDisable(true);
        tfNominalPerusahaan.setDisable(true);
        lblAddStatusPerusahaan.setText("");
        tblRekeningPerusahaan.setItems(null);
        lblTambahTarikPerusahaan.setText("");
        btnHapusPerusahaan.fire();
    }

    @FXML
    void handleAddNasabahIndividu(ActionEvent event) {
        try {
            if (tfNamaIndividual.getText().trim().equals("") || tfAlamatIndividual.getText().trim().equals("") ||
                tfNIKIndividual.getText().trim().equals("") || tfNPWPIndividual.getText().trim().equals("") || tfSaldoRekIndividual.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Isi Data Dengan Lengkap");
            }else{
                nasabahDataModel.addIndvidu(new Individu(
                        Integer.parseInt(tfIDIndividual.getText()),tfNamaIndividual.getText(),tfAlamatIndividual.getText(),
                        Long.parseLong(tfNIKIndividual.getText()), Long.parseLong(tfNPWPIndividual.getText()),
                        new Rekening(Integer.parseInt(tfNoRekIndividual.getText()),Double.parseDouble(tfSaldoRekIndividual.getText()))
                ));
                lblAddStatusindividual.setText("successfully individual account");
                tfIDIndividual.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
                tfNoRekIndividual.setText(tfIDIndividual.getText()+"01");
                BTNclearIndividu.fire();
                tfIDIndividual.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
                tfNoRekIndividual.setText(tfIDIndividual.getText()+"01");
                tfIDPerusahaan.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
                tfNoRekPerusahaan.setText(tfIDIndividual.getText()+"01");
            }
        }catch (Exception e){
            System.out.println(e);
            lblAddStatusindividual.setText("failed individual account");
        }
    }
    @FXML
    void handleAddNasabahPerusahaan(ActionEvent event) {
        try {
            if(tfNamaPerusahaan.getText().trim().equals("") || tfAlamatPerusahaan.getText().trim().equals("") || tfNIBPerusahaan.getText().trim().equals("") ||
                    tfSaldoPerusahaan.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Isi Data Dengan Lengkap");
            }else {
                nasabahDataModel.addPerusahaan(new Perusahaan(
                        Integer.parseInt(tfIDPerusahaan.getText()),
                        tfNamaPerusahaan.getText(),
                        tfAlamatPerusahaan.getText(),
                        tfNIBPerusahaan.getText(), new Rekening(Integer.parseInt(tfNoRekPerusahaan.getText()),
                        Double.parseDouble(tfSaldoPerusahaan.getText())) ));
                tfNoRekPerusahaan.setText(tfIDIndividual.getText()+"01");
                btnHapusPerusahaan.fire();
                lblAddStatusPerusahaan.setText("successfully company account");
                tfIDIndividual.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
                tfNoRekIndividual.setText(tfIDIndividual.getText()+"01");
                tfIDPerusahaan.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
                tfNoRekPerusahaan.setText(tfIDIndividual.getText()+"01");
            }
        } catch (Exception e) {
            lblAddStatusPerusahaan.setText("failed company account");
        }
    }

    @FXML
    void handleAddRekBaruIndividu(ActionEvent event) {
        try {
            nasabahDataModel.addRekening(Integer.parseInt(tfIDNasabahBaruIndividual.getText()),
                    new Rekening(Integer.parseInt(tfNoRekBaruIndividual.getText()),
                    Double.parseDouble(tfSaldoRekBaruIndividual.getText())));
            btnPerbaruiIndividual.fire();
            tfSaldoRekBaruIndividual.setText("");
            showRekeningIndividu(Integer.parseInt(tfIDNasabahBaruIndividual.getText()));
            tfIDIndividual.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
            tfNoRekIndividual.setText(tfIDIndividual.getText()+"01");
            tfIDPerusahaan.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
            tfNoRekPerusahaan.setText(tfIDIndividual.getText()+"01");
        }catch (Exception E){
            System.out.println(E);
        }
    }

    @FXML
    void handleAddRekBaruPerusahaan(ActionEvent event) {
        try {
            nasabahDataModel.addRekening(Integer.parseInt(tfIDNasabahBaruPerusahaan.getText()),
                    new Rekening(Integer.parseInt(tfNoRekBaruPerusahaan.getText()),
                            Double.parseDouble(tfSaldoRekBaruPerusahaan.getText())));
            btnPerbaruiPerusahaan.fire();
            tfSaldoRekBaruPerusahaan.setText("");
            showRekeningCompany(Integer.parseInt(tfIDNasabahBaruPerusahaan.getText()));
            tfIDIndividual.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
            tfNoRekIndividual.setText(tfIDIndividual.getText()+"01");
            tfIDPerusahaan.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
            tfNoRekPerusahaan.setText(tfIDIndividual.getText()+"01");
        }catch (Exception E){
            System.out.println(E);
        }
    }

    @FXML
    void handleAddSaldoIndividu(ActionEvent event){
        try {
            rekeningsGB.tambahSaldo(Double.parseDouble(tfNominalIndividual.getText()));
            String tambahSaldo = "UPDATE Rekening SET saldo = " + rekeningsGB.getSaldo()
                    + " WHERE noRekening = " + rekeningsGB.getNoRekening();
            PreparedStatement preparedSaldoBaru = nasabahDataModel.connection.prepareStatement(tambahSaldo);
            preparedSaldoBaru.execute();
            preparedSaldoBaru.close();
            btnPerbaruiIndividual.fire();
            BTNclearIndividu.fire();
            showRekeningIndividu(Integer.parseInt(tfIDNasabahBaruIndividual.getText()));
            lblTarikTambahIndividu.setText("Berhasil!");
        }catch (SQLException E){
            System.out.println(E);
        }
    }

    @FXML
    void handleTarikTunaiIndividu(ActionEvent event){
        try {
            if (rekeningsGB.getSaldo() < Double.parseDouble(tfNominalIndividual.getText())) {
                lblTarikTambahIndividu.setText("Saldo Kurang");
            } else {
                rekeningsGB.tarikTunai(Double.parseDouble(tfNominalIndividual.getText()));
                String tarikTunai = "UPDATE Rekening SET saldo = " + rekeningsGB.getSaldo()
                        + " WHERE noRekening = " + rekeningsGB.getNoRekening();
                PreparedStatement preparedSaldoBaru = nasabahDataModel.connection.prepareStatement(tarikTunai);
                preparedSaldoBaru.execute();
                preparedSaldoBaru.close();
                btnPerbaruiIndividual.fire();
                BTNclearIndividu.fire();
                showRekeningIndividu(Integer.parseInt(tfIDNasabahBaruIndividual.getText()));
                lblTarikTambahIndividu.setText("Berhasil!");
            }
        }catch (SQLException E){
            System.out.println(E);
        }
    }

    @FXML
    void handleAddSaldoPerusahaan(ActionEvent event) {
        try {
            rekeningsGB.tambahSaldo(Double.parseDouble(tfNominalPerusahaan.getText()));
            String tambahSaldo = "UPDATE Rekening SET saldo = " + rekeningsGB.getSaldo()
                    + " WHERE noRekening = " + rekeningsGB.getNoRekening();
            PreparedStatement preparedSaldoBaru = nasabahDataModel.connection.prepareStatement(tambahSaldo);
            preparedSaldoBaru.execute();
            preparedSaldoBaru.close();
            btnPerbaruiPerusahaan.fire();
            btnHapusPerusahaan.fire();
            showRekeningCompany(Integer.parseInt(tfIDNasabahBaruPerusahaan.getText()));
            lblTambahTarikPerusahaan.setText("Berhasil!");
        }catch (SQLException E){
            System.out.println(E);
        }
    }

    @FXML
    void handleTarikTunaiPerusahaan(ActionEvent event) {
        try {
            if (rekeningsGB.getSaldo() < Double.parseDouble(tfNominalPerusahaan.getText())) {
                lblTarikTambahIndividu.setText("Saldo Kurang");
            } else {
                rekeningsGB.tarikTunai(Double.parseDouble(tfNominalPerusahaan.getText()));
//            Double saldoBaru = globalRekening.getSaldo() - Double.parseDouble(tfNominalPerusahaan.getText());
                String tarikTunai = "UPDATE Rekening SET saldo = " + rekeningsGB.getSaldo()
                        + " WHERE noRekening = " + rekeningsGB.getNoRekening();
                PreparedStatement preparedSaldoBaru = nasabahDataModel.connection.prepareStatement(tarikTunai);
                preparedSaldoBaru.execute();
                preparedSaldoBaru.close();
                btnPerbaruiPerusahaan.fire();
                btnHapusPerusahaan.fire();
                showRekeningCompany(Integer.parseInt(tfIDNasabahBaruPerusahaan.getText()));
                lblTambahTarikPerusahaan.setText("Berhasil!");
            }
        }catch (SQLException E){
            System.out.println(E);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nasabahDataModel = new NasabahDataModel();
            lblDBStatus.setText(nasabahDataModel.connection == null ? "Not Connected" : "Connected");
            tfIDIndividual.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
            tfNoRekIndividual.setText(tfIDIndividual.getText()+"01");
            tfIDPerusahaan.setText(String.valueOf(nasabahDataModel.nextNasabahID()));
            tfNoRekPerusahaan.setText(tfIDIndividual.getText()+"01");
            tblDataIndividu.getSelectionModel().selectedIndexProperty().addListener(listener -> {
                if (tblDataIndividu.getSelectionModel().getSelectedItem() != null) {
                    Individu individu =tblDataIndividu.getSelectionModel().getSelectedItem();
                    individu.print();
                    showRekeningIndividu(individu.getId());
                    btnTambahRekBaruIndividual.setDisable(false);
                    tfSaldoRekBaruIndividual.setDisable(false);
                    tfIDNasabahBaruIndividual.setText("" + individu.getId());
                    try {
                        tfNoRekBaruIndividual.setText("" + nasabahDataModel.nextNoRekening(individu.getId()));
                    } catch (Exception ex) {
                        System.out.println("Gagal load data rekening");
                    }
                }
            });

            tblDataPerusahaan.getSelectionModel().selectedIndexProperty().addListener(listener -> {
                if (tblDataPerusahaan.getSelectionModel().getSelectedItem() != null) {
                    Perusahaan perusahaan = tblDataPerusahaan.getSelectionModel().getSelectedItem();
                    perusahaan.print();
                    showRekeningCompany(perusahaan.getId());
                    btnTambahRekBaruPerusahaan.setDisable(false);
                    tfSaldoRekBaruPerusahaan.setDisable(false);
                    tfIDNasabahBaruPerusahaan.setText("" + perusahaan.getId());
                    try {
                        tfNoRekBaruPerusahaan.setText("" + nasabahDataModel.nextNoRekening(perusahaan.getId()));
                    } catch (Exception ex) {
                        System.out.println("Gagal load data rekening");
                    }
                }
            });

            tblRekeningIndividu.getSelectionModel().selectedIndexProperty().addListener(listener -> {
                if (tblRekeningIndividu.getSelectionModel().getSelectedItem() != null) {
                    rekeningsGB = tblRekeningIndividu.getSelectionModel().getSelectedItem();
                    btnTarikTunaiIndividual.setDisable(false);
                    btnTambahSaldoIndividual.setDisable(false);
                    tfNominalIndividual.setDisable(false);
                    tfRekeningDipilihIndividual.setText("" + rekeningsGB.getNoRekening());
                }
            });

            tblRekeningPerusahaan.getSelectionModel().selectedIndexProperty().addListener(listener -> {
                if (tblRekeningPerusahaan.getSelectionModel().getSelectedItem() != null) {
                    rekeningsGB = tblRekeningPerusahaan.getSelectionModel().getSelectedItem();
                    btnTarikTunaiPerusahaan.setDisable(false);
                    btnTambahSaldoPerusahaan.setDisable(false);
                    tfNominalPerusahaan.setDisable(false);
                    tfRekeningDipilihPerusahaan.setText("" + rekeningsGB.getNoRekening());
                }
            });
        } catch (Exception es) {
            System.out.println(es);
        }
    }

    public void showRekeningIndividu(int id) {
        ObservableList<Rekening> data = nasabahDataModel.getRekening(id);
        colNumRekIndividu.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
        colSaldoIndividu.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        tblRekeningIndividu.setItems(null);
        tblRekeningIndividu.setItems(data);
    }

    public void showRekeningCompany(int id) {
        ObservableList<Rekening> data = nasabahDataModel.getRekening(id);
        colNumRekPerusahaan.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
        colSaldoPerusahaan.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        tblRekeningPerusahaan.setItems(null);
        tblRekeningPerusahaan.setItems(data);
    }
}
