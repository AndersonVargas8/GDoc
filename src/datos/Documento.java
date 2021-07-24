package datos;

import gui.servicios.serviciosLogicos.FechaService;

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
        return this.id - documento.id;
    }

    public boolean estaVencido(){
        Fecha fechaActual = FechaService.getServicio().getFecha();

        if(this.expiracion.getAnnio() < fechaActual.getAnnio())
            return true;
        if(this.expiracion.getAnnio() > fechaActual.getAnnio())
            return false;

        if(this.expiracion.getMes() < fechaActual.getMes())
            return true;
        if(this.expiracion.getMes() > fechaActual.getMes())
            return false;

        if(this.expiracion.getDia() <= fechaActual.getDia())
            return true;

        return false;
    }
}
