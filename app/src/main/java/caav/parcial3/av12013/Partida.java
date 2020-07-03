package caav.parcial3.av12013;

public class Partida {
    private String numpartida;
    private String corr;
    private String codcuenta;
    private String concepto;
    private String car_abo;
    private float  valor;

    public Partida(){

    }
    public Partida(String numpartida, String corr, String codcuenta, String concepto, String car_abo, float valor) {
        this.numpartida = numpartida;
        this.corr = corr;
        this.codcuenta = codcuenta;
        this.concepto = concepto;
        this.car_abo = car_abo;
        this.valor = valor;
    }

    public String getNumpartida() {
        return numpartida;
    }

    public void setNumpartida(String numpartida) {
        this.numpartida = numpartida;
    }

    public String getCorr() {
        return corr;
    }

    public void setCorr(String corr) {
        this.corr = corr;
    }

    public String getCodcuenta() {
        return codcuenta;
    }

    public void setCodcuenta(String codcuenta) {
        this.codcuenta = codcuenta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCar_abo() {
        return car_abo;
    }

    public void setCar_abo(String car_abo) {
        this.car_abo = car_abo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
