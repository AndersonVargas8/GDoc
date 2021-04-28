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
    
    /**
     *Construye una carpeta con una ubicación, id y nombre especificados
     * @param ubicacion
     * @param id
     * @param nombre
     */
    public Carpeta(String ubicacion, int id, String nombre) {           //Constructor
        this.ubicacion = ubicacion;
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Retorna la ubicación de la carpeta.
     * @return la ubicación de la carpeta.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     *Establece la ubicación de la carpeta con una especificada.
     * @param ubicacion
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     *Retorna el identificador de la carpeta.
     * @return el identificador de la carpeta.
     */
    public int getId() {
        return id;
    }

    /**
     *Establece el identificadaor de la carpeta.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Retorna el nombre de la carpeta.
     * @return el nombre de la carpeta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *Establece el nombre de la carpeta con uno especificado.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {                      //Convierte en String las diferentes variables de la clase
        return "Carpeta "+id+": " + "Nombre = " + nombre  + ", Ubicacion = " + ubicacion;
    }
    
    
    
}
