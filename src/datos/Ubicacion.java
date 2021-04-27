package datos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import static logica.Controlador.Cpline;

/**
 * La clase Ubicación contiene las caracteristicas de una ubicación para las carpetas.
 * @author Anderson
 */
public class Ubicacion implements Serializable{
    private String seccion;
    private String estante;
    
    public Ubicacion (String seccion, String estante) {
        this.seccion = seccion;
        this.estante = estante;
    }
    
    public void setSeccion (BufferedReader reader) throws IOException {
        System.out.print("* Nueva sección:  ");
        String nueva_seccion = reader.readLine();
        this.seccion = nueva_seccion;
    }
    
    public void setEstante (BufferedReader reader) throws IOException {
        System.out.print("* Nuevo estante:  ");
        String nuevo_estante = reader.readLine();
        this.estante = nuevo_estante;
    }
    
    public void print () {
        System.out.println(Cpline("* -- SECCION: " + this.seccion));
        System.out.println(Cpline("* -- ESTANTE: " + this.estante));
    }
}
