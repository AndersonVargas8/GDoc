package datos;

public class Documento implements Comparable<Documento>{
    private int id;
    private String tipo, nombre, estante, carpeta;
    private Fecha ingreso,expiracion;

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

    public Fecha getIngreso() {
        return ingreso;
    }

    public void setIngreso(Fecha ingreso) {
        this.ingreso = ingreso;
    }

    public Fecha getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Fecha expiracion) {
        this.expiracion = expiracion;
    }

    @Override
    public int compareTo(Documento documento) {
        /*if(this.expiracion.getAnnio() == -1 || documento.expiracion.getAnnio() == -1)
            return this.id - documento.id;

        if(this.expiracion.getAnnio() - documento.expiracion.getAnnio() != 0)
            return this.expiracion.getAnnio() - documento.ingreso.getAnnio();

        if(this.expiracion.getMes() - documento.expiracion.getMes() != 0)
            return this.expiracion.getMes() - documento.expiracion.getMes();

        if(this.expiracion.getDia() - documento.expiracion.getDia() != 0)
            return this.expiracion.getDia() - documento.expiracion.getDia();*/

        return documento.id - this.id;
    }
}
