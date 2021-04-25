package datos;

import java.io.Serializable;
import static logica.Controlador.Cpline;

/**
 *
 * @author andres
 */
public class Historial implements Serializable{
    private String usuario;
    private String fecha;
    private String accion;
    
    public Historial (String usuario, String fecha, String accion) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.accion = accion;
    }
    
    public void print () {
        System.out.println(Cpline("* -- USUARIO: " + this.usuario));
        System.out.println(Cpline("* -- FECHA: " + this.fecha));
        System.out.println(Cpline("* -- ACCION: " + this.accion));
    }
}
