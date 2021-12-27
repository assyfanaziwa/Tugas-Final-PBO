package koperasi;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rekening {
    private IntegerProperty noRekening;
    private DoubleProperty saldo;

    public Rekening(int noRekening, double saldo){
        this.noRekening = new SimpleIntegerProperty(noRekening);
        this.saldo = new SimpleDoubleProperty(saldo);
    }

    public Integer getNoRekening() {
        return noRekening.get();
    }
    
    public IntegerProperty noRekeningProperty() {
        return noRekening;
    }

    public void setNoRekening(Integer noRekening) {
        this.noRekening.set(noRekening);
    }

    public double getSaldo() {
        return saldo.get();
    }

    public DoubleProperty saldoProperty() {
        return saldo;
    }
 public void setSaldo(Double saldo) {
        this.saldo.set(saldo);
    }

    public void tambahSaldo(Double jumlah){
        setSaldo(this.saldo.get() + jumlah);
    }

    public void tarikTunai(Double jumlah){
        setSaldo(this.saldo.get() - jumlah);
    }
}
    
