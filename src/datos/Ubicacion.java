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
    
    public Ubicacion (String seccion, String estante) {                     //Costructor
        this.seccion = seccion;
        this.estante = estante;
    }
    
    public void setSeccion (BufferedReader reader) throws IOException {     //Guarda en que sección está guardado un documento
        System.out.print("* Nueva sección:  ");
        String nueva_seccion = reader.readLine();
        this.seccion = nueva_seccion;
    }
    
    public void setEstante (BufferedReader reader) throws IOException {     //Guarda en que estante está guardado un documento
        System.out.print("* Nuevo estante:  ");
        String nuevo_estante = reader.readLine();
        this.estante = nuevo_estante;
    }
    
    public void print () {
        System.out.println(Cpline("* -- SECCION: " + this.seccion));        //Imprime la sección y el estando donde se encuentra el documentro seleccionado
        System.out.println(Cpline("* -- ESTANTE: " + this.estante));
    }
}
