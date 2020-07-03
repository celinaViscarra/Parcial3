package caav.parcial3.av12013;

public class Libro {
    //Clase que se modificara de acuerdo a lo solicitado.
    //En este caso libro.

    public String CODLIBRO, NOMLIBRO;
    public float PRECIO;

    public Libro(){}

    public String getCODLIBRO() {
        return CODLIBRO;
    }

    public void setCODLIBRO(String CODLIBRO) {
        this.CODLIBRO = CODLIBRO;
    }

    public String getNOMLIBRO() {
        return NOMLIBRO;
    }

    public void setNOMLIBRO(String NOMLIBRO) {
        this.NOMLIBRO = NOMLIBRO;
    }

    public float getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(float PRECIO) {
        this.PRECIO = PRECIO;
    }
}
