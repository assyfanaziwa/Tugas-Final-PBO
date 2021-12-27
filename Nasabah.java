package koperasi;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;


public abstract class Nasabah {
    protected IntegerProperty id;
    protected StringProperty nama;
    protected StringProperty alamat;
    private IntegerProperty rekNum;
    protected ArrayList <Rekening> rekening;

    public Nasabah(int id, String nama, String alamat, ArrayList<Rekening> rekening) {
        this.id = new SimpleIntegerProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekNum = new SimpleIntegerProperty(rekening.size());
        this.rekening = rekening;
    }

    public Nasabah(int id, String nama, String alamat, Rekening rekenings) {
        rekening = new ArrayList<>();
        this.id = new SimpleIntegerProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekNum = new SimpleIntegerProperty(rekening.size());
        this.rekening.add(rekenings);
    }

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNama() {
        return nama.get();
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getAlamat() {
        return alamat.get();
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public Integer getRekNum() {
        return rekNum.get();
    }

    public IntegerProperty rekNumProperty() {
        return rekNum;
    }

    public void setRekNum(int rekNum) {
        this.rekNum.set(rekNum);
    }

    public ArrayList<Rekening> getRekening() {
        return rekening;
    }

    public void setRekening(ArrayList<Rekening> rekening) {
        this.rekening = rekening;
    }

    abstract public void print();

}
