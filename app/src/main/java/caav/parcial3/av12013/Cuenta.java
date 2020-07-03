package caav.parcial3.av12013;

public class Cuenta {
    private String  codcuenta;
    private String  nomcuenta;
    private float   saldoinicial;
    private float   saldodebe;
    private float   saldohaber;

    public Cuenta(){

    }
    public Cuenta(String codcuenta, String nomcuenta, float saldoinicial, float saldodebe, float saldohaber) {
        this.codcuenta = codcuenta;
        this.nomcuenta = nomcuenta;
        this.saldoinicial = saldoinicial;
        this.saldodebe = saldodebe;
        this.saldohaber = saldohaber;
    }

    public String getCodcuenta() {
        return codcuenta;
    }

    public void setCodcuenta(String codcuenta) {
        this.codcuenta = codcuenta;
    }

    public String getNomcuenta() {
        return nomcuenta;
    }

    public void setNomcuenta(String nomcuenta) {
        this.nomcuenta = nomcuenta;
    }

    public float getSaldoinicial() {
        return saldoinicial;
    }

    public void setSaldoinicial(float saldoinicial) {
        this.saldoinicial = saldoinicial;
    }

    public float getSaldodebe() {
        return saldodebe;
    }

    public void setSaldodebe(float saldodebe) {
        this.saldodebe = saldodebe;
    }

    public float getSaldohaber() {
        return saldohaber;
    }

    public void setSaldohaber(float saldohaber) {
        this.saldohaber = saldohaber;
    }
}
