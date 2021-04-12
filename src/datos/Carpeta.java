package datos;

import java.util.logging.Logger;

/**
 * La clase Carpeta permite agrupar diferentes documentos, definiendo la ubicación donde estarán almacenados.
 * @author Anderson
 */
public class Carpeta {
    private String ubicacion;
    private int id;
    private String nombre;

    public Carpeta(String ubicacion, int id, String nombre) {
        this.ubicacion = ubicacion;
        this.id = id;
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Carpeta: " + "Nombre = " + nombre  + ", Ubicacion = " + ubicacion;
    }
    
    
    
}
