package koperasi;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;

public class Perusahaan extends Nasabah{
    private StringProperty nib;

    public Perusahaan(int id, String nama, String alamat, String nib, ArrayList<Rekening> rekening) {
        super(id, nama, alamat, rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    public Perusahaan(int id, String nama, String alamat,String nib, Rekening rekening) {
        super(id, nama, alamat, rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    public String getNib() {
        return nib.get();
    }

    public StringProperty nibProperty() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib.set(nib);
    }

    @Override
    public void print(){
        System.out.println("NIB                 : " + getNib());
        System.out.println("Nama Perusahan      : "+getNama());
        System.out.println("Alamat Perusahaan   : "+getAlamat());
        System.out.println("Rekening : ");
        System.out.printf("No %-10s %s\n", "No Rek", "Saldo");
        int i = 1;
        for (Rekening rek : rekening){
            System.out.printf("%d. %-10d %.2f\n", i, rek.getNoRekening(), rek.getSaldo());
            i++;
        }
        System.out.println();
    }
}
