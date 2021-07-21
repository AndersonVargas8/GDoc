package datos;

public class Documento implements Comparable<Documento>{
    private int id;
    private String tipo, nombre, estante, carpeta, ingreso, expiración;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getExpiración() {
        return expiración;
    }

    public void setExpiración(String expiración) {
        this.expiración = expiración;
    }

    @Override
    public int compareTo(Documento documento) {
        return this.id - documento.id;
    }
}
